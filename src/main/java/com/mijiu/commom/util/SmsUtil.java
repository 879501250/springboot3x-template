package com.mijiu.commom.util;


import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;



/**
 * @author mijiupro
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.sms")
@Slf4j
public class SmsUtil {

    private String accessKeyId;// 访问密钥id

    private String accessKeySecret;// 访问密钥secret

    private String endpoint;// 短信 API 的访问域名

    private String signName;// 短信签名

    private String templateCode;// 短信模板ID


    // 发送短信
    public Boolean sendSms(String phone, String code) {
        // 创建阿里云客户端
        Config config = new Config()
                .setAccessKeyId(accessKeyId)// 配置访问密钥 ID
                .setAccessKeySecret(accessKeySecret);// 配置密钥
        config.endpoint = endpoint;// 配置访问端点
        Client client;
        try {
            client = new Client(config);
        } catch (Exception e) {
            log.error("阿里云短信服务初始化失败", e);
            return false;
        }

        // 构建发送请求
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName(signName) // 设置签名
                .setTemplateCode(templateCode) // 设置模板
                .setPhoneNumbers(phone) // 设置手机号为参数传入的值
                .setTemplateParam("{\"code\":\"" + code + "\"}"); // 设置模板参数为传入的验证码

        RuntimeOptions runtime = new RuntimeOptions();// 运行时选择，可以设置不同的属性来配置运行时环境的参数。
        try {
            // 复制代码运行请自行打印 API 的返回值
            SendSmsResponse sendSmsResponse = client.sendSmsWithOptions(sendSmsRequest, runtime);
            if (!"OK".equals(sendSmsResponse.getBody().getCode())) {
                log.error("短信发送失败:{}", sendSmsResponse.getBody().getMessage());
                return false;
            }
            log.info("短信发送成功");
            return true;

        } catch (Exception error) {
            log.error("短信发送失败:{}", error.getMessage());
            return false;
        }
    }
}

