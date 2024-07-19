package com.mijiu.commom.model.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


/**
 * @author mijiupro
 */
@Data
@Builder
public class UserLoginVO implements Serializable {
    private String token;//令牌
    private String userName;//用户名
    private String avatar;//头像
}
