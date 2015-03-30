/*
 * SalesServiceImpl.java
 */
package com.ustcsoft.gs.crm.webui.sales.service.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.sales.dao.SalesTrackDao;
import com.ustcsoft.gs.crm.webui.sales.service.SalesTrackService;

/**
 * 
 * @author shiben
 * 
 */
public class SalesTrackServiceImpl implements SalesTrackService {

    /**
     * log
     */
    private static final Log LOG = LogFactory.getLog(SalesTrackServiceImpl.class);

    /**
     * 注入salesTrackDao
     */
    private SalesTrackDao salesTrackDao = null;

    /**
     * salesTrackDao的set（）方法
     * 
     * @param salesTrackDao
     */
    public void setSalesTrackDao(SalesTrackDao salesTrackDao) {
        this.salesTrackDao = salesTrackDao;
    }

    /**
     * 查询销售履历
     * 
     * @param id
     * @return map
     * @throws CRMDBException
     */
    @Override
    public Map<String, Object> getAllSalesTrack(String id) throws CRMDBException {
        LOG.debug("method getAllSalesTrack start!");
        Map<String, Object> map = null;
        try {
            map = salesTrackDao.getAllSalesTrack(id);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getAllSalesTrack:{}", e);
            throw new CRMDBException("Error getAllSalesTrack(#" + id + "#):" + e);
        }
        LOG.debug("method getAllSalesTrack end!");
        return map;
    }
}
