/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : grademanage

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 06/11/2019 19:30:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for filees
-- ----------------------------
DROP TABLE IF EXISTS `filees`;
CREATE TABLE `filees`  (
  `file_Path` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_Names` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of filees
-- ----------------------------
INSERT INTO `filees` VALUES ('G:\\', '实验4  对象与类.doc');
INSERT INTO `filees` VALUES ('G:\\', '1.txt');

SET FOREIGN_KEY_CHECKS = 1;
