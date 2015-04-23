package com.ustcsoft.gs.crm.webui.contact.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.contact.bean.ContactBean;
import com.ustcsoft.gs.crm.webui.contact.bean.ContactSearchBean;
import com.ustcsoft.gs.crm.webui.contact.dao.ContactInfoDao;
import com.ustcsoft.gs.crm.webui.contact.dto.ContactInfoDto;
import com.ustcsoft.gs.crm.webui.contact.dto.FamilyDto;
import com.ustcsoft.gs.crm.webui.contact.dto.SocialDto;
import com.ustcsoft.gs.crm.webui.contact.service.ContactInfoService;

/**
 * @author xuzhen and shenkaichuan
 */
public class ContactInfoServiceImpl implements ContactInfoService {
    private ContactInfoDao contactInfoDao;

    private static Log LOG = LogFactory.getLog(ContactInfoServiceImpl.class);

    public List<ContactBean> getAllContact(int cusotmerID) throws CRMDBException {
        try {
            LOG.debug("method get all contacts records start!");
            List<ContactBean> list = contactInfoDao.getAllContact(cusotmerID);
            LOG.debug("method get all contacts records end!");

            return list;
        } catch (DataAccessException e) {
            LOG.info(" DataAccessException occurs in method getAllContact!", e);
            throw new CRMDBException(e);
        }
    }

    /**
     * method to get all contacts records and searched records
     * 
     * @param searchFlag
     *            search mark
     * @param contactSearchBean
     *            search conditions
     * @param currpage
     *            search page
     * @return map search result,Map<String, Object>
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * 
     */
    @Override
    public Map<String, Object> getAllContact(final int searchFlag,
            final ContactSearchBean contactSearchBean, final int currpage, final int limit, String cusotmerID) throws CRMDBException {
        Map<String, Object> map;
        try {
            LOG.debug("method get all contacts records start!");
            map = getContactInfoDao().getAllContact(searchFlag, contactSearchBean, currpage, limit, cusotmerID);
            LOG.debug("method get all contacts records end!");
        } catch (DataAccessException e) {
            LOG.info(" DataAccessException occurs in method getAllContact!", e);
            throw new CRMDBException(e);
        }
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
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * 
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void addContact(final ContactInfoDto contactInfo, final List<FamilyDto> familyInfoList,
            final List<SocialDto> socialInfoList, final String familyID, final String socialID)
            throws CRMDBException {
        try {
            LOG.debug("method add or update contact records start!");
            if (StringUtils.isBlank(contactInfo.getCreateTime())) {
                contactInfo.setCreateTime(CRMUtils.currentTimeAsString());
                contactInfo.setUpdateTime(null);
            } else {
                contactInfo.setUpdateTime(CRMUtils.currentTimeAsString());
                
            }
            contactInfoDao.addContact(contactInfo, familyInfoList, socialInfoList, familyID,
                    socialID);
            LOG.debug("method add or update contact records end!");
        } catch (DataAccessException e) {
            LOG.info("DataAccessException occurs in method addContact!", e);
            throw new CRMDBException(e);
        }

    }

    /**
     * delete contct record
     * 
     * @param contactIDs
     *            will be deteled contactIDs
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * 
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Map<String, Object> deleteContact(final String contactIDs) throws CRMDBException {
        LOG.debug("method delete contact record start!");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            contactInfoDao.deleteContact(CRMUtils.getStringForDelete(contactIDs));
            LOG.debug("method delete contact record end!");

        } catch (DataAccessException e) {
            LOG.info("DataAccessException occurs in method deleteContact!", e);
            throw new CRMDBException(e);
        }
        return map;
    }

    /**
     * find ContactInfo by contactID
     * 
     * @param contactID
     *            find contactID
     * @return ContactInfoDto
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * 
     */
    @Override
    public ContactInfoDto findByID(final int contactID) throws CRMDBException {
        ContactInfoDto contactInfo = null;
        try {
            LOG.debug("method findByID contactInfo record start!");
            contactInfo = contactInfoDao.findByID(contactID);
            LOG.debug("method findByID contactInfo record end!");

        } catch (DataAccessException e) {
            LOG.info("DataAccessException occurs in method findByID!", e);
            throw new CRMDBException(e);
        }
        return contactInfo;
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
    public Map<String, Object> getContactCanSelect(String contactSearchText) throws CRMDBException {
        LOG.debug("method getContactCanSelect start!");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = contactInfoDao.getContactCanSelect(contactSearchText);
        } catch (DataAccessException e) {
            LOG.info("DataAccessException occurs in getContactCanSelect!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getContactCanSelect start!");
        return map;
    }

    /**
     * 
     * @return contactInfoDao
     */
    public ContactInfoDao getContactInfoDao() {
        return contactInfoDao;
    }

    /**
     * 
     * @param contactInfoDao
     *            the contactInfoDao to set
     */
    public void setContactInfoDao(ContactInfoDao contactInfoDao) {
        this.contactInfoDao = contactInfoDao;
    }

    @Override
    public boolean exist(ContactInfoDto dto) {
        return contactInfoDao.exist(dto);
    }
}
