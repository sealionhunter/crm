package com.ustcsoft.gs.crm.webui.customer.dto;

public class CooperationProjectDto {

    private int cooperationProjectID;
    private int cooperatorID;
    private String projectName;
    private String expectedBeginTime;
    private String expectedEndTime;
    private String realBeginTime;
    private String realEndTime;
    private Integer projectScale;
    private Integer cooperatorScale;
    private Integer cooperatorPeopleNumber;
    private String principalWe;
    private String principalCooperator;
    private String principalCooperatorPhone;
    private String projectType;
    private String projectDetail;
    private String appraisal;
    private boolean isAbolished;

    /**
     * @return the cooperationProjectID
     */
    public int getCooperationProjectID() {
        return cooperationProjectID;
    }

    /**
     * @param cooperationProjectID
     *            the cooperationProjectID to set
     */
    public void setCooperationProjectID(int cooperationProjectID) {
        this.cooperationProjectID = cooperationProjectID;
    }

    /**
     * @return the cooperatorID
     */
    public int getCooperatorID() {
        return cooperatorID;
    }

    /**
     * @param cooperatorID
     *            the cooperatorID to set
     */
    public void setCooperatorID(int cooperatorID) {
        this.cooperatorID = cooperatorID;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getRealBeginTime() {
        return realBeginTime;
    }

    public String getRealEndTime() {
        return realEndTime;
    }

    public Integer getCooperatorScale() {
        return cooperatorScale;
    }

    public String getPrincipalWe() {
        return principalWe;
    }

    public String getPrincipalCooperator() {
        return principalCooperator;
    }

    public String getPrincipalCooperatorPhone() {
        return principalCooperatorPhone;
    }

    public String getProjectType() {
        return projectType;
    }

    public String getProjectDetail() {
        return projectDetail;
    }

    public String getAppraisal() {
        return appraisal;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName.trim();
    }

    public void setRealBeginTime(String realBeginTime) {
        if ("".equals(realBeginTime)) {
            this.realBeginTime = null;
        } else {
            this.realBeginTime = realBeginTime;
        }
    }

    public void setRealEndTime(String realEndTime) {
        if ("".equals(realEndTime)) {
            this.realEndTime = null;
        } else {
            this.realEndTime = realEndTime;
        }
    }

    public void setCooperatorScale(Integer cooperatorScale) {
        this.cooperatorScale = cooperatorScale;
    }

    public void setPrincipalWe(String principalWe) {
        this.principalWe = principalWe.trim();
    }

    public void setPrincipalCooperator(String principalCooperator) {
        this.principalCooperator = principalCooperator.trim();
    }

    public void setPrincipalCooperatorPhone(String principalCooperatorPhone) {
        this.principalCooperatorPhone = principalCooperatorPhone.trim();
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public void setProjectDetail(String projectDetail) {
        this.projectDetail = projectDetail.trim();
    }

    public void setAppraisal(String appraisal) {
        this.appraisal = appraisal.trim();
    }

    public boolean getIsAbolished() {
        return isAbolished;
    }

    public void setIsAbolished(boolean isAbolished) {
        this.isAbolished = isAbolished;
    }

    public String getExpectedBeginTime() {
        return expectedBeginTime;
    }

    public String getExpectedEndTime() {
        return expectedEndTime;
    }

    public void setExpectedBeginTime(String expectedBeginTime) {
        if ("".equals(expectedBeginTime)) {
            this.expectedBeginTime = null;
        } else {
            this.expectedBeginTime = expectedBeginTime;
        }
    }

    public void setExpectedEndTime(String expectedEndTime) {
        if ("".equals(expectedEndTime)) {
            this.expectedEndTime = null;
        } else {
            this.expectedEndTime = expectedEndTime;
        }
    }

    /**
     * @return the projectScale
     */
    public Integer getProjectScale() {
        return projectScale;
    }

    /**
     * @param projectScale
     *            the projectScale to set
     */
    public void setProjectScale(Integer projectScale) {
        this.projectScale = projectScale;
    }

    /**
     * @return the cooperatorPeopleNumber
     */
    public Integer getCooperatorPeopleNumber() {
        return cooperatorPeopleNumber;
    }

    /**
     * @param cooperatorPeopleNumber
     *            the cooperatorPeopleNumber to set
     */
    public void setCooperatorPeopleNumber(Integer cooperatorPeopleNumber) {
        this.cooperatorPeopleNumber = cooperatorPeopleNumber;
    }

    /**
     * @param isAbolished
     *            the isAbolished to set
     */
    public void setAbolished(boolean isAbolished) {
        this.isAbolished = isAbolished;
    }

}
