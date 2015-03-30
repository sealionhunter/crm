package com.ustcsoft.gs.crm.webui.customer.dto;

public class CoopResumeDto {

    private int coopResumeID = 0;
    private int customerID = 0;
    private String projectName = null;
    private String expBeginDate = null;
    private String expEndDate = null;
    private String beginDate = null;
    private String endDate = null;
    private String projectScale = null;
    private int peopleNumber = 0;
    private String principalThey = null;
    private String principalWe = null;
    private String projectDetail = null;
    private String projectType = null;
    private String appraisal = null;
    private Boolean isAbolished = false;
    private String descriptions = null;

    /**
     * @param coopResumeID
     *            the coopResumeID to set
     */
    public void setCoopResumeID(int coopResumeID) {
        this.coopResumeID = coopResumeID;
    }

    /**
     * @return the coopResumeID
     */
    public int getCoopResumeID() {
        return coopResumeID;
    }

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
     * @return the projectName
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @param projectName
     *            the projectName to set
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName.trim();
    }

    /**
     * @return the expBeginDate
     */
    public String getExpBeginDate() {
        return expBeginDate;
    }

    /**
     * @param expBeginDate
     *            the expBeginDate to set
     */
    public void setExpBeginDate(String expBeginDate) {
        this.expBeginDate = expBeginDate.trim();
    }

    /**
     * @return the expEndDate
     */
    public String getExpEndDate() {
        return expEndDate;
    }

    /**
     * @param expEndDate
     *            the expEndDate to set
     */
    public void setExpEndDate(String expEndDate) {
        this.expEndDate = expEndDate.trim();
    }

    /**
     * @return the beginDate
     */
    public String getBeginDate() {
        return beginDate;
    }

    /**
     * @param beginDate
     *            the beginDate to set
     */
    public void setBeginDate(String beginDate) {
        if (beginDate == null || beginDate.length() == 0) {
            this.beginDate = null;
        } else {
            this.beginDate = beginDate.trim();
        }
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(String endDate) {
        if (endDate == null || endDate.length() == 0) {
            this.endDate = null;
        } else {
            this.endDate = endDate.trim();
        }
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
     * @return the peopleNumber
     */
    public int getPeopleNumber() {
        return peopleNumber;
    }

    /**
     * @param peopleNumber
     *            the peopleNumber to set
     */
    public void setPeopleNumber(int peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    /**
     * @return the principalThey
     */
    public String getPrincipalThey() {
        return principalThey;
    }

    /**
     * @param principalThey
     *            the principalThey to set
     */
    public void setPrincipalThey(String principalThey) {
        this.principalThey = principalThey.trim();
    }

    /**
     * @return the principalWe
     */
    public String getPrincipalWe() {
        return principalWe;
    }

    /**
     * @param principalWe
     *            the principalWe to set
     */
    public void setPrincipalWe(String principalWe) {
        this.principalWe = principalWe.trim();
    }

    /**
     * @return the projectDetail
     */
    public String getProjectDetail() {
        return projectDetail;
    }

    /**
     * @param projectDetail
     *            the projectDetail to set
     */
    public void setProjectDetail(String projectDetail) {
        this.projectDetail = projectDetail.trim();
    }

    /**
     * @return the projectType
     */
    public String getProjectType() {
        return projectType;
    }

    /**
     * @param projectType
     *            the projectType to set
     */
    public void setProjectType(String projectType) {
        this.projectType = projectType.trim();
    }

    /**
     * @return the appraisal
     */
    public String getAppraisal() {
        return appraisal;
    }

    /**
     * @param appraisal
     *            the appraisal to set
     */
    public void setAppraisal(String appraisal) {
        this.appraisal = appraisal.trim();
    }

    /**
     * @return the isAbolished
     */
    public Boolean getIsAbolished() {
        return isAbolished;
    }

    /**
     * @param isAbolished
     *            the isAbolished to set
     */
    public void setIsAbolished(Boolean isAbolished) {
        this.isAbolished = isAbolished;
    }

    /**
     * @return the descriptions
     */
    public String getDescriptions() {
        return descriptions;
    }

    /**
     * @param descriptions
     *            the descriptions to set
     */
    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions.trim();
    }
}
