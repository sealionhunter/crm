/*
 * SalesEventDao.java
 */
package com.ustcsoft.gs.crm.webui.sales.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.dto.OrderDto;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesEventDto;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesEventFlowDto;

/**
 * 
 * @author chenguangzhou and shiben
 * 
 */
public interface SalesEventDao {
    /***
     * 获取所有销售事件
     * 
     * @param start
     *            分页数据读取起始点
     * @param pageSize
     *            分页大小
     * @return map 包括获得的数据总数以及当前页面所需数据
     * @throws DataAccessException
     */
    public Map<String, Object> getAllEvents(int start, int pageSize, List<Integer> userList)
            throws DataAccessException;

    /***
     * 删除所选的销售事件
     * 
     * @param salesEventIDs
     *            所要删除的销售事件ID
     * @throws DataAccessException
     */
    public void deleteEvents(String salesEventIDs) throws DataAccessException;

    /***
     * 更新销售事件
     * 
     * @param salesEventDto
     *            需要更新的信息
     * @throws DataAccessException
     */
    public void addOrUpdateEvent(SalesEventDto salesEventDto) throws DataAccessException;

    /***
     * 加载tab所需信息
     * 
     * @param eventID
     *            事件ID，为0时返回默认tab
     * @return 返回tab所需所有信息
     * @throws DataAccessException
     */
    public Map<String, Object> getTabs(int eventID) throws DataAccessException;

    /**
     * 添加销售履历各阶段的需求
     * 
     * @param salesEventFlowDto
     * @throws DataAccessException
     */
    public void addEventFlow(SalesEventFlowDto salesEventFlowDto) throws DataAccessException;

    /**
     * 更新销售履历各阶段的需求
     * 
     * @param salesEventFlowDto
     * @throws DataAccessException
     */
    public void updateEventFlow(SalesEventFlowDto salesEventFlowDto) throws DataAccessException;

    public boolean findEventFlowByDto(SalesEventFlowDto salesEventFlowDto)
            throws DataAccessException;

    /**
     * 判断eventName是否已经存在
     * 
     * @param eventName
     * @return count
     * @throws CRMDBException
     */
    public long judgeSalesEventName(SalesEventDto salesEventDto) throws DataAccessException;

    /**
     * 查询中标阶段的需求
     * 
     * @param eventID
     * @return type of String
     * @throws DataAccessException
     */
    public String findDemandOfDuringObjectives(int eventID) throws DataAccessException;

    /**
     * 修改中标阶段的需求
     * 
     * @param eventID
     * @param demand
     * @throws DataAccessException
     */
    public void updateDemandOfDuringObjectives(int eventID, String demand)
            throws DataAccessException;

    /**
     * 查询订单模块的第一个流程
     * 
     * @return int
     * @throws DataAccessException
     */
    public int findFristFlowStatusInOrder() throws DataAccessException;

    /**
     * 查询销售事件对应的意向订单
     * 
     * @param eventID
     * @return orderDto
     * @throws DataAccessException
     */
    public OrderDto findIntentionOrder(int eventID) throws DataAccessException;

    /**
     * 将意向订单转化为订单
     * 
     * @param orderDto
     * @throws DataAccessException
     */
    public void addOrUpdateOrder(OrderDto orderDto) throws DataAccessException;

    public SalesEventDto findEventInfoById(int eventID) throws DataAccessException;

    public Map<String, Object> deleteSalesEventCheck(String salesEventIDs)
            throws DataAccessException;
}
