package com.example.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.domain.LoginLog;

@Repository
public class LoginLogDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

    private final static String INSERT_LOGIN_LOG_SQL = "INSERT INTO t_login_log (user_id, ip, login_datetime) VALUES (?, ?, ?)";
    
    public void insertLoginLog(LoginLog log) {
        Object [] args = {log.getUserId(), log.getIp(), log.getLoginDate()};
        jdbcTemplate.update(INSERT_LOGIN_LOG_SQL, args);
    }


}