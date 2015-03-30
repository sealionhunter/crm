/*
 * Interface name: ProjectTeamService
 * 
 * Version information: 1.0
 * 
 * Date:2013.10.22
 *  
 */
package com.ustcsoft.gs.crm.webui.system.service;

import java.util.List;
import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.exception.CRMException;
import com.ustcsoft.gs.crm.webui.system.bean.ProjectTeamSearchBean;
import com.ustcsoft.gs.crm.webui.system.bean.UserInfoSearchBean;
import com.ustcsoft.gs.crm.webui.system.dto.ProjectTeamDto;

/**
 * ProjectTeamService interface
 * 
 * @author wangzhanxu
 * 
 */
public interface ProjectTeamService {
    /**
     * get projectTeam members
     * 
     * @param queryMembersMode
     * @param searchBean
     * @param currPage
     * @param pageSize
     * @return Map
     * @throws CRMDBException
     * @throws CRMException
     */
    public Map<String, Object> getMembers(int queryMembersMode, UserInfoSearchBean searchBean,
            int currPage, int pageSize) throws CRMDBException, CRMException;

    /**
     * remove projectTeam members
     * 
     * @param removeIDs
     * @param projectTeamID
     * @return Map<String, Object>
     * @throws CRMDBException
     * @throws CRMDBException
     */
    public Map<String, Object> removeMembers(String removeIDs, int projectTeamID)
            throws CRMDBException, CRMException;

    /**
     * add projectTeam members
     * 
     * @param addIDs
     * @param projectTeamID
     * @throws CRMDBException
     *             in case of CRMDBException
     */
    public void addMembers(String addIDs, int projectTeamID) throws CRMDBException, CRMException;

    /**
     * delete projetTeam according to their IDs
     * 
     * @param receivedIDs
     * @throws CRMDBException
     * 
     */
    public void deleteProjectTeam(String receivedIDs) throws CRMDBException;

    /**
     * get projectTeam according to search condition
     * 
     * @param searchBean
     * @param currPage
     * @param limit
     * @return Map
     * @throws CRMDBException
     */
    public Map<String, Object> getProjectTeam(ProjectTeamSearchBean searchBean, int currPage,
            int limit) throws CRMDBException;

    /**
     * get projectTeam leader selecting users
     * 
     * @param searchBean
     * @return Map
     * @throws CRMDBException
     */
    public Map<String, Object> getTeamLeaderSelecting(UserInfoSearchBean searchBean)
            throws CRMDBException;

    /**
     * get projectTeam status
     * 
     * @return Map
     * @throws CRMDBException
     */
    public Map<String, Object> getProjectTeamStatus() throws CRMDBException;

    /**
     * judge projectTeam whether exist
     * 
     * @param projectTeamName
     * @return boolean
     * @throws CRMDBException
     */
    public boolean judgeProjectTeamExistByName(String projectTeamName) throws CRMDBException;

    /**
     * find projectTeam ID by name
     * 
     * @param projectTeamName
     * @return List<Integer>
     * @throws CRMDBException
     */
    public List<Integer> findProjectTeamIDByName(String projectTeamName) throws CRMDBException;

    /**
     * insert or update projectTeam
     * 
     * @param projectTeamDto
     * @throws CRMDBException
     */
    public void saveOrUpdateProjectTeam(ProjectTeamDto projectTeamDto) throws CRMDBException;
}
