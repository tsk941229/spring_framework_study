package com.springbook.view.board;

import com.springbook.board.BoardVO;
import com.springbook.board.impl.BoardDAO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetBoardController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("글 상세 조회");

        String seq = request.getParameter("seq");

        BoardVO vo = new BoardVO();
        vo.setSeq(Integer.parseInt(seq));

        BoardDAO boardDAO = new BoardDAO();
        BoardVO board = boardDAO.getBoard(vo);

//        HttpSession session = request.getSession();
//        session.setAttribute("board", board);
//        return "getBoard";

        ModelAndView mav = new ModelAndView();
        mav.addObject("board", board);
        mav.setViewName("getBoard");

        return mav;

    }
}
