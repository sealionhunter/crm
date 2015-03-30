/*
 * Copyright 2012 the original author or authors.
 */
package com.ustcsoft.gs.crm.webui.customer.action;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.CprNameChooseBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.customer.dto.CprAnalysisDto;
import com.ustcsoft.gs.crm.webui.customer.service.CprAnalysisService;

/**
 * Description: The class is used to deal with the page, which is
 * CprAnalysisList.js.
 * 
 * @author wuhao1
 * @author libaoshan
 * @since August 2012
 */
public class CprAnalysisAction extends CRMAction {

    private static final long serialVersionUID = 1L;

    private static Log LOG = LogFactory.getLog(CprAnalysisAction.class);

    /** Used for getting class CprAnalysisService */
    private CprAnalysisService cprAnalysisService = null;

    /** Store the info with CprAnalysisDto */
    private CprAnalysisDto cprAnalysis = null;

    private int competitorInfoId = 0;

    /**
     * Get all the analysis information for competitors.
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * 
     * @return SUCCESS
     */
    @Override
    public String execute() throws CRMDBException {
        LOG.debug("method execute() start!");
        map = cprAnalysisService.getAnalysis(page, competitorInfoId, super.limit);
        LOG.debug("method execute() end!");
        return SUCCESS;
    }

    /**
     * Description: Get all the competitors' id and name from table
     * CompetitorInfo.
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String getAllCprName() throws CRMDBException {
        LOG.debug("method getAllCprName start!");
        List<CprNameChooseBean> cprNameList = cprAnalysisService.getAllCprName();
        map.put(CRMConstant.ITEMS, cprNameList);
        LOG.debug("method getAllCprName end!");
        return SUCCESS;
    }

    /**
     * Description: Verify AddOrEditCprAnalysis page input is in accordance with
     * the requirements.
     * 
     */
    @Override
    public void validateUpdate() {
        LOG.debug("method validateUpdate() start!");

        // Change the date from String to CprAnalysisDto
        JSONObject json = JSONObject.fromObject(jsonString);
        cprAnalysis = (CprAnalysisDto) JSONObject.toBean(json, CprAnalysisDto.class);

        // Check competitorInfoId
        if (cprAnalysis.getCompetitorInfoId() <= CRMConstant.ZERO) {
            addFieldError(CustomerConstant.COMPETITORINFOID,
                    this.getText(CustomerConstant.COMPETITORINFOID_INVALID));
        }

        // Check area
        if (cprAnalysis.getArea().length() <= CRMConstant.ZERO
                || cprAnalysis.getArea().length() > CRMConstant.AREA_MAX_LENGTH) {
            if (cprAnalysis.getArea().length() <= CRMConstant.ZERO) {
                addFieldError(CustomerConstant.AREA,
                        this.getText(CustomerConstant.AREANULL_INVALID));
            } else {
                addFieldError(CustomerConstant.AREA, this.getText(CustomerConstant.AREA_INVALID));
            }
        }

        // Check ability
        if (cprAnalysis.getAbility().length() > CRMConstant.MAX_LENGTH) {
            addFieldError(CustomerConstant.ABILITY,
                    this.getText(CustomerConstant.MAXLENGTH_INVALID));
        }

        // Check targets
        if (cprAnalysis.getTargets().length() > CRMConstant.MAX_LENGTH) {
            addFieldError(CustomerConstant.TARGETS,
                    this.getText(CustomerConstant.MAXLENGTH_INVALID));
        }

        // Check targets
        if (cprAnalysis.getStrategy().length() > CRMConstant.MAX_LENGTH) {
            addFieldError(CustomerConstant.STRATEGY,
                    this.getText(CustomerConstant.MAXLENGTH_INVALID));
        }

        // Check prediction
        if (cprAnalysis.getPrediction().length() > CRMConstant.MAX_LENGTH) {
            addFieldError(CustomerConstant.PERDICTION,
                    this.getText(CustomerConstant.MAXLENGTH_INVALID));
        }

        // Check advantage
        if (cprAnalysis.getAdvantage().length() > CRMConstant.MAX_LENGTH) {
            addFieldError(CustomerConstant.ADVANTAGE,
                    this.getText(CustomerConstant.MAXLENGTH_INVALID));
        }
        if (cprAnalysis.getAdvantage().length() <= CRMConstant.ZERO) {
            addFieldError(CustomerConstant.ADVANTAGE,
                    this.getText(CustomerConstant.ADVANTAGE_INVALID));
        }

        // Check disadvantage
        if (cprAnalysis.getDisadvantage().length() > CRMConstant.MAX_LENGTH) {
            addFieldError(CustomerConstant.DISADVANTAGE,
                    this.getText(CustomerConstant.MAXLENGTH_INVALID));
        }
        if (cprAnalysis.getDisadvantage().length() <= CRMConstant.ZERO) {
            addFieldError(CustomerConstant.DISADVANTAGE,
                    this.getText(CustomerConstant.DISADVANTAGE_INVALID));
        }

        // Check advanAlysis
        if (cprAnalysis.getAdvAnalysis().length() > CRMConstant.MAX_LENGTH) {
            addFieldError(CustomerConstant.ADVANALYSIS,
                    this.getText(CustomerConstant.MAXLENGTH_INVALID));
        }

        // Check disadvanAlysis
        if (cprAnalysis.getDisadvAnalysis().length() > CRMConstant.MAX_LENGTH) {
            addFieldError(CustomerConstant.DISADVANALYSIS,
                    this.getText(CustomerConstant.MAXLENGTH_INVALID));
        }

        // Check others
        if (cprAnalysis.getOthers().length() > CRMConstant.MAX_LENGTH) {
            addFieldError(CustomerConstant.OTHERS, this.getText(CustomerConstant.MAXLENGTH_INVALID));
        }

        // Check compositeComp
        if (cprAnalysis.getCompositeComp() < CRMConstant.ZERO
                || cprAnalysis.getCompositeComp() > CRMConstant.COMPOSITECOMPDEFENSE_MAX_LENGTH) {
            addFieldError(CustomerConstant.COMPOSITECOMP,
                    this.getText(CustomerConstant.COMPOSITECOMPDEFENSE_INVALID));
        }

        // Check compositeDefense
        if (cprAnalysis.getCompositeDefense() < CRMConstant.ZERO
                || cprAnalysis.getCompositeDefense() > CRMConstant.COMPOSITECOMPDEFENSE_MAX_LENGTH) {
            addFieldError(CustomerConstant.COMPOSITEDEFENSE,
                    this.getText(CustomerConstant.COMPOSITECOMPDEFENSE_INVALID));
        }

        // Check advice
        if (cprAnalysis.getAdvice().length() > CRMConstant.MAX_LENGTH) {
            addFieldError(CustomerConstant.ADVICE, this.getText(CustomerConstant.MAXLENGTH_INVALID));
        }

        // Verify all of info
        if (getFieldErrors().size() != CRMConstant.ZERO) {
            addFieldError(CRMConstant.VALIDATE, CRMConstant.FALSE);
            map.putAll(getFieldErrors());
        }
        LOG.debug("method validateUpdate() end!");
    }

    /**
     * Description: Add or edit the information of table CompetitorInfo.
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public String update() throws CRMDBException {
        LOG.debug("method update() start!");
        cprAnalysisService.updateCprAnalysis(cprAnalysis);
        LOG.debug("method update() end!");
        return SUCCESS;
    }

    /**
     * Description: Delete the information of table CompetitorInfo.
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public String delete() throws CRMDBException {
        LOG.debug("method delete() start!");
        cprAnalysisService.deleteCprAnalysis(jsonString);
        LOG.debug("method delete() end!");
        return SUCCESS;
    }

    /**
     * @return the cprAnalysisService
     */
    public CprAnalysisService getCprAnalysisService() {
        return cprAnalysisService;
    }

    /**
     * @param cprAnalysisService
     *            the cprAnalysisService to set
     */
    public void setCprAnalysisService(CprAnalysisService cprAnalysisService) {
        this.cprAnalysisService = cprAnalysisService;
    }

    /**
     * @return the competitorInfoId
     */
    public int getCompetitorInfoId() {
        return competitorInfoId;
    }

    /**
     * @param competitorInfoId
     *            the competitorInfoId to set
     */
    public void setCompetitorInfoId(int competitorInfoId) {
        this.competitorInfoId = competitorInfoId;
    }

}
