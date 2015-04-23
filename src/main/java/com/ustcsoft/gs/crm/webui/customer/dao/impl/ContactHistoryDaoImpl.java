package com.ustcsoft.gs.crm.webui.customer.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.util.PagingHibernateCallback;
import com.ustcsoft.gs.crm.webui.common.util.SuperHibernateCallback;
import com.ustcsoft.gs.crm.webui.customer.bean.ContactHistorySearchBean;
import com.ustcsoft.gs.crm.webui.customer.bean.ContactTrackListBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.customer.dao.ContactHistoryDao;

/**
 * @author yinweili
 * 
 */
public class ContactHistoryDaoImpl implements ContactHistoryDao {

    private static final Log LOG = LogFactory.getLog(ContactHistoryDaoImpl.class);

    /** define hibernateTemplate */
    private HibernateTemplate hibernateTemplate;

    @SuppressWarnings("unchecked")
    public List<ContactTrackListBean> getAllContactHistory(int customerID) {
        LOG.debug("method getAllContactHistory start!");
        List<ContactTrackListBean> list = hibernateTemplate.find(
                CustomerConstant.CONTACTHISTORY_GETALL_HQL_BY_CUSTOMERID,
                customerID);
        LOG.debug("method getAllContactHistory end!");

        return list;
    }

    /**
     * simple search or super search or get all
     * 
     * @param searchFlag
     *            used for judging query type
     * @param contactHistorySearchBean
     *            used for recoding query condition
     * @param currPage
     *            used for recording the current page
     * @param pageSize
     *            used for recording the size of one page
     * @param userID
     *            used for recording the userID
     * @return map used for return the query records
     * @throws DataAccessException
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> searchOrGetAllContactHistory(int searchFlag,
            final ContactHistorySearchBean contactHistorySearchBean, final int currPage,
            final int pageSize, final int customerID) {
        LOG.debug("method searchOrGetAllContactHistory start!");
        Map<String, Object> map = new HashMap<String, Object>();
        long total = 0;
        List<ContactTrackListBean> contactTrackListBeans = null;
        if (searchFlag == 0) {
            // get all records
            contactTrackListBeans = hibernateTemplate.executeFind(new PagingHibernateCallback(
                    CustomerConstant.CONTACTHISTORY_GETALL_HQL, currPage, CustomerConstant.CUSTOMER_ID,
                    customerID, pageSize));
            total = (Long) hibernateTemplate.findByNamedParam(
                    CustomerConstant.CONTACTHISTORY_COUNT_HQL, CustomerConstant.CUSTOMER_ID, customerID).get(0);
        } else if (searchFlag == 1) {
            // simple search
            contactHistorySearchBean.setCustomerID(customerID);
            contactTrackListBeans = hibernateTemplate.executeFind(new SuperHibernateCallback(
                    CustomerConstant.CONTACTHISTORY_GETALL_HQL
                            + CustomerConstant.CONTACTHISTORY_SIMPLE_HQL, currPage,
                    contactHistorySearchBean, pageSize));
            total = (Long) hibernateTemplate.executeFind(
                    new SuperHibernateCallback(CustomerConstant.CONTACTHISTORY_COUNT_HQL
                            + CustomerConstant.CONTACTHISTORY_SIMPLE_HQL, 0,
                            contactHistorySearchBean, 0)).get(0);
        } else {
            // super search
            final String customerName = contactHistorySearchBean.getCustomerName();
//            final String weContact = contactHistorySearchBean.getWeContact();
            final String oppositeContact = contactHistorySearchBean.getOppositeContact();
            final String contactType = contactHistorySearchBean.getContactType();
            final String contactWay = contactHistorySearchBean.getContactWay();
            final String ifContact = contactHistorySearchBean.getIfContact();
            final String searchDateStart = contactHistorySearchBean.getSearchDateStart();
            final String searchDateEnd = contactHistorySearchBean.getSearchDateEnd();
            StringBuffer searchHqlList = new StringBuffer(
                    CustomerConstant.CONTACTHISTORY_GETALL_HQL);
            StringBuffer searchHqlCount = new StringBuffer(
                    CustomerConstant.CONTACTHISTORY_COUNT_HQL);
            StringBuffer searchHqlWhere = new StringBuffer();
            if (!(customerName == null || CRMConstant.DOUBLE_PER_CENT.equals(customerName))) {
                searchHqlWhere.append(CustomerConstant.CONTACTHISTORY_CUSTOMERSEARCH);
            }
//            if (!(weContact == null || CRMConstant.DOUBLE_PER_CENT.equals(weContact))) {
//                searchHqlWhere.append(CustomerConstant.CONTACTHISTORY_WECONTACTSEARCH);
//            }
            if (!(oppositeContact == null || CRMConstant.DOUBLE_PER_CENT.equals(oppositeContact))) {
                searchHqlWhere.append(CustomerConstant.CONTACTHISTORY_OPPOSITESEARCH);
            }
            if (!(searchDateStart == null || CRMConstant.SPACE.equals(searchDateStart))) {
                searchHqlWhere.append(CustomerConstant.CONTACTHISTORY_DATESTARTSEARCH);
            }
            if (!(searchDateEnd == null || CRMConstant.SPACE.equals(searchDateEnd))) {
                searchHqlWhere.append(CustomerConstant.CONTACTHISTORY_DATEENDSEARCH);
            }
            if (!(contactType == null || contactType.length() == 0 || CRMConstant.SEARCHALL_FLAG
                    .equals(contactHistorySearchBean.getContactType()))) {
                searchHqlWhere.append(CustomerConstant.CONTACTHISTORY_CONTACTTYPESEARCH);
            }
            if (!(contactWay == null || contactWay.length() == 0 || CRMConstant.SEARCHALL_FLAG
                    .equals(contactHistorySearchBean.getContactWay()))) {
                searchHqlWhere.append(CustomerConstant.CONTACTHISTORY_CONTACTWAYSEARCH);
            }
            if (!(ifContact == null || CRMConstant.SPACE.equals(ifContact) || CRMConstant.SEARCHALL_FLAG
                    .equals(ifContact))) {
                searchHqlWhere.append(CustomerConstant.CONTACTHISTORY_IFCONTACTSEARCH);
                searchHqlWhere.append(ifContact);
            }
            searchHqlList.append(searchHqlWhere);
            searchHqlCount.append(searchHqlWhere);
            final String searchHqlCountStr = searchHqlCount.toString();
            contactHistorySearchBean.setCustomerID(customerID);
            contactTrackListBeans = hibernateTemplate.executeFind(new SuperHibernateCallback(
                    searchHqlList.toString(), currPage, contactHistorySearchBean, pageSize));
            total = (Long) hibernateTemplate.executeFind(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException,
                        SQLException {
                    Query query = session.createQuery(searchHqlCountStr);
                    query.setProperties(contactHistorySearchBean);
                    return query.list();
                }
            }).get(0);
        }
        map.put(CRMConstant.TOTAL, total);
        map.put(CRMConstant.ITEMS, contactTrackListBeans);
        LOG.debug("method searchOrGetAllContactHistory end!");
        return map;
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
