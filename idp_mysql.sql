/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Version : 50635
 Source Host           : localhost
 Source Database       : idp

 Target Server Version : 50635
 File Encoding         : utf-8

 Date: 08/06/2017 14:53:37 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `ip` varchar(20) NOT NULL,
  `time` varchar(10) NOT NULL,
  `msg` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `t_log_time` (`time`),
  KEY `t_log_ip` (`ip`),
  KEY `uid` (`uid`),
  CONSTRAINT `t_log_uid` FOREIGN KEY (`uid`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `t_sp`
-- ----------------------------
DROP TABLE IF EXISTS `t_sp`;
CREATE TABLE `t_sp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appkey` varchar(64) NOT NULL,
  `appsecret` varchar(64) NOT NULL,
  `time` varchar(20) NOT NULL,
  `redirect` varchar(100) NOT NULL,
  `logout` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `t_sp`
-- ----------------------------
BEGIN;
INSERT INTO `t_sp` VALUES ('1', 'b15e76a41f50e', '1234567890', '', 'http://localhost:8080/demo/doLogin', 'http://localhost:8080/demo/doLogout');
COMMIT;

-- ----------------------------
--  Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(64) NOT NULL,
  `time` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_user_username` (`username`) USING BTREE,
  KEY `t_user_time` (`time`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `t_user`
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES ('1', '13070537189', '40f077a10b4420b7f517c7e23c972581', '2017/07/31');
COMMIT;

-- ----------------------------
--  Table structure for `t_yzm`
-- ----------------------------
DROP TABLE IF EXISTS `t_yzm`;
CREATE TABLE `t_yzm` (
  `phone` varchar(12) NOT NULL,
  `code` varchar(6) NOT NULL,
  `time` varchar(10) NOT NULL,
  KEY `t_yzm_username` (`phone`),
  KEY `t_yzm_time` (`time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
