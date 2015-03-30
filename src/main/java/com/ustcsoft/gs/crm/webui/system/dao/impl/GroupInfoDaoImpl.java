package com.ustcsoft.gs.crm.webui.system.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.util.SuperHibernateCallback;
import com.ustcsoft.gs.crm.webui.system.bean.UserInfoSearchBean;
import com.ustcsoft.gs.crm.webui.system.constant.SystemConstant;
import com.ustcsoft.gs.crm.webui.system.dao.GroupInfoDao;
import com.ustcsoft.gs.crm.webui.system.dto.GroupManagerDto;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;

/**
 * Class GroupInfoDao used to operating database,return the result to service
 * 
 * @author maxue
 * 
 */
public class GroupInfoDaoImpl implements GroupInfoDao {
    private static final Log log = LogFactory.getLog(GroupInfoDaoImpl.class);
    private HibernateTemplate hibernateTemplate;

    /**
     * @return hibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    /**
     * @param hibernateTemplate
     *            the hibernateTemplate to set
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /**
     * query Members
     * 
     * @param departmentID
     * @param groupID
     * @param currPage
     * @param pageSize
     * @return map
     * @throws DataAccessException
     *             in case of DataAccessException
     * @throws NumberFormatException
     *             in case of NumberFormatException
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getGroupMembers(int departmentID, int groupID, int currPage,
            int pageSize) {
        log.debug("method getGroupMembers start!");
        Map<String, Object> map = new HashMap<String, Object>();
        List<UserInfoDto> uDtos = null;
        UserInfoSearchBean searchValue = new UserInfoSearchBean();
        searchValue.setGroupID(groupID);
        StringBuffer hqlList = new StringBuffer(SystemConstant.GROUPMEMBERS_LIST_HQL);
        StringBuffer hqlCount = new StringBuffer(SystemConstant.GROUPMEMBERS_COUNT_HQL);
        if (groupID != 1) {
            searchValue.getChildDepartmentID().add(departmentID);
            searchValue
                    .setChildDepartmentID(getChildDepartment(searchValue.getChildDepartmentID()));
            hqlList.append(SystemConstant.IN_DEPARTMENT_LIST_HQL);
            hqlCount.append(SystemConstant.IN_DEPARTMENT_LIST_HQL);
        }

        uDtos = getHibernateTemplate().executeFind(
                new SuperHibernateCallback(hqlList.toString(), currPage, searchValue, pageSize));

        String[] params = { SystemConstant.GROUPID, SystemConstant.CHILDDEPARTMENTID };
        Object[] values = { groupID, searchValue.getChildDepartmentID() };
        long total = (Long) getHibernateTemplate().findByNamedParam(hqlCount.toString(), params,
                values).get(0);

        map.put(CRMConstant.TOTAL, total);
        map.put(CRMConstant.ITEMS, uDtos);
        log.debug("method getGroupMembers end!");
        return map;
    }

    /**
     * update groupInfo
     * 
     * @param groupManagerDto
     * @throws DataAccessException
     *             in case of DataAccessException
     * @throws NumberFormatException
     *             in case of NumberFormatException
     */
    @Override
    public Map<String, Object> updateGroup(GroupManagerDto groupManagerDto) {
        log.debug("method updateGroup start!");
        Map<String, Object> map = new HashMap<String, Object>();
        getHibernateTemplate().saveOrUpdate(groupManagerDto);
        map.put("groupID", groupManagerDto.getGroupID());
        log.debug("method updateGroup end!");
        return map;
    }

    /**
     * get Group
     * 
     * @param groupID
     *            ID of a group
     * @return type of Map<String, Object>
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getGroup(int groupID) {
        log.debug("method getGroup start!");
        Map<String, Object> groupMap = new HashMap<String, Object>();
        List<GroupManagerDto> groupList = hibernateTemplate.findByNamedParam(
                SystemConstant.SELECT_GROUP_HQL, "groupID", groupID);
        groupMap.put(CRMConstant.ITEMS, groupList);
        log.debug("method getGroup end!");
        return groupMap;
    }

    /**
     * delete GroupInfo and update UserInfo
     * 
     * @param groupID
     * @throws DataAccessException
     *             in case of DataAccessException
     * @throws NumberFormatException
     *             in case of NumberFormatException
     */
    @Override
    public void deleteGroup(int groupID) {
        log.debug("method deleteGroup start!");
        getHibernateTemplate().bulkUpdate(SystemConstant.GROUP_DELETE_HQL, groupID);
        getHibernateTemplate().bulkUpdate(SystemConstant.GROUPACTION_DELETE_HQL, groupID);
        log.debug("method deleteGroup end!");
    }

    /**
     * judge groupName is existed
     * 
     * @param groupName
     * @return boolean
     * @throws DataAccessException
     *             in case of DataAccessException
     * @throws NumberFormatException
     *             in case of NumberFormatException
     */
    @Override
    public boolean judgeGroupName(String groupName) {
        log.debug("method judgeGroupName start!");
        long groupCount = (Long) hibernateTemplate.findByNamedParam(
                SystemConstant.GROUP_GROUPNAME_COUNT_HQL, "groupName", groupName).get(0);
        log.debug("method judgeGroupName end!");
        return groupCount != 0;
    }

    /**
     * find groupName By groupID
     * 
     * @param groupID
     * @return groupName
     * @throws DataAccessException
     *             in case of DataAccessException
     * @throws NumberFormatException
     *             in case of NumberFormatException
     */
    @Override
    public String findGroupNameByID(int groupID) {
        log.debug("method findUserNameByID start!");
        String groupName = (String) hibernateTemplate.findByNamedParam(
                SystemConstant.FIND_GROUPNAME_HQL, "groupID", groupID).get(0);
        log.debug("method findUserNameByID end!");
        return groupName;
    }

    /**
     * convert received IDs to array
     * 
     * @param sIDs
     * @return resultIDs
     * @throws NumberFormatException
     *             in case of NumberFormatException
     */
    private List<Integer> convertStringIDs(String sIDs) throws NumberFormatException {
        log.debug("convertStringIDs method start!");
        List<Integer> resultIDs = new ArrayList<Integer>();
        String[] ids = null;
        if (sIDs == null || "".equals(sIDs)) {
            ids = new String[0];
        } else {
            ids = sIDs.split(",");
        }
        // convert users' IDs type to Integer
        for (String id : ids) {
            resultIDs.add(Integer.valueOf(id.trim()));
        }
        log.debug("convertStringIDs method end!");
        return resultIDs;
    }

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
    @Override
    @SuppressWarnings("unchecked")
    public void addMembers(String addIDs, int groupID) {
        log.debug("addMembers method start!");
        // convert addIDs to array
        List<Integer> resultIDs = convertStringIDs(addIDs);
        List<UserInfoDto> userInfoList = hibernateTemplate.findByNamedParam(
                SystemConstant.ADD_GROUP_MEMBERS, "userID", resultIDs);
        if (userInfoList.size() != 0) {
            for (UserInfoDto userInfo : userInfoList) {
                userInfo.setGroupID(groupID);
            }
            hibernateTemplate.saveOrUpdateAll(userInfoList);
        }
        log.debug("addMembers method end!");
    }

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
    @Override
    @SuppressWarnings("unchecked")
    public void removeMembers(String removeIDs, int groupID, int otherGroupID) {
        log.debug("removeMembers method start!");
        List<Integer> resultIDs = convertStringIDs(removeIDs);
        String[] paramNames = new String[] { "groupID", "userID" };
        Object[] values = new Object[] { groupID, resultIDs };
        List<UserInfoDto> userInfoList = hibernateTemplate.findByNamedParam(
                SystemConstant.REMOVE_GROUP_MEMBERS, paramNames, values);
        if (userInfoList.size() != 0) {
            for (UserInfoDto userInfo : userInfoList) {
                userInfo.setGroupID(otherGroupID);
            }
            hibernateTemplate.saveOrUpdateAll(userInfoList);
        }
        log.debug("removeMembers method end!");
    }

    /**
     * get children department
     * 
     * @param departmentIDs
     * @return departmentIDs
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<Integer> getChildDepartment(List<Integer> departmentIDs) {
        log.debug("method getChildDepartment start!");
        List<Integer> list = departmentIDs;
        int sizea = 0;
        int sizeb = -1;
        // 防止两个部门互为上级时出现死循环
        while (sizea != sizeb) {
            list = getChild(list);
            sizea = departmentIDs.size();
            departmentIDs.addAll(list);
            HashSet h = new HashSet(departmentIDs);
            departmentIDs.clear();
            departmentIDs.addAll(h);
            sizeb = departmentIDs.size();
        }
        log.debug("method getChildDepartment end!");
        return departmentIDs;
    }

    /**
     * get list's children while list is not null
     * 
     * @param list
     * @return list
     */
    @SuppressWarnings("unchecked")
    public List<Integer> getChild(List<Integer> list) {
        log.debug("method getChild start!");
        String hqlString = "select departmentID from DepartmentDto where fatherDepartmentID in(:departmentIDs)";
        list = hibernateTemplate.findByNamedParam(hqlString, "departmentIDs", list);
        log.debug("method getChild end!");
        return list;
    }
}
