/*
 * SalesTrackService.java
 */
package com.ustcsoft.gs.crm.webui.sales.service;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;

/**
 * 销售事件履历
 * 
 * @author shiben
 * 
 */
public interface SalesTrackService {
    /**
     * 
     * @param id
     * @return map
     * @throws CRMDBException
     */
    public Map<String, Object> getAllSalesTrack(final String id) throws CRMDBException;
}
