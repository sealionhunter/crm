package com.ustcsoft.gs.crm.webui.product.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.product.bean.PriceBean;
import com.ustcsoft.gs.crm.webui.product.bean.PriceSearchBean;
import com.ustcsoft.gs.crm.webui.product.constant.ProductConstant;
import com.ustcsoft.gs.crm.webui.product.dao.PriceDao;
import com.ustcsoft.gs.crm.webui.product.dao.ProductDao;
import com.ustcsoft.gs.crm.webui.product.dto.ProductDto;
import com.ustcsoft.gs.crm.webui.product.dto.ProductPriceRangeDto;
import com.ustcsoft.gs.crm.webui.product.service.PriceService;
import com.ustcsoft.gs.crm.webui.system.dao.UserInfoDao;
import com.ustcsoft.gs.crm.webui.system.dto.GroupManagerDto;

public class PriceServiceImpl implements PriceService {

    /** used for priceDao */
    private PriceDao priceDao = null;

    /** used for userInfoDao */
    private UserInfoDao userInfoDao = null;

    /** used for productDao */
    private ProductDao productDao = null;

    /** used for recording log */
    private static Log LOG = LogFactory.getLog(PriceServiceImpl.class);

    /**
     * set priceDao
     * 
     * @param priceDao
     */
    public void setPriceDao(PriceDao priceDao) {
        this.priceDao = priceDao;
    }

    /**
     * set userInfoDao
     * 
     * @param userInfoDao
     */
    public void setUserInfoDao(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    /**
     * set productDao
     * 
     * @param productDao
     */
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    /**
     * get grid list header
     * 
     * return construct Map<String, Object>,grid list header information
     * 
     * @throws CRMDBException
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getConstruct() throws CRMDBException {
        LOG.debug("method getConstruct start!");
        List<HashMap<String, Object>> colunmsMapList = new ArrayList<HashMap<String, Object>>();
        List<HashMap<String, Object>> fieldsMapList = new ArrayList<HashMap<String, Object>>();
        Map<String, Object> colunmsMap = new HashMap<String, Object>();
        Map<String, Object> fieldMap = new HashMap<String, Object>();
        // stationary header
        colunmsMap.put(ProductConstant.HEADER, "序号");
        colunmsMap.put(ProductConstant.DATAINDEX, "number");
        colunmsMap.put(ProductConstant.SORTABLE, false);
        colunmsMap.put(ProductConstant.ALIGN, "right");
        colunmsMap.put(ProductConstant.WIDTH, 40);
        fieldMap.put(ProductConstant.NAME, "number");
        colunmsMapList.add((HashMap<String, Object>) colunmsMap);
        fieldsMapList.add((HashMap<String, Object>) fieldMap);
        colunmsMap = new HashMap<String, Object>();
        fieldMap = new HashMap<String, Object>();
        colunmsMap.put(ProductConstant.DATAINDEX, "id");
        colunmsMap.put(ProductConstant.HIDDEN, true);
        colunmsMap.put(ProductConstant.SORTABLE, false);
        colunmsMap.put(ProductConstant.FLEX, 1);
        fieldMap.put(ProductConstant.NAME, "id");
        colunmsMapList.add((HashMap<String, Object>) colunmsMap);
        fieldsMapList.add((HashMap<String, Object>) fieldMap);
        colunmsMap = new HashMap<String, Object>();
        fieldMap = new HashMap<String, Object>();
        colunmsMap.put(ProductConstant.HEADER, "产品名称");
        colunmsMap.put(ProductConstant.DATAINDEX, "name");
        colunmsMap.put(ProductConstant.SORTABLE, false);
        colunmsMap.put(ProductConstant.MINWIDTH, 100);
        colunmsMap.put(ProductConstant.FLEX, 1);
        fieldMap.put(ProductConstant.NAME, "name");
        colunmsMapList.add((HashMap<String, Object>) colunmsMap);
        fieldsMapList.add((HashMap<String, Object>) fieldMap);
        colunmsMap = new HashMap<String, Object>();
        fieldMap = new HashMap<String, Object>();
        colunmsMap.put(ProductConstant.HEADER, "标准报价（元）");
        colunmsMap.put(ProductConstant.MINWIDTH, 100);
        colunmsMap.put(ProductConstant.DATAINDEX, "price");
        colunmsMap.put(ProductConstant.SORTABLE, true);
        colunmsMap.put(ProductConstant.FLEX, 1);
        fieldMap.put(ProductConstant.NAME, "price");
        colunmsMapList.add((HashMap<String, Object>) colunmsMap);
        fieldsMapList.add((HashMap<String, Object>) fieldMap);
        colunmsMap = new HashMap<String, Object>();
        fieldMap = new HashMap<String, Object>();
        // get groups where groupID greater than 2
        Map<String, Object> groups;
        try {
            groups = userInfoDao.getGroupID(ProductConstant.GROUPID);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getProductCount!", e);
            throw new CRMDBException(e);
        }
        ArrayList<GroupManagerDto> groupList = (ArrayList<GroupManagerDto>) groups
                .get(ProductConstant.ITEMS);
        for (int i = 0; i < groupList.size() && i < 8; i++) {
            colunmsMap.put(ProductConstant.HEADER, groupList.get(i).getGroupName());
            colunmsMap.put(ProductConstant.DATAINDEX, groupList.get(i).getGroupID());
            colunmsMap.put(ProductConstant.SORTABLE, false);
            colunmsMap.put(ProductConstant.MINWIDTH, 100);
            colunmsMap.put(ProductConstant.FLEX, 1);
            colunmsMapList.add((HashMap<String, Object>) colunmsMap);
            fieldMap.put(ProductConstant.NAME, groupList.get(i).getGroupID());
            fieldsMapList.add((HashMap<String, Object>) fieldMap);
            colunmsMap = new HashMap<String, Object>();
            fieldMap = new HashMap<String, Object>();
        }
        Map<String, Object> construct = new HashMap<String, Object>();
        construct.put(ProductConstant.COLUMMODLE, colunmsMapList);
        construct.put(ProductConstant.FIELDSNAMES, fieldsMapList);
        LOG.debug("method getConstruct end!");
        return construct;
    }

    /**
     * get price grid list data
     * 
     * @param currpage
     * @param limit
     * @return dataMapList List<HashMap<String, Object>>,grid list data
     *         information
     */
    @Override
    public List<HashMap<String, Object>> getData(int currpage, int limit) throws CRMDBException {
        LOG.debug("method getData start!");
        List<HashMap<String, Object>> dataMapList = new ArrayList<HashMap<String, Object>>();
        // 查询产品
        List<ProductDto> productList;
        try {
            productList = priceDao.getAllProduct(currpage, limit);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getData!", e);
            throw new CRMDBException(e);
        }
        int number = currpage * limit - 24;
        for (int i = 0; i < productList.size(); i++) {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("number", number);
            number++;
            data.put("id", productList.get(i).getId());
            data.put("name", productList.get(i).getName());
            data.put("price", productList.get(i).getPrice());
            try {
                List<ProductPriceRangeDto> priceRangeList = priceDao.getProductRange(productList
                        .get(i).getId());

                for (int j = 0; j < priceRangeList.size(); j++) {
                    data.put(String.valueOf(priceRangeList.get(j).getGroupID()), priceRangeList
                            .get(j).getDiscount());
                }
            } catch (DataAccessException e) {
                LOG.error("DataAccessException occurs in method getData!", e);
                throw new CRMDBException(e);
            }
            dataMapList.add((HashMap<String, Object>) data);
        }
        LOG.debug("method getData end!");
        return dataMapList;
    }

    /**
     * get product count
     * 
     * @return productCount int,price grid list
     */
    @Override
    public int getProductCount() throws CRMDBException {
        LOG.debug("method getProductCount start!");
        int productCount;
        try {
            productCount = productDao.getProductCount();
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getProductCount!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getProductCount end!");
        return productCount;
    }

    /**
     * search product by priceSearchBean
     * 
     * @param priceSearchBean
     * @param searchFlag
     * @param start
     * @param limit
     * @return dataMapList List<HashMap<String, Object>>,search product's price
     *         discount information
     */
    @Override
    public List<HashMap<String, Object>> searchProduct(PriceSearchBean priceSearchBean,
            int searchFlag, int start, int limit) throws CRMDBException {
        LOG.debug("method searchProduct start!");
        List<HashMap<String, Object>> dataMapList = new ArrayList<HashMap<String, Object>>();
        List<ProductDto> productList;
        try {
            productList = priceDao.searchPrice(priceSearchBean, searchFlag, start, limit);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method searchProduct!", e);
            throw new CRMDBException(e);
        }
        int number = start + 1;
        for (int i = 0; i < productList.size(); i++) {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("number", number);
            number++;
            data.put("id", productList.get(i).getId());
            data.put("name", productList.get(i).getName());
            data.put("price", productList.get(i).getPrice());
            List<ProductPriceRangeDto> priceRangeList = priceDao.getProductRange(productList.get(i)
                    .getId());
            for (int j = 0; j < priceRangeList.size(); j++) {
                data.put(String.valueOf(priceRangeList.get(j).getGroupID()), priceRangeList.get(j)
                        .getDiscount());
            }
            dataMapList.add((HashMap<String, Object>) data);
        }
        LOG.debug("method searchProduct end!");
        return dataMapList;
    }

    /**
     * get product discount by groupID and productID
     * 
     * @param groupID
     * @return map Map<String, Object>,product information and product price
     *         discount information
     */
    @Override
    public Map<String, Object> getProductDiscount(int groupID, int productID) throws CRMDBException {
        LOG.debug("method getProductDiscount start!");
        Map<String, Object> groupList;
        List<ProductPriceRangeDto> priceRangeList;
        try {
            groupList = userInfoDao.getGroupID(groupID);
            priceRangeList = priceDao.getProductRange(productID);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getProductDiscount!", e);
            throw new CRMDBException(e);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("groupList", groupList);
        map.put("priceRangeList", priceRangeList);
        LOG.debug("method getProductDiscount end!");
        return map;
    }

    /**
     * get search product count
     * 
     * @param priceSearchBean
     * @param searchFlag
     * @param start
     * @param limit
     * @return integer of searched product count
     */
    @Override
    public int searchProductCount(PriceSearchBean priceSearchBean, int searchFlag, int start,
            int limit) throws CRMDBException {
        LOG.debug("method searchProductCount start!");
        int count;
        try {
            count = priceDao.searchPriceCount(priceSearchBean, searchFlag, start, limit);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method searchProductCount!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method searchProductCount end!");
        return count;
    }

    /**
     * save or update product's price discount
     * 
     * @param priceBeans
     *            product's price discount list
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public void updatePriceDiscount(List<PriceBean> priceBeans) throws CRMDBException {
        LOG.debug("method updatePriceDiscount start!");
        try {
            priceDao.updatePriceDiscount(priceBeans);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method updatePriceDiscount!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method updatePriceDiscount end!");
    }

    /**
     * delete product's discount
     * 
     * @param productIDs
     *            product's id array string
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public void deletePrice(String productIDs) throws CRMDBException {
        LOG.debug("method deletePrice start!");
        try {
            priceDao.deletePrice(CRMUtils.getStringForDelete(productIDs));
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method deletePrice!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method deletePrice end!");
    }

    /**
     * delete priceRangeDto by groupID and productID
     * 
     * @param groupID
     * @param productID
     */
    @Override
    public void deletePriceByID(int groupID, int productID) {
        priceDao.deletePriceByID(groupID, productID);
    }

    /**
     * get product count by id in priceRangeDto
     * 
     */
    @Override
    public int getProductCountByPrice() {
        int count = priceDao.getProductCountByPrice();
        return count;
    }
}
