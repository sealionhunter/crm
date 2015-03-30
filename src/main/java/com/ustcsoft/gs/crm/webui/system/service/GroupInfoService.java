package com.ustcsoft.gs.crm.webui.system.service;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.system.dto.GroupManagerDto;

public interface GroupInfoService {

    /**
     * query User by groupID
     * 
     * @param departmentID
     * @param groupID
     * @param currtPage
     * @param pageSize
     * @return type of Map<String,Object>
     * @throws CRMDBException
     */
    public Map<String, Object> getGroupMembers(int departmentID, int groupID, int currtPage,
            int pageSize) throws CRMDBException;

    /**
     * update Group
     * 
     * @param groupManagerDto
     */
    public Map<String, Object> updateGroup(GroupManagerDto groupManagerDto) throws CRMDBException;

    /**
     * get Group
     * 
     * @param groupID
     * @return type of Map<String,Object>
     */
    public Map<String, Object> getGroup(int groupID);

    /**
     * delete groupInfo
     * 
     * @param groupID
     */
    public void deleteGroup(int groupID) throws CRMDBException;

    /**
     * judge if GroupName existed
     * 
     * @param groupManagerDto
     * @return type of boolean
     */
    public boolean judgeGroupName(GroupManagerDto groupManagerDto) throws CRMDBException;

    /**
     * remove group members
     * 
     * @param removeIDs
     * @param groupID
     * @throws CRMDBException
     */
    public void removeMembers(String removeIDs, int groupID, int otherGroupID)
            throws CRMDBException;

    /**
     * add group members
     * 
     * @param addIDs
     * @param groupID
     * @throws CRMDBException
     *             in case of CRMDBException
     */
    public void addMembers(String addIDs, int groupID) throws CRMDBException;
}
