package com.ustcsoft.gs.crm.webui.customer.service.impl;

import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.customer.dao.CustomerCommonDao;
import com.ustcsoft.gs.crm.webui.customer.service.CustomerCommonService;

public class CustomerCommonServiceImpl extends TimerTask implements CustomerCommonService {

    private static final Log LOG = LogFactory.getLog(CustomerCommonServiceImpl.class);
    private CustomerCommonDao customerCommonDao;
    
    public CustomerCommonDao getCustomerCommonDao() {
        return customerCommonDao;
    }

    public void setCustomerCommonDao(CustomerCommonDao customerCommonDao) {
        this.customerCommonDao = customerCommonDao;
    }

    /**
     * Timing execution method
     */
    @Override
    public void run() {
        LOG.debug("method run start!");
        customerCommonDao.freeCustomer();
        LOG.debug("method run end!");
    }

}
