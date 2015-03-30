package com.ustcsoft.gs.crm.webui.customer.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.customer.bean.SourceSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.SourceInfoDto;

/**
 * @author xuzhen
 */
public interface SourceInfoDao {
    /**
     * select all customer source from for list and search
     * 
     * @param currpage
     *            search page
     * @return map
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> getAllSource(int currpage, int limit, Integer[] userID)
            throws DataAccessException;

    /**
     * search customer source from for list and search
     * 
     * @param searchFlag
     *            search mark
     * @param sourceSearchBean
     *            search conditions
     * @param currpage
     *            search page
     * @return map
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> searchSource(int searchFlag, SourceSearchBean sourceSearchBean,
            int currpage, int limit) throws DataAccessException;

    /**
     * method to delete customerSource record from DB
     * 
     * @param sourceIDs
     *            sourceID which will be deleted
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public void deleteSource(String sourceIDs) throws DataAccessException;

    /**
     * method to update customerSource record from DB
     * 
     * @param source
     *            sourceInfo which will be added or edited
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public void updateSource(SourceInfoDto source) throws DataAccessException;

    /**
     * method to get all customers' name and id
     * 
     * @return map
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> getCustomer(int customerFlag, Integer[] userID)
            throws DataAccessException;
}