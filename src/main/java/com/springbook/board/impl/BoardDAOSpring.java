package com.springbook.board.impl;

import com.springbook.board.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDAOSpring{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // SQL
//    (select nvl(max(seq), 0) + 1 from board)
    private final String BOARD_INSERT = "insert into board(seq, title, writer, content) values(?,?,?,?)";
    private final String BOARD_UPDATE = "update board set title = ?, content = ? where seq = ?";
    private final String BOARD_DELETE = "delete board where seq = ?";
    private final String BOARD_GET = "select * from board where seq = ?";
    private final String BOARD_LIST = "select * from board order by seq desc";


    // CRUD 메소드
    // 글 등록
    public void insertBoard(BoardVO vo) {
        System.out.println("===> Spring JDBC로 insertBoard() 기능 처리");
        jdbcTemplate.update(BOARD_INSERT, vo.getSeq(), vo.getTitle(), vo.getWriter(), vo.getContent());
    }

    // 글 수정
    public void updateBoard(BoardVO vo) {
        System.out.println("===> Spring JDBC로 updateBoard() 기능 처리");
        jdbcTemplate.update(BOARD_UPDATE, vo.getTitle(), vo.getWriter(), vo.getContent());
    }

    // 글 삭제
    public void deleteBoard(BoardVO vo) {
        System.out.println("===> Spring JDBC로 deleteBoard() 기능 처리");
        jdbcTemplate.update(BOARD_DELETE, vo.getTitle());
    }

    // 글 상세 조회
    public BoardVO getBoard(BoardVO vo) {
        System.out.println("===> Spring JDBC로 getBoard() 기능 처리");
        Object[] args = {vo.getSeq()};
        return jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());
    }

    // 글 목록 조회
    public List<BoardVO> getBoardList() {
        System.out.println("===> Spring JDBC로 getBoardList() 기능 처리");
        return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
    }

}
