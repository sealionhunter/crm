package com.ustcsoft.gs.crm.webui.product.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.product.bean.PriceBean;
import com.ustcsoft.gs.crm.webui.product.bean.PriceSearchBean;
import com.ustcsoft.gs.crm.webui.product.constant.ProductConstant;
import com.ustcsoft.gs.crm.webui.product.dao.PriceDao;
import com.ustcsoft.gs.crm.webui.product.dto.ProductDto;
import com.ustcsoft.gs.crm.webui.product.dto.ProductPriceRangeDto;

/**
 * price dao implement class
 * 
 * @author zhangchuanhong
 */
public class PriceDaoImpl implements PriceDao {

    /** used for recording log */
    private static Log LOG = LogFactory.getLog(PriceDaoImpl.class);

    /** used for HibernateTemplate */
    private HibernateTemplate hibernateTemplate = null;

    /**
     * get HibernateTemplate
     * 
     * @return HibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    /**
     * set HibernateTemplate
     * 
     * @param hibernateTemplate
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /**
     * get product price range by id
     * 
     * @param id
     * @return priceRangeDtos List<ProductPriceRangeDto>,price range class
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ProductPriceRangeDto> getProductRange(int id) {
        LOG.debug("method getProductRange start!");
        List<ProductPriceRangeDto> priceRangeDtos = hibernateTemplate.find(
                ProductConstant.GET_PRODUCT_RANGE_BYID, id);
        LOG.debug("method getProductRange end!");
        return priceRangeDtos;
    }

    /**
     * search price by searchBean
     * 
     * @param priceSearchBean
     * @param searchFlag
     * @param start
     * @param limit
     * @return productDtos List<ProductDto>,search productDto list
     */
    @Override
    public List<ProductDto> searchPrice(PriceSearchBean priceSearchBean, int searchFlag, int start,
            int limit) {
        LOG.debug("method searchPrice start!");
        List<ProductDto> productDtos = new ArrayList<ProductDto>();
        String searchHql;
        // 简单检索
        if (searchFlag == 1) {
            List<Object> paramList = new ArrayList<Object>();
            String searchString = ProductConstant.PERCENTSTRING + priceSearchBean.getSearchText()
                    + ProductConstant.PERCENTSTRING;
            searchHql = ProductConstant.SEARCH_PRODUCT_NAME;
            paramList.add(searchString);
            if (!ProductConstant.EMPTYSTRING.equals(priceSearchBean.getSearchText())
                    && priceSearchBean.getSearchText().matches(ProductConstant.NUMBERFORMAT)) {
                searchHql = searchHql + ProductConstant.SEARCH_PRODUCT_BYPRICE;
                paramList.add(priceSearchBean.getSearchText());
            }
            searchHql = searchHql + ProductConstant.SEARCH_PRODUCT_PRICE
                    + ProductConstant.RIGHT_PARENTHESIS + "order by product.id";
            Object[] params = paramList.toArray();
            productDtos = getListForPage(searchHql, params, start, limit);
        }
        // 复杂检索
        else if (searchFlag == 2) {
            List<Object> paramList = new ArrayList<Object>();
            searchHql = ProductConstant.SEARCH_PRODUCT_MINPRICE;
            if (!ProductConstant.EMPTYSTRING.equals(priceSearchBean.getMinPrice())
                    && priceSearchBean.getMinPrice() != null) {
                paramList.add(priceSearchBean.getMinPrice());
            } else {
                paramList.add(ProductConstant.PRICEMINDEFAULT);
            }
            if (!ProductConstant.EMPTYSTRING.equals(priceSearchBean.getProductName())
                    && priceSearchBean.getProductName() != null) {
                searchHql = searchHql + ProductConstant.SEARCH_PRODUCT_ADD_NAME;
                paramList.add(ProductConstant.PERCENTSTRING + priceSearchBean.getProductName()
                        + ProductConstant.PERCENTSTRING);
            }
            if (!ProductConstant.EMPTYSTRING.equals(priceSearchBean.getMaxPrice())
                    && priceSearchBean.getMaxPrice() != null) {
                searchHql = searchHql + ProductConstant.SEARCH_PRODUCT_MAXPRICE;
                paramList.add(priceSearchBean.getMaxPrice());
            }
            // search product id in productPriceRangeDto
            searchHql = searchHql + ProductConstant.SEARCH_PRODUCT_PRICE;
            if (priceSearchBean.getGroupID() != 0) {
                searchHql = searchHql + ProductConstant.SEARCH_PRODUCT_GROUPID;
                paramList.add(priceSearchBean.getGroupID());
                if (!ProductConstant.EMPTYSTRING.equals(priceSearchBean.getMinDiscount())
                        && priceSearchBean.getMinDiscount() != null) {
                    searchHql = searchHql + ProductConstant.SEARCH_PRODUCT_MINDISCOUNT;
                    paramList.add(priceSearchBean.getMinDiscount());
                }
                if (!ProductConstant.EMPTYSTRING.equals(priceSearchBean.getMaxDiscount())
                        && priceSearchBean.getMaxDiscount() != null) {
                    searchHql = searchHql + ProductConstant.SEARCH_PRODUCT_MAXDISCOUNT;
                    paramList.add(priceSearchBean.getMaxDiscount());
                }
            }
            searchHql = searchHql + ProductConstant.RIGHT_PARENTHESIS + "order by product.id";
            Object[] params = paramList.toArray();
            productDtos = getListForPage(searchHql, params, start, limit);
        }
        LOG.debug("method searchPrice end!");
        return productDtos;
    }

    /**
     * get productDto list for page
     * 
     * @param hql
     * @param start
     * @param limit
     * @return list List<ProductDto>,productDto list for page
     */
    @SuppressWarnings("unchecked")
    public List<ProductDto> getListForPage(final String hql, final int start, final int limit) {
        List<ProductDto> list = hibernateTemplate.executeFind(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) {
                Query query = session.createQuery(hql);
                query.setFirstResult(start);
                query.setMaxResults(limit);
                List<ProductDto> list = query.list();
                return list;
            }
        });
        return list;
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

    /**
     * save or update price discount
     * 
     * @param priceBeans
     *            priceBean list
     */
    @Override
    public void updatePriceDiscount(List<PriceBean> priceBeans) {
        LOG.debug("method updatePriceDiscount end!");
        for (PriceBean priceBean : priceBeans) {
            ProductPriceRangeDto priceRangeDto = new ProductPriceRangeDto();
            priceRangeDto.setDiscount(priceBean.getDiscount());
            priceRangeDto.setGroupID(priceBean.getGroupID());
            priceRangeDto.setId(priceBean.getId());
            priceRangeDto.setProductID(priceBean.getProductID());
            hibernateTemplate.saveOrUpdate(priceRangeDto);
        }
        LOG.debug("method updatePriceDiscount end!");
    }

    /**
     * delete price discount
     * 
     * @param productIDs
     *            string for delete product's id
     */
    @Override
    public void deletePrice(String productIDs) {
        LOG.debug("method deletePrice start!");
        hibernateTemplate.bulkUpdate(ProductConstant.DELETE_PRICE_RANGE_BYID + productIDs);
        LOG.debug("method deletePrice end!");
    }

    /**
     * get search product by priceSearchBean count
     * 
     * @param priceSearchBean
     * @param searchFlag
     * @param start
     * @param limit
     * @return int
     */
    @SuppressWarnings("unchecked")
    @Override
    public int searchPriceCount(PriceSearchBean priceSearchBean, int searchFlag, int start,
            int limit) {
        LOG.debug("method searchPrice start!");
        int count = 0;
        String searchString = ProductConstant.PERCENTSTRING + priceSearchBean.getSearchText()
                + ProductConstant.PERCENTSTRING;
        String searchHql = new String();
        // 简单检索
        if (searchFlag == 1) {
            List<Object> paramList = new ArrayList<Object>();
            searchHql = ProductConstant.SEARCH_COUNT_PRODUCT_NAME;
            paramList.add(searchString);
            if (!ProductConstant.EMPTYSTRING.equals(priceSearchBean.getSearchText())
                    && priceSearchBean.getSearchText().matches(ProductConstant.NUMBERFORMAT)) {
                searchHql = searchHql + ProductConstant.SEARCH_PRODUCT_BYPRICE;
                paramList.add(priceSearchBean.getSearchText());
            }
            searchHql = searchHql + ProductConstant.SEARCH_PRODUCT_PRICE
                    + ProductConstant.RIGHT_PARENTHESIS;
            Object[] params = paramList.toArray();
            List<Integer> list = hibernateTemplate.find(searchHql, params);
            Number number = list.get(0);
            count = number.intValue();
        }
        // 复杂检索
        else if (searchFlag == 2) {
            List<Object> paramList = new ArrayList<Object>();
            searchHql = ProductConstant.SEARCH_COUNT_PRODUCT_MINPRICE;
            if (!ProductConstant.EMPTYSTRING.equals(priceSearchBean.getMinPrice())
                    && priceSearchBean.getMinPrice() != null) {
                paramList.add(priceSearchBean.getMinPrice());
            } else {
                paramList.add(ProductConstant.PRICEMINDEFAULT);
            }
            if (!ProductConstant.EMPTYSTRING.equals(priceSearchBean.getProductName())
                    && priceSearchBean.getProductName() != null) {
                searchHql = searchHql + ProductConstant.SEARCH_PRODUCT_ADD_NAME;
                paramList.add(ProductConstant.PERCENTSTRING + priceSearchBean.getProductName()
                        + ProductConstant.PERCENTSTRING);
            }
            if (!ProductConstant.EMPTYSTRING.equals(priceSearchBean.getMaxPrice())
                    && priceSearchBean.getMaxPrice() != null) {
                searchHql = searchHql + ProductConstant.SEARCH_PRODUCT_MAXPRICE;
                paramList.add(priceSearchBean.getMaxPrice());
            }
            // search product id in productPriceRangeDto
            searchHql = searchHql + ProductConstant.SEARCH_PRODUCT_PRICE;
            if (priceSearchBean.getGroupID() != 0) {
                searchHql = searchHql + ProductConstant.SEARCH_PRODUCT_GROUPID;
                paramList.add(priceSearchBean.getGroupID());
                if (!ProductConstant.EMPTYSTRING.equals(priceSearchBean.getMinDiscount())
                        && priceSearchBean.getMinDiscount() != null) {
                    searchHql = searchHql + ProductConstant.SEARCH_PRODUCT_MINDISCOUNT;
                    paramList.add(priceSearchBean.getMinDiscount());
                }
                if (!ProductConstant.EMPTYSTRING.equals(priceSearchBean.getMaxDiscount())
                        && priceSearchBean.getMaxDiscount() != null) {
                    searchHql = searchHql + ProductConstant.SEARCH_PRODUCT_MAXDISCOUNT;
                    paramList.add(priceSearchBean.getMaxDiscount());
                }
            }
            searchHql = searchHql + ProductConstant.RIGHT_PARENTHESIS;
            Object[] params = paramList.toArray();
            List<Integer> list = hibernateTemplate.find(searchHql, params);
            Number number = list.get(0);
            count = number.intValue();
        }
        LOG.debug("method searchPrice end!");
        return count;
    }

    @Override
    public void deletePriceByID(int groupID, int productID) {
        hibernateTemplate.bulkUpdate(ProductConstant.DELETEPRICEBYID, groupID, productID);
    }

    /**
     * get productDto list count where product has a priceRange
     * 
     */
    @Override
    public int getProductCountByPrice() {
        LOG.debug("method getProductCountByPrice start!");
        @SuppressWarnings("unchecked")
        List<Integer> list = getHibernateTemplate().find(
                ProductConstant.PRODUCT_GET_PRODUCT_COUNT_PRICE);
        Number number = list.get(0);
        LOG.debug("method getProductCountByPrice end!");
        return number.intValue();
    }

    /**
     * get productDto list where product has a priceRange
     * 
     * @param currpage
     * @param limit
     * @return List<ProductDto>
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<ProductDto> getAllProduct(final int currpage, final int limit) {
        LOG.debug("method getAllProduct start!");
        List<ProductDto> productList = hibernateTemplate.executeFind(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) {
                String sql = ProductConstant.PRICE_GET_ALL;
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
}
