package com.ustcsoft.gs.crm.webui.customer.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.util.PagingHibernateCallback;
import com.ustcsoft.gs.crm.webui.common.util.SuperHibernateCallback;
import com.ustcsoft.gs.crm.webui.customer.bean.CooperatorSearchBean;
import com.ustcsoft.gs.crm.webui.customer.bean.CooperatorShowBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.customer.dao.CooperatorDao;
import com.ustcsoft.gs.crm.webui.customer.dto.CooperatorDto;

/**
 * CoopeatorDaoimplements
 * 
 * @author xujialong
 * 
 */
public class CooperatorDaoImpl implements CooperatorDao {

    private static final Log LOG = LogFactory.getLog(CooperatorDaoImpl.class);

    private HibernateTemplate hibernateTemplate;

    /**
     * @return the hibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    /**
     * @param hibernateTemplate
     *            the hibernateTemplate to set
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /**
     * get all cooperator
     * 
     * @param currPage
     *            int current page for paging
     * @param limit
     *            int current page's limit for paging
     * @return Map<String, Object> the cooperator list for page
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getCooperatorList(int currPage, int limit) {
        LOG.debug("method getCooperatorList start!");
        Map<String, Object> map = new HashMap<String, Object>();
        List<CooperatorShowBean> cooperatorShowList = null;
        long total = 0;
        String hqlList = CustomerConstant.SELECT_COOPERATOR_HQL
                + CustomerConstant.GET_COOPERATOR_HQL;
        String hqlCount = CustomerConstant.SELECT_COOPERATOR_COUNT_HQL
                + CustomerConstant.GET_COOPERATOR_HQL;
        cooperatorShowList = hibernateTemplate.executeFind(new PagingHibernateCallback(hqlList,
                currPage, limit));
        total = (Long) hibernateTemplate.executeFind(
                new PagingHibernateCallback(hqlCount, 0, limit)).get(0);
        map.put(CRMConstant.TOTAL, total);
        map.put(CRMConstant.ITEMS, cooperatorShowList);
        LOG.debug("method getCooperatorList end!");
        return map;
    }

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
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> searchCooperator(int searchFlag, CooperatorSearchBean searchBean,
            int currPage, int limit) {
        LOG.debug("method searchCooperator start!");
        Map<String, Object> map = new HashMap<String, Object>();
        List<CooperatorShowBean> cooperatorShowList = null;
        long total = 0;
        String hqlList = null;
        String hqlCount = null;
        if (searchFlag == 1) {
            hqlList = CustomerConstant.SELECT_COOPERATOR_HQL
                    + CustomerConstant.GET_COOPERATOR_SIMPLE_HQL;
            hqlCount = CustomerConstant.SELECT_COOPERATOR_COUNT_HQL
                    + CustomerConstant.GET_COOPERATOR_SIMPLE_HQL;
        } else {
            StringBuffer search_HQL = new StringBuffer(CustomerConstant.GET_COOPERATOR_ADVANCED_HQL);
            // first cooperation date search
            String name = searchBean.getCooperatorNameSearch();
            String dateMin = searchBean.getCooperationDateMin();
            String dateMax = searchBean.getCooperationDateMax();
            Long timesMin = searchBean.getCooperationTimesMin();
            Long timesMax = searchBean.getCooperationTimesMax();
            String industry = searchBean.getCooperatorIndustrySearch();
            String scale = searchBean.getCooperatorScaleSearch();
            if (!name.equals(CRMConstant.PER_CENT)) {
                search_HQL.append(CustomerConstant.COOPERATOR_NAME_HQL);
            }
            if (dateMin != null && !"".equals(dateMin)) {
                search_HQL.append(CustomerConstant.COOPERATION_DATE_MIN_HQL);
            }
            if (dateMax != null && !"".equals(dateMax)) {
                search_HQL.append(CustomerConstant.COOPERATION_DATE_MAX_HQL);
            }
            // cooperation times search
            if (timesMin != null) {
                search_HQL.append(CustomerConstant.COOPERATION_TIMES_MIN_HQL);
            }
            if (timesMax != null) {
                search_HQL.append(CustomerConstant.COOPERATION_TIMES_MAX_HQL);
            }
            // industry combo search
            if (industry.length() != 0 && !CRMConstant.SEARCHALL_FLAG.equals(industry)) {
                search_HQL.append(CustomerConstant.COOPERATOR_INDUSTRY_HQL);
            }
            // scale combo search
            if (scale.length() != 0 && !CRMConstant.SEARCHALL_FLAG.equals(scale)) {
                search_HQL.append(CustomerConstant.COOPERATOR_SCALE_HQL);
            }
            hqlList = CustomerConstant.SELECT_COOPERATOR_HQL + search_HQL.toString();
            hqlCount = CustomerConstant.SELECT_COOPERATOR_COUNT_HQL + search_HQL.toString();
        }
        // put values into map
        cooperatorShowList = hibernateTemplate.executeFind(new SuperHibernateCallback(hqlList,
                currPage, searchBean, limit));
        total = (Long) hibernateTemplate.executeFind(
                new SuperHibernateCallback(hqlCount, 0, searchBean, limit)).get(0);
        map.put(CRMConstant.TOTAL, total);
        map.put(CRMConstant.ITEMS, cooperatorShowList);
        LOG.debug("method searchCooperator end!");
        return map;
    }

    /**
     * add Cooperator or update Cooperator
     * 
     * @param cooperatorDto
     *            CooperatorDto cooperator dto for save or update
     */
    @Override
    public void updateCooperator(CooperatorDto cooperatorDto) {
        LOG.debug("method updateCooperator start!");
        hibernateTemplate.saveOrUpdate(cooperatorDto);
        LOG.debug("method updateCooperator end!");
    }

    /**
     * delete Cooperator
     * 
     * @param cooperatorIDs
     *            String Strings includes all ids for del
     */
    @Override
    public void deleteCooperator(String cooperatorIDs) {
        LOG.debug("method deleteCooperator start!");
        hibernateTemplate.bulkUpdate(CustomerConstant.DEL_COOPERATOR_HQL + cooperatorIDs);
        hibernateTemplate.bulkUpdate(CustomerConstant.REMOVE_COPANALY_HQL + cooperatorIDs);
        hibernateTemplate.bulkUpdate(CustomerConstant.DEL_COOPPROJECT_HQL + cooperatorIDs);
        hibernateTemplate.bulkUpdate(CustomerConstant.DEL_COOPCONTACT_HQL + cooperatorIDs);
        LOG.debug("method deleteCooperator end!");
    }

    /**
     * check input name existed
     * 
     * @param coo
     *            CooperatorDto Cooperator dto
     * @return boolean,true mean name existing,false mean name not existing
     */
    @Override
    public boolean checkNameExisted(CooperatorDto coo) {
        LOG.debug("method checkNameExisted start!");
        boolean nameExistedValid = false;
        Long total = (Long) hibernateTemplate.executeFind(
                new SuperHibernateCallback(CustomerConstant.CHECK_COOPERATOR_NAME_HQL, 0, coo, 0))
                .get(0);
        if (total > 0) {
            nameExistedValid = true;
        } else {
            nameExistedValid = false;
        }
        LOG.debug("method checkNameExisted end!");
        return nameExistedValid;
    }
}