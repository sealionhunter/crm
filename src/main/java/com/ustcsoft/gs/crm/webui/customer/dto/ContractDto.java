package com.ustcsoft.gs.crm.webui.customer.dto;

/**
 * @author chenshengwei
 */
public class ContractDto {

    private int contractID;
    private String contractName;
    private String payEndTime;
    private String payType;
    private int orderID;
    private String contractValue;
    private boolean isAbolished;
    private String fileTemplateName;

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
        this.payEndTime = payEndTime;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
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

}
