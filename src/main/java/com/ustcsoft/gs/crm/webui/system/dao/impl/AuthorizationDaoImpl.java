/*
 * Class name: AuthorizationDaoImpl
 * 
 * Version information: 1.0
 * 
 * Date:2012.11.09
 *  
 */
package com.ustcsoft.gs.crm.webui.system.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.system.constant.SystemConstant;
import com.ustcsoft.gs.crm.webui.system.dao.AuthorizationDao;
import com.ustcsoft.gs.crm.webui.system.dto.ActionGroupDto;
import com.ustcsoft.gs.crm.webui.system.dto.TreeDto;

/**
 * authorization dao implement.
 * 
 * @author xujueyin and xujialong
 * 
 */
public class AuthorizationDaoImpl implements AuthorizationDao {

    /** get Log */
    private static final Log LOG = LogFactory.getLog(AuthorizationDaoImpl.class);

    /** used for getting hibernateTemplate */
    private HibernateTemplate hibernateTemplate;

    /**
     * @return hibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    /**
     * @param hibernateTemplate
     *            the hibernateTemplate to set
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

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
    @SuppressWarnings("unchecked")
    @Override
    public List<TreeDto> getGroupTree(String treeFlag, int groupID) throws DataAccessException {
        LOG.debug("method getGroupTree start!");
        List<TreeDto> treeList = hibernateTemplate.find(SystemConstant.GET_GROUP_TREE_HQL,
                new Object[] { treeFlag, groupID });
        LOG.debug("method getGroupTree end!");
        return DBDataToTree(treeList);
    }

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
    @SuppressWarnings("unchecked")
    @Override
    public List<TreeDto> getMenuTree(String treeFlag, int id) throws DataAccessException {
        LOG.debug("method getMenuTree start!");
        List<TreeDto> treeList = null;
        treeList = hibernateTemplate.find(SystemConstant.GET_MENU_TREE_HQL,
                new Object[] { treeFlag });
        LOG.debug("method getMenuTree end!");
        return DBDataToTree(treeList);
    }

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
    @SuppressWarnings("unchecked")
    @Override
    public void saveMenuTree(String jsonString, int groupID) throws DataAccessException,
            NumberFormatException {
        LOG.debug("method saveMenuTree start!");
        // need to delete actionIDS
        List<Integer> delIDs = new ArrayList<Integer>();
        // need to insert actionIDs
        List<Integer> insIDs = new ArrayList<Integer>();
        String[] ids = null;
        if (jsonString == null || "".equals(jsonString)) {
            ids = new String[0];
        } else {
            ids = jsonString.split(",");
        }
        // convert actionIDs's type to Integer
        List<Integer> newIDs = new ArrayList<Integer>();
        for (String id : ids) {
            newIDs.add(Integer.valueOf(id.trim()));
        }

        // get role actionIDs by groupID
        List<Integer> oldIDs = hibernateTemplate.find(SystemConstant.GET_GP_ACTIONIDS_HQL, groupID);

        // get needed to insert actionIDs
        insIDs = getInsertOrDeleteActionIDs(newIDs, oldIDs);
        // get needed to delete actionIDs
        delIDs = getInsertOrDeleteActionIDs(oldIDs, newIDs);

        if (!insIDs.isEmpty()) {
            doInsert(insIDs, groupID);
        }

        // when delete role authorizations,we also should delete the
        // authorizations of under the role, then we should delete users'
        // authorizations of the role and under the user.
        if (!delIDs.isEmpty()) {
            doDelete(delIDs, groupID);
        }
        LOG.debug("method saveMenuTree end!");

    }

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
    @SuppressWarnings("unchecked")
    @Override
    public void saveGroupAuthorization(String jsonString, int groupID) throws DataAccessException,
            NumberFormatException {
        LOG.debug("method saveAuthorization start!");
        // need to delete actionIDS
        List<Integer> delIDs = new ArrayList<Integer>();
        // need to insert actionIDs
        List<Integer> insIDs = new ArrayList<Integer>();
        String[] ids = null;
        if (jsonString == null || "".equals(jsonString)) {
            ids = new String[0];
        } else {
            ids = jsonString.split(",");
        }
        // convert actionIDs's type to Integer
        List<Integer> newIDs = new ArrayList<Integer>();
        for (String id : ids) {
            newIDs.add(Integer.valueOf(id.trim()));
        }

        // get role actionIDs by groupID
        List<Integer> oldIDs = hibernateTemplate.find(SystemConstant.GET_GP_ACTIONIDS_HQL, groupID);

        // get needed to insert actionIDs
        insIDs = getInsertOrDeleteActionIDs(newIDs, oldIDs);
        // get needed to delete actionIDs
        delIDs = getInsertOrDeleteActionIDs(oldIDs, newIDs);

        if (!insIDs.isEmpty()) {
            doInsert(insIDs, groupID);
        }

        // when delete role authorizations,we also should delete the
        // authorizations of under the role, then we should delete users'
        // authorizations of the role and under the user.
        if (!delIDs.isEmpty()) {
            doDelete(delIDs, groupID);
        }
        LOG.debug("method saveAuthorization end!");
    }

    /**
     * get users.
     * 
     * @param userID
     *            int user ID
     * @return Map<String, Object> the users
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public Map<String, Object> getUser(int userID) throws DataAccessException {
        LOG.debug("method getUser start!");
        Map<String, Object> map = new HashMap<String, Object>();
        List<?> users = null;
        int groupID = (Integer) hibernateTemplate.find(SystemConstant.GET_GROUPID_HQL, userID).get(
                0);
        /**
         * if groupID = 3 expressed that minister register
         */
        if (groupID == 3) {
            users = hibernateTemplate.find(SystemConstant.GET_USER_DOWN_OF_MINISTER, new Object[] {
                    userID, groupID });
            /**
             * if groupID = 4 expressed that chargehand register
             */
        } else if (groupID == 4) {
            users = hibernateTemplate.find(SystemConstant.GET_USER_DOWN_OF_CHARGEHAND,
                    new Object[] { userID, userID, groupID });
        }
        map.put("items", users);
        map.put("total", users.size());
        LOG.debug("method getUser end!");
        return map;
    }

    /**
     * get roles.
     * 
     * @param groupID
     *            int group ID
     * @return Map<String, Object>
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public Map<String, Object> getGroupManager(int groupID) throws DataAccessException {
        LOG.debug("method getGroupManager start!");
        Map<String, Object> map = new HashMap<String, Object>();
        List<?> groupList = hibernateTemplate.find(SystemConstant.GET_GROUP_MANAGER_HQL, groupID);
        map.put("items", groupList);
        LOG.debug("method getGroupManager end!");
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
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public Map<String, Object> getOperationAuthorization(int groupID, int rootID)
            throws DataAccessException {
        LOG.debug("method getOperationAuthorization start!");
        Map<String, Object> map = new HashMap<String, Object>();
        List<?> list = hibernateTemplate.find(SystemConstant.GET_OPERA_AUTHORIZATION_HQL, groupID,
                rootID * 10);
        map.put("actionIDs", list);
        LOG.debug("method getOperationAuthorization end!");
        return map;
    }

    /**
     * get role authorization for display when save or delete authorization.
     * 
     * @param groupID
     *            int role ID
     * @return Map<String, Object> role authorization
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @Override
    public Map<String, Object> getGroupAuthorization(int groupID) throws DataAccessException {
        LOG.debug("method getGroupAuthorization start!");
        Map<String, Object> map = new HashMap<String, Object>();
        List<?> menuNodes = null;
        List<?> operationNodes = null;
        menuNodes = hibernateTemplate.find(SystemConstant.GET_GROUP_ACCESS_AUTHORIZATION_HQL,
                groupID);
        operationNodes = hibernateTemplate.find(
                SystemConstant.GET_GROUP_OPERATION_AUTHORIZATION_HQL, groupID);
        map.put("menuNodes", menuNodes);
        map.put("operationNodes", operationNodes);
        LOG.debug("method getGroupAuthorization end!");
        return map;
    }

    /**
     * format db list data to exjs tree json data
     * 
     * @param treeList
     * @return List<TreeDto> extjs tree data
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    @SuppressWarnings("unchecked")
    private List<TreeDto> DBDataToTree(List<TreeDto> treeList) throws DataAccessException {
        LOG.debug("method DBDataToTree start!");
        List<TreeDto> fatherNodeList = hibernateTemplate
                .find(SystemConstant.GET_TREE_FATHER_NODES_HQL);
        Map<Integer, TreeDto> fatherMap = new HashMap<Integer, TreeDto>();
        for (TreeDto tree : fatherNodeList) {
            fatherMap.put(tree.getId(), tree);
        }
        TreeDto treeNode = null;
        int fatherID;
        Map<Integer, TreeDto> map = new LinkedHashMap<Integer, TreeDto>();
        for (TreeDto tree : treeList) {
            map.put(tree.getId(), tree);

        }
        for (int i = 0; i < treeList.size(); i++) {
            treeNode = treeList.get(i);
            fatherID = treeNode.getFatherID();
            while (fatherID != -1) {
                if (!map.containsKey(fatherID)) {

                    treeNode = fatherMap.get(fatherID);
                    map.put(fatherID, treeNode);
                    treeList.add(treeNode);
                } else {
                    treeNode = map.get(fatherID);
                }
                fatherID = treeNode.getFatherID();
            }
        }
        for (TreeDto tree : treeList) {
            fatherID = tree.getFatherID();
            if (fatherID != -1) {
                map.get(tree.getFatherID()).addChild(tree);
            }
        }
        treeList.clear();
        for (Integer i : map.keySet()) {
            if (map.get(i).getFatherID() == -1) {
                treeList.add(map.get(i));
            }
        }
        LOG.debug("method DBDataToTree end!");
        return treeList;
    }

    /**
     * get insert or delete authorization IDs.
     * 
     * @param beforeIDs
     *            List<Integer> a list of actionIDs
     * @param afterIDs
     *            List<Integer> the other one list of actionIDs
     * @return List<Integer> insert or delete authorization IDs
     */
    private List<Integer> getInsertOrDeleteActionIDs(List<Integer> beforeIDs, List<Integer> afterIDs) {
        List<Integer> list = new ArrayList<Integer>();
        Iterator<Integer> beforeIter = beforeIDs.iterator();
        Iterator<Integer> afterIter = null;

        while (beforeIter.hasNext()) {
            boolean isEqual = false;
            Integer temp = beforeIter.next();
            afterIter = afterIDs.iterator();
            while (afterIter.hasNext()) {
                Integer test = afterIter.next();
                if (temp.equals(test)) {
                    isEqual = true;
                    break;
                }
            }
            if (!isEqual) {
                list.add(temp);
            }
        }
        return list;
    }

    /**
     * delete role authorization by group ID
     * 
     * @param deletedIDs
     *            List<Integer> deleted actionIDs
     * @param groupID
     *            int role ID
     */
    @SuppressWarnings("unchecked")
    private void doDelete(List<Integer> deletedIDs, int groupID) {
        String[] paramNames = new String[] { "actionIDs", "groupID" };
        Object[] values = new Object[] { deletedIDs, groupID };
        List<ActionGroupDto> listGroup = hibernateTemplate.findByNamedParam(
                SystemConstant.DEL_GP_ACTIONIDS_HQL, paramNames, values);
        hibernateTemplate.deleteAll(listGroup);
    }

    /**
     * insert authorization IDs to role.
     * 
     * @param insertedIDs
     *            List<Integer> inserted actionIDs
     * @param groupID
     *            int role ID
     */
    private void doInsert(List<Integer> insertedIDs, int groupID) {
        List<ActionGroupDto> authInsert = new ArrayList<ActionGroupDto>();
        Iterator<Integer> insert = insertedIDs.iterator();
        while (insert.hasNext()) {
            authInsert.add(new ActionGroupDto(0, insert.next(), groupID));
        }
        hibernateTemplate.saveOrUpdateAll(authInsert);
    }

}
