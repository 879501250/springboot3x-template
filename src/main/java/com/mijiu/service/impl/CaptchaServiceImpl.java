package com.mijiu.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.mijiu.commom.exception.GeneralBusinessException;
import com.mijiu.commom.model.vo.CaptchaVO;
import com.mijiu.commom.util.SmsUtil;
import com.mijiu.service.CaptchaService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author mijiupro
 */
@Service
@Slf4j
public class CaptchaServiceImpl implements CaptchaService {
    private final StringRedisTemplate stringRedisTemplate;
    private final SmsUtil smsUtil;


    public CaptchaServiceImpl(StringRedisTemplate stringRedisTemplate, SmsUtil smsUtil) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.smsUtil = smsUtil;
    }


    @Override
    public CaptchaVO getCaptcha(String captchaId) {
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(130, 48, 4, 10);
        // 获取验证码的文本
        String captchaText = circleCaptcha.getCode();
        log.info("captchaText: {}", captchaText);
        // 获取验证码图片的Base64编码
        String captchaImageBase64Data = circleCaptcha.getImageBase64Data();
        //如果没有传入captchaId，则生成一个随机字符串作为captchaId
        captchaId = Optional.ofNullable(captchaId).orElseGet(()-> UUID.randomUUID().toString());
        // 保存验证码文本到Redis中，有效期30秒
        stringRedisTemplate.opsForValue().set("login:captcha:" + captchaId, captchaText, 300, TimeUnit.SECONDS);

        return CaptchaVO.builder()
                .captchaId(captchaId)
                .captchaImage(captchaImageBase64Data)
                .build();
    }

    @Override
    public void getSmsCaptcha(String phone) {
        String hashKey = "login:sms:captcha:" + phone;
        BoundHashOperations<String, String, String> hashOps = stringRedisTemplate.boundHashOps(hashKey);

        // 初始检查
        String lastSendTimestamp = hashOps.get("lastSendTimestamp");
        String sendCount = hashOps.get("sendCount");
        String captcha = hashOps.get("captcha");
        hashOps.expire(5, TimeUnit.MINUTES); // 设置过期时间为5分钟

        // 判断发送次数是否超过限制
        if (StringUtils.isNotBlank(sendCount) && Integer.parseInt(sendCount) >= 5) {
            hashOps.expire(24, TimeUnit.HOURS); // 重新设置过期时间为24H
            throw new GeneralBusinessException("发送次数过多，请24H后再试");
        }

        // 判断发送频率是否过高
        if (StringUtils.isNotBlank(lastSendTimestamp)) {
            long lastSendTime = Long.parseLong(lastSendTimestamp);
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - lastSendTime;
            long interval = 60 * 1000; // 60秒
            if (elapsedTime < interval) {
                throw new GeneralBusinessException("发送短信过于频繁，请稍后再试");
            }
        }

        // 更新发送次数
        int newSendCount = StringUtils.isNotBlank(sendCount) ? Integer.parseInt(sendCount) + 1 : 1;

        // 生成新验证码
        if (StringUtils.isBlank(captcha)) {
            captcha = RandomStringUtils.randomNumeric(6);
        }

        // 发送短信
        if (!smsUtil.sendSms(phone, captcha)) {
            throw new GeneralBusinessException("发送短信失败");
        }

        // 更新 Redis 中的信息
        hashOps.put("captcha", captcha);
        hashOps.put("lastSendTimestamp", String.valueOf(System.currentTimeMillis()));
        hashOps.put("sendCount", String.valueOf(newSendCount));

    }

}
