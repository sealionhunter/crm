package com.ustcsoft.gs.crm.webui.count.dto;

/**
 * 
 * @author jiaxu
 * 
 */
public class SalesCountDto {

    private boolean isAbolished;

    private int salesTrackID;

    private String status;

    private String submitDate;

    /**
     * 
     * @return salesTrackID
     */
    public int getSalesTrackID() {
        return salesTrackID;
    }

    /**
     * 
     * @param salesTrackID
     */
    public void setSalesTrackID(int salesTrackID) {
        this.salesTrackID = salesTrackID;
    }

    /**
     * 
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return submitDate
     */
    public String getSubmitDate() {
        return submitDate;
    }

    /**
     * 
     * @param submitDate
     */
    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    /**
     * 
     * @return isAbolished
     */
    public boolean getIsAbolished() {
        return isAbolished;
    }

    /**
     * 
     * @param isAbolished
     */
    public void setIsAbolished(boolean isAbolished) {
        this.isAbolished = isAbolished;
    }
}
