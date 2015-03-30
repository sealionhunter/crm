package com.ustcsoft.gs.crm.webui.count.constant;

public class SalesCountConstant {

    public static final String STATUS = "sort";

    public static final String MIN_START_DATE = "1970-01-01";

    public static final String[] CONDITION_DATE = { "startDate", "endDate" };

    public static final String[] CONDITION_ALL = { "status", "startDate", "endDate" };
    public static final String HQL_SELECT_SALESEVENTFLOW = " from SalesEventFlowCodeDto as com order by com.sort";

    public static final String HQL_CONDITION_SELECT_STATUS = "from SalesCountDto dto "
            + "where dto.submitDate>=:startDate and dto.submitDate<=:endDate";
    public static final String HQL_CONDITION_SELECT_STATUS_ENDDATE_NULL = "from SalesCountDto dto "
            + "where dto.submitDate>=:startDate";

}
