package com.ustcsoft.gs.crm.webui.system.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.system.dto.GroupManagerDto;
import com.ustcsoft.gs.crm.webui.system.service.GroupInfoService;

public class GroupInfoAction extends CRMAction {
    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(GroupInfoAction.class);
    private GroupInfoService groupInfoService = null;
    private GroupManagerDto groupManagerDto = null;
    private int groupID = 0;
    private int otherGroupID = 0;
    private String receivedIDs = null;
    private int departmentID = 0;

    /**
     * @return ortherGroupID
     */
    public int getOtherGroupID() {
        return otherGroupID;
    }

    /**
     * @param otherGroupID
     *            the otherGroupID to set
     */
    public void setOtherGroupID(int otherGroupID) {
        this.otherGroupID = otherGroupID;
    }

    /**
     * @return receivedIDs
     */
    public String getReceivedIDs() {
        return receivedIDs;
    }

    /**
     * @param receivedIDs
     *            the receivedIDs to set
     */
    public void setReceivedIDs(String receivedIDs) {
        this.receivedIDs = receivedIDs;
    }

    /**
     * @return groupID
     */
    public int getGroupID() {
        return groupID;
    }

    /**
     * @param groupID
     *            the groupID to set
     */
    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    /**
     * @return groupInfoService
     */
    public GroupInfoService getGroupInfoService() {
        return groupInfoService;
    }

    /**
     * @param groupInfoService
     *            the groupInfoService to set
     */
    public void setGroupInfoService(GroupInfoService groupInfoService) {
        this.groupInfoService = groupInfoService;
    }

    /**
     * get Group
     * 
     * @return type of String "successs"
     */
    public String getGroup() {
        log.debug("method getGroup start!");
        map = groupInfoService.getGroup(groupID);
        log.debug("method getGroup end!");
        return SUCCESS;
    }

    /**
     * get user by groupID
     * 
     * @return type of String
     * @throws CRMDBException
     */
    public String getGroupMembers() throws CRMDBException {
        log.debug("method getGroupMembers start!");
        map = groupInfoService.getGroupMembers(departmentID, groupID, super.page, super.limit);
        log.debug("method getGroupMembers end!");
        return SUCCESS;
    }

    /**
     * add or update group
     * 
     * @return type of String
     * @throws CRMDBException
     */
    public String updateGroupInfo() throws CRMDBException {
        log.debug("method updateGroupInfo start!");
        groupManagerDto = (GroupManagerDto) CRMUtils.jsonToBean(super.jsonString,
                GroupManagerDto.class);
        map = groupInfoService.updateGroup(groupManagerDto);
        log.debug("method updateGroupInfo end!");
        return SUCCESS;
    }

    /**
     * validate of updateGroupAction
     * 
     * @throws CRMDBException
     */
    public void validateUpdateGroupInfo() throws CRMDBException {
        log.debug("method validateUpdateGroupInfo start!");
        groupManagerDto = (GroupManagerDto) CRMUtils.jsonToBean(super.jsonString,
                GroupManagerDto.class);
        if (StringUtils.isBlank(groupManagerDto.getGroupName())) {
            addFieldError("groupName", "角色名不能为空！");
        } else if (groupManagerDto.getGroupName().length() > 15) {
            addFieldError("groupName", "角色名最大长度为15！");
        } else if (groupInfoService.judgeGroupName(groupManagerDto)) {
            addFieldError("groupName", "角色名已经存在！");
        }

        if (getFieldErrors().size() != 0) {
            map.putAll(getFieldErrors());
            map.put(CRMConstant.VALIDATE, false);
        }
        log.debug("method validateUpdateGroupInfo end!");
    }

    /**
     * delete groupInfo
     * 
     * @return type of String
     * @throws CRMDBException
     */
    public String deleteGroupInfo() throws CRMDBException {
        log.debug("method deleteGroupInfo start!");
        groupInfoService.deleteGroup(groupID);
        log.debug("method deleteGroupInfo end!");
        return SUCCESS;
    }

    /**
     * remove members IDs according to groupID
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of CRMDBException
     */
    public String removeMembers() throws CRMDBException {
        log.debug("removeMembers method start!");
        groupInfoService.removeMembers(receivedIDs, groupID, otherGroupID);
        log.debug("removeMembers method end!");
        return SUCCESS;
    }

    /**
     * add members IDs according to groupID
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of CRMDBException
     */
    public String addMembers() throws CRMDBException {
        log.debug("addMembers method start!");
        groupInfoService.addMembers(receivedIDs, groupID);
        log.debug("addMembers method end!");
        return SUCCESS;
    }

    /**
     * @return the departmentID
     */
    public int getDepartmentID() {
        return departmentID;
    }

    /**
     * @param departmentID
     *            the departmentID to set
     */
    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }
}
