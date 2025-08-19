package com.springbook.common;

import org.aspectj.lang.JoinPoint;

public class AfterThrowingAdvice {

    public void exceptionLog(JoinPoint joinPoint, Exception exceptObj) {

        String method = joinPoint.getSignature().getName();
        String longString = joinPoint.getSignature().toLongString();
        String shortString = joinPoint.getSignature().toShortString();

        System.out.println("[예외 처리] " + shortString + "() 메소드 수행 중 발생된 예외 메시지 : " + exceptObj.getMessage());
    }

}
