package com.ustcsoft.gs.crm.webui.customer.dto;

public class BusinessDto {
    private int businessId = 0;
    private int customerID = 0;
    private String businessType = null;
    private String contractYear = null;
    private String contractNumber = null;
    private String contractMoney = null;
    private String startDate = null;
    private String descriptions = null;
    private Boolean isAbolished = false;
    private String createTime = null;
    private String updateTime = null;
    public int getBusinessId() {
        return businessId;
    }
    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }
    public int getCustomerID() {
        return customerID;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public String getBusinessType() {
        return businessType;
    }
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
    public String getContractYear() {
        return contractYear;
    }
    public void setContractYear(String contractYear) {
        this.contractYear = contractYear;
    }
    public String getContractNumber() {
        return contractNumber;
    }
    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }
    public String getContractMoney() {
        return contractMoney;
    }
    public void setContractMoney(String contractMoney) {
        this.contractMoney = contractMoney;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getDescriptions() {
        return descriptions;
    }
    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }
    public Boolean getIsAbolished() {
        return isAbolished;
    }
    public void setIsAbolished(Boolean isAbolished) {
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
