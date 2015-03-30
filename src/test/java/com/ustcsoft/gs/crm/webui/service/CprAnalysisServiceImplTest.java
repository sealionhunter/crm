package com.ustcsoft.gs.crm.webui.service;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.CompetitorSearchBean;
import com.ustcsoft.gs.crm.webui.customer.bean.CprNameChooseBean;
import com.ustcsoft.gs.crm.webui.customer.dto.CompetitorinfoDto;
import com.ustcsoft.gs.crm.webui.customer.dto.CprAnalysisDto;
import com.ustcsoft.gs.crm.webui.customer.service.CompetitorinfoService;
import com.ustcsoft.gs.crm.webui.customer.service.CprAnalysisService;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

/**
 * 
 * @author libaoshan
 * 
 */
public class CprAnalysisServiceImplTest extends CRMTest {

    /** Used for test */
    private static CprAnalysisService cprAnalysisServiceTest = null;

    private Map<String, Object> map = null;

    private CprAnalysisDto cprAnalysisDto = null;

    private List<CprNameChooseBean> cprNameList = null;

    private int cprAnalysisID = 0;

    /**
     * 
     * @throws Exception
     */
    @BeforeClass
    public static void beforeClass() throws Exception {
        cprAnalysisServiceTest = (CprAnalysisService) CTX.getBean("cprAnalysisService");
        CompetitorinfoService test = (CompetitorinfoService) CTX.getBean("competitorinfoService");
        CompetitorSearchBean comSearchBean = new CompetitorSearchBean();
        comSearchBean.setCompetitorType("1");
        Map<String, Object> compMap = test.getAllCompetitor(0, comSearchBean, 1, 25);
        long compMapSize = (Long) compMap.get("results");
        if (compMapSize == 0L) {
            CompetitorinfoDto compe = new CompetitorinfoDto();
            compe.setCompetitorName("科大恒星123");
            compe.setCompetitorLocation("安徽合肥");
            compe.setCompetitorFoundDate("1990-12-19");
            compe.setCompetitorProperty("02");
            compe.setCompetitorMoney("03");
            compe.setCompetitorPeople("04");
            compe.setCompetitorField("软件研发");
            compe.setCompetitorType("1");
            compe.setCompetitorDetail("你好111");
            compe.setCompetitorDescription("你好2222");
            test.updateCompetitorinfo(compe);
        }
    }

    /**
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
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
     * @throws MyException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetAllAnalysis() throws CRMDBException {
        Boolean flag = false;
        map = cprAnalysisServiceTest.getAnalysis(1, 0, 25);
        long total = (Long) map.get(CRMConstant.TOTAL);
        List<CprAnalysisDto> cprAnalysisDtos = (List<CprAnalysisDto>) map.get("CprAnalysis");
        long size = cprAnalysisDtos.size();
        if (total >= size) {
            flag = true;
        }
        Assert.assertEquals(flag, true);
    }

    /**
     * 
     * @throws MyException
     */
    @Test
    public void testUpdateCprAnalysisInfo() throws CRMDBException {
        map = cprAnalysisServiceTest.getAnalysis(1, 0, 25);
        long start = (Long) map.get(CRMConstant.TOTAL);
        cprAnalysisDto = getCprAnalysisDto();
        cprAnalysisServiceTest.updateCprAnalysis(cprAnalysisDto);
        cprAnalysisID = cprAnalysisDto.getCprAnalysisID();
        map = cprAnalysisServiceTest.getAnalysis(1, 0, 25);
        long end = (Long) map.get(CRMConstant.TOTAL);
        Assert.assertEquals(1L, end - start);
    }

    /**
     * 
     * @throws MyException
     */
    @Test
    public void testGetAllCprName() throws CRMDBException {
        Boolean flag = false;
        cprNameList = cprAnalysisServiceTest.getAllCprName();
        if (cprNameList.size() >= 1) {
            flag = true;
        }
        Assert.assertEquals(flag, true);
    }

    /**
     * 
     * @throws MyException
     */
    @Test
    public void testDeleteCprAnalysis() throws CRMDBException {
        map = cprAnalysisServiceTest.getAnalysis(1, 0, 25);
        long start = (Long) map.get(CRMConstant.TOTAL);
        cprAnalysisDto = getCprAnalysisDto();
        cprAnalysisServiceTest.updateCprAnalysis(cprAnalysisDto);
        cprAnalysisID = cprAnalysisDto.getCprAnalysisID();
        String deletedId = cprAnalysisID + "";
        cprAnalysisServiceTest.deleteCprAnalysis(deletedId);
        map = cprAnalysisServiceTest.getAnalysis(1, 0, 25);
        long end = (Long) map.get(CRMConstant.TOTAL);
        Assert.assertEquals(0L, end - start);
    }

    /**
     * 
     * @throws MyException
     */
    @Test
    public void testGetAllAnalysisException() {
        Boolean flag = false;
        try {
            map = cprAnalysisServiceTest.getAnalysis(-1, 0, 25);
        } catch (Exception e) {
            flag = true;
        }
        Assert.assertEquals(flag, true);
    }

    /**
     * 
     * @throws MyException
     */
    @Test
    public void testUpdateCprAnalysisInfoException() {
        Boolean flag = false;
        try {
            CprAnalysisDto cprAnalysisDto = new CprAnalysisDto();
            cprAnalysisServiceTest.updateCprAnalysis(cprAnalysisDto);
        } catch (Exception e) {
            flag = true;
        }
        Assert.assertEquals(flag, true);
    }

    /**
     * 
     * @throws MyException
     */
    @Test
    public void testDeleteCprAnalysisException() {
        Boolean flag = false;
        try {
            cprAnalysisServiceTest.deleteCprAnalysis("abc");
        } catch (Exception e) {
            flag = true;
        }
        Assert.assertEquals(flag, true);
    }

    /**
     * 
     * @return cprAnalysisDto
     */
    public CprAnalysisDto getCprAnalysisDto() {
        CprAnalysisDto cprAnalysisDto = new CprAnalysisDto();
        cprAnalysisDto.setCompetitorInfoId(1);
        cprAnalysisDto.setArea("01");
        cprAnalysisDto.setAbility("勉強します");
        cprAnalysisDto.setTargets("更好");
        cprAnalysisDto.setStrategy("好好的");
        cprAnalysisDto.setPrediction("<tr>");
        cprAnalysisDto.setAdvantage("领导力雄厚");
        cprAnalysisDto.setDisadvantage("新生力比较多，经验不足");
        cprAnalysisDto.setAdvAnalysis("<td>");
        cprAnalysisDto.setDisadvAnalysis("<table>");
        cprAnalysisDto.setOthers("其他");
        cprAnalysisDto.setCompositeComp(90);
        cprAnalysisDto.setCompositeDefense(90);
        cprAnalysisDto.setAdvice("建议");
        cprAnalysisDto.setIsAbolished("False");
        return cprAnalysisDto;
    }
}
