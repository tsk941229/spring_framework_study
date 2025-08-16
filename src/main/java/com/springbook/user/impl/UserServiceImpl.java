package com.springbook.user.impl;

import com.springbook.user.UserVO;

public class UserServiceImpl {

    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserVO getUser(UserVO vo) {
        return userDAO.getUser(vo);
    }

}
