package com.ustcsoft.gs.crm.webui.customer.dto;

/**
 * 
 * @author jiaxu
 * 
 */
public class OrderDto {

    private int id = 0;

    private int customerID = 0;

    private String orderID = null;

    private String orderState = null;

    private String contactID = null;

    private String ourRepresentative = null;

    private String deliveryDate = null;

    private String transactionPrice = null;

    private String remark = null;

    private Boolean isAbolished = false;

    private String ourTelephone = null;

    private int type = 0;

    private String customerContact = null;

    private int eventID = 0;

    private String status = null;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID
     *            the customerID to set
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * @return the orderState
     */
    public String getOrderState() {
        return orderState;
    }

    /**
     * @param orderState
     *            the orderState to set
     */
    public void setOrderState(String orderState) {
        this.orderState = orderState.trim();
    }

    /**
     * @return the customerRepresentative
     */
    public String getCustomerRepresentative() {
        return contactID;
    }

    /**
     * @param customerRepresentative
     *            the customerRepresentative to set
     */
    public void setCustomerRepresentative(String customerRepresentative) {
        contactID = customerRepresentative.trim();
    }

    /**
     * @return the ourRepresentative
     */
    public String getOurRepresentative() {
        return ourRepresentative;
    }

    /**
     * @param ourRepresentative
     *            the ourRepresentative to set
     */
    public void setOurRepresentative(String ourRepresentative) {
        this.ourRepresentative = ourRepresentative.trim();
    }

    /**
     * @return the deliveryDate
     */
    public String getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * @param deliveryDate
     *            the deliveryDate to set
     */
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate.trim();
    }

    /**
     * @return the transactionPrice
     */
    public String getTransactionPrice() {
        return transactionPrice;
    }

    /**
     * @param transactionPrice
     *            the transactionPrice to set
     */
    public void setTransactionPrice(String transactionPrice) {
        this.transactionPrice = transactionPrice.trim();
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     *            the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark.trim();
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
     * @return the contactID
     */
    public String getContactID() {
        return contactID;
    }

    /**
     * @param contactID
     *            the contactID to set
     */
    public void setContactID(String contactID) {
        this.contactID = contactID;
    }

    /**
     * 
     * @return ourTelephone
     */
    public String getOurTelephone() {
        return ourTelephone;
    }

    /**
     * 
     * @param ourTelephone
     */
    public void setOurTelephone(String ourTelephone) {
        this.ourTelephone = ourTelephone;
    }

    /**
     * 
     * @return customerContact
     */
    public String getCustomerContact() {
        return customerContact;
    }

    /**
     * 
     * @param customerContact
     */
    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    /**
     * 
     * @return type
     */
    public int getType() {
        return type;
    }

    /**
     * 
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 
     * @return eventID
     */
    public int getEventID() {
        return eventID;
    }

    /**
     * 
     * @param eventID
     */
    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    /**
     * 
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return a type of Map<String, String>
     */

    /**
     * 
     * @return type of List<SalesEventFloseDto>
     */
}
