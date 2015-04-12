package com.ustcsoft.gs.crm.webui.customer.service.impl;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.customer.bean.LeaderAdviceSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dao.LeaderAdviceDao;
import com.ustcsoft.gs.crm.webui.customer.dto.LeaderAdviceDto;
import com.ustcsoft.gs.crm.webui.customer.service.LeaderAdviceService;

public class LeaderAdviceServiceImpl implements LeaderAdviceService {
    private LeaderAdviceDao dao;

    @Override
    public void addOrUpdateLeaderAdvice(LeaderAdviceDto leaderAdviceDto, int userID) {
        String now = CRMUtils.currentTimeAsString();
        if (leaderAdviceDto.getAdviceID() < 1) {
            leaderAdviceDto.setCreateTime(now);
            leaderAdviceDto.setUpdateTime(null);
        } else {
            leaderAdviceDto.setUpdateTime(now);
        }

        try {
            dao.addOrUpdateLeaderAdvice(leaderAdviceDto);
        } catch (CRMDBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteLeaderAdvice(String jsonString) {
        try {
            String adviceIDs = CRMConstant.LEFT_PARENTHESIS + jsonString
                    + CRMConstant.RIGHT_PARENTHESIS;
            dao.deleteLeaderAdvice(adviceIDs);
        } catch (CRMDBException e) {
            e.printStackTrace();
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
            int limit, int userID, int customerID) {
        Map<String, Object> map = null;
        try {
            if (searchFlag == 1) {
                searchBean.setCustomerID(customerID);
                searchBean.setUserID(userID);
                map = dao.queryLeaderAdvice(searchBean, page, limit);
            } else {
                map = dao.getAllLeaderAdvice(searchFlag, searchBean, page, limit, userID, customerID);
            }
        } catch (CRMDBException e) {
            e.printStackTrace();
        }
        return map;
    }

}
