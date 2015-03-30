package com.ustcsoft.gs.crm.webui.system.dto;

/**
 * 
 * @author jiaxu
 * 
 */
public class OrganizationalStructureDto {

    private String departmentName;

    private int departmentID;

    private int fatherDepartmentID;

    private int depth;

    /**
     * 
     * @return departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * 
     * @param departmentName
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * 
     * @return departmentID
     */
    public int getDepartmentID() {
        return departmentID;
    }

    /**
     * 
     * @param departmentID
     */
    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    /**
     * 
     * @return fatherDepartmentID
     */
    public int getFatherDepartmentID() {
        return fatherDepartmentID;
    }

    /**
     * 
     * @param fatherDepartmentID
     */
    public void setFatherDepartmentID(int fatherDepartmentID) {
        this.fatherDepartmentID = fatherDepartmentID;
    }

    /**
     * 
     * @return depth
     */
    public int getDepth() {
        return depth;
    }

    /**
     * 
     * @param depth
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }
}
