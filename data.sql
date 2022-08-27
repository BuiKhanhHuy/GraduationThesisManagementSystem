-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: graduation_thesis_management_sys_db
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `council`
--

DROP TABLE IF EXISTS `council`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `council` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_block` bit(1) DEFAULT NULL,
  `school_year_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_council_school_year_idx` (`school_year_id`),
  CONSTRAINT `fk_council_school_year` FOREIGN KEY (`school_year_id`) REFERENCES `school_year` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `council`
--

LOCK TABLES `council` WRITE;
/*!40000 ALTER TABLE `council` DISABLE KEYS */;
INSERT INTO `council` VALUES (41,'Hội đồng 2','Mô tả thông tin hội đồng 2',_binary '',1),(44,'Hội đồng 3','',_binary '\0',14),(46,'Hội đông mới','Mô tả hội đồng mới',_binary '\0',1),(47,'MAHDDACBIET','',_binary '\0',12),(48,'HOIDONGNUA','HOIDONGNUA',_binary '',1),(49,'HỘI ĐỒNG 01','',_binary '',1),(50,'HỘI ĐỒNG 02','',_binary '',1),(51,'HOIDONG03','',_binary '\0',12),(52,'HỘI ĐỒNG 04','',_binary '',1),(53,'Hội đồng 10','ádfsdf',_binary '',1),(54,'HOIDONG11','',_binary '',1);
/*!40000 ALTER TABLE `council` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `council_detail`
--

DROP TABLE IF EXISTS `council_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `council_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `position` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `council_id` int(11) DEFAULT NULL,
  `lecturer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_council_detail_council_idx` (`council_id`),
  KEY `fk_council_detail_lecturer_idx` (`lecturer_id`),
  CONSTRAINT `fk_council_detail_council` FOREIGN KEY (`council_id`) REFERENCES `council` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_council_detail_lecturer` FOREIGN KEY (`lecturer_id`) REFERENCES `lecturer` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=175 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `council_detail`
--

LOCK TABLES `council_detail` WRITE;
/*!40000 ALTER TABLE `council_detail` DISABLE KEYS */;
INSERT INTO `council_detail` VALUES (122,'Chủ tịch',41,1),(123,'Thư ký',41,2),(124,'Phản biện',41,3),(125,'Thành viên',41,4),(136,'Chủ tịch',44,1),(137,'Thư ký',44,2),(138,'Phản biện',44,3),(139,'Thành viên',44,4),(144,'Chủ tịch',46,1),(145,'Thư ký',46,12),(146,'Phản biện',46,3),(147,'Chủ tịch',47,13),(148,'Thư ký',47,14),(149,'Phản biện',47,15),(150,'Thành viên',47,17),(151,'Thành viên',47,18),(152,'Chủ tịch',48,13),(153,'Thư ký',48,14),(154,'Phản biện',48,15),(155,'Chủ tịch',49,13),(156,'Thư ký',49,14),(157,'Phản biện',49,15),(158,'Thành viên',49,17),(159,'Thành viên',49,18),(160,'Chủ tịch',50,13),(161,'Thư ký',50,14),(162,'Phản biện',50,15),(163,'Chủ tịch',51,13),(164,'Thư ký',51,14),(165,'Phản biện',51,15),(166,'Chủ tịch',52,13),(167,'Thư ký',52,14),(168,'Phản biện',52,15),(169,'Chủ tịch',53,13),(170,'Thư ký',53,14),(171,'Phản biện',53,15),(172,'Chủ tịch',54,13),(173,'Thư ký',54,14),(174,'Phản biện',54,15);
/*!40000 ALTER TABLE `council_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `founding` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (99,'KHOAXD','Xây dựngzz','Thông tin khoa xây dựng','1990-09-01'),(100,'QTKD','Quản trị kinh doanh','','1990-06-05'),(101,'DTDB','Đào tạo đặc biệt','','1990-01-01'),(104,'KHOACNTT','Công nghệ thông tin','','1990-01-01'),(105,'TCNH','Tài chính Ngân hàng','mô tả khoa tài chính ngân hàng','1989-12-30'),(107,'MK999999','Khoa công nghệ sinh học','Mô tả khoa công nghệ sinh học','1990-01-01'),(108,'MKDEMO','Khoa demo','Mô tả','1989-12-31'),(109,'ádf','sdf','ádfsdfdsf','1989-12-31');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluation_method`
--

DROP TABLE IF EXISTS `evaluation_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `evaluation_method` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluation_method`
--

LOCK TABLES `evaluation_method` WRITE;
/*!40000 ALTER TABLE `evaluation_method` DISABLE KEYS */;
INSERT INTO `evaluation_method` VALUES (1,'Phương pháp đánh giá điểm khóa luận tốt nghiệp 2019-2020',_binary '\0'),(9,'Phương pháp đánh giá điểm khóa luận tốt nghiệp 2025-2050',_binary '');
/*!40000 ALTER TABLE `evaluation_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guide`
--

DROP TABLE IF EXISTS `guide`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `guide` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `thesis_id` int(11) NOT NULL,
  `lecturer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`thesis_id`),
  KEY `fk_guide_thesis_idx` (`thesis_id`),
  KEY `fk_guide_lecturer_idx` (`lecturer_id`),
  CONSTRAINT `fk_guide_lecturer` FOREIGN KEY (`lecturer_id`) REFERENCES `lecturer` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_guide_thesis` FOREIGN KEY (`thesis_id`) REFERENCES `thesis` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guide`
--

LOCK TABLES `guide` WRITE;
/*!40000 ALTER TABLE `guide` DISABLE KEYS */;
INSERT INTO `guide` VALUES (47,37,1),(48,38,1),(49,38,2),(50,39,2),(66,51,2),(68,52,2),(69,53,2),(71,55,2),(51,39,3),(67,51,3),(70,54,3),(72,55,3);
/*!40000 ALTER TABLE `guide` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecturer`
--

DROP TABLE IF EXISTS `lecturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `lecturer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `full_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `birthday` date NOT NULL,
  `gender` int(11) NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `position_id` int(11) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `phone_UNIQUE` (`phone`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  KEY `fk_lecturer_position_idx` (`position_id`),
  KEY `fk_lecturer_department_idx` (`department_id`),
  CONSTRAINT `fk_lecturer_department` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_lecturer_position` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_lecturer_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecturer`
--

LOCK TABLES `lecturer` WRITE;
/*!40000 ALTER TABLE `lecturer` DISABLE KEYS */;
INSERT INTO `lecturer` VALUES (1,'0000000000','Trần Thị A','khuy0@gmail.com','0965665321','2022-07-15',2,'1242 QL1A, Tân Tạo A, Bình Tân, TP. HCM',5,7,104),(2,'2222222222','Trần Văn D','bbbbbbbb@gmail.com','0356056359','1980-01-31',1,'Tp. HCM',17,4,99),(3,'3333333333','Trần Ngọc Giàu','ngocgiay@gmail.com','0965465789','2022-08-03',1,'Tp. HCM',21,4,100),(4,'4444444444','Bùi Khánh Huy','1951050027huy@ou.edu.vn','0865466598','2001-02-22',1,'Bình Tân, TP. HCM',22,4,104),(8,'5555555555','Dương Ngọc Thái','ngocthai@gmail.com','0935464568','2022-07-26',1,'Bình Tân, TP. HCM',26,4,104),(10,'6666666666','Ten demo','demo@gmail.com','0955553214','2022-08-09',1,'An qui',37,4,104),(11,'7777777777','Ten demo hahahaa','demo1@gmail.com','0965467777','2022-07-26',1,'An qui',38,4,101),(12,'GV00001111','Giang vien demo','giangviendemo@gmail.com','0946546897','2022-08-13',1,'An qui',50,4,104),(13,'A','A','khuy220@gmail.com','0911111111','2022-08-14',1,'An qui',51,4,104),(14,'B','B','b@gmail.com','0922222222','2022-08-15',1,'An qui',52,4,104),(15,'C','C','c@gmail.com','0933333333','2022-08-15',1,'An qui',53,4,104),(17,'D','D','d@gmail.com','0944444444','2022-08-15',1,'An qui',55,4,104),(18,'E','E','e@gmail.com','0955555555','2022-08-13',1,'An qui',56,4,104),(19,'1000000001','Nguyễn Thị A','thiaaaaaaaaaaaaa@gmail.com','0236585495','2022-08-21',1,'Địa chỉ của bạn',75,4,99),(22,'sdfsdf','sfsdfsdfsdfsdf','sdfsfsfdf@gmail.com','0547894458','2022-08-26',1,'An qui',78,4,104),(24,'0000000011','Nguyễn Thị B','aaaaa@gmail.com','0236585496','2022-08-27',2,'Địa chỉ của bạn',80,4,99),(25,'0000000012','Nguyễn Thị C','bbbbb@gmail.com','0236585497','2022-08-27',2,'Địa chỉ của bạn',81,4,99),(26,'0000000013','Nguyễn Thị D','cccccc@gmail.com','0236585498','2022-08-27',2,'Địa chỉ của bạn',82,4,99),(27,'0000000014','Nguyễn Thị E','dddddd@gmail.com','0236585499','2022-08-27',2,'Địa chỉ của bạn',83,4,99),(28,'0000000015','Nguyễn Thị F','ffffffffff@gmail.com','0236585500','2022-08-27',2,'Địa chỉ của bạn',84,4,99),(29,'0000000016','Trần Văn G','gggggggggg@gmail.com','0236585501','2022-08-27',1,'Địa chỉ của bạn',85,4,99),(30,'0000000017','Trần Văn H','hhhhhhhhhhhh@gmail.com','0236585502','2022-08-27',2,'Địa chỉ của bạn',86,4,99),(31,'0000000018','Trần Văn I','yyyyyyyyyy@gmail.com','0236585503','2022-08-27',3,'Địa chỉ của bạn',87,4,99),(32,'0000000019','Trần Văn J','jjjjjjjjjjjjj@gmail.com','0236585504','2022-08-27',4,'Địa chỉ của bạn',88,4,99),(33,'0000000020','Trần Văn K','kkkkkkkkk@gmail.com','0236585505','2022-08-27',5,'Địa chỉ của bạn',89,4,99);
/*!40000 ALTER TABLE `lecturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `major`
--

DROP TABLE IF EXISTS `major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `major` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `fk_major_department_idx` (`department_id`),
  CONSTRAINT `fk_major_department` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major`
--

LOCK TABLES `major` WRITE;
/*!40000 ALTER TABLE `major` DISABLE KEYS */;
INSERT INTO `major` VALUES (6,'NGANHCNTT','Công nghệ thông tin','Mô tả ngành công nghệ thông tin',104),(7,'HTTTQL','Hệ thống thông tin quản lý','Mô tả ngành hệ thống thông tin quản lý',104),(8,'KHMT','Khoa học máy tính','',104),(9,'KHDL','Khoa học dữ liệu','',104),(11,'NAMA','Nam Á Học','',101);
/*!40000 ALTER TABLE `major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manage`
--

DROP TABLE IF EXISTS `manage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `manage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `phone_UNIQUE` (`phone`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  CONSTRAINT `fk_manage_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manage`
--

LOCK TABLES `manage` WRITE;
/*!40000 ALTER TABLE `manage` DISABLE KEYS */;
INSERT INTO `manage` VALUES (3,'Kent Dodds','kent@gmail.com','0922233333',3),(4,'Cindy Baker','cindy@gmail.com','0956265456',4),(16,'Bùi Khánh Huy','khuy220@gmail.com','0888425094',15),(19,'Trần Thị Ngọc Hoa','admin4@gmail.com','0856465358',19),(20,'Trần Thị Thu Hiền','admin3@gmail.com','0965465568',20),(22,'Bùi Khánh Huy','buikhanhuy@gmail.com','0888425594',34),(25,'sdfsdf','sdfsfsfsdfsd@gmail.com','0961321234',59);
/*!40000 ALTER TABLE `manage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `created_date` datetime(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_news_user_idx` (`user_id`),
  CONSTRAINT `fk_news_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (24,'THÔNG BÁO: KẾ HOẠCH THỰC TẬP TỐT NGHIỆP HỌC KỲ 1 NĂM HỌC 2022 - 2023','<b>Cụ thể, tại lĩnh vực Kinh tế học, kinh tế lượng và tài chính, Trường Đại học Mở Thành phố Hồ Chí Minh đứng thứ hai trong số 13 trường ĐH Việt Nam và 288 trong tổng số 1.854 trường đại học trên thế giới, theo kết quả xếp hạng các trường đại học, viện nghiên cứu trên thế giới năm 2022 của tổ chức xếp hạng các cơ sở nghiên cứu khoa học uy tín Tây Ban Nha – SCImago vừa công bố.<br><br></b><span>Việc Trường Đại học Mở Thành phố Hồ Chí Minh được công nhận vào top đầu các trường đại học Việt Nam ở lĩnh vực Kinh tế tại một bảng xếp hạng có uy tín quốc tế là động lực để tập thể Ban giám hiệu, cán bộ, viên chức, giảng viên, sinh viên Trường tiếp tục phấn đấu, nỗ lực không ngừng nâng cao chất lượng&nbsp;đào tạo và nghiên cứu khoa học.<br><br></span>SCImago Institutions Rankings&nbsp;(SCImago) ra đời từ năm 2009, là bảng xếp hạng uy tín, được các tổ chức như Bộ Khoa học và Công nghệ VN, Quỹ Nafosted sử dụng làm một trong các cơ sở để tham khảo cho quá trình đánh giá tổ chức khoa học công nghệ tại Việt Nam. Hằng năm, SCImago công bố kết quả xếp hạng một lần, dựa trên 3 tiêu chí:<br><span>–<b>&nbsp;Năng lực nghiên cứu&nbsp;(50%)</b>: thực hiện dựa vào khối lượng, tác động và chất lượng của nghiên cứu từ cơ sở giáo dục.<br></span><span>–&nbsp;<b>Sáng tạo và đổi mới&nbsp;(30%)</b>: được tính toán dựa trên số lượng đơn đăng ký bằng sáng chế của cơ sở giáo dục và các trích dẫn mà kết quả nghiên cứu của cơ sở giáo dục đó nhận được từ các bằng sáng chế.<br></span><span>–&nbsp;<b>Tác động xã hội&nbsp;(20%):</b> dựa vào số trang của website thuộc cơ sở giáo dục và số lượng liên kết ngược và thảo luận trên các mạng xã hội./.</span><b><br><br></b>',3,'2022-08-25 18:07:44.1'),(25,'ádf','dsf',3,'2022-08-25 18:12:36.8'),(26,'sdf','adsf',34,'2022-08-25 18:16:03.4');
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_date` datetime(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (76,'Thông báo demo','demo thôi','2022-08-24 00:38:16.8'),(77,'Thông báo thực hiện khóa luận tốt nghiệp','Thông báo V/v triển khai thực hiện Khóa luận tốt nghiệp ngành Quản trị kinh doanh khóa học 2019 năm học 2019-2020','2022-08-24 00:39:34.4'),(78,'Thông báo thực hiện khóa luận tốt nghiệp','Thông báo thực hiện khóa luận từ ngày 2022-08-25 00:00:00.0 đến ngày 2022-08-26 00:00:00.0. Thông tin chi tiết trên hệ thống.','2022-08-24 01:56:23.7'),(79,'Thông báo giảng viên hướng dẫn khóa luận','Đăng nhập vào hệ thống để xem thông tin chi tiết','2022-08-24 01:56:23.7'),(80,'Thông báo giảng viên phản biện khóa luận tốt nghiệp','Đăng nhập vào hệ thống để xem thông tin chi tiết','2022-08-24 01:56:23.8'),(81,'Thông báo thực hiện khóa luận tốt nghiệp','Thông báo thực hiện khóa luận từ ngày 2022-08-26 00:00:00.0 đến ngày 2022-08-27 00:00:00.0. Thông tin chi tiết trên hệ thống.','2022-08-24 02:54:42.7'),(82,'Thông báo giảng viên hướng dẫn khóa luận','Đăng nhập vào hệ thống để xem thông tin chi tiết','2022-08-24 02:54:42.7'),(83,'Thông báo giảng viên phản biện khóa luận tốt nghiệp','Đăng nhập vào hệ thống để xem thông tin chi tiết','2022-08-24 02:54:42.7'),(84,'Thông báo thực hiện khóa luận tốt nghiệp','Thông báo thực hiện khóa luận từ ngày 2022-08-27 00:00:00.0 đến ngày 2022-08-28 00:00:00.0. Thông tin chi tiết trên hệ thống.','2022-08-26 17:48:48.5'),(85,'Thông báo giảng viên hướng dẫn khóa luận','Đăng nhập vào hệ thống để xem thông tin chi tiết','2022-08-26 17:48:48.6'),(86,'Thông báo giảng viên phản biện khóa luận tốt nghiệp','Đăng nhập vào hệ thống để xem thông tin chi tiết','2022-08-26 17:48:48.6'),(87,'Nộp khóa luận','ádfsdf','2022-08-26 17:56:37.2'),(88,'Gnhir hcojc','ádfsdf','2022-08-27 01:50:20.7'),(89,'Thông báo thực hiện khóa luận tốt nghiệp','Thông báo thực hiện khóa luận từ ngày 2022-08-29 00:00:00.0 đến ngày 2022-08-30 00:00:00.0. Thông tin chi tiết trên hệ thống.','2022-08-27 01:54:09.3'),(90,'Thông báo giảng viên hướng dẫn khóa luận','Đăng nhập vào hệ thống để xem thông tin chi tiết','2022-08-27 01:54:09.4'),(91,'Thông báo giảng viên phản biện khóa luận tốt nghiệp','Đăng nhập vào hệ thống để xem thông tin chi tiết','2022-08-27 01:54:09.5');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification_user`
--

DROP TABLE IF EXISTS `notification_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `notification_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) DEFAULT b'1',
  `notification_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_notification_user_user_idx` (`user_id`),
  KEY `fk_notification_user_notification_idx` (`notification_id`),
  CONSTRAINT `fk_notification_user_notification` FOREIGN KEY (`notification_id`) REFERENCES `notification` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_notification_user_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=932 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification_user`
--

LOCK TABLES `notification_user` WRITE;
/*!40000 ALTER TABLE `notification_user` DISABLE KEYS */;
INSERT INTO `notification_user` VALUES (765,_binary '',76,3),(766,_binary '',76,4),(767,_binary '',76,5),(768,_binary '\0',76,6),(769,_binary '',76,13),(770,_binary '',76,14),(771,_binary '',76,15),(772,_binary '\0',76,17),(773,_binary '',76,19),(774,_binary '',76,20),(775,_binary '',76,21),(776,_binary '',76,22),(777,_binary '',76,26),(778,_binary '',76,30),(779,_binary '',76,31),(780,_binary '',76,32),(781,_binary '\0',76,34),(782,_binary '',76,37),(783,_binary '',76,38),(784,_binary '',76,40),(785,_binary '',76,45),(786,_binary '',76,48),(787,_binary '',76,49),(788,_binary '',76,50),(789,_binary '\0',76,51),(790,_binary '',76,52),(791,_binary '',76,53),(792,_binary '',76,55),(793,_binary '',76,56),(794,_binary '',76,57),(795,_binary '',76,58),(796,_binary '',76,59),(797,_binary '',76,60),(798,_binary '',76,61),(799,_binary '',76,62),(800,_binary '',77,3),(801,_binary '',77,4),(802,_binary '',77,5),(803,_binary '\0',77,6),(804,_binary '',77,13),(805,_binary '',77,14),(806,_binary '',77,15),(807,_binary '\0',77,17),(808,_binary '',77,19),(809,_binary '',77,20),(810,_binary '',77,21),(811,_binary '',77,22),(812,_binary '',77,26),(813,_binary '',77,30),(814,_binary '',77,31),(815,_binary '',77,32),(816,_binary '\0',77,34),(817,_binary '',77,37),(818,_binary '',77,38),(819,_binary '',77,40),(820,_binary '',77,45),(821,_binary '',77,48),(822,_binary '',77,49),(823,_binary '',77,50),(824,_binary '\0',77,51),(825,_binary '',77,52),(826,_binary '',77,53),(827,_binary '',77,55),(828,_binary '',77,56),(829,_binary '',77,57),(830,_binary '',77,58),(831,_binary '',77,59),(832,_binary '',77,60),(833,_binary '',77,61),(834,_binary '',77,62),(835,_binary '',78,48),(836,_binary '\0',79,17),(837,_binary '',80,5),(838,_binary '',81,61),(839,_binary '\0',82,17),(840,_binary '',83,5),(841,_binary '',84,71),(842,_binary '',85,21),(843,_binary '',86,5),(844,_binary '',87,3),(845,_binary '',87,4),(846,_binary '',87,68),(847,_binary '',87,5),(848,_binary '\0',87,6),(849,_binary '',87,70),(850,_binary '',87,71),(851,_binary '',87,72),(852,_binary '',87,73),(853,_binary '',87,75),(854,_binary '',87,13),(855,_binary '',87,14),(856,_binary '',87,15),(857,_binary '',87,17),(858,_binary '',87,19),(859,_binary '',87,20),(860,_binary '',87,21),(861,_binary '',87,22),(862,_binary '',87,26),(863,_binary '',87,30),(864,_binary '',87,31),(865,_binary '',87,32),(866,_binary '\0',87,34),(867,_binary '',87,37),(868,_binary '',87,38),(869,_binary '',87,40),(870,_binary '',87,45),(871,_binary '',87,48),(872,_binary '',87,49),(873,_binary '',87,50),(874,_binary '',87,51),(875,_binary '',87,52),(876,_binary '',87,53),(877,_binary '',87,55),(878,_binary '',87,56),(879,_binary '',87,57),(880,_binary '',87,58),(881,_binary '',87,59),(882,_binary '',87,60),(883,_binary '',87,61),(884,_binary '',87,62),(885,_binary '',88,3),(886,_binary '',88,4),(887,_binary '',88,68),(888,_binary '',88,5),(889,_binary '\0',88,6),(890,_binary '',88,70),(891,_binary '',88,71),(892,_binary '',88,72),(893,_binary '',88,73),(894,_binary '',88,75),(895,_binary '',88,13),(896,_binary '',88,78),(897,_binary '',88,14),(898,_binary '',88,15),(899,_binary '',88,17),(900,_binary '',88,19),(901,_binary '',88,20),(902,_binary '',88,21),(903,_binary '',88,22),(904,_binary '',88,26),(905,_binary '',88,30),(906,_binary '',88,31),(907,_binary '',88,32),(908,_binary '\0',88,34),(909,_binary '',88,37),(910,_binary '',88,38),(911,_binary '',88,40),(912,_binary '',88,45),(913,_binary '',88,48),(914,_binary '',88,49),(915,_binary '',88,50),(916,_binary '',88,51),(917,_binary '',88,52),(918,_binary '',88,53),(919,_binary '',88,55),(920,_binary '',88,56),(921,_binary '',88,57),(922,_binary '',88,58),(923,_binary '',88,59),(924,_binary '',88,60),(925,_binary '',88,61),(926,_binary '',88,62),(927,_binary '',89,70),(928,_binary '',89,14),(929,_binary '',90,17),(930,_binary '',90,21),(931,_binary '',91,5);
/*!40000 ALTER TABLE `notification_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perform`
--

DROP TABLE IF EXISTS `perform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `perform` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `thesis_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_perform_thesis_idx` (`thesis_id`),
  KEY `fk_perform_student_idx` (`student_id`),
  CONSTRAINT `fk_perform_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_perform_thesis` FOREIGN KEY (`thesis_id`) REFERENCES `thesis` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perform`
--

LOCK TABLES `perform` WRITE;
/*!40000 ALTER TABLE `perform` DISABLE KEYS */;
INSERT INTO `perform` VALUES (44,1,37),(45,20,38),(46,18,39),(47,19,39),(60,22,51),(61,16,52),(62,21,53),(63,25,54),(64,2,55),(65,24,55);
/*!40000 ALTER TABLE `perform` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (4,'Giảng viên','Mô tả chức vụ giảng viên'),(7,'Phó khoa','Giảng viên dưới quyền của trưởng khoa'),(8,'Trưởng khoa','Mô tả nè\r\n');
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN','Quản trị viên'),(2,'MINISTRY','Giáo vụ'),(3,'LECTURER','Giảng viên'),(4,'STUDENT','Sinh viên');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `school_year`
--

DROP TABLE IF EXISTS `school_year`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `school_year` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `school_year`
--

LOCK TABLES `school_year` WRITE;
/*!40000 ALTER TABLE `school_year` DISABLE KEYS */;
INSERT INTO `school_year` VALUES (1,'2019-2020','2019-09-06','2020-09-06'),(12,'2020-2021','2020-09-08','2021-09-08'),(14,'2021-2022','2021-09-07','2022-09-07');
/*!40000 ALTER TABLE `school_year` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `thesis_id` int(11) DEFAULT NULL,
  `council_detail_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mark_thesis_idx` (`thesis_id`),
  KEY `fk_mark_council_detail_idx` (`council_detail_id`),
  CONSTRAINT `fk_mark_council_detail` FOREIGN KEY (`council_detail_id`) REFERENCES `council_detail` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_mark_thesis` FOREIGN KEY (`thesis_id`) REFERENCES `thesis` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` VALUES (1,NULL,NULL),(2,NULL,122),(3,NULL,122),(4,NULL,122),(6,NULL,136),(7,NULL,145),(8,NULL,145),(9,NULL,145),(10,NULL,147),(11,NULL,148),(12,NULL,149),(13,NULL,150),(14,NULL,151),(15,NULL,152),(16,NULL,153),(18,NULL,146),(19,NULL,146),(20,NULL,146),(21,NULL,146),(22,NULL,146),(23,NULL,154),(24,38,155),(25,38,156),(26,38,157),(27,38,158),(28,38,159),(29,39,155),(30,39,156),(31,39,157),(32,39,158),(33,39,159),(34,52,160),(35,52,161),(36,52,162),(37,53,163),(38,53,164),(39,53,165),(40,37,166),(41,37,167),(42,37,168),(43,54,169),(44,54,170),(45,54,171),(46,55,172),(47,55,173),(48,55,174);
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score_column`
--

DROP TABLE IF EXISTS `score_column`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `score_column` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `weight` double DEFAULT '0',
  `score_component_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_score_column_score_component_idx` (`score_component_id`),
  CONSTRAINT `fk_score_column_score_component` FOREIGN KEY (`score_component_id`) REFERENCES `score_component` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score_column`
--

LOCK TABLES `score_column` WRITE;
/*!40000 ALTER TABLE `score_column` DISABLE KEYS */;
INSERT INTO `score_column` VALUES (1,'Trình bày khóa luận',0.2,1),(2,'Nội dung khóa luận',0.2,1),(3,'Thuyết trình tốt',0.2,2),(4,'Thuyết trình hay',0.2,2),(5,'Trả lời đúng',0.2,3),(29,'Trình bày tốt (slide báo cáo tốt , trình bày rõ ràng, đúng thời gian quy định)',0.15,16),(30,'Nội dung thực hiện đề tài đạt yêu cầu đặt ra, có tính khoa học.',0.25,16),(31,'Phương pháp nghiên cứu thực hiện tốt, kết quả nghiên cứu được trình bày tốt.',0.2,16),(32,'Kết quả đề tài đóng góp về lý luận, hoặc thực tiễn; mô hình/prototype hoạt động tốt',0.1,16),(33,'Đề tài mới, hoặc phương pháp thực hiện có tính sáng tạo.',0.1,16),(34,'Hiểu đúng các câu hỏi, trả lời tập trung vào vấn đề đặt ra, không lạc đề.',0.1,17),(35,'Trả lời đúng các câu hỏi, thể hiện có kiến thức tốt.',0.1,17);
/*!40000 ALTER TABLE `score_column` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score_component`
--

DROP TABLE IF EXISTS `score_component`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `score_component` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `evaluation_method_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_score_component_evaluation_method_idx` (`evaluation_method_id`),
  CONSTRAINT `fk_score_component_evaluation_method` FOREIGN KEY (`evaluation_method_id`) REFERENCES `evaluation_method` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score_component`
--

LOCK TABLES `score_component` WRITE;
/*!40000 ALTER TABLE `score_component` DISABLE KEYS */;
INSERT INTO `score_component` VALUES (1,'Khóa luận',1),(2,'Thuyết trình',1),(3,'Trả lời câu hỏi',1),(16,'Phần báo cáo',9),(17,'Phần trả lời câu hỏi ',9);
/*!40000 ALTER TABLE `score_component` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score_detail`
--

DROP TABLE IF EXISTS `score_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `score_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `score` double NOT NULL DEFAULT '0',
  `score_id` int(11) DEFAULT NULL,
  `score_column_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_score_detail_mark_idx` (`score_id`),
  KEY `fk_score_detail_score_column_idx` (`score_column_id`),
  CONSTRAINT `fk_score_detail_score` FOREIGN KEY (`score_id`) REFERENCES `score` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_score_detail_score_column` FOREIGN KEY (`score_column_id`) REFERENCES `score_column` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=259 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score_detail`
--

LOCK TABLES `score_detail` WRITE;
/*!40000 ALTER TABLE `score_detail` DISABLE KEYS */;
INSERT INTO `score_detail` VALUES (1,10,1,1),(2,10,1,2),(3,10,1,3),(4,10,1,4),(5,10,1,5),(6,9,2,1),(7,9,2,2),(8,9,2,3),(9,9,2,4),(10,9,2,5),(11,8,3,1),(12,8,3,2),(13,8,3,3),(14,8,3,4),(15,8,3,5),(16,5,4,1),(17,5,4,2),(18,5,4,3),(19,5,4,4),(20,5,4,5),(26,6.5,6,1),(27,6.5,6,2),(28,6.5,6,3),(29,6.5,6,4),(30,6.5,6,5),(31,10,7,1),(32,10,7,2),(33,10,7,3),(34,10,7,4),(35,10,7,5),(36,9,8,1),(37,9,8,2),(38,9,8,3),(39,9,8,4),(40,9,8,5),(41,10,9,1),(42,10,9,2),(43,10,9,3),(44,10,9,4),(45,10,9,5),(46,10,10,1),(47,10,10,2),(48,10,10,3),(49,10,10,4),(50,10,10,5),(51,9,11,1),(52,9,11,2),(53,9,11,3),(54,9,11,4),(55,9,11,5),(56,8,12,1),(57,8,12,2),(58,8,12,3),(59,8,12,4),(60,8,12,5),(61,7,13,1),(62,7,13,2),(63,7,13,3),(64,7,13,4),(65,7,13,5),(66,6,14,1),(67,6,14,2),(68,6,14,3),(69,6,14,4),(70,6,14,5),(71,10,15,1),(72,10,15,2),(73,10,15,3),(74,10,15,4),(75,10,15,5),(76,9,16,1),(77,9,16,2),(78,9,16,3),(79,9,16,4),(80,9,16,5),(86,10,18,1),(87,10,18,2),(88,10,18,3),(89,10,18,4),(90,10,18,5),(91,9,19,1),(92,9,19,2),(93,10,19,3),(94,9,19,4),(95,9,19,5),(96,8,20,1),(97,8,20,2),(98,8,20,3),(99,8,20,4),(100,8,20,5),(101,10,21,1),(102,10,21,2),(103,10,21,3),(104,10,21,4),(105,10,21,5),(106,7,22,1),(107,7,22,2),(108,7,22,3),(109,7,22,4),(110,7,22,5),(111,10,23,1),(112,10,23,2),(113,10,23,3),(114,10,23,4),(115,8,23,5),(116,5,24,1),(117,5,24,2),(118,5,24,3),(119,5,24,4),(120,5,24,5),(121,10,25,1),(122,10,25,2),(123,10,25,3),(124,10,25,4),(125,10,25,5),(126,10,26,1),(127,10,26,2),(128,10,26,3),(129,10,26,4),(130,10,26,5),(131,10,27,1),(132,10,27,2),(133,10,27,3),(134,10,27,4),(135,10,27,5),(136,10,28,1),(137,10,28,2),(138,10,28,3),(139,10,28,4),(140,10,28,5),(141,9,29,1),(142,9,29,2),(143,9,29,3),(144,9,29,4),(145,9,29,5),(146,9,30,1),(147,9,30,2),(148,9,30,3),(149,9,30,4),(150,9,30,5),(151,9,31,1),(152,9,31,2),(153,9,31,3),(154,9,31,4),(155,9,31,5),(156,9,32,1),(157,9,32,2),(158,9,32,3),(159,9,32,4),(160,9,32,5),(161,9,33,1),(162,9,33,2),(163,9,33,3),(164,9,33,4),(165,9,33,5),(166,10,34,1),(167,10,34,2),(168,10,34,3),(169,10,34,4),(170,10,34,5),(171,9,35,1),(172,9,35,2),(173,9,35,3),(174,9,35,4),(175,9,35,5),(176,7,36,1),(177,7,36,2),(178,7,36,3),(179,7,36,4),(180,7,36,5),(181,10,37,1),(182,10,37,2),(183,10,37,3),(184,10,37,4),(185,10,37,5),(186,8,38,1),(187,8,38,2),(188,8,38,3),(189,8,38,4),(190,8,38,5),(191,9,39,1),(192,9,39,2),(193,9,39,3),(194,9,39,4),(195,9,39,5),(196,10,40,29),(197,10,40,30),(198,10,40,31),(199,10,40,32),(200,10,40,33),(201,10,40,34),(202,10,40,35),(203,9,41,29),(204,9,41,30),(205,9,41,31),(206,9,41,32),(207,9,41,33),(208,9,41,34),(209,9,41,35),(210,8,42,29),(211,8,42,30),(212,8,42,31),(213,8,42,32),(214,8,42,33),(215,8,42,34),(216,8,42,35),(217,10,43,29),(218,10,43,30),(219,10,43,31),(220,10,43,32),(221,10,43,33),(222,10,43,34),(223,10,43,35),(224,10,44,29),(225,10,44,30),(226,10,44,31),(227,10,44,32),(228,10,44,33),(229,10,44,34),(230,10,44,35),(231,10,45,29),(232,10,45,30),(233,10,45,31),(234,10,45,32),(235,10,45,33),(236,10,45,34),(237,10,45,35),(238,10,46,29),(239,10,46,30),(240,10,46,31),(241,10,46,32),(242,10,46,33),(243,10,46,34),(244,10,46,35),(245,10,47,29),(246,10,47,30),(247,10,47,31),(248,10,47,32),(249,10,47,33),(250,10,47,34),(251,10,47,35),(252,10,48,29),(253,10,48,30),(254,10,48,31),(255,10,48,32),(256,10,48,33),(257,10,48,34),(258,10,48,35);
/*!40000 ALTER TABLE `score_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `full_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `birthday` date NOT NULL,
  `gender` int(11) NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `gpa` double NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `major_id` int(11) DEFAULT NULL,
  `school_year_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `phone_UNIQUE` (`phone`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  KEY `fk_student_major_idx` (`major_id`),
  KEY `fk_student_school_year_idx` (`school_year_id`),
  CONSTRAINT `fk_student_major` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_student_school_year` FOREIGN KEY (`school_year_id`) REFERENCES `school_year` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_student_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'1951050027','Bùi Khánh Huy','khuy20@gmail.com','0888425094','2001-02-24',1,'1242QL1A Tân Tạo A, Bình Tân, TP. HCM',7,6,6,1),(2,'1951050028','Trần Văn A','khuy220@gmail.com','0888425095','2001-02-25',1,'1242QL1A Tân Tạo A, Bình Tân, TP. HCM',8,14,7,12),(5,'1951050031','Trần Thị H','thih@gmail.com','0954565789','2022-08-03',1,'Bình Tân, TP. HCM',8.4,30,9,14),(6,'1951050032','Nguyễn Thị Ngọc Trinh','ngoctrinh@gmail.com','0935534657','2022-07-28',2,'TP. HCM',7,31,8,12),(7,'1951050033','Nguyễn Thị Ngọc Quy','ngocquyen@gmail.com','0965434562','2022-07-28',2,'TP. HCM',9,32,7,1),(9,'u','u','u@gmail.com','0888425194','2022-08-15',1,'sdfsdf',5,40,6,1),(13,'195','uikui','kkykyukyu@gmail.com','0111111119','2022-08-11',1,'An qui',6,45,6,1),(16,'an ne','sdfdsf','khuy5@gmail.com','0355556359','2022-08-10',1,'An qui',5,48,6,1),(17,'sdfsdfsdf','ádfsdfsdf','llil@gmail.com','0399996359','2022-08-11',1,'An qui',6,49,6,1),(18,'SV1','SV1','sv1@gmail.com','0666666666','2022-08-15',1,'An qui',10,57,6,12),(19,'SV2','SV2','sv2@gmail.com','0777777777','2022-08-15',2,'An qui',9,58,6,12),(20,'1951050050','Trần Ngọc A','khuy22@gmail.com','0356896359','2000-01-31',2,'TP. HCM',9,60,6,1),(21,'1951050051','Trần Thị Ngọc Quế','ngocque@gmail.com','0654986598','2001-01-01',2,'TP. HCM',9,61,6,1),(22,'sdfsd','fsdfdf','asdfsdfsfsdf@gmail.com','0964654789','1999-12-27',1,'An qui',9,62,6,1),(23,'0000000001','Trần Văn A','tranvana@gmail.com','0898655094','2022-08-25',1,'Địa chỉ của bạn',10,68,6,1),(24,'0000000002','Trần Văn B','tranvanc@gmail.com','0898655495','2022-08-25',1,'Địa chỉ của bạn',10,70,6,1),(25,'0000000003','Nguyễn Thị C','khuy2202222@gmail.com','0898655896','2022-08-25',2,'Địa chỉ của bạn',10,71,6,1),(26,'0000000004','Trần Ngọc An','tranngocan@gmail.com','0898658997','2022-08-25',1,'Địa chỉ của bạn',10,72,6,1),(27,'0000000005','Nguyễn Thị Thủy','nguyenthithuy@gmail.com','0898655068','2022-08-25',2,'Địa chỉ của bạn',10,73,6,1);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thesis`
--

DROP TABLE IF EXISTS `thesis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `thesis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `start_date` date NOT NULL,
  `complate_date` date NOT NULL,
  `thesis_start_date` date NOT NULL,
  `thesis_end_date` date NOT NULL,
  `report_file` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `comment` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `total_score` double DEFAULT '0',
  `result` int(11) DEFAULT '1',
  `topic_id` int(11) DEFAULT NULL,
  `major_id` int(11) DEFAULT NULL,
  `school_year_id` int(11) DEFAULT NULL,
  `council_id` int(11) DEFAULT NULL,
  `review_lecturer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `fk_thesis_topic_idx` (`topic_id`),
  KEY `fk_thesis_school_year_idx` (`school_year_id`),
  KEY `fk_thesis_council_idx` (`council_id`),
  KEY `fk_thesis_lecturer_idx` (`review_lecturer_id`),
  KEY `fk_thesis_major_idx` (`major_id`),
  CONSTRAINT `fk_thesis_council` FOREIGN KEY (`council_id`) REFERENCES `council` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_thesis_lecturer` FOREIGN KEY (`review_lecturer_id`) REFERENCES `lecturer` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_thesis_major` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_thesis_school_year` FOREIGN KEY (`school_year_id`) REFERENCES `school_year` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_thesis_topic` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thesis`
--

LOCK TABLES `thesis` WRITE;
/*!40000 ALTER TABLE `thesis` DISABLE KEYS */;
INSERT INTO `thesis` VALUES (37,'KLCNTT0000','2022-08-15','2022-10-23','2022-08-18','2022-10-31','https://res.cloudinary.com/dtnpj540t/raw/upload/v1661540401/%221951050027BuiKhanhHuy%22_1661540400577.zip','',9,3,8,6,1,52,3),(38,'KLCNTT0001','2022-05-01','2022-08-01','2022-08-10','2022-08-20','https://res.cloudinary.com/dtnpj540t/raw/upload/v1660922426/%22K19_NguyenNgocA_WebsiteChamSocThuCung%22_1660922425234.zip','',9,3,16,6,1,49,4),(39,'KLCNTT0002','2022-06-16','2022-08-16','2022-08-18','2022-08-30','','',9,3,15,6,1,49,4),(51,'KHOALUAN02','2022-08-25','2022-08-26','2022-08-27','2022-08-28','','',0,1,15,6,1,NULL,1),(52,'KHOALUAN03','2022-08-25','2022-08-26','2022-08-27','2022-08-28','','',8.667,3,17,11,1,50,1),(53,'KHOALUAN04','2022-08-26','2022-08-27','2022-08-28','2022-08-29','','',9,3,13,6,12,51,1),(54,'pppppppp','2022-08-27','2022-08-28','2022-08-29','2022-08-30','','ádfdsf',10,3,18,6,1,53,1),(55,'MakhoaDemo','2022-08-29','2022-08-30','2022-09-02','2022-09-07','','',10,3,8,6,1,54,1);
/*!40000 ALTER TABLE `thesis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `fk_topic_department_idx` (`department_id`),
  CONSTRAINT `fk_topic_department` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` VALUES (1,'Một số biện pháp nhằm tăng cường quản lý chi ngân sách nhà nước cho sự nghiệp giáo dục trên địa bàn thủ đô Hà Nội đến năm 2020','Đề tài tốt nghiệp quản trị kinh doanh cho sinh viên cao đẳng/đại học',101),(5,'Xây dựng web site quản lý khóa luận tốt nghiệp','Mô tả website quản lý khóa luận tốt nghiệp',104),(8,'Đánh Giá Về Công Tác Chuẩn Bị Đầu Tư Xây Dựng Dự Án Cải Tạo, Sửa Chữa Hồ Chứa Nước Khe Chè','Đánh Giá Về Công Tác Chuẩn Bị Đầu Tư Xây Dựng Dự Án Cải Tạo, Sửa Chữa Hồ Chứa Nước Khe Chè',99),(9,'Lựa Chọn Công Nghệ Xử Lý Nền Mềm Yếu Của Đập Phá Sóng-Áp Dụng Cho Đập Phá Sóng Dung Quất','Lựa Chọn Công Nghệ Xử Lý Nền Mềm Yếu Của Đập Phá Sóng-Áp Dụng Cho Đập Phá Sóng Dung Quất',99),(10,'Xây dựng website thương mại điện tử','',104),(11,'Xây dựng website học Tiếng Anh','',104),(12,'Xây dựng website quản lý chi tiêu','',104),(13,'Xây dựng website đánh bạc','',104),(14,'Xây dựng website bán lúa giống','',104),(15,'Tái chế rác thải nhựa','',107),(16,'Xây dựng website chắm sóc thú cưng','',104),(17,'Giao lưu văn hóa','',108),(18,'WEbsite cá cược','',104),(19,'WEbsite cá độ bóng đá','',104);
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `avatar` varchar(350) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `fk_user_role_idx` (`role_id`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,'admin1','$2a$10$np4lcidF0ZlSQpHzseb9Q.pCqTKVHwAU6wVngTE3dMMirzcHxA2lS','https://mui.com/static/images/avatar/2.jpg',_binary '',1),(4,'admin2','$2a$10$np4lcidF0ZlSQpHzseb9Q.pCqTKVHwAU6wVngTE3dMMirzcHxA2lS','https://mui.com/static/images/avatar/3.jpg',_binary '',1),(5,'0000000000','$2a$10$np4lcidF0ZlSQpHzseb9Q.pCqTKVHwAU6wVngTE3dMMirzcHxA2lS','https://cungcau.qltns.mediacdn.vn/421196537165905920/2022/5/6/8-16517978870501185423136.jpg',_binary '',3),(6,'1951050027','$2a$10$qYLqjpMTf5IA2GnPx78v3eBp7tOIUm2IpDo7eG.25HBnlLk2bX3lS','https://yt3.ggpht.com/yti/APfAmoFyKDxY7tw6Yk0VElNP8_CtCU0DYExl2SL4qimG=s88-c-k-c0x00ffffff-no-rj-mo',_binary '',4),(13,'h','$2a$10$np4lcidF0ZlSQpHzseb9Q.pCqTKVHwAU6wVngTE3dMMirzcHxA2lS','https://res.cloudinary.com/dtnpj540t/image/upload/v1661526519/GraduationThesisManagementSystem/ipklz9n8lljcb9yxagk3.jpg',_binary '\0',1),(14,'1951050028','$2a$10$np4lcidF0ZlSQpHzseb9Q.pCqTKVHwAU6wVngTE3dMMirzcHxA2lS','https://res.cloudinary.com/dtnpj540t/image/upload/v1661526519/GraduationThesisManagementSystem/ipklz9n8lljcb9yxagk3.jpg',_binary '\0',4),(15,'superAdmin','$2a$10$Ji1mJW35YgvPcv6.7LSFeehbGwvkdAHABccJfOU8KeGjpTuHpn.Sy','https://res.cloudinary.com/dtnpj540t/image/upload/v1660683096/ncxz7ofmkqhkp2yzbgmr.jpg',_binary '',1),(17,'2222222222','$2a$10$np4lcidF0ZlSQpHzseb9Q.pCqTKVHwAU6wVngTE3dMMirzcHxA2lS','https://res.cloudinary.com/dtnpj540t/image/upload/v1661526519/GraduationThesisManagementSystem/ipklz9n8lljcb9yxagk3.jpg',_binary '',3),(19,'admin4','$2a$10$uLlGGJ8Myk3XYdz7EJNc..NDO860ay7XgOfL6PgLi8z2qUhLUmW86','https://res.cloudinary.com/dtnpj540t/image/upload/v1660683070/fdzq7ydzazrocfg2wyhq.jpg',_binary '',1),(20,'admin3','$2a$10$qSEGF2bNFXEMECQ38jE9xeZtqylRfyKYB1MBc9zpZSDzAZmz0v9LC','https://res.cloudinary.com/dtnpj540t/image/upload/v1660555268/vktd7ps2p69dplzyy7a0.jpg',_binary '',1),(21,'3333333333','$2a$10$np4lcidF0ZlSQpHzseb9Q.pCqTKVHwAU6wVngTE3dMMirzcHxA2lS','https://res.cloudinary.com/dtnpj540t/image/upload/v1661526519/GraduationThesisManagementSystem/ipklz9n8lljcb9yxagk3.jpg',_binary '\0',3),(22,'4444444444','$2a$10$np4lcidF0ZlSQpHzseb9Q.pCqTKVHwAU6wVngTE3dMMirzcHxA2lS','https://res.cloudinary.com/dtnpj540t/image/upload/v1661526519/GraduationThesisManagementSystem/ipklz9n8lljcb9yxagk3.jpg',_binary '',2),(26,'5555555555','$2a$10$v9qGsGjA1kCLcsRtXNkxc.m5iGtaWsWEagLnmO52UMwgCSnHUXhAO','https://res.cloudinary.com/dtnpj540t/image/upload/v1661526519/GraduationThesisManagementSystem/ipklz9n8lljcb9yxagk3.jpg',_binary '',2),(30,'1951050031','$2a$10$wvNvC33tT5XvoXFGR1rey.K6hKS7/llmP6jLP9wDnPAntSnV96PgG','https://nguoinoitieng.tv/images/nnt/98/0/bdio.jpg',_binary '',4),(31,'1951050032','$2a$10$np4lcidF0ZlSQpHzseb9Q.pCqTKVHwAU6wVngTE3dMMirzcHxA2lS','https://res.cloudinary.com/dtnpj540t/image/upload/v1661526519/GraduationThesisManagementSystem/ipklz9n8lljcb9yxagk3.jpg',_binary '',4),(32,'1951050033',' ','https://res.cloudinary.com/dtnpj540t/image/upload/v1661526519/GraduationThesisManagementSystem/ipklz9n8lljcb9yxagk3.jpg',_binary '\0',4),(34,'admin','$2a$10$GUi6AAL1bRLHjd.N5LPZWOhEu5P1f8KGqobhj0ftHSY6h5FQgpTX.','https://res.cloudinary.com/dtnpj540t/image/upload/v1659979186/cfqww5joxzydomnldsgf.jpg',_binary '',1),(37,'6666666666','$2a$10$jn61GyRpFSgPAGEywe9JcO2Ew9y471Q6yrDBQ5XsnZyJbvA5vgA1G','https://res.cloudinary.com/dtnpj540t/image/upload/v1661526519/GraduationThesisManagementSystem/ipklz9n8lljcb9yxagk3.jpg',_binary '',2),(38,'7777777777','$2a$10$oGUCkFmHDxEUqEo8Wjx.oe8m03NkfNGcbtk0yM.8PrlMynVCaeJAO','https://res.cloudinary.com/dtnpj540t/image/upload/v1661526519/GraduationThesisManagementSystem/ipklz9n8lljcb9yxagk3.jpg',_binary '',2),(40,'u','u','https://res.cloudinary.com/dtnpj540t/image/upload/v1661526519/GraduationThesisManagementSystem/ipklz9n8lljcb9yxagk3.jpg',_binary '',4),(45,'195',' ','https://res.cloudinary.com/dtnpj540t/image/upload/v1660683156/wzmoha94agaucr1bt3f0.jpg',_binary '',4),(48,'an ne',' ','https://res.cloudinary.com/dtnpj540t/image/upload/v1660552917/bzho8r3hm8emmz67yggw.jpg',_binary '',4),(49,'sdfsdfsdf',' ','https://res.cloudinary.com/dtnpj540t/image/upload/v1660552489/awe1hlc3nwup6sbprrxh.jpg',_binary '\0',4),(50,'GV00001111','$2a$10$jn61GyRpFSgPAGEywe9JcO2Ew9y471Q6yrDBQ5XsnZyJbvA5vgA1G','https://res.cloudinary.com/dtnpj540t/image/upload/v1660562974/kiz4mgetkchjqci7qdvi.jpg',_binary '',3),(51,'A','$2a$10$jn61GyRpFSgPAGEywe9JcO2Ew9y471Q6yrDBQ5XsnZyJbvA5vgA1G','https://res.cloudinary.com/dtnpj540t/image/upload/v1661412957/flmv11srtgthiziipoh4.jpg',_binary '',3),(52,'B','$2a$10$jn61GyRpFSgPAGEywe9JcO2Ew9y471Q6yrDBQ5XsnZyJbvA5vgA1G','https://res.cloudinary.com/dtnpj540t/image/upload/v1661526519/GraduationThesisManagementSystem/ipklz9n8lljcb9yxagk3.jpg',_binary '',3),(53,'C','$2a$10$jn61GyRpFSgPAGEywe9JcO2Ew9y471Q6yrDBQ5XsnZyJbvA5vgA1G','https://res.cloudinary.com/dtnpj540t/image/upload/v1661526519/GraduationThesisManagementSystem/ipklz9n8lljcb9yxagk3.jpg',_binary '',3),(55,'D','$2a$10$jn61GyRpFSgPAGEywe9JcO2Ew9y471Q6yrDBQ5XsnZyJbvA5vgA1G','https://res.cloudinary.com/dtnpj540t/image/upload/v1661526519/GraduationThesisManagementSystem/ipklz9n8lljcb9yxagk3.jpg',_binary '',3),(56,'E','$2a$10$jn61GyRpFSgPAGEywe9JcO2Ew9y471Q6yrDBQ5XsnZyJbvA5vgA1G','https://res.cloudinary.com/dtnpj540t/image/upload/v1661526519/GraduationThesisManagementSystem/ipklz9n8lljcb9yxagk3.jpg',_binary '',3),(57,'SV1','SV1','https://res.cloudinary.com/dtnpj540t/image/upload/v1661526519/GraduationThesisManagementSystem/ipklz9n8lljcb9yxagk3.jpg',_binary '',4),(58,'SV2','$2a$10$0kl6eJMP7WSKfMMd2WJJRuOsQ2V8EpfDn6IpdslvzNB9IdPSYaOHa','https://res.cloudinary.com/dtnpj540t/image/upload/v1661526519/GraduationThesisManagementSystem/ipklz9n8lljcb9yxagk3.jpg',_binary '',4),(59,'ádfsfsdfa','$2a$10$/W7zdV1RlNMzGlPHreT8Gu0YF3g.47LqDDwtPOA38QYQvp0Qtm.4G','https://res.cloudinary.com/dtnpj540t/image/upload/v1660932876/joijmlepzxdwmcy3gjhr.jpg',_binary '',1),(60,'1951050050',' ','https://res.cloudinary.com/dtnpj540t/image/upload/v1660921968/g0oliruwqor44y5muety.jpg',_binary '',4),(61,'1951050051','$2a$10$WAZ1Qo8jf7a4aQxBIAO9feSZR3KykK1RxUgvkOWP8Q3Z57bx9z2qy','https://res.cloudinary.com/dtnpj540t/image/upload/v1661021523/syp2osg6qjhyodvccmo6.png',_binary '',4),(62,'sdfsd',' ','https://res.cloudinary.com/dtnpj540t/image/upload/v1661413036/srh61fbhbfziq0yugv5x.png',_binary '\0',4),(68,'0000000001','$2a$10$ibbkxLqJWT/8EwHh2JxOROElTNQ0YjVVpDBKwE5NFVjc9LZr6OxPK','https://res.cloudinary.com/dtnpj540t/image/upload/v1661367494/ji94lmhjljzzstwuzj7w.jpg',_binary '',4),(70,'0000000002','$2a$10$E41CvI7qtLwnPwJ4XGzmm.IlBo2X6BCt64dgvkmGgvla22RiA9UAi','https://res.cloudinary.com/dtnpj540t/image/upload/v1661369343/rurqriychquyvrfesuaq.jpg',_binary '',4),(71,'0000000003','$2a$10$3CSqNXmxpn89cWG/IfKAy.xHBPvOmMnqQ.g6yc0/e8K.dAGxCPOcu','https://res.cloudinary.com/dtnpj540t/image/upload/v1661369345/urm5mdnt6ibmpg6x5bic.jpg',_binary '',4),(72,'0000000004','$2a$10$1YAK/WD.gS8w4yiQ42AotucBKTOhIqOZeRqAvrsaoD/ZplQLwECXy','https://res.cloudinary.com/dtnpj540t/image/upload/v1661369347/n3sevalpepwurcvncucx.jpg',_binary '',4),(73,'0000000005','$2a$10$4y7efWlPS/KMUkulxUSROevC5id6Tpi6evtHMn4TTifC1FLX69OAG','https://res.cloudinary.com/dtnpj540t/image/upload/v1661369349/x66fjr03f1sk2oyi4msc.jpg',_binary '',4),(75,'1000000001','$2a$10$1AYJbF4rK3kAGaq57GT0iuVH.tRMymCsiVUW2mM64W1RZr69FuhRS','https://res.cloudinary.com/dtnpj540t/image/upload/v1661605260/b5zj9hwpeakjnyqr278b.png',_binary '',2),(78,'sdfsdf','$2a$10$nIjA9/88nTPbUJwf8elNReRKQiMr8Ji/gHu2Dwz27UHPVKSQAsEJC','https://res.cloudinary.com/dtnpj540t/image/upload/v1661526519/GraduationThesisManagementSystem/ipklz9n8lljcb9yxagk3.jpg',_binary '\0',2),(80,'0000000011','$2a$10$yalRFEQRdnYVlH7p0nWEle.mC3yQfhlxQhKHO9Newe01EAzDqDoSK','https://res.cloudinary.com/dtnpj540t/image/upload/v1661606006/lkdxwb9naczerq4bnrvz.jpg',_binary '',3),(81,'0000000012','$2a$10$UpNSjao2agmhQg82CTfFJuwuFGxl6uiIQh0bohdjfa39fSAmekIwq','https://res.cloudinary.com/dtnpj540t/image/upload/v1661606008/h86szcjahjxpp664trab.jpg',_binary '',3),(82,'0000000013','$2a$10$dci4nbxxNsRsoWjEAgFDEuCT6znmfd9gX132BiQs3NTYZcqzHrURa','https://res.cloudinary.com/dtnpj540t/image/upload/v1661606010/jhn95rwztk45p8l3kugf.jpg',_binary '',3),(83,'0000000014','$2a$10$SHq24wEu7Esn47rr8me2T.RDbnVbQyf4Xr.kUxN5vO4Tvg4T/IWy.','https://res.cloudinary.com/dtnpj540t/image/upload/v1661606012/ssnu77kisqrytsm7c73n.jpg',_binary '',3),(84,'0000000015','$2a$10$LTEfcFV8zM3oU6bkbLF9Pe0aZV5FsKpRtVzgrIFZ4o9deocWL857e','https://res.cloudinary.com/dtnpj540t/image/upload/v1661606014/oxe0hl1ztiz7i3qoscrx.jpg',_binary '',3),(85,'0000000016','$2a$10$HxiRRbdxrUr4fN/FDBPymeq6I6HvDMYr.WRQs4kzXm2VI.fyq19bS','https://res.cloudinary.com/dtnpj540t/image/upload/v1661606016/vv6iajkaw0bbdgnjyv17.jpg',_binary '',3),(86,'0000000017','$2a$10$z1jt4koiInvCpuBh3iWAOufaF8GAmPmxrXtJpedF3Rz.nqaDrhwHC','https://res.cloudinary.com/dtnpj540t/image/upload/v1661606018/v3j60fcqyr5pse0cwcil.jpg',_binary '',3),(87,'0000000018','$2a$10$x71C5RA1MK28SceENk2Pm.OTTfm5Cm6Ts/JyWS1nSgvcWg.vygCLC','https://res.cloudinary.com/dtnpj540t/image/upload/v1661606020/evdlrnmp2vmpoxfh7pnd.jpg',_binary '',3),(88,'0000000019','$2a$10$zMsNenjy6m/O5iunZebDUuX4budUZQx46NbtH7cwGUcjnPZ6jqtw6','https://res.cloudinary.com/dtnpj540t/image/upload/v1661606022/musotxpcir3k9hkycclq.jpg',_binary '',3),(89,'0000000020','$2a$10$lgkstgPc8TpXIhXOEqEyKu79pT7yihtC0KT.wTJR.pajIL6YAod6a','https://res.cloudinary.com/dtnpj540t/image/upload/v1661606024/ulztc8zd4y9dcuspsidt.jpg',_binary '',3);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-28  0:56:58
