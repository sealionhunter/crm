package com.ustcsoft.gs.crm.webui.system.dto;

public class UserInfoDto {
    private int userID = 0;
    private int groupID = 0;
    private String jobID = null;
    private String password;
    private String userName = null;
    private String realName = null;
    private String company = null;
    private int departmentID = 0;
    private int projectTeamID = 0;
    private String job = null;
    private String jobTitle = null;
    private String email = null;
    private String mobile = null;
    private String officePhone = null;
    private String education = null;
    private String entryTime = null;
    private String descriptions = null;
    private Boolean isAbolished = false;

    /**
     * 
     * @return userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * 
     * @param userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * 
     * @return groupID
     */
    public int getGroupID() {
        return groupID;
    }

    /**
     * 
     * @param groupID
     */
    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    /**
     * 
     * @return jobID
     */
    public String getJobID() {
        return jobID;
    }

    /**
     * 
     * @param jobID
     */
    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    /**
     * 
     * @return password
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;

    }

    /**
     * 
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 
     * @return realName
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 
     * @param realName
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 
     * @return company
     */
    public String getCompany() {
        return company;
    }

    /**
     * 
     * @param company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 
     * @return departmentID
     */
    public int getDepartmentID() {
        return departmentID;
    }

    /**
     * 
     * @param departmentID
     */
    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    /**
     * 
     * @return projectTeamID
     */
    public int getProjectTeamID() {
        return projectTeamID;
    }

    /**
     * 
     * @param projectTeamID
     */
    public void setProjectTeamID(int projectTeamID) {
        this.projectTeamID = projectTeamID;
    }

    /**
     * 
     * @return job
     */
    public String getJob() {
        return job;
    }

    /**
     * 
     * @param job
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * 
     * @return jobTitle
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * 
     * @param jobTitle
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * 
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 
     * @return officePhone
     */
    public String getOfficePhone() {
        return officePhone;
    }

    /**
     * 
     * @param officePhone
     */
    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    /**
     * 
     * @return education
     */
    public String getEducation() {
        return education;
    }

    /**
     * 
     * @param education
     */
    public void setEducation(String education) {
        this.education = education;
    }

    /**
     * 
     * @return entryTime
     */
    public String getEntryTime() {
        return entryTime;
    }

    /**
     * 
     * @param entryTime
     */
    public void setEntryTime(String entryTime) {
        if (entryTime == null) {
            this.entryTime = entryTime;
        } else if (entryTime.trim().isEmpty()) {
            this.entryTime = null;
        } else {
            this.entryTime = entryTime.trim();
        }
    }

    /**
     * 
     * @return descriptions
     */
    public String getDescriptions() {
        return descriptions;
    }

    /**
     * 
     * @param descriptions
     */
    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    /**
     * 
     * @return isAbolished
     */
    public Boolean getIsAbolished() {
        return isAbolished;
    }

    /**
     * 
     * @param isAbolished
     */
    public void setIsAbolished(Boolean isAbolished) {
        this.isAbolished = isAbolished;
    }
}
