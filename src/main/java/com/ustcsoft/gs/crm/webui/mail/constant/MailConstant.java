package com.ustcsoft.gs.crm.webui.mail.constant;

public class MailConstant {

    public static final String CUSTOMERNAME = "客户：";

    public static final String COMPLETIONNAME = "完成情况：";

    public static final String PRIOIRTYNAME = "优先级：";

    public static final String WORKTYPENAME = "任务类型：";

    public static final String STARTTIME = "开始时间：";

    public static final String ENDTIME = "结束时间：";

    public static final String WORKDETAIL = "任务内容：";

    public static final String DESCRIPTIONS = "备注：";

    public static final String ENTER = "\r\n";

    public static final String MAILCONTENT = "工作任务：";

    public static final String LEFT_BRACKET = "(";

    public static final String RIGHT_BRACKET = ")";

    public static final String GET_MAILINFO = "select WI.workID, (select CI.customerName from CustomerInfo as CI where CI.customerID = WI.customerID and CI.isAbolished = 'false') as customerName,"
            + "(select UI.realName from UserInfo as UI where UI.userID = WI.userID and UI.isAbolished = 'false') as realName, WI.theme, WI.workDetail, WI.descriptions,"
            + "(select UI.email from UserInfo as UI where UI.userID = WI.userID and UI.isAbolished = 'false') as email,"
            + "WI.startTime, WI.endTime, (select CB.value from ComboBox as CB where CB.category = 'workCompletion' and WI.completion = CB.name) as completionName,"
            + "(select CB.value from ComboBox as CB where CB.category = 'workType' and WI.workType = CB.name) as workTypeName,"
            + "(select CB.value from ComboBox as CB where CB.category = 'workPriority' and WI.priority = CB.name) as priorityName "
            + "from WorkInfo as WI "
            + "where WI.isMailInformed = 'true' "
            + "and WI.isAbolished = 'false' "
            + "and WI.startTime >= CONVERT(varchar(10),?,120)+' 00:00' "
            + "and WI.startTime <= DATEADD(DAY,1,?)";

    public static final String GET_MAILINFO_UNIT = "select WI.workID, (select CI.customerName from CustomerInfo as CI where CI.customerID = WI.customerID and CI.isAbolished = 'false') as customerName,"
            + "(select UI.realName from UserInfo as UI where UI.userID = WI.userID and UI.isAbolished = 'false') as realName, WI.theme, WI.workDetail, WI.descriptions,"
            + "(select UI.email from UserInfo as UI where UI.userID = WI.userID and UI.isAbolished = 'false') as email,"
            + "WI.startTime, WI.endTime, (select CB.value from ComboBox as CB where CB.category = 'workCompletion' and WI.completion = CB.name) as completionName,"
            + "(select CB.value from ComboBox as CB where CB.category = 'workType' and WI.workType = CB.name) as workTypeName,"
            + "(select CB.value from ComboBox as CB where CB.category = 'workPriority' and WI.priority = CB.name) as priorityName "
            + "from WorkInfo as WI "
            + "where WI.isMailInformed = 'true' "
            + "and WI.isAbolished = 'false' " + "and WI.teamFlag = ?";

    public static final String SETISMAILED = "update WorkInfo set isMailInformed = 'false' where workID in ";
}
