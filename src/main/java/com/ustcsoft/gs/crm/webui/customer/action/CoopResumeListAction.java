/*
 * Class name: CoopResumeListAction
 * 
 * Version information: 1.0
 * 
 * Date:2012.9.11
 *  
 */
package com.ustcsoft.gs.crm.webui.customer.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.customer.bean.CoopResumeSearchBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.customer.dto.CoopResumeDto;
import com.ustcsoft.gs.crm.webui.customer.service.CoopResumeService;

/** 
 * class CoopResumeListAction for jsp page to execute action
 * 
 * @author tangyunpeng
 * 
 */
public class CoopResumeListAction extends CRMAction {

    private static final long serialVersionUID = 1L;

    private static final Log LOG = LogFactory.getLog(CoopResumeListAction.class);

    /** used for get class CoopResumeService */
    private CoopResumeService coopResumeService = null;

    /** used for accept the customer id */
    private int customerID = 0;

    /** CoopResumeDto information which will be added or edited */
    private CoopResumeDto coopResumeDto = null;

    /**
     * validate addOrUpdateCoopResume method.
     * 
     * @throws CRMDBException
     */
    public void validateAddOrUpdateCoopResume() throws CRMDBException {

        coopResumeDto = (CoopResumeDto) CRMUtils.jsonToBean(super.jsonString, CoopResumeDto.class);
        if (StringUtils.isBlank(coopResumeDto.getProjectName())) {
            addFieldError(CustomerConstant.PROJECT_NAME, this.getText("projectName.invalid"));
        } else if (coopResumeDto.getProjectName().length() > 50) {
            addFieldError(CustomerConstant.PROJECT_NAME, this.getText("projectNameRegex.invalid"));
        } else if (coopResumeService.judgeProjectName(coopResumeDto)) {
            addFieldError(CustomerConstant.PROJECT_NAME, this.getText("projectNameExist.invalid"));
        }
        if (StringUtils.isBlank(coopResumeDto.getProjectType())) {
            addFieldError(CustomerConstant.PROJECTTYPE, this.getText("projectType.invalid"));
        } else if (coopResumeDto.getProjectType().length() > 12) {
            addFieldError(CustomerConstant.PROJECTTYPE, this.getText("projectTypeRegex.invalid"));
        }
        if (StringUtils.isBlank(coopResumeDto.getExpBeginDate())) {
            addFieldError(CustomerConstant.EXP_BEGIN_DATE, this.getText("expEndDate.invalid"));
        } else if (!CRMUtils.dateCheck(coopResumeDto.getExpBeginDate())) {
            addFieldError(CustomerConstant.EXP_BEGIN_DATE, this.getText("dateRegex.invalid"));
        }

        if (StringUtils.isBlank(coopResumeDto.getExpEndDate())) {
            addFieldError(CustomerConstant.EXP_END_DATE, this.getText("expBeginDate.invalid"));
        } else if (!CRMUtils.dateCheck(coopResumeDto.getExpEndDate())) {
            addFieldError(CustomerConstant.EXP_END_DATE, this.getText("dateRegex.invalid"));
        }

        if (!CRMUtils.dateJudge(coopResumeDto.getExpBeginDate(), coopResumeDto.getExpEndDate())) {
            addFieldError(CustomerConstant.EXP_BEGIN_DATE, this.getText("dateCheck.invalid"));
            addFieldError(CustomerConstant.EXP_END_DATE, this.getText("dateCheck.invalid"));
        }
        if (!CRMUtils.dateJudge(coopResumeDto.getBeginDate(), coopResumeDto.getEndDate())) {
            addFieldError(CustomerConstant.BEGIN_DATE, this.getText("dateCheck.invalid"));
            addFieldError(CustomerConstant.END_DATE, this.getText("dateCheck.invalid"));
        }
        if (StringUtils.isBlank(coopResumeDto.getProjectScale())) {
            addFieldError(CustomerConstant.PROJECTSCALE, this.getText("peopleNumber.invalid"));
        }

        if (!(coopResumeDto.getPeopleNumber() > 0)) {
            addFieldError(CustomerConstant.PEOPLE_NUMBER, this.getText("dateCheck.invalid"));
        }

        if (StringUtils.isBlank(coopResumeDto.getPrincipalWe())) {
            addFieldError(CustomerConstant.PRINCIPAL_WE, this.getText("principalWe.invalid"));
        } else if (coopResumeDto.getPrincipalWe().length() > 50
                || coopResumeDto.getPrincipalWe().length() < 2) {
            addFieldError(CustomerConstant.PRINCIPAL_WE, this.getText("principalWeRegex.invalid"));
        }

        if (StringUtils.isBlank(coopResumeDto.getPrincipalThey())) {
            addFieldError(CustomerConstant.PRINCIPAL_THEY, this.getText("principalThey.invalid"));
        } else if (coopResumeDto.getPrincipalThey().length() > 50
                || coopResumeDto.getPrincipalThey().length() < 2) {
            addFieldError(CustomerConstant.PRINCIPAL_THEY,
                    this.getText("principalTheyRegex.invalid"));
        }

        if (coopResumeDto.getAppraisal().length() > 1024) {
            addFieldError(CustomerConstant.APPRAISAL, this.getText("appraisal.invalid"));
        }
        if (coopResumeDto.getProjectDetail().length() > 1024) {
            addFieldError(CustomerConstant.PROJECT_DETAIL, this.getText("projectDetail.invalid"));
        }
        if (coopResumeDto.getDescriptions().length() > 1024) {
            addFieldError(CustomerConstant.DESCRIPTIONS,
                    this.getText("workDescriptionsLength.invalid"));
        }
        if (getFieldErrors().size() != 0) {
            map.putAll(getFieldErrors());
            map.put(CRMConstant.VALIDATE, false);
        }
    }

    /**
     * The method is used to get customer cooperator resume List.
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    public String getCoopResume() throws CRMDBException {
        LOG.debug("method coopResumeList start.");
        CoopResumeSearchBean searchValue = (CoopResumeSearchBean) CRMUtils.jsonToBean(jsonString,
                CoopResumeSearchBean.class);
        // query coopResume or init page.
        map = coopResumeService.searchOrGetAllCoopResume(super.searchFlag, searchValue, super.page,
                customerID, limit);
        LOG.debug("method coopResumeList end.");
        return SUCCESS;
    }

    /**
     * The method is used to add or update record.
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    public String addOrUpdateCoopResume() throws CRMDBException {
        LOG.debug("method addOrUpdateCoopResume start.");
        coopResumeService.addOrUpdateCoopResume(coopResumeDto);
        LOG.debug("method addOrUpdateCoopResume end.");
        return SUCCESS;
    }

    /**
     * The method is used to delete record.
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    public String deleteCoopResume() throws CRMDBException {
        LOG.debug("method deleteCoopResume start.");
        coopResumeService.deleteCoopResume(super.jsonString);
        LOG.debug("method deleteCoopResume end.");
        return SUCCESS;
    }

    /**
     * @return the coopResumeService
     */
    public CoopResumeService getCoopResumeService() {
        return coopResumeService;
    }

    /**
     * @param coopResumeService
     *            the coopResumeService to set
     */
    public void setCoopResumeService(CoopResumeService coopResumeService) {
        this.coopResumeService = coopResumeService;
    }

    /**
     * @return the customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID
     *            the customerID to set
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
