Drop DATABASE IF EXISTS `ubao`;
CREATE DATABASE  IF NOT EXISTS `ubao`  DEFAULT CHARACTER SET utf8;
GRANT ALL PRIVILEGES ON ubao.* TO pc@localhost IDENTIFIED BY 'pc';
USE `ubao`;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(128) NOT NULL DEFAULT '0',
  `email` varchar(128) DEFAULT NULL,
  `signIp` varchar(128) DEFAULT NULL,
  `state` varchar(64) DEFAULT 'ok',
  `addTime` datetime DEFAULT NULL,
  `signTime` datetime DEFAULT NULL,
  `retry` int(11) DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_username` (`username`, `email`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `code`;
CREATE TABLE `code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `type` int(11) NOT NULL,
  `keyword` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type_UNIQUE` (`code`, `type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `style`;
CREATE TABLE `style` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clazz1` int(11) NOT NULL,
  `clazz2` int(11) NOT NULL,
  `series` int(11) NOT NULL,
  `code` varchar(128) NOT NULL,
  `name` varchar(45) NOT NULL,
  `comment` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `foreign_key_type1_type2` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) NOT NULL,
  `total` int(11) DEFAULT '0',
  `locked` int(11) DEFAULT '0',
  `code` varchar(45) NOT NULL,
  `series` int(11) NOT NULL,
  `clazz1` int(11) NOT NULL,
  `clazz2` int(11) NOT NULL,
  `createBy` int(11) DEFAULT NULL,
  `updateBy` int(11) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `foreign_key_user_idx` (`user`),
  KEY `foreign_key_createby_idx` (`createBy`),
  KEY `foreign_key_updateby_idx` (`updateBy`),
  CONSTRAINT `foreign_key_updateby` FOREIGN KEY (`updateBy`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `foreign_key_createby` FOREIGN KEY (`createBy`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `foreign_key_user` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  UNIQUE KEY `foreign_key_type1_type2` (`user`,`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(128) NOT NULL,
  `user` int(11) NOT NULL,
  `code` varchar(128) NOT NULL,
  `total` int(11) NOT NULL,
  `begin` int(11) NOT NULL,
  `end` int(11) NOT NULL,
  `beginTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `state` int(11) NOT NULL DEFAULT '1',
  `addTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `foreign_key_user_idx` (`user`),
  KEY `foreign_key_code_key_idx` (`code`),
  CONSTRAINT `foreign_key_codes` FOREIGN KEY (`code`) REFERENCES `style` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `foreign_key_users` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  UNIQUE KEY `foreign_key_code_url` (`url`,`code`,`addTime`, `user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `task-detail`;
CREATE TABLE `taskdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task` int(11) NOT NULL,
  `account` int(11) NOT NULL,
  `locked` int(11) NOT NULL DEFAULT '0',
  `addTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `foreign_key_task_idx` (`task`),
  KEY `foreign_key_account_idx` (`account`),
  CONSTRAINT `foreign_key_task` FOREIGN KEY (`task`) REFERENCES `task` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `foreign_key_account` FOREIGN KEY (`account`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `video`;
CREATE TABLE `video` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(128) NOT NULL,
  `videoId` varchar(64) NOT NULL,
  `videoId2` varchar(64) NOT NULL,
  `catId` int(11) DEFAULT NULL,
  `playmode` int(11) DEFAULT '1',
  `showId` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `foreign_key_url` (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
