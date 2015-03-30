/*
 * SalesTrackBean.java
 */
package com.ustcsoft.gs.crm.webui.sales.bean;

/**
 * 销售事件履历bean
 * 
 * @author shiben
 * 
 */
public class SalesTrackBean {
    private Integer salesTrackNo = 0;
    private Integer editPeople = 0;
    private Integer eventID = 0;
    private Integer customerID = 0;
    private String status = null;
    private String recordTime = null;
    private String demand = null;
    private Boolean isAbolished = false;

    public Integer getSalesTrackNo() {
        return salesTrackNo;
    }

    public void setSalesTrackNo(Integer salesTrackNo) {
        this.salesTrackNo = salesTrackNo;
    }

    public Integer getEditPeople() {
        return editPeople;
    }

    public void setEditPeople(Integer editPeople) {
        this.editPeople = editPeople;
    }

    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
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

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public Boolean getIsAbolished() {
        return isAbolished;
    }

    public void setIsAbolished(Boolean isAbolished) {
        this.isAbolished = isAbolished;
    }

}
