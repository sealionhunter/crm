package com.ustcsoft.gs.crm.webui.customer.dto;

public class LeaderAdviceDto {
    private int adviceID = 0;
    private int customerID = 0;
    private int userID = 0;
    private String adviceContent = null;
    private Boolean hasRead = false;
    private Boolean isAbolished = false;
    private String createTime = null;
    private String updateTime = null;

    public int getAdviceID() {
        return adviceID;
    }

    public void setAdviceID(int adviceID) {
        this.adviceID = adviceID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getAdviceContent() {
        return adviceContent;
    }

    public void setAdviceContent(String adviceContent) {
        this.adviceContent = adviceContent;
    }

    public boolean getHasRead() {
        return hasRead;
    }

    public void setHasRead(boolean hasRead) {
        this.hasRead = hasRead;
    }

    public boolean getIsAbolished() {
        return isAbolished;
    }

    public void setIsAbolished(boolean isAbolished) {
        this.isAbolished = isAbolished;
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
