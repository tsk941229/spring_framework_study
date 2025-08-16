package com.springbook.user;

import com.springbook.user.impl.UserServiceImpl;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserServiceClient {

    public static void main(String[] args) {

        AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");

        UserServiceImpl userServiceImpl = (UserServiceImpl) container.getBean("UserServiceImpl");

        UserVO userVO = new UserVO();
        userVO.setId("tester01");
        userVO.setPassword("1234");

        UserVO selectedUser = userServiceImpl.getUser(userVO);

        System.out.println(selectedUser.toString());


    }

}
