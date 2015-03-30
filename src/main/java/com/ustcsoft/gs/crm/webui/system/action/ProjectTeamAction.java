/*
 * Class name: ProjectTeamAction
 * 
 * Version information: 1.0
 * 
 * Date:2013.10.22
 *  
 */
package com.ustcsoft.gs.crm.webui.system.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.exception.CRMException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.system.bean.ProjectTeamSearchBean;
import com.ustcsoft.gs.crm.webui.system.bean.UserInfoSearchBean;
import com.ustcsoft.gs.crm.webui.system.dto.ProjectTeamDto;
import com.ustcsoft.gs.crm.webui.system.service.ProjectTeamService;

/**
 * projectTeam action extends CRMAction
 * 
 * @author wangzhanxu
 * 
 */
public class ProjectTeamAction extends CRMAction {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    /** get LOG */
    private static final Log LOG = LogFactory.getLog(ProjectTeamAction.class);
    /** define projectTeamService */
    private ProjectTeamService projectTeamService = null;
    /** added members or canAdd members to query */
    private int queryMembersMode;
    /** projectTeam id */
    private int projectTeamID;
    /** received IDs */
    private String receivedIDs = null;
    /** search value */
    private String searchValue = null;

    /**
     * get projectTeam members
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of CRMDBException
     * @throws CRMException
     *             in case of CRMException
     */
    public String getMembers() throws CRMDBException, CRMException {
        LOG.debug("getMembers method start!");
        UserInfoSearchBean searchBean = (UserInfoSearchBean) CRMUtils.jsonToBean(jsonString,
                UserInfoSearchBean.class);
        map = projectTeamService.getMembers(queryMembersMode, searchBean, page, limit);
        LOG.debug("getMembers method end!");
        return SUCCESS;
    }

    /**
     * remove members IDs according to projectTeamID
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of CRMDBException
     * @throws CRMException
     */
    public String removeMembers() throws CRMDBException, CRMException {
        LOG.debug("removeMembers method start!");
        map = projectTeamService.removeMembers(receivedIDs, projectTeamID);
        LOG.debug("removeMembers method end!");
        return SUCCESS;
    }

    /**
     * add members IDs according to projectTeamID
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of CRMDBException
     * @throws CRMException
     */
    public String addMembers() throws CRMDBException, CRMException {
        LOG.debug("addMembers method start!");
        projectTeamService.addMembers(receivedIDs, projectTeamID);
        LOG.debug("addMembers method end!");
        return SUCCESS;
    }

    /**
     * get projectTeam according to search condition
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of CRMDBException
     */
    public String getProjectTeam() throws CRMDBException {
        LOG.debug("getProjectTeam method start!");
        ProjectTeamSearchBean searchBean = (ProjectTeamSearchBean) CRMUtils.jsonToBean(
                super.jsonString, ProjectTeamSearchBean.class);
        map = projectTeamService.getProjectTeam(searchBean, page, limit);
        LOG.debug("getProjectTeam method end!");
        return SUCCESS;
    }

    /**
     * get projectTeam leader selecting users
     * 
     * @return SUCCESS
     * @throws CRMDBException
     */
    public String getTeamLeaderSelecting() throws CRMDBException {
        LOG.debug("getTeamLeaderSelecting method start!");
        UserInfoSearchBean searchBean = (UserInfoSearchBean) CRMUtils.jsonToBean(super.jsonString,
                UserInfoSearchBean.class);
        map = projectTeamService.getTeamLeaderSelecting(searchBean);
        LOG.debug("getTeamLeaderSelecting method end!");
        return SUCCESS;
    }

    /**
     * get projectTeam status
     * 
     * @return SUCCESS
     * @throws CRMDBException
     */
    public String getProjectTeamStatus() throws CRMDBException {
        LOG.debug("getProjectTeamStatus method start!");
        map = projectTeamService.getProjectTeamStatus();
        LOG.debug("getProjectTeamStatus method end!");
        return SUCCESS;
    }

    /**
     * insert or update projectTeam after validate method execute
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of CRMDBException
     */
    public String saveOrUpdateProjectTeam() throws CRMDBException {
        LOG.debug("saveOrUpdateProjectTeam method start!");
        // convert json string to projectTeamDto
        ProjectTeamDto projectTeamDto = (ProjectTeamDto) CRMUtils.jsonToBean(jsonString,
                ProjectTeamDto.class);
        projectTeamService.saveOrUpdateProjectTeam(projectTeamDto);
        LOG.debug("saveOrUpdateProjectTeam method end!");
        return SUCCESS;
    }

    /**
     * saveOrUpdateProjectTeam's validate method
     * 
     * @throws CRMDBException
     *             in case of CRMDBException
     */
    public void validateSaveOrUpdateProjectTeam() throws CRMDBException {
        LOG.debug("validateSaveOrUpdateProjectTeam method start!");
        ProjectTeamDto projectTeamDto = (ProjectTeamDto) CRMUtils.jsonToBean(jsonString,
                ProjectTeamDto.class);
        // if projectTeamID is 0, it will be 'insert' operation
        if (projectTeamDto.getProjectTeamID() == 0) {
            // judge the projectTeamName whether exist
            if (projectTeamService.judgeProjectTeamExistByName(projectTeamDto.getProjectTeamName())) {
                addFieldError("projectTeamName", "团队名称已经存在！");
            }
        } else {
            List<Integer> projectTeamIDs = projectTeamService
                    .findProjectTeamIDByName(projectTeamDto.getProjectTeamName());
            if (projectTeamIDs.size() == 1) {
                if (projectTeamIDs.get(0) != projectTeamDto.getProjectTeamID()) {
                    addFieldError("projectTeamName", "团队名称已经存在！");
                }
            }
        }
        if (getFieldErrors().size() != 0) {
            map.putAll(getFieldErrors());
            map.put(CRMConstant.VALIDATE, false);
        }
        LOG.debug("validateSaveOrUpdateProjectTeam method end!");
    }

    /**
     * delete projectTeam acccording to projectTeamIDs string
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of CRMDBException
     */
    public String deleteProjectTeam() throws CRMDBException {
        LOG.debug("deleteProjectTeam method start!");
        projectTeamService.deleteProjectTeam(receivedIDs);
        LOG.debug("deleteProjectTeam method end!");
        return SUCCESS;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public ProjectTeamService getProjectTeamService() {
        return projectTeamService;
    }

    public void setProjectTeamService(ProjectTeamService projectTeamService) {
        this.projectTeamService = projectTeamService;
    }

    public String getReceivedIDs() {
        return receivedIDs;
    }

    public void setReceivedIDs(String receivedIDs) {
        this.receivedIDs = receivedIDs;
    }

    public int getProjectTeamID() {
        return projectTeamID;
    }

    public void setProjectTeamID(int projectTeamID) {
        this.projectTeamID = projectTeamID;
    }

    public int getQueryMembersMode() {
        return queryMembersMode;
    }

    public void setQueryMembersMode(int queryMembersMode) {
        this.queryMembersMode = queryMembersMode;
    }
}
