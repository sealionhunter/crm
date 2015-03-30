package com.ustcsoft.gs.crm.webui.system.service;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;

/**
 * 
 * @author zhouzhou
 * 
 */
public interface LoginService {

    /**
     * method to find userinfo by username and password
     * 
     * @param userName
     * @param password
     * @return UserInfoDto
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public UserInfoDto findByUsernamePassword(String userName, String password)
            throws CRMDBException;
}
