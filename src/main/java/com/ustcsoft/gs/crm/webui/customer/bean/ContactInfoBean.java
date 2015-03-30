package com.ustcsoft.gs.crm.webui.customer.bean;

public class ContactInfoBean {

    private int customerID = 0;
    private int contactID;
    private String contactName;
    private String department;
    private String job;

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
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department
     *            the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
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

}
