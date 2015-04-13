package com.ustcsoft.gs.crm.webui.contact.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.contact.bean.ContactSearchBean;
import com.ustcsoft.gs.crm.webui.contact.dto.ContactInfoDto;
import com.ustcsoft.gs.crm.webui.contact.dto.FamilyDto;
import com.ustcsoft.gs.crm.webui.contact.dto.SocialDto;

/**
 * @author xuzhen
 */
public interface ContactInfoDao {
    /**
     * method to get all contacts records and searched records from DB
     * 
     * @param searchFlag
     *            search mark
     * @param contactSearchBean
     *            search conditions
     * @param currpage
     *            search page
     * @param customerID 
     * @return map search result,Map<String, Object>
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * 
     */
    public Map<String, Object> getAllContact(final int searchFlag,
            final ContactSearchBean contactSearchBean, final int currpage, final int limit, final String customerID)
            throws DataAccessException;

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
     * @param customerID 
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * 
     */
    public void addContact(ContactInfoDto contactInfo, List<FamilyDto> familyInfoList,
            List<SocialDto> socialInfoList, String familyID, String socialID)
            throws DataAccessException;

    /**
     * delete Contact record from DB
     * 
     * @param contactIDs
     *            will be deteled contactIDs
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * 
     */
    public void deleteContact(String contactIDs) throws DataAccessException;

    /**
     * find ContactInfo by contactID
     * 
     * @param contactID
     *            find contactID
     * @return ContactInfoDto
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * 
     */
    public ContactInfoDto findByID(int contactID) throws DataAccessException;

    /**
     * get contact can select
     * 
     * @param contactSearchText
     * @return map
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * 
     */
    public Map<String, Object> getContactCanSelect(String contactSearchText)
            throws DataAccessException;

    public boolean exist(ContactInfoDto dto);
}
