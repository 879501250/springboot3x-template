package com.mijiu.commom.custom.serializable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;

import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.mijiu.commom.annotation.Desensitization;
import com.mijiu.commom.enumerate.DesensitizationStrategyEnum;

import java.io.IOException;
import java.util.Objects;

/**
 * 自定义的脱敏JSON序列化器
 * @author mijiupro
 */


public class DesensitizationJsonSerializable extends JsonSerializer<String> implements ContextualSerializer {

    private DesensitizationStrategyEnum desensitizationStrategy; // 脱敏策略

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        // 将字符串按照设定的脱敏策略进行脱敏处理后序列化到 JSON 中
        jsonGenerator.writeString(desensitizationStrategy.getDesensitization().apply(s));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        // 获取属性上的 Desensitization 注解
        Desensitization annotation = beanProperty.getAnnotation(Desensitization.class);

        // 判断注解不为空且属性类型为 String
        if (Objects.nonNull(annotation) && Objects.equals(String.class, beanProperty.getType().getRawClass())) {
            this.desensitizationStrategy = annotation.desensitizationStrategy(); // 设置脱敏策略
            return this;
        }

        // 返回默认的序列化器
        return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
    }
}
