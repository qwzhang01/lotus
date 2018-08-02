/*
Navicat MySQL Data Transfer

Source Server         : beikeo2o_dev
Source Server Version : 50544
Source Host           : 192.168.10.20:3306
Source Database       : test_qw111

Target Server Type    : MYSQL
Target Server Version : 50544
File Encoding         : 65001

Date: 2018-02-07 21:35:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for at_attachment
-- ----------------------------
DROP TABLE IF EXISTS `at_attachment`;
CREATE TABLE `at_attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(50) NOT NULL COMMENT '文件标题',
  `author` varchar(50) NOT NULL DEFAULT '' COMMENT '音频作者',
  `thumbnail` varchar(200) NOT NULL DEFAULT '' COMMENT '音频图片',
  `path` varchar(200) NOT NULL COMMENT '文件实际路径\r\n            ',
  `folder_id` int(11) NOT NULL DEFAULT '0' COMMENT '文件归档文件夹',
  `summary` varchar(200) NOT NULL DEFAULT '' COMMENT '简介',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=893 DEFAULT CHARSET=utf8 COMMENT='附件表，用于保存用户上传的附件内容。';

-- ----------------------------
-- Records of at_attachment
-- ----------------------------
INSERT INTO `at_attachment` VALUES ('891', 'u001.mp3', '', '', '20180207212601555S4153.mp3', '1', '');
INSERT INTO `at_attachment` VALUES ('892', 'u002.mp3', '', '', '2018020721292971S5197.mp3', '107', '');

-- ----------------------------
-- Table structure for at_folder
-- ----------------------------
DROP TABLE IF EXISTS `at_folder`;
CREATE TABLE `at_folder` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '文件夹名称',
  `full_path` varchar(100) NOT NULL DEFAULT '' COMMENT '文件夹虚拟全路径地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8 COMMENT='附件归档文件夹：用于保存附近归档的虚拟路径';

-- ----------------------------
-- Records of at_folder
-- ----------------------------
INSERT INTO `at_folder` VALUES ('1', '根目录', '/');
INSERT INTO `at_folder` VALUES ('59', '禅修频道', '/1/');
INSERT INTO `at_folder` VALUES ('60', '《现代社会的正念与禅修》', '/1/59/');
INSERT INTO `at_folder` VALUES ('61', '藏传禅修', '/1/59/');
INSERT INTO `at_folder` VALUES ('62', '大学生的佛法课', '/1/');
INSERT INTO `at_folder` VALUES ('63', '大学演讲录', '/1/');
INSERT INTO `at_folder` VALUES ('64', '佛法与人生', '/1/');
INSERT INTO `at_folder` VALUES ('65', '佛法与商道', '/1/');
INSERT INTO `at_folder` VALUES ('67', '佛法与心灵', '/1/');
INSERT INTO `at_folder` VALUES ('68', '海外大学演讲录', '/1/');
INSERT INTO `at_folder` VALUES ('69', '海外开示集', '/1/');
INSERT INTO `at_folder` VALUES ('70', '慧晤频道', '/1/');
INSERT INTO `at_folder` VALUES ('71', '科学与佛法', '/1/');
INSERT INTO `at_folder` VALUES ('72', '人文与佛法', '/1/');
INSERT INTO `at_folder` VALUES ('73', '神秘的佛法修行', '/1/');
INSERT INTO `at_folder` VALUES ('74', '艺术与佛法', '/1/');
INSERT INTO `at_folder` VALUES ('75', '双语频道', '/1/');
INSERT INTO `at_folder` VALUES ('76', '智悲-问答录', '/1/');
INSERT INTO `at_folder` VALUES ('77', '企业演讲', '/1/');
INSERT INTO `at_folder` VALUES ('78', '开示录', '/1/');
INSERT INTO `at_folder` VALUES ('79', '泰国 第六届世界青年佛学研讨会', '/1/63/');
INSERT INTO `at_folder` VALUES ('80', '泰国 清迈大学-《感谢我的世界》', '/1/63/');
INSERT INTO `at_folder` VALUES ('81', '法国格勒布UIAD综合大学《幸福人生》', '/1/63/');
INSERT INTO `at_folder` VALUES ('82', '荷兰 藏传佛教对都市青年的吸引力', '/1/63/');
INSERT INTO `at_folder` VALUES ('83', '摩诃朱拉隆功大学清迈校区-演讲', '/1/63/');
INSERT INTO `at_folder` VALUES ('84', '-第五届世界青年佛教研讨会-演讲', '/1/63/');
INSERT INTO `at_folder` VALUES ('85', '上海外国语大学探寻生活的另一种价值', '/1/63/');
INSERT INTO `at_folder` VALUES ('86', '海南大学-演讲', '/1/63/');
INSERT INTO `at_folder` VALUES ('87', '南京大学-演讲', '/1/63/');
INSERT INTO `at_folder` VALUES ('88', '《跨文化译传藏传佛教之人生感悟 牛津大学', '/1/63/');
INSERT INTO `at_folder` VALUES ('89', '13.杭州淘宝大学-佛法与人生', '/1/64/');
INSERT INTO `at_folder` VALUES ('90', '纳米比亚ACC-改变自他命运的方法', '/1/64/');
INSERT INTO `at_folder` VALUES ('91', '25.意大利都灵-美丽人生', '/1/64/');
INSERT INTO `at_folder` VALUES ('93', '早稻田大学《快乐人生—佛法可以改变世界》', '/1/64/');
INSERT INTO `at_folder` VALUES ('95', '新加坡《佛教与信仰--梦幻的人生》', '/1/64/');
INSERT INTO `at_folder` VALUES ('97', '中国人大重庆校友会-六和精神与企业文化', '/1/65/');
INSERT INTO `at_folder` VALUES ('98', '北京启明星辰佛学-在现代商业中的意义', '/1/65/');
INSERT INTO `at_folder` VALUES ('99', '南京农业大学-《财富与人生价值》', '/1/65/');
INSERT INTO `at_folder` VALUES ('100', '中国人大重庆校友会《商之大者 自利利他》', '/1/65/');
INSERT INTO `at_folder` VALUES ('101', '《佛教如何看待幸福》-伦敦政经学院演讲', '/1/67/');
INSERT INTO `at_folder` VALUES ('102', '莱索托阿弥陀佛关怀中心- 爱的传递', '/1/67/');
INSERT INTO `at_folder` VALUES ('103', '北京雨枫书馆-走向自己-佛法治愈心灵创伤', '/1/67/');
INSERT INTO `at_folder` VALUES ('104', '剑桥大学演讲03-《抑郁症的治疗》', '/1/67/');
INSERT INTO `at_folder` VALUES ('105', '美国华盛顿乔治城大学-禅修开示', '/1/59/');
INSERT INTO `at_folder` VALUES ('106', '走近藏传佛教', '/1/');
INSERT INTO `at_folder` VALUES ('107', '.日本东京 佛教如何帮助日本社会', '/1/72/');
INSERT INTO `at_folder` VALUES ('108', '30.法国格勒诺布尔-寻找生命的皈依处', '/1/64/');
INSERT INTO `at_folder` VALUES ('109', '31.法国巴黎-东方智慧与西方生活', '/1/72/');
INSERT INTO `at_folder` VALUES ('110', '浮世修行-索达吉堪布对话海涛法师', '/1/70/');
INSERT INTO `at_folder` VALUES ('111', '成功的另一种定义-对话潘宗光教授', '/1/70/');
INSERT INTO `at_folder` VALUES ('112', '【慧晤 第十一期】科学与佛教的握手', '/1/70/');
INSERT INTO `at_folder` VALUES ('113', '从大学校长到佛法传播者-对话潘宗光教授', '/1/70/');
INSERT INTO `at_folder` VALUES ('114', '当科学与佛法不谋而合-对话朱清时教授', '/1/70/');
INSERT INTO `at_folder` VALUES ('115', '兴衰的幻剧-索达吉堪布对话阎雨院长', '/1/70/');
INSERT INTO `at_folder` VALUES ('116', '文殊菩萨的智慧宝剑对话威廉.道格拉斯教授', '/1/70/');
INSERT INTO `at_folder` VALUES ('118', '佛法与科学是殊途同归-朱清时访谈', '/1/70/');
INSERT INTO `at_folder` VALUES ('119', '《佛教对进化论与造物论的看法》伦敦大学', '/1/71/');
INSERT INTO `at_folder` VALUES ('121', '《问佛陀法为何物——戒律与法律异同》', '/1/72/');
INSERT INTO `at_folder` VALUES ('122', '六祖坛经', '/1/');
INSERT INTO `at_folder` VALUES ('123', '入行论', '/1/');
INSERT INTO `at_folder` VALUES ('124', '前行广释', '/1/');
INSERT INTO `at_folder` VALUES ('125', '前行实修法', '/1/');
INSERT INTO `at_folder` VALUES ('126', '藏传净土法', '/1/');
INSERT INTO `at_folder` VALUES ('127', '聚焦人工智能', '/1/70/');
INSERT INTO `at_folder` VALUES ('128', '药师七佛经', '/1/');
INSERT INTO `at_folder` VALUES ('129', '大乘经庄严论', '/1/');

-- ----------------------------
-- Table structure for auth_permission
-- ----------------------------
DROP TABLE IF EXISTS `auth_permission`;
CREATE TABLE `auth_permission` (
  `PermissionId` int(11) NOT NULL AUTO_INCREMENT,
  `Menu` varchar(50) DEFAULT '' COMMENT '主模块名称',
  `SubMenu` varchar(50) DEFAULT NULL COMMENT '子模块名称',
  `Operate` varchar(50) DEFAULT NULL COMMENT '操作名称',
  `SortNo` varchar(10) DEFAULT NULL,
  `CreatedOn` datetime DEFAULT NULL,
  `CreatedBy` varchar(50) DEFAULT NULL,
  `ModifiedOn` datetime DEFAULT NULL,
  `ModifiedBy` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`PermissionId`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_permission
-- ----------------------------
INSERT INTO `auth_permission` VALUES ('10', '用户', '角色管理', '删除', '121040', null, null, null, null);
INSERT INTO `auth_permission` VALUES ('11', '用户', '角色管理', '分配权限', '121030', null, null, null, null);
INSERT INTO `auth_permission` VALUES ('52', '用户', '用户管理', '重置密码', '120030', null, '', null, '');
INSERT INTO `auth_permission` VALUES ('62', '内容', '文章', '查看', '110000', null, null, null, null);
INSERT INTO `auth_permission` VALUES ('63', '内容', '文章', '新增', '110010', null, null, null, null);
INSERT INTO `auth_permission` VALUES ('64', '内容', '文章', '删除', '110030', null, null, null, null);
INSERT INTO `auth_permission` VALUES ('66', '内容', '文章', '编辑', '110020', null, null, null, null);
INSERT INTO `auth_permission` VALUES ('67', '内容', '专辑', '新增', '110050', null, null, null, null);
INSERT INTO `auth_permission` VALUES ('68', '内容', '专辑', '查看', '110040', null, null, null, null);
INSERT INTO `auth_permission` VALUES ('69', '用户', '角色管理', '新增', '121010', null, null, null, null);
INSERT INTO `auth_permission` VALUES ('70', '用户', '角色管理', '编辑', '121020', null, null, null, null);
INSERT INTO `auth_permission` VALUES ('74', '用户', '用户管理', '新增', '120000', null, null, null, null);
INSERT INTO `auth_permission` VALUES ('75', '用户', '用户管理', '编辑', '120010', null, null, null, null);
INSERT INTO `auth_permission` VALUES ('76', '用户', '用户管理', '删除', '120020', null, null, null, null);
INSERT INTO `auth_permission` VALUES ('77', '内容', '专辑', '编辑', '110060', null, null, null, null);
INSERT INTO `auth_permission` VALUES ('78', '内容', '专辑', '删除', '110070', null, null, null, null);
INSERT INTO `auth_permission` VALUES ('79', '内容', '曲目', '查看', '110080', null, null, null, null);
INSERT INTO `auth_permission` VALUES ('80', '内容', '曲目', '新增', '110090', null, null, null, null);
INSERT INTO `auth_permission` VALUES ('81', '内容', '曲目', '编辑', '110100', null, null, null, null);
INSERT INTO `auth_permission` VALUES ('82', '内容', '曲目', '删除', '110110', null, null, null, null);
INSERT INTO `auth_permission` VALUES ('83', '设置', '参数', '查看', '123000', null, null, null, null);
INSERT INTO `auth_permission` VALUES ('84', '设置', '配置', '查看', '123100', null, null, null, null);
INSERT INTO `auth_permission` VALUES ('85', '设置', '设置引导页', '设置', '123110', null, null, null, null);
INSERT INTO `auth_permission` VALUES ('86', '设置', '配置', '编辑', '123105', null, null, null, null);

-- ----------------------------
-- Table structure for auth_permission_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_permission_role`;
CREATE TABLE `auth_permission_role` (
  `RpId` int(11) NOT NULL AUTO_INCREMENT,
  `RoleId` int(11) DEFAULT NULL,
  `PermissionId` int(11) DEFAULT NULL,
  `CreatedOn` datetime DEFAULT NULL,
  `CreatedBy` varchar(50) DEFAULT NULL,
  `ModifiedOn` datetime DEFAULT NULL,
  `ModifiedBy` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`RpId`),
  KEY `auth_permission_role_ibfk1` (`PermissionId`) USING BTREE,
  CONSTRAINT `global_permission_role_ibfk1` FOREIGN KEY (`PermissionId`) REFERENCES `auth_permission` (`PermissionId`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9371 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_permission_role
-- ----------------------------
INSERT INTO `auth_permission_role` VALUES ('9330', '112', '62', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9331', '112', '63', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9332', '112', '66', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9333', '112', '64', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9334', '112', '68', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9335', '112', '67', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9336', '112', '77', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9337', '112', '78', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9338', '112', '79', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9339', '112', '80', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9340', '112', '81', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9341', '112', '82', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9342', '112', '74', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9343', '112', '75', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9344', '112', '76', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9345', '112', '52', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9346', '112', '85', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9347', '1', '62', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9348', '1', '63', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9349', '1', '66', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9350', '1', '64', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9351', '1', '68', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9352', '1', '67', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9353', '1', '77', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9354', '1', '78', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9355', '1', '79', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9356', '1', '80', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9357', '1', '81', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9358', '1', '82', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9359', '1', '74', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9360', '1', '75', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9361', '1', '76', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9362', '1', '52', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9363', '1', '69', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9364', '1', '70', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9365', '1', '11', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9366', '1', '10', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9367', '1', '83', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9368', '1', '84', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9369', '1', '86', null, null, null, null);
INSERT INTO `auth_permission_role` VALUES ('9370', '1', '85', null, null, null, null);

-- ----------------------------
-- Table structure for auth_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role` (
  `RoleId` int(11) NOT NULL AUTO_INCREMENT,
  `RoleName` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `Description` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `CreatedBy` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `CreatedOn` datetime DEFAULT NULL,
  `ModifiedBy` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ModifiedOn` datetime DEFAULT NULL,
  PRIMARY KEY (`RoleId`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of auth_role
-- ----------------------------
INSERT INTO `auth_role` VALUES ('1', '超级管理员', '拥有系统所有权限，开发维护人员使用', 'admin', '2014-01-03 13:40:10', 'admin', '2017-03-24 15:44:53');
INSERT INTO `auth_role` VALUES ('112', '系统管理员', '编辑文章、专辑、曲目；设置APP；管理后台用户', null, null, null, null);
INSERT INTO `auth_role` VALUES ('113', '内容管理员', 'test1', null, null, null, null);

-- ----------------------------
-- Table structure for auth_role_user
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_user`;
CREATE TABLE `auth_role_user` (
  `RoleId` int(11) NOT NULL,
  `UserId` int(11) NOT NULL,
  PRIMARY KEY (`RoleId`,`UserId`),
  KEY `RoleId` (`RoleId`),
  KEY `UserId` (`UserId`),
  CONSTRAINT `auth_role_user_ibfk_1` FOREIGN KEY (`RoleId`) REFERENCES `auth_role` (`RoleId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `auth_role_user_ibfk_2` FOREIGN KEY (`UserId`) REFERENCES `auth_user` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of auth_role_user
-- ----------------------------
INSERT INTO `auth_role_user` VALUES ('1', '367');
INSERT INTO `auth_role_user` VALUES ('112', '1');

-- ----------------------------
-- Table structure for auth_user
-- ----------------------------
DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `RealName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Department` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `PasswordType` int(11) DEFAULT NULL,
  `ContactWay` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `IsOnline` int(11) NOT NULL DEFAULT '0',
  `State` int(11) DEFAULT '0',
  `DeleteMark` int(11) NOT NULL DEFAULT '0' COMMENT '删除状态(0:未删除;1:已删除)',
  `CreatedBy` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `CreatedOn` datetime DEFAULT NULL,
  `ModifiedBy` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ModifiedOn` datetime DEFAULT NULL,
  `GlobalId` int(11) DEFAULT NULL,
  `LastIpAddress` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LastLoginDate` datetime DEFAULT NULL,
  `OrgId` int(11) DEFAULT NULL,
  `ExpirationDate` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT ' 上次失败日期和即将失效日期：主要用于处理消息推送',
  PRIMARY KEY (`UserId`),
  UNIQUE KEY `UserName_UNIQUE` (`UserName`)
) ENGINE=InnoDB AUTO_INCREMENT=383 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户信息表';

-- ----------------------------
-- Records of auth_user
-- ----------------------------
INSERT INTO `auth_user` VALUES ('1', 'admin', 'f902353066c4a8203a742a4978dc92f2', '管理员', null, '0', '13570869066', '0', '1', '1', 'admin', '2013-10-11 15:16:56', 'admin', '2017-04-18 22:31:23', '0', null, null, '89', '2017/5/5 9:21:35|2017/5/5 8:51:51');
INSERT INTO `auth_user` VALUES ('367', 'qwzhang01', 'f902353066c4a8203a742a4978dc92f2', '张奇文', null, null, '13994219417', '0', '0', '1', 'admin', '2017-05-19 20:37:12', 'admin', '2017-10-17 08:32:18', null, null, null, null, null);

-- ----------------------------
-- Table structure for co_audio_attachment
-- ----------------------------
DROP TABLE IF EXISTS `co_audio_attachment`;
CREATE TABLE `co_audio_attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content_id` int(11) NOT NULL DEFAULT '0' COMMENT '内容ID',
  `attachment_id` int(11) NOT NULL DEFAULT '0' COMMENT '附件ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2259 DEFAULT CHARSET=utf8 COMMENT='音乐附件关系表';

-- ----------------------------
-- Records of co_audio_attachment
-- ----------------------------
INSERT INTO `co_audio_attachment` VALUES ('2258', '120', '892');

-- ----------------------------
-- Table structure for co_content
-- ----------------------------
DROP TABLE IF EXISTS `co_content`;
CREATE TABLE `co_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `taxonomy_id` int(11) NOT NULL DEFAULT '0' COMMENT '文章类型ID',
  `title` varchar(200) NOT NULL DEFAULT '' COMMENT '文章标题',
  `text` text COMMENT '文章内容',
  `summary` varchar(200) NOT NULL DEFAULT '' COMMENT '简介',
  `thumbnail` varchar(100) NOT NULL DEFAULT '' COMMENT '缩略图',
  `is_stick` int(1) NOT NULL DEFAULT '0' COMMENT '置顶状态:0不置顶;1置顶',
  `is_published` int(1) NOT NULL DEFAULT '0' COMMENT '发布状态:0未发布;1已发布',
  `sort_num` int(11) NOT NULL DEFAULT '0' COMMENT '排序码',
  `source` varchar(50) NOT NULL DEFAULT '' COMMENT '来源',
  `source_href` varchar(200) NOT NULL DEFAULT '' COMMENT '来源链接',
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `icon` varchar(100) NOT NULL DEFAULT '' COMMENT '专辑图标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8 COMMENT='文字内容表，用于存放比如文章、音乐专辑等用户自定义模型内容。';

-- ----------------------------
-- Records of co_content
-- ----------------------------
INSERT INTO `co_content` VALUES ('26', '1', '如何寻找人生的坐标', '<!DOCTYPE html>\r\n<html>\r\n<head>\r\n</head>\r\n<body>\r\n<div id=\"list_top\">\r\n<div id=\"top_title\" style=\"text-align: center;\">如何寻找人生的坐标&mdash;&mdash;柬埔寨金边端华学校演讲</div>\r\n</div>\r\n<div id=\"list_cont\">\r\n<p>&nbsp; &nbsp; &nbsp; 这是我第一次来到柬埔寨，感觉这里佛缘殊胜。今天也很高兴可以与金边大学、端华中学，以及其他高校的师生们一起探讨&ldquo;如何寻找人生的坐标&rdquo;。</p>\r\n<p>&nbsp; &nbsp; &nbsp;我相信每个人都有自己的人生坐标和方向。作为一名穿着藏传佛教僧衣的人来讲，我也很关心这个问题。人是不可能无缘无故来到这个世界的。人活在这个世界上，也不可能没有任何方向和价值观，而当我们离开这个世界时，是否会对这一生的坐标和方向满意呢？今天，我会用&ldquo;德、智、悲、力、信&rdquo;这五个字，和大家一起探讨这个问题。我觉得缺少了这五个字的人生，不一定非常有意义。</p>\r\n<p class=\"bold\">&nbsp; &nbsp; &nbsp;道德</p>\r\n<p>&nbsp; &nbsp; &nbsp;任何人都不能缺少道德。如果一个人缺少了道德，即使他很漂亮、很富裕、地位很高，但却没有灵魂。在21世纪，人们一味追求物质上的财富，却不追求心中的道德，因此，这也是一个道德下滑的时代。我们应该用智慧来观察当今社会的状况。</p>\r\n<p>&nbsp; &nbsp; &nbsp;如果我们心中没有良好的道德，即使外表再美好，也没有太大的意义。《玉耶经》中提到，给孤独长者的儿媳妇玉耶，长得非常漂亮，像天女一样。她也觉得自己很漂亮，于是特别傲慢，认为世界上没有人比得上她。有一次，给孤独长者迎请佛陀到家里来应供，玉耶觉得佛陀配不上她们家，于是躲在一旁，没有出来迎接佛陀。当僧众应供时，她偷偷地看了一下，结果发现佛陀相好庄严，极其美妙，远远超过了自己的容貌。于是她便走出来，在佛陀的足下顶礼。佛陀告诉她，人的美，不仅在于外表，更重要的是心中的善良、正直、慈悲。如果能做到这几点，那才是真的美。玉耶听到佛陀的教言后，原本傲慢的心态全部不见了，当下生起了清净心。</p>\r\n<p>&nbsp; &nbsp; &nbsp;现在的年轻人都很重视长相的美丑，甚至愿意花很多钱去整容。但其实，一个人不管再怎么美，几年以后，脸上都会因为岁月而留下皱纹，而且会越来越多，头发很快就会花白，牙齿也会一颗颗掉下来。谁也没有办法阻止岁月的流逝，但心中的德行却永远也不会消失。</p>\r\n<p>&nbsp; &nbsp; &nbsp;哲学家康德说过，世界上有两样东西让人敬仰，一个是我们头上灿烂的星空，还有一个就是人类的高尚美德。苏格拉底也说，人类最大的幸福就是每天都可以谈论道德的事，人如果没有道德，就相当于失去了灵魂，无法有利于社会。</p>\r\n<p>&nbsp; &nbsp; &nbsp;在当前社会的每一个角落里，人们都在盲目、高速地积累着财富。在这种环境下，我们心中的道德就显得更加重要。无论是佛教、儒教，还是其他宗教思想所提倡的道德，都有不可估量的力量，这种力量一定要熏习到我们的心里。佛陀在《楞伽经》中讲到&ldquo;不思议熏、不思议变&rdquo;，也就是说，所谓的业力，会不可思议地熏习到我们的心上。当一个人的心中种下了善业，那无论他到哪里去，都会有一种善的力量影响他。</p>\r\n<p class=\"bold\">&nbsp; &nbsp; &nbsp;智慧</p>\r\n<p>&nbsp; &nbsp; &nbsp; 大家每天都花费大量的时间在希求智慧上，比如学习语文、数学、物理、化学等各门学科，如果没有这种智慧，恐怕我们很难在这个世界上生活。大家能在这样一所很好的学校里学习，是一件值得自豪的事，但同时，你们不能因此而满足，还要更加努力地去追求智慧。其实在世间中，智慧无法被人夺走，是我们一生当中最有价值、最有力量的品质。</p>\r\n<p>&nbsp; &nbsp; &nbsp; 大家都知道，在第二次世界大战中，希特勒想要灭绝犹太民族，但犹太人不但没有被灭绝，现在反而遍布全世界。在犹太人的价值观当中，钱财、地位并不是最重要的，最重要的是智慧。犹太父母会经常教育孩子，钱财并不是最可靠的财富，他们应该更多地依靠自己的智慧，因为智慧才是世界上最有意义、最有价值的东西。因此，犹太人的孩子从小就知道要好好学习。也正因为这样，犹太人中出现过一些很伟大的知识分子，比如爱因斯坦、弗洛伊德等等。</p>\r\n<p>&nbsp; &nbsp; &nbsp; 藏地也有类似的教育。在孩子学习知识的时候，父母会教育他要努力学习，因为学习知识是一生当中最有意义的事情。藏地也有很多关于学习的比喻，比如把教育比喻成给孩子送黄金，而老师就是无条件地送黄金的人。</p>\r\n<p>&nbsp; &nbsp; &nbsp; 是不是因为老师要求严格，你们就觉得老师很讨厌？不要这样想。俗话说&ldquo;严师出高徒&rdquo;，严格的老师会教出很多高材生。如果老师对工作不认真，上课讲故事应付，不重视学生的话，虽然课堂上很轻松，但这不一定是一件好事。</p>\r\n<p>&nbsp; &nbsp; &nbsp; 我以前读书的时候，也很喜欢轻松的课堂，讨厌特别严格的老师。但后来回想起来才意识到，原来严格的老师会在我们今后的人生当中留下许多珍贵的东西。所以，老师是非常伟大的。</p>\r\n<p>&nbsp; &nbsp; &nbsp;这里有几位教授，从战争时期就开始做老师，培养学生，已经坚持了三四十年，很不容易。有些出家人说，与佛教事业最接近的职业就是老师和医生。这一点我很赞同。释迦牟尼佛的传记中说，佛陀在因地时很想找到一个生活有意义的在家人，结果发现，只有老师和医生的生活才比较有意义。可能有些学生以后也会当老师、医生，有些会从事其他的职业，无论怎样，除了我们所学习的知识以外，我们还需要有一种让自己和他人都快乐的智慧，而千万不能有损害自己或别人的智慧。</p>\r\n<p style=\"text-align: left;\" align=\"center\">&nbsp; &nbsp; &nbsp; <img src=\"http://www.zhibeifw.com/upload/ks_dxyj/20171004160705_cgwa.png\" alt=\"\" border=\"0\" />从柬埔寨的历史上可以看到，很多的&ldquo;智慧&rdquo;实际上都是非常不明智、反人类的，如今反省起来，就会发现那些&ldquo;智慧&rdquo;非常可怕。就拿&ldquo;万人坑&rdquo;和&ldquo;罪恶馆&rdquo;的历史来讲，在那段特殊时期里，很多人死于非命。我到&ldquo;万人坑&rdquo;的时候，看到很多人被埋在一起的痕迹，觉得特别恐怖。也许是历史的原因，当时的人们有一种很不好的&ldquo;智慧&rdquo;，杀害了很多人。</p>\r\n<p>&nbsp; &nbsp; &nbsp; 我们很幸运地没有出生在充满饥饿、战争的年代。今天可以无忧无虑地在校园里共同学习，应该说是很快乐的。大家不要因为现在有学习的压力，将来会有家庭的压力，于是就整天愁眉苦脸。其实，我们现在是很幸运的。</p>\r\n<p>&nbsp; &nbsp; &nbsp; 智慧，是要帮助我们获得快乐的。人生的确有很多痛苦，一些表面上看起来很有智慧的人，心里可能会很痛苦。有人曾请教一位大德：&ldquo;我了知世间所有的学问，但心里的烦恼却越来越多，这是为什么呢？&rdquo;大德回答：&ldquo;你拥有的只是知识，而不是智慧，所以你依然会经常烦恼。但如果你获得了真正的智慧，那一切事情都会豁然开朗的。&rdquo;</p>\r\n<p>&nbsp; &nbsp; &nbsp; &nbsp;希望大家不仅追求学问，还要追求真正的智慧。这种智慧一定是内在的智慧，而不是把自己打扮成知识分子的模样。现在很多人只注重外在形象的包装，而内在什么都没有，这样的形象不是很重要。</p>\r\n<p>&nbsp; &nbsp; &nbsp; 有了智慧，很多事情就不会再让你烦恼。很多人心胸特别狭隘、斤斤计较，会因为一点小小的事情而烦恼，这就是没有智慧的缘故。如果有智慧的话，不管是在任何环境中，你都会很快乐，无论学任何专业，只要你投入时间和精力去专注，最后获得的结果也会是非常满意的。所以我希望大家在希求智慧的时候，不要只追求学问，也一定要追求正确、清净的智慧。</p>\r\n<p class=\"bold\">&nbsp; &nbsp; &nbsp; 悲心</p>\r\n<p>&nbsp; &nbsp; &nbsp; 一个人能不能只有道德和智慧，而没有悲心呢？恐怕不行。我们不仅仅是独自活在这个世界上，我们的身边还有很多与我们有关联的生命。到了柬埔寨之后，我去探望了五所孤儿院，见到了很多孤儿，内心当中有了一些新的感受。我希望各位同学，不要只待在漂亮、舒适的环境中，否则你不会知道，其实还有很多众生正在承受苦难。你需要多走一点路，多看一点书，多说一些话，有必要去了解一下社会各阶层人们的生活。</p>\r\n<p>&nbsp; &nbsp; &nbsp; 我在孤儿院见到了一个孤儿，问了一下他的情况，他说他很自卑，父亲和母亲外出打鱼时不幸去世，他现在跟奶奶一起生活，基本上没有生活保障。虽然现在还能在学校里吃到一顿饭，但不知道以后的人生要怎么办。后来孤儿院的人合唱了一首歌，不仅打动了我，也打动了在场的很多人，包括我们的导游。我们的导游见多识广，但当时他也被感动得一直流泪。</p>\r\n<p>&nbsp; &nbsp; &nbsp; 同学们，你们不要只关注阳光，有时候也应该关注一下黑暗，看一看黑暗的背后，阳光是如何出现的，而在阳光的背后，又还有多少黑暗等待被驱散。其实我们身边有很多苦难的众生。比如在一些农村，人们日常生活中的卫生条件非常差。柬埔寨的周围还有一些水上难民，他们逃离家乡的战乱，来到了柬埔寨，但因为没有合法的身份，所以没办法在地面上定居。同时，他们也没有越南的身份，所以也没办法回去。因为战争，他们现在一无所有，只能生活在水上。目前世界上大约有4520万难民。这些人没有身份，没有自由，更没有家庭，生活没有任何保障。有时候，我们有必要去了解一下难民的生活状况，比如阿富汗、非洲等地的难民。</p>\r\n<p>&nbsp; &nbsp; &nbsp; 我们在日常生活中很挑剔，有时候觉得菜不好吃，有时候又觉得衣服不好看，但其实我们身边还有很多人，连基本的生活保障也没有，甚至在战争中被夺去生命。多了解一下他们的生活，我们就会生起悲心。</p>\r\n<p class=\"bold\">&nbsp; &nbsp; &nbsp; 力量</p>\r\n<p>&nbsp; &nbsp; &nbsp; 我们还需要一种心灵的力量，一种能够让自己坚强，同时也能帮助别人的力量。如果没有这种力量，那生存在这个世界上可能会有一定的困难。比如，老师没有这种心灵的力量，面对繁重的工作，可能会想要辞职；出家人没有这种心灵的力量，修行有可能会半途而废；而学生没有这种心灵的力量，可能无法顺利完成中学、大学，一直到硕士生、博士生的学业。</p>\r\n<p>&nbsp; &nbsp; &nbsp; 世界上的一些故事，可以激发我们这种心灵的力量。比如，2008年&ldquo;5.12&rdquo;地震中廖智老师的故事。她原本是一位舞蹈老师，但在地震中失去了孩子和婆婆，而且在被救出后，不得不自己签字，同意截去自己的双腿，保住性命。但后来，她依靠自己内心的力量又走上了舞台，用装着假肢的腿表演。</p>\r\n<p>&nbsp; &nbsp; &nbsp; 依靠这种内心的力量，可以让一个人完成这么困难的事情。我们可以想一想，如果是自己遇到苦难，又会怎样面对呢？所以，不论是谁，做任何事情时，心中都要有这样一种力量，没有它，人生会困难重重。</p>\r\n<p style=\"text-align: left;\" align=\"center\">&nbsp; &nbsp; &nbsp; 信仰</p>\r\n<p>&nbsp; &nbsp; &nbsp; 在柬埔寨，几乎人人都有信仰，这是非常难得的事。虽然从历史上看，这里也曾发生过战乱，不论是给家庭，还是国家，都带来过极大的痛苦。但如今，柬埔寨90%以上的人都信仰佛教，佛教已经成为了人们生活的支撑。能够从小生长在这样的环境当中，是非常快乐的事。</p>\r\n<p>&nbsp; &nbsp; &nbsp; 藏族人有很多习惯都跟柬埔寨人相似。比如说，藏族人见到出家人时会摘下帽子，恭敬合掌。柬埔寨人也这样做，西哈努克和西哈莫尼两位国王甚至曾在寺院中出家。因为国王信仰佛教，所以，所有的民众，包括知识分子，至少也会对佛教有所了解，而不会像其他地方一样，认为佛教只是一种迷信。我在电视上看到，柬埔寨的国王也拜僧王、拜出家人，所有的大臣，包括国家最高层的领导人，也会到寺院里面供养僧众、参加佛事活动。我感觉柬埔寨的人民有信仰的支撑，应该是非常快乐的。</p>\r\n<p>&nbsp; &nbsp; &nbsp; 有位老师跟我讲，人非常需要信仰，因为在各种各样的痛苦面前，其他知识都无能为力，只有信仰才能很好地帮助人们面对。我觉得这句话非常有意义。如果同学们信仰佛教的话，那不要仅仅停留在表面，要进一步地去学习佛教三藏十二部中所宣讲的教义，学校也应该把佛教教义当做一种知识去传授。</p>\r\n<p>&nbsp; &nbsp; &nbsp; 从1947年开始，佛教就成为了柬埔寨的国教。我记得洪森首相说过，只依靠电台、电视宣传佛教是不够的，佛教的宣传要与时俱进，比如通过网络等手段来传播佛教教育。我也觉得这非常有必要。大家不论是从事医生、老师，还是其他工作，都非常好，但我们不能失去佛教的信仰。也许年轻时感受不到佛教为我们带来的快乐，但随着年龄的增长，就会逐渐明白它的价值。</p>\r\n<p>&nbsp; &nbsp; &nbsp; 可能有些人会有疑问，既然佛教在柬埔寨如此兴盛，那为什么这里还是会经历战争，经济落后呢？其实，战争和经济的落后跟佛教信仰没有关系。如果把佛教当作发财工具的话，那你可能就会觉得，信仰佛教的地方，经济肯定很发达。也正因为我们只关注金钱，所以才会提出这样的问题。但佛教阐述的是真理，不论社会生产力水平如何，真理都可以存在，包括战争频发、经济落后的地方也会有佛教。</p>\r\n<p>&nbsp; &nbsp; &nbsp; 一些柬埔寨的大学生告诉我，这里很少有学生自杀。但是在一些富裕的国家里，物质水平虽然很不错，但是却有很多大学生自杀。柬埔寨人还告诉我，这里的离婚率也非常低。而2013年的统计结果显示，北京的离婚率甚至高于结婚率，高达39%，美国的离婚率几乎达到50%，英国也有42%。那是不是经济越发达，离婚率就会越高？人们是因为生活快乐而离婚，还是痛苦而离婚呢？他们应该是很不快乐，所以才离婚的吧。很多同学都没有经历过离婚，所以可能感受不到离婚的痛苦，我作为出家人也感受不到，但当我们去了解的时候，这个现实的确让人很难过。</p>\r\n<p>&nbsp; &nbsp; &nbsp; 在战争年代，柬埔寨的佛教经历过灭顶之灾，整个国家几乎都没有寺院，除了几个僧人以外，也没有僧团。但如今，柬埔寨有这么多的佛教寺院，而且不论国王，还是国家领导人、民众，看见三宝时都很欢喜，甚至宾馆里面的服务员，对出家人的态度也与其他地方完全不同。在有信仰的国家里，人们对他人的恭敬心和态度都是发自内心的，他们的笑容并不是刻意的，而是真诚的。所以，年轻人应该要有信仰。我尊重没有信仰的人，但是我并不赞叹这样的智慧。因为没有信仰的人在痛苦面前很脆弱，而有信仰的人则会很坚强，并且会用恰当的方式去面对痛苦。</p>\r\n<p>&nbsp; &nbsp; &nbsp; 在柬埔寨，学生们可以自由地学习任何宗教的知识，但同时，学习佛教知识也很重要。当然这种学习，不要只停留在信仰表面。佛教并不是只依赖信仰而安立的，它是一种有智慧的信仰，而没有智慧的信仰是迷信。所以，我们一定要通过学习佛教的知识来赞叹佛教。</p>\r\n<p>&nbsp; &nbsp; &nbsp; 以前在柬埔寨，藏传佛教并不是很兴盛，这次我特意从藏地带了一尊莲花生大士像，安放在柬埔寨，希望依靠这种缘起，南传佛教、藏传佛教，还有汉传佛教都可以在这里兴盛，而且并不是仅仅停留在形象上，而是真正地闻思修行三藏十二部。这样的话，不仅在僧团中会有智慧现前，甚至在家人的群体，比如知识分子的群体当中，也会有一种智慧现前。</p>\r\n<p>&nbsp; &nbsp; &nbsp;我用德、智、悲、力、信五个字和大家做了一个简单的交流，希望大家可以记住其中的一些道理，并依靠它们找到&ldquo;人生的坐标&rdquo;。&emsp;</p>\r\n</div>\r\n</body>\r\n</html>', '公历2017年11月6日至 11月13日（藏历九月十八日至九月二十五日），是天降月中极为殊胜的日子。往年，学院在此期间举行极乐法会，但今年由于种种原因，学院不开极乐法会。', '6826520180207212734.jpg', '0', '1', '26', '智悲佛网', '', '2017-11-03 20:33:01', '2018-02-07 21:27:41', '');
INSERT INTO `co_content` VALUES ('120', '3', '爱乐', null, '爱乐', '7213120180207213305.jpeg', '0', '1', '1', '爱乐', '', '2018-02-07 21:33:23', '2018-02-07 21:33:23', '4356120180207213309.jpeg');

-- ----------------------------
-- Table structure for co_taxonomy
-- ----------------------------
DROP TABLE IF EXISTS `co_taxonomy`;
CREATE TABLE `co_taxonomy` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(50) NOT NULL DEFAULT '' COMMENT '标题',
  `type` varchar(10) NOT NULL DEFAULT '' COMMENT '类型.文章 音乐等',
  `app_column` varchar(10) NOT NULL DEFAULT '' COMMENT '对应APP显示栏目，包括文章、推荐、频道',
  `remark` varchar(200) NOT NULL DEFAULT '' COMMENT '备注',
  `order_number` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='分类表。文章、音乐的标签、专题、类别等都属于taxonomy。';

-- ----------------------------
-- Records of co_taxonomy
-- ----------------------------
INSERT INTO `co_taxonomy` VALUES ('1', '文章', 'article', 'article', '文章', '1');
INSERT INTO `co_taxonomy` VALUES ('2', '频道', 'audio', 'channel', '频道', '3');
INSERT INTO `co_taxonomy` VALUES ('3', '推荐', 'audio', 'recomm', '推荐', '2');

-- ----------------------------
-- Table structure for sys_option
-- ----------------------------
DROP TABLE IF EXISTS `sys_option`;
CREATE TABLE `sys_option` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `option_key` varchar(20) NOT NULL,
  `option_value` varchar(200) NOT NULL,
  `option_remark` varchar(200) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='配置信息表，用来保存网站的所有配置信息。';

-- ----------------------------
-- Records of sys_option
-- ----------------------------
INSERT INTO `sys_option` VALUES ('1', 'qiniu_path', 'http://oxp6ahsqs.bkt.clouddn.com', '七牛文件路径前缀');
INSERT INTO `sys_option` VALUES ('2', 'h5_href', 'http://192.168.10.197:7070/share', 'h5页面前缀');
INSERT INTO `sys_option` VALUES ('3', 'qiniu_ak', 'k2j3lKaelkXPQZqodNfOzrKI2Oc8PoUAIGMEVXM4', '七牛');
INSERT INTO `sys_option` VALUES ('4', 'qiniu_sk', 'lFdCYRYJKFD1fqh9b5UdeqxlE0ML4PDxOP35s_GN', '七牛');
INSERT INTO `sys_option` VALUES ('5', 'qiniu_bucket', 'buddhist-doctrine', '七牛');
INSERT INTO `sys_option` VALUES ('6', 'default_thumbnail', '8267720171022222522.jpg', '默认音乐专辑以及音乐图片');
INSERT INTO `sys_option` VALUES ('7', 'download_btn_href', 'https://fir.im/ptx', '分享页面下载按钮文字连接');
INSERT INTO `sys_option` VALUES ('8', 'download_btn_desc', '下载LOTUS播放器', '分享页面下载按钮文字说明');
INSERT INTO `sys_option` VALUES ('9', 'splash', '5066320171116154806.jpg', 'APP引导页图片');
INSERT INTO `sys_option` VALUES ('10', 'app_title', 'LOTUS音频播放系统', '应用标题');

-- ----------------------------
-- Table structure for sys_param
-- ----------------------------
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE `sys_param` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(20) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '参数编码',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '参数名称',
  `type` varchar(20) NOT NULL DEFAULT '' COMMENT '参数类型',
  `remark` varchar(50) NOT NULL DEFAULT '' COMMENT '参数备注',
  `order_number` int(11) NOT NULL DEFAULT '0' COMMENT '参数排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='系统参数表';

-- ----------------------------
-- Records of sys_param
-- ----------------------------
INSERT INTO `sys_param` VALUES ('1', 'article', '文章', 'content_type', '内容格式，包括音乐、文章', '1');
INSERT INTO `sys_param` VALUES ('2', 'audio', '音频', 'content_type', '内容格式，包括音乐、文章', '2');
INSERT INTO `sys_param` VALUES ('3', 'article', '文章', 'app_column', 'APP栏目，包括文章、推荐、频道', '1');
INSERT INTO `sys_param` VALUES ('4', 'recomm', '推荐', 'app_column', 'APP栏目，包括文章、推荐、频道', '2');
INSERT INTO `sys_param` VALUES ('5', 'channel', '频道', 'app_column', 'APP栏目，包括文章、推荐、频道', '3');
