package com.ustcsoft.gs.crm.webui.customer.bean;

import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;

/**
 * simple and super query bean.
 * 
 * @author xujueyin
 * 
 */
public class CustomerSearchBean {
    private String searchText = "";
    private Integer[] userID = null;
    private String customerName = "";
    private String holder = "";
    private String[] scale = {};
    private String[] industry = {};
    private String[] fee = {};
    private String earning = "";
    private String customerAddr = "";

    /**
     * @return the simple
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * @param searchText
     *            the simple to set
     */
    public void setSearchText(String searchText) {
        this.searchText = CRMUtils.trimSearch(searchText);
    }

    /**
     * @return the cusName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName
     *            the cusName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = CRMUtils.trimSearch(customerName);
    }

    /**
     * @return the holder
     */
    public String getHolder() {
        return holder;
    }

    /**
     * @param holder
     *            the holder to set
     */
    public void setHolder(String holder) {
        this.holder = holder;
    }

    /**
     * @return the scale
     */
    public String[] getScale() {
        return scale;
    }

    /**
     * @param scale
     *            the scale to set
     */
    public void setScale(String[] scale) {
        this.scale = scale;
    }

    /**
     * @return the industry
     */
    public String[] getIndustry() {
        return industry;
    }

    /**
     * @param industry
     *            the industry to set
     */
    public void setIndustry(String[] industry) {
        this.industry = industry;
    }

    /**
     * @return the fee
     */
    public String[] getFee() {
        return fee;
    }

    /**
     * @param fee
     *            the fee to set
     */
    public void setFee(String[] fee) {
        this.fee = fee;
    }

    /**
     * @return the earning
     */
    public String getEarning() {
        return earning;
    }

    /**
     * @param earning
     *            the earning to set
     */
    public void setEarning(String earning) {
        this.earning = earning;
    }

    /**
     * @return the cusAddr
     */
    public String getCustomerAddr() {
        return customerAddr;
    }

    /**
     * @param customerAddr
     *            the cusAddr to set
     */
    public void setCustomerAddr(String customerAddr) {
        this.customerAddr = CRMUtils.trimSearch(customerAddr);
    }

    public Integer[] getUserID() {
        return userID;
    }

    public void setUserID(Integer[] userID) {
        this.userID = userID;
    }
}
