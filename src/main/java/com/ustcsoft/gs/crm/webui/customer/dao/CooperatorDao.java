package com.ustcsoft.gs.crm.webui.customer.dao;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.customer.bean.CooperatorSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.CooperatorDto;

/**
 * CoopearaotrDao interface
 * 
 * @author xujialong
 * 
 */
public interface CooperatorDao {

    /**
     * get all cooperator
     * 
     * @param currPage
     *            int current page for paging
     * @param limit
     *            int current page's limit for paging
     * @return Map<String, Object> the cooperator list for page
     */
    public Map<String, Object> getCooperatorList(int currPage, int limit);

    /**
     * search Cooperator
     * 
     * @param searchFlag
     *            int 1 mean simple search,2 mean advanced search
     * @param searchBean
     *            CooperatorSearchBean the search Values dto
     * @param currPage
     *            int current page for paging
     * @param limit
     *            int current page's limit for paging
     * @return Map<String, Object> the cooperator search list for page
     */
    public Map<String, Object> searchCooperator(int searchFlag, CooperatorSearchBean searchBean,
            int currPage, int limit);

    /**
     * add Cooperator or update Cooperator
     * 
     * @param cooperatorDto
     *            CooperatorDto cooperator dto for save or update
     */
    public void updateCooperator(CooperatorDto cooperatorDto);

    /**
     * delete Cooperator
     * 
     * @param cooperatorIDs
     *            String Strings includes all ids for del
     */
    public void deleteCooperator(String cooperatorIDs);

    /**
     * check input name existed
     * 
     * @param coo
     *            CooperatorDto Cooperator dto
     * @return boolean,true mean name existing,false mean name not existing
     */
    public boolean checkNameExisted(CooperatorDto coo);
}