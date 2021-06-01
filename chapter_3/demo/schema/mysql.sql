-- 创建数据库
DROP DATABASE IF EXISTS sampledb;
CREATE DATABASE sampledb DEFAULT CHARACTER SET utf8;


USE sampledb;


-- 创建用户表
CREATE TABLE t_user (
    user_id     INT AUTO_INCREMENT PRIMARY KEY,
    user_name   VARCHAR(30),
    credits     INT,
    password	VARCHAR(32),
    last_visit  datetime,
    last_ip     VARCHAR(23)
) ENGINE=InnoDB;


-- 创建用户登录日志
CREATE TABLE t_login_log (
    login_log_id    INT AUTO_INCREMENT PRIMARY KEY,
    user_id         INT,
    ip              VARCHAR(23),
    login_datetime  datetime
) ENGINE=InnoDB;


-- 插入管理员
INSERT INTO t_user (user_name, password) VALUES ('admin', '123456');
COMMIT;
