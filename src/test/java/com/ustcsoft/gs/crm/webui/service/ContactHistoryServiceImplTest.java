/**
 * 
 */
package com.ustcsoft.gs.crm.webui.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.ContactHistorySearchBean;
import com.ustcsoft.gs.crm.webui.customer.service.ContactHistoryService;
import com.ustcsoft.gs.crm.webui.customer.service.impl.ContactHistoryServiceImpl;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

/**
 * @author yinweili
 * 
 */
public class ContactHistoryServiceImplTest extends CRMTest {

    private static ContactHistoryService contactHistoryService = null;
//    private static CustomerService customerService = null;
//    private static ContactInfoService contactInfoService = null;
//    private static ContactTrackService contactTrackService = null;
//    private static CustomerDto customerDto = new CustomerDto();
//    private static ContactInfoDto contactInfoDto = new ContactInfoDto();
    private static ContactHistorySearchBean contactHistorySearchBean = new ContactHistorySearchBean();
    private static String searchText = null;
    private int pageSize = 25;
//    private static final ContactTrackListBean contactTrackListBean = new ContactTrackListBean();
    List<?> list = null;

    /**
     * insert a record
     * 
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
//        contactHistoryService = (ContactHistoryService) CTX.getBean("contactHistoryService");
//        contactTrackService = (ContactTrackService) CTX.getBean("contactTrackService");
//        customerService = (CustomerService) CTX.getBean("customerService");
//        contactInfoService = (ContactInfoService) CTX.getBean("contactInfoService");
        // add a record of contact for the customer
        // contactInfoDto.setCompany("ustc");
        // contactInfoDto.setContactName("李四");
        // contactInfoDto.setJob("FFF");
        // contactInfoDto.setPhoneNumber("12546875624");
        // contactInfoDto.setIsAbolished(false);
        // contactInfoDto.setDepartment("11");
        // contactInfoDto.setAddr("11");
        // contactInfoService.addContact(contactInfoDto, null, null, null,
        // null);
        // // add a record of customer
        // customerDto.setCustomerName("zkk");
        // customerDto.setIndustry("01");
        // customerDto.setFee("1");
        // customerDto.setCustomerAddr("合肥");
        // customerDto.setCustomerStatement("1");
        // customerDto.setCustomerType("1");
        // customerDto.setIsAbolished(false);
        // customerDto.setScale("2");
        // customerDto.setValueEvaluate("2");
        // customerService.updateCustomer(customerDto);
        //
        // // // add a record of contactHistory
        // contactTrackListBean.setCustomerID(5);
        // contactTrackListBean.setWeContact(3);
        // contactTrackListBean.setOppositeContact(1);
        // contactTrackListBean.setContactType("1");
        // contactTrackListBean.setContactWay("1");
        // contactTrackListBean.setContactContent("soft");
        // contactTrackListBean.setPlanDateBegin("2013-10-18 15:52:52");
        // contactTrackListBean.setRemarks("zkk");
        // contactTrackListBean.setIfContact(false);
        // contactTrackListBean.setIsAbolished(false);
        // contactTrackListBean.setNotContantReason("123");
        // contactTrackService.updateContactTrack(contactTrackListBean,
        // submitFlag[3], 3, 0);
        searchText = "";
        contactHistorySearchBean.setSearchText(searchText);
        // contactHistoryService.searchOrGetAllContactHistory(0,
        // contactHistorySearchBean, 1, 25, 3);
    }

    /**
     * test method searchOrGetAllContactHistory to get records from DB
     * 
     * @throws java.lang.Exception
     */
    @Test
    public void testSearchOrGetAllContactHistory_getAll() throws CRMDBException {

        ContactHistoryServiceImpl contactHistoryServiceImpl = new ContactHistoryServiceImpl();
        contactHistoryServiceImpl.getContactHistoryDao();
        contactHistoryServiceImpl.getSourceInfoService();

        map = contactHistoryService.searchOrGetAllContactHistory(searchFlag[0],
                contactHistorySearchBean, currpage, pageSize, userID);
        int count = Integer.parseInt(map.get("total").toString());
        list = (List<?>) map.get("items");
        boolean bool = count >= list.size();
        Assert.assertEquals(bool, true);
        // 异常测试
        contactHistorySearchBean.setSearchDateEnd("hello");
        try {
            map = contactHistoryService.searchOrGetAllContactHistory(searchFlag[2],
                    contactHistorySearchBean, currpage, limit, userID);
            fail("No exception thrown.");
        } catch (CRMDBException e) {
            Assert.assertTrue(e instanceof CRMDBException);
        }
    }

    /**
     * delete the record
     * 
     * @throws java.lang.Exception
     */
    // @AfterClass
    // public static void tearDownAfterClass() throws Exception {
    // customerService.deleteCustomer("1");
    // contactInfoService.deleteContact("1");
    // contactTrackService.deleteContactTrack("1");
    // }

}
