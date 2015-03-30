/**
 * 
 */
package com.ustcsoft.gs.crm.webui.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.dgic.testing.common.virtualmock.MockObjectManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.product.bean.PriceBean;
import com.ustcsoft.gs.crm.webui.product.bean.PriceSearchBean;
import com.ustcsoft.gs.crm.webui.product.dao.PriceDao;
import com.ustcsoft.gs.crm.webui.product.dto.ProductDto;
import com.ustcsoft.gs.crm.webui.product.dto.ProductPriceRangeDto;
import com.ustcsoft.gs.crm.webui.product.service.PriceService;
import com.ustcsoft.gs.crm.webui.system.dao.UserInfoDao;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

/**
 * @author zhangchuanhong
 * 
 */
public class PriceServiceTest extends CRMTest {

    private static final String TEST0 = "H";
    private static final String TEST1 = "2000";
    private static final String TEST2 = "3000";
    private static final int TEST3 = 4;
    private static final String TEST4 = "0.8";
    private static final String TEST5 = "0.9";
    private int searchFlagComplex = 1;
    List<PriceBean> priceBeans = new ArrayList<PriceBean>();
    List<PriceBean> errorPriceBeans = new ArrayList<PriceBean>();
    PriceSearchBean priceSearchBean = new PriceSearchBean();
    static ProductDto productDto = new ProductDto();
    static HibernateTemplate hibernateTemplate = null;
    static int productID = 0;
    private static PriceService priceService = null;
    public int start = 0;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        priceService = (PriceService) CTX.getBean("priceService");
        hibernateTemplate = (HibernateTemplate) CTX.getBean("hibernateTemplate");
        productDto.setCategory("000500010001");
        productDto.setDescription("");
        productDto.setName("test");
        productDto.setPrice("2000");
        productDto.setProductID("FW20131126142233140");
        productDto.setProductModel("q");
        productDto.setRemark("dasdas");
        hibernateTemplate.saveOrUpdate(productDto);
        productID = productDto.getId();
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        hibernateTemplate.delete(productDto);
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        priceService = (PriceService) CTX.getBean("priceService");
        priceSearchBean.setSearchText("test");
        priceSearchBean.setProductName(TEST0);
        priceSearchBean.setMinPrice(TEST1);
        priceSearchBean.setMaxPrice(TEST2);
        priceSearchBean.setGroupID(TEST3);
        priceSearchBean.setMinDiscount(TEST4);
        priceSearchBean.setMaxDiscount(TEST5);
        PriceBean priceBean1 = new PriceBean();
        priceBean1.setProductID(productID);
        priceBean1.setGroupID(1);
        priceBean1.setDiscount("0.90");
        PriceBean priceBean2 = new PriceBean();
        priceBean2.setProductID(productID);
        priceBean2.setGroupID(2);
        priceBean2.setDiscount("0.80");
        PriceBean priceBean3 = new PriceBean();
        priceBean3.setProductID(productID);
        priceBean3.setGroupID(5);
        priceBean3.setDiscount("18121212313213213213113213123132.9110");
        priceBeans.add(priceBean1);
        priceBeans.add(priceBean2);
        errorPriceBeans.add(priceBean3);
    }

    @Test
    public void testUpdatePriceDiscount() throws Exception {
        int count1 = priceService.getProductCountByPrice();
        priceService.updatePriceDiscount(priceBeans);
        int count2 = priceService.getProductCountByPrice();
        Assert.assertEquals(count1, count2, 1);
    }

    @Test
    public void testUpdatePriceDiscountException() throws Exception {
        try {
            priceService.updatePriceDiscount(errorPriceBeans);
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

    @Test
    public void testSearchProduct() throws Exception {
        List<HashMap<String, Object>> searcHashMaps = priceService.searchProduct(priceSearchBean,
                1, start, 10);
        Assert.assertEquals(searcHashMaps.size(), 1);
    }

    @Test
    public void testSearchProductException() {
        MockObjectManager.initialize();
        MockObjectManager.setReturnValueAtAllTimes(PriceDao.class, "searchPrice",
                new EmptyResultDataAccessException(0));
        try {
            priceService.searchProduct(priceSearchBean, searchFlagComplex, start, 10);
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

    @Test
    public void testSearchProductCount() throws CRMDBException {
        int count = priceService.searchProductCount(priceSearchBean, searchFlagComplex, start, 10);
        Assert.assertEquals(count, 1);
    }

    @Test
    public void testSearchProductCountException() throws CRMDBException {
        MockObjectManager.initialize();
        MockObjectManager.setReturnValueAtAllTimes(PriceDao.class, "searchPriceCount",
                new EmptyResultDataAccessException(0));
        try {
            priceService.searchProductCount(priceSearchBean, searchFlagComplex, start, 10);
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

    @Test
    public void testGetConstruct() throws Exception {
        Map<String, Object> contructMap = priceService.getConstruct();
        assertEquals(true, contructMap.get("columModle") instanceof List);
    }

    /**  */
    @Test
    public void testGetConstructException() throws Exception {
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(UserInfoDao.class, "getGroupID",
                new EmptyResultDataAccessException(0));
        try {
            priceService.getConstruct();
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

    @Test
    public void testGetData() throws Exception {
        List<HashMap<String, Object>> HashMaps = priceService.getData(1, 10);
        Assert.assertEquals(HashMaps.get(0).get("id"), productID);
    }

    @Test
    public void testGetDataExceptionA() throws Exception {
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(UserInfoDao.class, "getAllProduct",
                new EmptyResultDataAccessException(0));
        try {
            priceService.getData(1, 10);
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

    @Test
    public void testGetDataExceptionB() throws Exception {
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(PriceDao.class, "getProductRange",
                new EmptyResultDataAccessException(0));
        try {
            priceService.getData(1, 10);
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

    @Test
    public void testGetProductCountByPrice() throws Exception {
        int count = priceService.getProductCountByPrice();
        Assert.assertEquals(count, 1);
    }

    @Test
    public void testGetProductCount() throws Exception {
        int count = priceService.getProductCount();
        Assert.assertEquals(count, 1);
    }

    @Test
    public void testGetProductCountException() throws Exception {
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(PriceDao.class, "getProductCount",
                new EmptyResultDataAccessException(0));
        try {
            priceService.getProductCount();
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

    @SuppressWarnings({ "unchecked" })
    @Test
    public void testGetProductDiscount() throws Exception {
        Map<String, Object> map = priceService.getProductDiscount(1, productID);
        Assert.assertEquals(((List<ProductPriceRangeDto>) map.get("priceRangeList")).get(0)
                .getDiscount(), "0.90");
    }

    @Test
    public void testGetProductDiscountException() throws Exception {
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(PriceDao.class, "getProductRange",
                new EmptyResultDataAccessException(0));
        try {
            priceService.getProductDiscount(4, 1);
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testDeletePriceByID() throws Exception {
        priceService.deletePriceByID(5, 1);
        Map<String, Object> map = priceService.getProductDiscount(1, productID);
        Assert.assertEquals(((List<ProductPriceRangeDto>) map.get("priceRangeList")).size(), 2);
    }

    @Test
    public void testDeletePrice() throws Exception {
        int count = priceService.getProductCountByPrice();
        Assert.assertEquals(count, 1);
        priceService.deletePrice(productID + "");
        count = priceService.getProductCountByPrice();
        Assert.assertEquals(count, 0);
    }

    @Test
    public void testDeletePriceException() throws Exception {

        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(PriceDao.class, "deletePrice",
                new EmptyResultDataAccessException(0));
        try {
            priceService.deletePrice("1");
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }
}
