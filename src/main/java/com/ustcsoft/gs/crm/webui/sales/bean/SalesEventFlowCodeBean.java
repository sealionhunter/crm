package com.ustcsoft.gs.crm.webui.sales.bean;

public class SalesEventFlowCodeBean {

    private int code = 0;
    private String value = null;
    private String category = null;
    private int sort = 0;
    private String categoryName = null;

    public SalesEventFlowCodeBean() {

    }

    public SalesEventFlowCodeBean(int code, String value, String category, int sort,
            String categoryName) {
        this.code = code;
        this.value = value;
        this.category = category;
        this.sort = sort;
        this.categoryName = categoryName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
