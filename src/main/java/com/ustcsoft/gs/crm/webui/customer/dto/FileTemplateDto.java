package com.ustcsoft.gs.crm.webui.customer.dto;

public class FileTemplateDto {

    private int fileTemplateID = 0;
    private String fileTemplateName;
    private String fileTemplateAddDate;
    private String fileTemplateEditDate;
    private String descriptions = "";
    private Boolean isAbolished = false;
    private String fileTemplateValue;
    private int type;

    /**
     * @return the fileTemplateID
     */
    public int getFileTemplateID() {
        return fileTemplateID;
    }

    /**
     * @param fileTemplateID
     *            the fileTemplateID to set
     */
    public void setFileTemplateID(int fileTemplateID) {
        this.fileTemplateID = fileTemplateID;
    }

    /**
     * @return the fileTemplateName
     */
    public String getFileTemplateName() {
        return fileTemplateName;
    }

    /**
     * @param fileTemplateName
     *            the fileTemplateName to set
     */
    public void setFileTemplateName(String fileTemplateName) {
        this.fileTemplateName = fileTemplateName.trim();
    }

    /**
     * @return the fileTemplateAddDate
     */
    public String getFileTemplateAddDate() {
        return fileTemplateAddDate;
    }

    /**
     * @param fileTemplateAddDate
     *            the fileTemplateAddDate to set
     */
    public void setFileTemplateAddDate(String fileTemplateAddDate) {
        this.fileTemplateAddDate = fileTemplateAddDate;
    }

    /**
     * @return the fileTemplateEditDate
     */
    public String getFileTemplateEditDate() {
        return fileTemplateEditDate;
    }

    /**
     * @param fileTemplateEditDate
     *            the fileTemplateEditDate to set
     */
    public void setFileTemplateEditDate(String fileTemplateEditDate) {
        this.fileTemplateEditDate = fileTemplateEditDate;
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
        this.descriptions = descriptions;
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
     * @return the fileTemplateValue
     */
    public String getFileTemplateValue() {
        return fileTemplateValue;
    }

    /**
     * @param fileTemplateValue
     *            the fileTemplateValue to set
     */
    public void setFileTemplateValue(String fileTemplateValue) {
        this.fileTemplateValue = fileTemplateValue;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(int type) {
        this.type = type;
    }
}
