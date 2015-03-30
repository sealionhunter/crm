package com.ustcsoft.gs.crm.webui.customer.action;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.service.CustomerTransferService;

/**
 * class CustomerTransferAction for jsp page to execute action
 * 
 * @author tangyunpeng
 * 
 */
public class CustomerTransferAction extends CRMAction {

    private static final long serialVersionUID = 1L;

    private static final Log LOG = LogFactory.getLog(CustomerTransferAction.class);

    /** Used for get class CustomerTransferService */
    private CustomerTransferService transferService = null;

    /** Used to accept the department id. */
    private int departmentID = 0;

    /** Used to accept the projectTeam id. */
    private int projectTeamID = 0;

    /** Used to accept the group id. */
    private int groupID = 0;

    private int queryDepartmentID = 0;

    private int queryProjectTeamID = 0;

    private int loginUser_ID = 0;

    /**
     * The method execute for changing customer's holder
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    public String saveTransfer() throws CRMDBException {
        LOG.debug("method saveTransfer start.");
        transferService.saveTransfer(super.jsonString, userID);
        LOG.debug("method saveTransfer end.");
        return SUCCESS;
    }

    /**
     * Get the customer by customer'holder
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    public String getCustomerForTransfer() throws CRMDBException {
        LOG.debug("method getCustomerForTransfer start.");
        loginUser_ID = getLoginUserID();
        map = transferService.getCustomerForTransfer(super.searchFlag, loginUser_ID, departmentID,
                projectTeamID, userID, page, limit);
        LOG.debug("method getCustomerForTransfer end.");
        return SUCCESS;
    }

    /**
     * Get all department
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    public String getDepartment() throws CRMDBException {
        LOG.debug("method getDepartment start.");
        map = transferService.getDepartment();
        LOG.debug("method getDepartment end.");
        return SUCCESS;
    }

    /**
     * Get projectTeam by userID
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    public String getProjectTeam() throws CRMDBException {
        LOG.debug("method getProjectTeam start.");
        map = transferService.getProjectTeam(userID);
        LOG.debug("method getProjectTeam end.");
        return SUCCESS;
    }

    /**
     * Get all can operate user
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    public String getUser() throws CRMDBException {
        LOG.debug("method getProjectTeam start.");
        map = transferService.getUser(queryDepartmentID, queryProjectTeamID, userID);
        LOG.debug("method getProjectTeam end.");
        return SUCCESS;
    }

    public String getUserByProjectTeam() throws CRMDBException {
        LOG.debug("method getUserByProjectTeam start.");
        map = transferService.getUserByProjectTeam(userID);
        LOG.debug("method getUserByProjectTeam end.");
        return SUCCESS;
    }

    /**
     * @return the transferService
     */
    public CustomerTransferService getTransferService() {
        return transferService;
    }

    /**
     * @param transferService
     *            the transferService to set
     */
    public void setTransferService(CustomerTransferService transferService) {
        this.transferService = transferService;
    }

    /**
     * @return the departmentID
     */
    public int getDepartmentID() {
        return departmentID;
    }

    /**
     * @param departmentID
     *            the departmentID to set
     */
    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    /**
     * @return the projectTeamID
     */
    public int getProjectTeamID() {
        return projectTeamID;
    }

    /**
     * @param projectTeamID
     *            the projectTeamID to set
     */
    public void setProjectTeamID(int projectTeamID) {
        this.projectTeamID = projectTeamID;
    }

    /**
     * 
     * @return the groupID
     */
    public int getGroupID() {
        return groupID;
    }

    /**
     * 
     * @param groupID
     *            the groupID to set
     */
    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    /**
     * 
     * @return the queryDepartmentID
     */
    public int getQueryDepartmentID() {
        return queryDepartmentID;
    }

    /**
     * 
     * @param queryDepartmentID
     *            the departmentID to set
     */
    public void setQueryDepartmentID(int queryDepartmentID) {
        this.queryDepartmentID = queryDepartmentID;
    }

    /**
     * 
     * @return the queryProjectTeamID
     */
    public int getQueryProjectTeamID() {
        return queryProjectTeamID;
    }

    /**
     * 
     * @param queryProjectTeamID
     *            the queryProjectTeamID to set
     */
    public void setQueryProjectTeamID(int queryProjectTeamID) {
        this.queryProjectTeamID = queryProjectTeamID;
    }

    /**
     * @return the userID
     */
    public int getLoginUserID() {
        Map<String, Object> session = ActionContext.getContext().getSession();
        if (session != null) {
            return (Integer) session.get("userID");
        }
        return 0;
    }
}
