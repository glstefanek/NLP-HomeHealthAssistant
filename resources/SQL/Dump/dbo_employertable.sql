CREATE DATABASE  IF NOT EXISTS `dbo` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `dbo`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: dbo
-- ------------------------------------------------------
-- Server version	5.7.14-log

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
-- Table structure for table `employertable`
--

DROP TABLE IF EXISTS `employertable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employertable` (
  `EmployerID` int(11) NOT NULL AUTO_INCREMENT,
  `EmployerName` varchar(128) DEFAULT NULL,
  `EmployerAddress1` varchar(128) DEFAULT NULL,
  `EmployerAddress2` varchar(128) DEFAULT NULL,
  `EmployerCity` varchar(75) DEFAULT NULL,
  `EmployerState/Province/Region` varchar(50) DEFAULT NULL,
  `EmployerCountry` varchar(75) DEFAULT NULL,
  `EmployerZipCode` varchar(15) DEFAULT NULL,
  `EmployerPhone` varchar(14) DEFAULT NULL,
  `EmployerFAX` varchar(14) DEFAULT NULL,
  `EmployerComments` varchar(254) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `EmployerFirstName` varchar(50) DEFAULT NULL,
  `EmployerMiddleName` varchar(50) DEFAULT NULL,
  `EmployerPrefix` varchar(10) DEFAULT NULL,
  `EmployerSuffix` varchar(10) DEFAULT NULL,
  `CreditCardHolderID` varchar(50) DEFAULT NULL,
  `CreditCardNumber` varchar(50) DEFAULT NULL,
  `CreditCardExpiration` varchar(10) DEFAULT NULL,
  `CreditCardType` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`EmployerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employertable`
--

LOCK TABLES `employertable` WRITE;
/*!40000 ALTER TABLE `employertable` DISABLE KEYS */;
/*!40000 ALTER TABLE `employertable` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-01 21:58:35
