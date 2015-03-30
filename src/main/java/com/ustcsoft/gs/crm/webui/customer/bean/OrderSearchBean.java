package com.ustcsoft.gs.crm.webui.customer.bean;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;

public class OrderSearchBean {

    private Integer[] userID = {};
    private String searchText = null;
    private String orderIDValue = null;
    private String customerIDValue = null;
    private String ourRepresentativeValue = null;
    private String transactionPriceValue = null;

    /**
     * @return the userID
     */
    public Integer[] getUserID() {
        return userID;
    }

    /**
     * @param userID
     *            the userID to set
     */
    public void setUserID(Integer[] userID) {
        this.userID = userID;
    }

    /**
     * @return the searchText
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * @param searchText
     *            the searchText to set
     */
    public void setSearchText(String searchText) {
        this.searchText = CRMUtils.trimSearch(searchText);
    }

    /**
     * @return the orderIDValue
     */
    public String getOrderIDValue() {
        return orderIDValue;
    }

    /**
     * @param orderIDValue
     *            the orderIDValue to set
     */
    public void setOrderIDValue(String orderIDValue) {
        this.orderIDValue = CRMUtils.trimSearch(orderIDValue);
    }

    /**
     * @return the customerIDValue
     */
    public String getCustomerIDValue() {
        return customerIDValue;
    }

    /**
     * @param customerIDValue
     *            the customerIDValue to set
     */
    public void setCustomerIDValue(String customerIDValue) {
        this.customerIDValue = CRMConstant.PER_CENT + CRMUtils.trimSearch(customerIDValue)
                + CRMConstant.PER_CENT;
    }

    /**
     * @return the ourRepresentativeValue
     */
    public String getOurRepresentativeValue() {
        return ourRepresentativeValue;
    }

    /**
     * @param ourRepresentativeValue
     *            the ourRepresentativeValue to set
     */
    public void setOurRepresentativeValue(String ourRepresentativeValue) {
        this.ourRepresentativeValue = CRMUtils.trimSearch(ourRepresentativeValue);
    }

    /**
     * @return the transactionPriceValue
     */
    public String getTransactionPriceValue() {
        return transactionPriceValue;
    }

    /**
     * @param transactionPriceValue
     *            the transactionPriceValue to set
     */
    public void setTransactionPriceValue(String transactionPriceValue) {
        this.transactionPriceValue = transactionPriceValue;
    }

}
