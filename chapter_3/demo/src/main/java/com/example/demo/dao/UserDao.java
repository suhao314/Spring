package com.example.demo.dao;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.example.demo.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;
    
    // 根据用户名和密码查询的 SQL 语句, 验证用户密码是否输入正确
    private final static String MATCH_COUNT_SQL = "SELECT COUNT(*) FROM t_user WHERE user_name=? and password=? ";
    // 更新用户登录记录的 SQL 语句, 登录成功后执行
    private final static String UPDATE_LOGIN_INFO_SQL = "UPDATE t_user SET last_visit=?, last_ip=?, credits=? WHERE user_id=? ";
    // 根据用户名查找用户
    private final static String FIND_USER_BY_USERNAME = "SELECT user_id, user_name, credits, FROM t_user WHERE user_name=? "; 

    public int getMatchCount(String userName, String password) {
        return jdbcTemplate.queryForList(MATCH_COUNT_SQL).size();
    }

    // 同名用户返回查到的第一个
    public User findUserByUserName(final String userName) {
        return jdbcTemplate.query(FIND_USER_BY_USERNAME, new RowMapper<User> () {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                String userName = resultSet.getString("user_name");
                String lastIp = resultSet.getString("last_ip");
                int userId = resultSet.getInt("user_id");
                int credits = resultSet.getInt("credits");
                Date lastVisit = resultSet.getDate("last_visit");
                String password = resultSet.getString("password");

                User user = new User(userId, userName, password, credits, lastIp, lastVisit);
                return user;
            } 
        }).get(0);
    }


    // RowMapper 对象将数据库中的子断和对象属性进行一一对应
    public List<User> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM t_user ", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                String userName = resultSet.getString("user_name");
                String lastIp = resultSet.getString("last_ip");
                int userId = resultSet.getInt("user_id");
                int credits = resultSet.getInt("credits");
                Date lastVisit = resultSet.getDate("last_visit");
                String password = resultSet.getString("password");

                User user = new User(userId, userName, password, credits, lastIp, lastVisit);
                return user;
            } 
        });
    }

    public void updateLoginInfo(User user) {
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL, new Object[] {user.getLastVisit(), user.getLastIp(), user.getCredits(), user.getLastIp()});
    }


    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
