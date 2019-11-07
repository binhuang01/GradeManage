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

 Date: 06/11/2019 19:29:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `Course_id` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Course_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Course_hour` smallint(6) NULL DEFAULT NULL,
  `Introduce` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('0101', '数据库', 85, '介绍关系数据库基础，以MICROSOFT SQL SERVER为例介绍数据库的知识。');
INSERT INTO `course` VALUES ('0102', '网络开发技术', 60, '网络编程，使用ASP开发网络应用程序。');
INSERT INTO `course` VALUES ('0103', 'C语言', 100, NULL);
INSERT INTO `course` VALUES ('0104', '计算机应用基础', 60, NULL);
INSERT INTO `course` VALUES ('0105', '数据结构', 80, NULL);
INSERT INTO `course` VALUES ('0106', '计算机组成原理', 80, NULL);
INSERT INTO `course` VALUES ('0107', '计算机网络', 80, NULL);
INSERT INTO `course` VALUES ('0108', '软件工程', 100, NULL);
INSERT INTO `course` VALUES ('0109', '面向对象程序设计', 60, NULL);
INSERT INTO `course` VALUES ('0110', '汇编语言', 80, NULL);
INSERT INTO `course` VALUES ('0111', '操作系统', 60, NULL);
INSERT INTO `course` VALUES ('0112', '数字逻辑', 90, NULL);
INSERT INTO `course` VALUES ('0118', '网络安全与防火墙', 40, '介绍网络安全与主要的防火墙软件');
INSERT INTO `course` VALUES ('0201', '公共关系学', 80, NULL);
INSERT INTO `course` VALUES ('0202', '古代汉语', 60, NULL);
INSERT INTO `course` VALUES ('0203', '广告学', 30, NULL);
INSERT INTO `course` VALUES ('0204', '基础写作', 80, NULL);
INSERT INTO `course` VALUES ('0205', '现代汉语', 60, NULL);
INSERT INTO `course` VALUES ('0206', '档案学', 40, NULL);
INSERT INTO `course` VALUES ('0207', '电脑设计', 100, NULL);
INSERT INTO `course` VALUES ('0208', '广告策划', 30, NULL);
INSERT INTO `course` VALUES ('0209', '平面设计', 80, NULL);
INSERT INTO `course` VALUES ('0210', '美术史', 60, NULL);
INSERT INTO `course` VALUES ('0211', '素描', 80, NULL);
INSERT INTO `course` VALUES ('0501', '工业会计', 60, NULL);
INSERT INTO `course` VALUES ('0502', '成本会计', 60, NULL);
INSERT INTO `course` VALUES ('0503', '市场营销', 40, NULL);
INSERT INTO `course` VALUES ('0504', '财政与金融', 60, NULL);
INSERT INTO `course` VALUES ('0505', '会计核算', 30, NULL);
INSERT INTO `course` VALUES ('0506', '经济法概论', 60, NULL);
INSERT INTO `course` VALUES ('0507', '会计学基础', 80, NULL);
INSERT INTO `course` VALUES ('0508', '政治经济学', 60, NULL);
INSERT INTO `course` VALUES ('0509', '统计学基础', 60, NULL);
INSERT INTO `course` VALUES ('0510', '商业银行业务', 80, NULL);
INSERT INTO `course` VALUES ('0511', '银行信贷', 60, NULL);
INSERT INTO `course` VALUES ('0512', '货币银行学', 60, NULL);
INSERT INTO `course` VALUES ('0513', '投资项目评估', 50, NULL);

SET FOREIGN_KEY_CHECKS = 1;
