package com.ustcsoft.gs.crm.webui.customer.service;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.customer.bean.LeaderAdviceSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.LeaderAdviceDto;

public interface LeaderAdviceService {

    void addOrUpdateLeaderAdvice(LeaderAdviceDto leaderAdviceDto, int userID);

    void deleteLeaderAdvice(String jsonString);

    Map<String, Object> getAllLeaderAdvice(int searchFlag, LeaderAdviceSearchBean searchBean, int page, int limit, int userID, int customerID);

}
