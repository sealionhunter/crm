package com.ustcsoft.gs.crm.webui.service;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ustcsoft.gs.crm.webui.customer.bean.CooperatorSearchBean;
import com.ustcsoft.gs.crm.webui.customer.bean.CooperatorShowBean;
import com.ustcsoft.gs.crm.webui.customer.dto.CooperationProjectDto;
import com.ustcsoft.gs.crm.webui.customer.dto.CooperatorDto;
import com.ustcsoft.gs.crm.webui.customer.service.CooperationProjectService;
import com.ustcsoft.gs.crm.webui.customer.service.CooperatorService;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

public class CooperatorServiceImplTest extends CRMTest {
    private static CooperatorService service = null;
    private static CooperatorSearchBean searchBean = new CooperatorSearchBean();
    private static CooperatorDto coo = new CooperatorDto();
    private Map<String, Object> map = null;
    private List<CooperatorShowBean> list = null;
    private static int id = 0;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        service = (CooperatorService) CTX.getBean("cooperatorService");
    }

    @Test
    public void testUpdateCooperator() throws Exception {
        coo.setCooperatorID(0);
        coo.setCooperatorName("测试用值");
        coo.setCooperatorCity("city");
        coo.setCooperatorIndustry("01");
        coo.setCooperatorScale("1");
        coo.setCooperatorType("01");
        coo.setCooperatorTelephone("13965895847");
        coo.setCooperatorEmail("xjlong@foxmail.com");
        coo.setCooperatorRemark("welcome");
        coo.setCooperatorDetail("welcome");
        coo.setCooperatorWebsite("www.ahnu.edu.cn");
        coo.setCooperatorFax("0553-2568258");
        coo.setCooperatorAddress("ahnu");
        coo.setIsAbolished(false);
        // search total before add
        Long startCount = (Long) service.getAllCooperatorShow(0, searchBean, 1, 25).get("total");
        service.updateCooperator(coo);
        Long endCount = (Long) service.getAllCooperatorShow(0, searchBean, 1, 25).get("total");
        assertEquals(1L, endCount - startCount);
        // add cooperation project
        CooperationProjectDto cpd = new CooperationProjectDto();
        cpd.setCooperatorID(coo.getCooperatorID());
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
        CooperationProjectService projectService = (CooperationProjectService) CTX
                .getBean("cooperationProjectService");
        projectService.updateCooperationProject(cpd);
        // exception test
        CooperatorDto coo1 = new CooperatorDto();
        try {
            service.updateCooperator(coo1);
            fail("失败");
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetAllCooperatorShow() throws Exception {
        map = service.getAllCooperatorShow(0, searchBean, 1, 25);
        Assert.assertNotNull(map);
        // simple searchText = null
        searchBean.setSearchText(null);
        map = service.getAllCooperatorShow(1, searchBean, 1, 25);
        Assert.assertNotNull(map);
        // simple search test
        searchBean.setSearchText("测试用值");
        map = service.getAllCooperatorShow(1, searchBean, 1, 25);
        list = (List<CooperatorShowBean>) map.get("items");
        Map<String, Object> result = null;
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                result = (Map<String, Object>) list.get(i);
                boolean b1 = ((String) result.get("industryDisplay")).contains("测试用值");
                boolean b2 = ((String) result.get("scaleDisplay")).contains("测试用值");
                boolean b3 = ((String) result.get("typeDisplay")).contains("测试用值");
                boolean b4 = Long.toString((Long) result.get("projectNumber")).contains("测试用值");
                boolean b5 = false;
                if ((String) result.get("cooperationDate") != null) {
                    b5 = ((String) result.get("cooperationDate")).contains("测试用值");
                }
                boolean b6 = ((String) result.get("cooperatorName")).contains("测试用值");
                boolean b7 = ((String) result.get("cooperatorCity")).contains("测试用值");
                boolean b8 = ((String) result.get("cooperatorTelephone")).contains("测试用值");
                boolean b9 = ((String) result.get("cooperatorEmail")).contains("测试用值");
                boolean b10 = ((String) result.get("cooperatorFax")).contains("测试用值");
                boolean b11 = ((String) result.get("cooperatorWebsite")).contains("测试用值");
                boolean b12 = ((String) result.get("cooperatorAddress")).contains("测试用值");
                boolean b13 = ((String) result.get("cooperatorRemark")).contains("测试用值");
                boolean b14 = ((String) result.get("cooperatorDetail")).contains("测试用值");
                Assert.assertEquals(true, b1 || b2 || b3 || b4 || b5 || b6 || b7 || b8 || b9 || b10
                        || b11 || b12 || b13 || b14);
            }
        }
        // advanced search test
        searchBean.setCooperatorNameSearch("测试用值");
        String industry = "01";
        String scale = "1";
        searchBean.setCooperatorIndustrySearch(industry);
        searchBean.setCooperatorScaleSearch(scale);
        searchBean.setCooperationTimesMin(0L);
        searchBean.setCooperationTimesMax(1L);
        searchBean.setCooperationDateMin("2012-01-25");
        searchBean.setCooperationDateMax("2012-12-25");
        map = service.getAllCooperatorShow(2, searchBean, 1, 25);
        list = (List<CooperatorShowBean>) map.get("items");
        if (list.size() != 0) {
            DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i < list.size(); i++) {
                result = (Map<String, Object>) list.get(i);

                long cooperationDate = date.parse((String) result.get("cooperationDate")).getTime();
                Assert.assertEquals(true, cooperationDate >= date.parse("2012-01-25").getTime()
                        && cooperationDate <= date.parse("2012-12-25").getTime());
                Assert.assertEquals(true, ((String) result.get("cooperatorName")).contains("测试用值"));
                Assert.assertEquals("01", result.get("cooperatorIndustry"));
                Assert.assertEquals("1", result.get("cooperatorScale"));
                Assert.assertEquals(
                        true,
                        (Long) result.get("projectNumber") >= 0L
                                && (Long) result.get("projectNumber") <= 1L);
            }
        }
        // exception test
        searchBean.setCooperationDateMax("invalidDate");
        try {
            service.getAllCooperatorShow(2, searchBean, 1, 25);
            fail("失败");
        } catch (Exception e) {
        }
    }

    @Test
    public void testCheckNameExisted() throws Exception {
        id = coo.getCooperatorID();
        coo.setCooperatorID(id);
        coo.setCooperatorName("测试用值");
        assertEquals(false, service.checkNameExisted(coo));
        coo.setCooperatorID(id + 1);
        assertEquals(true, service.checkNameExisted(coo));
    }

    @Test
    public void testDelCooperator() throws Exception {
        Long startCount = (Long) service.getAllCooperatorShow(0, searchBean, 1, 25).get("total");
        service.deleteCooperator(id + "");
        map = service.getAllCooperatorShow(0, searchBean, 1, 25);
        Long endCount = (Long) service.getAllCooperatorShow(0, searchBean, 1, 25).get("total");
        assertEquals(1L, startCount - endCount);
        // exception test
        try {
            service.deleteCooperator("test");
            fail("失败");
        } catch (Exception e) {
        }
    }
}
