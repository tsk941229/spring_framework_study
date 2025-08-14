package com.springbook.common;

import java.sql.*;

public class JDBCUtil {

    public static Connection getConnection() {

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "taeseok2", "1234");

            return conn;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {

        if(pstmt != null) {

            try {
                if(!pstmt.isClosed()) pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                pstmt = null;
            }

        }

        if(conn != null) {

            try {
                if(!conn.isClosed()) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conn = null;
            }

        }

        if(rs != null) {

            try {
                if(!rs.isClosed()) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                rs = null;
            }

        }

    }

}
