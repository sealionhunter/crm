/*
 * Copyright 2012 the original author or authors.
 */
package com.ustcsoft.gs.crm.webui.customer.dto;

/**
 * @author wuhao1
 * @author libaoshan
 * @since August 2012
 */
public class CprAnalysisDto {

    /** Analysis ID */
    private int cprAnalysisID;

    /** Competitor ID */
    private int competitorInfoId;

    /** Analysis area */
    private String area;

    /** Competitor ability */
    private String ability;

    /** Competitor targets */
    private String targets;

    /** Competitor strategy */
    private String strategy;

    /** Competitor prediction */
    private String prediction;

    /** Competitor advantage */
    private String advantage;

    /** Competitor disadvantage */
    private String disadvantage;

    /** Competitor advAnalysis */
    private String advAnalysis;

    /** Competitor disadvAnalysis */
    private String disadvAnalysis;

    /** Competitor other information */
    private String others;

    /** Competitor compositeComp */
    private int compositeComp;

    /** Competitor compositeDefense */
    private int compositeDefense;

    /** Analysis Time */
    private String cprAnalysisTime;

    /** Analysis advice */
    private String advice;

    /** Analysis is deleted or not */
    private String isAbolished = "False";

    /**
     * @return the isAbolished
     */
    public String getIsAbolished() {
        return isAbolished;
    }

    /**
     * @param isAbolished
     *            the isAbolished to set
     */
    public void setIsAbolished(String isAbolished) {
        this.isAbolished = isAbolished;
    }

    /**
     * @return the cprAnalysisID
     */
    public int getCprAnalysisID() {
        return cprAnalysisID;
    }

    /**
     * @param cprAnalysisID
     *            the cprAnalysisID to set
     */
    public void setCprAnalysisID(int cprAnalysisID) {
        this.cprAnalysisID = cprAnalysisID;
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

    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area
     *            the area to set
     */
    public void setArea(String area) {
        this.area = area.trim();
    }

    /**
     * @return the ability
     */
    public String getAbility() {
        return ability;
    }

    /**
     * @param ability
     *            the ability to set
     */
    public void setAbility(String ability) {
        this.ability = ability.trim();
    }

    /**
     * @return the targets
     */
    public String getTargets() {
        return targets;
    }

    /**
     * @param targets
     *            the targets to set
     */
    public void setTargets(String targets) {
        this.targets = targets.trim();
    }

    /**
     * @return the strategy
     */
    public String getStrategy() {
        return strategy;
    }

    /**
     * @param strategy
     *            the strategy to set
     */
    public void setStrategy(String strategy) {
        this.strategy = strategy.trim();
    }

    /**
     * @return the prediction
     */
    public String getPrediction() {
        return prediction;
    }

    /**
     * @param prediction
     *            the prediction to set
     */
    public void setPrediction(String prediction) {
        this.prediction = prediction.trim();
    }

    /**
     * @return the advantage
     */
    public String getAdvantage() {
        return advantage;
    }

    /**
     * @param advantage
     *            the advantage to set
     */
    public void setAdvantage(String advantage) {
        this.advantage = advantage.trim();
    }

    /**
     * @return the disadvantage
     */
    public String getDisadvantage() {
        return disadvantage;
    }

    /**
     * @param disadvantage
     *            the disadvantage to set
     */
    public void setDisadvantage(String disadvantage) {
        this.disadvantage = disadvantage.trim();
    }

    /**
     * @return the advAnalysis
     */
    public String getAdvAnalysis() {
        return advAnalysis;
    }

    /**
     * @param advAnalysis
     *            the advAnalysis to set
     */
    public void setAdvAnalysis(String advAnalysis) {
        this.advAnalysis = advAnalysis.trim();
    }

    /**
     * @return the disadvAnalysis
     */
    public String getDisadvAnalysis() {
        return disadvAnalysis;
    }

    /**
     * @param disadvAnalysis
     *            the disadvAnalysis to set
     */
    public void setDisadvAnalysis(String disadvAnalysis) {
        this.disadvAnalysis = disadvAnalysis.trim();
    }

    /**
     * @return the others
     */
    public String getOthers() {
        return others;
    }

    /**
     * @param others
     *            the others to set
     */
    public void setOthers(String others) {
        this.others = others.trim();
    }

    /**
     * @return the compositeComp
     */
    public int getCompositeComp() {
        return compositeComp;
    }

    /**
     * @param compositeComp
     *            the compositeComp to set
     */
    public void setCompositeComp(int compositeComp) {
        this.compositeComp = compositeComp;
    }

    /**
     * @return the compositeDefense
     */
    public int getCompositeDefense() {
        return compositeDefense;
    }

    /**
     * @param compositeDefense
     *            the compositeDefense to set
     */
    public void setCompositeDefense(int compositeDefense) {
        this.compositeDefense = compositeDefense;
    }

    /**
     * @return the advice
     */
    public String getAdvice() {
        return advice;
    }

    /**
     * @param advice
     *            the advice to set
     */
    public void setAdvice(String advice) {
        this.advice = advice.trim();
    }

    /**
     * @return the cprAnalysisTime
     */
    public String getCprAnalysisTime() {
        return cprAnalysisTime;
    }

    /**
     * @param cprAnalysisTime
     *            the cprAnalysisTime to set
     */
    public void setCprAnalysisTime(String cprAnalysisTime) {
        this.cprAnalysisTime = cprAnalysisTime;
    }
}
