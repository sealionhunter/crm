/*
 * SalesEventFlowBean.java
 */
package com.ustcsoft.gs.crm.webui.sales.bean;

/**
 * SalesEventFlowBean
 * 
 * @author shiben
 * 
 */
public class SalesEventFlowBean {
    private int eventID = 0;
    private int id = 0;
    // tabpanel对应的事件状态
    private String status = null;
    // tabpanel的标题
    private String title = null;
    // 对应事件状态的需求信息
    private String demand = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }
}
