package com.ustcsoft.gs.crm.webui.customer.service;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.BusinessSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.BusinessDto;

public interface BusinessService {

    public void addOrUpdateBusiness(BusinessDto leaderAdviceDto, int userID) throws CRMDBException;

    public void deleteBusiness(String jsonString) throws CRMDBException;

    public Map<String, Object> getAllBusiness(int searchFlag, BusinessSearchBean searchBean, int page, int limit, int userID, int customerID) throws CRMDBException;

}
