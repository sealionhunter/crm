package com.ustcsoft.gs.crm.webui.customer.service.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.customer.bean.BusinessSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dao.BusinessDao;
import com.ustcsoft.gs.crm.webui.customer.dto.BusinessDto;
import com.ustcsoft.gs.crm.webui.customer.service.BusinessService;

public class BusinessServiceImpl implements BusinessService {
    private static final Log LOG = LogFactory.getLog(BusinessServiceImpl.class);

    private BusinessDao dao;

    @Override
    public void addOrUpdateBusiness(BusinessDto leaderAdviceDto, int userID) throws CRMDBException {
        String now = CRMUtils.currentTimeAsString();
        if (leaderAdviceDto.getBusinessId() == 0) {
            leaderAdviceDto.setCreateTime(now);
            leaderAdviceDto.setUpdateTime(now);
        } else {
            // update leader advice
            leaderAdviceDto.setUpdateTime(now);
        }

        try {
            dao.addOrUpdateBusiness(leaderAdviceDto);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs.", e);
            throw new CRMDBException(e);
        }
    }

    @Override
    public void deleteBusiness(String jsonString) throws CRMDBException {
        try {
            String adviceIDs = CRMConstant.LEFT_PARENTHESIS + jsonString
                    + CRMConstant.RIGHT_PARENTHESIS;
            dao.deleteBusiness(adviceIDs);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs.", e);
            throw new CRMDBException(e);
        }
    }

    public BusinessDao getDao() {
        return dao;
    }

    public void setDao(BusinessDao dao) {
        this.dao = dao;
    }

    @Override
    public Map<String, Object> getAllBusiness(int searchFlag, BusinessSearchBean searchBean, int page,
            int limit, int userID, int customerID) throws CRMDBException {
        Map<String, Object> map = null;
        try {
            if (searchFlag == 1) {
                searchBean.setCustomerID(customerID);
                searchBean.setUserID(userID);
                map = dao.queryBusiness(searchBean, page, limit);
            } else if (searchFlag == 3) {
                map = dao.getBusinessMessage(page, limit);
            } else {
                map = dao.getAllBusiness(searchFlag, searchBean, page, limit, userID, customerID);
            }
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs.", e);
            throw new CRMDBException(e);
        }
        return map;
    }

}
