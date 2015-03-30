/**
 * 
 */
package com.ustcsoft.gs.crm.webui.customer.bean;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangchuanhong
 * 
 */
public class ContractSearchBean {
    private String textValue;
    private String contractName;
    private String orderID;
    private String fileTemplateName;
    private String payEndTimeMin;
    private String payEndTimeMax;
    private String payType;

    /**
     * @return the textValue
     */
    public String getTextValue() {
        return textValue;
    }

    /**
     * @param textValue
     *            the textValue to set
     */
    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    /**
     * @return the contractName
     */
    public String getContractName() {
        return contractName;
    }

    /**
     * @param contractName
     *            the contractName to set
     */
    public void setContractName(String contractName) {
        this.contractName = contractName;
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
     * @return the payEndTimeMin
     */
    public String getPayEndTimeMin() {
        return payEndTimeMin;
    }

    /**
     * @param payEndTimeMin
     *            the payEndTimeMin to set
     */
    public void setPayEndTimeMin(String payEndTimeMin) {
        if (!StringUtils.isBlank(payEndTimeMin)) {
            this.payEndTimeMin = payEndTimeMin.substring(0, 7) + "-01";
        }
    }

    /**
     * @return the payEndTimeMax
     */
    public String getPayEndTimeMax() {
        return payEndTimeMax;
    }

    /**
     * @param payEndTimeMax
     *            the payEndTimeMax to set
     */
    public void setPayEndTimeMax(String payEndTimeMax) {
        if (!StringUtils.isBlank(payEndTimeMax)) {
            this.payEndTimeMax = payEndTimeMax.substring(0, 7) + "-01";
        }
    }

    /**
     * @return the payType
     */
    public String getPayType() {
        return payType;
    }

    /**
     * @param payType
     *            the payType to set
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }
}
