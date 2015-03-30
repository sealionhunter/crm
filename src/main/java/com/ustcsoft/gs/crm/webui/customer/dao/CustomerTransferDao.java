/*
 * 
 */
package com.ustcsoft.gs.crm.webui.customer.dao;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.CustomerTrasferBean;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;

/**
 * 
 * @author tangyunpeng
 * 
 */
public interface CustomerTransferDao {

    /**
     * 
     * @param customerIDs
     *            transfer object
     * @param userID
     *            customer holder
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    public void saveTransfer(String customerIDs, int userID);

    /**
     * method to get all customer records which customer has no holder
     * 
     * @param searchFlag
     *            search mark
     * @param limit
     *            pageSize
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    public Map<String, Object> getCustomer(int searchFlag, int page, int limit);

    /**
     * method to get all customer records for transfer
     * 
     * @param page
     *            current page
     * @param searchBean
     *            CustomerTrasferBean
     * @param limit
     *            pageSize
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    public Map<String, Object> getCustomer(int searchFalg, CustomerTrasferBean searchBean,
            int page, int limit);

    /**
     * get all department information
     * 
     * @return Map department information Collection
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    public Map<String, Object> getDepartment();

    /**
     * get projectTeam information
     * 
     * @param userID
     * @return Map projectTeam information Collection
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    public Map<String, Object> getProjectTeam(int userID);

    /**
     * get all user information
     * 
     * @param queryDepartmentID
     * @param queryProjectTeamID
     * @param userDto
     * @return Map user information Collection
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    public Map<String, Object> getUser(int queryDepartmentID, int queryProjectTeamID,
            UserInfoDto userDto);

    public Map<String, Object> getUserByProjectTeam(UserInfoDto userDto);

}
