package com.ustcsoft.gs.crm.webui.product.bean;

import org.springframework.web.util.HtmlUtils;

public class PriceSearchBean {
    /** simple search conditions */
    private String searchText = "";

    /** expert search:Product Name */
    private String productName;

    private String minDiscount = "";

    private String maxDiscount = "";

    /** expert search:MinPrice */
    private String minPrice = "";

    /** expert search:MaxPrice */
    private String maxPrice;

    private int groupID = 0;

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
        this.minPrice = minPrice;
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
        this.maxPrice = maxPrice;
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
     * @return the minDiscount
     */
    public String getMinDiscount() {
        return minDiscount;
    }

    /**
     * @param minDiscount
     *            the minDiscount to set
     */
    public void setMinDiscount(String minDiscount) {
        this.minDiscount = minDiscount.trim().replace("[", "[[]").replace("%", "[%]")
                .replace("_", "[_]");
    }

    /**
     * @return the maxDiscount
     */
    public String getMaxDiscount() {
        return maxDiscount;
    }

    /**
     * @param maxDiscount
     *            the maxDiscount to set
     */
    public void setMaxDiscount(String maxDiscount) {
        this.maxDiscount = maxDiscount.trim().replace("[", "[[]").replace("%", "[%]")
                .replace("_", "[_]");
    }

    /**
     * @return the groupID
     */
    public int getGroupID() {
        return groupID;
    }

    /**
     * @param groupID
     *            the groupID to set
     */
    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }
}
