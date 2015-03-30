/*
 * Class name: CoopResumeDaoImpl
 * 
 * Version information: 1.0
 * 
 * Date:2012.9.11
 *  
 */
package com.ustcsoft.gs.crm.webui.customer.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.util.PagingHibernateCallback;
import com.ustcsoft.gs.crm.webui.common.util.SuperHibernateCallback;
import com.ustcsoft.gs.crm.webui.customer.bean.CoopResumeBean;
import com.ustcsoft.gs.crm.webui.customer.bean.CoopResumeSearchBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.customer.dao.CoopResumeDao;
import com.ustcsoft.gs.crm.webui.customer.dto.CoopResumeDto;

/**
 * 
 * @author tangyunpeng
 */
public class CoopResumeDaoImpl implements CoopResumeDao {

    private static Log LOG = LogFactory.getLog(CoopResumeDaoImpl.class);

    HibernateTemplate hibernateTemplate = null;

    /**
     * select all customer from DB return to page
     * 
     * @param currPage
     *            search page
     * @return map query results(coopResumeList, total) to display
     */
    @Override
    public Map<String, Object> getCoopResume(final int currPage, int customerID, int pageSize) {
        LOG.debug("getCoopResume start");
        Map<String, Object> map = new HashMap<String, Object>();
        @SuppressWarnings("unchecked")
        List<CoopResumeBean> coopResumeList = hibernateTemplate
                .executeFind(new PagingHibernateCallback(CustomerConstant.GET_COOPRESUME_HQL,
                        currPage, CustomerConstant.CUSTOMER_ID, customerID, pageSize));
        map.put(CRMConstant.ITEMS, coopResumeList);
        map.put(CRMConstant.TOTAL,
                (int) getCoopResumeSize(CustomerConstant.COOPRESUME_COUNT_HQL, customerID));
        LOG.debug("getCoopResume end.");
        return map;
    }

    /**
     * The method is used to get the number of all records.
     * 
     * @param queryString
     * @return count long
     */
    @Override
    public long getCoopResumeSize(String queryString, int customerID) {
        List<?> result = hibernateTemplate.find(queryString, customerID);
        return (Long) result.get(0);
    }

    /**
     * The method is used to query coopResume.
     * 
     * @param searchFlag
     *            search mark
     * @param searchValue
     *            search conditions
     * @param currPage
     *            search page
     * @return map query results(sourceList, total) to display
     */
    @Override
    @SuppressWarnings({ "unchecked" })
    public Map<String, Object> queryCoopResume(int searchFlag,
            final CoopResumeSearchBean searchValue, final int currPage, int pageSize) {
        List<CoopResumeBean> result = null;
        long total = 0;
        Map<String, Object> map = new HashMap<String, Object>();
        // super query
        if (searchFlag == 2) {

            String query = CustomerConstant.COOP_SUPER_QUERY_HQL + CustomerConstant.COOP_CONDITIONS;
            String queryCount = CustomerConstant.COOP_SUPER_COUNT_HQL
                    + CustomerConstant.COOP_CONDITIONS;
            if (!(searchValue.getProjectScale() == null || CRMConstant.SPACE
                    .equalsIgnoreCase(searchValue.getProjectScale()))) {
                query += " and coop.projectScale like:projectScale";
                queryCount += " and coop.projectScale like:projectScale";
            }
            if (searchValue.getPeopleNumber() > 0) {
                query += " and coop.peopleNumber like:peopleNumber";
                queryCount += " and coop.peopleNumber like:peopleNumber";
            }

            if (!(searchValue.getBeginDateSearchMin() == null || CRMConstant.SPACE
                    .equalsIgnoreCase(searchValue.getBeginDateSearchMin()))) {
                query += CustomerConstant.COOP_BEGIN_DATE_MIN;
                queryCount += CustomerConstant.COOP_BEGIN_DATE_MIN;
            }
            if (!(searchValue.getBeginDateSearchMax() == null || CRMConstant.SPACE
                    .equalsIgnoreCase(searchValue.getBeginDateSearchMax()))) {
                query += CustomerConstant.COOP_BEGIN_DATE_MAX;
                queryCount += CustomerConstant.COOP_BEGIN_DATE_MAX;
            }
            if (!(searchValue.getProjectType().length == 0 || CRMConstant.SEARCHALL_FLAG
                    .equalsIgnoreCase(searchValue.getProjectType()[0]))) {
                query += CustomerConstant.PROJECTTYPE_SEARCH;
                queryCount += CustomerConstant.PROJECTTYPE_SEARCH;

            }

            result = hibernateTemplate.executeFind(new SuperHibernateCallback(query, currPage,
                    searchValue, pageSize));
            final String queryCountStr = queryCount;
            total = (Long) hibernateTemplate.executeFind(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) {
                    Query query = session.createQuery(queryCountStr);
                    query.setProperties(searchValue);
                    return query.list();
                }
            }).get(0);
        }
        // simple query
        if (searchFlag == 1) {
            String value = searchValue.getSearchText();
            int customerID = searchValue.getCustomerID();
            String[] paramNames = { CRMConstant.SEARCHTEXT, CustomerConstant.CUSTOMER_ID };
            Object[] values = { value, customerID };
            result = hibernateTemplate.executeFind(new PagingHibernateCallback(
                    CustomerConstant.COOPRESUME_SIMPLEQUERY_HQL, currPage, paramNames, values,
                    pageSize));
            total = (Long) hibernateTemplate.findByNamedParam(
                    CustomerConstant.COOPRESUME_SIMPLECOUNT_HQL, paramNames, values).get(0);
        }
        map.put(CRMConstant.ITEMS, result);
        map.put(CRMConstant.TOTAL, total);
        LOG.debug("method queryCoopResume end.");
        return map;
    }

    /**
     * The method is used to add or update record.
     * 
     * @param coopResumeDto
     *            CoopResumeDto which will be added or edited
     */
    @Override
    public void addOrUpdateCoopResume(CoopResumeDto coopResumeDto) {
        LOG.debug("method addOrUpdateCoopResume start.");
        hibernateTemplate.saveOrUpdate(coopResumeDto);
        LOG.debug("method addOrUpdateCoopResume end.");
    }

    /**
     * The method is used to delete record.
     * 
     * @param coopResumeID
     *            coopResumeID which will be deleted
     */
    @Override
    public void deleteCoopResume(String coopResumeID) {
        LOG.debug("method deleteCoopResume start.");
        hibernateTemplate.bulkUpdate(CustomerConstant.COOP_DEL_HQL + "(" + coopResumeID + ")");
        LOG.debug("method deleteCoopResume start.");
    }

    /**
     * @param coopResumeDto
     * 
     * @return true projectName is
     */
    @Override
    public boolean judgeProjectName(CoopResumeDto coopResumeDto) {
        boolean bool = true;
        String[] paramNames = { CustomerConstant.COOP_RESUME_ID, CustomerConstant.PROJECT_NAME,
                CustomerConstant.CUSTOMER_ID };
        Object[] values = { coopResumeDto.getCoopResumeID(), coopResumeDto.getProjectName(),
                coopResumeDto.getCustomerID() };
        long count = (Long) hibernateTemplate.findByNamedParam(
                CustomerConstant.COOP_PROJECTNAME_COUNT, paramNames, values).get(0);
        if (count == 0) {
            return false;
        }
        return bool;
    }

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

}