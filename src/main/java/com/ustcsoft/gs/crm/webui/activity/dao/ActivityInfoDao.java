package com.ustcsoft.gs.crm.webui.activity.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.activity.bean.ActivitySearchBean;
import com.ustcsoft.gs.crm.webui.activity.dto.ActivityInfoDto;

/**
 * the ActivityInfoDao interface define some methods of operating the database
 * 
 * @author xuzhen
 */
public interface ActivityInfoDao {

    /**
     * get all activities from DB
     * 
     * @param currpage
     *            current page
     * @return map query results(activityList, total) to display
     * @throws DataAccessException
     *             in case of Hibernate exception
     * @throws NumberFormatException
     *             in case of NumberFormat Exception
     */
    public Map<String, Object> getAllActivity(int currpage, int limit) throws DataAccessException,
            NumberFormatException;

    /**
     * search activities from DB
     * 
     * @param searchFlag
     *            search mark
     * @param activitySearchBean
     *            search conditions
     * @param currpage
     *            current page
     * @return map query results(activityList, total) to display
     * @throws DataAccessException
     *             in case of Hibernate exception
     * @throws NumberFormatException
     *             in case of NumberFormat Exception
     */
    public Map<String, Object> searchActivity(int searchFlag,
            ActivitySearchBean activitySearchBean, final int currpage, int limit)
            throws DataAccessException, NumberFormatException;

    /**
     * update activity for DB
     * 
     * @param activityInfoDto
     *            activityInfo which will be added or edited
     * @throws DataAccessException
     *             in case of Hibernate exception
     */
    public void updateActivity(ActivityInfoDto activityInfoDto) throws DataAccessException;

    /**
     * delete activity from DB
     * 
     * @param activityIDs
     *            all activityIDs which will be deleted
     * @throws DataAccessException
     *             in case of Hibernate exception
     */
    public void deleteActivity(String activityIDs) throws DataAccessException;

}