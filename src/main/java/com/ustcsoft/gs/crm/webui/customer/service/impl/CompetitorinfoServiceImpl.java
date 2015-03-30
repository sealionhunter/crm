/*
 * Class name: CompetitorinfoServiceImpl
 * 
 * Version information: 1.0
 * 
 * Date:2012.9.27
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
import com.ustcsoft.gs.crm.webui.customer.bean.CompetitorSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dao.CompetitorinfoDao;
import com.ustcsoft.gs.crm.webui.customer.dto.CompetitorinfoDto;
import com.ustcsoft.gs.crm.webui.customer.service.CompetitorinfoService;

/**
 * @author weijinmei CompetitorinfoServiceImpl
 */
public class CompetitorinfoServiceImpl implements CompetitorinfoService {
    private static Log log = LogFactory.getLog(CompetitorinfoServiceImpl.class);
    private CompetitorinfoDao competitorinfoDao;

    /**
     * @return competitorinfoDao
     */
    @Override
    public CompetitorinfoDao getCompetitorinfoDao() {
        return competitorinfoDao;
    }

    /**
     * @param competitorinfoDao
     *            the competitorinfoDao to set
     */
    @Override
    public void setCompetitorinfoDao(CompetitorinfoDao competitorinfoDao) {
        this.competitorinfoDao = competitorinfoDao;
    }

    /**
     * update Competitorinfo
     * 
     * @param competitorinfoDto
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void updateCompetitorinfo(CompetitorinfoDto competitorinfoDto) throws CRMDBException {
        log.debug("method updateCompetitorinfo start!");
        try {
            competitorinfoDao.updateCompetitorinfo(competitorinfoDto);
        } catch (DataAccessException e) {
            log.error("DataAccessException occurs in method updateCompetitorinfo!", e);
            throw new CRMDBException(e);
        }
        log.debug("method updateCompetitorinfo end!");
    }

    /**
     * get All Competitor
     * 
     * @param searchFlag
     * @param searchBean
     * @param currpage
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public Map<String, Object> getAllCompetitor(int searchFlag, CompetitorSearchBean searchBean,
            int currpage, int pageSize) throws CRMDBException {
        log.debug("method getAllCompetitor start!");
        Map<String, Object> allCompetitor = null;
        try {
            allCompetitor = getCompetitorinfoDao().getAllCompetitor(searchFlag, searchBean,
                    currpage, pageSize);
        } catch (DataAccessException e) {
            log.error("DataAccessException occurs in method getAllCompetitor!", e);
            throw new CRMDBException(e);
        }
        log.debug("method getAllCompetitor end!");
        return allCompetitor;
    }

    /**
     * delete CompetitorinfoAndCprAnalysis
     * 
     * @param competitorInfoIds
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteCompetitorinfoAndCprAnalysis(String competitorInfoIds) throws CRMDBException {
        log.debug("method deleteCompetitorinfo start!");
        try {
            competitorinfoDao.deleteCompetitorinfoAndCprAnalysis(competitorInfoIds);

        } catch (DataAccessException e) {
            log.error("DataAccessException occurs in method deleteCompetitorinfo!", e);
            throw new CRMDBException(e);
        }
        log.debug("method deleteCompetitorinfo end!");
    }

    /**
     * judge if CompetitorName existed
     * 
     * @param competitorinfoDto
     * @return boolean of true or false
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public boolean judgeCompetitorName(CompetitorinfoDto competitorinfoDto) throws CRMDBException {
        log.debug("method judgeCompetitorName start!");
        boolean NameIsExisted = false;
        String competitorName = competitorinfoDto.getCompetitorName();
        int competitorID = competitorinfoDto.getCompetitorInfoId();
        try {
            if (competitorID == 0) {
                NameIsExisted = competitorinfoDao.judgeCompetitorName(competitorName);
            } else {
                String oldName = competitorinfoDao.getCompetitorNameByComID(competitorID);
                if (oldName.equals(competitorName)) {
                    NameIsExisted = false;
                } else {
                    NameIsExisted = competitorinfoDao.judgeCompetitorName(competitorName);
                }
            }
        } catch (DataAccessException e) {
            log.error("DataAccessException occurs in method judgeCompetitorName!", e);
            throw new CRMDBException(e);
        }
        log.debug("method judgeCompetitorName end!");
        return NameIsExisted;
    }
}
