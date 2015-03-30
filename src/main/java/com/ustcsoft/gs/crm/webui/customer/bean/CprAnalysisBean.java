/*
 * Copyright 2012 the original author or authors.
 */
package com.ustcsoft.gs.crm.webui.customer.bean;

import com.ustcsoft.gs.crm.webui.customer.dto.CprAnalysisDto;

/**
 * Store the information which is need in the page
 * 
 * @author wuhao1
 * @author libaoshan
 * 
 */
public class CprAnalysisBean extends CprAnalysisDto {

    /** Competitor name */
    private String competitorName;

    /** Analysis competitor area name */
    private String areaName;

    /**
     * @return the competitorName
     */
    public String getCompetitorName() {
        return competitorName;
    }

    /**
     * @param competitorName
     *            the competitorName to set
     */
    public void setCompetitorName(String competitorName) {
        this.competitorName = competitorName;
    }

    /**
     * @return the areaName
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * @param areaName
     *            the areaName to set
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

}
