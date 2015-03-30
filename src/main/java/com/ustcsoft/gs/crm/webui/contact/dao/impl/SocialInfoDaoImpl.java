package com.ustcsoft.gs.crm.webui.contact.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.contact.constant.ContactConstant;
import com.ustcsoft.gs.crm.webui.contact.dao.SocialInfoDao;
import com.ustcsoft.gs.crm.webui.contact.dto.SocialDto;

/**
 * get socialInfo messages
 * 
 * @author shenkaichuan
 * 
 */
public class SocialInfoDaoImpl implements SocialInfoDao {
    private HibernateTemplate hibernateTemplate;

    private static Log LOG = LogFactory.getLog(SocialInfoDaoImpl.class);

    /**
     * method to get all socialInfo records from DB
     * 
     * @return socialInfoList socialInfo records
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * 
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<SocialDto> getAllSocialInfo() throws DataAccessException {
        LOG.debug("method get socialInfo start!");
        List<SocialDto> socialInfoList = hibernateTemplate.find(ContactConstant.SOCIAL_SELECT_ALL);
        LOG.debug("method get socialInfo end!");
        return socialInfoList;
    }

    /**
     * method to get contactID's socialInfo records from DB
     * 
     * @param contactID
     * @return socialInfoList socialInfo records
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * 
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<SocialDto> getSocialInfoByContact(final int contactID) throws DataAccessException {
        LOG.debug("method get socialInfo by contact start!");
        List<SocialDto> socialInfoList = hibernateTemplate.find(ContactConstant.SOCIAL_SELECT
                + contactID);
        LOG.debug("method get socialInfo by contact end!");
        return socialInfoList;
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
