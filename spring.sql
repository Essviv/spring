DROP TABLE IF EXISTS  `role`;
CREATE TABLE `role` (
  `id`  bigint(18) NOT NULL AUTO_INCREMENT COMMENT '自增ID' ,
  `guid`  varchar(64) NOT NULL COMMENT '应用内唯一标识',
  `name`  varchar(255) NULL COMMENT '角色名称',
  `create_time` timestamp NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_flag`  tinyint(1) NOT NULL  COMMENT '删除标识',
  PRIMARY KEY (`id`),
  INDEX `guid` USING BTREE (`guid`)
)
  ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
  COMMENT='角色表';

DROP TABLE IF EXISTS  `user`;
CREATE TABLE `user` (
  `id`  bigint(18) NOT NULL AUTO_INCREMENT COMMENT '自增ID' ,
  `guid`  varchar(64) NOT NULL COMMENT '应用内唯一标识',
  `name`  varchar(255) NULL COMMENT '用户名称',
  `create_time` timestamp NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_flag`  tinyint(1) NOT NULL  COMMENT '删除标识',
  PRIMARY KEY (`id`),
  INDEX `guid` USING BTREE (`guid`)
)
  ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
  COMMENT='用户表';

DROP TABLE IF EXISTS  `authority`;
CREATE TABLE `authority` (
  `id`  bigint(18) NOT NULL AUTO_INCREMENT COMMENT '自增ID' ,
  `guid`  varchar(64) NOT NULL COMMENT '应用内唯一标识',
  `name`  varchar(255) NULL COMMENT '权限名称',
  `code` varchar(255) NOT NULL  COMMENT '权限代码',
  `create_time` timestamp NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_flag`  tinyint(1) NOT NULL  COMMENT '删除标识',
  PRIMARY KEY (`id`),
  INDEX `guid` USING BTREE (`guid`)
)
  ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
  COMMENT='权限表';

DROP TABLE IF EXISTS  `user_role`;
CREATE TABLE `user_role` (
  `id`  bigint(18) NOT NULL AUTO_INCREMENT COMMENT '自增ID' ,
  `guid`  varchar(64) NOT NULL COMMENT '应用内唯一标识',
  `user_guid` varchar(64) NOT NULL COMMENT '用户标识',
  `role_guid` varchar(64) NOT NULL COMMENT '角色标识',
  `create_time` timestamp NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_flag`  tinyint(1) NOT NULL  COMMENT '删除标识',
  PRIMARY KEY (`id`),
  INDEX `guid` USING BTREE (`guid`)
)
  ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
  COMMENT='用户角色表';

DROP TABLE IF EXISTS  `role_authority`;
CREATE TABLE `role_authority` (
  `id`  bigint(18) NOT NULL AUTO_INCREMENT COMMENT '自增ID' ,
  `guid`  varchar(64) NOT NULL COMMENT '应用内唯一标识',
  `authority_guid` varchar(64) NOT NULL COMMENT '权限标识',
  `role_guid` varchar(64) NOT NULL COMMENT '角色标识',
  `create_time` timestamp NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_flag`  tinyint(1) NOT NULL  COMMENT '删除标识',
  PRIMARY KEY (`id`),
  INDEX `guid` USING BTREE (`guid`)
)
  ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
  COMMENT='角色权限表';