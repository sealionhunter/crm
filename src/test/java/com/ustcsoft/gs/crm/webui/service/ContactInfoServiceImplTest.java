package com.ustcsoft.gs.crm.webui.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.contact.bean.ContactSearchBean;
import com.ustcsoft.gs.crm.webui.contact.dto.ContactInfoDto;
import com.ustcsoft.gs.crm.webui.contact.dto.FamilyDto;
import com.ustcsoft.gs.crm.webui.contact.dto.SocialDto;
import com.ustcsoft.gs.crm.webui.contact.service.ContactInfoService;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

/**
 * @author shenkaichuan
 * 
 */
public class ContactInfoServiceImplTest extends CRMTest {
    /** ContactInfoService test */
    private static ContactInfoService test;

    /** contactInfo List */
    private List<ContactInfoDto> contactInfoList = null;

    /** a contactInfo */
    private ContactInfoDto contactInfo = new ContactInfoDto();

    /** familyInfo List */
    private List<FamilyDto> familyInfoList = null;

    /** socialInfoList List */
    private List<SocialDto> socialInfoList = null;

    /** familyInfo1 */
    private FamilyDto familyInfo1 = new FamilyDto();

    /** socialInfo1 */
    private SocialDto socialInfo1 = new SocialDto();

    /** familyInfo2 */
    private FamilyDto familyInfo2 = new FamilyDto();

    /** socialInfo2 */
    private SocialDto socialInfo2 = new SocialDto();

    /** familyInfo3 */
    private FamilyDto familyInfo3 = new FamilyDto();

    /** deleter familyID */
    private String familyID = null;

    /** deleter socialID */
    private String socialID = null;

    /** search bean */
    private ContactSearchBean contactSearchBean = new ContactSearchBean();

    /** a search condition */
    private String searchText1 = "111";

    /** another search condition */
    private String searchText2 = "1asdhfaskjdfhlsaiudrwqbheol3irug";

    /** deleter contactIDs */
    private static int[] contactIDs;

    /**
     * Service initialize
     */
    @BeforeClass
    public final static void setUpBeforeClass() {
        test = (ContactInfoService) CTX.getBean("contactInfoService");
        contactIDs = new int[2];
    }

    /**
	 */
    @After
    public void tearDown() {
    }

    /**
     * Test method for
     * {@link com.ustcsoft.gs.crm.webui.contact.service.impl.ContactInfoServiceImpl#getAllContact(int, com.ustcsoft.gs.crm.webui.contact.bean.ContactSearchBean, int,int)}
     * .
     * 
     * @throws CRMDBException
     * 
     */
    @SuppressWarnings("unchecked")
    @Test
    public final void testGetAllContact1() throws CRMDBException {
        map = test.getAllContact(searchFlag[0], contactSearchBean, currpage, 25);
        Assert.assertNotNull(map);
        contactInfoList = (List<ContactInfoDto>) map.get("items");
        Assert.assertFalse(contactInfoList.size() < 0);
    }

    /**
     * Test method for
     * {@link com.ustcsoft.gs.crm.webui.contact.service.impl.ContactInfoServiceImpl #addContact(com.ustcsoft.gs.crm.webui.contact.dto.ContactInfoDto, java.util.List, java.util.List, java.lang.String, java.lang.String)}
     * .
     * 
     * @throws CRMDBException
     * 
     */
    @Test
    public final void testAddContact1() throws CRMDBException {
        /**
         * add records
         */
        contactInfo.setContactName("王二");
        contactInfo.setSex("3");
        contactInfo.setCompany(searchText1);
        contactInfo.setDepartment("222");
        contactInfo.setJob("jiao");
        contactInfo.setPhoneNumber("13111445654");
        contactInfo.setAddr("11122233si往");
        setList();
        test.addContact(contactInfo, familyInfoList, socialInfoList, familyID, socialID);
        contactIDs[0] = contactInfo.getContactID();
        contactInfo = test.findByID(contactIDs[0]);
        Assert.assertEquals(contactInfo.getContactName(), "王二");
        Assert.assertEquals(contactInfo.getJob(), "jiao");
    }

    /**
     * Test method for
     * {@link com.ustcsoft.gs.crm.webui.contact.service.impl.ContactInfoServiceImpl #addContact(com.ustcsoft.gs.crm.webui.contact.dto.ContactInfoDto, java.util.List, java.util.List, java.lang.String, java.lang.String)}
     * .
     * 
     * @throws CRMDBException
     * 
     */
    @Test
    public final void testAddContact2() throws CRMDBException {
        /**
         * add records
         */
        contactInfo.setContactName("王三");
        contactInfo.setSex("1");
        contactInfo.setCompany("11111");
        contactInfo.setDepartment("222");
        contactInfo.setJob("jiaoshi");
        contactInfo.setPhoneNumber("13165461114");
        contactInfo.setJobResume("曾经╭(╯^╰");
        setList();
        test.addContact(contactInfo, familyInfoList, socialInfoList, familyID, socialID);
        contactIDs[1] = contactInfo.getContactID();
        contactInfo = test.findByID(contactIDs[1]);
        Assert.assertEquals(contactInfo.getContactName(), "王三");
        Assert.assertEquals(contactInfo.getJobResume(), "曾经╭(╯^╰");
    }

    protected void setList() {
        familyInfoList = new ArrayList<FamilyDto>();
        socialInfoList = new ArrayList<SocialDto>();
        familyInfo1.setFamilyName("王尚");
        familyInfo1.setFamilyRelation("哥哥");
        familyInfo2.setFamilyName("王yi");
        familyInfo2.setFamilyRelation("哥哥");
        familyInfo3.setFamilyName("Green");
        familyInfo3.setFamilyRelation("哥哥");
        socialInfo1.setSocialName("周胄");
        socialInfo1.setSocialRelation("朋友");
        socialInfo2.setSocialName("何健");
        socialInfo2.setSocialRelation("朋友");
        familyInfoList.add(familyInfo1);
        familyInfoList.add(familyInfo2);
        familyInfoList.add(familyInfo3);
        socialInfoList.add(socialInfo1);
        socialInfoList.add(socialInfo2);
    }

    /**
     * Test method for
     * {@link com.ustcsoft.gs.crm.webui.contact.service.impl.ContactInfoServiceImpl #addContact(com.ustcsoft.gs.crm.webui.contact.dto.ContactInfoDto, java.util.List, java.util.List, java.lang.String, java.lang.String)}
     * .
     * 
     * @throws CRMDBException
     * 
     */
    @Test
    public final void testAddContact3() throws CRMDBException {
        /**
         * Edit records
         */
        contactInfo.setContactName("王四");
        contactInfo.setSex("2");
        contactInfo.setCompany("111111111");
        contactInfo.setDepartment("456");
        contactInfo.setJob("jshi");
        contactInfo.setPhoneNumber("13111111114");
        contactInfo.setContactID(contactIDs[0]);
        String familyID = "(3)";
        String socialID = "(1)";
        test.addContact(contactInfo, familyInfoList, socialInfoList, familyID, socialID);
        contactInfo = test.findByID(contactIDs[0]);
        Assert.assertTrue(contactInfo.getContactName().equals("王四"));
        Assert.assertTrue(contactInfo.getSex().equals("2"));
        Assert.assertTrue(contactInfo.getJob().equals("jshi"));
    }

    /**
     * Test method for
     * {@link com.ustcsoft.gs.crm.webui.contact.service.impl.ContactInfoServiceImpl#getAllContact(int, com.ustcsoft.gs.crm.webui.contact.bean.ContactSearchBean, int,int)}
     * .
     * 
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Test
    public final void testGetAllContact2() throws CRMDBException {
        /**
         * have records
         */
        contactSearchBean.setSearchText(searchText1);
        map = test.getAllContact(searchFlag[1], contactSearchBean, currpage, 25);
        contactInfoList = (List<ContactInfoDto>) map.get("items");
        Assert.assertTrue(contactInfoList.size() > 0);
        for (int i = 0; i < contactInfoList.size(); i++) {
            boolean company = contactInfoList.get(i).getCompany().contains(searchText1);
            boolean contactName = contactInfoList.get(i).getContactName().contains(searchText1);
            boolean department = contactInfoList.get(i).getDepartment().contains(searchText1);
            boolean phoneNumber = contactInfoList.get(i).getPhoneNumber().contains(searchText1);
            Assert.assertTrue(company || contactName || department || phoneNumber);
        }

        /**
         * have not records
         */
        contactSearchBean.setSearchText(searchText2);
        map = test.getAllContact(searchFlag[1], contactSearchBean, currpage, 25);
        Assert.assertTrue(Integer.parseInt(map.get("total").toString()) == 0);

    }

    /**
     * Test method for
     * {@link com.ustcsoft.gs.crm.webui.contact.service.impl.ContactInfoServiceImpl#getAllContact(int, com.ustcsoft.gs.crm.webui.contact.bean.ContactSearchBean, int,int)}
     * .
     * 
     * @throws CRMDBException
     * 
     */
    @SuppressWarnings("unchecked")
    @Test
    public final void testGetAllContact3() throws CRMDBException {
        /**
         * have not records
         */
        contactSearchBean.setContactCompany(searchText2);
        contactSearchBean.setContactDepartment("");
        contactSearchBean.setContactJob("");
        contactSearchBean.setContactName("");
        map = test.getAllContact(searchFlag[2], contactSearchBean, currpage, 25);
        Assert.assertNotNull(map);
        Assert.assertTrue(Integer.parseInt(map.get("total").toString()) == 0);

        /**
         * have records
         */
        contactSearchBean.setContactCompany("222");
        contactSearchBean.setContactName("王三");
        contactSearchBean.setContactDepartment(null);
        contactSearchBean.setContactJob("jiaoshi");
        map = test.getAllContact(searchFlag[2], contactSearchBean, currpage, 25);
        Assert.assertNotNull(map);
        contactInfoList = (List<ContactInfoDto>) map.get("items");
        for (int i = 0; i < contactInfoList.size(); i++) {
            boolean company = contactInfoList.get(i).getCompany().contains("222");
            boolean contactName = contactInfoList.get(i).getContactName().contains("王三");
            boolean job = contactInfoList.get(i).getJob().contains("jiaoshi");
            Assert.assertTrue(company && contactName && job);
        }
    }

    /**
     * Test method for
     * {@link com.ustcsoft.gs.crm.webui.contact.service.impl.ContactInfoServiceImpl#deleteContact(java.lang.String)}
     * .
     * 
     * @throws CRMDBException
     * 
     */
    @Test
    public final void testDeleteContact() throws CRMDBException {
        /**
         * delete success
         */
        test.deleteContact(contactIDs[0] + "," + contactIDs[1]);
        contactInfo = test.findByID(contactIDs[0]);
        Assert.assertTrue(contactInfo.getIsAbolished());
        contactInfo = test.findByID(contactIDs[1]);
        Assert.assertTrue(contactInfo.getIsAbolished());

        /**
         * delete fail
         */
        thrown.expect(CRMDBException.class);
        thrown.expectMessage("aaa");
        test.deleteContact("aaa");

    }

}
