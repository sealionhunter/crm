package com.ustcsoft.gs.crm.webui.sales.dto;

/**
 * SalesEventFlowCode entity.
 */

public class SalesEventFlowCodeDto {

    // Fields

    private Integer code = 0;
    private String value = null;
    private String category = null;
    private Integer sort = 0;

    // Constructors

    /** default constructor */
    public SalesEventFlowCodeDto() {
    }

    /** full constructor */
    public SalesEventFlowCodeDto(Integer code, String value, String category, Integer sort) {
        this.code = code;
        this.value = value;
        this.category = category;
        this.sort = sort;
    }

    // Property accessors

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

}