package com.ustcsoft.gs.crm.webui.customer.bean;

import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;

/**
 * search conditions
 * 
 * @author xuzhen
 */
public class SourceSearchBean {
    /** simple search conditions */
    private String searchText = null;

    /** expert search:sourceCustomer conditions */
    private String sourceCustomer = null;

    /** expert search:sourceArea conditions */
    private String sourceArea = null;

    /** expert search:sourceType conditions */
    private String[] sourceType = null;

    private Integer[] userID = null;

    /**
     * 
     * @return searchText
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * 
     * @param searchText
     *            the searchText to set
     */
    public void setSearchText(String searchText) {
        this.searchText = CRMUtils.trimSearch(searchText);

    }

    /**
     * 
     * @return sourceCustomer
     */
    public String getSourceCustomer() {
        return sourceCustomer;
    }

    /**
     * 
     * @param sourceCustomer
     *            the sourceCustomer to set
     */
    public void setSourceCustomer(String sourceCustomer) {
        this.sourceCustomer = CRMUtils.trimSearch(sourceCustomer);
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
        this.sourceArea = CRMUtils.trimSearch(sourceArea);

    }

    /**
     * 
     * @return sourceType
     */
    public String[] getSourceType() {
        return sourceType;
    }

    /**
     * 
     * @param sourceType
     *            the sourceType to set
     */
    public void setSourceType(String[] sourceType) {
        this.sourceType = sourceType;
    }

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
}
