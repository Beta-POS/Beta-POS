-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.1.55-community - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for beta_pos_x
DROP DATABASE IF EXISTS `beta_pos_x`;
CREATE DATABASE IF NOT EXISTS `beta_pos_x` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `beta_pos_x`;


-- Dumping structure for table beta_pos_x.m_account
DROP TABLE IF EXISTS `m_account`;
CREATE TABLE IF NOT EXISTS `m_account` (
  `code` varchar(10) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `category` varchar(10) DEFAULT NULL,
  `account_group` varchar(25) DEFAULT NULL,
  `print_order` int(11) DEFAULT NULL,
  `before_word` varchar(25) DEFAULT NULL,
  `after_word` varchar(25) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `fk_account_idx` (`category`),
  CONSTRAINT `fk_account` FOREIGN KEY (`category`) REFERENCES `m_account_category` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.m_account: ~0 rows (approximately)
/*!40000 ALTER TABLE `m_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_account` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.m_account_category
DROP TABLE IF EXISTS `m_account_category`;
CREATE TABLE IF NOT EXISTS `m_account_category` (
  `code` varchar(10) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `parent` varchar(10) DEFAULT NULL,
  `type` enum('BALANCE_SHEET','PROFIT_AND_LOSS') DEFAULT NULL,
  `path` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `fk_m_account_category_m_account_category1_idx` (`parent`),
  CONSTRAINT `fk_m_account_category_m_account_category1` FOREIGN KEY (`parent`) REFERENCES `m_account_category` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.m_account_category: ~0 rows (approximately)
/*!40000 ALTER TABLE `m_account_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_account_category` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.m_bank
DROP TABLE IF EXISTS `m_bank`;
CREATE TABLE IF NOT EXISTS `m_bank` (
  `code` varchar(10) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.m_bank: ~0 rows (approximately)
/*!40000 ALTER TABLE `m_bank` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_bank` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.m_bank_branch
DROP TABLE IF EXISTS `m_bank_branch`;
CREATE TABLE IF NOT EXISTS `m_bank_branch` (
  `code` varchar(10) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `bank` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `fk_bank_branch_m_bank1_idx` (`bank`),
  CONSTRAINT `fk_bank_branch_m_bank1` FOREIGN KEY (`bank`) REFERENCES `m_bank` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.m_bank_branch: ~0 rows (approximately)
/*!40000 ALTER TABLE `m_bank_branch` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_bank_branch` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.m_branch
DROP TABLE IF EXISTS `m_branch`;
CREATE TABLE IF NOT EXISTS `m_branch` (
  `code` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `address_line1` varchar(50) NOT NULL,
  `address_line2` varchar(50) NOT NULL,
  `address_line3` varchar(50) NOT NULL,
  `hotline` varchar(25) NOT NULL,
  `telephone1` varchar(25) NOT NULL,
  `telephone_2` varchar(25) NOT NULL,
  `fax` varchar(25) NOT NULL,
  `email` varchar(50) NOT NULL,
  `note` longtext NOT NULL,
  `company` varchar(10) DEFAULT NULL,
  `working_date` date NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`code`),
  KEY `fk_m_branch_m_company1_idx` (`company`),
  CONSTRAINT `fk_m_branch_m_company1` FOREIGN KEY (`company`) REFERENCES `m_company` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.m_branch: ~1 rows (approximately)
/*!40000 ALTER TABLE `m_branch` DISABLE KEYS */;
REPLACE INTO `m_branch` (`code`, `name`, `address_line1`, `address_line2`, `address_line3`, `hotline`, `telephone1`, `telephone_2`, `fax`, `email`, `note`, `company`, `working_date`, `active`) VALUES
	('DEF', 'Default', '-', '-', '-', '-', '-', '-', '-', '-', '-', '001', '2015-01-01', 1);
/*!40000 ALTER TABLE `m_branch` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.m_company
DROP TABLE IF EXISTS `m_company`;
CREATE TABLE IF NOT EXISTS `m_company` (
  `code` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `version_id` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.m_company: ~1 rows (approximately)
/*!40000 ALTER TABLE `m_company` DISABLE KEYS */;
REPLACE INTO `m_company` (`code`, `name`, `version_id`) VALUES
	('001', 'Company', '1.0.6');
/*!40000 ALTER TABLE `m_company` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.m_department
DROP TABLE IF EXISTS `m_department`;
CREATE TABLE IF NOT EXISTS `m_department` (
  `hash` varchar(21) NOT NULL,
  `code` varchar(10) NOT NULL,
  `branch` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`hash`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.m_department: ~1 rows (approximately)
/*!40000 ALTER TABLE `m_department` DISABLE KEYS */;
REPLACE INTO `m_department` (`hash`, `code`, `branch`, `name`, `active`) VALUES
	('DEF-001', '001', 'DEF', 'Department', 1);
/*!40000 ALTER TABLE `m_department` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.m_employee
DROP TABLE IF EXISTS `m_employee`;
CREATE TABLE IF NOT EXISTS `m_employee` (
  `hash` varchar(21) NOT NULL,
  `code` varchar(10) NOT NULL,
  `branch` varchar(10) NOT NULL,
  `nic_no` varchar(25) NOT NULL,
  `salutation` varchar(25) NOT NULL,
  `name` varchar(50) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `address_line1` varchar(50) NOT NULL,
  `address_line2` varchar(50) NOT NULL,
  `address_line3` varchar(50) NOT NULL,
  `mobile` varchar(25) NOT NULL,
  `telephone1` varchar(25) NOT NULL,
  `telephone_2` varchar(25) NOT NULL,
  `fax` varchar(25) NOT NULL,
  `email` varchar(50) NOT NULL,
  `note` longtext NOT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `photo` int(11) DEFAULT NULL,
  `user_role` varchar(10) DEFAULT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`hash`),
  KEY `fk_m_employee_photo1_idx` (`photo`),
  KEY `fk_m_employee_m_branch1_idx` (`branch`),
  KEY `fk_m_employee_r_user_role1_idx` (`user_role`),
  CONSTRAINT `fk_m_employee_m_branch1` FOREIGN KEY (`branch`) REFERENCES `m_branch` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_m_employee_photo1` FOREIGN KEY (`photo`) REFERENCES `m_photo` (`index_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_m_employee_r_user_role1` FOREIGN KEY (`user_role`) REFERENCES `r_user_role` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.m_employee: ~1 rows (approximately)
/*!40000 ALTER TABLE `m_employee` DISABLE KEYS */;
REPLACE INTO `m_employee` (`hash`, `code`, `branch`, `nic_no`, `salutation`, `name`, `full_name`, `address_line1`, `address_line2`, `address_line3`, `mobile`, `telephone1`, `telephone_2`, `fax`, `email`, `note`, `user_name`, `password`, `photo`, `user_role`, `active`) VALUES
	('DEF', 'DEF', 'DEF', '01112112125', 'Mr.', 'Developer', 'Developer', 'No. 07', 'Gorge E De Silva Mawatha', 'Kandy', '-', '-', '-', '-', '-', '-', 'dev', '7529', NULL, NULL, 1);
/*!40000 ALTER TABLE `m_employee` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.m_item
DROP TABLE IF EXISTS `m_item`;
CREATE TABLE IF NOT EXISTS `m_item` (
  `hash` varchar(21) NOT NULL,
  `code` varchar(10) NOT NULL,
  `branch` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `barcode` varchar(25) DEFAULT NULL,
  `sinhala_name` text,
  `tamil_name` text,
  `brand` int(11) DEFAULT NULL,
  `make` int(11) DEFAULT NULL,
  `model` int(11) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `unit` int(11) DEFAULT NULL,
  `generic` int(11) DEFAULT NULL,
  `department` varchar(21) DEFAULT NULL,
  `main_category` varchar(21) DEFAULT NULL,
  `sub_category` varchar(21) DEFAULT NULL,
  `quantity_in_pack` int(11) NOT NULL,
  `cost_price` double NOT NULL,
  `whole_sale_margin` double NOT NULL,
  `whole_sale_price` double NOT NULL,
  `max_discount_percent` double NOT NULL,
  `last_sale_price` double NOT NULL,
  `sale_margin` double NOT NULL,
  `sale_price` double NOT NULL,
  `average_price` double NOT NULL,
  `batch` tinyint(1) NOT NULL,
  `serial` tinyint(1) NOT NULL,
  `service` tinyint(1) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`hash`),
  KEY `fk_m_item_m_department1_idx` (`department`),
  KEY `fk_m_item_m_main_category1_idx` (`main_category`),
  KEY `fk_m_item_m_sub_category1_idx` (`sub_category`),
  KEY `fk_m_item_m_item_properties1_idx` (`brand`),
  KEY `fk_m_item_m_item_properties2_idx` (`make`),
  KEY `fk_m_item_m_item_properties3_idx` (`model`),
  KEY `fk_m_item_m_item_properties4_idx` (`size`),
  KEY `fk_m_item_m_item_properties5_idx` (`unit`),
  KEY `fk_m_item_m_item_properties6_idx` (`generic`),
  CONSTRAINT `fk_m_item_m_department1` FOREIGN KEY (`department`) REFERENCES `m_department` (`hash`),
  CONSTRAINT `fk_m_item_m_item_properties1` FOREIGN KEY (`brand`) REFERENCES `m_item_property` (`index_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_m_item_m_item_properties2` FOREIGN KEY (`make`) REFERENCES `m_item_property` (`index_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_m_item_m_item_properties3` FOREIGN KEY (`model`) REFERENCES `m_item_property` (`index_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_m_item_m_item_properties4` FOREIGN KEY (`size`) REFERENCES `m_item_property` (`index_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_m_item_m_item_properties5` FOREIGN KEY (`unit`) REFERENCES `m_item_property` (`index_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_m_item_m_item_properties6` FOREIGN KEY (`generic`) REFERENCES `m_item_property` (`index_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_m_item_m_main_category1` FOREIGN KEY (`main_category`) REFERENCES `m_main_category` (`hash`),
  CONSTRAINT `fk_m_item_m_sub_category1` FOREIGN KEY (`sub_category`) REFERENCES `m_sub_category` (`hash`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.m_item: ~1 rows (approximately)
/*!40000 ALTER TABLE `m_item` DISABLE KEYS */;
REPLACE INTO `m_item` (`hash`, `code`, `branch`, `name`, `barcode`, `sinhala_name`, `tamil_name`, `brand`, `make`, `model`, `size`, `unit`, `generic`, `department`, `main_category`, `sub_category`, `quantity_in_pack`, `cost_price`, `whole_sale_margin`, `whole_sale_price`, `max_discount_percent`, `last_sale_price`, `sale_margin`, `sale_price`, `average_price`, `batch`, `serial`, `service`, `active`) VALUES
	('DEF-001001', '001001', 'DEF', 'Sample Item', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, 'DEF-001', 'DEF-001', 'DEF-001', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1);
/*!40000 ALTER TABLE `m_item` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.m_item_batch
DROP TABLE IF EXISTS `m_item_batch`;
CREATE TABLE IF NOT EXISTS `m_item_batch` (
  `hash` varchar(32) NOT NULL,
  `item` varchar(21) NOT NULL,
  `branch` varchar(10) NOT NULL,
  `batch_number` int(11) NOT NULL,
  `sale_price` double NOT NULL,
  `last_sales_price` double NOT NULL,
  `whole_sale_price` double NOT NULL,
  `cost_price` double NOT NULL,
  `expire_date` date NOT NULL,
  PRIMARY KEY (`hash`),
  KEY `fk_m_item_batch_m_item1_idx` (`item`),
  CONSTRAINT `fk_m_item_batch_m_item1` FOREIGN KEY (`item`) REFERENCES `m_item` (`hash`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.m_item_batch: ~1 rows (approximately)
/*!40000 ALTER TABLE `m_item_batch` DISABLE KEYS */;
REPLACE INTO `m_item_batch` (`hash`, `item`, `branch`, `batch_number`, `sale_price`, `last_sales_price`, `whole_sale_price`, `cost_price`, `expire_date`) VALUES
	('DEF-001001-1', 'DEF-001001', 'DEF', 1, 0, 0, 0, 0, '2016-08-26');
/*!40000 ALTER TABLE `m_item_batch` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.m_item_property
DROP TABLE IF EXISTS `m_item_property`;
CREATE TABLE IF NOT EXISTS `m_item_property` (
  `index_no` int(11) NOT NULL AUTO_INCREMENT,
  `branch` varchar(10) NOT NULL,
  `type` varchar(25) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`index_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.m_item_property: ~0 rows (approximately)
/*!40000 ALTER TABLE `m_item_property` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_item_property` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.m_item_settings
DROP TABLE IF EXISTS `m_item_settings`;
CREATE TABLE IF NOT EXISTS `m_item_settings` (
  `hash` varchar(32) NOT NULL,
  `item` varchar(21) NOT NULL,
  `branch` varchar(10) NOT NULL,
  `store` varchar(21) DEFAULT NULL,
  `reorder_level` int(11) NOT NULL,
  `reorder_quantity` int(11) NOT NULL,
  `shelf` varchar(50) NOT NULL,
  PRIMARY KEY (`hash`),
  KEY `fk_m_item_settings_m_store1_idx` (`store`),
  KEY `fk_m_item_settings_m_item1_idx` (`item`),
  CONSTRAINT `fk_m_item_settings_m_item1` FOREIGN KEY (`item`) REFERENCES `m_item` (`hash`),
  CONSTRAINT `fk_m_item_settings_m_store1` FOREIGN KEY (`store`) REFERENCES `m_store` (`hash`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.m_item_settings: ~1 rows (approximately)
/*!40000 ALTER TABLE `m_item_settings` DISABLE KEYS */;
REPLACE INTO `m_item_settings` (`hash`, `item`, `branch`, `store`, `reorder_level`, `reorder_quantity`, `shelf`) VALUES
	('DEF-001001', 'DEF-001001', 'DEF', NULL, 0, 0, '');
/*!40000 ALTER TABLE `m_item_settings` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.m_main_category
DROP TABLE IF EXISTS `m_main_category`;
CREATE TABLE IF NOT EXISTS `m_main_category` (
  `hash` varchar(21) NOT NULL,
  `code` varchar(10) NOT NULL,
  `branch` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `department` varchar(21) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`hash`),
  KEY `fk_main_category_department_idx` (`department`),
  CONSTRAINT `fk_main_category_department` FOREIGN KEY (`department`) REFERENCES `m_department` (`hash`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.m_main_category: ~1 rows (approximately)
/*!40000 ALTER TABLE `m_main_category` DISABLE KEYS */;
REPLACE INTO `m_main_category` (`hash`, `code`, `branch`, `name`, `department`, `active`) VALUES
	('DEF-001', '001', 'DEF', 'Main Category', 'DEF-001', 1);
/*!40000 ALTER TABLE `m_main_category` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.m_photo
DROP TABLE IF EXISTS `m_photo`;
CREATE TABLE IF NOT EXISTS `m_photo` (
  `index_no` int(11) NOT NULL AUTO_INCREMENT,
  `photo` longblob NOT NULL,
  PRIMARY KEY (`index_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.m_photo: ~0 rows (approximately)
/*!40000 ALTER TABLE `m_photo` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_photo` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.m_report
DROP TABLE IF EXISTS `m_report`;
CREATE TABLE IF NOT EXISTS `m_report` (
  `code` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `category` varchar(50) NOT NULL,
  `report` longblob NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.m_report: ~0 rows (approximately)
/*!40000 ALTER TABLE `m_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_report` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.m_route
DROP TABLE IF EXISTS `m_route`;
CREATE TABLE IF NOT EXISTS `m_route` (
  `hash` varchar(21) NOT NULL,
  `code` varchar(10) NOT NULL,
  `branch` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`hash`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.m_route: ~1 rows (approximately)
/*!40000 ALTER TABLE `m_route` DISABLE KEYS */;
REPLACE INTO `m_route` (`hash`, `code`, `branch`, `name`) VALUES
	('DEF-001', '001', 'DEF', 'Route');
/*!40000 ALTER TABLE `m_route` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.m_store
DROP TABLE IF EXISTS `m_store`;
CREATE TABLE IF NOT EXISTS `m_store` (
  `hash` varchar(21) NOT NULL,
  `code` varchar(10) NOT NULL,
  `branch` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`hash`),
  KEY `fk_m_store_m_branch1_idx` (`branch`),
  CONSTRAINT `fk_m_store_m_branch1` FOREIGN KEY (`branch`) REFERENCES `m_branch` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.m_store: ~1 rows (approximately)
/*!40000 ALTER TABLE `m_store` DISABLE KEYS */;
REPLACE INTO `m_store` (`hash`, `code`, `branch`, `name`, `active`) VALUES
	('DEF-001', '001', 'DEF', 'Store 01', 1);
/*!40000 ALTER TABLE `m_store` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.m_sub_category
DROP TABLE IF EXISTS `m_sub_category`;
CREATE TABLE IF NOT EXISTS `m_sub_category` (
  `hash` varchar(21) NOT NULL,
  `code` varchar(10) NOT NULL,
  `branch` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `main_category` varchar(21) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`hash`),
  KEY `fk_sub_category_main_category1_idx` (`main_category`),
  CONSTRAINT `fk_sub_category_main_category1` FOREIGN KEY (`main_category`) REFERENCES `m_main_category` (`hash`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.m_sub_category: ~1 rows (approximately)
/*!40000 ALTER TABLE `m_sub_category` DISABLE KEYS */;
REPLACE INTO `m_sub_category` (`hash`, `code`, `branch`, `name`, `main_category`, `active`) VALUES
	('DEF-001', '001', 'DEF', 'Sub Category', 'DEF-001', 1);
/*!40000 ALTER TABLE `m_sub_category` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.m_transactor
DROP TABLE IF EXISTS `m_transactor`;
CREATE TABLE IF NOT EXISTS `m_transactor` (
  `hash` varchar(21) NOT NULL,
  `code` varchar(10) NOT NULL,
  `branch` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `contact_person` varchar(50) DEFAULT NULL,
  `mobile` varchar(25) DEFAULT NULL,
  `address_line1` varchar(50) DEFAULT NULL,
  `address_line2` varchar(50) DEFAULT NULL,
  `address_line3` varchar(50) DEFAULT NULL,
  `telephone1` varchar(25) DEFAULT NULL,
  `telephone2` varchar(25) DEFAULT NULL,
  `telephone3` varchar(25) DEFAULT NULL,
  `fax` varchar(25) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `note` longtext,
  `route` varchar(21) DEFAULT NULL,
  `category` varchar(21) NOT NULL,
  `credit_limit` double NOT NULL,
  `credit_period` int(11) NOT NULL,
  `client` tinyint(1) NOT NULL,
  `supplier` tinyint(1) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`hash`),
  KEY `fk_m_client_route1_idx` (`route`),
  KEY `fk_m_transactor_m_transactor_category1_idx` (`category`),
  CONSTRAINT `fk_m_client_route1` FOREIGN KEY (`route`) REFERENCES `m_route` (`hash`),
  CONSTRAINT `fk_m_transactor_m_transactor_category1` FOREIGN KEY (`category`) REFERENCES `m_transactor_category` (`hash`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.m_transactor: ~1 rows (approximately)
/*!40000 ALTER TABLE `m_transactor` DISABLE KEYS */;
REPLACE INTO `m_transactor` (`hash`, `code`, `branch`, `name`, `contact_person`, `mobile`, `address_line1`, `address_line2`, `address_line3`, `telephone1`, `telephone2`, `telephone3`, `fax`, `email`, `note`, `route`, `category`, `credit_limit`, `credit_period`, `client`, `supplier`, `active`) VALUES
	('DEF-C001', 'C001', 'DEF', 'Cash Customer', 'Mohan', '071', '-', '-', '', '', '', '', '', '', NULL, 'DEF-001', 'DEF-00001', 0, 0, 1, 0, 1);
/*!40000 ALTER TABLE `m_transactor` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.m_transactor_category
DROP TABLE IF EXISTS `m_transactor_category`;
CREATE TABLE IF NOT EXISTS `m_transactor_category` (
  `hash` varchar(21) NOT NULL,
  `code` varchar(10) NOT NULL,
  `branch` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`hash`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.m_transactor_category: ~1 rows (approximately)
/*!40000 ALTER TABLE `m_transactor_category` DISABLE KEYS */;
REPLACE INTO `m_transactor_category` (`hash`, `code`, `branch`, `name`) VALUES
	('DEF-00001', '00001', 'DEF', 'Cash Customer');
/*!40000 ALTER TABLE `m_transactor_category` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.r_branch_setting
DROP TABLE IF EXISTS `r_branch_setting`;
CREATE TABLE IF NOT EXISTS `r_branch_setting` (
  `hash` varchar(21) NOT NULL,
  `branch` varchar(10) NOT NULL,
  `setting` varchar(25) NOT NULL,
  `description` varchar(50) NOT NULL,
  `value` varchar(25) NOT NULL,
  `default_value` varchar(25) NOT NULL,
  `type` varchar(25) NOT NULL,
  PRIMARY KEY (`hash`),
  KEY `fk_r_branch_setting_m_branch1_idx` (`branch`),
  CONSTRAINT `fk_r_branch_setting_m_branch1` FOREIGN KEY (`branch`) REFERENCES `m_branch` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.r_branch_setting: ~0 rows (approximately)
/*!40000 ALTER TABLE `r_branch_setting` DISABLE KEYS */;
/*!40000 ALTER TABLE `r_branch_setting` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.r_permission
DROP TABLE IF EXISTS `r_permission`;
CREATE TABLE IF NOT EXISTS `r_permission` (
  `code` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `class` varchar(100) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.r_permission: ~0 rows (approximately)
/*!40000 ALTER TABLE `r_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `r_permission` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.r_system_setting
DROP TABLE IF EXISTS `r_system_setting`;
CREATE TABLE IF NOT EXISTS `r_system_setting` (
  `setting` varchar(25) NOT NULL,
  `description` varchar(50) NOT NULL,
  `value` varchar(25) NOT NULL,
  `default_value` varchar(25) NOT NULL,
  `type` varchar(25) NOT NULL,
  PRIMARY KEY (`setting`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.r_system_setting: ~12 rows (approximately)
/*!40000 ALTER TABLE `r_system_setting` DISABLE KEYS */;
REPLACE INTO `r_system_setting` (`setting`, `description`, `value`, `default_value`, `type`) VALUES
	('BRANCH_AUTO_CODE', 'Auto Generate Branch Code Length', '3', '3', 'INTEGER'),
	('CLIENT_AUTO_CODE', 'Auto Generate Client Code Length', '3', '3', 'INTEGER'),
	('COMPANY_AUTO_CODE', 'Auto Generate Company Code Length', '3', '3', 'INTEGER'),
	('DEPARTMENT_AUTO_CODE', 'Auto Generate Department Code Length', '3', '3', 'INTEGER'),
	('EMPLOYEE_AUTO_CODE', 'Auto Generate Employee Code Length', '3', '3', 'INTEGER'),
	('ITEM_AUTO_CODE', 'Auto Generate Item Code Length', '3', '3', 'INTEGER'),
	('MAIN_CAT_AUTO_CODE', 'Auto Generate Main Category Code Length', '3', '3', 'INTEGER'),
	('ROUTE_AUTO_CODE', 'Auto Generate Route Code Length', '3', '3', 'INTEGER'),
	('STORE_AUTO_CODE', 'Auto Generate Store Code Length', '3', '3', 'INTEGER'),
	('SUB_CAT_AUTO_CODE', 'Auto Generate Sub Category Code Length', '3', '3', 'INTEGER'),
	('SUPPLIER_AUTO_CODE', 'Auto Generate Supplier Code Length', '3', '3', 'INTEGER'),
	('TRNSACT_CAT_AUTO_CODE', 'Auto Generate Transactor Code Length', '3', '3', 'INTEGER');
/*!40000 ALTER TABLE `r_system_setting` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.r_transaction_type
DROP TABLE IF EXISTS `r_transaction_type`;
CREATE TABLE IF NOT EXISTS `r_transaction_type` (
  `code` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `form_type` varchar(25) NOT NULL,
  `type` varchar(25) NOT NULL,
  `item_in` tinyint(1) NOT NULL,
  `item_out` tinyint(1) NOT NULL,
  `in_store` varchar(21) DEFAULT NULL,
  `out_store` varchar(21) DEFAULT NULL,
  `fixed_price` tinyint(1) NOT NULL,
  `price_type` varchar(25) NOT NULL,
  `percentage_discount` tinyint(1) NOT NULL,
  `settlement_transaction` tinyint(1) NOT NULL,
  `settlement_transaction_type` varchar(10) DEFAULT NULL,
  `expire_date` tinyint(1) NOT NULL,
  `client` tinyint(1) NOT NULL,
  `supplier` tinyint(1) NOT NULL,
  `print_first_report` tinyint(1) NOT NULL,
  `first_report` varchar(10) DEFAULT NULL,
  `print_second_report` tinyint(1) NOT NULL,
  `second_report` varchar(10) DEFAULT NULL,
  `print_third_report` tinyint(1) NOT NULL,
  `third_report` varchar(10) DEFAULT NULL,
  `credit_amount` tinyint(1) NOT NULL,
  `debit_amount` tinyint(1) NOT NULL,
  `payment` tinyint(1) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`code`),
  KEY `fk_r_transaction_type_m_store1_idx` (`in_store`),
  KEY `fk_r_transaction_type_m_store2_idx` (`out_store`),
  KEY `fk_r_transaction_type_m_report1_idx` (`first_report`),
  KEY `fk_r_transaction_type_m_report2_idx` (`second_report`),
  KEY `fk_r_transaction_type_m_report3_idx` (`third_report`),
  KEY `fk_r_transaction_type_r_transaction_type1_idx` (`settlement_transaction_type`),
  CONSTRAINT `fk_r_transaction_type_m_report1` FOREIGN KEY (`first_report`) REFERENCES `m_report` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_r_transaction_type_m_report2` FOREIGN KEY (`second_report`) REFERENCES `m_report` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_r_transaction_type_m_report3` FOREIGN KEY (`third_report`) REFERENCES `m_report` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_r_transaction_type_m_store1` FOREIGN KEY (`in_store`) REFERENCES `m_store` (`hash`),
  CONSTRAINT `fk_r_transaction_type_m_store2` FOREIGN KEY (`out_store`) REFERENCES `m_store` (`hash`),
  CONSTRAINT `fk_r_transaction_type_r_transaction_type1` FOREIGN KEY (`settlement_transaction_type`) REFERENCES `r_transaction_type` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.r_transaction_type: ~1 rows (approximately)
/*!40000 ALTER TABLE `r_transaction_type` DISABLE KEYS */;
REPLACE INTO `r_transaction_type` (`code`, `name`, `form_type`, `type`, `item_in`, `item_out`, `in_store`, `out_store`, `fixed_price`, `price_type`, `percentage_discount`, `settlement_transaction`, `settlement_transaction_type`, `expire_date`, `client`, `supplier`, `print_first_report`, `first_report`, `print_second_report`, `second_report`, `print_third_report`, `third_report`, `credit_amount`, `debit_amount`, `payment`, `active`) VALUES
	('001', 'Sales', 'ITEM TRANSACTION', 'SALES', 0, 1, NULL, NULL, 0, 'SALES PRICE', 0, 0, NULL, 0, 1, 0, 0, NULL, 0, NULL, 0, NULL, 1, 0, 1, 1);
/*!40000 ALTER TABLE `r_transaction_type` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.r_user_role
DROP TABLE IF EXISTS `r_user_role`;
CREATE TABLE IF NOT EXISTS `r_user_role` (
  `code` varchar(10) NOT NULL,
  `name` varchar(25) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.r_user_role: ~0 rows (approximately)
/*!40000 ALTER TABLE `r_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `r_user_role` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.r_user_role_permission
DROP TABLE IF EXISTS `r_user_role_permission`;
CREATE TABLE IF NOT EXISTS `r_user_role_permission` (
  `user_role` varchar(10) NOT NULL,
  `permission` varchar(10) NOT NULL,
  PRIMARY KEY (`user_role`,`permission`),
  KEY `fk_user_role_permission_permission1_idx` (`permission`),
  CONSTRAINT `fk_user_role_permission_permission1` FOREIGN KEY (`permission`) REFERENCES `r_permission` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_role_permission_user_role1` FOREIGN KEY (`user_role`) REFERENCES `r_user_role` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.r_user_role_permission: ~0 rows (approximately)
/*!40000 ALTER TABLE `r_user_role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `r_user_role_permission` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.r_user_role_report
DROP TABLE IF EXISTS `r_user_role_report`;
CREATE TABLE IF NOT EXISTS `r_user_role_report` (
  `user_role` varchar(10) NOT NULL,
  `report` varchar(10) NOT NULL,
  PRIMARY KEY (`user_role`,`report`),
  KEY `fk_r_user_role_report_m_report1_idx` (`report`),
  CONSTRAINT `fk_r_user_role_report_m_report1` FOREIGN KEY (`report`) REFERENCES `m_report` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_r_user_role_report_r_user_role1` FOREIGN KEY (`user_role`) REFERENCES `r_user_role` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.r_user_role_report: ~0 rows (approximately)
/*!40000 ALTER TABLE `r_user_role_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `r_user_role_report` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.t_account_transaction
DROP TABLE IF EXISTS `t_account_transaction`;
CREATE TABLE IF NOT EXISTS `t_account_transaction` (
  `index_no` int(11) NOT NULL,
  `transaction_date` date DEFAULT NULL,
  `branch` varchar(10) DEFAULT NULL,
  `account_setting` varchar(25) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `account` varchar(10) DEFAULT NULL,
  `credit_amount` double DEFAULT NULL,
  `debit_amount` double DEFAULT NULL,
  `transaction` int(11) DEFAULT NULL,
  `transaction_type` varchar(25) DEFAULT NULL,
  `type` varchar(25) DEFAULT NULL,
  `status` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`index_no`),
  KEY `fk_account_transaction_idx` (`account`),
  KEY `fk_m_branch_idx` (`branch`),
  CONSTRAINT `fk_account_transaction` FOREIGN KEY (`account`) REFERENCES `m_account` (`code`),
  CONSTRAINT `fk_m_branch` FOREIGN KEY (`branch`) REFERENCES `m_branch` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.t_account_transaction: ~0 rows (approximately)
/*!40000 ALTER TABLE `t_account_transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_account_transaction` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.t_cheque
DROP TABLE IF EXISTS `t_cheque`;
CREATE TABLE IF NOT EXISTS `t_cheque` (
  `index_no` int(11) NOT NULL AUTO_INCREMENT,
  `branch` varchar(10) NOT NULL,
  `client` varchar(21) NOT NULL,
  `bank_branch` varchar(10) NOT NULL,
  `transaction_summary` int(11) NOT NULL,
  `account_no` varchar(25) NOT NULL,
  `cheque_no` varchar(25) NOT NULL,
  `amount` double NOT NULL,
  `cheque_date` date DEFAULT NULL,
  `deposit_date` varchar(45) DEFAULT NULL,
  `realize_date` date DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  `status` varchar(25) NOT NULL,
  PRIMARY KEY (`index_no`),
  KEY `fk_t_cheque_m_branch1_idx` (`branch`),
  KEY `fk_t_cheque_m_transactor1_idx` (`client`),
  KEY `fk_t_cheque_m_bank_branch1_idx` (`bank_branch`),
  KEY `fk_t_cheque_t_transaction_summary1_idx` (`transaction_summary`),
  CONSTRAINT `fk_t_cheque_m_bank_branch1` FOREIGN KEY (`bank_branch`) REFERENCES `m_bank_branch` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_cheque_m_branch1` FOREIGN KEY (`branch`) REFERENCES `m_branch` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_cheque_m_transactor1` FOREIGN KEY (`client`) REFERENCES `m_transactor` (`hash`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_cheque_t_transaction_summary1` FOREIGN KEY (`transaction_summary`) REFERENCES `t_transaction_summary` (`index_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.t_cheque: ~0 rows (approximately)
/*!40000 ALTER TABLE `t_cheque` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_cheque` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.t_item_movement
DROP TABLE IF EXISTS `t_item_movement`;
CREATE TABLE IF NOT EXISTS `t_item_movement` (
  `index_no` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_summary` int(11) DEFAULT NULL,
  `branch` varchar(10) DEFAULT NULL,
  `store` varchar(21) DEFAULT NULL,
  `item` varchar(21) DEFAULT NULL,
  `in_quantity` int(11) DEFAULT NULL,
  `out_quantity` int(11) DEFAULT NULL,
  `avarage_price` double DEFAULT NULL,
  `status` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`index_no`),
  KEY `fk_t_item_movement_m_store1_idx` (`store`),
  KEY `fk_t_item_movement_m_item1_idx` (`item`),
  KEY `fk_t_item_movement_t_transaction_summary1_idx` (`transaction_summary`),
  CONSTRAINT `fk_t_item_movement_m_item1` FOREIGN KEY (`item`) REFERENCES `m_item` (`hash`),
  CONSTRAINT `fk_t_item_movement_m_store1` FOREIGN KEY (`store`) REFERENCES `m_store` (`hash`),
  CONSTRAINT `fk_t_item_movement_t_transaction_summary1` FOREIGN KEY (`transaction_summary`) REFERENCES `t_transaction_summary` (`index_no`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.t_item_movement: ~8 rows (approximately)
/*!40000 ALTER TABLE `t_item_movement` DISABLE KEYS */;
REPLACE INTO `t_item_movement` (`index_no`, `transaction_summary`, `branch`, `store`, `item`, `in_quantity`, `out_quantity`, `avarage_price`, `status`) VALUES
	(1, 1, 'DEF', 'DEF-001', 'DEF-001001', 0, 10, 0, 'ACTIVE'),
	(2, 2, 'DEF', 'DEF-001', 'DEF-001001', 0, 10, 0, 'ACTIVE'),
	(3, 2, 'DEF', 'DEF-001', 'DEF-001001', 0, 3, 0, 'ACTIVE'),
	(4, 3, 'DEF', 'DEF-001', 'DEF-001001', 0, 8, 0, 'ACTIVE'),
	(5, 4, 'DEF', 'DEF-001', 'DEF-001001', 0, 33, 0, 'ACTIVE'),
	(6, 5, 'DEF', 'DEF-001', 'DEF-001001', 0, 10, 0, 'ACTIVE'),
	(7, 6, 'DEF', 'DEF-001', 'DEF-001001', 0, 35, 0, 'ACTIVE'),
	(8, 7, 'DEF', 'DEF-001', 'DEF-001001', 0, 9, 0, 'ACTIVE');
/*!40000 ALTER TABLE `t_item_movement` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.t_serial_movement
DROP TABLE IF EXISTS `t_serial_movement`;
CREATE TABLE IF NOT EXISTS `t_serial_movement` (
  `index_no` int(11) NOT NULL AUTO_INCREMENT,
  `branch` varchar(10) NOT NULL,
  `transaction_detail` int(11) NOT NULL,
  `item` varchar(21) NOT NULL,
  `serial_number` varchar(25) NOT NULL,
  `in_store` varchar(21) DEFAULT NULL,
  `out_store` varchar(21) DEFAULT NULL,
  `status` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`index_no`),
  KEY `fk_t_serial_movement_m_store1_idx` (`in_store`),
  KEY `fk_t_serial_movement_m_store2_idx` (`out_store`),
  KEY `fk_t_serial_movement_m_item1_idx` (`item`),
  KEY `fk_t_serial_movement_t_transaction_detail1_idx` (`transaction_detail`),
  CONSTRAINT `fk_t_serial_movement_m_item1` FOREIGN KEY (`item`) REFERENCES `m_item` (`hash`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_serial_movement_m_store1` FOREIGN KEY (`in_store`) REFERENCES `m_store` (`hash`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_serial_movement_m_store2` FOREIGN KEY (`out_store`) REFERENCES `m_store` (`hash`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_serial_movement_t_transaction_detail1` FOREIGN KEY (`transaction_detail`) REFERENCES `t_transaction_detail` (`index_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.t_serial_movement: ~0 rows (approximately)
/*!40000 ALTER TABLE `t_serial_movement` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_serial_movement` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.t_transaction_detail
DROP TABLE IF EXISTS `t_transaction_detail`;
CREATE TABLE IF NOT EXISTS `t_transaction_detail` (
  `index_no` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_summary` int(11) DEFAULT NULL,
  `item` varchar(21) DEFAULT NULL,
  `batch` varchar(32) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `expire_date` date DEFAULT NULL,
  `net_value` double DEFAULT NULL,
  `in_store` varchar(21) DEFAULT NULL,
  `out_store` varchar(21) DEFAULT NULL,
  `avarage_price` double DEFAULT NULL,
  PRIMARY KEY (`index_no`),
  KEY `fk_t_transaction_detail_m_store1_idx` (`in_store`),
  KEY `fk_t_transaction_detail_m_store2_idx` (`out_store`),
  KEY `fk_t_transaction_detail_m_item1_idx` (`item`),
  KEY `fk_t_transaction_detail_m_item_batch1_idx` (`batch`),
  KEY `fk_t_transaction_detail_t_transaction_summary1_idx` (`transaction_summary`),
  CONSTRAINT `fk_t_transaction_detail_m_item1` FOREIGN KEY (`item`) REFERENCES `m_item` (`hash`),
  CONSTRAINT `fk_t_transaction_detail_m_item_batch1` FOREIGN KEY (`batch`) REFERENCES `m_item_batch` (`hash`),
  CONSTRAINT `fk_t_transaction_detail_m_store1` FOREIGN KEY (`in_store`) REFERENCES `m_store` (`hash`),
  CONSTRAINT `fk_t_transaction_detail_m_store2` FOREIGN KEY (`out_store`) REFERENCES `m_store` (`hash`),
  CONSTRAINT `fk_t_transaction_detail_t_transaction_summary1` FOREIGN KEY (`transaction_summary`) REFERENCES `t_transaction_summary` (`index_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.t_transaction_detail: ~8 rows (approximately)
/*!40000 ALTER TABLE `t_transaction_detail` DISABLE KEYS */;
REPLACE INTO `t_transaction_detail` (`index_no`, `transaction_summary`, `item`, `batch`, `price`, `quantity`, `discount`, `expire_date`, `net_value`, `in_store`, `out_store`, `avarage_price`) VALUES
	(1, 1, 'DEF-001001', 'DEF-001001-1', 250, 10, 0, '2016-08-26', 2500, NULL, 'DEF-001', 0),
	(2, 2, 'DEF-001001', 'DEF-001001-1', 8, 3, 0, '2016-08-26', 24, NULL, 'DEF-001', 0),
	(3, 2, 'DEF-001001', 'DEF-001001-1', 5, 10, 0, '2016-08-26', 50, NULL, 'DEF-001', 0),
	(4, 3, 'DEF-001001', 'DEF-001001-1', 50, 8, 0, '2016-08-26', 400, NULL, 'DEF-001', 0),
	(5, 4, 'DEF-001001', 'DEF-001001-1', 258, 33, 0, '2016-08-26', 8514, NULL, 'DEF-001', 0),
	(6, 5, 'DEF-001001', 'DEF-001001-1', 87.5, 10, 0, '2016-08-26', 875, NULL, 'DEF-001', 0),
	(7, 6, 'DEF-001001', 'DEF-001001-1', 85, 35, 0, '2016-08-26', 2975, NULL, 'DEF-001', 0),
	(8, 7, 'DEF-001001', 'DEF-001001-1', 85, 9, 0, '2016-08-26', 765, NULL, 'DEF-001', 0);
/*!40000 ALTER TABLE `t_transaction_detail` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.t_transaction_settlement
DROP TABLE IF EXISTS `t_transaction_settlement`;
CREATE TABLE IF NOT EXISTS `t_transaction_settlement` (
  `index_no` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_summary` int(11) NOT NULL,
  `settle_transaction_summary` int(11) NOT NULL,
  `branch` varchar(10) NOT NULL,
  `amount` double NOT NULL,
  `status` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`index_no`),
  KEY `fk_t_transaction_settlement_t_transaction_summary1_idx` (`transaction_summary`),
  KEY `fk_t_transaction_settlement_t_transaction_summary2_idx` (`settle_transaction_summary`),
  CONSTRAINT `fk_t_transaction_settlement_t_transaction_summary1` FOREIGN KEY (`transaction_summary`) REFERENCES `t_transaction_summary` (`index_no`),
  CONSTRAINT `fk_t_transaction_settlement_t_transaction_summary2` FOREIGN KEY (`settle_transaction_summary`) REFERENCES `t_transaction_summary` (`index_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.t_transaction_settlement: ~0 rows (approximately)
/*!40000 ALTER TABLE `t_transaction_settlement` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_transaction_settlement` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.t_transaction_summary
DROP TABLE IF EXISTS `t_transaction_summary`;
CREATE TABLE IF NOT EXISTS `t_transaction_summary` (
  `index_no` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_type` varchar(10) NOT NULL,
  `transaction_no` int(11) NOT NULL,
  `branch` varchar(10) NOT NULL,
  `transaction_date` date NOT NULL,
  `reference_no` int(11) DEFAULT NULL,
  `in_store` varchar(21) DEFAULT NULL,
  `out_store` varchar(21) DEFAULT NULL,
  `transactor` varchar(21) DEFAULT NULL,
  `total_value` double NOT NULL,
  `item_discount` double NOT NULL,
  `special_discount` double NOT NULL,
  `net_total` double NOT NULL,
  `balance_amount` double NOT NULL,
  `cash_amount` double NOT NULL,
  `cheque_amount` double NOT NULL DEFAULT '0',
  `credit_amount` double NOT NULL,
  `session` int(11) DEFAULT NULL,
  `document_no` varchar(25) DEFAULT NULL,
  `description` varchar(25) DEFAULT NULL,
  `employee` varchar(21) DEFAULT NULL,
  `route` varchar(21) DEFAULT NULL,
  `status` varchar(25) NOT NULL,
  `action_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`index_no`),
  KEY `fk_t_transaction_summary_m_store1_idx` (`in_store`),
  KEY `fk_t_transaction_summary_m_store2_idx` (`out_store`),
  KEY `fk_t_transaction_summary_m_transactor1_idx` (`transactor`),
  KEY `fk_t_transaction_summary_r_transaction_type1_idx` (`transaction_type`),
  KEY `fk_t_transaction_summary_m_employee1_idx` (`employee`),
  KEY `fk_t_transaction_summary_m_route1_idx` (`route`),
  CONSTRAINT `fk_t_transaction_summary_m_employee1` FOREIGN KEY (`employee`) REFERENCES `m_employee` (`hash`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_transaction_summary_m_route1` FOREIGN KEY (`route`) REFERENCES `m_route` (`hash`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_transaction_summary_m_store1` FOREIGN KEY (`in_store`) REFERENCES `m_store` (`hash`),
  CONSTRAINT `fk_t_transaction_summary_m_store2` FOREIGN KEY (`out_store`) REFERENCES `m_store` (`hash`),
  CONSTRAINT `fk_t_transaction_summary_m_transactor1` FOREIGN KEY (`transactor`) REFERENCES `m_transactor` (`hash`),
  CONSTRAINT `fk_t_transaction_summary_r_transaction_type1` FOREIGN KEY (`transaction_type`) REFERENCES `r_transaction_type` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.t_transaction_summary: ~7 rows (approximately)
/*!40000 ALTER TABLE `t_transaction_summary` DISABLE KEYS */;
REPLACE INTO `t_transaction_summary` (`index_no`, `transaction_type`, `transaction_no`, `branch`, `transaction_date`, `reference_no`, `in_store`, `out_store`, `transactor`, `total_value`, `item_discount`, `special_discount`, `net_total`, `balance_amount`, `cash_amount`, `cheque_amount`, `credit_amount`, `session`, `document_no`, `description`, `employee`, `route`, `status`, `action_time`) VALUES
	(1, '001', 1, 'DEF', '2016-08-26', 0, NULL, 'DEF-001', 'DEF-C001', 2500, 0, 0, 2500, 2500, 0, 0, 2500, 0, '', '', 'DEF', 'DEF-001', 'ACTIVE', '2016-08-26 22:54:33'),
	(2, '001', 2, 'DEF', '2016-08-26', 0, NULL, 'DEF-001', 'DEF-C001', 74, 0, 0, 74, 74, 0, 0, 74, 0, '', '', 'DEF', 'DEF-001', 'ACTIVE', '2016-08-26 23:05:07'),
	(3, '001', 3, 'DEF', '2016-08-26', 0, NULL, 'DEF-001', 'DEF-C001', 400, 0, 0, 400, 400, 0, 0, 0, 0, '', '', 'DEF', 'DEF-001', 'ACTIVE', '2016-08-26 23:22:40'),
	(4, '001', 4, 'DEF', '2016-08-26', 0, NULL, 'DEF-001', 'DEF-C001', 8514, 0, 0, 8514, 8514, 0, 0, 0, 0, '', '', 'DEF', 'DEF-001', 'ACTIVE', '2016-08-26 23:24:39'),
	(5, '001', 5, 'DEF', '2016-08-26', 0, NULL, 'DEF-001', 'DEF-C001', 875, 0, 0, 875, 500, 375, 0, 500, 0, '', '', 'DEF', 'DEF-001', 'ACTIVE', '2016-08-26 23:25:56'),
	(6, '001', 6, 'DEF', '2016-08-26', 0, NULL, 'DEF-001', 'DEF-C001', 2975, 0, 0, 2975, 2000, 975, 0, 2000, 0, '', '', 'DEF', 'DEF-001', 'ACTIVE', '2016-08-26 23:30:10'),
	(7, '001', 7, 'DEF', '2016-08-26', 0, NULL, 'DEF-001', 'DEF-C001', 765, 0, 0, 765, 700, 65, 0, 700, 0, '', '', 'DEF', 'DEF-001', 'ACTIVE', '2016-08-26 23:31:16');
/*!40000 ALTER TABLE `t_transaction_summary` ENABLE KEYS */;


-- Dumping structure for table beta_pos_x.t_transactor_transaction
DROP TABLE IF EXISTS `t_transactor_transaction`;
CREATE TABLE IF NOT EXISTS `t_transactor_transaction` (
  `index_no` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_type` varchar(25) DEFAULT NULL,
  `transaction_summary` int(11) DEFAULT NULL,
  `transactor` varchar(21) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `debit_amount` double DEFAULT NULL,
  `credit_amount` double DEFAULT NULL,
  `status` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`index_no`),
  KEY `fk_t_transactor_transaction_m_transactor1_idx` (`transactor`),
  KEY `fk_t_transactor_transaction_t_transaction_summary1_idx` (`transaction_summary`),
  CONSTRAINT `fk_t_transactor_transaction_m_transactor1` FOREIGN KEY (`transactor`) REFERENCES `m_transactor` (`hash`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_transactor_transaction_t_transaction_summary1` FOREIGN KEY (`transaction_summary`) REFERENCES `t_transaction_summary` (`index_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Dumping data for table beta_pos_x.t_transactor_transaction: ~7 rows (approximately)
/*!40000 ALTER TABLE `t_transactor_transaction` DISABLE KEYS */;
REPLACE INTO `t_transactor_transaction` (`index_no`, `transaction_type`, `transaction_summary`, `transactor`, `description`, `debit_amount`, `credit_amount`, `status`) VALUES
	(1, '001', 1, 'DEF-C001', 'Sales', 0, 2500, 'ACTIVE'),
	(2, '001', 2, 'DEF-C001', 'Sales', 0, 74, 'ACTIVE'),
	(3, '001', 3, 'DEF-C001', 'Sales', 0, 400, 'ACTIVE'),
	(4, '001', 4, 'DEF-C001', 'Sales', 0, 8514, 'ACTIVE'),
	(5, '001', 5, 'DEF-C001', 'Sales', 0, 875, 'ACTIVE'),
	(6, '001', 6, 'DEF-C001', 'Sales', 0, 0, 'ACTIVE'),
	(7, '001', 7, 'DEF-C001', 'Sales', 0, 700, 'ACTIVE');
/*!40000 ALTER TABLE `t_transactor_transaction` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
