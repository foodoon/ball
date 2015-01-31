/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : ball

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2015-01-31 19:46:11
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `challenge`
-- ----------------------------
DROP TABLE IF EXISTS `challenge`;
CREATE TABLE `challenge` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `request_team_id` bigint(11) NOT NULL,
  `apply_team_id` bigint(20) DEFAULT NULL,
  `court_apply_id` bigint(11) NOT NULL,
  `challenge_desc` varchar(1024) DEFAULT NULL,
  `challenge_result` varchar(255) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `goal_count` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `gmt_modify` datetime NOT NULL,
  `gmt_create` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of challenge
-- ----------------------------
INSERT INTO `challenge` VALUES ('6', '2', '3', '6', '一起踢球吧', null, '2015-02-01 08:00:00', '2015-02-01 10:00:00', null, '1', '2015-01-31 17:50:52', '2015-01-31 17:50:52');

-- ----------------------------
-- Table structure for `challenge_apply`
-- ----------------------------
DROP TABLE IF EXISTS `challenge_apply`;
CREATE TABLE `challenge_apply` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `challenge_id` bigint(11) NOT NULL,
  `team_id` bigint(11) NOT NULL,
  `goal_count` int(11) DEFAULT NULL,
  `challenge_result` varchar(16) DEFAULT NULL,
  `accept` int(11) NOT NULL DEFAULT '0',
  `gmt_modify` datetime NOT NULL,
  `gmt_create` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of challenge_apply
-- ----------------------------
INSERT INTO `challenge_apply` VALUES ('6', '6', '3', null, null, '1', '2015-01-31 19:12:38', '2015-01-31 19:12:38');

-- ----------------------------
-- Table structure for `challenge_apply_member`
-- ----------------------------
DROP TABLE IF EXISTS `challenge_apply_member`;
CREATE TABLE `challenge_apply_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `challenge_apply_id` bigint(20) NOT NULL,
  `challenge_id` bigint(20) NOT NULL,
  `team_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `gmt_create` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of challenge_apply_member
-- ----------------------------
INSERT INTO `challenge_apply_member` VALUES ('4', '6', '6', '3', '19', '2015-01-31 19:12:39');
INSERT INTO `challenge_apply_member` VALUES ('5', '6', '6', '3', '18', '2015-01-31 19:12:39');
INSERT INTO `challenge_apply_member` VALUES ('6', '6', '6', '3', '20', '2015-01-31 19:12:39');

-- ----------------------------
-- Table structure for `challenge_member`
-- ----------------------------
DROP TABLE IF EXISTS `challenge_member`;
CREATE TABLE `challenge_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `challenge_id` bigint(20) NOT NULL,
  `team_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `gmt_create` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of challenge_member
-- ----------------------------
INSERT INTO `challenge_member` VALUES ('1', '1', '2', '15', '2015-01-31 16:51:38');
INSERT INTO `challenge_member` VALUES ('13', '6', '2', '15', '2015-01-31 17:50:52');
INSERT INTO `challenge_member` VALUES ('14', '6', '2', '16', '2015-01-31 17:50:52');
INSERT INTO `challenge_member` VALUES ('15', '6', '2', '17', '2015-01-31 17:50:52');

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `comment_id` bigint(11) NOT NULL,
  `comment_type` varchar(16) NOT NULL,
  `user_id` bigint(11) NOT NULL,
  `msg` varchar(1024) NOT NULL,
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
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NOT NULL,
  `name` varchar(256) NOT NULL,
  `address` varchar(1024) DEFAULT NULL,
  `contact_name` varchar(64) DEFAULT NULL,
  `tel` varchar(16) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `court_desc` varchar(1024) DEFAULT NULL,
  `gmt_modify` datetime NOT NULL,
  `gmt_create` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of court
-- ----------------------------
INSERT INTO `court` VALUES ('1', '2', 's', '2', '张三', '17098158159', '2', '场地描述', '2014-07-31 06:08:47', '2014-07-31 06:08:47');

-- ----------------------------
-- Table structure for `court_apply`
-- ----------------------------
DROP TABLE IF EXISTS `court_apply`;
CREATE TABLE `court_apply` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NOT NULL,
  `court_id` bigint(11) NOT NULL,
  `court_site_id` bigint(20) DEFAULT NULL,
  `booking_time` varchar(256) NOT NULL,
  `booking_date` date NOT NULL,
  `status` int(11) DEFAULT '0',
  `gmt_modify` datetime DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of court_apply
-- ----------------------------
INSERT INTO `court_apply` VALUES ('6', '15', '1', '1', '08:00:00-10:00:00', '2015-02-01', '1', '2015-01-31 17:50:52', '2015-01-31 17:50:52');

-- ----------------------------
-- Table structure for `court_service`
-- ----------------------------
DROP TABLE IF EXISTS `court_service`;
CREATE TABLE `court_service` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `court_id` bigint(20) NOT NULL,
  `service_name` varchar(256) DEFAULT NULL,
  `gmt_created` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of court_service
-- ----------------------------

-- ----------------------------
-- Table structure for `court_site`
-- ----------------------------
DROP TABLE IF EXISTS `court_site`;
CREATE TABLE `court_site` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `court_id` bigint(20) DEFAULT NULL,
  `site_name` varchar(256) DEFAULT NULL,
  `site_type` varchar(256) DEFAULT NULL,
  `price` varchar(64) DEFAULT NULL,
  `open` int(11) DEFAULT NULL,
  `open_template` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of court_site
-- ----------------------------
INSERT INTO `court_site` VALUES ('1', '1', '1号场地', '5人制', null, '1', '[{\"timeSection\":[{\"price\":2500,\"status\":0,\"timeInfo\":\"08:00:00-10:00:00\"},{\"price\":2500,\"status\":0,\"timeInfo\":\"10:00:00-12:00:00\"},{\"price\":2500,\"status\":0,\"timeInfo\":\"13:00:00-15:00:00\"},{\"price\":2500,\"status\":0,\"timeInfo\":\"15:00:00-17:00:00\"}],\"week\":0},{\"timeSection\":[{\"price\":2500,\"status\":0,\"timeInfo\":\"08:00:00-10:00:00\"},{\"price\":2500,\"status\":0,\"timeInfo\":\"10:00:00-12:00:00\"},{\"price\":2500,\"status\":0,\"timeInfo\":\"13:00:00-15:00:00\"},{\"price\":2500,\"status\":0,\"timeInfo\":\"15:00:00-17:00:00\"}],\"week\":1},{\"timeSection\":[{\"price\":2500,\"status\":0,\"timeInfo\":\"08:00:00-10:00:00\"},{\"price\":2500,\"status\":0,\"timeInfo\":\"10:00:00-12:00:00\"},{\"price\":2500,\"status\":0,\"timeInfo\":\"13:00:00-15:00:00\"},{\"price\":2500,\"status\":0,\"timeInfo\":\"15:00:00-17:00:00\"}],\"week\":2},{\"timeSection\":[{\"price\":2500,\"status\":0,\"timeInfo\":\"08:00:00-10:00:00\"},{\"price\":2500,\"status\":0,\"timeInfo\":\"10:00:00-12:00:00\"},{\"price\":2500,\"status\":0,\"timeInfo\":\"13:00:00-15:00:00\"},{\"price\":2500,\"status\":0,\"timeInfo\":\"15:00:00-17:00:00\"}],\"week\":3},{\"timeSection\":[{\"price\":2500,\"status\":0,\"timeInfo\":\"08:00:00-10:00:00\"},{\"price\":2500,\"status\":0,\"timeInfo\":\"10:00:00-12:00:00\"},{\"price\":2500,\"status\":0,\"timeInfo\":\"13:00:00-15:00:00\"},{\"price\":2500,\"status\":0,\"timeInfo\":\"15:00:00-17:00:00\"}],\"week\":4},{\"timeSection\":[{\"price\":2500,\"status\":0,\"timeInfo\":\"08:00:00-10:00:00\"},{\"price\":2500,\"status\":0,\"timeInfo\":\"10:00:00-12:00:00\"},{\"price\":2500,\"status\":0,\"timeInfo\":\"13:00:00-15:00:00\"},{\"price\":2500,\"status\":0,\"timeInfo\":\"15:00:00-17:00:00\"}],\"week\":5},{\"timeSection\":[{\"price\":2500,\"status\":0,\"timeInfo\":\"08:00:00-10:00:00\"},{\"price\":2500,\"status\":0,\"timeInfo\":\"10:00:00-12:00:00\"},{\"price\":2500,\"status\":0,\"timeInfo\":\"13:00:00-15:00:00\"},{\"price\":2500,\"status\":0,\"timeInfo\":\"15:00:00-17:00:00\"}],\"week\":6}]');

-- ----------------------------
-- Table structure for `court_site_section`
-- ----------------------------
DROP TABLE IF EXISTS `court_site_section`;
CREATE TABLE `court_site_section` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `court_site_id` bigint(20) NOT NULL,
  `date_info` date DEFAULT NULL,
  `time_info` varchar(128) DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modify` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of court_site_section
-- ----------------------------

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(64) NOT NULL,
  `goods_desc` varchar(1024) DEFAULT NULL,
  `price` bigint(20) NOT NULL DEFAULT '0',
  `court_id` bigint(11) NOT NULL,
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
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `goods_id` bigint(11) NOT NULL,
  `buyer_id` bigint(11) NOT NULL,
  `seller_id` bigint(11) NOT NULL,
  `delivery_time` datetime DEFAULT NULL,
  `leave_msg` varchar(1024) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
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
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NOT NULL,
  `s_id` varchar(64) NOT NULL,
  `store_data` text,
  `expire_time` datetime NOT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modify` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of session
-- ----------------------------
INSERT INTO `session` VALUES ('1', '13', '3ff7282e-35b5-4dce-9769-98d2c047ac00', null, '2015-02-01 19:41:45', '2015-01-14 21:13:09', '2015-01-31 19:41:45');
INSERT INTO `session` VALUES ('2', '14', 'e64fd81e-41c1-4f9a-bd8d-21b56f04a28b', null, '2015-02-01 16:19:35', '2015-01-31 16:19:35', '2015-01-31 16:19:35');
INSERT INTO `session` VALUES ('3', '15', '732c77c7-b281-4708-bbf1-91b282d1559a', null, '2015-02-01 19:21:13', '2015-01-31 16:22:03', '2015-01-31 19:21:13');
INSERT INTO `session` VALUES ('4', '16', 'f84a2b2d-d077-4dde-b012-f2a1c5023d57', null, '2015-02-01 16:36:21', '2015-01-31 16:35:52', '2015-01-31 16:36:21');
INSERT INTO `session` VALUES ('5', '17', '4fadeb64-6528-46a3-b889-bde2daa64c39', null, '2015-02-01 16:37:23', '2015-01-31 16:37:23', '2015-01-31 16:37:23');
INSERT INTO `session` VALUES ('6', '18', '80228a7d-d079-4d60-b6f5-3082a7010ea8', null, '2015-02-01 19:20:52', '2015-01-31 18:17:14', '2015-01-31 19:20:52');
INSERT INTO `session` VALUES ('7', '19', '77713ee6-fdfd-4cee-a79c-afb35fdb8efa', null, '2015-02-01 18:21:47', '2015-01-31 18:18:06', '2015-01-31 18:21:47');
INSERT INTO `session` VALUES ('8', '20', 'bcd840de-687c-4691-be1e-6c9b390cfea2', null, '2015-02-01 18:23:25', '2015-01-31 18:18:12', '2015-01-31 18:23:25');

-- ----------------------------
-- Table structure for `team`
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `team_desc` varchar(1024) DEFAULT NULL,
  `team_type` varchar(64) DEFAULT NULL,
  `can_join` int(11) DEFAULT NULL,
  `gmt_modify` datetime NOT NULL,
  `gmt_create` datetime NOT NULL,
  `area` varchar(256) DEFAULT NULL,
  `home_court` varchar(256) DEFAULT NULL,
  `jersey_color` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of team
-- ----------------------------
INSERT INTO `team` VALUES ('1', '13', '测试绯闻 ', '介绍登记费啊发', null, '1', '2015-01-21 09:51:12', '2015-01-21 09:51:12', null, null, null);
INSERT INTO `team` VALUES ('2', '15', 'wang的球队', '热血每一天', '9', '1', '2015-01-31 16:22:04', '2015-01-31 16:22:04', '长沙', '长沙', '白色');
INSERT INTO `team` VALUES ('3', '18', 'zhang的球队', '踢吧', '7', '1', '2015-01-31 18:17:16', '2015-01-31 18:17:16', '芙蓉区', '芙蓉区', '黑色');

-- ----------------------------
-- Table structure for `team_apply`
-- ----------------------------
DROP TABLE IF EXISTS `team_apply`;
CREATE TABLE `team_apply` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `team_id` bigint(11) NOT NULL,
  `user_id` bigint(11) NOT NULL,
  `status` int(11) NOT NULL,
  `gmt_modify` datetime NOT NULL,
  `gmt_create` datetime NOT NULL,
  `apply_msg` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of team_apply
-- ----------------------------
INSERT INTO `team_apply` VALUES ('1', '1', '14', '1', '2015-01-31 16:19:36', '2015-01-31 16:19:36', null);
INSERT INTO `team_apply` VALUES ('2', '2', '16', '1', '2015-01-31 16:35:53', '2015-01-31 16:35:53', null);
INSERT INTO `team_apply` VALUES ('3', '2', '17', '1', '2015-01-31 16:37:24', '2015-01-31 16:37:24', null);
INSERT INTO `team_apply` VALUES ('4', '3', '19', '1', '2015-01-31 18:18:06', '2015-01-31 18:18:06', null);
INSERT INTO `team_apply` VALUES ('5', '3', '20', '1', '2015-01-31 18:18:13', '2015-01-31 18:18:13', null);

-- ----------------------------
-- Table structure for `team_member`
-- ----------------------------
DROP TABLE IF EXISTS `team_member`;
CREATE TABLE `team_member` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `team_id` bigint(11) NOT NULL,
  `user_id` bigint(11) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modify` datetime NOT NULL,
  `creator` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of team_member
-- ----------------------------
INSERT INTO `team_member` VALUES ('1', '1', '14', '2015-01-31 16:20:27', '2015-01-31 16:20:27', null);
INSERT INTO `team_member` VALUES ('2', '2', '15', '2015-01-31 16:22:04', '2015-01-31 16:22:04', '1');
INSERT INTO `team_member` VALUES ('3', '2', '16', '2015-01-31 16:36:30', '2015-01-31 16:36:30', '0');
INSERT INTO `team_member` VALUES ('4', '2', '17', '2015-01-31 16:37:48', '2015-01-31 16:37:48', '0');
INSERT INTO `team_member` VALUES ('5', '3', '18', '2015-01-31 18:17:16', '2015-01-31 18:17:16', '1');
INSERT INTO `team_member` VALUES ('6', '3', '19', '2015-01-31 18:18:41', '2015-01-31 18:18:41', '0');
INSERT INTO `team_member` VALUES ('7', '3', '20', '2015-01-31 18:18:53', '2015-01-31 18:18:53', '0');

-- ----------------------------
-- Table structure for `team_recruit`
-- ----------------------------
DROP TABLE IF EXISTS `team_recruit`;
CREATE TABLE `team_recruit` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NOT NULL,
  `team_id` bigint(11) NOT NULL,
  `recruit_desc` varchar(1024) DEFAULT NULL,
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
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
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
  `img` varchar(512) DEFAULT NULL,
  `gmt_modify` datetime NOT NULL,
  `gmt_create` datetime NOT NULL,
  `expert_location` varchar(32) DEFAULT NULL,
  `height` varchar(32) DEFAULT NULL,
  `weight` varchar(32) DEFAULT NULL,
  `hometown` varchar(32) DEFAULT NULL,
  `expert_footer` varchar(16) DEFAULT NULL,
  `sign` varchar(512) DEFAULT NULL,
  `sex` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('15', 'test', 'test', null, '', '7F15BE00BA1A7468B5A58EC0A8B3F69E', 'b', '', '', '', null, null, '2015-01-24 15:29:16', '2015-01-24 15:29:16', '', '', '', '', '', null, null);
INSERT INTO `user` VALUES ('16', 'test1', null, null, null, '3BCD3A88754C4DA3353196974BFBA03F', null, null, null, null, null, null, '2015-01-24 17:11:08', '2015-01-24 17:11:08', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('17', 'zhangsan', '刘德华', null, '长沙市区', 'AEF42A4125884C16D6AB400F7F6954CA', '', '', '', '', null, '20150124/508cd445-9fc9-49ba-a842-f13b0b7ab71a_180x180.png?mod=1422099192000', '2015-01-24 19:33:12', '2015-01-24 19:26:58', '', '', '', '', '', null, null);
INSERT INTO `user` VALUES ('18', 'abcd', null, null, null, 'AEF42A4125884C16D6AB400F7F6954CA', null, null, null, null, null, null, '2015-01-24 22:59:26', '2015-01-24 22:59:26', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('19', 'lifei', null, null, null, 'A040946D9877ED69A3AC65BE49175B05', null, null, null, null, null, null, '2015-01-26 19:59:37', '2015-01-26 19:59:37', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('20', 'zhang2', null, null, null, 'AEF42A4125884C16D6AB400F7F6954CA', null, null, null, null, null, null, '2015-01-29 09:55:40', '2015-01-29 09:55:40', null, null, null, null, null, null, null);
