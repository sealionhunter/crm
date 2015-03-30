/*
 * SalesTrackAction.java
 */
package com.ustcsoft.gs.crm.webui.sales.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.sales.service.SalesTrackService;

/**
 * 销售履历
 * 
 * @author shiben
 * 
 */
public class SalesTrackAction extends CRMAction {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * log
     */
    private static final Log LOG = LogFactory.getLog(SalesTrackAction.class);

    /**
     * 注入salesTrackService
     */
    private SalesTrackService salesTrackService = null;

    /**
     * 获得相关所有履历信息
     * 
     * @return type of String
     * @throws CRMDBException
     */
    public String getSalesTrack() throws CRMDBException {
        LOG.debug("method getSalesTrack start!");
        map = salesTrackService.getAllSalesTrack(jsonString);
        LOG.debug("method getSalesTrack end!");
        return SUCCESS;
    }

    /**
     * salesTrackService的get（）方法
     * 
     * @return salesTrackService
     */
    public SalesTrackService getSalesTrackService() {
        return salesTrackService;
    }

    /**
     * salesTrackService的set（）方法
     * 
     * @param salesTrackService
     */
    public void setSalesTrackService(SalesTrackService salesTrackService) {
        this.salesTrackService = salesTrackService;
    }

}
