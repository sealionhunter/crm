/**
 * 
 */
package com.ustcsoft.gs.crm.webui.index.service;

import java.util.List;
import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.index.bean.WorkSearchBean;
import com.ustcsoft.gs.crm.webui.index.dto.WorkDto;
import com.ustcsoft.gs.crm.webui.mail.bean.MailInfoBean;

/**
 * @author yinweili
 * 
 */
public interface WorkService {

    /**
     * list and search
     * 
     * @param searchFlag
     *            used for judging query type
     * @param workSearchBean
     *            used for recoding query condition
     * @param currPage
     *            used for recording the current page
     * @param pageSize
     *            used for recording the size of one page
     * @return map used for return the query records
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> searchOrGetAllWork(int searchFlag, WorkSearchBean workSearchBean,
            int currPage, int pageSize) throws CRMDBException;

    /**
     * the function is add or update the information
     * 
     * @param workDto
     * @return type of int
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public int updateWork(WorkDto workDto) throws CRMDBException;

    /**
     * the function is change the isAbolished of workInfo
     * 
     * @param workIDs
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void deleteWork(String workIDs) throws CRMDBException;

    /**
     * Get important task or inform task by search conditions
     * 
     * @param userID
     *            userID
     * @param searchFlag
     *            search condition
     * @param date
     *            search date
     * @return map used for return the query records
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    Map<String, Object> getTask(int page, int userID, int searchFlag, String date, int limit)
            throws CRMDBException;

    /**
     * 
     * @param workID
     *            need change work id
     */
    public void readMessage(String workID);

    /**
     * 
     * @param teamFlag
     * @throws CRMDBException
     */
    public void sendMail(String teamFlag) throws CRMDBException;

    /**
     * Prepare to send mail
     * 
     * @param mailInfoBeans
     */
    public void prepareSendMail(List<MailInfoBean> mailInfoBeans);

    public boolean userIsProjectTeamLeader(int userID) throws CRMDBException;

    public Map<String, Object> getReadedTask(int page, int userID, int searchFlag, String date,
            int limit);

    public Map<String, Object> getContactTrackInfo(int userID, String date) throws CRMDBException;

    public Map<String, Object> getCustomerUpdatedStatus(int userID, String date) throws CRMDBException;
}
