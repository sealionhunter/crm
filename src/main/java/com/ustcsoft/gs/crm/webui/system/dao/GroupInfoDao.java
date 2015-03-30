package com.ustcsoft.gs.crm.webui.system.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.system.dto.GroupManagerDto;

public interface GroupInfoDao {

    /**
     * query user
     * 
     * @param departmentID
     * @param groupID
     * @param currtPage
     * @param pageSize
     * @return type of Map<String,Object>
     */
    public Map<String, Object> getGroupMembers(int departmentID, int groupID, int currtPage,
            int pageSize);

    /**
     * update Group for DB
     * 
     * @param groupManagerDto
     */
    public Map<String, Object> updateGroup(GroupManagerDto groupManagerDto);

    /**
     * delete groupInfo
     * 
     * @param groupID
     */
    public void deleteGroup(int groupID);

    /**
     * get Group
     * 
     * @return type of Map<String,Object>
     */
    public Map<String, Object> getGroup(int groupID);

    /**
     * judge groupName is existed
     * 
     * @param groupName
     * @return type of boolean
     */
    public boolean judgeGroupName(String groupName);

    /**
     * find groupName By groupID
     * 
     * @param groupID
     * @return type of String
     */
    public String findGroupNameByID(int groupID);

    /**
     * remove group members
     * 
     * @param removeIDs
     * @param groupID
     * @throws DataAccessException
     *             in case of DataAccessException
     * @throws NumberFormatException
     *             in case of NumberFormatException
     */
    public void removeMembers(String removeIDs, int groupID, int otherGroupID);

    /**
     * add group members
     * 
     * @param addIDs
     * @param groupID
     * @throws DataAccessException
     *             in case of DataAccessException
     * @throws NumberFormatException
     *             in case of NumberFormatException
     */
    public void addMembers(String addIDs, int groupID);
}
