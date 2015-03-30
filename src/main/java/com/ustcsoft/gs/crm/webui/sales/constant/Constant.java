/*
 * Constant.java
 */
package com.ustcsoft.gs.crm.webui.sales.constant;

/**
 * 
 * @author chenguangzhou and shiben
 * 
 */
public class Constant {
    /** 获取所有销售事件 */
    public static final String GET_ALL_EVENTS = "select distinct new com.ustcsoft.gs.crm.webui.sales.bean.SalesEventBean(sales.eventID as eventID,"
            + "sales.eventName as eventName,sales.customerID as customerID,cus.customerName as customerName,"
            + "sales.contactID as contactID, cont.contactName as contactName,user.realName as submitPersonName,"
            + "sales.status as status,sfcode.value as statusName,sales.submitDate as submitDate,sales.remarks as remarks,sales.isAbolished as isAbolished,sfcode.sort as sort)"
            + " from SalesEventDto as sales,CustomerDto as cus,ContactInfoDto as cont,UserInfoDto as user,SalesEventFlowCodeDto as sfcode"
            + " where sales.customerID=cus.customerID and sales.submitterID=user.userID and sales.status=sfcode.code and sales.contactID=cont.contactID and sales.submitterID in (:userID)";
    /** 销售事件数据总数 */
    public static final String COUNT_OF_EVENT = "select count(*) from SalesEventDto as sales where sales.submitterID in (:userID)";
    /** 销售事件模糊查找 */
    public static final String SALES_EVENT_QUERY = "select distinct new com.ustcsoft.gs.crm.webui.sales.bean.SalesEventBean(sales.eventID as eventID,"
            + "sales.eventName as eventName,sales.customerID as customerID,cus.customerName as customerName,"
            + "sales.contactID as contactID,cont.contactName as contactName,user.realName as submitPersonName,"
            + "sales.status as status,sfcode.value as statusName,sales.submitDate as submitDate,sales.remarks as remarks,sales.isAbolished as isAbolished,sfcode.sort as sort)"
            + " from SalesEventDto as sales ,CustomerDto as cus,ContactInfoDto as cont,UserInfoDto as user,SalesEventFlowCodeDto as sfcode"
            + " where sales.customerID=cus.customerID and sales.submitterID=user.userID and sales.contactID=cont.contactID"
            + " and sales.status = sfcode.code and sales.submitterID in (:userID) and (sales.eventName like :searchKey"
            + " or cus.customerName like :searchKey or user.realName like :searchKey or sales.submitDate like :searchKey or sfcode.value like :searchKey or cont.contactName like :searchKey)";
    /** 销售事件模糊查找数据总数 */
    public static final String COUNT_OF_QUERY = "select count(*) from SalesEventDto as sales where sales.submitterID in (:userID) and"
            + " (select sfcode.value from SalesEventFlowCodeDto as sfcode where sales.status=sfcode.code) like :searchKey"
            + " or (select cus.customerName from CustomerDto as cus where sales.customerID=cus.customerID) like :searchKey"
            + " or (select cont.contactName from ContactInfoDto as cont where sales.contactID=cont.contactID) like :searchKey"
            + " or (select user.realName from UserInfoDto as user where sales.submitterID=user.userID) like :searchKey"
            + " or sales.eventName like :searchKey or sales.submitDate like :searchKey";
    /** 搜索条件之客户 */
    public static final String CUSTOMER_OF_SUPERQUERY = " and cus.customerName like :customerName";
    public static final String CUSTOMER_COUNT_OF_SUPERQUERY = " and (select cus.customerName from CustomerDto as cus where sales.customerID=cus.customerID) like :customerName";
    /** 搜索条件条件之提交人员 */
    public static final String SUBMIT_OF_SUPERQUERY = " and user.realName like :realName";
    public static final String SUBMIT_COUNT_OF_SUPERQUERY = " and (select user.realName from UserInfoDto as user where sales.submitterID=user.userID) like :realName";
    /** 搜索条件之现处状态 */
    public static final String STATUS_OF_SUPERQUERY = " and sales.status in(:status)";
    public static final String STATUS_COUNT_OF_SUPERQUERY = " and (select sfcode.value from SalesEventFlowCodeDto as sfcode where sales.status=sfcode.code) in(:status)";
    /** 搜索条件之起始日期搜索 */
    public static final String DATEFROM_OF_SUPERQUERY = " and sales.submitDate >=:dateFrom";
    public static final String DATEFROM_COUNT_OF_SUPERQUERY = " and sales.submitDate >=:dateFrom";
    /** 搜索条件之截止日期搜索 */
    public static final String DATETO_OF_SUPERQUERY = " and sales.submitDate <=:dateTo";
    public static final String DATETO_COUNT_OF_SUPERQUERY = " and sales.submitDate <=:dateTo";
    /** 搜索条件 销售事件名称 */
    public static final String EVENT_OF_SUPERQUERY = " and sales.eventName like :eventName";

    /** 销售事件批量删除 */
    public static final String DELETE_SALESEVENTS = " delete SalesEventDto as sales where sales.eventID in";
    public static final String DELETE_SALESEVENTS_DEMAND = "delete SalesEventFlowDto as sf where sf.eventID in";
    public static final String DELETE_SALESEVENTS_TRACK = "delete SalesTrackDto as st where st.eventID in";

    /** 点击添加事件按钮加载tab信息 */
    public static final String GET_DEFUALT_TAB = "select new com.ustcsoft.gs.crm.webui.sales.bean.TabBean("
            + "sfcode.code as status,sfcode.value as title) from SalesEventFlowCodeDto as sfcode where order by sfcode.sort asc";
    public static final String GET_SALES_DEMAND = "from SalesEventFlowDto sf where sf.eventID=?";

    public static final String GET_ALL_SALES_DEMAND = "select new com.ustcsoft.gs.crm.webui.sales.bean.TabBean("
            + "sfcode.code as status,sfcode.value as title,(select sf.demand as demand from SalesEventFlowDto sf where sf.status=sfcode.code and sf.eventID=?),sfcode.sort)"
            + "from SalesEventFlowCodeDto as sfcode order by sfcode.sort asc";
    public static final String UPDATE_SALES_EVENT_FLOW = "update SalesEventFlowDto sf set sf.demand = :demand where sf.eventID = :eventID and sf.status = :status ";

    public static final String JUDGE_SALES_EVENT_NAME = "select count(*) from SalesEventDto as s where s.eventName=:eventName and s.eventID != :eventID";

    public static final String FIND_DEMAND_OF_DURING_OBJECTIVES = "select sf.demand from SalesEventFlowDto sf where"
            + " sf.status=3 and sf.eventID = :eventID";

    public static final String FIND_EVENT_FLOW_BY_DTO = "from SalesEventFlowDto sf where sf.status= :status and sf.eventID = :eventID";
    public static final String UPDATE_DEMAND_OF_DURING_OBJECTIVES = "update SalesEventFlowDto sf set sf.demand = :demand where sf.eventID = :eventID and sf.status = 3";
    public static final String UPDATE_STATUS_OF_DURING_OBJECTIVES = "update SalesEventDto sales set sales.status = '3' where sales.eventID = ?";

    public static final String FIND_FRIST_FLOW_STATUS_IN_ORDER = "select sfcode.code from SalesEventFlowCodeDto as sfcode where sfcode.category = '00040003' and sort = "
            + "(select min(sfcode.sort) from sfcode where sfcode.category = '00040003')";

    public static final String FIND_INTENTION_ORDER = "from OrderDto order where order.eventID = ?";

    public static final String DELETE_CHECK_CONTACT_TRACK_INFO = "select sales.eventName from ContactTrackInfoDto as cti,SalesEventDto as sales where cti.eventID = sales.eventID and cti.eventID in ";
    public static final String DELETE_CHECK_INTENT_ORDER = "select sales.eventName from OrderDto as order,SalesEventDto as sales where order.eventID = sales.eventID and order.type=0 and order.eventID in ";
    public static final String DELETE_CHECK_ORDER = "select sales.eventName from OrderDto as order,SalesEventDto as sales where order.eventID = sales.eventID and order.type=1 and order.eventID in ";
    public static final String DELETE_CHECK_EVENT_NAME_STRING = "select sales.eventName from OrderDto as order,ContactTrackInfoDto as cti,SalesEventDto as sales where cti.eventID = sales.eventID and"
            + " order.eventID = sales.eventID and sales.eventID in ";

    public static final String FIND_STATUS_BY_EVENTID = "select sfcode.value from SalesEventFlowCodeDto as sfcode,SalesEventDto as sales where sales.status = sfcode.code and sales.eventID=?";
}
