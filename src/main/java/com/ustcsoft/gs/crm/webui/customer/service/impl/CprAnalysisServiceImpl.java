/*
 * Copyright 2012 the original author or authors.
 */
package com.ustcsoft.gs.crm.webui.customer.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.CprNameChooseBean;
import com.ustcsoft.gs.crm.webui.customer.dao.CprAnalysisDao;
import com.ustcsoft.gs.crm.webui.customer.dto.CprAnalysisDto;
import com.ustcsoft.gs.crm.webui.customer.service.CprAnalysisService;

/**
 * Description: The class is used for add, edit and delete information from
 * table.
 * 
 * @author wuhao1
 * @author libaoshan
 * @since August 2012
 */
public class CprAnalysisServiceImpl implements CprAnalysisService {

    private static Log LOG = LogFactory.getLog(CprAnalysisServiceImpl.class);

    /** Used for getting class CprAnalysisDao */
    private CprAnalysisDao cprAnalysisDao = null;

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
    @Override
    public Map<String, Object> getAnalysis(int page, int competitorInfoId, int pageSize)
            throws CRMDBException {
        LOG.debug("method getAnalysis start!");
        Map<String, Object> map = null;
        try {
            map = cprAnalysisDao.getAnalysis(page, competitorInfoId, pageSize);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getAllAnalysis!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getAnalysis end!");
        return map;
    }

    /**
     * Description: Add and update info.
     * 
     * @param cprAnalysisInfo
     *            CprAnalysisDto which is added or updated
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void updateCprAnalysis(CprAnalysisDto cprAnalysisInfo) throws CRMDBException {
        LOG.debug("method updateCprAnalysis start!");
        try {

            // Set the style of time
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(CRMConstant.SIMPLEDATEFORMAT);

            // Get time of now
            String nowTime = sdf.format(calendar.getTime());
            cprAnalysisInfo.setCprAnalysisTime(nowTime);
            cprAnalysisDao.updateCprAnalysis(cprAnalysisInfo);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method updateCprAnalysisInfo!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method updateCprAnalysis end!");
    }

    /**
     * Description: Get all the cprName.
     * 
     * @return cprNameList store the analysis name
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public List<CprNameChooseBean> getAllCprName() throws CRMDBException {
        LOG.debug("method getAllCprName start!");
        List<CprNameChooseBean> cprNameList = null;
        try {
            cprNameList = cprAnalysisDao.getAllCprName();
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getAllCprName!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getAllCprName end!");
        return cprNameList;
    }

    /**
     * Description: Delete cprAnalysis.
     * 
     * @param cprAnalysisIDs
     *            store the deleted IDs
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteCprAnalysis(String cprAnalysisIDs) throws CRMDBException {
        LOG.debug("method deleteCprAnalysis start!");
        try {
            cprAnalysisIDs = CRMConstant.LEFT_PARENTHESIS + cprAnalysisIDs
                    + CRMConstant.RIGHT_PARENTHESIS;
            cprAnalysisDao.deleteCprAnalysis(cprAnalysisIDs);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method deleteCprAnalysis!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method deleteCprAnalysis end!");
    }

    /**
     * @return the cprAnalysisDao
     */
    public CprAnalysisDao getCprAnalysisDao() {
        return cprAnalysisDao;
    }

    /**
     * @param cprAnalysisDao
     *            the cprAnalysisDao to set
     */
    public void setCprAnalysisDao(CprAnalysisDao cprAnalysisDao) {
        this.cprAnalysisDao = cprAnalysisDao;
    }
}
