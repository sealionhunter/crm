package com.ustcsoft.gs.crm.webui.system.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.system.constant.SystemConstant;
import com.ustcsoft.gs.crm.webui.system.dao.MenuManageDao;

public class MenuManageDaoImpl implements MenuManageDao {
    /** get Log */
    private static final Log LOG = LogFactory.getLog(MenuManageDaoImpl.class);
    /** hibernateTemplate object */
    private HibernateTemplate hibernateTemplate = null;

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
    @Override
    public void editMenuTree(int id, String text) throws DataAccessException {
        LOG.debug("method editMenuTree start!");
        hibernateTemplate.bulkUpdate(SystemConstant.EDIT_MENU_TEXT_HQL, new Object[] { text, id });
        LOG.debug("method editMenuTree end!");
    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

}
