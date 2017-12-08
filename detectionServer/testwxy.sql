/*
Navicat MySQL Data Transfer

Source Server         : 192.168.101.42
Source Server Version : 50717
Source Host           : 192.168.101.42:3306
Source Database       : testwxy

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-12-08 09:28:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_cam
-- ----------------------------
DROP TABLE IF EXISTS `tb_cam`;
CREATE TABLE `tb_cam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `camid` char(18) COLLATE utf8_unicode_ci NOT NULL,
  `exposuretime` int(11) DEFAULT NULL,
  `status` tinyint(3) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `deprecatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_camid` (`camid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for tb_ctl
-- ----------------------------
DROP TABLE IF EXISTS `tb_ctl`;
CREATE TABLE `tb_ctl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ctlid` char(18) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `deprecatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_ctlid` (`ctlid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for tb_exec
-- ----------------------------
DROP TABLE IF EXISTS `tb_exec`;
CREATE TABLE `tb_exec` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `execid` char(18) COLLATE utf8_unicode_ci NOT NULL,
  `ctlid` char(18) COLLATE utf8_unicode_ci DEFAULT NULL,
  `camid` char(18) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` tinyint(3) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `deprecatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_execid` (`execid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for tb_proc
-- ----------------------------
DROP TABLE IF EXISTS `tb_proc`;
CREATE TABLE `tb_proc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `procid` char(18) COLLATE utf8_unicode_ci NOT NULL,
  `execid` char(18) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` tinyint(3) DEFAULT NULL,
  `filename` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `deprecatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_procid` (`procid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for tb_schedule
-- ----------------------------
DROP TABLE IF EXISTS `tb_sch`;
CREATE TABLE `tb_sch` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `schid` char(18) COLLATE utf8_unicode_ci NOT NULL,
  `execid` char(18) COLLATE utf8_unicode_ci NOT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `stoptime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `deprecatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_schid` (`schid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
