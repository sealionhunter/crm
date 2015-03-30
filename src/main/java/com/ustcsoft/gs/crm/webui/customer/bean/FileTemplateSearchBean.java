package com.ustcsoft.gs.crm.webui.customer.bean;

import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;

/**
 * FileTemplateSearchBean
 * 
 * @author heweiwei
 * 
 */
public class FileTemplateSearchBean {

    private String searchText = "";
    private String fileTemplateName = "";
    private String fileTemplateDescriptions = "";
    private String fileTemplateAddDateStart = "";
    private String fileTemplateAddDateEnd = "";
    private String fileTemplateEditDateStart = "";
    private String fileTemplateEditDateEnd = "";
    private int type = 1;

    /**
     * @return the searchText
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * @param searchText
     *            the searchText to set
     */
    public void setSearchText(String searchText) {
        this.searchText = CRMUtils.trimSearch(searchText);
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
        this.fileTemplateName = CRMUtils.trimSearch(fileTemplateName);
    }

    /**
     * @return the fileTemplateDescriptions
     */
    public String getFileTemplateDescriptions() {
        return fileTemplateDescriptions;
    }

    /**
     * @param fileTemplateDescriptions
     *            the fileTemplateDescriptions to set
     */
    public void setFileTemplateDescriptions(String fileTemplateDescriptions) {
        this.fileTemplateDescriptions = CRMUtils.trimSearch(fileTemplateDescriptions);
    }

    /**
     * @return the fileTemplateAddDateStart
     */
    public String getFileTemplateAddDateStart() {
        return fileTemplateAddDateStart;
    }

    /**
     * @param fileTemplateAddDateStart
     *            the fileTemplateAddDateStart to set
     */
    public void setFileTemplateAddDateStart(String fileTemplateAddDateStart) {
        this.fileTemplateAddDateStart = fileTemplateAddDateStart;
    }

    /**
     * @return the fileTemplateAddDateEnd
     */
    public String getFileTemplateAddDateEnd() {
        return fileTemplateAddDateEnd;
    }

    /**
     * @param fileTemplateAddDateEnd
     *            the fileTemplateAddDateEnd to set
     */
    public void setFileTemplateAddDateEnd(String fileTemplateAddDateEnd) {
        this.fileTemplateAddDateEnd = fileTemplateAddDateEnd;
    }

    /**
     * @return the fileTemplateEditDateStart
     */
    public String getFileTemplateEditDateStart() {
        return fileTemplateEditDateStart;
    }

    /**
     * @param fileTemplateEditDateStart
     *            the fileTemplateEditDateStart to set
     */
    public void setFileTemplateEditDateStart(String fileTemplateEditDateStart) {
        this.fileTemplateEditDateStart = fileTemplateEditDateStart;
    }

    /**
     * @return the fileTemplateEditDateEnd
     */
    public String getFileTemplateEditDateEnd() {
        return fileTemplateEditDateEnd;
    }

    /**
     * @param fileTemplateEditDateEnd
     *            the fileTemplateEditDateEnd to set
     */
    public void setFileTemplateEditDateEnd(String fileTemplateEditDateEnd) {
        this.fileTemplateEditDateEnd = fileTemplateEditDateEnd;
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
