package com.ustcsoft.gs.crm.webui.customer.bean;

import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;

/**
 * contractSearchBean for contract search
 * 
 * @author zhangqiuli
 */
public class ProposalOrContractSearchBean {

    private String textValue;
    private String proposalOrContractName;
    private String proposalOrContractType;
    private String proposalOrContractObject;
    private String proposalOrContractAddDateMin;
    private String proposalOrContractAddDateMax;
    private String proposalOrContractEditDateMin;
    private String proposalOrContractEditDateMax;
    private int type;

    /**
     * method for get textValue
     * 
     * @return textValue
     */
    public String getTextValue() {
        return textValue;
    }

    /**
     * @return proposalOrContractName
     */
    public String getProposalOrContractName() {
        return proposalOrContractName;
    }

    /**
     * method for get proposalOrContractType
     * 
     * @return proposalOrContractType
     */
    public String getProposalOrContractType() {
        return proposalOrContractType;
    }

    /**
     * method for get proposalOrContractObject
     * 
     * @return proposalOrContractObject
     */
    public String getProposalOrContractObject() {
        return proposalOrContractObject;
    }

    /**
     * textValue's set method
     * 
     * @param textValue
     */
    public void setTextValue(final String textValue) {
        this.textValue = CRMUtils.trimSearch(textValue);
    }

    /**
     * proposalOrContractName' set method
     * 
     * @param proposalOrContractName
     */
    public void setProposalOrContractName(final String proposalOrContractName) {
        this.proposalOrContractName = CRMUtils.trimSearch(proposalOrContractName);
    }

    /**
     * proposalOrContractType' set method
     * 
     * @param proposalOrContractType
     */
    public void setProposalOrContractType(final String proposalOrContractType) {
        this.proposalOrContractType = CRMUtils.trimSearch(proposalOrContractType);
    }

    /**
     * proposalOrContractObject' set method
     * 
     * @param proposalOrContractObject
     */
    public void setProposalOrContractObject(final String proposalOrContractObject) {
        this.proposalOrContractObject = CRMUtils.trimSearch(proposalOrContractObject);
    }

    /**
     * get method for proposalOrContractAddDateMin
     * 
     * @return proposalOrContractAddDateMin
     */
    public String getProposalOrContractAddDateMin() {
        return proposalOrContractAddDateMin;
    }

    /**
     * get method for proposalOrContractAddDateMax
     * 
     * @return proposalOrContractAddDateMax
     */
    public String getProposalOrContractAddDateMax() {
        return proposalOrContractAddDateMax;
    }

    /**
     * set method for proposalOrContractAddDateMin
     * 
     * @param contractAddDateMin
     *            proposalOrContractAddDateMin
     */
    public void setProposalOrContractAddDateMin(final String contractAddDateMin) {
        proposalOrContractAddDateMin = contractAddDateMin;
    }

    /**
     * set method for proposalOrContractAddDateMax
     * 
     * @param contractAddDateMax
     *            proposalOrContractAddDateMax
     */
    public void setProposalOrContractAddDateMax(final String contractAddDateMax) {
        proposalOrContractAddDateMax = contractAddDateMax;
    }

    /**
     * proposalOrContractEditDateMin's get method
     * 
     * @return proposalOrContractEditDateMin
     */
    public String getProposalOrContractEditDateMin() {
        return proposalOrContractEditDateMin;
    }

    /**
     * gset method for proposalOrContractEditDateMax
     * 
     * @return proposalOrContractEditDateMax
     */
    public String getProposalOrContractEditDateMax() {
        return proposalOrContractEditDateMax;
    }

    /**
     * set method for proposalOrContractAddDateMax
     * 
     * @param contractEditDateMin
     *            proposalOrContractEditDateMin
     */
    public void setProposalOrContractEditDateMin(final String contractEditDateMin) {
        proposalOrContractEditDateMin = contractEditDateMin;
    }

    /**
     * set method for proposalOrContractEditDateMax
     * 
     * @param contractEditDateMax
     *            proposalOrContractEditDateMax
     */
    public void setProposalOrContractEditDateMax(final String contractEditDateMax) {
        proposalOrContractEditDateMax = contractEditDateMax;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(final int type) {
        this.type = type;
    }
}
