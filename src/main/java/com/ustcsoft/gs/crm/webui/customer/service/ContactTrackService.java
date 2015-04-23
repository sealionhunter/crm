package com.ustcsoft.gs.crm.webui.customer.service;

import java.util.List;
import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.ContactTrackListBean;
import com.ustcsoft.gs.crm.webui.customer.bean.ContactTrackSearchBean;

/**
 * 
 * @author libaoshan
 * 
 */
public interface ContactTrackService {

    public List<ContactTrackListBean> getAllContactTrack(int customerID)
            throws CRMDBException;

    /**
     * the function is getting information form database to ContactTrackList.js
     * 
     * @param searchFlag
     *            is 1 or 2, a flag of searching
     * @param contactTrackSearchBean
     *            store the conditions of searching
     * @param page
     *            store the currentPage
     * @return Map<String, Object>
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> getAllContactTrack(int searchFlag,
            ContactTrackSearchBean contactTrackSearchBean, int page, int limit, int userID, int customerID)
            throws CRMDBException;

    /**
     * the function is change the isAbolished of contactTrackInfo
     * 
     * @param contactIDs
     * @throws CRMDBException
     */
    public void deleteContactTrack(String contactIDs) throws CRMDBException;

    /**
     * the function is add or update the information
     * 
     * @param submitFlag
     * @param userID
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void updateContactTrack(ContactTrackListBean contactTrackListBean, int submitFlag,
            int userID) throws CRMDBException;

}
