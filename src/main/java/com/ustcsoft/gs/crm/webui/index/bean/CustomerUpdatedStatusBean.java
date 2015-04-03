package com.ustcsoft.gs.crm.webui.index.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ustcsoft.gs.crm.webui.index.constant.IndexConstant;

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

    public CustomerUpdatedStatusBean(String customerName,
            String updateTime) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long interval = new Date().getTime() - formatter.parse(updateTime).getTime();
            int days = IndexConstant.MAX_DAYS_TO_NOTIIFY 
                    -(int) (interval / IndexConstant.MILLIS_OF_ONE_DAY) ;
            this.days = days + 1;
            this.customerName = customerName;
        } catch (ParseException e) {
            throw new RuntimeException("parse date error.", e);
        }
    }
}
