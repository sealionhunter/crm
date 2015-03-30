package com.ustcsoft.gs.crm.webui.customer.dao;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.customer.bean.ContactHistorySearchBean;

/**
 * @author yinweili
 * 
 */
public interface ContactHistoryDao {

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
     * @param customerID
     *            used for recording the userID
     * @return map used for return the query records
     */
    public Map<String, Object> searchOrGetAllContactHistory(int searchFlag,
            ContactHistorySearchBean contactHistorySearchBean, final int currPage,
            final int pageSize, final int customerID);
}
