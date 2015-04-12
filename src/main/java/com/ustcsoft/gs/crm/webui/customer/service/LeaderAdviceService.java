package com.ustcsoft.gs.crm.webui.customer.service;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.LeaderAdviceSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.LeaderAdviceDto;

public interface LeaderAdviceService {

    public void addOrUpdateLeaderAdvice(LeaderAdviceDto leaderAdviceDto, int userID) throws CRMDBException;

    public void deleteLeaderAdvice(String jsonString) throws CRMDBException;

    public Map<String, Object> getAllLeaderAdvice(int searchFlag, LeaderAdviceSearchBean searchBean, int page, int limit, int userID, int customerID) throws CRMDBException;

}
