package com.ustcsoft.gs.crm.webui.system.service;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;

/**
 * 
 * @author jiaxu
 */
public interface OrganizationalStructureService {
    /**
     * This method will be overrided if some class implements the interface.
     * 
     * @return Map<String,Object>
     * @throws CRMDBException
     */
    public Map<String, Object> queryOrganizationStructure() throws CRMDBException;
}
