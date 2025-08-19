package com.springbook.user.impl;

import com.springbook.user.UserVO;

public class UserServiceImpl {

    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserVO getUser(UserVO vo) {

//        if(true) throw new RuntimeException("런타임 에러");

        return userDAO.getUser(vo);
    }

}
