package com.mijiu.commom.exception;

import com.mijiu.commom.enumerate.ResultEnum;
import lombok.Getter;

/**
 * @author mijiupro
 */
@Getter
public class AccountForbiddenException extends RuntimeException {
    private final ResultEnum resultEnum;

    public AccountForbiddenException(ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
    }
}
