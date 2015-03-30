/**
 * @author xuzhen
 */
package com.ustcsoft.gs.crm.webui.activity.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.activity.bean.ActivityBean;
import com.ustcsoft.gs.crm.webui.activity.bean.ActivitySearchBean;
import com.ustcsoft.gs.crm.webui.activity.constant.ActivityConstant;
import com.ustcsoft.gs.crm.webui.activity.dao.ActivityInfoDao;
import com.ustcsoft.gs.crm.webui.activity.dto.ActivityInfoDto;
import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.util.PagingHibernateCallback;
import com.ustcsoft.gs.crm.webui.common.util.SuperHibernateCallback;

/**
 * Class ActivityInfoDao used for operating database, return the result to
 * service
 * 
 * @author xuzhen
 * @see ActivityInfoDao
 */
public class ActivityInfoDaoImpl implements ActivityInfoDao {

    /** define one object of the HibernateTemplate */
    private HibernateTemplate hibernateTemplate;
    /** define one object of the Log and is used to write log */
    private final static Log LOG = LogFactory.getLog(ActivityInfoDaoImpl.class);

    /**
     * get all activity
     * 
     * @param currpage
     * @param limit
     * @throws DataAccessException
     * @return map of all activities
     * 
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getAllActivity(int currpage, int limit) throws DataAccessException,
            NumberFormatException {
        LOG.debug("method getAllActivity start.");
        Map<String, Object> map = new HashMap<String, Object>();
        List<ActivityBean> activityList = getHibernateTemplate().executeFind(
                new PagingHibernateCallback(ActivityConstant.GET_ALLACTIVITY
                        + ActivityConstant.GET_ALLACTIVITY_HQL, currpage, limit));
        long total = (Long) getHibernateTemplate().findByNamedParam(
                ActivityConstant.GET_ALLACTIVITYCOUNT_HQL, ActivityConstant.ISABOLISHED, false)
                .get(0);
        map.put(CRMConstant.ITEMS, changeActivityList(activityList));
        map.put(CRMConstant.TOTAL, total);
        LOG.debug("method getAllActivity end.");
        return map;
    }

    /**
     * search activity
     * 
     * @param searchFlag
     * @param activitySearchBean
     * @param currpage
     * @param limit
     * @throws DataAccessException
     * @return map of searched activities
     * 
     */
    @Override
    @SuppressWarnings({ "unchecked" })
    public Map<String, Object> searchActivity(int searchFlag,
            ActivitySearchBean activitySearchBean, int currpage, int limit)
            throws DataAccessException, NumberFormatException {
        LOG.debug("method searchActivity start.");
        Map<String, Object> map = new HashMap<String, Object>();
        long total = 0;
        List<ActivityBean> activityList = null;
        if (searchFlag == 1) {
            activityList = getHibernateTemplate().executeFind(
                    new SuperHibernateCallback(ActivityConstant.GET_ALLACTIVITY
                            + ActivityConstant.SIMPLE_SEARCH_HQL, currpage, activitySearchBean,
                            limit));
            total = (Long) getHibernateTemplate().executeFind(
                    new SuperHibernateCallback(ActivityConstant.SELECTCOUNT
                            + ActivityConstant.SIMPLE_SEARCH_HQL, 0, activitySearchBean, 0)).get(0);
        } else {
            String[] activityType = activitySearchBean.getActivityType();
            String[] activityRange = activitySearchBean.getActivityRange();
            String[] activityState = activitySearchBean.getActivityState();
            String activityStartTime = activitySearchBean.getActivityStartTime();
            String activityEndTime = activitySearchBean.getActivityEndTime();
            // list query string
            String query = ActivityConstant.GET_ALLACTIVITY + ActivityConstant.GET_ALLACTIVITY_HQL
                    + ActivityConstant.SEARCH;
            // count query string
            String queryCount = ActivityConstant.SELECTCOUNT + ActivityConstant.GET_ALLACTIVITY_HQL
                    + ActivityConstant.SEARCH;
            // add search condition activityStartTime
            if (!(activityStartTime == null || activityStartTime.length() == 0)) {
                query += ActivityConstant.STARTTIME_SEARCH;
                queryCount += ActivityConstant.STARTTIME_SEARCH;
            }
            // add search condition activityEndTime
            if (!(activityEndTime == null || activityEndTime.length() == 0)) {
                query += ActivityConstant.ENDTIME_SEARCH;
                queryCount += ActivityConstant.ENDTIME_SEARCH;
            }
            // add search condition activityTypes
            if (!(activityType == null || activityType.length == 0 || CRMConstant.SEARCHALL_FLAG
                    .equalsIgnoreCase(activityType[0]))) {
                query += ActivityConstant.TYPELIST_SEARCH;
                queryCount += ActivityConstant.TYPELIST_SEARCH;
            }
            // add search condition activityRanges
            if (!(activityRange == null || activityRange.length == 0 || CRMConstant.SEARCHALL_FLAG
                    .equalsIgnoreCase(activityRange[0]))) {
                query += ActivityConstant.RANGELIST_SEARCH;
                queryCount += ActivityConstant.RANGELIST_SEARCH;
            }
            // add search condition activityStates
            if (!(activityState == null || activityState.length == 0 || CRMConstant.SEARCHALL_FLAG
                    .equalsIgnoreCase(activityState[0]))) {
                query += ActivityConstant.STATELIST_SEARCH;
                queryCount += ActivityConstant.STATELIST_SEARCH;
            }
            activityList = getHibernateTemplate().executeFind(
                    new SuperHibernateCallback(query, currpage, activitySearchBean, limit));
            total = (Long) getHibernateTemplate().executeFind(
                    new SuperHibernateCallback(queryCount, 0, activitySearchBean, limit)).get(0);
        }
        map.put(CRMConstant.TOTAL, total);
        map.put(CRMConstant.ITEMS, changeActivityList(activityList));
        LOG.debug("method searchActivity end.");
        return map;
    }

    /**
     * @see ActivityInfoDao#updateActivity(ActivityInfoDto)
     */
    @Override
    public void updateActivity(ActivityInfoDto activityInfoDto) throws DataAccessException {
        LOG.debug("method updateActivity start.");
        int[] customerName = activityInfoDto.getCustomerName();
        StringBuffer sb = new StringBuffer();
        sb.append(customerName[0]);
        // change customerName array to a String with a comma split
        for (int i = 1; i < customerName.length; i++) {
            sb.append(CRMConstant.COMMA_SYMBOL);
            sb.append(customerName[i]);
        }
        activityInfoDto.setActivityMember(sb.toString());
        getHibernateTemplate().saveOrUpdate(activityInfoDto);
        LOG.debug("method updateActivity end.");
    }

    /**
     * @see ActivityInfoDao#deleteActivity(String)
     */
    @Override
    public void deleteActivity(String activityIDs) {
        LOG.debug("method deleteActivity start.");
        getHibernateTemplate().bulkUpdate(ActivityConstant.DELETE_ACTIVITY + activityIDs);
        LOG.debug("method deleteActivity end.");
    }

    /**
     * change activityList to a new list that from customerID String to
     * customerName String
     * 
     * @param activityList
     * @return newActivityList
     */
    private List<ActivityBean> changeActivityList(List<ActivityBean> activityList)
            throws DataAccessException, NumberFormatException {
        LOG.debug("method changeActivityList start.");
        List<ActivityBean> newActivityList = new ArrayList<ActivityBean>();
        for (int i = 0; i < activityList.size(); i++) {
            ActivityBean activityBean = activityList.get(i);
            activityBean.setActivityFunds(activityBean.getActivityFunds() + ActivityConstant.YUAN);
            String activityMember = activityBean.getActivityMember();
            activityBean.setMemberName(getCustomerNameStrbyIDStr(activityMember));
            newActivityList.add(activityBean);
        }
        LOG.debug("method changeActivityList end.");
        return newActivityList;
    }

    /**
     * get customerName String from customerID String
     * 
     * @param customerIDStr
     * @return customerNameStr
     */
    private String getCustomerNameStrbyIDStr(String customerIDStr) throws DataAccessException,
            NumberFormatException {
        LOG.debug("method getCustomerName start.");
        String[] customerIDs = customerIDStr.split(CRMConstant.COMMA_SYMBOL);
        List<String> customerName = new ArrayList<String>();
        int customerIDFirst = Integer.valueOf(customerIDs[0]);
        customerName.add(findCustomerNameByID(customerIDFirst));
        StringBuffer customerNameStr = new StringBuffer();
        customerNameStr.append(customerName.get(0));
        for (int i = 1; i < customerIDs.length; i++) {
            int customerID = Integer.valueOf(customerIDs[i]);
            customerName.add(findCustomerNameByID(customerID));
            customerNameStr.append(CRMConstant.COMMA_SYMBOL);
            customerNameStr.append(customerName.get(i));
        }
        LOG.debug("method getCustomerName end.");
        return customerNameStr.toString();
    }

    /**
     * find customerName by past customerID
     * 
     * @param customerID
     * @return customerName
     */
    @SuppressWarnings("rawtypes")
    private String findCustomerNameByID(int customerID) throws DataAccessException {
        LOG.debug("method findCustomerNameByID start.");
        String customerName = ActivityConstant.DELETED_CUSTOMER;
        List customerNameList = hibernateTemplate.find(ActivityConstant.SELECT_CUS_CUSTOMER_NAME
                + customerID);
        if (customerNameList.size() != 0) {
            customerName = (String) customerNameList.get(0);
        }
        LOG.debug("method findCustomerNameByID end.");
        return customerName;
    }

    /**
     * 
     * @return hibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    /**
     * 
     * @param hibernateTemplate
     *            the hibernateTemplate to set
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
}
