package com.ustcsoft.gs.crm.webui.system.service.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.system.dao.OrganizationalStructureDao;
import com.ustcsoft.gs.crm.webui.system.service.OrganizationalStructureService;

/**
 * 
 * @author jiaxu
 * 
 */
public class OrganizationalStructureServiceImpl implements OrganizationalStructureService {
    private static final Log LOG = LogFactory.getLog(OrganizationalStructureServiceImpl.class);
    private OrganizationalStructureDao organizationalStructureDao;

    /**
     * 
     * @return orgnizationalStructureDao
     */
    public OrganizationalStructureDao getOrganizationalStructureDao() {
        return organizationalStructureDao;
    }

    /**
     * 
     * @param orgnizationalStructureDao
     */
    public void setOrganizationalStructureDao(OrganizationalStructureDao organizationalStructureDao) {
        this.organizationalStructureDao = organizationalStructureDao;
    }

    /**
     * this method uses to get the data of organization from database.
     * 
     * @return Map<String,Object>
     */
    @Override
    public Map<String, Object> queryOrganizationStructure() throws CRMDBException {
        LOG.debug("method getOrganizationStructure start!");
        Map<String, Object> map;
        try {
            map = getOrganizationalStructureDao().queryOrganizationStructure();
        } catch (DataAccessException e) {
            LOG.info("DataAccessException occurs in method getOrganizationStructure!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getOrganizationStructure end");
        return map;
    }
}
