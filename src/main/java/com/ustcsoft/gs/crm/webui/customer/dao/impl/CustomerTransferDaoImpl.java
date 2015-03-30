package com.ustcsoft.gs.crm.webui.customer.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.util.PagingHibernateCallback;
import com.ustcsoft.gs.crm.webui.common.util.SuperHibernateCallback;
import com.ustcsoft.gs.crm.webui.customer.bean.CustomerTrasferBean;
import com.ustcsoft.gs.crm.webui.customer.bean.TransferBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.customer.dao.CustomerTransferDao;
import com.ustcsoft.gs.crm.webui.system.dto.DepartmentDto;
import com.ustcsoft.gs.crm.webui.system.dto.ProjectTeamDto;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;

/**
 * 
 * @author tangyunpeng
 * 
 */
public class CustomerTransferDaoImpl implements CustomerTransferDao {

    private static final Log LOG = LogFactory.getLog(CustomerTransferDaoImpl.class);

    private HibernateTemplate hibernateTemplate = null;

    /**
     * @return the hibernateTemplate
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
	 * 
	 */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getCustomer(int searchFlag, int page, int limit) {
        LOG.debug("method getCustomer start");
        Map<String, Object> map = new HashMap<String, Object>();
        List<TransferBean> list = null;
        int total = 0;
        if (searchFlag == 2) {
            list = hibernateTemplate.executeFind(new PagingHibernateCallback(
                    CustomerConstant.CUS_TRANSFER_HQL, page, limit));
            total = (int) getSize(CustomerConstant.SELECT_COUNT_TARANSFER);

        } else {
            list = hibernateTemplate.executeFind(new PagingHibernateCallback(
                    CustomerConstant.SELECT_TRANSFER, page, limit));
            total = (int) getSize(CustomerConstant.SELECT_TRANSFER_COUNT);
        }
        map.put(CRMConstant.ITEMS, list);
        map.put(CRMConstant.TOTAL, total);
        LOG.debug("method getCustomer end");
        return map;
    }

    /**
     * 
     * @param departmentID
     * @param projectTeamID
     * @param userID
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getCustomer(int searchFlag, CustomerTrasferBean searchBean,
            int page, int limit) {
        LOG.debug("method getCustomer start");
        Map<String, Object> map = new HashMap<String, Object>();
        List<TransferBean> list = null;
        StringBuffer hqlList = null;
        StringBuffer hqlCount = null;
        long total = 0;
        if (searchFlag == 0) {
            hqlList = new StringBuffer(CustomerConstant.CUS_GET_ALL_HQL);
            hqlList.append(" and cus.holder in (:userIDs)");
            hqlCount = new StringBuffer(CustomerConstant.CUS_COUNT_BEFORE_HQL);
            hqlCount.append(" and cus.holder in(:userIDs)");
        }
        if (searchFlag == 2) {
            hqlList = new StringBuffer(CustomerConstant.CUS_GET_ALL_HQL);
            hqlList.append(" and cus.holder = 0");
            hqlCount = new StringBuffer(CustomerConstant.CUS_COUNT_BEFORE_HQL);
            hqlCount.append(" and cus.holder = 0");
        }
        if (searchFlag == 1) {
            hqlList = new StringBuffer(CustomerConstant.SELECT_TRANSFER);
            hqlCount = new StringBuffer(CustomerConstant.SELECT_TRANSFER_COUNT);

            if (searchBean.getUserID() != 0) {
                hqlList.append(" and user.userID = :userID");
                hqlCount.append(" and user.userID = :userID");
            } else if (searchBean.getDepartmentID() != 0 || searchBean.getProjectTeamID() != 0) {
                if (searchBean.getDepartmentID() != 0) {
                    hqlList.append(" and user.departmentID = :departmentID");
                    hqlCount.append(" and user.departmentID = :departmentID");
                } else if (searchBean.getProjectTeamID() != 0) {
                    hqlList.append(" and user.projectTeamID = :projectTeamID");
                    hqlCount.append(" and user.projectTeamID = :projectTeamID");
                }
            }
        }

        list = hibernateTemplate.executeFind(new SuperHibernateCallback(hqlList.toString(), page,
                searchBean, limit));
        total = (Long) hibernateTemplate.executeFind(
                new SuperHibernateCallback(hqlCount.toString(), 0, searchBean, 0)).get(0);

        map.put(CRMConstant.ITEMS, list);
        map.put(CRMConstant.TOTAL, total);
        LOG.debug("method getCustomer end");
        return map;
    }

    /**
     * 
     * @param customerIDs
     * @param userID
     */
    @Override
    public void saveTransfer(String customerIDs, int userID) {

        hibernateTemplate.bulkUpdate(CustomerConstant.UPDATE_CUS_TRANSFER
                + CRMConstant.LEFT_PARENTHESIS + customerIDs + CRMConstant.RIGHT_PARENTHESIS,
                userID);
        hibernateTemplate.bulkUpdate(CustomerConstant.UPDATE_WORK + customerIDs
                + CRMConstant.RIGHT_PARENTHESIS, userID);
    }

    /**
     * get the number of records.
     * 
     * @param queryString
     * @return long
     */
    private long getSize(String queryString) {
        List<?> result = hibernateTemplate.find(queryString);
        return (Long) result.get(0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getDepartment() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<DepartmentDto> list = hibernateTemplate.find(CustomerConstant.SELECT_DEPARTMENT);
        map.put(CRMConstant.ITEMS, list);
        return map;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getProjectTeam(int userID) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<ProjectTeamDto> list = hibernateTemplate.find(
                "from ProjectTeamDto ptd where ptd.projectTeamLeaderID=?", userID);
        map.put(CRMConstant.ITEMS, list);
        return map;
    }

    /**
     * get users by logined user
     * 
     * @param queryDepartmentID
     * @param queryProjectTeamID
     * @param userDto
     *            userDto
     * @return Map<String,Object> map
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getUser(int queryDepartmentID, int queryProjectTeamID,
            UserInfoDto userDto) {
        LOG.debug("method getUser start!");
        int departmentID = userDto.getDepartmentID();
        int projectTeamID = userDto.getProjectTeamID();
        int groupID = userDto.getGroupID();
        int userID = userDto.getUserID();
        Map<String, Object> map = new HashMap<String, Object>();
        List<UserInfoDto> list = null;
        List<Integer> departmentIDs = new ArrayList<Integer>();
        departmentIDs.add(departmentID);
        StringBuffer hqlList = new StringBuffer(CustomerConstant.SELECT_USER_INFO);

        if (groupID != 1) {
            boolean isManager = userIsManager(userID);
            boolean isLeader = userIsProjectTeamLeader(userID);
            // 用户不是部长也不是团队负责人
            if (!isManager && !isLeader) {
                departmentIDs = getChildDepartment(departmentIDs);
                departmentIDs.remove(0);
                if (departmentIDs.size() != 0) {
                    hqlList.append(CustomerConstant.USER_NOTMANAGER_NOTLEADER);
                    String[] params = { "userID", "departmentIDs" };
                    Object[] values = { userID, departmentIDs };
                    list = hibernateTemplate.findByNamedParam(hqlList.toString(), params, values);
                } else {
                    hqlList.append(" and user.userID=:userID");
                    list = hibernateTemplate.findByNamedParam(hqlList.toString(), "userID", userID);
                }
                // 用户是部长，不是团队负责人
            } else if (isManager && !isLeader) {
                departmentIDs = getChildDepartment(departmentIDs);
                hqlList.append(CustomerConstant.USER_MANAGER_NOTLEADER);
                list = hibernateTemplate.findByNamedParam(hqlList.toString(), "departmentIDs",
                        departmentIDs);
                // 用户不是部长，是团队负责人
            } else if (!isManager && isLeader) {
                departmentIDs = getChildDepartment(departmentIDs);
                departmentIDs.remove(0);
                if (departmentIDs.size() != 0) {
                    String[] params = { "userID", "departmentIDs", "projectTeamID" };
                    Object[] values = { userID, departmentIDs, projectTeamID };
                    hqlList.append(CustomerConstant.USER_NOTMANAGER_LEADER);
                    list = hibernateTemplate.findByNamedParam(hqlList.toString(), params, values);
                } else {
                    String[] params = { "userID", "projectTeamID" };
                    Object[] values = { userID, projectTeamID };
                    hqlList.append(" and (user.userID = :userID or user.projectTeamID =:projectTeamID)");
                    list = hibernateTemplate.findByNamedParam(hqlList.toString(), params, values);
                }
                // 用户是部长，又是团队负责人
            } else if (isManager && isLeader) {
                departmentIDs = getChildDepartment(departmentIDs);
                String[] params = { "departmentIDs", "projectTeamID" };
                Object[] values = { departmentIDs, projectTeamID };
                hqlList.append(CustomerConstant.USER_MANAGER_LEADER);
                list = hibernateTemplate.findByNamedParam(hqlList.toString(), params, values);
            }
        } else {
            list = hibernateTemplate.find(hqlList.toString());
        }
        if (queryDepartmentID == -1) {
            int flag = 0;
            for (int i = 0; i < list.size(); i++) {
                flag = 0;
                for (Integer integer : departmentIDs) {
                    if (list.get(i).getDepartmentID() == integer) {
                        flag = 1;
                    }
                }
                if (flag == 0) {
                    list.remove(i--);
                }
            }
            if (!userIsManager(userDto.getUserID())) {
                list.add(userDto);
            }
        }
        if (queryDepartmentID != 0 && queryDepartmentID != -1) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getDepartmentID() != queryDepartmentID) {
                    list.remove(i--);
                }
            }
        }
        if (queryProjectTeamID != 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getProjectTeamID() != queryProjectTeamID) {
                    list.remove(i--);
                }
            }
        }
        map.put(CRMConstant.ITEMS, list);
        LOG.debug("method getUser end!");
        return map;
    }

    /**
     * get children department
     * 
     * @param departmentIDs
     * @return departmentIDs
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<Integer> getChildDepartment(List<Integer> departmentIDs) {
        LOG.debug("method getChildDepartment start!");
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
        LOG.debug("method getChildDepartment end!");
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
        LOG.debug("method getChild start!");
        String hqlString = "select departmentID from DepartmentDto where fatherDepartmentID in(:departmentIDs)";
        list = hibernateTemplate.findByNamedParam(hqlString, "departmentIDs", list);
        LOG.debug("method getChild end!");
        return list;
    }

    /**
     * judge user is or isn't departmentManager
     * 
     * @param userID
     * @return boolean
     */
    public boolean userIsManager(int userID) {
        LOG.debug("method userIsManager start!");
        String hqlCount = "select count(*) from DepartmentDto as dmDto where dmDto.departmentManager=:userID";
        long total = (Long) hibernateTemplate.findByNamedParam(hqlCount, "userID", userID).get(0);
        LOG.debug("method userIsManager end!");
        return total != 0;
    }

    /**
     * judge user is or isn't departmentManager
     * 
     * @param userID
     * @return boolean
     */
    public boolean userIsProjectTeamLeader(int userID) {
        LOG.debug("method userIsProjectTeamLeader start!");
        String hqlCount = "select count(*) from ProjectTeamDto as ptDto where ptDto.projectTeamLeaderID=:userID";
        long total = (Long) hibernateTemplate.findByNamedParam(hqlCount, "userID", userID).get(0);
        LOG.debug("method userIsProjectTeamLeader end!");
        return total != 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getUserByProjectTeam(UserInfoDto userDto) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<UserInfoDto> list = null;
        if (userIsProjectTeamLeader(userDto.getUserID())) {
            String hqlCount = "from UserInfoDto as user where user.isAbolished = 0 and user.projectTeamID = :projectTeamID";
            list = hibernateTemplate.findByNamedParam(hqlCount, "projectTeamID",
                    userDto.getProjectTeamID());
        }
        map.put(CRMConstant.ITEMS, list);
        return map;
    }
}
