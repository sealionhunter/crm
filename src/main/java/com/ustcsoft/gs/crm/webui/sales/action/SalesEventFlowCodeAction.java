/*
 * SalesEventFlowCodeAction.java
 */
package com.ustcsoft.gs.crm.webui.sales.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.sales.bean.SalesEventFlowCodeBean;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesEventFlowCodeDto;
import com.ustcsoft.gs.crm.webui.sales.service.SalesEventFlowCodeService;

/**
 * 事件流程各个阶段管理dao接口层
 * 
 * @author shiben
 * 
 */
public class SalesEventFlowCodeAction extends CRMAction {
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * log
     */
    private static final Log LOG = LogFactory.getLog(SalesEventFlowCodeAction.class);

    /**
     * 注入salesEventFlowCodeService
     */
    private SalesEventFlowCodeService salesEventFlowCodeService = null;

    /**
     * salesEventFlowCodeDto
     */
    private SalesEventFlowCodeDto salesEventFlowCodeDto = null;

    /**
     * 标示
     */
    private int flag = 0;
    private int oldSort = 0;

    /**
     * 验证
     * 
     * @throws CRMDBException
     */
    public void validateAddOrUpdateCode() throws CRMDBException {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            salesEventFlowCodeDto = (SalesEventFlowCodeDto) JSONObject.toBean(jsonObject,
                    SalesEventFlowCodeDto.class);
            if (salesEventFlowCodeDto.getValue().trim().equals("")) {
                addFieldError("value", this.getText("valueNull.invalid"));
            } else if (salesEventFlowCodeDto.getValue().trim().length() > 50) {
                addFieldError("value", this.getText("valueLength.invalid"));
            } else if (salesEventFlowCodeService.judgeEventNameRepeat(salesEventFlowCodeDto) != 0) {
                addFieldError("value", this.getText("valueLength.invalid"));
            }
        }
        showFieldError();
    }

    /**
     * 获得所有销售事件流程
     * 
     * @return SUCCSSS
     * @throws CRMDBException
     */
    @Override
    public String execute() throws CRMDBException {
        LOG.debug("method execute start.");
        map = salesEventFlowCodeService.findSalesEventFlowCode();
        LOG.debug("method execute end.");
        return SUCCESS;
    }

    /**
     * 添加或修改销售事件流程
     * 
     * @return SUCCSSS
     * @throws CRMDBException
     */
    public String addOrUpdateCode() throws CRMDBException {
        LOG.debug("method addOrUpdateCode start.");
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        List<SalesEventFlowCodeDto> SalesEventFlowCodeDtos = new ArrayList<SalesEventFlowCodeDto>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            SalesEventFlowCodeDtos.add((SalesEventFlowCodeDto) JSONObject.toBean(jsonObject,
                    SalesEventFlowCodeDto.class));
        }
        salesEventFlowCodeService.addOrUpdateCode(SalesEventFlowCodeDtos, flag, oldSort);
        LOG.debug("method addOrUpdateCode end.");
        return SUCCESS;
    }

    /**
     * 删除销售事件流程
     * 
     * @return SUCCSSS
     * @throws CRMDBException
     */
    public String deleteCode() throws CRMDBException {
        LOG.debug("method deleteCode start.");
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        List<SalesEventFlowCodeDto> SalesEventFlowCodeDtos = new ArrayList<SalesEventFlowCodeDto>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            SalesEventFlowCodeDtos.add((SalesEventFlowCodeDto) JSONObject.toBean(jsonObject,
                    SalesEventFlowCodeDto.class));
        }
        map = salesEventFlowCodeService.deleteCode(SalesEventFlowCodeDtos);
        LOG.debug("method deleteCode end.");
        return SUCCESS;
    }

    public String salesEventFlowCategory() throws CRMDBException {
        LOG.debug("method salesEventFlowCategory start.");
        map = salesEventFlowCodeService.findSalesEventFlowCategory();
        LOG.debug("method salesEventFlowCategory end.");
        return SUCCESS;
    }

    public String getCodeByCategory() throws CRMDBException {
        LOG.debug("method getCodeByCategory start.");
        List<SalesEventFlowCodeBean> list = salesEventFlowCodeService.getCodeByCategory(jsonString);
        map.put("items", list);
        LOG.debug("method getCodeByCategory end.");
        return SUCCESS;
    }

    public SalesEventFlowCodeDto getSalesEventFlowCodeDto() {
        return salesEventFlowCodeDto;
    }

    public void setSalesEventFlowCodeDto(SalesEventFlowCodeDto salesEventFlowCodeDto) {
        this.salesEventFlowCodeDto = salesEventFlowCodeDto;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public SalesEventFlowCodeService getSalesEventFlowCodeService() {
        return salesEventFlowCodeService;
    }

    public void setSalesEventFlowCodeService(SalesEventFlowCodeService salesEventFlowCodeService) {
        this.salesEventFlowCodeService = salesEventFlowCodeService;
    }

    public int getOldSort() {
        return oldSort;
    }

    public void setOldSort(int oldSort) {
        this.oldSort = oldSort;
    }
}
