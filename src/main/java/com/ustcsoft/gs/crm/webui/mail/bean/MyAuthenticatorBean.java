package com.ustcsoft.gs.crm.webui.mail.bean;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Description: MyAuthenticatorService is the information of the user
 * 
 * @author libaoshan
 * 
 */
public class MyAuthenticatorBean extends Authenticator {
    String userName = null;
    String password = null;

    /**
     * 
     * @param username
     * @param password
     */
    public MyAuthenticatorBean(String username, String password) {
        userName = username;
        this.password = password;
    }

    /**
     * get username and password
     */
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }

}
