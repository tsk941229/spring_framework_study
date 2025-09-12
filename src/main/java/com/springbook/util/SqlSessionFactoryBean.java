package com.springbook.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

// 안쓰고 Spring Bean에 Mybatis 등록해서 씀
public class SqlSessionFactoryBean {
    private static SqlSessionFactory sqlSessionFactory = null;

    static {

        if(sqlSessionFactory == null) {
            try {
                Reader reader = Resources.getResourceAsReader("sql-map-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static SqlSession getSqlSessionInstance() {
        return sqlSessionFactory.openSession();
    }

}
