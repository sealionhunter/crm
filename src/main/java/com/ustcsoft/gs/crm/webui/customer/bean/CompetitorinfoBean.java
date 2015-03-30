/*
 * Class name: CompetitorinfoBean
 * 
 * Version information: 1.0
 * 
 * Date:2012.9.27
 *  
 */
package com.ustcsoft.gs.crm.webui.customer.bean;

import com.ustcsoft.gs.crm.webui.customer.dto.CompetitorinfoDto;

/**
 * @author weijinmei CompetitorinfoBean
 */
public class CompetitorinfoBean extends CompetitorinfoDto {
    private String competitorPropertyB;

    private String competitorMoneyB;

    private String competitorPeopleB;

    private String competitorTypeB;

    public String getCompetitorTypeB() {
        return competitorTypeB;
    }

    public void setCompetitorTypeB(String competitorTypeB) {
        this.competitorTypeB = competitorTypeB;
    }

    /**
     * @return competitorPropertyB
     */
    public String getCompetitorPropertyB() {
        return competitorPropertyB;
    }

    /**
     * @param competitorPropertyB
     *            the competitorPropertyB to set
     */
    public void setCompetitorPropertyB(String competitorPropertyB) {
        this.competitorPropertyB = competitorPropertyB;
    }

    /**
     * @return competitorMoneyB
     */
    public String getCompetitorMoneyB() {
        return competitorMoneyB;
    }

    /**
     * @param competitorMoneyB
     *            the competitorMoneyB to set
     */
    public void setCompetitorMoneyB(String competitorMoneyB) {
        this.competitorMoneyB = competitorMoneyB;
    }

    /**
     * @return competitorPeopleB
     */
    public String getCompetitorPeopleB() {
        return competitorPeopleB;
    }

    /**
     * @param competitorPeopleB
     *            the competitorPeopleB to set
     */
    public void setCompetitorPeopleB(String competitorPeopleB) {
        this.competitorPeopleB = competitorPeopleB;
    }

}
