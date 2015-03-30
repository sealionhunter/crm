package com.ustcsoft.gs.crm.webui.activity.service;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.activity.bean.ActivitySearchBean;
import com.ustcsoft.gs.crm.webui.activity.dto.ActivityInfoDto;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;

/**
 * the ActivityInfoService interface defined some methods of handle the result
 * from dao
 * 
 * @author xuzhen
 */
public interface ActivityInfoService {

    /**
     * get all activities or search activities from DB
     * 
     * @param searchFlag
     *            search mark
     * @param activitySearchBean
     *            search conditions
     * @param currpage
     *            current page
     * @return map the result from dao
     * @throws CRMDBException
     *             in case of Hibernate exception
     */
    public Map<String, Object> getOrSearchActivity(int searchFlag,
            ActivitySearchBean activitySearchBean, int currpage, int limit) throws CRMDBException;

    /**
     * update activity for DB
     * 
     * @param activity
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void updateActivity(ActivityInfoDto activity) throws CRMDBException;

    /**
     * delete activity from DB
     * 
     * @param activityIDs
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void deleteActivity(String activityIDs) throws CRMDBException;

}