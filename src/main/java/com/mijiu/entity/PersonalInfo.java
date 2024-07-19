package com.mijiu.entity;


import com.mijiu.commom.annotation.Desensitization;
import com.mijiu.commom.enumerate.DesensitizationStrategyEnum;
import lombok.Builder;
import lombok.Data;

/**
 * @author mijiupro
 */
@Data
@Builder
public class PersonalInfo {

    @Desensitization(desensitizationStrategy = DesensitizationStrategyEnum.PHONE)
    private String phone; // 手机号

    @Desensitization(desensitizationStrategy = DesensitizationStrategyEnum.EMAIL)
    private String email; // 邮箱

    @Desensitization(desensitizationStrategy = DesensitizationStrategyEnum.ID_CARD)
    private String idCard; // 身份证号

    @Desensitization(desensitizationStrategy = DesensitizationStrategyEnum.ADDRESS)
    private String address; // 地址

    @Desensitization(desensitizationStrategy = DesensitizationStrategyEnum.BANK_CARD)
    private String bankCard; // 银行卡号

    @Desensitization(desensitizationStrategy = DesensitizationStrategyEnum.NAME)
    private String name; // 姓名

    @Desensitization(desensitizationStrategy = DesensitizationStrategyEnum.PASSWORD)
    private String password; // 密码

}
