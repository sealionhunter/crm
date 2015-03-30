/**
 * 
 */
package com.ustcsoft.gs.crm.webui.customer.bean;

/**
 * 
 * @author tangyunpeng
 * 
 */
public class TransferBean {

    private int customerID = 0;
    private String customerName = null;
    private int holder = 0;
    private String holderName = null;
    private String fee = null;
    private String feeName = null;
    private String customerAddr = null;

    /**
	 * 
	 */
    public TransferBean() {

    }

    /**
     * 
     * @param feeName
     * @param customerID
     * @param customerAddr
     * @param customerName
     * @param holder
     * @param fee
     */
    public TransferBean(String feeName, String holderName, int customerID, String customerAddr,
            String customerName, int holder, String fee) {

        this.customerID = customerID;
        this.customerAddr = customerAddr;
        this.customerName = customerName;
        this.fee = fee;
        this.feeName = feeName;
        this.holder = holder;
        this.holderName = holderName;
    }

    /**
     * 
     * @param feeName
     * @param customerID
     * @param customerAddr
     * @param customerName
     * @param fee
     */
    public TransferBean(String feeName, int customerID, String customerAddr, String customerName,
            String fee) {

        this.customerID = customerID;
        this.customerAddr = customerAddr;
        this.customerName = customerName;
        this.fee = fee;
        this.feeName = feeName;
    }

    /**
     * @return the customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID
     *            the customerID to set
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName
     *            the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the holder
     */
    public int getHolder() {
        return holder;
    }

    /**
     * @param holder
     *            the holder to set
     */
    public void setHolder(int holder) {
        this.holder = holder;
    }

    /**
     * @return the holderName
     */
    public String getHolderName() {
        return holderName;
    }

    /**
     * @param holderName
     *            the holderName to set
     */
    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    /**
     * @return the fee
     */
    public String getFee() {
        return fee;
    }

    /**
     * @param fee
     *            the fee to set
     */
    public void setFee(String fee) {
        this.fee = fee;
    }

    /**
     * @return the feeName
     */
    public String getFeeName() {
        return feeName;
    }

    /**
     * @param feeName
     *            the feeName to set
     */
    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    /**
     * @return the customerAddr
     */
    public String getCustomerAddr() {
        return customerAddr;
    }

    /**
     * @param customerAddr
     *            the customerAddr to set
     */
    public void setCustomerAddr(String customerAddr) {
        this.customerAddr = customerAddr;
    }
}
