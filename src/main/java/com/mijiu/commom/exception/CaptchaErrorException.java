package com.mijiu.commom.exception;

import com.mijiu.commom.enumerate.ResultEnum;
import lombok.Getter;

/**
 * @author mijiupro
 */
@Getter
public class CaptchaErrorException extends RuntimeException {
    private final ResultEnum resultEnum;//返回提示信息枚举(code,message)

    public CaptchaErrorException(ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
    }
}
