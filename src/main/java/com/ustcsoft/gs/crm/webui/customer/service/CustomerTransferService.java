/*
 * Interface name: CustomerTransferService
 * 
 * Version information: 1.0
 * 
 * Date:2012.11.13
 *  
 */
package com.ustcsoft.gs.crm.webui.customer.service;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;

/**
 * 
 * @author tangyunpeng
 */
public interface CustomerTransferService {

    /**
     * 
     * @param customerIDs
     *            transfer object
     * @param userID
     *            customer holder
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    public void saveTransfer(String customerIDs, int userID) throws CRMDBException;

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
    public Map<String, Object> getCustomerForTransfer(int searchFlag, int loginUser_ID,
            int departmentID, int projectTeamID, int userID, int page, int limit)
            throws CRMDBException;

    /**
     * get all department information
     * 
     * @return Map department information Collection
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    public Map<String, Object> getDepartment() throws CRMDBException;

    /**
     * get information
     * 
     * @param userID
     * @return Map projectTeam information Collection
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    public Map<String, Object> getProjectTeam(int userID) throws CRMDBException;

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
    public Map<String, Object> getUser(int queryDepartmentID, int queryProjectTeamID, int userID)
            throws CRMDBException;

    public Map<String, Object> getUserByProjectTeam(int userID) throws CRMDBException;

}
