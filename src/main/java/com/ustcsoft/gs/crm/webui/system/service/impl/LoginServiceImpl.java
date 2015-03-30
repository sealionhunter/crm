package com.ustcsoft.gs.crm.webui.system.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.system.dao.LoginDao;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;
import com.ustcsoft.gs.crm.webui.system.service.LoginService;

/**
 * 
 * @author zhouzhou
 * 
 */
public class LoginServiceImpl implements LoginService {

    /** used for getting class loginDao */
    private LoginDao loginDao = null;

    /** used for getting log */
    private static final Log LOG = LogFactory.getLog(LoginServiceImpl.class);

    /**
     * method to find userinfo by username and password
     * 
     * @param userName
     * @param password
     * @return UserInfoDto
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public UserInfoDto findByUsernamePassword(String userName, String password)
            throws CRMDBException {
        LOG.debug("method findByUsernamePassword start!");
        UserInfoDto userInfoDto = null;
        try {
            userInfoDto = loginDao.findByUsernamePassword(userName, password);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method findByUsernamePassword!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method findByUsernamePassword end!");
        return userInfoDto;
    }

    /**
     * @param loginDao
     *            the loginDao to set
     */
    public void setLoginDao(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

}
