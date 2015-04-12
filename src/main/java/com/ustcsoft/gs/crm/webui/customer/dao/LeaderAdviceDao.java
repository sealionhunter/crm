package com.ustcsoft.gs.crm.webui.customer.dao;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.LeaderAdviceSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.LeaderAdviceDto;

public interface LeaderAdviceDao {

    public void addOrUpdateLeaderAdvice(LeaderAdviceDto leaderAdviceDto) throws CRMDBException;

    public void deleteLeaderAdvice(String adviceIDs) throws CRMDBException;

    public Map<String, Object> getAllLeaderAdvice(int searchFlag, LeaderAdviceSearchBean searchBean, int page,
            int limit, int userID, int customerID) throws CRMDBException;

    public Map<String, Object> queryLeaderAdvice(LeaderAdviceSearchBean searchBean, int page, int limit);

}
