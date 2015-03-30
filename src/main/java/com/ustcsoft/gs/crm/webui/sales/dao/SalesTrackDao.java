/*
 * SalesTrackDao.java 
 */
package com.ustcsoft.gs.crm.webui.sales.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.sales.bean.SalesTrackBean;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesTrackDto;

/**
 * 销售事件履历
 * 
 * @author shiben
 * 
 */
public interface SalesTrackDao {
    /**
     * The method is used to add sales track record.
     * 
     * @param salesTrackDto
     * @throws DataAccessException
     */
    public void addSalesTrack(SalesTrackDto salesTrackDto) throws DataAccessException;

    /**
     * The method is used to get all sales track record by opportunityID.
     * 
     * @param id
     * @return type of Map<String,Object>
     * @throws DataAccessException
     */
    public Map<String, Object> getAllSalesTrack(final String id) throws DataAccessException;

    /**
     * 更新销售履历
     * 
     * @param salesTrackBean
     * @throws DataAccessException
     */
    public void updateSalesTrack(SalesTrackBean salesTrackBean) throws DataAccessException;
}
