/**
 * @version v1.0
 */
package com.ustcsoft.gs.crm.webui.activity.dto;

import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;

/**
 * @author YangLibo
 */
public class ActivityInfoDto {
    /** the id of the activity */
    private int activityID;
    /** the id of the activity's type */
    private String activityType;
    /** the name of the activity */
    private String activityName;
    /** the id of the activity's range */
    private String activityRange;
    /** the starting time of the activity */
    private String activityStartTime;
    /** the ending time of the activity */
    private String activityEndTime;
    /** the place of the activity */
    private String activityPlace;
    /** our leader of the activity */
    private String activityLeader;
    private String activityModifer;
    /** the number of our person of the activity */
    private String activityOurPersonNO;
    /** the funds of the activity */
    private String activityFunds;
    /** the Detail of the activity */
    private String activityDetail;
    /** the id of the activity's emphasis */
    private String activityEmphasis;
    /** the remark of the activity */
    private String activityComment;
    /** the plan of the activity */
    private String activityPlan;
    /** the id of the activity's state */
    private String activityState;
    /** the id of the activity's period */
    private String activityPeriod;
    /** the means of the activity */
    private String activityMeans;
    /** the aim of the activity */
    private String activityAim;
    /** Whether true be deleted,the default value is 0 */
    private Boolean isAbolished = false;
    private String activityMember;
    private int[] customerName;

    /**
     * 
     * @return activityID
     */
    public int getActivityID() {
        return activityID;
    }

    /**
     * 
     * @param activityID
     *            the activityID to set
     */
    public void setActivityID(int activityID) {
        this.activityID = activityID;
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
        this.activityName = activityName.trim();
    }

    /**
     * 
     * @return activityRange
     */
    public String getActivityRange() {
        return activityRange;
    }

    /**
     * 
     * @param activityRange
     *            the activityRange to set
     */
    public void setActivityRange(String activityRange) {
        this.activityRange = activityRange;
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
        this.activityPlace = activityPlace.trim();
    }

    /**
     * 
     * @return activityLeader
     */
    public String getActivityLeader() {
        return activityLeader;
    }

    /**
     * 
     * @param activityLeader
     *            the activityLeader to set
     */
    public void setActivityLeader(String activityLeader) {
        this.activityLeader = activityLeader.trim();
    }

    /**
     * 
     * @return activityModifer
     */
    public String getActivityModifer() {
        return activityModifer;
    }

    /**
     * 
     * @param activityModifer
     *            the activityModifer to set
     */
    public void setActivityModifer(String activityModifer) {
        this.activityModifer = activityModifer.trim();
    }

    /**
     * 
     * @return activityOurPersonNO
     */
    public String getActivityOurPersonNO() {
        return activityOurPersonNO;
    }

    /**
     * 
     * @param activityOurPersonNO
     *            the activityOurPersonNO to set
     */
    public void setActivityOurPersonNO(String activityOurPersonNO) {
        this.activityOurPersonNO = CRMUtils.trim(activityOurPersonNO);
    }

    /**
     * 
     * @return activityFunds
     */
    public String getActivityFunds() {
        return activityFunds;
    }

    /**
     * 
     * @param activityFunds
     *            the activityFunds to set
     */
    public void setActivityFunds(String activityFunds) {
        this.activityFunds = CRMUtils.trim(activityFunds);
    }

    /**
     * 
     * @return activityDetail
     */
    public String getActivityDetail() {
        return activityDetail;
    }

    /**
     * 
     * @param activityDetail
     *            the activityDetail to set
     */
    public void setActivityDetail(String activityDetail) {
        this.activityDetail = activityDetail;
    }

    /**
     * 
     * @return activityComment
     */
    public String getActivityComment() {
        return activityComment;
    }

    /**
     * 
     * @param activityComment
     *            the activityComment to set
     */
    public void setActivityComment(String activityComment) {
        this.activityComment = CRMUtils.trim(activityComment);
    }

    /**
     * 
     * @return activityPlan
     */
    public String getActivityPlan() {
        return activityPlan;
    }

    /**
     * 
     * @param activityPlan
     *            the activityPlan to set
     */
    public void setActivityPlan(String activityPlan) {
        this.activityPlan = activityPlan;
    }

    /**
     * 
     * @return activityState
     */
    public String getActivityState() {
        return activityState;
    }

    /**
     * 
     * @param activityState
     *            the activityState to set
     */
    public void setActivityState(String activityState) {
        this.activityState = activityState;
    }

    /**
     * 
     * @return activityPeriod
     */
    public String getActivityPeriod() {
        return activityPeriod;
    }

    /**
     * 
     * @param activityPeriod
     *            the activityPeriod to set
     */
    public void setActivityPeriod(String activityPeriod) {
        this.activityPeriod = activityPeriod;
    }

    /**
     * 
     * @return activityMeans
     */
    public String getActivityMeans() {
        return activityMeans;
    }

    /**
     * 
     * @param activityMeans
     *            the activityMeans to set
     */
    public void setActivityMeans(String activityMeans) {
        this.activityMeans = CRMUtils.trim(activityMeans);
    }

    /**
     * 
     * @return activityAim
     */
    public String getActivityAim() {
        return activityAim;
    }

    /**
     * 
     * @param activityAim
     *            the activityAim to set
     */
    public void setActivityAim(String activityAim) {
        this.activityAim = CRMUtils.trim(activityAim);
    }

    /**
     * 
     * @return isAbolished
     */
    public Boolean getIsAbolished() {
        return isAbolished;
    }

    /**
     * 
     * @param isAbolished
     *            the isAbolished to set
     */
    public void setIsAbolished(Boolean isAbolished) {
        this.isAbolished = isAbolished;
    }

    /**
     * 
     * @return activityType
     */
    public String getActivityType() {
        return activityType;
    }

    /**
     * 
     * @param activityType
     *            the activityType to set
     */
    public void setActivityType(String activityType) {
        this.activityType = activityType;
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
     * @return activityEmphasis
     */
    public String getActivityEmphasis() {
        return activityEmphasis;
    }

    /**
     * 
     * @param activityEmphasis
     *            the activityEmphasis to set
     */
    public void setActivityEmphasis(String activityEmphasis) {
        this.activityEmphasis = activityEmphasis;
    }

    /**
     * 
     * @return activityMember
     */
    public String getActivityMember() {
        return activityMember;
    }

    /**
     * 
     * @param activityMember
     *            the activityMember to set
     */
    public void setActivityMember(String activityMember) {
        this.activityMember = CRMUtils.trim(activityMember);
    }

    /**
     * @return the customerName
     */
    public int[] getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName
     *            the customerName to set
     */
    public void setCustomerName(int[] customerName) {
        this.customerName = customerName;
    }
}
