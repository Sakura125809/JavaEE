/*
SQLyog Ultimate v9.62 
MySQL - 5.5.53 : Database - test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `test`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` varchar(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `admin` */

insert  into `admin`(`id`,`name`,`password`) values ('1','陈','123'),('2','刘','123'),('3','薛','123');

/*Table structure for table `grade` */

DROP TABLE IF EXISTS `grade`;

CREATE TABLE `grade` (
  `stu_no` varchar(10) NOT NULL,
  `sub_no` varchar(10) NOT NULL,
  `usual_grade` double DEFAULT NULL,
  `paper_grade` double DEFAULT NULL,
  `final_grade` double NOT NULL,
  `comment` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`stu_no`,`sub_no`),
  KEY `fk_g_sub` (`sub_no`),
  CONSTRAINT `fk_g_s` FOREIGN KEY (`stu_no`) REFERENCES `student` (`stu_no`),
  CONSTRAINT `fk_g_sub` FOREIGN KEY (`sub_no`) REFERENCES `subject` (`sub_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `grade` */

insert  into `grade`(`stu_no`,`sub_no`,`usual_grade`,`paper_grade`,`final_grade`,`comment`) values ('1','1124',30,80,94,'good'),('1','2014',25,100,95,'优秀'),('2','1124',0,60,42,'不及格'),('2','2014',20,100,90,'不错'),('22','1124',40,100,100,'非常好'),('22','2014',18,100,98,'很好！'),('3','1076',31,90,85,'还行'),('4','1076',16,100,86,'良好'),('5','1076',13,80,69,'及格'),('5','2513',0,100,90,'优秀');

/*Table structure for table `stu_te_sub` */

DROP TABLE IF EXISTS `stu_te_sub`;

CREATE TABLE `stu_te_sub` (
  `stu_no` varchar(10) NOT NULL,
  `te_no` varchar(10) NOT NULL,
  `sub_no` varchar(10) NOT NULL,
  PRIMARY KEY (`stu_no`,`sub_no`,`te_no`),
  KEY `fk_teno` (`te_no`),
  KEY `fk_subno` (`sub_no`),
  CONSTRAINT `fk_stuno` FOREIGN KEY (`stu_no`) REFERENCES `student` (`stu_no`),
  CONSTRAINT `fk_subno` FOREIGN KEY (`sub_no`) REFERENCES `subject` (`sub_no`),
  CONSTRAINT `fk_teno` FOREIGN KEY (`te_no`) REFERENCES `teacher` (`te_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `stu_te_sub` */

insert  into `stu_te_sub`(`stu_no`,`te_no`,`sub_no`) values ('1','1','1124'),('1','1','2014'),('2','1','1124'),('2','1','2014'),('22','1','1124'),('22','1','2014'),('3','4','1076'),('4','4','1076'),('5','4','1076'),('5','5','2513');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `stu_no` varchar(10) NOT NULL,
  `stu_name` varchar(10) NOT NULL,
  `password` varchar(20) NOT NULL,
  `class` varchar(10) NOT NULL,
  `major` varchar(20) NOT NULL,
  `admissiontime` date NOT NULL,
  `sex` varchar(1) NOT NULL,
  `department` varchar(20) NOT NULL,
  PRIMARY KEY (`stu_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `student` */

insert  into `student`(`stu_no`,`stu_name`,`password`,`class`,`major`,`admissiontime`,`sex`,`department`) values ('1','lzq','123','2021','软件工程','2021-07-28','男','计院'),('11','cqx','123','2021','软件工程','2021-09-06','男','计院'),('2','cpz','123','2021','软件工程','2021-07-25','男','计院'),('22','xjy','123','2021','软件工程','2021-02-01','男','计院'),('3','ccc','123','2020','英语','2020-08-03','男','外国语学院'),('4','qqq','123','2020','英语','2020-08-16','男','外国语学院'),('5','xxx','123','2020','经济','2020-08-01','女','经济学院');

/*Table structure for table `subject` */

DROP TABLE IF EXISTS `subject`;

CREATE TABLE `subject` (
  `sub_no` varchar(10) NOT NULL,
  `sub_name` varchar(10) NOT NULL,
  `open_department` varchar(20) NOT NULL,
  `paper_grade_per` float NOT NULL,
  PRIMARY KEY (`sub_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `subject` */

insert  into `subject`(`sub_no`,`sub_name`,`open_department`,`paper_grade_per`) values ('1076','英语','外语学院',0.7),('1124','数据结构与算法','软件学院',0.7),('2014','javaEE','软件学院',0.7),('2513','微观经济学','经济学院',0.9);

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `te_no` varchar(10) NOT NULL,
  `te_name` varchar(10) NOT NULL,
  `password` varchar(20) NOT NULL,
  `department` varchar(20) NOT NULL,
  `wage` int(11) NOT NULL,
  `entry_time` date NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  `phonenumber` varchar(11) DEFAULT NULL,
  `job_title` varchar(10) NOT NULL,
  PRIMARY KEY (`te_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `teacher` */

insert  into `teacher`(`te_no`,`te_name`,`password`,`department`,`wage`,`entry_time`,`email`,`phonenumber`,`job_title`) values ('1','李达','123','软件学院',10000,'1901-02-02','131@163.com','13544443333','教授'),('4','李木','123','外语学院',9000,'2018-08-01','limu@163.com','18845667898','讲师'),('5','李鑫','123','经济学院',10004,'2018-08-15','789@163.com','15789772343','教授');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
