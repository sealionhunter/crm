/**
 * 
 */
package com.ustcsoft.gs.crm.webui.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.index.bean.WorkBean;
import com.ustcsoft.gs.crm.webui.index.bean.WorkSearchBean;
import com.ustcsoft.gs.crm.webui.index.dto.WorkDto;
import com.ustcsoft.gs.crm.webui.index.service.WorkService;
import com.ustcsoft.gs.crm.webui.mail.bean.MailInfoBean;
import com.ustcsoft.gs.crm.webui.mail.service.MailService;
import com.ustcsoft.gs.crm.webui.mail.service.impl.MailServiceImpl;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

/**
 * test class WorkServiceImpl
 * 
 * @author shenkaichuan
 * 
 */
public class WorkServiceImplTest extends CRMTest {
    /** ContactInfoService test */
    private static WorkService test;
    private List<WorkBean> workBeans = null;
    private static WorkSearchBean workSearchBean = new WorkSearchBean();
    private int pageSize = 15;
    private static int workId_person = 0;
    private static int workId_team = 0;
    private static int userID = 1;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        test = (WorkService) CTX.getBean("workService");
        workSearchBean.setUserID(userID);
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link com.ustcsoft.gs.crm.webui.index.service.impl.WorkServiceImpl#updateWork(com.ustcsoft.gs.crm.webui.index.dto.WorkDto)}
     * 
     * @throws CRMDBException
     */
    @Test
    public final void testUpdateWork_add_personWork() throws CRMDBException {
        WorkDto workDto = new WorkDto();
        int teamFlag = 100;
        workDto.setUserID(userID);
        workDto.setTheme("天空不曾留下鸟的痕迹，但是我已飞过");
        workDto.setCustomerID(2);
        workDto.setCompletion("01");
        workDto.setPriority("01");
        workDto.setWorkType("01");
        workDto.setStartTime("2012-11-09 17:36:05");
        workDto.setEndTime("2012-11-21 17:36:01");
        workDto.setIsMailInformed(true);
        workDto.setWorkDetail("授人玫瑰，手有余香");
        workDto.setDescriptions("水滴虽小，却可以折射出太阳的光彩");
        test.updateWork(workDto);
        workId_person = workDto.getWorkID();
        teamFlag = workDto.getTeamFlag();
        Assert.assertNotSame(0, workId_person);
        Assert.assertEquals(0, teamFlag);
    }

    /**
     * Test method for
     * {@link com.ustcsoft.gs.crm.webui.index.service.impl.WorkServiceImpl#searchOrGetAllWork(int, com.ustcsoft.gs.crm.webui.index.bean.WorkSearchBean, int, int)}
     * 
     * @throws CRMDBException
     * 
     */
    @Test
    public final void testSearchOrGetAllWork_list() throws CRMDBException, ParseException {
        // searchFlag is 0
        super.map = test.searchOrGetAllWork(searchFlag[0], workSearchBean, currpage, pageSize);
        int total = Integer.parseInt(map.get("total").toString());
        Assert.assertNotNull(map);
        Assert.assertTrue(total >= 1);
    }

    /**
     * Test method for
     * {@link com.ustcsoft.gs.crm.webui.index.service.impl.WorkServiceImpl#updateWork(com.ustcsoft.gs.crm.webui.index.dto.WorkDto)}
     * 
     * @throws CRMDBException
     */
    @Test
    public final void testUpdateWork_add_teamWork() throws CRMDBException {
        WorkDto workDto1 = new WorkDto();
        workDto1.setUserID(userID);
        workDto1.setTheme("我爱你中国");
        workDto1.setCustomerID(2);
        workDto1.setCompletion("01");
        workDto1.setPriority("01");
        workDto1.setWorkType("02");
        workDto1.setStartTime("2012-11-09 17:36:05");
        workDto1.setEndTime("2012-11-21 17:36:01");
        workDto1.setIsMailInformed(true);
        workDto1.setTeamFlag(-1);
        workDto1.setAssignees(new int[] { 2, 3 });
        workDto1.setWorkDetail("完成功能模块的设计");
        workDto1.setDescriptions("大家一定要在规定时间内完成，加油！");
        super.map = test.searchOrGetAllWork(searchFlag[0], workSearchBean, currpage, pageSize);
        int totalBefore = Integer.parseInt(map.get("total").toString());
        test.updateWork(workDto1);
        workId_team = workDto1.getWorkID();
        super.map = test.searchOrGetAllWork(searchFlag[0], workSearchBean, currpage, pageSize);
        int totalEnd = Integer.parseInt(map.get("total").toString());
        Assert.assertNotSame(0, workId_team);
        Assert.assertEquals(totalEnd - totalBefore, 1);
    }

    /**
     * Test method for
     * {@link com.ustcsoft.gs.crm.webui.index.service.impl.WorkServiceImpl#updateWork(com.ustcsoft.gs.crm.webui.index.dto.WorkDto)}
     * 
     * @throws CRMDBException
     */
    @Test
    public final void testUpdateWork_edit_teamWork() throws CRMDBException {
        WorkDto workDto2 = new WorkDto();
        workDto2.setUserID(userID);
        workDto2.setWorkID(workId_team);
        workDto2.setTheme("我爱你中国编辑");
        workDto2.setCustomerID(2);
        workDto2.setCompletion("01");
        workDto2.setPriority("01");
        workDto2.setWorkType("02");
        workDto2.setStartTime("2012-11-09 17:36:05");
        workDto2.setEndTime("2012-11-21 17:36:01");
        workDto2.setIsMailInformed(true);
        workDto2.setTeamFlag(-1);
        workDto2.setAssignees(new int[] { 2, 3 });
        workDto2.setWorkDetail("完成功能模块的设计");
        workDto2.setDescriptions("大家一定要在规定时间内完成，加油！");
        super.map = test.searchOrGetAllWork(searchFlag[0], workSearchBean, currpage, pageSize);
        int totalBefore = Integer.parseInt(map.get("total").toString());
        test.updateWork(workDto2);
        super.map = test.searchOrGetAllWork(searchFlag[0], workSearchBean, currpage, pageSize);
        int totalEnd = Integer.parseInt(map.get("total").toString());
        Assert.assertEquals(totalEnd - totalBefore, 0);
    }

    /**
     * Test method for
     * {@link com.ustcsoft.gs.crm.webui.index.service.impl.WorkServiceImpl#searchOrGetAllWork(int, com.ustcsoft.gs.crm.webui.index.bean.WorkSearchBean, int, int)}
     * 
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Test
    public final void testSearchOrGetAllWork_simple() throws CRMDBException, ParseException {
        // searchFlag is 1
        workSearchBean.setSearchText("我爱你");
        super.map = test.searchOrGetAllWork(searchFlag[1], workSearchBean, currpage, pageSize);
        Assert.assertNotNull(map);
        List<Map<String, Object>> workBeans = (List<Map<String, Object>>) map.get("items");
        String searchText = workSearchBean.getSearchText();
        boolean flag = false;
        for (Map<String, Object> workBean : workBeans) {
            if (((String) workBean.get("customerName")).contains(searchText)) {
                flag = flag || true;
            }
            if (((String) workBean.get("theme")).contains(searchText)) {
                flag = flag || true;
            }
            if (((String) workBean.get("workType")).contains(searchText)) {
                flag = flag || true;
            }
            if (((String) workBean.get("completion")).contains(searchText)) {
                flag = flag || true;
            }
            if (((String) workBean.get("priority")).contains(searchText)) {
                flag = flag || true;
            }
            if (((String) workBean.get("endTime")).contains(searchText)) {
                flag = flag || true;
            }
            Assert.assertTrue(flag);
        }
    }

    /**
     * Test method for
     * {@link com.ustcsoft.gs.crm.webui.index.service.impl.WorkServiceImpl#searchOrGetAllWork(int, com.ustcsoft.gs.crm.webui.index.bean.WorkSearchBean, int, int)}
     * 
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Test
    public final void testSearchOrGetAllWork_super() throws CRMDBException, ParseException {
        // searchFlag is 2
        workSearchBean.setTheme("我爱你中国编辑");
        workSearchBean.setCustomerName("system");
        workSearchBean.setCompletion("01");
        workSearchBean.setPriority("01");
        workSearchBean.setWorkType("02");
        super.map = test.searchOrGetAllWork(searchFlag[2], workSearchBean, currpage, pageSize);
        Assert.assertNotNull(map);
        List<Map<String, Object>> workBeans = (List<Map<String, Object>>) map.get("items");
        for (Map<String, Object> workBean : workBeans) {
            Assert.assertEquals(workBean.get("completion"), workSearchBean.getCompletion());
            Assert.assertEquals(workBean.get("priority"), workSearchBean.getPriority());
            Assert.assertEquals(workBean.get("workType"), workSearchBean.getWorkType());
            Assert.assertTrue(((String) workBean.get("theme")).contains(workSearchBean.getTheme()
                    .substring(1, workSearchBean.getTheme().length() - 1)));
            Assert.assertTrue(((String) workBean.get("customerName")).contains(workSearchBean
                    .getCustomerName().substring(1, workSearchBean.getCustomerName().length() - 1)));
        }
    }

    /**
     * Test method for
     * {@link com.ustcsoft.gs.crm.webui.index.service.impl.WorkServiceImpl#searchOrGetAllWork(int, com.ustcsoft.gs.crm.webui.index.bean.WorkSearchBean, int, int)}
     * .
     * 
     * @throws CRMDBException
     * 
     * @throws ParseException
     *             date parse fail
     */
    @SuppressWarnings("unchecked")
    @Test
    public final void testSearchOrGetAllWork() throws CRMDBException, ParseException {
        // searchFlag is 3
        date = "2012-11-07 08:30";
        map = test.searchOrGetAllWork(searchFlag[3], workSearchBean, currpage, pageSize);
        Assert.assertNotNull(map);
        workBeans = (List<WorkBean>) map.get("items");
        for (int i = 0; i < workBeans.size(); i++) {
            boolean startjudge = dateBefore(date, workBeans.get(i).getStartTime(), 0);
            boolean endjudge = dateAfter(date, workBeans.get(i).getEndTime());
            boolean userIDjudge = userID == workBeans.get(i).getUserID();
            Assert.assertTrue(startjudge && endjudge && userIDjudge);
        }
    }

    /**
     * Test method for
     * {@link com.ustcsoft.gs.crm.webui.index.service.impl.WorkServiceImpl#getTask(int, int, int, java.lang.String, int)}
     * .
     * 
     * @throws CRMDBException
     * @throws ParseException
     *             date parse fail
     */
    @SuppressWarnings("unchecked")
    @Test
    public final void testGetTask() throws CRMDBException, ParseException {
        List<WorkDto> workDtos = null;

        // searchFlag is 0
        date = "2012-11-07 00:00";
        map = test.getTask(0, userID, searchFlag[0], date, limit);
        Assert.assertNotNull(map);
        workDtos = (List<WorkDto>) map.get("items");
        for (int i = 0; i < workDtos.size(); i++) {
            boolean startjudge = dateBefore(date, workDtos.get(i).getStartTime(), 1);
            boolean endjudge = dateAfter(date, workDtos.get(i).getEndTime());
            boolean userIDjudge = userID == workDtos.get(i).getUserID();
            Assert.assertTrue(startjudge && endjudge && userIDjudge);
        }

        // searchFlag is 1
        date = "2012-11-08 08:00";
        map = test.getTask(0, userID, searchFlag[1], date, limit);
        Assert.assertNotNull(map);
        workDtos = (List<WorkDto>) map.get("items");
        for (int i = 0; i < workDtos.size(); i++) {
            boolean startjudge = dateBefore(date, workDtos.get(i).getStartTime(), 1);
            boolean endjudge = dateAfter(date, workDtos.get(i).getEndTime());
            boolean userIDjudge = userID == workDtos.get(i).getUserID();
            Assert.assertTrue(startjudge && endjudge && userIDjudge);
        }

        // searchFlag is 1
        date = "2012-11-08 08";
        thrown.expect(CRMDBException.class);
        map = test.getTask(0, userID, searchFlag[1], date, limit);
        Assert.assertNotNull(map);
        workDtos = (List<WorkDto>) map.get("items");
        for (int i = 0; i < workDtos.size(); i++) {
            boolean startjudge = dateBefore(date, workDtos.get(i).getStartTime(), 1);
            boolean endjudge = dateAfter(date, workDtos.get(i).getEndTime());
            boolean userIDjudge = userID == workDtos.get(i).getUserID();
            Assert.assertTrue(startjudge && endjudge && userIDjudge);
        }
    }

    /**
     * Test method for setting isReaded true
     * {@link com.ustcsoft.gs.crm.webui.index.service.impl.WorkServiceImpl#readMessage(java.lang.String)}
     * .
     * 
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Test
    public final void testReadMessage() throws CRMDBException {
        map = test.getTask(1, 2, searchFlag[3], date, 10);
        Object start = map.get("total");
        List<WorkDto> workDtos = (List<WorkDto>) map.get("items");
        String workID = workDtos.get(0).getWorkID() + "";
        int startTotal = Integer.parseInt(start.toString());
        test.readMessage(workID);
        map = test.getTask(1, 2, searchFlag[3], date, 10);
        Object end = map.get("total");
        int endTotal = Integer.parseInt(end.toString());
        Assert.assertEquals(startTotal - endTotal, 1);
    }

    /**
     * Test sendMail
     * 
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Test
    public final void testSendMail() throws CRMDBException {
        map = test.getTask(1, 2, searchFlag[3], date, 10);
        List<WorkDto> workDtos = (List<WorkDto>) map.get("items");
        int workID = workDtos.get(0).getWorkID() - 1;
        MailService mailService = new MailServiceImpl();
        List<MailInfoBean> mailInfoBeans = mailService.getMailInfo(workID);
        int start = mailInfoBeans.size();
        test.sendMail(workID + "");
        mailInfoBeans = mailService.getMailInfo(workID);
        int end = mailInfoBeans.size();
        Assert.assertEquals(start - end >= 0, true);
    }

    /**
     * Test method for
     * {@link com.ustcsoft.gs.crm.webui.index.service.impl.WorkServiceImpl#deleteWork(java.lang.String)}
     * .
     */
    @Test
    public final void testDeleteWork() throws CRMDBException {
        int start = Integer.parseInt(test
                .searchOrGetAllWork(searchFlag[0], workSearchBean, currpage, pageSize).get("total")
                .toString());
        test.deleteWork(String.valueOf(workId_person));
        test.deleteWork(String.valueOf(workId_team));
        int end = Integer.parseInt(test
                .searchOrGetAllWork(searchFlag[0], workSearchBean, currpage, pageSize).get("total")
                .toString());
        Assert.assertTrue(start - end == 2);
    }

    /**
     * Test exception when 'startTime' = 'sss'
     * 
     * @throws CRMDBException
     */
    @Test(expected = CRMDBException.class)
    public void testUpdateWork_add_exception() throws CRMDBException {
        WorkDto workDto = new WorkDto();
        workDto.setUserID(userID);
        workDto.setTheme("天空不曾留下鸟的痕迹，但是我已飞过");
        workDto.setCustomerID(2);
        workDto.setCompletion("01");
        workDto.setPriority("01");
        workDto.setWorkType("01");
        workDto.setStartTime("sss");
        workDto.setEndTime("2012-11-21 17:36:01");
        workDto.setIsMailInformed(true);
        workDto.setWorkDetail("授人玫瑰，手有余香");
        workDto.setDescriptions("水滴虽小，却可以折射出太阳的光彩");
        test.updateWork(workDto);
    }

    /**
     * Test exception when 'searchDateStart' = 'sss'
     * 
     * @throws CRMDBException
     */
    @Test(expected = CRMDBException.class)
    public void testSearchOrGetAllWork_super_exception() throws CRMDBException {
        workSearchBean.setTheme("我爱你中国编辑");
        workSearchBean.setCustomerName("system");
        workSearchBean.setCompletion("01");
        workSearchBean.setPriority("01");
        workSearchBean.setWorkType("02");
        workSearchBean.setSearchDateStart("sss");
        super.map = test.searchOrGetAllWork(searchFlag[2], workSearchBean, currpage, pageSize);
    }

    /**
     * Test exception when 'workID' = 'sss'
     * 
     * @throws CRMDBException
     */
    @Test(expected = CRMDBException.class)
    public void testDeleteWork_exception() throws CRMDBException {
        test.deleteWork(String.valueOf("sss"));
    }
}
