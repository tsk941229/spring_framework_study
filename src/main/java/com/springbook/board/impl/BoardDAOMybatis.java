package com.springbook.board.impl;

import com.springbook.board.BoardVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDAOMybatis {

    @Autowired
    private SqlSessionTemplate mybatis;

    public void insertBoard(BoardVO vo) {
        mybatis.insert("insertBoard", vo);
        mybatis.commit();
    }

    public void updateBoard(BoardVO vo) {
        mybatis.update("updateBoard", vo);
        mybatis.commit();
    }

    public void deleteBoard(BoardVO vo) {
        mybatis.delete("deleteBoard", vo);
        mybatis.commit();
    }

    public BoardVO getBoard(BoardVO vo) {
        return (BoardVO) mybatis.selectOne("getBoard", vo);
    }

    public List<BoardVO> getBoardList(BoardVO vo) {
        return mybatis.selectList("getBoardList", vo);
    }

}
