package com.ustcsoft.gs.crm.webui.customer.action;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.ContactSelectSearchBean;
import com.ustcsoft.gs.crm.webui.customer.service.ContactSelectService;

/**
 * Description: The class is used to deal with the page, which is
 * ContactSelect.js.
 * 
 * @author libaoshan
 * 
 * @since August 2012
 */
public class ContactSelectAction extends CRMAction {

    private static final long serialVersionUID = 1L;

    private static Log LOG = LogFactory.getLog(ContactSelectAction.class);

    /** Used for getting class ContactSelectService */
    private ContactSelectService contactSelectService = null;

    /** Store the search information */
    private String contactSelectSearchBean = "";

    /** Store the page */
    private int page = 1;

    /** Store the information */
    private Map<String, Object> map = new HashMap<String, Object>();

    /** Used for add information */
    private String contactSelectAddIDs = "";

    /** Store the onjectID */
    private int objectID = 0;

    /** Store the objectFlag */
    private int objectFlag = 0;

    /** Used for deleting information */
    private String contactSelectIDs = "";

    /** Search way */
    private int searchFlag = 0;

    /**
     * Get all ContactSelect
     * 
     * @return SUCCESS
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public String execute() throws CRMDBException {
        LOG.debug("method execute start!");

        // change JsonString to an object
        JSONObject json = JSONObject.fromObject(contactSelectSearchBean);

        // change JSONObject to bean ContactSearchBean
        ContactSelectSearchBean searchBean = (ContactSelectSearchBean) JSONObject.toBean(json,
                ContactSelectSearchBean.class);
        map = contactSelectService.getAllContactSelect(searchFlag, searchBean, page, limit);
        LOG.debug("method execute end!");
        return SUCCESS;
    }

    /**
     * Add ContactSelect
     * 
     * @return SUCCESS
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String addContactSelect() throws CRMDBException {
        LOG.debug("method addContactSelect start!");
        contactSelectService.saveContactSelect(contactSelectAddIDs, objectFlag, objectID);
        LOG.debug("method addContactSelect end!");
        return SUCCESS;
    }

    /**
     * Delete ContactSelect
     * 
     * @return SUCCESS
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String deleteContactSelect() throws CRMDBException {
        LOG.debug("method deleteContactSelect start!");
        contactSelectService.deleteContactSelect(contactSelectIDs);
        LOG.debug("method deleteContactSelect end!");
        return SUCCESS;
    }

    /**
     * @return the contactSelectService
     */
    public ContactSelectService getContactSelectService() {
        return contactSelectService;
    }

    /**
     * @param contactSelectService
     *            the contactSelectService to set
     */
    public void setContactSelectService(ContactSelectService contactSelectService) {
        this.contactSelectService = contactSelectService;
    }

    /**
     * @return the contactSelectSearchBean
     */
    public String getContactSelectSearchBean() {
        return contactSelectSearchBean;
    }

    /**
     * @param contactSelectSearchBean
     *            the contactSelectSearchBean to set
     */
    public void setContactSelectSearchBean(String contactSelectSearchBean) {
        this.contactSelectSearchBean = contactSelectSearchBean;
    }

    /**
     * @return the page
     */
    @Override
    public int getPage() {
        return page;
    }

    /**
     * @param page
     *            the page to set
     */
    @Override
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return the map
     */
    @Override
    public Map<String, Object> getMap() {
        return map;
    }

    /**
     * @param map
     *            the map to set
     */
    @Override
    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    /**
     * @return the objectID
     */
    public int getObjectID() {
        return objectID;
    }

    /**
     * @param objectID
     *            the objectID to set
     */
    public void setObjectID(int objectID) {
        this.objectID = objectID;
    }

    /**
     * @return the objectFlag
     */
    public int getObjectFlag() {
        return objectFlag;
    }

    /**
     * @param objectFlag
     *            the objectFlag to set
     */
    public void setObjectFlag(int objectFlag) {
        this.objectFlag = objectFlag;
    }

    /**
     * @return the contactSelectIDs
     */
    public String getContactSelectIDs() {
        return contactSelectIDs;
    }

    /**
     * @param contactSelectIDs
     *            the contactSelectIDs to set
     */
    public void setContactSelectIDs(String contactSelectIDs) {
        this.contactSelectIDs = contactSelectIDs;
    }

    /**
     * @return the searchFlag
     */
    @Override
    public int getSearchFlag() {
        return searchFlag;
    }

    /**
     * @param searchFlag
     *            the searchFlag to set
     */
    @Override
    public void setSearchFlag(int searchFlag) {
        this.searchFlag = searchFlag;
    }

    /**
     * 
     * @return contactSelectAddIDs
     */
    public String getContactSelectAddIDs() {
        return contactSelectAddIDs;
    }

    /**
     * 
     * @param contactSelectAddIDs
     *            the contactSelectAddIDs to set
     */
    public void setContactSelectAddIDs(String contactSelectAddIDs) {
        this.contactSelectAddIDs = contactSelectAddIDs;
    }

}