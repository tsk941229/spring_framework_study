package com.springbook.common;

import org.aspectj.lang.JoinPoint;

public class BeforeAdvice {

    public void beforeLog(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        System.out.println("[사전 처리] " + method + "() 메소드 ARGS 정보 : " + args[0].toString());
    }



}
