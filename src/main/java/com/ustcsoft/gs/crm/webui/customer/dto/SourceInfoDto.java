/*
 * Class name: UserInfoDto
 * 
 * Version information: 1.0
 * 
 * Date:2012.3.29
 *  
 */
package com.ustcsoft.gs.crm.webui.customer.dto;

/**
 * CustomerSourceInfo表对应的dto
 * 
 * @author xuzhen
 * 
 */
public class SourceInfoDto {
    private int sourceID;

    private String customerID;

    private String sourceArea;

    private String sourceType;

    private Boolean isAbolished = false;

    private String descriptions;

    /**
     * 
     * @return sourceID
     */
    public int getSourceID() {
        return sourceID;
    }

    /**
     * 
     * @param sourceID
     *            the sourceID to set
     */
    public void setSourceID(int sourceID) {
        this.sourceID = sourceID;
    }

    /**
     * 
     * @return customerID
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * 
     * @param customerID
     *            the customerID to set
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID.trim();
    }

    /**
     * 
     * @return sourceArea
     */
    public String getSourceArea() {
        return sourceArea;
    }

    /**
     * 
     * @param sourceArea
     *            the sourceArea to set
     */
    public void setSourceArea(String sourceArea) {
        this.sourceArea = sourceArea.trim();
    }

    /**
     * 
     * @return sourceType
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * 
     * @param sourceType
     *            the sourceType to set
     */
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType.trim();
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

    /**
     * 
     * @return descriptions
     */
    public String getDescriptions() {
        return descriptions;
    }

    /**
     * 
     * @param descriptions
     *            the descriptions to set
     */
    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions.trim();
    }

}
