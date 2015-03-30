package com.ustcsoft.gs.crm.webui.system.service;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;

/**
 * MenuManageService interface
 * 
 * @author wangzhanxu
 * 
 */
public interface MenuManageService {
    /**
     * edit menu text
     * 
     * @param id
     *            tree id to edit text
     * @param text
     *            edit text
     * @throws CRMDBException
     *             in case of CRMDBException
     */
    public void editMenuText(int id, String text) throws CRMDBException;
}
