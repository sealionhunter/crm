/*
 * Class name: ProductAction
 * 
 * Version information: 1.0
 * 
 * Date:2013.9.12
 *  
 */
package com.ustcsoft.gs.crm.webui.product.action;

import org.springframework.dao.DataAccessException;
import org.springframework.web.util.HtmlUtils;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.product.dto.ProductDto;
import com.ustcsoft.gs.crm.webui.product.service.ProductService;

/**
 * For Product information's action operation
 * 
 * @author chenshengwei
 * 
 */
public class ProductAction extends CRMAction {

    private static final long serialVersionUID = 1L;

    /** product id. */
    private ProductService productService;

    /** product id. */
    private int id;

    /**
     * 
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the productService
     */
    public ProductService getProductService() {
        return productService;
    }

    /**
     * @param productService
     *            the id to set
     */
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    /**
     * add or update product
     * 
     * @return SUCCESS
     * @throws CRMDBException
     */
    public String addOrUpdateProduct() throws CRMDBException {
        try {
            LOG.debug("method addOrUpdateProduct start!");
            ProductDto productDto = (ProductDto) CRMUtils.jsonToBean(jsonString, ProductDto.class);
            productDto.setName(HtmlUtils.htmlEscapeDecimal(productDto.getName()));
            productService.addOrUpdateProduct(productDto);
            LOG.debug("method addOrUpdateProduct end!");
            return SUCCESS;
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method addOrUpdateProduct!", e);
            throw new CRMDBException(e);
        }
    }

    /**
     * delete product
     * 
     * @return SUCCESS
     * @throws CRMDBException
     */
    public String deletesProduct() throws CRMDBException {
        try {
            LOG.debug("method deletesProduct start!");
            map = productService.deleteProduct(jsonString);
            LOG.debug("method deletesProduct end!");
            return SUCCESS;
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method deletesProduct!", e);
            throw new CRMDBException(e);
        }
    }

    /**
     * get single product
     * 
     * @return 获取到单个产品
     * @throws CRMDBException
     */
    public String getProduct() throws CRMDBException {
        try {
            LOG.debug("method getProduct start!");
            map.put("item", productService.getProduct(id));
            LOG.debug("method getProduct end!");
            return SUCCESS;
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getProduct!", e);
            throw new CRMDBException(e);
        }
    }

    /**
     * get all product.
     * 
     * @return 获取产品列表
     * @throws CRMDBException
     */
    public String getAllProduct() throws CRMDBException {
        try {
            LOG.debug("method getAllProduct start!");
            map = productService.getAllProduct(page, limit);
            LOG.debug("method getAllProduct start!");
            return SUCCESS;
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getAllProduct!", e);
            throw new CRMDBException(e);
        }
    }

    public String getAllProductByUserID() throws CRMDBException {
        try {
            LOG.debug("method getAllProductByUser start!");
            map = productService.getAllProductByUser(userID, page, limit);
            LOG.debug("method getAllProductByUser start!");
            return SUCCESS;
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getAllProductByUser!", e);
            throw new CRMDBException(e);
        }
    }
}
