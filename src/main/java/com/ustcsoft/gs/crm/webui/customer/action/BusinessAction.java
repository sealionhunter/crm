package com.ustcsoft.gs.crm.webui.customer.action;

import java.util.HashMap;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.customer.bean.BusinessSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.BusinessDto;
import com.ustcsoft.gs.crm.webui.customer.service.BusinessService;

public class BusinessAction extends CRMAction {
    private static final long serialVersionUID = -2743274558934757350L;

    //private static final Log LOG = LogFactory.getLog(BusinessAction.class);

    private BusinessDto businessDto = null;

    private BusinessService service = null;

    private int customerID = 0;

    public void validateUpdateBusiness() {
        businessDto = (BusinessDto) CRMUtils.jsonToBean(super.jsonString, BusinessDto.class);
//        if (StringUtils.isBlank(businessDto.getAdviceContent())) {
//            addFieldError("adviceContent", this.getText("adviceContent.empty"));
//        } else if (businessDto.getAdviceContent().length() > 1024) {
//            addFieldError("adviceContent", this.getText("adviceContent.overMaxLength"));
//        }
        if (getFieldErrors().size() != 0) {
            map.putAll(getFieldErrors());
            map.put(CRMConstant.VALIDATE, false);
        }
    }

    public String updateBusiness() throws CRMDBException {
        service.addOrUpdateBusiness(businessDto, this.userID);
        return SUCCESS;
    }

    public String deleteBusiness() throws CRMDBException {
        service.deleteBusiness(super.jsonString);
        map = new HashMap<String, Object>();
        return SUCCESS;
    }

    public String getAllBusiness() throws CRMDBException {
        BusinessSearchBean searchBean = null;
        if (super.searchFlag == 1) {
            searchBean = (BusinessSearchBean) CRMUtils.jsonToBean(super.jsonString, BusinessSearchBean.class);
        }
        map = service.getAllBusiness(this.searchFlag, searchBean, page ,limit, userID, customerID);
        return SUCCESS;
    }

    public BusinessService getService() {
        return service;
    }

    public void setService(BusinessService service) {
        this.service = service;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
