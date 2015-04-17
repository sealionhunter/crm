package com.ustcsoft.gs.crm.webui.customer.action;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.ustcsoft.gs.crm.webui.common.action.CRMExcelExportAction;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.contact.bean.ContactBean;
import com.ustcsoft.gs.crm.webui.contact.service.ContactInfoService;
import com.ustcsoft.gs.crm.webui.customer.bean.ContactTrackListBean;
import com.ustcsoft.gs.crm.webui.customer.bean.CustomerBean;
import com.ustcsoft.gs.crm.webui.customer.bean.CustomerExportBean;
import com.ustcsoft.gs.crm.webui.customer.service.ContactHistoryService;
import com.ustcsoft.gs.crm.webui.customer.service.ContactTrackService;
import com.ustcsoft.gs.crm.webui.customer.service.CustomerService;

public class CustomerExportAction extends CRMExcelExportAction<CustomerExportBean> {
    private int customerID = 0;
    private String customerIDList = null;
    
    private CustomerService customerService = null;
    private ContactInfoService contactInfoService = null;
    private ContactTrackService contactTrackService = null;
    private ContactHistoryService contactHistoryService = null;


    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerIDList() {
        return customerIDList;
    }

    public void setCustomerIDList(String customerIDList) {
        this.customerIDList = customerIDList;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public ContactInfoService getContactInfoService() {
        return contactInfoService;
    }

    public void setContactInfoService(ContactInfoService contactInfoService) {
        this.contactInfoService = contactInfoService;
    }

    public ContactTrackService getContactTrackService() {
        return contactTrackService;
    }

    public void setContactTrackService(ContactTrackService contactTrackService) {
        this.contactTrackService = contactTrackService;
    }

    public ContactHistoryService getContactHistoryService() {
        return contactHistoryService;
    }

    public void setContactHistoryService(ContactHistoryService contactHistoryService) {
        this.contactHistoryService = contactHistoryService;
    }

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 9205870617945517794L;

    @Override
    public List<CustomerExportBean> getExportTarget() throws CRMDBException {
        if (isExportList()) {
            // export customer list
            List<CustomerBean> customerBeanList = customerService.findCustomersByIds(customerIDList);
            List<CustomerExportBean> beanList = new ArrayList<CustomerExportBean>();
            for (CustomerBean customer : customerBeanList) {
                CustomerExportBean bean = new CustomerExportBean();
                bean.setCustomerBean(customer);
                beanList.add(bean);
            }
            return beanList;
        } else {
            CustomerBean customerBean = customerService.getCustomerById(customerID);
            List<ContactBean> contactList = contactInfoService.getAllContact(customerID);
            List<ContactTrackListBean> contactTrackList = contactTrackService.getAllContactTrack(customerID);
            List<ContactTrackListBean> contactHistoryList = contactHistoryService.getAllContactHistory(customerID);
            
            CustomerExportBean exportBean = new CustomerExportBean();
            exportBean.setContactHistoryList(contactHistoryList);
            exportBean.setContactList(contactList);
            exportBean.setContactTrackList(contactTrackList);
            exportBean.setCustomerBean(customerBean);;
            return Collections.singletonList(exportBean);
        }
    }

    @Override
    public InputStream getTemplate() throws IOException {
        String rootPath = ServletActionContext.getServletContext().getRealPath("/");
        String templatePathSingle = rootPath + "WEB-INF\\conf\\customer\\customerTemplate.xls";
        String templatePathMulti = rootPath + "WEB-INF\\conf\\customer\\customerListTemplate.xls";
        
        InputStream templateStream = null;
        if (isExportList()) {
            templateStream = new BufferedInputStream(new FileInputStream(templatePathMulti));
        } else {
            templateStream = new BufferedInputStream(new FileInputStream(templatePathSingle));
        }
        return templateStream;
    }

    private boolean isExportList() {
        if (customerIDList != null && !customerIDList.isEmpty()) {
            return true;
        }
        return false;
    }
}
