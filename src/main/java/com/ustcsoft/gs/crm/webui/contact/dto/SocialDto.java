/**
 * 
 */
package com.ustcsoft.gs.crm.webui.contact.dto;

import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;

/**
 * SocialInfo Dto
 * 
 * @author shenkaichuan
 * 
 */
public class SocialDto extends People {
    /** social ID */
    private int socialID = 0;

    /** social name */
    private String socialName;

    /** social relation */
    private String socialRelation;

    /**
     * @return the socialID
     */
    public int getSocialID() {
        return socialID;
    }

    /**
     * @param socialID
     *            the socialID to set
     */
    public void setSocialID(int socialID) {
        this.socialID = socialID;
    }

    /**
     * @return the socialName
     */
    public String getSocialName() {
        return socialName;
    }

    /**
     * @param socialName
     *            the socialName to set
     */
    public void setSocialName(String socialName) {
        this.socialName = CRMUtils.trim(socialName);
    }

    /**
     * @return the socialRelation
     */
    public String getSocialRelation() {
        return socialRelation;
    }

    /**
     * @param socialRelation
     *            the socialRelation to set
     */
    public void setSocialRelation(String socialRelation) {
        this.socialRelation = CRMUtils.trim(socialRelation);
    }

}
