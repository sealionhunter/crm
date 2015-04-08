package com.ustcsoft.gs.crm.webui.system.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.common.util.SuperHibernateCallback;
import com.ustcsoft.gs.crm.webui.system.bean.UserInfoSearchBean;
import com.ustcsoft.gs.crm.webui.system.constant.SystemConstant;
import com.ustcsoft.gs.crm.webui.system.dao.UserInfoDao;
import com.ustcsoft.gs.crm.webui.system.dto.DepartmentDto;
import com.ustcsoft.gs.crm.webui.system.dto.GroupManagerDto;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;

public class UserInfoDaoImpl implements UserInfoDao {
    private static final Log log = LogFactory.getLog(UserInfoDaoImpl.class);
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public UserInfoDto getUserByID(Integer userID) {
        log.debug("method getUserByID start!");
        UserInfoDto user = hibernateTemplate.get(UserInfoDto.class, userID);
        log.debug("method getUserByID end!");
        return user;
    }

    /**
     * get Size of UserInfo
     * 
     * @param queryString
     * @return type of long
     */
    @Override
    public long getUserInfoSize(String queryString) {
        log.debug("method getUserInfoSize start!");
        List<?> userResult = hibernateTemplate.find(queryString);
        log.debug("method getUserInfoSize end!");
        return (Long) userResult.get(0);
    }

    /**
     * get GroupID
     * 
     * @param groupID
     *            ID of a group
     * @return type of Map<String, Object>
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getGroupID(int groupID) {
        log.debug("method getGroupID start!");
        Map<String, Object> groupMap = new HashMap<String, Object>();
        List<GroupManagerDto> groupList = hibernateTemplate.findByNamedParam(
                SystemConstant.SELECT_GROUPID, "groupID", groupID);
        groupMap.put(CRMConstant.ITEMS, groupList);
        log.debug("method getGroupID end!");
        return groupMap;
    }

    /**
     * get user's department
     * 
     * @param userID
     * @param groupID
     * @param departmentID
     * @return type of Map<String, Object>
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getUserDepartment(int userID, int groupID, int departmentID) {
        log.debug("method getUserDepartment start!");
        Map<String, Object> map = new HashMap<String, Object>();
        List<DepartmentDto> list = null;

        List<Integer> departmentIDs = new ArrayList<Integer>();
        departmentIDs.add(departmentID);
        departmentIDs = getChildDepartment(departmentIDs);

        StringBuffer hqlString = new StringBuffer(SystemConstant.SELECT_USER_DEPARTMENT);
        if (groupID > 1) {
            hqlString.append(" where dmDto.departmentID in(:departmentIDs)");
            if (userIsManager(userID)) {
                list = hibernateTemplate.findByNamedParam(hqlString.toString(), "departmentIDs",
                        departmentIDs);
            } else {
                hqlString.append(" and dmDto.departmentID != :departmentID");
                String[] params = { "departmentIDs", "departmentID" };
                Object[] values = { departmentIDs, departmentID };
                list = hibernateTemplate.findByNamedParam(hqlString.toString(), params, values);
            }
        } else {
            list = hibernateTemplate.find(hqlString.toString());
        }
        map.put(CRMConstant.ITEMS, list);
        log.debug("method getUserDepartment start!");
        return map;
    }

    /**
     * update User
     * 
     * @param userInfoDto
     */
    @Override
    @SuppressWarnings("unchecked")
    public void updateUser(UserInfoDto userInfoDto) {
        log.debug("method updateUser start!");
        List<Integer> IDs = hibernateTemplate.find(
                "select uid.projectTeamID from UserInfoDto as uid where uid.userID = ?",
                userInfoDto.getUserID());
        if (IDs.size() > 0) {
            userInfoDto.setProjectTeamID(IDs.get(0));// TODO 有bug
        }
//        if (userInfoDto.getUserID() == 0) {
//        } else {
//            Integer groupID = (Integer) hibernateTemplate.find(
//                    "select uid.groupID from UserInfoDto as uid where uid.userID = ?",
//                    userInfoDto.getUserID()).get(0);
//            if (groupID != userInfoDto.getGroupID()) {
//                // do nothing
//                List<?> list = hibernateTemplate.find(
//                        "from ActionUserDto as aud where aud.userID = ? ", userInfoDto.getUserID());
//                hibernateTemplate.deleteAll(list);
//            }
//        }
        hibernateTemplate.saveOrUpdate(userInfoDto);
        log.debug("method updateUser end!");
    }

    /**
     * check delete
     * 
     * @param userIDs
     * @return map
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> checkDelete(String userIDs) {
        log.debug("method checkDelete start!");
        Map<String, Object> map = new HashMap<String, Object>();
        Integer[] userID = CRMUtils.getDeleteIds(userIDs);
        List<Integer> list1 = null;
        list1 = hibernateTemplate.findByNamedParam(SystemConstant.QUERY_IN_DEPARTMENT, "userID",
                userID);

        List<Integer> list2 = null;
        list2 = hibernateTemplate.findByNamedParam(SystemConstant.QUERY_IN_TEAM, "userID", userID);

        List<Integer> list3 = null;
        list3 = hibernateTemplate.findByNamedParam(SystemConstant.QUERY_IN_CUSTOMER, "userID",
                userID);

//        List<Integer> list4 = null;
//        list4 = hibernateTemplate.findByNamedParam(SystemConstant.QUERY_IN_SALES, "userID", userID);

        List<Integer> list5 = null;
        list5 = hibernateTemplate.findByNamedParam(SystemConstant.QUERY_IN_CONTACTTRACK, "userID",
                userID);

        List<Integer> result = new ArrayList<Integer>();
        result.addAll(list1);
        result.addAll(list2);
        result.addAll(list3);
//        result.addAll(list4);
        result.addAll(list5);
        map.put("list1", list1);
        map.put("list2", list2);
        map.put("list3", list3);
//        map.put("list4", list4);
        map.put("list5", list5);
        map.put("result", result);
        map.put("userID", userID);

        log.debug("method checkDelete end!");
        return map;
    }

    /**
     * delete User
     * 
     * @param userIDs
     */
    @Override
    public void deleteUser(String userIDs) {
        log.debug("method deleteUser start!");
        userIDs = "(" + userIDs + ")";
        hibernateTemplate.bulkUpdate(SystemConstant.USER_DELETE_HQL + userIDs);
        log.debug("method deleteUser end!");
    }

    /**
     * query User
     * 
     * @param searchFlag
     * @param searchValues
     * @param currentPage
     * @param pageSize
     * @return type of Map<String, Object>
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> queryUser(int searchFlag, UserInfoSearchBean searchValues,
            int currentPage, final int pageSize) {
        log.debug("method queryUser start!");
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer hqlList = new StringBuffer(SystemConstant.USER_GET_ALL_HQL);
        StringBuffer hqlCount = new StringBuffer(SystemConstant.USERINFO_COUNT1_HQL);
        int groupID = searchValues.getGroupID();
        if (groupID != 1) {
            boolean isManager = userIsManager(searchValues.getUserID());
            searchValues.setChildDepartmentID(getChildDepartment(searchValues
                    .getChildDepartmentID()));
            // 用户不是部长
            if (!isManager) {
                searchValues.getChildDepartmentID().remove(0);
                if (searchValues.getChildDepartmentID().size() != 0) {
                    hqlList.append(" and (user.userID = :userID or user.departmentID in(:childDepartmentID)) ");
                    hqlCount.append(" and (user.userID = :userID or user.departmentID in(:childDepartmentID)) ");
                } else {
                    hqlList.append(" and user.userID = :userID ");
                    hqlCount.append(" and user.userID = :userID ");
                }
                // 用户是部长
            } else if (isManager) {
                hqlList.append(" and user.departmentID in(:childDepartmentID) ");
                hqlCount.append(" and user.departmentID in(:childDepartmentID) ");
            }
        }
        if (searchFlag == 2) {
            if (!(searchValues.getDepartmentID() == 0)) {
                hqlList.append(" and user.departmentID =:departmentID ");
                hqlCount.append(" and user.departmentID =:departmentID ");
            }
            if (!(searchValues.getRealName() == null || searchValues.getRealName() == "" || searchValues
                    .getRealName().length() == 0)) {
                hqlList.append(SystemConstant.USERINFO_END_HQL);
                hqlCount.append(SystemConstant.USERINFO_END_HQL);
            }
        } else if (searchFlag == 1) {
            if (!(searchValues.getSearchText() == null || searchValues.getSearchText() == "" || searchValues
                    .getSearchText().length() == 0)) {
                hqlList.append(SystemConstant.SIMPLEQUERY_HQL);
                hqlCount.append(SystemConstant.SIMPLEQUERY_HQL);
            }
        }
        List<UserInfoSearchBean> userResult = hibernateTemplate
                .executeFind(new SuperHibernateCallback(hqlList.toString(), currentPage,
                        searchValues, pageSize));
        long userTotal = (Long) hibernateTemplate.executeFind(
                new SuperHibernateCallback(hqlCount.toString(), 0, searchValues, pageSize)).get(0);
        map.put(CRMConstant.TOTAL, userTotal);
        map.put(CRMConstant.ITEMS, userResult);
        log.debug("method queryUser end!");
        return map;
    }

    /**
     * judge if UserName existed
     * 
     * @param userName
     * @return type of boolean
     */
    @Override
    public boolean judgeUserName(String userName) {
        log.debug("method judgeUserName start!");
        long userCount = (Long) hibernateTemplate.findByNamedParam(
                SystemConstant.USER_USERNAME_COUNT_HQL, "userName", userName).get(0);
        log.debug("method judgeUserName end!");
        return !(userCount == 0);
    }

    /**
     * query userName by userID
     * 
     * @param userID
     * @return String userName
     */
    @Override
    public String getUserNameByID(int userID) {
        log.debug("method getUserNameByID start!");
        String queryName = "select userName from UserInfoDto where userID = :userID";
        String userName = (String) hibernateTemplate.findByNamedParam(queryName, "userID", userID)
                .get(0);
        log.debug("method getUserNameByID end!");
        return userName;
    }

    /**
     * find UserName By ID
     * 
     * @param userID
     * @return type of String
     */
    @Override
    public String findUserNameByUserID(int userID) {
        log.debug("method findUserNameByID start!");
        String userName = (String) hibernateTemplate.findByNamedParam(
                SystemConstant.FIND_USERNAME_HQL, "userID", userID).get(0);
        log.debug("method findUserNameByID end!");
        return userName;
    }

    /**
     * judge if JobID existed
     * 
     * @param jobID
     * @return type of boolean
     */
    @Override
    public boolean judgeJobID(String jobID) {
        log.debug("method judgeUserName start!");
        long userCount = (Long) hibernateTemplate.findByNamedParam(
                SystemConstant.USER_JOBID_COUNT_HQL, "jobID", jobID).get(0);
        log.debug("method judgeUserName end!");
        return !(userCount == 0);
    }

    /**
     * find JobID By ID
     * 
     * @param userID
     * @return String
     */
    @Override
    public String findJobIDByUserID(int userID) {
        log.debug("method findJobIDByID start!");
        String jobID = (String) hibernateTemplate.findByNamedParam(SystemConstant.FIND_JOBID_HQL,
                "userID", userID).get(0);
        log.debug("method findJobIDByID end!");
        return jobID;
    }

    /**
     * judge user is or isn't departmentManager
     * 
     * @param userID
     * @return boolean
     */
    public boolean userIsManager(int userID) {
        log.debug("method userIsManager start!");
        String hqlCount = "select count(*) from DepartmentDto as dmDto where dmDto.departmentManager=:userID";
        long total = (Long) hibernateTemplate.findByNamedParam(hqlCount, "userID", userID).get(0);
        log.debug("method userIsManager end!");
        return total != 0;
    }

    /**
     * judge user is or isn't userIsProjectTeamLeader
     * 
     * @param userID
     * @return boolean
     */
    public boolean userIsProjectTeamLeader(int userID) {
        log.debug("method userIsProjectTeamLeader start!");
        String hqlCount = "select count(*) from ProjectTeamDto as ptDto where ptDto.projectTeamLeaderID=:userID";
        long total = (Long) hibernateTemplate.findByNamedParam(hqlCount, "userID", userID).get(0);
        log.debug("method userIsProjectTeamLeader end!");
        return total != 0;
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

    @Override
    public String getDepartmentNameByID(int departmentID) {

        return hibernateTemplate
                .find("select dto.departmentName from DepartmentDto as dto where dto.departmentID =: "
                        + departmentID + "").get(0).toString();
    }
}
