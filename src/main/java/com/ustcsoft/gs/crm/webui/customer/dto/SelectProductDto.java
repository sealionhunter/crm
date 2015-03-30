package com.ustcsoft.gs.crm.webui.customer.dto;

public class SelectProductDto {

    private int id = 0;

    private int selectProductID;

    private String orderID = null;

    private String productID = null;

    private String name = null;

    private String price = null;

    private int productNumber = 0;

    private Boolean isAbolished = false;

    private String discount = null;

    /**
     * 
     * @return selectProductID
     */
    public int getSelectProductID() {
        return selectProductID;
    }

    /**
     * 
     * @param selectProductID
     */
    public void setSelectProductID(int selectProductID) {
        this.selectProductID = selectProductID;
    }

    /**
     * 
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @return orderID
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * 
     * @param orderID
     */
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * 
     * @return productID
     */
    public String getProductID() {
        return productID;
    }

    /**
     * 
     * @param productID
     */
    public void setProductID(String productID) {
        this.productID = productID;
    }

    /**
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return price
     */
    public String getPrice() {
        return price;
    }

    /**
     * 
     * @param price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * 
     * @return productNumber
     */
    public int getProductNumber() {
        return productNumber;
    }

    /**
     * 
     * @param productNumber
     */
    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    /**
     * 
     * @return isAbolished
     */
    public Boolean getIsAbolished() {
        return isAbolished;
    }

    /**
     * 
     * @param isAbolished
     */
    public void setIsAbolished(Boolean isAbolished) {
        this.isAbolished = isAbolished;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

}