DROP DATABASE `dietack`;
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
/*!40000 ALTER TABLE `ingredienti_ricetta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mangia`
--

DROP TABLE IF EXISTS `mangia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mangia` (
  `id` int(11) NOT NULL,
  `id_ricetta` int(11) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `mangia_ricetta_id_ricetta_fk` FOREIGN KEY (`id`) REFERENCES `ricetta` (`id_ricetta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mangia`
--

LOCK TABLES `mangia` WRITE;
/*!40000 ALTER TABLE `mangia` DISABLE KEYS */;
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
  PRIMARY KEY (`id_ricetta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ricetta`
--

LOCK TABLES `ricetta` WRITE;
/*!40000 ALTER TABLE `ricetta` DISABLE KEYS */;
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
  PRIMARY KEY (`id_utente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
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

-- Dump completed on 2016-03-10 20:46:06
