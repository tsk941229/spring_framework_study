package com.springbook.view.board;

import com.springbook.board.BoardVO;
import com.springbook.board.impl.BoardDAO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class InsertBoardController {

    @RequestMapping(value = "/insertBoard.do")
    public void insertBoard(BoardVO vo) {
        System.out.println("글 등록 처리");

        BoardDAO boardDAO = new BoardDAO();
        boardDAO.insertBoard(vo);

    }
}
