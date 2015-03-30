package com.ustcsoft.gs.crm.webui.count.service;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;

/**
 * 
 * @author jiaxu
 * 
 */
public interface SalesCountService {

    /**
     * this method will be overrided in class which implents this interface
     * 
     * @param startDate
     *            start of the date
     * @param endDate
     *            end of the date
     * @throws CRMDBException
     * @return type of Map<String, Object>
     */
    public Map<String, Object> countSalesEvents(String startDate, String endDate)
            throws CRMDBException;
}
