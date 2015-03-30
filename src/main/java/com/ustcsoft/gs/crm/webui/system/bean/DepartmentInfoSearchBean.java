package com.ustcsoft.gs.crm.webui.system.bean;

import java.util.ArrayList;
import java.util.List;

import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;

public class DepartmentInfoSearchBean {
    private int groupID = 0;
    private String searchText;
    private int departmentID = 0;
    private int userID = 0;
    private Integer fatherDepartmentID = 0;
    private List<Integer> departmentIDs = new ArrayList<Integer>();
    private int loginDepartmentID = 0;
    private String departmentManager;
    private String departmentName;

    public String getDepartmentManager() {
        return departmentManager;
    }

    public void setDepartmentManager(String departmentManager) {
        this.departmentManager = CRMUtils.trimSearch(departmentManager);
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = CRMUtils.trimSearch(departmentName);
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = CRMUtils.trimSearch(searchText);
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public List<Integer> getDepartmentIDs() {
        return departmentIDs;
    }

    public void setDepartmentIDs(List<Integer> departmentIDs) {
        this.departmentIDs = departmentIDs;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getLoginDepartmentID() {
        return loginDepartmentID;
    }

    public void setLoginDepartmentID(int loginDepartmentID) {
        this.loginDepartmentID = loginDepartmentID;
    }

    public Integer getFatherDepartmentID() {
        return fatherDepartmentID;
    }

    public void setFatherDepartmentID(Integer fatherDepartmentID) {
        this.fatherDepartmentID = fatherDepartmentID;
    }

}
