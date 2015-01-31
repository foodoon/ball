ALTER TABLE `challenge` MODIFY COLUMN `id`  bigint(11) NOT NULL FIRST ;

ALTER TABLE `challenge` MODIFY COLUMN `team_id`  bigint(11) NOT NULL AFTER `id`;

ALTER TABLE `challenge` MODIFY COLUMN `court_id`  bigint(11) NOT NULL AFTER `team_id`;

ALTER TABLE `challenge` MODIFY COLUMN `gmt_modify`  datetime NOT NULL AFTER `status`;

ALTER TABLE `challenge` DROP COLUMN `is_deleted`;

ALTER TABLE `challenge` MODIFY COLUMN `id`  bigint(11) NOT NULL AUTO_INCREMENT FIRST ;

ALTER TABLE `challenge_apply` MODIFY COLUMN `id`  bigint(11) NOT NULL FIRST ;

ALTER TABLE `challenge_apply` MODIFY COLUMN `challenge_id`  bigint(11) NOT NULL AFTER `id`;

ALTER TABLE `challenge_apply` MODIFY COLUMN `team_id`  bigint(11) NOT NULL AFTER `challenge_id`;

ALTER TABLE `challenge_apply` MODIFY COLUMN `gmt_modify`  datetime NOT NULL AFTER `accept`;

ALTER TABLE `challenge_apply` DROP COLUMN `is_deleted`;

ALTER TABLE `challenge_apply` MODIFY COLUMN `id`  bigint(11) NOT NULL AUTO_INCREMENT FIRST ;

ALTER TABLE `comment` MODIFY COLUMN `id`  bigint(11) NOT NULL FIRST ;

ALTER TABLE `comment` MODIFY COLUMN `comment_id`  bigint(11) NOT NULL AFTER `id`;

ALTER TABLE `comment` MODIFY COLUMN `user_id`  bigint(11) NOT NULL AFTER `comment_type`;

ALTER TABLE `comment` MODIFY COLUMN `gmt_modify`  datetime NOT NULL AFTER `msg`;

ALTER TABLE `comment` DROP COLUMN `is_deleted`;

ALTER TABLE `comment` MODIFY COLUMN `id`  bigint(11) NOT NULL AUTO_INCREMENT FIRST ;

ALTER TABLE `court` MODIFY COLUMN `id`  bigint(11) NOT NULL FIRST ;

ALTER TABLE `court` MODIFY COLUMN `user_id`  bigint(11) NOT NULL AFTER `square`;

ALTER TABLE `court` MODIFY COLUMN `status`  int(11) NULL DEFAULT NULL AFTER `user_id`;

ALTER TABLE `court` DROP COLUMN `is_deleted`;

ALTER TABLE `court` MODIFY COLUMN `id`  bigint(11) NOT NULL AUTO_INCREMENT FIRST ;

ALTER TABLE `court_apply` MODIFY COLUMN `id`  bigint(11) NOT NULL FIRST ;

ALTER TABLE `court_apply` MODIFY COLUMN `user_id`  bigint(11) NOT NULL AFTER `id`;

ALTER TABLE `court_apply` MODIFY COLUMN `court_id`  bigint(11) NOT NULL AFTER `user_id`;

ALTER TABLE `court_apply` MODIFY COLUMN `gmt_modify`  datetime NULL DEFAULT NULL AFTER `status`;

ALTER TABLE `court_apply` DROP COLUMN `is_deleted`;

ALTER TABLE `court_apply` MODIFY COLUMN `id`  bigint(11) NOT NULL AUTO_INCREMENT FIRST ;

CREATE TABLE `court_site` (
`id`  bigint(20) NOT NULL DEFAULT 0 ,
`court_id`  bigint(20) NULL DEFAULT NULL ,
`site_name`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`site_type`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`price`  mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`open`  int(11) NULL DEFAULT NULL ,
`open_template`  varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
ROW_FORMAT=Compact
;

ALTER TABLE `goods` MODIFY COLUMN `id`  bigint(11) NOT NULL FIRST ;

ALTER TABLE `goods` MODIFY COLUMN `court_id`  bigint(11) NOT NULL AFTER `price`;

ALTER TABLE `goods` MODIFY COLUMN `gmt_modify`  datetime NOT NULL AFTER `court_id`;

ALTER TABLE `goods` DROP COLUMN `is_deleted`;

ALTER TABLE `goods` MODIFY COLUMN `id`  bigint(11) NOT NULL AUTO_INCREMENT FIRST ;

ALTER TABLE `order` MODIFY COLUMN `id`  bigint(11) NOT NULL FIRST ;

ALTER TABLE `order` MODIFY COLUMN `goods_id`  bigint(11) NOT NULL AFTER `id`;

ALTER TABLE `order` MODIFY COLUMN `buyer_id`  bigint(11) NOT NULL AFTER `goods_id`;

ALTER TABLE `order` MODIFY COLUMN `seller_id`  bigint(11) NOT NULL AFTER `buyer_id`;

ALTER TABLE `order` MODIFY COLUMN `gmt_modify`  datetime NOT NULL AFTER `status`;

ALTER TABLE `order` DROP COLUMN `is_deleted`;

ALTER TABLE `order` MODIFY COLUMN `id`  bigint(11) NOT NULL AUTO_INCREMENT FIRST ;

ALTER TABLE `session` MODIFY COLUMN `id`  bigint(11) NOT NULL FIRST ;

ALTER TABLE `session` MODIFY COLUMN `user_id`  bigint(11) NOT NULL AFTER `id`;

ALTER TABLE `session` MODIFY COLUMN `id`  bigint(11) NOT NULL AUTO_INCREMENT FIRST ;

ALTER TABLE `team` MODIFY COLUMN `id`  bigint(11) NOT NULL FIRST ;

ALTER TABLE `team` MODIFY COLUMN `user_id`  bigint(11) NOT NULL AFTER `id`;

ALTER TABLE `team` MODIFY COLUMN `gmt_modify`  datetime NOT NULL AFTER `can_join`;

ALTER TABLE `team` DROP COLUMN `is_deleted`;

ALTER TABLE `team` MODIFY COLUMN `id`  bigint(11) NOT NULL AUTO_INCREMENT FIRST ;

ALTER TABLE `team_apply` MODIFY COLUMN `id`  bigint(11) NOT NULL FIRST ;

ALTER TABLE `team_apply` MODIFY COLUMN `team_id`  bigint(11) NOT NULL AFTER `id`;

ALTER TABLE `team_apply` MODIFY COLUMN `user_id`  bigint(11) NOT NULL AFTER `team_id`;

ALTER TABLE `team_apply` MODIFY COLUMN `gmt_modify`  datetime NOT NULL AFTER `status`;

ALTER TABLE `team_apply` DROP COLUMN `is_deleted`;

ALTER TABLE `team_apply` MODIFY COLUMN `id`  bigint(11) NOT NULL AUTO_INCREMENT FIRST ;

ALTER TABLE `team_member` MODIFY COLUMN `id`  bigint(11) NOT NULL FIRST ;

ALTER TABLE `team_member` MODIFY COLUMN `team_id`  bigint(11) NOT NULL AFTER `id`;

ALTER TABLE `team_member` MODIFY COLUMN `user_id`  bigint(11) NOT NULL AFTER `team_id`;

ALTER TABLE `team_member` ADD COLUMN `creator`  int(11) NULL DEFAULT 0 AFTER `gmt_modify`;

ALTER TABLE `team_member` MODIFY COLUMN `id`  bigint(11) NOT NULL AUTO_INCREMENT FIRST ;

ALTER TABLE `team_recruit` MODIFY COLUMN `id`  bigint(11) NOT NULL FIRST ;

ALTER TABLE `team_recruit` MODIFY COLUMN `user_id`  bigint(11) NOT NULL AFTER `id`;

ALTER TABLE `team_recruit` MODIFY COLUMN `team_id`  bigint(11) NOT NULL AFTER `user_id`;

ALTER TABLE `team_recruit` MODIFY COLUMN `gmt_modify`  datetime NOT NULL AFTER `recruit_desc`;

ALTER TABLE `team_recruit` DROP COLUMN `is_deleted`;

ALTER TABLE `team_recruit` MODIFY COLUMN `id`  bigint(11) NOT NULL AUTO_INCREMENT FIRST ;

ALTER TABLE `user` MODIFY COLUMN `id`  bigint(11) NOT NULL FIRST ;

ALTER TABLE `user` MODIFY COLUMN `id`  bigint(11) NOT NULL AUTO_INCREMENT FIRST ;

