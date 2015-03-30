package com.ustcsoft.gs.crm.webui.system.dao;

import java.util.Map;

/**
 * 
 * @author jiaxu
 * 
 */
public interface OrganizationalStructureDao {
    /**
     * This method will be overrided if some class implements the interface.
     * 
     * @return Map<String,Object> contains the OrganizationStructure data
     */
    public Map<String, Object> queryOrganizationStructure();
}
