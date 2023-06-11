package top.healthylife.gzx.dochelper;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;

/**
 * 公共异常定义
 *
 * @author maoxiaomeng
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    @Getter
    private Integer code;

    protected final String message;


    public BusinessException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BusinessException(String messageTemplate, Object... params) {
        this.message = StrUtil.format(messageTemplate, params);
    }

    public BusinessException(String message) {
        this.message = message;
    }

    public BusinessException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
