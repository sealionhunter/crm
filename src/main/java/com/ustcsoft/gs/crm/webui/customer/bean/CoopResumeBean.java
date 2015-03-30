package com.ustcsoft.gs.crm.webui.customer.bean;

import com.ustcsoft.gs.crm.webui.customer.dto.CoopResumeDto;

public class CoopResumeBean extends CoopResumeDto {

    private String projectTypeName;

    /**
     * 
     * @param projectTypeName
     */
    public void setProjectTypeName(String projectTypeName) {
        this.projectTypeName = projectTypeName;
    }

    /**
     * 
     * @return the projectTypeName
     */
    public String getProjectTypeName() {
        return projectTypeName;
    }
}
