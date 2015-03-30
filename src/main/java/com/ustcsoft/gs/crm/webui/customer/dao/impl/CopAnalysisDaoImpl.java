/*
 * Class name: CopAnalysisDaoImpl
 * 
 * Version information: 1.0
 * 
 * Date:2012.10.8
 *  
 */
package com.ustcsoft.gs.crm.webui.customer.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.code.dto.CodeDto;
import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.util.PagingHibernateCallback;
import com.ustcsoft.gs.crm.webui.customer.bean.CopAnalysisBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.customer.dao.CopAnalysisDao;
import com.ustcsoft.gs.crm.webui.customer.dto.CopAnalysisDto;

/**
 * the implement of cooperator analysis dao interface.
 * 
 * @author xujueyin
 * 
 */
public class CopAnalysisDaoImpl implements CopAnalysisDao {

    /** get Log */
    private static final Log LOG = LogFactory.getLog(CopAnalysisDaoImpl.class);

    /** operate database */
    private HibernateTemplate hibernateTemplate;

    /**
     * @return the hibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    /**
     * @param hibernateTemplate
     *            the hibernateTemplate to set
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /**
     * get all cooperator analysis information and show in page.
     * 
     * @param currpage
     *            int current page
     * @param limit
     *            int pageSize
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * @return Map<String, Object> the results of cooperator analysis
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getAllCopAnalysis(int currpage, int limit)
            throws DataAccessException {
        LOG.debug("method getAllCopAnalysis start!");
        Map<String, Object> map = new HashMap<String, Object>();
        // get cooperator analysis records
        List<CopAnalysisBean> copAnalysisList = hibernateTemplate
                .executeFind(new PagingHibernateCallback(
                        CustomerConstant.COPANALYSIS_LIST_BEFORE_HQL, currpage, limit));
        // get cooperator analysis records number
        long total = (Long) hibernateTemplate.find(CustomerConstant.COPANALYSIS_COUNT_BEFORE_HQL)
                .get(0);
        map.put(CRMConstant.ITEMS, copAnalysisList);
        map.put(CRMConstant.TOTAL, total);
        LOG.debug("method getAllCopAnalysis end!");
        return map;
    }

    /**
     * add or update cooperator analysis.
     * 
     * @param copAnalysis
     *            CopAnalysisDto add or update dto
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public void saveOrUpdateCopAnalysis(CopAnalysisDto copAnalysis) throws DataAccessException {
        LOG.debug("method saveOrUpdateCopAnalysis start!");
        hibernateTemplate.saveOrUpdate(copAnalysis);
        LOG.debug("method saveOrUpdateCopAnalysis end!");
    }

    /**
     * delete cooperator analysis by cooperator IDs.
     * 
     * @param copAnalysisIDs
     *            String deleted cooperator analysis ids
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public void deleteCopAnalysis(String copAnalysisIDs) throws DataAccessException {
        LOG.debug("method deleteCopAnalysis start!");
        hibernateTemplate.bulkUpdate(CustomerConstant.BATCH_REMOVE_COPANALY_HQL + copAnalysisIDs);
        LOG.debug("method deleteCopAnalysis end!");
    }

    /**
     * get cooperator analysis information by assigned ID and show in page.
     * 
     * @param cooperatorID
     *            int cooperator ID to retrieve
     * @param currPage
     *            int current page
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * @return Map<String, Object> the assigned ID's results of cooperator
     *         analysis
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getCopAnalysisByID(int cooperatorID, int currPage)
            throws DataAccessException {
        LOG.debug("method getCopAnalysisByID start!");
        Map<String, Object> map = new HashMap<String, Object>();
        // generate cooperator analysis hql
        String hqlList = CustomerConstant.COPANALYSIS_LIST_BEFORE_HQL
                + CustomerConstant.COPANALYSIS_SHOW_AFTER_ORDER_HQL;
        String hqlCount = CustomerConstant.COPANALYSIS_COUNT_BEFORE_HQL
                + CustomerConstant.COPANALYSIS_SHOW_AFTER_HQL;

        // get cooperator analysis records by ids
        List<CopAnalysisDto> result = hibernateTemplate.executeFind(new PagingHibernateCallback(
                hqlList, currPage, CRMConstant.ID, cooperatorID, 15));
        // get cooperator analysis records number by ids
        long total = (Long) hibernateTemplate.findByNamedParam(hqlCount, CRMConstant.ID,
                cooperatorID).get(0);
        map.put(CRMConstant.ITEMS, result);
        map.put(CRMConstant.TOTAL, total);
        LOG.debug("method getCopAnalysisByID end!");
        return map;
    }

    /**
     * get cooperator name.
     * 
     * @return List<CodeDto>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<CodeDto> getCopName() {
        LOG.debug("method getCopName() start!");
        List<CodeDto> list = hibernateTemplate.find("select new Map(cop.cooperatorID as code"
                + ",cop.cooperatorName as value) from CooperatorDto "
                + "as cop where cop.isAbolished = 0 order by cop.cooperatorName");
        LOG.debug("method getCopName() end!");
        return list;
    }
}