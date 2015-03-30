package com.ustcsoft.gs.crm.webui.contact.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.common.util.PagingHibernateCallback;
import com.ustcsoft.gs.crm.webui.common.util.SuperHibernateCallback;
import com.ustcsoft.gs.crm.webui.contact.bean.ContactBean;
import com.ustcsoft.gs.crm.webui.contact.bean.ContactSearchBean;
import com.ustcsoft.gs.crm.webui.contact.constant.ContactConstant;
import com.ustcsoft.gs.crm.webui.contact.dao.ContactInfoDao;
import com.ustcsoft.gs.crm.webui.contact.dto.ContactInfoDto;
import com.ustcsoft.gs.crm.webui.contact.dto.FamilyDto;
import com.ustcsoft.gs.crm.webui.contact.dto.SocialDto;
import com.ustcsoft.gs.crm.webui.customer.dto.ContactSelectDto;

public class ContactInfoDaoImpl extends CRMUtils implements ContactInfoDao {
    private static Log LOG = LogFactory.getLog(ContactInfoDaoImpl.class);

    private HibernateTemplate hibernateTemplate;

    /**
     * method to get all contacts records and searched records from DB
     * 
     * @param searchFlag
     *            search mark
     * @param contactSearchBean
     *            search conditions
     * @param currpage
     *            search page
     * @return map search result,Map<String, Object>
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * 
     */
    @Override
    @SuppressWarnings({ "unchecked" })
    public Map<String, Object> getAllContact(final int searchFlag,
            final ContactSearchBean contactSearchBean, final int currpage, final int limit,
            String customerID) throws DataAccessException {
        LOG.debug("method getAllContact start!");
        Map<String, Object> map = new HashMap<String, Object>();
        long total = 0;
        List<ContactBean> contactList = null;
        if (searchFlag == 0) {
            if (StringUtils.isBlank(customerID)) {
                // return the records
                contactList = hibernateTemplate.executeFind(new PagingHibernateCallback(
                        ContactConstant.GETALLCONTACT_HQL, currpage, limit));
                total = (Long) hibernateTemplate.find(ContactConstant.GETALLCONTACTCOUNT_HQL).get(0);
            } else {
                contactList = hibernateTemplate.executeFind(new PagingHibernateCallback(
                        ContactConstant.GETALLCONTACT_HQL_2, currpage, "customerID", Integer.parseInt(customerID), limit));
                total = (Long) hibernateTemplate.findByNamedParam(
                        ContactConstant.GETALLCONTACTCOUNT_HQL_2, "customerID", Integer.parseInt(customerID)).get(0);
            }
        } else if (searchFlag == 1) {
            String value = contactSearchBean.getSearchText();

            if (StringUtils.isBlank(customerID)) {
                // return the records conformed to the simple search criteria
                contactList = hibernateTemplate.executeFind(new PagingHibernateCallback(
                        ContactConstant.CONTACT_SIMPLESEARCH, currpage, CRMConstant.SEARCHTEXT,
                        value, limit));
                total = (Long) hibernateTemplate.findByNamedParam(
                        ContactConstant.CONTACT_SIMPLECOUNT, CRMConstant.SEARCHTEXT, value).get(0);
            } else {
                contactList = hibernateTemplate.executeFind(new PagingHibernateCallback(
                        ContactConstant.CONTACT_SIMPLESEARCH_2, currpage, new String[] {
                                "customerID", CRMConstant.SEARCHTEXT }, new Object[] { Integer.parseInt(customerID),
                                value }, limit));
                total = (Long) hibernateTemplate.findByNamedParam(
                        ContactConstant.CONTACT_SIMPLECOUNT_2,
                        new String[] { "customerID", CRMConstant.SEARCHTEXT },
                        new Object[] { Integer.parseInt(customerID), value }).get(0);
            }
        } else {

            if (StringUtils.isBlank(customerID)) {
                // the query for query record list
                String query = ContactConstant.CONTACT_ADVANCESEARCH + ContactConstant.CONTACT_SEARCH;
                
                // the queryCount for query count of record
                String queryCount = ContactConstant.CONTACT_ADVANCECOUNT
                        + ContactConstant.CONTACT_SEARCH;
                
                // return the records conformed to the expert search criteria
                contactList = hibernateTemplate.executeFind(new SuperHibernateCallback(query, currpage,
                        contactSearchBean, limit));
                total = (Long) hibernateTemplate.executeFind(
                        new SuperHibernateCallback(queryCount, 0, contactSearchBean, limit)).get(0);
            }
        }
        map.put(CRMConstant.TOTAL, total);
        map.put(ContactConstant.ITEMS, contactList);
        LOG.debug("method getAllContact end!");
        return map;
    }

    /**
     * add or update contact record ,maintenance it's familyInfo message and
     * socialInfo message
     * 
     * @param contactInfo
     *            add or update contact message
     * @param familyInfoList
     *            add or update familyInfo message
     * @param socialInfoList
     *            add or update socialInfo message
     * @param familyID
     *            will be deteled familyIDs
     * @param socialID
     *            will be deteled socialIDs
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * 
     */
    @Override
    public final void addContact(final ContactInfoDto contactInfo,
            final List<FamilyDto> familyInfoList, final List<SocialDto> socialInfoList,
            final String familyID, final String socialID) throws DataAccessException {
        LOG.debug("method addContact  start!");
        if (contactInfo.getContactID() == 0) {
            // add/edit contactInfo by contactInfo
            hibernateTemplate.saveOrUpdate(contactInfo);

            // add ContactID in familyInfoList
            if (familyInfoList != null) {
                for (int i = 0; i < familyInfoList.size(); i++) {
                    familyInfoList.get(i).setContactID(contactInfo.getContactID());
                }
            }

            // add ContactID in socialInfoList
            if (socialInfoList != null) {
                for (int i = 0; i < socialInfoList.size(); i++) {
                    socialInfoList.get(i).setContactID(contactInfo.getContactID());
                }
            }
        } else {
            hibernateTemplate.saveOrUpdate(contactInfo);
        }

        // add/edit familyInfo by familyInfoList
        if (familyInfoList != null) {
            LOG.debug("method add familyInfo  start!");
            hibernateTemplate.saveOrUpdateAll(familyInfoList);
            LOG.debug("method add familyInfo  end!");
        }

        // add/edit SocialInfo by socialInfoList
        if (socialInfoList != null) {
            LOG.debug("method add SocialInfo  start!");
            hibernateTemplate.saveOrUpdateAll(socialInfoList);
            LOG.debug("method add SocialInfo  end!");
        }

        // delete familyInfo by familyID
        if (familyID != null) {
            LOG.debug("method delete familyInfo start!");
            hibernateTemplate.bulkUpdate(ContactConstant.FAMILY_DELETE
                    + CRMConstant.LEFT_PARENTHESIS + familyID + CRMConstant.RIGHT_PARENTHESIS);
            LOG.debug("method delete familyInfo  end!");
        }

        // delete SocialInfo by socialID
        if (socialID != null) {
            LOG.debug("method delete SocialInfo  start!");
            hibernateTemplate.bulkUpdate(ContactConstant.SOCIAL_DELETE
                    + CRMConstant.LEFT_PARENTHESIS + socialID + CRMConstant.RIGHT_PARENTHESIS);
            LOG.debug("method delete SocialInfo  end!");
        }

        LOG.debug("method addContact  end!");

    }

    /**
     * delete ContactInfo
     * 
     * @param contactIDs
     *            will be deteled contactIDs
     * 
     */
    @Override
    public final void deleteContact(final String contactIDs) {
        LOG.debug("method deleteContact  start!");
        hibernateTemplate.bulkUpdate(ContactConstant.CONTACT_DELETE + contactIDs);
        LOG.debug("method deleteContact end!");
    }

    /**
     * delete ContactInfo to check
     * 
     * @param contactIDs
     *            will be check contactIDs
     * 
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<ContactSelectDto> deleteContactCheck(final String contactIDs) {
        LOG.debug("method deleteContactCheck  start!");
        List<ContactSelectDto> list = null;
        list = hibernateTemplate.find(ContactConstant.CONTACT_DELETE_CHECK + contactIDs);
        LOG.debug("method deleteContactCheck end!");
        return list;
    }

    /**
     * find ContactInfo by contactID
     * 
     * @param contactID
     *            find contactID
     * @return contactinfo
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * 
     */
    @Override
    public final ContactInfoDto findByID(final int contactID) throws DataAccessException {
        LOG.debug("method find contactInfo  start!");
        ContactInfoDto contactinfo = (ContactInfoDto) hibernateTemplate.find(
                ContactConstant.CONTACT_FIND_BY_ID + contactID).get(0);
        LOG.debug("method find contactInfo  end!");
        return contactinfo;
    }

    /**
     * get contact can select
     * 
     * @param contactSearchText
     * @return map
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * 
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getContactCanSelect(String contactSearchText)
            throws DataAccessException {
        LOG.debug("method find getContactCanSelect  start!");
        Map<String, Object> map = new HashMap<String, Object>();
        List<Integer> canSelectList = null;

        String selectHql = ContactConstant.GET_CONTACT_SELECTED;
        StringBuffer canSelectHql = new StringBuffer(ContactConstant.GET_CONTACT_CANSELECT);

        contactSearchText = CRMUtils.trimSearch(contactSearchText);
        canSelectHql.append(ContactConstant.QUERY_CONTACT_CANSELECT);

        List<Integer> selectList = hibernateTemplate.find(selectHql);
        String[] params = { ContactConstant.CONTACT_SEARCH_TEXT, ContactConstant.SELECT_LIST };
        Object[] values = { contactSearchText, selectList };

        if (selectList.size() == 0) {
            canSelectList = hibernateTemplate.findByNamedParam(canSelectHql.toString(),
                    ContactConstant.CONTACT_SEARCH_TEXT, contactSearchText);
        } else {
            canSelectHql.append(ContactConstant.REMOVE_SELECTED);
            canSelectList = hibernateTemplate.findByNamedParam(canSelectHql.toString(), params,
                    values);
        }
        map.put(ContactConstant.ITEMS, canSelectList);
        LOG.debug("method find getContactCanSelect end!");
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

    @Override
    public boolean exist(ContactInfoDto dto) {
        String[] paramNames = { "contactType", "customerID", "contactID" };
        Object[] values = { dto.getContactType(), dto.getCustomerID(), dto.getContactID() };
        long count = (Long) hibernateTemplate.findByNamedParam(
                ContactConstant.CONTACT_COUNT, paramNames, values).get(0);
        return count > 0;
    }

}
