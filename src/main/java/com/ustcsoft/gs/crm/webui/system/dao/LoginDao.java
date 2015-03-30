package com.ustcsoft.gs.crm.webui.system.dao;

import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;

/**
 * 
 * @author zhouzhou
 * 
 */
public interface LoginDao {

    /**
     * method to find userinfo by username and password
     * 
     * @param userName
     * @param password
     * @return UserInfoDto
     */
    public UserInfoDto findByUsernamePassword(String userName, String password);
}
