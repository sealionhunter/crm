package com.ustcsoft.gs.crm.webui.customer.action;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.customer.bean.LeaderAdviceSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.LeaderAdviceDto;
import com.ustcsoft.gs.crm.webui.customer.service.LeaderAdviceService;

public class LeaderAdviceAction extends CRMAction {
    private static final long serialVersionUID = -2743274558934757350L;

    //private static final Log LOG = LogFactory.getLog(LeaderAdviceAction.class);

    private LeaderAdviceDto leaderAdviceDto = null;

    private LeaderAdviceService service = null;

    private int customerID = 0;

    public void validateUpdateLeaderAdvice() {
        leaderAdviceDto = (LeaderAdviceDto) CRMUtils.jsonToBean(super.jsonString, LeaderAdviceDto.class);
        if (StringUtils.isBlank(leaderAdviceDto.getAdviceContent())) {
            addFieldError("adviceContent", this.getText("adviceContent.empty"));
        } else if (leaderAdviceDto.getAdviceContent().length() > 1024) {
            addFieldError("adviceContent", this.getText("adviceContent.overMaxLength"));
        }
        if (getFieldErrors().size() != 0) {
            map.putAll(getFieldErrors());
            map.put(CRMConstant.VALIDATE, false);
        }
    }

    public String updateLeaderAdvice() {
        service.addOrUpdateLeaderAdvice(leaderAdviceDto, this.userID);
        return SUCCESS;
    }

    public String deleteLeaderAdvice() {
        service.deleteLeaderAdvice(super.jsonString);
        map = new HashMap<String, Object>();
        return SUCCESS;
    }

    public String getAllLeaderAdvice() {
        LeaderAdviceSearchBean searchBean = null;
        // query leader advice
        if (super.searchFlag == 1) {
            searchBean = (LeaderAdviceSearchBean) CRMUtils.jsonToBean(super.jsonString, LeaderAdviceSearchBean.class);
        }
        map = service.getAllLeaderAdvice(this.searchFlag, searchBean, page ,limit, userID, customerID);
        return SUCCESS;
    }

    public LeaderAdviceService getService() {
        return service;
    }

    public void setService(LeaderAdviceService service) {
        this.service = service;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
