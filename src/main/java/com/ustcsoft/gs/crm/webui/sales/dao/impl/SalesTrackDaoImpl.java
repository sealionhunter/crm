/*
 * SalesDaoImpl.java
 */
package com.ustcsoft.gs.crm.webui.sales.dao.impl;

import java.util.ArrayList;
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

import com.ustcsoft.gs.crm.webui.sales.bean.SalesTrackBean;
import com.ustcsoft.gs.crm.webui.sales.constant.SalesConstant;
import com.ustcsoft.gs.crm.webui.sales.dao.SalesTrackDao;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesTrackDto;

/**
 * 销售履历
 * 
 * @author shiben
 * 
 */
public class SalesTrackDaoImpl implements SalesTrackDao {

    /**
     * log
     */
    private static final Log LOG = LogFactory.getLog(SalesTrackDaoImpl.class);

    /**
     * 注入hibernateTemplate
     */
    private HibernateTemplate hibernateTemplate;

    /**
     * 添加銷售事件履历
     * 
     * @param salesTrackDto
     * @throws DataAccessException
     */
    @Override
    public void addSalesTrack(SalesTrackDto salesTrackDto) throws DataAccessException {
        LOG.debug("SalesTrackDaoImpl method addSalesTrack start!");
        hibernateTemplate.save(salesTrackDto);
        LOG.debug("SalesTrackDaoImpl method addSalesTrack end!");
    }

    /**
     * 获得所有履历信息
     * 
     * @param id
     *            String
     * @return map
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getAllSalesTrack(String id) throws DataAccessException {
        LOG.debug("SalesTrackDaoImpl method getAllSalesTrack start!");
        Map<String, Object> map = new HashMap<String, Object>();
        final Map<String, Object> params = new HashMap<String, Object>();
        List<Object> list = new ArrayList<Object>();
        final String hql = SalesConstant.SALESTRACK_HQL;
        params.put(SalesConstant.EVENTID, Integer.valueOf(id));
        list = hibernateTemplate.executeFind(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) {
                Query query = session.createQuery(hql);
                query.setProperties(params);
                return query.list();
            }
        });
        map.put("items", list);
        LOG.debug("SalesTrackDaoImpl method getAllSalesTrack end!");
        return map;
    }

    /**
     * 更新履历信息
     * 
     * @param salesTrackBean
     * @throws DataAccessException
     */
    @Override
    public void updateSalesTrack(SalesTrackBean salesTrackBean) throws DataAccessException {
        LOG.debug("SalesTrackDaoImpl method updateSalesTrack start!");
        final Map<String, Object> params = new HashMap<String, Object>();
        final String hql = SalesConstant.UPDATESALESTRACK_HQL_STRING;
        params.put(SalesConstant.EVENTID, salesTrackBean.getEventID());
        params.put(SalesConstant.ISABOLISHED, salesTrackBean.getIsAbolished());
        params.put(SalesConstant.STATUS, salesTrackBean.getStatus());
        hibernateTemplate.execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) {
                Query query = session.createQuery(hql);
                query.setProperties(params);
                return query.executeUpdate();
            }
        });
        LOG.debug("SalesTrackDaoImpl method updateSalesTrack end!");
    }

    /**
     * hibernateTemplate的get（）方法
     * 
     * @return hibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
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
