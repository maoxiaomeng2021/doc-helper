package top.healthylife.gzx.dochelper.config;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import top.healthylife.gzx.dochelper.*;
import top.healthylife.gzx.swagger.SwaggerParser;

import java.io.File;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 功能描述: DocLeafBaseConfig
 *
 * @author maoxiaomeng
 * @date 20230610
 */
@Data
@Slf4j
public class DocBaseConfig {
    /**
     * Excel 对应 sheet配置项的标题key
     */
    String key;
    /**
     * Excel 对应 sheet配置项的标题value
     */
    String value;
    /**
     * Excel 对应 sheet配置项的标题 desc
     */
    String desc;
    /**
     * Excel 对应 sheet配置项的标题 hKey 目前只有自定义叶子项内容会用到此值
     */
    String hKey;

    /**
     * 配置是否已经加载
     */
    public static boolean isLoadConfig;

    /**
     * 基础菜单目录树
     */
    public static List<DocMenu> baseMenuConfig;
    /**
     * 叶子项菜单通用配置
     */
    public static List<DocMenu> leafCommonConfig;
    /**
     * 叶子项菜单自定义配置
     */
    public static List<DocMenu> leafCustomConfig;
    /**
     * 全局自定义配置
     */
    public static List<DocBaseConfig> globalCustomConfig;

    /**
     * 菜单标题列表树
     */
    public static List<DocTitleTree> docTitleTrees;


    /**
     * k:hKey: v: <key,value>
     */
    public static Map<String, Map<String, DocMenu>> customLeafContent;

    public static void initConfig(File file) {
        if(isLoadConfig){
           return;
        }
        log.info("初始化配置文件:{}", file.getPath());
        DocConfigSheet config = DocConfigSheet.GLOBAL_CONFIG;
        try {
            //全局配置
            globalCustomConfig = ExcelConfig2JsonHelper.parseGlobalCustomConfig(file, config);
            //通用叶子节点配置
            config = DocConfigSheet.COMMON_LEAF_CONFIG;
            leafCommonConfig = ExcelConfig2JsonHelper.parseCommonLeafMenuSheet(file, config);
            //基础菜单配置
            config = DocConfigSheet.BASE_MENU_CONFIG;
            DocBaseConfig.baseMenuConfig = ExcelConfig2JsonHelper.parseBaseMenuSheet(file, config);
            //自定义叶子节点配置
            config = DocConfigSheet.CUSTOM_LEAF_CONFIG;
            ExcelConfig2JsonHelper.parseLeafCustomConfig(file, config);
        } catch (Exception e) {
            throw new BusinessException(config.desc + "导入解析失败,错误信息" + e.getMessage());
        }
        docTitleTrees = DocHelper.buildDocTitleTreeList();
    }

    public List<DocContent> getContent() {
        if (value == null) {
            if (needBr()) {
                return ListUtil.of(DocContent.buildBlankParagraph());
            }
            return Collections.emptyList();
        }
        return parseValue();
    }

    public void customLeaf2DocMenu() {
        DocMenu docMenu = new DocMenu();
        String hKey = this.getHKey();
        String key = this.getKey();
        String value = this.getValue();
        docMenu.setName(key);
        if (ObjectUtil.isEmpty(value)) {
            //如果自定义配置项为null,读取通用配置
            Map<String, DocMenu> commonLeafMap = leafCommonConfig.stream().collect(Collectors.toMap(DocMenu::getName, Function.identity()));
            DocMenu commonLeafConfig = commonLeafMap.get(key);
            docMenu.setDocContents(commonLeafConfig.getDocContents());
        } else {
            docMenu.setDocContents(this.getContent());
        }
        docMenu.setNoLeaf(true);
        docMenu.setChildren(Collections.emptyList());
        if (customLeafContent == null) {
            customLeafContent = new LinkedHashMap<>(64);
        }
        if (customLeafContent.containsKey(hKey)) {
            Map<String, DocMenu> stringDocMenuMap = customLeafContent.get(hKey);
            if (!stringDocMenuMap.containsKey(key)) {
                stringDocMenuMap.put(key, docMenu);
            }
        } else {
            Map<String, DocMenu> keyMap = new LinkedHashMap<>(16);
            keyMap.put(key, docMenu);
            customLeafContent.put(hKey, keyMap);
        }
    }

    public static Boolean needBr() {
        String appendBr = DocBaseConfig.getConfigByKey("appendBr");
        return BooleanUtil.toBoolean(appendBr);
    }

    public DocMenu leaf2DocMenu() {
        DocMenu docMenu = new DocMenu();
        String key = this.getKey();
        docMenu.setName(key);
        docMenu.setDocContents(this.getContent());
        docMenu.setNoLeaf(true);
        docMenu.setChildren(Collections.emptyList());
        return docMenu;
    }


    /**
     * 解析叶子节项菜单的值
     *
     * @return 解析叶子节项菜单的值
     */
    public List<DocContent> parseValue() {
        DocContentType plainType = DocContentType.getPlainType(value);
        List<DocContent> contents = new ArrayList<>();
        switch (plainType) {
            case TEXT:
            case HTML:
            case TABLE:
                contents.add(DocContent.buildDocContent(plainType, value));
                break;
            case API:
                String plainText = DocContentType.getPlainText(DocContentType.API, value);
                //多个接口名称
                String[] split = plainText.split(",");
                //获取接口信息,组装成DocContent
                DocContent apiInfo = getApiInfo(split);
                contents.add(apiInfo);
                break;
            case IMAGE:
                String imageUrl = DocContentType.getPlainText(DocContentType.IMAGE, value);
                contents.add(DocContent.buildDocContent(plainType, imageUrl));
                break;
            default:
                throw new BusinessException("未知的文本类型" + plainType);
        }
        return contents;
    }

    /**
     * 解析解析叶子节项菜单的值-api信息
     *
     * @param apiPath 即swagger文档的 path
     * @return word内容
     */
    public static DocContent getApiInfo(String... apiPath) {
        log.info("获取接口信息:{}", (Object) apiPath);
        String tableHtml = SwaggerParser.buildApiHtmlChunk(getConfigByKey("swaggerUrl"), getConfigByKey("apiInfoTpl"), apiPath);
        return DocContent.buildDocContent(DocContentType.HTML, tableHtml);
    }


    public static String getConfigByKey(String key) {
        if (ObjectUtil.isEmpty(globalCustomConfig)) {
            throw new BusinessException("全局配置未加载");
        }
        DocBaseConfig docBaseConfig = globalCustomConfig.stream().filter(e -> e.getKey().equalsIgnoreCase(key)).findAny().orElseThrow(() -> new BusinessException("未找到配置项" + key));
        return docBaseConfig.value;
    }

    /**
     * properties
     */
    static String swaggerUrl;
    static String docDir;
    static String appendBr;
}
