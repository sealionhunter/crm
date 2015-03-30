/*
 * SalesEventFlowCodeService.java
 */
package com.ustcsoft.gs.crm.webui.sales.service;

import java.util.List;
import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.sales.bean.SalesEventFlowCodeBean;
import com.ustcsoft.gs.crm.webui.sales.dao.SalesEventFlowCodeDao;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesEventFlowCodeDto;

/**
 * 事件流程各个阶段管理Service接口层
 * 
 * @author shiben
 * 
 */
public interface SalesEventFlowCodeService {
    SalesEventFlowCodeDao salesEventFlowCodeDao = null;

    /**
     * 获得所有销售事件流程
     * 
     * @return Map<String, Object>
     * @throws CRMDBException
     */
    public Map<String, Object> findSalesEventFlowCode() throws CRMDBException;

    /**
     * 添加或修改销售事件流程
     * 
     * @param SalesEventFlowCodeDtos
     * @param flag
     * @throws CRMDBException
     */
    public void addOrUpdateCode(List<SalesEventFlowCodeDto> SalesEventFlowCodeDtos, int flag,
            int oldSort) throws CRMDBException;

    /**
     * 删除销售事件流程
     * 
     * @param SalesEventFlowCodeDtos
     * @throws CRMDBException
     */
    public Map<String, Object> deleteCode(List<SalesEventFlowCodeDto> SalesEventFlowCodeDtos)
            throws CRMDBException;

    /**
     * 判断销售事件流程名称是否存在
     * 
     * @param salesEventFlowCodeDto
     * @param flag
     * @return INT
     * @throws CRMDBException
     */
    public long judgeEventNameRepeat(SalesEventFlowCodeDto salesEventFlowCodeDto)
            throws CRMDBException;

    /**
     * 
     * @return MAP
     * @throws CRMDBException
     */
    public Map<String, Object> findSalesEventFlowCategory() throws CRMDBException;

    /**
     * 
     * @param category
     * @return LIST
     * @throws CRMDBException
     */
    public List<SalesEventFlowCodeBean> getCodeByCategory(String category) throws CRMDBException;

    public SalesEventFlowCodeDao getSalesEventFlowCodeDao();

    public void setSalesEventFlowCodeDao(SalesEventFlowCodeDao salesEventFlowCodeDao);
}
