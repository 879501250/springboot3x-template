package com.mijiu.result.handle;

import cn.hutool.json.JSONUtil;
import com.mijiu.commom.result.Result;
import io.micrometer.common.lang.NonNullApi;
import io.micrometer.common.lang.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一结果封装增强器
 * @author mijiupro
 */
@RestControllerAdvice(basePackages = "com.mijiu.controller")//指定要增强的包
@NonNullApi
public class ResultAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 判断是否支持对返回类型的处理
     *
     * @param returnType    方法参数的类型
     * @param converterType 转换器的类型
     * @return 是否支持处理
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    /**
     * 在写入响应体之前对返回结果进行处理和封装
     *
     * @param body                  返回结果对象
     * @param returnType            方法参数的类型
     * @param selectedContentType   响应内容的类型
     * @param selectedConverterType 转换器的类型
     * @param request               HTTP 请求对象
     * @param response              HTTP 响应对象
     * @return 处理后的返回结果
     */
    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType,
                                  MediaType selectedContentType, Class selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        //当返回结果为字符串类型需要单独处理
        if (body instanceof String) {
            // 如果返回结果是字符串类型，将其封装为成功的结果对象，并转换为 JSON 字符串
            return JSONUtil.toJsonStr(Result.success(body));
        }

        // 将返回结果封装为成功的结果对象
        return Result.success(body);
    }
}
