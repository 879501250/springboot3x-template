package com.mijiu.service;

import com.mijiu.commom.model.dto.UserLoginDTO;
import com.mijiu.commom.model.dto.UserSmsLoginDTO;
import com.mijiu.commom.model.vo.UserLoginVO;
import com.mijiu.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 蒾酒
 * @since 2024-02-03
 */
public interface UserService extends IService<User> {


    /**
     *
     * @param userLoginDTO 用户登录表单
     * @return 用户信息返回
     */
     UserLoginVO login(UserLoginDTO userLoginDTO);

    /**
     *
     * @param userSmsLoginDTO 用户手机号登录表单
     * @return 用户信息返回
     */
     UserLoginVO smsLogin(UserSmsLoginDTO userSmsLoginDTO);


}
