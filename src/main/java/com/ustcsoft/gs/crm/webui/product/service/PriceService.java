package com.ustcsoft.gs.crm.webui.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.product.bean.PriceBean;
import com.ustcsoft.gs.crm.webui.product.bean.PriceSearchBean;

public interface PriceService {

    /**
     * get price grid list construct
     * 
     * @return map of Construct
     */
    public Map<String, Object> getConstruct() throws CRMDBException;

    /**
     * get price grid list data
     * 
     * @param currpage
     * @param limit
     * @return list of price of product
     */
    public List<HashMap<String, Object>> getData(int currpage, int limit) throws CRMDBException;

    /**
     * get product count
     * 
     * @return int of product count
     */
    public int getProductCount() throws CRMDBException;

    /**
     * search product by priceSearchBean
     * 
     * @param priceSearchBean
     * @param searchFlag
     * @param start
     * @param limit
     * @return list of product by search
     */
    public List<HashMap<String, Object>> searchProduct(PriceSearchBean priceSearchBean,
            int searchFlag, int start, int limit) throws CRMDBException;

    /**
     * get search product count
     * 
     * @param priceSearchBean
     * @param searchFlag
     * @param start
     * @param limit
     * @return integer of product count
     */
    public int searchProductCount(PriceSearchBean priceSearchBean, int searchFlag, int start,
            int limit) throws CRMDBException;

    /**
     * get product discount by groupID and productID
     * 
     * @param groupID
     * @param productID
     * @return map of product discount
     */
    public Map<String, Object> getProductDiscount(int groupID, int productID) throws CRMDBException;

    /**
     * save or update product's discount
     * 
     * @param priceBeans
     */
    public void updatePriceDiscount(List<PriceBean> priceBeans) throws CRMDBException;

    /**
     * delete product's discount
     * 
     * @param jsonString
     */
    public void deletePrice(String jsonString) throws CRMDBException;

    /**
     * @param groupID
     * @param productID
     */
    public void deletePriceByID(int groupID, int productID);

    /**
     * @return integer of product count by price
     */
    public int getProductCountByPrice();
}
