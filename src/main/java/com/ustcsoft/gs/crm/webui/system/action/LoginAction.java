package com.ustcsoft.gs.crm.webui.system.action;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;
import com.ustcsoft.gs.crm.webui.system.service.LoginService;

/**
 * 
 * @author zhouzhou
 * 
 */
public class LoginAction extends CRMAction {
    /** used for getting log */
    private static final Log LOG = LogFactory.getLog(LoginAction.class);

    private static final long serialVersionUID = 1L;

    /** used for getting logion information */
    private String userName = null;

    /** used for getting logion information */
    private String password = null;

    /** used for getting userInfo */
    private UserInfoDto userInfo = null;

    /** used for getting class LoginService */
    private LoginService loginService = null;

    /** used for getting session */
    private Map<String, Object> session = ActionContext.getContext().getSession();

    /**
     * the function is to login
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String login() throws CRMDBException {
        LOG.debug("method login start!");
        userInfo = loginService.findByUsernamePassword(userName, password);
        if (userInfo != null) {
            session.put("isLogin", 1);
            session.put("userID", userInfo.getUserID());
            session.put("groupID", userInfo.getGroupID());
            session.put("realName", userInfo.getRealName());
            session.put("departmentID", userInfo.getDepartmentID());
            session.put("projectTeamID", userInfo.getProjectTeamID());
            session.put("contactInterval", userInfo.getContactInterval());
        }
        map.put("userInfo", userInfo);
        LOG.debug("method login end!");
        return SUCCESS;
    }

    /**
     * check login information
     */
    public void validateLogin() {
        if (userName == null || userName.isEmpty()) {
            addFieldError("userName", this.getText("loginUserName.invalid"));
        }
        if (password == null) {
            addFieldError("password", this.getText("password.invalid"));
        }
        showFieldError();
    }

    /**
     * the function is to logout
     * 
     * @return SUCCESS
     */
    public String logout() {
        LOG.debug("method logout start!");
        session.put("isLogin", 0);
        session.put("userID", 0);
        session.put("groupID", 0);
        session.put("realName", 0);
        session.put("departmentID", 0);
        session.put("projectTeamID", 0);

        LOG.debug("method logout end!");
        return SUCCESS;
    }

    /**
     * @param loginService
     *            the loginService to set
     */
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * @return the userInfo
     */
    public UserInfoDto getUserInfo() {
        return userInfo;
    }

    /**
     * @param userInfo
     *            the userInfo to set
     */
    public void setUserInfo(UserInfoDto userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName.trim();
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
