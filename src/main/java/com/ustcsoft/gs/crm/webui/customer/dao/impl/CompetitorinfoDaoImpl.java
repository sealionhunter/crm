/*
 * Class name: CompetitorinfoDaoImpl
 * 
 * Version information: 1.0
 * 
 * Date:2012.9.27
 *  
 */
package com.ustcsoft.gs.crm.webui.customer.dao.impl;

import java.util.ArrayList;
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
import com.ustcsoft.gs.crm.webui.customer.bean.CompetitorSearchBean;
import com.ustcsoft.gs.crm.webui.customer.bean.CompetitorinfoBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.customer.dao.CompetitorinfoDao;
import com.ustcsoft.gs.crm.webui.customer.dto.CompetitorinfoDto;

/**
 * @author weijinmei
 */
public class CompetitorinfoDaoImpl implements CompetitorinfoDao {

    private static Log log = LogFactory.getLog(CompetitorinfoDaoImpl.class);
    private HibernateTemplate hibernateTemplate;

    /**
     * @return hibernateTemplate
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
     * update Competitorinfo
     * 
     * @param competitorinfoDto
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public void updateCompetitorinfo(CompetitorinfoDto competitorinfoDto)
            throws DataAccessException {
        log.debug("method updateCompetitorinfo start!");
        hibernateTemplate.saveOrUpdate(competitorinfoDto);
        log.debug("method updateCompetitorinfo end!");
    }

    @Override
    @SuppressWarnings("unchecked")
    /**
     * get All Competitor
     * @param searchFlag 
     * @param searchBean 
     * @param currpage  
     * @throws DataAccessException
     * 						in case of Hibernate Exception
     */
    public Map<String, Object> getAllCompetitor(int searchFlag, CompetitorSearchBean searchBean,
            final int currpage, final int pageSize) throws DataAccessException {
        log.debug("method getAllCompetitor start!");
        Map<String, Object> competitorMap = new HashMap<String, Object>();
        long competitorTotal = 0;
        List<CompetitorinfoBean> competitorList = new ArrayList<CompetitorinfoBean>();
        StringBuffer queryList = new StringBuffer(CustomerConstant.GET_ALLCOMPETITOR_HQL);
        StringBuffer queryCount = new StringBuffer(CustomerConstant.COMPETITOR_COUNT_HQL);
        if (searchFlag != 0) {
            final String competitorType = searchBean.getCompetitorType();
            if (searchFlag == 2) {
                queryList.append(CustomerConstant.MID_GETALLCOMPETITOR_HQL);
                queryCount.append(CustomerConstant.MID_GETALLCOMPETITOR_HQL);
                final String competitorFoundDateStart = searchBean.getCompetitorFoundDateStart();
                final String competitorFoundDateEnd = searchBean.getCompetitorFoundDateEnd();
                if (!(searchBean.getCompetitorPeople().length == 0
                        || CRMConstant.SEARCHALL_FLAG.equalsIgnoreCase(searchBean
                                .getCompetitorPeople()[0]) || searchBean.getCompetitorPeople() == null)) {
                    queryList.append(" and ci.competitorPeople in (:competitorPeople) ");
                    queryCount.append(" and ci.competitorPeople in (:competitorPeople) ");
                }
                if (!(searchBean.getCompetitorMoney().length == 0
                        || CRMConstant.SEARCHALL_FLAG.equalsIgnoreCase(searchBean
                                .getCompetitorMoney()[0]) || searchBean.getCompetitorMoney() == null)) {
                    queryList.append(" and ci.competitorMoney in (:competitorMoney) ");
                    queryCount.append(" and ci.competitorMoney in (:competitorMoney) ");
                }
                if (!(searchBean.getCompetitorProperty().length == 0
                        || CRMConstant.SEARCHALL_FLAG.equalsIgnoreCase(searchBean
                                .getCompetitorProperty()[0]) || searchBean.getCompetitorProperty() == null)) {
                    queryList.append(" and ci.competitorProperty in (:competitorProperty) ");
                    queryCount.append(" and ci.competitorProperty in (:competitorProperty) ");
                }
                if (!("".equals(competitorFoundDateStart) || competitorFoundDateStart == null || competitorFoundDateStart
                        .length() == 0)) {
                    queryList.append(" and ci.competitorFoundDate >= '" + competitorFoundDateStart
                            + "'");
                    queryCount.append(" and ci.competitorFoundDate >= '" + competitorFoundDateStart
                            + "'");
                }
                if (!("".equals(competitorFoundDateEnd) || competitorFoundDateEnd == null || competitorFoundDateEnd
                        .length() == 0)) {
                    queryList.append(" and ci.competitorFoundDate <= '" + competitorFoundDateEnd
                            + "'");
                    queryCount.append(" and ci.competitorFoundDate <= '" + competitorFoundDateEnd
                            + "'");
                }
                competitorList = hibernateTemplate.executeFind(new SuperHibernateCallback(queryList
                        .toString(), currpage, searchBean, pageSize));
                competitorTotal = (Long) hibernateTemplate.executeFind(
                        new SuperHibernateCallback(queryCount.toString(), 0, searchBean, pageSize))
                        .get(0);
            } else if (searchFlag == 1) {
                queryList.append(CustomerConstant.COMPETITOR_SIMPLE_HQL);
                queryCount.append(CustomerConstant.COMPETITOR_SIMPLE_HQL);
                final String competitorSearchText = searchBean.getCompetitorSearchText();
                String[] paramNames = { CRMConstant.SEARCHTEXT, "type" };
                Object[] values = { competitorSearchText, competitorType };
                competitorList = hibernateTemplate.executeFind(new PagingHibernateCallback(
                        queryList.toString(), currpage, paramNames, values, 25));
                competitorTotal = (Long) hibernateTemplate.findByNamedParam(queryCount.toString(),
                        new String[] { "searchText", "type" },
                        new Object[] { competitorSearchText, competitorType }).get(0);
            }
        } else if (searchFlag == 0) {
            final String competitorType = searchBean.getCompetitorType();
            queryList.append(" and ci.competitorType = :competitorType");
            queryCount.append(" and ci.competitorType = ?");
            final String hqlList = queryList.toString();
            competitorTotal = (Long) hibernateTemplate.find(queryCount.toString(), competitorType)
                    .get(0);
            competitorList = hibernateTemplate.executeFind(new PagingHibernateCallback(hqlList,
                    currpage, "competitorType", competitorType, 25));
        }
        competitorMap.put(CRMConstant.TOTAL, competitorTotal);
        competitorMap.put(CRMConstant.ITEMS, competitorList);
        log.debug("method getAllCompetitor end!");
        return competitorMap;
    }

    /**
     * get Size of Competitorinfo
     * 
     * @param competitorType
     */
    @Override
    public long getCompetitorinfoSize(String competitorType) {
        log.debug("method getCompetitorinfoSize start!");
        CompetitorSearchBean searchBean = new CompetitorSearchBean();
        searchBean.setCompetitorType(competitorType);
        long competitorSize = (Long) getAllCompetitor(0, searchBean, 1, 25).get("results");
        log.debug("method getCompetitorinfoSize end!");
        return competitorSize;
    }

    /**
     * judge if CompetitorName existed
     * 
     * @param competitorName
     * @return type of boolean
     */
    @Override
    public boolean judgeCompetitorName(String competitorName) {
        log.debug("method judgeCompetitorName start!");
        long count = (Long) hibernateTemplate.findByNamedParam(
                CustomerConstant.COMPETITOR_NAME_COUNT_HQL, "competitorName", competitorName)
                .get(0);
        log.debug("method judgeCompetitorName end!");
        return !(count == 0);
    }

    /**
     * get CompetitorName By ID
     * 
     * @param competitorID
     * @return customerName type of String
     */
    @Override
    public String getCompetitorNameByComID(int competitorID) {
        log.debug("method getCompetitorNameByID start!");
        String customerName = (String) hibernateTemplate.findByNamedParam(
                CustomerConstant.COMPETITOR_FINDNAME_HQL, "competitorInfoId", competitorID).get(0);
        log.debug("method getCompetitorNameByID end!");
        return customerName;
    }

    /**
     * delete Competitorinfo
     * 
     * @param competitorInfoIds
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public void deleteCompetitorinfoAndCprAnalysis(String competitorInfoIds)
            throws DataAccessException {
        log.debug("method deleteCompetitors start!");
        String deleteIDs = "(" + competitorInfoIds.substring(1, competitorInfoIds.length() - 1)
                + ")";
        hibernateTemplate
                .bulkUpdate("update CompetitorinfoDto competitor set competitor.isAbolished = 1 "
                        + " where competitor.competitorInfoId in " + deleteIDs);
        hibernateTemplate
                .bulkUpdate("update CprAnalysisDto compAnalysis set compAnalysis.isAbolished = 1 "
                        + " where compAnalysis.competitorInfoId in " + deleteIDs);
        log.debug("method deletecompetitors end!");
    }

}
