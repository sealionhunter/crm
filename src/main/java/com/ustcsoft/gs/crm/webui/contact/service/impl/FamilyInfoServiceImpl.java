package com.ustcsoft.gs.crm.webui.contact.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.contact.dao.FamilyInfoDao;
import com.ustcsoft.gs.crm.webui.contact.dto.FamilyDto;
import com.ustcsoft.gs.crm.webui.contact.service.FamilyInfoService;

/**
 * get familyInfo messages
 * 
 * @author shenkaichuan
 * 
 */
public class FamilyInfoServiceImpl implements FamilyInfoService {
    private FamilyInfoDao familyInfoDao = null;

    private static Log LOG = LogFactory.getLog(FamilyInfoServiceImpl.class);

    /**
     * query contactID's familyInfo by contactID
     * 
     * @param contactID
     * @return List<FamilyDto> familyInfo records
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * 
     */
    @Override
    public List<FamilyDto> getFamilyInfoByContact(final int contactID) throws CRMDBException {
        List<FamilyDto> familyInfoList;
        try {
            LOG.debug("method query contactID's familyInfo by contactID start!");
            familyInfoList = familyInfoDao.getFamilyInfoByContact(contactID);
            LOG.debug("method query contactID's familyInfo by contactID end!");
        } catch (DataAccessException e) {
            LOG.info("DataAccessException occurs in method getFamilyInfoByContact!", e);
            throw new CRMDBException(e);
        }
        return familyInfoList;
    }

    /**
     * query all familyInfo
     * 
     * @return List<FamilyDto> familyInfo records
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * 
     */
    @Override
    public List<FamilyDto> getAllFamilyInfo() throws CRMDBException {
        List<FamilyDto> familyInfoList;
        try {
            LOG.debug("method query all familyInfo start!");
            familyInfoList = familyInfoDao.getAllFamilyInfo();
            LOG.debug("method query all familyInfo end!");
        } catch (DataAccessException e) {
            LOG.info("DataAccessException occurs in method getAllFamilyInfo!", e);
            throw new CRMDBException(e);
        }
        return familyInfoList;
    }

    /**
     * @return the familyInfoDao
     */
    public FamilyInfoDao getFamilyInfoDao() {
        return familyInfoDao;
    }

    /**
     * @param familyInfoDao
     *            the familyInfoDao to set
     */
    public void setFamilyInfoDao(FamilyInfoDao familyInfoDao) {
        this.familyInfoDao = familyInfoDao;
    }
}
