package com.ustcsoft.gs.crm.webui.mail.dao;

import java.sql.SQLException;
import java.util.List;

import com.ustcsoft.gs.crm.webui.mail.bean.MailInfoBean;

/**
 * Description: The interface is used to link the database and get the
 * information.
 * 
 * @author libaoshan
 * 
 */
public interface MailDao {

    /**
     * Get send information
     * 
     * @param date
     *            today
     * @param flag
     *            = 0 today need send information, flag > 0 the unit need send
     *            information
     * 
     * @throws SQLException
     *             when an application tries to load in a class through its
     *             string name using
     * @throws ClassNotFoundException
     *             when an application tries to load in a class through its
     *             string name using
     */
    public List<MailInfoBean> getMailInfo(String date, int flag) throws SQLException,
            ClassNotFoundException;

    /**
     * Set the isMailInformed false
     * 
     * @param workIDs
     *            the information need set
     * 
     * @throws SQLException
     *             when an application tries to load in a class through its
     *             string name using
     * @throws ClassNotFoundException
     *             when an application tries to load in a class through its
     *             string name using
     */
    public void setIsMailed(List<String> workIDs) throws SQLException, ClassNotFoundException;

}
