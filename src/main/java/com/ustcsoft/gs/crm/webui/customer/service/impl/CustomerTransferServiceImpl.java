package com.ustcsoft.gs.crm.webui.customer.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.CustomerTrasferBean;
import com.ustcsoft.gs.crm.webui.customer.dao.CustomerTransferDao;
import com.ustcsoft.gs.crm.webui.customer.service.CustomerTransferService;
import com.ustcsoft.gs.crm.webui.system.dao.MainDao;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;

public class CustomerTransferServiceImpl implements CustomerTransferService {

    private static Log LOG = LogFactory.getLog(CustomerTransferServiceImpl.class);
    private CustomerTransferDao transferDao = null;
    private MainDao mainDao = null;

    /**
     * @return the transferDao
     */
    public CustomerTransferDao getTransferDao() {
        return transferDao;
    }

    /**
     * @param transferDao
     *            the transferDao to set
     */
    public void setTransferDao(CustomerTransferDao transferDao) {
        this.transferDao = transferDao;
    }

    /**
     * method to get all customer records for transfer
     * 
     * @param searchFlag
     *            search mark
     * @param page
     *            current page
     * @param departmentID
     *            customer holder's department
     * @param projectTeamID
     *            customer holder's projectTeam
     * @param userID
     *            customer holder
     * @param limit
     *            pageSize
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getCustomerForTransfer(int searchFlag, int loginUser_ID,
            int departmentID, int projectTeamID, int userID, int page, int limit)
            throws CRMDBException {
        LOG.debug("method getCustomerForTransfer start.");
        CustomerTrasferBean searchBean = new CustomerTrasferBean();
        Map<String, Object> map = null;
        List<UserInfoDto> list = (List<UserInfoDto>) getUser(0, 0, loginUser_ID).get("items");
        int size = list.size() + 1;
        Integer[] userIDs = new Integer[size];
        for (int i = 0; i < list.size(); i++) {
            userIDs[i] = list.get(i).getUserID();
        }
        userIDs[userIDs.length - 1] = 0;
        try {
            searchBean.setDepartmentID(departmentID);
            searchBean.setProjectTeamID(projectTeamID);
            searchBean.setUserID(userID);
            searchBean.setUserIDs(userIDs);
            map = transferDao.getCustomer(searchFlag, searchBean, page, limit);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getCustomerForTransfer.", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getCustomerForTransfer end.");
        return map;
    }

    /**
     * 
     * @param customerIDs
     * @param userID
     * @throws CRMDBException
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void saveTransfer(String customerIDs, int userID) throws CRMDBException {
        LOG.debug("method saveTransfer start.");
        try {
            transferDao.saveTransfer(customerIDs, userID);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method saveTransfer.", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method saveTransfer end.");

    }

    /**
     * get all department information
     * 
     * @return Map department information Collection
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    @Override
    public Map<String, Object> getDepartment() throws CRMDBException {
        LOG.debug("method getDepartment start.");
        Map<String, Object> map = null;
        try {
            map = transferDao.getDepartment();
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getDepartment.", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getDepartment start.");
        return map;
    }

    /**
     * get projectTeam information
     * 
     * @param userID
     * @return Map projectTeam information Collection
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    @Override
    public Map<String, Object> getProjectTeam(int userID) throws CRMDBException {
        LOG.debug("method getProjectTeam start.");
        Map<String, Object> map = null;
        try {
            map = transferDao.getProjectTeam(userID);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getProjectTeam.", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getProjectTeam end.");
        return map;
    }

    /**
     * get all user information
     * 
     * @param queryDepartmentID
     * @param queryProjectTeamID
     * @param userID
     * @return Map user information Collection
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    @Override
    public Map<String, Object> getUser(int queryDepartmentID, int queryProjectTeamID, int userID)
            throws CRMDBException {
        LOG.debug("method getUser start.");
        Map<String, Object> map = null;
        try {
            UserInfoDto userDto = getMainDao().findByUserID(userID);
            map = transferDao.getUser(queryDepartmentID, queryProjectTeamID, userDto);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getUser.", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getUser end.");
        return map;
    }

    public MainDao getMainDao() {
        return mainDao;
    }

    public void setMainDao(MainDao mainDao) {
        this.mainDao = mainDao;
    }

    @Override
    public Map<String, Object> getUserByProjectTeam(int userID) throws CRMDBException {
        LOG.debug("method getUser start.");
        Map<String, Object> map = null;
        try {
            UserInfoDto userDto = getMainDao().findByUserID(userID);
            map = transferDao.getUserByProjectTeam(userDto);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getUser.", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getUser end.");
        return map;
    }

}
