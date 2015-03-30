package com.ustcsoft.gs.crm.webui.sales.service.impl;

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
import com.ustcsoft.gs.crm.webui.customer.dto.OrderDto;
import com.ustcsoft.gs.crm.webui.sales.bean.SalesTrackBean;
import com.ustcsoft.gs.crm.webui.sales.dao.SalesEventDao;
import com.ustcsoft.gs.crm.webui.sales.dao.SalesTrackDao;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesEventDto;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesEventFlowDto;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesTrackDto;
import com.ustcsoft.gs.crm.webui.sales.service.SalesEventService;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;

public class SalesEventServiceImpl implements SalesEventService {

    /**
     * 注入salesEventDao
     */
    private SalesEventDao salesEventDao;

    /**
     * 注入salesTrackDao
     */
    private SalesTrackDao salesTrackDao;
    /**
     * log
     */
    private static final Log LOG = LogFactory.getLog(SalesEventServiceImpl.class);

    /**
     * 获得所有的销售事件
     * 
     * @param start
     * @param pageSize
     * @return map
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getAllEvents(int start, int pageSize, Map<String, Object> userMap)
            throws CRMDBException {
        LOG.debug("service start to get all sales event");
        Map<String, Object> map = null;
        List<Integer> userList = new ArrayList<Integer>();
        try {
            List<UserInfoDto> list = (List<UserInfoDto>) userMap.get("items");
            for (UserInfoDto userInfoDto : list) {
                userList.add(userInfoDto.getUserID());
            }
            map = salesEventDao.getAllEvents(start, pageSize, userList);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getAllEvents：{}", e);
            throw new CRMDBException("Error getAllEvents start:(#" + start + "#) pageSize:(#"
                    + pageSize + "#):" + e);
        }
        LOG.debug("service end to get all sales event");
        return map;
    }

    /**
     * 删除销售事件
     * 
     * @param salesEventIDs
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Map<String, Object> deleteEvent(String salesEventIDs) throws CRMDBException {
        LOG.debug("service start to delete sales event.");
        StringBuffer stringBuffer = new StringBuffer().append("");
        StringBuffer sb = new StringBuffer();
        StringBuffer sb1 = new StringBuffer().append("");
        Map<String, Object> errorMap = new HashMap<String, Object>();
        String[] salesIDs = salesEventIDs.split(",");
        try {
            Map<String, Object> map = salesEventDao.deleteSalesEventCheck(salesEventIDs);
            List<String> contactList = (List<String>) map.get("contactItems");
            List<String> intentOrderList = (List<String>) map.get("intentOrderItems");
            List<String> orderList = (List<String>) map.get("orderItems");
            Map<String, Object> allMap = new HashMap<String, Object>();
            if (contactList.size() > 0 && contactList != null) {
                for (String name : contactList) {
                    allMap.put(name, sb.append("客户联系模块,"));
                }
            }
            if (intentOrderList.size() > 0 && intentOrderList != null) {
                for (String name : intentOrderList) {
                    allMap.put(name, sb.append("意向订单模块,"));
                }
            }
            if (orderList.size() > 0 && orderList != null) {
                for (String name : orderList) {
                    allMap.put(name, sb.append("订单模块,"));
                }
            }
            for (String salesID : salesIDs) {
                int id = Integer.parseInt(salesID.trim());
                SalesEventDto salesEventDto = salesEventDao.findEventInfoById(id);
                String eventName = salesEventDto.getEventName();
                if (allMap.get(eventName) != null) {
                    stringBuffer = (StringBuffer) allMap.get(eventName);
                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                    sb1.append(eventName).append("正在被").append(stringBuffer).append("使用,无法删除！");
                    break;
                }
            }
            if (!sb1.toString().equals("")) {
                errorMap.put("errorMessage", sb1.toString());
            } else {
                errorMap.put("errorMessage", "");
                salesEventDao.deleteEvents(salesEventIDs);
            }
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method deleteEvent：{}", e);
            throw new CRMDBException("Error deleteEvent(#" + salesEventIDs + "#):" + e);
        }
        LOG.debug("service end to delete sales event.");
        return errorMap;
    }

    /**
     * 添加、修改、改变、中止销售事件
     * 
     * @param salesEventDto
     * @param flag
     * @throws CRMDBException
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void addOrUpdateEvent(SalesEventDto salesEventDto, String flag, String nextStatus)
            throws CRMDBException {
        LOG.debug("service start to addOrUpdateEvent.");
        try {
            Date now = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = formatter.format(now);
            String date1 = formatter1.format(now);
            // 增加
            if (salesEventDto.getEventID() == 0 && flag.equals("add")) {
                salesEventDto.setSubmitDate(date);
                salesEventDao.addOrUpdateEvent(salesEventDto);
                List<String> list = salesEventDto.getDemand();
                for (int i = 1; i < list.size() + 1; i++) {
                    SalesEventFlowDto salesEventFlowDto = new SalesEventFlowDto();
                    salesEventFlowDto.setDemand(list.get(i - 1));
                    salesEventFlowDto.setEventID(salesEventDto.getEventID());
                    String demandStatuString = salesEventDto.getDemandStatus().get(i - 1);
                    salesEventFlowDto.setStatus(Integer.valueOf(demandStatuString));
                    salesEventDao.addEventFlow(salesEventFlowDto);
                }
                SalesTrackDto salesTrackDto = new SalesTrackDto();
                salesTrackDto.setCustomerID(salesEventDto.getCustomerID());
                salesTrackDto.setEventID(salesEventDto.getEventID());
                salesTrackDto.setStatus(salesEventDto.getStatus());
                salesTrackDto.setSubmitDate(date1);
                salesTrackDto.setSubmitterID(salesEventDto.getSubmitterID());
                salesTrackDao.addSalesTrack(salesTrackDto);
            }
            // 修改销售事件阶段
            if (salesEventDto.getEventID() != 0 && flag.equals("transactionAllow")) {
                String status = salesEventDto.getStatus();
                // 获得下一个销售阶段名称
                salesEventDto.setStatus(nextStatus);
                SalesTrackDto salesTrackDto = new SalesTrackDto();
                salesTrackDto.setCustomerID(salesEventDto.getCustomerID());
                salesTrackDto.setEventID(salesEventDto.getEventID());
                salesTrackDto.setStatus(salesEventDto.getStatus());
                salesTrackDto.setSubmitDate(date1);
                salesTrackDto.setSubmitterID(salesEventDto.getSubmitterID());
                // 添加销售履历
                salesTrackDao.addSalesTrack(salesTrackDto);
                if (salesEventDto.getIsAbolished()) {
                    salesEventDto.setIsAbolished(false);
                    SalesTrackBean salesTrackBean = new SalesTrackBean();
                    salesTrackBean.setEventID(salesEventDto.getEventID());
                    salesTrackBean.setStatus(status);
                    salesTrackBean.setIsAbolished(false);
                    salesTrackDao.updateSalesTrack(salesTrackBean);
                }
                // 修改销售事件
                salesEventDao.addOrUpdateEvent(salesEventDto);
                // 修改销售事件需求
                SalesEventFlowDto nextSalesEventFlowDto = new SalesEventFlowDto();
                nextSalesEventFlowDto.setDemand(salesEventDto.getNextDemand());
                nextSalesEventFlowDto.setEventID(salesEventDto.getEventID());
                nextSalesEventFlowDto.setStatus(Integer.valueOf(nextStatus));
                if (salesEventDao.findEventFlowByDto(nextSalesEventFlowDto)) {
                    salesEventDao.updateEventFlow(nextSalesEventFlowDto);
                } else {
                    salesEventDao.addEventFlow(nextSalesEventFlowDto);
                }
                // 如果从意向订单阶段到订单阶段，将意向订单修改为订单
                if (Integer.valueOf(nextStatus) == salesEventDao.findFristFlowStatusInOrder()) {
                    OrderDto orderDto = salesEventDao
                            .findIntentionOrder(salesEventDto.getEventID());
                    if (orderDto != null) {
                        orderDto.setType(1);
                        salesEventDao.addOrUpdateOrder(orderDto);
                    }
                }
            }
            // 中止事件
            if (salesEventDto.getEventID() != 0 && flag.equals("transactionRefused")) {
                SalesTrackBean salesTrackBean = new SalesTrackBean();
                salesTrackBean.setEventID(salesEventDto.getEventID());
                salesTrackBean.setStatus(salesEventDto.getStatus());
                salesTrackBean.setIsAbolished(true);
                salesTrackDao.updateSalesTrack(salesTrackBean);
                salesEventDto.setIsAbolished(true);
                salesEventDao.addOrUpdateEvent(salesEventDto);
            }
            // 修改
            if (flag.equals("update")) {
                salesEventDao.addOrUpdateEvent(salesEventDto);
                List<String> list = salesEventDto.getDemand();
                for (int i = 1; i < list.size() + 1; i++) {
                    SalesEventFlowDto salesEventFlowDto = new SalesEventFlowDto();
                    salesEventFlowDto.setDemand(list.get(i - 1));
                    salesEventFlowDto.setEventID(salesEventDto.getEventID());
                    String demandStatuString = salesEventDto.getDemandStatus().get(i - 1);
                    salesEventFlowDto.setStatus(Integer.valueOf(demandStatuString));
                    if (salesEventDao.findEventFlowByDto(salesEventFlowDto)) {
                        salesEventDao.updateEventFlow(salesEventFlowDto);
                    } else {
                        salesEventDao.addEventFlow(salesEventFlowDto);
                    }
                }
            }
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method addOrUpdateEvent：{}", e);
            throw new CRMDBException("Error addOrUpdateEvent:" + e);
        }
        LOG.debug("service end to addOrUpdateEvent.");
    }

    /**
     * 获得所有tabpanel显示
     * 
     * @param eventID
     * @return map
     * @throws CRMDBException
     */
    @Override
    public Map<String, Object> getTabs(int eventID) throws CRMDBException {
        LOG.debug("service start to get tabs info");
        Map<String, Object> map = null;
        try {
            map = salesEventDao.getTabs(eventID);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getTabs：{}", e);
            throw new CRMDBException("Error getTabs(#" + eventID + "#):" + e);
        }
        LOG.debug("service end to get tabs info");
        return map;
    }

    /**
     * 判断eventName是否存在
     * 
     * @param salesEventDto
     * @return eventNameCount
     * @throws CRMDBException
     */
    @Override
    public long judgeSalesEventName(SalesEventDto salesEventDto) throws CRMDBException {
        LOG.debug("service start to judgeSalesEventName");
        long eventNameCount = 0;
        try {
            eventNameCount = salesEventDao.judgeSalesEventName(salesEventDto);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method judgeSalesEventName：{}", e);
            throw new CRMDBException("Error judgeSalesEventName(#" + "#):" + e);
        }
        LOG.debug("service end to judgeSalesEventName");
        return eventNameCount;
    }

    /**
     * salesEventDao的set（）方法
     * 
     * @param salesEventDao
     */
    public void setSalesEventDao(SalesEventDao salesEventDao) {
        this.salesEventDao = salesEventDao;
    }

    /**
     * salesTrackDao的set（）方法
     * 
     * @param salesTrackDao
     */
    public void setSalesTrackDao(SalesTrackDao salesTrackDao) {
        this.salesTrackDao = salesTrackDao;
    }

    @Override
    public Boolean judgeFindIntentionOrder(int eventID) throws CRMDBException {
        LOG.debug("service start to judgeSalesEventName");
        boolean flag = true;
        try {
            OrderDto orderDto = salesEventDao.findIntentionOrder(eventID);
            if (orderDto == null) {
                flag = false;
            }
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method findIntentionOrder：{}", e);
            throw new CRMDBException("Error findIntentionOrder(#" + eventID + "#):" + e);
        }
        LOG.debug("service end to get tabs info");
        return flag;
    }

    @Override
    public SalesEventDto findEventInfoById(int eventID) throws CRMDBException {
        // TODO Auto-generated method stub
        return salesEventDao.findEventInfoById(eventID);
    }

}
