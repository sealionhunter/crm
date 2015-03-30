package com.ustcsoft.gs.crm.webui.customer.action;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.json.annotations.JSON;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.customer.bean.ContractSearchBean;
import com.ustcsoft.gs.crm.webui.customer.bean.FileTemplateNameBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.customer.dto.ContractDto;
import com.ustcsoft.gs.crm.webui.customer.service.ContractService;
import com.ustcsoft.gs.crm.webui.customer.service.ProposalOrContractService;

/**
 * action for contract or proposal ,execute the page's action.
 * 
 * @author zhangqiuli
 */
public class ContractAction extends CRMAction {

    private static final long serialVersionUID = 1L;
    /** statement log */
    private static final Log LOG = LogFactory.getLog(ContractAction.class);
    /** statement proposalOrContractService */
    private ProposalOrContractService proposalOrContractService = null;
    /** statement contractService */
    private ContractService contractService = null;
    /** used for file path */
    private String filePath = "";
    private boolean success = false;
    private int type;
    /** page start */
    private int start;

    private String demand;

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    /**
     * @return the contractService
     */
    public ContractService getContractService() {
        return contractService;
    }

    /**
     * @param contractService
     *            the contractService to set
     */
    public void setContractService(ContractService contractService) {
        this.contractService = contractService;
    }

    /**
     * used to verify retrieval condition are accord with a condition
     */
    @Override
    public void validateExecute() {
        LOG.debug("method validateExecute start!");
        JSONObject json = JSONObject.fromObject(jsonString);
        ContractSearchBean csb = (ContractSearchBean) JSONObject.toBean(json,
                ContractSearchBean.class);
        if (searchFlag == 1) {
            if (csb.getTextValue().length() > CRMConstant.CONTRACTLENGTH_2) {
                addFieldError(CustomerConstant.SEARCHTEXTERROR,
                        this.getText(CustomerConstant.SEARCHTEXTINVAILD));
            }
        } else if (searchFlag == 2) {
            if (csb.getContractName().length() > CRMConstant.CONTRACTLENGTH_1) {
                addFieldError(CustomerConstant.CONTRACTNAMEERROR,
                        this.getText(CustomerConstant.CONTRACTNAMEINVALID));
            }
            if (csb.getFileTemplateName().length() > CRMConstant.CONTRACTLENGTH_1) {
                addFieldError(CustomerConstant.FILETEMPLATENAMEERROR,
                        this.getText(CustomerConstant.FILETEMPLATENAMEINVALID));
            }
        }
        map.putAll(getFieldErrors());
        LOG.debug("method validateExecute end!");
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
        ContractSearchBean ContractSearchBean = (ContractSearchBean) JSONObject.toBean(json,
                ContractSearchBean.class);
        map = contractService.getAllContract(searchFlag, ContractSearchBean, start, super.limit);
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
     * delete contract from db
     * 
     * @return success
     * @throws CRMDBException
     */
    public String deleteContract() throws CRMDBException {
        LOG.debug("method deleteContract start!");
        contractService.deleteContract(jsonString);
        LOG.debug("method deleteContract end!");
        return SUCCESS;
    }

    /**
     * add or update contract or proposal
     * 
     * @return success
     * @throws CRMDBException
     */
    public String addOrUpdateContract() throws CRMDBException {
        ContractDto contractDto = (ContractDto) CRMUtils.jsonToBean(jsonString, ContractDto.class);
        int eventID = (Integer) contractService.getContractEvent(contractDto.getOrderID()).get(
                "eventID");
        if (eventID != 0) {
            contractService.updateContractEvent(eventID, demand, contractDto, userID);
        }
        contractDto.setPayEndTime(contractDto.getPayEndTime() + "-01");
        contractService.addOrUpdateContract(contractDto);
        map.put("state", "success");
        return SUCCESS;
    }

    /**
     * add or update contract or proposal
     * 
     * @return success
     * @throws CRMDBException
     */
    public String NameOrOrderIdIsExit() {
        ContractDto contractDto = (ContractDto) CRMUtils.jsonToBean(jsonString, ContractDto.class);
        if (contractService.NameOrOrderIdIsExit(contractDto.getContractName()) == true) {
            map.put("state", "exit");
        } else {
            map.put("state", "noExit");
        }
        return SUCCESS;
    }

    /**
     * add or update contract or proposal
     * 
     * @return success
     * @throws CRMDBException
     */
    public String getContractEvent() {
        ContractDto contractDto = (ContractDto) CRMUtils.jsonToBean(jsonString, ContractDto.class);
        map = contractService.getContractEvent(contractDto.getOrderID());
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
     * success to set
     * 
     * @param success
     */
    public void setSuccess(final boolean success) {
        this.success = success;
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

    /**
     * @return the start
     */
    public int getStart() {
        return start;
    }

    /**
     * @param start
     *            the start to set
     */
    public void setStart(int start) {
        this.start = start;
    }

}
