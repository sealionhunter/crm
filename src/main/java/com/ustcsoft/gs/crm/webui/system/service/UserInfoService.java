package com.ustcsoft.gs.crm.webui.system.service;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.system.bean.UserInfoSearchBean;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;

public interface UserInfoService {
    /**
     * search Or Get All UserInfo
     * 
     * @param searchFlag
     * @param searchValue
     * @param currPage
     * @param pageSize
     * @return type of Map<String,Object.>
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> searchOrGetAllUserInfo(int searchFlag,
            UserInfoSearchBean searchValue, int currPage, int pageSize) throws CRMDBException;

    /**
     * get GroupID
     * 
     * @param groupID
     * @return type of Map<String,Object>
     */
    public Map<String, Object> getGroupID(int groupID);

    /**
     * get user's department information
     * 
     * @param userID
     * @param groupID
     * @param departmentID
     * @return Map department information Collection
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    public Map<String, Object> getUserDepartment(int userID, int groupID, int departmentID)
            throws CRMDBException;

    /**
     * update User
     * 
     * @param userInfoDto
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void updateUser(UserInfoDto userInfoDto) throws CRMDBException;

    /**
     * delete User
     * 
     * @param userIDs
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> deleteUser(String userIDs) throws CRMDBException;

    /**
     * reset User's Password
     * 
     * @param userID
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> resetUserPass(String userID) throws CRMDBException;

    /**
     * judge if UserName existed
     * 
     * @param userInfoDto
     * @return type of boolean
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public boolean judgeUserName(UserInfoDto userInfoDto) throws CRMDBException;

    /**
     * judge if JobID existed
     * 
     * @param userInfoDto
     * @return type of boolean
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public boolean judgeJobID(UserInfoDto userInfoDto) throws CRMDBException;

    public String getDepartmentNameByID(int departmentID);
}
