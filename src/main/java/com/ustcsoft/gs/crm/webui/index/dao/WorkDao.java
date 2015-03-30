package com.ustcsoft.gs.crm.webui.index.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.index.bean.WorkSearchBean;
import com.ustcsoft.gs.crm.webui.index.dto.WorkDto;
import com.ustcsoft.gs.crm.webui.mail.bean.MailInfoBean;

/**
 * @author yinweili
 * 
 */
public interface WorkDao {

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
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> searchOrGetAllWork(int searchFlag, WorkSearchBean workSearchBean,
            final int currPage, final int pageSize) throws DataAccessException;

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
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    Map<String, Object> getTask(final int page, final int userID, final int searchFlag,
            final String date, final int limit) throws DataAccessException;

    /**
     * the function is add or update the information
     * 
     * @param workDto
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public void updateWork(WorkDto workDto) throws DataAccessException;

    /**
     * the function is change the isAbolished of workInfo
     * 
     * @param workIDs
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public void deleteWork(String workIDs) throws DataAccessException;

    /**
     * 
     * @param workID
     *            need change work id
     */
    public void readMessage(String workID);

    /**
     * 
     * @param workDto
     * @return type of Integer
     */
    public int addTeamWork(WorkDto workDto);

    /**
     * Get send information
     * 
     * @param teamFlag
     *            sender
     * @return mailInfoBeans
     */
    public List<MailInfoBean> getMailInfo(int teamFlag);

    /**
     * 
     * @param workDto
     * @return Type of Integer
     */
    public int editTeamWork(WorkDto workDto);

    public boolean userIsProjectTeamLeader(int userID) throws CRMDBException;

    public Map<String, Object> getReadedTask(int page, int userID, int searchFlag, String date,
            int limit);

    public Map<String, Object> getContactTrackInfo(int userID, String date);
}
