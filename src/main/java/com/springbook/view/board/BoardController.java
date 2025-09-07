package com.springbook.view.board;

import com.springbook.board.BoardService;
import com.springbook.board.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("board")
public class BoardController {

    @Autowired
    private BoardService boardService;

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
//            @RequestParam(value = "searchCondition", defaultValue = "TITLE", required = false) String condition,
//            @RequestParam(value = "searchKeyword", defaultValue = "", required = false) String keyword,
            BoardVO vo,
            Model model
    ) {

        if(vo.getSearchCondition() == null) vo.setSearchCondition("TITLE");
        if(vo.getSearchKeyword() == null) vo.setSearchKeyword("");

        List<BoardVO> boardList = boardService.getBoardList(vo);
        model.addAttribute("boardList", boardList);

        return "getBoardList.jsp";

    }

    // 글 상세 조회
    @RequestMapping("/getBoard.do")
    public String getBoard(BoardVO boardVO, Model model) {

        BoardVO board = boardService.getBoard(boardVO);
        model.addAttribute("board", board);

        return "getBoard.jsp";

    }

    // 글 등록
    @RequestMapping("/insertBoard.do")
    public String insertBoard(BoardVO boardVO, ModelAndView mav) throws IOException {

        MultipartFile uploadFile = boardVO.getUploadFile();
        if(!uploadFile.isEmpty()) {
            String fileName = uploadFile.getOriginalFilename();
            uploadFile.transferTo(new File("D:/" + fileName));
        }

        boardService.insertBoard(boardVO);
        return "getBoardList.do";

    }

    // 글 수정
    @RequestMapping("/updateBoard.do")
    public String updateBoard(@ModelAttribute("board") BoardVO boardVO, ModelAndView mav) {
        System.out.println("번호: " + boardVO.getSeq());
        System.out.println("제목: " + boardVO.getTitle());
        System.out.println("내용: " + boardVO.getContent());
        System.out.println("작성자: " + boardVO.getWriter());
        System.out.println("날짜: " + boardVO.getRegdate());
        System.out.println("cnt: " + boardVO.getCnt());
        boardService.updateBoard(boardVO);
        return "getBoardList.do";

    }

    // 글 삭제
    @RequestMapping("/deleteBoard.do")
    public String deleteBoard(BoardVO boardVO, ModelAndView mav) {

        boardService.deleteBoard(boardVO);
        return "getBoardList.do";

    }

}
