package top.healthylife.gzx;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import top.healthylife.gzx.dochelper.DocHelper;

/**
 * 功能描述: DocTest
 *
 * @author maoxiaomeng
 * @date 20230609
 */
@Slf4j
public class DocTest {


    @Test
    public void test02() {
        DocHelper.start(null);
    }

}
