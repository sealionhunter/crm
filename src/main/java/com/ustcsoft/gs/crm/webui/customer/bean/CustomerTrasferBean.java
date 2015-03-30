package com.ustcsoft.gs.crm.webui.customer.bean;

public class CustomerTrasferBean {
    private Integer[] userIDs = null;
    private int departmentID = 0;
    private int projectTeamID = 0;
    private int userID = 0;

    public Integer[] getUserIDs() {
        return userIDs;
    }

    public void setUserIDs(Integer[] userIDs) {
        this.userIDs = userIDs;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public int getProjectTeamID() {
        return projectTeamID;
    }

    public void setProjectTeamID(int projectTeamID) {
        this.projectTeamID = projectTeamID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
