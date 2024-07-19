package com.mijiu.commom.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mijiupro
 */

@Aspect
@Component
@Slf4j
public class MethodCallCountAspect {
    // 用于存储方法调用次数的Map，使用ConcurrentMap保证线程安全
    private final Map<String, AtomicInteger> counterMap = new ConcurrentHashMap<>();
    @Around("@annotation(com.mijiu.commom.annotation.MethodCallCount)")
    public Object methodCallCountAspect(ProceedingJoinPoint joinPoint) {

        String methodName = joinPoint.getSignature().toShortString();
        try{
            return joinPoint.proceed();
        }catch (Throwable ignored){
        //异常处理
            return null;
        }finally {
            AtomicInteger counter = counterMap.computeIfAbsent(methodName,k -> new AtomicInteger(0));
            counter.incrementAndGet();
            log.info("方法 {} 调用次数：{}", methodName, counter.get());
        }

    }

    // 提供一个方法，用于获取方法调用次数的Map
    public Map<String, AtomicInteger> getCounterMap() {
        return new ConcurrentHashMap<>(counterMap);
    }
}
