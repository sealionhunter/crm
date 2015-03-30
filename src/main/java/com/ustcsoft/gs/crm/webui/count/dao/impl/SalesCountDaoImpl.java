package com.ustcsoft.gs.crm.webui.count.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.count.constant.SalesCountConstant;
import com.ustcsoft.gs.crm.webui.count.dao.SalesCountDao;
import com.ustcsoft.gs.crm.webui.count.dto.SalesCountDto;
import com.ustcsoft.gs.crm.webui.count.dto.SalesCountResult;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesEventFlowCodeDto;

/**
 * this class will select data from database by some condition
 * 
 * @author jiaxu
 * 
 */
public class SalesCountDaoImpl implements SalesCountDao {

    private static Log LOG = LogFactory.getLog(SalesCountDaoImpl.class);

    private HibernateTemplate hibernateTemplate;

    /**
     * 
     * @return hibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    /**
     * 
     * @param hibernateTemplate
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /**
     * this method will query in database which is used for get the data of the
     * chart of sales funnel.
     * 
     * @param startDate
     *            start of the date
     * @param endDate
     *            end of the date
     * @throws DataAccessException
     * @return Map<String,Object>
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> countSalesEvents(String startDate, String endDate)
            throws DataAccessException {
        LOG.debug("method querySalesEvents start!");
        Map<String, Object> smap = new HashMap<String, Object>();
        List<SalesCountResult> salesCountResults = new ArrayList<SalesCountResult>();
        List<SalesCountResult> sCountResults = new ArrayList<SalesCountResult>();
        List<SalesEventFlowCodeDto> salesEventFlowCodeDtos = new ArrayList<SalesEventFlowCodeDto>();
        List<SalesCountDto> salesCountDtos = new ArrayList<SalesCountDto>();
        int allCount = 0;
        int count = 0;
        if (startDate == null) {
            startDate = SalesCountConstant.MIN_START_DATE;
        }
        if (endDate == null || endDate.equals("")) {
            String date = startDate.substring(0, 10);
            salesCountDtos = hibernateTemplate.findByNamedParam(
                    SalesCountConstant.HQL_CONDITION_SELECT_STATUS_ENDDATE_NULL, "startDate", date);
        } else {
            String startDateTime = startDate.substring(0, 10);
            String endDateTime = endDate.substring(0, 10);
            String dateValue[] = { startDateTime, endDateTime };
            salesCountDtos = hibernateTemplate.findByNamedParam(
                    SalesCountConstant.HQL_CONDITION_SELECT_STATUS,
                    SalesCountConstant.CONDITION_DATE, dateValue);
        }

        salesEventFlowCodeDtos = hibernateTemplate
                .find(SalesCountConstant.HQL_SELECT_SALESEVENTFLOW);
        SalesCountResult salesCountResult;
        if (salesEventFlowCodeDtos.size() == 0) {
            salesCountResult = new SalesCountResult();
            salesCountResult.setAllCount(0);
            sCountResults.add(salesCountResult);
        } else {
            for (int i = 0; i < salesEventFlowCodeDtos.size(); i++) {
                for (int j = 0; j < salesCountDtos.size(); j++) {
                    if (salesEventFlowCodeDtos.get(i).getCode().toString()
                            .equals(salesCountDtos.get(j).getStatus())) {
                        count++;
                        allCount++;
                    }
                }
                salesCountResult = new SalesCountResult();
                salesCountResult.setCount(count);
                salesCountResult.setLeftCount(count + 1);
                salesCountResult.setRightCount(count + 1);
                salesCountResult.setState(salesEventFlowCodeDtos.get(i).getValue());
                sCountResults.add(salesCountResult);
                count = 0;
            }
        }
        for (SalesCountResult sCountResult : sCountResults) {
            sCountResult.setAllCount(allCount);
        }
        salesCountResults = sortList(sCountResults);
        smap.put("items", salesCountResults);
        smap.put("item", sCountResults);
        LOG.debug("method querySalesCountEvents end!");
        return smap;
    }

    /**
     * this method uses for sorting list by index
     * 
     * @param sCountResults
     * @return type of List<SalesCountResult>
     */
    public List<SalesCountResult> sortList(List<SalesCountResult> sCountResults) {
        LOG.debug("method sortList start");
        List<SalesCountResult> tempList = new ArrayList<SalesCountResult>();
        for (int i = 0; i < sCountResults.size(); i++) {
            tempList.add(i, sCountResults.get(sCountResults.size() - i - 1));
        }
        LOG.debug("method sortList end");
        return tempList;
    }
}