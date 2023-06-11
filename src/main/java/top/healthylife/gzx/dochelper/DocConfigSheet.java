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
public enum DocConfigSheet {
    /**
     * doc内容分类
     */
    BASE_MENU_CONFIG(0,"基础菜单配置"),
    COMMON_LEAF_CONFIG(1,"通用叶子节点配置"),
    CUSTOM_LEAF_CONFIG(2,"自定义叶子节点配置"),
    GLOBAL_CONFIG(3,"全局叶子节点配置")

    ;

    public Integer sheetIndex;
    public String desc;
}
