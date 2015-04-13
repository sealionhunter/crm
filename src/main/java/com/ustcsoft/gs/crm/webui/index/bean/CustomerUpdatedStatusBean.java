package com.ustcsoft.gs.crm.webui.index.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;


public class CustomerUpdatedStatusBean {
    private int days = 0;
    private String customerName = null;

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public CustomerUpdatedStatusBean() {
    }

    public CustomerUpdatedStatusBean(String customerName, Long days) {
            this.customerName = customerName;
            this.days = days.intValue();
    }

    public CustomerUpdatedStatusBean(String customerName, String updateTime) {
        try {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date update = f.parse(updateTime);
            this.days = CRMUtils.getBetweenDays(update, new Date());
            this.customerName = customerName;
        } catch (ParseException e) {
            throw new RuntimeException("parse date error.", e);
        }
    }
}
