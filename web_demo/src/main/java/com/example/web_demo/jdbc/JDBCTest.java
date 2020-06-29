package com.example.web_demo.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Types;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2020/9/1 9:26
 * @see
 **/
@Component
public class JDBCTest implements CommandLineRunner {

//    @Autowired
//    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
//        Connection connection = jdbcTemplate.getDataSource().getConnection();
//        connection.setAutoCommit(false);
//        String sql = "select cast(? as  char(10))  a," +
//                "cast(? as  char(10)) b," +
//                "cast(? as  char(10)) c," +
//                "cast(? as  char(10)) d," +
//                "date_format(?, '%Y-%m-%d') e," +
//                "str_to_date(?, '%Y-%m-%d %H:%i:%s') f" +
//                " from dual";
//        PreparedStatement ps = new LoggableStatement(connection, sql);
//        java.util.Date date = new java.util.Date();
//        ps.setString(1, "a");
//        ps.setInt(2, 23);
//        ps.setDouble(3, 23.6);
//        ps.setNull(4, Types.NULL);
//        ps.setDate(5, new Date(date.getTime()));
//        ps.setString(6, "2016-12-15 16:48:40");
//        System.out.println("*******************************");
//        System.out.println(((LoggableStatement) ps).getQueryString());
//        System.out.println("*******************************");
//        ps.execute();
//        connection.commit();
//        connection.close();
    }

}
