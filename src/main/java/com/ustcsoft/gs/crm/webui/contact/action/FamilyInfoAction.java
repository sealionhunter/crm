/*
 * 
 */
package com.ustcsoft.gs.crm.webui.contact.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.contact.constant.ContactConstant;
import com.ustcsoft.gs.crm.webui.contact.dto.FamilyDto;
import com.ustcsoft.gs.crm.webui.contact.service.FamilyInfoService;

/**
 * get familyInfo messages
 * 
 * @author shenkaichuan
 * 
 */
public class FamilyInfoAction extends CRMAction {
    private static final long serialVersionUID = 1L;

    private static Log LOG = LogFactory.getLog(FamilyInfoAction.class);

    private FamilyInfoService familyInfoService;

    /** receive contactID to query it's familyInfo messages */
    private int contactID;

    /**
     * get familyinfo by contactID
     * 
     * @return success output map(familyInfoList)
     * @throws CRMDBException
     *             DataAccessException in case of Hibernate Exception
     */
    @Override
    public String execute() throws CRMDBException {
        LOG.debug("method get contactID's familyInfos start!");
        List<FamilyDto> familyInfoList = familyInfoService.getFamilyInfoByContact(contactID);
        map.put(ContactConstant.CONTACTFAMILY, familyInfoList);
        map.put(CRMConstant.TOTAL, familyInfoList.size());
        LOG.debug("method get contactID's familyInfos end!");
        return SUCCESS;
    }

    /**
     * @return the contactID
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * @param contactID
     *            the contactID to set
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * @return the familyInfoService
     */
    public FamilyInfoService getFamilyInfoService() {
        return familyInfoService;
    }

    /**
     * @param familyInfoService
     *            the familyInfoService to set
     */
    public void setFamilyInfoService(FamilyInfoService familyInfoService) {
        this.familyInfoService = familyInfoService;
    }

}
