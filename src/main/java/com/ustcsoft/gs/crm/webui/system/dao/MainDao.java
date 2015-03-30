package com.ustcsoft.gs.crm.webui.system.dao;

import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;

/**
 * 
 * @author zhouzhou
 * 
 */
public interface MainDao {

    /**
     * method used to change password by userid
     * 
     * @param userID
     * @param password
     */
    public void changePasswordByUserID(int userID, String password);

    /**
     * method used to get userinfodto by userid
     * 
     * @param userID
     * @return UserInfoDto
     */
    public UserInfoDto findByUserID(int userID);
}
