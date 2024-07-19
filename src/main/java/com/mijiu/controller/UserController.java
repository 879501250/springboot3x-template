package com.mijiu.controller;

import com.mijiu.commom.annotation.RepeatSubmit;
import com.mijiu.commom.model.dto.UserLoginDTO;
import com.mijiu.commom.model.dto.UserSmsLoginDTO;
import com.mijiu.commom.model.vo.UserLoginVO;
import com.mijiu.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author 蒾酒
 * @since 2024-02-03
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")//允许所有来源的请求跨域
@Tag(name = "用户模块")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @RepeatSubmit(interval = 5000)
    @Operation(summary = "用户账密登录")
    public UserLoginVO login(@RequestBody @Validated UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }

    @PostMapping("/login/sms")
    @Operation(summary = "用户短信验证登录")
    public UserLoginVO smsLogin(@RequestBody @Validated UserSmsLoginDTO userSmsLoginDTO) {
        return userService.smsLogin(userSmsLoginDTO);
    }

}
