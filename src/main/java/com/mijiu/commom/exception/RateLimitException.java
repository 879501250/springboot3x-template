package com.mijiu.commom.exception;

import com.mijiu.commom.enumerate.ResultEnum;
import lombok.Getter;

/**
 * @author mijiupro
 */
@Getter
public class RateLimitException extends RuntimeException{
    private final ResultEnum resultEnum;

    public RateLimitException(ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
    }
}
