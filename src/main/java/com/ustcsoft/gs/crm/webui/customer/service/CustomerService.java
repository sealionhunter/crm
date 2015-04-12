/*
 * Interface name: CustomerServiceImpl
 * 
 * Version information: 1.0
 * 
 * Date:2012.9.11
 *  
 */
package com.ustcsoft.gs.crm.webui.customer.service;

import java.util.List;
import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.CustomerBean;
import com.ustcsoft.gs.crm.webui.customer.bean.CustomerSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dao.CustomerDao;
import com.ustcsoft.gs.crm.webui.customer.dto.CustomerDto;

/**
 * Processing and customer management relevant logic operation
 * 
 * @author tangyunpeng
 */
public interface CustomerService {

    CustomerDao customerDao = null;

    public List<String> getAllCustomerName(int customerID);

    /**
     * method to get all customer records or searched customer records
     * 
     * @param searchFlag
     *            search mark
     * @param page
     *            current page
     * @param pageSize
     *            size of page
     * @param searchValue
     *            CustomerSearchBean customer holder's projectTeam
     * @param userID
     *            customer holder
     * @param isGonghai 
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    public Map<String, Object> searchOrGetAllCustomer(int searchFlag,
            CustomerSearchBean searchValue, int page, int pageSize, int userID, boolean isGonghai)
            throws CRMDBException;

    /**
     * delete customer and cooperator resume where customerID in <customerIDs>.
     * 
     * @param customerIDs
     *            store the customerID.
     * @return Map<String, Object>
     * @throws CRMDBException
     *             in case of DataAccessException.
     */
    public Map<String, Object> deleteCustomer(String customerIDs) throws CRMDBException;

    /**
     * update or save the customer.
     * 
     * @param customerDto
     * @throws CRMDBException
     *             in case of DataAccessException.
     */
    public void updateCustomer(CustomerDto customerDto) throws CRMDBException;

    /**
     * 
     * @param customerDto
     * @return true
     * @throws CRMDBException
     *             in case of DataAccessException.
     */
    public boolean judgeCustomerName(CustomerDto customerDto) throws CRMDBException;

    public Map<String, Object> receiveCustomer(int customerID, int userID);

    public CustomerBean getCustomerById(int id) throws CRMDBException;
    
    public List<CustomerBean> findCustomersByIds(String ids) throws CRMDBException;

    public Map<String, Object> getCustomerCount(String statisticsType, int userID);
}
