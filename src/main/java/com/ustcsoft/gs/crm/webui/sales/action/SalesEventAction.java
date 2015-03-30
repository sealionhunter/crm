/*
 * SalesEventAction.java
 */
package com.ustcsoft.gs.crm.webui.sales.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.customer.service.CustomerTransferService;
import com.ustcsoft.gs.crm.webui.sales.bean.SalesEventBean;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesEventDto;
import com.ustcsoft.gs.crm.webui.sales.service.SalesEventService;

/**
 * 
 * @author chenguangzhou and shiben
 * 
 */
public class SalesEventAction extends CRMAction {
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 注入salesEventService
     */
    private SalesEventService salesEventService;
    private CustomerTransferService transferService = null;
    /**
     * log
     */
    private static final Log LOG = LogFactory.getLog(SalesEventAction.class);

    /**
     * 开始
     */
    private int start;

    /**
     * 销售事件id
     */
    private int eventID = 0;

    /**
     * 更新标示
     */
    private String flag = null;

    /**
     * 下一阶段
     */
    private String nextStatus = null;

    /**
     * salesEventBean实体类
     */
    private SalesEventBean salesEventBean = null;

    /**
     * salesEventDto实体类
     */
    private SalesEventDto salesEventDto = null;

    /**
     * 错误信息MAP
     */
    private Map<String, Object> errMap = new HashMap<String, Object>();

    /**
     * tab信息MAP
     */
    private Map<String, Object> tabMap = new HashMap<String, Object>();

    /**
     * 验证输入的信息
     * 
     * @throws CRMDBException
     */
    public void validateAddOrUpdateEvent() throws CRMDBException {
        salesEventDto = (SalesEventDto) CRMUtils.jsonToBean(jsonString, SalesEventDto.class);
        clearErrorsAndMessages();
        if ("".equals(salesEventDto.getEventName())) {
            addFieldError("eventName", this.getText("eventName.invalid"));
        } else if (salesEventDto.getEventName().length() > 50) {
            addFieldError("eventName", this.getText("eventNameLength.invalid"));
        } else if (salesEventService.judgeSalesEventName(salesEventDto) != 0) {
            addFieldError("eventName", this.getText("eventNameExist.invalid"));
        }
        if (salesEventDto.getCustomerID() == 0) {
            addFieldError("customerID", this.getText("customerID.invalid"));
        }
        if ("".equals(salesEventDto.getStatus())) {
            addFieldError("status", this.getText("status.invalid"));
        }
        errMap.clear();
        if (!getFieldErrors().isEmpty()) {
            errMap.putAll(getFieldErrors());
            errMap.put("validate", false);
        }
    }

    /**
     * 获得所有的销售信息
     * 
     * @return SUCCESS
     * @throws CRMDBException
     */
    @Override
    public String execute() throws CRMDBException {
        LOG.debug("action start to get sales event");
        Map<String, Object> userMap = transferService.getUser(0, 0, userID);
        map = salesEventService.getAllEvents(start, limit, userMap);
        LOG.debug("action end to get sales event");
        return SUCCESS;
    }

    /***
     * 删除销售事件处理
     * 
     * @return SUCCESS 处理结果
     * @throws CRMDBException
     */
    public String deleteEvents() throws CRMDBException {
        LOG.debug("action start to delete events");
        map = salesEventService.deleteEvent(jsonString);
        LOG.debug("action end to delete events");
        return SUCCESS;
    }

    /**
     * 添加或修改销售事件
     * 
     * @return SUCCESS
     * @throws CRMDBException
     */
    public String addOrUpdateEvent() throws CRMDBException {
        LOG.debug("action start to addOrUpdateevents");
        salesEventService.addOrUpdateEvent(salesEventDto, flag, nextStatus);
        LOG.debug("action end to addOrUpdate events");
        return SUCCESS;
    }

    /**
     * 获得所有的tabpanel信息
     * 
     * @return type of String
     * @throws CRMDBException
     */
    public String getTabInfo() throws CRMDBException {
        LOG.debug("action start to getTabInfo");
        tabMap = salesEventService.getTabs(eventID);
        LOG.debug("action end to getTabInfo");
        return SUCCESS;
    }

    /**
     * 查询该销售事件对应的订单是否存在
     * 
     * @return SUCCESS
     * @throws CRMDBException
     */
    public String orderIsExist() throws CRMDBException {
        LOG.debug("action start to orderIsExist");
        boolean isExist = salesEventService.judgeFindIntentionOrder(eventID);
        map.put("isExist", isExist);
        LOG.debug("action end to orderIsExist");
        return SUCCESS;
    }

    public String findEventInfoById() throws CRMDBException {
        LOG.debug("action start to findEventInfoById");
        SalesEventDto salseEvent = salesEventService.findEventInfoById(eventID);
        map.put("item", salseEvent);
        LOG.debug("action end to findEventInfoById");
        return SUCCESS;
    }

    /**
     * eventID的get（）方法
     * 
     * @return eventID
     */
    @Override
    public int getEventID() {
        return eventID;
    }

    /**
     * eventID的set（）方法
     * 
     * @param eventID
     */
    @Override
    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    /**
     * salesEventService的get（）方法
     * 
     * @return salesEventService
     */
    public SalesEventService getSalesEventService() {
        return salesEventService;
    }

    /**
     * salesEventService的set（）方法
     * 
     * @param salesEventService
     */
    public void setSalesEventService(SalesEventService salesEventService) {
        this.salesEventService = salesEventService;
    }

    /**
     * start的get（）方法
     * 
     * @return start
     */
    public int getStart() {
        return start;
    }

    /**
     * start的set（）方法
     * 
     * @param start
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * salesEventBean的get（）方法
     * 
     * @return salesEventBean
     */
    public SalesEventBean getSalesEventBean() {
        return salesEventBean;
    }

    /**
     * salesEventBean的set（）方法
     * 
     * @param salesEventBean
     */
    public void setSalesEventBean(SalesEventBean salesEventBean) {
        this.salesEventBean = salesEventBean;
    }

    /**
     * salesEventDto的get（）方法
     * 
     * @return salesEventDto
     */
    public SalesEventDto getSalesEventDto() {
        return salesEventDto;
    }

    /**
     * salesEventDto的set（）方法
     * 
     * @param salesEventDto
     */
    public void setSalesEventDto(SalesEventDto salesEventDto) {
        this.salesEventDto = salesEventDto;
    }

    /**
     * flag的get（）方法
     * 
     * @return flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * flag的set（）方法
     * 
     * @param flag
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Map<String, Object> getErrMap() {
        return errMap;
    }

    public void setErrMap(Map<String, Object> errMap) {
        this.errMap = errMap;
    }

    public Map<String, Object> getTabMap() {
        return tabMap;
    }

    public void setTabMap(Map<String, Object> tabMap) {
        this.tabMap = tabMap;
    }

    public String getNextStatus() {
        return nextStatus;
    }

    public void setNextStatus(String nextStatus) {
        this.nextStatus = nextStatus;
    }

    public CustomerTransferService getTransferService() {
        return transferService;
    }

    public void setTransferService(CustomerTransferService transferService) {
        this.transferService = transferService;
    }
}
