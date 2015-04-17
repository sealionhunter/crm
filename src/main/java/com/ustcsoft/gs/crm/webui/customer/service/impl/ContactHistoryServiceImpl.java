package com.ustcsoft.gs.crm.webui.customer.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.ContactHistorySearchBean;
import com.ustcsoft.gs.crm.webui.customer.bean.ContactTrackListBean;
import com.ustcsoft.gs.crm.webui.customer.dao.ContactHistoryDao;
import com.ustcsoft.gs.crm.webui.customer.service.ContactHistoryService;
import com.ustcsoft.gs.crm.webui.customer.service.SourceInfoService;

/**
 * @author yinweili
 * 
 */
public class ContactHistoryServiceImpl implements ContactHistoryService {

    private static final Log LOG = LogFactory.getLog(ContactHistoryServiceImpl.class);

    /** define contactHistoryDao */
    private ContactHistoryDao contactHistoryDao = null;

    /** define sourceInfoService */
    private SourceInfoService sourceInfoService = null;

    public List<ContactTrackListBean> getAllContactHistory(final int customerID)
            throws CRMDBException {
        LOG.debug("method searchOrGetAllContactHistory start!");
        List<ContactTrackListBean> list = contactHistoryDao.getAllContactHistory(customerID);
        LOG.debug("method searchOrGetAllContactHistory end!");

        return list;
    }

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
    @Override
    public Map<String, Object> searchOrGetAllContactHistory(int searchFlag,
            ContactHistorySearchBean contactHistorySearchBean, int currPage, int pageSize,
            final int customerID) throws CRMDBException {
        LOG.debug("method searchOrGetAllContactHistory start!");
        Map<String, Object> map = null;
//        Integer[] userIDs = sourceInfoService.getUserID(userID);
        try {
            map = contactHistoryDao.searchOrGetAllContactHistory(searchFlag,
                    contactHistorySearchBean, currPage, pageSize, customerID);
        } catch (DataAccessException e) {
            LOG.debug("run into DataAccessException in method searchOrGetAllContactHistory", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method searchOrGetAllContactHistory end!");
        return map;
    }

    /**
     * @return the contactHistoryDao
     */
    public ContactHistoryDao getContactHistoryDao() {
        return contactHistoryDao;
    }

    /**
     * @param contactHistoryDao
     *            the contactHistoryDao to set
     */
    public void setContactHistoryDao(ContactHistoryDao contactHistoryDao) {
        this.contactHistoryDao = contactHistoryDao;
    }

    /**
     * @return the sourceInfoService
     */
    public SourceInfoService getSourceInfoService() {
        return sourceInfoService;
    }

    /**
     * @param sourceInfoService
     *            the sourceInfoService to set
     */
    public void setSourceInfoService(SourceInfoService sourceInfoService) {
        this.sourceInfoService = sourceInfoService;
    }
}