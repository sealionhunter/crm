package com.ustcsoft.gs.crm.webui.customer.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.util.PagingHibernateCallback;
import com.ustcsoft.gs.crm.webui.common.util.SuperHibernateCallback;
import com.ustcsoft.gs.crm.webui.customer.bean.CustomerNameBean;
import com.ustcsoft.gs.crm.webui.customer.bean.SourceInfoBean;
import com.ustcsoft.gs.crm.webui.customer.bean.SourceSearchBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.customer.dao.SourceInfoDao;
import com.ustcsoft.gs.crm.webui.customer.dto.SourceInfoDto;

/**
 * 客户来源与数据库交互的增删改查实现类
 * 
 * @author xuzhen
 * 
 */
public class SourceInfoDaoImpl implements SourceInfoDao {
    private static final Log LOG = LogFactory.getLog(SourceInfoDao.class);

    private HibernateTemplate hibernateTemplate;

    /**
     * select all customer source from DB return to page
     * 
     * @param currpage
     *            search page
     * @return map query results(sourceList, total) to display
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getAllSource(int currpage, int limit, Integer[] userID)
            throws DataAccessException {
        LOG.debug("method getAllSource method start!");
        Map<String, Object> map = new HashMap<String, Object>();
        List<SourceInfoBean> sourceList = null;
        sourceList = getHibernateTemplate().executeFind(
                new PagingHibernateCallback(CustomerConstant.GET_ALLSOURCE, currpage,
                        CustomerConstant.USER_ID, userID, limit));
        long total = (Long) getHibernateTemplate().findByNamedParam(
                CustomerConstant.GET_ALLSOURCECOUNT_HQL, CRMConstant.USER_ID, userID).get(0);
        map.put(CRMConstant.TOTAL, total);
        map.put(CRMConstant.ITEMS, sourceList);
        LOG.debug("method getAllSource method end!");
        return map;
    }

    /**
     * search customer source from DB return to page
     * 
     * @param searchFlag
     *            search mark
     * @param sourceSearchBean
     *            search conditions
     * @param currpage
     *            search page
     * @return map query results(sourceList, total) to display
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    @SuppressWarnings({ "unchecked" })
    public Map<String, Object> searchSource(int searchFlag, SourceSearchBean sourceSearchBean,
            int currpage, int limit) throws DataAccessException {
        LOG.debug("method searchSource method start!");
        Map<String, Object> map = new HashMap<String, Object>();
        long total = 0;
        List<SourceInfoBean> sourceList = null;
        if (searchFlag == 1) {
            // // return the records conformed to the simple search criteria
            // String value = sourceSearchBean.getSearchText();
            sourceList = getHibernateTemplate().executeFind(
                    new SuperHibernateCallback(CustomerConstant.GET_ALLSOURCE
                            + CustomerConstant.SOURCE_SIMPLESEARCH_HQL, currpage, sourceSearchBean,
                            limit));
            total = (Long) getHibernateTemplate().executeFind(
                    new SuperHibernateCallback(CustomerConstant.SOURCE_SIMPLECOUNT_HQL, 0,
                            sourceSearchBean, 0)).get(0);
        } else {
            String query = CustomerConstant.GET_ALLSOURCE + CustomerConstant.SOURCE_SEARCH
                    + CustomerConstant.SOURCECUSTOMER_SEARCH;
            String queryCount = CustomerConstant.SOURCE_ADVANCECOUNT_HQL
                    + CustomerConstant.SOURCE_SEARCH + CustomerConstant.SOURCECUSTOMER_SEARCH;
            String[] sourceType = sourceSearchBean.getSourceType();
            if (!(sourceType == null || sourceType.length == 0 || CRMConstant.SEARCHALL_FLAG
                    .equalsIgnoreCase(sourceType[0]))) {
                query += CustomerConstant.SOURCETYPE_SEARCH;
                queryCount += CustomerConstant.SOURCETYPE_SEARCH;
            }
            sourceList = getHibernateTemplate().executeFind(
                    new SuperHibernateCallback(query, currpage, sourceSearchBean, limit));
            total = (Long) getHibernateTemplate().executeFind(
                    new SuperHibernateCallback(queryCount, 0, sourceSearchBean, limit)).get(0);
        }
        map.put(CRMConstant.TOTAL, total);
        map.put(CRMConstant.ITEMS, sourceList);
        LOG.debug("method searchSource method end!");
        return map;
    }

    /**
     * method to delete customerSource record from DB
     * 
     * @param sourceIDs
     *            sourceID which will be deleted
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public void deleteSource(String sourceIDs) {
        LOG.debug("method deleteSource method start!");
        getHibernateTemplate().bulkUpdate(CustomerConstant.DELETE_SOURCE + sourceIDs);
        LOG.debug("method deleteSource method end!");
    }

    /**
     * method to update customerSource record from DB
     * 
     * @param source
     *            sourceInfo which will be added or edited
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public void updateSource(SourceInfoDto source) throws DataAccessException {
        LOG.debug("method updateSource method start!");
        getHibernateTemplate().saveOrUpdate(source);
        LOG.debug("method updateSource method end!");
    }

    /**
     * method to get all customers' name and id
     * 
     * @return map query results(customerNameList, total) to display
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    @SuppressWarnings({ "unchecked" })
    public Map<String, Object> getCustomer(int customerFlag, final Integer[] userID)
            throws DataAccessException {
        LOG.debug("method getCustomer method start!");
        Map<String, Object> map = new HashMap<String, Object>();
        List<CustomerNameBean> customerNameList;
        String queryStr = CustomerConstant.GETALL_CUSTOMER_NAMEANDID;
        if (customerFlag == 1) {
            queryStr = CustomerConstant.GETCUSTOMER_NAMEANDID;
        }
        queryStr += CustomerConstant.HOLDER_USER_ID;
        customerNameList = getHibernateTemplate().executeFind(
                new PagingHibernateCallback(queryStr, 0, CRMConstant.USER_ID, userID, 0));
        map.put(CRMConstant.ITEMS, customerNameList);
        // map.put(CRMConstant.TOTAL, total);
        LOG.debug("method getCustomer method end!");
        return map;
    }

    /**
     * 
     * @return hibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    /**
     * 
     * @param hibernateTemplate
     *            the hibernateTemplate to set
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

}
