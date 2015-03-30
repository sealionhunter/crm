package com.ustcsoft.gs.crm.webui.customer.service;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.CooperationProjectSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.CooperationProjectDto;

/**
 * CooperationProject Service interface
 * 
 * @author xujialong
 * 
 */
public interface CooperationProjectService {

    /**
     * list and simple or super search cooperation project
     * 
     * @param searchFlag
     *            int 0 mean list,1 mean simple search,2 mean advanced search
     * @param cooperationProjectSearchBean
     *            CooperationProjectSearchBean Cooperation project search bean
     *            for searching
     * @param currpage
     *            int current page for paging
     * @param limit
     *            int current page's limit for paging
     * @return Map<String, Object> the cooperation project search list for page
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> searchCooperationProject(int searchFlag,
            CooperationProjectSearchBean cooperationProjectSearchBean, int currpage, int limit)
            throws CRMDBException;

    /**
     * add or update cooperation's project
     * 
     * @param cooperationProject
     *            CooperationProjectDto cooperation project dto for save or
     *            update
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void updateCooperationProject(CooperationProjectDto cooperationProject)
            throws CRMDBException;

    /**
     * delete cooperation's project about cooperationProjectID
     * 
     * @param cooperationProjectIDs
     *            String Strings includes all ids for del
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void deleteCooperationProject(String cooperationProjectIDs) throws CRMDBException;

    /**
     * check name existed
     * 
     * @param cpd
     *            CooperationProjectDto cooperation project dto
     * @return boolean,true mean name existing,false mean name not existing
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public boolean checkNameExisted(CooperationProjectDto cpd) throws CRMDBException;
}