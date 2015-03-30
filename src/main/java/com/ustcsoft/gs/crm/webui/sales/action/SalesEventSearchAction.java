/*
 * SalesEventSearchAction.java
 */
package com.ustcsoft.gs.crm.webui.sales.action;

import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.customer.service.CustomerTransferService;
import com.ustcsoft.gs.crm.webui.sales.bean.SalesSuperSearchBean;
import com.ustcsoft.gs.crm.webui.sales.service.SalesEventSearchService;

/**
 * 
 * @author chenguangzhou and shiben
 * 
 */
public class SalesEventSearchAction extends CRMAction {
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * log
     */
    private static final Log LOG = LogFactory.getLog(SalesEventSearchAction.class);

    /**
     * 注入salesEventSearchService
     */
    private SalesEventSearchService salesEventSearchService;
    private CustomerTransferService transferService = null;
    /**
     * 分页数据读取起始点
     */
    private int start;

    /**
     * 分页大小
     */
    private int limit;

    /**
     * 检索功能
     * 
     * @throws CRMDBException
     * @return SUCCESS
     */
    @Override
    public String execute() throws CRMDBException {
        LOG.debug("SalesEventSearchAction method search start!");
        SalesSuperSearchBean salesSuperSearchBean = (SalesSuperSearchBean) CRMUtils.jsonToBean(
                jsonString, SalesSuperSearchBean.class);
        String searchKey = salesSuperSearchBean.getSearchKey();
        Map<String, Object> userMap = transferService.getUser(0, 0, userID);
        map = salesEventSearchService.search(start, limit, searchKey, userMap);
        LOG.debug("SalesEventSearchAction method search end!");
        return SUCCESS;
    }

    /**
     * 高级检索功能
     * 
     * @return SUCCSSS
     * @throws CRMDBException
     */
    public String superQuery() throws CRMDBException {
        LOG.debug("SalesEventSearchAction method advancedSearch start!");
        JSONObject searchObj = JSONObject.fromObject(jsonString);
        SalesSuperSearchBean searchBean = (SalesSuperSearchBean) JSONObject.toBean(searchObj,
                SalesSuperSearchBean.class);
        Map<String, Object> userMap = transferService.getUser(0, 0, userID);
        map = salesEventSearchService.advancedSearch(start, limit, searchBean, userMap);
        LOG.debug("SalesEventSearchAction method advancedSearch end!");
        return SUCCESS;
    }

    /**
     * start的get（）方法
     * 
     * @return start
     */
    public int getStart() {
        return start;
    }

    /**
     * start的set（）方法
     * 
     * @param start
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * limit的get（）方法
     * 
     * @return limit
     */
    @Override
    public int getLimit() {
        return limit;
    }

    /**
     * limit的set（）方法
     * 
     * @param limit
     */
    @Override
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * map的get（）方法
     * 
     * @return map
     */
    @Override
    public Map<String, Object> getMap() {
        return map;
    }

    /**
     * map的set（）方法
     * 
     * @param map
     */
    @Override
    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    /**
     * salesEventSearchService的set（）方法
     * 
     * @param salesEventSearchService
     */
    public void setSalesEventSearchService(SalesEventSearchService salesEventSearchService) {
        this.salesEventSearchService = salesEventSearchService;
    }

    /**
     * jsonString的get（）方法
     * 
     * @param jsonString
     */
    @Override
    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public CustomerTransferService getTransferService() {
        return transferService;
    }

    public void setTransferService(CustomerTransferService transferService) {
        this.transferService = transferService;
    }
}
