package com.ustcsoft.gs.crm.webui.product.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.product.bean.PriceBean;
import com.ustcsoft.gs.crm.webui.product.service.PriceService;

public class PriceAction extends CRMAction {

    private static final long serialVersionUID = 1L;

    /** used for recording log */
    private static final Log LOG = LogFactory.getLog(PriceAction.class);

    /** used for priceService */
    private PriceService priceService = null;

    /** used for save groupID */
    private int groupID = 0;

    /** used for save productID */
    private int productID = 0;

    /** used for save or update price discount */
    List<PriceBean> priceBeans = null;

    /**
     * get groupID
     * 
     * @return groupID
     */
    public int getGroupID() {
        return groupID;
    }

    /**
     * set groupID
     * 
     * @param groupID
     */
    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    /**
     * get priceService
     * 
     * @return priceService
     */
    public PriceService getPriceService() {
        return priceService;
    }

    /**
     * set priceService
     * 
     * @param priceService
     */
    public void setPriceService(PriceService priceService) {
        this.priceService = priceService;
    }

    /**
     * get productID
     * 
     * @return productID
     */
    public int getProductID() {
        return productID;
    }

    /**
     * set productID
     * 
     * @param productID
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * get price grid list header action
     * 
     * @return SUCCESS string,action has been correct execute
     * @throws CRMDBException
     */
    public String getConstruct() throws CRMDBException {
        LOG.debug("method getConstruct start!");
        map.put("headerAndField", priceService.getConstruct());
        LOG.debug("method getConstruct end!");
        return SUCCESS;
    }

    /**
     * get price grid list data action
     * 
     * @return SUCCESS string,action has been correct execute
     * @throws CRMDBException
     */
    public String getData() throws CRMDBException {
        LOG.debug("method getData start!");
        map.put("items", priceService.getData(page, limit));
        map.put("total", priceService.getProductCountByPrice());
        LOG.debug("method getData end!");
        return SUCCESS;
    }

    /**
     * get product discount action
     * 
     * @return SUCCESS string,action has been correct execute
     * @throws CRMDBException
     */
    public String getProductDiscount() throws CRMDBException {
        LOG.debug("method getProductDiscount start!");
        map = priceService.getProductDiscount(groupID, productID);
        LOG.debug("method getProductDiscount end!");
        return SUCCESS;
    }

    /**
     * validate setProductDiscount
     * 
     * @throws CRMDBException
     * 
     */
    public void validateSetProductDiscount() throws CRMDBException {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        priceBeans = new ArrayList<PriceBean>();
        priceService.deletePrice(productID + "");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            PriceBean priceBean = (PriceBean) JSONObject.toBean(jsonObject, PriceBean.class);
            if ("0".equals(priceBean.getDiscount())) {
                addFieldError("group" + priceBean.getGroupID(),
                        this.getText("productDiscount.invalid"));
                showFieldError();
            }
            priceBeans.add(priceBean);
        }
    }

    /**
     * set product discount action
     * 
     * @return SUCCESS string,action has been correct execute
     * @throws CRMDBException
     */
    public String setProductDiscount() throws CRMDBException {
        LOG.debug("method setProductDiscount start!");
        priceService.updatePriceDiscount(priceBeans);
        LOG.debug("method setProductDiscount end!");
        return SUCCESS;
    }

    /**
     * delete price action
     * 
     * @return SUCCESS string,action has been correct execute
     * @throws CRMDBException
     */
    public String deletePrice() throws CRMDBException {
        LOG.debug("method deletePrice start!");
        priceService.deletePrice(jsonString);
        LOG.debug("method deletePrice start!");
        return SUCCESS;
    }
}
