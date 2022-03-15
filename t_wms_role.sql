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
-- Table structure for table `t_wms_role`
--

DROP TABLE IF EXISTS `t_wms_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_wms_role` (
  `id` varchar(40) NOT NULL COMMENT '主键ID',
  `role_name` varchar(40) NOT NULL COMMENT '角色名称',
  `role_code` varchar(40) NOT NULL COMMENT '角色代码',
  `type` varchar(2) NOT NULL COMMENT '角色类型',
  `del_flag` varchar(1) DEFAULT '1' COMMENT '是否删除',
  `create_by` varchar(200) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(200) DEFAULT NULL COMMENT '最后更新人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_wms_role`
--

LOCK TABLES `t_wms_role` WRITE;
/*!40000 ALTER TABLE `t_wms_role` DISABLE KEYS */;
INSERT INTO `t_wms_role` VALUES ('032a1a6fc439467890340ca84bf649cf','测试角色4','test4','2','0','super','2022-01-28 09:05:10','super','2022-01-28 09:05:10'),('1486972177930084353','一级库仓库保管员','lvlOneStoreman','2','1',NULL,'2022-01-28 07:59:28','super','2022-01-28 07:59:28'),('1486972230878978050','二级库仓库保管员','lvlTwoStoreman','2','1',NULL,'2022-01-28 07:59:41','super','2022-01-28 07:59:41'),('14a2bb85732a4381b485bb57b72baa76','二级库领料员','lvlTwoApplyer','2','0',NULL,'2022-01-28 08:41:15',NULL,'2022-01-28 08:41:15'),('5b195705127a49c383b5be11700f7a7c','测试角色3','testRole3','2','0',NULL,'2022-01-28 08:59:41','super','2022-01-28 08:59:41'),('5e9066572ab8463392c3b418edd3b914','超级管理员','superAdmin','1','1','sys','2022-01-27 06:33:22','super','2022-01-27 06:33:22'),('97c0e555e00f4f2db436eabb49a692f4','一级库领料员','lvlOneApplyer','2','1',NULL,'2022-01-28 08:39:27','super','2022-01-28 08:39:27'),('9c76a8edf05d4de4be29ed9495d58d82','二级库领料员','lvlTwoApplyer','2','1',NULL,'2022-01-28 08:42:03','super','2022-01-28 08:42:03'),('b0555248fa5a4334859e6a40352af921','班组长','groupMonitor','2','0',NULL,'2022-01-28 08:49:26',NULL,'2022-01-28 08:49:26'),('b9406dd7532b433fb988d33dfddfda90','班组长','groupMonitor','2','1',NULL,'2022-01-28 08:49:44','super','2022-01-28 08:49:44'),('cf91f586d4ae4c18a8dce3412d6ad02f','测试角色2','test1','2','0',NULL,'2022-01-28 08:57:29','super','2022-01-28 08:57:29'),('e18130a6ee4249029f364ccb05fdcfaa','测试角色1','test11','2','0',NULL,'2022-01-28 08:59:18','super','2022-01-28 08:59:18');
/*!40000 ALTER TABLE `t_wms_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-15 21:33:35
