package com.ustcsoft.gs.crm.webui.system.action;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.system.bean.UserInfoSearchBean;
import com.ustcsoft.gs.crm.webui.system.constant.SystemConstant;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;
import com.ustcsoft.gs.crm.webui.system.service.UserInfoService;

public class UserInfoAction extends CRMAction {
    private static final Log log = LogFactory.getLog(UserInfoAction.class);
    private static final long serialVersionUID = 1L;
    private UserInfoService userInfoService = null;
    private UserInfoSearchBean searchBean = null;
    private UserInfoDto userInfoDto = null;
    private int departmentID = 0;
    private int groupID;

    /**
     * 
     * @param groupID
     *            the groupID to set
     */
    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    /**
     * 
     * @return userInfoDto
     */
    public UserInfoDto getUserInfoDto() {
        return userInfoDto;
    }

    /**
     * 
     * @param userInfoDto
     *            the userInfoDto to set
     */
    public void setUserInfoDto(UserInfoDto userInfoDto) {
        this.userInfoDto = userInfoDto;
    }

    /**
     * 
     * @return userInfoService
     */
    public UserInfoService getUserInfoService() {
        return userInfoService;
    }

    /**
     * 
     * @param userInfoService
     *            the userInfoService to set
     */
    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    /**
     * get all user
     * 
     * @return type of String
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String getAllUserInfo() throws CRMDBException {
        log.debug("method getAllUserInfo start!");
        searchBean = (UserInfoSearchBean) CRMUtils.jsonToBean(super.jsonString,
                UserInfoSearchBean.class);
        searchBean.getChildDepartmentID().add(departmentID);
        map = userInfoService.searchOrGetAllUserInfo(super.searchFlag, searchBean, super.page,
                super.limit);
        log.debug("method getAllUserInfo end!");
        return SUCCESS;
    }

    /**
     * get groupID
     * 
     * @return type of String "successs"
     */
    public String getGroupID() {
        log.debug("method getGroupID start!");
        map = userInfoService.getGroupID(groupID);
        log.debug("method getGroupID end!");
        return SUCCESS;
    }

    /**
     * Get user's department
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    public String getUserDepartment() throws CRMDBException {
        LOG.debug("method getUserDepartment start.");
        map = userInfoService.getUserDepartment(userID, groupID, departmentID);
        LOG.debug("method getUserDepartment end.");
        return SUCCESS;
    }

    /**
     * update UserInfo
     * 
     * @return "success"
     * @throws CRMDBException
     */
    public String updateUserInfo() throws CRMDBException {
        log.debug("method updateUserInfo start!");
        JSONObject json = JSONObject.fromObject(jsonString);
        UserInfoDto userInfoDto = (UserInfoDto) JSONObject.toBean(json, UserInfoDto.class);
        userInfoService.updateUser(userInfoDto);
        log.debug("method updateUserInfo end!");
        return SUCCESS;
    }

    /**
     * validate method of UpdateUserInfo
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void validateUpdateUserInfo() throws CRMDBException {
        log.debug("method validateUpdateUserInfo start!");
        JSONObject json = JSONObject.fromObject(jsonString);
        UserInfoDto userInfoDto = (UserInfoDto) JSONObject.toBean(json, UserInfoDto.class);
        if (StringUtils.isBlank(userInfoDto.getUserName())) {
            addFieldError("userName", "用户名不能为空！");
        } else if (userInfoService.judgeUserName(userInfoDto)) {
            addFieldError("userName", "用户名已经存在！");
        } else if (userInfoDto.getUserName().length() > 20) {
            addFieldError("userName", "用户名最大长度为20！");
        } else if (!userInfoDto.getUserName().matches(SystemConstant.USERNAME_VALIDATE)) {
            addFieldError("userName", "用户名必须是1-20位的数字、字母或者下划线！");
        }
        if (userInfoDto.getGroupID() == 0) {
            addFieldError("groupID", "用户角色不能为空！");
        }
        if (StringUtils.isBlank(userInfoDto.getJobID())) {
            addFieldError("jobID", "用户工号不能为空！");
        } else if (userInfoDto.getJobID().length() > 20) {
            addFieldError("jobID", "用户工号最大长度为20！");
        } else if (userInfoService.judgeJobID(userInfoDto)) {
            addFieldError("jobID", "用户工号已经存在！");
        } else if (!userInfoDto.getJobID().matches(SystemConstant.JOBID_VALIDATE)) {
            addFieldError("jobID", "用户工号必须是1-20位的数字！");
        }
        if (StringUtils.isBlank(userInfoDto.getRealName())) {
            addFieldError("realName", "用户姓名不能为空！");
        } else if (userInfoDto.getRealName().length() > 20) {
            addFieldError("realName", "用户姓名最大长度为20！");
        }
        if (StringUtils.isBlank(userInfoDto.getCompany())) {
            addFieldError("company", "公司名不能为空！");
        } else if (userInfoDto.getCompany().length() > 50) {
            addFieldError("company", "公司名最大长度为50！");
        }
        if (!userInfoDto.getJob().isEmpty()) {
            if (userInfoDto.getJob().length() > 30) {
                addFieldError("job", "工作最大长度为30！");
            }
        }
        if (userInfoDto.getJobTitle() != null) {
            if (userInfoDto.getJobTitle().length() > 12) {
                addFieldError("jobTitle", "工作职称最大长度为12！");
            }
        }
        if (StringUtils.isBlank(userInfoDto.getEmail())) {
            addFieldError("email", "邮箱不能为空！");
        } else if (userInfoDto.getEmail().length() > 50) {
            addFieldError("email", "邮箱最大长度为50！");
        } else if (!userInfoDto.getEmail().matches(CRMConstant.EMAIL_REG)) {
            addFieldError("email", "邮箱格式为user@example.com！");
        }
        if (!userInfoDto.getMobile().isEmpty()) {
            if (userInfoDto.getMobile().length() > 20) {
                addFieldError("mobile", "手机号码最大长度为20！");
            } else if (!userInfoDto.getMobile().matches(CRMConstant.MOBILE_REG)) {
                addFieldError("mobile", "手机号码格式为13333333333！");
            }
        }
        if (!userInfoDto.getOfficePhone().isEmpty()) {
            if (userInfoDto.getOfficePhone().length() > 30) {
                addFieldError("officePhone", "办公室电话最大长度为30！");
            } else if (!userInfoDto.getOfficePhone().matches(CRMConstant.PHONE_REG)) {
                addFieldError("officePhone", "办公室电话格式为(010-)3333333！");
            }
        }
        if (userInfoDto.getEducation() != null) {
            if (userInfoDto.getEducation().length() > 12) {
                addFieldError("education", "学历最大长度为12！");
            }
        }
        if (!userInfoDto.getDescriptions().isEmpty()) {
            if (userInfoDto.getDescriptions().length() > 1024) {
                addFieldError("descriptions", "备注最大长度为1024！");
            }
        }
        if (getFieldErrors().size() != 0) {
            map.putAll(getFieldErrors());
            map.put(CRMConstant.VALIDATE, false);
        }
        log.debug("method validateUpdateUserInfo end!");
    }

    /**
     * delete UserInfo
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String deleteUserInfo() throws CRMDBException {
        log.debug("method deleteUserInfo start!");
        map = userInfoService.deleteUser(jsonString);
        log.debug("method deleteUserInfo end!");
        return SUCCESS;
    }

    public String getDepartmentNameByID(int departmentID) {

        map.put("departmentName", getUserInfoService().getDepartmentNameByID(departmentID));
        return SUCCESS;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }
}
