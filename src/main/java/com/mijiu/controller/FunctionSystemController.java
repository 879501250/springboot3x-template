package com.mijiu.controller;

import com.mijiu.commom.result.Result;
import com.mijiu.entity.Function1;
import com.mijiu.service.Function1Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 商户功能关系表 前端控制器
 * </p>
 *
 * @author 蒾酒
 * @since 2024-03-07
 */
@RestController
@RequestMapping("/function-system")
@Tag(name = "系统功能管理")
public class FunctionSystemController {
    private final Function1Service function1Service;

    public FunctionSystemController(Function1Service function1Service) {
        this.function1Service = function1Service;
    }

    @GetMapping("/system-function-tree")
    @Operation( summary = "获取系统功能树")
    public Result<List<Function1>> getSystemFunctionTree() {
        return Result.success(function1Service.getSystemFunctionTree());
    }

}
