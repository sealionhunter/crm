package com.ustcsoft.gs.crm.webui.activity.bean;

import com.ustcsoft.gs.crm.webui.activity.dto.ActivityInfoDto;

/**
 * class ActivityBean create a bean for save properties showing in page
 * 
 * @author xuzhen
 * 
 */
public class ActivityBean extends ActivityInfoDto {
    private String activityTypeName;
    private String activityEmphasisName;
    private String activityPeriodName;
    private String activityRangeName;
    private String activityStateName;
    private String memberName;

    public ActivityBean() {
    }

    public ActivityBean(ActivityInfoDto activity, String activityTypeName,
            String activityEmphasisName, String activityPeriodName, String activityRangeName,
            String activityStateName) {
        this.activityEmphasisName = activityEmphasisName;
        this.activityPeriodName = activityPeriodName;
        this.activityRangeName = activityRangeName;
        this.activityStateName = activityStateName;
        this.activityTypeName = activityTypeName;
        super.setActivityAim(activity.getActivityAim());
        super.setActivityComment(activity.getActivityComment());
        super.setActivityDetail(activity.getActivityDetail());
        super.setActivityEmphasis(activity.getActivityEmphasis());
        super.setActivityEndTime(activity.getActivityEndTime());
        super.setActivityFunds(activity.getActivityFunds());
        super.setActivityID(activity.getActivityID());
        super.setActivityLeader(activity.getActivityLeader());
        super.setActivityModifer(activity.getActivityModifer());
        super.setActivityMeans(activity.getActivityMeans());
        super.setActivityName(activity.getActivityName());
        super.setActivityOurPersonNO(activity.getActivityOurPersonNO());
        super.setActivityPeriod(activity.getActivityPeriod());
        super.setActivityPlace(activity.getActivityPlace());
        super.setActivityPlan(activity.getActivityPlan());
        super.setActivityRange(activity.getActivityRange());
        super.setActivityStartTime(activity.getActivityStartTime());
        super.setActivityState(activity.getActivityState());
        super.setActivityType(activity.getActivityType());
        super.setIsAbolished(activity.getIsAbolished());
        super.setActivityMember(activity.getActivityMember());
    }

    /**
     * 
     * @return activityTypeName
     */
    public String getActivityTypeName() {
        return activityTypeName;
    }

    /**
     * 
     * @param activityTypeName
     *            the activityTypeName to set
     */
    public void setActivityTypeName(String activityTypeName) {
        this.activityTypeName = activityTypeName;
    }

    /**
     * 
     * @return activityEmphasisName
     */
    public String getActivityEmphasisName() {
        return activityEmphasisName;
    }

    /**
     * 
     * @param activityEmphasisName
     *            the activityEmphasisName to set
     */
    public void setActivityEmphasisName(String activityEmphasisName) {
        this.activityEmphasisName = activityEmphasisName;
    }

    /**
     * 
     * @return activityPeriodName
     */
    public String getActivityPeriodName() {
        return activityPeriodName;
    }

    /**
     * 
     * @param activityPeriodName
     *            the activityPeriodName to set
     */
    public void setActivityPeriodName(String activityPeriodName) {
        this.activityPeriodName = activityPeriodName;
    }

    /**
     * 
     * @return activityRangeName
     */
    public String getActivityRangeName() {
        return activityRangeName;
    }

    /**
     * 
     * @param activityRangeName
     *            the activityRangeName to set
     */
    public void setActivityRangeName(String activityRangeName) {
        this.activityRangeName = activityRangeName;
    }

    /**
     * 
     * @return activityStateName
     */
    public String getActivityStateName() {
        return activityStateName;
    }

    /**
     * 
     * @param activityStateName
     *            the activityStateName to set
     */
    public void setActivityStateName(String activityStateName) {
        this.activityStateName = activityStateName;
    }

    /**
     * @return the memberName
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * @param memberName
     *            the memberName to set
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
