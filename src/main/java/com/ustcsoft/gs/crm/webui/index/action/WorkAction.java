package com.ustcsoft.gs.crm.webui.index.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.index.bean.WorkSearchBean;
import com.ustcsoft.gs.crm.webui.index.constant.IndexConstant;
import com.ustcsoft.gs.crm.webui.index.dto.WorkDto;
import com.ustcsoft.gs.crm.webui.index.service.WorkService;

/**
 * @author yinweili
 * 
 */
public class WorkAction extends CRMAction {

    private static final long serialVersionUID = 1L;

    /** used for recording log */
    private static final Log LOG = LogFactory.getLog(WorkAction.class);

    /** used for update information */
    private WorkDto workDto = null;

    /** define workService */
    private WorkService workService = null;

    /** define workService */
    private String date = null;

    /**
     * @return the workService
     */
    public WorkService getWorkService() {
        return workService;
    }

    /**
     * @param workService
     *            the workService to set
     */
    public void setWorkService(WorkService workService) {
        this.workService = workService;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * search or get all Work records
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public String execute() throws CRMDBException {
        LOG.debug("method execute start!");
        WorkSearchBean workSearchBean = (WorkSearchBean) CRMUtils.jsonToBean(jsonString,
                WorkSearchBean.class);
        if (userID != 0) {
            workSearchBean.setUserID(userID);
        }
        if (date != null) {
            workSearchBean.setDate(date);
        }
        map = workService.searchOrGetAllWork(super.searchFlag, workSearchBean, super.page,
                super.limit);
        LOG.debug("method execute end!");
        return SUCCESS;
    }

    /**
     * Get important task or inform task
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String getTask() throws CRMDBException {
        LOG.debug("method getTask start!");
        map = workService.getTask(page, userID, searchFlag, date, limit);
        LOG.debug("method getTask end!");
        return SUCCESS;
    }

    public String getContactTrackInfo() throws CRMDBException {
        LOG.debug("method getContactTrackInfo start!");
        map = workService.getContactTrackInfo(userID, date);
        LOG.debug("method getContactTrackInfo end!");
        return SUCCESS;
    }

    public String getCustomerUpdatedStatus() throws CRMDBException {
        LOG.debug("method getCustomerUpdatedStatus start!");
        map = workService.getCustomerUpdatedStatus(userID, this.page, this.limit);
        LOG.debug("method getCustomerUpdatedStatus end!");
        return SUCCESS;
    }

    public String getReadedTask() throws CRMDBException {
        LOG.debug("method getReadedTask start!");
        map = workService.getReadedTask(page, userID, searchFlag, date, limit);
        LOG.debug("method getReadedTask end!");
        return SUCCESS;
    }

    /**
     * the method is to change the isReaded of workInfo from false to true * @return
     * 
     * @return SUCCESS
     */
    public String readMessage() {
        workService.readMessage(jsonString);
        map.put("aaa", "bbb");
        return SUCCESS;
    }

    /**
     * validate addOrUpdate
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void validateUpdateWork() throws CRMDBException {
        workDto = (WorkDto) CRMUtils.jsonToBean(jsonString, WorkDto.class);
        if (StringUtils.isBlank(workDto.getTheme())) {
            addFieldError(IndexConstant.WORK_THEME, this.getText(IndexConstant.WORK_THEME_INVALID));
        } else if (workDto.getTheme().length() > 50) {
            addFieldError(IndexConstant.WORK_THEME,
                    this.getText(IndexConstant.WORK_THEME_LENGTH_INVALID));
        }
        if (StringUtils.isBlank(workDto.getCompletion())) {
            addFieldError(IndexConstant.WORK_COMPLETION,
                    this.getText(IndexConstant.WORK_COMPLETION_INVALID));
        }
        if (StringUtils.isBlank(workDto.getPriority())) {
            addFieldError(IndexConstant.WORK_PRIORITY,
                    this.getText(IndexConstant.WORK_PRIORITY_INVALID));
        }
        if (StringUtils.isBlank(workDto.getWorkType())) {
            addFieldError(IndexConstant.WORK_WORKTYPE,
                    this.getText(IndexConstant.WORK_WORKTYPE_INVALID));
        }
        if (StringUtils.isBlank(workDto.getStartTime())) {
            addFieldError(IndexConstant.WORK_STARTTIME,
                    this.getText(IndexConstant.WORK_STARTTIME_INVALID));
        } else if (!workDto.getStartTime().matches(CRMConstant.DATE_TIME_REG)) {
            addFieldError(IndexConstant.WORK_STARTTIME,
                    this.getText(IndexConstant.WORK_STARTTIME_MATCH_INVALID));
        }
        if (workDto.getEndTime() != null
                && !workDto.getEndTime().matches(CRMConstant.DATE_TIME_REG)) {
            addFieldError(IndexConstant.WORK_ENDTIME,
                    this.getText(IndexConstant.WORK_ENDTIME_MATCH_INVALID));
        }
        if (!StringUtils.isBlank(workDto.getWorkDetail())
                && workDto.getWorkDetail().length() >= 2048) {
            addFieldError(IndexConstant.WORK_WORKDETAIL,
                    this.getText(IndexConstant.WORK_WORKDETAIL_LENGTH_INVALID));
        }
        if (!StringUtils.isBlank(workDto.getDescriptions())
                && workDto.getDescriptions().length() >= 1024) {
            addFieldError(IndexConstant.WORK_DESCRIPTIONS,
                    this.getText(IndexConstant.WORK_DESCRIPTIONS_LENGTH_INVALID));
        }
        if (workDto.getTeamFlag() == -1 && workDto.getAssignees().length == 0) {
            addFieldError(IndexConstant.WORK_ASSIGNEES,
                    this.getText(IndexConstant.WORK_ASSIGNEES_INVALID));
        }
        showFieldError();
    }

    /**
     * the method to add or edit work record for DB
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String updateWork() throws CRMDBException {
        LOG.debug("method updateWork start!");
        int flag = workService.updateWork(workDto);
        map.put(IndexConstant.FLAG, flag);
        LOG.debug("method updateWork end!");
        return SUCCESS;
    }

    /**
     * Set isMailInformed false
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String setIsMailed() throws CRMDBException {
        workService.sendMail(jsonString);
        return SUCCESS;
    }

    /**
     * the function is to change the isAbolished of workInfo from false to true
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String deleteWork() throws CRMDBException {
        LOG.debug("method deleteWork start!");
        workService.deleteWork(jsonString);
        LOG.debug("method deleteWork end!");
        return SUCCESS;
    }

    public String userIsProjectTeamLeader() throws CRMDBException {
        LOG.debug("method userIsProjectTeamLeader start!");
        Boolean flagBoolean = workService.userIsProjectTeamLeader(userID);
        map.put(IndexConstant.FLAG, flagBoolean);
        LOG.debug("method userIsProjectTeamLeader end!");
        return SUCCESS;
    };
}
