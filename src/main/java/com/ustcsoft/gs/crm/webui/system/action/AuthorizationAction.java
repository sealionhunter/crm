/*
 * Class name: AuthorizationAction
 * 
 * Version information: 1.0
 * 
 * Date:2012.11.09
 *  
 */
package com.ustcsoft.gs.crm.webui.system.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.exception.CRMException;
import com.ustcsoft.gs.crm.webui.system.constant.SystemConstant;
import com.ustcsoft.gs.crm.webui.system.dto.TreeDto;
import com.ustcsoft.gs.crm.webui.system.service.AuthorizationService;

/**
 * CRM authorization action.
 * 
 * @author xujueyin and xujialong
 * 
 */
public class AuthorizationAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    /** get Log */
    private static final Log LOG = LogFactory.getLog(AuthorizationAction.class);

    /** tree menus for display */
    private List<TreeDto> treeList;

    /** authorization service */
    private AuthorizationService authorizationService = null;

    /** get actionIDs from page */
    private String jsonString;

    /** the map used for passing json values */
    private Map<String, Object> map = null;

    /** remarked it is a personal user or manager */
    private int userMode;

    /** the display tree root ID */
    private int rootID;

    /** user or role Id */
    private int id;

    /** the ID of the user currently logged on */
    private int userID;

    /**
     * get access rights tree.
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * @return SUCCESS
     * @throws CRMException
     */
    public String getAccessTree() throws CRMDBException, CRMException {
        LOG.debug("method getAccessTree start!");
        treeList = authorizationService.getTree(userMode, SystemConstant.TREE_FLAG_MENU, id);
        LOG.debug("method getAccessTree end!");
        return SUCCESS;
    }

    /**
     * get operation rights tree.
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * @return SUCCESS
     * @throws CRMException
     */
    public String getOperationTree() throws CRMDBException, CRMException {
        LOG.debug("method getOperationTree start!");
        treeList = authorizationService.getTree(userMode, SystemConstant.TREE_FLAG_OPERATION, id);
        LOG.debug("method getOperationTree end!");
        return SUCCESS;
    }

    /**
     * save authorization.
     * 
     * @throws CRMException
     *             in case of NumberFormatException
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * @return SUCCESS
     */
    public String saveAuthorization() throws CRMDBException, CRMException {
        LOG.debug("method saveAuthorization start!");
        authorizationService.saveAuthorization(userMode, jsonString, id, userID);
        LOG.debug("method saveAuthorization end!");
        return SUCCESS;
    }

    /**
     * get members of the current user.
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * @return SUCCESS
     */
    public String getMembers() throws CRMDBException {
        LOG.debug("method getMembers start!");
        map = authorizationService.getMembers(userMode, id);
        LOG.debug("method getMembers end!");
        return SUCCESS;
    }

    /**
     * get user authorization.
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * @return SUCCESS
     */
    public String getOperationAuthorization() throws CRMDBException {
        LOG.debug("method getOperationAuthorization start!");
        map = authorizationService.getOperationAuthorization(id, rootID);
        LOG.debug("method getOperationAuthorization end!");
        return SUCCESS;
    }

    /**
     * get personal user authorization for setting up.
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * @return SUCCESS
     */
    public String getMemberAuthorization() throws CRMDBException {
        LOG.debug("method getMemberAuthorization start!");
        map = authorizationService.getMemberAuthorization(userMode, id);
        LOG.debug("method getMemberAuthorization end!");
        return SUCCESS;
    }

    /**
     * validate format of passed authorization IDs from page.
     */
    public void validateSaveAuthorization() {
        if (jsonString != null && !"".equals(jsonString)
                && !jsonString.matches(SystemConstant.SAVED_IDS_REGEX)) {
            addFieldError("savedAuthorizationIds", this.getText("savedAuthorizationIds.error"));
        }

        /*
         * put all error messages to map
         */
        if (!getFieldErrors().isEmpty()) {
            map = new HashMap<String, Object>();
            map.putAll(getFieldErrors());
            map.put("validate", false);
        }
    }

    /**
     * @return the treeList
     */
    public List<TreeDto> getTreeList() {
        return treeList;
    }

    /**
     * @param treeList
     *            the treeList to set
     */
    public void setTreeList(List<TreeDto> treeList) {
        this.treeList = treeList;
    }

    /**
     * @return the authorizationService
     */
    public AuthorizationService getAuthorizationService() {
        return authorizationService;
    }

    /**
     * @param authorizationService
     *            the authorizationService to set
     */
    public void setAuthorizationService(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    /**
     * @return the jsonString
     */
    public String getJsonString() {
        return jsonString;
    }

    /**
     * @param jsonString
     *            the jsonString to set
     */
    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the map
     */
    public Map<String, Object> getMap() {
        return map;
    }

    /**
     * @param map
     *            the map to set
     */
    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    /**
     * @return the userMode
     */
    public int getUserMode() {
        return userMode;
    }

    /**
     * @param userMode
     *            the userMode to set
     */
    public void setUserMode(int userMode) {
        this.userMode = userMode;
    }

    /**
     * @return the rootID
     */
    public int getRootID() {
        return rootID;
    }

    /**
     * @param rootID
     *            the rootID to set
     */
    public void setRootID(int rootID) {
        this.rootID = rootID;
    }

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID
     *            the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }
}