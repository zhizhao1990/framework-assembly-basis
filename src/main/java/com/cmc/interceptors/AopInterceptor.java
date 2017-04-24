package com.cmc.interceptors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * AOP拦截器.
 * @author Thomas Lee
 * @version 2017年4月23日 下午5:31:12
 */
@Aspect
public class AopInterceptor {

    /**
     * 切入点
     * 1. execution执行
     * 2. 第一个*代表返回任何类型
     * 3. 拦截包和类
     * 4. 第二个*是代表所有方法
     * 5. 方法中所有类型的参数
     */
    @Pointcut("execution(* com.cmc.user.service.biz.impl.UserServiceImpl.*(..))")
    private void anyMethod() {
    }

    /** 前置通知，满足切入点（anyMethod）而且方法参数是一个String类型 */
    @Before("anyMethod() && args(name)")
    public void beforeAnyMethod(String name) {
        System.out.println("前置通知" + name);
    }

    /** 返回通知，满足切入点，并且获取返回值 */
    @AfterReturning(pointcut = "anyMethod()", returning = "result")
    public void afterReturningAnyMethod(String result) {
        System.out.println("后置通知" + result);
    }

    /** 后置通知 */
    @After("anyMethod()")
    public void afterAnyMethod() {
        System.out.println("最终通知");
    }

    /** 例外通知，并且获取例外 */
    @AfterThrowing(pointcut = "anyMethod()", throwing = "e")
    public void throwAnyMethod(Exception e) {
        System.out.println("例外通知" + e);
    }

    /** 环绕通知 */
    @Around("anyMethod()")
    public Object aroundAnyMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("进入方法");
        Object result = pjp.proceed();
        System.out.println("退出方法");
        return result;
    }

}