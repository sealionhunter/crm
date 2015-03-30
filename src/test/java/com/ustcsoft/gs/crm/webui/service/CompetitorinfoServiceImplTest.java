package com.ustcsoft.gs.crm.webui.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.CompetitorSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.CompetitorinfoDto;
import com.ustcsoft.gs.crm.webui.customer.service.CompetitorinfoService;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

/**
 * @author weijinmei test method of CompetitorinfoService
 */
public class CompetitorinfoServiceImplTest extends CRMTest {

    private static CompetitorinfoService test = null;
    private static int competitorinfoId = 0;
    private static CompetitorinfoDto compe = new CompetitorinfoDto();
    private static CompetitorSearchBean comSearchBean = new CompetitorSearchBean();
    private static String searchText = null;

    @BeforeClass
    public final static void setUpBeforeClass() throws Exception {
        test = (CompetitorinfoService) CTX.getBean("competitorinfoService");
        compe.setCompetitorName("lalala");
        compe.setCompetitorDescription("不错");
        compe.setCompetitorDetail("");
        compe.setCompetitorField("java");
        compe.setCompetitorFoundDate("2012-12-13");
        compe.setCompetitorLocation("合肥");
        compe.setCompetitorMoney("01");
        compe.setCompetitorPeople("01");
        compe.setCompetitorProperty("01");
        compe.setCompetitorType("01");
        compe.setIsAbolished(false);
        searchText = "la";
        String[] all = { "00" };
        comSearchBean.setCompetitorSearchText(searchText);
        comSearchBean.setCompetitorField("ja");
        comSearchBean.setCompetitorLocation("");
        comSearchBean.setCompetitorMoney(all);
        comSearchBean.setCompetitorName("");
        comSearchBean.setCompetitorPeople(all);
        comSearchBean.setCompetitorProperty(all);
        comSearchBean.setCompetitorType("01");

    }

    @Rule
    public ExpectedException exceptededEx = ExpectedException.none();

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void TestUpdateCompetitorinfo() throws Exception {
        int start = (int) test.getCompetitorinfoDao().getCompetitorinfoSize("01");
        test.updateCompetitorinfo(compe);
        competitorinfoId = compe.getCompetitorInfoId();
        int end = (int) test.getCompetitorinfoDao().getCompetitorinfoSize("01");
        assertEquals(1, end - start);
    }

    @Test
    public void TestUpdateCompetitorinfoException() throws Exception {
        exceptededEx.expect(CRMDBException.class);
        compe.setCompetitorInfoId(22222);
        compe.setCompetitorLocation("安徽合肥");
        compe.setCompetitorFoundDate("1990-12-20");
        compe.setCompetitorProperty("03");
        compe.setCompetitorMoney("02");
        compe.setCompetitorPeople("03");
        compe.setCompetitorField("软件研发");
        compe.setCompetitorType("01");
        compe.setCompetitorDetail("你好111");
        compe.setCompetitorDescription("你好2222");
        test.updateCompetitorinfo(compe);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void TestGetAllCompetitor() throws Exception {
        // test load
        comSearchBean.setCompetitorType("01");
        super.map = test.getAllCompetitor(0, comSearchBean, 1, 25);
        int total = Integer.parseInt(super.map.get("results").toString());
        List<?> list1 = (List<?>) map.get("items");
        boolean bool = total >= list1.size();
        Assert.assertEquals(bool, true);

        // test simpleQuery
        super.map = test.getAllCompetitor(1, comSearchBean, 1, 25);
        List<?> list2 = (List<?>) map.get("items");
        for (int i = 0; i < list2.size(); i++) {
            boolean boolBefore = true;
            Map<String, Object> result = (Map<String, Object>) list2.get(i);
            boolean bool1 = ((String) result.get("competitorName")).contains(searchText);
            boolean bool2 = ((String) result.get("competitorField")).contains(searchText);
            boolean bool3 = ((String) result.get("competitorLocation")).contains(searchText);
            boolean bool4 = ((String) result.get("competitorFoundDate")).contains(searchText);
            boolean bool5 = ((String) result.get("competitorProperty")).contains(searchText);
            String competitorPeople = (String) result.get("competitorPeople");
            boolean bool6 = competitorPeople == null || !competitorPeople.contains(searchText);
            boolean bool7 = ((String) result.get("competitorMoney")).contains(searchText);
            boolean boolAfter = bool1 || bool2 || bool3 || bool4 || bool5 || !bool6 || bool7;
            Assert.assertEquals(boolAfter, boolBefore);

        }

        // test superQuery
        super.map = test.getAllCompetitor(2, comSearchBean, 1, 25);
        List<?> list3 = (List<?>) map.get("items");
        for (int i = 0; i < list3.size(); i++) {
            boolean bool31 = true;
            Map<String, Object> result = (Map<String, Object>) list3.get(i);
            bool31 = ((String) result.get("competitorField")).contains("ja");
            Assert.assertEquals(bool31, true);
        }

    }

    @Test
    public void TestJudgeCompetitorName() throws Exception {
        CompetitorinfoDto compe1 = new CompetitorinfoDto();
        compe1.setCompetitorName("nihao");
        compe1.setCompetitorLocation("安徽合肥");
        compe1.setCompetitorFoundDate("1990-12-19");
        compe1.setCompetitorProperty("02");
        compe1.setCompetitorMoney("03");
        compe1.setCompetitorPeople("04");
        compe1.setCompetitorField("软件研发");
        compe1.setCompetitorType("01");
        compe1.setCompetitorDetail("你好111");
        compe1.setCompetitorDescription("你好2222");
        boolean bool1 = test.judgeCompetitorName(compe1);
        Assert.assertEquals(bool1, false);
        test.updateCompetitorinfo(compe1);
        int id = compe1.getCompetitorInfoId();
        String deleteId = "[" + id + "]";
        boolean bool2 = test.judgeCompetitorName(compe1);
        Assert.assertEquals(bool2, false);
        compe1.setCompetitorName("lalala");
        boolean bool3 = true;
        bool3 = test.judgeCompetitorName(compe1);
        Assert.assertEquals(bool3, true);
        test.deleteCompetitorinfoAndCprAnalysis(deleteId);

    }

    @Test
    public void TestDeleteCompetitorinfo() throws Exception {
        int start = (int) test.getCompetitorinfoDao().getCompetitorinfoSize("01");
        String competitorInfoIds = "[" + competitorinfoId + "]";
        test.deleteCompetitorinfoAndCprAnalysis(competitorInfoIds);
        int end = (int) test.getCompetitorinfoDao().getCompetitorinfoSize("01");
        assertEquals(1, start - end);

    }

    @Test
    public void TestDeleteCompetitorinfoException() throws Exception {
        exceptededEx.expect(CRMDBException.class);
        String competitorInfoIds = "[nihao]";
        test.deleteCompetitorinfoAndCprAnalysis(competitorInfoIds);
    }

}
