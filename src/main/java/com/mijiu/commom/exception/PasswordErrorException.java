package com.mijiu.commom.exception;


import com.mijiu.commom.enumerate.ResultEnum;
import lombok.Getter;

/**
 * 密码错误异常
 *
 * @author mijiupro
 */
@Getter
public class PasswordErrorException extends RuntimeException {
    private final ResultEnum resultEnum;

    public PasswordErrorException(ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
    }

}
