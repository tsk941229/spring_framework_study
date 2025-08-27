package com.springbook.view.controller;

import com.springbook.board.BoardVO;
import com.springbook.board.impl.BoardDAO;
import com.springbook.user.UserVO;
import com.springbook.user.impl.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class DispatcherServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private HandlerMapping handlerMapping;
    private ViewResolver viewResolver;

    @Override
    public void init() throws ServletException {
        handlerMapping = new HandlerMapping();
        viewResolver = new ViewResolver();
        viewResolver.setPrefix("./");
        viewResolver.setSuffix(".jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        // 1. 클라이언트의 요청 path 정보를 추출한다.
        String uri = req.getRequestURI();
        String path = uri.substring(uri.lastIndexOf('/'));

        // 2. HandlerMapping을 통해 path에 해당하는 Controller를 검색한다.
        Controller ctrl = handlerMapping.getController(path);

        // 3. 검색된 Controller를 실행한다.
        String viewName = ctrl.handleRequest(req, res);

        // 4. ViewResolver를 통해 viewName에 해당하는 화면을 검색한다.
        String view = null;
        if(!viewName.contains(".do")) {
            view = viewResolver.getView(viewName);
        } else {
            view = viewName;
        }

        // 5. 검색된 화면으로 이동한다.
        res.sendRedirect(view);

    }

}
