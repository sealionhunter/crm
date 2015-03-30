/**
 * 
 */
package com.ustcsoft.gs.crm.webui.contact.dto;

import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;

/**
 * @author shenkaichuan
 * 
 */
public class People {

    /** contact ID */
    private int contactID;

    /** birthday */
    private String birthday;

    /** political */
    private String political;

    /** company */
    private String company;

    /** job */
    private String job;

    /** descriptions */
    private String descriptions;

    /** isAbolished */
    private Boolean isAbolished = false;

    /**
     * @return the contactID
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * @param contactID
     *            the contactID to set
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * @return the birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     *            the birthday to set
     */
    public void setBirthday(String birthday) {
        this.birthday = CRMUtils.trim(birthday);
    }

    /**
     * @return the political
     */
    public String getPolitical() {
        return political;
    }

    /**
     * @param political
     *            the political to set
     */
    public void setPolitical(String political) {
        this.political = CRMUtils.trim(political);
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company
     *            the company to set
     */
    public void setCompany(String company) {
        this.company = CRMUtils.trim(company);
    }

    /**
     * @return the job
     */
    public String getJob() {
        return job;
    }

    /**
     * @param job
     *            the job to set
     */
    public void setJob(String job) {
        this.job = CRMUtils.trim(job);
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
        this.descriptions = CRMUtils.trim(descriptions);
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
