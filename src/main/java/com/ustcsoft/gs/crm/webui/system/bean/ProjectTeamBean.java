package com.ustcsoft.gs.crm.webui.system.bean;

public class ProjectTeamBean {

    private int projectTeamID;
    private String projectTeamName = null;
    private int departmentID;
    private String departmentName = null;
    private int projectTeamLeaderID;
    private String projectTeamLeaderName = null;
    private String projectTeamStatusCode = null;
    private String projectTeamStatusValue = null;
    private String createTime = null;
    private String endTime = null;
    private String description = null;

    public int getProjectTeamID() {
        return projectTeamID;
    }

    public void setProjectTeamID(int projectTeamID) {
        this.projectTeamID = projectTeamID;
    }

    public String getProjectTeamName() {
        return projectTeamName;
    }

    public void setProjectTeamName(String projectTeamName) {
        this.projectTeamName = projectTeamName;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getProjectTeamLeaderID() {
        return projectTeamLeaderID;
    }

    public void setProjectTeamLeaderID(int projectTeamLeaderID) {
        this.projectTeamLeaderID = projectTeamLeaderID;
    }

    public String getProjectTeamLeaderName() {
        return projectTeamLeaderName;
    }

    public void setProjectTeamLeaderName(String projectTeamLeaderName) {
        this.projectTeamLeaderName = projectTeamLeaderName;
    }

    public String getProjectTeamStatusCode() {
        return projectTeamStatusCode;
    }

    public void setProjectTeamStatusCode(String projectTeamStatusCode) {
        this.projectTeamStatusCode = projectTeamStatusCode;
    }

    public String getProjectTeamStatusValue() {
        return projectTeamStatusValue;
    }

    public void setProjectTeamStatusValue(String projectTeamStatusValue) {
        this.projectTeamStatusValue = projectTeamStatusValue;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
