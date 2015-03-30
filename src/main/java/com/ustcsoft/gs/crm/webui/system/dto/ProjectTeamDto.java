package com.ustcsoft.gs.crm.webui.system.dto;

public class ProjectTeamDto {

    private int projectTeamID = 0;
    private int departmentID = 0;
    private String projectTeamName = null;
    private int projectTeamLeaderID;
    private String projectTeamStatusCode = null;
    private String createTime = null;
    private String endTime = null;
    private String description = null;

    public int getProjectTeamID() {
        return projectTeamID;
    }

    public void setProjectTeamID(int projectTeamID) {
        this.projectTeamID = projectTeamID;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getProjectTeamName() {
        return projectTeamName;
    }

    public void setProjectTeamName(String projectTeamName) {
        this.projectTeamName = projectTeamName;
    }

    public int getProjectTeamLeaderID() {
        return projectTeamLeaderID;
    }

    public void setProjectTeamLeaderID(int projectTeamLeaderID) {
        this.projectTeamLeaderID = projectTeamLeaderID;
    }

    public String getProjectTeamStatusCode() {
        return projectTeamStatusCode;
    }

    public void setProjectTeamStatusCode(String projectTeamStatusCode) {
        this.projectTeamStatusCode = projectTeamStatusCode;
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
