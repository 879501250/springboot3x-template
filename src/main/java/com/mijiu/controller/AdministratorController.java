package com.mijiu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author 蒾酒
 * @since 2024-03-07
 */
@RestController
@RequestMapping("/administrator")
@CrossOrigin( origins="*")
@Tag(name = "管理员表", description = "管理员表接口")
public class AdministratorController {

}
