/*
 * Interface name: CoopResumeDao
 * 
 * Version information: 1.0
 * 
 * Date:2012.9.11
 *  
 */
package com.ustcsoft.gs.crm.webui.customer.dao;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.customer.bean.CoopResumeSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.CoopResumeDto;

/**
 * 
 * @author tangyunpeng
 */
public interface CoopResumeDao {

    /**
     * select all customer from DB return to page
     * 
     * @param currPage
     *            search page
     * @param customerID
     *            customer ID
     * @param pageSize
     *            size of page
     * @return map query results(coopResumeList, total) to display
     */
    public Map<String, Object> getCoopResume(final int currPage, final int customerID, int pageSize);

    /**
     * The method is used to add or update record.
     * 
     * @param coopResumeDto
     *            CoopResumeDto which will be added or edited
     */
    public void addOrUpdateCoopResume(CoopResumeDto coopResumeDto);

    /**
     * The method is used to query coopResume.
     * 
     * @param searchFlag
     *            search mark
     * @param searchValue
     *            search conditions
     * @param currPage
     *            search page
     * @return map query results(sourceList, total) to display
     */
    public Map<String, Object> queryCoopResume(int searchFlag,
            final CoopResumeSearchBean searchValue, final int currPage, int pageSize);

    /**
     * The method is used to delete record.
     * 
     * @param coopResumeID
     *            coopResumeID which will be deleted
     */
    public void deleteCoopResume(String coopResumeID);

    /**
     * The method is used to get the number of records.
     * 
     * @param queryString
     * @return type of long
     */
    public long getCoopResumeSize(String queryString, int customerID);

    /**
     * The method is used to get the number of all records.
     * 
     * @param coopResumeDto
     * 
     * @return count long
     */
    public boolean judgeProjectName(CoopResumeDto coopResumeDto);

}