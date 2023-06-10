package top.healthylife.gzx;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import org.testng.annotations.Test;
import top.healthylife.gzx.dochelper.DocMenu;
import top.healthylife.gzx.dochelper.ExcelConfig2JsonHelper;
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
public class ExcelConfig2JsonHelperTest {


    static final File f = new File("C:\\Users\\maoxiaomeng\\Desktop\\DocHelper配置表.xlsx");


    @Test
    public void testExcelParse(){
        DocBaseConfig.initConfig(f);
        List<DocMenu> parse = ExcelConfig2JsonHelper.parseCommonLeafMenuSheet(f);
        System.out.println("parse = " + JSONUtil.toJsonPrettyStr(parse));
    }
}
