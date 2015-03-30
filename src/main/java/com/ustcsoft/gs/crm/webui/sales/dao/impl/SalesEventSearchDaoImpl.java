/*
 * SalesEventSearchDaoImpl.java
 */
package com.ustcsoft.gs.crm.webui.sales.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.sales.bean.SalesEventBean;
import com.ustcsoft.gs.crm.webui.sales.bean.SalesSuperSearchBean;
import com.ustcsoft.gs.crm.webui.sales.constant.Constant;
import com.ustcsoft.gs.crm.webui.sales.dao.SalesEventSearchDao;

/**
 * 销售事件的搜索
 * 
 * @author chenguangzhou and shiben
 * 
 */
public class SalesEventSearchDaoImpl implements SalesEventSearchDao {

    /**
     * 注入hibernateTemplate
     */
    private HibernateTemplate hibernateTemplate;

    /**
     * log
     */
    private static final Log LOG = LogFactory.getLog(SalesEventSearchDaoImpl.class);

    /**
     * 简单查询
     * 
     * @param start
     * @param pageSize
     * @param searchKey
     * @return map
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> search(final int start, final int pageSize, final String searchKey,
            List<Integer> userList) throws DataAccessException {
        LOG.debug("start to search sales event");
        Map<String, Object> map = new HashMap<String, Object>();
        List<SalesEventBean> list = null;
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("searchKey", "%" + searchKey.trim() + "%");
        params.put("userID", userList);
        list = hibernateTemplate.executeFind(new HibernateCallback<List<SalesEventBean>>() {
            @Override
            public List<SalesEventBean> doInHibernate(Session session) throws HibernateException,
                    SQLException {
                List<SalesEventBean> list = null;
                // 如果为空，查询所有
                if ("".equals(searchKey)) {
                    list = session.createQuery(Constant.GET_ALL_EVENTS).setProperties(params)
                            .setFirstResult(start).setMaxResults(pageSize).list();
                    return list;
                }
                String salesEventQuery = Constant.SALES_EVENT_QUERY;
                list = session.createQuery(salesEventQuery).setProperties(params)
                        .setFirstResult(start).setMaxResults(pageSize).list();
                return list;
            }
        });
        map.put("items", list);
        if ("".equals(searchKey)) {
            long total = (Long) hibernateTemplate.getSessionFactory().openSession()
                    .createQuery(Constant.COUNT_OF_EVENT).setProperties(params).list().get(0);
            map.put("total", total);
        } else {
            long total = (Long) hibernateTemplate.getSessionFactory().openSession()
                    .createQuery(Constant.COUNT_OF_QUERY).setProperties(params).list().get(0);
            map.put("total", total);
        }

        LOG.debug("end to search sales event");
        return map;
    }

    /**
     * 
     * @param start
     * @param pageSize
     * @param salesSuperSearchBean
     * @return map
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> advancedSearch(final int start, final int pageSize,
            final SalesSuperSearchBean salesSuperSearchBean, List<Integer> userList)
            throws DataAccessException {
        LOG.debug("start to super query.");
        Map<String, Object> map = new HashMap<String, Object>();
        final Map<String, Object> params = new HashMap<String, Object>();
        String query = Constant.GET_ALL_EVENTS;
        String queryCount = Constant.COUNT_OF_EVENT;
        // 客户名不为空
        if (!"".equals(salesSuperSearchBean.getCustomerName().trim())) {
            query = query + Constant.CUSTOMER_OF_SUPERQUERY;
            queryCount = queryCount + Constant.CUSTOMER_COUNT_OF_SUPERQUERY;
            params.put("customerName", "%" + salesSuperSearchBean.getCustomerName().trim() + "%");
        }
        // 提交人姓名不为空
        if (!"".equals(salesSuperSearchBean.getSubmitPersonName().trim())) {
            query = query + Constant.SUBMIT_OF_SUPERQUERY;
            queryCount = queryCount + Constant.SUBMIT_COUNT_OF_SUPERQUERY;
            params.put("realName", "%" + salesSuperSearchBean.getSubmitPersonName().trim() + "%");
        }
        // 销售事件阶段状态不为空
        if (!"0".equals(salesSuperSearchBean.getStatus().trim())) {
            query = query + Constant.STATUS_OF_SUPERQUERY;
            queryCount = queryCount + Constant.STATUS_COUNT_OF_SUPERQUERY;
            params.put("status", salesSuperSearchBean.getStatus());
        }
        // 最小时间不为空
        if (!"".equals(salesSuperSearchBean.getDateFrom().trim())) {
            query = query + Constant.DATEFROM_OF_SUPERQUERY;
            queryCount = queryCount + Constant.DATEFROM_COUNT_OF_SUPERQUERY;
            params.put("dateFrom", salesSuperSearchBean.getDateFrom().trim());
        }
        // 最大事件不为空
        if (!"".equals(salesSuperSearchBean.getDateTo().trim())) {
            query = query + Constant.DATETO_OF_SUPERQUERY;
            queryCount = queryCount + Constant.DATETO_COUNT_OF_SUPERQUERY;
            params.put("dateTo", salesSuperSearchBean.getDateTo().trim());
        }
        // 销售事件名称不为空
        if (!"".equals(salesSuperSearchBean.getEventName().trim())) {
            query += Constant.EVENT_OF_SUPERQUERY;
            queryCount += Constant.EVENT_OF_SUPERQUERY;
            params.put("eventName", "%" + salesSuperSearchBean.getEventName().trim() + "%");
        }
        params.put("userID", userList);
        final String queryString = query;
        List<SalesEventBean> list = hibernateTemplate
                .executeFind(new HibernateCallback<List<SalesEventBean>>() {
                    @Override
                    public List<SalesEventBean> doInHibernate(Session session) {
                        List<SalesEventBean> list = session.createQuery(queryString)
                                .setProperties(params).setFirstResult(start)
                                .setMaxResults(pageSize).list();
                        return list;
                    }
                });
        map.put("items", list);
        long total = (Long) hibernateTemplate.getSessionFactory().openSession()
                .createQuery(queryCount).setProperties(params).list().get(0);
        map.put("total", total);
        LOG.debug("end to super query.");
        return map;
    }

    /**
     * hibernateTemplate的set（）方法
     * 
     * @param hibernateTemplate
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
}
