/*
 * Interface name: CustomerDao
 * 
 * Version information: 1.0
 * 
 * Date:2012.9.11
 *  
 */
package com.ustcsoft.gs.crm.webui.customer.dao;

import java.util.List;
import java.util.Map;

import com.ustcsoft.gs.crm.webui.customer.bean.CustomerSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.CustomerDto;

/**
 * Deal with the database operations on the CustomerInfo table.
 * 
 * @author tangyunpeng and xujueyin
 * 
 */
public interface CustomerDao {

    /**
     * get customer by page
     * 
     * @param currPage
     *            the current page.
     * @param userID
     * @return map the customer list by page.
     */
    public Map<String, Object> getAllCustomer(final int currPage, final int pageSize,
            Integer[] userID);

    public List<String> getAllCustomerName(int customerID);

    /**
     * super and query customer from database.
     * 
     * @param searchFlag
     *            int remark simple or super query
     * @param searchValue
     *            CustomerSearchBean search conditions
     * @param currPage
     *            int the current page
     * @return Map<String, Object> the customer query result list for page
     */
    public Map<String, Object> queryCustomer(int searchFlag, CustomerSearchBean searchValue,
            int currPage, int pageSize);

    /**
     * 
     * @param customerID
     * @return String
     */
    public String getCusNameByID(int customerID);

    /**
     * delete customer and cooperator resume
     * 
     * @param customerIDs
     */
    public void deleteCustomer(String customerIDs);

    /**
     * save or update customer information.
     * 
     * @param customerDto
     *            update object.
     */
    public void updateCustomer(CustomerDto customerDto);

    /**
     * find all activity member
     * 
     * @return List<String>
     */
    public List<String> findActivityMember();

    /**
     * get using cnt
     * 
     * @param customerID
     * @return Map<String, Object>
     */
    public Map<String, Object> getUsingCnt(int customerID);

    /**
     * get the number of records.
     * 
     * @param queryString
     * @return count
     */
    public long getCustomerSize(String queryString, Integer[] userID);

    /**
     * 
     * @param customerDto
     * @return true
     */
    public boolean judgeCustomerName(CustomerDto customerDto);

    public boolean isHolderExist(int customerID);

    public void receiveCustomer(int customerID, int userID);

}
