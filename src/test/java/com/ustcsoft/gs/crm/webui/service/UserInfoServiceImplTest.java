package com.ustcsoft.gs.crm.webui.service;

import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.system.bean.UserInfoSearchBean;
import com.ustcsoft.gs.crm.webui.system.constant.SystemConstant;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;
import com.ustcsoft.gs.crm.webui.system.service.impl.UserInfoServiceImpl;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

public class UserInfoServiceImplTest extends CRMTest {
    private UserInfoDto user = new UserInfoDto();
    private UserInfoServiceImpl test = null;
    private UserInfoSearchBean searchBean = new UserInfoSearchBean();
    private String searchText = null;
    public static int deleteUserId = 0;

    @Before
    public void setup() throws Exception {
        test = (UserInfoServiceImpl) CTX.getBean("userInfoService");
        user.setCompany("科大恒星");
        user.setDepartmentID(1);
        user.setDescriptions("不错");
        user.setEducation("01");
        user.setEmail("123@163.com");
        user.setEntryTime("2012-12-12");
        user.setGroupID(2);
        // user.setIsAbolished(false);
        user.setJob("java");
        user.setJobID("01");
        user.setProjectTeamID(1);
        user.setRealName("wei0");
        user.setUserName("12_asa");

        searchText = "科大";
        // searchBean.setDepartmentID(1);
        // searchBean.setProjectTeamID(1);
        searchBean.setRealName("wei");
        searchBean.setDepartmentID(1);
        searchBean.setProjectTeamID(1);
        searchBean.setGroupID(4);
        searchBean.setSearchText(searchText);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testUpdateUserInfo() throws CRMDBException {
        // int groupID = 1;
        // boolean isAbolished = false;
        int start = (int) test.getUserInfoDao().getUserInfoSize(SystemConstant.USERINFO_COUNT_HQL);
        test.updateUser(user);
        deleteUserId = user.getUserID();
        int end = (int) test.getUserInfoDao().getUserInfoSize(SystemConstant.USERINFO_COUNT_HQL);
        Assert.assertEquals(end - start, 1);

    }

    @SuppressWarnings("unchecked")
    @Test
    public void testSearchOrGetAllUserInfo() throws CRMDBException {
        // test load
        super.map = test.searchOrGetAllUserInfo(0, searchBean, 1, 25);
        int total = Integer.parseInt(super.map.get("results").toString());
        List<?> list1 = (List<?>) map.get("items");
        boolean bool = total >= list1.size();
        Assert.assertEquals(bool, true);

        // test simpleQuery
        super.map = test.searchOrGetAllUserInfo(1, searchBean, 1, 25);
        List<?> list2 = (List<?>) map.get("items");
        for (int i = 0; i < list2.size(); i++) {
            boolean boolBefore = true;
            Map<String, Object> result = (Map<String, Object>) list2.get(i);
            boolean bool1 = ((String) result.get("groupIDB")).contains(searchText);
            boolean bool2 = ((String) result.get("jobID")).contains(searchText);
            boolean bool3 = ((String) result.get("userName")).contains(searchText);
            boolean bool4 = ((String) result.get("realName")).contains(searchText);
            boolean bool5 = ((String) result.get("company")).contains(searchText);
            String departmentID = (String) result.get("departmentIDB");
            boolean bool6 = departmentID == null || !departmentID.contains(searchText);
            String projectTeamID = (String) result.get("projectTeamIDB");
            boolean bool7 = projectTeamID == null || !projectTeamID.contains(searchText);
            boolean bool8 = ((String) result.get("email")).contains(searchText);
            String job = (String) result.get("job");
            boolean bool9 = job == null || !job.contains(searchText);
            String jobTitle = (String) result.get("jobTitleB");
            boolean bool10 = jobTitle == null || !jobTitle.contains(searchText);
            String mobile = (String) result.get("mobile");
            boolean bool11 = mobile == null || !mobile.contains(searchText);
            String officePhone = (String) result.get("officePhone");
            boolean bool12 = officePhone == null || !officePhone.contains(searchText);
            String education = (String) result.get("educationB");
            boolean bool13 = education == null || !education.contains(searchText);
            String entryTime = (String) result.get("entryTime");
            boolean bool14 = entryTime == null || !entryTime.contains(searchText);
            String descriptions = (String) result.get("descriptions");
            boolean bool15 = descriptions == null || !descriptions.contains(searchText);
            boolBefore = bool1 || bool2 || bool3 || bool4 || bool5 || !bool6 || !bool7 || bool8
                    || !bool9 || !bool10 || !bool11 || !bool12 || !bool13 || !bool14 || !bool15;
            Assert.assertEquals(boolBefore, true);
        }

        // test superQuery
        super.map = test.searchOrGetAllUserInfo(2, searchBean, 1, 25);
        List<?> list3 = (List<?>) map.get("items");
        for (int i = 0; i < list3.size(); i++) {
            boolean bool31 = true;
            Map<String, Object> result = (Map<String, Object>) list3.get(i);
            bool31 = ((String) result.get("realName")).contains("wei");
            Assert.assertEquals(bool31, true);
        }

    }

    @Test
    public void testJudgeUserName() throws CRMDBException {
        UserInfoDto user1 = new UserInfoDto();
        user1.setGroupID(2);
        user1.setCompany("科大恒星");
        user1.setUserName("username1122");
        user1.setRealName("realname");
        user1.setEmail("12345@163.com");
        user1.setJobID("12345");
        boolean bool11 = true;
        bool11 = test.judgeUserName(user1);
        Assert.assertEquals(false, bool11);
        user1.setUserName("username111");
        test.updateUser(user1);
        boolean bool12 = true;
        bool12 = test.judgeUserName(user1);
        Assert.assertEquals(false, bool12);
        user1.setUserName("12_asa");
        boolean bool13 = true;
        bool13 = test.judgeUserName(user1);
        Assert.assertEquals(true, bool13);
        test.deleteUser(String.valueOf(user1.getUserID()));
    }

    @Test
    public void testJudgeJobID() throws CRMDBException {
        UserInfoDto user2 = new UserInfoDto();
        user2.setGroupID(2);
        user2.setCompany("科大恒星");
        user2.setUserName("username");
        user2.setRealName("realname");
        user2.setEmail("12345@163.com");
        user2.setJobID("12345999888");
        boolean bool21 = true;
        bool21 = test.judgeJobID(user2);
        Assert.assertEquals(false, bool21);
        user2.setJobID("1234567899999");
        test.updateUser(user2);
        boolean bool22 = true;
        bool22 = test.judgeJobID(user2);
        Assert.assertEquals(false, bool22);
        user2.setJobID("01");
        boolean bool23 = true;
        bool23 = test.judgeJobID(user2);
        Assert.assertEquals(true, bool23);
        test.deleteUser(String.valueOf(user2.getUserID()));
    }

    @Test
    public void testdeleteUserInfo() throws CRMDBException {
        int start = (int) test.getUserInfoDao().getUserInfoSize(SystemConstant.USERINFO_COUNT_HQL);
        test.deleteUser(String.valueOf(deleteUserId));
        int end = (int) test.getUserInfoDao().getUserInfoSize(SystemConstant.USERINFO_COUNT_HQL);
        Assert.assertEquals(1, start - end);
    }

    @Test
    public void testGetGroupid() {
        super.map = test.getGroupID(1);
        List<?> list = (List<?>) map.get(CRMConstant.ITEMS);
        int size = list.size();
        Assert.assertEquals(4, size);
    }

}
