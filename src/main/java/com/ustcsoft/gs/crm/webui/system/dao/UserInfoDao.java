package com.ustcsoft.gs.crm.webui.system.dao;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.system.bean.UserInfoSearchBean;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;

public interface UserInfoDao {

    /**
     * get Size of UserInfo
     * 
     * @param queryString
     * @return type of long
     */
    public long getUserInfoSize(String queryString);

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
    public Map<String, Object> getUserDepartment(int userID, int groupID, int departmentID);

    /**
     * update User
     * 
     * @param userInfoDto
     */
    public void updateUser(UserInfoDto userInfoDto);

    /**
     * check Delete
     * 
     * @param userIDs
     * @return map
     */
    public Map<String, Object> checkDelete(String userIDs);

    /**
     * delete User
     * 
     * @param userIDs
     */
    public void deleteUser(String userIDs);

    /**
     * query userName by userID
     * 
     * @param userID
     * @return String userName
     */
    public String getUserNameByID(int userID);

    /**
     * query User
     * 
     * @param searchFlag
     * @param searchValues
     * @param currentPage
     * @param pageSize
     * @return type of Map<String, Object>
     */
    public Map<String, Object> queryUser(int searchFlag, UserInfoSearchBean searchValues,
            int currentPage, final int pageSize);

    /**
     * judge if UserName existed
     * 
     * @param userName
     * @return type of boolean
     */
    public boolean judgeUserName(String userName);

    /**
     * find UserName By ID
     * 
     * @param userID
     * @return type of String
     */
    public String findUserNameByUserID(int userID);

    /**
     * judge if JobID existed
     * 
     * @param jobID
     * @return type of boolean
     */
    public boolean judgeJobID(String jobID);

    /**
     * find JobID By ID
     * 
     * @param userID
     * @return type of String
     */
    public String findJobIDByUserID(int userID);

    public String getDepartmentNameByID(int departmentID);

}
