/*
 * SalesTrackDto.java
 */
package com.ustcsoft.gs.crm.webui.sales.dto;

/**
 * SalesTrackInfo entity.
 * 
 * @author shiben
 */

public class SalesTrackDto {

    // id
    private Integer salesTrackNo = 0;
    // 添加人姓名
    private Integer submitterID = 0;
    // 销售事件id
    private Integer eventID = 0;
    private Integer customerID = 0;
    private String status = null;
    private String submitDate = null;
    private Boolean isAbolished = false;

    // Constructors

    /** default constructor */
    public SalesTrackDto() {
    }

    // Property accessors

    public Integer getSalesTrackNo() {
        return salesTrackNo;
    }

    public void setSalesTrackNo(Integer salesTrackNo) {
        this.salesTrackNo = salesTrackNo;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSubmitterID() {
        return submitterID;
    }

    public void setSubmitterID(Integer submitterID) {
        this.submitterID = submitterID;
    }

    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public Boolean getIsAbolished() {
        return isAbolished;
    }

    public void setIsAbolished(Boolean isAbolished) {
        this.isAbolished = isAbolished;
    }
}