/*
 * SalesEventSearchDao.java
 */
package com.ustcsoft.gs.crm.webui.sales.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.sales.bean.SalesSuperSearchBean;

/**
 * 查询DAO
 * 
 * @author chenguangzhou and shiben
 * 
 */
public interface SalesEventSearchDao {
    /**
     * 销售事件模糊检索
     * 
     * @param start
     *            分页数据读取起始点
     * @param pageSize
     *            分页大小
     * @param searchKey
     *            模糊搜索关键字
     * @return map 包含查询到的数据总数以及当前页所需数据
     * @throws DataAccessException
     */
    public Map<String, Object> search(int start, int pageSize, String searchKey,
            List<Integer> userList) throws DataAccessException;

    /**
     * 销售事件高级搜索
     * 
     * @param start
     *            分页数据读取起始点
     * @param pageSize
     *            分页大小
     * @param salesSuperSearchBean
     *            存放销售事件高级检索信息的bean
     * @return map 高级检索返回查询数据总数以及当前页数据
     * @throws DataAccessException
     */
    public Map<String, Object> advancedSearch(int start, int pageSize,
            SalesSuperSearchBean salesSuperSearchBean, List<Integer> userList)
            throws DataAccessException;
}
