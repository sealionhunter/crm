/*
 * Class name: CompetitorSearchBean
 * 
 * Version information: 1.0
 * 
 * Date:2012.9.27
 *  
 */
package com.ustcsoft.gs.crm.webui.customer.bean;

import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;

/**
 * @author weijinmei CompetitorSearchBean
 */
public class CompetitorSearchBean {
    private String competitorSearchText;

    private String competitorName;

    private String competitorField;

    private String[] competitorProperty;

    private String competitorLocation;

    private String competitorFoundDateStart;

    private String competitorFoundDateEnd;

    private String[] competitorMoney;

    private String[] competitorPeople;

    private String competitorType;

    /**
     * @return competitorSearchText
     */
    public String getCompetitorSearchText() {
        return competitorSearchText;
    }

    /**
     * @param competitorSearchText
     *            the competitorSearchText to set
     */
    public void setCompetitorSearchText(String competitorSearchText) {
        this.competitorSearchText = CRMUtils.trimSearch(competitorSearchText);
    }

    /**
     * @return competitorName
     */
    public String getCompetitorName() {
        return competitorName;
    }

    /**
     * @param competitorName
     *            the competitorName to set
     */
    public void setCompetitorName(String competitorName) {
        this.competitorName = CRMUtils.trimSearch(competitorName);
    }

    /**
     * @return competitorField
     */
    public String getCompetitorField() {
        return competitorField;
    }

    /**
     * @param competitorField
     *            the competitorField to set
     */
    public void setCompetitorField(String competitorField) {
        this.competitorField = CRMUtils.trimSearch(competitorField);
    }

    /**
     * @return competitorLocation
     */
    public String getCompetitorLocation() {
        return competitorLocation;
    }

    /**
     * @param competitorLocation
     *            the competitorLocation to set
     */
    public void setCompetitorLocation(String competitorLocation) {
        this.competitorLocation = CRMUtils.trimSearch(competitorLocation);
    }

    /**
     * @return competitorFoundDateStart
     */
    public String getCompetitorFoundDateStart() {
        return competitorFoundDateStart;
    }

    /**
     * @param competitorFoundDateStart
     *            the competitorFoundDateStart to set
     */
    public void setCompetitorFoundDateStart(String competitorFoundDateStart) {
        this.competitorFoundDateStart = competitorFoundDateStart.trim();
    }

    /**
     * @return competitorFoundDateEnd
     */
    public String getCompetitorFoundDateEnd() {
        return competitorFoundDateEnd;
    }

    /**
     * @param competitorFoundDateEnd
     *            the competitorFoundDateEnds to set
     */
    public void setCompetitorFoundDateEnd(String competitorFoundDateEnd) {
        this.competitorFoundDateEnd = competitorFoundDateEnd.trim();
    }

    public String[] getCompetitorProperty() {
        return competitorProperty;
    }

    public void setCompetitorProperty(String[] competitorProperty) {
        this.competitorProperty = competitorProperty;
    }

    public String[] getCompetitorMoney() {
        return competitorMoney;
    }

    public void setCompetitorMoney(String[] competitorMoney) {
        this.competitorMoney = competitorMoney;
    }

    public String[] getCompetitorPeople() {
        return competitorPeople;
    }

    public void setCompetitorPeople(String[] competitorPeople) {
        this.competitorPeople = competitorPeople;
    }

    /**
     * @return competitorType
     */
    public String getCompetitorType() {
        return competitorType;
    }

    /**
     * @param competitorType
     *            the competitorType to set
     */
    public void setCompetitorType(String competitorType) {
        this.competitorType = competitorType.trim();
    }

}
