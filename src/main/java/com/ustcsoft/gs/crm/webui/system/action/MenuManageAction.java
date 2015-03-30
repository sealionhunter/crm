package com.ustcsoft.gs.crm.webui.system.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.system.service.MenuManageService;

/**
 * menu manage action
 * 
 * @author wangzhanxu
 * 
 */
public class MenuManageAction extends CRMAction {
    private static final long serialVersionUID = 1L;
    /** get Log */
    private static final Log LOG = LogFactory.getLog(MenuManageAction.class);
    private MenuManageService menuManageService = null;
    /** tree id */
    private int id;
    /** tree text */
    private String text;

    /**
     * edit menu text
     * 
     * @return SUCCESS
     * @throws CRMDBException
     */
    public String editMenuText() throws CRMDBException {
        LOG.debug("method editMenuText start!");
        menuManageService.editMenuText(id, text);
        LOG.debug("method editMenuText end!");
        return SUCCESS;
    }

    /**
     * get menuManageService
     * 
     * @return menuManageService
     */
    public MenuManageService getMenuManageService() {
        return menuManageService;
    }

    /**
     * set menuManageService
     * 
     * @param menuManageService
     */
    public void setMenuManageService(MenuManageService menuManageService) {
        this.menuManageService = menuManageService;
    }

    /**
     * get tree node id
     * 
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * set tree node id
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * get tree node text
     * 
     * @return text
     */
    public String getText() {
        return text;
    }

    /**
     * set tree node text
     * 
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }
}
