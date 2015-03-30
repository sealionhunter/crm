package com.ustcsoft.gs.crm.webui.common.action;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;

/**
 * 
 * Description:all action's father
 * 
 * @author xujialong
 * 
 */
public class CRMAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    /** current page */
    protected int page = 1;

    protected int limit = 0;

    /** send list message */
    protected Map<String, Object> map = new HashMap<String, Object>();

    /** receive message which will be added , updated ,deleted,searched */
    protected String jsonString = "{}";

    /**
     * receive search Mode(0 for list,1 for simple search,2 for advanced search)
     */
    protected int searchFlag = 0;

    /** user ID */
    protected int userID = 0;

    protected int eventID = 0;

    /**
     * 
     * @return eventID
     */
    public int getEventID() {
        return eventID;
    }

    /**
     * 
     * @param eventID
     */
    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public void showFieldError() {
        if (!getFieldErrors().isEmpty()) {
            map.putAll(getFieldErrors());
            map.put("validate", false);
        }
    }

    @Override
    public String execute() throws CRMDBException {
        return SUCCESS;
    }

    public void validateExecute() {

    }

    public String update() throws CRMDBException {
        return SUCCESS;
    }

    public void validateUpdate() throws CRMDBException {

    }

    public String delete() throws CRMDBException {
        return SUCCESS;
    }

    public void validateDelete() {
        if (!jsonString.matches(CRMConstant.REMOVE_IDS_REGEX)) {
            addFieldError("jsonString", this.getText("del.error"));
        }
        showFieldError();
    }

    /**
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page
     *            the page to set
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return the pageSize
     */
    public int getLimit() {
        return limit;
    }

    /**
     * @param limit
     *            the pageSize to set
     */
    public void setLimit(int limit) {
        this.limit = limit;
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
     * @return the searchFlag
     */
    public int getSearchFlag() {
        return searchFlag;
    }

    /**
     * @param searchFlag
     *            the searchFlag to set
     */
    public void setSearchFlag(int searchFlag) {
        this.searchFlag = searchFlag;
    }

    /**
     * @param userID
     *            the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }
}
