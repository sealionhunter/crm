package com.ustcsoft.gs.crm.webui.mail.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.mail.bean.MailInfoBean;
import com.ustcsoft.gs.crm.webui.mail.constant.MailConstant;
import com.ustcsoft.gs.crm.webui.mail.dao.MailDao;
import com.ustcsoft.gs.crm.webui.mail.dao.db.DBConnection;

/**
 * Description: The class is used to link the database and get the information.
 * 
 * @author libaoshan
 * 
 */
public class MailDaoImpl implements MailDao {

    private static final Log LOG = LogFactory.getLog(MailDaoImpl.class);

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
    @Override
    public List<MailInfoBean> getMailInfo(String date, int flag) throws SQLException,
            ClassNotFoundException {
        LOG.debug("method getMail start!");
        List<MailInfoBean> mailInfoBeans = new ArrayList<MailInfoBean>();
        Connection conn = null;
        PreparedStatement psGet = null;
        ResultSet result = null;
        try {
            conn = DBConnection.getConnection();
            if (flag == 0) {
                psGet = conn.prepareStatement(MailConstant.GET_MAILINFO);
                psGet.setString(1, date);
                psGet.setString(2, date);
            } else {
                psGet = conn.prepareStatement(MailConstant.GET_MAILINFO_UNIT);
                psGet.setInt(1, flag);
            }
            result = psGet.executeQuery();

            while (result.next()) {
                MailInfoBean mailInfoBean = new MailInfoBean();
                mailInfoBean.setWorkID(result.getInt("workID"));
                mailInfoBean.setUserName(result.getString("realName"));
                mailInfoBean.setCustomerName(result.getString("customerName"));
                mailInfoBean.setTheme(result.getString("theme"));
                mailInfoBean.setPriorityName(result.getString("priorityName"));
                mailInfoBean.setWorkTypeName(result.getString("workTypeName"));
                mailInfoBean.setCompletionName(result.getString("completionName"));
                mailInfoBean.setStartTime(result.getString("startTime"));
                mailInfoBean.setEndTime(result.getString("endTime"));
                mailInfoBean.setWorkDetail(result.getString("workDetail"));
                mailInfoBean.setDescriptions(result.getString("descriptions"));
                mailInfoBean.setEmail(result.getString("email"));
                mailInfoBeans.add(mailInfoBean);
            }
        } finally {
            if (result != null) {
                result.close();
            }
            if (psGet != null) {
                psGet.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        LOG.debug("method getMail end!");
        return mailInfoBeans;
    }

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
    @Override
    public void setIsMailed(List<String> workIDs) throws SQLException, ClassNotFoundException {
        LOG.debug("method setIsMailed start!");
        String sql = MailConstant.SETISMAILED + MailConstant.LEFT_BRACKET;
        for (int i = 1; i <= workIDs.size(); i++) {
            if (i < workIDs.size()) {
                sql = sql + "?,";
            } else {
                sql = sql + "?" + MailConstant.RIGHT_BRACKET;
            }
        }
        Connection conn = null;
        PreparedStatement psUpdate = null;
        try {
            conn = DBConnection.getConnection();
            psUpdate = conn.prepareStatement(sql);
            for (int i = 0; i < workIDs.size(); i++) {
                psUpdate.setString(i + 1, workIDs.get(i));
            }
            psUpdate.executeUpdate();
        } finally {
            if (psUpdate != null) {
                psUpdate.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        LOG.debug("method setIsMailed end!");
    }
}
