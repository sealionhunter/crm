USE [CRM]

/****** Object:  Table [dbo].[ActionGroup]    Script Date: 10/15/2013 14:45:14 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ActionGroup]') AND type in (N'U')) 
DROP TABLE [dbo].[ActionGroup];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ActivityInfo]') AND type in (N'U'))
DROP TABLE [dbo].[ActivityInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Code]') AND type in (N'U')) 
DROP TABLE [dbo].[Code];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CompetitorInfo]') AND type in (N'U')) 
DROP TABLE [dbo].[CompetitorInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ContactFamilyInfo]') AND type in (N'U')) 
DROP TABLE [dbo].[ContactFamilyInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ContactInfo]') AND type in (N'U')) 
DROP TABLE [dbo].[ContactInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ContactSelectInfo]') AND type in (N'U')) 
DROP TABLE [dbo].[ContactSelectInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ContactSocialInfo]') AND type in (N'U')) 
DROP TABLE [dbo].[ContactSocialInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ContactTrackInfo]') AND type in (N'U')) 
DROP TABLE [dbo].[ContactTrackInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ContractInfo]') AND type in (N'U')) 
DROP TABLE [dbo].[ContractInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CooperationProjectInfo]') AND type in (N'U')) 
DROP TABLE [dbo].[CooperationProjectInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CooperatorAnalyInfo]') AND type in (N'U')) 
DROP TABLE [dbo].[CooperatorAnalyInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CooperatorInfo]') AND type in (N'U')) 
DROP TABLE [dbo].[CooperatorInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CoopResumeInfo]') AND type in (N'U')) 
DROP TABLE [dbo].[CoopResumeInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CprAnalysis]') AND type in (N'U')) 
DROP TABLE [dbo].[CprAnalysis];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CustomerInfo]') AND type in (N'U')) 
DROP TABLE [dbo].[CustomerInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CustomerSourceInfo]') AND type in (N'U')) 
DROP TABLE [dbo].[CustomerSourceInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Department]') AND type in (N'U')) 
DROP TABLE [dbo].[Department];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[FileTemplateInfo]') AND type in (N'U')) 
DROP TABLE [dbo].[FileTemplateInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[GroupManager]') AND type in (N'U')) 
DROP TABLE [dbo].[GroupManager];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[OrderInfo]') AND type in (N'U')) 
DROP TABLE [dbo].[OrderInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[OrderTrackInfo]') AND type in (N'U')) 
DROP TABLE [dbo].[OrderTrackInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ProductInfo]') AND type in (N'U')) 
DROP TABLE [dbo].[ProductInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ProductPriceRange]') AND type in (N'U')) 
DROP TABLE [dbo].[ProductPriceRange];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ProjectTeam]') AND type in (N'U')) 
DROP TABLE [dbo].[ProjectTeam];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ProposalOrContractInfo]') AND type in (N'U')) 
DROP TABLE [dbo].[ProposalOrContractInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[SalesEventFlow]') AND type in (N'U')) 
DROP TABLE [dbo].[SalesEventFlow];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[SalesEventFlowCode]') AND type in (N'U')) 
DROP TABLE [dbo].[SalesEventFlowCode];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[SalesEventInfo]') AND type in (N'U')) 
DROP TABLE [dbo].[SalesEventInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[SalesTrackInfo]') AND type in (N'U')) 
DROP TABLE [dbo].[SalesTrackInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[SelectProductInfo]') AND type in (N'U')) 
DROP TABLE [dbo].[SelectProductInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Tree]') AND type in (N'U')) 
DROP TABLE [dbo].[Tree];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[UserInfo]') AND type in (N'U')) 
DROP TABLE [dbo].[UserInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[WorkInfo]') AND type in (N'U')) 
DROP TABLE [dbo].[WorkInfo];

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
    [password] [nvarchar](20) NOT NULL,
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

INSERT [dbo].[UserInfo] ([groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES ( 0, N'123', N'aaa', N'000000', N'系统管理员', N'中国联通合肥分公司', 1, 0, N'', N'', N'dummary@unicom.com', N'', N'', N'', CAST(0xAE370B00 AS Date), N'', 0);
INSERT [dbo].[UserInfo] ([groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES ( 1, N'123', N'crm', N'000000', N'超级管理员', N'中国联通合肥分公司', 1, 0, N'', N'', N'dummary@unicom.com', N'', N'', N'', CAST(0x7E360B00 AS Date), N'', 0);


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

INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (20, N'menu', 121, N'客户联系', 0, 1, 12);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (21, N'menu', 122, N'客户联系历史', 0, 1, 12);

INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (22, N'menu', 211, N'联系人基本信息', 0, 1, 21);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (23, N'menu', 42, N'用户管理', 0, 1, 4);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (24, N'menu', 431, N'组织结构图', 0, 1, 43);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (25, N'menu', 432, N'部门管理', 0, 1, 43);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (26, N'menu', 433, N'团队管理', 0, 1, 43);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (27, N'menu', 441, N'Code管理', 0, 1, 44);

INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (28, N'operation', 1110, N'客户基本信息', 0, 0, -1);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (29, N'operation', 11101, N'客户转移', 0, 1, 1110);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (30, N'operation', 11102, N'客户详情', 0, 1, 1110);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (31, N'operation', 11103, N'领导建议', 0, 1, 1110);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (32, N'operation', 11104, N'添加客户', 0, 1, 1110);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (33, N'operation', 11105, N'编辑客户', 0, 1, 1110);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (34, N'operation', 11106, N'删除客户', 0, 1, 1110);

INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (35, N'operation', 1120, N'公海', 0, 0, -1);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (36, N'operation', 11201, N'添加公海客户', 0, 1, 1120);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (37, N'operation', 11202, N'编辑公海客户', 0, 1, 1120);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (38, N'operation', 11203, N'删除公海客户', 0, 1, 1120);

INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (39, N'operation', 1210, N'客户联系', 0, 0, -1);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (40, N'operation', 12101, N'客户反馈', 0, 1, 1210);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (41, N'operation', 12102, N'添加客户联系', 0, 1, 1210);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (42, N'operation', 12103, N'编辑客户联系', 0, 1, 1210);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (43, N'operation', 12104, N'删除客户联系', 0, 1, 1210);

INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (44, N'operation', 1220, N'客户联系历史', 0, 0, -1);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (45, N'operation', 12201, N'重新反馈', 0, 1, 1220);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (46, N'operation', 12203, N'添加客户联系历史', 0, 1, 1220);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (47, N'operation', 12204, N'编辑客户联系历史', 0, 1, 1220);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (48, N'operation', 12202, N'删除客户联系历史', 0, 1, 1220);

INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (49, N'operation', 2110, N'联系人基本信息', 0, 0, -1);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (50, N'operation', 21101, N'添加联系人', 0, 1, 2110);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (51, N'operation', 21102, N'编辑联系人', 0, 1, 2110);
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (51, N'operation', 21103, N'删除联系人', 0, 1, 2110);

SET IDENTITY_INSERT [dbo].[Tree] OFF
/****** Object:  Table [dbo].[SelectProductInfo]    Script Date: 10/15/2013 14:45:10 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[SelectProductInfo]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[SelectProductInfo](
	[selectProductID] [int] IDENTITY(1,1) NOT NULL,
    [id] [int] NOT NULL,
    [orderID] [nchar](21) NULL,
    [productID] [nchar](21) NULL,
    [name] [nvarchar](50) NULL,
    [price] [decimal](18, 2) NULL,
    [productNumber] [int] NULL,
    [isAbolished] [bit] NULL,
    [discount] [decimal](18, 2) NULL
) ON [PRIMARY]
END;

/****** Object:  Table [dbo].[SalesTrackInfo]    Script Date: 10/15/2013 14:45:11 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

SET ANSI_PADDING OFF

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[SalesTrackInfo]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[SalesTrackInfo](
    [salesTrackNo] [int] IDENTITY(1,1) NOT NULL,
    [submitterID] [int] NOT NULL,
    [eventID] [int] NOT NULL,
    [customerID] [int] NOT NULL,
    [status] [varchar](5) NOT NULL,
    [submitDate] [varchar](30) NOT NULL,
    [isAbolished] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
    [salesTrackNo] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END;

SET ANSI_PADDING OFF

/****** Object:  Table [dbo].[SalesEventInfo]    Script Date: 10/15/2013 14:45:11 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

SET ANSI_PADDING ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[SalesEventInfo]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[SalesEventInfo](
    [eventID] [int] IDENTITY(1,1) NOT NULL,
    [eventName] [nvarchar](50) NOT NULL,
    [customerID] [int] NOT NULL,
    [contactID] [int] NULL,
    [submitterID] [int] NOT NULL,
    [status] [varchar](5) NOT NULL,
    [remarks] [nvarchar](1024) NULL,
    [submitDate] [varchar](30) NOT NULL,
    [isAbolished] [bit] NOT NULL,
 CONSTRAINT [PK__SalesEve__2DC7BD6943E1CB86] PRIMARY KEY CLUSTERED 
(
    [eventID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END;

SET ANSI_PADDING OFF


/****** Object:  Table [dbo].[SalesEventFlowCode]    Script Date: 10/21/2013 16:16:08 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

SET ANSI_PADDING ON

CREATE TABLE [dbo].[SalesEventFlowCode](
    [code] [int] IDENTITY(1,1) NOT NULL,
    [value] [nvarchar](50) NOT NULL,
    [category] [varchar](10) NOT NULL,
    [sort] [int] NOT NULL,
 CONSTRAINT [PK_SalesEventFlowCode] PRIMARY KEY CLUSTERED 
(
    [code] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

SET ANSI_PADDING OFF

SET IDENTITY_INSERT [dbo].[SalesEventFlowCode] ON
INSERT [dbo].[SalesEventFlowCode] ([code], [value], [category], [sort]) VALUES (1, N'发现机会', N'00040001', 1);
INSERT [dbo].[SalesEventFlowCode] ([code], [value], [category], [sort]) VALUES (2, N'验证机会', N'00040001', 2);
INSERT [dbo].[SalesEventFlowCode] ([code], [value], [category], [sort]) VALUES (3, N'中标', N'00040004', 9);
INSERT [dbo].[SalesEventFlowCode] ([code], [value], [category], [sort]) VALUES (4, N'需求分析', N'00040002', 3);
INSERT [dbo].[SalesEventFlowCode] ([code], [value], [category], [sort]) VALUES (5, N'价值分析', N'00040002', 4);
INSERT [dbo].[SalesEventFlowCode] ([code], [value], [category], [sort]) VALUES (6, N'确定决策者', N'00040002', 5);
INSERT [dbo].[SalesEventFlowCode] ([code], [value], [category], [sort]) VALUES (7, N'认知', N'00040002', 6);
INSERT [dbo].[SalesEventFlowCode] ([code], [value], [category], [sort]) VALUES (8, N'报价', N'00040003', 7);
INSERT [dbo].[SalesEventFlowCode] ([code], [value], [category], [sort]) VALUES (9, N'谈判审核', N'00040003', 8);
SET IDENTITY_INSERT [dbo].[SalesEventFlowCode] OFF
/****** Object:  Table [dbo].[ProposalOrContractInfo]    Script Date: 10/15/2013 14:45:11 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON
/****** Object:  Table [dbo].[SalesEventFlow]    Script Date: 10/15/2013 14:45:11 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[SalesEventFlow]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[SalesEventFlow](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [eventID] [int] NOT NULL,
    [status] [int] NOT NULL,
    [demand] [nvarchar](1024) NULL,
 CONSTRAINT [PK_SalesEventFlow] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END;

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ProposalOrContractInfo]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[ProposalOrContractInfo](
    [proposalOrContractID] [int] IDENTITY(1,1) NOT NULL,
    [proposalOrContractName] [nvarchar](50) NOT NULL,
    [fileTemplateName] [nvarchar](50) NULL,
    [proposalOrContractAddDate] [nvarchar](10) NULL,
    [proposalOrContractEditDate] [nvarchar](10) NULL,
    [proposalOrContractObject] [nvarchar](50) NOT NULL,
    [proposalOrContractType] [nvarchar](50) NOT NULL,
    [descriptions] [nvarchar](1024) NULL,
    [isAbolished] [bit] NOT NULL DEFAULT ((0)),
    [proposalOrContractValue] [ntext] NULL,
    [type] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
    [proposalOrContractID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END;

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

/****** Object:  Table [dbo].[ProductPriceRange]    Script Date: 10/15/2013 14:45:11 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ProductPriceRange]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[ProductPriceRange](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [productID] [int] NOT NULL,
    [groupID] [int] NOT NULL,
    [discount] [decimal](18, 2) NOT NULL,
 CONSTRAINT [PK_ProductPriceRange] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END;

/****** Object:  Table [dbo].[ProductInfo]    Script Date: 10/15/2013 14:45:11 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ProductInfo]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[ProductInfo](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [name] [nvarchar](250) NOT NULL,
    [category] [nvarchar](12) NOT NULL,
    [description] [ntext] NULL,
    [remark] [ntext] NULL,
    [productID] [nchar](21) NULL,
    [productModel] [nvarchar](20) NULL,
    [price] [decimal](18, 2) NULL,
 CONSTRAINT [PK_ProductInfo] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END;


/****** Object:  Table [dbo].[OrderTrackInfo]    Script Date: 10/15/2013 14:45:11 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[OrderTrackInfo]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[OrderTrackInfo](
    [orderTrackNo] [int] IDENTITY(1,1) NOT NULL,
    [editPeople] [int] NOT NULL,
    [orderID] [nchar](21) NOT NULL,
    [afterState] [nvarchar](12) NOT NULL,
    [recordTime] [nvarchar](50) NOT NULL,
    [isAbolished] [bit] NOT NULL DEFAULT ((0)),
PRIMARY KEY CLUSTERED 
(
    [orderTrackNo] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END;

/****** Object:  Table [dbo].[OrderInfo]    Script Date: 10/15/2013 14:45:12 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[OrderInfo]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[OrderInfo](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [orderID] [nchar](21) NOT NULL,
    [customerID] [int] NOT NULL,
    [orderState] [nvarchar](12) NOT NULL,
    [contactID] [int] NULL,
    [ourRepresentative] [nvarchar](50) NOT NULL,
    [deliveryDate] [nvarchar](50) NOT NULL,
    [transactionPrice] [float] NOT NULL,
    [remark] [ntext] NULL,
    [isAbolished] [bit] NOT NULL CONSTRAINT [DF__OrderInfo__isAbo__35BCFE0A]  DEFAULT ((0)),
    [ourTelephone] [nvarchar](11) NULL,
    [type] [int] NULL,
    [customerContact] [nvarchar](20) NOT NULL,
    [eventID] [int] NULL,
 CONSTRAINT [PK__OrderInf__3213E83F32E0915F] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
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
/****** Object:  Table [dbo].[FileTemplateInfo]    Script Date: 10/15/2013 14:45:12 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[FileTemplateInfo]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[FileTemplateInfo](
    [fileTemplateID] [int] IDENTITY(1,1) NOT NULL,
    [fileTemplateName] [nvarchar](50) NOT NULL,
    [fileTemplateAddDate] [nvarchar](10) NULL,
    [fileTemplateEditDate] [nvarchar](10) NULL,
    [descriptions] [nvarchar](1024) NULL,
    [isAbolished] [bit] NOT NULL DEFAULT ((0)),
    [fileTemplateValue] [ntext] NULL,
    [type] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
    [fileTemplateID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END;

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
/****** Object:  Table [dbo].[CustomerSourceInfo]    Script Date: 10/15/2013 14:45:12 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

SET ANSI_PADDING ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CustomerSourceInfo]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[CustomerSourceInfo](
    [sourceID] [int] IDENTITY(1,1) NOT NULL,
    [customerID] [int] NOT NULL,
    [sourceArea] [nvarchar](50) NOT NULL,
    [sourceType] [varchar](12) NOT NULL,
    [isAbolished] [bit] NOT NULL DEFAULT ((0)),
    [descriptions] [nvarchar](1024) NULL,
PRIMARY KEY CLUSTERED 
(
    [sourceID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END;

SET ANSI_PADDING OFF

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
    [customerStatement] [nvarchar](50) NOT NULL,
    [valueEvaluate] [varchar](12) NOT NULL,
    [scale] [varchar](12) NOT NULL,
    [customerAddr] [nvarchar](50) NOT NULL,
    [fee] [varchar](12) NOT NULL,
    [earning] [nvarchar](20) NULL,
    [isAbolished] [bit] NOT NULL DEFAULT ((0)),
    [descriptions] [nvarchar](1024) NULL,
    [business1] [nvarchar](1024) NULL,
    [business2] [nvarchar](1024) NULL,
    [business3] [nvarchar](1024) NULL,
    [business4] [nvarchar](1024) NULL,
PRIMARY KEY CLUSTERED 
(
    [customerID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END;

SET ANSI_PADDING OFF

/****** Object:  Table [dbo].[CprAnalysis]    Script Date: 10/15/2013 14:45:12 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CprAnalysis]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[CprAnalysis](
    [cprAnalysisID] [int] IDENTITY(1,1) NOT NULL,
    [competitorInfoId] [int] NOT NULL,
    [area] [nvarchar](20) NOT NULL,
    [ability] [nvarchar](2048) NULL,
    [targets] [nvarchar](2048) NULL,
    [strategy] [nvarchar](2048) NULL,
    [prediction] [nvarchar](2048) NULL,
    [advantage] [nvarchar](2048) NULL,
    [disadvantage] [nvarchar](2048) NULL,
    [advAnalysis] [nvarchar](2048) NULL,
    [disadvAnalysis] [nvarchar](2048) NULL,
    [others] [nvarchar](2048) NULL,
    [compositeComp] [int] NOT NULL,
    [compositeDefense] [int] NOT NULL,
    [cprAnalysisTime] [nvarchar](20) NOT NULL,
    [advice] [nvarchar](2048) NULL,
    [isAbolished] [bit] NOT NULL DEFAULT ((0)),
PRIMARY KEY CLUSTERED 
(
    [cprAnalysisID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END;

/****** Object:  Table [dbo].[CoopResumeInfo]    Script Date: 10/15/2013 14:45:12 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CoopResumeInfo]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[CoopResumeInfo](
    [coopResumeID] [int] IDENTITY(1,1) NOT NULL,
    [customerID] [int] NOT NULL,
    [projectName] [nvarchar](50) NOT NULL,
    [projectDetail] [nvarchar](50) NULL,
    [expBeginDate] [date] NOT NULL,
    [expEndDate] [date] NOT NULL,
    [beginDate] [date] NULL,
    [endDate] [date] NULL,
    [projectScale] [float] NOT NULL,
    [peopleNumber] [int] NOT NULL,
    [principalWe] [nvarchar](50) NOT NULL,
    [principalThey] [nvarchar](50) NULL,
    [projectType] [nvarchar](20) NOT NULL,
    [appraisal] [nvarchar](1024) NULL,
    [descriptions] [nvarchar](1024) NULL,
    [isAbolished] [bit] NOT NULL DEFAULT ((0)),
PRIMARY KEY CLUSTERED 
(
    [coopResumeID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END;

/****** Object:  Table [dbo].[CooperatorInfo]    Script Date: 10/15/2013 14:45:12 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CooperatorInfo]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[CooperatorInfo](
    [cooperatorID] [int] IDENTITY(1,1) NOT NULL,
    [cooperatorName] [nvarchar](50) NOT NULL,
    [cooperatorCity] [nvarchar](20) NOT NULL,
    [cooperatorIndustry] [nvarchar](12) NOT NULL,
    [cooperatorScale] [nvarchar](12) NOT NULL,
    [cooperatorType] [nvarchar](12) NOT NULL,
    [cooperatorTelephone] [nvarchar](20) NULL,
    [cooperatorEmail] [nvarchar](50) NULL,
    [cooperatorFax] [nvarchar](50) NULL,
    [cooperatorWebsite] [nvarchar](50) NULL,
    [cooperatorAddress] [nvarchar](50) NULL,
    [cooperatorRemark] [nvarchar](1024) NULL,
    [cooperatorDetail] [nvarchar](1024) NULL,
    [isAbolished] [bit] NOT NULL DEFAULT ((0)),
PRIMARY KEY CLUSTERED 
(
    [cooperatorID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END;

/****** Object:  Table [dbo].[CooperatorAnalyInfo]    Script Date: 10/15/2013 14:45:13 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CooperatorAnalyInfo]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[CooperatorAnalyInfo](
    [copAnalysisID] [int] IDENTITY(1,1) NOT NULL,
    [cooperatorID] [int] NOT NULL,
    [advantageField] [nvarchar](1024) NOT NULL,
    [disadvantageField] [nvarchar](1024) NOT NULL,
    [managementAbility] [nvarchar](12) NOT NULL,
    [responseSpeed] [nvarchar](12) NOT NULL,
    [trustDegree] [nvarchar](12) NOT NULL,
    [engLevelEvaluation] [nvarchar](1024) NOT NULL,
    [cooperationSummarize] [nvarchar](1024) NOT NULL,
    [comphsAnalysis] [nvarchar](1024) NOT NULL,
    [descriptions] [nvarchar](1024) NULL,
    [createTime] [datetime] NOT NULL,
    [isAbolished] [bit] NOT NULL DEFAULT ((0)),
PRIMARY KEY CLUSTERED 
(
    [copAnalysisID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END;

/****** Object:  Table [dbo].[CooperationProjectInfo]    Script Date: 10/15/2013 14:45:13 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CooperationProjectInfo]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[CooperationProjectInfo](
    [cooperationProjectID] [int] IDENTITY(1,1) NOT NULL,
    [cooperatorID] [int] NOT NULL,
    [projectName] [nvarchar](50) NOT NULL,
    [expectedBeginTime] [nvarchar](10) NOT NULL,
    [expectedEndTime] [nvarchar](10) NOT NULL,
    [realBeginTime] [nvarchar](10) NULL,
    [realEndTime] [nvarchar](10) NULL,
    [projectScale] [int] NULL,
    [cooperatorScale] [int] NULL,
    [cooperatorPeopleNumber] [int] NULL,
    [principalWe] [nvarchar](20) NOT NULL,
    [principalCooperator] [nvarchar](20) NOT NULL,
    [principalCooperatorPhone] [nvarchar](20) NOT NULL,
    [projectType] [nvarchar](12) NOT NULL,
    [projectDetail] [nvarchar](1024) NULL,
    [appraisal] [nvarchar](1024) NULL,
    [isAbolished] [bit] NOT NULL DEFAULT ((0)),
PRIMARY KEY CLUSTERED 
(
    [cooperationProjectID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END;

/****** Object:  Table [dbo].[ContractInfo]    Script Date: 10/15/2013 14:45:13 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ContractInfo]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[ContractInfo](
    [contractID] [int] IDENTITY(1,1) NOT NULL,
    [contractName] [nvarchar](50) NOT NULL,
    [contractValue] [ntext] NULL,
    [orderID] [int] NOT NULL,
    [isAbolished] [bit] NOT NULL,
    [payType] [nvarchar](12) NULL,
    [payEndTime] [nvarchar](10) NULL,
    [fileTemplateName] [nvarchar](50) NULL,
 CONSTRAINT [PK_ContractInfo] PRIMARY KEY CLUSTERED 
(
    [contractID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END;

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
--    [eventID] [int] NOT NULL,
--    [state] [int] NULL,
--    [checkResult] [bit] NULL,
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

/****** Object:  Table [dbo].[ContactSelectInfo]    Script Date: 10/15/2013 14:45:13 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ContactSelectInfo]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[ContactSelectInfo](
    [contactSelectID] [int] IDENTITY(1,1) NOT NULL,
    [contactID] [int] NOT NULL,
    [objectID] [int] NOT NULL,
    [flag] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
    [contactSelectID] ASC
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

/****** Object:  Table [dbo].[CompetitorInfo]    Script Date: 10/15/2013 14:45:13 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CompetitorInfo]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[CompetitorInfo](
    [competitorInfoId] [int] IDENTITY(1,1) NOT NULL,
    [competitorName] [nvarchar](30) NOT NULL,
    [competitorField] [nvarchar](30) NULL,
    [competitorProperty] [nvarchar](30) NULL,
    [competitorLocation] [nvarchar](20) NULL,
    [competitorFoundDate] [nvarchar](20) NULL,
    [competitorMoney] [nvarchar](20) NULL,
    [competitorPeople] [nvarchar](15) NULL,
    [competitorDescription] [nvarchar](1024) NULL,
    [competitorType] [nvarchar](20) NOT NULL,
    [isAbolished] [bit] NOT NULL DEFAULT ((0)),
    [competitorDetail] [nvarchar](2048) NULL,
PRIMARY KEY CLUSTERED 
(
    [competitorInfoId] ASC
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

INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100010001', N'新客户');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100010002', N'老客户');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100010003', N'潜在客户');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100010004', N'忠诚客户');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100020001', N'大企业');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100020002', N'政企');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100020003', N'中小企业');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100020004', N'外资');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100020005', N'合资');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100020006', N'其他');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100030001', N'上市公司');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100030002', N'国有公司');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100030003', N'分公司');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100030004', N'保密');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100030005', N'其它');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040001', N'广告');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040002', N'员工推荐');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040003', N'外部推荐');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040004', N'合作伙伴');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040005', N'公共关系');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040006', N'研讨会-内部');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040007', N'研讨会-合作伙伴');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040008', N'展览会');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040009', N'web');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040010', N'口头推荐');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040011', N'其它');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100050001', N'按部门检索');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100050002', N'按团队检索');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100050003', N'未指定用户');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100060001', N'未合作');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100060002', N'正在合作');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100060003', N'已合作');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100060004', N'已取消合作');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200020001', N'面向大型企业');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200020002', N'面向中型企业');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200020003', N'面向小型企业及个人');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200030001', N'非周期');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200030002', N'年');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200030003', N'季度');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200030004', N'月');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200030005', N'日');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200040001', N'高');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200040002', N'中');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200040003', N'低');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200050001', N'未开始');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200050002', N'进行中');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200050003', N'已结束');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200060001', N'顾客销售会/联谊会');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200060002', N'重点顾客参与公司相关会议');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200060003', N'座谈会/恳谈会');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200060004', N'经验交流会');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200060005', N'意见征询会');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200060006', N'评选类活动');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200060007', N'旅游活动');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200060008', N'节假日聚会/电话问候与家访');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200060009', N'组织战友会、同学会等');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200060010', N'其它');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500010001', N'服务类产品');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500010002', N'实体类产品');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020001', N'0.05');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020002', N'0.10');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020003', N'0.15');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020004', N'0.20');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020005', N'0.25');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020006', N'0.30');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020007', N'0.35');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020008', N'0.40');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020009', N'0.45');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020010', N'0.50');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020011', N'0.55');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020012', N'0.60');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020013', N'0.65');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020014', N'0.70');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020015', N'0.75');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020016', N'0.80');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020017', N'0.85');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020018', N'0.90');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020019', N'0.95');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020020', N'1.00');
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
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700010001', N'50万以下');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700010002', N'50~500万');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700010003', N'500~1000万');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700010004', N'1000~2000万');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700010005', N'2000~5000万');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700010006', N'5000万~1亿');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700010007', N'1~10亿');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700010008', N'10亿以上');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700010009', N'保密');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700020001', N'50人以下');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700020002', N'51~100人');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700020003', N'101~300人');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700020004', N'301~1000人');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700020005', N'1001~2000人');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700020006', N'2000人以上');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700020007', N'保密');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700030001', N'国有企业');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700030002', N'民营企业');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700030003', N'外商投资企业');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700030004', N'开发团队');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700030005', N'有限责任公司');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700030006', N'股份有限公司');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700030007', N'其它');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700040001', N'现有的对手');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700040002', N'潜在的对手');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700050001', N'普通伙伴');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700050002', N'重要伙伴');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700060001', N'非常强');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700060002', N'较强');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700060003', N'良好');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700060004', N'一般');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000800010001', N'现金支付');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000800010002', N'银行转账支付');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000800020001', N'绑定订单');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000800020002', N'不绑定订单');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000900010001', N'普通订单');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000900010002', N'VIP订单');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000900020001', N'客户公司');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000900020002', N'我方公司');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000900020003', N'两处同步');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000900030001', N'未进行');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000900030002', N'正在进行');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000900030003', N'已交付');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000900030004', N'已取消');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000900040001', N'允许通过');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000900040002', N'拒绝通过');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100010001', N'个人任务');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100010002', N'分配任务');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100010003', N'被分配任务');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100020001', N'未开始');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100020002', N'进行中');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100020003', N'完成');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030001', N'Preparation before Project Start');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030002', N'Planning and Tracking');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030003', N'Intergroup Coordination');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030004', N'Requirement');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030005', N'Design');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030006', N'Coding');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030007', N'Unit Test');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030008', N'Integration Test');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030009', N'SubSystem Test');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030010', N'System Test');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030011', N'Acceptance Test');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030012', N'User Documentation Test');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030013', N'Project Training');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030014', N'SCM');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030015', N'PPQA');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030016', N'project HR');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030017', N'Center Activity');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030018', N'Work after Project End');
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
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300030001', N'100万以下');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300030002', N'100~499万');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300030003', N'500~999万');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300030004', N'1000~4999万');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300030005', N'5000~9999万');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300030006', N'1亿以上');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300030007', N'保密');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300040001', N'男');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300040002', N'女');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300040003', N'保密');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300050001', N'值得信任');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300050002', N'可以信任');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300050003', N'一般信任');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300060001', N'高');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300060002', N'一般');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300060003', N'低');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300070001', N'迅速');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300070002', N'较快');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300070003', N'良好');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300070004', N'一般');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080001', N'农业');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080002', N'金融');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080003', N'能源');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080004', N'政府');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080005', N'媒体');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080006', N'机械');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080007', N'保险');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080008', N'制造');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080009', N'零售');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080010', N'非盈利机构');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080011', N'服装');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080012', N'IT');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080013', N'银行');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080014', N'通讯');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080015', N'建筑');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080016', N'生物技术');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080017', N'化学');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080018', N'电子');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080019', N'其它');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100070001', N'决策人');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100070002', N'关键人');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100070003', N'相关人员');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00010001', N'客户状态');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00010002', N'客户分类');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00010003', N'所有权');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00010004', N'资源类型');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00010005', N'检索方式');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00010006', N'合作状态');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00010007', N'联系人类别');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00020002', N'活动范围');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00020003', N'活动周期');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00020004', N'重视程度');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00020005', N'活动状态');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00020006', N'活动类型');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00050001', N'产品类型');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00050002', N'产品折扣');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00060001', N'联系需求');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00060002', N'联系方式');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00060003', N'联系与否');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00070001', N'对手资金力量');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00070002', N'对手员工规模');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00070003', N'对手性质');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00070004', N'竞争类型');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00070005', N'伙伴类型');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00070006', N'管理能力');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00080001', N'付款方式');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00080002', N'绑定订单');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00090001', N'订单类型');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00090002', N'作业方式');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00090003', N'作业状态');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00090004', N'事件处理');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00110001', N'任务类型');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00110002', N'完成状态');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00110003', N'工作类型');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00110004', N'完成优先级别');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00100001', N'团队状态');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00100002', N'技术职称');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00030002', N'学历');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00030003', N'规模');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00030004', N'性别');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00030005', N'信任级别');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00030006', N'价值评估');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00030007', N'反应速度');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00030008', N'市场名称');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00040001', N'客户联系');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00040002', N'意向订单');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00040003', N'订单');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00040004', N'合同');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'0001',N'客户档案');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'0002',N'营销活动');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'0003',N'共通');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'0004',N'销售流程');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'0005',N'产品管理');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'0006',N'客户联系');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'0007',N'竞争对手与合作伙伴');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'0008',N'合同与建议书');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'0009',N'销售管理');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'0010',N'系统管理');
INSERT [dbo].[Code] ([code], [value]) VALUES (N'0011',N'任务');



/****** Object:  Table [dbo].[ActivityInfo]    Script Date: 10/15/2013 14:45:14 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

SET ANSI_PADDING ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ActivityInfo]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[ActivityInfo](
    [activityID] [int] IDENTITY(1,1) NOT NULL,
    [activityName] [nvarchar](30) NOT NULL,
    [activityType] [varchar](12) NOT NULL,
    [activityEmphasis] [varchar](12) NOT NULL,
    [activityRange] [varchar](12) NOT NULL,
    [activityAim] [nvarchar](100) NULL,
    [activityStartTime] [nvarchar](20) NOT NULL,
    [activityEndTime] [nvarchar](20) NOT NULL,
    [activityPlace] [nvarchar](50) NOT NULL,
    [activityLeader] [nvarchar](20) NOT NULL,
    [activityModifer] [nvarchar](20) NOT NULL,
    [activityOurPersonNO] [int] NULL DEFAULT ((1)),
    [activityFunds] [money] NULL,
    [activityState] [varchar](12) NOT NULL,
    [activityPeriod] [varchar](20) NOT NULL,
    [activityDetail] [ntext] NULL,
    [activityPlan] [ntext] NULL,
    [activityMeans] [nvarchar](1024) NULL,
    [activityComment] [nvarchar](1024) NULL,
    [isAbolished] [bit] NOT NULL DEFAULT ((0)),
    [activityMember] [nvarchar](1024) NOT NULL,
PRIMARY KEY CLUSTERED 
(
    [activityID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END;

SET ANSI_PADDING OFF

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