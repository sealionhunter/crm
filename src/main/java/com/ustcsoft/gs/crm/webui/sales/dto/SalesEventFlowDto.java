/*
 * SalesEventFlowDto.java
 */
package com.ustcsoft.gs.crm.webui.sales.dto;

/**
 * SalesEventFlow entity.
 * 
 * @author shiben
 */

public class SalesEventFlowDto {

    // Fields

    private Integer id = 0;
    private Integer eventID = 0;
    private Integer status = 0;
    private String demand = null;

    // Constructors

    /** default constructor */
    public SalesEventFlowDto() {
    }

    /** minimal constructor */
    public SalesEventFlowDto(Integer id, Integer eventID, Integer status) {
        this.id = id;
        this.eventID = eventID;
        this.status = status;
    }

    public SalesEventFlowDto(String demand, Integer status) {
        this.demand = demand;
        this.status = status;
    }

    /** full constructor */
    public SalesEventFlowDto(Integer id, Integer eventID, Integer status, String demand) {
        this.id = id;
        this.eventID = eventID;
        this.status = status;
        this.demand = demand;
    }

    // Property accessors

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

}