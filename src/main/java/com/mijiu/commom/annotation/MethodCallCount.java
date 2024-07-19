package com.mijiu.commom.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author mijiupro
 */


@Retention(RetentionPolicy.RUNTIME)// 指定注解的保留策略为RUNTIME
@Target(ElementType.METHOD)// 指定该注解可以用于方法
public @interface MethodCallCount {
}
