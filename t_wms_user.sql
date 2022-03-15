-- MySQL dump 10.13  Distrib 5.7.32, for Win64 (x86_64)
--
-- Host: localhost    Database: wms_db
-- ------------------------------------------------------
-- Server version	5.7.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_wms_user`
--

DROP TABLE IF EXISTS `t_wms_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_wms_user` (
  `id` varchar(40) NOT NULL COMMENT '主键ID',
  `user_name` varchar(40) NOT NULL COMMENT '用户名称',
  `role_code` varchar(40) NOT NULL COMMENT '角色代码',
  `user_pwd` varchar(500) NOT NULL COMMENT '用户密码',
  `dept_name` varchar(100) DEFAULT NULL COMMENT '部门名称',
  `card_no` varchar(40) DEFAULT NULL COMMENT '工号',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `education` varchar(100) DEFAULT NULL COMMENT '教育 ',
  `del_flag` varchar(1) DEFAULT '1' COMMENT '是否删除',
  `create_by` varchar(200) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(200) DEFAULT NULL COMMENT '最后更新人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_wms_user`
--

LOCK TABLES `t_wms_user` WRITE;
/*!40000 ALTER TABLE `t_wms_user` DISABLE KEYS */;
INSERT INTO `t_wms_user` VALUES ('0edacd149ade44e596f6e7557789418c','Jesse','lvlOneApplyer','MTQ3MjU4','SP','SP0002','12345697542',NULL,'1','super','2022-01-29 07:40:16','super','2022-01-29 07:40:16'),('574a612414b3464fb8e135bfc18ee777','super','superAdmin','MTIzNDU2','SP','SP0001','15300518706',NULL,'1','sys','2022-01-27 06:36:39','sys','2022-01-27 06:36:39');
/*!40000 ALTER TABLE `t_wms_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-15 21:33:44
