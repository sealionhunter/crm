package com.ustcsoft.gs.crm.webui.customer.bean;

/**
 * Store the competitorInfoId and competitorName which are get from the table
 * CompetitorInfo
 * 
 * @author wuhao1
 * @author libaoshan
 * 
 */
public class CprNameChooseBean {

    /** Competitor Id */
    private int competitorInfoId;

    /** Competitor name */
    private String competitorName;

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

}
