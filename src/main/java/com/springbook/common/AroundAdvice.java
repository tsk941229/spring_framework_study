package com.springbook.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Aspect
public class AroundAdvice {

    @Around("PointcutCommon.allPointcut()")
    public Object aroundLog(ProceedingJoinPoint joinPoint) throws Throwable {

        String method = joinPoint.getSignature().toShortString();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object resultObj = joinPoint.proceed();

        stopWatch.stop();

        System.out.println(method + " 메소드 수행에 걸린 시간 : " + stopWatch.getTotalTimeMillis() + "(ms)초");

        return resultObj;
    }

}
