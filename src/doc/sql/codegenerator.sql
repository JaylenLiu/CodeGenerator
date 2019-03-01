/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : qwe

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-03-01 09:40:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for agile_component
-- ----------------------------
DROP TABLE IF EXISTS `agile_component`;
CREATE TABLE `agile_component` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `entity_id` bigint(20) DEFAULT NULL COMMENT '实体id',
  `keyname_id` bigint(20) DEFAULT NULL COMMENT '键名id',
  `field_name` varchar(64) DEFAULT NULL COMMENT '字段名称',
  `label` varchar(128) DEFAULT NULL COMMENT '标签名',
  `default_value` varchar(64) DEFAULT NULL,
  `component_type` varchar(64) DEFAULT NULL COMMENT '组件类型',
  `placeholder` varchar(64) DEFAULT NULL COMMENT '提示语',
  `is_disabled` bit(1) DEFAULT NULL COMMENT '默认值',
  `is_visibled` bit(1) DEFAULT NULL,
  `is_required` bit(1) DEFAULT NULL,
  `is_query` bit(1) DEFAULT NULL COMMENT '是否查询条件',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of agile_component
-- ----------------------------

-- ----------------------------
-- Table structure for agile_entity
-- ----------------------------
DROP TABLE IF EXISTS `agile_entity`;
CREATE TABLE `agile_entity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `schema_id` bigint(20) DEFAULT NULL COMMENT '关联schema_id',
  `con_id` bigint(20) DEFAULT NULL COMMENT '数据库连接id',
  `database_name` varchar(64) DEFAULT NULL COMMENT '数据库名称',
  `parent_entity_id` bigint(20) DEFAULT NULL COMMENT '父实体id',
  `entity_type` int(11) DEFAULT NULL COMMENT '实体类型：1：主实体',
  `class_name` varchar(32) DEFAULT NULL COMMENT '类名',
  `table_name` varchar(32) DEFAULT NULL COMMENT '表名',
  `parent_field` varchar(32) DEFAULT NULL,
  `relation_field` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of agile_entity
-- ----------------------------

-- ----------------------------
-- Table structure for agile_schema
-- ----------------------------
DROP TABLE IF EXISTS `agile_schema`;
CREATE TABLE `agile_schema` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `schema_name` varchar(32) DEFAULT NULL COMMENT '方案名称',
  `module_name` varchar(32) DEFAULT NULL COMMENT '模块名称',
  `module_desc` varchar(255) DEFAULT NULL COMMENT '模块描述',
  `package_path` varchar(64) DEFAULT NULL COMMENT '包路径',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '最近更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of agile_schema
-- ----------------------------

-- ----------------------------
-- Table structure for database_connection
-- ----------------------------
DROP TABLE IF EXISTS `database_connection`;
CREATE TABLE `database_connection` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `conn_name` varchar(64) DEFAULT NULL,
  `conn_type` int(11) DEFAULT NULL COMMENT '1:mysql,2:sql server 3:oracle',
  `conn_ip` varchar(64) DEFAULT NULL,
  `conn_port` int(11) DEFAULT NULL,
  `conn_username` varchar(64) DEFAULT NULL,
  `conn_pwd` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of database_connection
-- ----------------------------

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(16) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户名',
  `invoker` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '调用者',
  `log_type` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '类型：save,update,delete',
  `content` text CHARACTER SET utf8 COMMENT '内容',
  `create_time` datetime DEFAULT NULL,
  `remark` text CHARACTER SET utf8,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('1', 'test', 'Message cn.pioneer.pws.service.impl.ProjectServiceImpl.saveProject(Project)', 'insert', 'params:cn.pioneer.pws.entity.Project@32bddbf7;result:Message{message=\'success\', ret=200, data={id=1}, pages=0}', '2018-08-30 15:02:38', null);
INSERT INTO `log` VALUES ('2', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"interestRate\":0.6,\"proId\":1,\"costType\":6,\"D_2018_3\":-1260.0,\"D_2018_2\":-360.0,\"D_2018_1\":-180.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-30 15:02:56', null);
INSERT INTO `log` VALUES ('3', 'test', 'Message cn.pioneer.pws.service.impl.CashFlowServiceImpl.saveCashFlow(Map)', 'insert', 'params:{itemName=123, itemContent=123, amount=3000, itemType=1, proId=1, D_2018_3=70, D_2018_2=20, D_2018_1=10};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-30 15:02:56', null);
INSERT INTO `log` VALUES ('4', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.updatePlanCost(String)', 'update', 'params:{\"interestRate\":0.6,\"proId\":1,\"costType\":6,\"D_2018_3\":-360.0,\"id\":1,\"D_2018_2\":0.0,\"D_2018_1\":360.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-30 15:03:09', null);
INSERT INTO `log` VALUES ('5', 'test', 'Message cn.pioneer.pws.service.impl.CashFlowServiceImpl.saveCashFlow(Map)', 'insert', 'params:{itemName=456, itemContent=45, amount=3000, itemType=2, proId=1, D_2018_3=50, D_2018_2=20, D_2018_1=30};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-30 15:03:09', null);
INSERT INTO `log` VALUES ('6', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.updatePadCost(PlanCost)', 'update', 'params:cn.pioneer.pws.entity.PlanCost@15577be0;result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-30 15:13:20', null);
INSERT INTO `log` VALUES ('7', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.updatePlanCost(String)', 'update', 'params:{\"interestRate\":0.6,\"proId\":1,\"costType\":6,\"D_2018_3\":360.0,\"id\":1,\"D_2018_2\":0.0,\"D_2018_1\":180.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-30 15:13:50', null);
INSERT INTO `log` VALUES ('8', 'test', 'Message cn.pioneer.pws.service.impl.CashFlowServiceImpl.updateCashFlow(Map)', 'update', 'params:{itemName=123, itemContent=123, itemType=1, amount=3000, proId=1, D_2018_3=30, id=1, D_2018_2=20, D_2018_1=20};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-30 15:13:50', null);
INSERT INTO `log` VALUES ('9', 'test', 'Message cn.pioneer.pws.service.impl.ProfitServiceImpl.updateProfit(Profit)', 'update', 'params:Profit{id=1, proId=1, serviceItem=\'213\', productLine=\'数据中心规划与设计服务\', revenueRecMethod=\'null\', quotationAmount=1000, taxRate=12.0, deductionAmount=892.86, costExcludeTax=540, riskRatio=5.0, riskAmount=27.00, grossProfit=325.86, grossMargin=36.5, deductibleVatCost=null, businessSurtax=null, adjustedGrossProfit=null, adjustedGrossMargin=null, createTime=Thu Aug 30 15:13:20 CST 2018, updateTime=Thu Aug 30 15:14:34 CST 2018, remark=\'null\'};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-30 15:14:34', null);
INSERT INTO `log` VALUES ('10', '管理员', 'Message cn.pioneer.pws.service.impl.AccountServiceImpl.updatePassword(Long[])', 'update', 'params:[Ljava.lang.Long;@65c15051;result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-30 16:11:40', null);
INSERT INTO `log` VALUES ('11', '管理员', 'Message cn.pioneer.pws.service.impl.AccountServiceImpl.updatePassword(Long[])', 'update', 'params:[Ljava.lang.Long;@28271956;result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-30 16:14:04', null);
INSERT INTO `log` VALUES ('12', '管理员', 'Message cn.pioneer.pws.service.impl.AccountServiceImpl.updatePassword(Long[])', 'update', 'params:[Ljava.lang.Long;@4cfe5a30;result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-30 16:16:12', null);
INSERT INTO `log` VALUES ('13', '管理员', 'Message cn.pioneer.pws.service.impl.AccountServiceImpl.updatePassword(Long[])', 'update', 'params:[Ljava.lang.Long;@22384945;result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 09:14:41', null);
INSERT INTO `log` VALUES ('14', '管理员', 'Message cn.pioneer.pws.service.impl.AccountServiceImpl.updatePassword(Long[])', 'update', 'params:[Ljava.lang.Long;@416082a2;result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 09:20:29', null);
INSERT INTO `log` VALUES ('15', '管理员', 'Message cn.pioneer.pws.service.impl.AccountServiceImpl.updatePassword(Long[])', 'update', 'params:[Ljava.lang.Long;@4ff61ca;result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 09:23:07', null);
INSERT INTO `log` VALUES ('16', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"企业应用服务\",\"serviceItem\":\"excelTest\",\"costType\":1,\"proId\":1,\"D_2018_3\":3.0,\"remark\":\"\",\"staffLevel\":\"\",\"D_2018_2\":2.0,\"D_2018_1\":1.0};result:Message{message=\'该人员级别不存在！\', ret=500, data=null, pages=0}', '2018-08-31 10:18:07', null);
INSERT INTO `log` VALUES ('17', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"企业应用服务\",\"serviceItem\":\"excelTest\",\"supplier\":\"盘古服务公司\",\"costType\":3,\"proId\":1,\"D_2018_3\":300.0,\"remark\":\"\",\"D_2018_2\":200.0,\"D_2018_1\":100.0};result:Message{message=\'该产品线不存在！\', ret=500, data=null, pages=0}', '2018-08-31 10:18:09', null);
INSERT INTO `log` VALUES ('18', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"企业应用服务\",\"serviceItem\":\"excelTest\",\"supplier\":\"盘古服务公司\",\"costType\":4,\"proId\":1,\"D_2018_3\":300.0,\"remark\":\"\",\"D_2018_2\":200.0,\"D_2018_1\":100.0};result:Message{message=\'该产品线不存在！\', ret=500, data=null, pages=0}', '2018-08-31 10:20:14', null);
INSERT INTO `log` VALUES ('19', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"企业应用服务\",\"serviceItem\":\"excelTest\",\"decription\":\"\",\"costType\":5,\"proId\":1,\"D_2018_3\":300.0,\"remark\":\"\",\"D_2018_2\":200.0,\"D_2018_1\":100.0};result:Message{message=\'该产品线不存在！\', ret=500, data=null, pages=0}', '2018-08-31 10:20:16', null);
INSERT INTO `log` VALUES ('20', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"企业应用服务\",\"serviceItem\":\"excelTest\",\"decription\":\"\",\"costType\":7,\"proId\":1,\"D_2018_3\":300.0,\"remark\":\"\",\"D_2018_2\":200.0,\"D_2018_1\":100.0};result:Message{message=\'该产品线不存在！\', ret=500, data=null, pages=0}', '2018-08-31 10:20:18', null);
INSERT INTO `log` VALUES ('21', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务\",\"serviceItem\":\"excelTest\",\"costType\":1,\"proId\":1,\"D_2018_3\":3.0,\"remark\":\"\",\"staffLevel\":\"工程部-高级项目经理\",\"D_2018_2\":2.0,\"D_2018_1\":1.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 10:22:12', null);
INSERT INTO `log` VALUES ('22', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务\",\"serviceItem\":\"excelTest\",\"supplier\":\"盘古服务公司\",\"costType\":3,\"proId\":1,\"D_2018_3\":300.0,\"remark\":\"\",\"D_2018_2\":200.0,\"D_2018_1\":100.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 10:22:14', null);
INSERT INTO `log` VALUES ('23', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务\",\"serviceItem\":\"excelTest\",\"supplier\":\"盘古服务公司\",\"costType\":4,\"proId\":1,\"D_2018_3\":300.0,\"remark\":\"\",\"D_2018_2\":200.0,\"D_2018_1\":100.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 10:22:16', null);
INSERT INTO `log` VALUES ('24', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务\",\"serviceItem\":\"excelTest\",\"decription\":\"\",\"costType\":5,\"proId\":1,\"D_2018_3\":300.0,\"remark\":\"\",\"D_2018_2\":200.0,\"D_2018_1\":100.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 10:22:17', null);
INSERT INTO `log` VALUES ('25', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务\",\"serviceItem\":\"excelTest\",\"decription\":\"\",\"costType\":7,\"proId\":1,\"D_2018_3\":300.0,\"remark\":\"\",\"D_2018_2\":200.0,\"D_2018_1\":100.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 10:22:18', null);
INSERT INTO `log` VALUES ('26', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务\",\"serviceItem\":\"excelTest\",\"costType\":1,\"proId\":1,\"D_2018_3\":3.0,\"remark\":\"\",\"staffLevel\":\"工程部-高级项目经理\",\"D_2018_2\":2.0,\"D_2018_1\":1.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 10:23:44', null);
INSERT INTO `log` VALUES ('27', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务\",\"serviceItem\":\"excelTest\",\"supplier\":\"盘古服务公司\",\"costType\":3,\"proId\":1,\"D_2018_3\":300.0,\"remark\":\"\",\"D_2018_2\":200.0,\"D_2018_1\":100.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 10:23:45', null);
INSERT INTO `log` VALUES ('28', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务\",\"serviceItem\":\"excelTest\",\"supplier\":\"盘古服务公司\",\"costType\":4,\"proId\":1,\"D_2018_3\":300.0,\"remark\":\"\",\"D_2018_2\":200.0,\"D_2018_1\":100.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 10:23:45', null);
INSERT INTO `log` VALUES ('29', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务\",\"serviceItem\":\"excelTest\",\"decription\":\"\",\"costType\":5,\"proId\":1,\"D_2018_3\":300.0,\"remark\":\"\",\"D_2018_2\":200.0,\"D_2018_1\":100.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 10:23:46', null);
INSERT INTO `log` VALUES ('30', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务\",\"serviceItem\":\"excelTest\",\"decription\":\"\",\"costType\":7,\"proId\":1,\"D_2018_3\":300.0,\"remark\":\"\",\"D_2018_2\":200.0,\"D_2018_1\":100.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 10:23:46', null);
INSERT INTO `log` VALUES ('31', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务\",\"serviceItem\":\"excelTest\",\"costType\":1,\"proId\":1,\"D_2018_3\":3.0,\"remark\":\"\",\"staffLevel\":\"工程部-高级项目经理\",\"D_2018_2\":2.0,\"D_2018_1\":1.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 10:38:15', null);
INSERT INTO `log` VALUES ('32', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务\",\"serviceItem\":\"excelTest\",\"supplier\":\"盘古服务公司\",\"costType\":3,\"proId\":1,\"D_2018_3\":300.0,\"remark\":\"\",\"D_2018_2\":200.0,\"D_2018_1\":100.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 10:38:15', null);
INSERT INTO `log` VALUES ('33', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务\",\"serviceItem\":\"excelTest\",\"supplier\":\"盘古服务公司\",\"costType\":4,\"proId\":1,\"D_2018_3\":300.0,\"remark\":\"\",\"D_2018_2\":200.0,\"D_2018_1\":100.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 10:38:15', null);
INSERT INTO `log` VALUES ('34', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务\",\"serviceItem\":\"excelTest\",\"decription\":\"\",\"costType\":5,\"proId\":1,\"D_2018_3\":300.0,\"remark\":\"\",\"D_2018_2\":200.0,\"D_2018_1\":100.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 10:38:15', null);
INSERT INTO `log` VALUES ('35', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务\",\"serviceItem\":\"excelTest\",\"decription\":\"\",\"costType\":7,\"proId\":1,\"D_2018_3\":300.0,\"remark\":\"\",\"D_2018_2\":200.0,\"D_2018_1\":100.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 10:38:15', null);
INSERT INTO `log` VALUES ('36', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务1\",\"serviceItem\":\"excelTest\",\"costType\":1,\"proId\":1,\"D_2018_3\":3.0,\"remark\":\"\",\"staffLevel\":\"工程部-高级项目经理\",\"D_2018_2\":2.0,\"D_2018_1\":1.0};result:Message{message=\'该产品线不存在！\', ret=500, data=null, pages=0}', '2018-08-31 10:38:34', null);
INSERT INTO `log` VALUES ('37', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务\",\"serviceItem\":\"excelTest\",\"costType\":1,\"proId\":1,\"D_2018_3\":3.0,\"remark\":\"\",\"staffLevel\":\"工程部-高级项目经理\",\"D_2018_2\":2.0,\"D_2018_1\":1.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 10:41:17', null);
INSERT INTO `log` VALUES ('38', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务\",\"serviceItem\":\"excelTest\",\"supplier\":\"盘古服务公司\",\"costType\":3,\"proId\":1,\"D_2018_3\":300.0,\"remark\":\"\",\"D_2018_2\":200.0,\"D_2018_1\":100.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 10:41:17', null);
INSERT INTO `log` VALUES ('39', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务\",\"serviceItem\":\"excelTest\",\"supplier\":\"盘古服务公司\",\"costType\":4,\"proId\":1,\"D_2018_3\":300.0,\"remark\":\"\",\"D_2018_2\":200.0,\"D_2018_1\":100.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 10:41:17', null);
INSERT INTO `log` VALUES ('40', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务\",\"serviceItem\":\"excelTest\",\"decription\":\"\",\"costType\":5,\"proId\":1,\"D_2018_3\":300.0,\"remark\":\"\",\"D_2018_2\":200.0,\"D_2018_1\":100.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 10:41:17', null);
INSERT INTO `log` VALUES ('41', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务2\",\"serviceItem\":\"excelTest\",\"decription\":\"\",\"costType\":7,\"proId\":1,\"D_2018_3\":300.0,\"remark\":\"\",\"D_2018_2\":200.0,\"D_2018_1\":100.0};result:Message{message=\'该产品线不存在！\', ret=500, data=null, pages=0}', '2018-08-31 10:41:17', null);
INSERT INTO `log` VALUES ('42', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务\",\"serviceItem\":\"excelTest\",\"costType\":1,\"proId\":1,\"D_2018_3\":3.0,\"remark\":\"\",\"staffLevel\":\"工程部-高级项目经理\",\"D_2018_2\":2.0,\"D_2018_1\":1.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 10:44:19', null);
INSERT INTO `log` VALUES ('43', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务\",\"serviceItem\":\"excelTest\",\"supplier\":\"盘古服务公司\",\"costType\":3,\"proId\":1,\"D_2018_3\":300.0,\"remark\":\"\",\"D_2018_2\":200.0,\"D_2018_1\":100.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 10:44:19', null);
INSERT INTO `log` VALUES ('44', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务\",\"serviceItem\":\"excelTest\",\"supplier\":\"盘古服务公司\",\"costType\":4,\"proId\":1,\"D_2018_3\":300.0,\"remark\":\"\",\"D_2018_2\":200.0,\"D_2018_1\":100.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 10:44:19', null);
INSERT INTO `log` VALUES ('45', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务\",\"serviceItem\":\"excelTest\",\"decription\":\"\",\"costType\":5,\"proId\":1,\"D_2018_3\":300.0,\"remark\":\"\",\"D_2018_2\":200.0,\"D_2018_1\":100.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 10:44:19', null);
INSERT INTO `log` VALUES ('46', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务2\",\"serviceItem\":\"excelTest\",\"decription\":\"\",\"costType\":7,\"proId\":1,\"D_2018_3\":300.0,\"remark\":\"\",\"D_2018_2\":200.0,\"D_2018_1\":100.0};result:Message{message=\'该产品线不存在！\', ret=500, data=null, pages=0}', '2018-08-31 10:44:19', null);
INSERT INTO `log` VALUES ('47', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务\",\"serviceItem\":\"excelTest\",\"costType\":1,\"proId\":1,\"D_2018_3\":3.0,\"remark\":\"\",\"staffLevel\":\"工程部-高级项目经理\",\"D_2018_2\":2.0,\"D_2018_1\":1.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 10:45:29', null);
INSERT INTO `log` VALUES ('48', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务\",\"serviceItem\":\"excelTest\",\"supplier\":\"盘古服务公司\",\"costType\":3,\"proId\":1,\"D_2018_3\":300.0,\"remark\":\"\",\"D_2018_2\":200.0,\"D_2018_1\":100.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 10:45:29', null);
INSERT INTO `log` VALUES ('49', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务\",\"serviceItem\":\"excelTest\",\"supplier\":\"盘古服务公司\",\"costType\":4,\"proId\":1,\"D_2018_3\":300.0,\"remark\":\"\",\"D_2018_2\":200.0,\"D_2018_1\":100.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 10:45:29', null);
INSERT INTO `log` VALUES ('50', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务\",\"serviceItem\":\"excelTest\",\"decription\":\"\",\"costType\":5,\"proId\":1,\"D_2018_3\":300.0,\"remark\":\"\",\"D_2018_2\":200.0,\"D_2018_1\":100.0};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 10:45:29', null);
INSERT INTO `log` VALUES ('51', 'test', 'Message cn.pioneer.pws.service.impl.PlanCostServiceImpl.savePlanCost(String)', 'insert', 'params:{\"productLine\":\"河北惠华项目系统集成服务2\",\"serviceItem\":\"excelTest\",\"decription\":\"\",\"costType\":7,\"proId\":1,\"D_2018_3\":300.0,\"remark\":\"\",\"D_2018_2\":200.0,\"D_2018_1\":100.0};result:Message{message=\'该产品线不存在！\', ret=500, data=null, pages=0}', '2018-08-31 10:45:29', null);
INSERT INTO `log` VALUES ('52', 'test', 'Message cn.pioneer.pws.service.impl.ProfitServiceImpl.updateProfit(Profit)', 'update', 'params:Profit{id=2, proId=1, serviceItem=\'excelTest\', productLine=\'河北惠华项目系统集成服务\', revenueRecMethod=\'null\', quotationAmount=100000, taxRate=4.0, deductionAmount=96153.85, costExcludeTax=97560, riskRatio=5.0, riskAmount=4878.00, grossProfit=-6284.15, grossMargin=-6.54, deductibleVatCost=null, businessSurtax=null, adjustedGrossProfit=null, adjustedGrossMargin=null, createTime=Fri Aug 31 10:22:11 CST 2018, updateTime=Fri Aug 31 10:46:40 CST 2018, remark=\'null\'};result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 10:46:40', null);
INSERT INTO `log` VALUES ('53', '管理员', 'Message cn.pioneer.pws.service.impl.RoleServiceImpl.saveRole(SysRole)', 'insert', 'params:cn.pioneer.pws.entity.SysRole@74eba629;result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 11:02:29', null);
INSERT INTO `log` VALUES ('54', '管理员', 'Message cn.pioneer.pws.service.impl.AccountServiceImpl.updatePassword(Long[])', 'update', 'params:[Ljava.lang.Long;@61f5de8e;result:Message{message=\'success\', ret=200, data=1, pages=0}', '2018-08-31 11:51:39', null);
INSERT INTO `log` VALUES ('55', null, 'Message cn.pioneer.codegenerator.service.impl.TestServiceImpl.saveTest(Test)', 'insert', 'params:cn.pioneer.codegenerator.entity.Test@1237d7b2;result:Message{message=\'success\', status=200, data=null, pages=0}', '2018-09-11 17:24:51', null);

-- ----------------------------
-- Table structure for sys_account
-- ----------------------------
DROP TABLE IF EXISTS `sys_account`;
CREATE TABLE `sys_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `state` int(10) unsigned DEFAULT NULL COMMENT '1:禁用，2：正常， 3：锁定',
  `error_count` int(11) DEFAULT NULL COMMENT '密码错误次数',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `last_login_ip` varchar(32) DEFAULT NULL,
  `creator` varchar(16) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `updater` varchar(16) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_account
-- ----------------------------
INSERT INTO `sys_account` VALUES ('1', '1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '2', '0', '2019-02-28 14:06:17', '127.0.0.1', 'ljl', '2018-07-12 09:29:44', '管理员', '2018-07-12 14:46:45', null);
INSERT INTO `sys_account` VALUES ('7', '1', 'test', 'e10adc3949ba59abbe56e057f20f883e', '2', '0', '2018-08-31 11:52:22', '127.0.0.1', '管理员', '2018-08-27 14:39:11', '管理员', '2018-08-30 16:16:12', null);
INSERT INTO `sys_account` VALUES ('8', '2', 'visitor', 'e10adc3949ba59abbe56e057f20f883e', '2', '0', '2018-08-31 12:05:02', '127.0.0.1', '管理员', '2018-08-31 11:03:33', null, null, null);

-- ----------------------------
-- Table structure for sys_account_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_account_role`;
CREATE TABLE `sys_account_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_account_role
-- ----------------------------
INSERT INTO `sys_account_role` VALUES ('1', '1', '1');
INSERT INTO `sys_account_role` VALUES ('15', '7', '2');
INSERT INTO `sys_account_role` VALUES ('16', '8', '3');
INSERT INTO `sys_account_role` VALUES ('17', '7', '1');

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `id` bigint(20) NOT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  `department_name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_department
-- ----------------------------

-- ----------------------------
-- Table structure for sys_keyname
-- ----------------------------
DROP TABLE IF EXISTS `sys_keyname`;
CREATE TABLE `sys_keyname` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父id',
  `keyname` varchar(64) DEFAULT NULL COMMENT '键值名称',
  `key_type` int(11) DEFAULT NULL COMMENT '1:系统参数；2：默认参数；3：用户参数',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_keyname
-- ----------------------------
INSERT INTO `sys_keyname` VALUES ('1', '-1', 'pws', '2', '2018-08-08 11:14:38', null, null);
INSERT INTO `sys_keyname` VALUES ('2', '1', '产品线', '2', '2018-08-08 11:15:10', null, null);
INSERT INTO `sys_keyname` VALUES ('3', '1', '公司名称', '2', '2018-08-21 15:42:03', null, null);

-- ----------------------------
-- Table structure for sys_keyvalue
-- ----------------------------
DROP TABLE IF EXISTS `sys_keyvalue`;
CREATE TABLE `sys_keyvalue` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `keyname_id` bigint(20) DEFAULT NULL COMMENT '键名id',
  `keyvalue` varchar(128) DEFAULT NULL COMMENT '键值名称',
  `description` varchar(128) DEFAULT NULL COMMENT '键值数据',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_keyvalue
-- ----------------------------
INSERT INTO `sys_keyvalue` VALUES ('1', '2', '数据中心规划与设计服务', null, '2018-08-08 11:16:25', null);
INSERT INTO `sys_keyvalue` VALUES ('2', '2', '数据中心实施与集成服务 ', null, '2018-08-08 11:16:25', null);
INSERT INTO `sys_keyvalue` VALUES ('3', '2', '数据中心测试与验证服务', null, '2018-08-08 11:16:25', null);
INSERT INTO `sys_keyvalue` VALUES ('4', '2', '运维与外包基础设施运维服务', null, '2018-08-08 11:16:25', null);
INSERT INTO `sys_keyvalue` VALUES ('5', '2', '运维与外包基础设施维保服务', null, '2018-08-08 11:16:25', null);
INSERT INTO `sys_keyvalue` VALUES ('6', '2', '运维与外包基础设施技术服务', null, '2018-08-08 11:16:25', null);
INSERT INTO `sys_keyvalue` VALUES ('7', '2', '运维与外包IT 运维与技术服务', null, '2018-08-08 11:16:25', null);
INSERT INTO `sys_keyvalue` VALUES ('8', '2', '运维与外包其他集成与外包服务', null, '2018-08-08 11:16:25', null);
INSERT INTO `sys_keyvalue` VALUES ('9', '2', '软件研发与测试部咨询与认证服务', null, '2018-08-08 11:16:25', null);
INSERT INTO `sys_keyvalue` VALUES ('10', '2', '软件研发与测试部测试服务', null, '2018-08-08 11:16:25', null);
INSERT INTO `sys_keyvalue` VALUES ('11', '2', '软件研发与测试部软件解决方案与集成服务 ', null, '2018-08-08 11:16:25', null);
INSERT INTO `sys_keyvalue` VALUES ('12', '2', '软件研发与测试部其他软件服务', null, '2018-08-08 11:16:25', null);
INSERT INTO `sys_keyvalue` VALUES ('13', '2', '河北惠华项目系统集成服务', null, '2018-08-08 11:16:25', null);
INSERT INTO `sys_keyvalue` VALUES ('14', '3', '深圳市盘古天地投资管理有限公司（母公司）', null, '2018-08-21 16:40:24', null);
INSERT INTO `sys_keyvalue` VALUES ('15', '3', '深圳市盘古数据有限公司', null, '2018-08-21 16:43:06', null);
INSERT INTO `sys_keyvalue` VALUES ('16', '3', '深圳市盘古运营服务有限公司', null, '2018-08-21 16:43:35', null);
INSERT INTO `sys_keyvalue` VALUES ('17', '3', '河北惠华电子科技有限公司', null, '2018-08-21 16:43:47', null);
INSERT INTO `sys_keyvalue` VALUES ('18', '3', '盘古运营上海分公司', null, '2018-08-21 16:43:56', null);
INSERT INTO `sys_keyvalue` VALUES ('19', '3', '云星数据(深圳)有限公司', null, '2018-08-21 16:44:09', null);
INSERT INTO `sys_keyvalue` VALUES ('20', '3', '盘古运营北京分公司', null, '2018-08-21 16:44:21', null);
INSERT INTO `sys_keyvalue` VALUES ('21', '3', '河北惠华北京分公司', null, '2018-08-21 16:44:30', null);
INSERT INTO `sys_keyvalue` VALUES ('22', '3', '云星网络传输（深圳）有限公司', null, '2018-08-21 16:44:39', null);

-- ----------------------------
-- Table structure for sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '机构id',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级id',
  `org_name` varchar(64) DEFAULT NULL COMMENT '组织名称',
  `org_type` int(11) DEFAULT NULL COMMENT '机构类型（1:机构、2:部门、3：职位）',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='机构表';

-- ----------------------------
-- Records of sys_organization
-- ----------------------------
INSERT INTO `sys_organization` VALUES ('1', '-1', 'xx集团', '1', '1', null, '2018-07-12 09:43:30', '2019-03-01 09:35:32');
INSERT INTO `sys_organization` VALUES ('2', '1', '深圳市xxx投资管理有限公司（母公司）', '1', '1', null, '2018-07-12 10:56:43', '2019-03-01 09:35:36');
INSERT INTO `sys_organization` VALUES ('3', '1', '深圳市xx服务有限公司', '1', '2', null, '2018-08-23 12:01:48', '2019-03-01 09:35:40');
INSERT INTO `sys_organization` VALUES ('4', '1', '深圳市xxx有限公司', '1', '3', null, '2018-08-23 13:38:38', '2019-03-01 09:35:44');
INSERT INTO `sys_organization` VALUES ('5', '1', 'abc传输（深圳）有限公司', '1', '4', null, '2018-08-23 13:39:06', '2019-03-01 09:35:49');
INSERT INTO `sys_organization` VALUES ('6', '1', 'sdf深圳）有限公司', '1', '5', null, '2018-08-23 13:39:31', '2019-03-01 09:35:56');
INSERT INTO `sys_organization` VALUES ('7', '3', 'IT部', '2', '1', null, '2018-08-23 13:40:20', '2018-08-23 13:41:08');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '资源id',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父id',
  `res_name` varchar(64) DEFAULT NULL COMMENT '资源名称',
  `res_path` varchar(64) DEFAULT NULL COMMENT '路由path信息',
  `res_url` varchar(128) DEFAULT NULL COMMENT '资源url',
  `icon` varchar(64) DEFAULT NULL COMMENT '图标',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COMMENT='资源表';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', '-1', '项目管理', 'project', null, 'el-icon-menu', '2018-07-12 14:59:35', '2018-07-19 15:27:26', '2', null);
INSERT INTO `sys_resource` VALUES ('2', '-1', '基础管理', 'null', null, 'el-icon-menu', '2018-07-12 15:16:34', null, '2', null);
INSERT INTO `sys_resource` VALUES ('3', '2', '账户管理', 'account', null, 'el-icon-menu', '2018-07-12 15:16:57', '2018-07-12 19:57:02', '1', null);
INSERT INTO `sys_resource` VALUES ('4', '2', '角色管理', 'role', null, 'el-icon-menu', '2018-07-12 15:17:20', '2018-07-12 19:57:16', '2', null);
INSERT INTO `sys_resource` VALUES ('5', '2', '组织管理', 'organization', null, 'el-icon-menu', '2018-07-12 15:17:59', '2018-07-12 19:57:27', '6', null);
INSERT INTO `sys_resource` VALUES ('6', '2', '资源管理', 'resource', null, 'el-icon-menu', '2018-07-12 15:18:17', '2018-07-12 19:57:41', '4', null);
INSERT INTO `sys_resource` VALUES ('7', '2', '资源分配', 'resAssignment', null, 'el-icon-menu', '2018-07-12 15:18:44', '2018-07-12 19:57:51', '5', null);
INSERT INTO `sys_resource` VALUES ('8', '2', '角色分配', 'roleAssignment', null, 'el-icon-menu', '2018-07-12 15:19:15', '2018-07-12 20:00:12', '3', null);
INSERT INTO `sys_resource` VALUES ('9', '-1', '主页', 'home', null, 'el-icon-menu', '2018-07-12 17:41:00', '2018-07-19 15:26:52', '1', null);
INSERT INTO `sys_resource` VALUES ('11', '-1', '月利息点', 'interestRate', null, 'el-icon-menu', '2018-07-19 11:10:03', null, '6', null);
INSERT INTO `sys_resource` VALUES ('12', '-1', '人员级别配置', 'staffLevelConf', null, 'el-icon-menu', '2018-07-19 15:26:21', '2018-07-19 16:01:15', '5', null);
INSERT INTO `sys_resource` VALUES ('15', '2', '字典管理', 'key', null, 'el-icon-menu', '2018-08-08 17:32:54', null, '7', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(1024) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', '2018-07-12 09:45:24', null, null);
INSERT INTO `sys_role` VALUES ('2', '一般用户', '2018-07-12 09:45:57', '2018-07-12 19:11:12', null);
INSERT INTO `sys_role` VALUES ('3', '游客', '2018-08-31 11:02:29', null, null);

-- ----------------------------
-- Table structure for sys_role_res
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_res`;
CREATE TABLE `sys_role_res` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_Id` bigint(20) NOT NULL COMMENT '角色ID',
  `res_Id` bigint(20) NOT NULL COMMENT '资源ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_res
-- ----------------------------
INSERT INTO `sys_role_res` VALUES ('1', '1', '1');
INSERT INTO `sys_role_res` VALUES ('2', '1', '2');
INSERT INTO `sys_role_res` VALUES ('3', '1', '3');
INSERT INTO `sys_role_res` VALUES ('4', '1', '4');
INSERT INTO `sys_role_res` VALUES ('5', '1', '5');
INSERT INTO `sys_role_res` VALUES ('6', '1', '6');
INSERT INTO `sys_role_res` VALUES ('7', '1', '7');
INSERT INTO `sys_role_res` VALUES ('8', '1', '8');
INSERT INTO `sys_role_res` VALUES ('12', '1', '11');
INSERT INTO `sys_role_res` VALUES ('13', '1', '12');
INSERT INTO `sys_role_res` VALUES ('19', '2', '1');
INSERT INTO `sys_role_res` VALUES ('20', '2', '11');
INSERT INTO `sys_role_res` VALUES ('23', '1', '15');
INSERT INTO `sys_role_res` VALUES ('24', '2', '12');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `org_id` bigint(20) DEFAULT NULL COMMENT '组织id',
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `sex` int(11) DEFAULT NULL COMMENT '1:男；0：女',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号码',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '7', 'test', null, null, null, '2018-08-27 14:39:11', null, null);
INSERT INTO `sys_user` VALUES ('2', '3', '游客', null, null, null, '2018-08-31 11:03:33', null, null);

