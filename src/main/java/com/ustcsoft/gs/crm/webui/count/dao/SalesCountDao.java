package com.ustcsoft.gs.crm.webui.count.dao;

import java.util.Map;

/**
 * 
 * @author jiaxu
 * 
 */
public interface SalesCountDao {
    /**
     * 
     * this method will be overrided in class which implents this interface
     * 
     * @param startDate
     *            the start of the date
     * @param endDate
     *            the end of the date
     * @return Map<String,Object>
     */
    public Map<String, Object> countSalesEvents(String startDate, String endDate);
}
