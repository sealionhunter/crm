/*
 * Class name: CustomerServiceImpl
 * 
 * Version information: 1.0
 * 
 * Date:2012.9.11
 *  
 */
package com.ustcsoft.gs.crm.webui.customer.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.customer.bean.CustomerSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dao.CustomerDao;
import com.ustcsoft.gs.crm.webui.customer.dto.CustomerDto;
import com.ustcsoft.gs.crm.webui.customer.service.CustomerService;
import com.ustcsoft.gs.crm.webui.customer.service.CustomerTransferService;
import com.ustcsoft.gs.crm.webui.customer.service.SourceInfoService;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;

/**
 * Processing and customer management relevant logic operation
 * 
 * @author tangyunpeng and xujueyin
 * 
 */
public class CustomerServiceImpl implements CustomerService {

    private static final Log LOG = LogFactory.getLog(CustomerServiceImpl.class);

    private CustomerDao customerDao = null;
    private SourceInfoService sourceInfoService = null;
    private CustomerTransferService transferService = null;

    /**
     * method to get all customer records or searched customer records
     * 
     * @param searchFlag
     *            search mark
     * @param searchValue
     *            search conditions
     * @param page
     *            search page
     * @param pageSize
     *            size of page
     * 
     * @param userID
     * 
     * @return Map<String, Object>
     * 
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> searchOrGetAllCustomer(int searchFlag,
            CustomerSearchBean searchValue, int page, int pageSize, int userID, boolean isGonghai)
            throws CRMDBException {
        LOG.debug("method searchOrGetAllCustomer start.");
        Map<String, Object> map = null;
        Integer[] userIDs = null;
        try {
            if (isGonghai) {
                userIDs = new Integer[]{0};
            } else {
                List<UserInfoDto> list = (List<UserInfoDto>) transferService.getUser(0, 0, userID).get(
                        "items");
                int size = list.size();
                userIDs = new Integer[size];
                for (int i = 0; i < list.size(); i++) {
                    userIDs[i] = list.get(i).getUserID();
                }
            }
            if (searchFlag == 1 || searchFlag == 2) {
                // query customer
                searchValue.setUserID(userIDs);
                map = customerDao.queryCustomer(searchFlag, searchValue, page, pageSize);
            } else {
                // load customer from database.
                map = customerDao.getAllCustomer(page, pageSize, userIDs);
            }
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method searchOrGetAllCustomer.", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method searchOrGetAllCustomer end.");
        return map;
    }

    @Override
    public List<String> getAllCustomerName(int customerID) {
        LOG.debug("method getAllCustomerName start.");
        List<String> nameList = this.customerDao.getAllCustomerName(customerID);
        LOG.debug("method getAllCustomerName end.");
        return nameList;
    }

    /**
     * delete customer and cooperator resume where customerID in <customerIDs>.
     * 
     * @param customerIDs
     *            store the customerID.
     * 
     * @return Map<String, Object>
     * @throws CRMDBException
     *             in case of DataAccessException.
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Map<String, Object> deleteCustomer(String customerIDs) throws CRMDBException {
        LOG.debug("method deleteCustomer start.");
        try {
            Map<String, Object> map = checkDelete(customerIDs);
            if (map.isEmpty()) {
                customerIDs = CRMConstant.LEFT_PARENTHESIS + customerIDs
                        + CRMConstant.RIGHT_PARENTHESIS;
                customerDao.deleteCustomer(customerIDs);
            } else {
                LOG.debug("method deleteCustomer end.");
                return map;
            }
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method deleteCustomer.", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method deleteCustomer end.");
        return new HashMap<String, Object>();
    }

    /**
     * check delete IDs
     * 
     * @param customerIDs
     * @return map
     */
    private Map<String, Object> checkDelete(String customerIDs) {
        LOG.debug("method checkDelete start.");
        Map<String, Object> map = new HashMap<String, Object>();
        Integer[] IDs = CRMUtils.getDeleteIds(customerIDs);
        List<String> table = null;
        StringBuffer errorMsg = new StringBuffer();
        String customerName = null;
        for (int i : IDs) {
            table = new ArrayList<String>();
            Map<String, Object> iMap = customerDao.getUsingCnt(i);
            if ((Long) iMap.get("ContactTrackInfoDto") > 0) {
                table.add("客户联系模块");
            }
//            if ((Long) iMap.get("OrderDto_1") > 0) {
//                table.add("订单模块");
//            }
//            if ((Long) iMap.get("OrderDto_0") > 0) {
//                table.add("意向订单模块");
//            }
//            if ((Long) iMap.get("SalesEventDto") > 0) {
//                table.add("销售事件模块");
//            }
//            if (isActivityMember(i)) {
//                table.add("营销活动模块");
//            }
            if (!table.isEmpty()) {
                customerName = customerDao.getCusNameByID(i);
                break;
            }
        }
        for (String s : table) {
            errorMsg.append(s);
            errorMsg.append("、");
        }
        if (errorMsg.length() > 0) {
            String errorMessage = errorMsg.substring(0, errorMsg.length() - 1);
            errorMessage = customerName + "在" + errorMessage + "使用，无法删除！";
            map.put("errorMessage", errorMessage);
        }
        LOG.debug("method checkDelete end.");
        return map;
    }

    /**
     * update or save the customer.
     * 
     * @param customerDto
     * @throws CRMDBException
     *             in case of DataAccessException.
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void updateCustomer(CustomerDto customerDto) throws CRMDBException {

        LOG.debug("method updateCustomer start.");
        try {
            String now = CRMUtils.currentTimeAsString();
            if (StringUtils.isBlank(customerDto.getCreateTime())) {
                customerDto.setCreateTime(now);
                customerDto.setUpdateTime(now);
            } else {
                customerDto.setUpdateTime(now);
            }
            customerDao.updateCustomer(customerDto);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method updateCustomer.", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method updateCustomer end.");
    }

    /**
     * @return the customerDao
     */
    @Override
    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    /**
     * @param customerDao
     *            the customerDao to set
     */
    @Override
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * 
     * @param customerDto
     * @return true
     * @throws CRMDBException
     *             in case of DataAccessException.
     */
    @Override
    public boolean judgeCustomerName(CustomerDto customerDto) throws CRMDBException {
        LOG.debug("method judgeCustomerName start.");
        boolean bool = false;
        try {
            bool = customerDao.judgeCustomerName(customerDto);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method judgeCustomerName.", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method judgeCustomerName end.");

        return bool;
    }

    private boolean isActivityMember(int customerID) {
        LOG.debug("method isActivityMember start.");
        List<String> activityMember = customerDao.findActivityMember();
        for (String s : activityMember) {
            Integer[] sArray = CRMUtils.getDeleteIds(s);
            for (int i : sArray) {
                if (i == customerID) {
                    LOG.debug("method isActivityMember end.");
                    return true;
                }
            }
        }
        LOG.debug("method isActivityMember end.");
        return false;
    }

    /**
     * @return the sourceInfoService
     */
    public SourceInfoService getSourceInfoService() {
        return sourceInfoService;
    }

    /**
     * @param sourceInfoService
     *            the sourceInfoService to set
     */
    public void setSourceInfoService(SourceInfoService sourceInfoService) {
        this.sourceInfoService = sourceInfoService;
    }

    public CustomerTransferService getTransferService() {
        return transferService;
    }

    public void setTransferService(CustomerTransferService transferService) {
        this.transferService = transferService;
    }

}
