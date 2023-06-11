package top.healthylife.gzx.dochelper;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import life.gzx.docx4jhelper.enums.Docx4jStyle;
import life.gzx.docx4jhelper.utils.WordProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import top.healthylife.gzx.dochelper.config.DocBaseConfig;

import java.io.File;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 功能描述: DocHelper
 *
 * @author maoxiaomeng
 * @date 20230609
 */
@SuppressWarnings("all")
@Slf4j
public class DocHelper {

    public static final File configFile = new File(defaultConfig());

    public static String defaultConfig() {
        return System.getProperty("user.dir") + File.separator + "target" + File.separator + "classes" + File.separator + "DocHelper配置表.xlsx";
    }

    /**
     * 这里会有并发问题,但是目前不打算支持并发调用
     */
    static List<DocMenu> staticMenu;
    String swaggerUrl = "";


    /**
     * Let's start from here
     */
    public static void start(File config) {
        DocBaseConfig.initLocalConfig(configFile);
        WordProcessor.DocumentBuilder documentBuilder = WordProcessor.startDocument();
        DocHelper.buildWordContent(DocBaseConfig.docTitleTrees, documentBuilder);
        String docDir = DocBaseConfig.getConfigByKey("docDir");
        documentBuilder.builder(new File(docDir));
        log.info("生成完毕,目录:{}", docDir);
    }


    /**
     * Let's start from here
     */
    public static void start(MultipartFile multipartFile, OutputStream outputStream) {
        //初始化配置
        DocBaseConfig.initRemoteConfig(multipartFile);
        //打印配置树
        log.info("打印配置树:{}", JSONUtil.toJsonPrettyStr(DocBaseConfig.docTitleTrees));
        //打印配置菜单
        //DocHelper.printTitle(DocBaseConfig.docTitleTrees, 0);
        WordProcessor.DocumentBuilder documentBuilder = WordProcessor.startDocument();
        DocHelper.buildWordContent(DocBaseConfig.docTitleTrees, documentBuilder);
        documentBuilder.builder(outputStream);
        log.info("生成完毕");
    }

    /**
     * 从指定json获取标题
     *
     * @return 标题列表树
     */
    static List<DocTitleTree> getWordTitle(String titleJson) {
        JSONArray objects = JSONUtil.parseArray(titleJson);
        List<DocMenu> collect = objects.stream().map(e -> JSONUtil.toBean(JSONUtil.toJsonStr(e), DocMenu.class)).collect(Collectors.toList());
        return buildList(null, collect, 0);
    }

    /**
     * 从指定excel读取
     *
     * @return 标题列表树
     */
    public static List<DocTitleTree> buildDocTitleTreeList() {
        List<DocMenu> baseMenuConfig = DocBaseConfig.baseMenuConfig;
        try {
            return buildList(null, baseMenuConfig, 0);
        } catch (Exception e) {
            throw new BusinessException("构建基础菜单目录树失败,请检查配置是否正确,错误信息:" + e.getMessage());
        }
    }

    /**
     * 构建标题列表树
     *
     * @param parentNode 父标题节点
     * @param docMenus   菜单
     * @param level      层级
     * @return 标题列表树
     */
    static List<DocTitleTree> buildList(DocTitleTree parentNode, List<DocMenu> docMenus, int level) {
        level++;
        if (ObjectUtil.isEmpty(docMenus)) {
            return Collections.emptyList();
        }
        List<DocTitleTree> objects = new ArrayList<>();
        for (int i = 0; i < docMenus.size(); i++) {
            DocMenu docMenu = docMenus.get(i);
            DocTitleTree docTitleTree = menu2WordTitle(docMenu, parentNode, i, level);

            List<DocMenu> children = docMenu.getChildren();
            if (ObjectUtil.isEmpty(children)) {
                //如果目录声明了结束,则不会追加叶子目录节点
                if (docMenu.hasLeaf()) {
                    //叶子节点
                    //追加固定目录
                    List<DocMenu> leafMenu = DocBaseConfig.leafCommonConfig;
                    Map<String, Map<String, DocMenu>> customLeafContent = DocBaseConfig.customLeafContent;
                    String hKey = docMenu.getName();
                    //如果叶子节点有自定义配置,则使用自定义配置
                    if (customLeafContent.containsKey(hKey)) {
                        Map<String, DocMenu> stringDocMenuMap = customLeafContent.get(hKey);
                        Collection<DocMenu> values = stringDocMenuMap.values();
                        leafMenu = new ArrayList<>(values);
                    }
                    //检查当前节点是否有自定义目录,如有则使用
                    List<DocTitleTree> docTitleTrees = buildList(docTitleTree, leafMenu, level);
                    docTitleTree.setChildren(docTitleTrees);
                } else {
                    //如果目录声明了结束,则不会追加叶子目录节点
                    List<DocTitleTree> docTitleTrees = new ArrayList<>();
                    docTitleTree.setChildren(docTitleTrees);
                    if (!docTitleTree.isHasContent() && DocBaseConfig.needBr()) {
                        docTitleTree.setHasContent(true);
                        docTitleTree.setDocContent(ListUtil.of(DocContent.buildBlankParagraph()));
                    }

                }
            } else {
                List<DocTitleTree> docTitleTrees = buildList(docTitleTree, children, level);
                docTitleTree.setChildren(docTitleTrees);
                //每一个标题新增个空白段落
                if (!docTitleTree.isHasContent() && DocBaseConfig.needBr()) {
                    docTitleTree.setHasContent(true);
                    docTitleTree.setDocContent(ListUtil.of(DocContent.buildBlankParagraph()));
                }

            }
            objects.add(docTitleTree);
        }
        return objects;
    }

    /**
     * 将简单的menu对象转换为标题树对象
     *
     * @param docMenu    菜单
     * @param parentNode 上级节点
     * @param index      菜单索引
     * @param level      层级
     * @return 标题树对象
     */
    public static DocTitleTree menu2WordTitle(DocMenu docMenu, DocTitleTree parentNode, int index, int level) {
        DocTitleTree docTitleTree = new DocTitleTree();
        docTitleTree.setLevel(level)
                .setFontFamily("宋体")
                .setTitleName(docMenu.getName())
                .setIndex(index + 1)
                .setDocContent(docMenu.getDocContents())
                .setHasContent(ObjectUtil.isNotEmpty(docMenu.getDocContents()))
                .setParentNo(parentNode == null ? "" : parentNode.getNo())
                .setParentName(parentNode == null ? "" : parentNode.printName());
        return docTitleTree;
    }


    /**
     * 根据接口id列表生成接口文档
     *
     * @param apiId 接口id列表
     * @return 接口文档
     */
    public static List<DocContent> getApiInfoByApiId(String... apiId) {
        return null;
    }

    public static Object swaggerInfo(String url) {
        return HttpUtil.createGet(url).execute().body();
    }


    public static List<DocMenu> getCommonLeafMenu() {
        return JSONUtil.parseArray(staticMenuJson).stream().map(e -> JSONUtil.parse(e).toBean(DocMenu.class)).collect(Collectors.toList());
    }

    public static List<DocMenu> getCommonLeafMenuByExcel(File file) {
        return ExcelConfig2JsonHelper.parseCommonLeafMenuSheet(file, DocConfigSheet.COMMON_LEAF_CONFIG);
    }


    /**
     * 缩进显示
     *
     * @param titles
     * @param indent
     */
    public static void printTitle(List<DocTitleTree> titles, int indent) {
        indent = indent + 4;
        if (ObjectUtil.isEmpty(titles)) {
            return;
        }
        for (DocTitleTree title : titles) {
            String indentName = String.format("%-" + (indent + 4) + "s", "") + title.getTitle();
            System.out.println(indentName);

            if (title.hasChildren()) {
                titles = title.getChildren();

                printTitle(titles, indent);
            }
        }
    }

    public static void buildWordContent(List<DocTitleTree> titles, WordProcessor.DocumentBuilder documentBuilder) {
        if (ObjectUtil.isEmpty(titles)) {
            return;
        }
        for (DocTitleTree title : titles) {
            documentBuilder.addStyledParagraph(getDocxStyle(title), title.getTitle());
            if (title.isHasContent()) {
                List<DocContent> docContent = title.getDocContent();
                docContent.forEach(e -> {
                    buildByContent(documentBuilder, e);
                });
            }
            if (title.hasChildren()) {
                titles = title.getChildren();
                buildWordContent(titles, documentBuilder);
            }
        }
    }

    public static Docx4jStyle getDocxStyle(DocTitleTree title) {
        Integer level = title.getLevel();
        switch (level) {
            case 1:
                return Docx4jStyle.Heading1;
            case 2:
                return Docx4jStyle.Heading2;
            case 3:
                return Docx4jStyle.Heading3;
            case 4:
                return Docx4jStyle.Heading4;
            case 5:
                return Docx4jStyle.Heading5;
            case 6:
                return Docx4jStyle.Heading6;
            case 7:
                return Docx4jStyle.Heading7;
            case 8:
                return Docx4jStyle.Heading8;
            case 9:
                return Docx4jStyle.Heading9;
            default:
                return Docx4jStyle.Title;

        }
    }

    public static void buildByContent(WordProcessor.DocumentBuilder documentBuilder, DocContent docContent) {
        DocContentType dataType = docContent.getDataType();
        Object content = docContent.getContent();
        if (content == null) {
            return;
        }
        String docContentStr = StrUtil.toString(content);
        switch (dataType) {
            case API:
            case HTML:
                documentBuilder.addHtml(docContentStr);
                return;
            case TABLE:
                documentBuilder.addDynamicDataTable((List<LinkedHashMap<String, Object>>) content);
                return;
            case IMAGE:
                documentBuilder.addImage(docContentStr, "doc-generator");
                return;
            case TEXT:
                String[] split = docContentStr.split("\n");
                Arrays.stream(split).forEach(e -> {
                    documentBuilder.addParagraph(e);
                });
                return;
            default:
                //ignore;
        }
    }

    static String staticMenuJson = "[\n" +
            "\n" +
            "{\n" +
            "\"name\":\"程序描述\",\n" +
            " \"children\":[] ,\n" +
            "  \"isEnd\":true\n" +
            "\n" +
            "},\n" +
            "  {\n" +
            "\"name\":\"功能\",\n" +
            " \"children\":[] ,\n" +
            "  \"isEnd\":true\n" +
            "\n" +
            "},\n" +
            "\n" +
            "  {\n" +
            "\"name\":\"性能\",\n" +
            " \"children\":[] ,\n" +
            "  \"isEnd\":true\n" +
            "\n" +
            "},\n" +
            "\n" +
            "  {\n" +
            "\"name\":\"输入项\",\n" +
            " \"children\":[] ,\n" +
            "  \"isEnd\":true\n" +
            "\n" +
            "},\n" +
            "\n" +
            "  {\n" +
            "\"name\":\"输出项\",\n" +
            " \"children\":[] ,\n" +
            "  \"isEnd\":true\n" +
            "\n" +
            "},\n" +
            "\n" +
            "  {\n" +
            "\"name\":\"算法\",\n" +
            " \"children\":[] ,\n" +
            "  \"isEnd\":true\n" +
            "\n" +
            "},\n" +
            "\n" +
            "  {\n" +
            "\"name\":\"流程逻辑\",\n" +
            " \"children\":[] ,\n" +
            "  \"isEnd\":true\n" +
            "\n" +
            "},\n" +
            "\n" +
            "  {\n" +
            "\"name\":\"接口\",\n" +
            " \"children\":[] ,\n" +
            "  \"isEnd\":true\n" +
            "\n" +
            "},\n" +
            "\n" +
            "  {\n" +
            "\"name\":\"存储分配\",\n" +
            " \"children\":[] ,\n" +
            "  \"isEnd\":true\n" +
            "\n" +
            "},\n" +
            "\n" +
            "  {\n" +
            "\"name\":\"注释设计\",\n" +
            " \"children\":[] ,\n" +
            "  \"isEnd\":true\n" +
            "\n" +
            "},\n" +
            "  \n" +
            "  {\n" +
            "\"name\":\"限制条件\",\n" +
            " \"children\":[] ,\n" +
            "  \"isEnd\":true\n" +
            "\n" +
            "},\n" +
            "  \n" +
            "  {\n" +
            "\"name\":\"测试计划\",\n" +
            " \"children\":[] ,\n" +
            "  \"isEnd\":true\n" +
            "\n" +
            "},\n" +
            "  \n" +
            "  {\n" +
            "\"name\":\"尚未解决的问题\",\n" +
            " \"children\":[] ,\n" +
            "  \"isEnd\":true\n" +
            "\n" +
            "}\n" +
            "\n" +
            "  \n" +
            "]";

}
