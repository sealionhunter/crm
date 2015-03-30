package com.ustcsoft.gs.crm.webui.system.service;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;

/**
 * 
 * @author zhouzhou
 * 
 */
public interface MainService {

    /**
     * method to change password by userid
     * 
     * @param userID
     * @param password
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void changePasswordByUserID(int userID, String password) throws CRMDBException;

    /**
     * method to find userinfodto byuserid
     * 
     * @param userID
     * @return UserInfoDto
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public UserInfoDto findByUserID(int userID) throws CRMDBException;
}
