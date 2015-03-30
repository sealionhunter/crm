package com.ustcsoft.gs.crm.webui.service;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.TransferBean;
import com.ustcsoft.gs.crm.webui.customer.service.CustomerTransferService;
import com.ustcsoft.gs.crm.webui.system.dao.MainDao;
import com.ustcsoft.gs.crm.webui.system.dto.DepartmentDto;
import com.ustcsoft.gs.crm.webui.system.dto.ProjectTeamDto;
import com.ustcsoft.gs.crm.webui.system.dto.UserInfoDto;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

public class CustomerTransferServiceTest extends CRMTest {

    private MainDao mainDao = null;
    private CustomerTransferService transferService = null;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        transferService = (CustomerTransferService) CTX.getBean("transferService");
        mainDao = (MainDao) CTX.getBean("mainDao");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * 
     * @throws CRMDBException
     */
    @Test
    public void testSaveTransfer() throws CRMDBException {
        transferService.saveTransfer("1", 1);
        super.map = transferService.getCustomerForTransfer(0, 1, 2, 1, 3, 1, 10);
    }

    /**
     * {@link com.ustcsoft.gs.crm.webui.contact.service.impl.SocialInfoServiceImpl#getAllSocialInfo()}
     * 
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetCustomerForTransfer() throws CRMDBException {
        super.map = transferService.getCustomerForTransfer(0, 1, 2, 1, 3, 1, 10);
        List<TransferBean> list = (List<TransferBean>) map.get("items");
        for (TransferBean bean : list) {
            Assert.assertEquals(bean.getHolder(), 1);
        }
        super.map = transferService.getCustomerForTransfer(0, 1, 2, 1, 3, 1, 10);
        List<TransferBean> list1 = (List<TransferBean>) map.get("items");
        for (TransferBean bean1 : list1) {
            int projectTeamID = mainDao.findByUserID(bean1.getHolder()).getProjectTeamID();
            Assert.assertEquals(1, projectTeamID);
        }
        super.map = transferService.getCustomerForTransfer(0, 1, 2, 1, 3, 1, 10);
        List<TransferBean> list2 = (List<TransferBean>) map.get("items");
        for (TransferBean bean2 : list2) {
            int departmentID = mainDao.findByUserID(bean2.getHolder()).getDepartmentID();
            Assert.assertEquals(1, departmentID);
        }
        super.map = transferService.getCustomerForTransfer(0, 1, 2, 1, 3, 1, 10);
        List<TransferBean> list3 = (List<TransferBean>) map.get("items");
        Assert.assertEquals(true, list3.size() >= 0);
    }

    /**
     * 
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetDepartment() throws CRMDBException {
        super.map = transferService.getDepartment();
        List<DepartmentDto> list = (List<DepartmentDto>) map.get("items");
        Assert.assertEquals(list.get(0).getDepartmentName(), "通用系统部");

    }

    /**
     * 
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetProjectTeam() throws CRMDBException {
        super.map = transferService.getProjectTeam(0);
        List<ProjectTeamDto> list = (List<ProjectTeamDto>) map.get("items");
        Assert.assertEquals(list.get(0).getProjectTeamName(), "DALM");
        super.map = transferService.getProjectTeam(2);
        List<ProjectTeamDto> list1 = (List<ProjectTeamDto>) map.get("items");
        Assert.assertEquals(list1.get(0).getProjectTeamName(), "DW");
    }

    /**
     * 
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetUser() throws CRMDBException {
        super.map = transferService.getUser(0, 0, 0);
        List<UserInfoDto> list = (List<UserInfoDto>) map.get("items");
        if (list.size() > 0) {
            Assert.assertEquals(list.get(0).getGroupID() >= 2, true);
        }

        super.map = transferService.getUser(0, 0, 1);
        List<UserInfoDto> list1 = (List<UserInfoDto>) map.get("items");
        if (list1.size() > 0) {
            Assert.assertEquals(list1.get(0).getGroupID() > 2, true);
        }

        super.map = transferService.getUser(0, 1, 0);
        List<UserInfoDto> list2 = (List<UserInfoDto>) map.get("items");
        if (list2.size() > 0) {
            Assert.assertEquals(list2.get(0).getGroupID() >= 4, true);
        }

        super.map = transferService.getUser(0, 1, 1);
        List<UserInfoDto> list3 = (List<UserInfoDto>) map.get("items");
        if (list3.size() > 0) {
            Assert.assertEquals(list3.get(0).getGroupID() > 4, true);
        }

        super.map = transferService.getUser(1, 0, 0);
        List<UserInfoDto> list4 = (List<UserInfoDto>) map.get("items");
        if (list4.size() > 0) {
            Assert.assertEquals(list4.get(0).getGroupID() >= 3, true);
        }

        super.map = transferService.getUser(1, 0, 1);
        List<UserInfoDto> list5 = (List<UserInfoDto>) map.get("items");
        if (list5.size() > 0) {
            Assert.assertEquals(list5.get(0).getGroupID() > 3, true);
        }
    }
}
