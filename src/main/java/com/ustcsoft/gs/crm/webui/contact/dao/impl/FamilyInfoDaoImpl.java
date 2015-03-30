package com.ustcsoft.gs.crm.webui.contact.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.contact.constant.ContactConstant;
import com.ustcsoft.gs.crm.webui.contact.dao.FamilyInfoDao;
import com.ustcsoft.gs.crm.webui.contact.dto.FamilyDto;

/**
 * get familyInfo messages
 * 
 * @author shenkaichuan
 * 
 */
public class FamilyInfoDaoImpl implements FamilyInfoDao {
    private HibernateTemplate hibernateTemplate;

    private static Log LOG = LogFactory.getLog(FamilyInfoDaoImpl.class);

    /**
     * method to get all familyInfo records from DB
     * 
     * @return familyInfoList familyInfo records
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * 
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<FamilyDto> getAllFamilyInfo() throws DataAccessException {
        LOG.debug("method getAllFamilyInfo  start!");
        List<FamilyDto> familyInfoList = hibernateTemplate.find(ContactConstant.FAMILY_SELECT_ALL);
        LOG.debug("method getAllFamilyInfo  end!");
        return familyInfoList;
    }

    /**
     * method to get all familyInfo records from DB
     * 
     * @param contactID
     *            contactID
     * @return familyInfoList familyInfo records
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * 
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<FamilyDto> getFamilyInfoByContact(final int contactID) throws DataAccessException {
        LOG.debug("method get familyInfo by contact  start!");
        List<FamilyDto> familyInfoList = hibernateTemplate.find(ContactConstant.FAMILY_SELECT
                + contactID);
        LOG.debug("method get familyInfo by contact end!");
        return familyInfoList;
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
