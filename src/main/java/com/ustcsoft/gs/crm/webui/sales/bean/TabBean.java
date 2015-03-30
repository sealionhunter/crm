/*
 * TabBean.java
 */
package com.ustcsoft.gs.crm.webui.sales.bean;

/**
 * 查询tabpanel的bean
 * 
 * @author chenguangzhou and shiben
 * 
 */
public class TabBean {
    // tabpanel对应的事件状态
    private int status = 0;
    // tabpanel的标题
    private String title = null;
    // 对应事件状态的需求信息
    private String demands = "";
    private int sort = 0;

    public int getStatus() {
        return status;
    }

    public TabBean(int status, String title) {
        this.status = status;
        this.title = title;
    }

    public TabBean(int status, String title, String demands, int sort) {
        this.status = status;
        this.title = title;
        setDemands(demands);
        this.sort = sort;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDemands() {
        return demands;
    }

    public void setDemands(String demands) {
        this.demands = demands;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
