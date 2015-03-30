/*
 * Class name: ProductDaoImpl
 * 
 * Version information: 1.0
 * 
 * Date:2013.9.12
 *  
 */
package com.ustcsoft.gs.crm.webui.product.dao.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.product.bean.ProductSearchBean;
import com.ustcsoft.gs.crm.webui.product.constant.ProductConstant;
import com.ustcsoft.gs.crm.webui.product.dao.ProductDao;
import com.ustcsoft.gs.crm.webui.product.dto.ProductDto;
import com.ustcsoft.gs.crm.webui.product.dto.ProductPriceRangeDto;

/**
 * implement of ProductDao
 * 
 * @author chenshengwei
 * 
 */
public class ProductDaoImpl implements ProductDao {

    private HibernateTemplate hibernateTemplate;

    private static final Log LOG = LogFactory.getLog(ProductDaoImpl.class);

    /**
     * get hibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    /**
     * @param hibernateTemplate
     *            the hibernateTemplate to set
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate)
            throws DataAccessException {
        this.hibernateTemplate = hibernateTemplate;
    }

    /**
     * add or update product
     * 
     * @param productDto
     *            ProductDto
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public void addOrUpdateProduct(ProductDto productDto) throws DataAccessException {
        hibernateTemplate.saveOrUpdate(productDto);
    }

    /**
     * delete product
     * 
     * @param productID
     *            productID
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public void deleteProduct(final String productID) throws DataAccessException {
        hibernateTemplate.bulkUpdate(ProductConstant.PRODUCT_DELETE + productID);
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
    @SuppressWarnings("unchecked")
    public ProductDto getProduct(int id) throws DataAccessException {
        LOG.debug("method getProduct start!");
        ProductDto product = hibernateTemplate.get(ProductDto.class, id);
        List<ProductPriceRangeDto> priceRanges = hibernateTemplate.find(
                ProductConstant.PRODUCT_GET, id);
        product.setPriceRanges(priceRanges);
        LOG.debug("method getProduct end!");
        return product;
    }

    /**
     * get all product information
     * 
     * @param currpage
     *            current page
     * 
     *            * @param int limit pageSize of page
     * 
     * @return List<ProductDto> product information list
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<ProductDto> getAllProduct(final int currpage, final int limit)
            throws DataAccessException {
        LOG.debug("method getAllProduct start!");
        List<ProductDto> productList = hibernateTemplate.executeFind(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) {
                String sql = ProductConstant.PRODUCT_GET_ALL;
                return session.createQuery(sql).setFirstResult((currpage - 1) * limit)
                        .setMaxResults(limit).list();
            }
        });
        for (ProductDto product : productList) {
            List<ProductPriceRangeDto> priceRanges = hibernateTemplate.find(
                    ProductConstant.PRODUCT_GET_ALL_PRICE, product.getId());
            product.setPriceRanges(priceRanges);
        }
        LOG.debug("method getAllProduct end!");
        return productList;
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<ProductDto> getAllProductByUser(int userID, final int currpage, final int limit)
            throws DataAccessException {
        LOG.debug("method getAllProduct start!");
        List<ProductDto> productList = hibernateTemplate.executeFind(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) {
                String sql = ProductConstant.PRODUCT_GET_ALL;
                return session.createQuery(sql).setFirstResult((currpage - 1) * limit)
                        .setMaxResults(limit).list();
            }
        });
        for (ProductDto product : productList) {
            List<ProductPriceRangeDto> priceRanges = hibernateTemplate.find(
                    ProductConstant.PRODUCT_GET_ALL_PRICEBYUSER, new Object[] { product.getId(),
                            userID });
            double rangePrice = Double.parseDouble(product.getPrice());
            if (priceRanges != null && priceRanges.size() > 0) {
                ProductPriceRangeDto priceRange = priceRanges.get(0);
                rangePrice = Double.parseDouble(priceRange.getDiscount())
                        * Double.parseDouble(product.getPrice());
            }
            DecimalFormat df = new DecimalFormat("0.00");
            product.setDiscount(df.format(rangePrice));
        }
        LOG.debug("method getAllProduct end!");
        return productList;
    }

    /**
     * get product count
     * 
     * @return int count count of product
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public int getProductCount() throws DataAccessException {
        LOG.debug("method getAllProduct start!");
        @SuppressWarnings("unchecked")
        List<Integer> list = getHibernateTemplate().find(ProductConstant.PRODUCT_GET_ALL_COUNT);
        Number number = list.get(0);
        LOG.debug("method getAllProduct end!");
        return number.intValue();
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
    @SuppressWarnings("unchecked")
    public Map<String, Object> searchProduct(ProductSearchBean searchProductBean, int searchFlag,
            int start, int limit) throws DataAccessException {
        LOG.debug("method searchProduct start!");
        Map<String, Object> map = new HashMap<String, Object>();
        List<ProductDto> productDtos = new ArrayList<ProductDto>();
        String searchString = "%" + searchProductBean.getSearchText() + "%";
        String searchHql = new String();
        String searchCountHql = new String();
        if (searchFlag == 1) {
            List<Object> valueObjects = new ArrayList<Object>();
            searchHql = "from ProductDto product where product.name like ?";
            searchCountHql = "select count(*) from ProductDto product where product.name like ?";
            valueObjects.add(searchString);
            searchHql = searchHql + " or product.productID like ?";
            searchCountHql = searchCountHql + " or product.productID like ?";
            valueObjects.add(searchString);
            searchHql = searchHql + " or product.productModel like ?";
            searchCountHql = searchCountHql + " or product.productModel like ?";
            valueObjects.add(searchString);
            if (searchProductBean.getSearchText().matches(ProductConstant.NUMBERFORMAT)
                    && !ProductConstant.EMPTYSTRING.equals(searchProductBean.getSearchText())) {
                searchHql = searchHql + " or product.price = ?";
                searchCountHql = searchCountHql + " or product.price = ?";
                valueObjects.add(searchProductBean.getSearchText());
            }
            searchHql = searchHql
                    + " or product.category in ( select code.code from CodeDto code where code.code like '00050001%' and length(code.code) = 12 and code.value like ?) order by product.id DESC";
            searchCountHql = searchCountHql
                    + " or product.category in ( select code.code from CodeDto code where code.code like '00050001%' and length(code.code) = 12 and code.value like ?)";
            valueObjects.add(searchString);
            Object[] valueObject = valueObjects.toArray();
            List<Integer> list = hibernateTemplate.find(searchCountHql, valueObject);
            Number number = list.get(0);
            map.put("total", number.intValue());
            productDtos = getListForPage(searchHql, valueObject, start, limit);
            map.put("items", productDtos);
        } else if (searchFlag == 2) {
            List<Object> valueObjects = new ArrayList<Object>();
            searchHql = "from ProductDto product where product.price >= ?";
            searchCountHql = "select count(*) from ProductDto product where product.price >= ?";
            if (!"".equals(searchProductBean.getMinPrice())
                    && searchProductBean.getMinPrice() != null) {
                valueObjects.add(searchProductBean.getMinPrice());
            } else {
                valueObjects.add("0.0");
            }
            if (!"".equals(searchProductBean.getProductName())
                    && searchProductBean.getProductName() != null) {
                searchHql = searchHql + " and product.name like ?";
                searchCountHql = searchCountHql + " and product.name like ?";
                valueObjects.add("%" + searchProductBean.getProductName() + "%");
            }
            if (!"".equals(searchProductBean.getProductID())
                    && searchProductBean.getProductID() != null) {
                searchHql = searchHql + " and product.productID like ?";
                searchCountHql = searchCountHql + " and product.productID like ?";
                valueObjects.add("%" + searchProductBean.getProductID() + "%");
            }
            if (!"".equals(searchProductBean.getProductModel())
                    && searchProductBean.getProductModel() != null) {
                searchHql = searchHql + " and product.productModel like ?";
                searchCountHql = searchCountHql + " and product.productModel like ?";
                valueObjects.add("%" + searchProductBean.getProductModel() + "%");
            }
            if (!"0".equals(searchProductBean.getCategory())
                    && searchProductBean.getCategory() != null) {
                searchHql = searchHql + " and product.category = ?";
                searchCountHql = searchCountHql + " and product.category = ?";
                valueObjects.add(searchProductBean.getCategory());
            }
            if (!"".equals(searchProductBean.getMaxPrice())
                    && searchProductBean.getMaxPrice() != null) {
                searchHql = searchHql + " and product.price <= ?";
                searchCountHql = searchCountHql + " and product.price <= ?";
                valueObjects.add(searchProductBean.getMaxPrice());
            }
            searchHql = searchHql + " order by product.id DESC";
            Object[] valueObject = valueObjects.toArray();
            List<Integer> list = hibernateTemplate.find(searchCountHql, valueObject);
            Number number = list.get(0);
            map.put("total", number.intValue());
            productDtos = getListForPage(searchHql, valueObject, start, limit);
            map.put("items", productDtos);
        }
        LOG.debug("method searchProduct end!");
        return map;
    }

    @SuppressWarnings("unchecked")
    public List<ProductDto> getListForPage(final String hql, final Object[] params,
            final int start, final int limit) {
        List<ProductDto> list = hibernateTemplate.executeFind(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) {
                Query query = session.createQuery(hql);
                int i = 0;
                for (Object param : params) {
                    query.setParameter(i, param);
                    i++;
                }
                query.setFirstResult(start);
                query.setMaxResults(limit);
                List<ProductDto> list = query.list();
                return list;
            }
        });
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Integer> checkOrder(String deleteIDs) {
        LOG.debug("method checkOrder start!");
        List<Integer> selectProductDtos = hibernateTemplate
                .find("select distinct selectProduct.id from SelectProductDto  selectProduct where selectProduct.isAbolished = 0 and selectProduct.id in "
                        + deleteIDs);
        LOG.debug("method checkOrder end!");
        return selectProductDtos;
    }
}
