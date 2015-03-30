package com.ustcsoft.gs.crm.webui.mail.bean;

import java.util.Properties;

/**
 * Description: The class is used to store information need to send.
 * 
 * @author libaoshan
 * 
 */
public class MailSenderInfoBean {

    /** the IP and port of sender */
    private String mailServerHost;
    private String mailServerPort = "25";

    /** the address of sender */
    private String fromAddress;

    /** the address of receiver */
    private String toAddress;

    /** the name and password of mail server */
    private String userName;
    private String password;

    /** identity authentication */
    private boolean validate = false;

    /** the title of E-mail */
    private String subject;

    /** the detail of E-mail */
    private String content;

    /**
     * get the conversation attribute of the E-mail
     */
    public Properties getProperties() {
        Properties p = new Properties();
        p.put("mail.smtp.host", mailServerHost);
        p.put("mail.smtp.port", mailServerPort);
        p.put("mail.smtp.auth", validate ? "true" : "false");
        return p;
    }

    /**
     * @return the mailServerHost
     */
    public String getMailServerHost() {
        return mailServerHost;
    }

    /**
     * @param mailServerHost
     *            the mailServerHost to set
     */
    public void setMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
    }

    /**
     * @return the mailServerPort
     */
    public String getMailServerPort() {
        return mailServerPort;
    }

    /**
     * @param mailServerPort
     *            the mailServerPort to set
     */
    public void setMailServerPort(String mailServerPort) {
        this.mailServerPort = mailServerPort;
    }

    /**
     * @return the fromAddress
     */
    public String getFromAddress() {
        return fromAddress;
    }

    /**
     * @param fromAddress
     *            the fromAddress to set
     */
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    /**
     * @return the toAddress
     */
    public String getToAddress() {
        return toAddress;
    }

    /**
     * @param toAddress
     *            the toAddress to set
     */
    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the validate
     */
    public boolean isValidate() {
        return validate;
    }

    /**
     * @param validate
     *            the validate to set
     */
    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject
     *            the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

}
