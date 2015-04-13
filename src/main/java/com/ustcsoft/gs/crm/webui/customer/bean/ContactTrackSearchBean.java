package com.ustcsoft.gs.crm.webui.customer.bean;

/**
 * 
 * @author zhouzhou
 * 
 */
public class ContactTrackSearchBean {

//    /** storage the search value of userID */
//    private Integer[] userID = {};

    /** storage the search value of searchText */
    private String searchText = null;

//    /** storage the search value of customerName */
//    private String customerName = null;

    /** storage the search value of oppositeContact */
    private String oppositeContact = null;

//    /** storage the search value of weContact */
//    private String weContact = null;

    /** storage the search value of contactWay */
    private String contactWay = null;

    /** storage the search value of planDateMax */
    private String planDateMax = null;

    /** storage the search value of planDateMin */
    private String planDateMin = null;

    /** storage the search value of contactType */
    private String contactType = null;

    private Integer customerID = null;

    /**
     * @return the searchText
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * @param searchText
     *            the contactSearch to set
     */
    public void setSearchText(String searchText) {
        this.searchText = searchText.trim();
    }

//    /**
//     * @return the customerName
//     */
//    public String getCustomerName() {
//        return customerName;
//    }

//    /**
//     * @param customerName
//     *            the customerName to set
//     */
//    public void setCustomerName(String customerName) {
//        this.customerName = customerName.trim();
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
        this.oppositeContact = oppositeContact.trim();
    }
//
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
//        this.weContact = weContact.trim();
//    }

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
     * @return the planDateMax
     */
    public String getPlanDateMax() {
        return planDateMax;
    }

    /**
     * @param planDateMax
     *            the planDateMax to set
     */
    public void setPlanDateMax(String planDateMax) {
        this.planDateMax = planDateMax.trim();
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
     * @return planDateMin
     */
    public String getPlanDateMin() {
        return planDateMin;
    }

    /**
     * @param planDateMin
     *            the planDateMin to set
     */
    public void setPlanDateMin(String planDateMin) {
        this.planDateMin = planDateMin.trim();
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

//    /**
//     * @return userID
//     */
//
//    public Integer[] getUserID() {
//        return userID;
//    }

//    /**
//     * @param userID
//     *            the userID to set
//     */
//    public void setUserID(Integer[] userID) {
//        this.userID = userID;
//    }

}
