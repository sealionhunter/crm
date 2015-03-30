/*
 * Class name: ProductServiceImpl
 * 
 * Version information: 1.0
 * 
 * Date:2013.9.12
 *  
 */
package com.ustcsoft.gs.crm.webui.product.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.index.action.WorkAction;
import com.ustcsoft.gs.crm.webui.product.bean.ProductSearchBean;
import com.ustcsoft.gs.crm.webui.product.dao.PriceDao;
import com.ustcsoft.gs.crm.webui.product.dao.ProductDao;
import com.ustcsoft.gs.crm.webui.product.dto.ProductDto;
import com.ustcsoft.gs.crm.webui.product.service.ProductService;

/**
 * a implement of ProductService
 * 
 * @author chenshengwei
 * 
 */
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    private PriceDao priceDao;

    /**
     * @return the priceDao
     */
    public PriceDao getPriceDao() {
        return priceDao;
    }

    /**
     * @param priceDao
     *            the priceDao to set
     */
    public void setPriceDao(PriceDao priceDao) {
        this.priceDao = priceDao;
    }

    /** used for recording log */
    private static final Log LOG = LogFactory.getLog(WorkAction.class);

    /**
     * @return the productDao
     */
    public ProductDao getProductDao() {
        return productDao;
    }

    /**
     * @param productDao
     *            the productDao to set
     */
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    /**
     * add or update product
     * 
     * @param product
     *            ProductDto
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public void addOrUpdateProduct(ProductDto product) throws CRMDBException {
        try {
            LOG.debug("method addOrUpdateProduct start!");
            productDao.addOrUpdateProduct(product);
            LOG.debug("method addOrUpdateProduct end!");
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method addOrUpdateProduct!", e);
            throw new CRMDBException(e);
        }
    }

    /**
     * delete product
     * 
     * @param productID
     *            product productID
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public Map<String, Object> deleteProduct(String productID) throws CRMDBException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            LOG.debug("method deleteProduct start!");
            List<Integer> selectProductIds = productDao.checkOrder(CRMUtils
                    .getStringForDelete(productID));
            String[] productIDs = productID.split(",");
            if (selectProductIds.size() == 0) {
                productDao.deleteProduct(CRMUtils.getStringForDelete(productID));
                priceDao.deletePrice(CRMUtils.getStringForDelete(productID));
            } else {
                String errorString = "";
                outFor: for (int i = 0; i < productIDs.length; i++) {
                    for (int j = 0; j < selectProductIds.size(); j++) {
                        if (Integer.parseInt(productIDs[i].trim()) == selectProductIds.get(j)) {
                            errorString = errorString
                                    + HtmlUtils.htmlUnescape(productDao.getProduct(
                                            selectProductIds.get(j)).getName());
                            break outFor;
                        }
                    }
                }
                map.put("errorMessage", errorString + "正在被订单模块使用，无法删除！");
            }
            LOG.debug("method deleteProduct end!");
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method deleteProduct!", e);
            throw new CRMDBException(e);
        }
        return map;
    }

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
    @Override
    public ProductDto getProduct(int id) throws CRMDBException {
        try {
            LOG.debug("method getProduct start!");
            return productDao.getProduct(id);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getProduct!", e);
            throw new CRMDBException(e);
        }
    }

    /**
     * get all product information
     * 
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
    @Override
    public Map<String, Object> getAllProduct(int currpage, int limit) throws CRMDBException {
        try {
            LOG.debug("method getAllProduct start!");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("items", productDao.getAllProduct(currpage, limit));
            map.put("total", productDao.getProductCount());
            LOG.debug("method getAllProduct end!");
            return map;
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getAllProduct!", e);
            throw new CRMDBException(e);
        }
    }

    @Override
    public Map<String, Object> getAllProductByUser(int userID, int currpage, int limit)
            throws CRMDBException {
        try {
            LOG.debug("method getAllProduct start!");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("items", productDao.getAllProductByUser(userID, currpage, limit));
            map.put("total", productDao.getProductCount());
            LOG.debug("method getAllProduct end!");
            return map;
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getAllProduct!", e);
            throw new CRMDBException(e);
        }
    }

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
    @Override
    public Map<String, Object> searchProduct(ProductSearchBean searchProductBean, int searchFlag,
            int start, int limit) throws CRMDBException {
        try {
            LOG.debug("method searchProduct start!");
            return productDao.searchProduct(searchProductBean, searchFlag, start, limit);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method searchProduct!", e);
            throw new CRMDBException(e);
        }
    }

    /**
     * get product count
     * 
     * @return int count count of product
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public int getProductCount() throws CRMDBException {
        try {
            LOG.debug("method getProductCount start!");
            return productDao.getProductCount();
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getProductCount!", e);
            throw new CRMDBException(e);
        }
    }
}
