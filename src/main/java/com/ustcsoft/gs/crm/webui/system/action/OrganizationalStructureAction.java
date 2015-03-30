package com.ustcsoft.gs.crm.webui.system.action;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.system.service.OrganizationalStructureService;

/**
 * 
 * @author jiaxu
 * 
 */
public class OrganizationalStructureAction extends CRMAction {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private OrganizationalStructureService organizationalStructureService;

    /**
     * 
     * @return orgnizationalStructureService
     */
    public OrganizationalStructureService getOrganizationalStructureService() {
        return organizationalStructureService;
    }

    /**
     * 
     * @param orgnizationalStructureService
     */
    public void setOrganizationalStructureService(
            OrganizationalStructureService organizationalStructureService) {
        this.organizationalStructureService = organizationalStructureService;
    }

    /**
     * This method will response a type of Map<String,String>
     * 
     * @return SUCCESS
     * @throws CRMDBException
     */
    public String queryOrganizationStructure() throws CRMDBException {
        map = organizationalStructureService.queryOrganizationStructure();
        return SUCCESS;
    }
}
