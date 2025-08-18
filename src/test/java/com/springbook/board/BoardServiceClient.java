package com.springbook.board;

import com.springbook.board.impl.BoardServiceImpl;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class BoardServiceClient {

    public static void main(String[] args) {

        // Spring 컨테이너 구동
        AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");

        // Spring 컨테이너로 부터 BoardServiceImpl 객체 Lookup
        BoardService boardService = (BoardService) container.getBean("boardService");


        // 글 등록
        BoardVO vo = new BoardVO();
        vo.setTitle("임시 제목");
        vo.setWriter("홍길동");
        vo.setContent("임시 내용.................");
//        boardService.insertBoard(vo);

        // 글 목록 조회
        List<BoardVO> boardList = boardService.getBoardList();

        for (BoardVO board : boardList) {

            System.out.println(board.toString());

        }

        // 컨테이너 종료
        container.close();


    }

}
