package com.mijiu.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mijiu.entity.Function1;

import java.util.List;

/**
 * <p>
 * 系统一级功能表 服务类
 * </p>
 *
 * @author 蒾酒
 * @since 2024-02-21
 */
public interface Function1Service extends IService<Function1> {
    /**
     * 获取系统功能树
     * @return 功能（对象）树集合
     */
    List<Function1> getSystemFunctionTree();

}
