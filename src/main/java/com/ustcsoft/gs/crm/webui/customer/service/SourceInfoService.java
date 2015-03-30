package com.ustcsoft.gs.crm.webui.customer.service;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.SourceSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.SourceInfoDto;

/**
 * @author xuzhen
 */
public interface SourceInfoService {
    /**
     * method to get all customer source records or searched source records
     * 
     * @param searchFlag
     *            search mark
     * @param sourceSearchBean
     *            search conditions
     * @param currpage
     *            search page
     * @return map
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> getOrSearchSource(int searchFlag, SourceSearchBean sourceSearchBean,
            int currpage, int limit, int userID) throws CRMDBException;

    /**
     * delete source record
     * 
     * @param sourceIDs
     *            sourceID which will be deleted
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void deleteSource(String sourceIDs) throws CRMDBException;

    /**
     * add or edit source record
     * 
     * @param source
     *            sourceInfo which will be added or edited
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void updateSource(SourceInfoDto source) throws CRMDBException;

    /**
     * get the userID of the user himself and his underlings
     * 
     * @return userIDs
     * @throws DataAccessException
     */
    public Integer[] getUserID(int userID) throws DataAccessException;

    /**
     * get all customers' name and id
     * 
     * @param customerFlag
     *            searchFlag
     * 
     * @return map
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> getCustomer(int customerFlag, int userID) throws CRMDBException;
}