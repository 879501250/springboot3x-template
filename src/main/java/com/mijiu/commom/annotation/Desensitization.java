package com.mijiu.commom.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mijiu.commom.custom.serializable.DesensitizationJsonSerializable;
import com.mijiu.commom.enumerate.DesensitizationStrategyEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author mijiupro
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@JacksonAnnotationsInside
@JsonSerialize(using = DesensitizationJsonSerializable.class)
public @interface Desensitization {
    DesensitizationStrategyEnum desensitizationStrategy();//这是自定义的一个枚举类型，用于指定脱敏策略

}
