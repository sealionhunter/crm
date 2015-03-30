/*
 * Class name: ProductSearchAction
 * 
 * Version information: 1.0
 * 
 * Date:2013.9.12
 *  
 */
package com.ustcsoft.gs.crm.webui.product.action;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.product.bean.ProductSearchBean;
import com.ustcsoft.gs.crm.webui.product.service.ProductService;

/**
 * For Search Product information's action operation
 * 
 * @author chenshengwei
 * 
 */
public class ProductSearchAction extends CRMAction {

    private static final long serialVersionUID = -8851064863832380667L;

    private ProductService productService;

    /** product search page start . */
    private int start;

    /**
     * @return the start
     */
    public int getStart() {
        return start;
    }

    /**
     * @param start
     *            the start to set
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * @return the start
     */
    public ProductService getProductService() {
        return productService;
    }

    /**
     * @param productService
     *            the productService to set
     */
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    /**
     * search product
     * 
     * @return SUCCESS
     * @throws CRMDBException
     */
    @Override
    public String execute() throws CRMDBException {

        ProductSearchBean productSearchBean = (ProductSearchBean) CRMUtils.jsonToBean(jsonString,
                ProductSearchBean.class);
        map = productService.searchProduct(productSearchBean, searchFlag, start, limit);
        return SUCCESS;
    }
}
