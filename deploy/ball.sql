/*
Navicat MySQL Data Transfer

Source Server         : well
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : well

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2014-08-10 07:54:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `ball_challenge`
-- ----------------------------
DROP TABLE IF EXISTS `ball_challenge`;
CREATE TABLE `ball_challenge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `team_id` int(11) NOT NULL,
  `court_id` int(11) NOT NULL,
  `challenge_time` date NOT NULL,
  `challenge_desc` varchar(1024) DEFAULT NULL,
  `challenge_result` varchar(255) DEFAULT NULL,
  `goal_count` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `is_deleted` int(11) NOT NULL DEFAULT '0',
  `gmt_modify` datetime NOT NULL,
  `gmt_create` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ball_challenge
-- ----------------------------

-- ----------------------------
-- Table structure for `ball_challenge_apply`
-- ----------------------------
DROP TABLE IF EXISTS `ball_challenge_apply`;
CREATE TABLE `ball_challenge_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `challenge_id` int(11) NOT NULL,
  `team_id` int(11) NOT NULL,
  `goal_count` int(11) DEFAULT NULL,
  `challenge_result` varchar(16) DEFAULT NULL,
  `accept` int(11) NOT NULL DEFAULT '0',
  `is_deleted` int(11) NOT NULL DEFAULT '0',
  `gmt_modify` datetime NOT NULL,
  `gmt_create` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ball_challenge_apply
-- ----------------------------

-- ----------------------------
-- Table structure for `ball_comment`
-- ----------------------------
DROP TABLE IF EXISTS `ball_comment`;
CREATE TABLE `ball_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_id` int(11) NOT NULL,
  `comment_type` varchar(16) NOT NULL,
  `user_id` int(11) NOT NULL,
  `msg` varchar(1024) NOT NULL,
  `is_deleted` int(11) NOT NULL DEFAULT '0',
  `gmt_modify` datetime NOT NULL,
  `gmt_create` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ball_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `ball_court`
-- ----------------------------
DROP TABLE IF EXISTS `ball_court`;
CREATE TABLE `ball_court` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `address` varchar(1024) DEFAULT NULL,
  `type` varchar(32) DEFAULT NULL,
  `open_time` varchar(16) DEFAULT NULL,
  `square` varchar(256) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `is_deleted` int(11) NOT NULL DEFAULT '0',
  `status` int(11) DEFAULT NULL,
  `court_desc` varchar(1024) DEFAULT NULL,
  `gmt_modify` datetime NOT NULL,
  `gmt_create` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ball_court
-- ----------------------------
INSERT INTO `ball_court` VALUES ('1', 's', '2', '2', '2014-', '2', '2', '0', '2', '2', '2014-07-31 06:08:47', '2014-07-31 06:08:47');

-- ----------------------------
-- Table structure for `ball_court_apply`
-- ----------------------------
DROP TABLE IF EXISTS `ball_court_apply`;
CREATE TABLE `ball_court_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `court_id` int(11) NOT NULL,
  `booking_time` datetime NOT NULL,
  `status` int(11) DEFAULT '0',
  `is_deleted` int(11) DEFAULT '0',
  `gmt_modify` datetime DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ball_court_apply
-- ----------------------------

-- ----------------------------
-- Table structure for `ball_goods`
-- ----------------------------
DROP TABLE IF EXISTS `ball_goods`;
CREATE TABLE `ball_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(64) NOT NULL,
  `goods_desc` varchar(1024) DEFAULT NULL,
  `price` bigint(20) NOT NULL DEFAULT '0',
  `court_id` int(11) NOT NULL,
  `is_deleted` int(11) NOT NULL DEFAULT '0',
  `gmt_modify` datetime NOT NULL,
  `gmt_create` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ball_goods
-- ----------------------------

-- ----------------------------
-- Table structure for `ball_order`
-- ----------------------------
DROP TABLE IF EXISTS `ball_order`;
CREATE TABLE `ball_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) NOT NULL,
  `buyer_id` int(11) NOT NULL,
  `seller_id` int(11) NOT NULL,
  `delivery_time` datetime DEFAULT NULL,
  `leave_msg` varchar(1024) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `is_deleted` int(11) NOT NULL DEFAULT '0',
  `gmt_modify` datetime NOT NULL,
  `gmt_create` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ball_order
-- ----------------------------

-- ----------------------------
-- Table structure for `ball_session`
-- ----------------------------
DROP TABLE IF EXISTS `ball_session`;
CREATE TABLE `ball_session` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `s_id` varchar(64) NOT NULL,
  `store_data` text,
  `expire_time` datetime NOT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modify` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ball_session
-- ----------------------------

-- ----------------------------
-- Table structure for `ball_team`
-- ----------------------------
DROP TABLE IF EXISTS `ball_team`;
CREATE TABLE `ball_team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `team_desc` varchar(1024) DEFAULT NULL,
  `team_type` varchar(64) DEFAULT NULL,
  `can_join` int(11) DEFAULT NULL,
  `is_deleted` int(11) DEFAULT '0',
  `gmt_modify` datetime NOT NULL,
  `gmt_create` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ball_team
-- ----------------------------

-- ----------------------------
-- Table structure for `ball_team_apply`
-- ----------------------------
DROP TABLE IF EXISTS `ball_team_apply`;
CREATE TABLE `ball_team_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `team_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `is_deleted` int(11) NOT NULL DEFAULT '0',
  `gmt_modify` datetime NOT NULL,
  `gmt_create` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ball_team_apply
-- ----------------------------

-- ----------------------------
-- Table structure for `ball_team_member`
-- ----------------------------
DROP TABLE IF EXISTS `ball_team_member`;
CREATE TABLE `ball_team_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `team_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modify` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ball_team_member
-- ----------------------------

-- ----------------------------
-- Table structure for `ball_team_recruit`
-- ----------------------------
DROP TABLE IF EXISTS `ball_team_recruit`;
CREATE TABLE `ball_team_recruit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `team_id` int(11) NOT NULL,
  `recruit_desc` varchar(1024) DEFAULT NULL,
  `is_deleted` int(11) NOT NULL DEFAULT '0',
  `gmt_modify` datetime NOT NULL,
  `gmt_create` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ball_team_recruit
-- ----------------------------

-- ----------------------------
-- Table structure for `ball_user`
-- ----------------------------
DROP TABLE IF EXISTS `ball_user`;
CREATE TABLE `ball_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(16) NOT NULL,
  `real_name` varchar(16) DEFAULT NULL,
  `email` varchar(16) DEFAULT NULL,
  `address` varchar(1024) DEFAULT NULL,
  `password` varchar(32) NOT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `ground_type_of_enjoy` varchar(32) DEFAULT NULL,
  `special` varchar(1024) DEFAULT NULL,
  `ground_of_daily` varchar(1024) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `img` varchar(256) DEFAULT NULL,
  `gmt_modify` datetime NOT NULL,
  `gmt_create` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ball_user
-- ----------------------------
INSERT INTO `ball_user` VALUES ('13', 'zhangsan', null, null, null, 'AEF42A4125884C16D6AB400F7F6954CA', null, null, null, null, null, null, '2014-08-01 17:34:18', '2014-08-01 17:34:18');
