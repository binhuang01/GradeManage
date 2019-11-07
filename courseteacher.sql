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

 Date: 06/11/2019 19:31:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for courseteacher
-- ----------------------------
DROP TABLE IF EXISTS `courseteacher`;
CREATE TABLE `courseteacher`  (
  `Course_id` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Class_id` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Teac_id` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of courseteacher
-- ----------------------------
INSERT INTO `courseteacher` VALUES ('0101', '0101', '0103');
INSERT INTO `courseteacher` VALUES ('0101', '0102', '0103');
INSERT INTO `courseteacher` VALUES ('0102', '0101', '0104');
INSERT INTO `courseteacher` VALUES ('0102', '0102', '0104');
INSERT INTO `courseteacher` VALUES ('0103', '0101', '0102');
INSERT INTO `courseteacher` VALUES ('0103', '0102', '0109');
INSERT INTO `courseteacher` VALUES ('0103', '0104', '0102');
INSERT INTO `courseteacher` VALUES ('0104', '0101', '0103');
INSERT INTO `courseteacher` VALUES ('0104', '0102', '0108');
INSERT INTO `courseteacher` VALUES ('0105', '0101', '0106');
INSERT INTO `courseteacher` VALUES ('0105', '0104', '0106');
INSERT INTO `courseteacher` VALUES ('0108', '0101', '0108');
INSERT INTO `courseteacher` VALUES ('0108', '0104', '0108');
INSERT INTO `courseteacher` VALUES ('0109', '0104', '0108');
INSERT INTO `courseteacher` VALUES ('0110', '0102', '0203');
INSERT INTO `courseteacher` VALUES ('0111', '0102', '0105');
INSERT INTO `courseteacher` VALUES ('0112', '0104', '0109');
INSERT INTO `courseteacher` VALUES ('0201', '0201', '0206');
INSERT INTO `courseteacher` VALUES ('0201', '0203', '0206');
INSERT INTO `courseteacher` VALUES ('0202', '0201', '0204');
INSERT INTO `courseteacher` VALUES ('0202', '0203', '0204');
INSERT INTO `courseteacher` VALUES ('0203', '0201', '0202');
INSERT INTO `courseteacher` VALUES ('0205', '0203', '0207');
INSERT INTO `courseteacher` VALUES ('0208', '0201', '0205');
INSERT INTO `courseteacher` VALUES ('0210', '0201', '0203');
INSERT INTO `courseteacher` VALUES ('0210', '0203', '0206');
INSERT INTO `courseteacher` VALUES ('0211', '0202', '0202');
INSERT INTO `courseteacher` VALUES ('0501', '0501', '0501');
INSERT INTO `courseteacher` VALUES ('0501', '0502', '0506');
INSERT INTO `courseteacher` VALUES ('0502', '0501', '0504');
INSERT INTO `courseteacher` VALUES ('0502', '0502', '0507');
INSERT INTO `courseteacher` VALUES ('0503', '0501', '0502');
INSERT INTO `courseteacher` VALUES ('0503', '0502', '0504');
INSERT INTO `courseteacher` VALUES ('0506', '0503', '0501');
INSERT INTO `courseteacher` VALUES ('0508', '0503', '0503');
INSERT INTO `courseteacher` VALUES ('0511', '0503', '0502');
INSERT INTO `courseteacher` VALUES ('0512', '0503', '0505');

SET FOREIGN_KEY_CHECKS = 1;
