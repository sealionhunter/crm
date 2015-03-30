package com.ustcsoft.gs.crm.webui.customer.bean;

import com.ustcsoft.gs.crm.webui.customer.dto.CopAnalysisDto;

/**
 * extends CopAnalysisDto and to show info in page.
 * 
 * @author xujueyin
 * 
 */
public class CopAnalysisBean extends CopAnalysisDto {
    private String cooperatorName;
    private String managementAbilityName;
    private String responseSpeedName;
    private String trustDegreeName;

    public String getCooperatorName() {
        return cooperatorName;
    }

    public void setCooperatorName(String cooperatorName) {
        this.cooperatorName = cooperatorName;
    }

    public String getManagementAbilityName() {
        return managementAbilityName;
    }

    public void setManagementAbilityName(String managementAbilityName) {
        this.managementAbilityName = managementAbilityName;
    }

    public String getResponseSpeedName() {
        return responseSpeedName;
    }

    public void setResponseSpeedName(String responseSpeedName) {
        this.responseSpeedName = responseSpeedName;
    }

    public String getTrustDegreeName() {
        return trustDegreeName;
    }

    public void setTrustDegreeName(String trustDegreeName) {
        this.trustDegreeName = trustDegreeName;
    }
}
