package com.ustcsoft.gs.crm.webui.system.service;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.system.bean.DepartmentInfoSearchBean;
import com.ustcsoft.gs.crm.webui.system.dto.DepartmentDto;

public interface DepartmentInfoService {

    /**
     * get all department or query department
     * 
     * @param searchFlag
     * @param searchBean
     * @param currPage
     * @param pageSize
     * @return map
     * @throws CRMDBException
     */
    public Map<String, Object> queryOrGetAllDepartment(int departmentID, int searchFlag,
            DepartmentInfoSearchBean searchBean, int currPage, int pageSize) throws CRMDBException;

    /**
     * update or add departmentInfo
     * 
     * @param departmentDto
     * @throws CRMDBException
     */
    public void updateDepartment(DepartmentDto departmentDto) throws CRMDBException;

    /**
     * delete department
     * 
     * @param departmentIDs
     * @return type of Map<String,Object>
     * @throws CRMDBException
     */
    public Map<String, Object> deleteDepartment(String departmentIDs) throws CRMDBException;

    /**
     * get fatherDepartment
     * 
     * @param userID
     * @param groupID
     * @param loginDepartmentID
     * @param departmentID
     * @return map
     * @throws CRMDBException
     */
    public Map<String, Object> getFatherDepartment(int userID, int groupID, int loginDepartmentID,
            int departmentID) throws CRMDBException;

    /**
     * get departmentManager
     * 
     * @param departmentID
     * @return map
     * @throws CRMDBException
     */
    public Map<String, Object> getDepartmentManager(int departmentID) throws CRMDBException;

    /**
     * judge if departmentName existed
     * 
     * @param departmentDto
     * @return type of boolean
     */
    public boolean judgeDepartmentName(DepartmentDto departmentDto) throws CRMDBException;

    /**
     * get departmentName by departmentID
     * 
     * @param DepartmentID
     * @return type of String
     * @throws CRMDBException
     */
    public String getDepartmentName(int DepartmentID) throws CRMDBException;
}
