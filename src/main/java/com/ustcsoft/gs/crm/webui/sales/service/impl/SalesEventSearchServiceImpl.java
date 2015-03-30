/*
 * SalesEventSearchServiceImpl.java
 */
package com.ustcsoft.gs.crm.webui.sales.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.sales.bean.SalesSuperSearchBean;
import com.ustcsoft.gs.crm.webui.sales.dao.SalesEventSearchDao;
import com.ustcsoft.gs.crm.webui.sales.service.SalesEventSearchService;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;

/**
 * 销售事件搜索
 * 
 * @author shiben
 * 
 */
public class SalesEventSearchServiceImpl implements SalesEventSearchService {

    /**
     * 注入salesEventSearchDao
     */
    private SalesEventSearchDao salesEventSearchDao;

    /**
     * log
     */
    private static final Log LOG = LogFactory.getLog(SalesEventSearchServiceImpl.class);

    /**
     * 简单查询
     * 
     * @param start
     * @param pageSize
     * @param searchKey
     * @return map
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> search(int start, int pageSize, String searchKey,
            Map<String, Object> userMap) throws CRMDBException {
        LOG.debug("search service start to query.");
        Map<String, Object> map = null;
        List<Integer> userList = new ArrayList<Integer>();
        try {
            List<UserInfoDto> list = (List<UserInfoDto>) userMap.get("items");
            for (UserInfoDto userInfoDto : list) {
                userList.add(userInfoDto.getUserID());
            }
            map = salesEventSearchDao.search(start, pageSize, searchKey, userList);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method search：{}", e);
            throw new CRMDBException("Error search (#" + searchKey + "#):" + e);
        }
        LOG.debug("search service end to query.");
        return map;
    }

    /**
     * 高級檢索
     * 
     * @param start
     * @param pageSize
     * @param salesSuperSearchBean
     * @return map
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> advancedSearch(int start, int pageSize,
            SalesSuperSearchBean salesSuperSearchBean, Map<String, Object> userMap)
            throws CRMDBException {
        LOG.debug("advance search start to query.");
        Map<String, Object> map = null;
        List<Integer> userList = new ArrayList<Integer>();
        try {
            List<UserInfoDto> list = (List<UserInfoDto>) userMap.get("items");
            for (UserInfoDto userInfoDto : list) {
                userList.add(userInfoDto.getUserID());
            }
            map = salesEventSearchDao.advancedSearch(start, pageSize, salesSuperSearchBean,
                    userList);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method advancedSearch：{}", e);
            throw new CRMDBException("Error advancedSearch (#"
                    + SalesSuperSearchBean.class.getName() + "#):" + e);
        }
        LOG.debug("advance search end to query.");
        return map;
    }

    /**
     * salesEventSearchDao的set（）方法
     * 
     * @param salesEventSearchDao
     */
    public void setSalesEventSearchDao(SalesEventSearchDao salesEventSearchDao) {
        this.salesEventSearchDao = salesEventSearchDao;
    }

}
