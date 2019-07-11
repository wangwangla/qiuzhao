/*
Navicat MySQL Data Transfer

Source Server         : www
Source Server Version : 50549
Source Host           : localhost:3306
Source Database       : jboot

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2019-06-03 21:02:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for commodity_info
-- ----------------------------
DROP TABLE IF EXISTS `commodity_info`;
CREATE TABLE `commodity_info` (
  `commodity_id` varchar(32) CHARACTER SET utf8 NOT NULL,
  `commodity_name` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `commodity_type` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `commodity_unit_price` decimal(11,2) DEFAULT NULL,
  `commodity_production_address` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`commodity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='商品信息\r\n';

-- ----------------------------
-- Records of commodity_info
-- ----------------------------
INSERT INTO `commodity_info` VALUES ('8dcfd8b0a19c4145926901f1499d01c8', '商品1', '供应商001', '100.00', '陕西西安');

-- ----------------------------
-- Table structure for contract
-- ----------------------------
DROP TABLE IF EXISTS `contract`;
CREATE TABLE `contract` (
  `contract_id` varchar(32) CHARACTER SET utf8 NOT NULL,
  `contract_name` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `contract_start_date` datetime DEFAULT NULL,
  `contract_end_date` datetime DEFAULT NULL,
  `contract_money` decimal(12,2) DEFAULT NULL,
  `contract_content` text CHARACTER SET utf8,
  `instinition_id` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `wd_id` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `jia_lead` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `install_date` datetime DEFAULT NULL,
  `enter` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `enter_date` datetime DEFAULT NULL,
  `contract_status` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`contract_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='合同';

-- ----------------------------
-- Records of contract
-- ----------------------------
INSERT INTO `contract` VALUES ('783caa11-a65d-48e8-b', '合同1', '2019-05-08 00:00:00', '2019-05-08 00:00:00', '1000.00', '无', 'c452670238b64140a6b90e9a57b08aaa', '2b84a213e5614bf6ab964a9d7e8011e1', '小陈', '2019-05-14 00:00:00', 'admin', '2019-05-18 09:06:39', '创建待执行');

-- ----------------------------
-- Table structure for enclosure
-- ----------------------------
DROP TABLE IF EXISTS `enclosure`;
CREATE TABLE `enclosure` (
  `enclosure_id` varchar(32) NOT NULL,
  `contract_id` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `image_path` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`enclosure_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enclosure
-- ----------------------------
INSERT INTO `enclosure` VALUES ('3c00009fab9d48a5ad02c9c88745507d', '07e70893-7add-4f17-8', '7114e109391-001969.jpg');
INSERT INTO `enclosure` VALUES ('873229faddd445e089b65e24020524d8', '07e70893-7add-4f17-8', '1628e109391-0019610.jpg');
INSERT INTO `enclosure` VALUES ('999858daa5394f41a2453421a8c7c747', '783caa11-a65d-48e8-b', 'da6b7109391-001968.jpg');
INSERT INTO `enclosure` VALUES ('dd07a6e64fee440885e92d9bad54755a', '783caa11-a65d-48e8-b', '21075109391-001967.jpg');

-- ----------------------------
-- Table structure for institution_detail_info
-- ----------------------------
DROP TABLE IF EXISTS `institution_detail_info`;
CREATE TABLE `institution_detail_info` (
  `wd_id` varchar(32) CHARACTER SET utf8 NOT NULL,
  `wd_name` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `wd_lead` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `wd_tel` varchar(32) DEFAULT NULL,
  `wd_address` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `wd_jd` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `wd_wd` varchar(255) DEFAULT NULL,
  `institution_id` varchar(32) DEFAULT NULL,
  `repair_num` int(10) DEFAULT NULL,
  `add_date` datetime DEFAULT NULL,
  `repair_date` datetime DEFAULT NULL,
  PRIMARY KEY (`wd_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='机构详情网点';

-- ----------------------------
-- Records of institution_detail_info
-- ----------------------------
INSERT INTO `institution_detail_info` VALUES ('2b84a213e5614bf6ab964a9d7e8011e1', '机构1--网点1', '小岑', '172', '陕西省山阳县', '23', '34', 'c452670238b64140a6b90e9a57b08aaa', '3', '2019-05-18 08:41:32', '2019-05-18 09:21:51');
INSERT INTO `institution_detail_info` VALUES ('5b1702b7c16847f4bc5d556bafef4a7b', '机构1--网点3', '侠士', '18263542363', '重庆', '34', '34', 'c452670238b64140a6b90e9a57b08aaa', '1', '2019-05-18 09:26:38', '2019-05-18 09:27:26');
INSERT INTO `institution_detail_info` VALUES ('c49840208dc2408cbc167840f03526d0', '机构2--网点1', '小于', '18253524252', '陕西西安', '27', '34', 'f4bbf22494fc4dba8f495756ded00a38', '1', '2019-05-18 09:22:53', '2019-05-18 09:24:47');
INSERT INTO `institution_detail_info` VALUES ('fb7e6181366247448138c273cdb0c5c8', '机构1--网点2', '小王', '1836426372552', '陕西咸阳', '34', '23', 'c452670238b64140a6b90e9a57b08aaa', '1', '2019-05-18 08:43:55', '2019-05-18 09:28:35');

-- ----------------------------
-- Table structure for institution_info
-- ----------------------------
DROP TABLE IF EXISTS `institution_info`;
CREATE TABLE `institution_info` (
  `institution_id` varchar(32) CHARACTER SET utf8 NOT NULL,
  `institution_name` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `institution_logo` varchar(32) DEFAULT NULL,
  `institution_wd_num` varchar(32) DEFAULT NULL,
  `institution_wd_id` int(32) DEFAULT NULL,
  PRIMARY KEY (`institution_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='机构信息';

-- ----------------------------
-- Records of institution_info
-- ----------------------------
INSERT INTO `institution_info` VALUES ('4c9271f94cf34badbe12f59cfa1126f0', 's ', 'e3440app_inco.png', '0', '0');
INSERT INTO `institution_info` VALUES ('c452670238b64140a6b90e9a57b08aaa', '维保机构---测试机构1', 'fd63a109391-001962.jpg', '3', '4');
INSERT INTO `institution_info` VALUES ('f4bbf22494fc4dba8f495756ded00a38', '维保机构--测试机构2', 'a96e5109391-001964.jpg', '1', '1');

-- ----------------------------
-- Table structure for service_order
-- ----------------------------
DROP TABLE IF EXISTS `service_order`;
CREATE TABLE `service_order` (
  `service_order_id` varchar(32) CHARACTER SET utf8 NOT NULL,
  `service_order_name` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `wd_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `jia_official` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `service_order_start_time` datetime DEFAULT NULL,
  `repair_cost` decimal(9,4) DEFAULT NULL,
  `order_category` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `order_work_time` datetime DEFAULT NULL,
  `order_finsh_time` datetime DEFAULT NULL,
  `order_status` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `order_enclosure_id` varchar(32) CHARACTER SET latin1 COLLATE latin1_bin DEFAULT NULL,
  `service_order_institution` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `rapair_staff` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`service_order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='服务订单';

-- ----------------------------
-- Records of service_order
-- ----------------------------
INSERT INTO `service_order` VALUES ('6202b8fc-060f-4a07-b', '维修订单01', '机构1--网点1', '小陈', '2019-05-18 09:00:42', '1000.0000', '平台下单', '2019-06-03 13:20:56', null, '创建处理', null, '维保机构---测试机构1', '小李');
INSERT INTO `service_order` VALUES ('97bf020e-4b48-43e3-b', '订单3', '机构1--网点1', '小陈', '2019-05-18 09:21:51', '288.0000', '自助下单', '2019-06-03 13:21:13', null, '创建处理', null, '维保机构---测试机构1', '小李');
INSERT INTO `service_order` VALUES ('b34dc508-50a0-47a1-9', '订单4', '机构2--网点1', '小时', '2019-05-18 09:24:47', '232.0000', '自助下单', null, null, '创建处理', null, '维保机构--测试机构2', '小完');
INSERT INTO `service_order` VALUES ('ea01e49a-e0de-4c9d-9', '订单5', '机构1--网点3', 'xoas', '2019-05-18 09:27:26', '3333.0000', '自助下单', null, null, '创建处理', null, '维保机构---测试机构1', '小陈');
INSERT INTO `service_order` VALUES ('f4aedf4a-0946-426f-a', '订单2', '机构1--网点1', '小陈', '2019-05-18 09:21:30', '1882.0000', '自助下单', null, null, '创建处理', null, '维保机构---测试机构1', '小春');

-- ----------------------------
-- Table structure for service_order_enclosure
-- ----------------------------
DROP TABLE IF EXISTS `service_order_enclosure`;
CREATE TABLE `service_order_enclosure` (
  `order_enclosure_id` char(32) NOT NULL,
  `order_enclosure_photo` char(100) DEFAULT NULL,
  `order_enclosure_up_time` char(32) DEFAULT NULL,
  PRIMARY KEY (`order_enclosure_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='服务订单附件';

-- ----------------------------
-- Records of service_order_enclosure
-- ----------------------------
INSERT INTO `service_order_enclosure` VALUES ('4e8d0dad322344fc98ed7b0099397114', '09414109391-001966.jpg', '6202b8fc-060f-4a07-b');
INSERT INTO `service_order_enclosure` VALUES ('696e1b06d1a241faa5a21aa68f6d99a8', '261b2109391-0019611.jpg', 'f4aedf4a-0946-426f-a');
INSERT INTO `service_order_enclosure` VALUES ('732ee769fd734df5a8c3ac3e21195645', 'd6ae8109391-0019613.jpg', 'b34dc508-50a0-47a1-9');
INSERT INTO `service_order_enclosure` VALUES ('d28a954a79db4c5bb6cc672d308444a5', '95a29109391-0019615.jpg', '9687e3ce-25b3-412f-9');
INSERT INTO `service_order_enclosure` VALUES ('dd1e8ff19d39414aa4f0daa12bfefec2', 'f90fd109391-0019614.jpg', 'ea01e49a-e0de-4c9d-9');
INSERT INTO `service_order_enclosure` VALUES ('e0c081a934864951a282ec5fd6c52f91', '44751109391-0019612.jpg', '97bf020e-4b48-43e3-b');

-- ----------------------------
-- Table structure for staff_info
-- ----------------------------
DROP TABLE IF EXISTS `staff_info`;
CREATE TABLE `staff_info` (
  `staff_id` varchar(32) NOT NULL,
  `staff_name` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `staff_birthday` datetime DEFAULT NULL,
  `staff_tel` char(16) DEFAULT NULL,
  `staff_address` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `staff_category` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `staff_photo` varchar(100) DEFAULT NULL,
  `staff_status` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='员工信息';

-- ----------------------------
-- Records of staff_info
-- ----------------------------
INSERT INTO `staff_info` VALUES ('1eb2ab42-0c13-44ee-b', '小李', '1999-05-12 00:00:00', '18392206122', '陕西西安', '普通员', 'eaddb109391-001961.jpg', '待命');
INSERT INTO `staff_info` VALUES ('76806733-3663-4deb-9', 's', '2019-06-12 00:00:00', '11123456789', 's', 's', 'fe6dbapp_inco2.png', '待命');
INSERT INTO `staff_info` VALUES ('78467072-7d38-4a95-8', '小王', '1995-05-18 00:00:00', '18567897532', '陕西西安', '普通员', '445bb109391-00196.jpg', '待命');

-- ----------------------------
-- Table structure for supplier_info
-- ----------------------------
DROP TABLE IF EXISTS `supplier_info`;
CREATE TABLE `supplier_info` (
  `supplier_id` varchar(32) NOT NULL,
  `supplier_name` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `supplier_address` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `supplier_lead` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `supplier_tel` varchar(15) DEFAULT NULL,
  `supplier_remark` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='供应商资料';

-- ----------------------------
-- Records of supplier_info
-- ----------------------------
INSERT INTO `supplier_info` VALUES ('7e63a18b5d7e46358fb07f22b742cb48', '供应商001', '陕西西安', '王城', '18643236783', '无');

-- ----------------------------
-- Table structure for sys_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_data`;
CREATE TABLE `sys_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) NOT NULL COMMENT '代码',
  `codeDesc` varchar(200) DEFAULT NULL COMMENT '代码描述',
  `type` varchar(20) DEFAULT NULL COMMENT '类型代码',
  `typeDesc` varchar(200) DEFAULT NULL COMMENT '类型描述',
  `status` varchar(2) DEFAULT NULL COMMENT '状态 0-未生效 1-已生效',
  `orderNo` varchar(255) DEFAULT NULL COMMENT '排序',
  `createDate` datetime DEFAULT NULL,
  `lastUpdAcct` varchar(20) DEFAULT NULL,
  `lastUpdTime` datetime DEFAULT NULL,
  `note` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `type` (`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_data
-- ----------------------------
INSERT INTO `sys_data` VALUES ('2', '1', '启用', 'STATUS_COMMON', '通用状态', '1', '2', '2016-09-28 11:00:56', 'sys', '2016-09-28 11:00:18', '初始数据');
INSERT INTO `sys_data` VALUES ('3', '0', '正常', 'STATUS_USER4S', '系统用户状态', '1', '1', '2016-09-28 11:20:03', 'sys', '2016-09-28 11:20:03', '添加数据字典');
INSERT INTO `sys_data` VALUES ('4', '2', '冻结', 'STATUS_USER4S', '系统用户状态', '1', '2', '2016-09-28 11:20:38', 'sys', '2017-10-17 10:35:59', '启用数据');
INSERT INTO `sys_data` VALUES ('5', '1', '菜单', 'TYPE_SYSTEM_RES', '资源类型1-菜单 2-功能', '1', '1', '2016-10-18 14:37:15', 'sys', '2016-10-18 14:37:15', '添加数据字典');
INSERT INTO `sys_data` VALUES ('6', '2', '功能', 'TYPE_SYSTEM_RES', '资源类型1-菜单 2-功能', '1', '2', '2016-10-18 14:38:15', 'sys', '2016-10-18 14:38:15', '添加数据字典');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) DEFAULT NULL,
  `browser` varchar(255) DEFAULT NULL,
  `operation` varchar(20) DEFAULT NULL COMMENT 'GET/POST',
  `from` varchar(255) DEFAULT NULL COMMENT '来源 url',
  `ip` varchar(200) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `status` varchar(2) DEFAULT '1' COMMENT '1-记录',
  `lastUpdAcct` varchar(20) DEFAULT NULL,
  `lastUpdTime` datetime DEFAULT NULL,
  `note` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_sys_EVENT` (`uid`) USING BTREE,
  CONSTRAINT `sys_log_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14034 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_res
-- ----------------------------
DROP TABLE IF EXISTS `sys_res`;
CREATE TABLE `sys_res` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) DEFAULT NULL,
  `name` varchar(111) DEFAULT NULL,
  `des` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `iconCls` varchar(255) DEFAULT 'am-icon-file',
  `seq` bigint(20) DEFAULT '1',
  `type` varchar(2) DEFAULT '2' COMMENT '1 功能 2 权限',
  `status` varchar(2) DEFAULT '1' COMMENT '1-启用 0-未启用',
  `lastUpdAcct` varchar(20) DEFAULT NULL,
  `lastUpdTime` datetime DEFAULT NULL,
  `note` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_res
-- ----------------------------
INSERT INTO `sys_res` VALUES ('1', '0', '维保机构', '系统管理', '#', '1', '&#xe614;', '1', '1', '1', 'admin', '2019-04-18 08:56:13', '停用系统资源');
INSERT INTO `sys_res` VALUES ('2', '1', '基础设置', '系统管理', '#', '2', '&#xe614;', '1', '1', '1', 'admin', '2016-10-25 10:47:23', '修改资源');
INSERT INTO `sys_res` VALUES ('3', '2', '仓库类型', '仓库类型', '/category/waregorycategory', '3', '&#xe614;', '1', '1', '1', 'admin', '2016-10-18 16:24:08', '添加资源');
INSERT INTO `sys_res` VALUES ('4', '2', '机构类型', '机构类型', '/category/institutioncategory', '3', '&#xe614;', '1', '1', '1', 'admin', '2016-10-18 16:24:08', '添加资源');
INSERT INTO `sys_res` VALUES ('5', '2', '员工类型', '员工类型', '/category/staffcategory', '3', '&#xe614;', '1', '1', '1', 'admin', '2016-10-18 16:24:08', '添加资源');
INSERT INTO `sys_res` VALUES ('6', '1', '维修工管理', '维修工管理', '#', '2', '&#xe614;', '1', '1', '1', 'admin', '2016-10-25 10:47:23', '修改资源');
INSERT INTO `sys_res` VALUES ('7', '6', '维修工信息', '维修工信息', '/staff/info', '3', '&#xe614;', '1', '1', '1', 'admin', '2016-10-18 16:24:08', '添加资源');
INSERT INTO `sys_res` VALUES ('8', '6', '维修工去向', '维修工去向', '/staff/info', '3', '&#xe614;', '1', '1', '1', 'admin', '2016-10-18 16:24:08', '添加资源');
INSERT INTO `sys_res` VALUES ('9', '1', '机构管理', '维修工管理', '#', '2', '&#xe614;', '1', '1', '1', 'admin', '2016-10-25 10:47:23', '修改资源');
INSERT INTO `sys_res` VALUES ('10', '9', '机构信息', '机构信息', '/institution/info', '3', '&#xe614;', '1', '1', '1', 'admin', '2016-10-18 16:24:08', '添加资源');
INSERT INTO `sys_res` VALUES ('11', '9', '待签约机构', '待签约机构', '/institution/waitorder', '3', '&#xe614;', '1', '1', '1', 'admin', '2016-10-18 16:24:08', '添加资源');
INSERT INTO `sys_res` VALUES ('12', '1', '服务订单', '服务订单', '#', '2', '&#xe614;', '1', '1', '1', 'admin', '2016-10-25 10:47:23', '修改资源');
INSERT INTO `sys_res` VALUES ('13', '12', '维修订单', '维修订单', '/service/order', '3', '&#xe614;', '1', '1', '1', 'admin', '2016-10-18 16:24:08', '添加资源');
INSERT INTO `sys_res` VALUES ('14', '12', '订单统计', '订单统计', '/service/statistics', '3', '&#xe614;', '1', '1', '1', 'admin', '2019-06-03 17:02:34', '修改系统资源');
INSERT INTO `sys_res` VALUES ('15', '1', '合同管理', '合同管理', '#', '2', '&#xe614;', '1', '1', '1', 'admin', '2016-10-25 10:47:23', '修改资源');
INSERT INTO `sys_res` VALUES ('16', '15', '网点参保合同', '网点参保合同', '/contract', '3', '&#xe614;', '1', '1', '1', 'admin', '2016-10-18 16:24:08', '添加资源');
INSERT INTO `sys_res` VALUES ('17', '15', '购买设备合同', '购买设备合同', '/system/res', '3', '&#xe614;', '1', '1', '1', 'admin', '2016-10-18 16:24:08', '添加资源');
INSERT INTO `sys_res` VALUES ('18', '1', '权限管理', '权限管理', '#', '2', '&#xe614;', '1', '1', '1', 'admin', '2016-10-25 10:47:23', '修改资源');
INSERT INTO `sys_res` VALUES ('19', '18', '日志管理', '日志管理', '/system/log', '3', '&#xe614;', '1', '1', '1', 'admin', '2016-10-18 16:24:08', '添加资源');
INSERT INTO `sys_res` VALUES ('20', '18', '数据备份', '数据备份', '/system/res', '3', '&#xe614;', '1', '1', '1', 'admin', '2016-10-18 16:24:08', '添加资源');
INSERT INTO `sys_res` VALUES ('21', '18', '资源管理', '资源管理', '/system/res', '3', '&#xe614;', '1', '1', '1', 'admin', '2016-10-18 16:24:08', '添加资源');
INSERT INTO `sys_res` VALUES ('22', '1', '资料管理', '资料管理', '#', '2', '&#xe614;', '1', '1', '1', 'admin', '2016-10-25 10:47:23', '修改资源');
INSERT INTO `sys_res` VALUES ('23', '22', '商品资料管理', '商品资料管理', '/commodity/info', '3', '&#xe614;', '1', '1', '1', 'admin', '2016-10-18 16:24:08', '添加资源');
INSERT INTO `sys_res` VALUES ('24', '22', '供应商资料', '供应商资料', '/supplier/info', '3', '&#xe614;', '1', '1', '1', 'admin', '2016-10-18 16:24:08', '添加资源');
INSERT INTO `sys_res` VALUES ('25', '22', '仓库信息', '仓库信息', '/warehouse/info', '3', '&#xe614;', '1', '1', '1', 'admin', '2016-10-18 16:24:08', '添加资源');
INSERT INTO `sys_res` VALUES ('26', '1', '仓库管理', '仓库管理', '#', '2', '&#xe614;', '1', '1', '1', 'admin', '2016-10-25 10:47:23', '修改资源');
INSERT INTO `sys_res` VALUES ('27', '26', '出库管理', '出库管理', '/warehouse/out', '3', '&#xe614;', '1', '1', '1', 'admin', '2016-10-18 16:24:08', '添加资源');
INSERT INTO `sys_res` VALUES ('28', '26', '入库管理', '入库管理', '/warehouse/into', '3', '&#xe614;', '1', '1', '1', 'admin', '2016-10-18 16:24:08', '添加资源');
INSERT INTO `sys_res` VALUES ('29', '26', '出库统计', '出库统计', '/warehouse/into/tongji', '3', '&#xe614;', '1', '1', '1', 'admin', '2019-06-03 20:16:59', '修改系统资源');
INSERT INTO `sys_res` VALUES ('30', '1', '款项管理', '款项管理', '#', '2', '&#xe614;', '1', '1', '1', 'admin', '2016-10-25 10:47:23', '修改资源');
INSERT INTO `sys_res` VALUES ('31', '30', '付款单管理', '付款单管理', '/system/res', '3', '&#xe614;', '1', '1', '1', 'admin', '2016-10-18 16:24:08', '添加资源');
INSERT INTO `sys_res` VALUES ('32', '30', '收款单管理', '收款单管理', '/system/res', '3', '&#xe614;', '1', '1', '1', 'admin', '2016-10-18 16:24:08', '添加资源');
INSERT INTO `sys_res` VALUES ('34', '9', '机构信息', '签约机构信息', '/institution', '3', '&#xe614;', '1', '1', '1', 'admin', '2016-10-18 16:24:08', '添加资源');
INSERT INTO `sys_res` VALUES ('35', '18', '角色管理', '角色管理', '/system/role', '3', '&#xe614;', '1', '1', '1', 'admin', '2016-10-18 16:24:08', '添加资源');
INSERT INTO `sys_res` VALUES ('36', '18', '用户管理', '用户管理', '/system/user', '3', '&#xe614;', '1', '1', '1', 'admin', '2016-10-18 16:24:08', '添加资源');
INSERT INTO `sys_res` VALUES ('41', '12', '服务明细-查看', '资源查看', '/service/order/wdetail', '4', '', '5', '2', '1', 'test01', '2019-04-18 10:26:58', '保存系统资源');
INSERT INTO `sys_res` VALUES ('53', '7', '维修工信息-添加资源', '维修工信息-增加', '/staff/info/add', '4', '', '0', '2', '1', 'admin', '2019-04-18 10:55:38', '保存系统资源');
INSERT INTO `sys_res` VALUES ('54', '7', '维修工信息-删除', '维修工信息-删除', '/staff/info/delete', '4', '', '2', '2', '1', 'admin', '2019-04-18 10:56:55', '保存系统资源');
INSERT INTO `sys_res` VALUES ('55', '7', '维修工信息-编辑', '维修工信息-编辑', '/staff/info/edit', '4', '', '3', '2', '1', 'admin', '2019-04-18 10:58:24', '保存系统资源');
INSERT INTO `sys_res` VALUES ('56', '10', '机构信息-增加', '机构信息-增加', '/institution/info', '4', '', '1', '2', '1', 'admin', '2019-04-18 11:00:51', '保存系统资源');
INSERT INTO `sys_res` VALUES ('57', '10', '机构信息-删除', '机构信息-删除', '/institution/info/delete', '4', '', '2', '2', '1', 'admin', '2019-04-18 11:01:55', '保存系统资源');
INSERT INTO `sys_res` VALUES ('58', '10', '机构信息-修改', '机构信息-修改', '/institution/info/postUpdate', '4', '', '3', '2', '1', 'admin', '2019-04-18 11:03:44', '保存系统资源');
INSERT INTO `sys_res` VALUES ('59', '10', '机构信息-增加网点', '机构信息-增加网点', '/institution/info/addwd', '4', '', '4', '2', '1', 'admin', '2019-04-18 11:05:07', '保存系统资源');
INSERT INTO `sys_res` VALUES ('60', '24', '供应商-增加', '供应商-增加', '/supplier/info/postadd', '4', '', '1', '2', '1', 'admin', '2019-04-18 11:07:05', '保存系统资源');
INSERT INTO `sys_res` VALUES ('61', '24', '供应商-删除', '供应商-删除', '/supplier/info/delete', '4', '', '1', '2', '1', 'admin', '2019-04-18 11:08:02', '保存系统资源');
INSERT INTO `sys_res` VALUES ('62', '24', '供应商-编辑', '供应商-编辑', '/supplier/info/edit', '4', '', '0', '2', '1', 'admin', '2019-04-18 11:08:49', '保存系统资源');
INSERT INTO `sys_res` VALUES ('63', '25', '仓库信息-增加', '仓库信息-增加', '/warehouse/info/add', '4', '', '2', '2', '1', 'admin', '2019-04-18 11:10:17', '保存系统资源');
INSERT INTO `sys_res` VALUES ('64', '25', '仓库信息-删除', '仓库信息-删除', '/warehouse/info/delete', '4', '', '2', '2', '1', 'admin', '2019-04-18 11:11:21', '保存系统资源');
INSERT INTO `sys_res` VALUES ('65', '25', '仓库信息-修改', '仓库信息-修改', '/warehouse/info/edit', '4', '', '3', '2', '1', 'admin', '2019-04-18 11:12:07', '保存系统资源');
INSERT INTO `sys_res` VALUES ('66', '23', '商品信息-删除', '商品信息-删除', '/commodity/info/delete', '4', '', '2', '2', '1', 'admin', '2019-04-18 11:13:57', '保存系统资源');
INSERT INTO `sys_res` VALUES ('67', '23', '商品信息-增加', '商品信息-增加', '/commodity/info/add', '4', '', '3', '2', '1', 'admin', '2019-04-18 11:15:54', '修改系统资源');
INSERT INTO `sys_res` VALUES ('68', '23', '商品信息-修改', '商品信息-增加', '/commodity/info/edit', '4', '', '3', '2', '1', 'admin', '2019-04-18 11:15:33', '保存系统资源');
INSERT INTO `sys_res` VALUES ('69', '13', '维修订单-增加', '维修订单-增加', '/service/order/add', '4', '', '1', '2', '1', 'admin', '2019-04-18 11:18:22', '修改系统资源');
INSERT INTO `sys_res` VALUES ('70', '13', '维修订单-删除', '维修订单-删除', '/service/order/delete', '4', '', '2', '2', '1', 'admin', '2019-04-18 11:18:06', '保存系统资源');
INSERT INTO `sys_res` VALUES ('71', '27', '出库信息-增加', '出库信息-增加', '/warehouse/out/add', '4', '', '1', '2', '1', 'admin', '2019-04-18 12:02:31', '保存系统资源');
INSERT INTO `sys_res` VALUES ('72', '27', '出库信息-删除', '出库信息-删除', '/warehouse/out/delete', '4', '', '2', '2', '1', 'admin', '2019-04-18 12:03:42', '保存系统资源');
INSERT INTO `sys_res` VALUES ('73', '27', '出库信息-修改', '出库信息-修改', '/warehouse/out', '4', '', '3', '2', '1', 'admin', '2019-04-18 12:04:11', '保存系统资源');
INSERT INTO `sys_res` VALUES ('74', '28', '入库信息-删除', '入库信息-删除', '/warehouse/into/delete', '4', '', '1', '2', '1', 'admin', '2019-04-18 12:06:21', '修改系统资源');
INSERT INTO `sys_res` VALUES ('75', '28', '入库信息-修改', '入库信息-修改', '/warehouse/into/edit', '4', '', '3', '2', '1', 'admin', '2019-04-18 12:05:56', '保存系统资源');
INSERT INTO `sys_res` VALUES ('76', '28', '入库信息-增加', '入库信息-增加', '/warehouse/into', '4', '', '3', '2', '1', 'admin', '2019-04-18 12:06:46', '保存系统资源');
INSERT INTO `sys_res` VALUES ('77', '16', '收入合同-增加', '收入合同-增加', '/contract/add', '4', '', '1', '2', '1', 'admin', '2019-04-18 12:08:45', '保存系统资源');
INSERT INTO `sys_res` VALUES ('78', '16', '收入合同-删除', '收入合同-删除', '/contract/delete', '4', '', '2', '2', '1', 'admin', '2019-04-18 12:09:20', '保存系统资源');
INSERT INTO `sys_res` VALUES ('79', '16', '收入合同-修改', '收入合同-修改', '/contract/edit', '4', '', '3', '2', '1', 'admin', '2019-04-18 12:10:05', '修改系统资源');
INSERT INTO `sys_res` VALUES ('80', '21', '资源管理-增加', '资源管理-增加', '/system/res/add', '4', '', '1', '2', '1', 'admin', '2019-04-18 12:12:33', '保存系统资源');
INSERT INTO `sys_res` VALUES ('81', '21', '资源管理-删除', '资源管理-删除', '/system/res/delete', '4', '', '2', '2', '1', 'admin', '2019-04-18 12:12:59', '保存系统资源');
INSERT INTO `sys_res` VALUES ('82', '21', '资源管理-编辑', '资源管理-编辑', '/system/res/edit', '4', '', '3', '2', '1', 'admin', '2019-04-18 12:13:53', '保存系统资源');
INSERT INTO `sys_res` VALUES ('83', '35', '角色管理-增加', '角色管理-增加', '/system/role/add', '4', '', '1', '2', '1', 'admin', '2019-04-19 07:17:27', '保存系统资源');
INSERT INTO `sys_res` VALUES ('84', '35', '角色管理-删除', '角色管理-删除', '/system/role/delete', '4', '', '2', '2', '1', 'admin', '2019-04-19 07:18:13', '保存系统资源');
INSERT INTO `sys_res` VALUES ('85', '35', '角色管理-修改', '角色管理-修改', '/system/role/edit', '4', '', '3', '2', '1', 'admin', '2019-04-19 07:19:26', '保存系统资源');
INSERT INTO `sys_res` VALUES ('86', '35', '角色管理-权限', '角色管理-权限', '/system/role/permission', '4', '', '4', '2', '1', 'admin', '2019-04-19 07:45:06', '保存系统资源');
INSERT INTO `sys_res` VALUES ('87', '36', '用户管理-增加', '用户管理-增加', '/system/user/add', '4', '', '1', '2', '1', 'admin', '2019-04-19 07:59:24', '保存系统资源');
INSERT INTO `sys_res` VALUES ('88', '36', '用户管理-编辑', '用户管理-编辑', '/system/user/edit', '4', '', '2', '2', '1', 'admin', '2019-04-19 08:00:29', '保存系统资源');
INSERT INTO `sys_res` VALUES ('89', '36', '用户管理-删除', '用户管理-删除', '/system/user/delete', '4', '', '3', '2', '1', 'admin', '2019-04-19 08:01:57', '保存系统资源');
INSERT INTO `sys_res` VALUES ('90', '36', '用户管理-解锁', '用户管理-解锁', '/system/user/use', '4', '', '4', '2', '1', 'admin', '2019-04-19 08:03:01', '保存系统资源');
INSERT INTO `sys_res` VALUES ('91', '36', '用户管理-锁定', '用户管理-锁定', '/system/user/unuse', '4', '', '5', '2', '1', 'admin', '2019-04-19 08:03:41', '保存系统资源');
INSERT INTO `sys_res` VALUES ('92', '10', '统计', '统计', '/institution/tongji', '4', '', '13', '2', '1', 'admin', '2019-06-03 20:57:44', '保存系统资源');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(55) DEFAULT NULL,
  `des` varchar(55) DEFAULT NULL,
  `seq` bigint(20) DEFAULT '1',
  `iconCls` varchar(55) DEFAULT 'status_online',
  `pid` bigint(20) DEFAULT '0',
  `createdate` datetime DEFAULT NULL,
  `status` varchar(2) DEFAULT '1' COMMENT '1-启用 0-未启用',
  `lastUpdAcct` varchar(20) DEFAULT NULL,
  `lastUpdTime` datetime DEFAULT NULL,
  `note` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', '管理员-系统最高权限拥有者', '1', 'status_online', '0', '2016-10-19 15:02:25', '1', 'admin', '2017-05-11 16:41:59', '更新角色');
INSERT INTO `sys_role` VALUES ('7', '测试01', '测试', '100', 'status_online', '0', '2019-04-16 10:01:30', '1', 'admin', '2018-01-11 13:18:55', '保存系统角色');

-- ----------------------------
-- Table structure for sys_role_res
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_res`;
CREATE TABLE `sys_role_res` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `res_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_sys_ROLE_RES_RES_ID` (`res_id`) USING BTREE,
  KEY `FK_sys_ROLE_RES_ROLE_ID` (`role_id`) USING BTREE,
  CONSTRAINT `sys_role_res_ibfk_1` FOREIGN KEY (`res_id`) REFERENCES `sys_res` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_role_res_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1118 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_res
-- ----------------------------
INSERT INTO `sys_role_res` VALUES ('832', '1', '7');
INSERT INTO `sys_role_res` VALUES ('833', '6', '7');
INSERT INTO `sys_role_res` VALUES ('834', '7', '7');
INSERT INTO `sys_role_res` VALUES ('835', '9', '7');
INSERT INTO `sys_role_res` VALUES ('836', '10', '7');
INSERT INTO `sys_role_res` VALUES ('837', '12', '7');
INSERT INTO `sys_role_res` VALUES ('838', '13', '7');
INSERT INTO `sys_role_res` VALUES ('839', '14', '7');
INSERT INTO `sys_role_res` VALUES ('840', '41', '7');
INSERT INTO `sys_role_res` VALUES ('841', '15', '7');
INSERT INTO `sys_role_res` VALUES ('842', '16', '7');
INSERT INTO `sys_role_res` VALUES ('843', '17', '7');
INSERT INTO `sys_role_res` VALUES ('844', '18', '7');
INSERT INTO `sys_role_res` VALUES ('845', '21', '7');
INSERT INTO `sys_role_res` VALUES ('846', '80', '7');
INSERT INTO `sys_role_res` VALUES ('847', '81', '7');
INSERT INTO `sys_role_res` VALUES ('848', '82', '7');
INSERT INTO `sys_role_res` VALUES ('849', '35', '7');
INSERT INTO `sys_role_res` VALUES ('850', '83', '7');
INSERT INTO `sys_role_res` VALUES ('851', '84', '7');
INSERT INTO `sys_role_res` VALUES ('852', '85', '7');
INSERT INTO `sys_role_res` VALUES ('853', '86', '7');
INSERT INTO `sys_role_res` VALUES ('854', '36', '7');
INSERT INTO `sys_role_res` VALUES ('855', '87', '7');
INSERT INTO `sys_role_res` VALUES ('856', '88', '7');
INSERT INTO `sys_role_res` VALUES ('857', '89', '7');
INSERT INTO `sys_role_res` VALUES ('858', '90', '7');
INSERT INTO `sys_role_res` VALUES ('859', '91', '7');
INSERT INTO `sys_role_res` VALUES ('860', '22', '7');
INSERT INTO `sys_role_res` VALUES ('861', '23', '7');
INSERT INTO `sys_role_res` VALUES ('862', '24', '7');
INSERT INTO `sys_role_res` VALUES ('863', '25', '7');
INSERT INTO `sys_role_res` VALUES ('864', '26', '7');
INSERT INTO `sys_role_res` VALUES ('865', '27', '7');
INSERT INTO `sys_role_res` VALUES ('866', '28', '7');
INSERT INTO `sys_role_res` VALUES ('1054', '1', '1');
INSERT INTO `sys_role_res` VALUES ('1055', '6', '1');
INSERT INTO `sys_role_res` VALUES ('1056', '7', '1');
INSERT INTO `sys_role_res` VALUES ('1057', '53', '1');
INSERT INTO `sys_role_res` VALUES ('1058', '54', '1');
INSERT INTO `sys_role_res` VALUES ('1059', '55', '1');
INSERT INTO `sys_role_res` VALUES ('1060', '9', '1');
INSERT INTO `sys_role_res` VALUES ('1061', '10', '1');
INSERT INTO `sys_role_res` VALUES ('1062', '56', '1');
INSERT INTO `sys_role_res` VALUES ('1063', '57', '1');
INSERT INTO `sys_role_res` VALUES ('1064', '58', '1');
INSERT INTO `sys_role_res` VALUES ('1065', '59', '1');
INSERT INTO `sys_role_res` VALUES ('1066', '92', '1');
INSERT INTO `sys_role_res` VALUES ('1067', '12', '1');
INSERT INTO `sys_role_res` VALUES ('1068', '13', '1');
INSERT INTO `sys_role_res` VALUES ('1069', '69', '1');
INSERT INTO `sys_role_res` VALUES ('1070', '70', '1');
INSERT INTO `sys_role_res` VALUES ('1071', '14', '1');
INSERT INTO `sys_role_res` VALUES ('1072', '41', '1');
INSERT INTO `sys_role_res` VALUES ('1073', '15', '1');
INSERT INTO `sys_role_res` VALUES ('1074', '16', '1');
INSERT INTO `sys_role_res` VALUES ('1075', '77', '1');
INSERT INTO `sys_role_res` VALUES ('1076', '78', '1');
INSERT INTO `sys_role_res` VALUES ('1077', '79', '1');
INSERT INTO `sys_role_res` VALUES ('1078', '18', '1');
INSERT INTO `sys_role_res` VALUES ('1079', '19', '1');
INSERT INTO `sys_role_res` VALUES ('1080', '21', '1');
INSERT INTO `sys_role_res` VALUES ('1081', '80', '1');
INSERT INTO `sys_role_res` VALUES ('1082', '81', '1');
INSERT INTO `sys_role_res` VALUES ('1083', '82', '1');
INSERT INTO `sys_role_res` VALUES ('1084', '35', '1');
INSERT INTO `sys_role_res` VALUES ('1085', '83', '1');
INSERT INTO `sys_role_res` VALUES ('1086', '84', '1');
INSERT INTO `sys_role_res` VALUES ('1087', '85', '1');
INSERT INTO `sys_role_res` VALUES ('1088', '86', '1');
INSERT INTO `sys_role_res` VALUES ('1089', '36', '1');
INSERT INTO `sys_role_res` VALUES ('1090', '87', '1');
INSERT INTO `sys_role_res` VALUES ('1091', '88', '1');
INSERT INTO `sys_role_res` VALUES ('1092', '89', '1');
INSERT INTO `sys_role_res` VALUES ('1093', '90', '1');
INSERT INTO `sys_role_res` VALUES ('1094', '91', '1');
INSERT INTO `sys_role_res` VALUES ('1095', '22', '1');
INSERT INTO `sys_role_res` VALUES ('1096', '23', '1');
INSERT INTO `sys_role_res` VALUES ('1097', '66', '1');
INSERT INTO `sys_role_res` VALUES ('1098', '67', '1');
INSERT INTO `sys_role_res` VALUES ('1099', '68', '1');
INSERT INTO `sys_role_res` VALUES ('1100', '24', '1');
INSERT INTO `sys_role_res` VALUES ('1101', '62', '1');
INSERT INTO `sys_role_res` VALUES ('1102', '60', '1');
INSERT INTO `sys_role_res` VALUES ('1103', '61', '1');
INSERT INTO `sys_role_res` VALUES ('1104', '25', '1');
INSERT INTO `sys_role_res` VALUES ('1105', '63', '1');
INSERT INTO `sys_role_res` VALUES ('1106', '64', '1');
INSERT INTO `sys_role_res` VALUES ('1107', '65', '1');
INSERT INTO `sys_role_res` VALUES ('1108', '26', '1');
INSERT INTO `sys_role_res` VALUES ('1109', '27', '1');
INSERT INTO `sys_role_res` VALUES ('1110', '71', '1');
INSERT INTO `sys_role_res` VALUES ('1111', '72', '1');
INSERT INTO `sys_role_res` VALUES ('1112', '73', '1');
INSERT INTO `sys_role_res` VALUES ('1113', '28', '1');
INSERT INTO `sys_role_res` VALUES ('1114', '74', '1');
INSERT INTO `sys_role_res` VALUES ('1115', '75', '1');
INSERT INTO `sys_role_res` VALUES ('1116', '76', '1');
INSERT INTO `sys_role_res` VALUES ('1117', '29', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(55) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `status` varchar(2) DEFAULT '1' COMMENT '#1 正常 2.封号 ',
  `icon` varchar(255) DEFAULT 'images/guest.jpg',
  `email` varchar(255) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `salt2` varchar(55) DEFAULT NULL,
  `onlineStatus` varchar(2) DEFAULT NULL COMMENT '在线状态  1-在线 0-离线',
  `lastUpdAcct` varchar(20) DEFAULT NULL,
  `lastUpdTime` datetime DEFAULT NULL,
  `note` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('3', 'admin', '8b276c6430bc6cfec325a09dc6713167', '1', 'images/guest.jpg', '1233', '2017-05-11 15:54:56', '123', '66432cd44c4abf25202aca49a5e14436', null, 'admin', '2019-05-18 08:22:48', '解锁系统用户');
INSERT INTO `sys_user` VALUES ('8', 'ceshi', '84c02ca5a49ef33b2fa070965a34677f', '1', 'images/guest.jpg', null, '2019-04-19 07:33:23', null, '3aa4ce25e04180eb99e3a2624c79e2ea', '0', 'admin', '2019-05-18 08:22:37', '解锁系统用户');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_SYSTME_USER_ROLE_USER_ID` (`user_id`) USING BTREE,
  KEY `FK_SYSTME_USER_ROLE_ROLE_ID` (`role_id`) USING BTREE,
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('21', '3', '1');
INSERT INTO `sys_user_role` VALUES ('24', '8', '7');

-- ----------------------------
-- Table structure for waregouse_into
-- ----------------------------
DROP TABLE IF EXISTS `waregouse_into`;
CREATE TABLE `waregouse_into` (
  `eqiup_id` varchar(32) CHARACTER SET utf8 NOT NULL,
  `eqiup_type` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `eqiup_xinghao` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `eqiup_name` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `eqiup_in_date` datetime DEFAULT NULL,
  `unit_price` decimal(32,3) DEFAULT NULL,
  `equip_status` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `equip_lq_lead` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `equip_lq_date` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `equip_lead` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `equip_date` datetime DEFAULT NULL,
  PRIMARY KEY (`eqiup_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='入库';

-- ----------------------------
-- Records of waregouse_into
-- ----------------------------
INSERT INTO `waregouse_into` VALUES ('88908e928038463f80d352f71d369835', 'ESP3033-334', 'RRTD', 'ESP芯片', '2019-05-16 00:00:00', '1000.000', '设备入库', '小陈', '临潼仓库', 'admin', '2019-05-16 00:00:00');

-- ----------------------------
-- Table structure for waregouse_out
-- ----------------------------
DROP TABLE IF EXISTS `waregouse_out`;
CREATE TABLE `waregouse_out` (
  `equip_id` varchar(32) NOT NULL,
  `equip_type` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `equip_xinghao` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `equip_name` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `equip_in_date` datetime DEFAULT NULL,
  `equip_status` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `equip_lq_lead` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `equip_lq_date` datetime DEFAULT NULL,
  `equip_lead` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `equip_out_date` datetime DEFAULT NULL,
  `hetong` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`equip_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of waregouse_out
-- ----------------------------
INSERT INTO `waregouse_out` VALUES ('07e70893-7add-4f17-8', 'ESP)#', 'DHE', 'ESP', '2019-05-18 00:00:00', '0', '1000', '2019-05-18 09:12:13', 'admin', '2019-04-30 00:00:00', null);

-- ----------------------------
-- Table structure for warehouse_info
-- ----------------------------
DROP TABLE IF EXISTS `warehouse_info`;
CREATE TABLE `warehouse_info` (
  `waregouse_id` varchar(32) NOT NULL,
  `waregouse_name` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `waregouse_type` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `waregouse_lead` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `waregouse_remark` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`waregouse_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='仓库信息';

-- ----------------------------
-- Records of warehouse_info
-- ----------------------------
INSERT INTO `warehouse_info` VALUES ('5db786fb7d8247b78cb591aea9956760', '临潼仓库', '基础设备', '小陈', '无');

-- ----------------------------
-- Table structure for wl_use
-- ----------------------------
DROP TABLE IF EXISTS `wl_use`;
CREATE TABLE `wl_use` (
  `wl_id` varchar(32) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `wl_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`wl_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of wl_use
-- ----------------------------

-- ----------------------------
-- Function structure for querySysRes
-- ----------------------------
DROP FUNCTION IF EXISTS `querySysRes`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `querySysRes`(treeCode INT) RETURNS varchar(4000) CHARSET utf8
    DETERMINISTIC
BEGIN

    DECLARE sTemp VARCHAR(4000);
    DECLARE sTempChd VARCHAR(1000);

    SET sTemp = '$';

    select id  into sTempChd from sys_res t where t.id = treeCode;

    WHILE sTempChd is not null DO
      IF (sTempChd != treeCode) THEN
        SET sTemp = concat(sTemp,',',sTempChd);
      END IF;
      SELECT group_concat(id) INTO sTempChd FROM sys_res where FIND_IN_SET(pid,sTempChd)>0;
    END WHILE;
    RETURN sTemp;
  END
;;
DELIMITER ;
