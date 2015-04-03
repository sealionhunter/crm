package com.ustcsoft.gs.crm.webui.customer.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.common.util.PagingHibernateCallback;
import com.ustcsoft.gs.crm.webui.common.util.SuperHibernateCallback;
import com.ustcsoft.gs.crm.webui.customer.bean.ContactTrackListBean;
import com.ustcsoft.gs.crm.webui.customer.bean.ContactTrackSearchBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.customer.dao.ContactTrackDao;
import com.ustcsoft.gs.crm.webui.customer.dto.ContactTrackInfoDto;

/***
 * 
 * @author zhouzhou
 * 
 */
public class ContactTrackDaoImpl implements ContactTrackDao {

    private static final Log LOG = LogFactory.getLog(ContactTrackDaoImpl.class);

    private HibernateTemplate hibernateTemplate;

    /**
     * @param hibernateTemplate
     *            the hibernateTemplate to set
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /**
     * the function is getting information form database to ContactTrackList.js
     * 
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * @param searchFlag
     *            is 1 or 2, a flag of searching
     * @param contactTrackSearchBean
     *            store the conditions of searching
     * @param page
     *            store the currentPage
     * @return List<ContactTrackInfoDto>
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getAllContactTrack(final int searchFlag,
            final ContactTrackSearchBean contactTrackSearchBean, final int page, final int limit,
            final int customerID) {
        LOG.debug("method getAllContactTrack start!");
        Map<String, Object> map = new HashMap<String, Object>();
        long total = 0;
        List<ContactTrackListBean> contactTrackListBeans = null;
        if (searchFlag == 0) {
            contactTrackListBeans = hibernateTemplate.executeFind(new PagingHibernateCallback(
                    CustomerConstant.GET_ALLCONTACTTRACK_HQL, page, CustomerConstant.CUSTOMER_ID,
                    customerID, limit));
            total = (Long) hibernateTemplate.findByNamedParam(
                    CustomerConstant.GET_ALLCONTACTTRACK_TOTAL_HQL, CustomerConstant.CUSTOMER_ID,
                    customerID).get(0);
        } else if (searchFlag == 1) {
            contactTrackSearchBean.setCustomerID(customerID);
            contactTrackSearchBean.setSearchText(CRMUtils.trimSearch(contactTrackSearchBean
                    .getSearchText()));
            contactTrackListBeans = hibernateTemplate.executeFind(new SuperHibernateCallback(
                    CustomerConstant.GET_ALLCONTACTTRACK_HQL + CustomerConstant.CONTACT_SIMPLE_HQL,
                    page, contactTrackSearchBean, limit));
            total = (Long) hibernateTemplate.executeFind(
                    new SuperHibernateCallback(CustomerConstant.GET_ALLCONTACTTRACK_TOTAL_HQL
                            + CustomerConstant.CONTACT_SIMPLE_HQL, 0, contactTrackSearchBean, 0))
                    .get(0);
        } else {
            contactTrackSearchBean.setCustomerID(customerID);
//            String customerName = contactTrackSearchBean.getCustomerName();
            String weContact = contactTrackSearchBean.getWeContact();
            String oppositeContact = contactTrackSearchBean.getOppositeContact();
            String contactWay = contactTrackSearchBean.getContactWay();
            String planDateMin = contactTrackSearchBean.getPlanDateMin();
            String planDateMax = contactTrackSearchBean.getPlanDateMax();
            String contactType = contactTrackSearchBean.getContactType();

            StringBuffer str = new StringBuffer();
//            if (!(customerName == null || CRMConstant.SPACE.equals(customerName))) {
//                str.append(CustomerConstant.CONTACTTRACK_CUSTOMER_SEARCH_HQL);
//                contactTrackSearchBean.setCustomerName(CRMUtils.trimSearch(customerName));
//            }
            if (!(weContact == null || CRMConstant.SPACE.equals(weContact))) {
                str.append(CustomerConstant.CONTACTTRACK_WECONTACT_SEARCH_HQL);
                contactTrackSearchBean.setWeContact(CRMUtils.trimSearch(weContact));
            }
            if (!(oppositeContact == null || CRMConstant.SPACE.equals(oppositeContact))) {
                str.append(CustomerConstant.CONTACTTRACK_OPPOSITECONTACT_SEARCH_HQL);
                contactTrackSearchBean.setOppositeContact(CRMUtils.trimSearch(oppositeContact));
            }
            if (!(planDateMin == null || CRMConstant.SPACE.equals(planDateMin))) {
                str.append(CustomerConstant.CONTACTTRACK_CONTACTDATESTART_SEARCH_HQL);
            }
            if (!(planDateMax == null || CRMConstant.SPACE.equals(planDateMax))) {
                str.append(CustomerConstant.CONTACTTRACK_CONTACTDATEEND_SEARCH_HQL);
            }
            if (!(contactWay == null || contactWay.length() == 0 || CRMConstant.SEARCHALL_FLAG
                    .equals(contactWay))) {
                str.append(CustomerConstant.CONTACTTRACK_CONTACTWAY_SEARCH_HQL);
            }

            if (!(contactType == null || contactType.length() == 0 || CRMConstant.SEARCHALL_FLAG
                    .equals(contactType))) {
                str.append(CustomerConstant.CONTACTTRACK_CONTACTTYPE_SEARCH_HQL);
            }
            StringBuffer sb = new StringBuffer(CustomerConstant.GET_ALLCONTACTTRACK_HQL);
            StringBuffer sbc = new StringBuffer(CustomerConstant.GET_ALLCONTACTTRACK_TOTAL_HQL);
            sb.append(str);
            sbc.append(str);
            final String countSql = sbc.toString();
            String sql = sb.toString();
            contactTrackListBeans = hibernateTemplate.executeFind(new SuperHibernateCallback(sql,
                    page, contactTrackSearchBean, limit));
            total = (Long) hibernateTemplate.executeFind(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) {
                    Query query = session.createQuery(countSql);
                    query.setProperties(contactTrackSearchBean);
                    return query.list();
                }
            }).get(0);
        }
        map.put(CRMConstant.ITEMS, contactTrackListBeans);
        map.put(CRMConstant.TOTAL, total);
        LOG.debug("method getAllContactTrack end!");
        return map;
    }

    /**
     * the function is change the isAbolished of contactTrackInfo from false to
     * true
     * 
     * @param contactIDs
     * 
     */
    @Override
    public void deleteContactTrack(String contactIDs) {
        LOG.debug("method deleteContactTrack start!");
        hibernateTemplate.bulkUpdate(CustomerConstant.GETNUMOFCONTACTTRACK_HQL + contactIDs);
        CRMUtils.setCustomerUpdateTime(hibernateTemplate);
        LOG.debug("method deleteContactTrack end!");
    }

    /**
     * the function is add or update the information
     * 
     * @param contactTrackInfoDto
     */
    @Override
    public void updateContactTrack(ContactTrackInfoDto contactTrackInfoDto) {
        LOG.debug("method updateContactTrack start!");
        hibernateTemplate.saveOrUpdate(contactTrackInfoDto);
        CRMUtils.setCustomerUpdateTime(hibernateTemplate);
        LOG.debug("method updateContactTrack end!");
    }

//    /**
//     * the function used for add sales event information to database
//     * 
//     * @param salesEventDto
//     */
//    @Override
//    public void updateSalesEvent(SalesEventDto salesEventDto) {
//        LOG.debug("method updateSalesEvent start!");
//        hibernateTemplate.saveOrUpdate(salesEventDto);
//        LOG.debug("method updateSalesEvent end!");
//    }

//    /**
//     * the function used for add sales track information to database
//     * 
//     * @param salesTrackDto
//     */
//    @Override
//    public void addSalesTrack(SalesTrackDto salesTrackDto) {
//        LOG.debug("method addSalesTrack start!");
//        hibernateTemplate.saveOrUpdate(salesTrackDto);
//        LOG.debug("method addSalesTrack end!");
//    }
//
//    /**
//     * the function used for add sales track information to database
//     * 
//     * @throws DataAccessException
//     *             in case of Hibernate Exception
//     * @param salesTrackNo
//     */
//    @Override
//    public void updateSalesTrack(int salesTrackNo) {
//        LOG.debug("method updateSalesTrack start!");
//        hibernateTemplate.bulkUpdate(CustomerConstant.UPDATE_SALESTRACK_HQL, salesTrackNo);
//        LOG.debug("method updateSalesTrack end!");
//    }
//
//    /**
//     * the function used for add sales event flow information to database
//     * 
//     * @param salesEventFlowDto
//     */
//    @Override
//    public void addSalesEventFlow(SalesEventFlowDto salesEventFlowDto) {
//        LOG.debug("method addSalesEventFlow start!");
//        hibernateTemplate.saveOrUpdate(salesEventFlowDto);
//        LOG.debug("method addSalesEventFlow end!");
//    }

//    /**
//     * the function used for add sales event flow information to database
//     * 
//     * @param salesEventFlowDto
//     */
//    @Override
//    public void updateSalesEventFlow(SalesEventFlowDto salesEventFlowDto) {
//        LOG.debug("method updateSalesEventFlow start!");
//        Object[] value = { salesEventFlowDto.getDemand(), salesEventFlowDto.getId() };
//        hibernateTemplate.bulkUpdate(CustomerConstant.UPDATE_SALESEVENTFLOW_HQL, value);
//        LOG.debug("method updateSalesEventFlow end!");
//    }

//    /**
//     * the function used for query SalesTrackNo information to database
//     * 
//     * @param eventID
//     */
//    @Override
//    @SuppressWarnings("unchecked")
//    public int querySalesTrackNoByEventID(int eventID) {
//        LOG.debug("method querySalesTrackNoByEventID start!");
//        List<SalesTrackDto> list = null;
//        int SalesTrackNo = 0;
//        list = hibernateTemplate.find(CustomerConstant.QUERY_SALESTRACK_HQL, eventID);
//        if (list != null) {
//            SalesTrackNo = list.get(0).getSalesTrackNo();
//        }
//        LOG.debug("method querySalesTrackNoByEventID end!");
//        return SalesTrackNo;
//    }

//    /**
//     * the function used for query SalesEventFlow id information from database
//     * by eventID and status
//     * 
//     * @param eventID
//     * @param status
//     */
//    @Override
//    @SuppressWarnings("unchecked")
//    public int querySalesEventFlowByEventID(int eventID, int status) {
//        LOG.debug("method querySalesEventFlowByEventID start!");
//        List<SalesEventFlowDto> list = null;
//        int id = 0;
//        String[] str = { "eventID", "status" };
//        Object[] values = { eventID, status };
//        list = hibernateTemplate.findByNamedParam(CustomerConstant.QUERY_SALESEVENTFLOW_HQL, str,
//                values);
//        if (list.size() != 0) {
//            id = list.get(0).getId();
//        }
//        LOG.debug("method querySalesEventFlowByEventID end!");
//        return id;
//    }

//    /**
//     * the function is delete failure sales event information by eventID
//     * 
//     * @param eventID
//     */
//    @Override
//    public void deleteSalesEventByEventID(int eventID) {
//        LOG.debug("method deleteSalesEventByEventID start!");
//        hibernateTemplate.bulkUpdate(CustomerConstant.DELETE_SALESEVENT_HQL, eventID);
//        LOG.debug("method deleteSalesEventByEventID end!");
//    }
}
