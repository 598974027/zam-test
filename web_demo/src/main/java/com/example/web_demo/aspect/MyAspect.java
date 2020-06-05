package com.example.web_demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 功能描述: AOP测试
 * 在aop的逻辑内，先走@Around注解的方法。然后是@Before注解的方法，然后这两个都通过了，走核心代码，核心代码走完，无论核心有没有返回值，都会走@After方法。
 * 然后如果程序无异常就走@AfterReturn,有异常就走@AfterThrowing。
 *
 * @author zhangam
 * @time 2019/10/15 10:10
 * @see
 **/
@Component
@Aspect//将一个java类定义为切面类
public class MyAspect {

    //定义一个切入点，可以是一个规则表达式，比如下例中某个package下的所有函数，也可以是一个注解等
    @Pointcut("execution(* com.example.web_demo.controller.Controller1.*(..))")
    private void logSender() {
    }

    @Pointcut("execution(* com.example.web_demo.controller.Controller2.*(..))")
    private void logReceiver() {
    }

    @Pointcut("logSender() || logReceiver()")
    private void logMessage() {
    }

    @Pointcut("@annotation(useCase)")
    private void limit(UseCase useCase) {
    }

    @Around("logSender()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around before");
        //调用原方法
        Object object = joinPoint.proceed();
        System.out.println("around after");
        return object;
    }

    @Before("logSender()")
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();
//        System.out.println("args : " + Arrays.toString(args));
//        System.out.println("keys : " + Arrays.toString(parameterNames));
        System.out.println("before " + joinPoint.toString());
    }

    //final增强，不管是抛出异常或者正常退出都会执行
    @After("logSender()")
    public void after(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();
//        System.out.println("args : " + Arrays.toString(args));
//        System.out.println("keys : " + Arrays.toString(parameterNames));
        System.out.println("after " + joinPoint.toString());
    }

    //后置增强，方法正常退出时执行
    @AfterReturning("logSender()")
    public void afterReturning(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();
//        System.out.println("args : " + Arrays.toString(args));
//        System.out.println("keys : " + Arrays.toString(parameterNames));
        System.out.println("afterReturning " + joinPoint.toString());
    }

    //异常抛出增强
    @AfterThrowing("logSender()")
    public void afterThrowing(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();
//        System.out.println("args : " + Arrays.toString(args));
//        System.out.println("keys : " + Arrays.toString(parameterNames));
        System.out.println("afterThrowing " + joinPoint.toString());
    }

}
