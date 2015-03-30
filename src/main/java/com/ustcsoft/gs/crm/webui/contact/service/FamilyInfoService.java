/**
 * 
 */
package com.ustcsoft.gs.crm.webui.contact.service;

import java.util.List;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.contact.dto.FamilyDto;

/**
 * get familyInfo messages
 * 
 * @author shenkaichuan
 * 
 */
public interface FamilyInfoService {
    /**
     * query contactID's familyInfos by contactID
     * 
     * @param contactID
     * @return List<FamilyDto> familyInfo records
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * 
     */
    public List<FamilyDto> getFamilyInfoByContact(int contactID) throws CRMDBException;

    /**
     * query all familyInfos
     * 
     * @return List<FamilyDto> familyInfo records
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * 
     */
    public List<FamilyDto> getAllFamilyInfo() throws CRMDBException;

}
