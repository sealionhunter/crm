package com.ustcsoft.gs.crm.webui.customer.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.exception.CRMException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.customer.bean.FileTemplateNameBean;
import com.ustcsoft.gs.crm.webui.customer.bean.ProposalOrContractSearchBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.customer.dto.ProposalOrContractDto;
import com.ustcsoft.gs.crm.webui.customer.service.ProposalOrContractService;

/**
 * action for contract or proposal ,execute the page's action.
 * 
 * @author zhangqiuli
 */
public class ProposalOrContractAction extends CRMAction implements ServletRequestAware {

    private static final long serialVersionUID = 1L;
    /** statement log */
    private static final Log LOG = LogFactory.getLog(ProposalOrContractAction.class);
    private HttpServletRequest request;
    /** statement contractService */
    private ProposalOrContractService proposalOrContractService = null;
    private String filePath = "";
    private boolean success = false;
    private int type;
    private String name;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get all contract for list.
     * 
     * @return SUCCESS
     * @throws CRMDBException
     */
    @Override
    public String execute() throws CRMDBException {
        LOG.debug(" method execute start!");
        JSONObject json = JSONObject.fromObject(jsonString);
        ProposalOrContractSearchBean proposalOrContractSearchBean = (ProposalOrContractSearchBean) JSONObject
                .toBean(json, ProposalOrContractSearchBean.class);
        map = proposalOrContractService.getAllProposalOrContract(searchFlag,
                proposalOrContractSearchBean, page, super.limit);
        LOG.debug("method execute end!");
        return SUCCESS;
    }

    /**
     * method for get fileTemplateName and fileTemplateValue.
     * 
     * @return SUCCESS
     * @throws CRMDBException
     */
    public String getFileTemplateName() throws CRMDBException {
        LOG.debug("method  getFileTemplateName start!");
        List<FileTemplateNameBean> fileTemplateName = proposalOrContractService
                .getFileTemplateName(type);
        map.put(CRMConstant.ITEMS, fileTemplateName);
        map.put(CRMConstant.TOTAL, fileTemplateName.size());
        LOG.debug("method  getFileTemplateName end!");
        return SUCCESS;
    }

    /**
     * delete contract or proposal from db
     * 
     * @return success
     * @throws CRMDBException
     */
    public String deleteProposalOrContract() throws CRMDBException {
        LOG.debug("method deleteProposalOrContract start!");
        proposalOrContractService.deleteProposalOrContract(jsonString);
        LOG.debug("method deleteProposalOrContract end!");
        return SUCCESS;
    }

    /**
     * add or update contract or proposal
     * 
     * @return success
     * @throws CRMDBException
     */
    public String addOrUpdateProposalOrContract() throws CRMDBException {
        LOG.debug("method addOrUpdateProposalOrContract start!");
        ProposalOrContractDto contract = (ProposalOrContractDto) CRMUtils.jsonToBean(jsonString,
                ProposalOrContractDto.class);
        proposalOrContractService.addOrUpdateProposalOrContract(contract);
        LOG.debug("method addOrUpdateProposalOrContract end!");
        return SUCCESS;
    }

    /**
     * when to add or edit contract, verify whether accord with conditions
     * 
     * @throws CRMDBException
     */
    public void validateAddOrUpdateProposalOrContract() throws CRMDBException {
        LOG.debug("method validateAddOrUpdateProposalOrContract started!");
        ProposalOrContractDto cod = (ProposalOrContractDto) CRMUtils.jsonToBean(jsonString,
                ProposalOrContractDto.class);
        if (!proposalOrContractService.checkProposalOrContractName(cod)) {
            addFieldError("proposalOrContractName", this.getText("contractNameCheck.invalid"));
        }
        if (cod.getProposalOrContractID() != 0
                && !CRMUtils.dateCheck(cod.getProposalOrContractEditDate())) {
            addFieldError("proposalOrContractEditDate", this.getText(CustomerConstant.TIMEERROR));
        }

        if (cod.getProposalOrContractName().isEmpty()) {
            addFieldError("proposalOrContractName", this.getText("contractnameN.invalid"));
        }
        if (cod.getProposalOrContractName().length() > CRMConstant.LENGTH_1) {
            addFieldError("proposalOrContractName", this.getText("contractName.invalid"));
        }
        if (cod.getProposalOrContractObject().isEmpty()) {
            addFieldError("proposalOrContractObject", this.getText("contractObjectN.invalid"));
        }
        if (cod.getProposalOrContractObject().length() > CRMConstant.LENGTH_1) {
            addFieldError("proposalOrContractObject", this.getText("contractObject.invalid"));
        }
        if (cod.getProposalOrContractType().isEmpty()) {
            addFieldError("proposalOrContractType", this.getText("contractTypeN.invalid"));
        }
        if (cod.getProposalOrContractType().length() > CRMConstant.LENGTH_1) {
            addFieldError("proposalOrContractType", this.getText("contractType.invalid"));
        }
        if (cod.getDescriptions().length() > CRMConstant.LENGTH_2) {
            addFieldError("descriptions", this.getText("contractDescriptions.invalid"));
        }
        if (!CRMUtils.dateCheck(cod.getProposalOrContractAddDate())) {
            addFieldError("proposalOrContractAddDate", this.getText(CustomerConstant.TIMEERROR));
        }
        showFieldError();
        LOG.debug("method validateAddOrUpdateProposalOrContract end!");
    }

    /**
     * used to verify retrieval condition are accord with a condition
     */
    @Override
    public void validateExecute() {

        LOG.debug("method validateExecute start!");
        JSONObject json = JSONObject.fromObject(jsonString);
        ProposalOrContractSearchBean pocsb = (ProposalOrContractSearchBean) JSONObject.toBean(json,
                ProposalOrContractSearchBean.class);
        String addMin = pocsb.getProposalOrContractAddDateMin();
        String addMax = pocsb.getProposalOrContractAddDateMax();
        String editMin = pocsb.getProposalOrContractEditDateMin();
        String editMax = pocsb.getProposalOrContractEditDateMax();
        if (searchFlag == 1) {
            if (pocsb.getTextValue().length() > CRMConstant.CONTRACTLENGTH_2) {
                addFieldError("searchTextError", this.getText("searchText.invalid"));
            }
        } else if (searchFlag == 2) {
            if (pocsb.getProposalOrContractType().length() > CRMConstant.CONTRACTLENGTH_1) {
                addFieldError("typeError", this.getText("contractType.invalid"));
            }
            if (pocsb.getProposalOrContractObject().length() > CRMConstant.CONTRACTLENGTH_1) {
                addFieldError("objectError", this.getText("contractObject.invalid"));
            }
            if (pocsb.getProposalOrContractName().length() > CRMConstant.CONTRACTLENGTH_1) {
                addFieldError("nameError", this.getText("contractName.invalid"));
            }
            if (!addMax.isEmpty() && !CRMUtils.dateCheck(addMax)) {
                addFieldError("addTimeMax", this.getText(CustomerConstant.TIMEERROR));
            }
            if (!addMin.isEmpty() && !CRMUtils.dateCheck(addMin)) {
                addFieldError("addTimeMin", this.getText(CustomerConstant.TIMEERROR));
            }
            if (!editMax.isEmpty() && !CRMUtils.dateCheck(editMax)) {
                addFieldError("editTimeMax", this.getText(CustomerConstant.TIMEERROR));
            }
            if (!editMin.isEmpty() && !CRMUtils.dateCheck(editMin)) {
                addFieldError("editTimeMin", this.getText(CustomerConstant.TIMEERROR));
            }

            if (!addMin.equals("") && !addMax.equals("") && !CRMUtils.dateJudge(addMin, addMax)) {
                addFieldError("addTimeError", this.getText("timeCompare.invalid"));
            }
            if (!editMin.equals("") && !editMax.equals("") && !CRMUtils.dateJudge(editMin, editMax)) {
                addFieldError("editTimeError", this.getText("timeCompare.invalid"));
            }
        }
        map.putAll(getFieldErrors());
        LOG.debug("method validateExecute end!");
    }

    /**
     * the method is used to create the word of contract
     * 
     * @return SUCCESS
     * @throws CRMDBException
     * @throws CRMException
     */
    public String createDoc() throws CRMDBException, CRMException {
        LOG.debug("method createDoc start!");
        filePath = CRMUtils.createDoc(request, "proposalOrContract", name);
        success = true;
        LOG.debug("method createDoc end!");
        return SUCCESS;
    }

    @JSON(serialize = false)
    public ProposalOrContractService getProposalOrContractService() {
        return proposalOrContractService;
    }

    public String getFilePath() {
        return filePath;
    }

    public boolean isSuccess() {
        return success;
    }

    /**
     * proposalOrContractService's set method
     * 
     * @param proOrCon
     */
    public void setProposalOrContractService(final ProposalOrContractService proOrCon) {
        proposalOrContractService = proOrCon;
    }

    /**
     * filePath to set
     * 
     * @param filePath
     */
    public void setFilePath(final String filePath) {
        this.filePath = filePath;
    }

    /**
     * success to set
     * 
     * @param success
     */
    public void setSuccess(final boolean success) {
        this.success = success;
    }

    @Override
    public void setServletRequest(final HttpServletRequest request) {
        this.request = request;

    }

    /**
     * get type
     * 
     * @return type
     */
    public int getType() {
        return type;
    }

    /**
     * type to set
     * 
     * @param type
     */
    public void setType(final int type) {
        this.type = type;
    }

}
