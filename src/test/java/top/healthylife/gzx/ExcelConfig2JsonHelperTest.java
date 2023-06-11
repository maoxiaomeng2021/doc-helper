package top.healthylife.gzx;

import cn.hutool.json.JSONUtil;
import org.testng.annotations.Test;
import top.healthylife.gzx.dochelper.DocConfigSheet;
import top.healthylife.gzx.dochelper.DocMenu;
import top.healthylife.gzx.dochelper.ExcelConfig2JsonHelper;
import top.healthylife.gzx.dochelper.config.DocBaseConfig;

import java.io.File;
import java.util.List;

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
        DocBaseConfig.initLocalConfig(f);
        List<DocMenu> parse = ExcelConfig2JsonHelper.parseCommonLeafMenuSheet(f, DocConfigSheet.COMMON_LEAF_CONFIG);
        System.out.println("parse = " + JSONUtil.toJsonPrettyStr(parse));
    }
}
