package com.ustcsoft.gs.crm.webui.contact.service;

import java.util.List;
import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.contact.bean.ContactSearchBean;
import com.ustcsoft.gs.crm.webui.contact.dto.ContactInfoDto;
import com.ustcsoft.gs.crm.webui.contact.dto.FamilyDto;
import com.ustcsoft.gs.crm.webui.contact.dto.SocialDto;

/**
 * @author xuzhen
 */
public interface ContactInfoService {
    /**
     * method to get all contacts records and searched records
     * 
     * @param searchFlag
     *            search mark
     * @param contactSearchBean
     *            search conditions
     * @param currpage
     *            search page
     * @param customerID 
     * @return map search result,Map<String, Object>
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * 
     */
    public Map<String, Object> getAllContact(int searchFlag, ContactSearchBean contactSearchBean,
            int currpage, int limit, String customerID) throws CRMDBException;

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
    public void addContact(ContactInfoDto contactInfo, List<FamilyDto> familyInfoList,
            List<SocialDto> socialInfoList, String familyID, String socialID) throws CRMDBException;

    /**
     * delete contct record
     * 
     * @param contactIDs
     *            will be deteled contactIDs
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * 
     */
    public Map<String, Object> deleteContact(String contactIDs) throws CRMDBException;

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
    public ContactInfoDto findByID(int contactID) throws CRMDBException;

    /**
     * get contact can select
     * 
     * @param contactSearchText
     * @return map
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * 
     */
    public Map<String, Object> getContactCanSelect(String contactSearchText) throws CRMDBException;

    public boolean exist(ContactInfoDto dto);
}
