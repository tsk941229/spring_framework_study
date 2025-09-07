package com.springbook.view.user;

import com.springbook.user.UserVO;
import com.springbook.user.impl.UserDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String login(UserVO userVO, UserDAO userDAO, HttpSession session) {

        if(userVO.getId() == null || userVO.getId().equals("")) {
            throw new IllegalArgumentException("아이디는 반드시 입력해야 합니다.");
        }

        UserVO user = userDAO.getUser(userVO);
        if(user != null) {
            session.setAttribute("userName", user.getName());
            return "getBoardList.do";
        }
        return "login.jsp";
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public String loginView(UserVO vo) {

        System.out.println("로그인 화면으로 이동");
        vo.setId("tester01");
        vo.setPassword("1234");

        return "login.jsp";
    }


}
