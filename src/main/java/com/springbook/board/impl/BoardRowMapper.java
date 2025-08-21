package com.springbook.board.impl;

import com.springbook.board.BoardVO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardRowMapper implements RowMapper<BoardVO> {

    @Override
    public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        BoardVO boardVO = new BoardVO();
        boardVO.setSeq(rs.getInt("seq"));
        boardVO.setTitle(rs.getString("title"));
        boardVO.setContent(rs.getString("content"));
        boardVO.setWriter(rs.getString("writer"));
        boardVO.setRegdate(rs.getDate("regdate"));
        boardVO.setCnt(rs.getInt("cnt"));
        return boardVO;
    }
}
