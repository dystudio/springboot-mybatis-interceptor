CREATE TABLE `edu_edmo_pms_user` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT 'ID',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT 'version',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_no` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户帐号',
  `user_type` varchar(1) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户类型（1:超级管理员，2:普通管理员，3:用户主帐号，4:用户子帐号）',
  `main_user_id` bigint(50) DEFAULT '0' COMMENT '主帐号ID',
  `user_pwd` varchar(256) COLLATE utf8_unicode_ci NOT NULL COMMENT '登录密码',
  `user_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '姓名',
  `mobile_no` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号',
  `email` varbinary(100) DEFAULT NULL COMMENT '邮箱',
  `status` int(11) NOT NULL COMMENT '状态(100:可用，101:不可用 )',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `is_changed_pwd` int(11) DEFAULT NULL COMMENT '是否更改过密码',
  `pwd_error_count` int(11) NOT NULL DEFAULT '0' COMMENT '连续输错密码次数',
  `pwd_error_time` datetime DEFAULT NULL COMMENT '最后输错密码时间',
  `remark` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_Key_2` (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


INSERT INTO `edu_edmo_pms_user` VALUES ('1', 18, '2017-11-10 18:07:51', 'admin', '1', 0, '7c4a8d09ca3762af61e59520943dc26494f8941b', '超级管理员', '13800138000', NULL, 100, '2019-6-28 11:56:45', 0, 0, '2018-10-8 17:55:24', '超级管理员');
INSERT INTO `edu_edmo_pms_user` VALUES ('2', 1, '2017-11-10 18:31:22', '张三', '1', 0, '7c4a8d09ca3762af61e59520943dc26494f8941b', '管理员', '15910288586', NULL, 100, NULL, 0, 0, NULL, '管理员');
INSERT INTO `edu_edmo_pms_user` VALUES ('3', 0, '2017-11-10 18:33:15', '李四', '1', 0, '7c4a8d09ca3762af61e59520943dc26494f8941b', '管理员', '13800138000', NULL, 100, NULL, 0, 0, NULL, '管理员');
INSERT INTO `edu_edmo_pms_user` VALUES ('4', 17, '2019-1-20 12:54:40', '123', '1', 0, '7c4a8d09ca3762af61e59520943dc26494f8941b', '超级管理员', '13800138000', NULL, 100, '2018-10-8 18:12:27', 0, 0, '2018-10-8 17:55:24', '超级管理员');