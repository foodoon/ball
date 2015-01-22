ALTER TABLE `team` ADD COLUMN `jersey_color`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `home_court`;
ALTER TABLE `user` MODIFY COLUMN `img`  varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `status`;
ALTER TABLE `user` ADD COLUMN `sign`  varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `expert_footer`;
ALTER TABLE `user` ADD COLUMN `sex`  varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `sign`;