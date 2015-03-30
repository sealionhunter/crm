/*
 * Class name: ProductDao
 * 
 * Version information: 1.0
 * 
 * Date:2013.9.12
 *  
 */
package com.ustcsoft.gs.crm.webui.product.dao;

import java.util.List;
import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.product.bean.ProductSearchBean;
import com.ustcsoft.gs.crm.webui.product.dto.ProductDto;

/**
 * For Product information's dao operation
 * 
 * @author chenshengwei
 * 
 */
public interface ProductDao {

    /**
     * add or update product
     * 
     * @param productDto
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void addOrUpdateProduct(ProductDto productDto);

    /**
     * delete product
     * 
     * @param productID
     *            product productID
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void deleteProduct(String productID);

    /**
     * get single product information
     * 
     * @param id
     *            id of product
     * 
     * @return ProductDto product infromation
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public ProductDto getProduct(int id);

    /**
     * get all product information
     * 
     * @param currpage
     *            current page
     * 
     * @param limit
     *            pageSize of page
     * 
     * @return List<ProductDto> product information list
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public List<ProductDto> getAllProduct(int currpage, int limit);

    public List<ProductDto> getAllProductByUser(int userID, final int currpage, final int limit);

    /**
     * get all product information
     * 
     * @param searchProductBean
     *            productBean searchinformation from web page
     * 
     * @param searchFlag
     *            search flag
     * 
     * @param start
     *            start of page
     * @param limit
     *            pageSize of page
     * 
     * @return List<ProductDto> product information list
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> searchProduct(ProductSearchBean searchProductBean, int searchFlag,
            int start, int limit);

    /**
     * get product count
     * 
     * @return int count count of product
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public int getProductCount();

    /**
     * @param stringForDelete
     * @return
     */
    public List<Integer> checkOrder(String stringForDelete);
}
