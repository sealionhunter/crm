package com.ustcsoft.gs.crm.webui.customer.bean;

import com.ustcsoft.gs.crm.webui.customer.dto.CustomerDto;

/**
 * 
 * @author tangyunpeng
 * 
 */
public class CustomerBean extends CustomerDto {

    private String industryName = null;

    private String customerTypeName = null;

    private String feeName = null;

    private String customerStatementName = null;

    private String valueEvaluateName = null;

    private String scaleName = null;

    private String holderName = null;

    /**
     * @return the industryName
     */
    public String getIndustryName() {
        return industryName;
    }

    /**
     * @param industryName
     *            the industryName to set
     */
    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    /**
     * @return the customerTypeName
     */
    public String getCustomerTypeName() {
        return customerTypeName;
    }

    /**
     * @param customerTypeName
     *            the customerTypeName to set
     */
    public void setCustomerTypeName(String customerTypeName) {
        this.customerTypeName = customerTypeName;
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
     * @return the customerStatementName
     */
    public String getCustomerStatementName() {
        return customerStatementName;
    }

    /**
     * @param customerStatementName
     *            the customerStatementName to set
     */
    public void setCustomerStatementName(String customerStatementName) {
        this.customerStatementName = customerStatementName;
    }

    /**
     * @return the valueEvaluateName
     */
    public String getValueEvaluateName() {
        return valueEvaluateName;
    }

    /**
     * @param valueEvaluateName
     *            the valueEvaluateName to set
     */
    public void setValueEvaluateName(String valueEvaluateName) {
        this.valueEvaluateName = valueEvaluateName;
    }

    /**
     * @return the scaleName
     */
    public String getScaleName() {
        return scaleName;
    }

    /**
     * @param scaleName
     *            the scaleName to set
     */
    public void setScaleName(String scaleName) {
        this.scaleName = scaleName;
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

}
