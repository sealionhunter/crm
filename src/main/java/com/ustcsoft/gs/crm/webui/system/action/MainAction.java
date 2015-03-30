package com.ustcsoft.gs.crm.webui.system.action;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;
import com.ustcsoft.gs.crm.webui.system.service.MainService;

/**
 * 
 * @author zhouzhou
 * 
 */
public class MainAction extends CRMAction {

    /** used for getting log */
    private static final Log LOG = LogFactory.getLog(LoginAction.class);

    private static final long serialVersionUID = 1L;

    /** used for getting oldPassword */
    private String oldPassword = null;

    /** used for getting newPassword */
    private String newPassword = null;

    /** used for getting session */
    private Map<String, Object> session = ActionContext.getContext().getSession();

    /** used for getting class MainService */
    private MainService mainService = null;

    /**
     * init the mian
     */
    @Override
    public String execute() {
        return SUCCESS;
    }

    /**
     * the function is to change password
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String passwordChange() throws CRMDBException {
        LOG.debug("method passwordChange in MainAction start");
        int userID = (Integer) session.get("userID");
        // 检测原始密码是否正确]
        UserInfoDto userInfo = mainService.findByUserID(userID);
        // 若原始密码错误，直接返回页面
        if (!oldPassword.equals(userInfo.getPassword())) {
            return SUCCESS;
        }
        // 更新新密码
        mainService.changePasswordByUserID(userID, newPassword);
        // 返回成功标志
        map.put("msg", "changesuccess");
        LOG.debug("method passwordChange in MainAction start");
        return SUCCESS;
    }

    /**
     * check PasswordChange information
     */
    public void validatePasswordChange() {
        if (oldPassword == null) {
            addFieldError("oldPassword", this.getText("password.invalid"));
        } else if (!oldPassword.matches("^[a-zA-Z0-9_]{6,20}$")) {
            addFieldError("oldPassword", this.getText("passwordMatch.invalid"));
        }

        if (newPassword == null) {
            addFieldError("newPassword", this.getText("password.invalid"));
        } else if (!newPassword.matches("^[a-zA-Z0-9_]{6,20}$")) {
            addFieldError("newPassword", this.getText("passwordMatch.invalid"));
        }
        showFieldError();
    }

    /**
     * @return the oldPassword
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * @param oldPassword
     *            the oldPassword to set
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     * @return the newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword
     *            the newPassword to set
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * @param mainService
     *            the mainService to set
     */
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    /**
     * @return the session
     */
    public Map<String, Object> getSession() {
        return session;
    }

    /**
     * @param session
     *            the session to set
     */
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

}
