package com.ustcsoft.gs.crm.webui.system.dao;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.system.bean.DepartmentInfoSearchBean;
import com.ustcsoft.gs.crm.webui.system.dto.DepartmentDto;

public interface DepartmentInfoDao {

    /**
     * query department
     * 
     * @param searchFlag
     * @param searchBean
     * @param currPage
     * @param pageSize
     * @return map
     */
    public Map<String, Object> queryDepartment(int departmentID, int searchFlag,
            DepartmentInfoSearchBean searchBean, int currPage, int pageSize);

    /**
     * update or add department
     * 
     * @param departmentDto
     */
    public void updateDepartment(DepartmentDto departmentDto);

    /**
     * delete department
     * 
     * @param departmentIDs
     */
    public void deleteDepartment(String departmentIDs);

    /**
     * check the department whether to delete
     * 
     * @param departmentIDs
     * @return boolean
     */
    public boolean checkDelete(String departmentIDs);

    /**
     * judge departmentName is existed
     * 
     * @param departmentName
     * @return boolean
     */
    public boolean judgeDepartmentName(String departmentName);

    /**
     * find departmentName By groupID
     * 
     * @param departmentID
     * @return departmentName
     */
    public String findDepartmentNameByID(int departmentID);

    /**
     * get fatherDepartment
     * 
     * @param userID
     * @param groupID
     * @param loginDepartmentID
     * @param departmentID
     * @return map
     */
    public Map<String, Object> getFatherDepartment(int userID, int groupID, int loginDepartmentID,
            int departmentID);

    /**
     * get departmentManager
     * 
     * @param departmentID
     * @return map
     */
    public Map<String, Object> getDepartmentManager(int departmentID);

    /**
     * get depth from DB
     * 
     * @param departmentDto
     * @return depth
     */
    public int getDepthByID(DepartmentDto departmentDto);

    /**
     * get departmentName by departmentID
     * 
     * @param departmentID
     * @return type of String
     */
    public String getDepartmentName(int departmentID);
}
