package com.ustcsoft.gs.crm.webui.contact.bean;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;

/**
 * search conditions
 * 
 * @author xuzhen
 */
public class ContactSearchBean {
    /** simple search conditions */
    private String searchText = CRMConstant.PER_CENT + CRMConstant.PER_CENT;

    /** expert search:Company conditions */
    private String contactCompany = CRMConstant.PER_CENT + CRMConstant.PER_CENT;

    /** expert search:Department conditions */
    private String contactDepartment = CRMConstant.PER_CENT + CRMConstant.PER_CENT;

    /** expert search:Job conditions */
    private String contactJob = CRMConstant.PER_CENT + CRMConstant.PER_CENT;

    /** expert search:Name conditions */
    private String contactName = CRMConstant.PER_CENT + CRMConstant.PER_CENT;

    /**
     * 
     * @return searchText
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * 
     * @param searchText
     *            the searchText to set
     */
    public void setSearchText(String searchText) {
        this.searchText = CRMUtils.trimSearch(searchText);
    }

    /**
     * 
     * @return contactCompany
     */
    public String getContactCompany() {
        return contactCompany;
    }

    /**
     * 
     * @param contactCompany
     *            the contactCompany to set
     */
    public void setContactCompany(String contactCompany) {
        this.contactCompany = CRMUtils.trimSearch(contactCompany);
    }

    /**
     * 
     * @return contactDepartment
     */
    public String getContactDepartment() {
        return contactDepartment;
    }

    /**
     * 
     * @param contactDepartment
     *            the contactDepartment to set
     */
    public void setContactDepartment(String contactDepartment) {
        this.contactDepartment = CRMUtils.trimSearch(contactDepartment);
    }

    /**
     * 
     * @return contactJob
     */
    public String getContactJob() {
        return contactJob;
    }

    /**
     * 
     * @param contactJob
     *            the contactJob to set
     */
    public void setContactJob(String contactJob) {
        this.contactJob = CRMUtils.trimSearch(contactJob);
    }

    /**
     * 
     * @return contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * 
     * @param contactName
     *            the contactName to set
     */
    public void setContactName(String contactName) {
        this.contactName = CRMUtils.trimSearch(contactName);
    }
}
