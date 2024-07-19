package com.mijiu.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.mijiu.commom.enumerate.ResultEnum;
import com.mijiu.commom.exception.*;
import com.mijiu.commom.model.dto.UserLoginDTO;
import com.mijiu.commom.model.dto.UserSmsLoginDTO;
import com.mijiu.commom.model.vo.UserLoginVO;
import com.mijiu.commom.util.JwtUtils;
import com.mijiu.entity.User;
import com.mijiu.mapper.UserMapper;
import com.mijiu.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 蒾酒
 * @since 2024-02-03
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;
    private final JwtUtils jwtUtils;
    private final StringRedisTemplate stringRedisTemplate;

    public UserServiceImpl(UserMapper userMapper, JwtUtils jwtUtils, StringRedisTemplate stringRedisTemplate) {
        this.userMapper = userMapper;
        this.jwtUtils = jwtUtils;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public UserLoginVO login(UserLoginDTO userLoginDTO) {
        // 获取验证码id
        String captchaId = userLoginDTO.getCaptchaId();
        // 获取用户提交验证码
        String userCaptcha = userLoginDTO.getCaptcha();
        // 获取缓存验证码
        String cacheCaptcha = stringRedisTemplate.opsForValue().get("login:captcha:" + captchaId);
        // 比较验证码是否正确
        if (cacheCaptcha == null || !cacheCaptcha.equalsIgnoreCase(userCaptcha)) {
            throw new CaptchaErrorException(ResultEnum.USER_CAPTCHA_ERROR);
        }
        // 判断用户是否存在
        User loginUser = new LambdaQueryChainWrapper<>(userMapper)
                .select(User::getId, User::getUserAccount, User::getPassword,
                        User::getUserName, User::getUserRole,
                        User::getAvatar, User::getStatus)
                .eq(User::getUserAccount, userLoginDTO.getUserAccount())
                .one();
        if (loginUser == null) {
            throw new AccountNotFoundException(ResultEnum.USER_NOT_EXIST);
        }
        log.info("loginUser: {}", loginUser);
        // 判断密码是否正确
        String md5Password = DigestUtils.md5DigestAsHex(userLoginDTO.getPassword().getBytes());
        if (!md5Password.equals(loginUser.getPassword())) {
            throw new PasswordErrorException(ResultEnum.USER_PASSWORD_ERROR);
        }
        // 判断用户状态是否正常
        if (!loginUser.getStatus()) {
            throw new AccountForbiddenException(ResultEnum.USER_ACCOUNT_FORBIDDEN);
        }
        // 生成token
        String token = jwtUtils.generateToken(Map.of("userId", loginUser.getId(),
                        "userRole", loginUser.getUserRole()),
                "user");
        //构建响应对象
        return UserLoginVO.builder()
                .userName(loginUser.getUserName())
                .avatar(loginUser.getAvatar())
                .token(token)
                .build();
    }

    @Override
    public UserLoginVO smsLogin(UserSmsLoginDTO userSmsLoginDTO) {
        // 校验验证码是否存在
        HashOperations<String, String, String> hashOps = stringRedisTemplate.opsForHash();
        String captcha = hashOps.get("login:sms:captcha:" + userSmsLoginDTO.getPhone(), "captcha");

        if (StringUtils.isEmpty(captcha)) {
            log.error("手机号 {} 的验证码不存在或已过期", userSmsLoginDTO.getPhone());
            throw new CaptchaErrorException(ResultEnum.USER_CAPTCHA_NOT_EXIST);
        }

        // 查询用户是否已注册
        User loginUser = new LambdaQueryChainWrapper<>(userMapper).eq(User::getPhone, userSmsLoginDTO.getPhone()).one();

        // 如果未注册则进行注册
        if (Objects.isNull(loginUser)) {
            loginUser = register(userSmsLoginDTO.getPhone());
        }

        // 校验验证码是否正确
        if (!userSmsLoginDTO.getCaptcha().equals(captcha)) {
            log.error("手机号 {} 的验证码错误", userSmsLoginDTO.getPhone());
            throw new CaptchaErrorException(ResultEnum.AUTH_CODE_ERROR);
        }
        //判断用户是否被禁用
        if (!loginUser.getStatus()) {
            throw new AccountForbiddenException(ResultEnum.USER_ACCOUNT_FORBIDDEN);
        }
        log.info("手机号 {} 用户登录成功", userSmsLoginDTO.getPhone());

        return UserLoginVO.builder()
                .token(jwtUtils.generateToken(Map.of("userId", loginUser.getId()), "user"))
                .userName(loginUser.getUserName())
                .build();
    }

    private User register(String phone) {
        User user = new User();
        user.setPhone(phone);
        user.setUserName(phone);
        user.setStatus(true);
        if (userMapper.insert(user) < 1) {
            log.error("手机号 {} 用户注册失败！", phone);
            throw new AccountRegisterFailException(ResultEnum.USER_REGISTER_FAIL);
        }
        log.info("手机号 {} 用户注册成功", phone);
        return user;
    }
}
