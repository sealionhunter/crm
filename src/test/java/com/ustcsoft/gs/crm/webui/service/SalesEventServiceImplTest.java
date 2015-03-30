package com.ustcsoft.gs.crm.webui.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.dgic.testing.common.virtualmock.MockObjectManager;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.sales.bean.SalesEventBean;
import com.ustcsoft.gs.crm.webui.sales.bean.TabBean;
import com.ustcsoft.gs.crm.webui.sales.dao.impl.SalesEventDaoImpl;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesEventDto;
import com.ustcsoft.gs.crm.webui.sales.service.SalesEventService;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

public class SalesEventServiceImplTest extends CRMTest {
    private SalesEventService salesEventService = null;
    private SalesEventDto salesEventDto = null;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        salesEventService = (SalesEventService) CTX.getBean("salesEventService");
        salesEventDto = new SalesEventDto();
        salesEventDto.setContactID(1);
        salesEventDto.setCustomerID(1);
        salesEventDto.setEventName("测试");
        salesEventDto.setStatus("1");
        salesEventDto.setSubmitterID(1);
        List<String> listDemand = new ArrayList<String>();
        List<String> listDemandStatus = new ArrayList<String>();
        listDemand.add("测试需求");
        listDemandStatus.add("1");
        salesEventDto.setDemand(listDemand);
        salesEventDto.setDemandStatus(listDemandStatus);

    }

    @Test
    public void testEntireOperation() throws CRMDBException {
        Map<String, Object> userMap = new HashMap<String, Object>();
        List<UserInfoDto> userList = new ArrayList<UserInfoDto>();
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setUserID(1);
        userList.add(userInfoDto);
        userMap.put("items", userList);
        map = salesEventService.getAllEvents(0, 25, userMap);
        List<SalesEventBean> list = new ArrayList<SalesEventBean>();
        int startCount = list.size();
        System.out.println(startCount);
        // 添加
        salesEventService.addOrUpdateEvent(salesEventDto, "add", null);
        map = salesEventService.getAllEvents(0, 25, userMap);
        List<SalesEventBean> list1 = new ArrayList<SalesEventBean>();
        int endCount1 = list1.size();
        assertEquals(endCount1 - startCount, 0);
        // 修改
        salesEventService.addOrUpdateEvent(salesEventDto, "update", null);
        map = salesEventService.getAllEvents(0, 25, userMap);
        List<SalesEventBean> list3 = new ArrayList<SalesEventBean>();
        int endCount3 = list3.size();
        assertEquals(endCount3 - startCount, 0);
        // 进入下一阶段
        salesEventDto.setIsAbolished(true);
        salesEventService.addOrUpdateEvent(salesEventDto, "transactionAllow", "2");
        salesEventService.addOrUpdateEvent(salesEventDto, "update", null);
        map = salesEventService.getAllEvents(0, 25, userMap);
        List<SalesEventBean> list4 = new ArrayList<SalesEventBean>();
        int endCount4 = list4.size();
        assertEquals(endCount4 - startCount, 0);
        // 中止
        salesEventService.addOrUpdateEvent(salesEventDto, "transactionRefused", null);
        salesEventService.addOrUpdateEvent(salesEventDto, "update", null);
        map = salesEventService.getAllEvents(0, 25, userMap);
        List<SalesEventBean> list5 = new ArrayList<SalesEventBean>();
        int endCount5 = list5.size();
        assertEquals(endCount5 - startCount, 0);
        // 删除
        int eventID = salesEventDto.getEventID();
        salesEventService.deleteEvent(String.valueOf(eventID));
        map = salesEventService.getAllEvents(0, 25, userMap);
        List<SalesEventBean> list2 = new ArrayList<SalesEventBean>();
        int endCount2 = list2.size();
        assertEquals(endCount2 - startCount, 0);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetTabs() throws CRMDBException {
        map = salesEventService.getTabs(0);
        List<TabBean> list = new ArrayList<TabBean>();
        list = (List<TabBean>) map.get("items");
        int count = list.size();
        System.out.println(count);
        assertEquals(count, 13);
    }

    @Test
    public void testJudgeSalesEventName() throws CRMDBException {
        SalesEventDto salesEventDto1 = new SalesEventDto();
        salesEventDto1.setEventID(0);
        salesEventDto1.setEventName("");
        long count = salesEventService.judgeSalesEventName(salesEventDto1);
        assertEquals(count, 0);
    }

    @Test
    public void testJudgeFindIntentionOrder() throws CRMDBException {
        Boolean flag = salesEventService.judgeFindIntentionOrder(0);
        assertEquals(flag, false);
    }

    @Test(expected = CRMDBException.class)
    public void testGetAllEvents_Exception() throws CRMDBException {
        Map<String, Object> userMap = new HashMap<String, Object>();
        List<UserInfoDto> userList = new ArrayList<UserInfoDto>();
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setUserID(1);
        userList.add(userInfoDto);
        userMap.put("items", userList);
        MockObjectManager.addReturnValue(SalesEventDaoImpl.class, "getAllEvents",
                new EmptyResultDataAccessException(0));
        salesEventService.getAllEvents(0, 25, userMap);
    }

    @Test(expected = CRMDBException.class)
    public void testDeleteEvent_Exception() throws CRMDBException {
        MockObjectManager.addReturnValue(SalesEventDaoImpl.class, "deleteEvents",
                new EmptyResultDataAccessException(0));
        salesEventService.deleteEvent("0");
    }

    @Test(expected = CRMDBException.class)
    public void testAddOrUpdateEvent_Exception() throws CRMDBException {
        MockObjectManager.addReturnValue(SalesEventDaoImpl.class, "addOrUpdateEvent",
                new EmptyResultDataAccessException(0));
        salesEventService.addOrUpdateEvent(salesEventDto, "add", null);
    }

    @Test(expected = CRMDBException.class)
    public void testGetTabs_Exception() throws CRMDBException {
        MockObjectManager.addReturnValue(SalesEventDaoImpl.class, "getTabs",
                new EmptyResultDataAccessException(0));
        salesEventService.getTabs(0);
    }

    @Test(expected = CRMDBException.class)
    public void testJudgeSalesEventName_Exception() throws CRMDBException {
        MockObjectManager.addReturnValue(SalesEventDaoImpl.class, "judgeSalesEventName",
                new EmptyResultDataAccessException(0));
        salesEventService.judgeSalesEventName(salesEventDto);
    }

    @Test(expected = CRMDBException.class)
    public void testJudgeFindIntentionOrder_Exception() throws CRMDBException {
        MockObjectManager.addReturnValue(SalesEventDaoImpl.class, "findIntentionOrder",
                new EmptyResultDataAccessException(0));
        salesEventService.judgeFindIntentionOrder(0);
    }
}
