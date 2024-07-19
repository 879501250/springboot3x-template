package com.mijiu.commom.exception;

import com.mijiu.commom.enumerate.ResultEnum;
import lombok.Getter;

/**
 * 令牌过期异常
 *
 * @author mijiupro
 */
@Getter
public class TokenOverdueException extends RuntimeException {
    private final ResultEnum resultEnum;
    public TokenOverdueException(ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
    }
}