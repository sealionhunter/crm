/**
 * 
 */
package com.ustcsoft.gs.crm.webui.contact.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.contact.dto.SocialDto;

/**
 * get socialInfo messages
 * 
 * @author shenkaichuan
 * 
 */
public interface SocialInfoDao {
    /**
     * method to get all socialInfo records from DB
     * 
     * @return List<SocialDto> socialInfo records
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public List<SocialDto> getAllSocialInfo() throws DataAccessException;

    /**
     * method to get contactID's socialInfo records from DB
     * 
     * @param contactID
     * @return List<SocialDto> socialInfo records
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * 
     */
    public List<SocialDto> getSocialInfoByContact(int contactID) throws DataAccessException;

}
