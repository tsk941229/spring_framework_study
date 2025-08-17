package com.springbook.common;

public class LogAdvice {

    public LogAdvice() {
        System.out.println("스프링이 생성해줌 지금!!");
    }

    public void printLog() {
        System.out.println("[공통 로그] 비즈니스 로직 수행 전 동작");
    }

}
