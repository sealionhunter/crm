package com.ustcsoft.gs.crm.webui.service;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.contact.dto.SocialDto;
import com.ustcsoft.gs.crm.webui.contact.service.SocialInfoService;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

/**
 * @author shenkaichuan
 * 
 */
public class SocialInfoServiceImplTest extends CRMTest {
    /** SocialInfoService test */
    private static SocialInfoService test = null;

    /** useless contactID */
    private final int contactID1 = 0;

    /** useful contactID */
    private final int contactID2 = 1;

    /** FamilyDto List */
    private List<SocialDto> list = null;

    /**
     * Service initialize
     */
    @BeforeClass
    public final static void setUpBeforeClass() {
        test = (SocialInfoService) CTX.getBean("socialInfoService");
    }

    /**
	 */
    @After
    public void tearDown() {
    }

    /**
     * Test method for
     * {@link com.ustcsoft.gs.crm.webui.contact.service.impl.SocialInfoServiceImpl#getAllSocialInfo()}
     * .
     * 
     * @throws CRMDBException
     * 
     */
    @Test
    public final void testGetAllSocialInfo() throws CRMDBException {
        list = test.getAllSocialInfo();
        Assert.assertTrue(list.size() > 0);
    }

    /**
     * Test method for
     * {@link com.ustcsoft.gs.crm.webui.contact.service.impl.SocialInfoServiceImpl#getSocialInfoByContact(int)}
     * .
     * 
     * @throws CRMDBException
     * 
     */
    @Test
    public final void testGetSocialInfoByContact1() throws CRMDBException {
        list = test.getSocialInfoByContact(contactID1);
        Assert.assertEquals(list.size(), 0);
    }

    /**
     * Test method for
     * {@link com.ustcsoft.gs.crm.webui.contact.service.impl.SocialInfoServiceImpl#getSocialInfoByContact(int)}
     * .
     * 
     * @throws CRMDBException
     * 
     */
    @Test
    public final void testGetSocialInfoByContact2() throws CRMDBException {
        list = test.getSocialInfoByContact(contactID2);
        Assert.assertTrue(list.size() > 0);
    }

}
