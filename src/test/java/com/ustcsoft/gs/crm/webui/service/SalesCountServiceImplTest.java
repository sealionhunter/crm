package com.ustcsoft.gs.crm.webui.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.dgic.testing.common.virtualmock.MockObjectManager;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.count.dao.SalesCountDao;
import com.ustcsoft.gs.crm.webui.count.dto.SalesCountResult;
import com.ustcsoft.gs.crm.webui.count.service.impl.SalesCountServiceImpl;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

/**
 * 
 * @author jiaxu
 * 
 */
public class SalesCountServiceImplTest extends CRMTest {
    private final static String END_DATE = "2020-01-01kjlhlkj";
    private final static String START_DATE = "2013-09-01asdfsdf";
    private static SalesCountServiceImpl salesCountServiceImpl = null;
    private static String startDate;
    private static String endDate;
    private Map<String, Object> map = new HashMap<String, Object>();

    /**
     * get the bean from spring bean factory.
     */
    @BeforeClass
    public final static void setUpBeforeClass() {
        salesCountServiceImpl = (SalesCountServiceImpl) CTX.getBean("salesCountService");
    }

    @Before
    @After
    public void tearDown() {

    }

    /**
     * test the method of countSalesEvents.
     * 
     * @throws CRMDBException
     */

    @SuppressWarnings("unchecked")
    @Test
    public final void testCountSalesEvents() throws CRMDBException {
        map = salesCountServiceImpl.countSalesEvents(startDate, endDate);
        List<SalesCountResult> salesCountResults = new ArrayList<SalesCountResult>();
        salesCountResults = (List<SalesCountResult>) map.get("items");
        if (salesCountResults.size() == 1) {
            assertEquals(true, salesCountResults.get(0).getAllCount() == 0);
        } else {
            assertEquals(true, salesCountResults.get(0).getAllCount() > 0);
        }
        startDate = START_DATE;
        endDate = END_DATE;
        map = salesCountServiceImpl.countSalesEvents(startDate, endDate);
        if (salesCountResults.size() == 1) {
            assertEquals(true, salesCountResults.get(0).getAllCount() == 0);
        } else {
            assertEquals(true, salesCountResults.get(0).getAllCount() > 0);
        }
    }

    /**
     * test the CRMDBException of method countSalesEvents
     * 
     * @throws CRMDBException
     * 
     */
    @Test(expected = CRMDBException.class)
    public void testCountSalesEventThrowsCRMDBException() throws CRMDBException {
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(SalesCountDao.class, "countSalesEvents",
                new EmptyResultDataAccessException(0));
        salesCountServiceImpl.countSalesEvents(startDate, endDate);
    }
}
