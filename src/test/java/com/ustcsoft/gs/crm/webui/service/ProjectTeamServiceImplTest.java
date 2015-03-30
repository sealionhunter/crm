package com.ustcsoft.gs.crm.webui.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.dgic.testing.common.virtualmock.MockObjectManager;
import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.exception.CRMException;
import com.ustcsoft.gs.crm.webui.system.bean.ProjectTeamSearchBean;
import com.ustcsoft.gs.crm.webui.system.bean.UserInfoSearchBean;
import com.ustcsoft.gs.crm.webui.system.dao.ProjectTeamDao;
import com.ustcsoft.gs.crm.webui.system.dto.DepartmentDto;
import com.ustcsoft.gs.crm.webui.system.dto.ProjectTeamDto;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;
import com.ustcsoft.gs.crm.webui.system.service.ProjectTeamService;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

/**
 * ProjectTeamService junit test
 * 
 * @author wangzhanxu
 * 
 */
public class ProjectTeamServiceImplTest extends CRMTest {
    /** get service */
    private static ProjectTeamService service = null;
    /** get hibernate */
    private static HibernateTemplate hibernate = null;
    /** test data list */
    private List<String> addUserNameList = new ArrayList<String>();
    /** projectTeamID */
    private int projectTeamID;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        service = (ProjectTeamService) CTX.getBean("projectTeamService");
        hibernate = (HibernateTemplate) CTX.getBean("hibernateTemplate");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        addTestUsers();
    }

    @After
    public void tearDown() throws Exception {
        deleteTestUsers();
    }

    /**
     * removeMembers method test
     * 
     * @throws CRMDBException
     * @throws CRMException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testRemoveMembers() throws CRMDBException, CRMException {
        List<UserInfoDto> userList = hibernate
                .find("from UserInfoDto as uid where uid.projectTeamID=" + projectTeamID);
        Assert.assertEquals(5, userList.size());
        String removeIDs = getTestIDs();
        service.removeMembers(removeIDs, projectTeamID);
        userList = hibernate.find("from UserInfoDto as uid where uid.projectTeamID="
                + projectTeamID);
        Assert.assertEquals(0, userList.size());
        try {
            service.removeMembers("1,2,,4", -1);
        } catch (Exception e) {
            Assert.assertEquals(true, e instanceof CRMException);
        }
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(ProjectTeamDao.class, "removeMembers",
                new EmptyResultDataAccessException(0));
        try {
            service.removeMembers("1,2", -1);
        } catch (Exception e) {
            Assert.assertEquals(true, e instanceof CRMDBException);
        }
    }

    /**
     * addMembers method test
     * 
     * @throws CRMDBException
     * @throws CRMException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testAddMembers() throws CRMDBException, CRMException {
        String addIDs = getTestIDs();
        List<Integer> projectTeamIDList = hibernate
                .find("select max(uid.projectTeamID) from UserInfoDto as uid");
        int projectTeamID = projectTeamIDList.get(0) + 1;
        service.addMembers(addIDs, projectTeamID);
        List<UserInfoDto> userList = hibernate
                .find("from UserInfoDto as uid where uid.projectTeamID=" + projectTeamID);
        Assert.assertEquals(5, userList.size());
        try {
            service.addMembers("!", projectTeamID);
        } catch (Exception e) {
            Assert.assertEquals(true, e instanceof CRMException);
        }
        try {
            service.addMembers("", projectTeamID);
        } catch (Exception e) {
            Assert.assertEquals(true, e instanceof CRMDBException);
        }
    }

    /**
     * getMembers method test
     * 
     * @throws CRMDBException
     * @throws CRMException
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetMembers() throws CRMDBException, CRMException, DataAccessException {
        List<UserInfoDto> userList = hibernate
                .find("from UserInfoDto as uid where uid.projectTeamID=" + projectTeamID);
        int queryMembersMode = 1;
        UserInfoSearchBean searchBean = new UserInfoSearchBean();
        searchBean.setProjectTeamID(projectTeamID);
        searchBean.setSearchText("");
        Map<String, Object> map = service.getMembers(queryMembersMode, searchBean, 1, 25);
        long cnt = (Long) map.get("results");
        Assert.assertEquals(userList.size(), cnt);
        queryMembersMode = 2;
        searchBean.setUserID(2);
        map = service.getMembers(queryMembersMode, searchBean, 1, 25);
        userList = (List<UserInfoDto>) map.get("items");
        cnt = 0;
        for (UserInfoDto uid : userList) {
            for (String userName : addUserNameList) {
                if (userName.equals(uid.getUserName())) {
                    cnt++;
                }
            }
        }
        Assert.assertEquals(0, cnt);
        queryMembersMode = 3;
        try {
            service.getMembers(queryMembersMode, searchBean, 1, 25);
        } catch (Exception e) {
            Assert.assertEquals(true, e instanceof CRMException);
        }
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(ProjectTeamDao.class, "getProjectTeamMembers",
                new EmptyResultDataAccessException(0));
        searchBean.setUserID(-1);
        try {
            service.getMembers(1, searchBean, 1, 25);
        } catch (Exception e) {
            Assert.assertEquals(true, e instanceof CRMDBException);
        }
    }

    /**
     * getProjectTeam method test
     * 
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetProjectTeam() throws CRMDBException {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("sdfhsjkf");
        hibernate.saveOrUpdate(departmentDto);

        // new a dto and then set it
        ProjectTeamDto projectTeamDto = new ProjectTeamDto();
        projectTeamDto.setDepartmentID(departmentDto.getDepartmentID());
        projectTeamDto.setDescription("");
        projectTeamDto.setCreateTime("2013-10-22");
        projectTeamDto.setEndTime("2013-10-23");
        projectTeamDto.setProjectTeamLeaderID(2);
        projectTeamDto.setProjectTeamName("test");
        projectTeamDto.setProjectTeamStatusCode("000100010001");

        hibernate.saveOrUpdate(projectTeamDto);
        ProjectTeamSearchBean searchBean = new ProjectTeamSearchBean();
        List<Integer> departmentIDs = new ArrayList<Integer>();
        departmentIDs.add(projectTeamDto.getDepartmentID());
        searchBean.setDepartmentIDs(departmentIDs);
        searchBean.setProjectTeamName("");
        Map<String, Object> map = service.getProjectTeam(searchBean, 1, 25);
        List<Map<String, Object>> projectTeamList = (List<Map<String, Object>>) map.get("items");
        boolean same = "test".equals(projectTeamList.get(0).get("projectTeamName"));
        Assert.assertEquals(true, same);
        searchBean = new ProjectTeamSearchBean();
        try {
            service.getProjectTeam(searchBean, 1, 25);
        } catch (Exception e) {
            Assert.assertEquals(true, e instanceof CRMDBException);
        }
        hibernate.delete(projectTeamDto);
        hibernate.delete(departmentDto);
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(ProjectTeamDao.class, "getProjectTeam",
                new EmptyResultDataAccessException(0));
        try {
            service.getProjectTeam(searchBean, 1, 1);
        } catch (Exception e) {
            Assert.assertEquals(true, e instanceof CRMDBException);
        }
    }

    /**
     * deleteProjectTeam method test
     * 
     * @throws CRMDBException
     */
    @Test
    public void testDeleteProjectTeam() throws CRMDBException {
        // new a dto and then set it
        ProjectTeamDto projectTeamDto = new ProjectTeamDto();
        projectTeamDto.setDepartmentID(123);
        projectTeamDto.setDescription("");
        projectTeamDto.setCreateTime("2013-10-22");
        projectTeamDto.setEndTime("2013-10-23");
        projectTeamDto.setProjectTeamID(0);
        projectTeamDto.setProjectTeamLeaderID(1);
        projectTeamDto.setProjectTeamName("test");
        projectTeamDto.setProjectTeamStatusCode("000100010001");
        // save a test data
        hibernate.saveOrUpdate(projectTeamDto);
        service.deleteProjectTeam("" + projectTeamDto.getProjectTeamID());
        // get the test data's id
        long cnt = (Long) hibernate.find(
                "select count(*) from ProjectTeamDto ptd where ptd.projectTeamID="
                        + projectTeamDto.getProjectTeamID()).get(0);
        Assert.assertEquals(0, cnt);
        try {
            service.deleteProjectTeam("");
        } catch (Exception e) {
            Assert.assertEquals(true, e instanceof CRMDBException);
        }
    }

    /**
     * saveOrUpdateProjectTeam method test
     * 
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testSaveOrUpdateProjectTeam() throws CRMDBException {
        // new a dto and then set it
        ProjectTeamDto projectTeamDto = new ProjectTeamDto();
        projectTeamDto.setDepartmentID(123);
        projectTeamDto.setDescription("");
        projectTeamDto.setCreateTime("2013-10-22");
        projectTeamDto.setEndTime("2013-10-23");
        projectTeamDto.setProjectTeamID(0);
        projectTeamDto.setProjectTeamLeaderID(1);
        projectTeamDto.setProjectTeamName("test");
        projectTeamDto.setProjectTeamStatusCode("000100010001");
        // save a test data
        service.saveOrUpdateProjectTeam(projectTeamDto);
        List<ProjectTeamDto> projectTeamList = hibernate
                .find("from ProjectTeamDto ptd where ptd.projectTeamName = 'test'");
        Assert.assertEquals(true, projectTeamList.size() > 0);
        int maxId = (Integer) hibernate.find(
                "select max(ptd.projectTeamID) from ProjectTeamDto ptd").get(0);
        projectTeamList = hibernate
                .find("from ProjectTeamDto ptd where ptd.projectTeamID=" + maxId);
        if (projectTeamList.size() > 0) {
            projectTeamDto = projectTeamList.get(0);
            projectTeamDto.setProjectTeamName("test1");
        }
        service.saveOrUpdateProjectTeam(projectTeamDto);
        List<String> projectTeamNameList = hibernate
                .find("select ptd.projectTeamName from ProjectTeamDto ptd where ptd.projectTeamID="
                        + maxId);
        Assert.assertEquals(true, "test1".equals(projectTeamNameList.get(0)));
        hibernate.delete(projectTeamDto);
        projectTeamDto = new ProjectTeamDto();
        projectTeamDto.setProjectTeamName(null);
        try {
            service.saveOrUpdateProjectTeam(projectTeamDto);
        } catch (Exception e) {
            Assert.assertEquals(true, e instanceof CRMDBException);
        }
    }

    /**
     * getTeamLeaderSelecting method test
     * 
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetTeamLeaderSelecting() throws CRMDBException {
        UserInfoSearchBean searchBean = new UserInfoSearchBean();
        searchBean.setDepartmentID(1);
        searchBean.setUserID(2);
        Map<String, Object> map = service.getTeamLeaderSelecting(searchBean);
        List<Map<String, Object>> userList = (List<Map<String, Object>>) map.get("items");
        Assert.assertEquals(true, userList.size() > 0);
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(ProjectTeamDao.class, "getTeamLeaderSelecting",
                new EmptyResultDataAccessException(0));
        try {
            service.getTeamLeaderSelecting(searchBean);
        } catch (Exception e) {
            Assert.assertEquals(true, e instanceof CRMDBException);
        }
    }

    /**
     * findProjectTeamIDByName method test
     * 
     * @throws CRMDBException
     */
    @Test
    public void testFindProjectTeamIDByName() throws CRMDBException {
        List<Integer> projectTeamIDs = service.findProjectTeamIDByName("test123");
        Assert.assertEquals(0, projectTeamIDs.size());
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(ProjectTeamDao.class, "findProjectTeamIDByName",
                new EmptyResultDataAccessException(0));
        try {
            service.findProjectTeamIDByName("");
        } catch (Exception e) {
            Assert.assertEquals(true, e instanceof CRMDBException);
        }
    }

    /**
     * judgeProjectTeamExistByName method test
     * 
     * @throws CRMDBException
     */
    @Test
    public void testJudgeProjectTeamExistByName() throws CRMDBException {
        // new a dto and then set it
        ProjectTeamDto projectTeamDto = new ProjectTeamDto();
        projectTeamDto.setDepartmentID(123);
        projectTeamDto.setDescription("");
        projectTeamDto.setCreateTime("2013-10-22");
        projectTeamDto.setEndTime("2013-10-23");
        projectTeamDto.setProjectTeamID(0);
        projectTeamDto.setProjectTeamLeaderID(1);
        projectTeamDto.setProjectTeamName("test");
        projectTeamDto.setProjectTeamStatusCode("000100010001");
        hibernate.saveOrUpdate(projectTeamDto);
        boolean exist = service.judgeProjectTeamExistByName("test");
        hibernate.bulkUpdate("delete from ProjectTeamDto ptd where ptd.projectTeamName='test'");
        Assert.assertEquals(true, exist);
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(ProjectTeamDao.class, "judgeProjectTeamExistByName",
                new EmptyResultDataAccessException(0));
        try {
            service.judgeProjectTeamExistByName(null);
        } catch (Exception e) {
            Assert.assertEquals(true, e instanceof CRMDBException);
        }
    }

    /**
     * getProjectTeamStatus method test
     * 
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetProjectTeamStatus() throws CRMDBException {
        Map<String, Object> statusList = service.getProjectTeamStatus();
        List<Map<String, Object>> mapList = (List<Map<String, Object>>) statusList.get("items");
        Assert.assertEquals(true, mapList.size() > 0);
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(ProjectTeamDao.class, "getProjectTeamStatus",
                new EmptyResultDataAccessException(0));
        try {
            service.getProjectTeamStatus();
        } catch (Exception e) {
            Assert.assertEquals(true, e instanceof CRMDBException);
        }
    }

    /**
     * add test data
     */
    private void addTestUsers() {
        ProjectTeamDto projectTeamDto = new ProjectTeamDto();
        projectTeamDto.setDepartmentID(123);
        projectTeamDto.setDescription("");
        projectTeamDto.setCreateTime("2013-10-22");
        projectTeamDto.setEndTime("2013-10-23");
        projectTeamDto.setProjectTeamLeaderID(33);
        projectTeamDto.setProjectTeamName("lalalla");
        projectTeamDto.setProjectTeamStatusCode("000100010001");
        hibernate.saveOrUpdate(projectTeamDto);
        List<UserInfoDto> addUserList = new ArrayList<UserInfoDto>();
        for (int i = 0; i < 5; i++) {
            UserInfoDto user = new UserInfoDto();
            user.setUserName("test" + i);
            user.setCompany("科大恒星");
            user.setDepartmentID(1);
            user.setDescriptions("不错");
            user.setEducation("01");
            user.setEmail("123@163.com");
            user.setEntryTime("2012-12-12");
            user.setGroupID(2);
            user.setJob("java");
            user.setJobID("01");
            user.setPassword("000000");
            user.setProjectTeamID(projectTeamDto.getProjectTeamID());
            user.setRealName("wei0");
            projectTeamID = projectTeamDto.getProjectTeamID();
            addUserList.add(user);
            addUserNameList.add(user.getUserName());
        }
        hibernate.saveOrUpdateAll(addUserList);
    }

    /**
     * convert to String like "1,2,3"
     * 
     * @return sb;
     */
    @SuppressWarnings("unchecked")
    private String getTestIDs() {
        List<UserInfoDto> userList = hibernate.findByNamedParam(
                "from UserInfoDto as uid where uid.userName in(:userName)", "userName",
                addUserNameList);
        StringBuilder sb = new StringBuilder();
        for (UserInfoDto uid : userList) {
            sb.append(uid.getUserID() + ",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * delete test data
     */
    @SuppressWarnings("unchecked")
    private void deleteTestUsers() {
        List<UserInfoDto> userList = hibernate.findByNamedParam(
                "from UserInfoDto as uid where uid.userName in(:userName)", "userName",
                addUserNameList);
        hibernate.deleteAll(userList);
        List<ProjectTeamDto> pList = hibernate
                .find("from ProjectTeamDto ptd where ptd.projectTeamID=" + projectTeamID);
        hibernate.delete(pList.get(0));
    }
}
