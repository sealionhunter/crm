package com.ustcsoft.gs.crm.webui.activity.constant;

public class ActivityConstant {

    public static final String CUSTOMER_ID = "customerID";
    public static final String YUAN = "万元";
    public static final String ISABOLISHED = "isAbolished";
    public static final String DELETED_CUSTOMER = "已删除客户";

    public static final String SELECT_CUS_CUSTOMER_NAME = "select cus.customerName from CustomerDto as cus "
            + "where cus.isAbolished = 0 and cus.customerID =";

    public static final String SELECTCOUNT = "select count(*)";

    public static final String GET_ALLACTIVITY = "select new com.ustcsoft.gs.crm.webui.activity.bean.ActivityBean(act, "
            + "(select com.value from CodeDto as com where com.code = act.activityType)as activityTypeName, "
            + "(select com.value from CodeDto as com where com.code = act.activityPeriod)as activityPeriodName, "
            + "(select com.value from CodeDto as com where com.code = act.activityEmphasis)as activityEmphasisName, "
            + "(select com.value from CodeDto as com where com.code = act.activityRange)as activityRangeName, "
            + "(select com.value from CodeDto as com where com.code =act.activityState)as activityStateName) ";
    public static final String GET_ALLACTIVITYCOUNT_HQL = "select count(*) from ActivityInfoDto as act where act.isAbolished = :isAbolished";

    public static final String GET_ALLACTIVITY_HQL = "from ActivityInfoDto as act where act.isAbolished=0";

    public static final String SIMPLE_SEARCH_HQL = "from ActivityInfoDto as act where act.isAbolished = 0 "
            + "and ((select com.value from CodeDto as com where com.code = act.activityType)like :searchText "
            + "or(select com.value from CodeDto as com where com.code = act.activityRange)like :searchText "
            + "or (select com.value from CodeDto as com where com.code = act.activityState)like :searchText "
            + "or act.activityName like :searchText "
            + "or act.activityPlace like :searchText or act.activityStartTime like :searchText "
            + "or act.activityEndTime like :searchText or act.activityMember like :searchText)";

    public static final String SEARCH = " and act.activityName like :activityName "
            + " and act.activityPlace like :activityPlace ";

    public static final String STARTTIME_SEARCH = " and act.activityStartTime >= :activityStartTime ";

    public static final String ENDTIME_SEARCH = " and act.activityEndTime <= :activityEndTime ";

    public static final String TYPELIST_SEARCH = " and act.activityType in (:activityType) ";

    public static final String RANGELIST_SEARCH = " and act.activityRange in (:activityRange) ";

    public static final String STATELIST_SEARCH = " and act.activityState in (:activityState) ";

    public static final String DELETE_ACTIVITY = "update ActivityInfoDto as act set act.isAbolished = 1 where act.activityID in";
}
