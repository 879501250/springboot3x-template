package com.mijiu.commom.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author mijiupro
 */
@Getter
@Setter
public class GeneralBusinessException extends RuntimeException{
    private int code=0;
    private String message;

    public GeneralBusinessException(String message) {
        this.message = message;
    }

}
