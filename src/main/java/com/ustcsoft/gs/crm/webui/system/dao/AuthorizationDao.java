/*
 * interface name: AuthorizationDao
 * 
 * Version information: 1.0
 * 
 * Date:2012.11.09
 *  
 */
package com.ustcsoft.gs.crm.webui.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.system.dto.TreeDto;

/**
 * authorization dao interface.
 * 
 * @author xujueyin and xujialong
 * 
 */
public interface AuthorizationDao {

    /**
     * get role tree nodes for display.
     * 
     * @param treeFlag
     *            int remarked load which tree
     * @param groupID
     *            int role ID
     * @return List<TreeDto>
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public List<TreeDto> getGroupTree(String treeFlag, int groupID) throws DataAccessException;

    /**
     * save menu tree.
     * 
     * @param jsonString
     *            String menu tree IDs
     * @param groupID
     *            int role ID
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * @throws NumberFormatException
     *             in case of format number error
     */
    public void saveMenuTree(String jsonString, int groupID) throws DataAccessException,
            NumberFormatException;

    /**
     * get system configuration manager nodes for display.
     * 
     * @param treeFlag
     *            int remarked load which tree
     * @param id
     *            int configuration manager ID
     * @return type of List<TreeDto>
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public List<TreeDto> getMenuTree(String treeFlag, int id) throws DataAccessException;

    /**
     * save access and operation authorization of user or role.
     * 
     * @param jsonString
     *            String authorization IDs
     * @param groupID
     *            int role ID
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * @throws NumberFormatException
     *             in case of format number error
     */
    public void saveGroupAuthorization(String jsonString, int groupID) throws DataAccessException,
            NumberFormatException;

    /**
     * get users.
     * 
     * @param userID
     *            int user ID
     * @return Map<String, Object> the users
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> getUser(int userID) throws DataAccessException;

    /**
     * get roles.
     * 
     * @param groupID
     *            int group ID
     * @return Map<String, Object>
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> getGroupManager(int groupID) throws DataAccessException;

    /**
     * get user operation authorization.
     * 
     * @param groupID
     *            int group ID
     * @param rootID
     *            int left tree node's ID of index page
     * @return Map<String, Object> operation authorization IDs
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> getOperationAuthorization(int groupID, int rootID)
            throws DataAccessException;

    /**
     * /** get role authorization for display when save or delete authorization.
     * 
     * @param groupID
     *            int role ID
     * @return Map<String, Object> role authorization
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> getGroupAuthorization(int groupID) throws DataAccessException;
}