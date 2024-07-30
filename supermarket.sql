/*
 Navicat Premium Data Transfer

 Source Server         : dockerMysql
 Source Server Type    : MySQL
 Source Server Version : 90000 (9.0.0)
 Source Host           : localhost:3306
 Source Schema         : supermarket

 Target Server Type    : MySQL
 Target Server Version : 90000 (9.0.0)
 File Encoding         : 65001

 Date: 11/07/2024 23:39:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `memberID` int NOT NULL AUTO_INCREMENT,
  `memberName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `telephone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`memberID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES (1, '张伟', '13800000001');
INSERT INTO `member` VALUES (2, '王芳', '13800000002');
INSERT INTO `member` VALUES (3, '李娜', '13800000003');
INSERT INTO `member` VALUES (4, '刘强', '13800000004');
INSERT INTO `member` VALUES (5, '陈杰', '13800000005');
INSERT INTO `member` VALUES (6, '杨洋', '13800000006');
INSERT INTO `member` VALUES (7, '赵丽', '13800000007');
INSERT INTO `member` VALUES (8, '黄磊', '13800000008');
INSERT INTO `member` VALUES (9, '周迅', '13800000009');
INSERT INTO `member` VALUES (10, '徐帆', '13800000010');
INSERT INTO `member` VALUES (11, '孙红雷', '13800000011');
INSERT INTO `member` VALUES (12, '马丽', '13800000012');
INSERT INTO `member` VALUES (13, '胡歌', '13800000013');
INSERT INTO `member` VALUES (14, '郭涛', '13800000014');
INSERT INTO `member` VALUES (15, '何炅', '13800000015');
INSERT INTO `member` VALUES (16, '林志玲', '13800000016');
INSERT INTO `member` VALUES (17, '梁朝伟', '13800000017');
INSERT INTO `member` VALUES (18, '李冰冰', '13800000018');
INSERT INTO `member` VALUES (19, '范冰冰', '13800000019');
INSERT INTO `member` VALUES (20, '钟汉良', '13800000020');

-- ----------------------------
-- Table structure for orderDetails
-- ----------------------------
DROP TABLE IF EXISTS `orderDetails`;
CREATE TABLE `orderDetails`  (
  `orderDetailID` int NOT NULL AUTO_INCREMENT,
  `orderID` int NOT NULL,
  `productID` int NOT NULL,
  `productName` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `unitPrice` int NOT NULL,
  `quantity` int NOT NULL,
  `totalPrice` int NOT NULL,
  PRIMARY KEY (`orderDetailID`) USING BTREE,
  INDEX `orderID`(`orderID` ASC) USING BTREE,
  INDEX `productID`(`productID` ASC) USING BTREE,
  CONSTRAINT `orderDetails_ibfk_1` FOREIGN KEY (`orderID`) REFERENCES `orders` (`orderID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `orderDetails_ibfk_2` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderDetails
-- ----------------------------

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `orderID` int NOT NULL AUTO_INCREMENT,
  `memberID` int NOT NULL,
  `orderDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`orderID`) USING BTREE,
  INDEX `memberID`(`memberID` ASC) USING BTREE,
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`memberID`) REFERENCES `member` (`memberID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `productID` int NOT NULL AUTO_INCREMENT,
  `productName` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` int NOT NULL,
  `stock` int NOT NULL,
  PRIMARY KEY (`productID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '苹果', 5, 100);
INSERT INTO `product` VALUES (2, '香蕉', 3, 150);
INSERT INTO `product` VALUES (3, '橙子', 4, 200);
INSERT INTO `product` VALUES (4, '葡萄', 6, 120);
INSERT INTO `product` VALUES (5, '西瓜', 10, 80);
INSERT INTO `product` VALUES (6, '草莓', 15, 60);
INSERT INTO `product` VALUES (7, '菠萝', 7, 90);
INSERT INTO `product` VALUES (8, '芒果', 12, 110);
INSERT INTO `product` VALUES (9, '蓝莓', 18, 70);
INSERT INTO `product` VALUES (10, '樱桃', 20, 50);
INSERT INTO `product` VALUES (11, '番茄', 4, 130);
INSERT INTO `product` VALUES (12, '黄瓜', 2, 140);
INSERT INTO `product` VALUES (13, '胡萝卜', 3, 150);
INSERT INTO `product` VALUES (14, '土豆', 2, 160);
INSERT INTO `product` VALUES (15, '洋葱', 2, 170);
INSERT INTO `product` VALUES (16, '生菜', 3, 90);
INSERT INTO `product` VALUES (17, '青椒', 5, 80);
INSERT INTO `product` VALUES (18, '西兰花', 6, 100);
INSERT INTO `product` VALUES (19, '蘑菇', 8, 70);
INSERT INTO `product` VALUES (20, '白菜', 3, 150);

SET FOREIGN_KEY_CHECKS = 1;
