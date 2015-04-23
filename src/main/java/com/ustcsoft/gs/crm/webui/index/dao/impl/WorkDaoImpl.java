package com.ustcsoft.gs.crm.webui.index.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.common.util.PagingHibernateCallback;
import com.ustcsoft.gs.crm.webui.common.util.SuperHibernateCallback;
import com.ustcsoft.gs.crm.webui.customer.bean.ContactTrackListBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.index.bean.CustomerUpdatedStatusBean;
import com.ustcsoft.gs.crm.webui.index.bean.WorkBean;
import com.ustcsoft.gs.crm.webui.index.bean.WorkSearchBean;
import com.ustcsoft.gs.crm.webui.index.constant.IndexConstant;
import com.ustcsoft.gs.crm.webui.index.dao.WorkDao;
import com.ustcsoft.gs.crm.webui.index.dto.WorkDto;
import com.ustcsoft.gs.crm.webui.mail.bean.MailInfoBean;

/**
 * @author yinweili
 * 
 */
public class WorkDaoImpl implements WorkDao {

    private static final Log LOG = LogFactory.getLog(WorkDaoImpl.class);

    /** define hibernateTemplate */
    private HibernateTemplate hibernateTemplate;

    /**
     * @return the hibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    /**
     * @param hibernateTemplate
     *            the hibernateTemplate to set
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /**
     * simple search or super search or get all
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
    @Override
    public Map<String, Object> searchOrGetAllWork(int searchFlag,
            final WorkSearchBean workSearchBean, final int currPage, final int pageSize)
            throws DataAccessException {
        LOG.debug("method searchOrGetAllWork start!");
        Map<String, Object> map = null;
        if (searchFlag == 0) {
            LOG.debug("method searchOrGetAllWork start with searchFlag = 0");
            map = getWork(IndexConstant.WORK_GETALL_HQL, IndexConstant.WORK_GETALL_COUNT_HQL,
                    currPage, workSearchBean, pageSize);
            LOG.debug("method searchOrGetAllWork end with searchFlag = 0");
        } else if (searchFlag == 1) {
            LOG.debug("method searchOrGetAllWork start with searchFlag = 1");
            String searchValue = CRMUtils.trimSearch(workSearchBean.getSearchText());
            workSearchBean.setTheme(searchValue);
            workSearchBean.setCustomerName(searchValue);
            workSearchBean.setPriority(searchValue);
            workSearchBean.setCompletion(searchValue);
            workSearchBean.setStartTime(searchValue);
            workSearchBean.setEndTime(searchValue);
            workSearchBean.setWorkType(searchValue);
            map = getWork(IndexConstant.WORK_GETALL_HQL + IndexConstant.WORK_SIMPLE_HQL,
                    IndexConstant.WORK_GETALL_COUNT_HQL + IndexConstant.WORK_SIMPLE_HQL, currPage,
                    workSearchBean, pageSize);
            LOG.debug("method searchOrGetAllWork end with searchFlag = 1");
        } else if (searchFlag == 2) {
            LOG.debug("method searchOrGetAllWork start with searchFlag = 2");
            final String customerName = workSearchBean.getCustomerName();
            final String theme = workSearchBean.getTheme();
            final String jobStyle = workSearchBean.getJobStyle();
            final String priority = workSearchBean.getPriority();
            final String completion = workSearchBean.getCompletion();
            final String workType = workSearchBean.getWorkType();
            final String searchDateStart = workSearchBean.getSearchDateStart();
            final String searchDateEnd = workSearchBean.getSearchDateEnd();
            StringBuffer searchHqlList = new StringBuffer(IndexConstant.WORK_GETALL_HQL);
            StringBuffer searchHqlCount = new StringBuffer(IndexConstant.WORK_GETALL_COUNT_HQL);
            StringBuffer searchHqlWhere = new StringBuffer();
            if (StringUtils.isNotBlank(customerName)) {
                searchHqlWhere.append(IndexConstant.WORK_CUSTOMER_SEARCH);
                workSearchBean.setCustomerName(CRMUtils.trimSearch(customerName));
            }   
            if (StringUtils.isNotBlank(theme)) {
                searchHqlWhere.append(IndexConstant.WORK_THEME_SEARCH);
                workSearchBean.setTheme(CRMUtils.trimSearch(theme));
            }
            if (IndexConstant.ZERO_ONE.equals(jobStyle)) {
                searchHqlWhere.append(IndexConstant.WORK_JOBSTYLE_MYWORK_SEARCH);
            } else if (IndexConstant.ZERO_TWO.equals(jobStyle)) {
                searchHqlWhere.append(IndexConstant.WORK_JOBSTYLE_TEAMLEADER_SEARCH);
            } else if (IndexConstant.ZERO_THREE.equals(jobStyle)) {
                searchHqlWhere.append(IndexConstant.WORK_JOBSTYLE_TEAM_SEARCH);
            }
            if (!(priority == null || CRMConstant.SPACE.equals(priority) || CRMConstant.SEARCHALL_FLAG
                    .equals(priority))) {
                searchHqlWhere.append(IndexConstant.WORK_PRIORITY_SEARCH);
            }
            if (!(completion == null || CRMConstant.SPACE.equals(completion) || CRMConstant.SEARCHALL_FLAG
                    .equals(completion))) {
                searchHqlWhere.append(IndexConstant.WORK_COMPLETION_SEARCH);
            }
            if (!(workType == null || CRMConstant.SPACE.equals(workType) || CRMConstant.SEARCHALL_FLAG
                    .equals(workType))) {
                searchHqlWhere.append(IndexConstant.WORK_WORKTYPE_SEARCH);
            }
            if (!(searchDateStart == null || CRMConstant.SPACE.equals(searchDateStart))) {
                searchHqlWhere.append(IndexConstant.WORK_DATESTART_SEARCH);
            }
            if (!(searchDateEnd == null || CRMConstant.SPACE.equals(searchDateEnd))) {
                searchHqlWhere.append(IndexConstant.WORK_DATEEND_SEARCH);
            }
            searchHqlList.append(searchHqlWhere);
            searchHqlCount.append(searchHqlWhere);
            map = getWork(searchHqlList.toString(), searchHqlCount.toString(), currPage,
                    workSearchBean, pageSize);
            LOG.debug("method searchOrGetAllWork end with searchFlag = 2");
        } else if (searchFlag == 3) {
            LOG.debug("method searchOrGetAllWork start with searchFlag = 3");
            map = getWork(IndexConstant.WORK_GETALL_BY_DATE_HQL,
                    IndexConstant.WORK_GETALL_BY_DATE_COUNT_HQL, currPage, workSearchBean, pageSize);
            LOG.debug("method searchOrGetAllWork end with searchFlag = 3");
        }
        LOG.debug("method searchOrGetAllWork end!");
        return map;
    }

    /**
     * the function is get the work information
     * 
     * @param sql
     * @param countSql
     * @param currPage
     * @param workSearchBean
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getWork(final String sql, final String countSql, final int currPage,
            final WorkSearchBean workSearchBean, final int pageSize) throws DataAccessException {
        LOG.debug("method getWork start!");
        Map<String, Object> map = new HashMap<String, Object>();
        List<WorkBean> workBeans = hibernateTemplate.executeFind(new SuperHibernateCallback(sql,
                currPage, workSearchBean, pageSize));
        long total = (Long) hibernateTemplate.executeFind(
                new SuperHibernateCallback(countSql, 0, workSearchBean, pageSize)).get(0);
        map.put(CRMConstant.TOTAL, total);
        map.put(CRMConstant.ITEMS, workBeans);
        LOG.debug("method getWork end!");
        return map;
    }

    /**
     * the function is add or update the information
     * 
     * @param workDto
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public void updateWork(WorkDto workDto) throws DataAccessException {
        LOG.debug("method updateWork start!");
        hibernateTemplate.saveOrUpdate(workDto);
        LOG.debug("method updateWork end!");
    }

    /**
     * the function is add the team information
     * 
     * @param workDto
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public int addTeamWork(WorkDto workDto) throws DataAccessException {
        LOG.debug("method addTeamWork start!");
        hibernateTemplate.saveOrUpdate(workDto);
        int flag = workDto.getWorkID();
        saveOrUpdateTeamWork(workDto);
        LOG.debug("method addTeamWork end!");
        return flag;
    }

    /**
     * edit TeamWork
     * 
     * @param workDto
     */
    @Override
    public int editTeamWork(WorkDto workDto) {
        LOG.debug("method editTeamWork start!");
        hibernateTemplate.saveOrUpdate(workDto);
        int flag = workDto.getWorkID();
        hibernateTemplate.bulkUpdate(IndexConstant.EDIT_TEAMWORK, flag);
        saveOrUpdateTeamWork(workDto);
        LOG.debug("method editTeamWork end!");
        return flag;
    }

    /**
     * save or update TeamWork
     * 
     * @param workDto
     */
    public void saveOrUpdateTeamWork(WorkDto workDto) {
        LOG.debug("method saveOrUpdateTeamWork start!");
        for (int i : workDto.getAssignees()) {
            WorkDto workDtoNew = new WorkDto();
            workDtoNew.setTheme(workDto.getTheme());
            workDtoNew.setCustomerID(workDto.getCustomerID());
            workDtoNew.setPriority(workDto.getPriority());
            workDtoNew.setCompletion(workDto.getCompletion());
            workDtoNew.setWorkType(workDto.getWorkType());
            workDtoNew.setStartTime(workDto.getStartTime());
            workDtoNew.setEndTime(workDto.getEndTime());
            workDtoNew.setIsMailInformed(workDto.getIsMailInformed());
            workDtoNew.setWorkDetail(workDto.getWorkDetail());
            workDtoNew.setDescriptions(workDto.getDescriptions());
            workDtoNew.setTeamFlag(workDto.getWorkID());
            workDtoNew.setUserID(i);
            workDtoNew.setAssignee(null);
            hibernateTemplate.saveOrUpdate(workDtoNew);
        }
        LOG.debug("method saveOrUpdateTeamWork end!");
    }

    /**
     * the function is change the isAbolished of workInfo from false to true
     * 
     * @param workIDs
     *            store the IDs to delete
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public void deleteWork(String workIDs) throws DataAccessException {
        LOG.debug("method deleteWork start!");
        hibernateTemplate.bulkUpdate(IndexConstant.DELETE_TEAMWORK_ME + workIDs);
        hibernateTemplate.bulkUpdate(IndexConstant.DELETE_TEAMWORK_OTHER + workIDs);
        LOG.debug("method deleteWork end!");
    }

    /**
     * Get important task or inform task by search conditions
     * 
     * @param page
     *            currentPage
     * @param userID
     *            userID
     * @param searchFlag
     *            search condition
     * @param date
     *            search date
     * @param limit
     *            pageSize
     * @return map used for return the query records
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    @SuppressWarnings("unchecked")
    public final Map<String, Object> getTask(final int page, final int userID,
            final int searchFlag, final String date, final int limit) throws DataAccessException {
        Map<String, Object> map = new HashMap<String, Object>();
        List<WorkDto> workDtos = null;
        Object[] taskValues = { userID, date };
        // get my task and team task
        if (searchFlag == 0) {
            String importantTaskHql = IndexConstant.WORK_TASK_HQL_MAP + IndexConstant.WORK_IMPORTANT_TASK_HQL;
            String informTaskHql = IndexConstant.WORK_TASK_HQL_MAP + IndexConstant.WORK_INFORM_TASK_HQL;
            workDtos = new ArrayList<WorkDto>();

            List<WorkDto> list = hibernateTemplate.findByNamedParam(importantTaskHql,
                    IndexConstant.PARAMNAMES, taskValues);
            workDtos.addAll(list);

            list = hibernateTemplate.findByNamedParam(informTaskHql,
                    IndexConstant.PARAMNAMES, taskValues);
            workDtos.addAll(list);
        } else {
            String informMessageHql = IndexConstant.WORK_TASK_HQL_MAP
                    + IndexConstant.WORK_INFORM_MESSAGE_HQL;
            workDtos = hibernateTemplate.executeFind(new PagingHibernateCallback(informMessageHql,
                    page, CRMConstant.USER_ID, userID, limit));
        }
        long total = getMessageTotal(userID);
        map.put(CRMConstant.TOTAL, total);
        map.put(CRMConstant.ITEMS, workDtos);

        return map;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getContactTrackInfo(int userID, String date) throws DataAccessException {
        LOG.debug("method getContactTrackInfo start!");

        Map<String, Object> map = new HashMap<String, Object>();
//        Object[] taskValues = { userID, date };
//        String[] params = { "userID", "date" };
//        List<ContactTrackListBean> contactTrackList = hibernateTemplate.findByNamedParam(
//                CustomerConstant.GET_CONTACT_TO_NOTIFICATION_HQL, params, taskValues);
//        map.put(CRMConstant.ITEMS, contactTrackList);
//
//        LOG.debug("method getContactTrackInfo end!");
        return map;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getReadedTask(int page, int userID, int searchFlag, String date,
            int limit) {
        Map<String, Object> map = new HashMap<String, Object>();
        Object[] taskValues = { userID, date };
        List<WorkDto> workDtos = null;
        workDtos = hibernateTemplate.executeFind(new PagingHibernateCallback(
                IndexConstant.WORK_TASK_HQL_MAP + IndexConstant.WORK_INFORM_MESSAGE_HQL_READED,
                page, IndexConstant.PARAMNAMES, taskValues, limit));
        long total = getReadedMessageTotal(userID);
        map.put("total", total);
        map.put("items", workDtos);
        return map;
    }

    /**
     * 
     * @param userID
     *            userID
     * @param date
     *            today
     * @return (Long) list.get(0) total size
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public long getMessageTotal(int userID) throws DataAccessException {
        LOG.debug("method getMessageTotal start!");
        List<?> list = null;
        list = hibernateTemplate.findByNamedParam(IndexConstant.WORK_INFORM_MESSAGETOTAL_HQL,
                CRMConstant.USER_ID, userID);
        LOG.debug("method getMessageTotal end!");
        return (Long) list.get(0);
    }

    public long getReadedMessageTotal(int userID) {
        LOG.debug("method getReadedMessageTotal start!");
        LOG.debug("method getMessageTotal start!");
        List<?> list = null;
        list = hibernateTemplate.findByNamedParam(
                IndexConstant.WORK_INFORM_MESSAGETOTAL_HQL_READED, CRMConstant.USER_ID, userID);
        LOG.debug("method getMessageTotal end!");

        LOG.debug("method getReadedMessageTotal end!");
        return (Long) list.get(0);
    }

    /**
     * @param workID
     *            need change work id
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public void readMessage(String workID) throws DataAccessException {
        LOG.debug("method readMessage start!");
        hibernateTemplate.bulkUpdate(IndexConstant.READMESSAGE + workID);
        LOG.debug("method readMessage end!");
    }

    /**
     * Get send information
     * 
     * @param teamFlag
     *            sender
     * @return mailInfoBeans
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<MailInfoBean> getMailInfo(int teamFlag) throws DataAccessException {
        LOG.debug("method getMailInfo start!");
        List<MailInfoBean> mailInfoBeans = new ArrayList<MailInfoBean>();
        mailInfoBeans = hibernateTemplate.findByNamedParam(IndexConstant.GET_MAILINFO,
                IndexConstant.TEAMFLAG, teamFlag);
        LOG.debug("method getMailInfo end!");
        return mailInfoBeans;
    }

    /**
     * judge user is or isn't userIsProjectTeamLeader
     * 
     * @param userID
     * @return boolean
     */
    @Override
    public boolean userIsProjectTeamLeader(int userID) {
        LOG.debug("method userIsProjectTeamLeader start!");
        String hqlCount = "select count(*) from ProjectTeamDto as ptDto where ptDto.projectTeamLeaderID=:userID";
        long total = (Long) hibernateTemplate.findByNamedParam(hqlCount, "userID", userID).get(0);
        LOG.debug("method userIsProjectTeamLeader end!");
        return total != 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getCustomerUpdatedStatus(int userID, int page, int limit) {
        LOG.debug("method getCustomerUpdatedStatus start!");

        Map<String, Object> map = new HashMap<String, Object>();
        List<CustomerUpdatedStatusBean> list = new ArrayList<CustomerUpdatedStatusBean>();
        list = hibernateTemplate.executeFind(new PagingHibernateCallback(
                IndexConstant.GET_CUSTOMER_UPDATED_STATUS,
                page, CustomerConstant.USER_ID, userID, limit));
        List<?> result = hibernateTemplate.findByNamedParam(IndexConstant.GET_CUSTOMER_UPDATED_STATUS_COUNT,
                CustomerConstant.USER_ID, userID);
        map.put(CRMConstant.ITEMS, list);
        map.put(CRMConstant.TOTAL, result.get(0));

        LOG.debug("method getCustomerUpdatedStatus end!");
        return map;
    }
}
