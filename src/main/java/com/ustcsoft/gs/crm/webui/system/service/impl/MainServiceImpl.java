package com.ustcsoft.gs.crm.webui.system.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CryptUtil;
import com.ustcsoft.gs.crm.webui.system.dao.MainDao;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;
import com.ustcsoft.gs.crm.webui.system.service.MainService;

/**
 * 
 * @author zhouzhou
 * 
 */
public class MainServiceImpl implements MainService {

    /** used for getting class mainDao */
    private MainDao mainDao = null;

    /** used for getting log */
    private static final Log LOG = LogFactory.getLog(LoginServiceImpl.class);

    /**
     * method to change password by userid
     * 
     * @param userID
     * @param password
     * @throws CRMDBException
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changePasswordByUserID(int userID, String password) throws CRMDBException {
        LOG.debug("method changePasswordByUserID in MainServiceImpl start");
        try {
            mainDao.changePasswordByUserID(userID, CryptUtil.encrypt(password));
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method changePasswordByUserID!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method changePasswordByUserID in MainServiceImpl end");
    }

    /**
     * method to find userinfodto by userid
     * 
     * @param userID
     * @return UserInfoDto
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public UserInfoDto findByUserID(int userID) throws CRMDBException {
        LOG.debug("method findByUserID start!");
        UserInfoDto userInfo = null;
        try {
            userInfo = mainDao.findByUserID(userID);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method findByUserID!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method findByPassfindByUserIDwordUserID end!");
        return userInfo;
    }

    /**
     * @param mainDao
     *            the mainDao to set
     */
    public void setMainDao(MainDao mainDao) {
        this.mainDao = mainDao;
    }

}
