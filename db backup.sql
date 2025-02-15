-- MySQL dump 10.13  Distrib 8.0.34, for macos13 (arm64)
--
-- Host: localhost    Database: jb2404
-- ------------------------------------------------------
-- Server version	9.0.1

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `address_id` int NOT NULL,
  `street` varchar(100) DEFAULT NULL,
  `zip_code` int DEFAULT NULL,
  `country` varchar(50) NOT NULL,
  `uid` int DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `uid` (`uid`),
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (10101,'245/325 sector 12 side abc mall',22384,'INDIA',1415),(10102,'2/14 Indra nagar ',22384,'INDIA',1415),(10106,'2/14 gomti nagar ',22384,'INDIA',1414),(10107,'2/14 gomti nagar ',22384,'INDIA',NULL);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` int NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (101,'Trending','This is trending category');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `courseId` int NOT NULL,
  `title` varchar(50) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `category_id` int NOT NULL,
  PRIMARY KEY (`courseId`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `courses_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (10002,'String Boot course','This is first course.',0,101),(10003,'String Boot course- Live','This is first course.',4000,101),(10004,'String Boot course- Live','This is first course.',4000,101);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foodie_restaurant`
--

DROP TABLE IF EXISTS `foodie_restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foodie_restaurant` (
  `id` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `close_time` time(6) DEFAULT NULL,
  `is_open` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `open_time` time(6) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `open` bit(1) DEFAULT NULL,
  `banner` varchar(255) DEFAULT NULL,
  `description` longtext,
  `created_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4rkhhlf5ad3plnhmcebayd1im` (`user_id`),
  CONSTRAINT `FK4rkhhlf5ad3plnhmcebayd1im` FOREIGN KEY (`user_id`) REFERENCES `foodie_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foodie_restaurant`
--

LOCK TABLES `foodie_restaurant` WRITE;
/*!40000 ALTER TABLE `foodie_restaurant` DISABLE KEYS */;
INSERT INTO `foodie_restaurant` VALUES ('04927494-a8e8-4061-b784-5bc3da00b041','Delhi',NULL,NULL,'Varista LKO',NULL,'6c505078-3da1-4c5a-ad42-17b0d322d5a0',_binary '\0','1736959988463.png','This is Varista',NULL),('288c3596-bbee-43fd-8742-486f8a1c2ccb','235/235 Rang Bihar NOIDA','22:24:00.706000',NULL,'Restaurant 12354235235','22:24:00.706000',NULL,_binary '',NULL,'This is dummy restaurant',NULL),('6bb8dfd4-89c8-45e5-828f-4eb7a73bff16','235/235 Rang Bihar NOIDA','22:24:00.706000',NULL,'Restaurant 235325','22:24:00.706000',NULL,_binary '','1738082874740.png','This is dummy restaurant','2025-01-11 22:41:44.592656'),('875c9576-7cd4-4748-bb7c-6fc4a2d74c6d','Lucknow',NULL,NULL,'Haldiram',NULL,'6c505078-3da1-4c5a-ad42-17b0d322d5a0',_binary '',NULL,NULL,NULL),('8fc79540-ba2c-48d9-8c5f-55ffb6fc6265','235/235 Rang Bihar NOIDA','22:24:00.706000',NULL,'Restaurant 23525','22:24:00.706000',NULL,_binary '',NULL,'This is dummy restaurant',NULL),('96ff3c25-3ce3-4b33-97a6-d06cc65f1a5f','235/235 Rang Bihar NOIDA','20:00:00.000000',NULL,'Pizza Hu','10:00:00.000000',NULL,_binary '',NULL,'This is dummy restaurant','2025-01-11 22:49:45.777596'),('9754f413-2f82-4620-87a4-d930fbf228ec','235/235 Rang Bihar NOIDA','22:24:00.706000',NULL,'Restaurant 121','22:24:00.706000',NULL,_binary '','test banner','This is dummy restaurant',NULL),('c6c2e402-c472-4872-9a4c-0a09be13461c','235/235 Rang Bihar NOIDA','22:24:00.706000',NULL,'Restaurant 121','22:24:00.706000',NULL,_binary '',NULL,'This is dummy restaurant',NULL),('cd0cd245-206c-45e6-bd39-002d6f978b94','Lucknow',NULL,NULL,'KFC',NULL,'6c505078-3da1-4c5a-ad42-17b0d322d5a0',_binary '',NULL,NULL,NULL),('e2a38edb-4450-41a6-a2dd-f41a45a9d30c','235/235 Rang Bihar NOIDA','22:24:00.706000',NULL,'Restaurant 12354','22:24:00.706000',NULL,_binary '',NULL,'This is dummy restaurant',NULL);
/*!40000 ALTER TABLE `foodie_restaurant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foodie_users`
--

DROP TABLE IF EXISTS `foodie_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foodie_users` (
  `id` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_available` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','CUSTOMER','DELIVERY_BOY') DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foodie_users`
--

LOCK TABLES `foodie_users` WRITE;
/*!40000 ALTER TABLE `foodie_users` DISABLE KEYS */;
INSERT INTO `foodie_users` VALUES ('1d167e12-b6b9-4a3e-993a-ee06bda8d7f8','25/235 Viram Section 12 Noida INDIA','vivek124@gmail.com',_binary '','Vivek Kumar New User','abc','23152525',NULL,'2025-01-25',_binary '\0'),('29136e05-070b-42e7-9acb-5b0c07116772','25/235 Viram Section 12 Noida INDIA','saurabh@gmail.com',_binary '','Saurabh','abc','23152525',NULL,'2025-01-09',_binary '\0'),('6c505078-3da1-4c5a-ad42-17b0d322d5a0','Test address, DELHI','ankus@gmail.com',_binary '','Ankush Kumar','abc',NULL,'ADMIN',NULL,_binary '\0'),('6efaacb5-bf13-41f0-8c7e-54551b9e92d7','25/235 Viram Section 12 Noida INDIA','pritam@gmail.com',_binary '','Pritam Roy','$2a$10$c0gJfm69ArUndsxoVliM1e9hLLYcmcnMwbIRainUsmqPaK0z7rBKa','23152525',NULL,'2025-01-28',_binary ''),('a16aa6a6-12a0-4121-9ea4-5fcec70095fe','25/235 Viram Section 12 Noida INDIA','sayantan@gmail.com',_binary '','Sayantan g','$2a$10$FzpBOZ57V/5BLqo4x8oZI.DrWQ/DsFffi91.fwmmpFd91uaPv5H/O','23152525',NULL,'2025-01-28',_binary ''),('ad735074-5358-4a8d-b2c6-201cf7972461','Testing address for many to many','vivek@gmail.com',_binary '','Vivek Ashok','$2a$10$FzpBOZ57V/5BLqo4x8oZI.DrWQ/DsFffi91.fwmmpFd91uaPv5H/O',NULL,NULL,NULL,_binary '\0'),('dcfe7c39-8cda-4ea6-800c-27d7f2102258','25/235 Viram Section 12 Noida INDIA','madan@gmail.com',_binary '','Madan Deo','abc','23152525',NULL,'2025-01-09',_binary '\0');
/*!40000 ALTER TABLE `foodie_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jpa_department`
--

DROP TABLE IF EXISTS `jpa_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jpa_department` (
  `department_id` int NOT NULL AUTO_INCREMENT,
  `about` varchar(255) DEFAULT NULL,
  `department_code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jpa_department`
--

LOCK TABLES `jpa_department` WRITE;
/*!40000 ALTER TABLE `jpa_department` DISABLE KEYS */;
/*!40000 ALTER TABLE `jpa_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jpa_laptops`
--

DROP TABLE IF EXISTS `jpa_laptops`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jpa_laptops` (
  `id` int NOT NULL AUTO_INCREMENT,
  `about` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3atyboltb2c0kivdtrf6dc6p` (`user_id`),
  CONSTRAINT `FK3atyboltb2c0kivdtrf6dc6p` FOREIGN KEY (`user_id`) REFERENCES `jpa_user` (`jpa_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jpa_laptops`
--

LOCK TABLES `jpa_laptops` WRITE;
/*!40000 ALTER TABLE `jpa_laptops` DISABLE KEYS */;
INSERT INTO `jpa_laptops` VALUES (41,'This is first laptop of vivek','Macbook Air new new',NULL),(42,'This is second laptop of vivek','Macbook Pro new',NULL),(51,'This is first laptop of vivek','Macbook Air new new',1010),(52,'This is second laptop of vivek','Macbook Pro new',1010),(53,'This is first laptop of vivek','Macbook Air new new',1010),(54,'This is second laptop of vivek','Macbook Pro new',1010),(55,'This is first laptop of vivek','Macbook Air new new',1010),(56,'This is second laptop of vivek','Macbook Pro new',1010),(57,'This is first laptop of vivek','Macbook Air new new',1010),(58,'This is second laptop of vivek','Macbook Pro new',1010),(59,'This is first laptop of vivek','Macbook Air new new',1010),(60,'This is second laptop of vivek','Macbook Pro new',1010);
/*!40000 ALTER TABLE `jpa_laptops` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jpa_user`
--

DROP TABLE IF EXISTS `jpa_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jpa_user` (
  `jpa_user_id` int NOT NULL,
  `age` int NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `jpa_user_name` varchar(255) NOT NULL,
  `type` enum('DIRECTOR','HOD','STUDENT','TEACHER') DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `pin_code` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`jpa_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jpa_user`
--

LOCK TABLES `jpa_user` WRITE;
/*!40000 ALTER TABLE `jpa_user` DISABLE KEYS */;
INSERT INTO `jpa_user` VALUES (0,0,NULL,_binary '\0','Durgesh','STUDENT',NULL,NULL,NULL,NULL,NULL),(1001,0,'duregsh@gmail.com',_binary '\0','Durgesh kumar','STUDENT',NULL,NULL,NULL,NULL,NULL),(1010,30,'vivek@gmail.com',_binary '','Vivek','DIRECTOR',NULL,NULL,NULL,NULL,NULL),(10011,0,'duregsh@gmail.com',_binary '\0','Durgesh Change','STUDENT',NULL,NULL,NULL,'2024-12-21 19:23:14.438893','2024-12-21 20:13:48.537174'),(10101,33,'azaz@gmail.com',_binary '','Azaz Khan','STUDENT','INDIA','220990','2352/23532 V Section12',NULL,NULL),(101014,33,'azaz@gmail.com',_binary '','Azaz Khan','STUDENT','INDIA','220990','2352/23532 V Section12',NULL,NULL);
/*!40000 ALTER TABLE `jpa_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result_viewer_marks`
--

DROP TABLE IF EXISTS `result_viewer_marks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `result_viewer_marks` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `feedback` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `marks` varchar(255) DEFAULT NULL,
  `max_marks` varchar(255) DEFAULT NULL,
  `subject_name` varchar(255) DEFAULT NULL,
  `student_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdbcqft8jbg9dc7oqcqot965w7` (`student_id`),
  CONSTRAINT `FKdbcqft8jbg9dc7oqcqot965w7` FOREIGN KEY (`student_id`) REFERENCES `result_viewer_student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result_viewer_marks`
--

LOCK TABLES `result_viewer_marks` WRITE;
/*!40000 ALTER TABLE `result_viewer_marks` DISABLE KEYS */;
INSERT INTO `result_viewer_marks` VALUES (1,'GOOD','A','70','100','PHY','70b99c4d-f61c-41a2-9171-8cfe9c21e647'),(2,'AVG','B','50','100','CHEM','70b99c4d-f61c-41a2-9171-8cfe9c21e647'),(3,'GOOD','A','70','100','PHY','d1b1079b-b4b7-4bda-af26-54a8c9ef2d78'),(4,'AVG','B','80','100','CHEM','d1b1079b-b4b7-4bda-af26-54a8c9ef2d78'),(5,'GOOD','A','98','100','PHY','4a88ae23-976e-45ab-92d9-924756e5bcf0'),(6,'AVG','B','80','100','CHEM','4a88ae23-976e-45ab-92d9-924756e5bcf0'),(7,'Excellent','A','45','50','MATH','4a88ae23-976e-45ab-92d9-924756e5bcf0');
/*!40000 ALTER TABLE `result_viewer_marks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result_viewer_student`
--

DROP TABLE IF EXISTS `result_viewer_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `result_viewer_student` (
  `id` varchar(255) NOT NULL,
  `address` varchar(1000) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `father_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `photo_name` varchar(255) DEFAULT NULL,
  `roll_number` varchar(255) DEFAULT NULL,
  `school_name` varchar(255) DEFAULT NULL,
  `standard` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result_viewer_student`
--

LOCK TABLES `result_viewer_student` WRITE;
/*!40000 ALTER TABLE `result_viewer_student` DISABLE KEYS */;
INSERT INTO `result_viewer_student` VALUES ('4a88ae23-976e-45ab-92d9-924756e5bcf0','Mumbai','2025-01-14','akshanshsharama@gmail.com','XYZ','male','Akshansh Sharam',NULL,'21343416','MODERN PUBLIC LKO','CLASS 4'),('70b99c4d-f61c-41a2-9171-8cfe9c21e647','LUCKNOW','2025-01-13','durgeshtest@gmail.com','XYZ','male','Durgesh Tiwari',NULL,'21343414','MODERN PUBLIC LKO','CLASS 5'),('d1b1079b-b4b7-4bda-af26-54a8c9ef2d78','DELHI','2025-01-15','roshan@gmail.com','XYZ','male','Roshan Singh',NULL,'21343415','MODERN PUBLIC LKO','CLASS 5'),('dea81976-dfa6-4364-b261-f2a6d9f9faf9','354/2135 Section 19 NOIDA','2025-01-02','akashranjan@gmail.com','M. D. Ranjan','male','Akash Ranjan',NULL,'ROLL123','MODERN PUBLIC SCHOOL DELHI','CLASS 6');
/*!40000 ALTER TABLE `result_viewer_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_entity`
--

DROP TABLE IF EXISTS `role_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_entity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_entity`
--

LOCK TABLES `role_entity` WRITE;
/*!40000 ALTER TABLE `role_entity` DISABLE KEYS */;
INSERT INTO `role_entity` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_GUEST');
/*!40000 ALTER TABLE `role_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` varchar(255) NOT NULL,
  `role_id` int NOT NULL,
  KEY `FK66rggwh2ht8u9ig7hvam1jvai` (`role_id`),
  KEY `FK17oeem2d9eevunshka7b09fjn` (`user_id`),
  CONSTRAINT `FK17oeem2d9eevunshka7b09fjn` FOREIGN KEY (`user_id`) REFERENCES `foodie_users` (`id`),
  CONSTRAINT `FK66rggwh2ht8u9ig7hvam1jvai` FOREIGN KEY (`role_id`) REFERENCES `role_entity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES ('ad735074-5358-4a8d-b2c6-201cf7972461',1),('ad735074-5358-4a8d-b2c6-201cf7972461',2),('6efaacb5-bf13-41f0-8c7e-54551b9e92d7',2),('a16aa6a6-12a0-4121-9ea4-5fcec70095fe',2),('a16aa6a6-12a0-4121-9ea4-5fcec70095fe',1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `city` varchar(50) NOT NULL,
  `salary` int DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (555,'Ankit','LKO',2525),(1100,'Pankaj','Lucknow',34000),(1101,'Vivek Ashok','Delhi',50000),(1102,'Ankit','Kanpur',50000),(1414,'Abhinav','Lucknow',252555),(1415,'Abhishek','Delhi',40000);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-15 20:43:19
