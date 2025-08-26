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
        System.out.println(path);

        // 2. 클라이언트의 요청 path에 따라 적절히 분기처리 한다.
        if(path.equals("/login.do")) {

            // 로그인
            loginProc(req, res);

        } else if(path.equals("/logout.do")) {

            // 로그아웃
            logoutProc(req, res);

        } else if(path.equals("/insertBoard.do")) {

            // 글 등록
            insertBoardProc(req, res);

        } else if(path.equals("/updateBoard.do")) {

            // 글 수정
            updateBoardProc(req, res);

        } else if(path.equals("/deleteBoard.do")) {

            deleteBoardProc(req, res);

        } else if(path.equals("/getBoard.do")) {

            // 글 상세 조회
            getBoardProc(req, res);

        } else if(path.equals("/getBoardList.do")) {

            // 글 목록 조회
            getBoardListProc(req, res);

        }

    }


    // 로그인 처리
    private void loginProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("로그인 처리");

        // 1. 사용자 입력 정보 추출
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        // 2. DB 연동 처리
        UserVO vo = new UserVO();
        vo.setId(id);
        vo.setPassword(password);

        UserDAO userDAO = new UserDAO();
        UserVO user = userDAO.getUser(vo);

        // 3. 화면 네비게이션
        if(user != null) {
            response.sendRedirect("/getBoardList.do");
        } else {
            response.sendRedirect("login.jsp");
        }

    }

    // 로그아웃 처리
    private void logoutProc(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("로그아웃 처리");

        // 1. 브라우저와 연결된 세션 객체 강제 종료
        HttpSession session = request.getSession();
        session.invalidate();

        // 2. 세션 종료 후 메인 화면으로 이동
        response.sendRedirect("login.jsp");

    }

    // 글 목록 조회 처리
    private void getBoardListProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("글 목록 조회");

        // 1. 사용자 입력 정보 추출 (검색 기능은 나중에 구현)
        // 2. DB 연동 처리
        BoardVO vo = new BoardVO();
        BoardDAO boardDAO = new BoardDAO();
        List<BoardVO> boardList = boardDAO.getBoardList();

        // 3. 검색 결과를 세션에 저장하고 목록 화면으로 이동한다.
        HttpSession session = request.getSession();
        session.setAttribute("boardList", boardList);
        response.sendRedirect("getBoardList.jsp");

    }

    // 글 상세 조회 처리
    private void getBoardProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("글 상세 조회");

        String seq = request.getParameter("seq");

        BoardVO vo = new BoardVO();
        vo.setSeq(Integer.parseInt(seq));

        BoardDAO boardDAO = new BoardDAO();
        BoardVO board = boardDAO.getBoard(vo);

        HttpSession session = request.getSession();
        session.setAttribute("board", board);

        response.sendRedirect("getBoard.jsp");

    }

    // 글 등록 처리
    private void insertBoardProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("글 등록 처리");

        // 1. 사용자 입력 정보 추출
        String title = request.getParameter("title");
        String writer = request.getParameter("writer");
        String content = request.getParameter("content");

        // 2. DB 연동 처리
        BoardVO vo = new BoardVO();
        vo.setTitle(title);
        vo.setWriter(writer);
        vo.setContent(content);

        BoardDAO boardDAO = new BoardDAO();
        boardDAO.insertBoard(vo);

        // 3. 화면 네비게이션
        response.sendRedirect("getBoardList.do");

    }

    // 글 수정 처리
    private void updateBoardProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("글 수정 처리");

        String seq = request.getParameter("seq");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        BoardVO vo = new BoardVO();
        vo.setSeq(Integer.parseInt(seq));
        vo.setTitle(title);
        vo.setContent(content);

        BoardDAO boardDAO = new BoardDAO();
        boardDAO.updateBoard(vo);

        response.sendRedirect("getBoardList.do");

    }

    // 글 삭제 처리
    private void deleteBoardProc(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("글 삭제 처리");

        String seq = request.getParameter("seq");

        BoardVO vo = new BoardVO();
        vo.setSeq(Integer.parseInt(seq));

        BoardDAO boardDAO = new BoardDAO();
        boardDAO.deleteBoard(vo);

        response.sendRedirect("getBoardList.do");

    }

}
