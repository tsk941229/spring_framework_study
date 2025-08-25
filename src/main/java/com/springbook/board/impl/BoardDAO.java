package com.springbook.board.impl;

import com.springbook.board.BoardVO;
import com.springbook.common.JDBCUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository("boardDAO")
public class BoardDAO {

    // JDBC 관련
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    // SQL
    private final String BOARD_INSERT = "insert into BOARD (seq, title, writer, content) values((select nvl(max(seq), 0) + 1 from BOARD),?,?,?)";
    private final String BOARD_UPDATE = "update BOARD set title = ?, content = ? where seq = ?";
    private final String BOARD_DELETE = "delete from BOARD where seq = ?";
    private final String BOARD_GET = "select * from BOARD where seq = ?";
    private final String BOARD_LIST = "select * from BOARD order by seq desc";

    // ----- CRUD 구현 ----- //

    // 글 등록
    public void insertBoard(BoardVO vo) {

        System.out.println("insertBoard() :: 글 등록");

        conn = JDBCUtil.getConnection();

        try {

            pstmt = conn.prepareStatement(BOARD_INSERT);
            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getWriter());
            pstmt.setString(3, vo.getContent());
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, pstmt, rs);
        }

    }

    // 글 수정
    public void updateBoard(BoardVO vo) {

        System.out.println("updateBoard() :: 글 수정");

        conn = JDBCUtil.getConnection();

        try {

            pstmt = conn.prepareStatement(BOARD_UPDATE);
            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getContent());
            pstmt.setInt(3, vo.getSeq());
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, pstmt, rs);
        }

    }

    // 글 삭제
    public void deleteBoard(BoardVO vo) {

        System.out.println("deleteBoard() :: 글 삭제");

        try {

            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(BOARD_DELETE);
            pstmt.setInt(1, vo.getSeq());
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, pstmt, rs);
        }

    }

    // 글 상세 조회
    public BoardVO getBoard(BoardVO vo) {

        System.out.println("getBoard() :: 글 상세 조회");

        BoardVO board = null;

        try {

            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(BOARD_GET);
            pstmt.setInt(1, vo.getSeq());
            rs = pstmt.executeQuery();

            while (rs.next()) {

                board = new BoardVO();
                board.setSeq(rs.getInt("seq"));
                board.setTitle(rs.getString("title"));
                board.setWriter(rs.getString("writer"));
                board.setRegdate(rs.getDate("regdate"));
                board.setContent(rs.getString("content"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, pstmt, rs);
        }
        return board;

    }

    // 글 목록 조회
    public List<BoardVO> getBoardList() {

        System.out.println("getBoardList() :: 글 목록 조회");

        List<BoardVO> list = new ArrayList<>();

        try {

            conn = JDBCUtil.getConnection();

            pstmt = conn.prepareStatement(BOARD_LIST);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                BoardVO board = new BoardVO();
                board.setSeq(rs.getInt("seq"));
                board.setTitle(rs.getString("title"));
                board.setWriter(rs.getString("writer"));
                board.setRegdate(rs.getDate("regdate"));
                board.setContent(rs.getString("content"));
                list.add(board);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, pstmt, rs);
        }

        return list;


    }

}
