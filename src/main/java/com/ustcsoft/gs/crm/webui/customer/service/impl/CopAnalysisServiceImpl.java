/*
 * Class name: CopAnalysisServiceImpl
 * 
 * Version information: 1.0
 * 
 * Date:2012.10.8
 *  
 */
package com.ustcsoft.gs.crm.webui.customer.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.crm.webui.code.dto.CodeDto;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.customer.dao.CopAnalysisDao;
import com.ustcsoft.gs.crm.webui.customer.dto.CopAnalysisDto;
import com.ustcsoft.gs.crm.webui.customer.service.CopAnalysisService;

/**
 * implement of cooperator analysis service interface.
 * 
 * @author xujueyin
 * 
 */
public class CopAnalysisServiceImpl implements CopAnalysisService {

    /** get Log */
    private static final Log LOG = LogFactory.getLog(CopAnalysisServiceImpl.class);

    /** define dao for service */
    private CopAnalysisDao copAnalysisDao;

    /**
     * @return the copAnalysisDao
     */
    public CopAnalysisDao getCopAnalysisDao() {
        return copAnalysisDao;
    }

    /**
     * @param copAnalysisDao
     *            the copAnalysisDao to set
     */
    public void setCopAnalysisDao(CopAnalysisDao copAnalysisDao) {
        this.copAnalysisDao = copAnalysisDao;
    }

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
    @Override
    public Map<String, Object> getAllCopAnalysis(int currpage, int limit) throws CRMDBException {
        LOG.debug("method getAllCopAnalysis start!");
        Map<String, Object> map = null;
        try {
            // load cooperator analysis
            map = copAnalysisDao.getAllCopAnalysis(currpage, limit);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getAllCopAnalysis!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getAllCopAnalysis end!");
        return map;
    }

    /**
     * add or update cooperator analysis.
     * 
     * @param copAnalysis
     *            CopAnalysisDto add or update dto
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void saveOrUpdateCopAnalysis(CopAnalysisDto copAnalysis) throws CRMDBException {
        LOG.debug("method saveOrUpdateCopAnalysis start!");
        try {
            // generate cooperator analysis created date
            Date createTime = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            // format date
            String strTime = sdf.format(createTime);
            copAnalysis.setCreateTime(strTime);
            // save or update cooperator analysis
            copAnalysisDao.saveOrUpdateCopAnalysis(copAnalysis);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method saveOrUpdateCopAnalysis!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method saveOrUpdateCopAnalysis end!");
    }

    /**
     * delete cooperator analysis by cooperator IDs.
     * 
     * @param copAnalysisIDs
     *            String deleted cooperator ids
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteCopAnalysis(String copAnalysisIDs) throws CRMDBException {
        LOG.debug("method deleteCopAnalysis start!");
        try {
            // batch remove cooperator analysis
            copAnalysisDao.deleteCopAnalysis(CRMUtils.getStringForDelete(copAnalysisIDs));
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method deleteCopAnalysis!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method deleteCopAnalysis end!");
    }

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
    @Override
    public Map<String, Object> getCopAnalysisByID(int cooperatorID, int currpage)
            throws CRMDBException {
        LOG.debug("method getCopAnalysisByID start!");
        Map<String, Object> map = null;
        try {
            // get cooperator analysis by id
            map = copAnalysisDao.getCopAnalysisByID(cooperatorID, currpage);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getCopAnalysisByID!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getCopAnalysisByID end!");
        return map;
    }

    /**
     * get cooperator name.
     * 
     * @return List<CodeDto>
     * @throws CRMDBException
     */
    @Override
    public List<CodeDto> getCopName() throws CRMDBException {
        LOG.debug("getCopName method start!");
        try {
            return copAnalysisDao.getCopName();
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs.");
            throw new CRMDBException("DataAccessException occurs.", e);
        }
    }
}