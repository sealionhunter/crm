/*
 * Class name: CopAnalysisService
 * 
 * Version information: 1.0
 * 
 * Date:2012.9.11
 *  
 */
package com.ustcsoft.gs.crm.webui.customer.service;

import java.util.List;
import java.util.Map;

import com.ustcsoft.gs.crm.webui.code.dto.CodeDto;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.dto.CopAnalysisDto;

/**
 * cooperator analysis service interface.
 * 
 * @author xujueyin
 * 
 */
public interface CopAnalysisService {

    /**
     * get all cooperator analysis information and show in page.
     * 
     * @param currpage
     *            int current page
     * @param limit
     *            int pageSize
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * @return Map<String,Object> the results of cooperator analysis
     */
    public Map<String, Object> getAllCopAnalysis(int currpage, int limit) throws CRMDBException;

    /**
     * add or update cooperator analysis.
     * 
     * @param copAnalysis
     *            CopAnalysisDto add or update dto
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void saveOrUpdateCopAnalysis(CopAnalysisDto copAnalysis) throws CRMDBException;

    /**
     * delete cooperator analysis by cooperator IDs.
     * 
     * @param copAnalysisIDs
     *            String deleted cooperator ids
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void deleteCopAnalysis(String copAnalysisIDs) throws CRMDBException;

    /**
     * get cooperator analysis information by assigned ID and show in page.
     * 
     * @param cooperatorID
     *            int cooperator ID to retrieve
     * @param currpage
     *            int current page
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * @return Map<String, Object> the assigned ID's results of cooperator
     *         analysis
     */
    public Map<String, Object> getCopAnalysisByID(int cooperatorID, int currpage)
            throws CRMDBException;

    /**
     * get cooperator name.
     * 
     * @return List<CodeDto>
     * @throws CRMDBException
     */
    public List<CodeDto> getCopName() throws CRMDBException;
}