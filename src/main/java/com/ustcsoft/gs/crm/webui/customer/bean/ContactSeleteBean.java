package com.ustcsoft.gs.crm.webui.customer.bean;

public class ContactSeleteBean {
    private int contactSelectID;
    private int contactID;
    private int objectID;
    private String contactName;
    private String company;
    private String job;
    private String phoneNumber;

    /**
     * @return the contactSelectID
     */
    public int getContactSelectID() {
        return contactSelectID;
    }

    /**
     * @param contactSelectID
     *            the contactSelectID to set
     */
    public void setContactSelectID(int contactSelectID) {
        this.contactSelectID = contactSelectID;
    }

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

    public int getObjectID() {
        return objectID;
    }

    public void setObjectID(int objectID) {
        this.objectID = objectID;
    }

    /**
     * @return the contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contactName
     *            the contactName to set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
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
        this.company = company;
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
        this.job = job;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber
     *            the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
