package com.ustcsoft.gs.crm.webui.customer.dao.impl;

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

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.PagingHibernateCallback;
import com.ustcsoft.gs.crm.webui.common.util.SuperHibernateCallback;
import com.ustcsoft.gs.crm.webui.contact.dto.ContactInfoDto;
import com.ustcsoft.gs.crm.webui.customer.bean.ContactInfoBean;
import com.ustcsoft.gs.crm.webui.customer.bean.OrderBean;
import com.ustcsoft.gs.crm.webui.customer.bean.OrderSearchBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.customer.dao.OrderDao;
import com.ustcsoft.gs.crm.webui.customer.dto.ContractDto;
import com.ustcsoft.gs.crm.webui.customer.dto.OrderDto;
import com.ustcsoft.gs.crm.webui.customer.dto.OrderTrackDto;
import com.ustcsoft.gs.crm.webui.customer.dto.SelectProductDto;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesEventDto;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesEventFlowCodeDto;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesEventFlowDto;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesTrackDto;

/**
 * @author liangchengcheng
 */
public class OrderDaoImpl implements OrderDao {

    /**
     * output log information
     */
    private static final Log LOG = LogFactory.getLog(OrderDaoImpl.class);

    /**
     * hibernateTemplate
     */
    private HibernateTemplate hibernateTemplate;

    /**
     * @return the hibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    /**
     * @param hibernateTemplate
     *            the hibernateTemplate to set
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @SuppressWarnings("unchecked")
    public List<OrderBean> getListForPage(final String hql, final Object[] params, final int start,
            final int limit) {
        List<OrderBean> list = hibernateTemplate.executeFind(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) {
                Query query = session.createQuery(hql);
                int i = 0;
                for (Object param : params) {
                    query.setParameter(i, param);
                    i++;
                }
                query.setFirstResult(start);
                query.setMaxResults(limit);
                List<OrderBean> list = query.list();
                return list;
            }
        });
        return list;
    }

    /**
     * The method is used to search record by condition given.
     * 
     * @param searchFlag
     *            query mode
     * @param searchValue
     *            query condition
     * @param currPage
     *            return page
     * @param limit
     * @param type
     * @return searching's result
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    @SuppressWarnings({ "unchecked" })
    public Map<String, Object> queryOrder(final int searchFlag, final OrderSearchBean searchValue,
            final int currPage, final int limit, String type) throws DataAccessException {
        LOG.debug("method getAllOrderSearch start!");
        List<OrderBean> result = null;
        long total = 0;
        Map<String, Object> map = new HashMap<String, Object>();
        // super query
        if (searchFlag == 2) {
            if ("0".equals(type)) {
                List<Object> paramList = new ArrayList<Object>();
                String search_HQL = CustomerConstant.SELECT_ORDER_SUPERQUERY_HQL
                        + " and ord.type = 0 ";
                String count_HQL = "select count(*) from OrderDto as ord where ord.type = 0 and ord.isAbolished = 0 ";
                if (!searchValue.getCustomerIDValue().isEmpty()) {
                    search_HQL += CustomerConstant.ORDER_CUSTOMERNAME_SEARCH;
                    count_HQL += CustomerConstant.ORDER_CUSTOMERNAME_SEARCH;
                    paramList.add("%" + searchValue.getCustomerIDValue() + "%");
                }
                if (!searchValue.getOrderIDValue().isEmpty()) {
                    search_HQL += CustomerConstant.ORDER_ORDERID_SEARCH;
                    count_HQL += CustomerConstant.ORDER_ORDERID_SEARCH;
                    paramList.add("%" + searchValue.getOrderIDValue() + "%");
                }
                if (!searchValue.getTransactionPriceValue().isEmpty()) {
                    search_HQL += CustomerConstant.ORDER_TRANSACTION_SEARCH;
                    count_HQL += CustomerConstant.ORDER_TRANSACTION_SEARCH;
                    paramList.add(searchValue.getTransactionPriceValue());
                }
                if (!searchValue.getOurRepresentativeValue().isEmpty()) {
                    search_HQL += CustomerConstant.ORDER_OURREPRESENTATIVE_SEARCH;
                    count_HQL += CustomerConstant.ORDER_OURREPRESENTATIVE_SEARCH;
                    paramList.add("%" + searchValue.getOurRepresentativeValue() + "%");
                }
                Object[] valueObject = paramList.toArray();
                result = getListForPage(search_HQL, valueObject, (currPage - 1) * limit, limit);

                List<Integer> list = hibernateTemplate.find(count_HQL, valueObject);
                Number number = list.get(0);
                total = number.intValue();
            } else {
                List<Object> paramList = new ArrayList<Object>();
                String search_HQL = CustomerConstant.SELECT_ORDER_SUPERQUERY_HQL
                        + "and ord.type = 1 ";
                String count_HQL = "select count(*) from OrderDto as ord where ord.type = 1 and ord.isAbolished = 0 ";
                if (!searchValue.getCustomerIDValue().isEmpty()) {
                    search_HQL += CustomerConstant.ORDER_CUSTOMERNAME_SEARCH;// INTENTORDER_SUPERQUERY_HQL;
                    count_HQL += CustomerConstant.ORDER_CUSTOMERNAME_SEARCH;
                    paramList.add(searchValue.getCustomerIDValue());
                }
                if (!searchValue.getOrderIDValue().isEmpty()) {
                    search_HQL += CustomerConstant.ORDER_ORDERID_SEARCH;
                    count_HQL += CustomerConstant.ORDER_ORDERID_SEARCH;
                    paramList.add(searchValue.getOrderIDValue());
                }
                if (!searchValue.getTransactionPriceValue().isEmpty()) {
                    search_HQL += CustomerConstant.ORDER_TRANSACTION_SEARCH;
                    count_HQL += CustomerConstant.ORDER_TRANSACTION_SEARCH;
                    paramList.add(searchValue.getTransactionPriceValue());
                }
                if (!searchValue.getOurRepresentativeValue().isEmpty()) {
                    search_HQL += CustomerConstant.ORDER_OURREPRESENTATIVE_SEARCH;
                    count_HQL += CustomerConstant.ORDER_OURREPRESENTATIVE_SEARCH;
                    paramList.add(searchValue.getOurRepresentativeValue());
                }
                Object[] valueObject = paramList.toArray();
                result = getListForPage(search_HQL, valueObject, (currPage - 1) * limit, limit);

                List<Integer> list = hibernateTemplate.find(count_HQL, valueObject);
                Number number = list.get(0);
                total = number.intValue();
            }
        }
        if (searchFlag == 1) {
            if ("0".equals(type)) {
                result = hibernateTemplate
                        .executeFind(new SuperHibernateCallback(
                                CustomerConstant.INTENTORDER_SIMPLEQUERY_HQL, currPage,
                                searchValue, limit));
                total = (Long) hibernateTemplate.executeFind(
                        new SuperHibernateCallback(CustomerConstant.INTENTORDER_SIMPLECOUNT_HQL, 0,
                                searchValue, 0)).get(0);
            } else {
                result = hibernateTemplate.executeFind(new SuperHibernateCallback(
                        CustomerConstant.ORDER_SIMPLEQUERY_HQL, currPage, searchValue, limit));
                total = (Long) hibernateTemplate.executeFind(
                        new SuperHibernateCallback(CustomerConstant.ORDER_SIMPLECOUNT_HQL, 0,
                                searchValue, 0)).get(0);
            }
        }
        map.put(CRMConstant.ITEMS, result);
        map.put(CRMConstant.TOTAL, total);
        LOG.debug("method getAllOrderSearch end!");
        return map;
    }

    /**
     * The method is used to get all order
     * 
     * @param currPage
     * @param limit
     * @param userID
     * @param type
     * @return map query results to display
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getAllOrder(final int currPage, final int limit,
            final Integer[] userID, String type) throws DataAccessException {
        LOG.debug("method getAllOrder start!");
        if (type.equals("0")) {
            Map<String, Object> map = new HashMap<String, Object>();
            List<OrderBean> orderList = hibernateTemplate.executeFind(new PagingHibernateCallback(
                    CustomerConstant.INTENTORDER_HQL_LIST, currPage, CustomerConstant.USER_ID,
                    userID, limit));
            map.put(CRMConstant.ITEMS, orderList);
            map.put(CRMConstant.TOTAL, getOrderSize(CustomerConstant.INTENTORDER_COUNT_HQL, userID));
            LOG.debug("method getAllOrder end!");
            return map;
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            List<OrderBean> orderList = hibernateTemplate.executeFind(new PagingHibernateCallback(
                    CustomerConstant.ORDER_HQL_LIST, currPage, CustomerConstant.USER_ID, userID,
                    limit));
            map.put(CRMConstant.ITEMS, orderList);
            map.put(CRMConstant.TOTAL, getOrderSize(CustomerConstant.ORDER_COUNT_HQL, userID));
            LOG.debug("method getAllOrder end!");
            return map;
        }
    }

    /**
     * The method is used to get the number of records.
     * 
     * @param queryString
     * @param userID
     * @return the size of order
     */
    @Override
    public long getOrderSize(final String queryString, final Integer[] userID) {
        LOG.debug("method getOrderSize start!");
        List<?> result = hibernateTemplate.findByNamedParam(queryString, CRMConstant.USER_ID,
                userID);
        LOG.debug("method getOrderSize end!");
        return (Long) result.get(0);
    }

    /**
     * The method is used to delete record.
     * 
     * @param orderIds
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public void deleteOrder(final String orderIds) throws DataAccessException {
        LOG.debug("method deleteOrder start!");
        hibernateTemplate.bulkUpdate(CustomerConstant.ORDER_DEL_HQL + orderIds);
        hibernateTemplate.bulkUpdate(CustomerConstant.ORDERTRACT_DEL_HQL + orderIds);
        LOG.debug("method deleteOrder end!");
    }

    /**
     * The method is used to add or update record.
     * 
     * @param orderDto
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public void addOrUpdateOrder(OrderDto orderDto) throws DataAccessException {
        LOG.debug("method addOrUpdateOrder start!");
        hibernateTemplate.saveOrUpdate(orderDto);
        LOG.debug("method addOrUpdateOrder end!");
    }

    /**
     * this method will update table "SalesEventFlow"
     * 
     * @param salesEventFlowDtos
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    @Override
    public void updateSalesEventFlow(List<SalesEventFlowDto> salesEventFlowDtos)
            throws DataAccessException {
        LOG.debug("method updateSalesEventFlow start!");
        if (salesEventFlowDtos.size() != 0) {
            int eventID = salesEventFlowDtos.get(0).getEventID();
            List<SalesEventFlowDto> sEventFlowDtos = hibernateTemplate.findByNamedParam(
                    CustomerConstant.SELECT_ALL_SALESEVENTFLOW_INFO, CustomerConstant.EVENT_ID,
                    eventID);
            int flag = 0;
            if (sEventFlowDtos.size() == 0) {
                hibernateTemplate.saveOrUpdateAll(salesEventFlowDtos);
            } else {
                for (int i = 0; i < salesEventFlowDtos.size(); i++) {
                    for (int j = 0; j < sEventFlowDtos.size(); j++) {
                        if (salesEventFlowDtos.get(i).getStatus() == sEventFlowDtos.get(j)
                                .getStatus()) {
                            flag = 1;
                            sEventFlowDtos.get(j).setDemand(salesEventFlowDtos.get(i).getDemand());
                            hibernateTemplate.update(sEventFlowDtos.get(j));
                        }
                    }
                    if (flag == 0) {
                        hibernateTemplate.save(salesEventFlowDtos.get(i));
                    }
                    flag = 0;
                }
            }
        }
        LOG.debug("method updateSalesEventFlow end!");
    }

    /**
     * this method will update table "SalesEventInfo"
     * 
     * @param salesEventDto
     * @throws DataAccessException
     */
    @Override
    public void updateSalesEventInfo(SalesEventDto salesEventDto) throws DataAccessException {
        LOG.debug("method updateSalesEventInfo start!");
        if (salesEventDto != null) {
            final Map<String, Object> map = new HashMap<String, Object>();
            final String hql = CustomerConstant.UPDATE_EVENT_STATUS;
            map.put("submitterID", salesEventDto.getSubmitterID());
            map.put("submitDate", salesEventDto.getSubmitDate());
            map.put("status", salesEventDto.getStatus());
            map.put("eventID", salesEventDto.getEventID());
            hibernateTemplate.execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) {
                    Query query = session.createQuery(hql);
                    query.setProperties(map);
                    return query.executeUpdate();
                }
            });
        }
        LOG.debug("method updateSalesEventInfo end!");
    }

    /**
     * this method will update table "salesTrackInfo"
     * 
     * @param salesTrackDtos
     * @throws DataAccessException
     */
    @Override
    @SuppressWarnings("unchecked")
    public void updateSalesTrackInfo(List<SalesTrackDto> salesTrackDtos) throws DataAccessException {
        LOG.debug("method updateSalesTrackInfo start!");
        if (salesTrackDtos.size() != 0) {
            int eventID = salesTrackDtos.get(0).getEventID();
            List<SalesTrackDto> sTrackDtos = hibernateTemplate.findByNamedParam(
                    CustomerConstant.SELECT_SALESTRACK_BY_EVENTID, CustomerConstant.EVENT_ID,
                    eventID);
            int flag = 0;
            if (sTrackDtos.size() == 0) {
                hibernateTemplate.saveOrUpdateAll(salesTrackDtos);
            } else {
                for (int i = 0; i < salesTrackDtos.size(); i++) {
                    for (int j = 0; j < sTrackDtos.size(); j++) {
                        if (salesTrackDtos.get(i).getStatus().equals(sTrackDtos.get(j).getStatus())) {
                            flag = 1;
                        }
                    }
                    if (flag == 0) {
                        hibernateTemplate.save(salesTrackDtos.get(i));
                    }
                    flag = 0;
                }
            }
        }
        LOG.debug("method updateSalesTrackInfo end!");
    }

    /**
     * @param salesEventDto
     * @throws DataAccessException
     */
    @Override
    public void changeStatus(SalesEventDto salesEventDto) throws DataAccessException {
        LOG.debug("method changeStatus start!");
        final Map<String, Object> map = new HashMap<String, Object>();
        final String hql = CustomerConstant.UPDATE_EVENT_STATUS;
        int code = Integer.parseInt(salesEventDto.getStatus());
        @SuppressWarnings("unchecked")
        List<Object> sortList = hibernateTemplate.findByNamedParam(
                CustomerConstant.GET_NEXT_SORT_TO_CHANGE, CustomerConstant.EVENT_FLOW_CODE, code);
        if (sortList.size() != 0) {
            int sort = Integer.parseInt(sortList.get(0).toString());
            // sort++;
            @SuppressWarnings("unchecked")
            List<Object> codeList = hibernateTemplate.findByNamedParam(
                    CustomerConstant.GET_NEXT_CODE_TO_CHANGE, CustomerConstant.EVENT_FLOW_SORT,
                    sort);
            if (codeList.size() != 0) {
                String status = codeList.get(0).toString();
                map.put("status", status);
                map.put("eventID", salesEventDto.getEventID());
                hibernateTemplate.execute(new HibernateCallback<Object>() {
                    @Override
                    public Object doInHibernate(Session session) {
                        Query query = session.createQuery(hql);
                        query.setProperties(map);
                        return query.executeUpdate();
                    }
                });
            }
        }

        LOG.debug("method changeStatus end!");
    }

    /**
     * The method is used to add ordertrack record
     * 
     * @param orderTrackDto
     *            order track information
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public void addOrderTrack(OrderTrackDto orderTrackDto) throws DataAccessException {
        LOG.debug("method addOrderTrack start!");
        hibernateTemplate.save(orderTrackDto);
        LOG.debug("method addOrderTrack end!");
    }

    /**
     * The method is used to get orderState
     * 
     * @param id
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * @return the status of the order
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<String> getOrderState(final String orderID) throws DataAccessException {
        LOG.debug("method getOrderState start!");
        List<String> list = hibernateTemplate.findByNamedParam(
                CustomerConstant.ORDERTRACT_ORDERSTATE, CustomerConstant.ORDER_ID, orderID);
        LOG.debug("method getOrderState end!");
        return list;
    }

    /**
     * The method is used to get all order track record by orderId
     * 
     * @param orderID
     *            orderId
     * @return searching's result
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getAllOrderTrack(final String orderID) throws DataAccessException {
        LOG.debug("method getAllOrderTrack start!");
        Map<String, Object> map = new HashMap<String, Object>();
        String sqlString = CustomerConstant.ORDERTRACK_HQL;
        List<OrderTrackDto> orderTrackList = hibernateTemplate.find(sqlString, orderID);
        map.put(CRMConstant.ITEMS, orderTrackList);
        LOG.debug("method getAllOrderTrack end!");
        return map;
    }

    /**
     * The method is used to get all contact
     * 
     * @return searching's result
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getCustomerContact(int customerID)
            throws DataAccessException {
        LOG.debug("method getCustomerContact start!");
        Map<String, Object> map = new HashMap<String, Object>();
        List<ContactInfoBean> contactInfoList = null;
        if (customerID == 0) {
            contactInfoList = hibernateTemplate
                    .find(CustomerConstant.GET_CONTACT_HQL);
        } else {
            contactInfoList = hibernateTemplate.findByNamedParam(
                    CustomerConstant.GET_CONTACT_HQL_2, "customerID",
                    customerID);
        }
        map.put(CRMConstant.ITEMS, contactInfoList);
        LOG.debug("method getCustomerContact end!");
        return map;
    }

    /**
     * The method is used to judge orderID whether repeat or not.
     * 
     * @param orderID
     * @return orderIDRepeat query results to judge
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public int judgeOrderRepeat(final String orderID) throws DataAccessException {
        LOG.debug("method queryOrderRepeat start!");
        List<?> orderIDRepeat = hibernateTemplate.find(CustomerConstant.OEDER_ORDERIDREPEAT
                + orderID + CRMConstant.SINGLEQUOTE);
        LOG.debug("method queryOrderRepeat end!");
        return orderIDRepeat.size();
    }

    /**
     * The method is used to change the intent order to order
     * 
     * @param orderIds
     * @throws DataAccessException
     */
    @Override
    public void changeToOrder(String orderIds) throws DataAccessException {
        LOG.debug("method changeToOrder start!");
        hibernateTemplate.bulkUpdate(CustomerConstant.INTENTORDER_TO_ORDER + orderIds);
        LOG.debug("method changeToOrder end!");
    }

    /**
     * The method is used to select the product
     * 
     * @param selectProductDtos
     * @throws DataAccessException
     */
    @Override
    public void addProductInfo(List<SelectProductDto> selectProductDtos) throws DataAccessException {
        LOG.debug("method addProductInfo start!");
        if (selectProductDtos != null && selectProductDtos.size() > 0) {
            hibernateTemplate.bulkUpdate(CustomerConstant.ORDER_DEL_PRODUCT,
                    selectProductDtos.get(0).getOrderID());
            hibernateTemplate.saveOrUpdateAll(selectProductDtos);
        }
        LOG.debug("method addProductInfo end!");
    }

    /**
     * The method is used to get the product's store
     * 
     * @param page
     * @param limit
     * @param userID
     * @param orderID
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> buyProduct(int page, int limit, int userID, String orderID) {
        LOG.debug("method buyProduct start!");
        Map<String, Object> map = new HashMap<String, Object>();
        List<SelectProductDto> selectProductDtos = hibernateTemplate.find(
                CustomerConstant.GET_BUY_PRODUCT, orderID);
        map.put(CRMConstant.ITEMS, selectProductDtos);
        LOG.debug("method buyProduct end!");
        return map;
    }

    /**
     * @param orderID
     * @return the eventID
     * @throws DataAccessException
     */
    @Override
    public int getOrderEventID(int id) throws DataAccessException {
        LOG.debug("method getOrderEventID start!");
        int eventID;
        @SuppressWarnings("unchecked")
        List<OrderDto> list = hibernateTemplate.find(CustomerConstant.SELECT_ORDER_EVENT_ID, id);
        if (list.size() == 0) {
            eventID = 0;
        } else {
            eventID = list.get(0).getEventID();
        }
        LOG.debug("method getOrderEventID end!");
        return eventID;
    }

    /**
     * @param contactID
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    @Override
    public String getContactNumber(String contactID) throws DataAccessException {
        LOG.debug("method getContactNumber start!");
        String numString = null;
        int id = Integer.parseInt(contactID);
        List<ContactInfoDto> list = hibernateTemplate
                .find(CustomerConstant.SELECT_CONTACT_INFO, id);
        numString = list.get(0).getPhoneNumber();
        LOG.debug("method getContactNumber end!");
        return numString;
    }

    /**
     * @param status
     * @param currPage
     * @return order
     * @throws DataAccessException
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getOrderByStatus(int orderId, int currPage, int limit)
            throws DataAccessException {
        LOG.debug("method getOrderByStatus start!");
        Map<String, Object> map = new HashMap<String, Object>();
        List<OrderBean> result = null;
        ContractDto contract = new ContractDto();
        contract.setOrderID(orderId);
        result = hibernateTemplate.executeFind(new SuperHibernateCallback(
                CustomerConstant.SELECT_ORDER_BYSTATUS, currPage, contract, limit));
        long total = (Long) hibernateTemplate.executeFind(
                new SuperHibernateCallback(CustomerConstant.SELECT_COUNT_BYSTATUS, 0, contract, 0))
                .get(0);
        map.put(CRMConstant.ITEMS, result);
        map.put(CRMConstant.TOTAL, total);
        LOG.debug("method getOrderByStatus end!");
        return map;
    }

    /**
     * @param CustomerID
     * @param status
     * @return the eventList
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getEventByCustomer(String CustomerID, String status)
            throws DataAccessException {
        LOG.debug("method getEventByCustomer start!");
        int customer = Integer.parseInt(CustomerID);

        Map<String, Object> map = new HashMap<String, Object>();

        List<SalesEventFlowCodeDto> salseCodes = hibernateTemplate
                .find("from SalesEventFlowCodeDto as salesFlow where salesFlow.category = ? order by salesFlow.sort desc",
                        status);
        int code = 0;
        if (salseCodes.size() > 0) {
            code = salseCodes.get(0).getCode();
        }
        List<SalesEventDto> list = hibernateTemplate.find(CustomerConstant.SELECT_EVENT_BYCUSTOMER,
                new Object[] { code + "", customer });
        map.put(CRMConstant.ITEMS, list);
        LOG.debug("method getEventByCustomer end!");
        return map;
    }

    /**
     * @param id
     * @return the status
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    @Override
    public int findStage(String jsonString) throws DataAccessException {
        LOG.debug("method findStage start!");
        // GET_MAX_STATUS
        List<SalesEventFlowCodeDto> list = null;
        list = hibernateTemplate.find(CustomerConstant.GET_MAX_STATUS, jsonString);
        int status = 0;
        if (list != null && list.size() > 0) {
            status = list.get(0).getSort();
        }
        LOG.debug("method findStage end!");
        return status;
    }

    @Override
    public Map<String, Object> getCodeStore(String id) throws DataAccessException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getPersonalDiscount(String userID, String productID) throws DataAccessException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public OrderDto getOrderByID(int orderID) {
        @SuppressWarnings("unchecked")
        List<OrderDto> orderDtos = hibernateTemplate.find(
                "from OrderDto order where order.id = ? and order.isAbolished =false ", orderID);
        OrderDto orderDto = null;
        if (orderDtos.size() > 0) {
            orderDto = orderDtos.get(0);
        }
        return orderDto;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ustcsoft.gs.crm.webui.customer.dao.OrderDao#getOrderEventDamands(int,
     * java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getOrderEventDamands(int eventID, String typeString) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<SalesEventDto> salesEventDtos = hibernateTemplate
                .find("from SalesEventDto sal where sal.eventID = ? and sal.isAbolished = false",
                        eventID);
        List<SalesEventFlowDto> salesEventFlowDtos;
        int status = Integer.parseInt(salesEventDtos.get(0).getStatus());
        SalesEventFlowCodeDto currentStateDto = hibernateTemplate.get(SalesEventFlowCodeDto.class,
                status);
        String hqlString = "";
        if ("0".equals(typeString)) {
            hqlString = "from SalesEventFlowDto flow where flow.eventID= ? and flow.status in (select salesCode.code from SalesEventFlowCodeDto as salesCode where salesCode.category = '00040002' and salesCode.sort <= ?)";
            // hqlString =
            // "from SalesEventFlowDto flow where flow.eventID= ? and flow.status <= ? and flow.status in (select code from SalesEventFlowCodeDto where category = '00040002') and flow.sort <= (select sort from SalesEventFlowCodeDto where category = '2004002' and status =?)";
        } else if ("1".equals(typeString)) {
            hqlString = "from SalesEventFlowDto flow where flow.eventID= ? and flow.status in (select salesCode.code from SalesEventFlowCodeDto as salesCode where salesCode.category = '00040003' and salesCode.sort <= ?)";
        } else {
            return null;
        }
        salesEventFlowDtos = hibernateTemplate.find(hqlString, new Object[] { eventID,
                currentStateDto.getSort() });
        map.put("items", salesEventFlowDtos);
        return map;
    }

    /**
     * 
     * @param eventID
     * @return the eventIsAbolished
     * @throws CRMDBException
     */
    @Override
    @SuppressWarnings("unchecked")
    public String eventIsAbolished(int eventID) {
        String result = "";
        List<SalesEventDto> sales = hibernateTemplate.find(
                "from SalesEventDto as event where event.eventID = ? and event.isAbolished = 0",
                eventID);
        List<SalesEventFlowCodeDto> flows = hibernateTemplate
                .find("from SalesEventDto as event where event.eventID = ? and event.status in ( select sfcode.code from SalesEventFlowCodeDto as sfcode where sfcode.category = '00040002' and sort = "
                        + "(select max(sfcode.sort) from sfcode where sfcode.category = '00040002'))",
                        eventID);
        if (sales.size() == 0) {
            result = "0";
        } else if (flows.size() == 0) {
            result = "1";
        } else {
            result = "2";
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ustcsoft.gs.crm.webui.customer.dao.OrderDao#checkContract(java.lang
     * .String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Integer> checkContract(String deleteIDString) {
        LOG.debug("method checkOrder start!");
        List<Integer> deleteIDs = hibernateTemplate
                .find("select distinct contract.orderID from ContractDto contract where contract.isAbolished = 0 and contract.orderID in "
                        + deleteIDString);
        LOG.debug("method checkOrder end!");
        return deleteIDs;
    }
}