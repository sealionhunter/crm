/**
 * 
 */
package com.ustcsoft.gs.crm.webui.contact.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.contact.dto.FamilyDto;

/**
 * get familyInfo messages
 * 
 * @author shenkaichuan
 * 
 */
public interface FamilyInfoDao {
    /**
     * method to get all familyInfo records from DB
     * 
     * @return List<FamilyDto> familyInfo records
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * 
     */
    public List<FamilyDto> getAllFamilyInfo() throws DataAccessException;

    /**
     * method to get contactID's familyInfo records from DB
     * 
     * @param contactID
     * @return List<FamilyDto> familyInfo records
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * 
     */
    public List<FamilyDto> getFamilyInfoByContact(int contactID) throws DataAccessException;

}
