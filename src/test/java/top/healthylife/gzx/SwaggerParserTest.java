package top.healthylife.gzx;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HtmlUtil;
import org.testng.annotations.Test;
import top.healthylife.gzx.swagger.SwaggerParser;

import java.io.File;
import java.util.Map;

public class SwaggerParserTest {

    @Test
    public void testParse() {

        String apiPath = "/api/risk-manage/jar/package/list";
        String url = "http://ip:port/v2/api-docs";
        File file = new File("C:\\Users\\maoxiaomeng\\Desktop\\html.html");
        Map<String, Object> map = SwaggerParser.parseBySwaggerUrl(url, apiPath);
        //writeContentToFile(map, file);
        String s = SwaggerParser.buildHtmlStr(map, "<html>Hello themleaf </html>");
        System.out.println("s = " + HtmlUtil.unescape(s));
        FileUtil.writeBytes(s.getBytes(), file);
        //System.out.println(JSONUtil.toJsonPrettyStr(map));

    }
}
