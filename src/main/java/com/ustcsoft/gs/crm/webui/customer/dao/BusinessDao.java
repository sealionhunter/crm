package com.ustcsoft.gs.crm.webui.customer.dao;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.customer.bean.BusinessSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.BusinessDto;

public interface BusinessDao {

    public void addOrUpdateBusiness(BusinessDto leaderAdviceDto);

    public void deleteBusiness(String adviceIDs);

    public Map<String, Object> getAllBusiness(int searchFlag, BusinessSearchBean searchBean, int page,
            int limit, int userID, int customerID);

    public Map<String, Object> queryBusiness(BusinessSearchBean searchBean, int page, int limit);

    public Map<String, Object> getBusinessMessage(int page, int limit);

}
