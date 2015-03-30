package com.ustcsoft.gs.crm.webui.product.dao;

import java.util.List;

import com.ustcsoft.gs.crm.webui.product.bean.PriceBean;
import com.ustcsoft.gs.crm.webui.product.bean.PriceSearchBean;
import com.ustcsoft.gs.crm.webui.product.dto.ProductDto;
import com.ustcsoft.gs.crm.webui.product.dto.ProductPriceRangeDto;

/**
 * interface for price
 * 
 * @author zhangchuanhong
 */
public interface PriceDao {
    /**
     * get product price range by id
     * 
     * @param id
     * @return List<ProductPriceRangeDto>
     */
    public List<ProductPriceRangeDto> getProductRange(int id);

    /**
     * search price by searchBean
     * 
     * @param priceSearchBean
     * @param searchFlag
     * @param start
     * @param limit
     * @return List<ProductDto>
     */
    public List<ProductDto> searchPrice(PriceSearchBean priceSearchBean, int searchFlag, int start,
            int limit);

    /**
     * update price discount
     * 
     * @param priceBeans
     */
    public void updatePriceDiscount(List<PriceBean> priceBeans);

    /**
     * delete price discount
     * 
     * @param productIDs
     */
    public void deletePrice(String productIDs);

    /**
     * get search product by priceSearchBean count
     * 
     * @param priceSearchBean
     * @param searchFlag
     * @param start
     * @param limit
     * @return int
     */
    public int searchPriceCount(PriceSearchBean priceSearchBean, int searchFlag, int start,
            int limit);

    /**
     * @param groupID
     * @param productID
     */
    public void deletePriceByID(int groupID, int productID);

    /**
     * get productDto list count where product has a priceRange
     * 
     */
    public int getProductCountByPrice();

    /**
     * get productDto list where product has a priceRange
     * 
     * @param currpage
     * @param limit
     * @return List<ProductDto>
     */
    public List<ProductDto> getAllProduct(int currpage, int limit);
}
