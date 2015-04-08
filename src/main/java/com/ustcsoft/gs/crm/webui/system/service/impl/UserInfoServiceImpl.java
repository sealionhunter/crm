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
import com.ustcsoft.gs.crm.webui.common.util.CryptUtil;
import com.ustcsoft.gs.crm.webui.system.bean.UserInfoSearchBean;
import com.ustcsoft.gs.crm.webui.system.dao.UserInfoDao;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;
import com.ustcsoft.gs.crm.webui.system.service.UserInfoService;

public class UserInfoServiceImpl implements UserInfoService {
    private static final String DEFAULT_PASSWORD = "000000";
    private UserInfoDao userInfoDao = null;
    private static final Log log = LogFactory.getLog(UserInfoServiceImpl.class);

    public UserInfoDao getUserInfoDao() {
        return userInfoDao;
    }

    public void setUserInfoDao(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    /**
     * search Or Get AllUserInfo
     * 
     * @param searchFlag
     * @param searchValue
     * @param currPage
     * @param pageSize
     * @return type of Map<String,Object>
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public Map<String, Object> searchOrGetAllUserInfo(int searchFlag,
            UserInfoSearchBean searchValue, int currPage, int pageSize) throws CRMDBException {
        log.debug("method searchOrGetAllUserInfo start!");
        Map<String, Object> userMap = null;
        try {
            userMap = userInfoDao.queryUser(searchFlag, searchValue, currPage, pageSize);
        } catch (DataAccessException e) {
            log.error("DataAccessException occurs in method judgeCompetitorName!", e);
            throw new CRMDBException(e);
        }
        log.debug("method searchOrGetAllUserInfo end!");
        return userMap;
    }

    /**
     * get GroupID
     * 
     * @param groupID
     * @return type of Map<String,Object>
     */
    @Override
    public Map<String, Object> getGroupID(int groupID) {
        return userInfoDao.getGroupID(groupID);
    }

    /**
     * get user's department information
     * 
     * @param userID
     * @param groupID
     * @param departmentID
     * @return Map department information Collection
     * @throws CRMDBException
     *             in case of Hibernate errors
     */
    @Override
    public Map<String, Object> getUserDepartment(int userID, int groupID, int departmentID)
            throws CRMDBException {
        log.debug("method getUserDepartment start.");
        Map<String, Object> map = null;
        try {
            map = userInfoDao.getUserDepartment(userID, groupID, departmentID);
        } catch (DataAccessException e) {
            log.error("DataAccessException occurs in method getUserDepartment.", e);
            throw new CRMDBException(e);
        }
        log.debug("method getUserDepartment start.");
        return map;
    }

    /**
     * update User
     * 
     * @param userInfoDto
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void updateUser(UserInfoDto userInfoDto) throws CRMDBException {
        log.debug("method updateUser start!");
        try {
            if (userInfoDto.getUserID() == 0) {
                userInfoDto.setPassword(CryptUtil.encrypt(DEFAULT_PASSWORD));
            }
            userInfoDao.updateUser(userInfoDto);
        } catch (DataAccessException e) {
            log.error("DataAccessException occurs in method updateUser!", e);
            throw new CRMDBException(e);
        }
        log.debug("method updateUser end!");
    }

    /**
     * delete User
     * 
     * @param userIDs
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @SuppressWarnings("unchecked")
    public Map<String, Object> deleteUser(String userIDs) throws CRMDBException {
        log.debug("method deleteUser start!");
        Map<String, Object> userMap = new HashMap<String, Object>();
        try {
            Map<String, Object> map = userInfoDao.checkDelete(userIDs);
            List<Integer> result = (List<Integer>) map.get("result");
            if (result.isEmpty()) {
                userInfoDao.deleteUser(userIDs);
            } else {
                String errorMessage = getErrMsg(map);
                userMap.put("errorMessage", errorMessage);
            }
        } catch (DataAccessException e) {
            log.error("DataAccessException occurs in method deleteUser!", e);
            throw new CRMDBException(e);
        }
        log.debug("method deleteUser end!");
        return userMap;
    }

    /**
     * reset User's Password
     * 
     * @param userID
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Map<String, Object> resetUserPass(String userID) throws CRMDBException {
        log.debug("method resetUserPass start!");
        Map<String, Object> userMap = new HashMap<String, Object>();
        try {
            UserInfoDto user = userInfoDao.getUserByID(Integer.valueOf(userID));
            if (user != null) {
                user.setPassword(CryptUtil.encrypt(DEFAULT_PASSWORD));
                userInfoDao.updateUser(user);
            } else {
                userMap.put("errorMessage", "该用户已被删除!");
            }
        } catch (DataAccessException e) {
            log.error("DataAccessException occurs in method resetUserPass!", e);
            throw new CRMDBException(e);
        }
        log.debug("method resetUserPass end!");
        return userMap;
    }

    /**
     * get checkDelete error message
     * 
     * @param map
     * @return string
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @SuppressWarnings("unchecked")
    public String getErrMsg(Map<String, Object> map) throws CRMDBException {
        StringBuffer errorMessage = new StringBuffer("");
        List<Integer> list1 = (List<Integer>) map.get("list1");
        List<Integer> list2 = (List<Integer>) map.get("list2");
        List<Integer> list3 = (List<Integer>) map.get("list3");
        List<Integer> list4 = (List<Integer>) map.get("list4");
        List<Integer> list5 = (List<Integer>) map.get("list5");
        List<Integer> result = (List<Integer>) map.get("result");
        Integer[] userID = (Integer[]) map.get("userID");
        int errUserID = 0;
        String errName = "";
        for (Integer element : userID) {
            if (result.contains(element)) {
                errUserID = element;
                break;
            }
        }
        try {
            errName = userInfoDao.getUserNameByID(errUserID);
        } catch (DataAccessException e) {
            log.error("DataAccessException occurs in method getErrMsg!", e);
            throw new CRMDBException(e);
        }

        errorMessage.append(errName + "正在被");
        int flag = 0;
        if (list1.contains(errUserID)) {
            errorMessage.append("部门管理模块");
            flag++;
        }
        if (list2.contains(errUserID)) {
            if (flag > 0) {
                errorMessage.append("、团队管理模块");
            } else {
                errorMessage.append("团队管理模块");
            }
            flag++;
        }
        if (list3.contains(errUserID)) {
            if (flag > 0) {
                errorMessage.append("、客户管理模块");
            } else {
                errorMessage.append("客户管理模块");
            }
            flag++;
        }
        if (list4.contains(errUserID)) {
            if (flag > 0) {
                errorMessage.append("、销售管理模块");
            } else {
                errorMessage.append("销售管理模块");
            }
            flag++;
        }
        if (list5.contains(errUserID)) {
            if (flag > 0) {
                errorMessage.append("、客户联系模块");
            } else {
                errorMessage.append("客户联系模块");
            }
            flag++;
        }
        errorMessage.append("使用，无法删除！");
        return errorMessage.toString();
    }

    /**
     * judge if UserName existed
     * 
     * @param userInfoDto
     * @return type of boolean
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public boolean judgeUserName(UserInfoDto userInfoDto) throws CRMDBException {
        log.debug("method judgeUserName start!");
        boolean nameIsExisted = true;
        String userName = userInfoDto.getUserName();
        int userID = userInfoDto.getUserID();
        try {
            if (userID == 0) {
                nameIsExisted = userInfoDao.judgeUserName(userName);
            } else {
                String oldUserName = userInfoDao.findUserNameByUserID(userID);
                if (userName.equals(oldUserName)) {
                    nameIsExisted = false;
                } else {
                    nameIsExisted = userInfoDao.judgeUserName(userName);
                }
            }
        } catch (DataAccessException e) {
            log.error("DataAccessException occurs in method judgeUserName!", e);
            throw new CRMDBException(e);
        }
        log.debug("method judgeUserName end!");
        return nameIsExisted;
    }

    /**
     * judge if JobID existed
     * 
     * @param userInfoDto
     * @return type of boolean
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public boolean judgeJobID(UserInfoDto userInfoDto) throws CRMDBException {
        log.debug("method judgeJobID start!");
        boolean jobIDIsExisted = true;
        String jobID = userInfoDto.getJobID();
        int userID = userInfoDto.getUserID();
        try {
            if (userID == 0) {
                jobIDIsExisted = userInfoDao.judgeJobID(jobID);
            } else {
                String oldJobID = userInfoDao.findJobIDByUserID(userID);
                if (jobID.equals(oldJobID)) {
                    jobIDIsExisted = false;
                } else {
                    jobIDIsExisted = userInfoDao.judgeJobID(jobID);
                }
            }
        } catch (DataAccessException e) {
            log.error("DataAccessException occurs in method judgeJobID!", e);
            throw new CRMDBException(e);
        }
        log.debug("method judgeJobID end!");
        return jobIDIsExisted;
    }

    @Override
    public String getDepartmentNameByID(int departmentID) {
        return getUserInfoDao().getDepartmentNameByID(departmentID);
    }
}
