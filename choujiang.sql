/*
 Navicat Premium Data Transfer

 Source Server         : mysql_test
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : choujiang

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 02/03/2022 18:45:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `adminID` int NOT NULL AUTO_INCREMENT COMMENT '管理员id，通常为录入顺序，自增1',
  `adminUserName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员账号',
  `adminPWD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员密码',
  `rights` int NOT NULL DEFAULT 1 COMMENT '权限，管理员默认为1',
  PRIMARY KEY (`adminID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '123456', 1);
INSERT INTO `admin` VALUES (2, 'admin33', '123456', 1);
INSERT INTO `admin` VALUES (3, 'admin333', '123456', 1);
INSERT INTO `admin` VALUES (4, 'admin333333', '123456', 1);
INSERT INTO `admin` VALUES (5, 'admin333333asdsa', '112233', 1);
INSERT INTO `admin` VALUES (6, 'admin333333asdsadsaas', 'tretwrhggr', 1);
INSERT INTO `admin` VALUES (7, 'admin333adsdasadc', '123456', 1);
INSERT INTO `admin` VALUES (8, 'admin333adsda', 'wtqfgergfwqraeg', 1);
INSERT INTO `admin` VALUES (9, 'qwe', 'eqwsdas', 1);
INSERT INTO `admin` VALUES (10, 'adminqwewq', '6666666', 1);
INSERT INTO `admin` VALUES (11, '2020112212', '123456', 1);
INSERT INTO `admin` VALUES (12, 'ada', 'dsadsads', 1);
INSERT INTO `admin` VALUES (13, 'daddfafsds', '123456789', 1);
INSERT INTO `admin` VALUES (14, 'daddfafsdswqw', '123456789sas', 2);
INSERT INTO `admin` VALUES (15, 'admin21321', '1234456', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `ID` int NOT NULL AUTO_INCREMENT COMMENT '抽奖ID，自增1',
  `studentID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `grade` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '年级',
  `target` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '入组选择',
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专业',
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `mail` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `rights` int NULL DEFAULT 2 COMMENT '权限，用户默认为2',
  `score` int NULL DEFAULT 0 COMMENT '分数',
  `result` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '未录用' COMMENT '录取结果',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '202012', '张', '2021', '机械组', '经济与管理学院', '123456', '552@qq.com', '157', 2, 110, '录用');
INSERT INTO `user` VALUES (2, '202012213', '张三儿', '2020', '开发组', '计算机与信息学院', '123456', 'ADSFFASD24213@qq.com', '0710864', 2, 120, '录用');
INSERT INTO `user` VALUES (3, '202111331166', '王五', '2021', '智能组', '水利与环境学院', 'SJRhc38E86Geh8TP', 'tim@foxmail.com', '17897104175', 2, 0, '录用');
INSERT INTO `user` VALUES (4, '201911345', '黄崇敬', '2077', '智能组', '计算机与信息学院', 'dadas65161', '6565151@dssffds.com', '15727', 2, 0, '未录用');
INSERT INTO `user` VALUES (5, '202111362354', '李二', '2021', '智能组', '经济与管理学院', 'zadas115622', 'ape@qq.com', '15963412187', 2, 0, '未录用');
INSERT INTO `user` VALUES (6, '2023163212', '李白', '2020', '开发组', '土木与建筑学院', 'adFSdfsd', 'adsAS@SADAAD.COM', '16513136845', 2, 0, '未录用');
INSERT INTO `user` VALUES (7, '202111322', 'Forest Elm', '2020', '开发组', '水利与环境学院', 'dasdas', 't@gmail.com', '041059', 2, 0, '未录用');
INSERT INTO `user` VALUES (8, '20011221213', '张', '2021', '开发组', '计算机与信息学院', '123456', 'sdgdfsg2234234@qq.com', '1572', 2, 0, '未录用');
INSERT INTO `user` VALUES (9, '2021336699', '白居易', '2020', '机械组', '计算机与信息学院', '123456', '3424324324324@qq.com', '', 2, 0, '未录用');
INSERT INTO `user` VALUES (10, '202011221216', '张', '2021', '智能组', '计算机与信息学院', '12345666', '243322dsf234324234@qq.com', '1572', 2, 0, '未录用');
INSERT INTO `user` VALUES (11, '20201121212', 'Forest Elm', '2020', '机械组', '电气与新能源学院', '123456', 'tim@foxmail.com', '1572', 2, 0, '未录用');
INSERT INTO `user` VALUES (12, '202011221255', 'Forest Elm', '2021', '开发组', '计算机与信息学院', '123456', 'asdasdasdasd@qq.com', '15727', 2, 0, '未录用');
INSERT INTO `user` VALUES (13, '20201121299', 'Forest Elm', '2021', '开发组', '计算机与信息学院', '123456', 'sdsdafwr@qq.com', '1572', 2, 0, '未录用');
INSERT INTO `user` VALUES (14, '20201166335', 'Forest Elm', '2020', '智能组', '计算机与信息学院', '123456', 'timbrid@mail.com', '04105915516', 2, 0, '未录用');
INSERT INTO `user` VALUES (15, '202012212184', 'Forest Elm', '2021', '开发组', '计算机与信息学院', '123456', 'timb@gmail.com', '04105915516', 2, 0, '未录用');
INSERT INTO `user` VALUES (16, '11234566789', '鲤鱼', '2021', 'ACM', '经济与管理学院', 'asddasasdasd', 'dsadasdas@dsads', 'dsadasdas', 2, 0, '录用');
INSERT INTO `user` VALUES (17, '1124566564', '杰克', '2021', '机械组', '土木与建筑学院', 'asddasasdasd', 'dsadasdsddas@dsads', 'dsadasdas', 2, 0, '未录用');
INSERT INTO `user` VALUES (18, '112345663213', '肉丝', '2021', 'ACM', '医学院', 'asddasasdasd', 'sdsdsdsdsddsdsdsadsa@dsads', 'dsadasdas', 2, 0, '未录用');
INSERT INTO `user` VALUES (19, '20011221233', '李正一', '2021', '智能组', '机械与动力学院', '123456', 'timmail.com', '0943474', 2, 0, '录用');
INSERT INTO `user` VALUES (20, '202012215', '张', '2021', 'ACM', '计算机与信息学院', '12345678', '553781we523@qq.com', '15727270175', 2, 0, '未录用');
INSERT INTO `user` VALUES (21, '20215115616', '李沐兮', '2021', 'ACM', '计算机与信息学院', '132121311151', 'sadassdas@qq.com', '17897104175', 2, 0, '未录用');
INSERT INTO `user` VALUES (22, '20216121569', '安芷荔', '2021', '机械组', '经济与管理学院', '1321211122', 'sa12552s@qq.com', '32142342345', 2, 0, '未录用');
INSERT INTO `user` VALUES (23, '202123559666', '欧阳奕清', '2021', '机械组', '土木与建筑学院', '32432432234rewr', 'sa125qcom', '13134545', 2, 0, '录用');
INSERT INTO `user` VALUES (24, '202012212323', 'Forest Elm', '2021', '机械组', '理学院', '123456', 'timbrid@foxmail.com', '17897104175', 2, 0, '未录用');
INSERT INTO `user` VALUES (25, '202011234', 'hechenghao', '2020', '机械组', '计算机与信息学院', '123123', '30365@qq.com', '1596336', 2, 0, '录用');
INSERT INTO `user` VALUES (26, '20166666', 'liability', '2020', '开发组', '其他学院', '132165415', 'asdsadsadfdgdf@qweqw.vdscvs', '', 2, 0, '未录用');
INSERT INTO `user` VALUES (27, '202012409', '张', '2020', '智能组', '水利与环境学院', '666666', '5537@qq.com', '15725', 2, 0, '未录用');

-- ----------------------------
-- Table structure for winner
-- ----------------------------
DROP TABLE IF EXISTS `winner`;
CREATE TABLE `winner`  (
  `winnerID` int NOT NULL AUTO_INCREMENT COMMENT '获奖ID，通常为获奖顺序，自增1',
  `ID` int NOT NULL,
  `studentID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`winnerID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 671 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of winner
-- ----------------------------
INSERT INTO `winner` VALUES (2, 1, '0');
INSERT INTO `winner` VALUES (4, 1, '2020111112');
INSERT INTO `winner` VALUES (5, 1, '2020111112');
INSERT INTO `winner` VALUES (666, 1, '2020111112');
INSERT INTO `winner` VALUES (667, 1, '2020111112');
INSERT INTO `winner` VALUES (668, 111, '2020111112');
INSERT INTO `winner` VALUES (669, 111, '2020111112');
INSERT INTO `winner` VALUES (670, 111, '2020111112');

SET FOREIGN_KEY_CHECKS = 1;
