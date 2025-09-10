package com.springbook.board.impl;

import com.springbook.board.BoardVO;
import com.springbook.util.SqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDAOMybatis {

    private SqlSession mybatis;

    public BoardDAOMybatis() {
        mybatis = SqlSessionFactoryBean.getSqlSessionInstance();
    }

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
