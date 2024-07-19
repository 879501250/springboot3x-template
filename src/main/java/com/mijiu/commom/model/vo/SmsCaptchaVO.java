package com.mijiu.commom.model.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author mijiupro
 */
@Data
@Builder
public class SmsCaptchaVO {
    //手机号
    private  String phone;

}