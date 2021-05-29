package com.example.domain;
import java.io.Serializable;
import java.util.Date;

// 领域对象也被称为实体类, 它代表了业务的状态, 且贯穿展现层, 业务层和持久层, 并最终被持久化到数据库中
// 领域对象使数据库表操作以面向对象的方式进行, 为程序扩展带来灵活性
// 领域对象不一定等同于数据库表, 对于简单应用来说, 领域对象往往拥有对应的数据表

// 领域对象一般要实现 Serializable 接口, 以方便序列化
public class User implements Serializable {
    private int userId;
    private String userName;
    private String password;
    private int credits;
    private String lastIp;
    private Date lastVisit;

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCredits() {
		return this.credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public String getLastIp() {
		return this.lastIp;
	}

	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}

	public Date getLastVisit() {
		return this.lastVisit;
	}

	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}

}