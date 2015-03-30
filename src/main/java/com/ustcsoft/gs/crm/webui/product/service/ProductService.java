/*
 * Class name: ProductService
 * 
 * Version information: 1.0
 * 
 * Date:2013.9.12
 *  
 */
package com.ustcsoft.gs.crm.webui.product.service;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.product.bean.ProductSearchBean;
import com.ustcsoft.gs.crm.webui.product.dto.ProductDto;

/**
 * For Product information's service operation
 * 
 * @author chenshengwei
 * 
 */
public interface ProductService {
    /**
     * add or update product
     * 
     * @param product
     *            ProductDto
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void addOrUpdateProduct(ProductDto product) throws CRMDBException;

    /**
     * delete product
     * 
     * @param productID
     *            product productID
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> deleteProduct(String productID) throws CRMDBException;

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
    public ProductDto getProduct(int id) throws CRMDBException;

    /**
     * get all product information
     * 
     * @param currpage
     *            current page
     * 
     *            *@param limit pageSize of page
     * @return List<ProductDto> product information list
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> getAllProduct(int currpage, int limit) throws CRMDBException;

    public Map<String, Object> getAllProductByUser(int userID, int currpage, int limit)
            throws CRMDBException;

    /**
     * get all product information
     * 
     * @param searchProductBean
     *            searchinformation from web page
     * 
     * @param searchFlag
     *            search flag
     * 
     * @param start
     *            start of page * @param limit pageSize of page
     * 
     * @return List<ProductDto> product information list
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> searchProduct(ProductSearchBean searchProductBean, int searchFlag,
            int start, int limit) throws CRMDBException;

    /**
     * get product count
     * 
     * @return count count of product
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public int getProductCount() throws CRMDBException;
}
