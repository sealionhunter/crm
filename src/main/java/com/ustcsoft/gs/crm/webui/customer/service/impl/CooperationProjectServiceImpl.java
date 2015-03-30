package com.ustcsoft.gs.crm.webui.customer.service.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.customer.bean.CooperationProjectSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dao.CooperationProjectDao;
import com.ustcsoft.gs.crm.webui.customer.dto.CooperationProjectDto;
import com.ustcsoft.gs.crm.webui.customer.service.CooperationProjectService;

/**
 * CooperationProjectServiceImplements
 * 
 * @author xujialong
 * 
 */
public class CooperationProjectServiceImpl implements CooperationProjectService {

    private CooperationProjectDao cooperationProjectDao = null;

    private static final Log LOG = LogFactory.getLog(CooperationProjectServiceImpl.class);

    /**
     * @return the cooperationProjectDao
     */
    public CooperationProjectDao getCooperationProjectDao() {
        return cooperationProjectDao;
    }

    /**
     * @param cooperationProjectDao
     *            the cooperationProjectDao to set
     */
    public void setCooperationProjectDao(CooperationProjectDao cooperationProjectDao) {
        this.cooperationProjectDao = cooperationProjectDao;
    }

    /**
     * list and simple or super search cooperation project
     * 
     * @param searchFlag
     *            int 0 mean list,1 mean simple search,2 mean advanced search
     * @param cooperationProjectSearchBean
     *            CooperationProjectSearchBean Cooperation project search bean
     *            for searching
     * @param currpage
     *            int current page for paging
     * @param limit
     *            int current page's limit for paging
     * @return Map<String, Object> the cooperation project search list for page
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public Map<String, Object> searchCooperationProject(int searchFlag,
            CooperationProjectSearchBean cooperationProjectSearchBean, int currpage, int limit)
            throws CRMDBException {
        LOG.debug("method searchCooperatorProject start!");
        Map<String, Object> map = null;
        try {
            map = cooperationProjectDao.searchCooperationProject(searchFlag,
                    cooperationProjectSearchBean, currpage, limit);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method searchCooperationProject!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method searchCooperationProject end!");
        return map;
    }

    /**
     * add or update cooperation's project
     * 
     * @param cooperationProject
     *            CooperationProjectDto cooperation project dto for save or
     *            update
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public void updateCooperationProject(CooperationProjectDto cooperationProject)
            throws CRMDBException {
        LOG.debug("method updateCooperationProject start!");
        try {
            cooperationProjectDao.updateCooperationProject(cooperationProject);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method updateCooperationProject!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method updateCooperationProject end!");
    }

    /**
     * delete cooperation's project about cooperationProjectID
     * 
     * @param cooperationProjectIDs
     *            String Strings includes all ids for del
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public void deleteCooperationProject(String cooperationProjectIDs) throws CRMDBException {
        LOG.debug("method deleteCooperatorProject start!");
        try {
            cooperationProjectDao.deleteCooperationProject(CRMUtils
                    .getStringForDelete(cooperationProjectIDs));
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method deleteCooperationProject!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method deleteCooperatorProject end!");
    }

    /**
     * check name existed
     * 
     * @param cpd
     *            CooperationProjectDto cooperation project dto
     * @return boolean,true mean name existing,false mean name not existing
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public boolean checkNameExisted(CooperationProjectDto cpd) throws CRMDBException {
        LOG.debug("method checkNameExisted start!");
        boolean nameValid = false;
        try {
            nameValid = cooperationProjectDao.checkNameExisted(cpd);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method checkNameExisted!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method checkNameExisted!");
        return nameValid;
    }
}