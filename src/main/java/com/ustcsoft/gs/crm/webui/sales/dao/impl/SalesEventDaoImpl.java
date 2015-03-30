/*
 * SalesEventDaoImpl.java
 */
package com.ustcsoft.gs.crm.webui.sales.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.util.SuperHibernateCallback;
import com.ustcsoft.gs.crm.webui.customer.dto.OrderDto;
import com.ustcsoft.gs.crm.webui.sales.bean.SalesEventBean;
import com.ustcsoft.gs.crm.webui.sales.bean.TabBean;
import com.ustcsoft.gs.crm.webui.sales.constant.Constant;
import com.ustcsoft.gs.crm.webui.sales.dao.SalesEventDao;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesEventDto;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesEventFlowDto;

/**
 * 销售事件管理
 * 
 * @author shiben
 * 
 */
public class SalesEventDaoImpl implements SalesEventDao {

    /**
     * 注入hibernateTemplate
     */
    private HibernateTemplate hibernateTemplate;

    /**
     * log
     */
    private static final Log LOG = LogFactory.getLog(SalesEventDaoImpl.class);

    /**
     * 得到所有的销售事件
     * 
     * @param start
     * @param pageSize
     * @return map
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getAllEvents(final int start, final int pageSize,
            List<Integer> userList) throws DataAccessException {
        LOG.debug("start to get all sales event");
        Map<String, Object> map = new HashMap<String, Object>();
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("userID", userList);
        List<SalesEventBean> list = new ArrayList<SalesEventBean>();
        list = hibernateTemplate.executeFind(new HibernateCallback<List<SalesEventBean>>() {
            @Override
            public List<SalesEventBean> doInHibernate(Session session) throws HibernateException,
                    SQLException {
                List<SalesEventBean> result = session.createQuery(Constant.GET_ALL_EVENTS)
                        .setProperties(params).setFirstResult(start).setMaxResults(pageSize).list();
                return result;
            }
        });
        map.put("items", list);
        long total = (Long) hibernateTemplate.findByNamedParam(Constant.COUNT_OF_EVENT, "userID",
                userList).get(0);
        map.put("total", total);
        LOG.debug("end to get all sales event");
        return map;
    }

    /**
     * 删除销售事件
     * 
     * @param salesEventIDs
     *            String
     * @throws DataAccessException
     */
    @Override
    public void deleteEvents(String salesEventIDs) throws DataAccessException {
        LOG.debug("start to delete events");
        String deleteIDs = "(" + salesEventIDs + ")";
        hibernateTemplate.bulkUpdate(Constant.DELETE_SALESEVENTS + deleteIDs);
        hibernateTemplate.bulkUpdate(Constant.DELETE_SALESEVENTS_DEMAND + deleteIDs);
        hibernateTemplate.bulkUpdate(Constant.DELETE_SALESEVENTS_TRACK + deleteIDs);
        LOG.debug("end to delete events");
    }

    /**
     * 添加或修改销售事件
     * 
     * @param salesEventDto
     *            SalesEventDto
     * @throws DataAccessException
     */
    @Override
    public void addOrUpdateEvent(SalesEventDto salesEventDto) throws DataAccessException {
        LOG.debug("start to addOrUpdateEvent");
        hibernateTemplate.saveOrUpdate(salesEventDto);
        LOG.debug("end to addOrUpdateEvent");
    }

    /**
     * 获得所有tabpanel显示
     * 
     * @param eventID
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getTabs(int eventID) throws DataAccessException {
        LOG.debug("start to get tabs info");
        Map<String, Object> map = new HashMap<String, Object>();
        List<TabBean> list = null;
        if (eventID == 0) {
            list = hibernateTemplate.getSessionFactory().openSession()
                    .createQuery(Constant.GET_DEFUALT_TAB).list();
        } else {
            list = hibernateTemplate.find(Constant.GET_ALL_SALES_DEMAND, eventID);
        }
        map.put("items", list);
        List<String> list1 = hibernateTemplate.find(Constant.FIND_STATUS_BY_EVENTID, eventID);
        if (list1.size() > 0 && list1 != null) {
            map.put("currentStatusName", list1.get(0));
        }
        LOG.debug("end to get tabs info");
        return map;
    }

    /**
     * 添加各銷售階段的需求
     * 
     * @param salesEventFlowDto
     * @throws DataAccessException
     */
    @Override
    public void addEventFlow(SalesEventFlowDto salesEventFlowDto) throws DataAccessException {
        LOG.debug("start to addEventFlow");
        hibernateTemplate.save(salesEventFlowDto);
        LOG.debug("end to addEventFlow");

    }

    /**
     * 更新各銷售階段的需求
     * 
     * @param salesEventFlowDto
     * @throws DataAccessException
     */
    @Override
    public void updateEventFlow(SalesEventFlowDto salesEventFlowDto) throws DataAccessException {
        LOG.debug("start to updateEventFlow");
        final Map<String, Object> params = new HashMap<String, Object>();
        final String hql = Constant.UPDATE_SALES_EVENT_FLOW;
        params.put("demand", salesEventFlowDto.getDemand());
        params.put("eventID", salesEventFlowDto.getEventID());
        params.put("status", salesEventFlowDto.getStatus());
        hibernateTemplate.execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) {
                Query query = session.createQuery(hql);
                query.setProperties(params);
                return query.executeUpdate();
            }
        });
        LOG.debug("end to updateEventFlow");
    }

    /**
     * 判斷eventName是否已經存在
     * 
     * @param eventName
     *            eventName
     * @throws DataAccessException
     * @return count
     */
    @Override
    public long judgeSalesEventName(SalesEventDto salesEventDto) throws DataAccessException {
        LOG.debug("start to judgeSalesEventName");
        long count = (Long) hibernateTemplate.executeFind(
                new SuperHibernateCallback(Constant.JUDGE_SALES_EVENT_NAME, 0, salesEventDto, 0))
                .get(0);
        LOG.debug("end to judgeSalesEventName");
        return count;
    }

    /**
     * 查询中标阶段的需求
     * 
     * @param eventID
     * @throws DataAccessException
     * @return String
     */
    @SuppressWarnings("unchecked")
    @Override
    public String findDemandOfDuringObjectives(int eventID) throws DataAccessException {
        LOG.debug("start to findDemandOfDuringObjectives");
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("eventID", eventID);
        List<String> list = hibernateTemplate.executeFind(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) {
                Query query = session.createQuery(Constant.FIND_DEMAND_OF_DURING_OBJECTIVES);
                query.setProperties(params);
                return query.list();
            }
        });
        LOG.debug("end to findDemandOfDuringObjectives");
        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    /**
     * 修改中标阶段的需求
     * 
     * @param eventID
     * @param demand
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    @Override
    public void updateDemandOfDuringObjectives(int eventID, String demand)
            throws DataAccessException {
        LOG.debug("start to updateDemandOfDuringObjectives");
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("eventID", eventID);
        params.put("demand", demand);
        List<String> list = hibernateTemplate.findByNamedParam(Constant.FIND_EVENT_FLOW_BY_DTO,
                new String[] { "eventID", "status" }, new Object[] { eventID, 3 });
        if (list != null && list.size() > 0) {
            hibernateTemplate.execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) {
                    Query query = session.createQuery(Constant.UPDATE_DEMAND_OF_DURING_OBJECTIVES);
                    query.setProperties(params);
                    return query.executeUpdate();
                }
            });
        } else {
            SalesEventFlowDto salesEventFlowDto = new SalesEventFlowDto();
            salesEventFlowDto.setDemand(demand);
            salesEventFlowDto.setEventID(eventID);
            salesEventFlowDto.setStatus(3);
            hibernateTemplate.save(salesEventFlowDto);
        }
        hibernateTemplate.bulkUpdate(Constant.UPDATE_STATUS_OF_DURING_OBJECTIVES, eventID);
        LOG.debug("end to updateDemandOfDuringObjectives");
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean findEventFlowByDto(SalesEventFlowDto salesEventFlowDto)
            throws DataAccessException {
        LOG.debug("start to findDemandOfDuringObjectives");
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("eventID", salesEventFlowDto.getEventID());
        params.put("status", salesEventFlowDto.getStatus());
        List<String> list = hibernateTemplate.executeFind(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) {
                Query query = session.createQuery(Constant.FIND_EVENT_FLOW_BY_DTO);
                query.setProperties(params);
                return query.list();
            }
        });
        LOG.debug("end to findDemandOfDuringObjectives");
        return list != null && list.size() > 0 ? true : false;
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

    @SuppressWarnings("unchecked")
    @Override
    public int findFristFlowStatusInOrder() throws DataAccessException {
        LOG.debug("start to findFristFlowStatusInOrder");
        List<Integer> list = hibernateTemplate.find(Constant.FIND_FRIST_FLOW_STATUS_IN_ORDER);
        LOG.debug("end to findFristFlowStatusInOrder");
        return list != null && list.size() > 0 ? list.get(0) : 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public OrderDto findIntentionOrder(int eventID) throws DataAccessException {
        LOG.debug("start to findIntentionOrder");
        List<OrderDto> list = hibernateTemplate.find(Constant.FIND_INTENTION_ORDER, eventID);
        LOG.debug("end to findIntentionOrder");
        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public void addOrUpdateOrder(OrderDto orderDto) throws DataAccessException {
        LOG.debug("start to findIntentionOrder");
        hibernateTemplate.saveOrUpdate(orderDto);
        LOG.debug("end to findIntentionOrder");
    }

    @Override
    public SalesEventDto findEventInfoById(int eventID) throws DataAccessException {
        return hibernateTemplate.get(SalesEventDto.class, eventID);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> deleteSalesEventCheck(String salesEventIDs)
            throws DataAccessException {
        LOG.debug("start to deleteSalesEventCheck");
        String deleteIDs = "(" + salesEventIDs + ")";
        Map<String, Object> map = new HashMap<String, Object>();
        // 客户联系
        List<String> contactList = hibernateTemplate.find(Constant.DELETE_CHECK_CONTACT_TRACK_INFO
                + deleteIDs);
        List<String> intentOrderList = hibernateTemplate.find(Constant.DELETE_CHECK_INTENT_ORDER
                + deleteIDs);
        List<String> orderList = hibernateTemplate.find(Constant.DELETE_CHECK_ORDER + deleteIDs);
        map.put("contactItems", contactList);
        map.put("intentOrderItems", intentOrderList);
        map.put("orderItems", orderList);
        LOG.debug("end to deleteSalesEventCheck");
        return map;
    }

}
