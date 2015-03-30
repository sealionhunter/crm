/*
 * SalesEventBean.java
 */
package com.ustcsoft.gs.crm.webui.sales.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 销售事件返回前台的bean
 * 
 * @author chenguangzhou and shiben
 * 
 */
public class SalesEventBean {
    private int eventID = 0;
    private String eventName = null;
    private int customerID = 0;
    private String customerName = null;
    private int contactID = 0;
    private String contactName = null;
    private String submitPersonName = null;
    private String status = null;
    private String statusName = null;
    private String submitDate = null;
    private String remarks = null;
    private Boolean isAbolished = false;
    private int sort = 0;
    private List<String> demand = new ArrayList<String>();

    public SalesEventBean(int eventID, String eventName, int customerID, String customerName,
            int contactID, String contactName, String submitPersonName, String status,
            String statusName, String submitDate, String remarks, Boolean isAbolished, int sort) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.customerID = customerID;
        this.customerName = customerName;
        this.contactID = contactID;
        this.contactName = contactName;
        this.submitPersonName = submitPersonName;
        this.status = status;
        this.statusName = statusName;
        this.submitDate = submitDate;
        this.remarks = remarks;
        this.isAbolished = isAbolished;
        this.sort = sort;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getSubmitPersonName() {
        return submitPersonName;
    }

    public void setSubmitPersonName(String submitPersonName) {
        this.submitPersonName = submitPersonName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getSubmitDate() {
        return submitDate.substring(0, 10);
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<String> getDemand() {
        return demand;
    }

    public void setDemand(List<String> demand) {
        this.demand = demand;
    }

    public Boolean getIsAbolished() {
        return isAbolished;
    }

    public void setIsAbolished(Boolean isAbolished) {
        this.isAbolished = isAbolished;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
