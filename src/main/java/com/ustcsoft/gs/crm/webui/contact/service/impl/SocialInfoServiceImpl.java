package com.ustcsoft.gs.crm.webui.contact.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.contact.dao.SocialInfoDao;
import com.ustcsoft.gs.crm.webui.contact.dto.SocialDto;
import com.ustcsoft.gs.crm.webui.contact.service.SocialInfoService;

/**
 * get socialInfo messages
 * 
 * @author shenkaichuan
 * 
 */
public class SocialInfoServiceImpl implements SocialInfoService {
    private SocialInfoDao socialInfoDao = null;

    private static Log LOG = LogFactory.getLog(SocialInfoServiceImpl.class);

    /**
     * query all socialInfos
     * 
     * @return socialInfoList socialInfo records
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * 
     */
    @Override
    public List<SocialDto> getAllSocialInfo() throws CRMDBException {
        List<SocialDto> socialInfoList;
        try {
            LOG.debug("method query all socialInfos,start!");
            socialInfoList = socialInfoDao.getAllSocialInfo();
            LOG.debug("method query all socialInfos,end!");
        } catch (DataAccessException e) {
            LOG.info("DataAccessException occurs in method getAllSocialInfo!", e);
            throw new CRMDBException(e);
        }
        return socialInfoList;
    }

    /**
     * query contactID's socialInfos
     * 
     * @param contactID
     * @return socialInfoList socialInfo records
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * 
     */
    @Override
    public List<SocialDto> getSocialInfoByContact(final int contactID) throws CRMDBException {
        List<SocialDto> socialInfoList;
        try {
            LOG.debug("method query contactID's socialInfos start!");
            socialInfoList = socialInfoDao.getSocialInfoByContact(contactID);
            LOG.debug("method query contactID's socialInfos end!");
        } catch (DataAccessException e) {
            LOG.info("DataAccessException occurs in method getSocialInfoByContact!", e);
            throw new CRMDBException(e);
        }
        return socialInfoList;
    }

    /**
     * @return the scialInfoDao
     */
    public SocialInfoDao getSocialInfoDao() {
        return socialInfoDao;
    }

    /**
     * @param socialInfoDao
     *            the scialInfoDao to set
     */
    public void setSocialInfoDao(SocialInfoDao socialInfoDao) {
        this.socialInfoDao = socialInfoDao;
    }
}
