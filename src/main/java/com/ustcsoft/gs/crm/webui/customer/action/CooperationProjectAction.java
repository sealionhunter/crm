package com.ustcsoft.gs.crm.webui.customer.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.customer.bean.CooperationProjectSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.CooperationProjectDto;
import com.ustcsoft.gs.crm.webui.customer.service.CooperationProjectService;

/**
 * cooperator project information's action operation
 * 
 * @author xujialong
 * 
 */
public class CooperationProjectAction extends CRMAction {

    private static final long serialVersionUID = 1L;

    private static Log LOG = LogFactory.getLog(CooperationProjectAction.class);

    private CooperationProjectService cooperationProjectService = null;

    private CooperationProjectDto cpd = null;

    /**
     * get 25 lines of cooperationProject and show in page.
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String getAllCooperationProject() throws CRMDBException {
        LOG.debug("method getAllCooperationProject started!");
        CooperationProjectSearchBean searchBean = (CooperationProjectSearchBean) CRMUtils
                .jsonToBean(jsonString, CooperationProjectSearchBean.class);
        map = cooperationProjectService.searchCooperationProject(searchFlag, searchBean, page,
                limit);
        LOG.debug("method getAllCooperationProject end!");
        return SUCCESS;
    }

    /**
     * delete cooperationProjects
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String deleteCooperationProject() throws CRMDBException {
        LOG.debug("method deleteCooperationProject start!");
        cooperationProjectService.deleteCooperationProject(jsonString);
        LOG.debug("method deleteCooperationProject end!");
        return SUCCESS;
    }

    /**
     * add or update cooperationProjects
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String updateCooperationProject() throws CRMDBException {
        LOG.debug("method updateCooperationProject start!");
        cpd.setIsAbolished(false);
        cooperationProjectService.updateCooperationProject(cpd);
        LOG.debug("method updateCooperationProject end!");
        return SUCCESS;
    }

    /**
     * validate input value (cooperationProject:add,update)
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void validateUpdateCooperationProject() throws CRMDBException {
        cpd = (CooperationProjectDto) CRMUtils.jsonToBean(super.jsonString,
                CooperationProjectDto.class);
        if (cooperationProjectService.checkNameExisted(cpd)) {
            addFieldError("projectName", this.getText("projectName.existed"));
        }
        if (cpd.getProjectName().length() == 0 || cpd.getProjectName().length() > 50) {
            addFieldError("projectName", this.getText("projectName.invalid"));
        }
        if (cpd.getPrincipalWe().length() == 0 || cpd.getPrincipalWe().length() > 20) {
            addFieldError("principalWe", this.getText("contactPersonName.invalid"));
        }
        if (cpd.getPrincipalCooperator().length() == 0
                || cpd.getPrincipalCooperator().length() > 20) {
            addFieldError("principalCooperator", this.getText("contactPersonName.invalid"));
        }
        if (cpd.getAppraisal().length() > 1024) {
            addFieldError("appraisal", this.getText("appraisal.invalid"));
        }
        if (cpd.getProjectDetail().length() > 1024) {
            addFieldError("projectDetail", this.getText("detail.invalid"));
        }
        if (!CRMUtils.dateCheck(cpd.getExpectedBeginTime())) {
            addFieldError("expectedBeginTime", this.getText("minDateSearch.invalid"));
        }
        if (!CRMUtils.dateCheck(cpd.getExpectedEndTime())) {
            addFieldError("expectedEndTime", this.getText("minDateSearch.invalid"));
        }
        if (!CRMUtils.dateCheck(cpd.getRealBeginTime())) {
            addFieldError("realBeginTime", this.getText("minDateSearch.invalid"));
        }
        if (!CRMUtils.dateCheck(cpd.getRealEndTime())) {
            addFieldError("realEndTime", this.getText("minDateSearch.invalid"));
        }
        if (cpd.getProjectScale() != null) {
            if (cpd.getProjectScale() < 0 || cpd.getProjectScale() > 10000) {
                addFieldError("projectScale", this.getText("number.invalid"));
            }
        }
        if (cpd.getCooperatorScale() != null) {
            if (cpd.getCooperatorScale() < 0 || cpd.getCooperatorScale() > 10000) {
                addFieldError("getCooperatorScale", this.getText("number.invalid"));
            }
        }
        if (cpd.getCooperatorPeopleNumber() != null) {
            if (cpd.getCooperatorPeopleNumber() < 0 || cpd.getCooperatorPeopleNumber() > 10000) {
                addFieldError("getCooperatorPeopleNumber", this.getText("number.invalid"));
            }
        }
        if (cpd.getPrincipalCooperatorPhone().isEmpty()
                || !cpd.getPrincipalCooperatorPhone().matches(CRMConstant.TELEPHONE_REGEX)) {
            addFieldError("principalCooperatorPhone", this.getText("telephone.invalid"));
        }

        showFieldError();
    }

    /**
     * @return the cooperationProjectService
     */
    public CooperationProjectService getCooperationProjectService() {
        return cooperationProjectService;
    }

    /**
     * @param cooperationProjectService
     *            the cooperationProjectService to set
     */
    public void setCooperationProjectService(CooperationProjectService cooperationProjectService) {
        this.cooperationProjectService = cooperationProjectService;
    }
}