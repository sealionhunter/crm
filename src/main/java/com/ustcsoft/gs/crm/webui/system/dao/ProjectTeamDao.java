/*
 * Interface name: ProjectTeamDao
 * 
 * Version information: 1.0
 * 
 * Date:2013.10.22
 *  
 */
package com.ustcsoft.gs.crm.webui.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.system.bean.ProjectTeamSearchBean;
import com.ustcsoft.gs.crm.webui.system.bean.UserInfoSearchBean;
import com.ustcsoft.gs.crm.webui.system.dto.ProjectTeamDto;

/**
 * ProjectTeamDao interface
 * 
 * @author wangzhanxu
 * 
 */
public interface ProjectTeamDao {
    /**
     * get projectTeam members
     * 
     * @param searchBean
     * @param currPage
     * @param pageSize
     * @return Map<String, Object>
     * @throws DataAccessException
     *             in case of DataAccessException
     */
    public Map<String, Object> getProjectTeamMembers(UserInfoSearchBean searchBean, int currPage,
            int pageSize) throws DataAccessException;

    /**
     * get other projectTeam or no projectTeam members
     * 
     * @param searchBean
     * @param currPage
     * @param pageSize
     * @return Map<String, Object>
     * @throws DataAccessException
     *             in case of DataAccessException
     */
    public Map<String, Object> getOtherMembers(UserInfoSearchBean searchBean, int currPage,
            int pageSize) throws DataAccessException;

    /**
     * remove projectTeam members
     * 
     * @param removeIDs
     * @param projectTeamID
     * @return Map<String, Object>
     * @throws DataAccessException
     * @throws NumberFormatException
     */
    public Map<String, Object> removeMembers(String removeIDs, int projectTeamID)
            throws DataAccessException, NumberFormatException;

    /**
     * add projectTeam members
     * 
     * @param addIDs
     * @param projectTeamID
     * @throws DataAccessException
     *             in case of DataAccessException
     * @throws NumberFormatException
     *             in case of NumberFormatException
     */
    public void addMembers(String addIDs, int projectTeamID) throws DataAccessException,
            NumberFormatException;

    /**
     * get projectTeam according to search condition
     * 
     * @param searchBean
     * @param currPage
     * @param limit
     * @return Map
     * @throws DataAccessException
     */
    public Map<String, Object> getProjectTeam(ProjectTeamSearchBean searchBean, int currPage,
            int limit) throws DataAccessException;

    /**
     * get projectTeam leader selecting users
     * 
     * @param searchBean
     * @return Map
     * @throws DataAccessException
     */
    public Map<String, Object> getTeamLeaderSelecting(UserInfoSearchBean searchBean)
            throws DataAccessException;

    /**
     * get projectTeam status
     * 
     * @return Map
     * @throws DataAccessException
     */
    public Map<String, Object> getProjectTeamStatus() throws DataAccessException;

    /**
     * delete projetTeam according to their IDs
     * 
     * @param receivedIDs
     * @throws DataAccessException
     */
    public void deleteProjectTeam(String receivedIDs) throws DataAccessException;

    /**
     * judge projectTeam whether exist
     * 
     * @param projectTeamName
     * @return boolean
     * @throws DataAccessException
     */
    public boolean judgeProjectTeamExistByName(String projectTeamName) throws DataAccessException;

    /**
     * find projectTeam ID by name
     * 
     * @param projectTeamName
     * @return List<Integer>
     * @throws DataAccessException
     */
    public List<Integer> findProjectTeamIDByName(String projectTeamName) throws DataAccessException;

    /**
     * insert or update projectTeam
     * 
     * @param projectTeamDto
     * @throws DataAccessException
     */
    public void saveOrUpdateProjectTeam(ProjectTeamDto projectTeamDto) throws DataAccessException;
}
