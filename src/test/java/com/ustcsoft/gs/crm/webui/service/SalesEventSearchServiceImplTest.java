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
import com.ustcsoft.gs.crm.webui.sales.bean.SalesSuperSearchBean;
import com.ustcsoft.gs.crm.webui.sales.dao.impl.SalesEventSearchDaoImpl;
import com.ustcsoft.gs.crm.webui.sales.service.SalesEventSearchService;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

public class SalesEventSearchServiceImplTest extends CRMTest {
    private SalesEventSearchService salesEventSearchService = null;
    private String searchKey = null;
    private SalesSuperSearchBean salesSuperSearchBean = null;

    @Before
    public void setUp() throws Exception {
        salesEventSearchService = (SalesEventSearchService) CTX.getBean("salesEventSearchService");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testSearch() throws CRMDBException {
        Map<String, Object> userMap = new HashMap<String, Object>();
        List<UserInfoDto> userList = new ArrayList<UserInfoDto>();
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setUserID(1);
        userList.add(userInfoDto);
        userMap.put("items", userList);
        searchKey = "zzzzzz";
        map = salesEventSearchService.search(0, 25, searchKey, userMap);
        List<SalesEventBean> list = new ArrayList<SalesEventBean>();
        list = (List<SalesEventBean>) map.get("items");
        int count = list.size();
        assertEquals(count, 0);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testAdvancedSearch() throws CRMDBException {
        Map<String, Object> userMap = new HashMap<String, Object>();
        List<UserInfoDto> userList = new ArrayList<UserInfoDto>();
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setUserID(1);
        userList.add(userInfoDto);
        userMap.put("items", userList);
        salesSuperSearchBean = new SalesSuperSearchBean();
        salesSuperSearchBean.setStatus("120302302030");
        salesSuperSearchBean.setCustomerName("111");
        salesSuperSearchBean.setDateFrom("1");
        salesSuperSearchBean.setDateTo("ewq");
        salesSuperSearchBean.setSubmitPersonName("das");
        salesSuperSearchBean.setEventName("1");
        map = salesEventSearchService.advancedSearch(0, 25, salesSuperSearchBean, userMap);
        List<SalesEventBean> list = new ArrayList<SalesEventBean>();
        list = (List<SalesEventBean>) map.get("items");
        int count = list.size();
        assertEquals(count, 0);
    }

    @Test(expected = CRMDBException.class)
    public void testSearch_Exception() throws CRMDBException {
        Map<String, Object> userMap = new HashMap<String, Object>();
        List<UserInfoDto> userList = new ArrayList<UserInfoDto>();
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setUserID(1);
        userList.add(userInfoDto);
        userMap.put("items", userList);
        searchKey = "zzzzzz";
        MockObjectManager.addReturnValue(SalesEventSearchDaoImpl.class, "search",
                new EmptyResultDataAccessException(0));
        salesEventSearchService.search(0, 25, searchKey, userMap);
    }

    @Test(expected = CRMDBException.class)
    public void testAdvancedSearch_Exception() throws CRMDBException {
        Map<String, Object> userMap = new HashMap<String, Object>();
        List<UserInfoDto> userList = new ArrayList<UserInfoDto>();
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setUserID(1);
        userList.add(userInfoDto);
        userMap.put("items", userList);
        salesSuperSearchBean = new SalesSuperSearchBean();
        salesSuperSearchBean.setStatus("120302302030");
        salesSuperSearchBean.setCustomerName("111");
        salesSuperSearchBean.setDateFrom("1");
        salesSuperSearchBean.setDateTo("ewq");
        salesSuperSearchBean.setSubmitPersonName("das");
        salesSuperSearchBean.setEventName("1");
        MockObjectManager.addReturnValue(SalesEventSearchDaoImpl.class, "advancedSearch",
                new EmptyResultDataAccessException(0));
        salesEventSearchService.advancedSearch(0, 25, salesSuperSearchBean, userMap);
    }
}
