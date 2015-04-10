package com.ustcsoft.gs.crm.webui.customer.bean;

import com.ustcsoft.gs.crm.webui.customer.dto.LeaderAdviceDto;

public class LeaderAdviceBean extends LeaderAdviceDto {
    private String customerName = null;
    private String userName = null;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
