/*
 * SalesEventFlowCodeServiceImpl.java
 */
package com.ustcsoft.gs.crm.webui.sales.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.sales.bean.SalesEventFlowCodeBean;
import com.ustcsoft.gs.crm.webui.sales.dao.SalesEventFlowCodeDao;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesEventFlowCodeDto;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesTrackDto;
import com.ustcsoft.gs.crm.webui.sales.service.SalesEventFlowCodeService;

/**
 * 事件流程管理service实现层
 * 
 * @author shiben
 * 
 */
public class SalesEventFlowCodeServiceImpl implements SalesEventFlowCodeService {

    /**
     * 注入salesEventFlowCodeDao
     */
    private SalesEventFlowCodeDao salesEventFlowCodeDao;

    /**
     * log
     */
    private static final Log LOG = LogFactory.getLog(SalesEventFlowCodeServiceImpl.class);

    @Override
    public SalesEventFlowCodeDao getSalesEventFlowCodeDao() {
        return salesEventFlowCodeDao;
    }

    @Override
    public void setSalesEventFlowCodeDao(SalesEventFlowCodeDao salesEventFlowCodeDao) {
        this.salesEventFlowCodeDao = salesEventFlowCodeDao;
    }

    /**
     * 获得所有销售事件流程
     * 
     * @return Map<String, Object>
     * @throws CRMDBException
     */
    @Override
    public Map<String, Object> findSalesEventFlowCode() throws CRMDBException {
        LOG.debug("service start to findSalesEventFlowCode");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<SalesEventFlowCodeBean> list = salesEventFlowCodeDao.findSalesEventFlowCode();
            map.put("items", list);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method findSalesEventFlowCode：{}", e);
            throw new CRMDBException("Error findSalesEventFlowCode:" + e);
        }
        LOG.debug("service end to findSalesEventFlowCode");
        return map;

    }

    /**
     * 添加或修改销售事件流程
     * 
     * @param SalesEventFlowCodeDtos
     * @param flag
     * @throws CRMDBException
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void addOrUpdateCode(List<SalesEventFlowCodeDto> SalesEventFlowCodeDtos, int flag,
            int oldSort) throws CRMDBException {
        LOG.debug("service start to addOrUpdateCode");
        try {
            // 添加
            if (flag == 1) {
                for (SalesEventFlowCodeDto salesEventFlowCodeDto : SalesEventFlowCodeDtos) {
                    salesEventFlowCodeDao.updateSortByPlus(salesEventFlowCodeDto.getSort());
                    salesEventFlowCodeDao.addOrUpdateCode(salesEventFlowCodeDto);
                }
            }
            // 修改
            if (flag == 0) {
                for (SalesEventFlowCodeDto salesEventFlowCodeDto : SalesEventFlowCodeDtos) {
                    int newSort = salesEventFlowCodeDto.getSort();
                    // 只修改没名字;上下移动
                    if (oldSort == newSort || oldSort == 0) {
                        salesEventFlowCodeDao.addOrUpdateCode(salesEventFlowCodeDto);
                    }
                    // 大边小
                    if (oldSort > newSort && oldSort != 0) {
                        salesEventFlowCodeDao.updateSortFromMaxToMin(oldSort, newSort);
                        salesEventFlowCodeDao.addOrUpdateCode(salesEventFlowCodeDto);
                    }
                    // 小变大
                    if (oldSort < newSort && oldSort != 0) {
                        salesEventFlowCodeDao.updateSortFromMinToMax(newSort, oldSort);
                        salesEventFlowCodeDao.addOrUpdateCode(salesEventFlowCodeDto);
                    }
                    // salesEventFlowCodeDao.addOrUpdateCode(salesEventFlowCodeDto);
                }
            }
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method addOrUpdateCode：{}", e);
            throw new CRMDBException("Error addOrUpdateCode:" + e);
        }
        LOG.debug("service end to addOrUpdateCode.");
    }

    /**
     * 删除销售事件流程
     * 
     * @param SalesEventFlowCodeDtos
     * @throws CRMDBException
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Map<String, Object> deleteCode(List<SalesEventFlowCodeDto> salesEventFlowCodeDtos)
            throws CRMDBException {
        LOG.debug("service start to deleteCode");
        Map<String, Object> map = new HashMap<String, Object>();
        List<Integer> statusCountList = new ArrayList<Integer>();
        List<SalesTrackDto> salesTrackDtos = new ArrayList<SalesTrackDto>();
        int count = 0;
        int flag = 0;
        try {
            salesTrackDtos = getSalesEventFlowCodeDao().checkDeleteCode();
            for (int i = 0; i < salesEventFlowCodeDtos.size(); i++) {
                for (int j = 0; j < salesTrackDtos.size(); j++) {
                    if (salesEventFlowCodeDtos.get(i).getCode() == Integer.parseInt(salesTrackDtos
                            .get(j).getStatus())) {
                        count++;
                    }
                }
                statusCountList.add(count);
                count = 0;
            }
            for (int i = 0; i < statusCountList.size(); i++) {
                if (statusCountList.get(i) != 0) {
                    flag = 1;
                    map.put("items", salesEventFlowCodeDtos.get(i).getValue() + "-正在被销售事件履历模块使用！");
                    break;
                }
            }
            if (flag == 0) {
                for (SalesEventFlowCodeDto salesEventFlowCodeDto : salesEventFlowCodeDtos) {
                    salesEventFlowCodeDao.updateSortByMinus(salesEventFlowCodeDto.getCode());
                    salesEventFlowCodeDao.deleteCode(salesEventFlowCodeDto);
                    salesEventFlowCodeDao.deleteEventFlowDemand(salesEventFlowCodeDto.getCode());
                }
            }
        } catch (DataAccessException e) {
            throw new CRMDBException("Error deleteCode:" + e);
        }
        LOG.debug("service end to deleteCode");
        return map;
    }

    /**
     * 判断销售事件流程名称是否存在
     * 
     * @param salesEventFlowCodeDto
     * @param flag
     * @return INT
     * @throws CRMDBException
     */
    @Override
    public long judgeEventNameRepeat(SalesEventFlowCodeDto salesEventFlowCodeDto)
            throws CRMDBException {
        LOG.debug("method judgeOrderRepeat start!");
        long count = 0;
        try {
            count = salesEventFlowCodeDao.judgeEventNameRepeat(salesEventFlowCodeDto);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method judgeOrderRepeat!", e);
            throw new CRMDBException("Error judgeEventNameRepeat:" + e);
        }
        LOG.debug("method judgeOrderRepeat end!");
        return count;
    }

    @Override
    public Map<String, Object> findSalesEventFlowCategory() throws CRMDBException {
        LOG.debug("method findSalesEventFlowCategory start!");
        Map<String, Object> map = null;
        try {
            map = salesEventFlowCodeDao.findSalesEventFlowCategory();
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method findSalesEventFlowCategory:{}", e);
            throw new CRMDBException("Error findSalesEventFlowCategory:" + e);
        }
        LOG.debug("method findSalesEventFlowCategory end!");
        return map;
    }

    @Override
    public List<SalesEventFlowCodeBean> getCodeByCategory(String category) throws CRMDBException {
        LOG.debug("method getCodeByCategory start!");
        List<SalesEventFlowCodeBean> list = null;
        try {
            list = salesEventFlowCodeDao.getCodeByCategory(category);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getCodeByCategory：{}", e);
            throw new CRMDBException("Error getAllEvents category:(#" + category + "#):" + e);
        }
        LOG.debug("method getCodeByCategory end!");
        return list;
    }
}
