package com.example.demo.dao;

import com.example.demo.domain.LoginLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginLogDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 保存登录日志的 SQL 语句
    private final static String INSERT_LOGIN_LOG_SQL = "INSERT INTO t_login_log(user_id, ip, login_datetime) VALUES (?, ?, ?) ";

    public void insertLoginLog(LoginLog loginLog) {
        Object[] args = {
            loginLog.getUserId(),
            loginLog.getIp(),
            loginLog.getLonginDate()
        };
        jdbcTemplate.update(INSERT_LOGIN_LOG_SQL, args);
    }
}
