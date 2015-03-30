package com.ustcsoft.gs.crm.webui.customer.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.customer.bean.SourceSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dao.SourceInfoDao;
import com.ustcsoft.gs.crm.webui.customer.dto.SourceInfoDto;
import com.ustcsoft.gs.crm.webui.customer.service.SourceInfoService;
import com.ustcsoft.gs.crm.webui.system.bean.UserInfoSearchBean;
import com.ustcsoft.gs.crm.webui.system.dao.AuthorizationDao;
import com.ustcsoft.gs.crm.webui.system.dao.MainDao;
import com.ustcsoft.gs.crm.webui.system.dao.UserInfoDao;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;

/**
 * @author xuzhen
 */
public class SourceInfoServiceImpl implements SourceInfoService {
    private static Log LOG = LogFactory.getLog(SourceInfoServiceImpl.class);

    private SourceInfoDao sourceInfoDao;
    private AuthorizationDao authorizationDao;
    private UserInfoDao userInfoDao;
    private MainDao mainDao;

    /**
     * method to get all customer source records or searched source records
     * 
     * @param searchFlag
     *            search mark
     * @param sourceSearchBean
     *            search conditions
     * @param currpage
     *            search page
     * @return map query results to display
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public Map<String, Object> getOrSearchSource(int searchFlag, SourceSearchBean sourceSearchBean,
            int currpage, int limit, int userID) throws CRMDBException {
        LOG.debug("method getOrSearchSource start!");
        Map<String, Object> map = null;

        Integer[] userIDs = getUserID(userID);
        try {
            if (searchFlag == 0) {
                map = getSourceInfoDao().getAllSource(currpage, limit, userIDs);
            } else {
                sourceSearchBean.setUserID(userIDs);
                map = getSourceInfoDao()
                        .searchSource(searchFlag, sourceSearchBean, currpage, limit);
            }
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getOrSearchSource.", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getOrSearchSource end!");
        return map;
    }

    /**
     * delete source record
     * 
     * @param sourceIDs
     *            sourceID which will be deleted
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteSource(String sourceIDs) throws CRMDBException {
        LOG.debug("method deleteCustomer start!");
        try {
            getSourceInfoDao().deleteSource(CRMUtils.getStringForDelete(sourceIDs));
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method deleteSource.", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method deleteCustomer end!");
    }

    /**
     * add or edit source record
     * 
     * @param source
     *            sourceInfo which will be added or edited
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void updateSource(SourceInfoDto source) throws CRMDBException {
        LOG.debug("method updateSource method start!");
        try {
            getSourceInfoDao().updateSource(source);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method updateSource.", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method updateSource method end!");
    }

    /**
     * get all customers' name and id
     * 
     * @return map query results to display
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public Map<String, Object> getCustomer(int customerFlag, int userID) throws CRMDBException {
        LOG.debug("method getCustomer method start!");
        Map<String, Object> map;
        Integer[] userIDs = { userID };
        if (customerFlag != 2) {
            userIDs = getUserID(userID);
        }
        try {
            // 取得用户自己及其下属的所有客户
            map = getSourceInfoDao().getCustomer(customerFlag, userIDs);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getCustomer.", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getCustomer method end!");
        return map;
    }

    /**
     * get the userID of the user himself and his underlings
     * 
     * @param userID
     * @return userIDs
     * @throws DataAccessException
     */
    @Override
    @SuppressWarnings("unchecked")
    public Integer[] getUserID(int userID) throws DataAccessException {
        LOG.debug("method getUserID method start!");
        UserInfoSearchBean userSearchBean = new UserInfoSearchBean();
        UserInfoDto userDto = getMainDao().findByUserID(userID);
        userSearchBean.setDepartmentID(userDto.getDepartmentID());
        userSearchBean.setGroupID(userDto.getGroupID());
        userSearchBean.setProjectTeamID(userDto.getProjectTeamID());
        userSearchBean.getChildDepartmentID().add(userSearchBean.getDepartmentID());
        Map<String, Object> userMap = getUserInfoDao().queryUser(0, userSearchBean, 0, 0);
        List<?> users = (List<?>) userMap.get(CRMConstant.ITEMS);
        Map<String, Object> user = null;
        int userSize = users.size();
        Integer[] userIDs = new Integer[userSize + 2];
        for (int i = 0; i < userSize; i++) {
            user = (Map<String, Object>) users.get(i);
            String userStr = user.get(CRMConstant.USER_ID).toString();
            userIDs[i] = Integer.parseInt(userStr);
        }
        userIDs[userSize] = userID;
        userIDs[userSize + 1] = 0;
        LOG.debug("method getUserID method end!");
        return userIDs;
    }

    /**
     * 
     * @return sourceInfoDao
     */
    public SourceInfoDao getSourceInfoDao() {
        return sourceInfoDao;
    }

    /**
     * 
     * @param sourceInfoDao
     *            the sourceInfoDao to set
     */
    public void setSourceInfoDao(SourceInfoDao sourceInfoDao) {
        this.sourceInfoDao = sourceInfoDao;
    }

    /**
     * @return the authorizationDao
     */
    public AuthorizationDao getAuthorizationDao() {
        return authorizationDao;
    }

    /**
     * @param authorizationDao
     *            the authorizationDao to set
     */
    public void setAuthorizationDao(AuthorizationDao authorizationDao) {
        this.authorizationDao = authorizationDao;
    }

    /**
     * @return the userInfoDao
     */
    public UserInfoDao getUserInfoDao() {
        return userInfoDao;
    }

    /**
     * @param userInfoDao
     *            the userInfoDao to set
     */
    public void setUserInfoDao(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    /**
     * @return the mainDao
     */
    public MainDao getMainDao() {
        return mainDao;
    }

    /**
     * @param mainDao
     *            the mainDao to set
     */
    public void setMainDao(MainDao mainDao) {
        this.mainDao = mainDao;
    }
}
