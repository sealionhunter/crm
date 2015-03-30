package com.ustcsoft.gs.crm.webui.customer.bean;

import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;

public class CoopResumeSearchBean {

    private int customerID = 0;
    private String searchText = null;
    private String projectName = null;
    private String[] projectType = {};
    private String projectScale = null;
    private int peopleNumber = 0;
    private String principalWe = null;
    private String principalThey = null;
    private String beginDateSearchMin = null;
    private String beginDateSearchMax = null;

    /**
     * @return the customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID
     *            the customerID to set
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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
     * @return the simple
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * 
     * @param projectName
     */
    public void setProjectName(String projectName) {
        this.projectName = CRMUtils.trimSearch(projectName);
    }

    /**
     * 
     * @return the projectName
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @return the projectType
     */
    public String[] getProjectType() {
        return projectType;
    }

    /**
     * @param projectType
     *            the projectType to set
     */
    public void setProjectType(String[] projectType) {
        this.projectType = projectType;
    }

    /**
     * 
     * @param projectScale
     */
    public void setProjectScale(String projectScale) {
        this.projectScale = projectScale;
    }

    /**
     * 
     * @return the projectScale
     */
    public String getProjectScale() {
        return projectScale;
    }

    /**
     * 
     * @param peopleNumber
     */
    public void setPeopleNumber(int peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    /**
     * 
     * @return the peopleNumber
     */
    public int getPeopleNumber() {
        return peopleNumber;
    }

    /**
     * 
     * @param principalWe
     */
    public void setPrincipalWe(String principalWe) {
        this.principalWe = CRMUtils.trimSearch(principalWe);
    }

    /**
     * 
     * @return the principalWe
     */
    public String getPrincipalWe() {
        return principalWe;
    }

    /**
     * 
     * @param principalThey
     */
    public void setPrincipalThey(String principalThey) {
        this.principalThey = CRMUtils.trimSearch(principalThey);
    }

    /**
     * 
     * @return the principalThey
     */
    public String getPrincipalThey() {
        return principalThey;
    }

    /**
     * 
     * @param beginDateSearchMin
     */
    public void setBeginDateSearchMin(String beginDateSearchMin) {
        this.beginDateSearchMin = beginDateSearchMin;
    }

    /**
     * 
     * @return the beginDateSearchMin
     */
    public String getBeginDateSearchMin() {
        return beginDateSearchMin;
    }

    /**
     * 
     * @param beginDateSearchMax
     */
    public void setBeginDateSearchMax(String beginDateSearchMax) {
        this.beginDateSearchMax = beginDateSearchMax;
    }

    /**
     * 
     * @return the beginDateSearchMax
     */
    public String getBeginDateSearchMax() {
        return beginDateSearchMax;
    }
}
