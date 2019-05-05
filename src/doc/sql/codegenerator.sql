/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : codegenerator

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-05-05 10:28:55
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
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of agile_component
-- ----------------------------
INSERT INTO `agile_component` VALUES ('140', '15', null, 'id', '主键', null, 'input', '主键', '\0', '\0', '\0', '\0', '2019-04-29 14:08:42', null);
INSERT INTO `agile_component` VALUES ('141', '15', null, 'content', '内容', null, 'input', '内容', '\0', '', '\0', '\0', '2019-04-29 14:08:42', null);
INSERT INTO `agile_component` VALUES ('142', '15', null, 'createTime', '通知创建时间', null, 'dateTimePicker', '通知创建时间', '\0', '', '\0', '\0', '2019-04-29 14:08:42', null);
INSERT INTO `agile_component` VALUES ('143', '15', null, 'notifier', '被通知者', null, 'input', '被通知者', '\0', '', '\0', '\0', '2019-04-29 14:08:42', null);
INSERT INTO `agile_component` VALUES ('144', '15', null, 'state', '1：未读，2：已读，3：删除', null, 'input', '1：未读，2：已读，3：删除', '\0', '', '\0', '\0', '2019-04-29 14:08:42', null);
INSERT INTO `agile_component` VALUES ('145', '16', null, 'id', '主键', null, 'input', '主键', '\0', '\0', '\0', '\0', '2019-04-29 14:28:40', null);
INSERT INTO `agile_component` VALUES ('146', '16', null, 'content', '内容', null, 'input', '内容', '\0', '', '\0', '\0', '2019-04-29 14:28:40', null);
INSERT INTO `agile_component` VALUES ('147', '16', null, 'createTime', '通知创建时间', null, 'dateTimePicker', '通知创建时间', '\0', '', '\0', '\0', '2019-04-29 14:28:40', null);
INSERT INTO `agile_component` VALUES ('148', '16', null, 'notifier', '被通知者id', null, 'input', '被通知者id', '\0', '', '\0', '\0', '2019-04-29 14:28:40', null);
INSERT INTO `agile_component` VALUES ('149', '16', null, 'state', '1：未读，2：已读，3：删除', null, 'input', '1：未读，2：已读，3：删除', '\0', '', '\0', '\0', '2019-04-29 14:28:40', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of agile_entity
-- ----------------------------
INSERT INTO `agile_entity` VALUES ('15', '12', '3', 'codegenerator', null, '1', 'SysNotice', 'sys_notice', null, null, '2019-04-29 14:08:42', null, null);
INSERT INTO `agile_entity` VALUES ('16', '13', '3', 'codegenerator', null, '1', 'SysNotice', 'sys_notice', null, null, '2019-04-29 14:28:40', null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of agile_schema
-- ----------------------------
INSERT INTO `agile_schema` VALUES ('12', '123', '123', '123', 'cn.jaylen.codegenerator', '2019-04-29 14:08:32', null, null);
INSERT INTO `agile_schema` VALUES ('13', '系统通知', '123', '123', 'cn.jaylen.codegenerator', '2019-04-29 14:28:31', null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of database_connection
-- ----------------------------
INSERT INTO `database_connection` VALUES ('3', 'localhost', '1', '127.0.0.1', '3306', 'root', '123456', '2019-03-01 09:34:24', '2019-03-01 09:34:26', '');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of log
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_account
-- ----------------------------
INSERT INTO `sys_account` VALUES ('1', '1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '2', '0', '2019-05-05 10:21:57', '127.0.0.1', 'ljl', '2018-07-12 09:29:44', '管理员', '2018-07-12 14:46:45', null);
INSERT INTO `sys_account` VALUES ('8', '2', 'visitor', 'e10adc3949ba59abbe56e057f20f883e', '2', '0', '2018-08-31 12:05:02', '127.0.0.1', '管理员', '2018-08-31 11:03:33', null, null, null);
INSERT INTO `sys_account` VALUES ('9', '3', '123', 'e10adc3949ba59abbe56e057f20f883e', '2', '0', null, null, null, '2019-03-01 11:56:47', null, '2019-03-01 11:56:58', null);

-- ----------------------------
-- Table structure for sys_account_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_account_role`;
CREATE TABLE `sys_account_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_account_role
-- ----------------------------
INSERT INTO `sys_account_role` VALUES ('1', '1', '1');
INSERT INTO `sys_account_role` VALUES ('16', '8', '3');
INSERT INTO `sys_account_role` VALUES ('18', '9', null);

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
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '通知创建时间',
  `notifier` bigint(20) DEFAULT NULL COMMENT '被通知者id',
  `state` int(11) DEFAULT NULL COMMENT '1：未读，2：已读，3：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES ('1', '123', '2019-04-30 09:46:03', '1', '1');

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
INSERT INTO `sys_organization` VALUES ('1', '-1', '盘古天地总公司', '1', '1', null, '2018-07-12 09:43:30', '2019-03-01 09:30:12');
INSERT INTO `sys_organization` VALUES ('2', '1', '深圳市盘古天地投资管理有限公司（母公司）', '1', '1', null, '2018-07-12 10:56:43', '2019-03-01 09:30:06');
INSERT INTO `sys_organization` VALUES ('3', '1', '深圳市盘古运营服务有限公司', '1', '2', null, '2018-08-23 12:01:48', '2018-08-23 13:40:55');
INSERT INTO `sys_organization` VALUES ('4', '1', '深圳市盘古数据有限公司', '1', '3', null, '2018-08-23 13:38:38', '2018-08-23 13:40:56');
INSERT INTO `sys_organization` VALUES ('5', '1', '云星网络传输（深圳）有限公司', '1', '4', null, '2018-08-23 13:39:06', '2018-08-23 13:40:58');
INSERT INTO `sys_organization` VALUES ('6', '1', '云星数据（深圳）有限公司', '1', '5', null, '2018-08-23 13:39:31', '2018-08-23 13:41:00');
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COMMENT='资源表';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('2', null, '基础管理', 'base', 'null', 'el-icon-menu', '2018-07-12 15:16:34', '2019-05-05 09:14:15', '2', null);
INSERT INTO `sys_resource` VALUES ('3', '2', '账户管理', 'account', '../container/account/account.vue', 'el-icon-menu', '2018-07-12 15:16:57', '2018-07-12 19:57:02', '1', null);
INSERT INTO `sys_resource` VALUES ('4', '2', '角色管理', 'role', '../container/role/role.vue', 'el-icon-menu', '2018-07-12 15:17:20', '2018-07-12 19:57:16', '2', null);
INSERT INTO `sys_resource` VALUES ('5', '2', '组织管理', 'organization', '../container/organization/organization.vue', 'el-icon-menu', '2018-07-12 15:17:59', '2018-07-12 19:57:27', '6', null);
INSERT INTO `sys_resource` VALUES ('6', '2', '资源管理', 'resource', '../container/resource/resource.vue', 'el-icon-menu', '2018-07-12 15:18:17', '2018-07-12 19:57:41', '4', null);
INSERT INTO `sys_resource` VALUES ('7', '2', '资源分配', 'resAssignment', '../container/resource/resAssignment.vue', 'el-icon-menu', '2018-07-12 15:18:44', '2018-07-12 19:57:51', '5', null);
INSERT INTO `sys_resource` VALUES ('8', '2', '角色分配', 'roleAssignment', '../container/role/roleAssignment.vue', 'el-icon-menu', '2018-07-12 15:19:15', '2018-07-12 20:00:12', '3', null);
INSERT INTO `sys_resource` VALUES ('9', null, '主页', 'home', '../components/page/Dashboard.vue', 'el-icon-menu', '2018-07-12 17:41:00', '2018-07-19 15:26:52', '1', null);
INSERT INTO `sys_resource` VALUES ('15', '2', '字典管理', 'key', '', 'el-icon-menu', '2018-08-08 17:32:54', null, '7', null);
INSERT INTO `sys_resource` VALUES ('16', null, '代码生成', 'codeGenerator', '', 'el-icon-menu', '2019-05-05 09:35:33', '2019-05-05 09:35:56', '3', null);
INSERT INTO `sys_resource` VALUES ('17', '16', '代码生成器', 'generateForm', '', 'el-icon-menu', '2019-05-05 09:37:15', null, '0', null);
INSERT INTO `sys_resource` VALUES ('18', null, '数据库连接管理', 'databaseConn', '', 'el-icon-menu', '2019-05-05 09:38:06', null, '4', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_res
-- ----------------------------
INSERT INTO `sys_role_res` VALUES ('2', '1', '2');
INSERT INTO `sys_role_res` VALUES ('3', '1', '3');
INSERT INTO `sys_role_res` VALUES ('4', '1', '4');
INSERT INTO `sys_role_res` VALUES ('5', '1', '5');
INSERT INTO `sys_role_res` VALUES ('6', '1', '6');
INSERT INTO `sys_role_res` VALUES ('7', '1', '7');
INSERT INTO `sys_role_res` VALUES ('8', '1', '8');
INSERT INTO `sys_role_res` VALUES ('23', '1', '15');
INSERT INTO `sys_role_res` VALUES ('25', '1', '9');
INSERT INTO `sys_role_res` VALUES ('26', '1', '16');
INSERT INTO `sys_role_res` VALUES ('27', '1', '17');
INSERT INTO `sys_role_res` VALUES ('28', '1', '18');
INSERT INTO `sys_role_res` VALUES ('29', '2', '9');
INSERT INTO `sys_role_res` VALUES ('30', '2', '16');
INSERT INTO `sys_role_res` VALUES ('31', '2', '17');
INSERT INTO `sys_role_res` VALUES ('32', '2', '18');
INSERT INTO `sys_role_res` VALUES ('33', '3', '9');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '1', '管理员', null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('2', '3', '游客', null, null, null, '2018-08-31 11:03:33', null, null);
INSERT INTO `sys_user` VALUES ('3', '1', '123', null, null, null, '2019-03-01 11:56:47', '2019-03-01 11:56:58', null);
