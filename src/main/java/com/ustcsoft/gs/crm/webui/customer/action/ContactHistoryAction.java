package com.ustcsoft.gs.crm.webui.customer.action;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.ContactHistorySearchBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.customer.service.ContactHistoryService;

/**
 * @author yinweili
 * 
 */
public class ContactHistoryAction extends CRMAction {

    private static final long serialVersionUID = 1L;

    /** used for recording log */
    private static final Log LOG = LogFactory.getLog(ContactHistoryAction.class);
    /** define contactHistoryService */
    private ContactHistoryService contactHistoryService = null;

    /** used for searching information */
    private ContactHistorySearchBean contactHistorySearchBean = null;

    private int customerID = 0;
    /**
     * @return the contactTrackService
     */
    public ContactHistoryService getContactHistoryService() {
        return contactHistoryService;
    }

    /**
     * @param contactHistoryService
     *            the contactTrackService to set
     */
    public void setContactHistoryService(ContactHistoryService contactHistoryService) {
        this.contactHistoryService = contactHistoryService;
    }

    /**
     * search or get all ContactHistory records for ContactHistoryList
     * 
     * @throws CRMDBException
     */
    @Override
    public String execute() throws CRMDBException {
        LOG.debug("method execute start!");
        map = contactHistoryService.searchOrGetAllContactHistory(super.searchFlag,
                contactHistorySearchBean, super.page, super.limit, this.customerID);
        LOG.debug("method execute end!");
        return SUCCESS;
    }

    /**
     * validate method execute(),format contactHistorySearchBean
     * 
     */
    @Override
    public void validate() {
        JSONObject json = null;
        try {
            json = JSONObject.fromObject(super.jsonString);
            contactHistorySearchBean = (ContactHistorySearchBean) JSONObject.toBean(json,
                    ContactHistorySearchBean.class);
        } catch (JSONException e) {
            LOG.error("execute error with error object");
            addFieldError(CustomerConstant.CONTACTHISTORY_SEARCH_OBJECT,
                    this.getText(CustomerConstant.CONTACTHISTORY_JSON_ERROR));
        }
        if (searchFlag == 0) {
        } else if (searchFlag == 1) {
            if (contactHistorySearchBean.getSearchText().length() > 50) {
                addFieldError(CRMConstant.SEARCHTEXT,
                        this.getText(CustomerConstant.CONTACTHISTORY_SEARCH_TEXT_INVALID));
            }
        } else if (searchFlag == 2) {
            if (contactHistorySearchBean.getCustomerName().length() > 50) {
                addFieldError(CustomerConstant.CONTACTHISTORY_CUSTOMER_NAME,
                        this.getText(CustomerConstant.CONTACTHISTORY_CUSTOMER_INVALID));
            }
//            if (contactHistorySearchBean.getWeContact().length() > 50) {
//                addFieldError(CustomerConstant.CONTACTHISTORY_WE_CONTACT,
//                        this.getText(CustomerConstant.CONTACTHISTORY_WE_CONTACT_INVALID));
//            }
            if (contactHistorySearchBean.getOppositeContact().length() > 50) {
                addFieldError(CustomerConstant.CONTACTHISTORY_OPPOSITE_CONTACT,
                        this.getText(CustomerConstant.CONTACTHISTORY_OPPOSITE_CONTACT_INVALID));
            }
            if (!contactHistorySearchBean.getSearchDateStart().isEmpty()) {
                if (!contactHistorySearchBean.getSearchDateStart().matches(CRMConstant.DATE_REG)) {
                    addFieldError(CustomerConstant.CONTACTHISTORY_SEARCH_DATE_START,
                            this.getText(CustomerConstant.CONTACTHISTORY_MINDATE_SEARCH_INVALID));
                }
            }
            if (!contactHistorySearchBean.getSearchDateEnd().isEmpty()) {
                if (!contactHistorySearchBean.getSearchDateEnd().matches(CRMConstant.DATE_REG)) {
                    addFieldError(CustomerConstant.CONTACTHISTORY_SEARCH_DATE_END,
                            this.getText(CustomerConstant.CONTACTHISTORY_MAXDATE_SEARCH_INVALID));
                }
            }
        } else {
            addFieldError(CustomerConstant.CONTACTHISTORY_SEARCH_FLAG,
                    this.getText(CustomerConstant.CONTACTHISTORY_SEARCH_FLAG_INVALID));
        }
        showFieldError();
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
