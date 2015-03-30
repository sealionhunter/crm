/*
 * SalesEventFlowCodeDao.java
 */
package com.ustcsoft.gs.crm.webui.sales.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.sales.bean.SalesEventFlowCodeBean;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesEventFlowCodeDto;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesTrackDto;

/**
 * 事件流程各个阶段管理dao接口层
 * 
 * @author shiben
 * 
 */
public interface SalesEventFlowCodeDao {

    /**
     * 获得所有销售事件流程
     * 
     * @return List<SalesEventFlowCodeBean>
     * @throws DataAccessException
     */
    public List<SalesEventFlowCodeBean> findSalesEventFlowCode() throws DataAccessException;

    /**
     * 添加或修改销售事件流程
     * 
     * @param salesEventFlowCodeDto
     * @throws DataAccessException
     */
    public void addOrUpdateCode(SalesEventFlowCodeDto salesEventFlowCodeDto)
            throws DataAccessException;

    /**
     * 修改sort变为sort+1
     * 
     * @param sort
     * @throws DataAccessException
     */
    public void updateSortByPlus(int sort) throws DataAccessException;

    /**
     * 删除销售事件流程check
     * 
     * @param salesEventFlowCodeDtos
     * @return type of Map<String, Object>
     */
    public List<SalesTrackDto> checkDeleteCode();

    /**
     * 删除销售事件流程
     * 
     * @param salesEventFlowCodeDto
     * @throws DataAccessException
     */
    public void deleteCode(SalesEventFlowCodeDto salesEventFlowCodeDto) throws DataAccessException;

    /**
     * 修改sort变为sort-1
     * 
     * @param sort
     * @throws DataAccessException
     */
    public void updateSortByMinus(int code) throws DataAccessException;

    /**
     * 判断销售事件流程名称是否存在
     * 
     * @param name
     * @return int
     * @throws DataAccessException
     */
    public long judgeEventNameRepeat(SalesEventFlowCodeDto salesEventFlowCodeDto)
            throws DataAccessException;

    /**
     * 删除销售流程阶段的需求
     * 
     * @param status
     * @throws DataAccessException
     */
    public void deleteEventFlowDemand(int status) throws DataAccessException;

    /**
     * 
     * @param status
     * @return long
     * @throws DataAccessException
     */
    public long findCountSalesEventByStatus(String status) throws DataAccessException;

    /**
     * 
     * @return map
     * @throws DataAccessException
     */
    public Map<String, Object> findSalesEventFlowCategory() throws DataAccessException;

    public List<SalesEventFlowCodeBean> getCodeByCategory(String category)
            throws DataAccessException;

    public void updateSortFromMaxToMin(int maxSort, int minSort) throws DataAccessException;

    public void updateSortFromMinToMax(int maxSort, int minSort) throws DataAccessException;
}
