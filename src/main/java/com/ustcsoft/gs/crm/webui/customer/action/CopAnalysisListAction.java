/*
 * Class name: CopAnalysisListAction
 * 
 * Version information: 1.0
 * 
 * Date:2012.10.8
 *  
 */
package com.ustcsoft.gs.crm.webui.customer.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.code.dto.CodeDto;
import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.customer.dto.CopAnalysisDto;
import com.ustcsoft.gs.crm.webui.customer.service.CopAnalysisService;

/**
 * Cooperator analysis operations action.
 * 
 * @author xujueyin
 * 
 */
public class CopAnalysisListAction extends CRMAction {

    private static final long serialVersionUID = 1L;

    /** get Log */
    private static final Log LOG = LogFactory.getLog(CopAnalysisListAction.class);

    /**
     * cooperator analysis service to operate the table named
     * CooperatorAnalyInfo
     */
    private CopAnalysisService copAnalysisService = null;

    /** cooperator analysis dto */
    private CopAnalysisDto copAnalysis = null;

    /** cooperator analysis search id */
    private int cooperatorID = -1;

    /**
     * @return the copAnalysisService
     */
    public CopAnalysisService getCopAnalysisService() {
        return copAnalysisService;
    }

    /**
     * @param copAnalysisService
     *            the copAnalysisService to set
     */
    public void setCopAnalysisService(CopAnalysisService copAnalysisService) {
        this.copAnalysisService = copAnalysisService;
    }

    /**
     * @return the cooperatorID
     */
    public int getCooperatorID() {
        return cooperatorID;
    }

    /**
     * @param cooperatorID
     *            the cooperatorID to set
     */
    public void setCooperatorID(int cooperatorID) {
        this.cooperatorID = cooperatorID;
    }

    /**
     * get all cooperator analysis information and show in page.
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * @return SUCCESS
     */
    public String getAllCopAnalysis() throws CRMDBException {
        LOG.debug("method getAllCopAnalysis start!");
        // load cooperator analysis to page from table CooperatorAnalyInfo
        map = copAnalysisService.getAllCopAnalysis(page, limit);
        LOG.debug("method getAllCopAnalysis end!");
        return SUCCESS;
    }

    /**
     * get cooperator name set to cooperator analysis
     * 
     * @return SUCCESS
     * @throws CRMDBException
     */
    public String getCopName() throws CRMDBException {
        LOG.debug("method getCopName() start!");
        List<CodeDto> copNameList = copAnalysisService.getCopName();
        map.put("items", copNameList);
        LOG.debug("method getCopName() end!");
        return SUCCESS;
    }

    /**
     * add or update cooperator analysis.
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * @return SUCCESS
     */
    public String saveOrUpdateCopAnalysis() throws CRMDBException {
        LOG.debug("method saveOrUpdateCopAnalysis start!");
        // save or update cooperator analysis
        copAnalysisService.saveOrUpdateCopAnalysis(copAnalysis);
        LOG.debug("method saveOrUpdateCopAnalysis end!");
        return SUCCESS;
    }

    /**
     * delete cooperator analysis.
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * @return SUCCESS
     */
    public String deleteCopAnalysis() throws CRMDBException {
        LOG.debug("method deleteCopAnalysis start!");
        // batch remove cooperator analysis
        copAnalysisService.deleteCopAnalysis(jsonString);
        LOG.debug("method deleteCopAnalysis end!");
        return SUCCESS;
    }

    /**
     * get a assigned cooperator analysis information by cooperator ID.
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * @return SUCCESS
     */
    public String getCopAnalysisByID() throws CRMDBException {
        LOG.debug("method getCopAnalysisByID start!");
        map = copAnalysisService.getCopAnalysisByID(cooperatorID, page);
        LOG.debug("method getCopAnalysisByID end!");
        return SUCCESS;
    }

    /**
     * validate input cooperator analysis information.
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void validateSaveOrUpdateCopAnalysis() throws CRMDBException {
        String strValid = null;
        copAnalysis = (CopAnalysisDto) CRMUtils.jsonToBean(jsonString, CopAnalysisDto.class);

        // check advantageField
        strValid = copAnalysis.getAdvantageField();
        if (StringUtils.isBlank(strValid) || strValid.length() > 1024) {
            addFieldError("advantageField", this.getText("advantageField.invalid"));
        }

        // check disadvantageField
        strValid = copAnalysis.getDisadvantageField();
        if (StringUtils.isBlank(strValid) || strValid.length() > 1024) {
            addFieldError("disadvantageField", this.getText("disadvantageField.invalid"));
        }

        // check managementAbility
        strValid = copAnalysis.getManagementAbility();
        if (StringUtils.isBlank(strValid) || strValid.length() > 12
                || !strValid.matches(CRMConstant.COMBO_REG)) {
            addFieldError("managementAbility", this.getText("managementAbility.invalid"));
        }

        // check responseSpeed
        strValid = copAnalysis.getResponseSpeed();
        if (StringUtils.isBlank(strValid) || strValid.length() > 12
                || !strValid.matches(CRMConstant.COMBO_REG)) {
            addFieldError("responseSpeed", this.getText("responseSpeed.invalid"));
        }

        // check trustDegree
        strValid = copAnalysis.getTrustDegree();
        if (StringUtils.isBlank(strValid) || strValid.length() > 12
                || !strValid.matches(CRMConstant.COMBO_REG)) {
            addFieldError("trustDegree", this.getText("trustDegree.invalid"));
        }

        // check engLevelEvaluation
        strValid = copAnalysis.getEngLevelEvaluation();
        if (StringUtils.isBlank(strValid) || strValid.length() > 1024) {
            addFieldError("engLevelEvaluation", this.getText("engLevelEvaluation.invalid"));
        }

        // check cooperationSummarize
        strValid = copAnalysis.getCooperationSummarize();
        if (StringUtils.isBlank(strValid) || strValid.length() > 1024) {
            addFieldError("cooperationSummarize", this.getText("cooperationSummarize.invalid"));
        }

        // check comphsAnalysis
        strValid = copAnalysis.getComphsAnalysis();
        if (StringUtils.isBlank(strValid) || strValid.length() > 1024) {
            addFieldError("comphsAnalysis", this.getText("comphsAnalysis.invalid"));
        }

        // check descriptions
        strValid = copAnalysis.getDescriptions();
        if (strValid.length() > 1024) {
            addFieldError("descriptions", this.getText("descriptions.invalid"));
        }

        // put all error messages to map
        showFieldError();
    }
}