// This file is auto-generated, don't edit it. Thanks.
package com.mijiu;

import com.alibaba.fastjson.JSON;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.tea.*;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;

import java.util.Arrays;
import java.util.List;

public class Sample {

    /**
     * 使用AK&SK初始化账号Client

     * @return Client
     * @throws Exception
     */
    public static Client createClient() throws Exception {

                Config config = new Config()
                // .setAccessKeyId(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"))//通过系统变量配置
                // .setAccessKeySecret(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
                .setAccessKeyId("LTAI5t7Kt1Ez3cge3aCAF9Ei")//通过代码嵌入配置
                .setAccessKeySecret("on45H8WMlsgrOySHJM97trpnG6m1n1");
        // Endpoint 请参考 https://api.aliyun.com/product/Dysmsapi
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new Client(config);
    }

    public static void main(String[] args_) throws Exception {

        Client client = Sample.createClient();
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName("mi9688")
                .setTemplateCode("SMS_465440884")
                .setPhoneNumbers("15666617395")
                .setTemplateParam("{\"code\":\"1234\"}");
       RuntimeOptions runtime = new RuntimeOptions();

        try {
            // 复制代码运行请自行打印 API 的返回值
            SendSmsResponse sendSmsResponse = client.sendSmsWithOptions(sendSmsRequest, runtime);
            System.out.println(JSON.toJSONString(sendSmsResponse));

        }
        // catch (TeaException error) {
        //     // 此处仅做打印展示，请谨慎对待异常处理，在工程项目中切勿直接忽略异常。
        //     // 错误 message
        //     System.out.println(error.getMessage()+"1132222");
        //     // 诊断地址
        //     // System.out.println(error.getData().get("Recommend"));
        //     // com.aliyun.teautil.Common.assertAsString(error.message);
        // }
        catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 此处仅做打印展示，请谨慎对待异常处理，在工程项目中切勿直接忽略异常。
            // 错误 message
            System.out.println(error.getMessage());

            // 诊断地址
            // System.out.println(error.getData().get("Recommend"));
            // com.aliyun.teautil.Common.assertAsString(error.message);
        }        
    }
}