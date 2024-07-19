package com.mijiu.controller;

import com.mijiu.entity.PersonalInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




/**
 * @author mijiupro
 */
@RestController
@RequestMapping("/test/desensitization")
@Tag(name = "脱敏测试接口", description = "脱敏测试接口")
public class DesensitizationTestController {
    @GetMapping("/get-info")
    @Operation(summary = "获取脱敏信息")
    public PersonalInfo getInt() {


        return PersonalInfo.builder()
                .name("言冰云")
                .phone("13812345678")
                .email("mijiu@qq.com")
                .idCard("110101199003073321")
                .address("四川省成都市郫都区百草路一号")
                .password("1234567890")
                .bankCard("62220210001234567890")
                .build();
    }
}
