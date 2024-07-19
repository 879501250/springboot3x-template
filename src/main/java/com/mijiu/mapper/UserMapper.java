package com.mijiu.mapper;

import com.mijiu.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author 蒾酒
 * @since 2024-02-03
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    //用户登录
    User selectUserByNameAndPassword(User user);
}
