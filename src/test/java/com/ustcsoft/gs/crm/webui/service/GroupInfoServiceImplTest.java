package com.ustcsoft.gs.crm.webui.service;

import java.util.List;
import java.util.Map;

import jp.co.dgic.testing.common.virtualmock.MockObjectManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.system.constant.SystemConstant;
import com.ustcsoft.gs.crm.webui.system.dao.GroupInfoDao;
import com.ustcsoft.gs.crm.webui.system.dto.GroupManagerDto;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;
import com.ustcsoft.gs.crm.webui.system.service.GroupInfoService;
import com.ustcsoft.gs.crm.webui.system.service.UserInfoService;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

/**
 * test class GroupInfoServideImpl
 * 
 * @author maxue
 * 
 */
public class GroupInfoServiceImplTest extends CRMTest {
    public static final String TEST0 = "测试角色名";
    private static final String TOTAL = "total";
    private static final String ITEMS = "items";
    public static GroupInfoService groupInfoService = null;
    public static UserInfoService userInfoService = null;
    public static HibernateTemplate hibernate = null;
    private Map<String, Object> map = null;
    List<?> GroupList = null;
    GroupManagerDto groupManagerDto = new GroupManagerDto();
    private static int deleteId = -1;
    private boolean isExisted = false;
    private static UserInfoDto user = new UserInfoDto();
    private static String userID = null;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        groupInfoService = (GroupInfoService) CTX.getBean("groupInfoService");
        userInfoService = (UserInfoService) CTX.getBean("userInfoService");
        hibernate = (HibernateTemplate) CTX.getBean("hibernateTemplate");
    }

    @Before
    public void setUp() {
        user.setCompany("科大恒星");
        user.setDepartmentID(1);
        user.setDescriptions("不错");
        user.setEducation("01");
        user.setEmail("123@163.com");
        user.setEntryTime("2012-12-12");
        user.setGroupID(2);
        user.setJob("java");
        user.setJobID("01");
        user.setProjectTeamID(1);
        user.setRealName("wei0");
        user.setUserName("12_asa");
    }

    @Test
    public void testUpdateGroup() throws CRMDBException {
        groupManagerDto.setGroupName(TEST0);
        map = userInfoService.getGroupID(0);
        GroupList = (List<?>) map.get(ITEMS);
        int start = GroupList.size();
        groupInfoService.updateGroup(groupManagerDto);
        deleteId = groupManagerDto.getGroupID();
        map = userInfoService.getGroupID(0);
        GroupList = (List<?>) map.get(ITEMS);
        int end = GroupList.size();
        Assert.assertEquals(end - start, 1);
    }

    @Test
    public void testGetGroup() throws CRMDBException {
        // TODO
        map = groupInfoService.getGroup(-1);
        List<?> list = (List<?>) map.get("items");
        Assert.assertNotNull(list);
    }

    @Test
    public void testDeleteGroup() throws CRMDBException {
        map = userInfoService.getGroupID(0);
        GroupList = (List<?>) map.get(ITEMS);
        int start = GroupList.size();
        groupInfoService.deleteGroup(deleteId);
        map = userInfoService.getGroupID(0);
        GroupList = (List<?>) map.get(ITEMS);
        int end = GroupList.size();
        Assert.assertEquals(start - end, 1);
    }

    @Test
    public void testGetGroupMembers() throws CRMDBException {
        int groupID = -1;
        int departmentID = 1;
        int currtPage = 1;
        int pageSize = 20;
        int count = -1;
        map = groupInfoService.getGroupMembers(departmentID, groupID, currtPage, pageSize);
        count = Integer.parseInt(map.get(TOTAL).toString());
        Assert.assertEquals(count, 0);
    }

    @Test
    public void testJudgeGroupName() throws CRMDBException {
        GroupManagerDto group1 = new GroupManagerDto();
        group1.setGroupID(1);
        group1.setGroupName("超级管理员");
        isExisted = groupInfoService.judgeGroupName(group1);
        Assert.assertEquals(isExisted, false);

        GroupManagerDto group2 = new GroupManagerDto();
        group2.setGroupID(0);
        group2.setGroupName("超级管理员");
        isExisted = groupInfoService.judgeGroupName(group2);
        Assert.assertEquals(isExisted, true);

        GroupManagerDto group3 = new GroupManagerDto();
        group3.setGroupID(2);
        group3.setGroupName("超级管理员");
        isExisted = groupInfoService.judgeGroupName(group3);
        Assert.assertEquals(isExisted, true);
    }

    @Test
    public void testAddMembers() throws CRMDBException {
        userInfoService.updateUser(user);
        long userTotal1 = (Long) hibernate.findByNamedParam(SystemConstant.GROUPMEMBERS_COUNT_HQL,
                SystemConstant.GROUPID, 2).get(0);
        userID = user.getUserID() + "";
        groupInfoService.addMembers(userID, 1);
        long userTotal2 = (Long) hibernate.findByNamedParam(SystemConstant.GROUPMEMBERS_COUNT_HQL,
                SystemConstant.GROUPID, 2).get(0);
        Assert.assertEquals(userTotal1 - userTotal2, 1);
    }

    @Test
    public void testRemoveMembers() throws CRMDBException {
        long userTotal1 = (Long) hibernate.findByNamedParam(SystemConstant.GROUPMEMBERS_COUNT_HQL,
                SystemConstant.GROUPID, 2).get(0);
        groupInfoService.removeMembers(userID, 1, 2);
        long userTotal2 = (Long) hibernate.findByNamedParam(SystemConstant.GROUPMEMBERS_COUNT_HQL,
                SystemConstant.GROUPID, 2).get(0);
        hibernate.delete(user);
        Assert.assertEquals(userTotal2 - userTotal1, 1);
    }

    @Test(expected = CRMDBException.class)
    public void testDeleteGroupThrowsCRMDBexception() throws CRMDBException {
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(GroupInfoDao.class, "deleteGroup",
                new EmptyResultDataAccessException(0));
        groupInfoService.deleteGroup(-1);
    }

    /**
     * test the method execute when throws a SQlException
     * 
     * @throws CRMDBException
     */
    @Test(expected = CRMDBException.class)
    public void testGetGroupMembersThrowsCRMDBException() throws CRMDBException {
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(GroupInfoDao.class, "getGroupMembers",
                new EmptyResultDataAccessException(0));
        map = groupInfoService.getGroupMembers(1, 1, 0, 10);
    }

    @Test(expected = CRMDBException.class)
    public void testJudgeGroupNameThorwsCRMDBException() throws CRMDBException {
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(GroupInfoDao.class, "judgeGroupName",
                new EmptyResultDataAccessException(0));
        groupManagerDto.setGroupID(1);
        groupManagerDto.setGroupName("");
        groupInfoService.judgeGroupName(groupManagerDto);
    }

    @Test(expected = CRMDBException.class)
    public void testUpdateThrowsCRMDBException() throws CRMDBException {
        groupManagerDto
                .setGroupName("ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
        groupInfoService.updateGroup(groupManagerDto);
    }

    @Test(expected = CRMDBException.class)
    public void testRemoveMembersThrowsCRMDBexception() throws CRMDBException {
        String string = "";
        groupInfoService.removeMembers(string, 1, 1);
    }

    @Test(expected = CRMDBException.class)
    public void testaddMembersThrowsCRMDBexception() throws CRMDBException {
        String string = "";
        groupInfoService.addMembers(string, 1);
    }
}
