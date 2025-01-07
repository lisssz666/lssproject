/**
 * CMS  内容管理部分表
 */


--- 站点表

CREATE TABLE `cms_sites` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` longtext DEFAULT NULL                COMMENT 'GUID',
  `extendValues` longtext DEFAULT NULL        COMMENT '扩展内容',
  `siteDir` longtext DEFAULT NULL             COMMENT '站点目录',  
  `siteName` longtext DEFAULT NULL            COMMENT '站点名称',
  `siteType` longtext DEFAULT NULL            COMMENT '站点类型',
  `imageUrl` longtext DEFAULT NULL            COMMENT '站点图片',
  `keywords` longtext DEFAULT NULL            COMMENT '关键字',
  `description` longtext DEFAULT NULL         COMMENT '站点描述',
  `tableName` longtext DEFAULT NULL           COMMENT '关联表名',
  `root` varchar(1) NOT NULL                  COMMENT '是否根目录',
  `parentId` int(11) NOT NULL                 COMMENT '上级站点',
  `OrderNum` int(11) NOT NULL                 COMMENT '顺序',
  `CreationTime` datetime(6) NOT NULL         COMMENT '创建时间',
  `CreatorUserId` bigint(20) DEFAULT NULL     COMMENT '创建人',
  `LastModificationTime` datetime(6) DEFAULT NULL COMMENT '最后修改时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL    COMMENT '最后修改人',
  `IsDeleted` tinyint(1) NOT NULL             COMMENT '是否删除',
  `DeleterUserId` bigint(20) DEFAULT NULL     COMMENT '删除人',
  `DeletionTime` datetime(6) DEFAULT NULL     COMMENT '删除时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment = '站点表';

--- 频道表


CREATE TABLE `cms_channels` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` longtext DEFAULT NULL          COMMENT 'GUID',
  `extendValues` longtext DEFAULT NULL  COMMENT '扩展内容',
  `channelName` longtext NOT NULL       COMMENT '频道名称',
  `siteId` int(11) NOT NULL             COMMENT '所属站点',
  `contentModelPluginId` longtext DEFAULT NULL  COMMENT '内容插件ID',
  `tableName` longtext DEFAULT NULL     COMMENT '关联表名', 
  `parentId` int(11) NOT NULL           COMMENT '父级ID',
  `parentsPath` longtext DEFAULT NULL   COMMENT '父级路径',
  `parentsCount` int(11) NOT NULL       COMMENT '父级数量',
  `childrenCount` int(11) NOT NULL      COMMENT '子级数量',
  `indexName` longtext DEFAULT NULL     COMMENT '排序名称',
  `groupNames` longtext DEFAULT NULL    COMMENT '分组',
  `imageUrl` longtext DEFAULT NULL      COMMENT '图片',
  `content` longtext DEFAULT NULL       COMMENT '内容描述',
  `filePath` longtext DEFAULT NULL      COMMENT '文件路径',
  `channelFilePathRule` longtext DEFAULT NULL COMMENT '频道路径路径规则',
  `contentFilePathRule` longtext DEFAULT NULL COMMENT '内容文件路径规则',
  `linkUrl` longtext DEFAULT NULL       COMMENT '外链',
  `linkType` longtext DEFAULT NULL      COMMENT '外链类型',
  `channelTemplteId` int(11) NOT NULL   COMMENT '频道模版',
  `contentTemplateId` int(11) NOT NULL  COMMENT '内容模版',
  `keywords` longtext DEFAULT NULL      COMMENT '关键字',
  `description` longtext DEFAULT NULL   COMMENT '描述',
  `OrderNum` int(11) NOT NULL           COMMENT '排序',
  `CreationTime` datetime(6) NOT NULL   COMMENT '创建时间',
  `CreatorUserId` bigint(20) DEFAULT NULL COMMENT '创建人',
  `LastModificationTime` datetime(6) DEFAULT NULL COMMENT '最后修改时间',
  `LastModifierUserId` bigint(20) DEFAULT NULL  COMMENT '最后修改人',
  `IsDeleted` tinyint(1) NOT NULL COMMENT '是否删除',
  `DeleterUserId` bigint(20) DEFAULT NULL COMMENT '删除人',
  `DeletionTime` datetime(6) DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`Id`),
  KEY `IX_Channels_siteId` (`siteId`),
  CONSTRAINT `FK_Channels_Sites_siteId` FOREIGN KEY (`siteId`) REFERENCES `cms_sites` (`Id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment = '频道表';