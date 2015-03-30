USE [CRM]

/****** Object:  Table [dbo].[ActionGroup]    Script Date: 10/15/2013 14:45:14 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ActionGroup]') AND type in (N'U')) 
DROP TABLE [dbo].[ActionGroup];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ActivityInfo]') AND type in (N'U')) DROP TABLE [dbo].[ActivityInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Code]') AND type in (N'U')) DROP TABLE [dbo].[Code];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CompetitorInfo]') AND type in (N'U')) DROP TABLE [dbo].[CompetitorInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ContactFamilyInfo]') AND type in (N'U')) DROP TABLE [dbo].[ContactFamilyInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ContactInfo]') AND type in (N'U')) DROP TABLE [dbo].[ContactInfo];

/****** Object:  Table [dbo].[ContactSelectInfo]    Script Date: 10/15/2013 14:45:13 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ContactSelectInfo]') AND type in (N'U')) DROP TABLE [dbo].[ContactSelectInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ContactSocialInfo]') AND type in (N'U')) DROP TABLE [dbo].[ContactSocialInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ContactTrackInfo]') AND type in (N'U')) DROP TABLE [dbo].[ContactTrackInfo];

/****** Object:  Table [dbo].[ContractInfo]    Script Date: 10/15/2013 14:45:13 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ContractInfo]') AND type in (N'U')) DROP TABLE [dbo].[ContractInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CooperationProjectInfo]') AND type in (N'U')) DROP TABLE [dbo].[CooperationProjectInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CooperatorAnalyInfo]') AND type in (N'U')) DROP TABLE [dbo].[CooperatorAnalyInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CooperatorInfo]') AND type in (N'U')) DROP TABLE [dbo].[CooperatorInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CoopResumeInfo]') AND type in (N'U')) DROP TABLE [dbo].[CoopResumeInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CprAnalysis]') AND type in (N'U')) DROP TABLE [dbo].[CprAnalysis];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CustomerInfo]') AND type in (N'U')) DROP TABLE [dbo].[CustomerInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CustomerSourceInfo]') AND type in (N'U')) DROP TABLE [dbo].[CustomerSourceInfo];

/****** Object:  Table [dbo].[Department]    Script Date: 10/15/2013 14:45:12 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Department]') AND type in (N'U')) DROP TABLE [dbo].[Department];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[FileTemplateInfo]') AND type in (N'U')) DROP TABLE [dbo].[FileTemplateInfo];

/****** Object:  Table [dbo].[GroupManager]    Script Date: 10/15/2013 14:45:12 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[GroupManager]') AND type in (N'U')) DROP TABLE [dbo].[GroupManager];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[OrderInfo]') AND type in (N'U')) DROP TABLE [dbo].[OrderInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[OrderTrackInfo]') AND type in (N'U')) DROP TABLE [dbo].[OrderTrackInfo];

/****** Object:  Table [dbo].[ProductInfo]    Script Date: 10/15/2013 14:45:11 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ProductInfo]') AND type in (N'U')) DROP TABLE [dbo].[ProductInfo];

/****** Object:  Table [dbo].[ProductPriceRange]    Script Date: 10/15/2013 14:45:11 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ProductPriceRange]') AND type in (N'U')) DROP TABLE [dbo].[ProductPriceRange];

/****** Object:  Table [dbo].[ProjectTeam]    Script Date: 10/15/2013 14:45:11 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ProjectTeam]') AND type in (N'U')) DROP TABLE [dbo].[ProjectTeam];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ProposalOrContractInfo]') AND type in (N'U')) DROP TABLE [dbo].[ProposalOrContractInfo];

/****** Object:  Table [dbo].[SalesEventFlow]    Script Date: 10/15/2013 14:45:11 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[SalesEventFlow]') AND type in (N'U')) DROP TABLE [dbo].[SalesEventFlow];

/****** Object:  Table [dbo].[SalesEventFlowCode]    Script Date: 10/15/2013 14:45:11 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[SalesEventFlowCode]') AND type in (N'U')) DROP TABLE [dbo].[SalesEventFlowCode];

/****** Object:  Table [dbo].[SalesEventInfo]    Script Date: 10/15/2013 14:45:11 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[SalesEventInfo]') AND type in (N'U')) DROP TABLE [dbo].[SalesEventInfo];

/****** Object:  Table [dbo].[SalesTrackInfo]    Script Date: 10/15/2013 14:45:11 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[SalesTrackInfo]') AND type in (N'U'))  DROP TABLE [dbo].[SalesTrackInfo];

/****** Object:  Table [dbo].[SelectProductInfo]    Script Date: 10/15/2013 14:45:10 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[SelectProductInfo]') AND type in (N'U')) DROP TABLE [dbo].[SelectProductInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Tree]') AND type in (N'U')) DROP TABLE [dbo].[Tree];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[UserInfo]') AND type in (N'U')) DROP TABLE [dbo].[UserInfo];

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[WorkInfo]') AND type in (N'U')) DROP TABLE [dbo].[WorkInfo];

/****** Object:  Table [dbo].[ActionGroup]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ActionGroup]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[ActionGroup](
	[actionGroupID] [int] IDENTITY(1,1) NOT NULL,
	[groupID] [int] NOT NULL,
	[actionID] [int] NOT NULL,
 CONSTRAINT [PK__ActionGr__7C75E48302084FDA] PRIMARY KEY CLUSTERED 
(
	[actionGroupID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END;
GO
/****** Object:  Table [dbo].[ActivityInfo]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
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
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Code]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING OFF
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Code]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Code](
	[code] [varchar](12) NOT NULL,
	[value] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Code] PRIMARY KEY CLUSTERED 
(
	[code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END;
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CompetitorInfo]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
GO
/****** Object:  Table [dbo].[ContactFamilyInfo]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
GO
/****** Object:  Table [dbo].[ContactInfo]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
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
PRIMARY KEY CLUSTERED 
(
    [contactID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END;
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ContactSelectInfo]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
GO
/****** Object:  Table [dbo].[ContactSocialInfo]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
GO
/****** Object:  Table [dbo].[ContactTrackInfo]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ContactTrackInfo]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[ContactTrackInfo](
    [contactID] [int] IDENTITY(1,1) NOT NULL,
    [customerID] [int] NOT NULL,
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
    [eventID] [int] NOT NULL,
    [state] [int] NULL,
    [checkResult] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
    [contactID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END;
GO
/****** Object:  Table [dbo].[ContractInfo]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
GO
/****** Object:  Table [dbo].[CooperationProjectInfo]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
GO
/****** Object:  Table [dbo].[CooperatorAnalyInfo]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
GO
/****** Object:  Table [dbo].[CooperatorInfo]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END;
GO
/****** Object:  Table [dbo].[CoopResumeInfo]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END;
GO
/****** Object:  Table [dbo].[CprAnalysis]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END;
GO
/****** Object:  Table [dbo].[CustomerInfo]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CustomerInfo]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[CustomerInfo](
	[customerID] [int] IDENTITY(1,1) NOT NULL,
	[holder] [int] NULL,
	[customerName] [nvarchar](50) NOT NULL,
	[industry] [varchar](12) NOT NULL,
	[customerType] [varchar](12) NOT NULL,
	[customerStatement] [varchar](12) NOT NULL,
	[valueEvaluate] [varchar](12) NOT NULL,
	[scale] [varchar](12) NOT NULL,
	[customerAddr] [nvarchar](50) NOT NULL,
	[fee] [varchar](12) NOT NULL,
	[earning] [nvarchar](20) NULL,
    [isAbolished] [bit] NOT NULL DEFAULT ((0)),
	[descriptions] [nvarchar](1024) NULL,
PRIMARY KEY CLUSTERED 
(
	[customerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END;
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CustomerSourceInfo]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END;
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Department]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END;
GO
/****** Object:  Table [dbo].[FileTemplateInfo]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END;
GO
/****** Object:  Table [dbo].[GroupManager]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[GroupManager]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[GroupManager](
	[groupID] [int] IDENTITY(1,1) NOT NULL,
	[groupName] [nvarchar](20) NOT NULL,
 CONSTRAINT [PK__GroupMan__88C102AD267ABA7A] PRIMARY KEY CLUSTERED 
(
	[groupID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END;
GO
/****** Object:  Table [dbo].[OrderInfo]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
    [isAbolished] [bit] NOT NULL DEFAULT ((0)),
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
GO
/****** Object:  Table [dbo].[OrderTrackInfo]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
GO
/****** Object:  Table [dbo].[ProductInfo]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
GO
/****** Object:  Table [dbo].[ProductPriceRange]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
GO
/****** Object:  Table [dbo].[ProjectTeam]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING OFF
GO
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END;
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ProposalOrContractInfo]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END;
GO
/****** Object:  Table [dbo].[SalesEventFlow]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
GO
/****** Object:  Table [dbo].[SalesEventFlowCode]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[SalesEventFlowCode]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[SalesEventFlowCode](
	[code] [int] IDENTITY(1,1) NOT NULL,
	[value] [nvarchar](50) NOT NULL,
	[category] [varchar](10) NOT NULL,
	[sort] [int] NOT NULL,
 CONSTRAINT [PK_SalesEventFlowCode] PRIMARY KEY CLUSTERED 
(
	[code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END;
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[SalesEventInfo]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END;
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[SalesTrackInfo]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING OFF
GO
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END;
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[SelectProductInfo]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
GO
/****** Object:  Table [dbo].[Tree]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Tree]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Tree](
    [treeID] [int] IDENTITY(1,1) NOT NULL,
    [flag] [varchar](20) NOT NULL,
    [id] [int] NOT NULL,
    [treeName] [nvarchar](20) NOT NULL,
    [isExpanded] [bit] NOT NULL DEFAULT ((0)),
    [isLeaf] [bit] NOT NULL DEFAULT ((1)),
    [fatherID] [int] NOT NULL,
 CONSTRAINT [PK_Tree] PRIMARY KEY CLUSTERED 
(
	[treeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END;
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[UserInfo]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
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
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[WorkInfo]    Script Date: 2014/12/26 17:58:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
GO
SET IDENTITY_INSERT [dbo].[ActionGroup] ON 

INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (1, 1, 51)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (2, 1, 61)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (3, 1, 151)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (4, 1, 152)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (5, 1, 71)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (6, 1, 72)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (7, 1, 41)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (8, 1, 42)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (9, 1, 431)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (10, 1, 432)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (11, 1, 433)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (12, 1, 441)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (13, 1, 442)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (14, 1, 111)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (15, 1, 112)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (16, 1, 121)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (17, 1, 122)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (18, 1, 131)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (19, 1, 132)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (20, 1, 141)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (21, 1, 142)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (22, 1, 1611)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (23, 1, 1612)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (24, 1, 1621)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (25, 1, 1622)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (26, 1, 211)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (27, 1, 311)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (28, 1, 11101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (29, 1, 11102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (30, 1, 11103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (31, 1, 11104)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (32, 1, 11105)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (33, 1, 11106)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (34, 1, 11201)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (35, 1, 11202)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (36, 1, 11203)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (37, 1, 12101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (38, 1, 12102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (39, 1, 12103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (40, 1, 12104)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (41, 1, 12201)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (42, 1, 12202)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (43, 1, 131101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (44, 1, 131102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (45, 1, 131103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (46, 1, 131104)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (47, 1, 131201)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (48, 1, 131202)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (49, 1, 131203)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (50, 1, 131204)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (51, 1, 13201)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (52, 1, 13202)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (53, 1, 13203)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (54, 1, 14101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (55, 1, 14102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (56, 1, 14103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (57, 1, 14104)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (58, 1, 14105)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (59, 1, 14106)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (60, 1, 14201)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (61, 1, 14202)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (62, 1, 14203)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (63, 1, 15101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (64, 1, 15102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (65, 1, 15103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (66, 1, 15104)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (67, 1, 161101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (68, 1, 161102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (69, 1, 161103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (70, 1, 161104)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (71, 1, 161201)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (72, 1, 161202)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (73, 1, 161203)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (74, 1, 161204)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (75, 1, 162101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (76, 1, 162102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (77, 1, 162103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (78, 1, 162104)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (79, 1, 162201)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (80, 1, 162202)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (81, 1, 162203)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (82, 1, 162204)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (83, 1, 21101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (84, 1, 21102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (85, 1, 21103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (86, 1, 31101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (87, 1, 31102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (88, 1, 31103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (89, 2, 51)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (90, 2, 61)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (91, 2, 151)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (92, 2, 152)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (93, 2, 71)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (94, 2, 72)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (95, 2, 41)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (96, 2, 42)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (97, 2, 431)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (98, 2, 432)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (99, 2, 433)
GO
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (100, 2, 441)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (101, 2, 442)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (102, 2, 111)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (103, 2, 112)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (104, 2, 121)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (105, 2, 122)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (106, 2, 131)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (107, 2, 132)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (108, 2, 141)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (109, 2, 142)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (110, 2, 1611)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (111, 2, 1612)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (112, 2, 1621)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (113, 2, 1622)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (114, 2, 211)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (115, 2, 311)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (116, 2, 11101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (117, 2, 11102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (118, 2, 11103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (119, 2, 11104)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (120, 2, 11105)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (121, 2, 11106)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (122, 2, 11201)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (123, 2, 11202)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (124, 2, 11203)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (125, 2, 12201)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (126, 2, 12202)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (127, 2, 131101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (128, 2, 131102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (129, 2, 131103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (130, 2, 131104)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (131, 2, 131201)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (132, 2, 131202)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (133, 2, 131203)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (134, 2, 131204)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (135, 2, 13201)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (136, 2, 13202)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (137, 2, 13203)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (138, 2, 14101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (139, 2, 14102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (140, 2, 14103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (141, 2, 14104)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (142, 2, 14105)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (143, 2, 14106)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (144, 2, 14201)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (145, 2, 14202)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (146, 2, 14203)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (147, 2, 161101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (148, 2, 161102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (149, 2, 161103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (150, 2, 161104)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (151, 2, 161201)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (152, 2, 161202)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (153, 2, 161203)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (154, 2, 161204)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (155, 2, 162101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (156, 2, 162102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (157, 2, 162103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (158, 2, 162104)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (159, 2, 21101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (160, 2, 21102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (161, 2, 21103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (162, 2, 31101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (163, 2, 31102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (164, 2, 31103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (165, 3, 51)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (166, 2, 12101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (167, 2, 12102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (168, 2, 12103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (169, 2, 12104)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (170, 2, 15101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (171, 2, 15102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (172, 2, 15103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (173, 2, 15104)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (174, 2, 162201)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (175, 2, 162202)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (176, 2, 162203)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (177, 2, 162204)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (178, 6, 51)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (179, 6, 61)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (180, 6, 151)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (181, 6, 152)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (182, 6, 71)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (183, 6, 72)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (184, 6, 41)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (185, 6, 42)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (186, 6, 431)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (187, 6, 432)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (188, 6, 433)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (189, 6, 441)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (190, 6, 442)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (191, 6, 111)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (192, 6, 112)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (193, 6, 121)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (194, 6, 122)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (195, 6, 131)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (196, 6, 132)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (197, 6, 141)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (198, 6, 142)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (199, 6, 1611)
GO
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (200, 6, 1612)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (201, 6, 1621)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (202, 6, 1622)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (203, 6, 211)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (204, 6, 311)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (205, 6, 11101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (206, 6, 11102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (207, 6, 11103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (208, 6, 11104)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (209, 6, 11105)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (210, 6, 11106)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (211, 6, 11201)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (212, 6, 11202)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (213, 6, 11203)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (214, 6, 12101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (215, 6, 12102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (216, 6, 12103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (217, 6, 12104)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (218, 6, 12201)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (219, 6, 12202)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (220, 6, 131101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (221, 6, 131102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (222, 6, 131103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (223, 6, 131104)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (224, 6, 131201)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (225, 6, 131202)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (226, 6, 131203)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (227, 6, 131204)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (228, 6, 13201)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (229, 6, 13202)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (230, 6, 13203)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (231, 6, 14101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (232, 6, 14102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (233, 6, 14103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (234, 6, 14104)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (235, 6, 14105)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (236, 6, 14106)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (237, 6, 14201)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (238, 6, 14202)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (239, 6, 14203)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (240, 6, 15101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (241, 6, 15102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (242, 6, 15103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (243, 6, 15104)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (244, 6, 161101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (245, 6, 161102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (246, 6, 161103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (247, 6, 161104)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (248, 6, 161201)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (249, 6, 161202)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (250, 6, 161203)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (251, 6, 161204)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (252, 6, 162101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (253, 6, 162102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (254, 6, 162103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (255, 6, 162104)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (256, 6, 162201)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (257, 6, 162202)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (258, 6, 162203)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (259, 6, 162204)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (260, 6, 31101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (261, 6, 31102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (262, 6, 31103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (263, 4, 51)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (264, 4, 61)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (265, 4, 151)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (266, 4, 152)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (267, 4, 71)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (268, 4, 72)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (269, 4, 111)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (270, 4, 112)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (271, 4, 121)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (272, 4, 122)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (273, 4, 131)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (274, 4, 132)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (275, 4, 141)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (276, 4, 142)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (277, 4, 1611)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (278, 4, 1612)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (279, 4, 1621)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (280, 4, 1622)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (281, 4, 211)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (282, 4, 311)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (283, 4, 11101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (284, 4, 11102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (285, 4, 11103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (286, 4, 11104)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (287, 4, 11105)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (288, 4, 11106)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (289, 4, 11201)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (290, 4, 11202)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (291, 4, 11203)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (292, 4, 12101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (293, 4, 12102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (294, 4, 12103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (295, 4, 12104)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (296, 4, 12201)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (297, 4, 12202)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (298, 4, 131101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (299, 4, 131102)
GO
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (300, 4, 131103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (301, 4, 131104)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (302, 4, 131201)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (303, 4, 131202)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (304, 4, 131203)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (305, 4, 131204)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (306, 4, 13201)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (307, 4, 13202)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (308, 4, 13203)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (309, 4, 14101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (310, 4, 14102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (311, 4, 14103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (312, 4, 14104)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (313, 4, 14105)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (314, 4, 14106)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (315, 4, 14201)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (316, 4, 14202)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (317, 4, 14203)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (318, 4, 161101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (319, 4, 161102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (320, 4, 161103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (321, 4, 161104)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (322, 4, 161201)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (323, 4, 161202)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (324, 4, 161203)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (325, 4, 161204)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (326, 4, 162101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (327, 4, 162102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (328, 4, 162103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (329, 4, 162104)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (330, 4, 162201)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (331, 4, 162202)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (332, 4, 162203)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (333, 4, 162204)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (334, 4, 21101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (335, 4, 21102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (336, 4, 21103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (337, 4, 31101)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (338, 4, 31102)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (339, 4, 31103)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (340, 4, 42)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (341, 4, 431)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (342, 4, 432)
INSERT [dbo].[ActionGroup] ([actionGroupID], [groupID], [actionID]) VALUES (343, 4, 433)
SET IDENTITY_INSERT [dbo].[ActionGroup] OFF
INSERT [dbo].[Code] ([code], [value]) VALUES (N'0001', N'客户档案')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00010001', N'客户状态')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100010001', N'新客户')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100010002', N'老客户')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100010003', N'潜在客户')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100010004', N'忠诚客户')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00010002', N'客户性质')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100020001', N'分析师')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100020002', N'竞争者')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100020003', N'集成商')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100020004', N'投资者')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100020005', N'新闻媒体')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100020006', N'转售商')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100020007', N'供应商')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100020008', N'其它')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00010003', N'所有权')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100030001', N'上市公司')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100030002', N'国有公司')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100030003', N'分公司')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100030004', N'保密')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100030005', N'其它')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00010004', N'资源类型')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040001', N'广告')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040002', N'员工推荐')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040003', N'外部推荐')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040004', N'合作伙伴')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040005', N'公共关系')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040006', N'研讨会-内部')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040007', N'研讨会-合作伙伴')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040008', N'展览会')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040009', N'web')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040010', N'口头推荐')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100040011', N'其它')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00010005', N'检索方式')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100050001', N'按部门检索')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100050002', N'按团队检索')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100050003', N'未指定用户')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00010006', N'合作状态')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100060001', N'未合作')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100060002', N'正在合作')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100060003', N'已合作')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000100060004', N'已取消合作')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'0002', N'营销活动')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00020002', N'活动范围')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200020001', N'面向大型企业')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200020002', N'面向中型企业')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200020003', N'面向小型企业及个人')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00020003', N'活动周期')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200030001', N'非周期')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200030002', N'年')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200030003', N'季度')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200030004', N'月')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200030005', N'日')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00020004', N'重视程度')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200040001', N'高')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200040002', N'中')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200040003', N'低')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00020005', N'活动状态')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200050001', N'未开始')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200050002', N'进行中')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200050003', N'已结束')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00020006', N'活动类型')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200060001', N'顾客销售会/联谊会')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200060002', N'重点顾客参与公司相关会议')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200060003', N'座谈会/恳谈会')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200060004', N'经验交流会')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200060005', N'意见征询会')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200060006', N'评选类活动')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200060007', N'旅游活动')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200060008', N'节假日聚会/电话问候与家访')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200060009', N'组织战友会、同学会等')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000200060010', N'其它')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'0003', N'共通')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00030002', N'学历')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300020001', N'硕士')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300020002', N'本科')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300020003', N'专科')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300020004', N'高中')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300020005', N'其它')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00030003', N'规模')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300030001', N'10人以下')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300030002', N'10~99人')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300030003', N'100~299人')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300030004', N'300~999人')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300030005', N'1000~1999人')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300030006', N'2000人以上')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300030007', N'保密')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00030004', N'性别')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300040001', N'男')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300040002', N'女')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300040003', N'保密')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00030005', N'信任级别')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300050001', N'值得信任')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300050002', N'可以信任')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300050003', N'一般信任')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00030006', N'价值评估')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300060001', N'高')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300060002', N'一般')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300060003', N'低')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00030007', N'反应速度')
GO
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300070001', N'迅速')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300070002', N'较快')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300070003', N'良好')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300070004', N'一般')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00030008', N'行业')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080001', N'农业')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080002', N'金融')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080003', N'能源')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080004', N'政府')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080005', N'媒体')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080006', N'机械')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080007', N'保险')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080008', N'制造')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080009', N'零售')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080010', N'非盈利机构')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080011', N'服装')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080012', N'IT')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080013', N'银行')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080014', N'通讯')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080015', N'建筑')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080016', N'生物技术')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080017', N'化学')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080018', N'电子')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000300080019', N'其它')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'0004', N'销售流程')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00040001', N'客户联系')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00040002', N'意向订单')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00040003', N'订单')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00040004', N'合同')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'0005', N'产品管理')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00050001', N'产品类型')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500010001', N'服务类产品')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500010002', N'实体类产品')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00050002', N'产品折扣')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020001', N'0.05')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020002', N'0.10')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020003', N'0.15')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020004', N'0.20')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020005', N'0.25')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020006', N'0.30')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020007', N'0.35')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020008', N'0.40')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020009', N'0.45')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020010', N'0.50')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020011', N'0.55')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020012', N'0.60')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020013', N'0.65')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020014', N'0.70')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020015', N'0.75')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020016', N'0.80')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020017', N'0.85')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020018', N'0.90')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020019', N'0.95')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000500020020', N'1.00')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'0006', N'客户联系')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00060001', N'联系需求')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000600010001', N'一般')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000600010002', N'重要')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000600010003', N'非常重要')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000600010004', N'急需联系')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00060002', N'联系方式')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000600020001', N'电话联系')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000600020002', N'邮件联系')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000600020003', N'视频会议')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000600020004', N'拜访客户')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000600020005', N'客户回访')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000600020006', N'其它')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00060003', N'联系与否')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000600030001', N'是')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000600030002', N'否')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'0007', N'竞争对手与合作伙伴')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00070001', N'对手资金力量')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700010001', N'50万以下')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700010002', N'50~500万')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700010003', N'500~1000万')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700010004', N'1000~2000万')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700010005', N'2000~5000万')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700010006', N'5000万~1亿')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700010007', N'1~10亿')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700010008', N'10亿以上')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700010009', N'保密')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00070002', N'对手员工规模')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700020001', N'50人以下')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700020002', N'51~100人')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700020003', N'101~300人')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700020004', N'301~1000人')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700020005', N'1001~2000人')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700020006', N'2000人以上')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700020007', N'保密')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00070003', N'对手性质')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700030001', N'国有企业')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700030002', N'民营企业')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700030003', N'外商投资企业')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700030004', N'开发团队')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700030005', N'有限责任公司')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700030006', N'股份有限公司')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700030007', N'其它')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00070004', N'竞争类型')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700040001', N'现有的对手')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700040002', N'潜在的对手')
GO
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00070005', N'伙伴类型')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700050001', N'普通伙伴')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700050002', N'重要伙伴')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00070006', N'管理能力')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700060001', N'非常强')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700060002', N'较强')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700060003', N'良好')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000700060004', N'一般')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'0008', N'合同与建议书')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00080001', N'付款方式')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000800010001', N'现金支付')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000800010002', N'银行转账支付')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00080002', N'绑定订单')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000800020001', N'绑定订单')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000800020002', N'不绑定订单')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'0009', N'销售管理')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00090001', N'订单类型')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000900010001', N'普通订单')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000900010002', N'VIP订单')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00090002', N'作业方式')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000900020001', N'客户公司')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000900020002', N'我方公司')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000900020003', N'两处同步')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00090003', N'作业状态')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000900030001', N'未进行')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000900030002', N'正在进行')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000900030003', N'已交付')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000900030004', N'已取消')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00090004', N'事件处理')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000900040001', N'允许通过')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'000900040002', N'拒绝通过')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'0010', N'系统管理')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00100001', N'团队状态')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001000010001', N'活动')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001000010002', N'解散')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00100002', N'技术职称')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001000020001', N'SE')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001000020002', N'PJL')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001000020003', N'PG')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001000020004', N'SSE')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'0011', N'任务')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00110001', N'任务类型')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100010001', N'个人任务')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100010002', N'分配任务')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100010003', N'被分配任务')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00110002', N'完成状态')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100020001', N'未开始')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100020002', N'进行中')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100020003', N'完成')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00110003', N'工作类型')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030001', N'Preparation before Project Start')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030002', N'Planning and Tracking')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030003', N'Intergroup Coordination')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030004', N'Requirement')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030005', N'Design')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030006', N'Coding')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030007', N'Unit Test')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030008', N'Integration Test')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030009', N'SubSystem Test')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030010', N'System Test')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030011', N'Acceptance Test')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030012', N'User Documentation Test')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030013', N'Project Training')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030014', N'SCM')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030015', N'PPQA')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030016', N'project HR')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030017', N'Center Activity')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100030018', N'Work after Project End')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'00110004', N'完成优先级别')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100040001', N'高')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100040002', N'一般')
INSERT [dbo].[Code] ([code], [value]) VALUES (N'001100040003', N'低')
SET IDENTITY_INSERT [dbo].[CompetitorInfo] ON 

INSERT [dbo].[CompetitorInfo] ([competitorInfoId], [competitorName], [competitorField], [competitorProperty], [competitorLocation], [competitorFoundDate], [competitorMoney], [competitorPeople], [competitorDescription], [competitorType], [isAbolished], [competitorDetail]) VALUES (1, N'竞争', N'hangye', N'000700030001', N'anhui', N'2014-12-16', N'000700010002', N'000700020002', N'', N'000700040001', 0, N'​')
SET IDENTITY_INSERT [dbo].[CompetitorInfo] OFF
SET IDENTITY_INSERT [dbo].[ContactInfo] ON 

INSERT [dbo].[ContactInfo] ([contactID], [contactName], [sex], [birthday], [company], [department], [job], [phoneNumber], [nativePlace], [contactNational], [addr], [education], [health], [email], [fax], [QQ], [homePhone], [political], [hobby], [jobResume], [eduBackground], [isAbolished], [descriptions]) VALUES (1, N'联系人', N'000300040001', NULL, N'test', N'test', N'他说他', N'14444444', N'vbvxc', N'法撒旦', N'', N'000300020001', N'属性', N'', N'', N'', N'', N'不v型吃', N'', N'​', N'​', 0, N'这边先吃吧')
INSERT [dbo].[ContactInfo] ([contactID], [contactName], [sex], [birthday], [company], [department], [job], [phoneNumber], [nativePlace], [contactNational], [addr], [education], [health], [email], [fax], [QQ], [homePhone], [political], [hobby], [jobResume], [eduBackground], [isAbolished], [descriptions]) VALUES (2, N'test', N'000300040002', CAST(0xFE380B00 AS Date), N'gf', N'fg', N'fg', N'1333333', N'', N'', N'', NULL, N'', N'', N'', N'', N'', N'', N'', N'', N'', 0, N'')
SET IDENTITY_INSERT [dbo].[ContactInfo] OFF
SET IDENTITY_INSERT [dbo].[ContactSelectInfo] ON 

INSERT [dbo].[ContactSelectInfo] ([contactSelectID], [contactID], [objectID], [flag]) VALUES (1, 1, 1, 1)
INSERT [dbo].[ContactSelectInfo] ([contactSelectID], [contactID], [objectID], [flag]) VALUES (2, 2, 2, 1)
SET IDENTITY_INSERT [dbo].[ContactSelectInfo] OFF
SET IDENTITY_INSERT [dbo].[ContactSocialInfo] ON 

INSERT [dbo].[ContactSocialInfo] ([socialID], [contactID], [socialRelation], [socialName], [birthday], [political], [company], [job], [isAbolished], [descriptions]) VALUES (1, 1, N'不想吃v', N'不想吃vb', CAST(0xF6380B00 AS Date), N'', N'', N'', 0, N'')
INSERT [dbo].[ContactSocialInfo] ([socialID], [contactID], [socialRelation], [socialName], [birthday], [political], [company], [job], [isAbolished], [descriptions]) VALUES (2, 1, N'bvnv', N'nbvc', CAST(0x58390B00 AS Date), N'vb', N'nbvb', N'n', 0, N'')
INSERT [dbo].[ContactSocialInfo] ([socialID], [contactID], [socialRelation], [socialName], [birthday], [political], [company], [job], [isAbolished], [descriptions]) VALUES (3, 1, N'n', N'nv', CAST(0x5A390B00 AS Date), N'b', N'bb', N'bvb', 0, N'nvbnn')
SET IDENTITY_INSERT [dbo].[ContactSocialInfo] OFF
SET IDENTITY_INSERT [dbo].[ContactTrackInfo] ON 

INSERT [dbo].[ContactTrackInfo] ([contactID], [customerID], [weContact], [oppositeContact], [contactWay], [contactContent], [contactType], [planDateBegin], [realityDateBegin], [realityDateEnd], [userFeedbackInformation], [strategy], [notContantReason], [ifContact], [isAbolished], [remarks], [eventID], [state], [checkResult]) VALUES (1, 1, 3, 1, N'000600020001', N'baifang', N'000600010001', CAST(0x0000A3FD00E150DC AS DateTime), CAST(0x0000A40A00E1639C AS DateTime), CAST(0x0000A40A00E16E28 AS DateTime), N'联系', N'test', NULL, 1, 0, N'', 0, 0, 0)
INSERT [dbo].[ContactTrackInfo] ([contactID], [customerID], [weContact], [oppositeContact], [contactWay], [contactContent], [contactType], [planDateBegin], [realityDateBegin], [realityDateEnd], [userFeedbackInformation], [strategy], [notContantReason], [ifContact], [isAbolished], [remarks], [eventID], [state], [checkResult]) VALUES (2, 1, 3, 1, N'000600020001', N'vxv', N'000600010002', CAST(0x0000A40A00E20554 AS DateTime), CAST(0x0000A40A00E216E8 AS DateTime), CAST(0x0000A40A00E22174 AS DateTime), N'test', N'test', NULL, 1, 0, N'', 13, 1, 0)
INSERT [dbo].[ContactTrackInfo] ([contactID], [customerID], [weContact], [oppositeContact], [contactWay], [contactContent], [contactType], [planDateBegin], [realityDateBegin], [realityDateEnd], [userFeedbackInformation], [strategy], [notContantReason], [ifContact], [isAbolished], [remarks], [eventID], [state], [checkResult]) VALUES (3, 2, 3, 2, N'000600020002', N'test', N'000600010001', CAST(0x0000A40B00E30D00 AS DateTime), CAST(0x0000A40B00E32344 AS DateTime), CAST(0x0000A40B00E326C8 AS DateTime), N'test', N'test', NULL, 1, 0, N'', 0, 0, 0)
SET IDENTITY_INSERT [dbo].[ContactTrackInfo] OFF
SET IDENTITY_INSERT [dbo].[CoopResumeInfo] ON 

INSERT [dbo].[CoopResumeInfo] ([coopResumeID], [customerID], [projectName], [projectDetail], [expBeginDate], [expEndDate], [beginDate], [endDate], [projectScale], [peopleNumber], [principalWe], [principalThey], [projectType], [appraisal], [descriptions], [isAbolished]) VALUES (1, 1, N'艾弗森的', N'vbxz', CAST(0xFE380B00 AS Date), CAST(0x02390B00 AS Date), CAST(0x03390B00 AS Date), NULL, 6, 11, N'撒地方', N'发的说法', N'000100060004', N'v vb中下', N'', 1)
SET IDENTITY_INSERT [dbo].[CoopResumeInfo] OFF
SET IDENTITY_INSERT [dbo].[CprAnalysis] ON 

INSERT [dbo].[CprAnalysis] ([cprAnalysisID], [competitorInfoId], [area], [ability], [targets], [strategy], [prediction], [advantage], [disadvantage], [advAnalysis], [disadvAnalysis], [others], [compositeComp], [compositeDefense], [cprAnalysisTime], [advice], [isAbolished]) VALUES (1, 1, N'000300080001', N'', N'', N'', N'', N'111', N'111', N'', N'', N'', 4, 1, N'2014-12-23 13:56', N'', 0)
SET IDENTITY_INSERT [dbo].[CprAnalysis] OFF
SET IDENTITY_INSERT [dbo].[CustomerInfo] ON 

INSERT [dbo].[CustomerInfo] ([customerID], [holder], [customerName], [industry], [customerType], [customerStatement], [valueEvaluate], [scale], [customerAddr], [fee], [earning], [isAbolished], [descriptions]) VALUES (1, 3, N'客户1', N'000300080001', N'000100020001', N'000100010001', N'000300060001', N'000300030001', N'form', N'000100030001', N'4', 0, N'')
INSERT [dbo].[CustomerInfo] ([customerID], [holder], [customerName], [industry], [customerType], [customerStatement], [valueEvaluate], [scale], [customerAddr], [fee], [earning], [isAbolished], [descriptions]) VALUES (2, 3, N'客户2', N'000300080001', N'000100020002', N'000100010001', N'000300060002', N'000300030002', N'test', N'000100030002', N'2', 0, N'')
SET IDENTITY_INSERT [dbo].[CustomerInfo] OFF
SET IDENTITY_INSERT [dbo].[CustomerSourceInfo] ON 

INSERT [dbo].[CustomerSourceInfo] ([sourceID], [customerID], [sourceArea], [sourceType], [isAbolished], [descriptions]) VALUES (1, 1, N'ncvb', N'000100040001', 0, N'ncv')
SET IDENTITY_INSERT [dbo].[CustomerSourceInfo] OFF
SET IDENTITY_INSERT [dbo].[Department] ON 

INSERT [dbo].[Department] ([departmentID], [serialNumber], [departmentName], [fatherDepartmentID], [departmentManager], [departmentPhone], [createTime], [departmentDescription], [depth]) VALUES (1, N'0001', N'科大国创', -1, -1, NULL, NULL, NULL, 1)
INSERT [dbo].[Department] ([departmentID], [serialNumber], [departmentName], [fatherDepartmentID], [departmentManager], [departmentPhone], [createTime], [departmentDescription], [depth]) VALUES (2, N'0002', N'通用系统部', 1, 3, N'13433333', N'2014-09-03', N'', 2)
INSERT [dbo].[Department] ([departmentID], [serialNumber], [departmentName], [fatherDepartmentID], [departmentManager], [departmentPhone], [createTime], [departmentDescription], [depth]) VALUES (3, N'0003', N'通用一部', 2, 5, N'1444444', N'2014-09-11', N'', 3)
INSERT [dbo].[Department] ([departmentID], [serialNumber], [departmentName], [fatherDepartmentID], [departmentManager], [departmentPhone], [createTime], [departmentDescription], [depth]) VALUES (4, N'0004', N'通用二部', 2, 9, N'1444444', N'2014-12-26', N'', 3)
INSERT [dbo].[Department] ([departmentID], [serialNumber], [departmentName], [fatherDepartmentID], [departmentManager], [departmentPhone], [createTime], [departmentDescription], [depth]) VALUES (5, N'0005', N'通用三部', 2, 65, N'', N'2014-12-26', N'', 3)
SET IDENTITY_INSERT [dbo].[Department] OFF
SET IDENTITY_INSERT [dbo].[GroupManager] ON 

INSERT [dbo].[GroupManager] ([groupID], [groupName]) VALUES (0, N'系统管理员')
INSERT [dbo].[GroupManager] ([groupID], [groupName]) VALUES (1, N'超级管理员')
INSERT [dbo].[GroupManager] ([groupID], [groupName]) VALUES (2, N'管理员')
INSERT [dbo].[GroupManager] ([groupID], [groupName]) VALUES (3, N'员工')
INSERT [dbo].[GroupManager] ([groupID], [groupName]) VALUES (4, N'部长')
INSERT [dbo].[GroupManager] ([groupID], [groupName]) VALUES (5, N'副部长')
INSERT [dbo].[GroupManager] ([groupID], [groupName]) VALUES (6, N'技术经理')
SET IDENTITY_INSERT [dbo].[GroupManager] OFF
SET IDENTITY_INSERT [dbo].[OrderInfo] ON 

INSERT [dbo].[OrderInfo] ([id], [orderID], [customerID], [orderState], [contactID], [ourRepresentative], [deliveryDate], [transactionPrice], [remark], [isAbolished], [ourTelephone], [type], [customerContact], [eventID]) VALUES (1, N'FUJI20140915113021216', 1, N'000900030001', 1, N'test', N'2014-09-15', 11111.33, N'', 0, N'1444444', 1, N'14444444', 2)
INSERT [dbo].[OrderInfo] ([id], [orderID], [customerID], [orderState], [contactID], [ourRepresentative], [deliveryDate], [transactionPrice], [remark], [isAbolished], [ourTelephone], [type], [customerContact], [eventID]) VALUES (2, N'FUJI20140915150153654', 1, N'000900030001', 1, N'et', N'2014-09-15', 0.01, N'', 0, N'1344444', 1, N'14444444', 3)
INSERT [dbo].[OrderInfo] ([id], [orderID], [customerID], [orderState], [contactID], [ourRepresentative], [deliveryDate], [transactionPrice], [remark], [isAbolished], [ourTelephone], [type], [customerContact], [eventID]) VALUES (3, N'FUJI20141223135932156', 1, N'000900030001', 1, N'tse', N'2014-12-23', 0.01, N'', 0, N'15166666666', 0, N'14444444', 9)
SET IDENTITY_INSERT [dbo].[OrderInfo] OFF
SET IDENTITY_INSERT [dbo].[OrderTrackInfo] ON 

INSERT [dbo].[OrderTrackInfo] ([orderTrackNo], [editPeople], [orderID], [afterState], [recordTime], [isAbolished]) VALUES (1, 0, N'FUJI20140915113021216', N'000900030001', N'2014-09-15 11:30:35', 0)
INSERT [dbo].[OrderTrackInfo] ([orderTrackNo], [editPeople], [orderID], [afterState], [recordTime], [isAbolished]) VALUES (2, 0, N'FUJI20140915150153654', N'000900030001', N'2014-09-15 15:02:29', 0)
INSERT [dbo].[OrderTrackInfo] ([orderTrackNo], [editPeople], [orderID], [afterState], [recordTime], [isAbolished]) VALUES (3, 3, N'FUJI20141223135932156', N'000900030001', N'2014-12-23 14:00:34', 0)
SET IDENTITY_INSERT [dbo].[OrderTrackInfo] OFF
SET IDENTITY_INSERT [dbo].[ProductInfo] ON 

INSERT [dbo].[ProductInfo] ([id], [name], [category], [description], [remark], [productID], [productModel], [price]) VALUES (1, N'test', N'000500010001', N'bvxcb', N'', N'FW20140915113003954  ', NULL, CAST(111.00 AS Decimal(18, 2)))
SET IDENTITY_INSERT [dbo].[ProductInfo] OFF
SET IDENTITY_INSERT [dbo].[ProjectTeam] ON 

INSERT [dbo].[ProjectTeam] ([projectTeamID], [departmentID], [projectTeamName], [projectTeamLeaderID], [projectTeamStatusCode], [createTime], [description], [endTime]) VALUES (1, 4, N'FM', 14, N'001000010001', N'2014-12-26', N'', N'')
INSERT [dbo].[ProjectTeam] ([projectTeamID], [departmentID], [projectTeamName], [projectTeamLeaderID], [projectTeamStatusCode], [createTime], [description], [endTime]) VALUES (2, 4, N'WebUIPF', 6, N'001000010001', N'2014-12-02', N'', N'')
INSERT [dbo].[ProjectTeam] ([projectTeamID], [departmentID], [projectTeamName], [projectTeamLeaderID], [projectTeamStatusCode], [createTime], [description], [endTime]) VALUES (3, 4, N'DALM', 11, NULL, N'', N'', N'')
INSERT [dbo].[ProjectTeam] ([projectTeamID], [departmentID], [projectTeamName], [projectTeamLeaderID], [projectTeamStatusCode], [createTime], [description], [endTime]) VALUES (4, 4, N'PM', 9, NULL, N'', N'', N'')
INSERT [dbo].[ProjectTeam] ([projectTeamID], [departmentID], [projectTeamName], [projectTeamLeaderID], [projectTeamStatusCode], [createTime], [description], [endTime]) VALUES (5, 3, N'Scan', 22, NULL, N'', N'', N'')
SET IDENTITY_INSERT [dbo].[ProjectTeam] OFF
SET IDENTITY_INSERT [dbo].[SalesEventFlow] ON 

INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (10, 2, 1, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (11, 2, 2, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (12, 2, 4, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (13, 2, 5, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (14, 2, 6, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (15, 2, 7, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (16, 2, 8, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (17, 2, 9, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (18, 2, 3, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (19, 3, 1, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (20, 3, 2, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (21, 3, 4, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (22, 3, 5, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (23, 3, 6, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (24, 3, 7, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (25, 3, 8, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (26, 3, 9, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (27, 3, 3, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (28, 4, 1, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (29, 4, 2, N'test')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (30, 4, 4, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (31, 4, 5, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (32, 4, 6, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (33, 4, 7, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (34, 4, 8, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (35, 4, 9, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (36, 4, 3, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (37, 5, 1, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (38, 5, 2, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (39, 5, 4, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (40, 5, 5, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (41, 5, 6, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (42, 5, 7, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (43, 5, 8, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (44, 5, 9, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (45, 5, 3, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (46, 6, 1, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (47, 6, 2, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (48, 6, 4, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (49, 6, 5, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (50, 6, 6, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (51, 6, 7, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (52, 6, 8, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (53, 6, 9, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (54, 6, 3, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (55, 7, 1, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (56, 7, 2, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (57, 7, 4, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (58, 7, 5, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (59, 7, 6, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (60, 7, 7, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (61, 7, 8, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (62, 7, 9, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (63, 7, 3, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (64, 8, 1, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (65, 8, 2, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (66, 8, 4, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (67, 8, 5, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (68, 8, 6, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (69, 8, 7, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (70, 8, 8, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (71, 8, 9, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (72, 8, 3, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (73, 9, 1, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (74, 9, 2, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (75, 9, 4, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (76, 9, 5, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (77, 9, 6, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (78, 9, 7, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (79, 9, 8, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (80, 9, 9, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (81, 9, 3, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (82, 10, 1, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (83, 10, 2, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (84, 10, 4, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (85, 10, 5, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (86, 10, 6, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (87, 10, 7, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (88, 10, 8, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (89, 10, 9, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (90, 10, 3, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (91, 11, 1, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (92, 11, 2, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (93, 11, 4, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (94, 11, 5, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (95, 11, 6, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (96, 11, 7, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (97, 11, 8, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (98, 11, 9, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (99, 11, 3, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (100, 12, 1, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (101, 12, 2, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (102, 12, 4, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (103, 12, 5, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (104, 12, 6, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (105, 12, 7, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (106, 12, 8, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (107, 12, 9, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (108, 12, 3, N'')
GO
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (109, 13, 1, N'这是一个机会')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (110, 13, 2, N'')
INSERT [dbo].[SalesEventFlow] ([id], [eventID], [status], [demand]) VALUES (111, 13, 4, N'需要')
SET IDENTITY_INSERT [dbo].[SalesEventFlow] OFF
SET IDENTITY_INSERT [dbo].[SalesEventFlowCode] ON 

INSERT [dbo].[SalesEventFlowCode] ([code], [value], [category], [sort]) VALUES (1, N'发现机会', N'00040001', 1)
INSERT [dbo].[SalesEventFlowCode] ([code], [value], [category], [sort]) VALUES (2, N'验证机会', N'00040001', 2)
INSERT [dbo].[SalesEventFlowCode] ([code], [value], [category], [sort]) VALUES (3, N'中标', N'00040004', 9)
INSERT [dbo].[SalesEventFlowCode] ([code], [value], [category], [sort]) VALUES (4, N'需求分析', N'00040002', 3)
INSERT [dbo].[SalesEventFlowCode] ([code], [value], [category], [sort]) VALUES (5, N'价值分析', N'00040002', 4)
INSERT [dbo].[SalesEventFlowCode] ([code], [value], [category], [sort]) VALUES (6, N'确定决策者', N'00040002', 5)
INSERT [dbo].[SalesEventFlowCode] ([code], [value], [category], [sort]) VALUES (7, N'认知', N'00040002', 6)
INSERT [dbo].[SalesEventFlowCode] ([code], [value], [category], [sort]) VALUES (8, N'报价', N'00040003', 7)
INSERT [dbo].[SalesEventFlowCode] ([code], [value], [category], [sort]) VALUES (9, N'谈判审核', N'00040003', 8)
SET IDENTITY_INSERT [dbo].[SalesEventFlowCode] OFF
SET IDENTITY_INSERT [dbo].[SalesEventInfo] ON 

INSERT [dbo].[SalesEventInfo] ([eventID], [eventName], [customerID], [contactID], [submitterID], [status], [remarks], [submitDate], [isAbolished]) VALUES (2, N'test', 1, 1, 3, N'9', N'', N'2014-09-15', 0)
INSERT [dbo].[SalesEventInfo] ([eventID], [eventName], [customerID], [contactID], [submitterID], [status], [remarks], [submitDate], [isAbolished]) VALUES (3, N'test2', 1, 1, 3, N'9', N'华为P7', N'2014-09-15', 0)
INSERT [dbo].[SalesEventInfo] ([eventID], [eventName], [customerID], [contactID], [submitterID], [status], [remarks], [submitDate], [isAbolished]) VALUES (4, N'test3', 1, 1, 3, N'8', N'dianxin', N'2014-09-15', 0)
INSERT [dbo].[SalesEventInfo] ([eventID], [eventName], [customerID], [contactID], [submitterID], [status], [remarks], [submitDate], [isAbolished]) VALUES (5, N'test4', 1, 1, 3, N'7', N'', N'2014-09-15', 0)
INSERT [dbo].[SalesEventInfo] ([eventID], [eventName], [customerID], [contactID], [submitterID], [status], [remarks], [submitDate], [isAbolished]) VALUES (6, N'test5', 1, 1, 3, N'7', N'', N'2014-09-15', 0)
INSERT [dbo].[SalesEventInfo] ([eventID], [eventName], [customerID], [contactID], [submitterID], [status], [remarks], [submitDate], [isAbolished]) VALUES (7, N'test6', 1, 1, 3, N'5', N'gfdsg', N'2014-09-15', 0)
INSERT [dbo].[SalesEventInfo] ([eventID], [eventName], [customerID], [contactID], [submitterID], [status], [remarks], [submitDate], [isAbolished]) VALUES (8, N'test7', 1, 1, 3, N'5', N'', N'2014-09-15', 0)
INSERT [dbo].[SalesEventInfo] ([eventID], [eventName], [customerID], [contactID], [submitterID], [status], [remarks], [submitDate], [isAbolished]) VALUES (9, N'test8', 1, 1, 3, N'7', N'', N'2014-09-15', 0)
INSERT [dbo].[SalesEventInfo] ([eventID], [eventName], [customerID], [contactID], [submitterID], [status], [remarks], [submitDate], [isAbolished]) VALUES (10, N'test9', 1, 1, 3, N'1', N'', N'2014-09-15', 0)
INSERT [dbo].[SalesEventInfo] ([eventID], [eventName], [customerID], [contactID], [submitterID], [status], [remarks], [submitDate], [isAbolished]) VALUES (11, N'test10', 1, 1, 3, N'1', N'', N'2014-09-15', 0)
INSERT [dbo].[SalesEventInfo] ([eventID], [eventName], [customerID], [contactID], [submitterID], [status], [remarks], [submitDate], [isAbolished]) VALUES (12, N'test11', 1, 1, 3, N'1', N'', N'2014-09-15', 0)
INSERT [dbo].[SalesEventInfo] ([eventID], [eventName], [customerID], [contactID], [submitterID], [status], [remarks], [submitDate], [isAbolished]) VALUES (13, N'事件', 1, 1, 3, N'4', N'', N'2014-12-23', 0)
SET IDENTITY_INSERT [dbo].[SalesEventInfo] OFF
SET IDENTITY_INSERT [dbo].[SalesTrackInfo] ON 

INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (2, 3, 2, 1, N'1', N'2014-09-15 11:29:05', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (3, 3, 2, 1, N'2', N'2014-09-15 11:29:10', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (4, 3, 2, 1, N'4', N'2014-09-15 11:29:19', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (5, 3, 2, 1, N'5', N'2014-09-15 11:29:23', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (6, 3, 2, 1, N'6', N'2014-09-15 11:29:26', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (7, 3, 2, 1, N'7', N'2014-09-15 11:29:30', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (8, 3, 2, 1, N'8', N'2014-09-15 11:29:33', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (9, 3, 2, 1, N'9', N'2014-09-15 11:29:42', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (10, 3, 2, 1, N'3', N'2014-09-15 11:30:45', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (11, 3, 3, 1, N'1', N'2014-09-15 11:31:13', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (12, 3, 3, 1, N'2', N'2014-09-15 11:31:20', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (13, 3, 3, 1, N'4', N'2014-09-15 11:31:22', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (14, 3, 3, 1, N'5', N'2014-09-15 11:31:27', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (15, 3, 3, 1, N'6', N'2014-09-15 11:31:31', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (16, 3, 3, 1, N'7', N'2014-09-15 11:31:34', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (17, 3, 3, 1, N'8', N'2014-09-15 11:31:40', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (18, 3, 3, 1, N'9', N'2014-09-15 11:31:45', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (19, 3, 4, 1, N'1', N'2014-09-15 11:32:04', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (20, 3, 4, 1, N'2', N'2014-09-15 11:32:18', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (21, 3, 4, 1, N'4', N'2014-09-15 11:32:22', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (22, 3, 4, 1, N'5', N'2014-09-15 11:32:25', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (23, 3, 4, 1, N'6', N'2014-09-15 11:32:30', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (24, 3, 4, 1, N'7', N'2014-09-15 11:32:33', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (25, 3, 4, 1, N'8', N'2014-09-15 11:32:38', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (26, 3, 5, 1, N'1', N'2014-09-15 11:32:55', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (27, 3, 5, 1, N'2', N'2014-09-15 11:32:59', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (28, 3, 5, 1, N'4', N'2014-09-15 11:33:02', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (29, 3, 5, 1, N'5', N'2014-09-15 11:33:05', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (30, 3, 5, 1, N'6', N'2014-09-15 11:33:09', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (31, 3, 5, 1, N'7', N'2014-09-15 11:33:13', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (32, 3, 6, 1, N'1', N'2014-09-15 11:33:25', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (33, 3, 6, 1, N'2', N'2014-09-15 11:33:29', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (34, 3, 6, 1, N'4', N'2014-09-15 11:33:32', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (35, 3, 6, 1, N'5', N'2014-09-15 11:33:37', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (36, 3, 6, 1, N'6', N'2014-09-15 11:33:44', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (37, 3, 7, 1, N'1', N'2014-09-15 11:33:59', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (38, 3, 7, 1, N'2', N'2014-09-15 11:34:03', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (39, 3, 7, 1, N'4', N'2014-09-15 11:34:06', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (40, 3, 7, 1, N'5', N'2014-09-15 11:34:10', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (41, 3, 8, 1, N'1', N'2014-09-15 11:36:02', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (42, 3, 9, 1, N'1', N'2014-09-15 11:36:13', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (43, 3, 8, 1, N'2', N'2014-09-15 11:36:19', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (44, 3, 8, 1, N'4', N'2014-09-15 11:36:23', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (45, 3, 9, 1, N'2', N'2014-09-15 11:36:26', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (46, 3, 10, 1, N'1', N'2014-09-15 11:36:38', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (47, 3, 11, 1, N'1', N'2014-09-15 11:37:21', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (48, 3, 12, 1, N'1', N'2014-09-15 11:37:54', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (49, 3, 2, 1, N'3', N'2014-09-15 11:40:55', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (50, 3, 2, 1, N'3', N'2014-09-15 11:47:05', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (51, 3, 6, 1, N'7', N'2014-09-15 14:48:55', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (52, 3, 8, 1, N'5', N'2014-09-15 14:49:31', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (53, 3, 2, 1, N'3', N'2014-09-15 14:54:09', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (54, 3, 2, 1, N'3', N'2014-09-15 15:01:33', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (55, 3, 2, 1, N'3', N'2014-09-15 15:01:37', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (56, 3, 3, 1, N'3', N'2014-09-15 15:03:13', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (57, 3, 2, 1, N'3', N'2014-10-08 09:54:30', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (58, 3, 13, 1, N'1', N'2014-12-23 13:43:19', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (59, 3, 13, 1, N'2', N'2014-12-23 13:58:44', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (60, 3, 13, 1, N'4', N'2014-12-23 13:59:18', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (61, 3, 9, 1, N'4', N'2014-12-23 14:00:34', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (62, 3, 9, 1, N'5', N'2014-12-23 14:00:34', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (63, 3, 9, 1, N'6', N'2014-12-23 14:00:34', 0)
INSERT [dbo].[SalesTrackInfo] ([salesTrackNo], [submitterID], [eventID], [customerID], [status], [submitDate], [isAbolished]) VALUES (64, 3, 9, 1, N'7', N'2014-12-23 14:00:34', 0)
SET IDENTITY_INSERT [dbo].[SalesTrackInfo] OFF
SET IDENTITY_INSERT [dbo].[SelectProductInfo] ON 

INSERT [dbo].[SelectProductInfo] ([selectProductID], [id], [orderID], [productID], [name], [price], [productNumber], [isAbolished], [discount]) VALUES (13, 1, N'FUJI20141223135932156', N'FW20140915113003954  ', N'test', CAST(111.00 AS Decimal(18, 2)), 1, 0, CAST(111.00 AS Decimal(18, 2)))
INSERT [dbo].[SelectProductInfo] ([selectProductID], [id], [orderID], [productID], [name], [price], [productNumber], [isAbolished], [discount]) VALUES (9, 1, N'FUJI20140915150153654', N'FW20140915113003954  ', N'test', CAST(111.00 AS Decimal(18, 2)), 1, 0, CAST(111.00 AS Decimal(18, 2)))
INSERT [dbo].[SelectProductInfo] ([selectProductID], [id], [orderID], [productID], [name], [price], [productNumber], [isAbolished], [discount]) VALUES (12, 1, N'FUJI20140915113021216', N'FW20140915113003954  ', N'test', CAST(111.00 AS Decimal(18, 2)), 1111133, 0, CAST(100.00 AS Decimal(18, 2)))
SET IDENTITY_INSERT [dbo].[SelectProductInfo] OFF
SET IDENTITY_INSERT [dbo].[Tree] ON 

INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (1, N'menu', 1, N'客户管理', 0, 0, -1)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (2, N'menu', 2, N'联系人管理', 0, 0, -1)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (3, N'menu', 3, N'营销活动管理', 0, 0, -1)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (4, N'menu', 5, N'统计', 0, 0, -1)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (5, N'menu', 6, N'销售管理', 0, 0, -1)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (6, N'menu', 7, N'产品管理', 0, 0, -1)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (7, N'menu', 4, N'系统管理', 0, 0, -1)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (8, N'menu', 11, N'客户档案', 0, 0, 1)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (9, N'menu', 12, N'客户联系跟踪', 0, 0, 1)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (10, N'menu', 13, N'竞争对手', 0, 0, 1)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (11, N'menu', 14, N'合作伙伴', 0, 0, 1)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (12, N'menu', 15, N'订单', 0, 0, 6)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (13, N'menu', 16, N'建议书与合同', 0, 0, 1)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (14, N'menu', 21, N'联系人档案', 0, 0, 2)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (15, N'menu', 31, N'营销活动', 0, 0, 3)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (17, N'menu', 51, N'销售漏斗统计', 0, 1, 5)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (18, N'menu', 161, N'建议书管理', 0, 0, 16)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (19, N'menu', 61, N'销售事件', 0, 1, 6)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (20, N'menu', 162, N'合同管理', 0, 0, 16)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (21, N'menu', 71, N'产品一览', 0, 1, 7)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (22, N'menu', 72, N'报价管理', 0, 1, 7)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (23, N'menu', 41, N'角色管理', 0, 1, 4)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (25, N'menu', 43, N'组织管理', 0, 0, 4)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (26, N'menu', 44, N'Master管理', 0, 0, 4)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (27, N'menu', 45, N'菜单管理', 0, 1, 4)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (28, N'menu', 111, N'客户基本信息', 0, 1, 11)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (29, N'menu', 112, N'客户来源', 0, 1, 11)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (30, N'menu', 121, N'客户联系', 0, 1, 12)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (31, N'menu', 122, N'客户联系历史', 0, 1, 12)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (32, N'menu', 131, N'竞争对手基本信息', 0, 1, 13)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (33, N'menu', 132, N'竞争对手分析', 0, 1, 13)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (34, N'menu', 141, N'合作伙伴基本信息', 0, 1, 14)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (35, N'menu', 142, N'合作伙伴分析', 0, 1, 14)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (36, N'menu', 151, N'意向订单信息', 0, 1, 15)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (37, N'menu', 152, N'订单基本信息', 0, 1, 15)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (38, N'menu', 1611, N'建议书模板', 0, 1, 161)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (39, N'menu', 1612, N'建议书一览', 0, 1, 161)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (40, N'menu', 1621, N'合同模板', 0, 1, 162)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (41, N'menu', 1622, N'合同一览', 0, 1, 162)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (42, N'menu', 211, N'联系人基本信息', 0, 1, 21)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (43, N'menu', 311, N'营销活动基本信息', 0, 1, 31)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (45, N'menu', 42, N'用户管理', 0, 1, 4)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (46, N'menu', 431, N'组织结构图', 0, 1, 43)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (47, N'menu', 432, N'部门管理', 0, 1, 43)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (48, N'menu', 433, N'团队管理', 0, 1, 43)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (49, N'menu', 441, N'Code管理', 0, 1, 44)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (50, N'menu', 442, N'销售事件流程管理', 0, 1, 44)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (51, N'operation', 1110, N'客户基本信息', 0, 0, -1)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (52, N'operation', 11101, N'客户转移', 0, 1, 1110)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (53, N'operation', 11102, N'查看联系人', 0, 1, 1110)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (54, N'operation', 11103, N'合作履历', 0, 1, 1110)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (55, N'operation', 11104, N'添加客户', 0, 1, 1110)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (56, N'operation', 11105, N'编辑客户', 0, 1, 1110)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (57, N'operation', 11106, N'删除客户', 0, 1, 1110)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (58, N'operation', 1120, N'客户来源', 0, 0, -1)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (59, N'operation', 11201, N'添加客户来源', 0, 1, 1120)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (60, N'operation', 11202, N'编辑客户来源', 0, 1, 1120)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (61, N'operation', 11203, N'删除客户来源', 0, 1, 1120)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (62, N'operation', 1210, N'客户联系', 0, 0, -1)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (63, N'operation', 12101, N'客户反馈', 0, 1, 1210)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (64, N'operation', 12102, N'添加客户联系', 0, 1, 1210)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (65, N'operation', 12103, N'编辑客户联系', 0, 1, 1210)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (66, N'operation', 12104, N'删除客户联系', 0, 1, 1210)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (67, N'operation', 1220, N'客户联系历史', 0, 0, -1)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (68, N'operation', 12201, N'重新反馈', 0, 1, 1220)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (69, N'operation', 12202, N'删除客户联系历史', 0, 1, 1220)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (70, N'operation', 1310, N'竞争对手基本信息', 0, 0, -1)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (71, N'operation', 13110, N'现有竞争对手', 0, 0, 1310)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (72, N'operation', 131101, N'查看详细分析', 0, 1, 13110)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (73, N'operation', 131102, N'添加竞争对手', 0, 1, 13110)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (74, N'operation', 131103, N'编辑竞争对手', 0, 1, 13110)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (75, N'operation', 131104, N'删除竞争对手', 0, 1, 13110)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (76, N'operation', 13120, N'潜在竞争对手', 0, 0, 1310)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (77, N'operation', 131201, N'查看详细分析', 0, 1, 13120)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (78, N'operation', 131202, N'添加竞争对手', 0, 1, 13120)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (79, N'operation', 131203, N'编辑竞争对手', 0, 1, 13120)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (80, N'operation', 131204, N'删除竞争对手', 0, 1, 13120)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (81, N'operation', 1320, N'竞争对手分析', 0, 0, -1)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (82, N'operation', 13201, N'添加分析', 0, 1, 1320)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (83, N'operation', 13202, N'编辑分析', 0, 1, 1320)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (84, N'operation', 13203, N'删除分析', 0, 1, 1320)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (85, N'operation', 1410, N'合作伙伴基本信息', 0, 0, -1)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (86, N'operation', 14101, N'查看详细分析', 0, 1, 1410)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (87, N'operation', 14102, N'查看联系人', 0, 1, 1410)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (88, N'operation', 14103, N'查看合作履历', 0, 1, 1410)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (89, N'operation', 14104, N'添加合作伙伴', 0, 1, 1410)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (90, N'operation', 14105, N'编辑合作伙伴', 0, 1, 1410)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (91, N'operation', 14106, N'删除合作伙伴', 0, 1, 1410)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (92, N'operation', 1420, N'合作伙伴分析', 0, 0, -1)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (93, N'operation', 14201, N'添加分析', 0, 1, 1420)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (94, N'operation', 14202, N'编辑分析', 0, 1, 1420)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (95, N'operation', 14203, N'删除分析', 0, 1, 1420)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (96, N'operation', 1510, N'订单基本信息', 0, 0, -1)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (97, N'operation', 15101, N'订单追踪', 0, 1, 1510)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (98, N'operation', 15102, N'添加订单', 0, 1, 1510)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (99, N'operation', 15103, N'编辑订单', 0, 1, 1510)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (100, N'operation', 15104, N'删除订单', 0, 1, 1510)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (101, N'operation', 16110, N'建议书模板', 0, 0, -1)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (102, N'operation', 161101, N'导出建议书模板', 0, 1, 16110)
GO
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (103, N'operation', 161102, N'添加建议书模板', 0, 1, 16110)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (104, N'operation', 161103, N'编辑建议书模板', 0, 1, 16110)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (105, N'operation', 161104, N'删除建议书模板', 0, 1, 16110)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (106, N'operation', 16120, N'建议书一览', 0, 0, -1)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (107, N'operation', 161201, N'导出建议书', 0, 1, 16120)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (108, N'operation', 161202, N'添加建议书', 0, 1, 16120)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (109, N'operation', 161203, N'编辑建议书', 0, 1, 16120)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (110, N'operation', 161204, N'删除建议书', 0, 1, 16120)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (111, N'operation', 16210, N'合同模板', 0, 0, -1)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (112, N'operation', 162101, N'导出合同模板', 0, 1, 16210)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (113, N'operation', 162102, N'添加合同模板', 0, 1, 16210)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (114, N'operation', 162103, N'编辑合同模板', 0, 1, 16210)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (115, N'operation', 162104, N'删除合同模板', 0, 1, 16210)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (116, N'operation', 16220, N'合同一览', 0, 0, -1)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (117, N'operation', 162201, N'导出合同', 0, 1, 16220)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (118, N'operation', 162202, N'添加合同', 0, 1, 16220)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (119, N'operation', 162203, N'编辑合同', 0, 1, 16220)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (120, N'operation', 162204, N'删除合同', 0, 1, 16220)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (121, N'operation', 2110, N'联系人基本信息', 0, 0, -1)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (122, N'operation', 21101, N'添加联系人', 0, 1, 2110)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (123, N'operation', 21102, N'编辑联系人', 0, 1, 2110)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (124, N'operation', 21103, N'删除联系人', 0, 1, 2110)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (125, N'operation', 3110, N'营销活动基本信息', 0, 0, -1)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (126, N'operation', 31101, N'添加营销活动', 0, 1, 3110)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (127, N'operation', 31102, N'编辑营销活动', 0, 1, 3110)
INSERT [dbo].[Tree] ([treeID], [flag], [id], [treeName], [isExpanded], [isLeaf], [fatherID]) VALUES (128, N'operation', 31103, N'删除营销活动', 0, 1, 3110)
SET IDENTITY_INSERT [dbo].[Tree] OFF
SET IDENTITY_INSERT [dbo].[UserInfo] ON 

INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (1, 0, N'123', N'aaa', N'000000', N'系统管理员', N'中国联通合肥分公司', 1, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAE370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (2, 1, N'123', N'crm', N'000000', N'超级管理员', N'中国联通合肥分公司', 1, 0, N'程序员', N'', N'service@ustcsoft.com', N'18715510000', N'', N'??', CAST(0x7E360B00 AS Date), N'', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (3, 2, N'1111', N'zlzhu', N'000000', N'朱张良', N'中国联通合肥分公司', 2, 0, N'', N'001000020001', N'fdsaf@121.bx', N'13333333333', N'', N'000300020001', CAST(0xF3380B00 AS Date), N'', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (4, 2, N'11333', N'test2', N'000000', N'test', N'中国联通合肥分公司', 2, 0, N'', NULL, N'12fg@fg.hgh', N'13333333333', N'', NULL, NULL, N'', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (5, 4, N'030046', N'grhuang', N'000000', N'黄根榮', N'中国联通合肥分公司', 3, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (6, 6, N'040018', N'hjliang', N'000000', N'梁海進', N'中国联通合肥分公司', 2, 2, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (7, 6, N'050003', N'brliu', N'000000', N'劉必榮', N'中国联通合肥分公司', 2, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (8, 4, N'050040', N'rongxiaohua', N'000000', N'栄小華', N'中国联通合肥分公司', 2, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (9, 4, N'050046', N'dhhuang', N'000000', N'黄定海', N'中国联通合肥分公司', 4, 4, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (10, 5, N'060035', N'liuxiao', N'000000', N'劉霄', N'中国联通合肥分公司', 4, 3, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAA370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (11, 6, N'060049', N'mshen', N'000000', N'沈敏', N'中国联通合肥分公司', 2, 3, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (12, 3, N'070026', N'xudan', N'000000', N'徐丹', N'中国联通合肥分公司', 4, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAE370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (13, 3, N'070029', N'wenzhenhua', N'000000', N'温振華', N'中国联通合肥分公司', 4, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAE370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (14, 3, N'080019', N'yangfeilong', N'000000', N'楊飛龍', N'中国联通合肥分公司', 4, 1, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAE370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (15, 3, N'090116', N'huyeming', N'000000', N'胡業明', N'中国联通合肥分公司', 3, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAE370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (16, 3, N'100196', N'zhangxu', N'000000', N'張許', N'中国联通合肥分公司', 3, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAE370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (17, 3, N'100207', N'zangyongxiang', N'000000', N'臧永祥', N'中国联通合肥分公司', 4, 3, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (18, 3, N'100208', N'xuxihu', N'000000', N'許錫虎', N'中国联通合肥分公司', 3, 0, N'程序员', N'', N'service@ustcsoft.com', N'15156498712', N'', NULL, CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (19, 5, N'100260', N'gaowei', N'000000', N'高偉', N'中国联通合肥分公司', 3, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (20, 3, N'110002', N'buhuimin', N'000000', N'卜慧敏', N'中国联通合肥分公司', 3, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAE370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (21, 3, N'110008', N'wanglongzeng', N'000000', N'汪龍増', N'中国联通合肥分公司', 3, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAE370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (22, 3, N'110010', N'xiafu', N'000000', N'夏富', N'中国联通合肥分公司', 3, 5, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAE370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (23, 3, N'110029', N'wangjun', N'000000', N'王軍', N'中国联通合肥分公司', 3, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAE370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (24, 3, N'110034', N'xubingshan', N'000000', N'徐炳山', N'中国联通合肥分公司', 4, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAE370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (25, 3, N'110035', N'shandianhui', N'000000', N'閃電輝', N'中国联通合肥分公司', 3, 3, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (26, 3, N'110038', N'xiekai', N'000000', N'謝凱', N'中国联通合肥分公司', 3, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAE370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (27, 3, N'110039', N'yebangqiang', N'000000', N'叶邦強', N'中国联通合肥分公司', 4, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAE370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (28, 3, N'110170', N'zhuyanwei', N'000000', N'朱艶偉', N'中国联通合肥分公司', 4, 3, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (29, 3, N'110298', N'wangrui', N'000000', N'王瑞', N'中国联通合肥分公司', 3, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAE370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (30, 3, N'110340', N'chenshouliang', N'000000', N'陳守亮', N'中国联通合肥分公司', 4, 3, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (31, 3, N'110400', N'lilinwei', N'000000', N'李林偉', N'中国联通合肥分公司', 4, 4, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (32, 3, N'110436', N'hutao', N'000000', N'胡涛', N'中国联通合肥分公司', 5, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAE370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (33, 3, N'110460', N'xuzhen', N'000000', N'徐珍', N'中国联通合肥分公司', 4, 1, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (34, 3, N'110464', N'tangyunpeng', N'000000', N'湯運鵬', N'中国联通合肥分公司', 4, 1, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (35, 3, N'110471', N'yangfan', N'000000', N'楊帆', N'中国联通合肥分公司', 4, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAE370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (36, 3, N'120006', N'zhangkai', N'000000', N'张凯', N'中国联通合肥分公司', 4, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAE370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (37, 3, N'120126', N'liuhao', N'000000', N'刘昊', N'中国联通合肥分公司', 4, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAE370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (38, 3, N'120145', N'zhangsiwen', N'000000', N'張思文', N'中国联通合肥分公司', 3, 0, N'程序员', N'', N'service@ustcsoft.com', N'15533333333', N'', NULL, CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (39, 3, N'120162', N'wangyuqing', N'000000', N'王玉青', N'中国联通合肥分公司', 4, 3, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (40, 3, N'120194', N'zengjin', N'000000', N'曾金', N'中国联通合肥分公司', 3, 0, N'程序员', N'', N'service@ustcsoft.com', N'15533333333', N'', NULL, CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (41, 3, N'120196', N'xujueyin', N'000000', N'許決銀', N'中国联通合肥分公司', 3, 0, N'程序员', N'', N'service@ustcsoft.com', N'15533333333', N'', NULL, CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (42, 3, N'120212', N'yuguo', N'000000', N'余果', N'中国联通合肥分公司', 3, 3, N'程序员', N'', N'service@ustcsoft.com', N'15533333333', N'', NULL, CAST(0xAA370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (43, 3, N'120213', N'lilongbao', N'000000', N'李龙宝', N'中国联通合肥分公司', 5, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAE370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (44, 3, N'120224', N'liangchengcheng', N'000000', N'梁程程', N'中国联通合肥分公司', 4, 2, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (45, 3, N'120282', N'dingli', N'000000', N'丁力', N'中国联通合肥分公司', 3, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAE370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (46, 3, N'120296', N'hecheng', N'000000', N'賀誠', N'中国联通合肥分公司', 3, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAE370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (47, 3, N'120439', N'qigan', N'000000', N'斉幹', N'中国联通合肥分公司', 3, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAE370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (48, 3, N'130021', N'wangzhanxu', N'000000', N'王占旭', N'中国联通合肥分公司', 4, 4, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (49, 3, N'130034', N'zhaokunkun', N'000000', N'赵坤坤', N'中国联通合肥分公司', 4, 2, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (50, 3, N'130038', N'shiben', N'000000', N'时奔', N'中国联通合肥分公司', 5, 4, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (51, 3, N'130040', N'maxue', N'000000', N'马雪', N'中国联通合肥分公司', 5, 1, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (52, 3, N'130043', N'zhangchuanhong', N'000000', N'张传宏', N'中国联通合肥分公司', 4, 4, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (53, 3, N'130059', N'jiaxu', N'000000', N'贾旭', N'中国联通合肥分公司', 5, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAE370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (54, 3, N'130152', N'chenxianmei', N'000000', N'陈先美', N'中国联通合肥分公司', 4, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAE370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (55, 3, N'130198', N'wanggang', N'000000', N'汪刚', N'中国联通合肥分公司', 4, 4, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (56, 3, N'140129', N'lixinyue', N'000000', N'李心悦', N'中国联通合肥分公司', 3, 5, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (57, 3, N'140136', N'zhangming', N'000000', N'张明', N'中国联通合肥分公司', 3, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAE370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (58, 3, N'140138', N'jiangyiwei', N'000000', N'江毅玮', N'中国联通合肥分公司', 3, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAE370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (59, 3, N'140140', N'zhangchuan', N'000000', N'张闯', N'中国联通合肥分公司', 3, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAE370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (60, 3, N'140144', N'tangerwei', N'000000', N'唐二伟', N'中国联通合肥分公司', 3, 3, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (61, 3, N'140298', N'oujiping', N'000000', N'欧继平', N'中国联通合肥分公司', 3, 5, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (62, 3, N'149994', N'dongbin', N'000000', N'董彬', N'中国联通合肥分公司', 3, 0, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAE370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (63, 3, N'140467', N'zhangweigen', N'000000', N'张为艮', N'中国联通合肥分公司', 3, 5, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (64, 3, N'140466', N'duzhun', N'000000', N'杜准', N'中国联通合肥分公司', 3, 3, N'程序员', N'', N'service@ustcsoft.com', N'', N'', N'??', CAST(0xAC370B00 AS Date), N'无', 0)
INSERT [dbo].[UserInfo] ([userID], [groupID], [jobID], [userName], [password], [realName], [company], [departmentID], [projectTeamID], [job], [jobTitle], [email], [mobile], [officePhone], [education], [entryTime], [descriptions], [isAbolished]) VALUES (65, 4, N'064555', N'jwang', N'000000', N'汪军', N'中国联通合肥分公司', 5, 0, N'', NULL, N'jwang@ustcsoft.com', N'14323232322', N'', NULL, NULL, N'', 0)
SET IDENTITY_INSERT [dbo].[UserInfo] OFF
SET IDENTITY_INSERT [dbo].[WorkInfo] ON 

INSERT [dbo].[WorkInfo] ([workID], [userID], [customerID], [teamFlag], [theme], [assignee], [priority], [workType], [completion], [startTime], [endTime], [workDetail], [descriptions], [isReaded], [isMailInformed], [isAbolished]) VALUES (1, 2, 0, 0, N'test', NULL, N'001100040001', N'001100030001', N'000200050002', CAST(0x0000A3A400E9E044 AS DateTime), CAST(0x0000A3AF00E9F55C AS DateTime), N'gsdfgsdfgfsdgsdfg', N'gsdf', 0, 0, 0)
INSERT [dbo].[WorkInfo] ([workID], [userID], [customerID], [teamFlag], [theme], [assignee], [priority], [workType], [completion], [startTime], [endTime], [workDetail], [descriptions], [isReaded], [isMailInformed], [isAbolished]) VALUES (2, 2, 0, 0, N'gdsfgfdsg', NULL, N'001100040002', N'001100030001', N'000200050002', CAST(0x0000A3A400EA162C AS DateTime), NULL, N'sfgfdsgfds', N'', 0, 0, 0)
INSERT [dbo].[WorkInfo] ([workID], [userID], [customerID], [teamFlag], [theme], [assignee], [priority], [workType], [completion], [startTime], [endTime], [workDetail], [descriptions], [isReaded], [isMailInformed], [isAbolished]) VALUES (3, 2, 0, 0, N'test', NULL, N'001100040002', N'001100030001', N'000200050002', CAST(0x0000A3A400EA4638 AS DateTime), NULL, N'testbxcvbvxcb', N'', 0, 0, 0)
INSERT [dbo].[WorkInfo] ([workID], [userID], [customerID], [teamFlag], [theme], [assignee], [priority], [workType], [completion], [startTime], [endTime], [workDetail], [descriptions], [isReaded], [isMailInformed], [isAbolished]) VALUES (4, 2, 0, 0, N'test', NULL, N'001100040002', N'001100030001', N'000200050001', CAST(0x0000A3A400EB0794 AS DateTime), CAST(0x0000A3B200EB134C AS DateTime), N'sfg54', N'', 0, 0, 0)
INSERT [dbo].[WorkInfo] ([workID], [userID], [customerID], [teamFlag], [theme], [assignee], [priority], [workType], [completion], [startTime], [endTime], [workDetail], [descriptions], [isReaded], [isMailInformed], [isAbolished]) VALUES (5, 2, 0, 0, N'zxcv', NULL, N'001100040002', N'001100030007', N'000200050001', CAST(0x0000A3A400EC0964 AS DateTime), NULL, N'', N'', 0, 0, 0)
INSERT [dbo].[WorkInfo] ([workID], [userID], [customerID], [teamFlag], [theme], [assignee], [priority], [workType], [completion], [startTime], [endTime], [workDetail], [descriptions], [isReaded], [isMailInformed], [isAbolished]) VALUES (6, 3, 1, 0, N'11', NULL, N'001100040001', N'001100030001', N'000200050001', CAST(0x0000A3A400EF67E4 AS DateTime), NULL, N'', N'', 1, 0, 0)
INSERT [dbo].[WorkInfo] ([workID], [userID], [customerID], [teamFlag], [theme], [assignee], [priority], [workType], [completion], [startTime], [endTime], [workDetail], [descriptions], [isReaded], [isMailInformed], [isAbolished]) VALUES (7, 0, 0, 0, N'22', NULL, N'001100040001', N'001100030001', N'000200050001', CAST(0x0000A3A400EF75F4 AS DateTime), NULL, N'', N'', 0, 0, 0)
INSERT [dbo].[WorkInfo] ([workID], [userID], [customerID], [teamFlag], [theme], [assignee], [priority], [workType], [completion], [startTime], [endTime], [workDetail], [descriptions], [isReaded], [isMailInformed], [isAbolished]) VALUES (8, 3, 0, 0, N'test', NULL, N'001100040001', N'001100030002', N'000200050003', CAST(0x0000A40A00E51D84 AS DateTime), NULL, N'', N'', 0, 0, 0)
SET IDENTITY_INSERT [dbo].[WorkInfo] OFF
