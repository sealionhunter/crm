package com.ustcsoft.gs.crm.webui.customer.bean;

public class CountBean {

    String name;
    String code;
    long count;

    public CountBean(String name, String code, long count) {
        super();
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
