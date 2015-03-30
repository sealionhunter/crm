package com.ustcsoft.gs.crm.webui.service;

import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.ContactSelectSearchBean;
import com.ustcsoft.gs.crm.webui.customer.bean.ContactSeleteBean;
import com.ustcsoft.gs.crm.webui.customer.dto.ContactSelectDto;
import com.ustcsoft.gs.crm.webui.customer.service.ContactSelectService;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

/**
 * 
 * @author libaoshan
 * 
 */
public class ContactSelectServiceImplTest extends CRMTest {

    /** Used for test */
    private static ContactSelectService contactSelectServiceTest = null;

    /** Used for search */
    private static ContactSelectSearchBean contactSelectSearchBean = null;

    private Map<String, Object> map = null;

    /**
     * 
     * @throws Exception
     */
    @BeforeClass
    public static void beforeClass() throws Exception {
        contactSelectServiceTest = (ContactSelectService) CTX.getBean("contactSelectService");
    }

    /**
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        contactSelectSearchBean = new ContactSelectSearchBean();
        contactSelectSearchBean.setObjectFlag(1);
        contactSelectSearchBean.setObjectID(1);
    }

    /**
     * 
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * 
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetAllContactSelect() throws CRMDBException {
        int flag = 0;
        map = contactSelectServiceTest.getAllContactSelect(0, contactSelectSearchBean, 1, 25);
        long total = (Long) map.get(CRMConstant.TOTAL);
        List<ContactSeleteBean> contactSelectBeans = (List<ContactSeleteBean>) map.get("items");
        long size = contactSelectBeans.size();
        if (total >= size) {
            flag = 1;
        }
        Assert.assertEquals(flag, 1);
    }

    /**
     * 
     * @throws CRMDBException
     */
    @Test
    public void testSaveContactSelect() throws CRMDBException {
        map = contactSelectServiceTest.getAllContactSelect(0, contactSelectSearchBean, 1, 25);
        long start = (Long) map.get(CRMConstant.TOTAL);
        contactSelectServiceTest.saveContactSelect("1", 1, 1);
        map = contactSelectServiceTest.getAllContactSelect(0, contactSelectSearchBean, 1, 25);
        long end = (Long) map.get(CRMConstant.TOTAL);
        Assert.assertEquals(1L, end - start);
    }

    /**
     * 
     * @throws CRMDBException
     */
    @Test
    public void testDeleteContactSelect() throws CRMDBException {
        map = contactSelectServiceTest.getAllContactSelect(0, contactSelectSearchBean, 1, 25);
        long start = (Long) map.get(CRMConstant.TOTAL);
        ContactSelectDto contactSelectDto = getontactSelectDto();
        contactSelectServiceTest.saveContactSelect("1", 1, 1);
        int contactSelectID = contactSelectDto.getContactSelectID();
        String deletedIds = contactSelectID + "";
        contactSelectServiceTest.deleteContactSelect(deletedIds);
        map = contactSelectServiceTest.getAllContactSelect(0, contactSelectSearchBean, 1, 25);
        long end = (Long) map.get(CRMConstant.TOTAL);
        Assert.assertEquals(1, end - start);
    }

    @Test
    public void testGetAllContactSelectException() {
        int flag = 0;
        try {
            ContactSelectSearchBean contactSelectSearchBean = new ContactSelectSearchBean();
            map = contactSelectServiceTest.getAllContactSelect(0, contactSelectSearchBean, -1, 25);
        } catch (Exception e) {
            flag = 1;
        }
        Assert.assertEquals(flag, 1);
    }

    @Test
    public void testSaveContactSelectExcfeption() {
        int flag = 0;
        try {
            ContactSelectDto contactSelectDto = new ContactSelectDto();
            contactSelectDto.setContactID(Integer.parseInt("abc"));
            contactSelectServiceTest.saveContactSelect("1", 1, 1);
        } catch (Exception e) {
            flag = 1;
        }
        Assert.assertEquals(flag, 1);
    }

    @Test
    public void testDeleteContactSelectException() {
        int flag = 0;
        try {
            contactSelectServiceTest.deleteContactSelect("def");
        } catch (Exception e) {
            flag = 1;
        }
        Assert.assertEquals(flag, 1);
    }

    /**
     * 
     * @return contactSelectDto
     */
    public ContactSelectDto getontactSelectDto() {
        ContactSelectDto contactSelectDto = new ContactSelectDto();
        contactSelectDto.setContactID(1);
        contactSelectDto.setFlag(1);
        contactSelectDto.setObjectID(1);
        return contactSelectDto;
    }
}
