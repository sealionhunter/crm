/*
 * Copyright 2012 the original author or authors.
 */
package com.ustcsoft.gs.crm.webui.customer.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.util.PagingHibernateCallback;
import com.ustcsoft.gs.crm.webui.customer.bean.CprAnalysisBean;
import com.ustcsoft.gs.crm.webui.customer.bean.CprNameChooseBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.customer.dao.CprAnalysisDao;
import com.ustcsoft.gs.crm.webui.customer.dto.CprAnalysisDto;

/**
 * Description: The class is working with DB{add, edit and delete}.
 * 
 * @author wuhao1
 * @author libaoshan
 */
public class CprAnalysisDaoImpl implements CprAnalysisDao {

    private static Log LOG = LogFactory.getLog(CprAnalysisDaoImpl.class);

    private HibernateTemplate hibernateTemplate;

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
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getAnalysis(final int page, int competitorInfoId, final int pageSize)
            throws DataAccessException {
        LOG.debug("method getAnalysis start!");
        Map<String, Object> map = new HashMap<String, Object>();
        long total = 0;
        List<CprAnalysisBean> cprAnalysisList = null;
        if (competitorInfoId == 0) {

            // store the total number in the database
            total = getAnalysisTotal(0);
            cprAnalysisList = hibernateTemplate.executeFind(new PagingHibernateCallback(
                    CustomerConstant.CPRANALYSIS_HQL, page, pageSize));
        } else {
            total = getAnalysisTotal(competitorInfoId);
            cprAnalysisList = hibernateTemplate.executeFind(new PagingHibernateCallback(
                    CustomerConstant.CPRANALYSISWIN_HQL, 1, "competitorInfoId", competitorInfoId,
                    pageSize));
        }
        map.put(CustomerConstant.CPRANALYSIS, cprAnalysisList);
        map.put(CRMConstant.TOTAL, total);
        LOG.debug("method getAnalysis end!");
        return map;
    }

    /**
     * Description: Update cprAnalysis.
     * 
     * @param cprAnalysisInfo
     *            CprAnalysisDto which is added or updated
     * 
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public void updateCprAnalysis(CprAnalysisDto cprAnalysisInfo) throws DataAccessException {
        LOG.debug("method updateCprAnalysis start!");
        hibernateTemplate.saveOrUpdate(cprAnalysisInfo);
        LOG.debug("method updateCprAnalysis end!");
    }

    /**
     * Description: Delete the info from the database.
     * 
     * @param cprAnalysisIDs
     *            store the deleted IDs
     * 
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public void deleteCprAnalysis(String cprAnalysisIDs) throws DataAccessException {
        LOG.debug("method deleteCprAnalysis start!");
        hibernateTemplate.bulkUpdate(CustomerConstant.DELETE_CPRANALYSIS + cprAnalysisIDs);
        LOG.debug("method deleteCprAnalysis end!");
    }

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
    @Override
    @SuppressWarnings("unchecked")
    public long getAnalysisTotal(int competitorInfoId) throws DataAccessException {
        LOG.debug("method getAnalysisTotal start!");
        List<Long> list = null;
        if (competitorInfoId == 0) {
            list = hibernateTemplate.find(CustomerConstant.CPRANALYSISTOTAL_HQL);
        } else {
            list = hibernateTemplate.findByNamedParam(CustomerConstant.CPRANALYSISTOTALWIN_HQL,
                    "competitorInfoId", competitorInfoId);
        }
        LOG.debug("method getAnalysisTotal end!");
        return list.get(0);
    }

    /**
     * Description: Get all the cprId and cprName from table CompetitorInfo.
     * 
     * @return cprNameList List<CprNameChooseBean> the name of analysised
     * 
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<CprNameChooseBean> getAllCprName() throws DataAccessException {
        LOG.debug("method getAllCprName start!");
        List<CprNameChooseBean> cprNameList = hibernateTemplate
                .find(CustomerConstant.GET_ALLCPRNAME);
        LOG.debug("method getAllCprName end!");
        return cprNameList;
    }

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

}
