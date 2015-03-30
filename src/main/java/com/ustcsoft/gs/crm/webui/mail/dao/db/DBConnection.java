package com.ustcsoft.gs.crm.webui.mail.dao.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Description: The class is used to connection the database.
 * 
 * @author libaoshan
 * 
 */
public class DBConnection {

    /**
     * Connection the database
     * 
     * @return dbConn Connection
     * 
     * @throws SQLException
     *             an exception that provides information on a database access
     *             error or other errors
     * @throws ClassNotFoundException
     *             an application tries to load in a class through its string
     *             name using
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String driverName = DBProperties.getDriverClassName();
        String dbURL = DBProperties.getUrl();
        String userName = DBProperties.getUserName();
        String userPwd = DBProperties.getPassword();
        Connection dbConn = null;
        Class.forName(driverName);
        dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
        return dbConn;
    }

    /**
     * Close the connection
     * 
     * @param conn
     *            Connection which need to close
     * @throws SQLException
     *             an exception that provides information on a database access
     *             error or other errors
     */
    public void closeConnection(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}
