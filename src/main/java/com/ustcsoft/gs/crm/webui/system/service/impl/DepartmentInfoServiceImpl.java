package com.ustcsoft.gs.crm.webui.system.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.system.bean.DepartmentInfoSearchBean;
import com.ustcsoft.gs.crm.webui.system.dao.DepartmentInfoDao;
import com.ustcsoft.gs.crm.webui.system.dto.DepartmentDto;
import com.ustcsoft.gs.crm.webui.system.service.DepartmentInfoService;

public class DepartmentInfoServiceImpl implements DepartmentInfoService {
    private static final Log log = LogFactory.getLog(DepartmentInfoServiceImpl.class);
    private DepartmentInfoDao departmentInfoDao;

    /**
     * @param departmentInfoDao
     *            the departmentInfoDao to set
     */
    public void setDepartmentInfoDao(DepartmentInfoDao departmentInfoDao) {
        this.departmentInfoDao = departmentInfoDao;
    }

    /**
     * get all department or query department
     * 
     * @param searchFlag
     * @param searchBean
     * @param currPage
     * @param pageSize
     * @return map
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public Map<String, Object> queryOrGetAllDepartment(int departmentID, int searchFlag,
            DepartmentInfoSearchBean searchBean, int currPage, int pageSize) throws CRMDBException {
        log.debug("method queryOrGetAllDepartment start!");
        Map<String, Object> map = null;
        try {
            map = departmentInfoDao.queryDepartment(departmentID, searchFlag, searchBean, currPage,
                    pageSize);
        } catch (DataAccessException e) {
            log.error("DataAccessException occurs in method queryOrGetAllDepartment!", e);
            throw new CRMDBException(
                    "DataAccessException occurs in method queryOrGetAllDepartment!", e);
        }
        log.debug("method queryOrGetAllDepartment end!");
        return map;
    }

    /**
     * update or add department
     * 
     * @param departmentDto
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void updateDepartment(DepartmentDto departmentDto) throws CRMDBException {
        log.debug("method updateDepartment start!");
        try {
            departmentDto.setDepth(departmentInfoDao.getDepthByID(departmentDto));
            departmentInfoDao.updateDepartment(departmentDto);
        } catch (DataAccessException e) {
            log.error("DataAccessException occurs in method updateDepartment!", e);
            throw new CRMDBException("DataAccessException occurs in method updateDepartment!", e);
        }
        log.debug("method updateDepartment end!");

    }

    /**
     * delete department
     * 
     * @param departmentIDs
     * @return map
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Map<String, Object> deleteDepartment(String departmentIDs) throws CRMDBException {
        log.debug("method deleteDepartment start!");
        departmentIDs = CRMUtils.getStringForDelete(departmentIDs);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (departmentInfoDao.checkDelete(departmentIDs)) {
                departmentInfoDao.deleteDepartment(departmentIDs);
                map.put("flag", true);
            } else {
                map.put("flag", false);
            }
        } catch (DataAccessException e) {
            log.error("DataAccessException occurs in method deleteDepartment.", e);
            throw new CRMDBException("DataAccessException occurs in method deleteDepartment.", e);
        }
        log.debug("method deleteDepartment end!");
        return map;
    }

    /**
     * judge if DepartmentName existed
     * 
     * @param departmentDto
     * @return boolean
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public boolean judgeDepartmentName(DepartmentDto departmentDto) throws CRMDBException {
        log.debug("method judgeDepartmentName start!");
        boolean nameIsExisted = true;
        String departmentName = departmentDto.getDepartmentName();
        int departmentID = departmentDto.getDepartmentID();
        try {
            if (departmentID == 0) {
                nameIsExisted = departmentInfoDao.judgeDepartmentName(departmentName);
            } else {
                String oldName = departmentInfoDao.findDepartmentNameByID(departmentID);
                if (departmentName.equals(oldName)) {
                    nameIsExisted = false;
                } else {
                    nameIsExisted = departmentInfoDao.judgeDepartmentName(departmentName);
                }
            }
        } catch (DataAccessException e) {
            log.error("DataAccessException occurs in method judgeDepartmentName!", e);
            throw new CRMDBException("DataAccessException occurs in method judgeDepartmentName!", e);
        }
        log.debug("method judgeDepartmentName end!");
        return nameIsExisted;
    }

    /**
     * get fatherDepartment
     * 
     * @param userID
     * @param groupID
     * @param loginDepartmentID
     * @param departmentID
     * @return map
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public Map<String, Object> getFatherDepartment(int userID, int groupID, int loginDepartmentID,
            int departmentID) throws CRMDBException {
        log.debug("method getFatherDepartment start!");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = departmentInfoDao.getFatherDepartment(userID, groupID, loginDepartmentID,
                    departmentID);
        } catch (DataAccessException e) {
            log.error("DataAccessException occurs in method getFatherDepartment!", e);
            throw new CRMDBException("DataAccessException occurs in method getFatherDepartment!", e);
        }
        log.debug("method getFatherDepartment end!");
        return map;
    }

    /**
     * get departmentManager
     * 
     * @param departmentID
     * @return map
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public Map<String, Object> getDepartmentManager(int departmentID) throws CRMDBException {
        log.debug("method getDepartmentManager start!");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = departmentInfoDao.getDepartmentManager(departmentID);
        } catch (DataAccessException e) {
            log.error("DataAccessException occurs in method getDepartmentManager!", e);
            throw new CRMDBException("DataAccessException occurs in method getDepartmentManager!",
                    e);
        }
        log.debug("method getDepartmentManager end!");
        return map;
    }

    /**
     * get departmentName by departmentID
     * 
     * @param departmentID
     * @return type of String
     * @throws CRMDBException
     */
    @Override
    public String getDepartmentName(int departmentID) throws CRMDBException {
        try {
            return departmentInfoDao.getDepartmentName(departmentID);
        } catch (DataAccessException e) {
            log.error("DataAccessException occurs in method getDepartmentName!", e);
            throw new CRMDBException("DataAccessException occurs in method getDepartmentName!", e);
        }

    }

}
