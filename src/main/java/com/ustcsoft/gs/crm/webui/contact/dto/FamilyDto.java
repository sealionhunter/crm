/*
 * 
 */
package com.ustcsoft.gs.crm.webui.contact.dto;

import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;

/**
 * FamilyInfo Dto
 * 
 * @author shenkaichuan
 * 
 */
public class FamilyDto extends People {
    /** family ID */
    private int familyID = 0;

    /** family name */
    private String familyName;

    /** family relation */
    private String familyRelation;

    /**
     * @return the familyID
     */
    public int getFamilyID() {
        return familyID;
    }

    /**
     * @param familyID
     *            the familyID to set
     */
    public void setFamilyID(int familyID) {
        this.familyID = familyID;
    }

    /**
     * @return the familyName
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * @param familyName
     *            the familyName to set
     */
    public void setFamilyName(String familyName) {
        this.familyName = CRMUtils.trim(familyName);
    }

    /**
     * @return the familyRelation
     */
    public String getFamilyRelation() {
        return familyRelation;
    }

    /**
     * @param familyRelation
     *            the familyRelation to set
     */
    public void setFamilyRelation(String familyRelation) {
        this.familyRelation = CRMUtils.trim(familyRelation);
    }

}
