package com.ustcsoft.gs.crm.webui.customer.dto;

/**
 * @author zhangqiuli
 */
public class ProposalOrContractDto {

    private int proposalOrContractID;
    private String proposalOrContractName;
    private String proposalOrContractObject;
    private String fileTemplateName;
    private String proposalOrContractAddDate;
    private String proposalOrContractEditDate;
    private String descriptions;
    private String proposalOrContractValue;
    private String proposalOrContractType;
    private boolean isAbolished;
    private int type;

    private String photoPath = null;

    /**
     * @return proposalOrContractID
     */
    public int getProposalOrContractID() {
        return proposalOrContractID;
    }

    /**
     * get method for proposalOrContractName
     * 
     * @return proposalOrContractName
     */
    public String getProposalOrContractName() {
        return proposalOrContractName;
    }

    /**
     * get method for proposalOrContractObject
     * 
     * @return proposalOrContractObject
     */
    public String getProposalOrContractObject() {
        return proposalOrContractObject;
    }

    /**
     * @return fileTemplateName
     */
    public String getFileTemplateName() {
        return fileTemplateName;
    }

    /**
     * @return proposalOrContractAddDate
     */
    public String getProposalOrContractAddDate() {
        return proposalOrContractAddDate;
    }

    /**
     * @return proposalOrContractEditDate
     */
    public String getProposalOrContractEditDate() {
        return proposalOrContractEditDate;
    }

    /**
     * descriptions to get
     * 
     * @return descriptions
     */
    public String getDescriptions() {
        return descriptions;
    }

    /**
     * proposalOrContractValue's get method
     * 
     * @return proposalOrContractValue
     */
    public String getProposalOrContractValue() {
        return proposalOrContractValue;
    }

    /**
     * get method for proposalOrContractType
     * 
     * @return proposalOrContractType
     */
    public String getProposalOrContractType() {
        return proposalOrContractType;
    }

    /**
     * @return isAbolished
     */
    public Boolean getIsAbolished() {
        return isAbolished;
    }

    /**
     * isAbolished to set
     * 
     * @param isAbolished
     */
    public void setIsAbolished(final Boolean isAbolished) {
        this.isAbolished = isAbolished;
    }

    /**
     * @return type
     */
    public int getType() {
        return type;
    }

    /**
     * proposalOrContractID to set
     * 
     * @param proposalOrContractID
     */
    public void setProposalOrContractID(final int proposalOrContractID) {
        this.proposalOrContractID = proposalOrContractID;
    }

    /**
     * proposalOrContractName to set
     * 
     * @param proposalOrContractName
     */
    public void setProposalOrContractName(final String proposalOrContractName) {
        this.proposalOrContractName = proposalOrContractName;
    }

    /**
     * proposalOrContractObject to set
     * 
     * @param proposalOrContractObject
     */
    public void setProposalOrContractObject(final String proposalOrContractObject) {
        this.proposalOrContractObject = proposalOrContractObject;
    }

    /**
     * fileTemplateName to set
     * 
     * @param fileTemplateName
     */
    public void setFileTemplateName(final String fileTemplateName) {
        this.fileTemplateName = fileTemplateName;
    }

    /**
     * proposalOrContractAddDate to set
     * 
     * @param proposalOrContractAddDate
     */
    public void setProposalOrContractAddDate(final String proposalOrContractAddDate) {
        this.proposalOrContractAddDate = proposalOrContractAddDate;
    }

    /**
     * proposalOrContractEditDate to set
     * 
     * @param proposalOrContractEditDate
     */
    public void setProposalOrContractEditDate(final String proposalOrContractEditDate) {
        this.proposalOrContractEditDate = proposalOrContractEditDate;
    }

    /**
     * descriptions to set
     * 
     * @param descriptions
     */
    public void setDescriptions(final String descriptions) {
        this.descriptions = descriptions;
    }

    /**
     * proposalOrContractValue to set
     * 
     * @param proposalOrContractValue
     */
    public void setProposalOrContractValue(final String proposalOrContractValue) {
        this.proposalOrContractValue = proposalOrContractValue;
    }

    /**
     * proposalOrContractType to set
     * 
     * @param proposalOrContractType
     */
    public void setProposalOrContractType(final String proposalOrContractType) {
        this.proposalOrContractType = proposalOrContractType;
    }

    /**
     * type to set
     * 
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

}
