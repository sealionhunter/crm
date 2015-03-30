package com.ustcsoft.gs.crm.webui.customer.service.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.customer.bean.CooperatorSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dao.CooperatorDao;
import com.ustcsoft.gs.crm.webui.customer.dto.CooperatorDto;
import com.ustcsoft.gs.crm.webui.customer.service.CooperatorService;

/**
 * CooperatorSerivceImplements
 * 
 * @author xujialong
 * 
 */
public class CooperatorServiceImpl implements CooperatorService {

    private static final Log LOG = LogFactory.getLog(CooperatorServiceImpl.class);

    private CooperatorDao cooperatorDao = null;

    /**
     * @return the cooperatorDao
     */
    public CooperatorDao getCooperatorDao() {
        return cooperatorDao;
    }

    /**
     * @param cooperatorDao
     *            the cooperatorDao to set
     */
    public void setCooperatorDao(CooperatorDao cooperatorDao) {
        this.cooperatorDao = cooperatorDao;
    }

    /**
     * list,simple search,advanced search
     * 
     * @param searchFlag
     *            int 0 mean list,1 mean simple search,2 mean advanced search
     * @param cooperatorSearchBean
     *            CooperatorSearchBean Cooperator search bean for searching
     * @param currPage
     *            int currPage for paging
     * @param limit
     *            int current page's limit for paging
     * @return Map<String, Object> the cooperator search list for page
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public Map<String, Object> getAllCooperatorShow(int searchFlag,
            CooperatorSearchBean cooperatorSearchBean, int currPage, int limit)
            throws CRMDBException {
        LOG.debug("method getAllCooperatorShow start!");
        Map<String, Object> map = null;
        try {
            if (searchFlag == 0) {
                map = cooperatorDao.getCooperatorList(currPage, limit);
            } else {
                map = cooperatorDao.searchCooperator(searchFlag, cooperatorSearchBean, currPage,
                        limit);
            }
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getAllCooperatorShow!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getAllCooperatorShow end!");
        return map;
    }

    /**
     * add Cooperator or update Cooperator
     * 
     * @param cooperatorDto
     *            CooperatorDto cooperator dto for save or update
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public void updateCooperator(CooperatorDto cooperatorDto) throws CRMDBException {
        LOG.debug("method updateCooperator start!");
        try {
            cooperatorDao.updateCooperator(cooperatorDto);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method updateCooperator!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method updateCooperator end!");

    }

    /**
     * delete Cooperator
     * 
     * @param cooperatorIDs
     *            String Strings includes all ids for del
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteCooperator(String cooperatorIDs) throws CRMDBException {
        LOG.debug("method deleteCooperator start!");
        try {
            cooperatorDao.deleteCooperator(CRMUtils.getStringForDelete(cooperatorIDs));
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method deleteCooperator!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method deleteCooperator end!");
    }

    /**
     * check name existed
     * 
     * @param coo
     *            CooperatorDto Cooperator dto
     * @return boolean,true mean name existing,false mean name not existing
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public boolean checkNameExisted(CooperatorDto coo) throws CRMDBException {
        LOG.debug("method checkNameExisted start!");
        boolean nameValid = false;
        try {
            nameValid = cooperatorDao.checkNameExisted(coo);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method checkNameExisted!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method checkNameExisted end!");
        return nameValid;
    }
}