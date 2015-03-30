package com.ustcsoft.gs.crm.webui.service;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.SourceInfoBean;
import com.ustcsoft.gs.crm.webui.customer.bean.SourceSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.CustomerDto;
import com.ustcsoft.gs.crm.webui.customer.dto.SourceInfoDto;
import com.ustcsoft.gs.crm.webui.customer.service.CustomerService;
import com.ustcsoft.gs.crm.webui.customer.service.SourceInfoService;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

/**
 * test class SourceInfoServiceImpl
 * 
 * @author xuzhen
 * 
 */
public class SourceInfoServiceImplTest extends CRMTest {

    private static final String TEST1 = "恒";
    private static final String TEST2 = "合肥";
    private static final String TEST3 = "01";
    private static final int USER = 1;
    private static final String TOTAL = "total";
    private static final String ITEMS = "items";
    private static SourceInfoService sourceInfoService = null;
    private static CustomerService customerService = null;
    private CustomerDto customerDto = new CustomerDto();
    private SourceInfoDto sourceInfoDto = new SourceInfoDto();
    private SourceSearchBean sourceSearchBean = new SourceSearchBean();
    private Map<String, Object> map = null;
    private List<SourceInfoBean> sourceInfoList = null;
    private static int deleteId = 0;

    @BeforeClass
    public static void setUpBeforeClass() {
        sourceInfoService = (SourceInfoService) CTX.getBean("sourceInfoService");
        customerService = (CustomerService) CTX.getBean("customerService");
    }

    @Before
    public void setUp() throws Exception {
        customerDto.setCustomerAddr("合肥市");
        customerDto.setCustomerName("恒星");
        customerDto.setCustomerStatement("1");
        customerDto.setCustomerType("1");
        customerDto.setFee("1");
        customerDto.setHolder(1);
        customerDto.setIndustry("01");
        customerDto.setIsAbolished(false);
        customerDto.setScale("1");
        customerDto.setValueEvaluate("1");

        sourceInfoDto.setCustomerID("1");
        sourceInfoDto.setSourceType("01");
        sourceInfoDto.setSourceArea("testArea");
        sourceInfoDto.setIsAbolished(false);
        sourceInfoDto.setDescriptions("");

        sourceSearchBean.setSearchText(TEST1);
        sourceSearchBean.setSourceArea(TEST2);
        sourceSearchBean.setSourceCustomer(TEST1);
        final String[] type = { TEST3 };
        sourceSearchBean.setSourceType(type);
    }

    /**
     * test method getCustomer
     * 
     * @throws CRMDBException
     * @throws CRMDBException
     */
    @Test
    public void testGetCustomer() throws CRMDBException, CRMDBException {
        customerService.updateCustomer(customerDto);
        map = sourceInfoService.getCustomer(1, USER);
        List<?> list = (List<?>) map.get("items");
        map = sourceInfoService.getCustomer(0, USER);
        List<?> listAll = (List<?>) map.get("items");
        boolean bool = listAll.size() >= list.size();
        Assert.assertEquals(bool, true);
        customerService.deleteCustomer(String.valueOf(customerDto.getCustomerID()));
    }

    /**
     * test method updateSource for add source
     * 
     * @throws CRMDBException
     */
    @Test
    public void testAddSource() throws CRMDBException {

        map = sourceInfoService.getOrSearchSource(0, null, 1, 25, USER);
        final int start = Integer.parseInt(map.get(TOTAL).toString());
        sourceInfoService.updateSource(sourceInfoDto);
        deleteId = sourceInfoDto.getSourceID();
        map = sourceInfoService.getOrSearchSource(0, null, 1, 25, USER);
        final int end = Integer.parseInt(map.get(TOTAL).toString());
        Assert.assertEquals(end - start, 1);
    }

    /**
     * test method deleteSource
     * 
     * @throws CRMDBException
     */
    @Test
    public void testDeleteSource() throws CRMDBException {
        map = sourceInfoService.getOrSearchSource(0, null, 1, 25, USER);
        final int start = Integer.parseInt(map.get(TOTAL).toString());
        sourceInfoService.deleteSource(String.valueOf(deleteId));
        map = sourceInfoService.getOrSearchSource(0, null, 1, 25, USER);
        final int end = Integer.parseInt(map.get(TOTAL).toString());
        Assert.assertEquals(start - end, 1);
    }

    /**
     * test method getCustomer getOrSearchSource
     * 
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetOrSearchSource() throws CRMDBException {
        map = sourceInfoService.getOrSearchSource(0, null, 1, 25, USER);
        int total = Integer.parseInt(map.get("total").toString());
        sourceInfoList = (List<SourceInfoBean>) map.get(ITEMS);
        boolean bool = total >= sourceInfoList.size();
        Assert.assertEquals(bool, true);

        map = sourceInfoService.getOrSearchSource(1, sourceSearchBean, 1, 25, USER);
        Assert.assertNotNull(map);
        sourceInfoList = (List<SourceInfoBean>) map.get(ITEMS);
        SourceInfoBean result = null;
        if (sourceInfoList.size() != 0) {
            for (int i = 0; i < sourceInfoList.size(); i++) {
                result = sourceInfoList.get(i);
                boolean b1 = result.getCustomerName().contains(TEST1);
                boolean b2 = result.getSourceArea().contains(TEST1);
                boolean b3 = result.getSourceTypeName().contains(TEST1);
                Assert.assertEquals(true, b1 || b2 || b3);
            }
        }

        map = sourceInfoService.getOrSearchSource(2, sourceSearchBean, 1, 25, USER);
        sourceInfoList = (List<SourceInfoBean>) map.get(ITEMS);
        Assert.assertNotNull(map);
        if (sourceInfoList.size() != 0) {
            for (int i = 0; i < sourceInfoList.size(); i++) {
                result = sourceInfoList.get(i);
                Assert.assertSame(TEST1, result.getCustomerName());
                Assert.assertSame(TEST2, result.getSourceArea());
                Assert.assertEquals(TEST3, result.getSourceType());
            }
        }
    }

    /**
     * test exception for method updateSource
     * 
     * @throws CRMDBException
     */
    @Test(expected = CRMDBException.class)
    public void testUpdateSourceThrowsCRMDBException() throws CRMDBException {
        SourceInfoDto source = new SourceInfoDto();
        sourceInfoService.updateSource(source);
    }

    /**
     * test exception for method getOrSearchSource
     * 
     * @throws CRMDBException
     */
    @Test(expected = CRMDBException.class)
    public void testGetOrSearchSourceThrowsCRMDBException() throws CRMDBException {
        map = sourceInfoService.getOrSearchSource(0, null, -1, 25, USER);
    }

    /**
     * test exception for method deleteSource
     * 
     * @throws CRMDBException
     */
    @Test(expected = CRMDBException.class)
    public void testDeleteSourceThrowsCRMDBException() throws CRMDBException {
        sourceInfoService.deleteSource("");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }
}
