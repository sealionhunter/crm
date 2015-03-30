/*
 * Class name: ProjectTeamDaoImpl
 * 
 * Version information: 1.0
 * 
 * Date:2013.10.22
 *  
 */
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
import com.ustcsoft.gs.crm.webui.system.bean.ProjectTeamSearchBean;
import com.ustcsoft.gs.crm.webui.system.bean.UserInfoSearchBean;
import com.ustcsoft.gs.crm.webui.system.constant.SystemConstant;
import com.ustcsoft.gs.crm.webui.system.dao.ProjectTeamDao;
import com.ustcsoft.gs.crm.webui.system.dto.ProjectTeamDto;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;

/**
 * ProjectTeamDaoImpl class implements ProjectTeamDao
 * 
 * @author wangzhanxu
 * 
 */
public class ProjectTeamDaoImpl implements ProjectTeamDao {
    /** get LOG */
    private static final Log LOG = LogFactory.getLog(ProjectTeamDaoImpl.class);
    /** get hibernateTemplate */
    private HibernateTemplate hibernateTemplate = null;

    /**
     * get hibernateTemplate
     * 
     * @return hibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    /**
     * set hibernateTemplate
     * 
     * @param hibernateTemplate
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
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
        LOG.debug("convertStringIDs method start!");
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
        LOG.debug("convertStringIDs method end!");
        return resultIDs;
    }

    /**
     * get projectTeam members
     * 
     * @param searchBean
     * @param currPage
     * @param pageSize
     * @return memberMap
     * @throws DataAccessException
     *             in case of DataAccessException
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getProjectTeamMembers(UserInfoSearchBean searchBean, int currPage,
            int pageSize) throws DataAccessException {
        LOG.debug("getProjectTeamMembers method start!");
        StringBuffer queryString = new StringBuffer(SystemConstant.GET_PROJECTTEAM_MEMBERS);
        StringBuffer queryCntString = new StringBuffer(SystemConstant.PROJECTTEAMMEMBERS_COUNT_HQL);
        queryString
                .append(" and (uid.userName like:searchText or uid.realName like:searchText or uid.jobID like:searchText)");
        queryCntString
                .append(" and (uid.userName like:searchText or uid.realName like:searchText or uid.jobID like:searchText)");
        Map<String, Object> memberMap = new HashMap<String, Object>();
        // query members
        List<UserInfoDto> memberList = hibernateTemplate.executeFind(new SuperHibernateCallback(
                queryString.toString(), currPage, searchBean, pageSize));
        // total count for page show
        long total = (Long) hibernateTemplate.executeFind(
                new SuperHibernateCallback(queryCntString.toString(), 0, searchBean, pageSize))
                .get(0);
        memberMap.put(CRMConstant.TOTAL, total);
        memberMap.put(CRMConstant.ITEMS, memberList);
        LOG.debug("getProjectTeamMembers method end!");
        return memberMap;
    }

    /**
     * get projectTeam according to search condition
     * 
     * @param searchBean
     * @param currPage
     * @param pageSize
     * @return map
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getProjectTeam(ProjectTeamSearchBean searchBean, int currPage,
            int pageSize) throws DataAccessException {
        LOG.debug("getProjectTeam method end!");
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer(SystemConstant.GET_PROJECTTEAM);
        StringBuffer queryCntString = new StringBuffer(SystemConstant.PROJECTTEAM_COUNT);
        if (searchBean.getDepartmentIDs() != null) {
            queryString.append(" and ptd.departmentID in(:departmentIDs)");
            queryCntString.append(" and ptd.departmentID in(:departmentIDs)");
        }
        if (!"%".equals(searchBean.getProjectTeamName())) {
            queryString.append(" and ptd.projectTeamName like :projectTeamName");
            queryCntString.append(" and ptd.projectTeamName like :projectTeamName");
        }
        List<Map<String, Object>> projectTeamList = hibernateTemplate
                .executeFind(new SuperHibernateCallback(queryString.toString(), currPage,
                        searchBean, pageSize));
        long total = (Long) hibernateTemplate.executeFind(
                new SuperHibernateCallback(queryCntString.toString(), 0, searchBean, pageSize))
                .get(0);
        map.put(CRMConstant.ITEMS, projectTeamList);
        map.put("total", total);
        LOG.debug("getProjectTeam method end!");
        return map;
    }

    /**
     * get other projectTeam or no projectTeam members
     * 
     * @param searchBean
     * @param currPage
     * @param pageSize
     * @return memberMap
     * @throws DataAccessException
     *             in case of DataAccessException
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getOtherMembers(UserInfoSearchBean searchBean, int currPage,
            int pageSize) throws DataAccessException {
        LOG.debug("getOtherMembers method start!");
        Map<String, Object> memberMap = new HashMap<String, Object>();
        int groupID = (Integer) hibernateTemplate.find(
                "select uid.groupID from UserInfoDto uid where uid.isAbolished=0 and uid.userID="
                        + searchBean.getUserID()).get(0);
        List<Integer> userIDs = null;
        if (groupID == 1) {
            userIDs = hibernateTemplate
                    .find("select uid.userID from UserInfoDto uid where uid.groupID>1 and uid.isAbolished=0");
        } else {
            List<Integer> departmentIDs = hibernateTemplate
                    .find("select uid.departmentID from UserInfoDto uid where uid.isAbolished=0 and uid.userID="
                            + searchBean.getUserID());
            Integer loginerDepartmentID = departmentIDs.get(0);
            departmentIDs = getChildDepartment(departmentIDs);
            if (!userIsManager(searchBean)) {
                departmentIDs.remove(loginerDepartmentID);
            }
            userIDs = hibernateTemplate
                    .findByNamedParam(
                            "select uid.userID from UserInfoDto uid where uid.isAbolished=0 and uid.departmentID in(:departmentIDs)",
                            "departmentIDs", departmentIDs);
            userIDs.add(searchBean.getUserID());
        }
        List<Integer> projectTeamLeaderIDs = hibernateTemplate
                .find("select ptd.projectTeamLeaderID from ProjectTeamDto ptd");
        userIDs.removeAll(projectTeamLeaderIDs);
        String uIDs = "";
        for (Integer i : userIDs) {
            uIDs = uIDs + i + ",";
        }
        if (!uIDs.isEmpty()) {
            uIDs = uIDs.substring(0, uIDs.length() - 1);
        } else {
            memberMap.put(CRMConstant.TOTAL, 0);
            memberMap.put(CRMConstant.ITEMS, new ArrayList<UserInfoDto>());
            return memberMap;
        }
        StringBuffer queryString = new StringBuffer(SystemConstant.GET_OTHER_PROJECTTEAM_MEMBERS
                + " and uid.userID in(" + uIDs + ")");
        StringBuffer queryCntString = new StringBuffer(
                SystemConstant.OTHER_PROJECTTEAMMEMBERS_COUNT_HQL + " and uid.userID in(" + uIDs
                        + ")");
        queryString
                .append(" and (uid.userName like :searchText or uid.realName like :searchText or uid.jobID like :searchText)");
        queryCntString
                .append(" and (uid.userName like :searchText or uid.realName like :searchText or uid.jobID like :searchText)");
        // query members
        List<UserInfoDto> memberList = hibernateTemplate.executeFind(new SuperHibernateCallback(
                queryString.toString(), currPage, searchBean, pageSize));
        // total count for page show
        long total = (Long) hibernateTemplate.executeFind(
                new SuperHibernateCallback(queryCntString.toString(), 0, searchBean, pageSize))
                .get(0);
        memberMap.put(CRMConstant.TOTAL, total);
        memberMap.put(CRMConstant.ITEMS, memberList);
        LOG.debug("getOtherMembers method end!");
        return memberMap;
    }

    /**
     * add projectTeam members
     * 
     * @param addIDs
     * @param projectTeamID
     * @throws DataAccessException
     *             in case of DataAccessException
     * @throws NumberFormatException
     *             in case of NumberFormatException
     */
    @SuppressWarnings("unchecked")
    @Override
    public void addMembers(String addIDs, int projectTeamID) throws DataAccessException,
            NumberFormatException {
        LOG.debug("addMembers method start!");
        // convert addIDs to array
        List<Integer> resultIDs = convertStringIDs(addIDs);
        List<UserInfoDto> userInfoList = hibernateTemplate.findByNamedParam(
                SystemConstant.ADD_PROJECTTEAM_MEMBERS, "userID", resultIDs);
        if (userInfoList.size() != 0) {
            for (UserInfoDto userInfo : userInfoList) {
                userInfo.setProjectTeamID(projectTeamID);
            }
            hibernateTemplate.saveOrUpdateAll(userInfoList);
        }
        LOG.debug("addMembers method end!");
    }

    /**
     * remove projectTeam members
     * 
     * @param removeIDs
     * @param projectTeamID
     * @return removeResult
     * @throws DataAccessException
     * @throws NumberFormatException
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> removeMembers(String removeIDs, int projectTeamID)
            throws DataAccessException, NumberFormatException {
        LOG.debug("removeMembers method start!");
        Map<String, Object> removeResult = new HashMap<String, Object>();
        List<Integer> resultIDs = convertStringIDs(removeIDs);
        Integer id = (Integer) hibernateTemplate.find(
                "select ptd.projectTeamLeaderID from ProjectTeamDto ptd where ptd.projectTeamID="
                        + projectTeamID).get(0);
        if (resultIDs.contains(id)) {
            resultIDs.remove(id);
            removeResult.put("leaderID", id);
        }
        if (resultIDs.isEmpty()) {
            removeResult.put("ok", false);
            removeResult.put("leaderID", id);
            return removeResult;
        }
        String[] paramNames = new String[] { "projectTeamID", "userID" };
        Object[] values = new Object[] { projectTeamID, resultIDs };
        List<UserInfoDto> userInfoList = hibernateTemplate.findByNamedParam(
                SystemConstant.REMOVE_PROJECTTEAM_MEMBERS, paramNames, values);
        if (userInfoList.size() != 0) {
            for (UserInfoDto userInfo : userInfoList) {
                userInfo.setProjectTeamID(-1);
            }
            hibernateTemplate.saveOrUpdateAll(userInfoList);
        }
        LOG.debug("removeMembers method end!");
        removeResult.put("ok", true);
        return removeResult;
    }

    /**
     * insert or update projectTeam
     * 
     * @param projectTeamDto
     * @throws DataAccessException
     */
    @Override
    public void saveOrUpdateProjectTeam(ProjectTeamDto projectTeamDto) throws DataAccessException {
        hibernateTemplate.saveOrUpdate(projectTeamDto);
        hibernateTemplate.bulkUpdate("update UserInfoDto uid set uid.projectTeamID="
                + projectTeamDto.getProjectTeamID() + " where uid.userID="
                + projectTeamDto.getProjectTeamLeaderID());
    }

    /**
     * delete projetTeam according to their IDs
     * 
     * @param receivedIDs
     * @throws DataAccessException
     */
    @Override
    public void deleteProjectTeam(String receivedIDs) throws DataAccessException {
        hibernateTemplate
                .bulkUpdate("delete from ProjectTeamDto as ptd where ptd.projectTeamID in("
                        + receivedIDs + ")");
        hibernateTemplate
                .bulkUpdate("update UserInfoDto as uid set projectTeamID=-1 where uid.projectTeamID in("
                        + receivedIDs + ")");
    }

    /**
     * get projectTeam leader selecting users
     * 
     * @param searchBean
     * @return map
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getTeamLeaderSelecting(UserInfoSearchBean searchBean)
            throws DataAccessException {
        LOG.debug("getTeamLeaderSelecting method start!");
        int loginerID = searchBean.getUserID();
        int groupID = (Integer) hibernateTemplate.find(
                "select uid.groupID from UserInfoDto uid where uid.isAbolished=0 and uid.userID="
                        + loginerID).get(0);
        List<Integer> userIDs = null;
        if (groupID == 1) {
            userIDs = hibernateTemplate
                    .find("select uid.userID from UserInfoDto uid where uid.groupID>1 and uid.isAbolished=0");
        } else {
            List<Integer> departmentIDs = hibernateTemplate
                    .find("select uid.departmentID from UserInfoDto uid where uid.isAbolished=0 and uid.userID="
                            + loginerID);
            Integer loginerDepartmentID = departmentIDs.get(0);
            departmentIDs = getChildDepartment(departmentIDs);
            if (!userIsManager(searchBean)) {
                departmentIDs.remove(loginerDepartmentID);
                if (!departmentIDs.isEmpty()) {
                    userIDs = hibernateTemplate
                            .findByNamedParam(
                                    "select uid.userID from UserInfoDto uid where uid.isAbolished=0 and uid.departmentID in(:departmentIDs)",
                                    "departmentIDs", departmentIDs);
                } else {
                    userIDs = new ArrayList<Integer>();
                }
                userIDs.add(loginerID);
            } else {
                userIDs = hibernateTemplate
                        .findByNamedParam(
                                "select uid.userID from UserInfoDto uid where uid.isAbolished=0 and uid.departmentID in(:departmentIDs)",
                                "departmentIDs", departmentIDs);
            }
        }
        List<Integer> projectTeamLeaderIDs = hibernateTemplate
                .find("select ptd.projectTeamLeaderID from ProjectTeamDto ptd");
        userIDs.removeAll(projectTeamLeaderIDs);
        String uIDs = "";
        for (int i : userIDs) {
            uIDs = uIDs + i + ",";
        }
        if (!uIDs.isEmpty()) {
            uIDs = uIDs.substring(0, uIDs.length() - 1);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        // List<HashMap<String, Object>> userList = null;
        List<Map<String, Object>> userList = null;
        if (uIDs.isEmpty()) {
            userList = new ArrayList<Map<String, Object>>();
        } else {
            StringBuffer hql = new StringBuffer(
                    "select distinct new Map (uid.userID as userID, uid.realName as realName, uid.jobID as jobID) from UserInfoDto as uid where uid.isAbolished=0 and uid.groupID>1 and uid.userID in("
                            + uIDs + ")");
            userList = hibernateTemplate.find(hql.toString());
        }
        Map<String, Object> codeUserMap = null;
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> hMap : userList) {
            codeUserMap = new HashMap<String, Object>();
            codeUserMap.put("projectTeamLeaderID", hMap.get("userID"));
            codeUserMap.put("projectTeamLeaderName", hMap.get("realName") + "(" + hMap.get("jobID")
                    + ")");
            mapList.add(codeUserMap);
        }
        map.put("items", mapList);
        LOG.debug("getTeamLeaderSelecting method end!");
        return map;
    }

    /**
     * judge user is or isn't a departmentManager
     * 
     * @param searchBean
     * @return boolean
     */
    private boolean userIsManager(UserInfoSearchBean searchBean) {
        LOG.debug("userIsManager method start!");
        String hqlCount = "select count(*) from DepartmentDto as dmDto where dmDto.departmentManager=:userID";
        long total = (Long) hibernateTemplate.findByNamedParam(hqlCount, "userID",
                searchBean.getUserID()).get(0);
        LOG.debug("userIsManager method end!");
        return total != 0;
    }

    /**
     * get children department
     * 
     * @param departmentIDs
     * @return departmentIDs
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private List<Integer> getChildDepartment(List<Integer> departmentIDs) {
        LOG.debug("getChildDepartment method start!");
        List<Integer> list = departmentIDs;
        int sizea = 0;
        int sizeb = -1;
        while (sizea != sizeb) {
            list = getChild(list);
            sizea = departmentIDs.size();
            departmentIDs.addAll(list);
            HashSet h = new HashSet(departmentIDs);
            departmentIDs.clear();
            departmentIDs.addAll(h);
            sizeb = departmentIDs.size();
        }
        LOG.debug("getChildDepartment method end!");
        return departmentIDs;
    }

    /**
     * get list's children while list is not null
     * 
     * @param list
     * @return list
     */
    @SuppressWarnings("unchecked")
    private List<Integer> getChild(List<Integer> list) {
        LOG.debug("getChild method start!");
        String hqlString = "select departmentID from DepartmentDto where fatherDepartmentID in(:departmentIDs)";
        list = hibernateTemplate.findByNamedParam(hqlString, "departmentIDs", list);
        LOG.debug("getChild method end!");
        return list;
    }

    /**
     * judge projectTeam whether exist
     * 
     * @param projectTeamName
     * @return boolean
     * @throws DataAccessException
     */
    @Override
    public boolean judgeProjectTeamExistByName(String projectTeamName) throws DataAccessException {
        long cnt = (Long) hibernateTemplate.find(
                "select count(*) from ProjectTeamDto as ptd where ptd.projectTeamName=?",
                projectTeamName).get(0);
        return cnt > 0;
    }

    /**
     * get projectTeam status
     * 
     * @return map
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getProjectTeamStatus() throws DataAccessException {
        LOG.debug("getProjectTeamStatus method start!");
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> projectTeamStatusList = hibernateTemplate
                .find("select new Map (cd.code as projectTeamStatusCode, cd.value as projectTeamStatusValue) from CodeDto as cd where cd.code>='001000010001' and cd.code<='001000019999'");
        map.put("items", projectTeamStatusList);
        LOG.debug("getProjectTeamStatus method end!");
        return map;
    }

    /**
     * find projectTeam ID by name
     * 
     * @param projectTeamName
     * @return projectTeamIDs
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Integer> findProjectTeamIDByName(String projectTeamName) throws DataAccessException {
        LOG.debug("findProjectTeamIDByName method start!");
        List<Integer> projectTeamIDs = hibernateTemplate.find(
                "select ptd.projectTeamID from ProjectTeamDto as ptd where ptd.projectTeamName=?",
                projectTeamName);
        LOG.debug("findProjectTeamIDByName method end!");
        return projectTeamIDs;
    }
}
