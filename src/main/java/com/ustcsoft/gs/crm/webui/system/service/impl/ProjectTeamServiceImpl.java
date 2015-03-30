/*
 * Class name: ProjectTeamServiceImpl
 * 
 * Version information: 1.0
 * 
 * Date:2013.10.22
 *  
 */
package com.ustcsoft.gs.crm.webui.system.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.exception.CRMException;
import com.ustcsoft.gs.crm.webui.system.bean.ProjectTeamSearchBean;
import com.ustcsoft.gs.crm.webui.system.bean.UserInfoSearchBean;
import com.ustcsoft.gs.crm.webui.system.dao.ProjectTeamDao;
import com.ustcsoft.gs.crm.webui.system.dto.ProjectTeamDto;
import com.ustcsoft.gs.crm.webui.system.service.ProjectTeamService;

/**
 * ProjectTeamServiceImpl class implements ProjectTeamService
 * 
 * @author wangzhanxu
 * 
 */
public class ProjectTeamServiceImpl implements ProjectTeamService {
    /** get LOG */
    private static final Log LOG = LogFactory.getLog(ProjectTeamServiceImpl.class);
    /** define projectTeamDao */
    private ProjectTeamDao projectTeamDao = null;

    /**
     * set projectTeamDao
     * 
     * @param projectTeamDao
     */
    public void setProjectTeamDao(ProjectTeamDao projectTeamDao) {
        this.projectTeamDao = projectTeamDao;
    }

    /**
     * remove projectTeam members
     * 
     * @param removeIDs
     * @param projectTeamID
     * @return Map<String, Object>
     * @throws CRMDBException
     * @throws CRMException
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public Map<String, Object> removeMembers(String removeIDs, int projectTeamID)
            throws CRMDBException, CRMException {
        LOG.debug("removeMembers method start!");
        Map<String, Object> removeResult = null;
        try {
            removeResult = projectTeamDao.removeMembers(removeIDs, projectTeamID);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in removeMembers method!", e);
            throw new CRMDBException(
                    "DataAccessException occurs in removeMembers method in ProjectTeamServiceImpl!",
                    e);
        } catch (NumberFormatException e) {
            LOG.error("NumberFormatException occurs in removeMembers method!", e);
            throw new CRMException(
                    "NumberFormatException occurs in removeMembers method in ProjectTeamServiceImpl!",
                    e);
        }
        LOG.debug("removeMembers method end!");
        return removeResult;
    }

    /**
     * add projectTeam members
     * 
     * @param addIDs
     * @param projectTeamID
     * @throws CRMDBException
     *             in case of CRMDBException
     * @throws CRMException
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public void addMembers(String addIDs, int projectTeamID) throws CRMDBException, CRMException {
        LOG.debug("addMembers method start!");
        try {
            projectTeamDao.addMembers(addIDs, projectTeamID);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in addMembers method!", e);
            throw new CRMDBException(
                    "DataAccessException occurs in addMembers method in ProjectTeamServiceImpl!", e);
        } catch (NumberFormatException e) {
            LOG.error("NumberFormatException occurs in addMembers method!", e);
            throw new CRMException(
                    "NumberFormatException occurs in addMembers method in ProjectTeamServiceImpl!",
                    e);
        }
        LOG.debug("addMembers method end!");
    }

    /**
     * get projectTeam members
     * 
     * @param queryMembersMode
     * @param searchBean
     * @param currPage
     * @param pageSize
     * @return memberMap
     * @throws CRMDBException
     * @throws CRMException
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public Map<String, Object> getMembers(int queryMembersMode, UserInfoSearchBean searchBean,
            int currPage, int pageSize) throws CRMDBException, CRMException {
        LOG.debug("getMembers method start!");
        Map<String, Object> memberMap = null;
        try {
            // when queryMembersMode is 1, method will get projectTeam members
            if (queryMembersMode == 1) {
                memberMap = projectTeamDao.getProjectTeamMembers(searchBean, currPage, pageSize);
                // when queryMembersMode is 2, method will get other projectTeam
                // members
            } else if (queryMembersMode == 2) {
                memberMap = projectTeamDao.getOtherMembers(searchBean, currPage, pageSize);
                // other value will throw Exception
            } else {
                LOG.error("false argument value in getMembers method!");
                throw new CRMException(
                        "false argument value in getMembers method in ProjectTeamServiceImpl!");
            }
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in getMembers method!", e);
            throw new CRMDBException(
                    "DataAccessException occurs in getMembers method in ProjectTeamServiceImpl!", e);
        }
        LOG.debug("getMembers method end!");
        return memberMap;
    }

    /**
     * get projectTeam according to search condition
     * 
     * @param searchBean
     * @param currPage
     * @param limit
     * @return map
     * @throws CRMDBException
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public Map<String, Object> getProjectTeam(ProjectTeamSearchBean searchBean, int currPage,
            int limit) throws CRMDBException {
        LOG.debug("getProjectTeam method start!");
        Map<String, Object> map = null;
        try {
            map = projectTeamDao.getProjectTeam(searchBean, currPage, limit);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in getProjectTeamByProjectTeamName method!", e);
            throw new CRMDBException(
                    "DataAccessException occurs in getProjectTeamByProjectTeamName method!", e);
        }
        LOG.debug("getProjectTeam method end!");
        return map;
    }

    /**
     * save or update a projectTeam
     * 
     * @param projectTeamDto
     * 
     * @throws CRMDBException
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public void saveOrUpdateProjectTeam(ProjectTeamDto projectTeamDto) throws CRMDBException {
        LOG.debug("saveOrUpdateProjectTeam method start!");
        try {
            projectTeamDao.saveOrUpdateProjectTeam(projectTeamDto);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in saveOrUpdateProjectTeam method!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("saveOrUpdateProjectTeam method end!");
    }

    /**
     * delete projetTeam according to their IDs
     * 
     * @param receivedIDs
     * @throws CRMDBException
     * 
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public void deleteProjectTeam(String receivedIDs) throws CRMDBException {
        LOG.debug("deleteProjectTeam method start!");
        try {
            projectTeamDao.deleteProjectTeam(receivedIDs);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in deleteProjectTeam method!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("deleteProjectTeam method end!");
    }

    /**
     * get projectTeam leader selecting users
     * 
     * @param searchBean
     * @return map
     * @throws CRMDBException
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public Map<String, Object> getTeamLeaderSelecting(UserInfoSearchBean searchBean)
            throws CRMDBException {
        LOG.debug("getTeamLeaderSelecting method start!");
        Map<String, Object> map = null;
        try {
            map = projectTeamDao.getTeamLeaderSelecting(searchBean);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in getTeamLeaderSelecting method in ProjectTeamServiceImpl!");
            throw new CRMDBException(
                    "DataAccessException occurs in getTeamLeaderSelecting method in ProjectTeamServiceImpl!",
                    e);
        }
        LOG.debug("getTeamLeaderSelecting method end!");
        return map;
    }

    /**
     * judge projectTeam whether exist
     * 
     * @param projectTeamName
     * @return judgeResult
     * @throws CRMDBException
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public boolean judgeProjectTeamExistByName(String projectTeamName) throws CRMDBException {
        LOG.debug("judgeProjectTeamExistByName method start!");
        boolean judgeResult = true;
        try {
            judgeResult = projectTeamDao.judgeProjectTeamExistByName(projectTeamName);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in judgeProjectTeamExistByName in ProjectTeamServiceImpl");
            throw new CRMDBException(
                    "DataAccessException occurs in judgeProjectTeamExistByName in ProjectTeamServiceImpl",
                    e);
        }
        LOG.debug("judgeProjectTeamExistByName method end!");
        return judgeResult;
    }

    /**
     * get projectTeam status
     * 
     * @return map
     * @throws CRMDBException
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public Map<String, Object> getProjectTeamStatus() throws CRMDBException {
        LOG.debug("getProjectTeamStatus method start!");
        Map<String, Object> map = null;
        try {
            map = projectTeamDao.getProjectTeamStatus();
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in getProjectTeamStatus method!");
            throw new CRMDBException("DataAccessException occurs in getProjectTeamStatus method!",
                    e);
        }
        LOG.debug("getProjectTeamStatus method end!");
        return map;
    }

    /**
     * find projectTeam ID by name
     * 
     * @param projectTeamName
     * @return projectTeamIDs
     * @throws DataAccessException
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public List<Integer> findProjectTeamIDByName(String projectTeamName) throws CRMDBException {
        LOG.debug("findProjectTeamIDByName method start!");
        List<Integer> projectTeamIDs = null;
        try {
            projectTeamIDs = projectTeamDao.findProjectTeamIDByName(projectTeamName);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in findProjectTeamIDByName!");
            throw new CRMDBException("DataAccessException occurs in findProjectTeamIDByName!", e);
        }
        LOG.debug("findProjectTeamIDByName method end!");
        return projectTeamIDs;
    }
}
