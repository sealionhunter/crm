package com.ustcsoft.gs.crm.webui.customer.action;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.ustcsoft.gs.crm.webui.common.action.CRMExcelExportAction;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.CustomerBean;
import com.ustcsoft.gs.crm.webui.customer.service.CustomerService;

public class CustomerExportAction extends CRMExcelExportAction<CustomerBean> {
    private int customerID = 0;
    private String customerIDList = null;
    
    private CustomerService customerService = null;

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

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 9205870617945517794L;

    @Override
    public List<CustomerBean> getExportTarget() throws CRMDBException {
        if (isExportList()) {
            // export customer list
            return customerService.findCustomersByIds(customerIDList);
        } else {
            CustomerBean dto = customerService.getCustomerById(customerID);
            return Collections.singletonList(dto);
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
