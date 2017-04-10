/*
Navicat MySQL Data Transfer

Source Server         : LDB
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : ssm_primary

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2017-03-13 13:56:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(36) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` char(255) DEFAULT NULL COMMENT '用户性别（1：男；0：女）',
  `age` tinyint(2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_time` datetime DEFAULT NULL,
  `is_delete` varchar(32) DEFAULT '' COMMENT '删除时间',
  `temp1` varchar(255) DEFAULT NULL,
  `temp2` varchar(255) DEFAULT NULL,
  `temp3` varchar(255) DEFAULT NULL,
  `intro` varchar(2048) DEFAULT NULL COMMENT '简介',
  `intro1` varchar(2048) DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户来源表，记录openid，设备号等';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('bde561a5-90e6-463b-bcf5-68b2b699a03b', 'admin', '-1', null, '2016-07-20 16:51:32', '2016-08-01 13:23:10', null, '1', '123456', null, null, null, null);
INSERT INTO `user` VALUES ('dddddddadd', null, null, null, null, null, null, '0', null, null, null, null, null);
INSERT INTO `user` VALUES ('dddddddddadd', null, null, null, null, null, null, 'x', null, null, null, null, null);
INSERT INTO `user` VALUES ('dddddddddddddaddeeeee', null, null, null, null, null, null, '4', null, null, null, null, null);
INSERT INTO `user` VALUES ('fdafda', null, null, null, null, null, null, 'a', null, null, null, null, null);
