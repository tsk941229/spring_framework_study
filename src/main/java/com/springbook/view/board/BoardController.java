package com.springbook.view.board;

import com.springbook.board.BoardVO;
import com.springbook.board.impl.BoardDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("board")
public class BoardController {

    // 검색 조건 목록 설정
    @ModelAttribute("conditionMap")
    public Map<String, String> searchConditionMap() {
        Map<String, String> conditionMap = new HashMap<String, String>();
        conditionMap.put("제목", "TITLE");
        conditionMap.put("내용", "CONTENT");
        return conditionMap;
    }

    // 글 목록 조회
    @RequestMapping("/getBoardList.do")
    public String getBoardList(
            @RequestParam(value = "searchCondition", defaultValue = "TITLE", required = false) String condition,
            @RequestParam(value = "searchKeyword", defaultValue = "", required = false) String keyword,
            BoardDAO boardDAO, Model model
    ) {
        System.out.println("검색 조건 : " + condition);
        System.out.println("검색 단어 : " + keyword);

        List<BoardVO> boardList = boardDAO.getBoardList();
        model.addAttribute("boardList", boardList);

        return "getBoardList.jsp";

    }

    // 글 상세 조회
    @RequestMapping("/getBoard.do")
    public String getBoard(BoardVO boardVO, BoardDAO boardDAO, Model model) {

        BoardVO board = boardDAO.getBoard(boardVO);
        model.addAttribute("board", board);

        return "getBoard.jsp";

    }

    // 글 등록
    @RequestMapping("/insertBoard.do")
    public String insertBoard(BoardVO boardVO, BoardDAO boardDAO, ModelAndView mav) {

        boardDAO.insertBoard(boardVO);
        return "getBoardList.do";

    }

    // 글 수정
    @RequestMapping("/updateBoard.do")
    public String updateBoard(@ModelAttribute("board") BoardVO boardVO, BoardDAO boardDAO, ModelAndView mav) {
        System.out.println("번호: " + boardVO.getSeq());
        System.out.println("제목: " + boardVO.getTitle());
        System.out.println("내용: " + boardVO.getContent());
        System.out.println("작성자: " + boardVO.getWriter());
        System.out.println("날짜: " + boardVO.getRegdate());
        System.out.println("cnt: " + boardVO.getCnt());
        boardDAO.updateBoard(boardVO);
        return "getBoardList.do";

    }

    // 글 삭제
    @RequestMapping("/deleteBoard.do")
    public String deleteBoard(BoardVO boardVO, BoardDAO boardDAO, ModelAndView mav) {

        boardDAO.deleteBoard(boardVO);
        return "getBoardList.do";

    }

}
