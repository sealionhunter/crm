package com.ustcsoft.gs.crm.webui.count.service.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.count.dao.SalesCountDao;
import com.ustcsoft.gs.crm.webui.count.service.SalesCountService;

/**
 * 
 * @author jiaxu
 * 
 */
public class SalesCountServiceImpl implements SalesCountService {

    private static Log LOG = LogFactory.getLog(SalesCountServiceImpl.class);

    private SalesCountDao salesCountDao;

    /**
     * 
     * @return salesCountDao
     */
    public SalesCountDao getSalesCountDao() {
        return salesCountDao;
    }

    /**
     * 
     * @param salesCountDao
     */
    public void setSalesCountDao(SalesCountDao salesCountDao) {
        this.salesCountDao = salesCountDao;
    }

    /**
     * this method will count every sales event by status
     * 
     * @param startDate
     *            start of the date
     * @param endDate
     *            end of the date
     * @throws CRMDBException
     * @return Map<String,Object>
     */
    @Override
    public Map<String, Object> countSalesEvents(String startDate, String endDate)
            throws CRMDBException {
        Map<String, Object> map;
        try {
            LOG.debug("method querySalesEvents start!");
            map = getSalesCountDao().countSalesEvents(startDate, endDate);
            LOG.debug("method querySalesEvents end!");
        } catch (DataAccessException e) {
            LOG.info("DataAccessException occurs in method querySalesEvents!", e);
            throw new CRMDBException(e);
        }
        return map;
    }
}
