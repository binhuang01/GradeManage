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

 Date: 06/11/2019 19:31:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for deparment
-- ----------------------------
DROP TABLE IF EXISTS `deparment`;
CREATE TABLE `deparment`  (
  `Depar_id` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Depar_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Phone` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `teac_num` int(11) NULL DEFAULT NULL,
  `teac_num1` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of deparment
-- ----------------------------
INSERT INTO `deparment` VALUES ('01', '计算机系', '85211234', 9, 9);
INSERT INTO `deparment` VALUES ('02', '中文系', '85213456', 6, 6);
INSERT INTO `deparment` VALUES ('03', '外语系', '85215678', 4, 4);
INSERT INTO `deparment` VALUES ('04', '物理系', '85216789', 0, 0);
INSERT INTO `deparment` VALUES ('05', '经济系', '85211139', 7, 7);
INSERT INTO `deparment` VALUES ('06', '化学系', '85215432', 0, 0);
INSERT INTO `deparment` VALUES ('07', '生物系', NULL, 0, 0);
INSERT INTO `deparment` VALUES ('08', '地理系', NULL, 0, 0);
INSERT INTO `deparment` VALUES ('09', '体育系', NULL, 0, 0);
INSERT INTO `deparment` VALUES ('10', '美术系', '85211011', 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
