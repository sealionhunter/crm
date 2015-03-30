package com.ustcsoft.gs.crm.webui.product.action;

import java.util.HashMap;
import java.util.List;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.product.bean.PriceSearchBean;
import com.ustcsoft.gs.crm.webui.product.constant.ProductConstant;
import com.ustcsoft.gs.crm.webui.product.service.PriceService;

public class PriceSearchAction extends CRMAction {

    private static final long serialVersionUID = 1L;

    private PriceService priceService = null;

    PriceSearchBean priceSearchBean = null;

    private int start = 0;

    /**
     * get priceService
     * 
     * @return the priceService
     */
    public PriceService getPriceService() {
        return priceService;
    }

    /**
     * set priceService
     * 
     * @param priceService
     *            the priceService to set
     */
    public void setPriceService(PriceService priceService) {
        this.priceService = priceService;
    }

    /**
     * get start
     * 
     * @return the start
     */
    public int getStart() {
        return start;
    }

    /**
     * set start
     * 
     * @param start
     *            the start to set
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * validate execute
     * 
     */
    @Override
    public void validateExecute() {
        priceSearchBean = (PriceSearchBean) CRMUtils.jsonToBean(jsonString, PriceSearchBean.class);
        if (!priceSearchBean.getMaxPrice().matches(ProductConstant.NUMBERFORMAT)
                && !ProductConstant.EMPTYSTRING.equals(priceSearchBean.getMaxPrice())) {
            addFieldError(ProductConstant.MAXPRICE, this.getText("productPrice.invalid"));
        }
        if (!priceSearchBean.getMinPrice().matches(ProductConstant.NUMBERFORMAT)
                && !ProductConstant.EMPTYSTRING.equals(priceSearchBean.getMinPrice())) {
            addFieldError(ProductConstant.MINPRICE, this.getText("productPrice.invalid"));
        }
        if (!priceSearchBean.getMinDiscount().matches(ProductConstant.DISCOUNTNUMBERFORMAT)
                && !ProductConstant.EMPTYSTRING.equals(priceSearchBean.getMinDiscount())) {
            addFieldError(ProductConstant.MINDISCOUNT, this.getText("productDiscount.invalid"));
        }
        if (!priceSearchBean.getMaxDiscount().matches(ProductConstant.DISCOUNTNUMBERFORMAT)
                && !ProductConstant.EMPTYSTRING.equals(priceSearchBean.getMaxDiscount())) {
            addFieldError(ProductConstant.MAXDISCOUNT, this.getText("productDiscount.invalid"));
        }
        showFieldError();
    }

    /**
     * search product price action
     * 
     * @return SUCCESS string,action has been correct execute
     */
    @Override
    public String execute() throws CRMDBException {
        List<HashMap<String, Object>> maps = priceService.searchProduct(priceSearchBean,
                searchFlag, start, limit);
        int total = priceService.searchProductCount(priceSearchBean, searchFlag, start, limit);
        map.put("total", total);
        map.put("items", maps);
        return SUCCESS;
    }
}
