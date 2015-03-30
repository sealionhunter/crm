/*
 * Class name: CompetitorinfoDao
 * 
 * Version information: 1.0
 * 
 * Date:2012.9.27
 *  
 */
package com.ustcsoft.gs.crm.webui.customer.dao;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.CompetitorSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.CompetitorinfoDto;

/**
 * Competitorinfo
 * 
 * @author weijinmei
 */
public interface CompetitorinfoDao {
    /**
     * get All Competitor
     * 
     * @param searchFlag
     * @param searchBean
     * @param currpage
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> getAllCompetitor(final int searchFlag,
            final CompetitorSearchBean searchBean, final int currpage, final int pageSize)
            throws CRMDBException;

    /**
     * get Size of Competitorinfo
     * 
     * @param competitorType
     * @return long
     */
    public long getCompetitorinfoSize(String competitorType);

    /**
     * update Competitorinfo
     * 
     * @param competitorinfoDto
     */
    public void updateCompetitorinfo(CompetitorinfoDto competitorinfoDto);

    /**
     * delete CompetitorinfoAndCprAnalysis
     * 
     * @param competitorInfoIds
     */
    public void deleteCompetitorinfoAndCprAnalysis(String competitorInfoIds);

    /**
     * judge if CompetitorName existed
     * 
     * @param competitorName
     * @return type of boolean
     */
    public boolean judgeCompetitorName(String competitorName);

    /**
     * get CompetitorName By ID
     * 
     * @param competitorID
     * @return type of String
     */
    public String getCompetitorNameByComID(int competitorID);

}
