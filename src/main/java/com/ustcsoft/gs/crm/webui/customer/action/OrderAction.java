package com.ustcsoft.gs.crm.webui.customer.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.customer.bean.OrderBean;
import com.ustcsoft.gs.crm.webui.customer.bean.OrderSearchBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.customer.dto.OrderDto;
import com.ustcsoft.gs.crm.webui.customer.dto.SelectProductDto;
import com.ustcsoft.gs.crm.webui.customer.service.OrderService;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesEventFlowDto;

/**
 * @author liangchengcheng
 */
public class OrderAction extends CRMAction {

    private static final long serialVersionUID = 1L;

    private static final Log LOG = LogFactory.getLog(OrderAction.class);

    Map<String, Object> session = ActionContext.getContext().getSession();

    private OrderDto orderDto = null;

    private OrderBean orderBean = null;

    private OrderService orderService = null;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @SuppressWarnings("rawtypes")
    public Map<String, Class> getClassMap() {
        return classMap;
    }

    @SuppressWarnings("rawtypes")
    public void setClassMap(Map<String, Class> classMap) {
        this.classMap = classMap;
    }

    @SuppressWarnings("rawtypes")
    private Map<String, Class> classMap = new HashMap<String, Class>();
    private String typeString = null;

    private String orderID = null;

    private String valueString = null;

    private String opStatus = null;

    private int id = 0;

    private int customerID = 0;

    protected int eventID = 0;

    /**
     * 
     * @return eventID
     */
    @Override
    public int getEventID() {
        return eventID;
    }

    /**
     * 
     * @param eventID
     */
    @Override
    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    /**
     * The method is used to validate addOrUpdateOrder method.
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void validateAddOrUpdateOrder() throws CRMDBException {
        /*
         * orderBean = (OrderBean) CRMUtils .jsonToBean(jsonString,
         * OrderBean.class); orderDto = orderBean.getOrderDto(); if
         * ("".equalsIgnoreCase(orderDto.getOrderID())) {
         * this.addFieldError("orderID", this.getText("orderID.invalid")); }
         * else if (orderDto.getOrderID().length() != 21) {
         * this.addFieldError("orderID", this.getText("orderIDLength.invalid"));
         * } else if (orderService.judgeOrderRepeat(orderDto) != 0) {
         * this.addFieldError("orderID", this.getText("orderIDExist.invalid"));
         * } if (orderDto.getCustomerID() == 0) {
         * this.addFieldError("customerID", this.getText("customerID.invalid"));
         * } if ("".equalsIgnoreCase(orderDto.getOurRepresentative())) {
         * this.addFieldError("ourRepresentative",
         * this.getText("ourRepresentative.invalid")); } else if
         * (orderDto.getOurRepresentative().length() > 50) {
         * this.addFieldError("ourRepresentative",
         * this.getText("ourRepresentativeLength.invalid")); } if
         * ("".equalsIgnoreCase(orderDto.getTransactionPrice())) {
         * this.addFieldError("transactionPrice",
         * this.getText("transactionPrice.invalid")); } else if
         * (!orderDto.getTransactionPrice().matches(
         * "(^([1-9][0-9]{0,}|0).[0-9]{0,}$)|(^[1-9][0-9]{0,}$)")) {
         * this.addFieldError("transactionPrice",
         * this.getText("transactionPriceFormat.invalid")); } if
         * ("".equalsIgnoreCase(orderDto.getDeliveryDate())) {
         * this.addFieldError("deliveryDate",
         * this.getText("deliveryDate.invalid")); } else if
         * (!CRMUtils.dateCheck(orderDto.getDeliveryDate())) {
         * this.addFieldError("deliveryDate",
         * this.getText("deliveryDateFormat.invalid")); } this.showFieldError();
         */
    }

    /**
     * The method is used to get orderList.
     * 
     * @return currentPage
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public String execute() throws CRMDBException {
        LOG.debug("method execute start!");
        String type = null;
        if ("0".equals(typeString)) {
            type = "0";
        } else {
            type = "1";
        }
        OrderSearchBean searchValue = (OrderSearchBean) CRMUtils.jsonToBean(jsonString,
                OrderSearchBean.class);
        map = orderService.searchOrGetAllOrder(searchFlag, searchValue, page, limit, userID, type);
        LOG.debug("method execute end!");
        return SUCCESS;
    }

    /**
     * default method to execute for action
     * 
     * @return success
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String getOrderTrack() throws CRMDBException {
        LOG.debug("method getOrderTrack start!");
        map = orderService.getAllOrderTrack(jsonString);
        LOG.debug("method getOrderTrack end!");
        return SUCCESS;
    }

    /**
     * The method is used to delete record.
     * 
     * @return success
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String deleteOrder() throws CRMDBException {
        LOG.debug("method deleteOrder start!");
        map = orderService.deleteOrder(jsonString);
        LOG.debug("method deleteOrder end!");
        return SUCCESS;
    }

    /**
     * the method is used to change intent order to order
     * 
     * @return success
     * @throws CRMDBException
     */
    public String changeToOrder() throws CRMDBException {
        LOG.debug("method changeToOrder start");
        classMap.put("orderDto", OrderDto.class);
        classMap.put("salesEventFlowDtos", SalesEventFlowDto.class);
        orderBean = (OrderBean) JSONObject.toBean(JSONObject.fromObject(jsonString),
                OrderBean.class, classMap);
        orderService.changeToOrder(orderBean, userID);
        LOG.debug("method changeToOrder end");
        return SUCCESS;
    }

    /**
     * The method is used to add or update record.
     * 
     * @return success
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String addOrUpdateOrder() throws CRMDBException {
        LOG.debug("method addOrUpdateOrder start!");
        classMap.put("orderDto", OrderDto.class);
        classMap.put("salesEventFlowDtos", SalesEventFlowDto.class);
        classMap.put("selectProductDtos", SelectProductDto.class);
        int userID = (Integer) session.get(CustomerConstant.USERID);
        orderBean = (OrderBean) JSONObject.toBean(JSONObject.fromObject(jsonString),
                OrderBean.class, classMap);
        orderDto = orderBean.getOrderDto();
        orderService.addOrUpdateOrder(orderBean, userID);
        LOG.debug("method addOrUpdateOrder end!");
        return SUCCESS;
    }

    /**
     * 正式订单添加修改
     */
    public String addOrUpdateFormalOrder() throws CRMDBException {
        LOG.debug("method addOrUpdateFormalOrder start!");
        classMap.put("orderDto", OrderDto.class);
        classMap.put("salesEventFlowDtos", SalesEventFlowDto.class);
        classMap.put("selectProductDtos", SelectProductDto.class);
        orderBean = (OrderBean) JSONObject.toBean(JSONObject.fromObject(jsonString),
                OrderBean.class, classMap);
        orderService.addOrUpdateFormalOrder(orderBean, userID);
        LOG.debug("method addOrUpdateFormalOrder end!");
        return SUCCESS;
    }

    /**
     * The method is used to get all customerContact.
     * 
     * @return success
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String getCustomerContact() throws CRMDBException {
        LOG.debug("method addOrUpdateOrder start!");
        map = orderService.getCustomerContact(customerID);
        LOG.debug("method addOrUpdateOrder end!");
        return SUCCESS;
    }

    /**
     * The method is used to get the product you want to buy
     * 
     * @return success
     * @throws CRMDBException
     */
    public String buyProduct() throws CRMDBException {
        LOG.debug("method execute start!");
        map = orderService.buyProduct(page, limit, userID, orderID);
        LOG.debug("method execute end!");
        return SUCCESS;
    }

    /**
     * 
     * @return the order
     * @throws CRMDBException
     */
    public String getOrderByStatus() throws CRMDBException {
        LOG.debug("method getOrderByStatus start!");
        map = orderService.getOrderByStatus(id, page, limit);
        LOG.debug("method getOrderByStatus end!");
        return SUCCESS;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @return the ContactName
     * @throws CRMDBException
     */
    public String getContactNumber() throws CRMDBException {
        LOG.debug("method getContactNumber start!");
        valueString = orderService.getContactNumber(jsonString);
        LOG.debug("method getContactNumber end!");
        return SUCCESS;
    }

    /**
     * 
     * @return the event
     * @throws CRMDBException
     */
    public String getEventByCustomer() throws CRMDBException {
        LOG.debug("method getEventByCustomer start!");
        map = orderService.getEventByCustomer(jsonString, opStatus);
        LOG.debug("method getEventByCustomer start!");
        return SUCCESS;
    }

    /**
     * 
     * @return the status
     * @throws CRMDBException
     */
    public String findStatsu() throws CRMDBException {
        LOG.debug("method findStatsu start!");
        int status = orderService.findStatus(jsonString);
        valueString = status + "";
        LOG.debug("method findStatsu start!");
        return SUCCESS;
    }

    /**
     * 
     * @return the abolished
     * @throws CRMDBException
     */
    public String eventIsAbolished() throws CRMDBException {
        LOG.debug("method eventIsAbolished start!");
        valueString = orderService.eventIsAbolished(eventID);
        LOG.debug("method eventIsAbolished start!");
        return SUCCESS;
    }

    /**
     * 
     * @return date
     */
    public String formatDate() {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(now);
        return date;
    }

    /**
     * get the orderDto
     * 
     * @return the orderDto
     */
    public OrderDto getOrderDto() {
        return orderDto;
    }

    /**
     * the orderDto to set
     * 
     * @param orderDto
     * 
     */
    public void setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
    }

    /**
     * get the orderService
     * 
     * @return the orderService
     */
    public OrderService getOrderService() {
        return orderService;
    }

    /**
     * the orderService to set
     * 
     * @param orderService
     * 
     */
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * get the typeString
     * 
     * @return the typeString
     */
    public String getTypeString() {
        return typeString;
    }

    /**
     * the typeString to set
     * 
     * @param typeString
     * 
     */
    public void setTypeString(String typeString) {
        this.typeString = typeString;
    }

    /**
     * get the orderID
     * 
     * @return the orderID
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * the orrderID to set
     * 
     * @param orderID
     * 
     */
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * get the valueString
     * 
     * @return valueString
     */
    public String getValueString() {
        return valueString;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * the valueString to set
     * 
     * @param valueString
     */
    public void setValueString(String valueString) {
        this.valueString = valueString;
    }

    /**
     * 
     * @return oppStatus
     */
    public String getOpStatus() {
        return opStatus;
    }

    /**
     * 
     * @param oppStatus
     */
    public void setOpStatus(String opStatus) {
        this.opStatus = opStatus;
    }

    public OrderBean getOrderBean() {
        return orderBean;
    }

    public void setOrderBean(OrderBean orderBean) {
        this.orderBean = orderBean;
    }

    public String getOrderEventDamands() {
        map = orderService.getOrderEventDamands(eventID, typeString);
        return SUCCESS;
    }
}