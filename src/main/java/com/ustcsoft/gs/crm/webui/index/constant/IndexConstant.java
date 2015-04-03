package com.ustcsoft.gs.crm.webui.index.constant;

public class IndexConstant {

    public static final String ZERO_ONE = "01";
    public static final String ZERO_TWO = "02";
    public static final String ZERO_THREE = "03";
    public static final String TEAMFLAG = "teamFlag";
    public static final String FLAG = "flag";

    public static final String WORK_THEME = "theme";
    public static final String WORK_CUSTOMERID = "customerID";
    public static final String WORK_COMPLETION = "completion";
    public static final String WORK_PRIORITY = "priority";
    public static final String WORK_WORKTYPE = "workType";
    public static final String WORK_STARTTIME = "startTime";
    public static final String WORK_ENDTIME = "endTime";
    public static final String WORK_WORKDETAIL = "workDetail";
    public static final String WORK_DESCRIPTIONS = "descriptions";
    public static final String WORK_ASSIGNEES = "assignees";

    public static final String WORK_THEME_INVALID = "workTheme.invalid";
    public static final String WORK_THEME_LENGTH_INVALID = "workThemeLength.invalid";
    public static final String WORK_COMPLETION_INVALID = "workCompletion.invalid";
    public static final String WORK_PRIORITY_INVALID = "workPriority.invalid";
    public static final String WORK_WORKTYPE_INVALID = "workWorkType.invalid";
    public static final String WORK_STARTTIME_INVALID = "workStartTime.invalid";
    public static final String WORK_STARTTIME_MATCH_INVALID = "workStartTimeMatch.invalid";
    public static final String WORK_ENDTIME_MATCH_INVALID = "workEndTimeMatch.invalid";
    public static final String WORK_WORKDETAIL_LENGTH_INVALID = "workWorkDetailLength.invalid";
    public static final String WORK_DESCRIPTIONS_LENGTH_INVALID = "workDescriptionsLength.invalid";
    public static final String WORK_ASSIGNEES_INVALID = "workAssignees.invalid";
    /** the millis of one day */
    public static long MILLIS_OF_ONE_DAY = 24 * 60 * 60 * 1000;
    public static int MAX_DAYS_TO_NOTIIFY = 90;

    public static final String WORK_GETALL_HQL = "select new Map("
            + " WD.workID as workID,"
            + " WD.customerID as customerID,"
            + " WD.teamFlag as teamFlag,"
            + " WD.priority as priority,"
            + " WD.workType as workType,"
            + " WD.completion as completion,"
            + " WD.assignee as assignee,"
            + " (select cd.customerName from CustomerDto as cd where WD.customerID = cd.customerID and cd.isAbolished = 0) as customerName,"
            + " (select code.value from CodeDto as code where WD.priority = code.code) as priorityName,"
            + " (select code.value from CodeDto as code where WD.workType = code.code) as workTypeName,"
            + " (select code.value from CodeDto as code where WD.completion = code.code) as completionName,"
            + " WD.theme as theme,"
            + " WD.startTime as startTime,"
            + " WD.endTime as endTime,"
            + " WD.workDetail as workDetail,"
            + " WD.descriptions as descriptions,"
            + " WD.isMailInformed as isMailInformed)"
            + " from WorkDto as WD where WD.isAbolished = 0"
            + " and WD.userID = :userID "
            + " and (WD.endTime >= CONVERT(varchar(10),GETDATE(),120)+' 00:00' or WD.endTime is null)"
            + " and WD.startTime <= CONVERT(varchar(10),DATEADD(DAY,1,GETDATE()),120)+' 00:00'";

    public static final String WORK_GETALL_COUNT_HQL = "select count(*) from WorkDto as WD where WD.isAbolished = 0"
            + " and WD.userID = :userID "
            + " and (WD.endTime >= CONVERT(varchar(10),GETDATE(),120)+' 00:00'  or WD.endTime is null)"
            + " and WD.startTime <= CONVERT(varchar(10),DATEADD(DAY,1,GETDATE()),120)+' 00:00' ";

    /** get all work messages by date */
    public static final String WORK_GETALL_BY_DATE_HQL = "select new Map("
            + " WD.workID as workID,"
            + " WD.customerID as customerID,"
            + " WD.teamFlag as teamFlag,"
            + " WD.priority as priority,"
            + " WD.workType as workType,"
            + " WD.completion as completion,"
            + " WD.assignee as assignee,"
            + " (select cd.customerName from CustomerDto as cd where WD.customerID = cd.customerID) as customerName,"
            + " (select code.value from CodeDto as code where WD.priority = code.code) as priorityName,"
            + " (select code.value from CodeDto as code where WD.workType = code.code) as workTypeName,"
            + " (select code.value from CodeDto as code where WD.completion = code.code) as completionName,"
            + " WD.theme as theme," + " WD.startTime as startTime," + " WD.endTime as endTime,"
            + " WD.workDetail as workDetail," + " WD.descriptions as descriptions,"
            + " WD.isMailInformed as isMailInformed)"
            + " from WorkDto as WD where WD.isAbolished = 0 " + " and WD.userID = :userID "
            + " and (WD.endTime >= CONVERT(varchar(10),:date,120)+' 00:00'  or WD.endTime is null)"
            + " and WD.startTime <= CONVERT(varchar(10),DATEADD(DAY,1,:date),120)+' 00:00' ";

    public static final String WORK_GETALL_BY_DATE_COUNT_HQL = "select count(*) from WorkDto as WD where WD.isAbolished = 0"
            + " and WD.userID = :userID "
            + " and (WD.endTime >= CONVERT(varchar(10),:startTime,120)+' 00:00'  or WD.endTime is null)"
            + " and WD.startTime <= CONVERT(varchar(10),DATEADD(DAY,1,:startTime),120)+' 00:00'";

    /** IMPORTANT_TASK_HQL */
    public static final String WORK_TASK_HQL_MAP = "select new Map("
            + " WD.workID as workID,"
            + " WD.customerID as customerID,"
            + " WD.teamFlag as teamFlag,"
            + " WD.priority as priority,"
            + " WD.workType as workType,"
            + " WD.completion as completion,"
            + " WD.assignee as assignee,"
            + " (select cd.customerName from CustomerDto as cd where WD.customerID = cd.customerID) as customerName,"
            + " (select code.value from CodeDto as code where WD.priority = code.code) as priorityName,"
            + " (select code.value from CodeDto as code where WD.workType = code.code) as workTypeName,"
            + " (select code.value from CodeDto as code where WD.completion = code.code) as completionName,"
            + " WD.theme as theme," + " WD.startTime as startTime," + " WD.endTime as endTime,"
            + " WD.workDetail as workDetail," + " WD.descriptions as descriptions,"
            + " WD.isMailInformed as isMailInformed) ";
    public static final String CONTACT_HQL_MAP2 = "select new Map("
            + " WD.workID as workID,"
            + " WD.customerID as customerID,"
            + " WD.teamFlag as teamFlag,"
            + " WD.priority as priority,"
            + " WD.workType as workType,"
            + " WD.completion as completion,"
            + " WD.assignee as assignee,"
            + " (select cd.customerName from CustomerDto as cd where WD.customerID = cd.customerID) as customerName,"
            + " (select code.value from CodeDto as code where WD.priority = code.code) as priorityName,"
            + " (select code.value from CodeDto as code where WD.workType = code.code) as workTypeName,"
            + " (select code.value from CodeDto as code where WD.completion = code.code) as completionName,"
            + " WD.theme as theme," + " WD.startTime as startTime," + " WD.endTime as endTime,"
            + " WD.workDetail as workDetail," + " WD.descriptions as descriptions,"
            + " WD.isMailInformed as isMailInformed) ";
    public static final String WORK_IMPORTANT_TASK_HQL = "from WorkDto as WD where WD.isAbolished = 0 "
            + "and WD.priority = '001100040001' and WD.userID = :userID and WD.teamFlag = 0 "
            + "and (WD.endTime >= CONVERT(varchar(10),:startTime,120)+' 00:00'  or WD.endTime is null)"
            + "and WD.startTime <= DATEADD(DAY,1,:startTime) "
            + "order by DATEDIFF(MINUTE,GETDATE(),WD.startTime)";

    /** INFORM_TASK_HQL */
    public static final String WORK_INFORM_TASK_HQL = "from WorkDto as WD where WD.isAbolished = 0 "
            + "and WD.userID = :userID and WD.teamFlag > 0 "
            + "and (WD.endTime >= CONVERT(varchar(10),:startTime,120)+' 00:00'  or WD.endTime is null)"
            + "and WD.startTime <= DATEADD(DAY,1,:startTime) "
            + "order by WD.priority, DATEDIFF(MINUTE,GETDATE(),WD.startTime)";

    /** INFORM_TASK_HQL */
    public static final String WORK_INFORM_TASK_HQL2 = "from WorkDto as WD where WD.isAbolished = 0 "
            + "and WD.userID = :userID and WD.teamFlag > 0 "
            + "and (WD.endTime >= CONVERT(varchar(10),:startTime,120)+' 00:00'  or WD.endTime is null)"
            + "and WD.startTime <= DATEADD(DAY,1,:startTime) "
            + "order by WD.priority, DATEDIFF(MINUTE,GETDATE(),WD.startTime)";

    public static final String WORK_INFORM_MESSAGE_HQL = "from WorkDto as WD where WD.isReaded = 0 "
            + "and WD.userID = :userID and WD.teamFlag > 0 "
            + "order by WD.priority, WD.endTime Desc";
    public static final String WORK_INFORM_MESSAGE_HQL_READED = "from WorkDto as WD where WD.isAbolished = 0 and WD.isReaded = 1 "
            + "and WD.userID = :userID and WD.teamFlag > 0 "
            + "and (WD.endTime >= CONVERT(varchar(10),:startTime,120)+' 00:00' or WD.endTime is null )"
            // + "and WD.startTime <= DATEADD(DAY,1,:startTime) "
            + "order by WD.priority, WD.endTime Desc";

    public static final String WORK_INFORM_MESSAGETOTAL_HQL = "select count(*) from WorkDto as WD where WD.isReaded = 0 "
            + " and WD.userID = :userID and WD.teamFlag > 0 ";
    public static final String WORK_INFORM_MESSAGETOTAL_HQL_READED = "select count(*) from WorkDto as WD where WD.isReaded = 1 "
            + " and WD.userID = :userID and WD.teamFlag > 0 ";
    public static final String READMESSAGE = "update WorkDto as WD set WD.isReaded = 'true' where WD.workID = ";

    public static final String WORK_SIMPLE_HQL = " and ((select cd.customerName from CustomerDto as cd where WD.customerID = cd.customerID)like :customerName "
            + " or WD.theme like :theme "
            + " or convert(varchar(20),WD.startTime,120) like :startTime "
            + " or convert(varchar(20),WD.endTime,120) like :endTime "
            + " or WD.priority in (select code.code from CodeDto as code where code.value like :priority)"
            + " or WD.workType in (select code.code from CodeDto as code where code.value like :workType)"
            + " or WD.completion in (select code.code from CodeDto as code where code.value like :completion)"
            + ")";

    public static final String WORK_CUSTOMER_SEARCH = " and (select cd.customerName from CustomerDto as cd where WD.customerID = cd.customerID) like :customerName ";
    public static final String WORK_THEME_SEARCH = " and WD.theme like :theme ";
    public static final String WORK_JOBSTYLE_MYWORK_SEARCH = " and WD.teamFlag = 0 ";
    public static final String WORK_JOBSTYLE_TEAMLEADER_SEARCH = " and WD.teamFlag = -1 ";
    public static final String WORK_JOBSTYLE_TEAM_SEARCH = " and WD.teamFlag > 0 ";
    public static final String WORK_PRIORITY_SEARCH = " and WD.priority in (:priority)";
    public static final String WORK_COMPLETION_SEARCH = " and WD.completion in (:completion)";
    public static final String WORK_WORKTYPE_SEARCH = " and WD.workType in (:workType)";
    public static final String WORK_DATESTART_SEARCH = " and WD.endTime >= :searchDateStart ";
    public static final String WORK_DATEEND_SEARCH = " and WD.endTime < DATEADD( day , 1, :searchDateEnd ) ";

    public static final String[] PARAMNAMES = { "userID", "startTime" };

    public static final String GET_MAILINFO = "select new Map(WI.workID as workID, (select CI.customerName from CustomerDto as CI where CI.customerID = WI.customerID and CI.isAbolished = 'false') as customerName,"
            + "(select UI.realName from UserInfoDto as UI where UI.userID = WI.userID and UI.isAbolished = 'false') as realName, WI.theme as theme, WI.workDetail as workDetail, WI.descriptions as descriptions,"
            + "(select UI.email from UserInfoDto as UI where UI.userID = WI.userID and UI.isAbolished = 'false') as email,"
            + "WI.startTime as startTime, WI.endTime as endTime, (select CB.value from CodeDto as CB where WI.completion = CB.code) as completionName,"
            + "(select code.value from CodeDto as code where WD.workType = code.code) as workTypeName,"
            + "(select code.value from CodeDto as code where WD.priority = code.code) as priorityName) "
            + "from WorkDto as WI "
            + "where WI.isMailInformed = 'true' "
            + "and WI.isAbolished = 'false' " + "and WI.teamFlag = :teamFlag";
    public static final String DELETE_TEAMWORK_ME = "update WorkDto as WD set WD.isAbolished = 'true' where WD.workID in ";
    public static final String DELETE_TEAMWORK_OTHER = "update WorkDto as WD set WD.isAbolished = 'true' where WD.teamFlag in ";
    public static final String EDIT_TEAMWORK = "update WorkDto as WD set WD.isAbolished = 'true' where WD.teamFlag = ? ";
    public static final String GET_CUSTOMER_UPDATED_STATUS = "select new com.ustcsoft.gs.crm.webui.index.bean.CustomerUpdatedStatusBean("
            + " cus.customerName, cus.updateTime) "
            + " from CustomerDto cus"
            + " where cus.holder = :userID"
            + " and cus.isAbolished = 0"
            + " and cus.updateTime <= CONVERT(varchar(19), getdate() - 70, 120)"
            + " and cus.updateTime >= CONVERT(varchar(19), getdate() - 90, 120)";
}
