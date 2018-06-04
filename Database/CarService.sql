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
  `id_cars` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(45) NOT NULL,
  `model` varchar(45) NOT NULL,
  `year_production` int(11) NOT NULL,
  `VIN` varchar(45) NOT NULL,
  `registration_number` varchar(45) NOT NULL,
  PRIMARY KEY (`id_cars`)
) ENGINE=InnoDB AUTO_INCREMENT=351 DEFAULT CHARSET=latin1 COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars`
--

LOCK TABLES `cars` WRITE;
/*!40000 ALTER TABLE `cars` DISABLE KEYS */;
INSERT INTO `cars` VALUES (32,'Peugeot','206',1998,'GHYDKJ3495U734054','RZ45676'),(39,'Volkswagen','Golf',2004,'GHBG5674UYT986JH5','RJSHN84'),(68,'Peugot','307',2013,'GHB654DTY87YH54FG','KRK5678'),(93,'Opel','Vectra',2011,'GRGD54DT457YH54FT','RZE876H'),(94,'Toyota','Celica',2002,'HFGKR342234JKFGKF','GD65421');
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clients` (
  `id_clients` int(11) NOT NULL AUTO_INCREMENT,
  `pesel` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  PRIMARY KEY (`id_clients`)
) ENGINE=InnoDB AUTO_INCREMENT=356 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (63,98122343456,'Pawel','Sroka'),(64,76453627895,'Lucjan','Kozyra'),(65,93734587690,'Mateusz','Lech'),(66,96325632147,'Mateusz','Tekiela'),(69,64320987546,'Pawel','Malak'),(70,75684905674,'Piotr','Paluch'),(71,46549878321,'Witek','Wrona'),(74,12358254679,'Piotr','Kruk');
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
INSERT INTO `hibernate_sequence` VALUES (391);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issued_parts`
--

DROP TABLE IF EXISTS `issued_parts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issued_parts` (
  `id_issued_parts` int(11) NOT NULL AUTO_INCREMENT,
  `id_repairs` int(11) NOT NULL,
  `id_parts` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id_issued_parts`),
  KEY `repairs_idx` (`id_repairs`),
  KEY `parts_idx` (`id_parts`),
  CONSTRAINT `parts1` FOREIGN KEY (`id_parts`) REFERENCES `parts` (`idparts`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `repairs` FOREIGN KEY (`id_repairs`) REFERENCES `repairs` (`id_repairs`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=390 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issued_parts`
--

LOCK TABLES `issued_parts` WRITE;
/*!40000 ALTER TABLE `issued_parts` DISABLE KEYS */;
INSERT INTO `issued_parts` VALUES (376,375,237,2,'Zakonczone'),(378,377,372,1,'W zamówieniu'),(380,379,231,1,'Zakonczone'),(382,381,231,1,'Zakonczone'),(386,385,237,4,'Nowe'),(388,387,236,2,'Zakonczone'),(389,385,236,1,'W zamówieniu');
/*!40000 ALTER TABLE `issued_parts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id_orders` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(45) NOT NULL,
  `value` double NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id_orders`)
) ENGINE=InnoDB AUTO_INCREMENT=391 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'Zakonczone',40,'2018-05-20'),(2,'Zakonczone',149.99,'2018-05-23'),(261,'Zakonczono',713.32,'2018-05-23'),(264,'Zakonczono',81.44,'2018-05-23'),(267,'Zakonczono',88,'2018-05-23'),(360,'Zakonczono',377.68,'2018-05-29'),(390,'Zakonczono',88.65,'2018-06-03');
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
  `id_type_parts` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`idparts`),
  KEY `typeParts_idx` (`id_type_parts`),
  CONSTRAINT `typeParts` FOREIGN KEY (`id_type_parts`) REFERENCES `type_parts` (`id_type_parts`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=373 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parts`
--

LOCK TABLES `parts` WRITE;
/*!40000 ALTER TABLE `parts` DISABLE KEYS */;
INSERT INTO `parts` VALUES (230,2,'Lusterko prawe',5,129.99),(231,3,'Olej 10W40 1L',9,20),(233,6,'Dywaniki welurowe Audi A4 2000-2004',7,88.65),(234,1,'Zarówka H7 PHILIPS',20,35.87),(235,1,'Zarówka PBT5 OSRAM -10sztuk',9,21.44),(236,1,'Zarówka H3 PHILIPS',10,11.87),(237,4,'Klocki hamulcowe BOSCH',8,88),(372,5,'Drazek kierowniczy - poprzeczny',0,91);
/*!40000 ALTER TABLE `parts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parts_orders`
--

DROP TABLE IF EXISTS `parts_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parts_orders` (
  `id_parts_orders` int(11) NOT NULL AUTO_INCREMENT,
  `id_parts` int(11) NOT NULL,
  `id_orders` int(11) NOT NULL DEFAULT '0',
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id_parts_orders`),
  KEY `parts_idx` (`id_parts`),
  KEY `orders_idx` (`id_orders`),
  CONSTRAINT `parts` FOREIGN KEY (`id_parts`) REFERENCES `parts` (`idparts`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=362 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parts_orders`
--

LOCK TABLES `parts_orders` WRITE;
/*!40000 ALTER TABLE `parts_orders` DISABLE KEYS */;
INSERT INTO `parts_orders` VALUES (1,231,1,2),(4,230,2,1),(5,231,2,1),(6,230,261,3),(240,236,261,5),(259,237,261,3),(262,231,264,3),(263,235,264,1),(266,237,267,1),(268,233,360,2),(269,235,360,6),(359,234,360,2),(361,233,390,1);
/*!40000 ALTER TABLE `parts_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repairs`
--

DROP TABLE IF EXISTS `repairs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `repairs` (
  `id_repairs` int(11) NOT NULL,
  `id_cars` int(11) NOT NULL,
  `id_client` int(11) NOT NULL,
  `id_type_repairs` int(11) NOT NULL,
  `dedicated_time` double NOT NULL,
  `comments` varchar(2000) NOT NULL,
  `status` varchar(45) NOT NULL,
  `price` double NOT NULL,
  `start_date` date NOT NULL,
  PRIMARY KEY (`id_repairs`),
  UNIQUE KEY `idrepairs_UNIQUE` (`id_repairs`),
  KEY `client_idx` (`id_client`),
  KEY `cars_idx` (`id_cars`),
  KEY `typerepairs_idx` (`id_type_repairs`),
  CONSTRAINT `cars` FOREIGN KEY (`id_cars`) REFERENCES `cars` (`id_cars`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `clients` FOREIGN KEY (`id_client`) REFERENCES `clients` (`id_clients`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `type` FOREIGN KEY (`id_type_repairs`) REFERENCES `type_repairs` (`id_type_repairs`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repairs`
--

LOCK TABLES `repairs` WRITE;
/*!40000 ALTER TABLE `repairs` DISABLE KEYS */;
INSERT INTO `repairs` VALUES (374,39,74,4,0,'Brak','Zakonczone',100,'2018-06-02'),(375,94,65,1,0,'Wymiana klocków hamulcowych','Zakonczone',176,'2018-06-02'),(377,32,70,1,2,'','Zakonczone',191,'2018-06-02'),(379,94,69,3,4,'','Zakonczone',0,'2018-06-02'),(381,32,69,3,1,'','Zakonczone',0,'2018-06-02'),(383,93,71,4,0,'','Nowe',0,'2018-06-02'),(384,94,66,2,0,'Instalacja gazowa','Nowe',0,'2018-06-02'),(385,68,70,1,0,'Klocki hamulcowe i tarcze ','W trakcie',0,'2018-06-02'),(387,93,71,1,0.5,'','Zakonczone',48.739999999999995,'2018-06-02');
/*!40000 ALTER TABLE `repairs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_contracts`
--

DROP TABLE IF EXISTS `service_contracts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service_contracts` (
  `id_service_contracts` int(11) NOT NULL,
  `id_client` int(11) NOT NULL,
  `amount_for_parts` double NOT NULL,
  `remaining_amount_for_parts` double NOT NULL,
  `remaining_working_time` double NOT NULL,
  `working_time` double NOT NULL,
  PRIMARY KEY (`id_service_contracts`),
  KEY `clientsId_idx` (`id_client`),
  CONSTRAINT `clientsID` FOREIGN KEY (`id_client`) REFERENCES `clients` (`id_clients`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_contracts`
--

LOCK TABLES `service_contracts` WRITE;
/*!40000 ALTER TABLE `service_contracts` DISABLE KEYS */;
INSERT INTO `service_contracts` VALUES (77,69,1345,1325,6,7),(78,74,4565.34,4565.34,40.5,43.5),(79,63,1560.23,1560.23,20,20);
/*!40000 ALTER TABLE `service_contracts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_parts`
--

DROP TABLE IF EXISTS `type_parts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type_parts` (
  `id_type_parts` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_type_parts`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_parts`
--

LOCK TABLES `type_parts` WRITE;
/*!40000 ALTER TABLE `type_parts` DISABLE KEYS */;
INSERT INTO `type_parts` VALUES (1,'Lampy'),(2,'Karoseria'),(3,'Olej'),(4,'Uklad hamowania'),(5,'Uklad napedowy'),(6,'Tapicerka');
/*!40000 ALTER TABLE `type_parts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_repairs`
--

DROP TABLE IF EXISTS `type_repairs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type_repairs` (
  `id_type_repairs` int(11) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_type_repairs`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_repairs`
--

LOCK TABLES `type_repairs` WRITE;
/*!40000 ALTER TABLE `type_repairs` DISABLE KEYS */;
INSERT INTO `type_repairs` VALUES (1,'Naprawa'),(2,'Gwarancja'),(3,'Naprawa serwisowa'),(4,'Przeglad techniczny');
/*!40000 ALTER TABLE `type_repairs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id_user_role` int(11) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id_user_role`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,'Administrator'),(2,'Magazynier'),(3,'Serwisant');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
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
  `password` varchar(100) NOT NULL,
  `id_role` int(11) NOT NULL,
  PRIMARY KEY (`idusers`),
  UNIQUE KEY `idusers_UNIQUE` (`idusers`),
  UNIQUE KEY `userscol_UNIQUE` (`username`),
  KEY `rolesusers_idx` (`id_role`),
  CONSTRAINT `rolesusers` FOREIGN KEY (`id_role`) REFERENCES `user_role` (`id_user_role`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=353 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (20,'admin','$2a$10$vCliyP7MUVNHn1x.FvOgleRiBjZnViPbYu6Y4NUP.bT5/ePNRh5n2',1),(21,'serwis','$2a$10$5uzEAokg7DcYh7ak5cCq.unydnmAc5Dk1CrciJR4DpMlB03TrC5.C',3),(22,'magazyn','$2a$10$Fcb1ACWsBwiDU3r/FHQiBOfMPw6mmQIlu.WPrQ8AGtnDI9jr/PSe.',2);
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

-- Dump completed on 2018-06-04 13:28:23
