package com.ustcsoft.gs.crm.webui.customer.bean;

public class ContractBean {
    private int contractID = 0;
    private String contractName = "";
    private String payEndTime = "";
    private String payType = "";
    /** order serial numbers */
    private String orderID = "";
    /** order id */
    private int orderKey = 0;
    private String contractValue = "";
    private boolean isAbolished = false;
    private String fileTemplateName = "";

    public ContractBean(int orderKey, int contractID, String contractName, String payEndTime,
            String payType, String contractValue, boolean isAbolished, String fileTemplateName,
            String orderID) {
        setOrderKey(orderKey);
        this.contractID = contractID;
        this.contractName = contractName;
        this.payEndTime = payEndTime.substring(0, payEndTime.length() - 3);
        this.payType = payType;
        this.orderID = orderID;
        this.contractValue = contractValue;
        this.isAbolished = isAbolished;
        this.fileTemplateName = fileTemplateName;
    }

    public int getContractID() {
        return contractID;
    }

    public void setContractID(int contractID) {
        this.contractID = contractID;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getPayEndTime() {
        return payEndTime;
    }

    public void setPayEndTime(String payEndTime) {
        this.payEndTime = payEndTime.substring(0, payEndTime.length() - 3);
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getContractValue() {
        return contractValue;
    }

    public void setContractValue(String contractValue) {
        this.contractValue = contractValue;
    }

    public boolean getIsAbolished() {
        return isAbolished;
    }

    public void setIsAbolished(boolean isAbolished) {
        this.isAbolished = isAbolished;
    }

    /**
     * @return the fileTemplateName
     */
    public String getFileTemplateName() {
        return fileTemplateName;
    }

    /**
     * @param fileTemplateName
     *            the fileTemplateName to set
     */
    public void setFileTemplateName(String fileTemplateName) {
        this.fileTemplateName = fileTemplateName;
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
     * @return the orderKey
     */
    public int getOrderKey() {
        return orderKey;
    }

    /**
     * @param orderKey
     *            the orderKey to set
     */
    public void setOrderKey(int orderKey) {
        this.orderKey = orderKey;
    }
}
