package com.ustcsoft.gs.crm.webui.system.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.system.bean.DepartmentInfoSearchBean;
import com.ustcsoft.gs.crm.webui.system.dto.DepartmentDto;
import com.ustcsoft.gs.crm.webui.system.service.DepartmentInfoService;

/**
 * 
 * @author maxue
 * 
 */
public class DepartmentInfoAction extends CRMAction {

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(DepartmentInfoAction.class);
    private DepartmentInfoSearchBean departmentInfoSearchBean = null;
    private DepartmentInfoService departmentInfoService = null;
    private DepartmentDto departmentDto = null;
    private int groupID = 0;
    private int departmentID = 0;
    private int loginDepartmentID = 0;

    /**
     * @return DeaprtmentInfoSearchBean
     */
    public DepartmentInfoSearchBean getDepartmentInfoSearchBean() {
        return departmentInfoSearchBean;
    }

    /**
     * @param departmentInfoSearchBean
     *            the departmentInfoSearchBean to set
     */
    public void setDepartmentInfoSearchBean(DepartmentInfoSearchBean departmentInfoSearchBean) {
        this.departmentInfoSearchBean = departmentInfoSearchBean;
    }

    /**
     * @return departmentInfoService
     */
    public DepartmentInfoService getDepartmentInfoService() {
        return departmentInfoService;
    }

    /**
     * @param departmentInfoService
     *            the departmentInfoService to set
     */
    public void setDepartmentInfoService(DepartmentInfoService departmentInfoService) {
        this.departmentInfoService = departmentInfoService;
    }

    /**
     * get all department or query department
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String getAllDepartment() throws CRMDBException {
        log.debug("method getAllDepartment start!");
        departmentInfoSearchBean = (DepartmentInfoSearchBean) CRMUtils.jsonToBean(super.jsonString,
                DepartmentInfoSearchBean.class);
        map = departmentInfoService.queryOrGetAllDepartment(departmentID, super.searchFlag,
                departmentInfoSearchBean, super.page, super.limit);
        log.debug("method getAllDepartment end!");
        return SUCCESS;
    }

    /**
     * add or update department
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String updateDepartmentInfo() throws CRMDBException {
        log.debug("method updateDepartmentInfo start!");
        departmentDto = (DepartmentDto) CRMUtils.jsonToBean(super.jsonString, DepartmentDto.class);
        departmentInfoService.updateDepartment(departmentDto);
        log.debug("method updateDepartmentInfo end!");
        return SUCCESS;
    }

    /**
     * delete department
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String deleteDepartmentInfo() throws CRMDBException {
        log.debug("method deleteDepartmentInfo start.");
        map = departmentInfoService.deleteDepartment(super.jsonString);
        log.debug("method deleteDepartmentInfo end.");
        return SUCCESS;
    }

    /**
     * get fatherDepartment
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String getFatherDepartment() throws CRMDBException {
        log.debug("method getFatherDepartment start.");
        map = departmentInfoService.getFatherDepartment(super.userID, groupID, loginDepartmentID,
                departmentID);
        log.debug("method getFatherDepartment end.");
        return SUCCESS;
    }

    /**
     * get DepartmentName by departmentID
     * 
     * @param departmeID
     * @return type of String
     * @throws CRMDBException
     */
    public String getDepartmentName() throws CRMDBException {
        map.put("departmentName", departmentInfoService.getDepartmentName(departmentID));
        return SUCCESS;
    }

    /**
     * get departmentManager
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public String getDepartmentManager() throws CRMDBException {
        log.debug("method getDepartmentManager start.");
        map = departmentInfoService.getDepartmentManager(departmentID);
        log.debug("method getDepartmentManager end.");
        return SUCCESS;
    }

    /**
     * validate of updateDepartmentAction
     * 
     * @throws CRMDBException
     */
    public void validateUpdateDepartmentInfo() throws CRMDBException {
        log.debug("method validateUpdateDepartmentInfo start!");
        departmentDto = (DepartmentDto) CRMUtils.jsonToBean(super.jsonString, DepartmentDto.class);
        if (StringUtils.isBlank(departmentDto.getDepartmentName())) {
            addFieldError("departmentName", "部门名不能为空！");
        } else if (departmentDto.getDepartmentName().length() > 20) {
            addFieldError("departmentName", "部门名最大长度为20！");
        }
        if ("".equals(departmentDto.getFatherDepartmentID())) {
            addFieldError("fatherDepartmentID", "上级部门不能为空！");
        } else if (departmentDto.getFatherDepartmentID() < 1) {
            addFieldError("fatherDepartmentID", "上级部门不存在！");
        }

        if (!departmentDto.getDepartmentPhone().isEmpty()) {
            if (departmentDto.getDepartmentPhone().length() > 30) {
                addFieldError("departmentPhone", "办公室电话最大长度为30！");
            } else if (!departmentDto.getDepartmentPhone()
                    .matches(CRMConstant.DEPARTMENT_PHONE_REG)) {
                addFieldError("departmentPhone", "办公室电话格式为(010-)4444444！");
            }
        }
        if (!departmentDto.getDepartmentDescription().isEmpty()) {
            if (departmentDto.getDepartmentDescription().length() > 1024) {
                addFieldError("departmentDescription", "备注长度不能超过1024个字符");
            }
        }
        if (departmentInfoService.judgeDepartmentName(departmentDto)) {
            addFieldError("departmentName", "部门已经存在！");
        }

        if (getFieldErrors().size() != 0) {
            map.putAll(getFieldErrors());
            map.put(CRMConstant.VALIDATE, false);
        }
        log.debug("method validateUpdateDepartmentInfo end!");
    }

    /**
     * @return departmentID
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

    /**
     * 
     * @return loginDepartmentID
     */
    public int getLoginDepartmentID() {
        return loginDepartmentID;
    }

    /**
     * 
     * @param loginDepartmentID
     *            the loginDepartmentId to set
     */
    public void setLoginDepartmentID(int loginDepartmentID) {
        this.loginDepartmentID = loginDepartmentID;
    }

    /**
     * 
     * @return groupID
     */
    public int getGroupID() {
        return groupID;
    }

    /**
     * 
     * @param groupID
     *            the groupID to set
     */
    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

}
