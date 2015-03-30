package com.ustcsoft.gs.crm.webui.customer.bean;

/**
 * class for get contractTemplate infomation from contractTemplate
 * 
 * @author zhangqiuli
 */
public class FileTemplateNameBean {

    private int fileTemplateID;
    private String fileTemplateName;
    private String fileTemplateValue;
    private int type;

    /**
     * the method of get fileTemplateID
     * 
     * @return fileTemplateID
     */
    public int getFileTemplateID() {
        return fileTemplateID;
    }

    /**
     * the method of get fileTemplateName
     * 
     * @return fileTemplateName
     */
    public String getFileTemplateName() {
        return fileTemplateName;
    }

    /**
     * the method of get fileTemplateValue
     * 
     * @return fileTemplateValue
     */
    public String getFileTemplateValue() {
        return fileTemplateValue;
    }

    /**
     * the fileTemplateID to set
     * 
     * @param fileTemplateID
     */
    public void setFileTemplateID(final int fileTemplateID) {
        this.fileTemplateID = fileTemplateID;
    }

    /**
     * the fileTemplateName to set
     * 
     * @param fileTemplateName
     */
    public void setFileTemplateName(final String fileTemplateName) {
        this.fileTemplateName = fileTemplateName.trim();
    }

    /**
     * the fileTemplateValue to set
     * 
     * @param fileTemplateValue
     */
    public void setFileTemplateValue(final String fileTemplateValue) {
        this.fileTemplateValue = fileTemplateValue.trim();
    }

    /**
     * method of get type
     * 
     * @return type
     */
    public int getType() {
        return type;
    }

    /**
     * type to set
     * 
     * @param type
     */
    public void setType(final int type) {
        this.type = type;
    }

}
