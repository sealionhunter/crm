package com.ustcsoft.gs.crm.webui.service;

import java.util.List;
import java.util.Map;

import jp.co.dgic.testing.common.virtualmock.MockObjectManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.system.bean.DepartmentInfoSearchBean;
import com.ustcsoft.gs.crm.webui.system.constant.SystemConstant;
import com.ustcsoft.gs.crm.webui.system.dao.DepartmentInfoDao;
import com.ustcsoft.gs.crm.webui.system.dto.DepartmentDto;
import com.ustcsoft.gs.crm.webui.system.service.DepartmentInfoService;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

/**
 * 
 * @author maxue
 * 
 */
public class DepartmentInfoServiceImplTest extends CRMTest {
    public static final String TEST0 = "测试部门名";
    public static final String TEST1 = "test";
    private static final String TOTAL = "total";
    private static final String ITEMS = "items";
    public static DepartmentInfoService departmentInfoService = null;
    public static HibernateTemplate hibernate = null;
    private Map<String, Object> map = null;
    private DepartmentInfoSearchBean searchBean = new DepartmentInfoSearchBean();
    List<?> list = null;
    DepartmentDto departmentDto = new DepartmentDto();
    private static int deleteId = -1;
    private boolean isExisted = false;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        departmentInfoService = (DepartmentInfoService) CTX.getBean("departmentInfoService");
        hibernate = (HibernateTemplate) CTX.getBean("hibernateTemplate");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        departmentDto.setFatherDepartmentID(1);
        departmentDto.setDepartmentName("测试部门名");

        searchBean.setDepartmentManager(TEST1);
        searchBean.setDepartmentName(TEST1);
        searchBean.setSearchText(TEST1);
        searchBean.setFatherDepartmentID(1);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testUpdateDepartment() throws CRMDBException {
        long total1 = (Long) hibernate.findByNamedParam(SystemConstant.DEPARTMENT_COUNT_HQL,
                SystemConstant.DEPARTMENTID, 1).get(0);
        departmentInfoService.updateDepartment(departmentDto);
        deleteId = departmentDto.getDepartmentID();
        map = departmentInfoService.getFatherDepartment(3, 2, 3, 0);
        long total2 = (Long) hibernate.findByNamedParam(SystemConstant.DEPARTMENT_COUNT_HQL,
                SystemConstant.DEPARTMENTID, 1).get(0);
        Assert.assertEquals(total2 - total1, 1);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testQueryOrGetAllDepartment() throws CRMDBException {
        DepartmentDto result = null;
        map = departmentInfoService.queryOrGetAllDepartment(1, 0, searchBean, 1, 25);
        int count = Integer.parseInt(map.get(TOTAL).toString());
        list = (List<?>) map.get(ITEMS);
        boolean bool = count >= list.size();
        Assert.assertEquals(bool, true);

        map = departmentInfoService.queryOrGetAllDepartment(1, 1, searchBean, 1, 25);
        Assert.assertNotNull(map);
        list = (List<DepartmentDto>) map.get(ITEMS);
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                result = (DepartmentDto) list.get(i);
                boolean b1 = result.getDepartmentName().contains(TEST1);
                boolean b3 = result.getDepartmentPhone().contains(TEST1);
                boolean b4 = result.getDepartmentDescription().contains(TEST1);
                boolean b5 = result.getCreateTime().contains(TEST1);
                Assert.assertEquals(true, b1 || b3 || b4 || b5);
            }
        }

        map = departmentInfoService.queryOrGetAllDepartment(1, 2, searchBean, 1, 25);
        Assert.assertNotNull(map);
        list = (List<DepartmentDto>) map.get(ITEMS);
        for (int i = 0; i < list.size(); i++) {
            boolean b6 = true;
            result = (DepartmentDto) list.get(i);
            b6 = result.getDepartmentName().contains(TEST1);
            Assert.assertEquals(b6, true);
        }
    }

    @Test
    public void testDeleteDepartment() throws CRMDBException {
        map = departmentInfoService.deleteDepartment("1");
        Assert.assertEquals(map.get("flag"), false);

        map = departmentInfoService.deleteDepartment(deleteId + "");
        Assert.assertEquals(map.get("flag"), true);
    }

    @Test
    public void testJudgeDepartmentName() throws CRMDBException {
        DepartmentDto department1 = new DepartmentDto();
        department1.setDepartmentID(1);
        department1.setDepartmentName("科大国创");
        isExisted = departmentInfoService.judgeDepartmentName(department1);
        Assert.assertEquals(isExisted, false);

        DepartmentDto department2 = new DepartmentDto();
        department2.setDepartmentID(0);
        department2.setDepartmentName("科大国创");
        isExisted = departmentInfoService.judgeDepartmentName(department2);
        Assert.assertEquals(isExisted, true);

        DepartmentDto department3 = new DepartmentDto();
        department3.setDepartmentID(5);
        department3.setDepartmentName("科大国创");
        isExisted = departmentInfoService.judgeDepartmentName(department3);
        Assert.assertEquals(isExisted, true);
    }

    @Test
    public void testGetDepartmentManager() throws CRMDBException {
        map = departmentInfoService.getDepartmentManager(0);
        list = (List<?>) map.get(ITEMS);
        Assert.assertEquals(list.size(), 0);
    }

    @Test(expected = CRMDBException.class)
    public void testQueryOrGetAllDepartmentThrowsCRMDBException() throws CRMDBException {
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(DepartmentInfoDao.class, "queryDepartment",
                new EmptyResultDataAccessException(0));
        departmentInfoService.queryOrGetAllDepartment(1, 1, searchBean, 1, 25);
    }

    @Test(expected = CRMDBException.class)
    public void testDeleteDepartmentThrowsCRMDBException() throws CRMDBException {
        departmentInfoService.deleteDepartment("");
    }

    @Test(expected = CRMDBException.class)
    public void testUpdateDepartmentThrowsCRMDBException() throws CRMDBException {
        departmentDto
                .setDepartmentName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        departmentInfoService.updateDepartment(departmentDto);
    }

    @Test(expected = CRMDBException.class)
    public void testJudgeDepartmentNameThrowsCRMDBException() throws CRMDBException {
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(DepartmentInfoDao.class, "judgeDepartmentName",
                new EmptyResultDataAccessException(0));
        departmentDto.setDepartmentID(0);
        departmentDto.setDepartmentName("123");
        departmentInfoService.judgeDepartmentName(departmentDto);
    }

    @Test(expected = CRMDBException.class)
    public void testGetFatherDepartmentThrowsCRMDBException() throws CRMDBException {
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(DepartmentInfoDao.class, "getFatherDepartment",
                new EmptyResultDataAccessException(0));
        departmentInfoService.getFatherDepartment(1, 1, 1, 2);
    }

    @Test(expected = CRMDBException.class)
    public void testGetDepartmentManagerThrowsCRMDBException() throws CRMDBException {
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(DepartmentInfoDao.class, "getDepartmentManager",
                new EmptyResultDataAccessException(0));
        departmentInfoService.getDepartmentManager(1);
    }
}
