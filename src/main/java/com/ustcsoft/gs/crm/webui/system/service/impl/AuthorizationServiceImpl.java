/*
 * Class name: AuthorizationServiceImpl
 * 
 * Version information: 1.0
 * 
 * Date:2012.11.09
 *  
 */
package com.ustcsoft.gs.crm.webui.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.exception.CRMException;
import com.ustcsoft.gs.crm.webui.system.constant.UserMode;
import com.ustcsoft.gs.crm.webui.system.dao.AuthorizationDao;
import com.ustcsoft.gs.crm.webui.system.dto.TreeDto;
import com.ustcsoft.gs.crm.webui.system.service.AuthorizationService;

/**
 * the implement of authorization service.
 * 
 * @author xujueyin and xujialong
 * 
 */
public class AuthorizationServiceImpl implements AuthorizationService {

    /** get Log */
    private static final Log LOG = LogFactory.getLog(AuthorizationServiceImpl.class);

    /** used for getting AuthorizationDao */
    private AuthorizationDao authorizationDao = null;

    /**
     * @return the authorzationDao
     */
    public AuthorizationDao getAuthorizationDao() {
        return authorizationDao;
    }

    /**
     * @param authorizationDao
     *            the authorzationDao to set
     */
    public void setAuthorizationDao(AuthorizationDao authorizationDao) {
        this.authorizationDao = authorizationDao;
    }

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
     * @throws CRMException
     */
    @Override
    public List<TreeDto> getTree(int userMode, String treeFlag, int id) throws CRMDBException,
            CRMException {
        LOG.debug("method getTree start!");
        List<TreeDto> treeList = null;
        try {
            if (userMode == UserMode.GROUP.getI()) {
                treeList = authorizationDao.getGroupTree(treeFlag, id);
            } else if (userMode == UserMode.USER.getI()) {
                // treeList = authorizationDao.getUserTree(treeFlag, id);
            } else if (userMode == UserMode.SYSTEM.getI() && id == 0) {
                treeList = authorizationDao.getMenuTree(treeFlag, id);
            } else {
                LOG.error("Argument value is not correct!");
                throw new CRMException("Argument value is not correct!");
            }
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getTree!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getTree end!");
        return treeList;
    }

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
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void saveAuthorization(int userMode, String jsonString, int idAuthorized, int userID)
            throws CRMDBException, CRMException {
        LOG.debug("method saveAuthorization start!");
        try {
            if (userMode == 0) {
                authorizationDao.saveGroupAuthorization(jsonString, idAuthorized);
            } else if (userMode == 2) {
                authorizationDao.saveMenuTree(jsonString, idAuthorized);
            }
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method saveAuthorization!", e);
            throw new CRMDBException(e);
        } catch (NumberFormatException e) {
            LOG.error("NumberFormatException occurs in method saveAuthorization!", e);
            throw new CRMException(e);
        }
        LOG.debug("method saveAuthorization end!");
    }

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
    @Override
    public Map<String, Object> getMembers(int userMode, int id) throws CRMDBException {
        LOG.debug("method getMembers start!");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (userMode == 0) {
                map = authorizationDao.getGroupManager(id);
            } else {
                map = authorizationDao.getUser(id);
            }
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getMembers!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getMembers end!");
        return map;
    }

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
    @Override
    public Map<String, Object> getOperationAuthorization(int groupID, int rootID)
            throws CRMDBException {
        LOG.debug("method OperationAuthorization start!");
        Map<String, Object> map = null;
        try {
            map = authorizationDao.getOperationAuthorization(groupID, rootID);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getOperationAuthorization!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method OperationAuthorization end!");
        return map;
    }

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
    @Override
    public Map<String, Object> getMemberAuthorization(int userMode, int id) throws CRMDBException {
        LOG.debug("method getMemberAuthorization start!");
        Map<String, Object> map = null;
        try {
            if (userMode == 0) {
                map = authorizationDao.getGroupAuthorization(id);
            } else {
                // map = authorizationDao.getUserAuthorization(id);
            }
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getMemberAuthorization!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getMemberAuthorization end!");
        return map;
    }
}