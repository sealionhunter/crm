package com.ustcsoft.gs.crm.webui.product.dto;

import java.util.List;

public class ProductDto {
    private int id;
    private String name;
    private String category;
    private String productID;
    private String productModel;
    private String price;
    private String description;
    private String remark;
    private String discount;

    /**
     * @return the discount
     */
    public String getDiscount() {
        return discount;
    }

    /**
     * @param discount
     *            the discount to set
     */
    public void setDiscount(String discount) {
        this.discount = discount;
    }

    private List<ProductPriceRangeDto> priceRanges;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<ProductPriceRangeDto> getPriceRanges() {
        return priceRanges;
    }

    public void setPriceRanges(List<ProductPriceRangeDto> priceRanges) {
        this.priceRanges = priceRanges;
    }
}
