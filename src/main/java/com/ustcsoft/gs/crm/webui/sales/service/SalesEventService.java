/*
 * SalesEventService.java
 */
package com.ustcsoft.gs.crm.webui.sales.service;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesEventDto;

/**
 * 
 * @author chenguangzhou and shiben
 * 
 */
public interface SalesEventService {
    /***
     * 加载销售事件列表时获取所有销售事件
     * 
     * @param start
     *            分页数据读取起始点
     * @param pageSize
     *            分页大小
     * @return map 包含当前页面所需数据以及数据总数
     * @throws CRMDBException
     */
    public Map<String, Object> getAllEvents(int start, int pageSize, Map<String, Object> userMap)
            throws CRMDBException;

    /***
     * 删除选择的销售事件
     * 
     * @param salesEventIDs
     *            将要删除的销售事件ID
     * @throws CRMDBException
     */
    public Map<String, Object> deleteEvent(String salesEventIDs) throws CRMDBException;

    /***
     * 更新销售事件信息
     * 
     * @param salesEventDto
     *            更新的信息
     * @param flag
     * @throws CRMDBException
     */
    public void addOrUpdateEvent(SalesEventDto salesEventDto, String flag, String nextStatus)
            throws CRMDBException;

    /***
     * 获取加载tab所需的信息
     * 
     * @param eventID
     *            销售事件ID，ID为0加载默认tab
     * @return map包含tab的信息
     * @throws CRMDBException
     */
    public Map<String, Object> getTabs(int eventID) throws CRMDBException;

    /**
     * 查询eventName是否存在
     * 
     * @param salesEventDto
     * @return count
     * @throws CRMDBException
     */
    public long judgeSalesEventName(SalesEventDto salesEventDto) throws CRMDBException;

    /**
     * 判断订单是否存在
     * 
     * @param eventID
     * @return boolean
     * @throws CRMDBException
     */
    public Boolean judgeFindIntentionOrder(int eventID) throws CRMDBException;

    public SalesEventDto findEventInfoById(int eventID) throws CRMDBException;
}
