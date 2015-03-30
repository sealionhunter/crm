package com.ustcsoft.gs.crm.webui.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.exception.CRMException;
import com.ustcsoft.gs.crm.webui.system.dto.GroupManagerDto;
import com.ustcsoft.gs.crm.webui.system.dto.TreeDto;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;
import com.ustcsoft.gs.crm.webui.system.service.AuthorizationService;

/**
 * Authorization service implement junit test.
 * 
 * @author xujueyin
 * 
 */
public class AuthorizationServiceImplTest {

    /** get bean factory */
    protected static final BeanFactory CTX = new ClassPathXmlApplicationContext(
            "WEB-INF/context/applicationContext.xml");

    private static AuthorizationService service = null;

    /** hibernate operation */
    private static HibernateTemplate hibernate = null;

    private Map<String, Object> map = null;

    /** remark personal user or role */
    private int userMode = 0;

    /** authorization actionIDs */
    private String jsonString = null;

    /** role id */
    private int groupID = 0;

    private int idAuthorized = 0;

    @BeforeClass
    public static void beforeClass() {
        service = (AuthorizationService) CTX.getBean("authorizationService");
        // get hibernateTemplate and execute some operations
        hibernate = (HibernateTemplate) CTX.getBean("hibernateTemplate");
    }

    @Before
    public void setUp() {
        // clean
        clean("delete ActionGroupDto");
        clean("delete UserInfoDto where groupID>1");
        clean("delete GroupManagerDto");
        clean("delete ActionUserDto");
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * test save role or personal user authorization when only insert
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testSaveAuthorizationWhenOnlyInsert() throws Exception {
        userMode = 0;
        jsonString = "111, 11101, 11102, 11103, 11104, 11105, 11106";
        groupID = 2;
        // test save authorization
        service.saveAuthorization(userMode, jsonString, groupID, 0);
        List<Integer> list = hibernate
                .find("select group.actionID from ActionGroupDto group where group.groupID ="
                        + groupID);
        Assert.assertEquals(7, list.size());

        userMode = 1;
        idAuthorized = 12;
        service.saveAuthorization(userMode, jsonString, idAuthorized, 13);
        list = hibernate
                .find("select actionUser.actionID from ActionUserDto actionUser where actionUser.userID ="
                        + idAuthorized);
        Assert.assertEquals(7, list.size());
    }

    /**
     * test save role authorization when save some and delete some.
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testSaveAuthorizationWhenChangeRoleAuthorization() throws Exception {
        String preparedAuth = "111, 11101, 11104, 11105, 11106";
        userMode = 0;
        jsonString = "111, 11102, 11103, 11104";
        groupID = 2;
        // generate users
        UserInfoDto user1 = new UserInfoDto();
        user1.setGroupID(2);
        user1.setJobID("122");
        user1.setDepartmentID(1);
        user1.setPassword("000000");
        user1.setUserName("xujueyin");
        user1.setRealName("xujueyin");
        user1.setCompany("科大国创");
        user1.setEmail("jiechu2@163.com");
        // prepare role authorization
        service.saveAuthorization(userMode, preparedAuth, groupID, 0);
        // save a user
        hibernate.save(user1);
        // save user authorization
        service.saveAuthorization(1, preparedAuth, user1.getUserID(), 13);

        // test save role authorization
        service.saveAuthorization(userMode, jsonString, groupID, 0);
        List<Integer> list = hibernate
                .find("select group.actionID from ActionGroupDto group where group.groupID ="
                        + groupID);
        List<Integer> authList = hibernate
                .find("select actionUser.actionID from ActionUserDto actionUser where actionUser.userID ="
                        + user1.getUserID());
        Assert.assertEquals(4, list.size());
        Assert.assertEquals(4, authList.size());
    }

    /**
     * test save personal user authorization when save some and delete some.
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testSaveAuthorizationWhenChangeUserAuthorization() throws Exception {
        String preparedAuth = "111, 11101, 11104, 11105, 11106";
        userMode = 1;
        idAuthorized = 12;
        jsonString = "111, 11103, 11104, 112, 11201, 11202";
        // prepare personal user authorization
        service.saveAuthorization(userMode, preparedAuth, idAuthorized, 12);
        // test save personal user authorization
        service.saveAuthorization(userMode, jsonString, idAuthorized, 12);
        List<Integer> list = hibernate
                .find("select actionUser.actionID from ActionUserDto actionUser where actionUser.userID ="
                        + idAuthorized);
        Assert.assertEquals(6, list.size());
    }

    /**
     * test delete all authorization of role
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testSaveAuthorizationWhenDeleteAllAuthOfRole() throws Exception {
        String preparedAuth = "111, 11101, 11104, 11105, 11106";
        userMode = 0;
        jsonString = null;
        groupID = 2;
        // prepare role authorization
        service.saveAuthorization(userMode, preparedAuth, groupID, 12);
        // test delete all authorization of role
        service.saveAuthorization(userMode, jsonString, groupID, 12);
        List<Integer> list = hibernate
                .find("select group.actionID from ActionGroupDto group where group.groupID ="
                        + groupID);
        Assert.assertEquals(0, list.size());
    }

    /**
     * test get personal user.
     * 
     * @throws Exception
     */
    @Test
    public void testGetMembersWhenGetUser() throws Exception {
        // generate users
        UserInfoDto user1 = new UserInfoDto();
        user1.setGroupID(3);
        user1.setJobID("122");
        user1.setDepartmentID(1);
        user1.setPassword("000000");
        user1.setUserName("xujueyin");
        user1.setRealName("xujueyin");
        user1.setCompany("科大国创");
        user1.setEmail("jiechu2@163.com");

        UserInfoDto user2 = new UserInfoDto();
        user2.setGroupID(4);
        user2.setJobID("122");
        user2.setDepartmentID(1);
        user2.setProjectTeamID(2);
        user2.setPassword("000000");
        user2.setUserName("xujueyin");
        user2.setRealName("xujueyin");
        user2.setCompany("科大国创");
        user2.setEmail("jiechu2@163.com");

        UserInfoDto user3 = new UserInfoDto();
        user3.setGroupID(5);
        user3.setJobID("122");
        user3.setDepartmentID(1);
        user3.setProjectTeamID(2);
        user3.setPassword("000000");
        user3.setUserName("xujueyin");
        user3.setRealName("xujueyin");
        user3.setCompany("科大国创");
        user3.setEmail("jiechu2@163.com");

        UserInfoDto user4 = new UserInfoDto();
        user4.setGroupID(5);
        user4.setJobID("122");
        user4.setDepartmentID(1);
        user4.setProjectTeamID(3);
        user4.setPassword("000000");
        user4.setUserName("xujueyin");
        user4.setRealName("xujueyin");
        user4.setCompany("科大国创");
        user4.setEmail("jiechu2@163.com");
        // save users
        hibernate.save(user1);
        hibernate.save(user2);
        hibernate.save(user3);
        hibernate.save(user4);

        userMode = 1;
        map = service.getMembers(userMode, user1.getUserID());
        int number = ((List<?>) map.get("items")).size();
        Assert.assertEquals(3, number);

        map = service.getMembers(userMode, user2.getUserID());
        number = ((List<?>) map.get("items")).size();
        Assert.assertEquals(1, number);
    }

    /**
     * test get role
     * 
     * @throws Exception
     */
    @Test
    public void testGetMembersWhenGetRole() throws Exception {
        // insert roles to DB
        GroupManagerDto role1 = new GroupManagerDto();
        role1.setGroupID(1);
        role1.setGroupName("超级管理员");
        GroupManagerDto role2 = new GroupManagerDto();
        role2.setGroupID(2);
        role2.setGroupName("管理员");
        GroupManagerDto role3 = new GroupManagerDto();
        role3.setGroupID(3);
        role3.setGroupName("部长");
        GroupManagerDto role4 = new GroupManagerDto();
        role4.setGroupID(4);
        role4.setGroupName("项目经理");
        GroupManagerDto role5 = new GroupManagerDto();
        role5.setGroupID(5);
        role5.setGroupName("个人");
        hibernate.save(role1);
        hibernate.save(role2);
        hibernate.save(role3);
        hibernate.save(role4);
        hibernate.save(role5);

        userMode = 0;
        map = service.getMembers(userMode, role1.getGroupID());
        int number = ((List<?>) map.get("items")).size();
        Assert.assertEquals(4, number);

        map = service.getMembers(userMode, role2.getGroupID());
        number = ((List<?>) map.get("items")).size();
        Assert.assertEquals(3, number);
    }

    /**
     * test get operation authorization by rootID.
     * 
     * @throws Exception
     */
    @Test
    public void testGetOperationAuthorization() throws Exception {
        String preparedAuth = "112, 11201, 11202, 11203";
        groupID = 12;
        int rootID = 112;
        // prepare personal user authorization
        service.saveAuthorization(1, preparedAuth, groupID, 13);
        map = service.getOperationAuthorization(groupID, rootID);
        int number = ((List<?>) map.get("actionIDs")).size();
        Assert.assertEquals(3, number);
    }

    /**
     * test get role authorization for display.
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetMemberAuthorizationOfRole() throws Exception {
        jsonString = "111, 11101, 11102, 11103, 11104, 11105, 11106, 112, 11201, 11202, 11203";
        userMode = 0;
        groupID = 2;
        // prepared role authorization
        service.saveAuthorization(userMode, jsonString, groupID, 0);
        // test getMemberAuthorization
        map = service.getMemberAuthorization(userMode, groupID);
        List<Integer> menu = (List<Integer>) map.get("menuNodes");
        List<Integer> operation = (List<Integer>) map.get("operationNodes");
        Assert.assertEquals(2, menu.size());
        Assert.assertEquals(9, operation.size());
    }

    /**
     * test get personal user authorization for display.
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetMemberAuthorizationOfUser() throws Exception {
        jsonString = "111, 11101, 11102, 11103, 11104, 11105, 11106, 112, 11201, 11202, 11203";
        userMode = 1;
        groupID = 12;
        // prepared role authorization
        service.saveAuthorization(userMode, jsonString, groupID, 13);
        // test getMemberAuthorization
        map = service.getMemberAuthorization(userMode, groupID);
        List<Integer> menu = (List<Integer>) map.get("menuNodes");
        List<Integer> operation = (List<Integer>) map.get("operationNodes");
        Assert.assertEquals(2, menu.size());
        Assert.assertEquals(9, operation.size());
    }

    /**
     * get tree of role.
     * 
     * @throws Exception
     */
    @Test
    public void testGetTreeOfGroup() throws Exception {
        userMode = 0;
        jsonString = "111, 11101, 11102, 11103, 11104, 11105, 11106, 112, 11201, 11202";
        int groupID = 2;
        // prepared role authorization
        service.saveAuthorization(userMode, jsonString, groupID, 0);
        // test get tree
        List<TreeDto> menu = service.getTree(userMode, "menu", groupID);
        List<TreeDto> operation = service.getTree(userMode, "operation", groupID);
        int numMenu = menu.get(0).getChildren().get(0).getChildren().size();
        int numOperation1 = operation.get(0).getChildren().size();
        int numOperation2 = operation.get(1).getChildren().size();
        Assert.assertEquals(2, numMenu);
        Assert.assertEquals(6, numOperation1);
        Assert.assertEquals(2, numOperation2);
    }

    /**
     * get tree of personal user.
     * 
     * @throws Exception
     */
    @Test
    public void testGetTreeOfUser() throws Exception {
        userMode = 1;
        jsonString = "111, 11101, 11102, 11103, 11104, 11105, 11106, 112, 11201, 11202";
        int userID = 12;
        // prepared role authorization
        service.saveAuthorization(userMode, jsonString, userID, 13);
        // test get tree
        List<TreeDto> menu = service.getTree(userMode, "menu", userID);
        List<TreeDto> operation = service.getTree(userMode, "operation", userID);
        int numMenu = menu.get(0).getChildren().get(0).getChildren().size();
        int numOperation1 = operation.get(0).getChildren().size();
        int numOperation2 = operation.get(1).getChildren().size();
        Assert.assertEquals(2, numMenu);
        Assert.assertEquals(6, numOperation1);
        Assert.assertEquals(2, numOperation2);
    }

    /**
     * clean tables of the database named CRM when test.
     * 
     * @param hql
     *            String hql statement
     */
    private void clean(final String hql) {
        hibernate.execute(new HibernateCallback<Integer>() {
            @Override
            public Integer doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(hql);
                return query.executeUpdate();
            }
        });
    }

    /**
     * get tree of system configer
     * 
     * @throws Exception
     */
    @Test
    public void testGetTreeOfSystemConfiger() throws Exception {
        userMode = 2;
        int groupID = 0;
        // test get tree
        List<TreeDto> menu = service.getTree(userMode, "menu", groupID);
        int size = menu.size();
        Assert.assertEquals(7, size);
    }

    /**
     * test save super manager's authorization
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testSaveAuthorizationOfSuperManager() throws Exception {
        String preparedAuth = "111, 11101, 11104, 11105, 11106";
        userMode = 2;
        jsonString = null;
        idAuthorized = 1;
        // prepare role authorization
        service.saveAuthorization(userMode, preparedAuth, idAuthorized, 0);
        List<Integer> list = hibernate
                .find("select group.actionID from ActionGroupDto group where group.groupID ="
                        + idAuthorized);
        // test delete all authorization of role
        service.saveAuthorization(userMode, null, idAuthorized, 0);
        Assert.assertEquals(5, list.size());
        userMode = 3;
        try {
            service.saveAuthorization(userMode, null, idAuthorized, 0);
        } catch (Exception e) {
            Assert.assertEquals(true, e instanceof CRMException);
        }
    }
}