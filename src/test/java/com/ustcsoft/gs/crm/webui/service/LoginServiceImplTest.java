/**
 * 
 */
package com.ustcsoft.gs.crm.webui.service;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;
import com.ustcsoft.gs.crm.webui.system.service.LoginService;
import com.ustcsoft.gs.crm.webui.system.service.UserInfoService;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

/**
 * @author zhouzhou
 * 
 */
public class LoginServiceImplTest extends CRMTest {
    private static LoginService test = null;
    private static UserInfoService userInfoService = null;
    private static int userID = 0;
    private static String userName = "aaaaaa";
    private static String password = "000000";

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        test = (LoginService) CTX.getBean("loginService");
        userInfoService = (UserInfoService) CTX.getBean("userInfoService");
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setPassword(password);
        userInfoDto.setUserName(userName);
        userInfoDto.setCompany("科大国创");
        userInfoDto.setEmail("651666433@qq.com");
        userInfoDto.setGroupID(1);
        userInfoDto.setIsAbolished(false);
        userInfoDto.setJobID("1");
        userInfoDto.setRealName("zhouzhou");
        userInfoService.updateUser(userInfoDto);
        userID = userInfoDto.getUserID();
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        userInfoService.deleteUser("" + userID);
    }

    /**
     * Test method for
     * {@link com.ustcsoft.gs.crm.webui.system.service.impl.LoginServiceImpl#findByUsernamePassword(java.lang.String, java.lang.String)}
     * .
     * 
     * @throws CRMDBException
     */
    @Test
    public void testFindByUsernamePassword() throws CRMDBException {
        UserInfoDto user = test.findByUsernamePassword(userName, password);
        if (user == null) {
            fail("user is null");
        } else {
            Assert.assertEquals(userName, user.getUserName());
            Assert.assertEquals(password, user.getPassword());
        }
    }

}
