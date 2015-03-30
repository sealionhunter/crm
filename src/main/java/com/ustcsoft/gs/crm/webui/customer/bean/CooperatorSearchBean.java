package com.ustcsoft.gs.crm.webui.customer.bean;

import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;

/**
 * cooperator search bean
 * 
 * @author xujialong
 * 
 */
public class CooperatorSearchBean {
    private String searchText;
    private String cooperatorIndustrySearch;
    private String cooperatorScaleSearch;
    private Long cooperationTimesMin;
    private Long cooperationTimesMax;
    private String cooperationDateMin;
    private String cooperationDateMax;
    private String cooperatorNameSearch;

    /**
     * @return the searchText
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * @param searchText
     *            the searchText to set
     */
    public void setSearchText(String searchText) {
        this.searchText = CRMUtils.trimSearch(searchText);
    }

    /**
     * @return the cooperatorIndustrySearch
     */
    public String getCooperatorIndustrySearch() {
        return cooperatorIndustrySearch;
    }

    /**
     * @param cooperatorIndustrySearch
     *            the cooperatorIndustrySearch to set
     */
    public void setCooperatorIndustrySearch(String cooperatorIndustrySearch) {
        this.cooperatorIndustrySearch = cooperatorIndustrySearch;
    }

    /**
     * @return the cooperatorScaleSearch
     */
    public String getCooperatorScaleSearch() {
        return cooperatorScaleSearch;
    }

    /**
     * @param cooperatorScaleSearch
     *            the cooperatorScaleSearch to set
     */
    public void setCooperatorScaleSearch(String cooperatorScaleSearch) {
        this.cooperatorScaleSearch = cooperatorScaleSearch;
    }

    /**
     * @return the cooperationTimesMin
     */
    public Long getCooperationTimesMin() {
        return cooperationTimesMin;
    }

    /**
     * @param cooperationTimesMin
     *            the cooperationTimesMin to set
     */
    public void setCooperationTimesMin(Long cooperationTimesMin) {
        this.cooperationTimesMin = cooperationTimesMin;
    }

    /**
     * @return the cooperationTimesMax
     */
    public Long getCooperationTimesMax() {
        return cooperationTimesMax;
    }

    /**
     * @param cooperationTimesMax
     *            the cooperationTimesMax to set
     */
    public void setCooperationTimesMax(Long cooperationTimesMax) {
        this.cooperationTimesMax = cooperationTimesMax;
    }

    /**
     * @return the cooperationDateMin
     */
    public String getCooperationDateMin() {
        return cooperationDateMin;
    }

    /**
     * @param cooperationDateMin
     *            the cooperationDateMin to set
     */
    public void setCooperationDateMin(String cooperationDateMin) {
        this.cooperationDateMin = cooperationDateMin;
    }

    /**
     * @return the cooperationDateMax
     */
    public String getCooperationDateMax() {
        return cooperationDateMax;
    }

    /**
     * @param cooperationDateMax
     *            the cooperationDateMax to set
     */
    public void setCooperationDateMax(String cooperationDateMax) {
        this.cooperationDateMax = cooperationDateMax;
    }

    /**
     * @return the cooperatorName
     */
    public String getCooperatorNameSearch() {
        return cooperatorNameSearch;
    }

    /**
     * @param cooperatorNameSearch
     *            the cooperatorName to set
     */
    public void setCooperatorNameSearch(String cooperatorNameSearch) {
        this.cooperatorNameSearch = CRMUtils.trimSearch(cooperatorNameSearch);
    }

}
