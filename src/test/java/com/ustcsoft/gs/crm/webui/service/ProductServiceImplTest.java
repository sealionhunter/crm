/**
 * 
 */
package com.ustcsoft.gs.crm.webui.service;

import static org.junit.Assert.*;
import jp.co.dgic.testing.common.virtualmock.MockObjectManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.product.bean.ProductSearchBean;
import com.ustcsoft.gs.crm.webui.product.dao.ProductDao;
import com.ustcsoft.gs.crm.webui.product.dto.ProductDto;
import com.ustcsoft.gs.crm.webui.product.service.ProductService;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

/**
 * @author chenshengwei
 * 
 */
public class ProductServiceImplTest extends CRMTest {

    private static ProductService productService = null;
    private static ProductDto productDto = null;
    private static ProductSearchBean searchProductBean = null;
    public int start = 0;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        productService = (ProductService) CTX.getBean("productService");

        productDto = new ProductDto();
        productDto.setCategory("000500010001");
        productDto.setDescription("Description");
        productDto.setDiscount("");
        productDto.setName("name");
        productDto.setPrice("2000.00");
        // productDto.setPriceRanges(null);
        productDto.setProductID("20131118182544");
        productDto.setProductModel("model");
        productDto.setRemark("remark");

        searchProductBean = new ProductSearchBean();
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testAddOrUpdateProduct() throws Exception {

        productService.addOrUpdateProduct(productDto);
        productService.deleteProduct(productDto.getId() + "");

        // 异常测试
        productDto.setCategory("33333333333333333333333333333333333333333333333333333");
        productDto.setId(0);
        productService.addOrUpdateProduct(productDto);
    }

    @Test
    public void testDeleteProduct() throws Exception {
        productDto.setId(0);
        productDto.setCategory("000500010001");
        productService.addOrUpdateProduct(productDto);
        productService.deleteProduct(productDto.getId() + "");

        productService.deleteProduct("aa");
    }

    @Test
    public void testGetProduct() throws Exception {
        productDto.setId(0);
        productDto.setCategory("000500010001");
        productService.addOrUpdateProduct(productDto);
        productService.getProduct(productDto.getId());
        productService.deleteProduct(productDto.getId() + "");

        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(ProductDao.class, "getProduct",
                new EmptyResultDataAccessException(0));
        try {
            productService.getProduct(-111111111);
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

    @Test
    public void testGetAllProduct() throws Exception {
        productService.getAllProduct(1, 25);

        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(ProductDao.class, "getAllProduct",
                new EmptyResultDataAccessException(0));
        try {
            productService.getAllProduct(1, 25);
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

    @Test
    public void testGetAllProductByUser() throws Exception {
        productService.getAllProductByUser(2, 1, 25);

        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(ProductDao.class, "getAllProductByUser",
                new EmptyResultDataAccessException(0));
        try {
            productService.getAllProductByUser(2, 1, 25);
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

    @Test
    public void testSearchProduct() throws Exception {
        searchProductBean.setSearchText("dd");
        productService.searchProduct(searchProductBean, 1, 1, 25);
        searchProductBean
                .setCategory("444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%444444%%%%%%%%");
        productService.searchProduct(searchProductBean, 2, 1, 25);

        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(ProductDao.class, "searchProduct",
                new EmptyResultDataAccessException(0));
        try {
            productService.searchProduct(searchProductBean, 2, 1, 25);
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

    @Test
    public void testGetProductCount() throws Exception {
        productService.getProductCount();

        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(ProductDao.class, "getProductCount",
                new EmptyResultDataAccessException(0));
        try {
            productService.getProductCount();
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
