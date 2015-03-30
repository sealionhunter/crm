package com.ustcsoft.gs.crm.webui.customer.service;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.CooperatorSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.CooperatorDto;

/**
 * CooperatorService interface
 * 
 * @author xujialong
 * 
 */
public interface CooperatorService {

    /**
     * list,simple search,advanced search
     * 
     * @param searchFlag
     *            int 0 mean list,1 mean simple search,2 mean advanced search
     * @param cooperatorSearchBean
     *            CooperatorSearchBean Cooperator search bean for searching
     * @param currpage
     *            int current page for paging
     * @param limit
     *            int current page's limit for paging
     * @return Map<String, Object> the cooperator search list for page
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> getAllCooperatorShow(int searchFlag,
            CooperatorSearchBean cooperatorSearchBean, int currpage, int limit)
            throws CRMDBException;

    /**
     * add Cooperator or update Cooperator
     * 
     * @param cooperatorDto
     *            CooperatorDto cooperator dto for save or update
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void updateCooperator(CooperatorDto cooperatorDto) throws CRMDBException;

    /**
     * delete Cooperator
     * 
     * @param cooperatorIDs
     *            String Strings includes all ids for del
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void deleteCooperator(String cooperatorIDs) throws CRMDBException;

    /**
     * check name existed
     * 
     * @param coo
     *            CooperatorDto Cooperator dto
     * @return boolean,true mean name existing,false mean name not existing
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public boolean checkNameExisted(CooperatorDto coo) throws CRMDBException;
}