/*
 * Copyright 2012 the original author or authors.
 */
package com.ustcsoft.gs.crm.webui.customer.service;

import java.util.List;
import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.CprNameChooseBean;
import com.ustcsoft.gs.crm.webui.customer.dto.CprAnalysisDto;

/**
 * Description: The interface is used for add, edit and delete information from
 * table.
 * 
 * @author libaoshan
 * @since August 2012
 */
public interface CprAnalysisService {

    /**
     * Get the information for the current page.
     * 
     * @param page
     *            store the current page of CprAnalysisList.js
     * @param competitorInfoId
     *            if it is zero, get all analysis. else get anaslysis by
     *            competitorInfoId
     * @param pageSize
     *            store the page size
     * 
     * @return Map<String, Object> map store the analysis information
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> getAnalysis(int page, int competitorInfoId, int pageSize)
            throws CRMDBException;

    /**
     * Description: Add and update info.
     * 
     * @param cprAnalysisInfo
     *            CprAnalysisDto which is added or updated
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void updateCprAnalysis(CprAnalysisDto cprAnalysisInfo) throws CRMDBException;

    /**
     * Description: Get all the cprName.
     * 
     * @return cprNameList store the analysis name
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public List<CprNameChooseBean> getAllCprName() throws CRMDBException;

    /**
     * Description: Delete cprAnalysis.
     * 
     * @param cprAnalysisIDs
     *            store the deleted IDs
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void deleteCprAnalysis(String cprAnalysisIDs) throws CRMDBException;
}
