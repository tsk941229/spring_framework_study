package com.springbook.common;

import com.springbook.user.UserVO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

//@Service
//@Aspect
public class AfterReturningAdvice {

    @AfterReturning(pointcut = "PointcutCommon.getPointcut()", returning = "returnObj")
    public void afterLog(JoinPoint joinPoint, Object returnObj) {

        String method = joinPoint.getSignature().getName();
        if(returnObj instanceof UserVO) {
            UserVO userVO = (UserVO) returnObj;
            if(userVO.getRole().equals("ADMIN")) {
                System.out.println(userVO.getName() + " 로그인(ADMIN)");
            }
        }

        System.out.println("[사후처리] " + method + "() 메소드 리턴값 : " + returnObj.toString());
    }

}
