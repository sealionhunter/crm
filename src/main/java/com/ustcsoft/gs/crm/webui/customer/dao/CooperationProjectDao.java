package com.ustcsoft.gs.crm.webui.customer.dao;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.customer.bean.CooperationProjectSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.CooperationProjectDto;

/**
 * CooperationProjectDao interface
 * 
 * @author xujialong
 * 
 */
public interface CooperationProjectDao {

    /**
     * show or search project
     * 
     * @param searchFlag
     *            int 0 mean list,1 mean simple search,2 mean advanced search
     * @param cooperationProjectSearchBean
     *            CooperationProjectSearchBean cooperation project search values
     *            for searching
     * @param currpage
     *            int current page for paging
     * @param limit
     *            int current page's limit for paging
     * @return Map<String, Object> the cooperation project search list for page
     */
    public Map<String, Object> searchCooperationProject(int searchFlag,
            CooperationProjectSearchBean cooperationProjectSearchBean, int currpage, int limit);

    /**
     * add or update cooperation project
     * 
     * @param cooperationProject
     *            CooperationProjectDto cooperation project dto for save or
     *            update
     */
    public void updateCooperationProject(CooperationProjectDto cooperationProject);

    /**
     * delete cooperation project about cooperationProjectID
     * 
     * @param cooperationProjectIDs
     *            String Strings includes all ids for del
     */
    public void deleteCooperationProject(String cooperationProjectIDs);

    /**
     * check name existed
     * 
     * @param cpd
     *            CooperationProjectDto Cooperation project dto
     * @return boolean,true mean name existing,false mean name not existing
     */
    public boolean checkNameExisted(CooperationProjectDto cpd);
}