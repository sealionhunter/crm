package com.ustcsoft.gs.crm.webui.activity.service.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.crm.webui.activity.bean.ActivitySearchBean;
import com.ustcsoft.gs.crm.webui.activity.dao.ActivityInfoDao;
import com.ustcsoft.gs.crm.webui.activity.dto.ActivityInfoDto;
import com.ustcsoft.gs.crm.webui.activity.service.ActivityInfoService;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;

/**
 * class ActivityListService handle the data from action and return to action
 * 
 * @see ActivityInfoService
 * @author xuzhen
 */
public class ActivityInfoServiceImpl implements ActivityInfoService {
    /** define one object of the Log and is used to write log */
    private final static Log LOG = LogFactory.getLog(ActivityInfoServiceImpl.class);
    /** define a object of the ActivityInfoDao */
    private ActivityInfoDao activityInfoDao;

    /**
     * get or search activities
     * 
     */
    @Override
    public Map<String, Object> getOrSearchActivity(final int searchFlag,
            ActivitySearchBean activitySearchBean, final int currpage, final int limit)
            throws CRMDBException {
        LOG.debug("method getOrSearchActivity start.");
        Map<String, Object> map = null;
        try {
            if (searchFlag == 0) {
                map = getActivityInfoDao().getAllActivity(currpage, limit);
            } else {
                map = getActivityInfoDao().searchActivity(searchFlag, activitySearchBean, currpage,
                        limit);
            }
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getOrSearchActivity.", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getOrSearchActivity end.");
        return map;
    }

    /**
     * @see ActivityInfoService#updateActivity(ActivityInfoDto)
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void updateActivity(ActivityInfoDto activity) throws CRMDBException {
        LOG.debug("method updateActivity start.");
        try {
            getActivityInfoDao().updateActivity(activity);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method updateActivity.", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method updateActivity end.");
    }

    /**
     * @see ActivityInfoService#deleteActivity(String)
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteActivity(String activityIDs) throws CRMDBException {
        LOG.debug("method deleteActivity start.");
        try {
            getActivityInfoDao().deleteActivity(CRMUtils.getStringForDelete(activityIDs));
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method deleteActivity.", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method deleteActivity end.");
    }

    /**
     * 
     * @return activityInfoDao
     */
    public ActivityInfoDao getActivityInfoDao() {
        return activityInfoDao;
    }

    /**
     * 
     * @param activityInfoDao
     *            the activityInfoDao to set
     */
    public void setActivityInfoDao(ActivityInfoDao activityInfoDao) {
        this.activityInfoDao = activityInfoDao;
    }
}
