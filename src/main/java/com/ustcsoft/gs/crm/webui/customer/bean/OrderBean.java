package com.ustcsoft.gs.crm.webui.customer.bean;

import java.util.List;

import com.ustcsoft.gs.crm.webui.customer.dto.OrderDto;
import com.ustcsoft.gs.crm.webui.customer.dto.SelectProductDto;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesEventFlowDto;

public class OrderBean {

    private OrderDto orderDto = null;

    private List<SelectProductDto> selectProductDtos = null;

    private List<SalesEventFlowDto> salesEventFlowDtos = null;

    /**
     * 
     * @return orderDto
     */
    public OrderDto getOrderDto() {
        return orderDto;
    }

    /**
     * 
     * @param orderDto
     */
    public void setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
    }

    /**
     * 
     * @return salesEventFlowDtos
     */
    public List<SalesEventFlowDto> getSalesEventFlowDtos() {
        return salesEventFlowDtos;
    }

    /**
     * 
     * @param salesEventFlowDtos
     */
    public void setSalesEventFlowDtos(List<SalesEventFlowDto> salesEventFlowDtos) {
        this.salesEventFlowDtos = salesEventFlowDtos;
    }

    /**
     * 
     * @return selectProductDtos
     */
    public List<SelectProductDto> getSelectProductDtos() {
        return selectProductDtos;
    }

    /**
     * 
     * @param selectProductDtos
     */
    public void setSelectProductDtos(List<SelectProductDto> selectProductDtos) {
        this.selectProductDtos = selectProductDtos;
    }

}
