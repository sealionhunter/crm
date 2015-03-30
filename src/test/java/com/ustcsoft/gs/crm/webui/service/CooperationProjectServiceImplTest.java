package com.ustcsoft.gs.crm.webui.service;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ustcsoft.gs.crm.webui.customer.bean.CooperationProjectSearchBean;
import com.ustcsoft.gs.crm.webui.customer.bean.CooperationProjectShowBean;
import com.ustcsoft.gs.crm.webui.customer.dto.CooperationProjectDto;
import com.ustcsoft.gs.crm.webui.customer.dto.CooperatorDto;
import com.ustcsoft.gs.crm.webui.customer.service.CooperationProjectService;
import com.ustcsoft.gs.crm.webui.customer.service.CooperatorService;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

public class CooperationProjectServiceImplTest extends CRMTest {
    private static CooperationProjectService service = null;
    private static CooperationProjectSearchBean searchBean = new CooperationProjectSearchBean();
    private static CooperationProjectDto cpd = new CooperationProjectDto();
    private static List<CooperationProjectShowBean> list = null;
    private Map<String, Object> map = null;
    private static int cooperatorID = 0;
    private static int projectID = 0;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        service = (CooperationProjectService) CTX.getBean("cooperationProjectService");
    }

    @Test
    public void testUpdateCooperationProject() throws Exception {
        // add cooperator
        CooperatorDto coo = new CooperatorDto();
        coo.setCooperatorID(0);
        coo.setCooperatorName("测试用合作伙伴名称");
        coo.setCooperatorCity("city");
        coo.setCooperatorIndustry("01");
        coo.setCooperatorScale("1");
        coo.setCooperatorType("01");
        coo.setIsAbolished(false);
        CooperatorService cooperatorService = (CooperatorService) CTX.getBean("cooperatorService");
        cooperatorService.updateCooperator(coo);
        // add cooperation project
        cooperatorID = coo.getCooperatorID();
        cpd.setCooperatorID(cooperatorID);
        cpd.setCooperationProjectID(0);
        cpd.setProjectName("测试用值");
        cpd.setProjectScale(5);
        cpd.setCooperatorScale(3);
        cpd.setCooperatorPeopleNumber(4);
        cpd.setProjectType("1");
        cpd.setExpectedBeginTime("2012-03-25");
        cpd.setExpectedEndTime("2012-03-25");
        cpd.setRealBeginTime("2012-03-25");
        cpd.setPrincipalCooperator("测试用值1");
        cpd.setPrincipalCooperatorPhone("13965895847");
        cpd.setPrincipalWe("测试用值2");
        cpd.setProjectDetail("welcome");
        cpd.setAppraisal("hello");
        cpd.setIsAbolished(false);
        // search total before add cooperation project
        searchBean.setCooperatorIDSearch(cooperatorID);
        Long startCount = (Long) service.searchCooperationProject(0, searchBean, 0, 25)
                .get("total");
        service.updateCooperationProject(cpd);
        projectID = cpd.getCooperationProjectID();
        // search total after add cooperation project
        Long endCount = (Long) service.searchCooperationProject(0, searchBean, 0, 25).get("total");
        assertEquals(1L, endCount - startCount);
        // exception test
        CooperationProjectDto cpd1 = new CooperationProjectDto();
        try {
            service.updateCooperationProject(cpd1);
            fail("失败");
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testSearchCooperationProject() throws Exception {
        // list
        searchBean.setCooperatorIDSearch(cooperatorID);
        map = service.searchCooperationProject(0, searchBean, 1, 25);
        Assert.assertNotNull(map);
        // simple searchText==null
        searchBean.setCooperatorIDSearch(cooperatorID);
        searchBean.setSearchText(null);
        map = service.searchCooperationProject(1, searchBean, 1, 25);
        Assert.assertNotNull(map);
        // simple search
        searchBean.setCooperatorIDSearch(cooperatorID);
        searchBean.setSearchText("测试用值");
        map = service.searchCooperationProject(1, searchBean, 1, 25);
        list = (List<CooperationProjectShowBean>) map.get("items");
        Map<String, Object> result = null;
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                result = (Map<String, Object>) list.get(i);
                boolean b1 = ((String) result.get("projectTypeDisplay")).contains("测试用值");
                boolean b2 = ((String) result.get("projectName")).contains("测试用值");
                boolean b3 = ((String) result.get("expectedBeginTime")).contains("测试用值");
                boolean b4 = ((String) result.get("expectedEndTime")).contains("测试用值");
                boolean b5 = Integer.toString((Integer) result.get("projectScale"))
                        .contains("测试用值");
                boolean b6 = Integer.toString((Integer) result.get("cooperatorScale")).contains(
                        "测试用值");
                boolean b7 = Integer.toString((Integer) result.get("cooperatorPeopleNumber"))
                        .contains("测试用值");
                boolean b8 = ((String) result.get("principalWe")).contains("测试用值");
                boolean b9 = ((String) result.get("principalCooperator")).contains("测试用值");
                boolean b10 = ((String) result.get("principalCooperatorPhone")).contains("测试用值");
                boolean b11 = ((String) result.get("projectDetail")).contains("测试用值");
                boolean b12 = ((String) result.get("appraisal")).contains("测试用值");
                Assert.assertEquals(true, b1 || b2 || b3 || b4 || b5 || b6 || b7 || b8 || b9 || b10
                        || b11 || b12);
            }
        }
        // advanced search
        searchBean.setCooperatorIDSearch(cooperatorID);
        searchBean.setProjectNameSearch("测试用值");
        searchBean.setPrincipalCooperatorSearch("测试用值1");
        searchBean.setPrincipalWeSearch("测试用值2");
        searchBean.setProjectScaleMax(6);
        searchBean.setProjectScaleMin(0);
        String type = "1";
        searchBean.setProjectTypeSearch(type);
        searchBean.setRealBeginTimeMax("2012-12-25");
        searchBean.setRealBeginTimeMin("2012-01-25");
        map = service.searchCooperationProject(2, searchBean, 1, 25);
        list = (List<CooperationProjectShowBean>) map.get("items");
        if (list.size() != 0) {
            DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i < list.size(); i++) {
                result = (Map<String, Object>) list.get(i);
                Assert.assertEquals(true, ((String) result.get("projectName")).contains("测试用值"));
                Assert.assertEquals(true, ((String) result.get("principalWe")).contains("测试用值2"));
                Assert.assertEquals(true,
                        ((String) result.get("principalCooperator")).contains("测试用值1"));
                Assert.assertEquals("1", result.get("projectType"));
                Assert.assertEquals(true, (Integer) result.get("projectScale") >= 0
                        && (Integer) result.get("projectScale") <= 6);
                long realBeginTime = date.parse((String) result.get("realBeginTime")).getTime();
                Assert.assertEquals(true, realBeginTime >= date.parse("2012-01-25").getTime()
                        && realBeginTime <= date.parse("2012-12-25").getTime());
            }
        }
        // exception test
        searchBean.setRealBeginTimeMax("invalidDate");
        try {
            service.searchCooperationProject(2, searchBean, 1, 25);
            fail("失败");
        } catch (Exception e) {
        }
    }

    @Test
    public void testCheckNameExisted() throws Exception {
        cpd.setCooperatorID(cooperatorID);
        cpd.setCooperationProjectID(projectID);
        cpd.setProjectName("测试用值");
        assertEquals(false, service.checkNameExisted(cpd));

        cpd.setCooperatorID(cooperatorID);
        cpd.setCooperationProjectID(projectID + 1);
        cpd.setProjectName("测试用值");
        assertEquals(true, service.checkNameExisted(cpd));
    }

    @Test
    public void testDeleteCooperationProject() throws Exception {
        searchBean.setCooperatorIDSearch(cooperatorID);
        Long startCount = (Long) service.searchCooperationProject(0, searchBean, 0, 25)
                .get("total");
        service.deleteCooperationProject(projectID + "");
        Long endCount = (Long) service.searchCooperationProject(0, searchBean, 0, 25).get("total");
        assertEquals(1L, startCount - endCount);
        // exception test
        try {
            service.deleteCooperationProject("th");
            fail("失败");
        } catch (Exception e) {
        }
        CooperatorService cooperatorService = (CooperatorService) CTX.getBean("cooperatorService");
        cooperatorService.deleteCooperator(cooperatorID + "");
    }
}
