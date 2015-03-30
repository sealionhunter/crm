package com.ustcsoft.gs.crm.webui.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.ContactTrackListBean;
import com.ustcsoft.gs.crm.webui.customer.bean.ContactTrackSearchBean;
import com.ustcsoft.gs.crm.webui.customer.service.ContactHistoryService;
import com.ustcsoft.gs.crm.webui.customer.service.ContactTrackService;
import com.ustcsoft.gs.crm.webui.customer.service.impl.ContactTrackServiceImpl;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

/**
 * @author zhouzhou
 * 
 */
public class ContactTrackServiceImplTest extends CRMTest {
    private static ContactTrackService test = null;
    private static ContactHistoryService test1 = null;
    private ContactTrackSearchBean contactTrackSearchBean = new ContactTrackSearchBean();
    private ContactTrackListBean contactTrackListBean = new ContactTrackListBean();
    private int limit = 25;
    List<?> list = null;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public final static void setUpBeforeClass() throws Exception {
        test = (ContactTrackService) CTX.getBean("contactTrackService");
        test1 = (ContactHistoryService) CTX.getBean("contactHistoryService");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {

    }

    /**
     * Test method for
     * {@link com.ustcsoft.gs.crm.webui.customer.service.ContactTrackService#getAllContactTrack(int, com.ustcsoft.gs.crm.webui.customer.bean.ContactTrackSearchBean, int,int,int)}
     * .
     */
    @Test
    public final void testGetAllContactTrack() throws CRMDBException {
        contactTrackSearchBean.setSearchText("");

        // flag = 0 ，一览测试
        map = test.getAllContactTrack(searchFlag[0], contactTrackSearchBean, currpage, limit,
                userID);
        int count = Integer.parseInt(map.get("total").toString());
        list = (List<?>) map.get("items");
        boolean bool = count >= list.size();
        Assert.assertEquals(bool, true);

        // 异常测试
        contactTrackSearchBean.setPlanDateMax("hello");
        try {
            map = test.getAllContactTrack(searchFlag[2], contactTrackSearchBean, currpage, limit,
                    userID);
            fail("No exception thrown.");
        } catch (CRMDBException e) {
            Assert.assertTrue(e instanceof CRMDBException);
        }
    }

    /**
     * Test method for
     * {@link com.ustcsoft.gs.crm.webui.customer.service.ContactTrackService#updateContactTrack(com.ustcsoft.gs.crm.webui.customer.bean.ContactTrackListBean,int,int,int)}
     * .
     * 
     * @throws CRMDBException
     */
    @Test
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public final void testUpdateContactTrackAdd() throws CRMDBException {
        ContactTrackServiceImpl contactTrackServiceImpl = new ContactTrackServiceImpl();
        contactTrackServiceImpl.setSourceInfoService(null);
        contactTrackSearchBean.setSearchText("");
        contactTrackListBean.setCustomerID(1);
        contactTrackListBean.setWeContact(3);
        contactTrackListBean.setOppositeContact(1);
        contactTrackListBean.setContactType("000600010001");
        contactTrackListBean.setContactWay("000600020002");
        contactTrackListBean.setContactContent("soft");
        contactTrackListBean.setIsAbolished(false);
        contactTrackListBean.setPlanDateBegin("2013-11-18");
        contactTrackListBean.setRemarks("zkk");
        // 测试0
        // 测试添加记录
        int start = Integer.parseInt(test
                .getAllContactTrack(searchFlag[0], contactTrackSearchBean, currpage, limit, 3)
                .get("total").toString());
        test.updateContactTrack(contactTrackListBean, submitFlag[1], userID, 0);
        int end = Integer.parseInt(test
                .getAllContactTrack(searchFlag[0], contactTrackSearchBean, currpage, limit, 3)
                .get("total").toString());
        Assert.assertTrue(end - start == 1);
        // 测试1
        contactTrackListBean.setIfContact(true);
        contactTrackListBean.setStrategy("hello");
        contactTrackListBean.setRealityDateBegin("2012-10-18");
        contactTrackListBean.setRealityDateEnd("2013-10-10");
        contactTrackListBean.setUserFeedbackInformation("hello");
        contactTrackListBean.setCheckResult(true);
        contactTrackListBean.setChanceType(1);
        contactTrackListBean.setEventName("shijian1");
        contactTrackListBean.setFindChanceContent("11111");
        // 测试添加记录1
        int start1 = Integer.parseInt(test1
                .searchOrGetAllContactHistory(searchFlag[0], null, currpage, limit, 3).get("total")
                .toString());
        test.updateContactTrack(contactTrackListBean, submitFlag[2], userID, 0);
        int end1 = Integer.parseInt(test1
                .searchOrGetAllContactHistory(searchFlag[0], null, currpage, limit, 3).get("total")
                .toString());
        Assert.assertTrue(end1 - start1 == 1);
        // 测试添加记录2
        int start6 = Integer.parseInt(test1
                .searchOrGetAllContactHistory(searchFlag[0], null, currpage, limit, 3).get("total")
                .toString());
        test.updateContactTrack(contactTrackListBean, submitFlag[2], userID, 1);
        int end6 = Integer.parseInt(test1
                .searchOrGetAllContactHistory(searchFlag[0], null, currpage, limit, 3).get("total")
                .toString());
        Assert.assertTrue(end6 - start6 == 1);

        // 测试2
        contactTrackListBean.setChanceType(2);
        contactTrackListBean.setEventName("shijian2");
        contactTrackListBean.setCheckResult(true);
        contactTrackListBean.setCheckChanceContent("22222");
        // 测试添加记录
        int start2 = Integer.parseInt(test1
                .searchOrGetAllContactHistory(searchFlag[0], null, currpage, limit, 3).get("total")
                .toString());
        test.updateContactTrack(contactTrackListBean, submitFlag[2], userID, 1);
        int end2 = Integer.parseInt(test1
                .searchOrGetAllContactHistory(searchFlag[0], null, currpage, limit, 3).get("total")
                .toString());
        Assert.assertTrue(end2 - start2 == 1);
        // 测试3
        contactTrackListBean.setChanceType(1);
        contactTrackListBean.setEventName("shijian3");
        contactTrackListBean.setFindChanceContent("11111");
        test.updateContactTrack(contactTrackListBean, submitFlag[2], 3, 0);
        contactTrackListBean.setChanceType(2);
        contactTrackListBean.setCheckResult(false);
        contactTrackListBean.setCheckChanceContent("22222");
        // 测试添加记录
        int start3 = Integer.parseInt(test1
                .searchOrGetAllContactHistory(searchFlag[0], null, currpage, limit, 3).get("total")
                .toString());
        test.updateContactTrack(contactTrackListBean, submitFlag[2], userID, 2);
        int end3 = Integer.parseInt(test1
                .searchOrGetAllContactHistory(searchFlag[0], null, currpage, limit, 3).get("total")
                .toString());
        Assert.assertTrue(end3 - start3 == 1);
        // 测试4
        contactTrackListBean.setChanceType(3);
        contactTrackListBean.setEventName("shijian");
        contactTrackListBean.setFindChanceContent("11111");
        contactTrackListBean.setCheckChanceContent("2222");
        contactTrackListBean.setCheckResult(true);

        // 测试添加记录1
        int start4 = Integer.parseInt(test1
                .searchOrGetAllContactHistory(searchFlag[0], null, currpage, limit, 3).get("total")
                .toString());
        test.updateContactTrack(contactTrackListBean, submitFlag[2], userID, 0);
        int end4 = Integer.parseInt(test1
                .searchOrGetAllContactHistory(searchFlag[0], null, currpage, limit, 3).get("total")
                .toString());
        Assert.assertTrue(end4 - start4 == 1);

        // 测试添加记录2
        int start5 = Integer.parseInt(test1
                .searchOrGetAllContactHistory(searchFlag[0], null, currpage, limit, 3).get("total")
                .toString());
        test.updateContactTrack(contactTrackListBean, submitFlag[2], userID, 1);
        int end5 = Integer.parseInt(test1
                .searchOrGetAllContactHistory(searchFlag[0], null, currpage, limit, 3).get("total")
                .toString());
        Assert.assertTrue(end5 - start5 == 1);
        // 测试7
        contactTrackListBean.setIfContact(true);
        contactTrackListBean.setStrategy("hello");
        contactTrackListBean.setRealityDateBegin("2012-10-18");
        contactTrackListBean.setRealityDateEnd("2013-10-10");
        contactTrackListBean.setUserFeedbackInformation("hello");
        contactTrackListBean.setCheckResult(true);
        contactTrackListBean.setChanceType(1);
        contactTrackListBean.setEventName("shijian1");
        contactTrackListBean.setFindChanceContent("11111");
        test.updateContactTrack(contactTrackListBean, submitFlag[2], 3, 0);
        contactTrackListBean.setChanceType(3);
        // 测试添加记录
        int start7 = Integer.parseInt(test1
                .searchOrGetAllContactHistory(searchFlag[0], null, currpage, limit, 3).get("total")
                .toString());
        test.updateContactTrack(contactTrackListBean, submitFlag[2], 3, 4);
        int end7 = Integer.parseInt(test1
                .searchOrGetAllContactHistory(searchFlag[0], null, currpage, limit, 3).get("total")
                .toString());
        Assert.assertTrue(end7 - start7 == 1);
        // 测试异常
        contactTrackListBean.setRealityDateBegin("hello");
        try {
            test.updateContactTrack(contactTrackListBean, submitFlag[1], 3, 0);
            fail("No exception thrown.");
        } catch (CRMDBException e) {
            Assert.assertTrue(e instanceof CRMDBException);
        }
    }

    /**
     * Test method for
     * {@link com.ustcsoft.gs.crm.webui.customer.service.ContactTrackService#deleteContactTrack(java.lang.String)}
     * .
     * 
     * @throws CRMDBException
     */
    @Test
    public final void testDeleteContactTrack() throws CRMDBException {
        // 删除测试
        int start = Integer.parseInt(test
                .getAllContactTrack(searchFlag[0], contactTrackSearchBean, 1, 25, 3).get("total")
                .toString());
        test.deleteContactTrack(String.valueOf(1));
        int end = Integer.parseInt(test
                .getAllContactTrack(searchFlag[0], contactTrackSearchBean, 1, 25, 3).get("total")
                .toString());
        Assert.assertTrue(start - end == 1);

        // 测试异常
        try {
            test.deleteContactTrack("hello");
            fail("No exception thrown.");
        } catch (CRMDBException e) {
            Assert.assertTrue(e instanceof CRMDBException);
        }
    }

}