package com.ustcsoft.gs.crm.webui.customer.service.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.ContactSelectSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dao.ContactSelectDao;
import com.ustcsoft.gs.crm.webui.customer.dto.ContactSelectDto;
import com.ustcsoft.gs.crm.webui.customer.service.ContactSelectService;

/**
 * Description: The class is used to deal with select service.
 * 
 * @author libaoshan
 * 
 */
public class ContactSelectServiceImpl implements ContactSelectService {

    private static Log LOG = LogFactory.getLog(ContactSelectServiceImpl.class);

    private ContactSelectDao contactSelectDao = null;

    /**
     * Get all ContactSelect
     * 
     * @param searchFlag
     *            0 is show list without conditions
     * @param searchBean
     *            is conditions
     * @param page
     *            is current page
     * @param limit
     *            is pageSize
     * 
     * @throws CRMDBException
     *             if there is a DataAccessException
     */
    @Override
    public Map<String, Object> getAllContactSelect(int searchFlag,
            ContactSelectSearchBean searchBean, int page, int limit) throws CRMDBException {
        LOG.debug("method getAllContactSelect start!");
        Map<String, Object> map = null;
        try {
            map = contactSelectDao.getContactSelect(searchFlag, searchBean, page, limit);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getAllContactSelect!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getAllContactSelect end!");
        return map;
    }

    /**
     * Add ContactSelect
     * 
     * @param contactSelectAddIDs
     *            entity object need add
     * 
     * @param objectFlag
     * @param objectID
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    // @Transactional(rollbackFor = Exception.class, propagation =
    // Propagation.REQUIRED)
    @Override
    public void saveContactSelect(String contactSelectAddIDs, int objectFlag, int objectID)
            throws CRMDBException {
        LOG.debug("method saveContactSelect start!");
        try {
            ContactSelectDto contactSelectDto = new ContactSelectDto();
            contactSelectDto.setFlag(objectFlag);
            contactSelectDto.setObjectID(objectID);
            String[] contactSelectAddID = contactSelectAddIDs.split(", ");
            for (String element : contactSelectAddID) {
                contactSelectDto.setContactSelectID(0);
                contactSelectDto.setContactID(Integer.valueOf(element));
                contactSelectDao.saveContactSelect(contactSelectDto);
            }
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method saveContactSelect!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method saveContactSelect end!");
    }

    /**
     * Delect ContactSelect
     * 
     * @param contactSelectIDs
     *            need delete
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteContactSelect(String contactSelectIDs) throws CRMDBException {
        LOG.debug("method deleteContactSelect start!");
        try {
            contactSelectDao.deleteContactSelect(contactSelectIDs);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method deleteContactSelect!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method deleteContactSelect end!");
    }

    /**
     * @return the contactSelectDao
     */
    public ContactSelectDao getContactSelectDao() {
        return contactSelectDao;
    }

    /**
     * @param contactSelectDao
     *            the contactSelectDao to set
     */
    public void setContactSelectDao(ContactSelectDao contactSelectDao) {
        this.contactSelectDao = contactSelectDao;
    }
}
