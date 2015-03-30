package com.ustcsoft.gs.crm.webui.contact.dto;

import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;

/**
 * ContactInfo Dto
 * 
 * @author xuzhen
 * 
 */
public class ContactInfoDto {
    private int contactID;

    private String contactName;

    private String sex;

    private String birthday;

    private String company;

    private String department;

    private String job;

    private String phoneNumber;

    private String nativePlace;

    private String contactNational;

    private String addr;

    private String education;

    private String health;

    private String email;

    private String fax;

    private String QQ;

    private String homePhone;

    private String political;

    private String hobby;

    private String jobResume;

    private String eduBackground;

    private Boolean isAbolished = false;

    private String descriptions;
    private int customerID;
    private String contactType;

    private String createTime = null;
    private String updateTime = null;

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
        this.contactName = CRMUtils.trim(contactName);
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex
     *            the sex to set
     */
    public void setSex(String sex) {
        this.sex = CRMUtils.trim(sex);
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
        if (birthday == null || birthday.length() == 0) {
            this.birthday = null;
        } else {
            this.birthday = CRMUtils.trim(birthday);
        }
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
        this.department = CRMUtils.trim(department);
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
        this.phoneNumber = CRMUtils.trim(phoneNumber);
    }

    /**
     * @return the nativePlace
     */
    public String getNativePlace() {
        return nativePlace;
    }

    /**
     * @param nativePlace
     *            the nativePlace to set
     */
    public void setNativePlace(String nativePlace) {
        this.nativePlace = CRMUtils.trim(nativePlace);
    }

    /**
     * @return the contactNational
     */
    public String getContactNational() {
        return contactNational;
    }

    /**
     * @param contactNational
     *            the contactNational to set
     */
    public void setContactNational(String contactNational) {
        this.contactNational = CRMUtils.trim(contactNational);
    }

    /**
     * @return the addr
     */
    public String getAddr() {
        return addr;
    }

    /**
     * @param addr
     *            the addr to set
     */
    public void setAddr(String addr) {
        this.addr = CRMUtils.trim(addr);
    }

    /**
     * @return the education
     */
    public String getEducation() {
        return education;
    }

    /**
     * @param education
     *            the education to set
     */
    public void setEducation(String education) {
        this.education = CRMUtils.trim(education);
    }

    /**
     * @return the health
     */
    public String getHealth() {
        return health;
    }

    /**
     * @param health
     *            the health to set
     */
    public void setHealth(String health) {
        this.health = CRMUtils.trim(health);
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = CRMUtils.trim(email);
    }

    /**
     * @return the fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax
     *            the fax to set
     */
    public void setFax(String fax) {
        this.fax = CRMUtils.trim(fax);
    }

    /**
     * @return the qQ
     */
    public String getQQ() {
        return QQ;
    }

    /**
     * @param qQ
     *            the qQ to set
     */
    public void setQQ(String qQ) {
        QQ = CRMUtils.trim(qQ);
    }

    /**
     * @return the homePhone
     */
    public String getHomePhone() {
        return homePhone;
    }

    /**
     * @param homePhone
     *            the homePhone to set
     */
    public void setHomePhone(String homePhone) {
        this.homePhone = CRMUtils.trim(homePhone);
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
     * @return the hobby
     */
    public String getHobby() {
        return hobby;
    }

    /**
     * @param hobby
     *            the hobby to set
     */
    public void setHobby(String hobby) {
        this.hobby = CRMUtils.trim(hobby);
    }

    /**
     * @return the jobResume
     */
    public String getJobResume() {
        return jobResume;
    }

    /**
     * @param jobResume
     *            the jobResume to set
     */
    public void setJobResume(String jobResume) {
        this.jobResume = jobResume;
    }

    /**
     * @return the eduBackground
     */
    public String getEduBackground() {
        return eduBackground;
    }

    /**
     * @param eduBackground
     *            the eduBackground to set
     */
    public void setEduBackground(String eduBackground) {
        this.eduBackground = eduBackground;
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

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
