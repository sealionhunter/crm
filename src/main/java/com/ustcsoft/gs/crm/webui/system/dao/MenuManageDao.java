package com.ustcsoft.gs.crm.webui.system.dao;

import org.springframework.dao.DataAccessException;

/**
 * MenuManageDao interface
 * 
 * @author wangzhanxu
 * 
 */
public interface MenuManageDao {
    /**
     * edit menu tree
     * 
     * @param id
     *            tree id
     * @param text
     *            edit text
     * @throws DataAccessException
     *             incase of DataAccessException
     */
    public void editMenuTree(int id, String text) throws DataAccessException;
}
