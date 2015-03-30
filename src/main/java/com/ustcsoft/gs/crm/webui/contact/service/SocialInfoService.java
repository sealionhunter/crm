/**
 * 
 */
package com.ustcsoft.gs.crm.webui.contact.service;

import java.util.List;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.contact.dto.SocialDto;

/**
 * get socialInfo messages
 * 
 * @author shenkaichuan
 * 
 */
public interface SocialInfoService {
    /**
     * query all socialInfos
     * 
     * @return List<SocialDto> socialInfo records
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * 
     */
    public List<SocialDto> getAllSocialInfo() throws CRMDBException;

    /**
     * query contactID's socialInfos by contactID
     * 
     * @param contactID
     * @return List<SocialDto> socialInfo records
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * 
     */
    public List<SocialDto> getSocialInfoByContact(int contactID) throws CRMDBException;

}
