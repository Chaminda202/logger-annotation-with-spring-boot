package com.springboot.logger.advice;

import com.springboot.logger.util.JacksonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import com.springboot.logger.common.ConstantValue;

@Aspect
@Component
public class MethodLogAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodLogAdvice.class);

    @PostConstruct
    public void init(){
        LOGGER.info("MethodLogAdvice is Initiated");
    }

    @Around("@annotation(com.springboot.logger.annotation.MethodLogger)")
    public Object methodLoggingWithAverageTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] inputArgs = proceedingJoinPoint.getArgs();
        LOGGER.info("{} -> {} -> {} -> {}", ConstantValue.REQUEST, className, methodName, JacksonUtil.convertObjectToJson(inputArgs));
        long startTime = System.currentTimeMillis();
        final Object proceed = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        LOGGER.info("{} -> {} -> {} -> {} -> {}", ConstantValue.RESPONSE, className, methodName,
                JacksonUtil.convertObjectToJson(proceed), (endTime - startTime) + " ms");
        return proceed;
    }
}
