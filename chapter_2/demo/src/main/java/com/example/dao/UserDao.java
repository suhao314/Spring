package com.example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.example.domain.User;



// 通过 Spring 注解定义一个 DAO
@Repository
public class UserDao {
    // 根据用户名查询的用户的 SQL 语句
    private final static String MATCH_COUNT_SQL="SELECT COUNT(*) FROM t_user WHERE user_name=? AND password=?";
    // 更新登录日志的 SQL 语句
    private final static String UPDATE_LOGIN_INFO_SQL = "UPDATE t_user SET last_visit=?, last_ip=?, credits=? WHERE user_id=?";
    // 根据用户名查询 SQL 语句
    private final static String FIND_USER_BY_NAME = "SELECT user_id, user_name, credits FROM t_user WHERE user_name=? ";


    private JdbcTemplate jdbcTemplate;


    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public int getMatchCount(String userName, String password) {
        // String sqlStr = "SELECT COUNT(*) FROM t_user WHERE user_name=? AND password=?";
        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL, new Object [] {userName, password}, Integer.class);
    }


    // JdbcTemplate.query(String sql, Object[] args, RowCallbackHandler)
    // sql语句, 语句中 ? 占位符对应的参数数组, 查询结果的处理回调接口; 此处使用匿名内部类实现, 将 ResultSet 转换为 User 对象
    public User findUserByUserName(final String userNmae) {
        final User user = new User();
        jdbcTemplate.query(FIND_USER_BY_NAME, new Object[] {userNmae},
            // 查询结果的处理回调接口, processRow 负责将查询结果从 ResultSet 装载到类似于领域对象的实体中
            new RowCallbackHandler() {
                public void processRow(ResultSet rs) throws SQLException {
                    user.setUserId(rs.getInt("user_id"));
                    user.setUserName(userNmae);
                    user.setCredits(rs.getInt("credits"));
                }
            }
        );
        return user;
    }


    // 更新用户登录日志
    public void updateLoginInfo(User user) {
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL, new Object[] {user.getLastVisit(), user.getLastIp(), user.getCredits(), user.getUserId()});
    }

}