package com.ustcsoft.gs.crm.webui.customer.dto;

/**
 * cooperator analysis dto.
 * 
 * @author xujueyin
 * 
 */
public class CopAnalysisDto {
    /** cooperator analysis ID */
    private int copAnalysisID;
    /** cooperator ID */
    private int cooperatorID;
    /** cooperator advantage field */
    private String advantageField;
    /** cooperator disadvantage field */
    private String disadvantageField;
    /** cooperator management ability */
    private String managementAbility;
    /** cooperator response speed */
    private String responseSpeed;
    /** cooperator trust degree */
    private String trustDegree;
    /** cooperator engineering level evaluation */
    private String engLevelEvaluation;
    /** cooperator summarize */
    private String cooperationSummarize;
    /** cooperator comprehensive analysis */
    private String comphsAnalysis;
    /** cooperator descriptions */
    private String descriptions;
    /** cooperator analysis created time */
    private String createTime;
    /** remark it delete or not */
    private Boolean isAbolished = false;

    /**
     * @return the copAnalysisID
     */
    public int getCopAnalysisID() {
        return copAnalysisID;
    }

    /**
     * @param copAnalysisID
     *            the copAnalysisID to set
     */
    public void setCopAnalysisID(int copAnalysisID) {
        this.copAnalysisID = copAnalysisID;
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

    /**
     * @return the advantageField
     */
    public String getAdvantageField() {
        return advantageField;
    }

    /**
     * @param advantageField
     *            the advantageField to set
     */
    public void setAdvantageField(String advantageField) {
        this.advantageField = advantageField.trim();
    }

    /**
     * @return the disadvantageField
     */
    public String getDisadvantageField() {
        return disadvantageField;
    }

    /**
     * @param disadvantageField
     *            the disadvantageField to set
     */
    public void setDisadvantageField(String disadvantageField) {
        this.disadvantageField = disadvantageField.trim();
    }

    /**
     * @return the managementAbility
     */
    public String getManagementAbility() {
        return managementAbility;
    }

    /**
     * @param managementAbility
     *            the managementAbility to set
     */
    public void setManagementAbility(String managementAbility) {
        this.managementAbility = managementAbility;
    }

    /**
     * @return the responseSpeed
     */
    public String getResponseSpeed() {
        return responseSpeed;
    }

    /**
     * @param responseSpeed
     *            the responseSpeed to set
     */
    public void setResponseSpeed(String responseSpeed) {
        this.responseSpeed = responseSpeed;
    }

    /**
     * @return the trustDegree
     */
    public String getTrustDegree() {
        return trustDegree;
    }

    /**
     * @param trustDegree
     *            the trustDegree to set
     */
    public void setTrustDegree(String trustDegree) {
        this.trustDegree = trustDegree;
    }

    /**
     * @return the engLevelEvaluation
     */
    public String getEngLevelEvaluation() {
        return engLevelEvaluation;
    }

    /**
     * @param engLevelEvaluation
     *            the engLevelEvaluation to set
     */
    public void setEngLevelEvaluation(String engLevelEvaluation) {
        this.engLevelEvaluation = engLevelEvaluation.trim();
    }

    /**
     * @return the cooperationSummarize
     */
    public String getCooperationSummarize() {
        return cooperationSummarize;
    }

    /**
     * @param cooperationSummarize
     *            the cooperationSummarize to set
     */
    public void setCooperationSummarize(String cooperationSummarize) {
        this.cooperationSummarize = cooperationSummarize.trim();
    }

    /**
     * @return the comphsAnalysis
     */
    public String getComphsAnalysis() {
        return comphsAnalysis;
    }

    /**
     * @param comphsAnalysis
     *            the comphsAnalysis to set
     */
    public void setComphsAnalysis(String comphsAnalysis) {
        this.comphsAnalysis = comphsAnalysis.trim();
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

    /**
     * @return the createTime
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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
}
