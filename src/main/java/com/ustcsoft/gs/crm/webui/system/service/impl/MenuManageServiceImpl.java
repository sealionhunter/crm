package com.ustcsoft.gs.crm.webui.system.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.system.dao.MenuManageDao;
import com.ustcsoft.gs.crm.webui.system.service.MenuManageService;

/**
 * MenuManageServiceImpl class implements MenuManageService
 * 
 * @author wangzhanxu
 * 
 */
public class MenuManageServiceImpl implements MenuManageService {
    /** get LOG */
    private static final Log LOG = LogFactory.getLog(MenuManageService.class);
    /** menuManageDao object */
    private MenuManageDao menuManageDao = null;

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
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public void editMenuText(int id, String text) throws CRMDBException {
        LOG.debug("method editMenuText start!");
        try {
            menuManageDao.editMenuTree(id, text);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method editMenuText!", e);
            throw new CRMDBException("DataAccessException occurs in method editMenuText!", e);
        }
        LOG.debug("method editMenuText end!");
    }

    public void setMenuManageDao(MenuManageDao menuManageDao) {
        this.menuManageDao = menuManageDao;
    }

}
