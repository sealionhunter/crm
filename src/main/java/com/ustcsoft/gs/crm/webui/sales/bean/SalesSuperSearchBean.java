/*
 * SalesSuperSearchBean.java
 */
package com.ustcsoft.gs.crm.webui.sales.bean;

import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;

/**
 * 用于查询存放的bean
 * 
 * @author chenguangzhou and shiben
 * 
 */
public class SalesSuperSearchBean {
    private String searchKey = null;
    private String eventName = null;
    private String customerName = null;
    private String submitPersonName = null;
    private String status = null;
    // 查询起始日期
    private String dateFrom = null;
    // 查询结束日期
    private String dateTo = null;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = CRMUtils.trimSearch(customerName);
    }

    public String getSubmitPersonName() {
        return submitPersonName;
    }

    public void setSubmitPersonName(String submitPersonName) {
        this.submitPersonName = CRMUtils.trimSearch(submitPersonName);
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = CRMUtils.trimSearch(eventName);
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = CRMUtils.trimSearch(searchKey);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
