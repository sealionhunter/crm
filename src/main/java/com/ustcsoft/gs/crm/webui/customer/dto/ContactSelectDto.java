package com.ustcsoft.gs.crm.webui.customer.dto;

public class ContactSelectDto {

    private int contactSelectID = 0;
    private int contactID;
    private int objectID;
    private int flag;

    /**
     * @return the contactSelectID
     */
    public int getContactSelectID() {
        return contactSelectID;
    }

    /**
     * @param contactSelectID
     *            the contactSelectID to set
     */
    public void setContactSelectID(int contactSelectID) {
        this.contactSelectID = contactSelectID;
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

    public int getObjectID() {
        return objectID;
    }

    public void setObjectID(int objectID) {
        this.objectID = objectID;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
