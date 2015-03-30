package com.ustcsoft.gs.crm.webui.system.bean;

import java.util.ArrayList;
import java.util.List;

import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;

public class UserInfoSearchBean {
    private String searchText;
    private int userID;
    private List<Integer> childDepartmentID = new ArrayList<Integer>();
    private int departmentID;
    private int projectTeamID;
    private String realName;
    private int groupID;

    /**
     * 
     * @return groupID
     */
    public int getGroupID() {
        return groupID;
    }

    /**
     * 
     * @param groupID
     */
    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

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
     */
    public void setSearchText(String searchText) {
        this.searchText = CRMUtils.trimSearch(searchText);
    }

    /**
     * 
     * @return projectTeamID
     */
    public int getProjectTeamID() {
        return projectTeamID;
    }

    /**
     * 
     * @param projectTeamID
     */
    public void setProjectTeamID(int projectTeamID) {
        this.projectTeamID = projectTeamID;
    }

    /**
     * 
     * @return realName
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 
     * @param realName
     */
    public void setRealName(String realName) {
        this.realName = CRMUtils.trimSearch(realName);
    }

    /**
     * 
     * @return userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * 
     * @param userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public List<Integer> getChildDepartmentID() {
        return childDepartmentID;
    }

    public void setChildDepartmentID(List<Integer> childDepartmentID) {
        this.childDepartmentID = childDepartmentID;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

}
