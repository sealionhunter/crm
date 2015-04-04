package com.ustcsoft.gs.crm.webui.mail.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimerTask;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.mail.bean.MailInfoBean;
import com.ustcsoft.gs.crm.webui.mail.bean.MailSenderInfoBean;
import com.ustcsoft.gs.crm.webui.mail.bean.MyAuthenticatorBean;
import com.ustcsoft.gs.crm.webui.mail.constant.MailConstant;
import com.ustcsoft.gs.crm.webui.mail.dao.MailDao;
import com.ustcsoft.gs.crm.webui.mail.dao.db.MailProperties;
import com.ustcsoft.gs.crm.webui.mail.dao.impl.MailDaoImpl;
import com.ustcsoft.gs.crm.webui.mail.service.MailService;

/**
 * Description: The class is used to send mail and get mail information.
 * 
 * @author libaoshan
 * 
 */
public class MailServiceImpl extends TimerTask implements MailService {

    private static final Log LOG = LogFactory.getLog(MailServiceImpl.class);

    /**
     * Timing execution method
     */
    @Override
    public void run() {
        LOG.debug("method run start!");
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour >= 8 && hour <= 23) {
            MailSenderInfoBean mailSenderInfoBean = new MailSenderInfoBean();
            mailSenderInfoBean.setMailServerHost(MailProperties.getMailServiceHost());

            // Set the port of the mail service
            mailSenderInfoBean.setMailServerPort(MailProperties.getMailServerPort());

            // Set the address of sender
            mailSenderInfoBean.setFromAddress(MailProperties.getFromAddress());

            // Set password authentication protocol
            mailSenderInfoBean.setValidate(false);

            // Get information to send
            List<MailInfoBean> mailInfoBeans = null;
            try {
                mailInfoBeans = getMailInfo(0);
            } catch (CRMDBException e) {
                LOG.error("DataAccessException occurs in method getMailInfo!", e);
                return;
            }

            List<String> workIDList = new ArrayList<String>();
            for (int i = 0; i < mailInfoBeans.size(); i++) {
                mailSenderInfoBean.setToAddress(mailInfoBeans.get(i).getEmail());
                mailSenderInfoBean.setSubject(mailInfoBeans.get(i).getTheme());
                mailSenderInfoBean.setContent(setMailContent(mailInfoBeans.get(i)));

                // Send mail
                Boolean mailSuccess = sendTextMail(mailSenderInfoBean);
                if (mailSuccess) {
                    workIDList.add(mailInfoBeans.get(i).getWorkID() + "");
                }
            }
            if (workIDList.size() != 0) {
                try {
                    setIsMailed(workIDList);
                } catch (CRMDBException e) {
                    LOG.error("DataAccessException occurs in method setIsMailed!", e);
                }
            }
        }
        LOG.debug("method run end!");
    }

    /**
     * Send email
     * 
     * @param mailInfo
     *            send information
     * 
     * @return true success, false failed
     */
    @Override
    public boolean sendTextMail(MailSenderInfoBean mailInfo) {
        LOG.debug("method sendTextMail start!");

        // judge if there need the authentication
        MyAuthenticatorBean authenticator = null;
        Properties pro = mailInfo.getProperties();
        if (mailInfo.isValidate()) {

            // if need, then create a validator of password
            authenticator = new MyAuthenticatorBean(mailInfo.getUserName(), mailInfo.getPassword());
        }

        /*
         * create a session of send mail according to the mail property and the
         * password-validator
         */
        Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
        try {

            // create a mail message according to the session
            MimeMessage mailMessage = new MimeMessage(sendMailSession);
            MimeMultipart mp = new MimeMultipart();

            // create the address of the mail sender
            Address from = new InternetAddress(mailInfo.getFromAddress());

            // set the sender
            mailMessage.setFrom(from);

            // create the address of the mail receiver,and put it into the mail
            Address to = new InternetAddress(mailInfo.getToAddress());
            mailMessage.setRecipient(Message.RecipientType.TO, to);

            // set the theme of the mail
            mailMessage.setSubject(mailInfo.getSubject(), "UTF-8");

            // set the send time
            mailMessage.setSentDate(new Date());

            // set the detail of the mail
            BodyPart body = new MimeBodyPart();
            String mailContent = mailInfo.getContent();
            body.setContent(mailContent, "text/plain;charset=UTF-8");
            mp.addBodyPart(body);
            mailMessage.setContent(mp);

            // send mail
            Transport.send(mailMessage);
            LOG.debug("sendTextMail end!");
            return true;
        } catch (MessagingException e) {
            LOG.error("mail send failed!", e);
        }
        LOG.debug("method sendTextMail end!");
        return false;
    }

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
    @Override
    public List<MailInfoBean> getMailInfo(int flag) throws CRMDBException {
        LOG.debug("method getMailInfo start!");
        List<MailInfoBean> mailInfoBeans = null;
        MailDao mailDao = new MailDaoImpl();
        String date = new CRMUtils().getDate();
        try {
            mailInfoBeans = mailDao.getMailInfo(date, flag);
        } catch (SQLException e) {
            LOG.error("DataAccessException occurs in method getMailInfo!", e);
            throw new CRMDBException(e);
        } catch (ClassNotFoundException e) {
            LOG.error("DataAccessException occurs in method getMailInfo!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getMailInfo end!");
        return mailInfoBeans;
    }

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
    @Override
    public void setIsMailed(List<String> workIDs) throws CRMDBException {
        LOG.debug("method setIsMailed start!");
        MailDao mailDao = new MailDaoImpl();
        try {
            mailDao.setIsMailed(workIDs);
        } catch (SQLException e) {
            LOG.error("DataAccessException occurs in method setIsMailed!", e);
            throw new CRMDBException(e);
        } catch (ClassNotFoundException e) {
            LOG.error("DataAccessException occurs in method setIsMailed!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method setIsMailed end!");
    }

    /**
     * Set mail content
     * 
     * @param mailInfoBean
     *            the information which need to send
     * 
     * @return mailContent mail content information
     */
    @Override
    public String setMailContent(MailInfoBean mailInfoBean) {
        LOG.debug("method setMailContent start!");
        String mailContent = mailInfoBean.getUserName() + MailConstant.MAILCONTENT
                + MailConstant.ENTER + MailConstant.ENTER;
        // if (!((mailInfoBean.getCustomerName() == null) || (mailInfoBean
        // .getCustomerName().equals("")))) {
        if (!"".equals(mailInfoBean.getCustomerName())) {
            mailContent = mailContent + MailConstant.CUSTOMERNAME + mailInfoBean.getCustomerName()
                    + MailConstant.ENTER;
        }
        if (!(mailInfoBean.getCompletionName() == null || mailInfoBean.getCompletionName().equals(
                ""))) {
            mailContent = mailContent + MailConstant.COMPLETIONNAME
                    + mailInfoBean.getCompletionName() + MailConstant.ENTER;
        }
        if (!(mailInfoBean.getPriorityName() == null || mailInfoBean.getPriorityName().equals(""))) {
            mailContent = mailContent + MailConstant.PRIOIRTYNAME + mailInfoBean.getPriorityName()
                    + MailConstant.ENTER;
        }
        if (!(mailInfoBean.getWorkTypeName() == null || mailInfoBean.getWorkTypeName().equals(""))) {
            mailContent = mailContent + MailConstant.WORKTYPENAME + mailInfoBean.getWorkTypeName()
                    + MailConstant.ENTER;
        }
        if (!(mailInfoBean.getStartTime() == null || mailInfoBean.getStartTime().equals(""))) {
            mailContent = mailContent + MailConstant.STARTTIME + mailInfoBean.getStartTime()
                    + MailConstant.ENTER;
        }
        if (!(mailInfoBean.getEndTime() == null || mailInfoBean.getEndTime().equals(""))) {
            mailContent = mailContent + MailConstant.ENDTIME + mailInfoBean.getEndTime()
                    + MailConstant.ENTER;
        }
        if (!(mailInfoBean.getWorkDetail() == null || mailInfoBean.getWorkDetail().equals(""))) {
            mailContent = mailContent + MailConstant.WORKDETAIL + mailInfoBean.getWorkDetail()
                    + MailConstant.ENTER;
        }
        if (!(mailInfoBean.getDescriptions() == null || mailInfoBean.getDescriptions().equals(""))) {
            mailContent = mailContent + MailConstant.DESCRIPTIONS + mailInfoBean.getDescriptions()
                    + MailConstant.ENTER;
        }
        mailContent = mailContent + MailConstant.ENTER + new CRMUtils().getDate();
        LOG.debug("method setMailContent end!");
        return mailContent;
    }
}
