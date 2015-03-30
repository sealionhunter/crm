package com.ustcsoft.gs.crm.webui.service;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.contact.dto.FamilyDto;
import com.ustcsoft.gs.crm.webui.contact.service.FamilyInfoService;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

/**
 * @author shenkaichuan
 * 
 */
public class FamilyInfoServiceImplTest extends CRMTest {

    /** FamilyInfoService test */
    private static FamilyInfoService test = null;

    /** useless contactID */
    private final int contactID1 = 0;

    /** useful contactID */
    private final int contactID2 = 1;

    /** FamilyDto List */
    private List<FamilyDto> list = null;

    /**
     * Service initialize
     */
    @BeforeClass
    public final static void setUpBeforeClass() {
        test = (FamilyInfoService) CTX.getBean("familyInfoService");
    }

    /**
	 */
    @After
    public void tearDown() {

    }

    /**
     * Test method for
     * {@link com.ustcsoft.gs.crm.webui.contact.service.impl.FamilyInfoServiceImpl#getFamilyInfoByContact(int)}
     * .
     * 
     * @throws CRMDBException
     * 
     */
    @Test
    public final void testGetFamilyInfoByContact() throws CRMDBException {
        list = test.getAllFamilyInfo();
        Assert.assertTrue(list.size() > 0);
    }

    /**
     * Test method for
     * {@link com.ustcsoft.gs.crm.webui.contact.service.impl.FamilyInfoServiceImpl#getAllFamilyInfo()}
     * .
     * 
     * @throws CRMDBException
     * 
     */
    @Test
    public final void testGetAllFamilyInfo1() throws CRMDBException {
        list = test.getFamilyInfoByContact(contactID1);
        Assert.assertEquals(list.size(), 0);
    }

    /**
     * Test method for
     * {@link com.ustcsoft.gs.crm.webui.contact.service .impl.FamilyInfoServiceImpl#getAllFamilyInfo()}
     * .
     * 
     * @throws CRMDBException
     * 
     */
    @Test
    public final void testGetAllFamilyInfo2() throws CRMDBException {
        list = test.getFamilyInfoByContact(contactID2);
        Assert.assertTrue(list.size() > 0);
    }

}
