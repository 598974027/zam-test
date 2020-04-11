package com.example.web_demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/10/15 10:10
 * @see
 **/
@Component
@Aspect
public class MyAspect {

    @Pointcut("@annotation(useCase)")
    public void limit(UseCase useCase) {
    }

    @Before("limit(useCase)")
    public void beforMethod(JoinPoint point, UseCase useCase) {
        String methodName = point.getSignature().getName();
        List<Object> args = Arrays.asList(point.getArgs());
        System.out.println("调用前连接点方法为：" + methodName + ",参数为：" + args);
    }

    @Around("limit(useCase)")
    public Object aroundLog(ProceedingJoinPoint joinpoint, UseCase useCase) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinpoint.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] args = joinpoint.getArgs();
        System.err.println("args : " + Arrays.toString(args));
        System.err.println("keys : " + Arrays.toString(parameterNames));
        return joinpoint.proceed();
    }

    @UseCase(id = "1")
    public String test(String str) {
        return str + "zam";
    }

}
