package com.ustcsoft.gs.crm.webui.service;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.index.dto.WorkDto;
import com.ustcsoft.gs.crm.webui.index.service.WorkService;
import com.ustcsoft.gs.crm.webui.mail.bean.MailInfoBean;
import com.ustcsoft.gs.crm.webui.mail.bean.MailSenderInfoBean;
import com.ustcsoft.gs.crm.webui.mail.constant.MailConstant;
import com.ustcsoft.gs.crm.webui.mail.service.MailService;
import com.ustcsoft.gs.crm.webui.mail.service.impl.MailServiceImpl;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;
import com.ustcsoft.gs.crm.webui.system.service.UserInfoService;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

/**
 * Test the class MailServiceImpl
 * 
 * @author libaoshan
 * 
 */
public class MailServiceImplTest extends CRMTest {

    private static MailService mailService = null;

    private static List<MailInfoBean> mailInfoBeans = null;

    private static MailSenderInfoBean mailSenderInfoBean = null;

    private static WorkDto workDto = null;

    @BeforeClass
    public static void beforeClass() throws CRMDBException {

        mailService = new MailServiceImpl();
        mailInfoBeans = new ArrayList<MailInfoBean>();
        mailSenderInfoBean = new MailSenderInfoBean();
        mailSenderInfoBean.setSubject("勉強します");
        mailSenderInfoBean.setFromAddress("mail.ustcsoft.com");
        mailSenderInfoBean.setMailServerHost("mail.ustcsoft.com");
        mailSenderInfoBean.setMailServerPort("25");
        mailSenderInfoBean.setValidate(false);
        mailSenderInfoBean.setToAddress("libaoshan@ustcsoft.com");
        mailSenderInfoBean.setContent("test");

        UserInfoService userInfoService = (UserInfoService) CTX.getBean("userInfoService");
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setUserID(2);
        userInfoDto.setGroupID(5);
        userInfoDto.setJobID("1104xx");
        userInfoDto.setCompany("科大国创");
        userInfoDto.setUserName("libaoshan");
        userInfoDto.setRealName("李宝山");
        userInfoDto.setPassword("123");
        userInfoDto.setDepartmentID(1);
        userInfoDto.setProjectTeamID(4);
        userInfoDto.setEmail("libaoshan@ustcsoft.com");
        userInfoService.updateUser(userInfoDto);

        WorkService workService = (WorkService) CTX.getBean("workService");
        workDto = new WorkDto();
        workDto.setWorkID(1);
        workDto.setUserID(2);
        workDto.setCustomerID(1);
        workDto.setTeamFlag(0);
        workDto.setTheme("中共");
        workDto.setAssignee("01");
        workDto.setPriority("01");
        workDto.setWorkType("01");
        workDto.setCompletion("01");
        String date = new CRMUtils().getDate();
        workDto.setStartTime(date);
        workDto.setEndTime(date);
        workDto.setWorkDetail("中共十八大");
        workDto.setDescriptions("zhonggong十八大を勉強します");
        workDto.setIsReaded(false);
        workDto.setIsMailInformed(true);
        workDto.setIsAbolished(false);
        workService.updateWork(workDto);

    }

    @AfterClass
    public static void afterClass() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test send email
     */
    @Test
    public void testSendTextMail() {
        boolean IsSend = mailService.sendTextMail(mailSenderInfoBean);
        Assert.assertEquals(IsSend, true);
    }

    /**
     * Test getMailInfo
     * 
     * @throws CRMDBException
     */
    @Test
    public void testGetMailInfo() throws CRMDBException {
        mailInfoBeans = mailService.getMailInfo(0);
        Assert.assertEquals(mailInfoBeans.size() > 0, true);
    }

    /**
     * Test setIsMailed to set isMailInformed false
     * 
     * @throws CRMDBException
     */
    @Test
    public void testSetIsMailed() throws CRMDBException {
        mailInfoBeans = mailService.getMailInfo(0);
        List<String> workIDs = new ArrayList<String>();
        for (int i = 0; i < mailInfoBeans.size(); i++) {
            workIDs.add(mailInfoBeans.get(0).getWorkID() + "");
        }
        mailService.setIsMailed(workIDs);
        mailInfoBeans = mailService.getMailInfo(0);
        Assert.assertEquals(mailInfoBeans.size() == 0, true);
    }

    /**
     * Test setMailContent
     */
    @Test
    public void testSetMailContent() {
        String mailContent = "";
        MailInfoBean mailInfoBean = new MailInfoBean();
        mailInfoBean.setCompletionName("未完成");
        mailInfoBean.setCustomerName(null);
        mailInfoBean.setDescriptions("1");
        mailInfoBean.setEmail("123@qq.com");
        mailInfoBean.setEndTime(null);
        mailInfoBean.setStartTime("2012-11-12 00:00");
        mailInfoBean.setPriorityName("高");
        mailInfoBean.setTheme("勉強します");
        mailInfoBean.setUserName("libaoshan");
        mailInfoBean.setWorkDetail("<tr></tr>");
        mailInfoBean.setWorkID(1);
        mailInfoBean.setWorkTypeName("Coding");
        mailContent = mailService.setMailContent(mailInfoBean);
        int flag = mailContent.indexOf(MailConstant.CUSTOMERNAME);
        Assert.assertEquals(flag, -1);
    }

}
