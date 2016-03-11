DROP DATABSE `dietack`;
CREATE DATABASE  IF NOT EXISTS `dietack` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `dietack`;
-- MySQL dump 10.13  Distrib 5.5.47, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: dietack
-- ------------------------------------------------------
-- Server version	5.6.29

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
-- Table structure for table `ingredienti`
--

DROP TABLE IF EXISTS `ingredienti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingredienti` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `kcal` float NOT NULL,
  `foto` varchar(200) DEFAULT NULL,
  `um` varchar(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredienti`
--

LOCK TABLES `ingredienti` WRITE;
/*!40000 ALTER TABLE `ingredienti` DISABLE KEYS */;
INSERT INTO `ingredienti` VALUES (1,'patate',770,'','kg'),(2,'carote',410,'','kg'),(3,'ducco d\'arancia',540,'','l'),(4,'pasta',350,NULL,'kg'),(5,'burro',7170,NULL,'kg'),(6,'ricotta',1380,NULL,'kg'),(7,'cernia',920,NULL,'kg'),(8,'piselli',810,NULL,'kg'),(9,'sedano',80,NULL,'kg'),(10,'spinaci',230,NULL,'kg'),(11,'aranciata',450,'','l'),(12,'birra',430,NULL,'l'),(13,'the',10,'','l'),(14,'vodka',2310,'','l'),(15,'banana',890,'','kg'),(16,'limone',110,'','kg'),(17,'tacchino',1600,'','kg'),(18,'gallina',1100,NULL,'kg'),(19,'grana',3920,'','kg'),(20,'mozzarella',2540,'','kg'),(21,'yogurt alla vaniglia',840,NULL,'kg'),(22,'zucchine',210,'','kg'),(23,'lattuga',170,'','kg');
/*!40000 ALTER TABLE `ingredienti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredienti_ricetta`
--

DROP TABLE IF EXISTS `ingredienti_ricetta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingredienti_ricetta` (
  `id_ricetta` int(11) NOT NULL,
  `id_ingrediente` int(11) NOT NULL,
  `quantita` float NOT NULL,
  `um` varchar(5) NOT NULL,
  UNIQUE KEY `ingredienti_ricetta_id_ricetta_id_ingrediente_pk` (`id_ricetta`,`id_ingrediente`),
  KEY `ingredienti_ricetta_ingredienti_id_fk` (`id_ingrediente`),
  CONSTRAINT `ingredienti_ricetta_ingredienti_id_fk` FOREIGN KEY (`id_ingrediente`) REFERENCES `ingredienti` (`id`),
  CONSTRAINT `ingredienti_ricetta_ricetta_id_ricetta_fk` FOREIGN KEY (`id_ricetta`) REFERENCES `ricetta` (`id_ricetta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredienti_ricetta`
--

LOCK TABLES `ingredienti_ricetta` WRITE;
/*!40000 ALTER TABLE `ingredienti_ricetta` DISABLE KEYS */;
INSERT INTO `ingredienti_ricetta` VALUES (1,4,0.08,'kg'),(2,1,0.5,'kg');
/*!40000 ALTER TABLE `ingredienti_ricetta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mangia`
--

DROP TABLE IF EXISTS `mangia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mangia` (
  `id_mangia` int(11) NOT NULL AUTO_INCREMENT,
  `id_utente` int(11) NOT NULL,
  `id_ricetta` int(11) NOT NULL,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_mangia`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mangia`
--

LOCK TABLES `mangia` WRITE;
/*!40000 ALTER TABLE `mangia` DISABLE KEYS */;
INSERT INTO `mangia` VALUES (1,1,1,'0000-00-00 00:00:00'),(2,1,2,'2016-03-11 06:09:12'),(3,2,1,'2016-03-11 06:09:12'),(4,2,0,'2016-03-11 06:09:12');
/*!40000 ALTER TABLE `mangia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ricetta`
--

DROP TABLE IF EXISTS `ricetta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ricetta` (
  `id_ricetta` int(11) NOT NULL,
  `nome` varchar(200) NOT NULL,
  `foto` varchar(200) DEFAULT NULL,
  `istruzioni` text NOT NULL,
  PRIMARY KEY (`id_ricetta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ricetta`
--

LOCK TABLES `ricetta` WRITE;
/*!40000 ALTER TABLE `ricetta` DISABLE KEYS */;
INSERT INTO `ricetta` VALUES (1,'pasta in bianco',NULL,'Bollire l\'acqua in una pentola, versare 80 grammi di pasta per persona ed aspettare 10 minuti'),(2,'purè',NULL,'Sbucciare 4-5 patate e metterle in una pentola. Aggiungere una noce di burro, latte e passare il tutto con il mixer fino alla consistenza desiderata. Scaldare a fuoco lento');
/*!40000 ALTER TABLE `ricetta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utente` (
  `id_utente` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `cognome` varchar(100) NOT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `dieta` float NOT NULL DEFAULT '2000',
  PRIMARY KEY (`id_utente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (1,'tizio','caio','345678910','tizio@ca.io',2000),(2,'enrico','magnago','345244711','em@dietack.app',2000),(3,'enrico','testori','345164461','et@dietack.app',2000);
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-11 10:48:54
