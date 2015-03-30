/*
 * Copyright 2012 the original author or authors.
 */
package com.ustcsoft.gs.crm.webui.customer.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.customer.bean.CprNameChooseBean;
import com.ustcsoft.gs.crm.webui.customer.dto.CprAnalysisDto;

/**
 * Description: The interface is working with DB{add, edit and delete}.
 * 
 * @author wuhao1
 * @author libaoshan
 */
public interface CprAnalysisDao {

    /**
     * Description: Get one page's information.
     * 
     * @param page
     *            store the current page of CprAnalysisList.js
     * @param competitorInfoId
     *            if it is zero, get all analysis. else get anaslysis by
     *            competitorInfoId
     * @param pageSize
     *            store the page size
     * 
     * @return map store the analysis information
     * 
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> getAnalysis(final int page, int competitorInfoId, int pageSize)
            throws DataAccessException;

    /**
     * Description: Update cprAnalysis.
     * 
     * @param cprAnalysisInfo
     *            CprAnalysisDto which is added or updated
     * 
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public void updateCprAnalysis(CprAnalysisDto cprAnalysisInfo) throws DataAccessException;

    /**
     * Description: Delete the info from the database.
     * 
     * @param cprAnalysisIDs
     *            store the deleted IDs
     * 
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public void deleteCprAnalysis(String cprAnalysisIDs) throws DataAccessException;

    /**
     * Description: Get all the info of cprAnalysis.
     * 
     * @param competitorInfoId
     *            int if is zero, get total of all analysis. else get total of
     *            analysis by competitorInfoId
     * 
     * @return (Long) list.get(0) long store the total
     * 
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public long getAnalysisTotal(int competitorInfoId) throws DataAccessException;

    /**
     * Description: Get all the cprId and cprName from table CompetitorInfo.
     * 
     * @return cprNameList List<CprNameChooseBean> the name of analysised
     * 
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public List<CprNameChooseBean> getAllCprName() throws DataAccessException;
}
