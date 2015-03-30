/*
 * Interface name: CoopResumeService
 * 
 * Version information: 1.0
 * 
 * Date:2012.9.11
 *  
 */
package com.ustcsoft.gs.crm.webui.customer.service;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.CoopResumeSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.CoopResumeDto;

/**
 * @author tangyunpeng
 */
public interface CoopResumeService {

    /**
     * The method is used to get CoopResumeList.
     * 
     * @param searchFlag
     *            int search mark
     * @param searchValue
     *            CoopResumeSearchBean search conditions
     * @param currPage
     *            int search page
     * @param customerID
     *            int customer mark
     * @return map Map query results to display
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    public Map<String, Object> searchOrGetAllCoopResume(int searchFlag,
            CoopResumeSearchBean searchValue, int currPage, int customerID, int pageSize)
            throws CRMDBException;

    /**
     * The method is used to add or update record.
     * 
     * @param coopResumeDto
     *            CoopResumeDto which will be added or edited
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    public void addOrUpdateCoopResume(CoopResumeDto coopResumeDto) throws CRMDBException;

    /**
     * The method is used to delete records.
     * 
     * @param coopResumeID
     *            sourceID list which will be deleted
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    public void deleteCoopResume(String coopResumeID) throws CRMDBException;

    /**
     * Judge customerName
     * 
     * @param coopResumeDto
     *            CoopResumeDto
     * @return true if customerName is not
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    public boolean judgeProjectName(CoopResumeDto coopResumeDto) throws CRMDBException;
}
