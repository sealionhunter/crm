package com.ustcsoft.gs.crm.webui.mail.dao.db;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Description: The class is used to get the mail information.
 * 
 * @author libaoshan
 * 
 */
public class MailProperties {

    private static String mailServiceHost = null;

    private static String mailServerPort = null;

    private static String fromAddress = null;

    static {
        init();
    }

    /**
     * @return the mailServiceHost
     */
    public static String getMailServiceHost() {
        return mailServiceHost;
    }

    /**
     * @return the mailServerPort
     */
    public static String getMailServerPort() {
        return mailServerPort;
    }

    /**
     * @return the fromAddress
     */
    public static String getFromAddress() {
        return fromAddress;
    }

    /**
     * This method is to get the IP、port of the Specified Mail Server.
     */
    private static void init() {

        // Get the service address
        String homeFolder = System.getProperty("wtp.deploy");
        String propertyFilePath = "";
        if (homeFolder == null) {
            homeFolder = System.getProperty("user.dir");
            propertyFilePath = homeFolder + "/src/main/webapp/WEB-INF/conf/mail.properties";
        } else {
            propertyFilePath = homeFolder + "/crm/WEB-INF/conf/mail.properties";
        }
        Properties props = new Properties();
        try {
            props.load(new BufferedInputStream(new FileInputStream(propertyFilePath)));
            mailServiceHost = props.getProperty("mailServiceHost");
            mailServerPort = props.getProperty("mailServerPort");
            fromAddress = props.getProperty("fromAddress");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
