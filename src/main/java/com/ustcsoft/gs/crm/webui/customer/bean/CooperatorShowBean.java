package com.ustcsoft.gs.crm.webui.customer.bean;

import com.ustcsoft.gs.crm.webui.customer.dto.CooperatorDto;

/**
 * 用于和前台对应的字段Dto,在原有CooperatorDto基础上增加了合作项目数，从合作项目履历中取得该值
 * 
 * @author xujialong
 * 
 */
public class CooperatorShowBean extends CooperatorDto {
    private String industryDisplay;
    private String scaleDisplay;
    private String typeDisplay;
    private int projectNumber;
    private String cooperationDate;
    private int copAnaNumber;

    public String getIndustryDisplay() {
        return industryDisplay;
    }

    public void setIndustryDisplay(String industryDisplay) {
        this.industryDisplay = industryDisplay;
    }

    public String getScaleDisplay() {
        return scaleDisplay;
    }

    public void setScaleDisplay(String scaleDisplay) {
        this.scaleDisplay = scaleDisplay;
    }

    public String getTypeDisplay() {
        return typeDisplay;
    }

    public void setTypeDisplay(String typeDisplay) {
        this.typeDisplay = typeDisplay;
    }

    public String getCooperationDate() {
        return cooperationDate;
    }

    public void setCooperationDate(String cooperationDate) {
        this.cooperationDate = cooperationDate;
    }

    public int getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(int projectNumber) {
        this.projectNumber = projectNumber;
    }

    public int getCopAnaNumber() {
        return copAnaNumber;
    }

    public void setCopAnaNumber(int copAnaNumber) {
        this.copAnaNumber = copAnaNumber;
    }

}
