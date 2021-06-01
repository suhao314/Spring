package com.example.demo.domain;

import java.util.Date;

public class LoginLog {
    private int longinLogId;
    private int userId;
    private String ip;
    private Date longinDate;

    public int getLonginLogId() {
        return this.longinLogId;
    }

    public void setLonginLogId(int longinLogId) {
        this.longinLogId = longinLogId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getLonginDate() {
        return this.longinDate;
    }

    public void setLonginDate(Date longinDate) {
        this.longinDate = longinDate;
    }

    // 构造器
    public LoginLog(int loginLogId, int userId, String ip, Date loginDate) {
        this.longinLogId = loginLogId;
        this.userId = userId;
        this.ip = ip;
        this.longinDate = loginDate;
    }
}
