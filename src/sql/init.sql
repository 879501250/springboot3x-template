-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: build
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrator` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `admin_account` varchar(32) DEFAULT NULL COMMENT '管理员账号',
  `admin_password` varchar(256) DEFAULT NULL COMMENT '管理员密码',
  `admin_name` varchar(64) DEFAULT NULL COMMENT '管理员昵称',
  `merchant_id` int DEFAULT NULL COMMENT '社区id',
  PRIMARY KEY (`id`),
  KEY `administrator_community_id_index` (`merchant_id`),
  CONSTRAINT `administrator_community_info_id_fk` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='管理员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `function1`
--

DROP TABLE IF EXISTS `function1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `function1` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(256) DEFAULT NULL COMMENT '一级功能名称',
  `fid` int DEFAULT NULL COMMENT '标识',
  `path` varchar(256) DEFAULT NULL COMMENT '路由',
  `father_id` int DEFAULT NULL COMMENT '父功能id',
  `component_name` varchar(256) DEFAULT NULL COMMENT '组件名',
  `father_name` varchar(256) DEFAULT NULL COMMENT '父功能名称',
  `icon` varchar(64) DEFAULT NULL COMMENT 'icon图标',
  `color` varchar(64) DEFAULT NULL COMMENT '图标颜色',
  `sort` int DEFAULT '1' COMMENT '相对排序',
  PRIMARY KEY (`id`),
  KEY `function1_fid_index` (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统一级功能表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `function1`
--

LOCK TABLES `function1` WRITE;
/*!40000 ALTER TABLE `function1` DISABLE KEYS */;
INSERT INTO `function1` VALUES (1,'首页',101,'/index',NULL,'index',NULL,'House','silver',1);
/*!40000 ALTER TABLE `function1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `function2`
--

DROP TABLE IF EXISTS `function2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `function2` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(256) DEFAULT NULL COMMENT '二级功能名称',
  `fid` int DEFAULT NULL COMMENT '标识',
  `path` varchar(256) DEFAULT NULL COMMENT '路由',
  `father_id` int DEFAULT NULL COMMENT '所属父功能',
  `component_name` varchar(256) DEFAULT NULL COMMENT '组件名',
  `father_name` varchar(256) DEFAULT NULL COMMENT '父功能名称',
  `icon` varchar(64) DEFAULT NULL COMMENT 'icon图标',
  `color` varchar(64) DEFAULT NULL,
  `sort` int DEFAULT '1' COMMENT '相对排序',
  PRIMARY KEY (`id`),
  KEY `function2_father_id_index` (`father_id`),
  KEY `function2_fid_index` (`fid`),
  CONSTRAINT `function2_function1_fid_fk` FOREIGN KEY (`father_id`) REFERENCES `function1` (`fid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统二级功能表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `function2`
--

LOCK TABLES `function2` WRITE;
/*!40000 ALTER TABLE `function2` DISABLE KEYS */;
INSERT INTO `function2` VALUES (1,'商户概况',201,'/merchantInfo',101,'merchantInfo','首页','Shop','silver',1),(2,'商户信息',202,'/merchantDetails',101,'merchantDetails','首页','Shop','silver',2);
/*!40000 ALTER TABLE `function2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `function3`
--

DROP TABLE IF EXISTS `function3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `function3` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(256) DEFAULT NULL COMMENT '三级功能名称',
  `fid` int DEFAULT NULL COMMENT '标识',
  `path` varchar(256) DEFAULT NULL COMMENT '路由',
  `father_id` int DEFAULT NULL COMMENT '所属父功能id',
  `component_name` varchar(256) DEFAULT NULL COMMENT '组件名',
  `father_name` varchar(256) DEFAULT NULL COMMENT '父功能名称',
  `icon` varchar(64) DEFAULT NULL COMMENT 'icon图标',
  `color` varchar(64) DEFAULT NULL,
  `sort` int DEFAULT '1' COMMENT '相对排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='平台三级功能表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `function3`
--

LOCK TABLES `function3` WRITE;
/*!40000 ALTER TABLE `function3` DISABLE KEYS */;
/*!40000 ALTER TABLE `function3` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `function_system`
--

DROP TABLE IF EXISTS `function_system`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `function_system` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `merchant_id` int DEFAULT NULL COMMENT '商户id',
  `f1id` varchar(256) DEFAULT NULL COMMENT '一级功能id列表',
  `f2id` varchar(256) DEFAULT NULL COMMENT '二级功能id列表',
  `f3id` varchar(256) DEFAULT NULL COMMENT '三级功能id列表',
  PRIMARY KEY (`id`),
  KEY `function_system_merchant_id_fk` (`merchant_id`),
  CONSTRAINT `function_system_merchant_id_fk` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商户功能关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `function_system`
--

LOCK TABLES `function_system` WRITE;
/*!40000 ALTER TABLE `function_system` DISABLE KEYS */;
INSERT INTO `function_system` VALUES (1,1,'101','201,201',NULL);
/*!40000 ALTER TABLE `function_system` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merchant`
--

DROP TABLE IF EXISTS `merchant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `merchant` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `logo_url` varchar(255) DEFAULT NULL,
  `description` text,
  `category` varchar(50) DEFAULT NULL,
  `contact_name` varchar(50) DEFAULT NULL,
  `contact_phone` varchar(15) DEFAULT NULL,
  `contact_email` varchar(50) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `latitude` decimal(10,8) DEFAULT NULL,
  `longitude` decimal(11,8) DEFAULT NULL,
  `open_hours` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchant`
--

LOCK TABLES `merchant` WRITE;
/*!40000 ALTER TABLE `merchant` DISABLE KEYS */;
INSERT INTO `merchant` VALUES (1,'ABC Store','https://example.com/logo.jpg','This is a sample description.','Retail','John Doe','1234567890','johndoe@example.com','123 Main Street, City, Country',40.71280000,-74.00600000,'9:00 AM - 6:00 PM','2024-03-07 20:16:48','2024-03-07 12:16:48');
/*!40000 ALTER TABLE `merchant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(32) DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(256) DEFAULT NULL COMMENT '密码',
  `user_account` varchar(64) DEFAULT NULL COMMENT '账号',
  `user_role` varchar(256) DEFAULT 'user' COMMENT '用户角色：user / admin',
  `avatar` varchar(1024) DEFAULT NULL COMMENT '头像',
  `create_time` datetime DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '逻辑删除：1删除/0存在',
  `gender` tinyint(1) DEFAULT NULL COMMENT '性别',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态：1正常0禁用',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'15666617395','e10adc3949ba59abbe56e057f20f883e','mijiu','user',NULL,'2024-04-02 15:50:29','2024-04-02 15:50:29',0,NULL,1,'15666617395');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-14 16:12:55
