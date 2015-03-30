package com.ustcsoft.gs.crm.webui.customer.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.ContractSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dao.ContractDao;
import com.ustcsoft.gs.crm.webui.customer.dao.OrderDao;
import com.ustcsoft.gs.crm.webui.customer.dto.ContractDto;
import com.ustcsoft.gs.crm.webui.customer.dto.OrderDto;
import com.ustcsoft.gs.crm.webui.customer.service.ContractService;
import com.ustcsoft.gs.crm.webui.sales.dao.SalesEventDao;
import com.ustcsoft.gs.crm.webui.sales.dao.SalesTrackDao;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesTrackDto;

/**
 * class for contract list,delete,add,update and search
 * 
 * @author zhangqiuli
 */
public class ContractServiceImpl implements ContractService {

    /** log statement */
    private static final Log LOG = LogFactory.getLog(ContractServiceImpl.class);
    /** contractDao statement */
    private ContractDao contractDao = null;

    private OrderDao orderDao = null;

    private SalesTrackDao salesTrackDao = null;

    /**
     * @param salesTrackDao
     *            the salesTrackDao to set
     */
    public void setSalesTrackDao(SalesTrackDao salesTrackDao) {
        this.salesTrackDao = salesTrackDao;
    }

    private SalesEventDao salesEventDao = null;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setSalesEventDao(SalesEventDao salesEventDao) {
        this.salesEventDao = salesEventDao;
    }

    /**
     * @param contractDao
     *            the contractDao to set
     */
    public void setContractDao(ContractDao contractDao) {
        this.contractDao = contractDao;
    }

    /**
     * add or update proposalOrContract
     * 
     * @param conDto
     * @throws CRMDBException
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void addOrUpdateContract(final ContractDto conDto) throws CRMDBException {
        LOG.debug("method addOrUpdateProposalOrContract start!");
        try {
            contractDao.addOrUpdateContract(conDto);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method addOrUpdateProposalOrContract!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method addOrUpdateProposalOrContract end!");
    }

    @Override
    public Boolean NameOrOrderIdIsExit(String name) {
        LOG.debug("method NameOrOrderIdIsExit start!");
        try {
            return contractDao.NameOrOrderIdIsExit(name);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method NameOrOrderIdIsExit!", e);
            return false;
        }
    }

    @Override
    public Map<String, Object> getContractEvent(int orderID) {
        Map<String, Object> map = new HashMap<String, Object>();
        int eventID = orderDao.getOrderEventID(orderID);
        int eventExit = 0;
        String eventValue = "";
        if (eventID == 0) {
            eventExit = 0;
            eventValue = "";
        } else {
            eventExit = 1;
            eventValue = salesEventDao.findDemandOfDuringObjectives(eventID);
        }
        map.put("eventID", eventID);
        map.put("eventExit", eventExit);
        map.put("eventValue", eventValue);
        return map;
    }

    @Override
    public void updateContractEvent(int eventID, String demand, ContractDto contractDto, int userID) {
        salesEventDao.updateDemandOfDuringObjectives(eventID, demand);
        if (contractDto.getContractID() == 0) {
            int orderID = contractDto.getOrderID();
            SalesTrackDto salesTrackDto = new SalesTrackDto();
            OrderDto orderDto = orderDao.getOrderByID(orderID);
            salesTrackDto.setCustomerID(orderDto.getCustomerID());
            salesTrackDto.setStatus("3");
            salesTrackDto.setEventID(eventID);
            salesTrackDto.setSubmitterID(userID);
            Date now = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String date = formatter.format(now);
            salesTrackDto.setSubmitDate(date);
            salesTrackDao.addSalesTrack(salesTrackDto);
        }
    }

    @Override
    public void deleteContract(String contractIDs) throws CRMDBException {
        contractDao.deleteContract(contractIDs);
    }

    @Override
    public Map<String, Object> getAllContract(int searchFlag,
            ContractSearchBean contractSearchBean, int start, int limit) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", contractDao.getAllContract(searchFlag, contractSearchBean, start, limit));
        map.put("total",
                contractDao.getAllContractCount(searchFlag, contractSearchBean, start, limit));
        return map;
    }
}