package top.healthylife.gzx.dochelper;

import cn.hutool.json.JSONUtil;
import top.healthylife.gzx.swagger.model.Table;

/**
 * 功能描述: MockTableData
 *
 * @author maoxiaomeng
 * @date 20230610
 */
public class MockTableData {

    static Table getTable(){
        return JSONUtil.toBean(tableJson,Table.class);
    }

    static String getHtml(){
        return tableHtml;
    }

    static final String tableHtml = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <meta http-equiv=\"Content-Type\" content=\"application/msword; charset=utf-8\"/>\n" +
            "    <title>toWord</title>\n" +
            "        <style type=\"text/css\">\n" +
            "        .bg {\n" +
            "            font-size: 14.5px;\n" +
            "            font-weight: bold;\n" +
            "            color: #000;\n" +
            "            background-color: #559e68;\n" +
            "        }\n" +
            "\n" +
            "        table {\n" +
            "            border-width: 1px;\n" +
            "            border-style: solid;\n" +
            "            border-color: black;\n" +
            "            table-layout: fixed;\n" +
            "        }\n" +
            "\n" +
            "        tr {\n" +
            "            height: 32px;\n" +
            "            font-size: 12px;\n" +
            "        }\n" +
            "\n" +
            "        td {\n" +
            "            padding-left: 10px;\n" +
            "            border-width: 1px;\n" +
            "            border-style: solid;\n" +
            "            border-color: black;\n" +
            "            height: 32px;\n" +
            "            overflow: hidden;\n" +
            "            word-break: break-all;\n" +
            "            word-wrap: break-word;\n" +
            "            font-size: 14.5px;\n" +
            "        }\n" +
            "\n" +
            "        .bg td {\n" +
            "            font-size: 14.5px;\n" +
            "        }\n" +
            "\n" +
            "        tr td {\n" +
            "            font-size: 14.5px;\n" +
            "        }\n" +
            "\n" +
            "        .specialHeight {\n" +
            "            height: 40px;\n" +
            "        }\n" +
            "\n" +
            "        .first_title {\n" +
            "            height: 60px;\n" +
            "            line-height: 60px;\n" +
            "            margin: 0;\n" +
            "            font-weight: bold;\n" +
            "            font-size: 21px;\n" +
            "        }\n" +
            "\n" +
            "        .second_title {\n" +
            "            height: 40px;\n" +
            "            line-height: 40px;\n" +
            "            margin: 0;\n" +
            "            font-size: 18.5px;\n" +
            "        }\n" +
            "\n" +
            "        .doc_title {\n" +
            "            font-size: 42.5px;\n" +
            "            text-align: center;\n" +
            "        }\n" +
            "\n" +
            "        .download_btn {\n" +
            "            float: right;\n" +
            "        }\n" +
            "\n" +
            "        body {\n" +
            "            font-family: 思源黑体 Normal;\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "<div style=\"width:1000px; margin: 0 auto\">\n" +
            "    <div>\n" +
            "        <p class=\"doc_title\">\uD83D\uDE0A风控项目（1.0）</p>\n" +
            "        <a class=\"download_btn\" href=\"/downloadWord?url=http://192.168.10.103:9203/v2/api-docs?group=manage\">下载文档</a>\n" +
            "        <br>\n" +
            "    </div>\n" +
            "\n" +
            "    <div style=\"margin-bottom:20px;\">\n" +
            "        <!--这个是类的说明-->\n" +
            "        <h4 class=\"first_title\">file-controller</h4>\n" +
            "        \n" +
            "\n" +
            "        <div>\n" +
            "\n" +
            "            <!--这个是每个请求的说明，方便生成文档后进行整理-->\n" +
            "            <h5 class=\"second_title\">13）简单场景标签分类查询</h5>\n" +
            "\n" +
            "            <table border=\"1\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">\n" +
            "                <tr class=\"bg\">\n" +
            "                    <td colspan=\"5\">简单场景标签分类查询</td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <td width=\"25%\">接口描述</td>\n" +
            "                    <td colspan=\"4\">简单场景标签分类查询</td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <td>URL</td>\n" +
            "                    <td colspan=\"4\">/api/risk-manage/rule/enginelabel/simple/classify</td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <td>请求方式</td>\n" +
            "                    <td colspan=\"4\">get</td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <td>请求类型</td>\n" +
            "                    <td colspan=\"4\"></td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <td>返回类型</td>\n" +
            "                    <td colspan=\"4\">*/*</td>\n" +
            "                </tr>\n" +
            "\n" +
            "                <tr class=\"bg\">\n" +
            "                    <td>参数名</td>\n" +
            "                    <td width=\"15%\">数据类型</td>\n" +
            "                    <td width=\"15%\">参数类型</td>\n" +
            "                    <td width=\"15%\">是否必填</td>\n" +
            "                    <td width=\"29%\">说明</td>\n" +
            "                </tr>\n" +
            "\n" +
            "                \n" +
            "                    <tr>\n" +
            "                        <td align=\"left\">1.Authorization</td>\n" +
            "                        <td>string</td>\n" +
            "                        <td>header</td>\n" +
            "                        \n" +
            "                        <td>N</td>\n" +
            "                        <td>授权token</td>\n" +
            "                        <!--                        <td th:if=\"${request.modelAttr}\" th:text=\"asdfagadfg\"></td>-->\n" +
            "                    </tr>\n" +
            "                    \n" +
            "\n" +
            "\n" +
            "                \n" +
            "\n" +
            "                <tr class=\"bg\">\n" +
            "                    <td>状态码</td>\n" +
            "                    <td colspan=\"2\">描述</td>\n" +
            "                    <td colspan=\"2\">说明</td>\n" +
            "                </tr>\n" +
            "\n" +
            "                <tr>\n" +
            "                    <td>200</td>\n" +
            "                    <td colspan=\"2\">OK</td>\n" +
            "                    <td colspan=\"2\"></td>\n" +
            "                </tr>\n" +
            "\n" +
            "                <tr>\n" +
            "                    <td>401</td>\n" +
            "                    <td colspan=\"2\">Unauthorized</td>\n" +
            "                    <td colspan=\"2\"></td>\n" +
            "                </tr>\n" +
            "\n" +
            "                <tr>\n" +
            "                    <td>403</td>\n" +
            "                    <td colspan=\"2\">Forbidden</td>\n" +
            "                    <td colspan=\"2\"></td>\n" +
            "                </tr>\n" +
            "\n" +
            "                <tr>\n" +
            "                    <td>404</td>\n" +
            "                    <td colspan=\"2\">Not Found</td>\n" +
            "                    <td colspan=\"2\"></td>\n" +
            "                </tr>\n" +
            "\n" +
            "                <tr class=\"bg\">\n" +
            "                    <td>返回属性名</td>\n" +
            "                    <td colspan=\"2\">类型</td>\n" +
            "                    <td colspan=\"2\">说明</td>\n" +
            "                </tr>\n" +
            "\n" +
            "<!--               对返回参数 递归生成行-->\n" +
            "                <tbody>\n" +
            "    \n" +
            "        <tr>\n" +
            "            <td align=\"left\" style=\"padding-left:10px\">1.children</td>\n" +
            "            <td colspan=\"2\">array:资源目录简单分类</td>\n" +
            "            <td colspan=\"2\">子节点</td>\n" +
            "        </tr>\n" +
            "        \n" +
            "    \n" +
            "        <tr>\n" +
            "            <td align=\"left\" style=\"padding-left:10px\">2.code</td>\n" +
            "            <td colspan=\"2\">string</td>\n" +
            "            <td colspan=\"2\">节点code</td>\n" +
            "        </tr>\n" +
            "        \n" +
            "    \n" +
            "        <tr>\n" +
            "            <td align=\"left\" style=\"padding-left:10px\">3.dataType</td>\n" +
            "            <td colspan=\"2\">string</td>\n" +
            "            <td colspan=\"2\">数据类型 - 是规则节点时带上</td>\n" +
            "        </tr>\n" +
            "        \n" +
            "    \n" +
            "        <tr>\n" +
            "            <td align=\"left\" style=\"padding-left:10px\">4.name</td>\n" +
            "            <td colspan=\"2\">string</td>\n" +
            "            <td colspan=\"2\">节点名称</td>\n" +
            "        </tr>\n" +
            "        \n" +
            "    \n" +
            "        <tr>\n" +
            "            <td align=\"left\" style=\"padding-left:10px\">5.type</td>\n" +
            "            <td colspan=\"2\">string</td>\n" +
            "            <td colspan=\"2\">节点类型</td>\n" +
            "        </tr>\n" +
            "        \n" +
            "    \n" +
            "</tbody>\n" +
            "\n" +
            "                <tr class=\"bg\">\n" +
            "                    <td colspan=\"5\">示例</td>\n" +
            "                </tr>\n" +
            "                <tr class=\"specialHeight\">\n" +
            "                    <td class=\"bg\">请求参数</td>\n" +
            "                    <td colspan=\"4\"> --header &#39;Authorization:string&#39;</td>\n" +
            "                </tr>\n" +
            "                <tr class=\"specialHeight\">\n" +
            "                    <td class=\"bg\">返回值</td>\n" +
            "                    <td colspan=\"4\">{&quot;name&quot;:&quot;string&quot;,&quot;code&quot;:&quot;string&quot;,&quot;type&quot;:&quot;string&quot;,&quot;children&quot;:[{}],&quot;dataType&quot;:&quot;string&quot;}</td>\n" +
            "                </tr>\n" +
            "\n" +
            "            </table>\n" +
            "        </div>\n" +
            "\n" +
            "    </div>\n" +
            "\n" +
            "    \n" +
            "</div>\n" +
            "\n" +
            "\n" +
            "    \n" +
            "\n" +
            "\n" +
            "\n" +
            "    \n" +
            "\n" +
            "</body>\n" +
            "</html>";

    static final String tableJson = "{\n" +
            "                \"requestType\": \"get\",\n" +
            "                \"requestList\": [\n" +
            "                    {\n" +
            "                        \"require\": false,\n" +
            "                        \"remark\": \"授权token\",\n" +
            "                        \"type\": \"string\",\n" +
            "                        \"paramType\": \"header\",\n" +
            "                        \"name\": \"Authorization\"\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"modelAttr\": {\n" +
            "                    \"description\": \"\",\n" +
            "                    \"className\": \"EngineJarManage对象\",\n" +
            "                    \"require\": false,\n" +
            "                    \"type\": \"\",\n" +
            "                    \"name\": \"\",\n" +
            "                    \"properties\": [\n" +
            "                        {\n" +
            "                            \"description\": \"jar包名称\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"string\",\n" +
            "                            \"name\": \"jarName\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"部门所属权限\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"string\",\n" +
            "                            \"name\": \"departmentBy\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"数据所属机构\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"string\",\n" +
            "                            \"name\": \"organizeBy\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"业务描述\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"string\",\n" +
            "                            \"name\": \"remark\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"应用唯一标识编码\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"string\",\n" +
            "                            \"name\": \"appCode\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"jar文件定义信息\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"string\",\n" +
            "                            \"name\": \"jarDefinition\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"jar包描述\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"string\",\n" +
            "                            \"name\": \"jarDesc\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"平台编码： 业务未使用\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"string\",\n" +
            "                            \"name\": \"pltCode\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"是否可用：-1未启用、1启用、2禁用\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"string\",\n" +
            "                            \"name\": \"enabled\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"所属实例： 业务未使用\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"string\",\n" +
            "                            \"name\": \"instanceBy\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"主键：时间戳+UUID转换数值后5位\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"integer(int64)\",\n" +
            "                            \"name\": \"jarId\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"级别：业务未使用\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"string\",\n" +
            "                            \"name\": \"pltLevel\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"排序标识：展示（正序排序）,更新保存时间戳\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"integer(int64)\",\n" +
            "                            \"name\": \"sortNum\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"平台类型： 业务未使用\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"string\",\n" +
            "                            \"name\": \"pltType\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"更新者\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"string\",\n" +
            "                            \"name\": \"pltUpdateBy\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"所属机构\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"string\",\n" +
            "                            \"name\": \"pltOrganizeBy\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"文件存储服务器路径\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"string\",\n" +
            "                            \"name\": \"filePath\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"平台父编码： 业务未使用\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"string\",\n" +
            "                            \"name\": \"pltPcode\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"平台描述： 业务未使用\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"string\",\n" +
            "                            \"name\": \"pltRemark\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"更新时间\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"string(date-time)\",\n" +
            "                            \"name\": \"updateTime\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"创建者\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"string\",\n" +
            "                            \"name\": \"pltCreateBy\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"所属系统： 业务未使用\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"string\",\n" +
            "                            \"name\": \"systemBy\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"所属实例版本： 业务未使用\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"string\",\n" +
            "                            \"name\": \"versionBy\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"创建日期\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"string(date-time)\",\n" +
            "                            \"name\": \"createTime\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"被引用次数：已发布状态规则被引用数量，删除时判断\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"integer(int32)\",\n" +
            "                            \"name\": \"referenceCount\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"访问权限控制[PRIVATE,PROTECTED]\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"string\",\n" +
            "                            \"name\": \"ruleShareType\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"临时文件路径\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"string\",\n" +
            "                            \"name\": \"tempDir\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"逻辑删除\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"string\",\n" +
            "                            \"name\": \"isDel\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"所属租户\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"string\",\n" +
            "                            \"name\": \"pltTenantBy\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"jar包编码\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"string\",\n" +
            "                            \"name\": \"jarFileCode\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"description\": \"平台状态： 业务未使用\",\n" +
            "                            \"className\": \"\",\n" +
            "                            \"require\": false,\n" +
            "                            \"type\": \"string\",\n" +
            "                            \"name\": \"pltStatus\",\n" +
            "                            \"properties\": [\n" +
            "                            ],\n" +
            "                            \"isCompleted\": false\n" +
            "                        }\n" +
            "                    ],\n" +
            "                    \"isCompleted\": false\n" +
            "                },\n" +
            "                \"responseForm\": \"*/*\",\n" +
            "                \"description\": \"jar包列表\",\n" +
            "                \"title\": \"jar包规则\",\n" +
            "                \"requestParam\": \" --header 'Authorization:string'\",\n" +
            "                \"url\": \"/api/risk-manage/jar/package/list\",\n" +
            "                \"requestForm\": \"\",\n" +
            "                \"responseParam\": \"{\\\"jarName\\\":\\\"string\\\",\\\"departmentBy\\\":\\\"string\\\",\\\"organizeBy\\\":\\\"string\\\",\\\"remark\\\":\\\"string\\\",\\\"appCode\\\":\\\"string\\\",\\\"jarDefinition\\\":\\\"string\\\",\\\"jarDesc\\\":\\\"string\\\",\\\"pltCode\\\":\\\"string\\\",\\\"enabled\\\":\\\"string\\\",\\\"instanceBy\\\":\\\"string\\\",\\\"jarId\\\":0,\\\"pltLevel\\\":\\\"string\\\",\\\"sortNum\\\":0,\\\"pltType\\\":\\\"string\\\",\\\"pltUpdateBy\\\":\\\"string\\\",\\\"pltOrganizeBy\\\":\\\"string\\\",\\\"filePath\\\":\\\"string\\\",\\\"pltPcode\\\":\\\"string\\\",\\\"pltRemark\\\":\\\"string\\\",\\\"updateTime\\\":\\\"2020/01/01 00:00:00\\\",\\\"pltCreateBy\\\":\\\"string\\\",\\\"systemBy\\\":\\\"string\\\",\\\"versionBy\\\":\\\"string\\\",\\\"createTime\\\":\\\"2020/01/01 00:00:00\\\",\\\"referenceCount\\\":0,\\\"ruleShareType\\\":\\\"string\\\",\\\"tempDir\\\":\\\"string\\\",\\\"isDel\\\":\\\"string\\\",\\\"pltTenantBy\\\":\\\"string\\\",\\\"jarFileCode\\\":\\\"string\\\",\\\"pltStatus\\\":\\\"string\\\"}\",\n" +
            "                \"responseList\": [\n" +
            "                    {\n" +
            "                        \"description\": \"OK\",\n" +
            "                        \"remark\": \"\",\n" +
            "                        \"name\": \"200\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"description\": \"Unauthorized\",\n" +
            "                        \"name\": \"401\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"description\": \"Forbidden\",\n" +
            "                        \"name\": \"403\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"description\": \"Not Found\",\n" +
            "                        \"name\": \"404\"\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"tag\": \"jar包列表\"\n" +
            "            }";
}
