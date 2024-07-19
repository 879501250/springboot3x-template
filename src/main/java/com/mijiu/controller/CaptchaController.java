package com.mijiu.controller;

import com.mijiu.commom.model.vo.CaptchaVO;
import com.mijiu.service.CaptchaService;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mijiupro
 */
@RestController
@RequestMapping("/captcha")
@Tag(name = "验证码接口", description = "验证码接口相关操作")
public class CaptchaController {

    private final CaptchaService captchaService;
    public CaptchaController(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }
    @GetMapping("/graph-captcha")
    @Operation(summary = "获取图形验证码")
    public CaptchaVO getCaptcha(String captchaId) {
        return captchaService.getCaptcha(captchaId);

    }

    @GetMapping("/sms-captcha/{phone}")
    @Operation(summary = "获取短信验证码")
    public void getSmsCaptcha(@PathVariable String phone) {
        captchaService.getSmsCaptcha(phone);
    }
}
