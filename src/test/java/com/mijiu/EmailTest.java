package com.mijiu;

import com.mijiu.commom.util.EmailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailTest {
    @Autowired
    private EmailUtil emailUtil;

    @Test
    public void sendEmail() {

        // 发送图片邮件
        boolean b = emailUtil.sendInlineResourceEmail("测试邮件", " 这是测试邮件", "dqwzam0@163.com",
                "C:\\Users\\mijiupro\\Desktop\\潮.jpg", "C:\\Users\\mijiupro\\Desktop\\潮.jpg");
        System.out.println(b);
    }
}
