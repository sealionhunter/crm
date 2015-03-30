/*
 * Class name: CopAnalysisDao
 * 
 * Version information: 1.0
 * 
 * Date:2012.9.11
 *  
 */
package com.ustcsoft.gs.crm.webui.customer.dao;

import java.util.List;
import java.util.Map;

import com.ustcsoft.gs.crm.webui.code.dto.CodeDto;
import com.ustcsoft.gs.crm.webui.customer.dto.CopAnalysisDto;

/**
 * cooperator analysis dao interface.
 * 
 * @author xujueyin
 * 
 */
public interface CopAnalysisDao {

    /**
     * get all cooperator analysis information and show in page.
     * 
     * @param currpage
     *            int current page
     * @param limit
     *            int pageSize
     * @return Map<String, Object> the results of cooperator analysis
     */
    public Map<String, Object> getAllCopAnalysis(int currpage, int limit);

    /**
     * add or update cooperator analysis.
     * 
     * @param copAnalysis
     *            CopAnalysisDto add or update dto
     */
    public void saveOrUpdateCopAnalysis(CopAnalysisDto copAnalysis);

    /**
     * delete cooperator analysis by cooperator IDs.
     * 
     * @param copAnalysisIDs
     *            String deleted cooperator analysis ids
     */
    public void deleteCopAnalysis(String copAnalysisIDs);

    /**
     * get cooperator analysis information by assigned ID and show in page.
     * 
     * @param cooperatorID
     *            int cooperator ID to retrieve
     * @param currpage
     *            int current page
     * @return Map<String, Object> the assigned ID's results of cooperator
     *         analysis
     */
    public Map<String, Object> getCopAnalysisByID(int cooperatorID, int currpage);

    /**
     * get cooperator name
     * 
     * @return List<CodeDto>
     */
    public List<CodeDto> getCopName();
}