package com.ustcsoft.gs.crm.webui.customer.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.customer.bean.OrderBean;
import com.ustcsoft.gs.crm.webui.customer.bean.OrderSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dao.OrderDao;
import com.ustcsoft.gs.crm.webui.customer.dto.OrderDto;
import com.ustcsoft.gs.crm.webui.customer.dto.OrderTrackDto;
import com.ustcsoft.gs.crm.webui.customer.dto.SelectProductDto;
import com.ustcsoft.gs.crm.webui.customer.service.OrderService;
import com.ustcsoft.gs.crm.webui.customer.service.SourceInfoService;
import com.ustcsoft.gs.crm.webui.sales.dao.SalesEventDao;
import com.ustcsoft.gs.crm.webui.sales.dao.SalesTrackDao;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesEventDto;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesEventFlowDto;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesTrackDto;

/**
 * @author liangchengcheng
 */
public class OrderServiceImpl implements OrderService {

    /**
     * output log information
     */
    private static final Log LOG = LogFactory.getLog(OrderServiceImpl.class);

    /**
     * receive DAO
     */
    private OrderDao orderDao = null;

    private OrderDto orderDto = null;

    private OrderTrackDto orderTrackDto = null;

    private List<SalesTrackDto> salesTrackDtos = null;

    private SalesEventDto salesEventDto = null;

    private List<SelectProductDto> selectProductDtos = null;

    private List<SalesEventFlowDto> salesEventFlowDtos = null;

    private SalesEventDao salesEventDao = null;
    /**
     * receive the orderDao
     */
    private SourceInfoService sourceInfoService = null;

    private int eventID = 0;

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    /**
     * get the orderDao
     * 
     * @return orderDao
     */
    @Override
    public OrderDao getOrderDao() {
        return orderDao;
    }

    /**
     * the orderDao to set
     * 
     * @param orderDao
     * 
     */
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public SalesEventDao getSalesEventDao() {
        return salesEventDao;
    }

    public void setSalesEventDao(SalesEventDao salesEventDao) {
        this.salesEventDao = salesEventDao;
    }

    public OrderDto getOrderDto() {
        return orderDto;
    }

    public void setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
    }

    public SalesEventDto getSalesEventDto() {
        return salesEventDto;
    }

    public void setSalesEventDto(SalesEventDto salesEventDto) {
        this.salesEventDto = salesEventDto;
    }

    public List<SelectProductDto> getSelectProductDtos() {
        return selectProductDtos;
    }

    public void setSelectProductDtos(List<SelectProductDto> selectProductDtos) {
        this.selectProductDtos = selectProductDtos;
    }

    public List<SalesEventFlowDto> getSalesEventFlowDtos() {
        return salesEventFlowDtos;
    }

    public void setSalesEventFlowDtos(List<SalesEventFlowDto> salesEventFlowDtos) {
        this.salesEventFlowDtos = salesEventFlowDtos;
    }

    public void setOrderTrackDto(OrderTrackDto orderTrackDto) {
        this.orderTrackDto = orderTrackDto;
    }

    public List<SalesTrackDto> getSalesTrackDtos() {
        return salesTrackDtos;
    }

    public void setSalesTrackDao(SalesTrackDao salesTrackDao) {
    }

    public void setSalesTrackDtos(List<SalesTrackDto> salesTrackDtos) {
        this.salesTrackDtos = salesTrackDtos;
    }

    /**
     * @return the sourceInfoService
     */
    public SourceInfoService getSourceInfoService() {
        return sourceInfoService;
    }

    /**
     * @param sourceInfoService
     *            the sourceInfoService to set
     */
    public void setSourceInfoService(SourceInfoService sourceInfoService) {
        this.sourceInfoService = sourceInfoService;
    }

    /**
     * The method is used to get all order by given condition
     * 
     * @param searchFlag
     *            query mode
     * @param searchValue
     *            query condition
     * @param currPage
     *            assigned page
     * @param limit
     * @param type
     * @param userID
     * @return map query results to display
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public Map<String, Object> searchOrGetAllOrder(final int searchFlag,
            OrderSearchBean searchValue, final int currPage, final int limit, final int userID,
            String type) throws CRMDBException {
        LOG.debug("method searchOrGetAllOrder start!");
        Map<String, Object> map = null;
        Integer[] userIDs = sourceInfoService.getUserID(userID);
        try {
            if (searchFlag == 0) {
                // load order from database.
                map = orderDao.getAllOrder(currPage, limit, userIDs, type);
            } else {
                // query order
                searchValue.setUserID(userIDs);
                map = orderDao.queryOrder(searchFlag, searchValue, currPage, limit, type);
            }
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getCustomer!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method searchOrGetAllOrder end!");
        return map;
    }

    /**
     * The method is used to delete record.
     * 
     * @param orderIds
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Map<String, Object> deleteOrder(String orderIds) throws CRMDBException {
        LOG.debug("method deleteOrder start!");
        Map<String, Object> map = new HashMap<String, Object>();
        String[] deleteIdStrings = orderIds.split(",");
        try {
            List<Integer> deleteIDs = orderDao.checkContract(CRMUtils.getStringForDelete(orderIds));
            if (deleteIDs.size() == 0) {
                orderDao.deleteOrder(CRMUtils.getStringForDelete(orderIds));
            } else {
                String errorString = "";
                outFor: for (int i = 0; i < deleteIdStrings.length; i++) {
                    for (Integer integer : deleteIDs) {
                        if (Integer.parseInt(deleteIdStrings[i].trim()) == integer) {
                            errorString = errorString + orderDao.getOrderByID(integer).getOrderID();
                            break outFor;
                        }
                    }
                }
                map.put("errorMessage", errorString + "正在被合同模块使用，无法删除！");
            }
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getCustomer!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method deleteOrder end!");
        return map;
    }

    /**
     * The method is used to add or update record.
     * 
     * @param orderDto
     * @param userID
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public void addOrUpdateOrder(OrderBean orderBean, int userID) throws CRMDBException {
        LOG.debug("method addOrUpdateOrder start!");
        orderDto = orderBean.getOrderDto();
        selectProductDtos = orderBean.getSelectProductDtos();
        salesEventFlowDtos = orderBean.getSalesEventFlowDtos();
        salesEventDto = new SalesEventDto();
        if (salesEventFlowDtos.size() != 0) {
            salesEventDto.setStatus(""
                    + salesEventFlowDtos.get(salesEventFlowDtos.size() - 1).getStatus());
        }
        salesEventDto.setEventID(orderDto.getEventID());
        try {
            List<String> orderIDList = orderDao.getOrderState(orderDto.getOrderID());
            orderTrackDto = initOrderTrackDto(orderDto, userID);
            if (orderIDList.size() != 0) {
                String orderState = orderDao.getOrderState(orderDto.getOrderID()).get(0).toString();
                orderDao.addOrUpdateOrder(orderDto);
                // 判断状态是否改变 to do====
                if (!orderState.equals(orderTrackDto.getAfterState())) {
                    orderDao.addOrderTrack(orderTrackDto);
                }
            } else {
                orderDao.addOrUpdateOrder(orderDto);
                orderDao.addOrderTrack(orderTrackDto);
            }
            orderDao.addProductInfo(selectProductDtos);
            if (orderDto.getEventID() != 0) {
                salesTrackDtos = new ArrayList<SalesTrackDto>();
                salesTrackDtos = initSalesTrackDtos(orderDto, salesEventFlowDtos, userID);
                orderDao.updateSalesEventFlow(salesEventFlowDtos);
                orderDao.changeStatus(salesEventDto);
                orderDao.updateSalesTrackInfo(salesTrackDtos);
            }
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getCustomer!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method addOrUpdateOrder end!");
    }

    /**
     * The method is used to get all order track by orderId
     * 
     * @param orderID
     * @return map query results to display
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public Map<String, Object> getAllOrderTrack(String orderID) throws CRMDBException {
        LOG.debug("method getAllOrderTrack start!");
        Map<String, Object> map = null;
        try {
            map = orderDao.getAllOrderTrack(orderID);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getCustomer!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getAllOrderTrack end.");
        return map;
    }

    /**
     * The method is used to get all customer contact.
     * 
     * @return map query results to display
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public Map<String, Object> getCustomerContact(int customerID) throws CRMDBException {
        LOG.debug("method getCustomerContact start!");
        Map<String, Object> map = null;
        try {
            map = orderDao.getCustomerContact(customerID);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getCustomer!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getCustomerContact end!");
        return map;
    }

    /**
     * The method is used to judge orderID whether repeat or not.
     * 
     * @param orderDto
     * @return orderIDRepeat query results to judge
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public int judgeOrderRepeat(OrderDto orderDto) throws CRMDBException {
        LOG.debug("method queryOrderRepeat start!");
        int orderIDRepeat = 0;
        try {
            int id = orderDto.getId();
            if (id == 0) {
                orderIDRepeat = orderDao.judgeOrderRepeat(orderDto.getOrderID());
            }
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method judgeOrderRepeat!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method queryOrderRepeat end!");
        return orderIDRepeat;
    }

    /**
     * the method is used to change the intent order to order
     * 
     * @param orderIds
     * 
     */
    @Override
    public void changeToOrder(OrderBean orderBean, int userID) throws CRMDBException {
        LOG.debug("method changeToOrder start!");
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(now);
        try {
            if (orderBean.getOrderDto().getId() != 0) {
                // 将意向订单改为订单
                orderBean.getOrderDto().setDeliveryDate(date);
                orderBean.getOrderDto().setType(1);
                orderDao.addOrUpdateOrder(orderBean.getOrderDto());
                // 添加订单追踪
                OrderTrackDto orderTrackDto = new OrderTrackDto();
                orderTrackDto.setEditPeople(userID);
                orderTrackDto.setOrderID(orderBean.getOrderDto().getOrderID());
                orderTrackDto.setRecordTime(date);
                orderTrackDto.setAfterState(orderBean.getOrderDto().getOrderState());
                // orderDao.addOrderTrack(orderTrackDto);
                // 修改销售事件流程,若eventID不等于0则表明绑定销售事件
                if (orderBean.getOrderDto().getEventID() != 0) {
                    SalesEventDto salesEventDto = new SalesEventDto();
                    salesEventDto.setEventID(orderBean.getOrderDto().getEventID());
                    int maxIndex = orderBean.getSalesEventFlowDtos().size() - 1;
                    int nowStatus = orderBean.getSalesEventFlowDtos().get(maxIndex).getStatus();
                    salesEventDto.setStatus(String.valueOf(nowStatus));
                    orderDao.changeStatus(salesEventDto);
                    salesTrackDtos = initSalesTrackDtos(orderBean.getOrderDto(),
                            orderBean.getSalesEventFlowDtos(), userID);
                    orderDao.updateSalesEventFlow(orderBean.getSalesEventFlowDtos());
                    orderDao.updateSalesTrackInfo(salesTrackDtos);
                }
            }
            // orderDao.changeToOrder(CRMUtils.getStringForDelete(orderIds));
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method changeToOrder!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method changeToOrder end!");
    }

    /**
     * the method is used to add product
     * 
     * @param selectProductDtos
     * 
     * @throws CRMDBException
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void addProductInfo(List<SelectProductDto> selectProductDtos) throws CRMDBException {
        LOG.debug("method addProductInfo start!");
        try {
            orderDao.addProductInfo(selectProductDtos);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method addProductInfo!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method addProductInfo end!");
    }

    /**
     * The method is used to add the product you want to buy
     * 
     * @param page
     * @param limit
     * @param userID
     * @param orderID
     * @throws CRMDBException
     * @return the product you want to buy
     */
    @Override
    public Map<String, Object> buyProduct(int page, int limit, int userID, String orderID)
            throws CRMDBException {
        LOG.debug("method getbuyProduct start!");
        Map<String, Object> map = null;
        try {
            map = orderDao.buyProduct(page, limit, userID, orderID);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method buyProduct!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getbuyProduct end!");
        return map;
    }

    /**
     * @param contactID
     * @throws CRMDBException
     */
    @Override
    public String getContactNumber(String contactID) throws CRMDBException {
        LOG.debug("method getContactNumber start!");
        String numString = orderDao.getContactNumber(contactID);
        LOG.debug("method getContactNumber end!");
        return numString;
    }

    /**
     * 
     * @param orderId
     * @param currPage
     * @param limit
     * @return the order
     * @throws CRMDBException
     */
    @Override
    public Map<String, Object> getOrderByStatus(int orderId, final int currPage, final int limit)
            throws CRMDBException {
        LOG.debug("method getOrderByStatus start!");
        Map<String, Object> map = null;
        try {
            map = orderDao.getOrderByStatus(orderId, currPage, limit);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method buyProduct!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getOrderByStatus end!");
        return map;
    }

    /**
     * @param customerID
     * @param status
     * @return event
     * @throws CRMDBException
     */
    @Override
    public Map<String, Object> getEventByCustomer(String customerID, String status)
            throws CRMDBException {
        LOG.debug("method getEventByCustomer start!");
        Map<String, Object> map = null;
        try {
            map = orderDao.getEventByCustomer(customerID, status);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method buyProduct!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getEventByCustomer end!");
        return map;
    }

    @Override
    public int findStatus(String jsonString) throws CRMDBException {
        LOG.debug("method findStatus start!");
        int status = orderDao.findStage(jsonString);
        LOG.debug("method findStatus end!");
        return status;
    }

    // public SalesTrackDto initSalesTrackDto(OrderDto orderDto,int userID){
    // salesTrackDto = new SalesTrackDto();
    // salesTrackDto.setCustomerID(orderDto.getCustomerID());
    // salesTrackDto.setEventID(orderDto.getEventID());
    // salesTrackDto.setIsAbolished(false);
    // salesTrackDto.setStatus(salesEventFlowDtos.get(0).getStatus()+"");
    // salesTrackDto.setSubmitDate(this.formatDate());
    // salesTrackDto.setSubmitterID(userID);
    // return salesTrackDto;
    // }
    public OrderTrackDto initOrderTrackDto(OrderDto orderDto, int userID) {
        orderTrackDto = new OrderTrackDto();
        orderTrackDto.setAfterState(orderDto.getOrderState());
        orderTrackDto.setEditPeople(userID);
        orderTrackDto.setOrderID(orderDto.getOrderID());
        orderTrackDto.setRecordTime(formatDate());
        orderTrackDto.setIsAbolished(false);
        return orderTrackDto;
    }

    public List<SalesTrackDto> initSalesTrackDtos(OrderDto orderDto,
            List<SalesEventFlowDto> salesEventFlowDtos, int userID) {
        List<SalesTrackDto> salesTrackDtos = new ArrayList<SalesTrackDto>();
        for (SalesEventFlowDto salesEventFlowDto : salesEventFlowDtos) {
            SalesTrackDto salesTrackDto = new SalesTrackDto();
            salesTrackDto.setSubmitterID(userID);
            salesTrackDto.setEventID(orderDto.getEventID());
            salesTrackDto.setCustomerID(orderDto.getCustomerID());
            salesTrackDto.setStatus(String.valueOf(salesEventFlowDto.getStatus()));
            salesTrackDto.setSubmitDate(formatDate());
            salesTrackDto.setIsAbolished(false);
            salesTrackDtos.add(salesTrackDto);
        }
        return salesTrackDtos;
    }

    public String formatDate() {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = formatter.format(now);
        return date;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void addOrUpdateFormalOrder(OrderBean orderBean, int userID) throws CRMDBException {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = formatter.format(now);
        // 添加
        if (orderBean.getOrderDto().getId() == 0) {
            // 添加订单
            orderBean.getOrderDto().setType(1);
            /*
             * if(orderBean.getSalesEventFlowDtos().size()>0){ int nowState =
             * orderBean.getSalesEventFlowDtos().get(0).getStatus();
             * 
             * }
             */
            orderDao.addOrUpdateOrder(orderBean.getOrderDto());
            // 添加订单追踪
            OrderTrackDto orderTrackDto = new OrderTrackDto();
            orderTrackDto.setEditPeople(userID);
            orderTrackDto.setOrderID(orderBean.getOrderDto().getOrderID());
            orderTrackDto.setRecordTime(date);
            orderTrackDto.setAfterState(orderBean.getOrderDto().getOrderState());
            orderDao.addOrderTrack(orderTrackDto);
            // 添加产品订单
            orderDao.addProductInfo(orderBean.getSelectProductDtos());
            // 修改销售事件流程,若eventID不等于0则表明绑定销售事件
            if (orderBean.getOrderDto().getEventID() != 0) {
                // 如果list的size大于0且不为空，说明没有修改销售事件阶段的需求
                if (orderBean.getSalesEventFlowDtos().size() > 0) {
                    SalesEventDto salesEventDto = new SalesEventDto();
                    salesEventDto.setEventID(orderBean.getOrderDto().getEventID());
                    int maxIndex = orderBean.getSalesEventFlowDtos().size() - 1;
                    int nowStatus = orderBean.getSalesEventFlowDtos().get(maxIndex).getStatus();
                    salesEventDto.setStatus(String.valueOf(nowStatus));
                    orderDao.changeStatus(salesEventDto);
                    salesTrackDtos = initSalesTrackDtos(orderBean.getOrderDto(),
                            orderBean.getSalesEventFlowDtos(), userID);
                    orderDao.updateSalesEventFlow(orderBean.getSalesEventFlowDtos());
                    orderDao.updateSalesTrackInfo(salesTrackDtos);
                }
            }
        } else {
            String orderID = orderBean.getOrderDto().getOrderID();
            List<String> orderStatusList = orderDao.getOrderState(orderID);
            // list不为空且size大于0
            if (orderStatusList != null && orderStatusList.size() > 0) {
                String oldOrderStatusString = orderStatusList.get(0).trim();
                String newOrderStatusString = orderBean.getOrderDto().getOrderState().trim();
                // orderState改变，添加订单履历
                if (!oldOrderStatusString.equals(newOrderStatusString)) {
                    OrderTrackDto orderTrackDto = new OrderTrackDto();
                    orderTrackDto.setEditPeople(userID);
                    orderTrackDto.setOrderID(orderBean.getOrderDto().getOrderID());
                    orderTrackDto.setRecordTime(date);
                    orderTrackDto.setAfterState(orderBean.getOrderDto().getOrderState());
                    orderDao.addOrderTrack(orderTrackDto);
                }
            }
            // orderBean.getOrderDto().setDeliveryDate(date);
            orderBean.getOrderDto().setType(1);
            // 修改订单
            orderDao.addOrUpdateOrder(orderBean.getOrderDto());
            // 修改或添加产品
            orderDao.addProductInfo(orderBean.getSelectProductDtos());
            // 修改销售事件个阶段需求
            // 修改销售事件流程,若eventID不等于0则表明绑定销售事件
            if (orderBean.getOrderDto().getEventID() != 0) {
                // 如果list的size大于0且不为空，说明没有修改销售事件阶段的需求
                if (orderBean.getSalesEventFlowDtos().size() > 0) {
                    SalesEventDto salesEventDto = new SalesEventDto();
                    salesEventDto.setEventID(orderBean.getOrderDto().getEventID());
                    int maxIndex = orderBean.getSalesEventFlowDtos().size() - 1;
                    int nowStatus = orderBean.getSalesEventFlowDtos().get(maxIndex).getStatus();
                    salesEventDto.setStatus(String.valueOf(nowStatus));
                    orderDao.changeStatus(salesEventDto);

                    salesTrackDtos = initSalesTrackDtos(orderBean.getOrderDto(),
                            orderBean.getSalesEventFlowDtos(), userID);
                    orderDao.updateSalesTrackInfo(salesTrackDtos);
                    orderDao.updateSalesEventFlow(orderBean.getSalesEventFlowDtos());
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ustcsoft.gs.crm.webui.customer.service.OrderService#getOrderEventDamands
     * (int, java.lang.String)
     */
    @Override
    public Map<String, Object> getOrderEventDamands(int eventID, String typeString) {
        return orderDao.getOrderEventDamands(eventID, typeString);
    }

    /**
     * 
     * @param eventID
     * @return the eventIsAbolished
     * @throws CRMDBException
     */
    @Override
    public String eventIsAbolished(int eventID) throws CRMDBException {
        return orderDao.eventIsAbolished(eventID);
    }

}