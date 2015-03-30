package com.ustcsoft.gs.crm.webui.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import jp.co.dgic.testing.common.virtualmock.MockObjectManager;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.sales.bean.SalesEventFlowCodeBean;
import com.ustcsoft.gs.crm.webui.sales.dao.impl.SalesEventFlowCodeDaoImpl;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesEventFlowCodeDto;
import com.ustcsoft.gs.crm.webui.sales.service.SalesEventFlowCodeService;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

public class SalesEventFlowCodeServiceImplTest extends CRMTest {
    private SalesEventFlowCodeService salesEventFlowCodeService = null;
    private SalesEventFlowCodeDto salesEventFlowCodeDto = null;
    private List<SalesEventFlowCodeDto> salesEventFlowCodeDtos = new ArrayList<SalesEventFlowCodeDto>();

    // private List salesEventFlowCodeDtos = new ArrayList();
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        salesEventFlowCodeService = (SalesEventFlowCodeService) CTX
                .getBean("salesEventFlowCodeService");
        salesEventFlowCodeDto = new SalesEventFlowCodeDto();
        salesEventFlowCodeDto.setCategory("00040002");
        salesEventFlowCodeDto.setSort(3);
        salesEventFlowCodeDto.setValue("测试");
        salesEventFlowCodeDtos.add(salesEventFlowCodeDto);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testEntireOperation() throws CRMDBException {
        map = salesEventFlowCodeService.findSalesEventFlowCode();
        List<SalesEventFlowCodeBean> list = new ArrayList<SalesEventFlowCodeBean>();
        list = (List<SalesEventFlowCodeBean>) map.get("items");
        int startCount = list.size();
        System.out.println("startCount:" + startCount);
        // 添加
        salesEventFlowCodeService.addOrUpdateCode(salesEventFlowCodeDtos, 1, 0);
        map = salesEventFlowCodeService.findSalesEventFlowCode();
        List<SalesEventFlowCodeBean> list1 = new ArrayList<SalesEventFlowCodeBean>();
        list1 = (List<SalesEventFlowCodeBean>) map.get("items");
        int endCount1 = list1.size();
        assertEquals(endCount1 - startCount, 1);
        System.out.println("endCount1:" + endCount1);
        salesEventFlowCodeDtos.get(0).setValue("测试1");
        // 修改名字
        salesEventFlowCodeService.addOrUpdateCode(salesEventFlowCodeDtos, 0, 0);
        map = salesEventFlowCodeService.findSalesEventFlowCode();
        List<SalesEventFlowCodeBean> list2 = new ArrayList<SalesEventFlowCodeBean>();
        list2 = (List<SalesEventFlowCodeBean>) map.get("items");
        int endCount2 = list2.size();
        assertEquals(endCount2 - startCount, 1);
        assertEquals(endCount2 - endCount1, 0);
        // 下移
        salesEventFlowCodeDtos.get(0).setSort(4);
        salesEventFlowCodeService.addOrUpdateCode(salesEventFlowCodeDtos, 0, 3);
        map = salesEventFlowCodeService.findSalesEventFlowCode();
        List<SalesEventFlowCodeBean> list3 = new ArrayList<SalesEventFlowCodeBean>();
        list3 = (List<SalesEventFlowCodeBean>) map.get("items");
        int endCount3 = list3.size();
        assertEquals(endCount3 - startCount, 1);
        assertEquals(endCount3 - endCount2, 0);
        // 上移
        salesEventFlowCodeDtos.get(0).setSort(3);
        salesEventFlowCodeService.addOrUpdateCode(salesEventFlowCodeDtos, 0, 4);
        map = salesEventFlowCodeService.findSalesEventFlowCode();
        List<SalesEventFlowCodeBean> list4 = new ArrayList<SalesEventFlowCodeBean>();
        list4 = (List<SalesEventFlowCodeBean>) map.get("items");
        int endCount4 = list4.size();
        assertEquals(endCount4 - startCount, 1);
        assertEquals(endCount4 - endCount3, 0);
        // 删除
        salesEventFlowCodeService.deleteCode(salesEventFlowCodeDtos);
        map = salesEventFlowCodeService.findSalesEventFlowCode();
        List<SalesEventFlowCodeBean> list5 = new ArrayList<SalesEventFlowCodeBean>();
        list5 = (List<SalesEventFlowCodeBean>) map.get("items");
        int endCount5 = list5.size();
        assertEquals(endCount5 - startCount, 0);
        assertEquals(endCount4 - endCount5, 1);
    }

    @Test
    public void testJudgeEventNameRepeat() throws CRMDBException {
        SalesEventFlowCodeDto salesEventFlowCodeDto = new SalesEventFlowCodeDto();
        salesEventFlowCodeDto.setValue("中标");
        long count = salesEventFlowCodeService.judgeEventNameRepeat(salesEventFlowCodeDto);
        assertEquals(count, 1);
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void testFindSalesEventFlowCategory() throws CRMDBException {
        map = salesEventFlowCodeService.findSalesEventFlowCategory();
        List list = new ArrayList();
        list = (List) map.get("items");
        int count = list.size();
        assertEquals(count, 2);
    }

    @Test
    public void testGetCodeByCategory() throws CRMDBException {
        String categoryString = "00040004";
        List<SalesEventFlowCodeBean> list = new ArrayList<SalesEventFlowCodeBean>();
        list = salesEventFlowCodeService.getCodeByCategory(categoryString);
        int count = list.size();
        assertEquals(count, 1);
    }

    @Test(expected = CRMDBException.class)
    public void testFindSalesEventFlowCode_Exception() throws CRMDBException {
        MockObjectManager.addReturnValue(SalesEventFlowCodeDaoImpl.class, "findSalesEventFlowCode",
                new EmptyResultDataAccessException(0));
        salesEventFlowCodeService.findSalesEventFlowCode();
    }

    @Test(expected = CRMDBException.class)
    public void testAddOrUpdateCode_Exception() throws CRMDBException {
        MockObjectManager.addReturnValue(SalesEventFlowCodeDaoImpl.class, "updateSortByPlus",
                new EmptyResultDataAccessException(0));
        salesEventFlowCodeService.addOrUpdateCode(salesEventFlowCodeDtos, 1, 0);
    }

    @Test(expected = CRMDBException.class)
    public void testDeleteCode_Exception() throws CRMDBException {
        MockObjectManager.addReturnValue(SalesEventFlowCodeDaoImpl.class, "updateSortByMinus",
                new EmptyResultDataAccessException(0));
        salesEventFlowCodeService.deleteCode(salesEventFlowCodeDtos);
    }

    @Test(expected = CRMDBException.class)
    public void testJudgeEventNameRepeat_Exception() throws CRMDBException {
        MockObjectManager.addReturnValue(SalesEventFlowCodeDaoImpl.class, "judgeEventNameRepeat",
                new EmptyResultDataAccessException(0));
        SalesEventFlowCodeDto salesEventFlowCodeDto = new SalesEventFlowCodeDto();
        salesEventFlowCodeDto.setValue("中标");
        salesEventFlowCodeService.judgeEventNameRepeat(salesEventFlowCodeDto);
    }

    @Test(expected = CRMDBException.class)
    public void testFindSalesEventFlowCategory_Exception() throws CRMDBException {
        MockObjectManager.addReturnValue(SalesEventFlowCodeDaoImpl.class,
                "findSalesEventFlowCategory", new EmptyResultDataAccessException(0));
        salesEventFlowCodeService.findSalesEventFlowCategory();
    }

    @Test(expected = CRMDBException.class)
    public void testGetCodeByCategory_Exception() throws CRMDBException {
        MockObjectManager.addReturnValue(SalesEventFlowCodeDaoImpl.class, "getCodeByCategory",
                new EmptyResultDataAccessException(0));
        salesEventFlowCodeService.getCodeByCategory("00040002");
    }

}
