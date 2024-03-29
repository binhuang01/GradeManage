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

 Date: 06/11/2019 19:30:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for studentgrade
-- ----------------------------
DROP TABLE IF EXISTS `studentgrade`;
CREATE TABLE `studentgrade`  (
  `Stu_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Course_id` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Grade` smallint(6) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of studentgrade
-- ----------------------------
INSERT INTO `studentgrade` VALUES ('000503001', '0508', 98);
INSERT INTO `studentgrade` VALUES ('000503001', '0511', 94);
INSERT INTO `studentgrade` VALUES ('000503001', '0512', 78);
INSERT INTO `studentgrade` VALUES ('000503002', '0506', 81);
INSERT INTO `studentgrade` VALUES ('000503002', '0508', 60);
INSERT INTO `studentgrade` VALUES ('000503002', '0511', 78);
INSERT INTO `studentgrade` VALUES ('000503002', '0512', 90);
INSERT INTO `studentgrade` VALUES ('000503003', '0506', 81);
INSERT INTO `studentgrade` VALUES ('000503003', '0508', 60);
INSERT INTO `studentgrade` VALUES ('000503003', '0512', 67);
INSERT INTO `studentgrade` VALUES ('000503004', '0506', 66);
INSERT INTO `studentgrade` VALUES ('000503004', '0508', 76);
INSERT INTO `studentgrade` VALUES ('000503004', '0511', 97);
INSERT INTO `studentgrade` VALUES ('000503004', '0512', 67);
INSERT INTO `studentgrade` VALUES ('000503005', '0506', 68);
INSERT INTO `studentgrade` VALUES ('000503005', '0508', 86);
INSERT INTO `studentgrade` VALUES ('000503005', '0511', 60);
INSERT INTO `studentgrade` VALUES ('000503005', '0512', 71);
INSERT INTO `studentgrade` VALUES ('000503006', '0506', 87);
INSERT INTO `studentgrade` VALUES ('000503006', '0508', 61);
INSERT INTO `studentgrade` VALUES ('000503006', '0511', 87);
INSERT INTO `studentgrade` VALUES ('000503006', '0512', 77);
INSERT INTO `studentgrade` VALUES ('000503007', '0506', 64);
INSERT INTO `studentgrade` VALUES ('000503007', '0512', 86);
INSERT INTO `studentgrade` VALUES ('000503008', '0506', 86);
INSERT INTO `studentgrade` VALUES ('000503008', '0508', 86);
INSERT INTO `studentgrade` VALUES ('000503008', '0511', 61);
INSERT INTO `studentgrade` VALUES ('000503008', '0512', 99);
INSERT INTO `studentgrade` VALUES ('980101001', '0101', 88);
INSERT INTO `studentgrade` VALUES ('980101001', '0104', 86);
INSERT INTO `studentgrade` VALUES ('980101002', '0101', 72);
INSERT INTO `studentgrade` VALUES ('980101002', '0102', 67);
INSERT INTO `studentgrade` VALUES ('980101002', '0105', 89);
INSERT INTO `studentgrade` VALUES ('980101002', '0108', 78);
INSERT INTO `studentgrade` VALUES ('980101003', '0101', 60);
INSERT INTO `studentgrade` VALUES ('980101003', '0102', 92);
INSERT INTO `studentgrade` VALUES ('980101003', '0104', 60);
INSERT INTO `studentgrade` VALUES ('980101003', '0105', 81);
INSERT INTO `studentgrade` VALUES ('980101003', '0108', 79);
INSERT INTO `studentgrade` VALUES ('980101004', '0101', 87);
INSERT INTO `studentgrade` VALUES ('980101004', '0102', 65);
INSERT INTO `studentgrade` VALUES ('980101004', '0105', 79);
INSERT INTO `studentgrade` VALUES ('980101004', '0108', 60);
INSERT INTO `studentgrade` VALUES ('980101005', '0101', 61);
INSERT INTO `studentgrade` VALUES ('980101005', '0102', 66);
INSERT INTO `studentgrade` VALUES ('980101005', '0104', 80);
INSERT INTO `studentgrade` VALUES ('980101005', '0105', 76);
INSERT INTO `studentgrade` VALUES ('980101005', '0108', 87);
INSERT INTO `studentgrade` VALUES ('980101006', '0101', 73);
INSERT INTO `studentgrade` VALUES ('980101006', '0102', 74);
INSERT INTO `studentgrade` VALUES ('980101006', '0104', 63);
INSERT INTO `studentgrade` VALUES ('980101006', '0105', 66);
INSERT INTO `studentgrade` VALUES ('980101006', '0108', 76);
INSERT INTO `studentgrade` VALUES ('980101007', '0101', 68);
INSERT INTO `studentgrade` VALUES ('980101007', '0102', 99);
INSERT INTO `studentgrade` VALUES ('980101007', '0104', 94);
INSERT INTO `studentgrade` VALUES ('980101007', '0105', 85);
INSERT INTO `studentgrade` VALUES ('980101010', '0101', 75);
INSERT INTO `studentgrade` VALUES ('980101010', '0102', 84);
INSERT INTO `studentgrade` VALUES ('980101010', '0104', 79);
INSERT INTO `studentgrade` VALUES ('980101010', '0105', 65);
INSERT INTO `studentgrade` VALUES ('980101010', '0108', 76);
INSERT INTO `studentgrade` VALUES ('980101011', '0101', 87);
INSERT INTO `studentgrade` VALUES ('980101011', '0102', 63);
INSERT INTO `studentgrade` VALUES ('980101011', '0104', 77);
INSERT INTO `studentgrade` VALUES ('980101011', '0105', 68);
INSERT INTO `studentgrade` VALUES ('980101011', '0108', 88);
INSERT INTO `studentgrade` VALUES ('980101012', '0101', 84);
INSERT INTO `studentgrade` VALUES ('980101012', '0102', 78);
INSERT INTO `studentgrade` VALUES ('980101012', '0104', 72);
INSERT INTO `studentgrade` VALUES ('980101012', '0105', 60);
INSERT INTO `studentgrade` VALUES ('980101012', '0108', 62);
INSERT INTO `studentgrade` VALUES ('980101013', '0101', 74);
INSERT INTO `studentgrade` VALUES ('980101013', '0104', 60);
INSERT INTO `studentgrade` VALUES ('980101013', '0105', 82);
INSERT INTO `studentgrade` VALUES ('980101013', '0108', 60);
INSERT INTO `studentgrade` VALUES ('980101014', '0101', 69);
INSERT INTO `studentgrade` VALUES ('980101014', '0104', 67);
INSERT INTO `studentgrade` VALUES ('980101014', '0105', 78);
INSERT INTO `studentgrade` VALUES ('980101014', '0108', 71);
INSERT INTO `studentgrade` VALUES ('980101015', '0101', 60);
INSERT INTO `studentgrade` VALUES ('980101015', '0102', 61);
INSERT INTO `studentgrade` VALUES ('980101015', '0104', 79);
INSERT INTO `studentgrade` VALUES ('980101016', '0101', 93);
INSERT INTO `studentgrade` VALUES ('980101016', '0102', 60);
INSERT INTO `studentgrade` VALUES ('980101016', '0104', 83);
INSERT INTO `studentgrade` VALUES ('980101016', '0105', 91);
INSERT INTO `studentgrade` VALUES ('980101017', '0101', 91);
INSERT INTO `studentgrade` VALUES ('980101017', '0102', 90);
INSERT INTO `studentgrade` VALUES ('980101017', '0105', 93);
INSERT INTO `studentgrade` VALUES ('980101017', '0108', 60);
INSERT INTO `studentgrade` VALUES ('980101018', '0101', 60);
INSERT INTO `studentgrade` VALUES ('980101018', '0105', 86);
INSERT INTO `studentgrade` VALUES ('980101018', '0108', 85);
INSERT INTO `studentgrade` VALUES ('980104001', '0105', 83);
INSERT INTO `studentgrade` VALUES ('980104001', '0109', 60);
INSERT INTO `studentgrade` VALUES ('980104001', '0112', 88);
INSERT INTO `studentgrade` VALUES ('980104003', '0105', 72);
INSERT INTO `studentgrade` VALUES ('980104003', '0108', 83);
INSERT INTO `studentgrade` VALUES ('980104003', '0109', 84);
INSERT INTO `studentgrade` VALUES ('980104003', '0112', 82);
INSERT INTO `studentgrade` VALUES ('980104004', '0105', 72);
INSERT INTO `studentgrade` VALUES ('980104004', '0109', 67);
INSERT INTO `studentgrade` VALUES ('980104004', '0112', 81);
INSERT INTO `studentgrade` VALUES ('980104005', '0108', 61);
INSERT INTO `studentgrade` VALUES ('980104005', '0112', 76);
INSERT INTO `studentgrade` VALUES ('980104006', '0108', 60);
INSERT INTO `studentgrade` VALUES ('980104006', '0109', 60);
INSERT INTO `studentgrade` VALUES ('980104008', '0105', 70);
INSERT INTO `studentgrade` VALUES ('980104008', '0108', 74);
INSERT INTO `studentgrade` VALUES ('980104008', '0109', 70);
INSERT INTO `studentgrade` VALUES ('980104008', '0112', 72);
INSERT INTO `studentgrade` VALUES ('980104009', '0105', 74);
INSERT INTO `studentgrade` VALUES ('980104009', '0108', 93);
INSERT INTO `studentgrade` VALUES ('980104009', '0109', 75);
INSERT INTO `studentgrade` VALUES ('980104009', '0112', 66);
INSERT INTO `studentgrade` VALUES ('980201001', '0201', 68);
INSERT INTO `studentgrade` VALUES ('980201001', '0202', 66);
INSERT INTO `studentgrade` VALUES ('980201001', '0203', 99);
INSERT INTO `studentgrade` VALUES ('980201001', '0210', 94);
INSERT INTO `studentgrade` VALUES ('980201002', '0201', 69);
INSERT INTO `studentgrade` VALUES ('980201002', '0202', 70);
INSERT INTO `studentgrade` VALUES ('980201002', '0203', 92);
INSERT INTO `studentgrade` VALUES ('980201002', '0208', 65);
INSERT INTO `studentgrade` VALUES ('980201002', '0210', 61);
INSERT INTO `studentgrade` VALUES ('980201004', '0201', 96);
INSERT INTO `studentgrade` VALUES ('980201004', '0202', 85);
INSERT INTO `studentgrade` VALUES ('980201004', '0203', 75);
INSERT INTO `studentgrade` VALUES ('980201004', '0208', 97);
INSERT INTO `studentgrade` VALUES ('980201004', '0210', 60);
INSERT INTO `studentgrade` VALUES ('980201005', '0210', 63);
INSERT INTO `studentgrade` VALUES ('980201006', '0201', 95);
INSERT INTO `studentgrade` VALUES ('980201006', '0202', 74);
INSERT INTO `studentgrade` VALUES ('980201006', '0203', 94);
INSERT INTO `studentgrade` VALUES ('980201006', '0208', 69);
INSERT INTO `studentgrade` VALUES ('980201007', '0201', 96);
INSERT INTO `studentgrade` VALUES ('980201007', '0202', 74);
INSERT INTO `studentgrade` VALUES ('980201007', '0203', 90);
INSERT INTO `studentgrade` VALUES ('980201007', '0208', 82);
INSERT INTO `studentgrade` VALUES ('980201009', '0208', 60);
INSERT INTO `studentgrade` VALUES ('980201009', '0210', 60);
INSERT INTO `studentgrade` VALUES ('980203001', '0201', 61);
INSERT INTO `studentgrade` VALUES ('980203001', '0202', 81);
INSERT INTO `studentgrade` VALUES ('980203001', '0205', 78);
INSERT INTO `studentgrade` VALUES ('980203002', '0201', 94);
INSERT INTO `studentgrade` VALUES ('980203002', '0202', 97);
INSERT INTO `studentgrade` VALUES ('980203002', '0205', 72);
INSERT INTO `studentgrade` VALUES ('980203002', '0210', 60);
INSERT INTO `studentgrade` VALUES ('980203003', '0201', 74);
INSERT INTO `studentgrade` VALUES ('980203003', '0205', 60);
INSERT INTO `studentgrade` VALUES ('980203003', '0210', 61);
INSERT INTO `studentgrade` VALUES ('980203004', '0201', 71);
INSERT INTO `studentgrade` VALUES ('980203004', '0202', 90);
INSERT INTO `studentgrade` VALUES ('980203004', '0205', 89);
INSERT INTO `studentgrade` VALUES ('980203004', '0210', 87);
INSERT INTO `studentgrade` VALUES ('980203005', '0201', 60);
INSERT INTO `studentgrade` VALUES ('980203005', '0202', 73);
INSERT INTO `studentgrade` VALUES ('980203005', '0205', 79);
INSERT INTO `studentgrade` VALUES ('980203005', '0210', 83);
INSERT INTO `studentgrade` VALUES ('980203006', '0201', 89);
INSERT INTO `studentgrade` VALUES ('980203006', '0202', 83);
INSERT INTO `studentgrade` VALUES ('980203006', '0205', 76);
INSERT INTO `studentgrade` VALUES ('980203006', '0210', 71);
INSERT INTO `studentgrade` VALUES ('980203008', '0201', 75);
INSERT INTO `studentgrade` VALUES ('980203008', '0202', 60);
INSERT INTO `studentgrade` VALUES ('980203008', '0205', 77);
INSERT INTO `studentgrade` VALUES ('980203008', '0210', 88);
INSERT INTO `studentgrade` VALUES ('980203009', '0202', 74);
INSERT INTO `studentgrade` VALUES ('980203009', '0205', 85);
INSERT INTO `studentgrade` VALUES ('980203009', '0210', 60);
INSERT INTO `studentgrade` VALUES ('980203010', '0201', 60);
INSERT INTO `studentgrade` VALUES ('980203010', '0202', 83);
INSERT INTO `studentgrade` VALUES ('980203010', '0205', 78);
INSERT INTO `studentgrade` VALUES ('980203010', '0210', 62);
INSERT INTO `studentgrade` VALUES ('980203012', '0201', 73);
INSERT INTO `studentgrade` VALUES ('980203012', '0202', 72);
INSERT INTO `studentgrade` VALUES ('980203012', '0205', 81);
INSERT INTO `studentgrade` VALUES ('980203012', '0210', 97);
INSERT INTO `studentgrade` VALUES ('980203013', '0202', 71);
INSERT INTO `studentgrade` VALUES ('980203013', '0205', 82);
INSERT INTO `studentgrade` VALUES ('980203013', '0210', 93);
INSERT INTO `studentgrade` VALUES ('980203014', '0202', 60);
INSERT INTO `studentgrade` VALUES ('980203014', '0205', 61);
INSERT INTO `studentgrade` VALUES ('980203014', '0210', 91);
INSERT INTO `studentgrade` VALUES ('980203016', '0202', 90);
INSERT INTO `studentgrade` VALUES ('980203016', '0205', 90);
INSERT INTO `studentgrade` VALUES ('980203016', '0210', 76);
INSERT INTO `studentgrade` VALUES ('980203017', '0202', 73);
INSERT INTO `studentgrade` VALUES ('980203017', '0205', 77);
INSERT INTO `studentgrade` VALUES ('980203017', '0210', 73);
INSERT INTO `studentgrade` VALUES ('980203019', '0201', 84);
INSERT INTO `studentgrade` VALUES ('980203019', '0202', 94);
INSERT INTO `studentgrade` VALUES ('980203019', '0205', 78);
INSERT INTO `studentgrade` VALUES ('980203019', '0210', 91);
INSERT INTO `studentgrade` VALUES ('980203020', '0201', 83);
INSERT INTO `studentgrade` VALUES ('980203020', '0202', 96);
INSERT INTO `studentgrade` VALUES ('980203020', '0205', 83);
INSERT INTO `studentgrade` VALUES ('980203020', '0210', 88);
INSERT INTO `studentgrade` VALUES ('980203021', '0201', 60);
INSERT INTO `studentgrade` VALUES ('980203021', '0202', 83);
INSERT INTO `studentgrade` VALUES ('980203021', '0210', 68);
INSERT INTO `studentgrade` VALUES ('980203022', '0201', 63);
INSERT INTO `studentgrade` VALUES ('980203022', '0202', 68);
INSERT INTO `studentgrade` VALUES ('980203022', '0205', 68);
INSERT INTO `studentgrade` VALUES ('980203022', '0210', 92);
INSERT INTO `studentgrade` VALUES ('980203023', '0201', 60);
INSERT INTO `studentgrade` VALUES ('980203023', '0202', 61);
INSERT INTO `studentgrade` VALUES ('980203023', '0205', 75);
INSERT INTO `studentgrade` VALUES ('980203024', '0201', 85);
INSERT INTO `studentgrade` VALUES ('980203024', '0202', 72);
INSERT INTO `studentgrade` VALUES ('980203024', '0205', 92);
INSERT INTO `studentgrade` VALUES ('980203024', '0210', 72);
INSERT INTO `studentgrade` VALUES ('980203025', '0201', 90);
INSERT INTO `studentgrade` VALUES ('980203025', '0202', 86);
INSERT INTO `studentgrade` VALUES ('980203025', '0205', 90);
INSERT INTO `studentgrade` VALUES ('980203025', '0210', 60);
INSERT INTO `studentgrade` VALUES ('980203026', '0201', 89);
INSERT INTO `studentgrade` VALUES ('980203026', '0202', 71);
INSERT INTO `studentgrade` VALUES ('980203026', '0205', 75);
INSERT INTO `studentgrade` VALUES ('980203026', '0210', 82);
INSERT INTO `studentgrade` VALUES ('980203027', '0201', 86);
INSERT INTO `studentgrade` VALUES ('980203027', '0202', 60);
INSERT INTO `studentgrade` VALUES ('980203027', '0205', 95);
INSERT INTO `studentgrade` VALUES ('980203027', '0210', 93);
INSERT INTO `studentgrade` VALUES ('980203028', '0201', 70);
INSERT INTO `studentgrade` VALUES ('980203028', '0210', 62);
INSERT INTO `studentgrade` VALUES ('980203029', '0201', 60);
INSERT INTO `studentgrade` VALUES ('980203029', '0202', 60);
INSERT INTO `studentgrade` VALUES ('980203029', '0205', 76);
INSERT INTO `studentgrade` VALUES ('980203029', '0210', 84);
INSERT INTO `studentgrade` VALUES ('980501001', '0501', 72);
INSERT INTO `studentgrade` VALUES ('980501001', '0502', 96);
INSERT INTO `studentgrade` VALUES ('980501001', '0503', 64);
INSERT INTO `studentgrade` VALUES ('980501002', '0501', 64);
INSERT INTO `studentgrade` VALUES ('980501002', '0502', 77);
INSERT INTO `studentgrade` VALUES ('980501002', '0503', 75);
INSERT INTO `studentgrade` VALUES ('980501003', '0501', 68);
INSERT INTO `studentgrade` VALUES ('980501003', '0502', 64);
INSERT INTO `studentgrade` VALUES ('980501003', '0503', 87);
INSERT INTO `studentgrade` VALUES ('980501004', '0501', 83);
INSERT INTO `studentgrade` VALUES ('980501004', '0502', 87);
INSERT INTO `studentgrade` VALUES ('980501004', '0503', 74);
INSERT INTO `studentgrade` VALUES ('980501005', '0502', 90);
INSERT INTO `studentgrade` VALUES ('980501005', '0503', 83);
INSERT INTO `studentgrade` VALUES ('980501006', '0501', 86);
INSERT INTO `studentgrade` VALUES ('980501006', '0503', 99);
INSERT INTO `studentgrade` VALUES ('980501007', '0502', 68);
INSERT INTO `studentgrade` VALUES ('980501007', '0503', 95);
INSERT INTO `studentgrade` VALUES ('980501008', '0501', 83);
INSERT INTO `studentgrade` VALUES ('980501008', '0502', 80);
INSERT INTO `studentgrade` VALUES ('980501008', '0503', 91);
INSERT INTO `studentgrade` VALUES ('980501009', '0501', 95);
INSERT INTO `studentgrade` VALUES ('980501009', '0502', 80);
INSERT INTO `studentgrade` VALUES ('980501009', '0503', 89);
INSERT INTO `studentgrade` VALUES ('980501010', '0501', 96);
INSERT INTO `studentgrade` VALUES ('980501010', '0502', 66);
INSERT INTO `studentgrade` VALUES ('980501010', '0503', 70);
INSERT INTO `studentgrade` VALUES ('980501013', '0501', 65);
INSERT INTO `studentgrade` VALUES ('980501015', '0502', 85);
INSERT INTO `studentgrade` VALUES ('980501015', '0503', 81);
INSERT INTO `studentgrade` VALUES ('9999999', '111', 1);
INSERT INTO `studentgrade` VALUES ('980101001', '0103', 11);
INSERT INTO `studentgrade` VALUES ('980101002', '0103', 22);
INSERT INTO `studentgrade` VALUES ('980101003', '0103', 33);
INSERT INTO `studentgrade` VALUES ('980101004', '0103', 4);
INSERT INTO `studentgrade` VALUES ('980101005', '0103', 5);
INSERT INTO `studentgrade` VALUES ('980101006', '0103', 6);
INSERT INTO `studentgrade` VALUES ('980101007', '0103', 43);

SET FOREIGN_KEY_CHECKS = 1;
