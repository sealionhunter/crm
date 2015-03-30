package com.ustcsoft.gs.crm.webui.system.bean;

import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;

public class UserInfoBean extends UserInfoDto {
    private int groupIDB;
    private int departmentIDB;
    private int projectTeamIDB;
    private String jobTitleB;
    private String educationB;

    public int getGroupIDB() {
        return groupIDB;
    }

    public void setGroupIDB(int groupIDB) {
        this.groupIDB = groupIDB;
    }

    public int getDepartmentIDB() {
        return departmentIDB;
    }

    public void setDepartmentIDB(int departmentIDB) {
        this.departmentIDB = departmentIDB;
    }

    public int getProjectTeamIDB() {
        return projectTeamIDB;
    }

    public void setProjectTeamIDB(int projectTeamIDB) {
        this.projectTeamIDB = projectTeamIDB;
    }

    public String getJobTitleB() {
        return jobTitleB;
    }

    public void setJobTitleB(String jobTitleB) {
        this.jobTitleB = jobTitleB;
    }

    public String getEducationB() {
        return educationB;
    }

    public void setEducationB(String educationB) {
        this.educationB = educationB;
    }

}
