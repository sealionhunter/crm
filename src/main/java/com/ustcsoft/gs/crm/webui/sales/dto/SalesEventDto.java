/*
 * SalesEventDto.java
 */
package com.ustcsoft.gs.crm.webui.sales.dto;

import java.util.List;

/**
 * 
 * @author chenguangzhou and shiben
 * 
 */
public class SalesEventDto {
    // 事件标识ID
    private int eventID = 0;
    private String eventName = null;
    private int customerID = 0;
    // 联系人ID
    private int contactID = 0;
    private int submitterID = 0;
    // 现处状态
    private String status = null;
    // 事件开始日期
    private String submitDate = null;
    // 销售时间备注
    private String remarks = null;
    private Boolean isAbolished = false;
    private String nextDemand = null;
    private List<String> demand;
    private List<String> demandStatus;

    public List<String> getDemandStatus() {
        return demandStatus;
    }

    public void setDemandStatus(List<String> demandStatus) {
        this.demandStatus = demandStatus;
    }

    public List<String> getDemand() {
        return demand;
    }

    public void setDemand(List<String> demand) {
        this.demand = demand;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public int getSubmitterID() {
        return submitterID;
    }

    public void setSubmitterID(int submitterID) {
        this.submitterID = submitterID;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean getIsAbolished() {
        return isAbolished;
    }

    public void setIsAbolished(Boolean isAbolished) {
        this.isAbolished = isAbolished;
    }

    public String getNextDemand() {
        return nextDemand;
    }

    public void setNextDemand(String nextDemand) {
        this.nextDemand = nextDemand;
    }

}
