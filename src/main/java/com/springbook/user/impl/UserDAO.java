package com.springbook.user.impl;

import com.springbook.common.JDBCUtil;
import com.springbook.user.UserVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    private final String USERS_GET = "select * from users where id = ? and password = ?";

    // 유저 리스트 조회
    public UserVO getUser(UserVO vo) {

        UserVO userVO = new UserVO();

        try {

            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(USERS_GET);

            pstmt.setString(1, vo.getId());
            pstmt.setString(2, vo.getPassword());

            rs = pstmt.executeQuery();

            while(rs.next()) {

                userVO.setId(rs.getString("id"));
                userVO.setPassword(rs.getString("password"));
                userVO.setName(rs.getString("name"));
                userVO.setRole(rs.getString("role"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, pstmt, rs);
        }

        return userVO;

    }

}
