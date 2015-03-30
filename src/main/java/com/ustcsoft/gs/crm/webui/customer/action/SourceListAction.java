package com.ustcsoft.gs.crm.webui.customer.action;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.customer.bean.SourceSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.SourceInfoDto;
import com.ustcsoft.gs.crm.webui.customer.service.SourceInfoService;

/**
 * class SourceListAction for js page to execute action
 * 
 * @author xuzhen
 */
public class SourceListAction extends CRMAction {
    private static final long serialVersionUID = 1L;

    private static Log LOG = LogFactory.getLog(SourceListAction.class);

    /** used for get class SourceInfoService */
    private transient SourceInfoService sourceInfoService = null;

    /** source information which will be added or edited */
    private transient SourceInfoDto source = null;

    private transient int customerFlag = 0;

    /**
     * the method execute for the action CustomerSourceList
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public String execute() throws CRMDBException {
        LOG.debug("method execute start!");
        SourceSearchBean searchBean = new SourceSearchBean();
        searchBean = (SourceSearchBean) CRMUtils.jsonToBean(jsonString, SourceSearchBean.class);
        map = getSourceInfoService().getOrSearchSource(super.searchFlag, searchBean, super.page,
                super.limit, super.userID);
        LOG.debug("method execute end!");
        return SUCCESS;
    }

    /**
     * the method to delete CustomerSource record from DB
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public String delete() throws CRMDBException {
        LOG.debug("method delete start!");
        getSourceInfoService().deleteSource(jsonString);
        LOG.debug("method delete end!");
        return SUCCESS;
    }

    /**
     * format and check message which are change from json data
     * 
     * @throws CRMDBException
     *             jsonField change failure
     */
    @Override
    public void validateUpdate() throws CRMDBException {
        source = (SourceInfoDto) CRMUtils.jsonToBean(jsonString, SourceInfoDto.class);
        check();
    }

    /*
     * check sourceInfo
     */
    private void check() {
        if ("".equalsIgnoreCase(source.getSourceArea())) {
            addFieldError("sourceArea",
                    this.getText("sourceArea.invalid") + this.getText("not_null.invalid"));
        }
        if (source.getSourceArea().length() > CRMConstant.LENGTH_1) {
            addFieldError("sourceArea",
                    this.getText("sourceArea.invalid") + this.getText("flow.invalid"));
        }
        if (source.getDescriptions().length() > CRMConstant.LENGTH_2) {
            addFieldError("sourceDescriptions",
                    this.getText("sourcedescriptions.invalid") + this.getText("flow.invalid"));
        }
        showFieldError();
    }

    /**
     * the method to add or edit CustomerSource record for DB
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public String update() throws CRMDBException {
        LOG.debug("method update start!");
        getSourceInfoService().updateSource(source);
        LOG.debug("method update end!");
        return SUCCESS;
    }

    /**
     * the method to get all Customer Name and ID from CustomerDto
     * 
     * @return success
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String getCustomer() throws CRMDBException {
        LOG.debug("method getCustomer start!");
        map = getSourceInfoService().getCustomer(customerFlag, super.userID);
        LOG.debug("method getCustomer end!");
        return SUCCESS;
    }

    /**
     * 
     * @return sourceInfoService
     */
    public SourceInfoService getSourceInfoService() {
        return sourceInfoService;
    }

    /**
     * 
     * @param sourceInfoService
     *            the sourceInfoService to set
     */
    public void setSourceInfoService(SourceInfoService sourceInfoService) {
        this.sourceInfoService = sourceInfoService;
    }

    /**
     * 
     * @return jsonString
     */
    @Override
    public String getJsonString() {
        return jsonString;
    }

    /**
     * 
     * @param jsonString
     *            the jsonString to set
     */
    @Override
    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    /**
     * 
     * @return page
     */
    @Override
    public int getPage() {
        return page;
    }

    /**
     * 
     * @param page
     *            the page to set
     */
    @Override
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * 
     * @return map
     */
    @Override
    public Map<String, Object> getMap() {
        return map;
    }

    /**
     * 
     * @param map
     *            the map to set
     */
    @Override
    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    /**
     * 
     * @return searchFlag
     */
    @Override
    public int getSearchFlag() {
        return searchFlag;
    }

    /**
     * 
     * @param searchFlag
     *            the searchFlag to set
     */
    @Override
    public void setSearchFlag(int searchFlag) {
        this.searchFlag = searchFlag;
    }

    /**
     * @return the customerFlag
     */
    public int getCustomerFlag() {
        return customerFlag;
    }

    /**
     * @param customerFlag
     *            the customerFlag to set
     */
    public void setCustomerFlag(int customerFlag) {
        this.customerFlag = customerFlag;
    }

}