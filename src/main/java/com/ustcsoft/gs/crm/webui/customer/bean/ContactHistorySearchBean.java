package com.ustcsoft.gs.crm.webui.customer.bean;

import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;

public class ContactHistorySearchBean {

//    /** storage the search value of userID */
//    private Integer[] userID = {};

    /** storage the search value of searchText */
    private String searchText = null;

    /** storage the search value of customer */
    private String customerName = null;

//    /** storage the search value of weContact */
//    private String weContact = null;

    /** storage the search value of oppositeContact */
    private String oppositeContact = null;

    /** storage the search value of contactWay */
    private String contactWay = null;

    /** storage the search value of contactType */
    private String contactType = null;

    /** storage the search value of ifContact */
    private String ifContact = null;

    /** storage the search value of searchDateStart */
    private String searchDateStart = null;

    /** storage the search value of searchDateEnd */
    private String searchDateEnd = null;

    private int customerID = 0;

//    /**
//     * @return the userID
//     */
//    public Integer[] getUserID() {
//        return userID;
//    }
//
//    /**
//     * @param userID
//     *            the userID to set
//     */
//    public void setUserID(Integer[] userID) {
//        this.userID = userID;
//    }

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
     * @return the customer
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName
     *            the customer to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = CRMUtils.trimSearch(customerName);
    }

//    /**
//     * @return the weContact
//     */
//    public String getWeContact() {
//        return weContact;
//    }
//
//    /**
//     * @param weContact
//     *            the weContact to set
//     */
//    public void setWeContact(String weContact) {
//        this.weContact = CRMUtils.trimSearch(weContact);
//    }

    /**
     * @return the oppositeContact
     */
    public String getOppositeContact() {
        return oppositeContact;
    }

    /**
     * @param oppositeContact
     *            the oppositeContact to set
     */
    public void setOppositeContact(String oppositeContact) {
        this.oppositeContact = CRMUtils.trimSearch(oppositeContact);
    }

    /**
     * @return the contactWay
     */
    public String getContactWay() {
        return contactWay;
    }

    /**
     * @param contactWay
     *            the contactWay to set
     */
    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    /**
     * @return the contactType
     */
    public String getContactType() {
        return contactType;
    }

    /**
     * @param contactType
     *            the contactType to set
     */
    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    /**
     * @return the ifContact
     */
    public String getIfContact() {

        return ifContact;
    }

    /**
     * @param ifContact
     *            the ifContact to set
     */
    public void setIfContact(String ifContact) {
        if (ifContact.equals("000600030001")) {
            this.ifContact = "1";
        } else if (ifContact.equals("000600030002")) {
            this.ifContact = "0";
        }

    }

    /**
     * @return the searchDateStart
     */
    public String getSearchDateStart() {
        return searchDateStart;
    }

    /**
     * @param searchDateStart
     *            the searchDateStart to set
     */
    public void setSearchDateStart(String searchDateStart) {
        this.searchDateStart = searchDateStart;
    }

    /**
     * @return the searchDateEnd
     */
    public String getSearchDateEnd() {
        return searchDateEnd;
    }

    /**
     * @param searchDateEnd
     *            the searchDateEnd to set
     */
    public void setSearchDateEnd(String searchDateEnd) {
        this.searchDateEnd = searchDateEnd;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
