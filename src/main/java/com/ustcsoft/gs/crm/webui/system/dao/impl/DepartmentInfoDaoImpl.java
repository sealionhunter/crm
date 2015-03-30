package com.ustcsoft.gs.crm.webui.system.dao.impl;

import java.text.DecimalFormat;
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
import com.ustcsoft.gs.crm.webui.system.bean.DepartmentInfoSearchBean;
import com.ustcsoft.gs.crm.webui.system.constant.SystemConstant;
import com.ustcsoft.gs.crm.webui.system.dao.DepartmentInfoDao;
import com.ustcsoft.gs.crm.webui.system.dto.DepartmentDto;

/**
 * 
 * @author maxue
 * 
 */
public class DepartmentInfoDaoImpl implements DepartmentInfoDao {
    private static final Log log = LogFactory.getLog(DepartmentInfoDaoImpl.class);
    private HibernateTemplate hibernateTemplate;

    /**
     * @return hibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    /**
     * @param hibernateTemplate
     *            the hibnernateTemplate to set
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /**
     * query department
     * 
     * @param searchFlag
     * @param searchBean
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
    public Map<String, Object> queryDepartment(int departmentID, int searchFlag,
            DepartmentInfoSearchBean searchBean, int currPage, int pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer hqlList = new StringBuffer(SystemConstant.DEPARTMENT_GET_ALL_HQL);
        StringBuffer hqlCount = new StringBuffer(SystemConstant.QUERY_COUNT_HQL);
        Integer fatherDepartmentID = searchBean.getFatherDepartmentID();
        int userID = searchBean.getUserID();
        int groupID = searchBean.getGroupID();
        searchBean.getDepartmentIDs().add(departmentID);
        if (groupID > 1) {
            searchBean.setDepartmentIDs(getChildDepartment(searchBean.getDepartmentIDs()));
            hqlList.append(" and dmDto.departmentID in(:departmentIDs)");
            hqlCount.append(" and dmDto.departmentID in(:departmentIDs)");
        }
        if (!userIsManager(userID)) {
            searchBean.setLoginDepartmentID(departmentID);
            hqlList.append(" and dmDto.departmentID != :loginDepartmentID ");
            hqlCount.append(" and dmDto.departmentID != :loginDepartmentID ");
        }
        if (searchFlag == 1) {
            if (searchBean.getSearchText() != null && searchBean.getSearchText().length() != 0) {
                hqlList.append(SystemConstant.SIMPLE_QUERY_HQL);
                hqlCount.append(SystemConstant.SIMPLE_QUERY_HQL);
            }
        } else if (searchFlag == 2) {
            if (fatherDepartmentID != 0) {
                hqlList.append(SystemConstant.QUERY_FATHERDEPARTNAME_HQL);
                hqlCount.append(SystemConstant.QUERY_FATHERDEPARTNAME_HQL);
            }
            if (searchBean.getDepartmentManager() != null
                    && searchBean.getDepartmentManager().length() != 0) {
                hqlList.append(SystemConstant.QUERY_MANEGER_HQL);
                hqlCount.append(SystemConstant.QUERY_MANEGER_HQL);
            }
            if (searchBean.getDepartmentName() != null
                    && searchBean.getDepartmentName().length() != 0) {
                hqlList.append(SystemConstant.QUERY_DEPARTMENTNAME_HQL);
                hqlCount.append(SystemConstant.QUERY_DEPARTMENTNAME_HQL);
            }
        }

        List<DepartmentDto> list = hibernateTemplate.executeFind(new SuperHibernateCallback(hqlList
                .toString(), currPage, searchBean, pageSize));
        long total = (Long) hibernateTemplate.executeFind(
                new SuperHibernateCallback(hqlCount.toString(), 0, searchBean, pageSize)).get(0);
        map.put(CRMConstant.TOTAL, total);
        map.put(CRMConstant.ITEMS, list);

        return map;
    }

    /**
     * update or add departmentInfo
     * 
     * @param departmentDto
     * @throws DataAccessException
     *             in case of DataAccessException
     * @throws NumberFormatException
     *             in case of NumberFormatException
     */
    @Override
    public void updateDepartment(DepartmentDto departmentDto) {
        log.debug("method updateDepartment start!");
        getHibernateTemplate().saveOrUpdate(departmentDto);

        DecimalFormat df = new DecimalFormat("0000");
        departmentDto.setSerialNumber(df.format(departmentDto.getDepartmentID()));
        getHibernateTemplate().update(departmentDto);

        String queryString = "update UserInfoDto set departmentID=? where userID=?";
        Object[] values = new Object[] { departmentDto.getDepartmentID(),
                departmentDto.getDepartmentManager() };
        getHibernateTemplate().bulkUpdate(queryString, values);
        log.debug("method updateDepartment end!");
    }

    /**
     * get depth from DB
     * 
     * @param departmentDto
     * @return depth
     * @throws DataAccessException
     *             in case of DataAccessException
     * @throws NumberFormatException
     *             in case of NumberFormatException
     */
    @Override
    @SuppressWarnings("unchecked")
    public int getDepthByID(DepartmentDto departmentDto) {
        log.debug("method getDepthByID start!");
        int depth = 0;
        List<Integer> list = hibernateTemplate.findByNamedParam(SystemConstant.GET_DEPTH_HQL,
                "departmentID", departmentDto.getFatherDepartmentID());
        if (list.size() != 0) {
            depth = list.get(0) + 1;
        }
        log.debug("method getDepthByID end!");
        return depth;
    }

    /**
     * delete department
     * 
     * @param departmentIDs
     * @throws DataAccessException
     *             in case of DataAccessException
     * @throws NumberFormatException
     *             in case of NumberFormatException
     */
    @Override
    public void deleteDepartment(String departmentIDs) {
        log.debug("method deleteDepartment start.");
        getHibernateTemplate().bulkUpdate(SystemConstant.DEPARTMENT_DELETE_HQL + departmentIDs);
        getHibernateTemplate().bulkUpdate(
                SystemConstant.UPDATE_USER_AFTERDELETE_HQL + departmentIDs);
        getHibernateTemplate().bulkUpdate(
                SystemConstant.UPDATE_TEAM_AFTERDELETE_HQL + departmentIDs);
        log.debug("method deleteDepartment end.");
    }

    /**
     * get fatherDepartment
     * 
     * @userID
     * @param groupID
     * @param loginDepartmentID
     * @param departmentID
     * @return map
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getFatherDepartment(int userID, int groupID, int loginDepartmentID,
            int departmentID) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Integer> departmentIDs = new ArrayList<Integer>();
        departmentIDs.add(loginDepartmentID);
        departmentIDs = getChildDepartment(departmentIDs);
        List<?> list = null;
        StringBuffer hqlList = new StringBuffer(SystemConstant.GET_FATHERDEPARTMENT_HQL);
        if (userIsManager(userID) || groupID == 1) {
            list = getHibernateTemplate().findByNamedParam(hqlList.toString(), "departmentIDs",
                    departmentIDs);
        } else {
            hqlList.append(" and dmDto.departmentID!=:loginDepartmentID");
            String[] params = { "departmentIDs", "loginDepartmentID" };
            Object[] values = { departmentIDs, loginDepartmentID };
            list = getHibernateTemplate().findByNamedParam(hqlList.toString(), params, values);
        }

        if (departmentID != 0) {
            List<Integer> childList = new ArrayList<Integer>();
            childList.add(departmentID);
            childList = getChildDepartment(childList);

            for (Integer i = 0; i < childList.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    String aString = String.valueOf(((Map<String, Object>) list.get(j))
                            .get("departmentID"));
                    String bString = String.valueOf(childList.get(i));
                    if (aString.equals(bString)) {
                        list.remove(j);
                    }
                }
            }
        }

        map.put(CRMConstant.ITEMS, list);
        return map;
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
     * get departmentManager
     * 
     * @param departmentID
     * @return map
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getDepartmentManager(int departmentID) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<DepartmentDto> list = getHibernateTemplate().findByNamedParam(
                SystemConstant.GET_DEPARTMENTMANAGER_HQL, "departmentID", departmentID);
        map.put(CRMConstant.ITEMS, list);
        return map;
    }

    /**
     * check the department whether to delete
     * 
     * @param departmentIDs
     * @return boolean
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean checkDelete(String departmentIDs) {
        log.debug("method checkDelete start!.");
        List<String> departmentNames = hibernateTemplate.find(SystemConstant.CHECK_DELETE_HQL
                + departmentIDs);
        log.debug("method checkDelete end!.");
        return departmentNames.size() == 0;
    }

    /**
     * judge departmentName is existed
     * 
     * @param departmentName
     * @return boolean
     */
    @Override
    public boolean judgeDepartmentName(String departmentName) {
        log.debug("method judgeDepartmentName start!");
        long departmentCount = (Long) hibernateTemplate.findByNamedParam(
                SystemConstant.DEPARTMENTNAME_COUNT_HQL, "departmentName", departmentName).get(0);
        log.debug("method judgeDepartmentName end!");
        return departmentCount != 0;
    }

    /**
     * find departmentName By departmentID
     * 
     * @param departmentID
     * @return departmentName
     */
    @Override
    @SuppressWarnings("unchecked")
    public String findDepartmentNameByID(int departmentID) {
        log.debug("method findDepartmentNameByID start!");
        String departmentName = null;
        List<String> list = hibernateTemplate.findByNamedParam(
                SystemConstant.FIND_DEPARTMENTNAME_HQL, "departmentID", departmentID);
        if (list.size() != 0) {
            departmentName = list.get(0);
        }
        log.debug("method findDepartmentNameByID end!");
        return departmentName;
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

    /**
     * get departmentName by departmentID
     * 
     * @param departmentID
     * @return type of String
     */
    @SuppressWarnings("unchecked")
    @Override
    public String getDepartmentName(int departmentID) {

        System.out.println(1);
        System.out.println(departmentID);
        String departmentName = null;
        List<String> list = hibernateTemplate
                .findByNamedParam(
                        "select dd.departmentName from DepartmentDto dd where dd.departmentID=:departmentID",
                        "departmentID", departmentID);
        // System.out.println(departmentDtos);
        // if (departmentDtos.size() != 0) {
        // System.out.println(2);
        // // return departmentDtos.get(0);
        //
        // }
        if (list.size() != 0) {
            departmentName = list.get(0);
        }
        return departmentName;
    }
}
