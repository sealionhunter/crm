USE [CRM]

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ActionGroup]') AND type in (N'U')) DROP TABLE [dbo].[ActionGroup];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Code]') AND type in (N'U')) DROP TABLE [dbo].[Code];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ContactFamilyInfo]') AND type in (N'U')) DROP TABLE [dbo].[ContactFamilyInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ContactInfo]') AND type in (N'U')) DROP TABLE [dbo].[ContactInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ContactSocialInfo]') AND type in (N'U')) DROP TABLE [dbo].[ContactSocialInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ContactTrackInfo]') AND type in (N'U')) DROP TABLE [dbo].[ContactTrackInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CustomerInfo]') AND type in (N'U')) DROP TABLE [dbo].[CustomerInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[LeaderAdvice]') AND type in (N'U')) DROP TABLE [dbo].[LeaderAdvice];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Department]') AND type in (N'U')) DROP TABLE [dbo].[Department];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[GroupManager]') AND type in (N'U')) DROP TABLE [dbo].[GroupManager];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ProjectTeam]') AND type in (N'U')) DROP TABLE [dbo].[ProjectTeam];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Tree]') AND type in (N'U')) DROP TABLE [dbo].[Tree];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[UserInfo]') AND type in (N'U')) DROP TABLE [dbo].[UserInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[WorkInfo]') AND type in (N'U')) DROP TABLE [dbo].[WorkInfo];

/****** Object:  Table [dbo].[WorkInfo]    Script Date: 10/15/2013 14:45:10 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[WorkInfo]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[WorkInfo](
    [workID] [int] IDENTITY(1,1) NOT NULL,
    [userID] [int] NOT NULL,
    [customerID] [int] NULL,
    [teamFlag] [int] NULL,
    [theme] [nvarchar](50) NOT NULL,
    [assignee] [nvarchar](1024) NULL,
    [priority] [nvarchar](12) NOT NULL,
    [workType] [nvarchar](12) NOT NULL,
    [completion] [nvarchar](12) NOT NULL,
    [startTime] [datetime] NULL,
    [endTime] [datetime] NULL,
    [workDetail] [nvarchar](2048) NULL,
    [descriptions] [nvarchar](1024) NULL,
    [isReaded] [bit] NOT NULL DEFAULT ((0)),
    [isMailInformed] [bit] NOT NULL DEFAULT ((0)),
    [isAbolished] [bit] NOT NULL DEFAULT ((0)),
PRIMARY KEY CLUSTERED 
(
    [workID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END;

/****** Object:  Table [dbo].[UserInfo]    Script Date: 10/15/2013 14:45:10 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

SET ANSI_PADDING ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[UserInfo]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[UserInfo](
    [userID] [int] IDENTITY(1,1) NOT NULL,
    [groupID] [int] NOT NULL,
    [jobID] [nvarchar](20) NOT NULL,
    [userName] [nvarchar](20) NOT NULL,
    [password] [nvarchar](64) NOT NULL,
    [realName] [nvarchar](20) NOT NULL,
    [company] [nvarchar](50) NOT NULL,
    [departmentID] [int] NOT NULL,
    [projectTeamID] [int] NULL,
    [job] [nvarchar](30) NULL,
    [jobTitle] [varchar](12) NULL,
    [email] [nvarchar](50) NOT NULL,
    [mobile] [nvarchar](20) NULL,
    [officePhone] [nvarchar](30) NULL,
    [education] [varchar](12) NULL,
    [entryTime] [date] NULL,
    [descriptions] [nvarchar](1024) NULL,
    [isAbolished] [bit] NOT NULL DEFAULT ((0)),
PRIMARY KEY CLUSTERED 
(
    [userID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END;

INSERT [dbo].[UserInfo] ([groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES ( 0, N'123', N'aaa', N'ZwsUcorZkCrsujLiL6T2vQ==', N'系统管理员', N'中国联通合肥分公司', 1, 0, N'', N'', N'dummary@unicom.com', N'', N'', N'', CAST(0xAE370B00 AS Date), N'', 0);
INSERT [dbo].[UserInfo] ([groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES ( 1, N'123', N'crm', N'ZwsUcorZkCrsujLiL6T2vQ==', N'超级管理员', N'中国联通合肥分公司', 1, 0, N'', N'', N'dummary@unicom.com', N'', N'', N'', CAST(0x7E360B00 AS Date), N'', 0);


IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Tree]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Tree](
    [treeID] [int] IDENTITY(1,1) NOT NULL,
    [flag] [varchar](20) NOT NULL,
    [id] [int] NOT NULL,
    [treeName] [nvarchar](20) NOT NULL,
    [isExpanded] [bit] NOT NULL CONSTRAINT [DF__Tree__isExpanded__08B54D69]  DEFAULT ((0)),
    [isLeaf] [bit] NOT NULL CONSTRAINT [DF__Tree__isLeaf__0A9D95DB]  DEFAULT ((1)),
    [fatherID] [int] NOT NULL,
 CONSTRAINT [PK_Tree] PRIMARY KEY CLUSTERED 
(
    [treeID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END;

SET ANSI_PADDING OFF

SET IDENTITY_INSERT [dbo].[Tree] ON
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (1, N'menu', 1, N'客户管理', 0, 0, -1);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (2, N'menu', 2, N'联系人管理', 0, 0, -1);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (3, N'menu', 5, N'统计', 0, 0, -1);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (4, N'menu', 4, N'系统管理', 0, 0, -1);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (5, N'menu', 11, N'客户档案', 0, 0, 1);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (6, N'menu', 12, N'客户联系跟踪', 0, 0, 1);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (7, N'menu', 13, N'竞争对手', 0, 0, 1);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (8, N'menu', 14, N'合作伙伴', 0, 0, 1);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (9, N'menu', 15, N'订单', 0, 0, 6);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (10, N'menu', 16, N'建议书与合同', 0, 0, 1);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (11, N'menu', 21, N'联系人档案', 0, 0, 2);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (12, N'menu', 51, N'客户状态统计', 0, 1, 5);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (13, N'menu', 41, N'角色管理', 0, 1, 4);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (14, N'menu', 43, N'组织管理', 0, 0, 4);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (15, N'menu', 44, N'Master管理', 0, 0, 4);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (16, N'menu', 45, N'菜单管理', 0, 1, 4);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (17, N'menu', 111, N'客户基本信息', 0, 1, 11);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (18, N'menu', 112, N'公海', 0, 1, 11);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (19, N'menu', 113, N'客户转移', 0, 1, 11);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (20, N'menu', 114, N'领导建议', 0, 1, 11);

INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (21, N'menu', 121, N'客户联系', 0, 1, 12);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (22, N'menu', 122, N'客户联系历史', 0, 1, 12);

INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (23, N'menu', 211, N'联系人基本信息', 0, 1, 21);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (24, N'menu', 42, N'用户管理', 0, 1, 4);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (25, N'menu', 431, N'组织结构图', 0, 1, 43);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (26, N'menu', 432, N'部门管理', 0, 1, 43);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (27, N'menu', 433, N'团队管理', 0, 1, 43);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (28, N'menu', 441, N'Code管理', 0, 1, 44);

INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (29, N'operation', 1110, N'客户基本信息', 0, 0, -1);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (30, N'operation', 11102, N'客户详情', 0, 1, 1110);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (31, N'operation', 11104, N'添加客户', 0, 1, 1110);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (32, N'operation', 11105, N'编辑客户', 0, 1, 1110);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (33, N'operation', 11106, N'删除客户', 0, 1, 1110);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (34, N'operation', 11107, N'导出客户', 0, 1, 1110);

INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (35, N'operation', 1120, N'公海', 0, 0, -1);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (36, N'operation', 11201, N'添加公海客户', 0, 1, 1120);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (37, N'operation', 11202, N'编辑公海客户', 0, 1, 1120);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (38, N'operation', 11203, N'删除公海客户', 0, 1, 1120);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (39, N'operation', 11204, N'领取公海客户', 0, 1, 1120);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (40, N'operation', 11205, N'导出公海客户', 0, 1, 1120);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (41, N'operation', 11206, N'客户详情', 0, 1, 1120);

INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (42, N'operation', 1210, N'客户联系', 0, 0, -1);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (43, N'operation', 12101, N'客户反馈', 0, 1, 1210);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (44, N'operation', 12102, N'添加客户联系', 0, 1, 1210);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (45, N'operation', 12103, N'编辑客户联系', 0, 1, 1210);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (46, N'operation', 12104, N'删除客户联系', 0, 1, 1210);

INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (47, N'operation', 1220, N'客户联系历史', 0, 0, -1);
-- INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (48, N'operation', 12201, N'重新反馈', 0, 1, 1220);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (49, N'operation', 12202, N'添加客户联系历史', 0, 1, 1220);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (50, N'operation', 12203, N'编辑客户联系历史', 0, 1, 1220);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (51, N'operation', 12204, N'删除客户联系历史', 0, 1, 1220);

INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (52, N'operation', 2110, N'联系人基本信息', 0, 0, -1);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (53, N'operation', 21101, N'添加联系人', 0, 1, 2110);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (54, N'operation', 21102, N'编辑联系人', 0, 1, 2110);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (56, N'operation', 21103, N'删除联系人', 0, 1, 2110);

INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (57, N'operation', 1140, N'领导建议', 0, 0, -1);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (58, N'operation', 11401, N'添加领导建议', 0, 1, 1140);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (59, N'operation', 11402, N'编辑领导建议', 0, 1, 1140);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (60, N'operation', 11403, N'删除领导建议', 0, 1, 1140);

SET IDENTITY_INSERT [dbo].[Tree] OFF

/****** Object:  Table [dbo].[ProjectTeam]    Script Date: 10/15/2013 14:45:11 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ProjectTeam]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[ProjectTeam](
	[projectTeamID] [int] IDENTITY(1,1) NOT NULL,
	[departmentID] [int] NOT NULL,
	[projectTeamName] [nvarchar](20) NOT NULL,
	[projectTeamLeaderID] [int] NOT NULL,
	[projectTeamStatusCode] [varchar](20) NULL,
	[createTime] [varchar](20) NULL,
	[description] [nvarchar](1024) NULL,
	[endTime] [varchar](20) NULL,
 CONSTRAINT [PK__ProjectT__BF985C16114A936A] PRIMARY KEY CLUSTERED 
(
	[projectTeamID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END;

/****** Object:  Table [dbo].[GroupManager]    Script Date: 10/15/2013 14:45:12 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[GroupManager]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[GroupManager](
    [groupID] [int] IDENTITY(1,1) NOT NULL,
    [groupName] [nvarchar](20) NOT NULL,
 CONSTRAINT [PK__GroupMan__88C102AD267ABA7A] PRIMARY KEY CLUSTERED 
(
    [groupID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END;

SET IDENTITY_INSERT [dbo].[GroupManager] ON
INSERT [dbo].[GroupManager] ([groupID], [groupName]) VALUES (0, N'系统管理员');
INSERT [dbo].[GroupManager] ([groupID], [groupName]) VALUES (1, N'超级管理员');
INSERT [dbo].[GroupManager] ([groupID], [groupName]) VALUES (2, N'管理员');
SET IDENTITY_INSERT [dbo].[GroupManager] OFF

/****** Object:  Table [dbo].[Department]    Script Date: 10/15/2013 14:45:12 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Department]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Department](
    [departmentID] [int] IDENTITY(1,1) NOT NULL,
    [serialNumber] [nvarchar](8) NULL,
    [departmentName] [nvarchar](50) NOT NULL,
    [fatherDepartmentID] [int] NOT NULL,
    [departmentManager] [int] NOT NULL,
    [departmentPhone] [nvarchar](20) NULL,
    [createTime] [nvarchar](20) NULL,
    [departmentDescription] [nvarchar](1024) NULL,
    [depth] [int] NOT NULL,
 CONSTRAINT [PK__Departme__F9B8344D7167D3BD] PRIMARY KEY CLUSTERED 
(
    [departmentID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END;

SET IDENTITY_INSERT [dbo].[Department] ON
INSERT [dbo].[Department] ([departmentID], [departmentName], [fatherDepartmentID], [departmentManager], [departmentPhone], [createTime], [departmentDescription], [depth], [serialNumber]) VALUES (1, N'中国联通合肥分公司', -1, -1, NULL, NULL, NULL, 1, N'0001');
SET IDENTITY_INSERT [dbo].[Department] OFF

/****** Object:  Table [dbo].[CustomerInfo]    Script Date: 10/15/2013 14:45:12 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

SET ANSI_PADDING ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CustomerInfo]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[CustomerInfo](
    [customerID] [int] IDENTITY(1,1) NOT NULL,
    [holder] [int] NULL,
    [customerName] [nvarchar](50) NOT NULL,
    [industry] [varchar](12) NOT NULL,
    [customerType] [varchar](12) NOT NULL,
    [customerStatement] [nvarchar](12) NOT NULL,
    [businessUnit] [nvarchar](12) NOT NULL,
    [valueEvaluate] [varchar](12) NOT NULL,
    [scale] [varchar](12) NOT NULL,
    [customerAddr] [nvarchar](50) NOT NULL,
    [number] [int] NOT NULL,
    [fee] [varchar](12) NOT NULL,
    [earning] [nvarchar](20) NULL,
    [isAbolished] [bit] NOT NULL DEFAULT ((0)),
    [descriptions] [nvarchar](1024) NULL,
    [business1] [nvarchar](1024) NULL,
    [unicomNumber] [int] NULL,
    [business2] [nvarchar](1024) NULL,
    [business3] [nvarchar](1024) NULL,
    [business4] [nvarchar](1024) NULL,
    -- 2015-3-29 13:23:12 modified start.
    [createTime] [datetime] NOT NULL,
    [updateTime] [datetime] NULL,
    -- 2015-3-29 13:23:12 modified end.
PRIMARY KEY CLUSTERED 
(
    [customerID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END;

SET ANSI_PADDING OFF

/****** Object:  Table [dbo].[LeaderAdvice]    Script Date: 10/15/2013 14:45:12 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

SET ANSI_PADDING ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[LeaderAdvice]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[LeaderAdvice](
    [adviceID] [int] IDENTITY(1,1) NOT NULL,
    [customerID] [int] NOT NULL,
    [userID] [int] NOT NULL,
    [adviceContent] [nvarchar](1024) NOT NULL,
    [hasRead] [bit] NOT NULL DEFAULT ((0)),
    [isAbolished] [bit] NOT NULL DEFAULT ((0)),
    [createTime] [datetime] NOT NULL,
    [updateTime] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
    [adviceID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END;

SET ANSI_PADDING OFF

/****** Object:  Table [dbo].[ContactTrackInfo]    Script Date: 10/15/2013 14:45:13 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ContactTrackInfo]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[ContactTrackInfo](
    [contactID] [int] IDENTITY(1,1) NOT NULL,
    [customerID] [int] NOT NULL,
    [contactTheme] [nvarchar](50) NOT NULL,
    [weContact] [int] NOT NULL,
    [oppositeContact] [int] NOT NULL,
    [contactWay] [nvarchar](12) NOT NULL,
    [contactContent] [nvarchar](2048) NOT NULL,
    [contactType] [nvarchar](12) NOT NULL,
    [planDateBegin] [datetime] NOT NULL,
    [realityDateBegin] [datetime] NULL,
    [realityDateEnd] [datetime] NULL,
    [userFeedbackInformation] [nvarchar](1024) NULL,
    [strategy] [nvarchar](1024) NULL,
    [notContantReason] [nvarchar](1024) NULL,
    [ifContact] [bit] NOT NULL,
    [isAbolished] [bit] NOT NULL,
    [remarks] [nvarchar](1024) NULL,
    -- 2015-3-29 13:23:12 modified start.
    [createTime] [datetime] NOT NULL,
    [updateTime] [datetime] NULL,
    -- 2015-3-29 13:23:12 modified end.
PRIMARY KEY CLUSTERED 
(
    [contactID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END;

/****** Object:  Table [dbo].[ContactSocialInfo]    Script Date: 10/15/2013 14:45:13 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ContactSocialInfo]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[ContactSocialInfo](
    [socialID] [int] IDENTITY(1,1) NOT NULL,
    [contactID] [int] NOT NULL,
    [socialRelation] [nvarchar](20) NOT NULL,
    [socialName] [nvarchar](20) NOT NULL,
    [birthday] [date] NULL,
    [political] [nvarchar](50) NULL,
    [company] [nvarchar](50) NULL,
    [job] [nvarchar](50) NULL,
    [isAbolished] [bit] NOT NULL DEFAULT ((0)),
    [descriptions] [nvarchar](1024) NULL,
PRIMARY KEY CLUSTERED 
(
    [socialID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END;

/****** Object:  Table [dbo].[ContactInfo]    Script Date: 10/15/2013 14:45:13 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

SET ANSI_PADDING ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ContactInfo]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[ContactInfo](
    [contactID] [int] IDENTITY(1,1) NOT NULL,
    [contactName] [nvarchar](20) NULL,
    [sex] [varchar](12) NULL,
    [birthday] [date] NULL,
    [company] [nvarchar](50) NOT NULL,
    [department] [nvarchar](50) NOT NULL,
    [job] [nvarchar](50) NOT NULL,
    [phoneNumber] [nvarchar](20) NOT NULL,
    [nativePlace] [nvarchar](50) NULL,
    [contactNational] [nvarchar](10) NULL,
    [addr] [nvarchar](50) NULL,
    [education] [varchar](12) NULL,
    [health] [nvarchar](10) NULL,
    [email] [nvarchar](50) NULL,
    [fax] [nvarchar](20) NULL,
    [QQ] [nvarchar](20) NULL,
    [homePhone] [nvarchar](20) NULL,
    [political] [nvarchar](10) NULL,
    [hobby] [nvarchar](50) NULL,
    [jobResume] [ntext] NULL,
    [eduBackground] [ntext] NULL,
    [isAbolished] [bit] NOT NULL DEFAULT ((0)),
    [descriptions] [nvarchar](1024) NULL,
    [customerID] [int] NOT NULL,
    [contactType] [varchar](12) NOT NULL,
    -- 2015-3-29 13:23:12 modified start.
    [createTime] [datetime] NOT NULL,
    [updateTime] [datetime] NULL,
    -- 2015-3-29 13:23:12 modified end.
PRIMARY KEY CLUSTERED 
(
    [contactID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END;

SET ANSI_PADDING OFF

/****** Object:  Table [dbo].[ContactFamilyInfo]    Script Date: 10/15/2013 14:45:13 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ContactFamilyInfo]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[ContactFamilyInfo](
    [familyID] [int] IDENTITY(1,1) NOT NULL,
    [contactID] [int] NOT NULL,
    [familyRelation] [nvarchar](20) NOT NULL,
    [familyName] [nvarchar](20) NOT NULL,
    [birthday] [date] NULL,
    [political] [nvarchar](50) NULL,
    [company] [nvarchar](50) NULL,
    [job] [nvarchar](50) NULL,
    [isAbolished] [bit] NOT NULL DEFAULT ((0)),
    [descriptions] [nvarchar](1024) NULL,
PRIMARY KEY CLUSTERED 
(
    [familyID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END;

/****** Object:  Table [dbo].[Code]    Script Date: 10/29/2013 11:11:42 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Code]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Code](
	[code] [varchar](12) NOT NULL,
	[value] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Code] PRIMARY KEY CLUSTERED 
(
	[code] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END;

INSERT [dbo].[Code] ([code], [value]) VALUES (N'0001',N'客户档案');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'0003',N'个人信息');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'0006',N'客户联系');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'0010',N'系统管理');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'0011',N'任务');

INSERT [dbo].[Code] ([code], [value]) VALUES (N'00010001', N'客户状态');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00010002', N'客户分类');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00010003', N'所有权');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00010004', N'经营单元');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00010005', N'检索方式');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00010006', N'价值评估');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00010007', N'联系人类别');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00010008', N'市场名称');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00010009', N'注册资金');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00010010', N'客户名称关键字');

INSERT [dbo].[Code] ([code], [value]) VALUES (N'00060001', N'联系需求');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00060002', N'联系方式');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00060003', N'联系与否');

INSERT [dbo].[Code] ([code], [value]) VALUES (N'00110001', N'任务类型');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00110002', N'完成情况');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00110003', N'工作类型');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00110004', N'完成优先级别');

INSERT [dbo].[Code] ([code], [value]) VALUES (N'00100001', N'团队状态');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00100002', N'技术职称');

INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040001', N'政要一部');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040002', N'政要二部');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040003', N'政要三部');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040004', N'交通物流一部');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040005', N'交通物流二部');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040006', N'公共事业部');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040007', N'金融部');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040008', N'商贸旅游传媒部');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040009', N'大企业一部');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040010', N'大企业二部');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040011', N'文教卫体部');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040012', N'商企中心庐阳分部');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040013', N'商企中心包河分部');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040014', N'商企中心蜀山分部');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040015', N'商企中心瑶海分部');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040016', N'商企中心滨湖分部');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040017', N'商企中心双凤分部');

INSERT [dbo].[Code] ([code], [value]) VALUES (N'00030002', N'学历');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00030004', N'性别');

INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100010001', N'初次拜访');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100010002', N'跟进洽谈');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100010003', N'达成意向');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100010004', N'签订合同');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100010005', N'办理入网');

INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100020001', N'政企');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100020002', N'商企');
--INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100020003', N'公共事业');
--INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100020004', N'金融');
--INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100020005', N'商贸旅游传媒');
--INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100020006', N'大企业');
--INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100020007', N'文教卫体');
--INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100020008', N'交通物流');

INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100030001', N'上市公司');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100030002', N'国有公司');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100030003', N'分公司');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100030004', N'保密');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100030005', N'其它');

INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100050001', N'按部门检索');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100050002', N'按团队检索');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100050003', N'公海客户');

INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100100001', N'中国');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100100002', N'股份');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100100003', N'有限');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100100004', N'责任');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100100005', N'公司');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100100006', N'安徽');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100100007', N'合肥');

INSERT [dbo].[Code] ([code], [value]) VALUES (N'000600010001', N'一般');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000600010002', N'重要');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000600010003', N'非常重要');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000600010004', N'急需联系');

INSERT [dbo].[Code] ([code], [value]) VALUES (N'000600020001', N'电话联系');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000600020002', N'邮件联系');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000600020003', N'视频会议');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000600020004', N'拜访客户');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000600020005', N'客户回访');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000600020006', N'其它');

INSERT [dbo].[Code] ([code], [value]) VALUES (N'000600030001', N'是');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000600030002', N'否');

INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100010001', N'个人任务');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100010002', N'分配任务');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100010003', N'被分配任务');

INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100020001', N'未开始');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100020002', N'进行中');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100020003', N'完成');

INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030001', N'准备');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030002', N'文档整理');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030003', N'会议');

INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100040001', N'高');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100040002', N'一般');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100040003', N'低');

INSERT [dbo].[Code] ([code], [value]) VALUES (N'001000010001', N'活动');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001000010002', N'解散');

INSERT [dbo].[Code] ([code], [value]) VALUES (N'001000020001', N'SE');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001000020002', N'PJL');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001000020003', N'PG');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001000020004', N'SSE');

INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300020001', N'硕士');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300020002', N'本科');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300020003', N'专科');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300020004', N'高中');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300020005', N'其它');

INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100090001', N'100万以下');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100090002', N'100~499万');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100090003', N'500~999万');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100090004', N'1000~4999万');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100090005', N'5000~9999万');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100090006', N'1亿以上');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100090007', N'保密');

INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300040001', N'男');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300040002', N'女');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300040003', N'保密');

INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100060001', N'高');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100060002', N'一般');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100060003', N'低');

INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100080001', N'商务楼宇');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100080002', N'宾馆酒店');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100080003', N'工业园区');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100080004', N'聚类市场');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100080005', N'沿街商铺');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100080006', N'建工市场');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100080007', N'地方政府');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100080008', N'事业单位');

INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100070001', N'决策人');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100070002', N'关键人');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100070003', N'相关人员');

INSERT [dbo].[Code] ([code], [value]) VALUES (N'002000010001', N'柱状图');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'002000010002', N'饼状图');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'002000020001', N'客户类型');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'002000020002', N'经营单元');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'002000020003', N'市场名称');

/****** Object:  Table [dbo].[ActionGroup]    Script Date: 10/15/2013 14:45:14 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ActionGroup]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[ActionGroup](
    [actionGroupID] [int] IDENTITY(1,1) NOT NULL,
    [groupID] [int] NOT NULL,
    [actionID] [int] NOT NULL,
 CONSTRAINT [PK__ActionGr__7C75E48302084FDA] PRIMARY KEY CLUSTERED 
(
    [actionGroupID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END;