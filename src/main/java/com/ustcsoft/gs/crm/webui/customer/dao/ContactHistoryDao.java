package com.ustcsoft.gs.crm.webui.customer.dao;

import java.util.List;
import java.util.Map;

import com.ustcsoft.gs.crm.webui.customer.bean.ContactHistorySearchBean;
import com.ustcsoft.gs.crm.webui.customer.bean.ContactTrackListBean;

/**
 * @author yinweili
 * 
 */
public interface ContactHistoryDao {
    public List<ContactTrackListBean> getAllContactHistory(int customerID);

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
