package com.ustcsoft.gs.crm.webui.customer.dto;

public class OrderTrackDto {

    /** order track id */
    private int orderTrackNo = 0;

    /** ourRepresentative */
    private int editPeople = 0;

    /** the orderID of order we see */
    private String orderID = null;

    /** after start */
    private String afterState = null;

    /** record time */
    private String recordTime = null;

    /** whether is abolished or not */
    private Boolean isAbolished = false;

    /**
     * @return the orderTrackNo
     */
    public int getOrderTrackNo() {
        return orderTrackNo;
    }

    /**
     * @param orderTrackNo
     *            the orderTrackNo to set
     */
    public void setOrderTrackNo(int orderTrackNo) {
        this.orderTrackNo = orderTrackNo;
    }

    /**
     * @return the editPeople
     */
    public int getEditPeople() {
        return editPeople;
    }

    /**
     * @param editPeople
     *            the editPeople to set
     */
    public void setEditPeople(int editPeople) {
        this.editPeople = editPeople;
    }

    /**
     * @return the orderID
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * @param orderID
     *            the orderID to set
     */
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * @return the afterState
     */
    public String getAfterState() {
        return afterState;
    }

    /**
     * @param afterState
     *            the afterState to set
     */
    public void setAfterState(String afterState) {
        this.afterState = afterState;
    }

    /**
     * @return the recordTime
     */
    public String getRecordTime() {
        return recordTime;
    }

    /**
     * @param recordTime
     *            the recordTime to set
     */
    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    /**
     * @return the isAbolished
     */
    public Boolean getIsAbolished() {
        return isAbolished;
    }

    /**
     * @param isAbolished
     *            the isAbolished to set
     */
    public void setIsAbolished(Boolean isAbolished) {
        this.isAbolished = isAbolished;
    }
}
