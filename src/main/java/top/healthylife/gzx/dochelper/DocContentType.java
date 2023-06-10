package top.healthylife.gzx.dochelper;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能描述: DocContentType
 *
 * @author maoxiaomeng
 * @date 20230609
 */
@Getter
@AllArgsConstructor
@Slf4j
public enum DocContentType {
    /**
     * doc内容分类
     */
    TEXT("\\$\\{TEXT:(.*?)}"),
    IMAGE("\\$\\{IMAGE:(.*?)}"),
    TABLE("\\$\\{TABLE:(.*?)}"),
    HTML("\\$\\{HTML:(.*?)}"),
    //目前api是会当成html来生成,可以更好的使用html来创建表格,比word创建处理表格easy很多
    API("\\$\\{API:(.*?)}");

    public String regex;
    /**
     * 获取内容的类型
     *
     * @param content
     * @return
     */
    public static DocContentType getPlainType(String content) {
        DocContentType[] values = values();
        for (DocContentType value : values) {
            String regex = value.getRegex();
            Pattern r = Pattern.compile(regex);
            Matcher m = r.matcher(content);
            List<String> res = new ArrayList<>();
            while (m.find()) {
                res.add(m.group());
            }
            if (ObjectUtil.isNotEmpty(res)) {
                log.info("接口如下:{}", res);
                return value;
            }
        }
        return TEXT;
    }

    public static String getPlainText(DocContentType type, String content) {
        if (type == DocContentType.TEXT) {
            return content;
        }
        List<String> strings = new ArrayList<>();
        String regex = type.getRegex();
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(content);
        while (m.find()) {
            String group = m.group();
            // 去除 前缀 ${xxx:  和后缀 }
            group = group.substring(type.name().length() + 3, group.length() - 1);
            strings.add(group);
        }
        return StrUtil.join(",", strings);
    }

    public static void main(String[] args) {
        String value = "${API:/api/dc/dms/dmsDataStandard/add},\n" +
                "${API:/api/dc/dms/dmsDataStandard/delete},\n" +
                "${API:/api/dc/dms/dmsDataStandard/detail/{code}},\n" +
                "${API:/api/dc/dms/dmsDataStandard/export},\n" +
                "${API:/api/dc/dms/dmsDataStandard/importExcel},\n" +
                "${API:/api/dc/dms/dmsDataStandard/list},\n" +
                "${API:/api/dc/dms/dmsDataStandard/modify},\n" +
                "${API:/api/dc/dms/dmsDataStandard/page},\n" +
                "${API:/api/dc/dms/dmsDataStandard/tableFieldMapping},\n" +
                "${API:/api/dc/dms/dmsDataStandard/template},\n" +
                "${API:/api/dc/dms/dmsDataDict/add},\n" +
                "${API:/api/dc/dms/dmsDataDict/delete},\n" +
                "${API:/api/dc/dms/dmsDataDict/detail/{code}},\n" +
                "${API:/api/dc/dms/dmsDataDict/list},\n" +
                "${API:/api/dc/dms/dmsDataDict/modify},\n" +
                "${API:/api/dc/dms/dmsDataDict/page},\n" +
                "${API:/api/dc/dms/dmsDevStandard/add},\n" +
                "${API:/api/dc/dms/dmsDevStandard/delete},\n" +
                "${API:/api/dc/dms/dmsDevStandard/detail/{code}},\n" +
                "${API:/api/dc/dms/dmsDevStandard/list},\n" +
                "${API:/api/dc/dms/dmsDevStandard/modify},\n" +
                "${API:/api/dc/dms/dmsDevStandard/page},\n" +
                "${API:/api/dc/dms/dmsDataCatalog/add},\n" +
                "${API:/api/dc/dms/dmsDataCatalog/delete},\n" +
                "${API:/api/dc/dms/dmsDataCatalog/detail/{code}},\n" +
                "${API:/api/dc/dms/dmsDataCatalog/list},\n" +
                "${API:/api/dc/dms/dmsDataCatalog/modify},\n" +
                "${API:/api/dc/dms/dmsDataCatalog/page}";

        DocContentType plainType = getPlainType(value);
        System.out.println("plainType = " + plainType);
    }

}
