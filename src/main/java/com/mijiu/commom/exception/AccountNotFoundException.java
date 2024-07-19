package com.mijiu.commom.exception;

import com.mijiu.commom.enumerate.ResultEnum;
import lombok.Getter;

/**
 * 账户不存在异常
 *
 * @author mijiupro
 */
@Getter
public class AccountNotFoundException extends RuntimeException {
    private final ResultEnum resultEnum;

    public AccountNotFoundException(ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
    }
}