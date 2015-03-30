package com.ustcsoft.gs.crm.webui.customer.bean;

import com.ustcsoft.gs.crm.webui.contact.bean.ContactSearchBean;

/**
 * Description: The class is storing the contact select search.
 * 
 * @author heweiwei
 * @author libaoshan
 * 
 */
public class ContactSelectSearchBean extends ContactSearchBean {

    /** contact flag */
    private int objectFlag;

    /** contact ID */
    private int objectID;

    /**
     * @return the objectFlag
     */
    public int getObjectFlag() {
        return objectFlag;
    }

    /**
     * @param objectFlag
     *            the objectFlag to set
     */
    public void setObjectFlag(int objectFlag) {
        this.objectFlag = objectFlag;
    }

    /**
     * @return the objectID
     */
    public int getObjectID() {
        return objectID;
    }

    /**
     * @param objectID
     *            the objectID to set
     */
    public void setObjectID(int objectID) {
        this.objectID = objectID;
    }

}
