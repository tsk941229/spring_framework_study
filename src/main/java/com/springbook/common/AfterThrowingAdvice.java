package com.springbook.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterThrowingAdvice {

    @AfterThrowing(pointcut = "PointcutCommon.allPointcut()", throwing = "exceptObj")
    public void exceptionLog(JoinPoint joinPoint, Exception exceptObj) {

        String method = joinPoint.getSignature().getName();
        String longString = joinPoint.getSignature().toLongString();
        String shortString = joinPoint.getSignature().toShortString();

        System.out.println("[예외 처리] " + shortString + "() 메소드 수행 중 발생된 예외 메시지 : " + exceptObj.getMessage());
    }

}
