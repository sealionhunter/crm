/*
 * Class name: CompetitorinfoService
 * 
 * Version information: 1.0
 * 
 * Date:2012.9.27
 *  
 */
package com.ustcsoft.gs.crm.webui.customer.service;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.CompetitorSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dao.CompetitorinfoDao;
import com.ustcsoft.gs.crm.webui.customer.dto.CompetitorinfoDto;

/**
 * @author weijinmei Competitorinfo
 */
public interface CompetitorinfoService {
    /**
     * @return competitorinfoDao
     */
    public CompetitorinfoDao getCompetitorinfoDao();

    /**
     * @param competitorinfoDao
     *            the competitorinfoDao to set
     */
    public void setCompetitorinfoDao(CompetitorinfoDao competitorinfoDao);

    /**
     * get All Competitor
     * 
     * @param searchFlag
     * @param searchBean
     * @param currpage
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> getAllCompetitor(int searchFlag, CompetitorSearchBean searchBean,
            int currpage, int pageSize) throws CRMDBException;

    /**
     * update Competitorinfo
     * 
     * @param competitorinfoDto
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void updateCompetitorinfo(CompetitorinfoDto competitorinfoDto) throws CRMDBException;

    /**
     * delete CompetitorinfoAndCprAnalysisInfo
     * 
     * @param competitorInfoIds
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void deleteCompetitorinfoAndCprAnalysis(String competitorInfoIds) throws CRMDBException;

    /**
     * judge if CompetitorName existed
     * 
     * @param competitorinfoDto
     * @return type of boolean
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */

    public boolean judgeCompetitorName(CompetitorinfoDto competitorinfoDto) throws CRMDBException;
}
