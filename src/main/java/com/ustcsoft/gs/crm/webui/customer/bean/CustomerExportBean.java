package com.ustcsoft.gs.crm.webui.customer.bean;

import java.util.List;

import com.ustcsoft.gs.crm.webui.contact.bean.ContactBean;

public class CustomerExportBean {
    private CustomerBean customerBean = null;
    private List<ContactBean> contactList = null;
    private List<ContactTrackListBean> contactTrackList = null;
    private List<ContactTrackListBean> contactHistoryList = null;

    public CustomerBean getCustomerBean() {
        return customerBean;
    }

    public void setCustomerBean(CustomerBean customerBean) {
        this.customerBean = customerBean;
    }

    public List<ContactBean> getContactList() {
        return contactList;
    }

    public void setContactList(List<ContactBean> contactList) {
        this.contactList = contactList;
    }

    public List<ContactTrackListBean> getContactTrackList() {
        return contactTrackList;
    }

    public void setContactTrackList(List<ContactTrackListBean> contactTrackList) {
        this.contactTrackList = contactTrackList;
    }

    public List<ContactTrackListBean> getContactHistoryList() {
        return contactHistoryList;
    }

    public void setContactHistoryList(
            List<ContactTrackListBean> contactHistoryList) {
        this.contactHistoryList = contactHistoryList;
    }
}
