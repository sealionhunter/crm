/*
 * Class name: CoopResumeServiceImpl
 * 
 * Version information: 1.0
 * 
 * Date:2012.9.11
 *  
 */
package com.ustcsoft.gs.crm.webui.customer.service.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.CoopResumeSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dao.CoopResumeDao;
import com.ustcsoft.gs.crm.webui.customer.dto.CoopResumeDto;
import com.ustcsoft.gs.crm.webui.customer.service.CoopResumeService;

/**
 * @author tangyunpeng
 */
public class CoopResumeServiceImpl implements CoopResumeService {

    private static Log LOG = LogFactory.getLog(CoopResumeServiceImpl.class);

    private CoopResumeDao coopResumeDao = null;

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
    @Override
    public Map<String, Object> searchOrGetAllCoopResume(int searchFlag,
            CoopResumeSearchBean searchValue, int currPage, int customerID, int pageSize)
            throws CRMDBException {

        LOG.debug("method searchOrGetAllCoopResume start.");
        Map<String, Object> map = null;
        try {
            if (searchFlag == 1 || searchFlag == 2) {
                // query coopResume
                searchValue.setCustomerID(customerID);
                map = coopResumeDao.queryCoopResume(searchFlag, searchValue, currPage, pageSize);
            } else {
                // load coopResume from database.
                map = coopResumeDao.getCoopResume(currPage, customerID, pageSize);
            }
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method searchOrGetAllCoopResume.", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method searchOrGetAllCoopResume end.");
        return map;
    }

    /**
     * The method is used to add or update record.
     * 
     * @param coopResumeDto
     *            CoopResumeDto which will be added or edited
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void addOrUpdateCoopResume(CoopResumeDto coopResumeDto) throws CRMDBException {

        LOG.debug("method addOrUpdateCoopResume start.");
        try {
            coopResumeDao.addOrUpdateCoopResume(coopResumeDto);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method addOrUpdateCoopResume.", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method addOrUpdateCoopResume end.");
    }

    /**
     * The method is used to delete records.
     * 
     * @param coopResumeIDs
     *            sourceID list which will be deleted
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteCoopResume(String coopResumeIDs) throws CRMDBException {

        LOG.debug("method deleteCoopResume start.");
        try {
            coopResumeDao.deleteCoopResume(coopResumeIDs);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method deleteCoopResume.", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method deleteCoopResume end.");
    }

    /**
     * Judge customerName
     * 
     * @param coopResumeDto
     * @return true if customerName is not
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    @Override
    public boolean judgeProjectName(CoopResumeDto coopResumeDto) throws CRMDBException {
        LOG.debug("method judgeProjectName start.");
        boolean bool = false;
        try {
            bool = coopResumeDao.judgeProjectName(coopResumeDto);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method judgeProjectName.", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method judgeProjectName end.");
        return bool;
    }

    /**
     * @return the coopResumeDao
     */
    public CoopResumeDao getCoopResumeDao() {
        return coopResumeDao;
    }

    /**
     * @param coopResumeDao
     *            the coopResumeDao to set
     */
    public void setCoopResumeDao(CoopResumeDao coopResumeDao) {
        this.coopResumeDao = coopResumeDao;
    }
}
