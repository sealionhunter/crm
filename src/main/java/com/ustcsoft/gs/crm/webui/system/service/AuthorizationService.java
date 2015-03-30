/*
 * interface name: AuthorizationService
 * 
 * Version information: 1.0
 * 
 * Date:2012.11.09
 *  
 */
package com.ustcsoft.gs.crm.webui.system.service;

import java.util.List;
import java.util.Map;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.exception.CRMException;
import com.ustcsoft.gs.crm.webui.system.dto.TreeDto;

/**
 * authorization service interface.
 * 
 * @author xujueyin and xujialong
 * 
 */
public interface AuthorizationService {

    /**
     * get tree nodes for display.
     * 
     * @param userMode
     *            int remarked the user is personal user or manager
     * @param treeFlag
     *            int remarked load which tree
     * @param id
     *            int role or user id
     * @throws CRMDBException
     *             in case of Hibernate Exception
     * @return List<TreeDto>
     */
    public List<TreeDto> getTree(int userMode, String treeFlag, int id) throws CRMDBException,
            CRMException;

    /**
     * save user or role authorization.
     * 
     * @param userMode
     *            int remarked the user is personal user or manager
     * @param jsonString
     *            String authorization IDs
     * @param idAuthorized
     *            int userID or groupID
     * @param userID
     *            int the ID of the user currently logged on
     * @throws CRMException
     *             in case of NumberFormatException
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void saveAuthorization(int userMode, String jsonString, int idAuthorized, int userID)
            throws CRMDBException, CRMException;

    /**
     * get user or role down of the registered user.
     * 
     * @param userMode
     *            int remarked the user is personal user or manager
     * @param id
     *            int userID
     * @return Map<String, Object> users or roles
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> getMembers(int userMode, int id) throws CRMDBException;

    /**
     * get user operation authorization.
     * 
     * @param groupID
     *            int group ID
     * @param rootID
     *            int left tree node's ID of index page
     * @return Map<String, Object> operation authorization IDs
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> getOperationAuthorization(int groupID, int rootID)
            throws CRMDBException;

    /**
     * get member authorization for display when save or delete authorization.
     * 
     * @param userMode
     *            int remarked the user is personal user or manager
     * @param id
     *            int user ID
     * @return Map<String, Object> users's or role's authorization
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> getMemberAuthorization(int userMode, int id) throws CRMDBException;
}