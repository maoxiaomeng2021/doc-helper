package top.healthylife.gzx.dochelper;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import top.healthylife.gzx.dochelper.config.DocBaseConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 功能描述: Excel2Json
 *
 * @author maoxiaomeng
 * @date 20230609
 */
public class ExcelConfig2JsonHelper {


    static final File f = new File("C:\\Users\\maoxiaomeng\\Desktop\\详设目录.xlsx");

    static final Map<String, DocMenu> MENU_MANAGE = new HashMap<>();

    public static void main(String[] args) {

        List<DocMenu> parse = parseCommonLeafMenuSheet(f);
        System.out.println("parse = " + JSONUtil.toJsonPrettyStr(parse));
    }

    public static DocMenu str2Menu(String menuName) {

        DocMenu docMenu = new DocMenu();

        if (menuName.contains(":")) {
            String[] split = menuName.split(":");
            boolean isEnd = BooleanUtil.toBoolean(split[1]);
            docMenu.setNoLeaf(isEnd);
            docMenu.setName(split[0]);
        } else {
            docMenu.setNoLeaf(false);
            docMenu.setName(menuName);
        }
        docMenu.setChildren(new ArrayList<>());
        return docMenu;
    }

    /**
     * 类似于国标编码形式
     * <p>
     * 引言
     * 引言	目的:true
     * 引言	编写依据:true
     * 引言	术语定义:true
     * 详细设计规则
     * 详细设计规则	设计原则:true
     * 详细设计规则	编码规范:true
     * 程序系统的结构
     * 程序系统的结构	管理后台
     * 程序系统的结构	管理后台	数据服务中心
     * 程序系统的结构	管理后台	数据服务中心	API管理
     * 程序系统的结构	管理后台	元数据
     * 程序系统的结构	管理后台	元数据	元数据管理
     *
     * @param f file
     * @return base菜单config
     */
    public static List<DocMenu> parseBaseMenuSheet(File f) {
        ExcelReader reader = ExcelUtil.getReader(f);
        reader.setSheet(0);
        List<List<Object>> rows = reader.read();
        ArrayList<DocMenu> docMenus = new ArrayList<>();
        for (List<Object> row : rows) {
            String s = row.stream().map(Object::toString).collect(Collectors.joining("."));
            String[] split = s.split("\\.");
            int length = split.length;
            int parentIndex = Math.max(length - 2, 0);
            String parentName = split[parentIndex];
            String currentName = split[length - 1];
            DocMenu docMenu = str2Menu(currentName);
            MENU_MANAGE.put(docMenu.name, docMenu);
            if (split.length > 1) {
                //找到父节点
                DocMenu docMenu1 = MENU_MANAGE.get(parentName);
                List<DocMenu> children = docMenu1.getChildren();
                if (children == null) {
                    children = new ArrayList<>();
                }
                children.add(docMenu);
            } else {
                docMenus.add(docMenu);
            }
        }
        return docMenus;
    }

    /**
     * 普通 <K,V>形式
     *
     * @param f file
     * @return leaf的base配置
     */
    public static List<DocMenu> parseCommonLeafMenuSheet(File f) {
        ExcelReader reader = ExcelUtil.getReader(f);
        reader.setSheet(1);
        List<DocBaseConfig> docBaseConfigs = reader.readAll(DocBaseConfig.class);
        return docBaseConfigs.stream().map(DocBaseConfig::leaf2DocMenu).collect(Collectors.toList());
    }

    public static void parseLeafCustomConfig(File f) {
        ExcelReader reader = ExcelUtil.getReader(f);
        reader.setSheet(2);
        List<DocBaseConfig> docBaseConfigs = reader.readAll(DocBaseConfig.class);
        docBaseConfigs.forEach(DocBaseConfig::customLeaf2DocMenu);
    }

    public static List<DocBaseConfig> parseCustomConfig(File f) {
        ExcelReader reader = ExcelUtil.getReader(f);
        reader.setSheet(3);
        return reader.readAll(DocBaseConfig.class);
    }


}
