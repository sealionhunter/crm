/*
 * SalesConstant.java
 */
package com.ustcsoft.gs.crm.webui.sales.constant;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;

/**
 * 
 * @author shiben
 * 
 */
public class SalesConstant extends CRMConstant {

    /**
     * 查找销售履历信息
     */
    public static final String SALESTRACK_HQL = "select new Map("
            + "(select sfcode.value from SalesEventFlowCodeDto as sfcode where st.status = sfcode.code) as status,"
            + "(select user.realName from UserInfoDto as user where user.userID = st.submitterID) as editPeople,"
            + "(select c.customerName from CustomerDto as c where c.customerID = st.customerID) as customerName,"
            + "st.isAbolished as isAbolished,"
            + "st.submitDate as recordTime,st.salesTrackNo as id)"
            + "from SalesTrackDto as st where st.eventID= :eventID";

    /**
     * 更新销售履历信息
     */
    public static final String UPDATESALESTRACK_HQL_STRING = "update SalesTrackDto st set st.isAbolished = :isAbolished where st.eventID = :eventID and st.status = :status";

    public static final String UPDATESALESTRACK_HQL_DEMAND = " st.demand = :demand,";

    public static final String UPDATESALESTRACK_HQL_CURRENTSTATUS = "st.currentStatus = :currentStatus,";

    public static final String UPDATESALESTRACK_HQL_WHERE = "1=1 where st.eventId = :id";

    public static final String DEMAND = "demand";

    public static final String ISABOLISHED = "isAbolished";

    public static final String EVENTID = "eventID";

    public static final String STATUS = "status";
}
