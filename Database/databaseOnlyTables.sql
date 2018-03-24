-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: carservice1.cubow3iaz1bh.eu-west-1.rds.amazonaws.com    Database: carservice1
-- ------------------------------------------------------
-- Server version	5.6.39-log

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
-- Table structure for table `cars`
--

DROP TABLE IF EXISTS `cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cars` (
  `idCars` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(45) NOT NULL,
  `model` varchar(45) NOT NULL,
  `yearProduction` int(11) NOT NULL,
  `VIN` varchar(45) NOT NULL,
  `registrationNumber` varchar(45) NOT NULL,
  PRIMARY KEY (`idCars`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars`
--

LOCK TABLES `cars` WRITE;
/*!40000 ALTER TABLE `cars` DISABLE KEYS */;
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clients` (
  `idClients` int(11) NOT NULL AUTO_INCREMENT,
  `imie` varchar(45) NOT NULL,
  `nazwisko` varchar(45) NOT NULL,
  `pesel` varchar(11) NOT NULL,
  PRIMARY KEY (`idClients`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issuedParts`
--

DROP TABLE IF EXISTS `issuedParts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issuedParts` (
  `idIssuedParts` int(11) NOT NULL AUTO_INCREMENT,
  `idRepairs` int(11) NOT NULL,
  `idParts` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`idIssuedParts`),
  KEY `repairs_idx` (`idRepairs`),
  KEY `parts_idx` (`idParts`),
  CONSTRAINT `parts1` FOREIGN KEY (`idParts`) REFERENCES `parts` (`idparts`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `repairs` FOREIGN KEY (`idRepairs`) REFERENCES `repairs` (`idRepairs`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issuedParts`
--

LOCK TABLES `issuedParts` WRITE;
/*!40000 ALTER TABLE `issuedParts` DISABLE KEYS */;
/*!40000 ALTER TABLE `issuedParts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `idOrders` int(11) NOT NULL AUTO_INCREMENT,
  `idParts-Orders` int(11) NOT NULL,
  `status` varchar(45) NOT NULL,
  `value` double NOT NULL,
  PRIMARY KEY (`idOrders`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parts`
--

DROP TABLE IF EXISTS `parts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parts` (
  `idparts` int(11) NOT NULL AUTO_INCREMENT,
  `idTypeParts` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`idparts`),
  KEY `type_idx` (`idTypeParts`),
  CONSTRAINT `type` FOREIGN KEY (`idTypeParts`) REFERENCES `typeParts` (`idTypeParts`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parts`
--

LOCK TABLES `parts` WRITE;
/*!40000 ALTER TABLE `parts` DISABLE KEYS */;
/*!40000 ALTER TABLE `parts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parts-orders`
--

DROP TABLE IF EXISTS `parts-orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parts-orders` (
  `idParts-Orders` int(11) NOT NULL AUTO_INCREMENT,
  `idParts` int(11) NOT NULL,
  `idOrders` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`idParts-Orders`),
  KEY `parts_idx` (`idParts`),
  KEY `orders_idx` (`idOrders`),
  CONSTRAINT `orders` FOREIGN KEY (`idOrders`) REFERENCES `orders` (`idOrders`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `parts` FOREIGN KEY (`idParts`) REFERENCES `parts` (`idparts`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parts-orders`
--

LOCK TABLES `parts-orders` WRITE;
/*!40000 ALTER TABLE `parts-orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `parts-orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repairs`
--

DROP TABLE IF EXISTS `repairs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `repairs` (
  `idRepairs` int(11) NOT NULL,
  `idCars` int(11) NOT NULL,
  `idClient` int(11) NOT NULL,
  `idTypeRepairs` int(11) NOT NULL,
  `dedicatedTime` double NOT NULL,
  `comments` varchar(2000) NOT NULL,
  `status` varchar(45) NOT NULL,
  `price` double NOT NULL,
  `startDate` date NOT NULL,
  PRIMARY KEY (`idRepairs`),
  UNIQUE KEY `idrepairs_UNIQUE` (`idRepairs`),
  KEY `cars_idx` (`idCars`),
  KEY `typeRepairs_idx` (`idTypeRepairs`),
  KEY `client_idx` (`idClient`),
  CONSTRAINT `cars` FOREIGN KEY (`idCars`) REFERENCES `cars` (`idCars`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `clients` FOREIGN KEY (`idClient`) REFERENCES `clients` (`idClients`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `typerepairs` FOREIGN KEY (`idTypeRepairs`) REFERENCES `typeRepairs` (`idTypeRepairs`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repairs`
--

LOCK TABLES `repairs` WRITE;
/*!40000 ALTER TABLE `repairs` DISABLE KEYS */;
/*!40000 ALTER TABLE `repairs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `serviceContracts`
--

DROP TABLE IF EXISTS `serviceContracts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `serviceContracts` (
  `idserviceContracts` int(11) NOT NULL AUTO_INCREMENT,
  `idClient` int(11) NOT NULL,
  `remainingWorkingTime` double NOT NULL,
  `remainingAmountForParts` double NOT NULL,
  PRIMARY KEY (`idserviceContracts`),
  KEY `client_idx` (`idClient`),
  CONSTRAINT `client` FOREIGN KEY (`idClient`) REFERENCES `clients` (`idClients`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serviceContracts`
--

LOCK TABLES `serviceContracts` WRITE;
/*!40000 ALTER TABLE `serviceContracts` DISABLE KEYS */;
/*!40000 ALTER TABLE `serviceContracts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `typeParts`
--

DROP TABLE IF EXISTS `typeParts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `typeParts` (
  `idTypeParts` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`idTypeParts`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typeParts`
--

LOCK TABLES `typeParts` WRITE;
/*!40000 ALTER TABLE `typeParts` DISABLE KEYS */;
/*!40000 ALTER TABLE `typeParts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `typeRepairs`
--

DROP TABLE IF EXISTS `typeRepairs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `typeRepairs` (
  `idTypeRepairs` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`idTypeRepairs`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typeRepairs`
--

LOCK TABLES `typeRepairs` WRITE;
/*!40000 ALTER TABLE `typeRepairs` DISABLE KEYS */;
/*!40000 ALTER TABLE `typeRepairs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `idusers` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`idusers`),
  UNIQUE KEY `idusers_UNIQUE` (`idusers`),
  UNIQUE KEY `userscol_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','admin','ADMINISTRATOR'),(2,'serwis','serwis','SERVICEMAN'),(3,'magazyn','magazyn','WAREHOUSEMAN');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'carservice1'
--

--
-- Dumping routines for database 'carservice1'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-24 17:34:25
