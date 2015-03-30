package com.ustcsoft.gs.crm.webui.customer.service;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.ContactSelectSearchBean;

/**
 * Description: The interface is used to deal with select service.
 * 
 * @author libaoshan
 * 
 */
public interface ContactSelectService {

    /**
     * Get all ContactSelect
     * 
     * @param searchFlag
     *            0 is show list without conditions
     * @param searchBean
     *            is conditions
     * @param page
     *            is current page
     * @param limit
     *            is pageSize
     * 
     * @throws CRMDBException
     *             if there is a DataAccessException
     */
    public Map<String, Object> getAllContactSelect(int searchFlag,
            ContactSelectSearchBean searchBean, int page, int limit) throws CRMDBException;

    /**
     * Add ContactSelect
     * 
     * @param contactSelectAddIDs
     *            entity object need add
     * @param objectFlag
     * @param objectID
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void saveContactSelect(String contactSelectAddIDs, int objectFlag, int objectID)
            throws CRMDBException;

    /**
     * Delete ContactSelect
     * 
     * @param contactSelectIDs
     *            need delete
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void deleteContactSelect(String contactSelectIDs) throws CRMDBException;

}
