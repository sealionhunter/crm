/*
 * SalesEventFlowCodeDaoImpl.java
 */
package com.ustcsoft.gs.crm.webui.sales.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.util.SuperHibernateCallback;
import com.ustcsoft.gs.crm.webui.sales.bean.SalesEventFlowCodeBean;
import com.ustcsoft.gs.crm.webui.sales.constant.SalesEventFlowConstant;
import com.ustcsoft.gs.crm.webui.sales.dao.SalesEventFlowCodeDao;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesEventFlowCodeDto;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesTrackDto;

/**
 * 事件流程管理DAO实现层
 * 
 * @author shiben
 * 
 */
public class SalesEventFlowCodeDaoImpl implements SalesEventFlowCodeDao {

    /**
     * 注入hibernateTemplate
     */
    private HibernateTemplate hibernateTemplate = null;

    /**
     * log
     */
    private static final Log LOG = LogFactory.getLog(SalesEventFlowCodeDaoImpl.class);

    /**
     * 获得所有销售事件流程
     * 
     * @return List<SalesEventFlowCodeBean>
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SalesEventFlowCodeBean> findSalesEventFlowCode() throws DataAccessException {
        LOG.debug("method findSalesEventFlowCode start.");
        List<SalesEventFlowCodeBean> list = hibernateTemplate
                .find(SalesEventFlowConstant.FIND_SALES_EVENT_FLOW_CODE);
        LOG.debug("method findSalesEventFlowCode end.");
        return list;
    }

    /**
     * 添加或修改销售事件流程
     * 
     * @param salesEventFlowCodeDto
     * @throws DataAccessException
     */
    @Override
    public void addOrUpdateCode(SalesEventFlowCodeDto salesEventFlowCodeDto)
            throws DataAccessException {
        LOG.debug("method addOrUpdateCode start.");
        hibernateTemplate.saveOrUpdate(salesEventFlowCodeDto);
        LOG.debug("method addOrUpdateCode end.");
    }

    /**
     * 修改sort变为sort+1
     * 
     * @param sort
     * @throws DataAccessException
     */
    @Override
    public void updateSortByPlus(final int sort) throws DataAccessException {
        LOG.debug("method updateSortByPlus start.");
        hibernateTemplate.execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) {
                Query query = session.createQuery(SalesEventFlowConstant.UPDATE_SORT_BY_PLUS);
                query.setParameter(0, sort);
                return query.executeUpdate();
            }
        });
        LOG.debug("method updateSortByPlus end.");
    }

    /**
     * 删除销售事件流程
     * 
     * @param salesEventFlowCodeDto
     * @throws DataAccessException
     */
    @Override
    public void deleteCode(SalesEventFlowCodeDto salesEventFlowCodeDto) throws DataAccessException {
        LOG.debug("method deleteCode start.");
        hibernateTemplate.delete(salesEventFlowCodeDto);
        LOG.debug("method deleteCode end.");
    }

    /**
     * 修改sort变为sort-1
     * 
     * @param sort
     * @throws DataAccessException
     */
    @Override
    public void updateSortByMinus(final int code) throws DataAccessException {
        LOG.debug("method updateSortByMinus start.");
        hibernateTemplate.execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) {
                Query query = session.createQuery(SalesEventFlowConstant.UPDATE_SORT_BY_MINUS);
                query.setParameter(0, code);
                return query.executeUpdate();
            }
        });
        LOG.debug("method updateSortByMinus end.");
    }

    /**
     * 判断销售事件流程名称是否存在
     * 
     * @param name
     * @return INT
     * @throws DataAccessException
     */
    @Override
    public long judgeEventNameRepeat(SalesEventFlowCodeDto salesEventFlowCodeDto)
            throws DataAccessException {
        LOG.debug("method judgeEventNameRepeat start!");
        long count = (Long) hibernateTemplate.executeFind(
                new SuperHibernateCallback(SalesEventFlowConstant.SALES_EVENT_NAME_REPEAT, 0,
                        salesEventFlowCodeDto, 0)).get(0);
        LOG.debug("method judgeEventNameRepeat end!");
        return count;
    }

    /**
     * 删除销售流程阶段的需求
     * 
     * @param status
     * @throws DataAccessException
     */
    @Override
    public void deleteEventFlowDemand(int status) throws DataAccessException {
        LOG.debug("method deleteEventFlowDemand start.");
        hibernateTemplate.bulkUpdate(SalesEventFlowConstant.DELETE_SALES_EVENT_DEMAND, status);
        LOG.debug("method deleteEventFlowDemand end.");
    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public long findCountSalesEventByStatus(String status) throws DataAccessException {
        LOG.debug("method findCountSalesEventByStatus start.");
        long count = (Long) hibernateTemplate.find(
                SalesEventFlowConstant.FIND_COUNT_SALES_EVENT_BY_STATUS, status).get(0);
        LOG.debug("method findCountSalesEventByStatus end.");
        return count;
    }

    @Override
    public Map<String, Object> findSalesEventFlowCategory() throws DataAccessException {
        LOG.debug("method findSalesEventFlowCategory start.");
        Map<String, Object> map = new HashMap<String, Object>();
        List<?> list = hibernateTemplate
                .find(SalesEventFlowConstant.FIND_SALES_EVENT_FLOW_CATEGORY);
        map.put("items", list);
        LOG.debug("method findSalesEventFlowCategory end.");
        return map;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SalesEventFlowCodeBean> getCodeByCategory(String category)
            throws DataAccessException {
        LOG.debug("method getCodeByCategory start.");
        List<SalesEventFlowCodeBean> list = hibernateTemplate.find(
                SalesEventFlowConstant.GET_CODE_BY_CATEGORY, category);
        LOG.debug("method getCodeByCategory end.");
        return list;
    }

    @Override
    public void updateSortFromMaxToMin(final int maxSort, final int minSort)
            throws DataAccessException {
        LOG.debug("method updateSortFromMaxToMin start.");
        hibernateTemplate.execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) {
                Query query = session
                        .createQuery(SalesEventFlowConstant.UPDATE_SORT_FROM_MAX_TO_MIN);
                query.setParameter(0, maxSort);
                query.setParameter(1, minSort);
                return query.executeUpdate();
            }
        });
        LOG.debug("method updateSortFromMaxToMin end.");
    }

    @Override
    public void updateSortFromMinToMax(final int maxSort, final int minSort)
            throws DataAccessException {
        LOG.debug("method getCodeByCategory start.");
        hibernateTemplate.execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) {
                Query query = session
                        .createQuery(SalesEventFlowConstant.UPDATE_SORT_FROM_MIN_TO_MAX);
                query.setParameter(0, maxSort);
                query.setParameter(1, minSort);
                return query.executeUpdate();
            }
        });
        LOG.debug("method getCodeByCategory end.");
    }

    /**
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SalesTrackDto> checkDeleteCode() {
        List<SalesTrackDto> salesTrackDtos = hibernateTemplate.find("from SalesTrackDto dto");
        return salesTrackDtos;
    }
}
