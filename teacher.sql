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

 Date: 06/11/2019 19:30:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `TeacId` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TeacName` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TeacSex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Birthday` datetime(0) NULL DEFAULT NULL,
  `TechPost` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Depar_id` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`TeacId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('0102', '聂益桂', '女', '1962-04-11 00:00:00', '教授', '01', '0102', '111111');
INSERT INTO `teacher` VALUES ('0103', '练菲彰', '男', '1975-09-04 00:00:00', '教授', '01', '0103', '111111');
INSERT INTO `teacher` VALUES ('0104', '钟开滨', '男', '1977-04-24 00:00:00', '讲师', '01', '0104', '111111');
INSERT INTO `teacher` VALUES ('0105', '汤俊逸', '女', '1973-09-24 00:00:00', '讲师', '01', '0105', '111111');
INSERT INTO `teacher` VALUES ('0106', '沈军靖', '男', '1976-09-22 00:00:00', '助理教师', '01', '0106', '111111');
INSERT INTO `teacher` VALUES ('0107', '邝越影', '男', '1977-06-02 00:00:00', '教授', '01', '0107', '111111');
INSERT INTO `teacher` VALUES ('0108', '艾彤荣', '男', '1971-04-02 00:00:00', '讲师', '01', '0108', '111111');
INSERT INTO `teacher` VALUES ('0109', '蚁贺薇', '男', '1964-07-24 00:00:00', '教授', '01', '0109', '111111');
INSERT INTO `teacher` VALUES ('0202', '刘伙鹰', '女', '1966-04-21 00:00:00', '副教授', '02', '0202', '111111');
INSERT INTO `teacher` VALUES ('0203', '万马金', '男', '1962-10-01 00:00:00', '教授', '02', '0203', '111111');
INSERT INTO `teacher` VALUES ('0204', '艾彤荣', '男', '1963-06-08 00:00:00', '教授', '02', '0204', '111111');
INSERT INTO `teacher` VALUES ('0205', '马祥艳', '男', '1976-11-21 00:00:00', '副教授', '02', '0205', '111111');
INSERT INTO `teacher` VALUES ('0206', '祁铠沁', '男', '1976-11-15 00:00:00', '教授', '02', '0206', '111111');
INSERT INTO `teacher` VALUES ('0207', '池显敏', '女', '1977-10-06 00:00:00', '助理教师', '02', '0207', '111111');
INSERT INTO `teacher` VALUES ('0301', '阳辉辉', '男', '1967-07-03 00:00:00', '副教授', '03', '0301', '111111');
INSERT INTO `teacher` VALUES ('0302', '黄中中', '女', '1964-07-06 00:00:00', '副教授', '03', '0302', '111111');
INSERT INTO `teacher` VALUES ('0303', '施怀舟', '女', '1970-08-06 00:00:00', '教授', '03', '0303', '111111');
INSERT INTO `teacher` VALUES ('0304', '苏洪澜', '男', '1962-10-16 00:00:00', '助理教师', '03', '0304', '111111');
INSERT INTO `teacher` VALUES ('0501', '祁宪戎', '女', '1968-06-21 00:00:00', '教授', '05', '0501', '111111');
INSERT INTO `teacher` VALUES ('0502', '邓焯翔', '女', '1971-03-02 00:00:00', '助理教师', '05', '0502', '111111');
INSERT INTO `teacher` VALUES ('0503', '万加尔', '女', '1966-03-27 00:00:00', '副教授', '05', '0503', '111111');
INSERT INTO `teacher` VALUES ('0504', '左骏俊', '女', '1961-05-18 00:00:00', '讲师', '05', '0504', '111111');
INSERT INTO `teacher` VALUES ('0505', '蚁钢驹', '男', '1960-10-09 00:00:00', '讲师', '05', '0505', '111111');
INSERT INTO `teacher` VALUES ('0506', '金贵成', '女', '1975-06-10 00:00:00', '教授', '05', '0506', '111111');
INSERT INTO `teacher` VALUES ('0507', '秦季基', '男', '1975-11-19 00:00:00', '讲师', '05', '0507', '111111');
INSERT INTO `teacher` VALUES ('0508', 'aa', '男', NULL, NULL, '01', '0508', '111111');

SET FOREIGN_KEY_CHECKS = 1;
