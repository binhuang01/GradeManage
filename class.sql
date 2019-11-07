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

 Date: 06/11/2019 19:30:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `Class_id` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Class_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Director` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Monitor` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Depar_id` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('0101', '计算机98高职', '0102', '980101006', '01');
INSERT INTO `class` VALUES ('0102', '计算机99高职', '0104', '990102001', '01');
INSERT INTO `class` VALUES ('0103', '计算机00高职', '0107', NULL, '01');
INSERT INTO `class` VALUES ('0104', '现管98', '0103', '980104009', '01');
INSERT INTO `class` VALUES ('0105', '现管99', '0106', '990105014', '01');
INSERT INTO `class` VALUES ('0106', '计算机99脱产', '0102', NULL, '01');
INSERT INTO `class` VALUES ('0107', '计算机00脱产', '0103', NULL, '01');
INSERT INTO `class` VALUES ('0201', '中文秘书98', '0202', '980201002', '02');
INSERT INTO `class` VALUES ('0202', '中文秘书99', '0204', NULL, '02');
INSERT INTO `class` VALUES ('0203', '广告设计98', '0206', '980203004', '02');
INSERT INTO `class` VALUES ('0204', '广告设计99', '0205', NULL, '02');
INSERT INTO `class` VALUES ('0501', '经济系会计学98', '0502', '980501005', '05');
INSERT INTO `class` VALUES ('0502', '经济系会计学99', '0503', '990502001', '05');
INSERT INTO `class` VALUES ('0503', '经济系会计学00', '0506', '000503008', '05');
INSERT INTO `class` VALUES ('0504', '经济系国际金融99', '0502', NULL, '05');
INSERT INTO `class` VALUES ('0505', '经济系国际金融00', '0503', NULL, '05');
INSERT INTO `class` VALUES ('0506', '经济系国际金融01', '0503', NULL, '05');

SET FOREIGN_KEY_CHECKS = 1;
