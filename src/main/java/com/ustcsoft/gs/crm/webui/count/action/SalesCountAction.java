package com.ustcsoft.gs.crm.webui.count.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.count.service.SalesCountService;

/**
 * this class uses for funnel count
 * 
 * @author jiaxu
 * 
 */
public class SalesCountAction extends CRMAction {

    /**
     * serialization
     */
    private static final long serialVersionUID = 1L;

    private static Log LOG = LogFactory.getLog(SalesCountAction.class);

    private SalesCountService salesCountService;

    private String startDate = null;

    private String endDate = null;

    /**
     * 
     * @return salesEventService
     */
    public SalesCountService getSalesCountService() {
        return salesCountService;
    }

    /**
     * 
     * @param salesCountService
     */
    public void setSalesCountService(SalesCountService salesCountService) {
        this.salesCountService = salesCountService;
    }

    /**
     * 
     * @return startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * 
     * @param startDate
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * 
     * @return endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * 
     * @param endDate
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * this method uses for counting all sales events to show those on a funnel
     * 
     * @throws CRMDBException
     */
    public String countSalesEventsByDateOrNot() throws CRMDBException {
        LOG.debug("method countSalesEvents start!");
        map = salesCountService.countSalesEvents(startDate, endDate);
        LOG.debug("method countSalesEvents end!");
        return SUCCESS;
    }
}
