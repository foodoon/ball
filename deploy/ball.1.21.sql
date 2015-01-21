/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : ball

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2015-01-21 23:00:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `challenge`
-- ----------------------------
DROP TABLE IF EXISTS `challenge`;
CREATE TABLE `challenge` (
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
-- Records of challenge
-- ----------------------------

-- ----------------------------
-- Table structure for `challenge_apply`
-- ----------------------------
DROP TABLE IF EXISTS `challenge_apply`;
CREATE TABLE `challenge_apply` (
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
-- Records of challenge_apply
-- ----------------------------

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
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
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for `court`
-- ----------------------------
DROP TABLE IF EXISTS `court`;
CREATE TABLE `court` (
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
  `tel` varchar(16) DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of court
-- ----------------------------
INSERT INTO `court` VALUES ('1', 's', '2', '2', '2014-', '2', '2', '0', '2', '2', '2014-07-31 06:08:47', '2014-07-31 06:08:47', null, null);

-- ----------------------------
-- Table structure for `court_apply`
-- ----------------------------
DROP TABLE IF EXISTS `court_apply`;
CREATE TABLE `court_apply` (
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
-- Records of court_apply
-- ----------------------------

-- ----------------------------
-- Table structure for `court_service`
-- ----------------------------
DROP TABLE IF EXISTS `court_service`;
CREATE TABLE `court_service` (
  `id` bigint(20) NOT NULL,
  `court_id` bigint(20) NOT NULL,
  `service_name` varchar(256) DEFAULT NULL,
  `gmt_created` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of court_service
-- ----------------------------

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
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
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
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
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for `session`
-- ----------------------------
DROP TABLE IF EXISTS `session`;
CREATE TABLE `session` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `s_id` varchar(64) NOT NULL,
  `store_data` text,
  `expire_time` datetime NOT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modify` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of session
-- ----------------------------
INSERT INTO `session` VALUES ('1', '13', 'cd69cde9-bdc1-4fe3-8281-e11a6b87c32f', null, '2015-01-22 22:57:26', '2015-01-14 21:13:09', '2015-01-21 22:57:26');

-- ----------------------------
-- Table structure for `team`
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `team_desc` varchar(1024) DEFAULT NULL,
  `team_type` varchar(64) DEFAULT NULL,
  `can_join` int(11) DEFAULT NULL,
  `is_deleted` int(11) DEFAULT '0',
  `gmt_modify` datetime NOT NULL,
  `gmt_create` datetime NOT NULL,
  `area` varchar(256) DEFAULT NULL,
  `home_court` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of team
-- ----------------------------
INSERT INTO `team` VALUES ('1', '13', '测试绯闻 ', '介绍登记费啊发', null, '1', '0', '2015-01-21 09:51:12', '2015-01-21 09:51:12', null, null);

-- ----------------------------
-- Table structure for `team_apply`
-- ----------------------------
DROP TABLE IF EXISTS `team_apply`;
CREATE TABLE `team_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `team_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `is_deleted` int(11) NOT NULL DEFAULT '0',
  `gmt_modify` datetime NOT NULL,
  `gmt_create` datetime NOT NULL,
  `apply_msg` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of team_apply
-- ----------------------------

-- ----------------------------
-- Table structure for `team_member`
-- ----------------------------
DROP TABLE IF EXISTS `team_member`;
CREATE TABLE `team_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `team_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modify` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of team_member
-- ----------------------------

-- ----------------------------
-- Table structure for `team_recruit`
-- ----------------------------
DROP TABLE IF EXISTS `team_recruit`;
CREATE TABLE `team_recruit` (
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
-- Records of team_recruit
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
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
  `expert_location` varchar(32) DEFAULT NULL,
  `height` varchar(32) DEFAULT NULL,
  `weight` varchar(32) DEFAULT NULL,
  `hometown` varchar(32) DEFAULT NULL,
  `expert_footer` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('13', 'zhangsan', '我的', null, '啊短发', 'AEF42A4125884C16D6AB400F7F6954CA', '1', '3', '1', '32', null, null, '2014-08-01 17:34:18', '2014-08-01 17:34:18', '12', '12', '34', '发', '12');
