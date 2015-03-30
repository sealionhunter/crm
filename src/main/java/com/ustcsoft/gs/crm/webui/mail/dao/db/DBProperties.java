package com.ustcsoft.gs.crm.webui.mail.dao.db;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Description: The class is used to get the information which connection the
 * database.
 * 
 * @author libaoshan
 * 
 */
public class DBProperties {

    /** Store the driver */
    private static String driverClassName = null;

    private static String userName = null;

    private static String password = null;

    private static String url = null;

    static {
        init();
    }

    /**
     * @return the driverClassName
     */
    public static String getDriverClassName() {
        return driverClassName;
    }

    /**
     * @return the userName
     */
    public static String getUserName() {
        return userName;
    }

    /**
     * @return the password
     */
    public static String getPassword() {
        return password;
    }

    /**
     * @return the url
     */
    public static String getUrl() {
        return url;
    }

    private static void init() {

        // Get the service address
        String homeFolder = System.getProperty("wtp.deploy");
        String propertyFilePath = "";
        if (homeFolder == null) {
            homeFolder = System.getProperty("user.dir");
            propertyFilePath = homeFolder + "/src/main/webapp/WEB-INF/conf/DB.properties";
        } else {
            propertyFilePath = homeFolder + "/crm/WEB-INF/conf/DB.properties";
        }
        Properties props = new Properties();
        try {
            props.load(new BufferedInputStream(new FileInputStream(propertyFilePath)));
            driverClassName = props.getProperty("driverClassName");
            url = props.getProperty("url");
            userName = props.getProperty("username");
            password = props.getProperty("password");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
