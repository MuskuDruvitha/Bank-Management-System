-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: atmbank
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `bank`
--

DROP TABLE IF EXISTS `bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank` (
  `pin` varchar(10) DEFAULT NULL,
  `deposit` varchar(15) DEFAULT NULL,
  `withdraw` varchar(15) DEFAULT NULL,
  `balance` varchar(15) DEFAULT NULL,
  `accno` varchar(50) DEFAULT NULL,
  `sno` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank`
--

LOCK TABLES `bank` WRITE;
/*!40000 ALTER TABLE `bank` DISABLE KEYS */;
INSERT INTO `bank` VALUES ('2005','0','0','0','5040936023347101','1'),('2005','15000.0','0','15000.0','5040936023347101','2.0'),('2005','0','10000.0','5000.0','5040936023347101','3.0'),('8590','0','0','0','5040936052211111','1'),('8590','10000.0','0','10000.0','5040936052211111','2.0'),('2005','9500.0','0','14500.0','5040936023347101','4.0'),('2005',NULL,'1000','13500.0','5040936023347101','5.0'),('2005','0','2000.0','11500.0','5040936023347101','6.0'),('2005','0','10000.0','1500.0','5040936023347101','7.0'),('2005','0','1500.0','0.0','5040936023347101','8.0'),('7462','0','0','0','5040935910222152','1'),('7462','1500.0','0','1500.0','5040935910222152','2.0'),('7462',NULL,'1000','500.0','5040935910222152','3.0'),('7462','0','500.0','0.0','5040935910222152','4.0'),('2005','10000.0','0','10000.0','5040936023347101','9.0');
/*!40000 ALTER TABLE `bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login` (
  `accno` varchar(50) NOT NULL,
  `pin` varchar(5) NOT NULL,
  PRIMARY KEY (`accno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('5040935910222152','7462'),('5040936023347101','2005'),('5040936052211111','8590');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `signup1`
--

DROP TABLE IF EXISTS `signup1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `signup1` (
  `formno` varchar(10) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `fname` varchar(30) DEFAULT NULL,
  `date` varchar(5) DEFAULT NULL,
  `month` varchar(10) DEFAULT NULL,
  `year` varchar(5) DEFAULT NULL,
  `gender` varchar(7) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `marital` varchar(10) DEFAULT NULL,
  `address` varchar(40) DEFAULT NULL,
  `city` varchar(40) DEFAULT NULL,
  `pincode` varchar(10) DEFAULT NULL,
  `statel` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`formno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `signup1`
--

LOCK TABLES `signup1` WRITE;
/*!40000 ALTER TABLE `signup1` DISABLE KEYS */;
INSERT INTO `signup1` VALUES ('4186','dry','kdjd','1','January','1990','Female','druv@gmail.com','Unmarried','gjbdja','dbauohd','503224','telms'),('4357','druvitha','ganesh','1','August','2005','Female','23211a05l0@bvrit.ac.in','Unmarried','vaishnavi towers,vidyanagar','armoor','203224','telangana'),('9108','musku druvitha','musku ganesh reddy','1','August','2005','Female','druvithareddy01@gmail.com','Unmarried','vaishnavi towers, vidya nagar colony','armoor','503224','telangana');
/*!40000 ALTER TABLE `signup1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `signup2`
--

DROP TABLE IF EXISTS `signup2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `signup2` (
  `formno` varchar(10) NOT NULL,
  `religion` varchar(15) DEFAULT NULL,
  `category` varchar(15) DEFAULT NULL,
  `income` varchar(5) DEFAULT NULL,
  `education` varchar(15) DEFAULT NULL,
  `occupation` varchar(15) DEFAULT NULL,
  `pan` varchar(15) DEFAULT NULL,
  `aadhar` varchar(20) DEFAULT NULL,
  `senior` varchar(5) DEFAULT NULL,
  `existing` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`formno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `signup2`
--

LOCK TABLES `signup2` WRITE;
/*!40000 ALTER TABLE `signup2` DISABLE KEYS */;
INSERT INTO `signup2` VALUES ('4186','Hindu','General','Null','Non-Graduate','Salaried','7891234506','448434051206','No','No'),('4357','Hindu','General','Null','Non-Graduate','Student','1234567890','448434051206','No','No'),('9108','Hindu','General','Null','Non-Graduate','Student','1234567890','448434051206','No','No');
/*!40000 ALTER TABLE `signup2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `signup3`
--

DROP TABLE IF EXISTS `signup3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `signup3` (
  `formno` varchar(10) NOT NULL,
  `account` varchar(30) DEFAULT NULL,
  `accno` varchar(50) DEFAULT NULL,
  `pin` varchar(5) DEFAULT NULL,
  `service_required` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`formno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `signup3`
--

LOCK TABLES `signup3` WRITE;
/*!40000 ALTER TABLE `signup3` DISABLE KEYS */;
INSERT INTO `signup3` VALUES ('4186','Saving Account','5040935910222152','7462',' ATM Card Mobile Banking'),('4357','Saving Account','5040936052211111','8590',' ATM Card Mobile Banking'),('9108','Saving Account','5040936023347101','2005',' ATM Card Mobile Banking');
/*!40000 ALTER TABLE `signup3` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-13  8:39:37
