package com.mijiu;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringbootTemplateApplicationTests {
@Autowired
private DataSource dataSource;

    @Test // 测试druid
    void contextLoads() {
        System.out.println(dataSource.getClass());
        DruidDataSource druidDataSource=(DruidDataSource) dataSource;

        System.out.println("初始化连接数："+druidDataSource.getInitialSize());
        System.out.println("最大连接数："+druidDataSource.getMaxActive());

    }



}
