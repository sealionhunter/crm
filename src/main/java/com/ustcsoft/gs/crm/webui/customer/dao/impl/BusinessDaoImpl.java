package com.ustcsoft.gs.crm.webui.customer.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.common.util.PagingHibernateCallback;
import com.ustcsoft.gs.crm.webui.customer.bean.BusinessSearchBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.customer.dao.BusinessDao;
import com.ustcsoft.gs.crm.webui.customer.dto.BusinessDto;

public class BusinessDaoImpl implements BusinessDao {
//    private static final Log LOG = LogFactory.getLog(BusinessDaoImpl.class);

    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public void addOrUpdateBusiness(BusinessDto businessDto) {
        hibernateTemplate.saveOrUpdate(businessDto);
        CRMUtils.setCustomerUpdateTime(hibernateTemplate, businessDto.getCustomerID());
    }

    @Override
    public void deleteBusiness(String businessIDs) {
        hibernateTemplate.bulkUpdate(CustomerConstant.BUSINESS_DEL_HQL + businessIDs);
        String substr = businessIDs.substring(1, businessIDs.length() - 1);
        String businessID = (substr.split(","))[0].trim();
        BusinessDto dto = hibernateTemplate.get(BusinessDto.class, Integer.parseInt(businessID));
        CRMUtils.setCustomerUpdateTime(hibernateTemplate, dto.getCustomerID());
    }

    @Override
    public Map<String, Object> getAllBusiness(int searchFlag, BusinessSearchBean searchBean, int page, int limit, int userID,
            int customerID) {
        Map<String, Object> map = new HashMap<String, Object>(); 
        List<?> businessList = hibernateTemplate.executeFind(new PagingHibernateCallback(
                CustomerConstant.GET_BUSINESS_LIST_HQL, page, CustomerConstant.CUSTOMER_ID,
                customerID, limit));
        long total = (Long) hibernateTemplate.findByNamedParam(CustomerConstant.GET_BUSINESS_TOTAL_HQL,
                CustomerConstant.CUSTOMER_ID, customerID).get(0);
        map.put(CRMConstant.ITEMS, businessList);
        map.put(CRMConstant.TOTAL, total);
        return map;
    }

    @Override
    public Map<String, Object> queryBusiness(BusinessSearchBean searchBean, int page, int limit) {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] paramNames = new String[] { "customerID", CRMConstant.SEARCHTEXT };
        Object[] values = new Object[] { searchBean.getCustomerID(), searchBean.getSearchText() };
        List<?> businessList = hibernateTemplate.executeFind(new PagingHibernateCallback(
                CustomerConstant.BUSINESS_QUERY_HQL, page, paramNames, values, limit));
        long total = (Long) hibernateTemplate.findByNamedParam(CustomerConstant.BUSINESS_QUERY_TOTAL_HQL,
                paramNames, values).get(0);
        map.put(CRMConstant.ITEMS, businessList);
        map.put(CRMConstant.TOTAL, total);
        return map;
    }

}
