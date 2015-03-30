package com.ustcsoft.gs.crm.webui.activity.bean;

import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;

public class ActivitySearchBean {

    private String searchText = null;
    private String[] activityType = null;
    private String activityName = null;
    private String[] activityRange = null;
    private String activityStartTime = null;
    private String activityEndTime = null;
    private String activityPlace = null;
    private String[] activityState = null;

    /**
     * 
     * @return searchText
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * 
     * @param searchText
     *            the searchText to set
     */
    public void setSearchText(String searchText) {
        this.searchText = CRMUtils.trimSearch(searchText);
    }

    /**
     * 
     * @return activityType
     */
    public String[] getActivityType() {
        return activityType;
    }

    /**
     * 
     * @param activityType
     *            the activityType to set
     */
    public void setActivityType(String[] activityType) {
        this.activityType = activityType;
    }

    /**
     * 
     * @return activityName
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * 
     * @param activityName
     *            the activityName to set
     */
    public void setActivityName(String activityName) {
        this.activityName = CRMUtils.trimSearch(activityName);
    }

    /**
     * 
     * @return activityRange
     */
    public String[] getActivityRange() {
        return activityRange;
    }

    /**
     * 
     * @param activityRange
     *            the activityRange to set
     */
    public void setActivityRange(String[] activityRange) {
        this.activityRange = activityRange;
    }

    /**
     * 
     * @return activityStartTime
     */
    public String getActivityStartTime() {
        return activityStartTime;
    }

    /**
     * 
     * @param activityStartTime
     *            the activityStartTime to set
     */
    public void setActivityStartTime(String activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    /**
     * 
     * @return activityEndTime
     */
    public String getActivityEndTime() {
        return activityEndTime;
    }

    /**
     * 
     * @param activityEndTime
     *            the activityEndTime to set
     */
    public void setActivityEndTime(String activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    /**
     * 
     * @return activityPlace
     */
    public String getActivityPlace() {
        return activityPlace;
    }

    /**
     * 
     * @param activityPlace
     *            the activityPlace to set
     */
    public void setActivityPlace(String activityPlace) {
        this.activityPlace = CRMUtils.trimSearch(activityPlace);
    }

    /**
     * 
     * @return activityState
     */
    public String[] getActivityState() {
        return activityState;
    }

    /**
     * 
     * @param activityState
     *            the activityState to set
     */
    public void setActivityState(String[] activityState) {
        this.activityState = activityState;
    }
}
