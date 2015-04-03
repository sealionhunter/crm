package com.ustcsoft.gs.crm.webui.index.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.index.bean.WorkSearchBean;
import com.ustcsoft.gs.crm.webui.index.dao.WorkDao;
import com.ustcsoft.gs.crm.webui.index.dto.WorkDto;
import com.ustcsoft.gs.crm.webui.index.service.WorkService;
import com.ustcsoft.gs.crm.webui.mail.bean.MailInfoBean;
import com.ustcsoft.gs.crm.webui.mail.bean.MailSenderInfoBean;
import com.ustcsoft.gs.crm.webui.mail.dao.db.MailProperties;
import com.ustcsoft.gs.crm.webui.mail.service.MailService;
import com.ustcsoft.gs.crm.webui.mail.service.impl.MailServiceImpl;

/**
 * @author yinweili
 * 
 */
public class WorkServiceImpl implements WorkService {

    private static final Log LOG = LogFactory.getLog(WorkServiceImpl.class);

    /** define workDao */
    private WorkDao workDao = null;

    /**
     * @return the workDao
     */
    public WorkDao getWorkDao() {
        return workDao;
    }

    /**
     * @param workDao
     *            the workDao to set
     */
    public void setWorkDao(WorkDao workDao) {
        this.workDao = workDao;
    }

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
    @Override
    public Map<String, Object> searchOrGetAllWork(int searchFlag, WorkSearchBean workSearchBean,
            int currPage, int pageSize) throws CRMDBException {
        LOG.debug("method searchOrGetAllWork start!");
        Map<String, Object> map = null;
        try {
            map = workDao.searchOrGetAllWork(searchFlag, workSearchBean, currPage, pageSize);
        } catch (DataAccessException e) {
            LOG.debug("DataAccessException occurs in method searchOrGetAllWork!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method searchOrGetAllWork end!");
        return map;
    }

    /**
     * the function is add or update the information
     * 
     * @param workDto
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int updateWork(WorkDto workDto) throws CRMDBException {
        LOG.debug("method updateWork start!");
        int flag = 0;
        try {
            if (workDto.getTeamFlag() != 0) {
                int[] assignees = workDto.getAssignees();
                String assignee = "";
                for (int assignee2 : assignees) {
                    assignee = assignee + assignee2 + ",";
                }
                workDto.setAssignee(assignee.substring(0, assignee.length() - 1));
                if (workDto.getWorkID() > 0) {
                    flag = workDao.editTeamWork(workDto);
                } else {
                    flag = workDao.addTeamWork(workDto);
                }
                if (!workDto.getIsMailInformed()) {
                    flag = 0;
                }
            } else {
                workDao.updateWork(workDto);
            }
        } catch (DataAccessException e) {
            LOG.debug("DataAccessException occurs in method updateWork!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method updateWork end!");
        return flag;
    }

    /**
     * the function is change the isAbolished of workInfo
     * 
     * @param workIDs
     *            store the IDs to delete
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteWork(String workIDs) throws CRMDBException {
        LOG.debug("method deleteWork start!");
        try {
            workIDs = "(" + workIDs + ")";
            workDao.deleteWork(workIDs);
        } catch (DataAccessException e) {
            LOG.debug("DataAccessException occurs in method deleteWork!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method deleteWork end!");
    }

    /**
     * Get important task or inform task by search conditions
     * 
     * @param userID
     *            userID
     * @param searchFlag
     *            search condition
     * @param date
     *            search date
     * @param limit
     *            the size of one page
     * @return map used for return the query records
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public final Map<String, Object> getTask(final int page, final int userID,
            final int searchFlag, final String date, final int limit) throws CRMDBException {
        LOG.debug("method getTask start!");
        Map<String, Object> map = null;
        try {
            map = workDao.getTask(page, userID, searchFlag, date, limit);
        } catch (DataAccessException e) {
            LOG.debug("DataAccessException occurs in method getTask!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getTask end!");
        return map;
    }

    @Override
    public Map<String, Object> getContactTrackInfo(int userID, String date) throws CRMDBException {
        LOG.debug("method getContactTrackInfo start!");
        Map<String, Object> map = null;
        try {
            map = workDao.getContactTrackInfo(userID, date);
        } catch (DataAccessException e) {
            LOG.debug("DataAccessException occurs in method getContactTrackInfo!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getContactTrackInfo end!");
        return map;
    }

    /**
     * Set isReaded true
     * 
     * @param workID
     *            need change work id
     */
    @Override
    public void readMessage(String workID) {
        LOG.debug("method readMessage start!");
        workDao.readMessage(workID);
        LOG.debug("method readMessage end!");
    }

    /**
     * Set isMailInformed false
     * 
     * @param teamFlag
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public void sendMail(String teamFlag) throws CRMDBException {
        MailService mailService = new MailServiceImpl();
        int tFlag = 0;
        try {
            tFlag = Integer.parseInt(teamFlag);
        } catch (NumberFormatException e) {
            LOG.debug("DataAccessException occurs in method sendMail!", e);
            throw new CRMDBException(e);
        }

        List<MailInfoBean> mailInfoBeans = mailService.getMailInfo(tFlag);
        prepareSendMail(mailInfoBeans);
    }

    /**
     * Prepare to send mail
     * 
     * @param mailInfoBeans
     */
    @Override
    public void prepareSendMail(List<MailInfoBean> mailInfoBeans) {
        LOG.debug("method prepareSendMail start!");
        MailSenderInfoBean mailSenderInfoBean = new MailSenderInfoBean();
        mailSenderInfoBean.setMailServerHost(MailProperties.getMailServiceHost());

        // Set the port of the mail service
        mailSenderInfoBean.setMailServerPort(MailProperties.getMailServerPort());

        // Set the address of sender
        mailSenderInfoBean.setFromAddress(MailProperties.getFromAddress());

        // Set password authentication protocol
        mailSenderInfoBean.setValidate(false);

        MailService mailService = new MailServiceImpl();
        List<String> workIDList = new ArrayList<String>();

        // Get information to send mail
        for (int i = 0; i < mailInfoBeans.size(); i++) {
            mailSenderInfoBean.setToAddress(mailInfoBeans.get(i).getEmail());
            mailSenderInfoBean.setSubject(mailInfoBeans.get(i).getTheme());
            mailSenderInfoBean.setContent(mailService.setMailContent(mailInfoBeans.get(i)));
            Boolean mailSuccess = mailService.sendTextMail(mailSenderInfoBean);
            if (mailSuccess) {
                workIDList.add(mailInfoBeans.get(i).getWorkID() + "");
            }
        }
        if (workIDList.size() != 0) {
            try {
                mailService.setIsMailed(workIDList);
            } catch (CRMDBException e) {
                LOG.debug("DataAccessException occurs in method prepareSendMail!", e);
            }
        }
        LOG.debug("method prepareSendMail end!");
    }

    @Override
    public boolean userIsProjectTeamLeader(int userID) throws CRMDBException {
        return workDao.userIsProjectTeamLeader(userID);
    }

    /**
     * get task which was readed
     * 
     * @param page
     * @param userID
     * @param searchFlag
     * @param date
     * @param limit
     * @return type of Map<String, Object>
     */
    @Override
    public Map<String, Object> getReadedTask(int page, int userID, int searchFlag, String date,
            int limit) {
        Map<String, Object> map = null;
        map = getWorkDao().getReadedTask(page, userID, searchFlag, date, limit);
        return map;
    }

    @Override
    public Map<String, Object> getCustomerUpdatedStatus(int userID, String date) throws CRMDBException {
        LOG.debug("method getCustomerUpdatedStatus start!");
        Map<String, Object> map = null;
        try {
            map = workDao.getCustomerUpdatedStatus(userID, date);
        } catch (DataAccessException e) {
            LOG.debug("DataAccessException occurs in method getContactTrackInfo!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getCustomerUpdatedStatus end!");
        return map;
    }
}
