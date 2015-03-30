package com.ustcsoft.gs.crm.webui.system.bean;

import java.util.List;

import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;

public class ProjectTeamSearchBean {

    private List<Integer> departmentIDs = null;
    private String projectTeamName = null;

    public List<Integer> getDepartmentIDs() {
        return departmentIDs;
    }

    public void setDepartmentIDs(List<Integer> departmentIDs) {
        this.departmentIDs = departmentIDs;
    }

    public String getProjectTeamName() {
        return projectTeamName;
    }

    public void setProjectTeamName(String projectTeamName) {
        this.projectTeamName = CRMUtils.trimSearch(projectTeamName);
    }
}
