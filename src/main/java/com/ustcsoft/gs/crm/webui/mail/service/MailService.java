package com.ustcsoft.gs.crm.webui.mail.service;

import java.util.List;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.mail.bean.MailInfoBean;
import com.ustcsoft.gs.crm.webui.mail.bean.MailSenderInfoBean;

/**
 * Description: The class is used to send mail and get mail information.
 * 
 * @author libaoshan
 * 
 */
public interface MailService {

    /**
     * Timing execution method
     */
    public void run();

    /**
     * Send email
     * 
     * @param mailInfo
     *            send information
     * 
     * @return true success, false failed
     */
    public boolean sendTextMail(MailSenderInfoBean mailInfo);

    /**
     * Get send information
     * 
     * @param flag
     *            = 0 today need send information, flag > 0 the unit need send
     *            information
     * 
     * @return mailInfoBeans store send information
     * 
     * @throws CRMDBException
     */
    public List<MailInfoBean> getMailInfo(int flag) throws CRMDBException;

    /**
     * Set mail content
     * 
     * @param mailInfoBean
     *            the information which need to send
     * 
     * @return mailContent mail content information
     */
    public String setMailContent(MailInfoBean mailInfoBean);

    /**
     * Set the ismailInformed false
     * 
     * @param workIDs
     *            the information need set
     * 
     * @throws CRMDBException
     *             when an application tries to load in a class through its
     *             string name using
     */
    public void setIsMailed(List<String> workIDs) throws CRMDBException;

}
