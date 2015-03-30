package com.ustcsoft.gs.crm.webui.customer.bean;

import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;

public class CooperationProjectSearchBean {

    private int cooperatorIDSearch;
    private String searchText;
    private String projectNameSearch;
    private String projectTypeSearch;
    private String principalCooperatorSearch;
    private String principalWeSearch;
    private Integer projectScaleMin;
    private Integer projectScaleMax;
    private String realBeginTimeMin;
    private String realBeginTimeMax;

    /**
     * @return the cooperatorIDSearch
     */
    public int getCooperatorIDSearch() {
        return cooperatorIDSearch;
    }

    /**
     * @param cooperatorIDSearch
     *            the cooperatorIDSearch to set
     */
    public void setCooperatorIDSearch(int cooperatorIDSearch) {
        this.cooperatorIDSearch = cooperatorIDSearch;
    }

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
     * @return the projectNameSearch
     */
    public String getProjectNameSearch() {
        return projectNameSearch;
    }

    /**
     * @param projectNameSearch
     *            the projectNameSearch to set
     */
    public void setProjectNameSearch(String projectNameSearch) {

        this.projectNameSearch = CRMUtils.trimSearch(projectNameSearch);

    }

    /**
     * @return the projectTypeSearch
     */
    public String getProjectTypeSearch() {
        return projectTypeSearch;
    }

    /**
     * @param projectTypeSearch
     *            the projectTypeSearch to set
     */
    public void setProjectTypeSearch(String projectTypeSearch) {
        this.projectTypeSearch = projectTypeSearch;
    }

    /**
     * @return the principalCooperatorSearch
     */
    public String getPrincipalCooperatorSearch() {
        return principalCooperatorSearch;
    }

    /**
     * @param principalCooperatorSearch
     *            the principalCooperatorSearch to set
     */
    public void setPrincipalCooperatorSearch(String principalCooperatorSearch) {
        this.principalCooperatorSearch = CRMUtils.trimSearch(principalCooperatorSearch);
    }

    /**
     * @return the principalWeSearch
     */
    public String getPrincipalWeSearch() {
        return principalWeSearch;
    }

    /**
     * @param principalWeSearch
     *            the principalWeSearch to set
     */
    public void setPrincipalWeSearch(String principalWeSearch) {
        this.principalWeSearch = CRMUtils.trimSearch(principalWeSearch);

    }

    /**
     * @return the projectScaleMin
     */
    public Integer getProjectScaleMin() {
        return projectScaleMin;
    }

    /**
     * @param projectScaleMin
     *            the projectScaleMin to set
     */
    public void setProjectScaleMin(Integer projectScaleMin) {
        this.projectScaleMin = projectScaleMin;
    }

    /**
     * @return the projectScaleMax
     */
    public Integer getProjectScaleMax() {
        return projectScaleMax;
    }

    /**
     * @param projectScaleMax
     *            the projectScaleMax to set
     */
    public void setProjectScaleMax(Integer projectScaleMax) {
        this.projectScaleMax = projectScaleMax;
    }

    /**
     * @return the realBeginTimeMin
     */
    public String getRealBeginTimeMin() {
        return realBeginTimeMin;
    }

    /**
     * @param realBeginTimeMin
     *            the realBeginTimeMin to set
     */
    public void setRealBeginTimeMin(String realBeginTimeMin) {
        this.realBeginTimeMin = realBeginTimeMin;
    }

    /**
     * @return the realBeginTimeMax
     */
    public String getRealBeginTimeMax() {
        return realBeginTimeMax;
    }

    /**
     * @param realBeginTimeMax
     *            the realBeginTimeMax to set
     */
    public void setRealBeginTimeMax(String realBeginTimeMax) {
        this.realBeginTimeMax = realBeginTimeMax;
    }

}
