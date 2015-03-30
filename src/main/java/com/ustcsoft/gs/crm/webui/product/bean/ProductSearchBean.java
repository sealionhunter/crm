package com.ustcsoft.gs.crm.webui.product.bean;

import org.springframework.web.util.HtmlUtils;

public class ProductSearchBean {
    /** simple search conditions */
    private String searchText;

    /** expert search:Product Name */
    private String productName;

    private String productID;

    private String productModel;

    private String category;

    /** expert search:MinPrice */
    private String minPrice = "";

    /** expert search:MaxPrice */
    private String maxPrice;

    /**
     * @return the minPrice
     */
    public String getMinPrice() {
        return minPrice;
    }

    /**
     * @param minPrice
     *            the minPrice to set
     */
    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice.trim().replace("[", "[[]").replace("%", "[%]").replace("_", "[_]");
    }

    /**
     * @return the maxPrice
     */
    public String getMaxPrice() {
        return maxPrice;
    }

    /**
     * @param maxPrice
     *            the maxPrice to set
     */
    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice.trim().replace("[", "[[]").replace("%", "[%]").replace("_", "[_]");
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = HtmlUtils.htmlEscapeDecimal(searchText.trim().replace("[", "[[]")
                .replace("%", "[%]").replace("_", "[_]"));
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = HtmlUtils.htmlEscapeDecimal(productName.trim().replace("[", "[[]")
                .replace("%", "[%]").replace("_", "[_]"));
    }

    /**
     * @return the productID
     */
    public String getProductID() {
        return productID;
    }

    /**
     * @param productID
     *            the productID to set
     */
    public void setProductID(String productID) {
        this.productID = productID.trim().replace("[", "[[]").replace("%", "[%]")
                .replace("_", "[_]");
    }

    /**
     * @return the productModel
     */
    public String getProductModel() {
        return productModel;
    }

    /**
     * @param productModel
     *            the productModel to set
     */
    public void setProductModel(String productModel) {
        this.productModel = productModel.trim().replace("[", "[[]").replace("%", "[%]")
                .replace("_", "[_]");
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category
     *            the category to set
     */
    public void setCategory(String category) {
        this.category = category.trim().replace("[", "[[]").replace("%", "[%]").replace("_", "[_]");
    }
}
