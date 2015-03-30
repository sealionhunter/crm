/*
 * SalesEventSearchService.java
 */
package com.ustcsoft.gs.crm.webui.sales.service;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.sales.bean.SalesSuperSearchBean;

/**
 * 
 * @author chenguangzhou and shiben
 * 
 */
public interface SalesEventSearchService {
    /***
     * 普通搜索
     * 
     * @param start
     *            分页数据读取起始点
     * @param pageSize
     *            分页大小
     * @param searchKey
     *            搜索关键字
     * @return map 包含查询到的数据总数以及当前页所需数据
     * @throws CRMDBException
     */
    public Map<String, Object> search(int start, int pageSize, String searchKey,
            Map<String, Object> userMap) throws CRMDBException;

    /***
     * 高级检索
     * 
     * @param start
     *            分页数据读取起始点
     * @param pageSize
     *            分页大小
     * @param salesSuperSearchBean
     *            包含高级搜索检索信息的bean
     * @return map 高级检索返回结果，包含检索到的数据总数以及当前页所需数据
     * @throws CRMDBException
     */
    public Map<String, Object> advancedSearch(int start, int pageSize,
            SalesSuperSearchBean salesSuperSearchBean, Map<String, Object> userMap)
            throws CRMDBException;
}
