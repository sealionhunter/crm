package com.ustcsoft.gs.crm.webui.customer.bean;

import com.ustcsoft.gs.crm.webui.customer.dto.SourceInfoDto;

/**
 * customer and sourceType display information
 * 
 * @author xuzhen
 */
public class SourceInfoBean extends SourceInfoDto {
    /** customer display information */
    private String customerName;

    /** sourceType display information */
    private String sourceTypeName;

    public SourceInfoBean() {
    }

    public SourceInfoBean(SourceInfoDto sourceInfoDto, String sourceTypeName, String customerName) {

        this.customerName = customerName;
        this.sourceTypeName = sourceTypeName;
        super.setCustomerID(sourceInfoDto.getCustomerID());
        super.setSourceID(sourceInfoDto.getSourceID());
        super.setSourceType(sourceInfoDto.getSourceType());
        super.setSourceArea(sourceInfoDto.getSourceArea());
        super.setDescriptions(sourceInfoDto.getDescriptions());
        super.setIsAbolished(sourceInfoDto.getIsAbolished());
    }

    /**
     * 
     * @return customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * 
     * @param customerName
     *            the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * 
     * @return sourceTypeName
     */
    public String getSourceTypeName() {
        return sourceTypeName;
    }

    /**
     * 
     * @param sourceTypeName
     *            the sourceTypeName to set
     */
    public void setSourceTypeName(String sourceTypeName) {
        this.sourceTypeName = sourceTypeName;
    }
}
