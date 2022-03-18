-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: tictactoedb
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `games`
--

DROP TABLE IF EXISTS `games`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `games` (
  `gid` bigint unsigned NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `turn` enum('X','O','') NOT NULL DEFAULT '',
  `cell0` enum('X','O','') NOT NULL DEFAULT '',
  `cell1` enum('X','O','') NOT NULL DEFAULT '',
  `cell2` enum('X','O','') NOT NULL DEFAULT '',
  `cell3` enum('X','O','') NOT NULL DEFAULT '',
  `cell4` enum('X','O','') NOT NULL DEFAULT '',
  `cell5` enum('X','O','') NOT NULL DEFAULT '',
  `cell6` enum('X','O','') NOT NULL DEFAULT '',
  `cell7` enum('X','O','') NOT NULL DEFAULT '',
  `cell8` enum('X','O','') NOT NULL DEFAULT '',
  `player1` bigint unsigned NOT NULL,
  `player2` bigint unsigned NOT NULL,
  PRIMARY KEY (`gid`),
  KEY `games_ibfk_1` (`player1`),
  KEY `games_ibfk_2` (`player2`),
  CONSTRAINT `games_ibfk_1` FOREIGN KEY (`player1`) REFERENCES `players` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `games_ibfk_2` FOREIGN KEY (`player2`) REFERENCES `players` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `games`
--

LOCK TABLES `games` WRITE;
/*!40000 ALTER TABLE `games` DISABLE KEYS */;
/*!40000 ALTER TABLE `games` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `players`
--

DROP TABLE IF EXISTS `players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `players` (
  `pid` bigint unsigned NOT NULL AUTO_INCREMENT,
  `username` longtext NOT NULL,
  `passwd` longtext NOT NULL,
  `email` longtext,
  `status` enum('offline','online','busy','none') NOT NULL DEFAULT 'none',
  `score` bigint unsigned NOT NULL DEFAULT '0',
  `avatar` longblob,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `players`
--

LOCK TABLES `players` WRITE;
/*!40000 ALTER TABLE `players` DISABLE KEYS */;
INSERT INTO `players` VALUES (5,'abdelfattah','b58iSw9lzo7ksc96xTcyrHZLfOTzpbnMcLoPjihdtv4=','null','offline',60,_binary 'null'),(6,'Shehata','b58iSw9lzo7ksc96xTcyrHZLfOTzpbnMcLoPjihdtv4=','null','offline',15,_binary 'null'),(7,'samar','b58iSw9lzo7ksc96xTcyrHZLfOTzpbnMcLoPjihdtv4=','null','offline',25,_binary 'null'),(8,'Marwa','b58iSw9lzo7ksc96xTcyrHZLfOTzpbnMcLoPjihdtv4=','null','offline',20,_binary 'null');
/*!40000 ALTER TABLE `players` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-18  0:39:44
