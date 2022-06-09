package com.posco.insta.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Before("execution(* com.posco.insta.user.service.*.find*(..))")
    public void loggerBefore(){
        //System.out.println("--------before-------");
        //log.info("--------before-------");
    }
    @After("execution(* com.posco.insta.user.service.*.find*(..))")
    public void loggerAfter(){
       // System.out.println("--------After-------");
        //log.info("--------After------");
    }
    @Around("execution(* com.posco.insta.user.service.*.find*(..))")
    public Object loggerAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        long beforeTimeMillis = System.currentTimeMillis();
        log.info("start : " +beforeTimeMillis);
        Object result = proceedingJoinPoint.proceed(); // proceedingJoinPoint.proceed()를 통해 전후로 나눠짐
        long afterTimeMillis = System.currentTimeMillis();
        log.info("before : + "+afterTimeMillis+" 시간차"+(afterTimeMillis-beforeTimeMillis));
        return result;
    }
}
