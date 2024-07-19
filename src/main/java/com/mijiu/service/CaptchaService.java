package com.mijiu.service;

import com.mijiu.commom.model.vo.CaptchaVO;

/**
 * @author mijiupro
 */

public interface CaptchaService {
    /**
     *  生成图形验证码
     * @param captchaId 验证码id
     * @return 验证码视图对象
     */
     CaptchaVO getCaptcha(String captchaId);

    /**
     *  获取短信验证码
     * @param phone
     */

    void getSmsCaptcha(String phone);

}
