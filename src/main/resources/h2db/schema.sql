DROP TABLE IF EXISTS USER1;
CREATE TABLE USER1(
 ID bigint PRIMARY KEY AUTO_INCREMENT,
 user_name VARCHAR(100) NOT NULL COMMENT '用户名',
 password VARCHAR(200) NOT NULL COMMENT '加密密码',
 email VARCHAR(200) DEFAULT NULL COMMENT '邮箱',
 mobile VARCHAR(100) DEFAULT NULL COMMENT '电话',
 status int DEFAULT 0,
 create_time timestamp,
 modify_time timestamp
);