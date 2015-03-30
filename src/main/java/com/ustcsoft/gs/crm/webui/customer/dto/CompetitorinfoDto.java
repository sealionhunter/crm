/*
 * Class name: CompetitorinfoDto
 * 
 * Version information: 1.0
 * 
 * Date:2012.9.27
 *  
 */
package com.ustcsoft.gs.crm.webui.customer.dto;

/**
 * CompetitorinfoDto
 * 
 * @author weijinmei
 */
public class CompetitorinfoDto {
    private int competitorInfoId;

    private String competitorName;

    private String competitorField;

    private String competitorProperty;

    private String competitorLocation;

    private String competitorFoundDate;

    private String competitorMoney;

    private String competitorPeople;

    private String competitorDescription;

    private String competitorType;

    private String competitorDetail;

    private Boolean isAbolished = false;

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
        this.competitorName = competitorName.trim();
    }

    /**
     * @return the competitorField
     */
    public String getCompetitorField() {
        return competitorField;
    }

    /**
     * @param competitorField
     *            the competitorField to set
     */
    public void setCompetitorField(String competitorField) {
        this.competitorField = competitorField.trim();
    }

    /**
     * @return the competitorProperty
     */
    public String getCompetitorProperty() {
        return competitorProperty;
    }

    /**
     * @param competitorProperty
     *            the competitorProperty to set
     */
    public void setCompetitorProperty(String competitorProperty) {
        this.competitorProperty = competitorProperty.trim();
    }

    /**
     * @return the competitorLocation
     */
    public String getCompetitorLocation() {
        return competitorLocation;
    }

    /**
     * @param competitorLocation
     *            the competitorLocation to set
     */
    public void setCompetitorLocation(String competitorLocation) {
        this.competitorLocation = competitorLocation.trim();
    }

    /**
     * @return the competitorFoundDate
     */
    public String getCompetitorFoundDate() {
        return competitorFoundDate;
    }

    /**
     * @param competitorFoundDate
     *            the competitorFoundDate to set
     */
    public void setCompetitorFoundDate(String competitorFoundDate) {
        // this.competitorFoundDate = competitorFoundDate.trim();
        if (competitorFoundDate == null) {
            this.competitorFoundDate = competitorFoundDate;
        } else if (competitorFoundDate.trim().isEmpty()) {
            this.competitorFoundDate = null;
        } else {
            this.competitorFoundDate = competitorFoundDate.trim();
        }
    }

    /**
     * @return the competitorMoney
     */
    public String getCompetitorMoney() {
        return competitorMoney;
    }

    /**
     * @param competitorMoney
     *            the competitorMoney to set
     */
    public void setCompetitorMoney(String competitorMoney) {
        this.competitorMoney = competitorMoney.trim();
    }

    /**
     * @return the competitorPeople
     */
    public String getCompetitorPeople() {
        return competitorPeople;
    }

    /**
     * @param competitorPeople
     *            the competitorPeople to set
     */
    public void setCompetitorPeople(String competitorPeople) {
        this.competitorPeople = competitorPeople.trim();
    }

    /**
     * @return the competitorDescription
     */
    public String getCompetitorDescription() {
        return competitorDescription;
    }

    /**
     * @param competitorDescription
     *            the competitorDescription to set
     */
    public void setCompetitorDescription(String competitorDescription) {
        this.competitorDescription = competitorDescription.trim();
    }

    /**
     * @return the competitorType
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

    /**
     * @return the isAbolished
     */
    public Boolean getIsAbolished() {
        return isAbolished;
    }

    /**
     * @param isAbolished
     *            the isAbolished to set
     */
    public void setIsAbolished(Boolean isAbolished) {
        this.isAbolished = isAbolished;
    }

    /**
     * @return the competitorDetail
     */
    public String getCompetitorDetail() {
        return competitorDetail;
    }

    /**
     * @param competitorDetail
     *            the competitorDetail to set
     */
    public void setCompetitorDetail(String competitorDetail) {
        this.competitorDetail = competitorDetail.trim();
    }

}
