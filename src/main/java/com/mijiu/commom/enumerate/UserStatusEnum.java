package com.mijiu.commom.enumerate;

import lombok.Getter;

/**
 * @author mijiupro
 */
@Getter
public enum UserStatusEnum {
    ENABLE(1, "启用"),
    DISABLE(0, "禁用");

    private final Integer code;
    private final String description;

    UserStatusEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }


    // 根据code获取枚举
    public static UserStatusEnum valueOf(int code) {
        for (UserStatusEnum status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid User Status code: " + code);
    }
}
