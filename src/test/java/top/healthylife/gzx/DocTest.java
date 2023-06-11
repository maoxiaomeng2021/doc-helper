package top.healthylife.gzx;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import top.healthylife.gzx.dochelper.DocHelper;
import top.healthylife.gzx.dochelper.config.DocBaseConfig;

/**
 * 功能描述: DocTest
 *
 * @author maoxiaomeng
 * @date 20230609
 */
@Slf4j
public class DocTest {


    @Test
    public void test01() {
        DocHelper.start(null);
    }

    @Test
    public void test02() {
        DocBaseConfig.initLocalConfig(DocHelper.configFile);
    }
}
