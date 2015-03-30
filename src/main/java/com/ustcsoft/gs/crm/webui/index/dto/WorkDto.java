package com.ustcsoft.gs.crm.webui.index.dto;

public class WorkDto {
    private int workID = 0;
    private int userID = 0;
    private int customerID = 0;
    private int teamFlag = 0;
    private String assignee = null;
    private int[] assignees = {};
    private String theme = null;
    private String priority = null;
    private String workType = null;
    private String completion = null;
    private String startTime = null;
    private String endTime = null;
    private String workDetail = null;
    private String descriptions = null;
    private Boolean isMailInformed = false;
    private Boolean isAbolished = false;
    private Boolean isReaded = false;

    /**
     * @return the workID
     */
    public int getWorkID() {
        return workID;
    }

    /**
     * @param workID
     *            the workID to set
     */
    public void setWorkID(int workID) {
        this.workID = workID;
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

    /**
     * @return the customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID
     *            the customerID to set
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * @return the teamFlag
     */
    public int getTeamFlag() {
        return teamFlag;
    }

    /**
     * @param teamFlag
     *            the teamFlag to set
     */
    public void setTeamFlag(int teamFlag) {
        this.teamFlag = teamFlag;
    }

    /**
     * @return the theme
     */
    public String getTheme() {
        return theme;
    }

    /**
     * @param theme
     *            the theme to set
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }

    /**
     * @return the priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @param priority
     *            the priority to set
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * @return the workType
     */
    public String getWorkType() {
        return workType;
    }

    /**
     * @param workType
     *            the workType to set
     */
    public void setWorkType(String workType) {
        this.workType = workType;
    }

    /**
     * @return the completion
     */
    public String getCompletion() {
        return completion;
    }

    /**
     * @param completion
     *            the completion to set
     */
    public void setCompletion(String completion) {
        this.completion = completion;
    }

    /**
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     *            the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(String endTime) {
        if ("".equals(endTime)) {
            endTime = null;
        }
        this.endTime = endTime;
    }

    /**
     * @return the workDetail
     */
    public String getWorkDetail() {
        return workDetail;
    }

    /**
     * @param workDetail
     *            the workDetail to set
     */
    public void setWorkDetail(String workDetail) {
        this.workDetail = workDetail;
    }

    /**
     * @return the isMailInformed
     */
    public Boolean getIsMailInformed() {
        return isMailInformed;
    }

    /**
     * @param isMailInformed
     *            the isMailInformed to set
     */
    public void setIsMailInformed(Boolean isMailInformed) {
        this.isMailInformed = isMailInformed;
    }

    /**
     * @return the isAbolished
     */
    public Boolean getIsAbolished() {
        return isAbolished;
    }

    /**
     * @param isAbolished
     *            the isAbolished to set
     */
    public void setIsAbolished(Boolean isAbolished) {
        this.isAbolished = isAbolished;
    }

    /**
     * @return the descriptions
     */
    public String getDescriptions() {
        return descriptions;
    }

    /**
     * @param descriptions
     *            the descriptions to set
     */
    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    /**
     * @return the assignee
     */
    public String getAssignee() {
        return assignee;
    }

    /**
     * @param assignee
     *            the assignee to set
     */
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    /**
     * @return the assigneeName
     */
    public int[] getAssignees() {
        return assignees;
    }

    /**
     * @param assignees
     *            the assigneeName to set
     */
    public void setAssignees(int[] assignees) {
        this.assignees = assignees;
    }

    /**
     * @return the isReaded
     */
    public Boolean getIsReaded() {
        return isReaded;
    }

    /**
     * @param isReaded
     *            the isReaded to set
     */
    public void setIsReaded(Boolean isReaded) {
        this.isReaded = isReaded;
    }

}
