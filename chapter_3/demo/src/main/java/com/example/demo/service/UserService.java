package com.example.demo.service;

import com.example.demo.dao.LoginLogDao;
import com.example.demo.dao.UserDao;
import com.example.demo.domain.LoginLog;
import com.example.demo.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 负责将持久层 DAO 组织起来, 完成用户登录认证, 记录日志
@Service
public class UserService {
    private UserDao userDao;
    private LoginLogDao loginLogDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }

    public boolean hasMatchUser(String userName, String password) {
        return (userDao.getMatchCount(userName, password) >= 1);
    }

    public User findUserByUserName(String userName) {
        return userDao.findUserByUserName(userName);
    }

    // 登陆成功的事务
    @Transactional
    public void loginSuccess(User user) {
        user.setCredits(user.getCredits()+5);
        LoginLog loginLog = new LoginLog(0, user.getUserId(), user.getLastIp(), user.getLastVisit());
        
        userDao.updateLoginInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }

    
}
