package com.ustcsoft.gs.crm.webui.customer.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.PagingHibernateCallback;
import com.ustcsoft.gs.crm.webui.customer.bean.LeaderAdviceSearchBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.customer.dao.LeaderAdviceDao;
import com.ustcsoft.gs.crm.webui.customer.dto.LeaderAdviceDto;

public class LeaderAdviceDaoImpl implements LeaderAdviceDao {
//    private static final Log LOG = LogFactory.getLog(LeaderAdviceDaoImpl.class);

    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public void addOrUpdateLeaderAdvice(LeaderAdviceDto leaderAdviceDto) {
        hibernateTemplate.saveOrUpdate(leaderAdviceDto);
    }

    @Override
    public void deleteLeaderAdvice(String adviceIDs) {
        hibernateTemplate.bulkUpdate(CustomerConstant.LEADER_ADVICE_DEL_HQL + adviceIDs);
    }

    @Override
    public Map<String, Object> getAllLeaderAdvice(int searchFlag, LeaderAdviceSearchBean searchBean, int page, int limit, int userID,
            int customerID) throws CRMDBException {
        Map<String, Object> map = new HashMap<String, Object>(); 
        List<?> leaderAdviceList = hibernateTemplate.executeFind(new PagingHibernateCallback(
                CustomerConstant.GET_LEADER_ADVICE_LIST_HQL, page, CustomerConstant.CUSTOMER_ID,
                customerID, limit));
        long total = (Long) hibernateTemplate.findByNamedParam(CustomerConstant.GET_LEADER_ADVICE_TOTAL_HQL,
                CustomerConstant.CUSTOMER_ID, customerID).get(0);
        map.put(CRMConstant.ITEMS, leaderAdviceList);
        map.put(CRMConstant.TOTAL, total);
        return map;
    }

    @Override
    public Map<String, Object> queryLeaderAdvice(LeaderAdviceSearchBean searchBean, int page, int limit) {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] paramNames = new String[] { "customerID", CRMConstant.SEARCHTEXT };
        Object[] values = new Object[] { searchBean.getCustomerID(), searchBean.getSearchText() };
        List<?> leaderAdviceList = hibernateTemplate.executeFind(new PagingHibernateCallback(
                CustomerConstant.LEADER_ADVICE_QUERY_HQL, page, paramNames, values, limit));
        long total = (Long) hibernateTemplate.findByNamedParam(CustomerConstant.LEADER_ADVICE_QUERY_TOTAL_HQL,
                paramNames, values).get(0);
        map.put(CRMConstant.ITEMS, leaderAdviceList);
        map.put(CRMConstant.TOTAL, total);
        return map;
    }

}
