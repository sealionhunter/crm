package com.ustcsoft.gs.crm.webui.customer.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.customer.bean.CooperatorSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.CooperatorDto;
import com.ustcsoft.gs.crm.webui.customer.service.CooperatorService;

/**
 * cooperator information's action operation
 * 
 * @author xujialong
 * 
 */
public class CooperatorListAction extends CRMAction {

    private static final long serialVersionUID = 1L;

    private static final Log LOG = LogFactory.getLog(CooperatorListAction.class);

    private CooperatorDto coo = null;

    private CooperatorService cooperatorService = null;

    /**
     * get 25 lines of cooperator and show in page.
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String getAllCooperator() throws CRMDBException {
        LOG.debug("method getAllCooperator start!");
        CooperatorSearchBean searchBean = (CooperatorSearchBean) CRMUtils.jsonToBean(jsonString,
                CooperatorSearchBean.class);
        map = cooperatorService.getAllCooperatorShow(searchFlag, searchBean, page, limit);
        LOG.debug("method getAllCooperator end!");
        return SUCCESS;
    }

    /**
     * add or update cooperator
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String updateCooperator() throws CRMDBException {
        LOG.debug("method updateCooperator start!");
        coo.setIsAbolished(false);
        cooperatorService.updateCooperator(coo);
        LOG.debug("method updateCooperator end!");
        return SUCCESS;
    }

    /**
     * validate input value (cooperator:add,update)
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void validateUpdateCooperator() throws CRMDBException {
        coo = (CooperatorDto) CRMUtils.jsonToBean(super.jsonString, CooperatorDto.class);
        if (cooperatorService.checkNameExisted(coo)) {
            addFieldError("cooperatorName", this.getText("cooperatorName.existed"));
        }
        if (coo.getCooperatorName().length() == 0 || coo.getCooperatorName().length() > 50) {
            addFieldError("cooperatorName", this.getText("cooperatorName.invalid"));
        }
        if (coo.getCooperatorIndustry() == null) {
            addFieldError("cooperatorIndustry", this.getText("industry.invalid"));
        }
        if (coo.getCooperatorScale() == null) {
            addFieldError("cooperatorScale", this.getText("scale.invalid"));
        }
        if (coo.getCooperatorType() == null) {
            addFieldError("cooperatorType", this.getText("type.invalid"));
        }
        if (coo.getCooperatorCity().length() == 0 || coo.getCooperatorCity().length() > 20) {
            addFieldError("cooperatorCity", this.getText("city.invalid"));
        }
        if (coo.getCooperatorAddress().length() > 50) {
            addFieldError("cooperatorAddress", this.getText("address.invalid"));
        }
        if (!coo.getCooperatorWebsite().isEmpty()) {
            if (!coo.getCooperatorWebsite().matches(CRMConstant.WEBSITE_REGEX)
                    || coo.getCooperatorWebsite().length() > 50) {
                addFieldError("cooperatorWebsite", this.getText("website.invalid"));
            }
        }
        if (!coo.getCooperatorTelephone().isEmpty()) {
            if (!coo.getCooperatorTelephone().matches(CRMConstant.TELEPHONE_REGEX)) {
                addFieldError("cooperatorTelephone", this.getText("telephone.invalid"));
            }
        }
        if (!coo.getCooperatorEmail().isEmpty()) {
            if (!coo.getCooperatorEmail().matches(CRMConstant.EMAIL_REGEX)
                    || coo.getCooperatorEmail().length() > 50) {
                addFieldError("cooperatorEmail", this.getText("email.invalid"));
            }
        }
        if (!coo.getCooperatorFax().isEmpty()) {
            if (!coo.getCooperatorFax().matches(CRMConstant.FAX_REGEX)) {
                addFieldError("cooperatorFax", this.getText("fax.invalid"));
            }
        }
        if (coo.getCooperatorRemark().length() > 1024) {
            addFieldError("cooperatorRemark", this.getText("remark.invalid"));
        }
        if (coo.getCooperatorDetail().length() > 1024) {
            addFieldError("cooperatorDetail", this.getText("detail.invalid"));
        }

        showFieldError();
    }

    /**
     * delete cooperators
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String deleteCooperator() throws CRMDBException {
        LOG.debug("method deleteCooperator start!");
        cooperatorService.deleteCooperator(jsonString);
        LOG.debug("method deleteCooperator end!");
        return SUCCESS;
    }

    /**
     * validate input value(delete)
     * 
     */
    public void validateDeleteCooperator() {
        if (!jsonString.matches(CRMConstant.REMOVE_IDS_REGEX)) {
            addFieldError("jsonString", this.getText("del.error"));
        }

        showFieldError();
    }

    /**
     * @return the cooperatorService
     */
    public CooperatorService getCooperatorService() {
        return cooperatorService;
    }

    /**
     * @param cooperatorService
     *            the cooperatorService to set
     */
    public void setCooperatorService(CooperatorService cooperatorService) {
        this.cooperatorService = cooperatorService;
    }
}