package com.mijiu.commom.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * @author mijiupro
 */
@Data
public class UserSmsLoginDTO {

    @NotBlank( message = "手机号不能为空")
    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "手机号格式不正确")
    private String phone;//手机号

    @NotBlank( message = "验证码不能为空")
    private String captcha;//验证码
}
