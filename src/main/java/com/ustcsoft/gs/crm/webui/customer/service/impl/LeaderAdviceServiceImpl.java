package com.ustcsoft.gs.crm.webui.customer.service.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.customer.bean.LeaderAdviceSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dao.LeaderAdviceDao;
import com.ustcsoft.gs.crm.webui.customer.dto.LeaderAdviceDto;
import com.ustcsoft.gs.crm.webui.customer.service.LeaderAdviceService;

public class LeaderAdviceServiceImpl implements LeaderAdviceService {
    private static final Log LOG = LogFactory.getLog(LeaderAdviceServiceImpl.class);

    private LeaderAdviceDao dao;

    @Override
    public void addOrUpdateLeaderAdvice(LeaderAdviceDto leaderAdviceDto, int userID) throws CRMDBException {
        String now = CRMUtils.currentTimeAsString();
        if (leaderAdviceDto.getAdviceID() < 1) {
            // add leader advice
            leaderAdviceDto.setCreateTime(now);
//            leaderAdviceDto.setUpdateTime(null);
            leaderAdviceDto.setUpdateTime(now);
        } else {
            // update leader advice
            leaderAdviceDto.setUpdateTime(now);
        }

        try {
            dao.addOrUpdateLeaderAdvice(leaderAdviceDto);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs.", e);
            throw new CRMDBException(e);
        }
    }

    @Override
    public void deleteLeaderAdvice(String jsonString) throws CRMDBException {
        try {
            String adviceIDs = CRMConstant.LEFT_PARENTHESIS + jsonString
                    + CRMConstant.RIGHT_PARENTHESIS;
            dao.deleteLeaderAdvice(adviceIDs);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs.", e);
            throw new CRMDBException(e);
        }
    }

    public LeaderAdviceDao getDao() {
        return dao;
    }

    public void setDao(LeaderAdviceDao dao) {
        this.dao = dao;
    }

    @Override
    public Map<String, Object> getAllLeaderAdvice(int searchFlag, LeaderAdviceSearchBean searchBean, int page,
            int limit, int userID, int customerID) throws CRMDBException {
        Map<String, Object> map = null;
        try {
            if (searchFlag == 1) {
                searchBean.setCustomerID(customerID);
                searchBean.setUserID(userID);
                map = dao.queryLeaderAdvice(searchBean, page, limit);
            } else {
                map = dao.getAllLeaderAdvice(searchFlag, searchBean, page, limit, userID, customerID);
            }
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs.", e);
            throw new CRMDBException(e);
        }
        return map;
    }

}
