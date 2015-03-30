package com.ustcsoft.gs.crm.webui.customer.service;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.ContactHistorySearchBean;

/**
 * @author yinweili
 * 
 */
public interface ContactHistoryService {

    /**
     * list and search
     * 
     * @param searchFlag
     *            used for judging query type
     * @param contactHistorySearchBean
     *            used for recoding query condition
     * @param currPage
     *            used for recording the current page
     * @param pageSize
     *            used for recording the size of one page
     * @param userID
     *            used for recording the userID
     * @return map used for return the query records
     * @throws CRMDBException
     */
    public Map<String, Object> searchOrGetAllContactHistory(int searchFlag,
            ContactHistorySearchBean contactHistorySearchBean, int currPage, int pageSize,
            final int userID) throws CRMDBException;
}
