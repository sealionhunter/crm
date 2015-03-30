package com.ustcsoft.gs.crm.webui.system.service.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.system.dao.GroupInfoDao;
import com.ustcsoft.gs.crm.webui.system.dto.GroupManagerDto;
import com.ustcsoft.gs.crm.webui.system.service.GroupInfoService;

/**
 * 
 * @author maxue
 * 
 */
public class GroupInfoServiceImpl implements GroupInfoService {
    public static final Log log = LogFactory.getLog(GroupInfoServiceImpl.class);
    private GroupInfoDao groupInfoDao = null;

    /**
     * @param groupInfoDao
     *            the groupInfoDao to set
     */
    public void setGroupInfoDao(GroupInfoDao groupInfoDao) {
        this.groupInfoDao = groupInfoDao;
    }

    /**
     * get Group
     * 
     * @return type of Map<String,Object>
     */
    @Override
    public Map<String, Object> getGroup(int groupID) {
        return groupInfoDao.getGroup(groupID);
    }

    /**
     * query User by groupID
     * 
     * @param departmentID
     * @param groupID
     * @param currtPage
     * @param pageSize
     * @return type of Map<String, Object>
     * @throws CRMDBException
     */
    @Override
    public Map<String, Object> getGroupMembers(int departmentID, int groupID, int currtPage,
            int pageSize) throws CRMDBException {
        log.debug("method getGroupMembers start!");
        Map<String, Object> members = null;
        try {
            members = groupInfoDao.getGroupMembers(departmentID, groupID, currtPage, pageSize);
        } catch (DataAccessException e) {
            log.error("DataAccessException occurs in method getGroupMembers!", e);
            throw new CRMDBException("DataAccessException occurs in method getGroupMembers!", e);
        }
        log.debug("method getGroupMembers end!");
        return members;
    }

    /**
     * update group
     * 
     * @param groupManagerDto
     * @throws CRMDBException
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Map<String, Object> updateGroup(GroupManagerDto groupManagerDto) throws CRMDBException {
        log.debug("method updateGroup start!");
        Map<String, Object> map = null;
        try {
            map = groupInfoDao.updateGroup(groupManagerDto);
        } catch (DataAccessException e) {
            log.error("DataAccessException occurs in method updateGroup!", e);
            throw new CRMDBException("DataAccessException occurs in method updateGroup!", e);
        }
        log.debug("method updateGroup end!");
        return map;
    }

    /**
     * delete GroupInfo
     * 
     * @param groupID
     * @throws CRMDBException
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteGroup(int groupID) throws CRMDBException {
        log.debug("method deleteGroup start!");
        try {
            groupInfoDao.deleteGroup(groupID);
        } catch (DataAccessException e) {
            log.error("DataAccessException occurs in method deleteGroup!", e);
            throw new CRMDBException("DataAccessException occurs in method deleteGroup!", e);
        }
        log.debug("method deleteGroup end!");
    }

    /**
     * judge groupName is existed
     * 
     * @param groupManagerDto
     * @return boolean
     * @throws CRMDBException
     */
    @Override
    public boolean judgeGroupName(GroupManagerDto groupManagerDto) throws CRMDBException {
        log.debug("method judgeGroupName start!");
        boolean nameIsExisted = true;
        String groupName = groupManagerDto.getGroupName();
        int groupID = groupManagerDto.getGroupID();
        try {
            if (groupID == 0) {
                nameIsExisted = groupInfoDao.judgeGroupName(groupName);
            } else {
                String oldName = groupInfoDao.findGroupNameByID(groupID);
                if (groupName.equals(oldName)) {
                    nameIsExisted = false;
                } else {
                    nameIsExisted = groupInfoDao.judgeGroupName(groupName);
                }
            }
        } catch (DataAccessException e) {
            log.error("DataAccessException occurs in method judgeGroupName!", e);
            throw new CRMDBException("DataAccessException occurs in method judgeGroupName!", e);
        }
        log.debug("method judgeGroupName end!");
        return nameIsExisted;
    }

    /**
     * remove Members from Group which id is groupID
     * 
     * @throws CRMDBException
     */
    @Override
    public void removeMembers(String removeIDs, int groupID, int otherGroupID)
            throws CRMDBException {
        log.debug("method removeMembers start!");
        try {
            groupInfoDao.removeMembers(removeIDs, groupID, otherGroupID);
        } catch (DataAccessException e) {
            log.error("DataAccessException occurs in method removeMwmbers", e);
            throw new CRMDBException("DataAccessException occurs in method removeMwmbers", e);
        }
        log.debug("method removeMembers start!");
    }

    /**
     * add Members to Group which id is groupID
     * 
     * @throws CRMDBException
     */
    @Override
    public void addMembers(String addIDs, int groupID) throws CRMDBException {
        log.debug("method removeMembers start!");
        try {
            groupInfoDao.addMembers(addIDs, groupID);
        } catch (DataAccessException e) {
            log.error("DataAccessException occurs in method addMembers", e);
            throw new CRMDBException("DataAccessException occurs in method addMembers", e);
        }
        log.debug("method removeMembers start!");
    }
}
