package com.mijiu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mijiu.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @MockBean
    private BaseMapper<User> baseMapper;

    @Test
    public void testSelectUserByNameAndPassword() {
        // 创建一个模拟的User对象，用于作为参数传入方法中
        User user = new User();
        user.setUserName("test");
        user.setPassword("password");

        // 创建一个模拟的查询结果
        User expectedResult = new User();
        expectedResult.setId(1L);
        expectedResult.setUserName("test");
        expectedResult.setPassword("password");

        // 模拟BaseMapper的行为，当调用其selectOne方法时，返回模拟的结果
        when(baseMapper.selectOne(new QueryWrapper<User>().eq("username", "test").eq("password", "password")))
                .thenReturn(expectedResult);

        // 调用被测试的方法
        User result = userMapper.selectUserByNameAndPassword(user);

        // 断言结果是否符合预期
        assertEquals(expectedResult, result);
    }
}

