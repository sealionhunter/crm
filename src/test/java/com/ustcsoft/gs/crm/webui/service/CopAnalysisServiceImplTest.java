package com.ustcsoft.gs.crm.webui.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.dto.CopAnalysisDto;
import com.ustcsoft.gs.crm.webui.customer.service.CopAnalysisService;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

/**
 * class CopAnalysisServiceImpl junit test.
 * 
 * @author xujueyin
 * 
 */
public class CopAnalysisServiceImplTest extends CRMTest {

    private static CopAnalysisService service = null;

    private Map<String, Object> map = null;

    private CopAnalysisDto copAnalysis = new CopAnalysisDto();

    private int cooperatorID = 1;

    /** hibernate operation */
    private static HibernateTemplate hibernate = null;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @BeforeClass
    public static void beforeClass() throws Exception {
        service = (CopAnalysisService) CTX.getBean("copAnalysisService");
        // get hibernateTemplate and execute some operations
        hibernate = (HibernateTemplate) CTX.getBean("hibernateTemplate");
    }

    @Before
    public void setUp() {
        copAnalysis.setCooperatorID(cooperatorID);
        copAnalysis.setAdvantageField("IT电力");
        copAnalysis.setDisadvantageField("软件");
        copAnalysis.setManagementAbility("01");
        copAnalysis.setResponseSpeed("01");
        copAnalysis.setTrustDegree("01");
        copAnalysis.setEngLevelEvaluation("技术工艺高");
        copAnalysis.setCooperationSummarize("合作愉快");
        copAnalysis.setComphsAnalysis("值得合作");
        copAnalysis.setDescriptions("无");
        copAnalysis.setCreateTime("2012-10-19");
    }

    @After
    public void tearDown() throws Exception {
        hibernate.delete(copAnalysis);
    }

    private long getTotal() throws Exception {
        map = service.getAllCopAnalysis(0, 25);
        return (Long) map.get(CRMConstant.TOTAL);
    }

    @SuppressWarnings("unchecked")
    private List<Object> getList() throws Exception {
        map = service.getAllCopAnalysis(0, 25);
        return (List<Object>) map.get(CRMConstant.ITEMS);
    }

    /**
     * test method saveOrUpdateCopAnalysis
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testSaveOrUpdateCopAnalysis() throws Exception {
        /*
         * test add cooperator analysis
         */
        CopAnalysisDto cop = new CopAnalysisDto();
        long start = getTotal();
        cop.setCooperatorID(2);
        cop.setAdvantageField("IT电力");
        cop.setDisadvantageField("软件");
        cop.setManagementAbility("01");
        cop.setResponseSpeed("01");
        cop.setTrustDegree("01");
        cop.setEngLevelEvaluation("技术工艺高");
        cop.setCooperationSummarize("合作愉快");
        cop.setComphsAnalysis("值得合作");
        cop.setDescriptions("无");
        service.saveOrUpdateCopAnalysis(cop);
        long end = getTotal();
        assertEquals(1L, end - start);

        /*
         * test update cooperator analysis
         */
        start = getTotal();
        cop.setAdvantageField("IT电力_改变后");
        cop.setDisadvantageField("软件_改变后");
        service.saveOrUpdateCopAnalysis(cop);
        end = getTotal();
        List<Object> list = getList();
        Map<String, Object> result = (Map<String, Object>) list.get(list.size() - 1);
        assertEquals("IT电力_改变后", result.get("advantageField"));
        assertEquals("软件_改变后", result.get("disadvantageField"));
        assertEquals(0L, end - start);

        hibernate.delete(cop);
    }

    /**
     * test method getAllCopAnalysis
     * 
     * @throws Exception
     */
    @Test
    public void testGetAllCopAnalysis() throws Exception {
        map = service.getAllCopAnalysis(0, 25);
        assertNotNull(map);
        long start = (Long) map.get(CRMConstant.TOTAL);
        hibernate.save(copAnalysis);
        map = service.getAllCopAnalysis(0, 25);
        long end = (Long) map.get(CRMConstant.TOTAL);
        assertEquals(1L, end - start);
    }

    /**
     * test method getCopAnalysisByID
     * 
     * @throws Exception
     */
    @Test
    public void testGetCopAnalysisByID() throws Exception {
        hibernate.save(copAnalysis);
        map = service.getCopAnalysisByID(cooperatorID, 0);
        long number = ((List<?>) map.get("items")).size();
        long count = (Long) hibernate.find(
                "select count(*) from CopAnalysisDto as cop where cop.isAbolished = 0 and cop.cooperatorID = "
                        + cooperatorID).get(0);
        assertEquals(count, number);
    }

    /**
     * test method deleteCopAnalysis
     * 
     * @throws Exception
     */
    @Test
    public void testDeleteCopAnalysis() throws Exception {
        long start = getTotal();
        hibernate.save(copAnalysis);
        String ids = String.valueOf(copAnalysis.getCopAnalysisID());
        service.deleteCopAnalysis(ids);
        long end = getTotal();
        assertEquals(start, end);
    }

    /**
     * test method getAllCopAnalysis throws CRMDBException
     * 
     * @throws Exception
     */
    @Test(expected = CRMDBException.class)
    public void testGetAllCopAnalysisThrowsCRMDBException() throws Exception {
        service.getAllCopAnalysis(-1, 25);
    }

    /**
     * test method saveOrUpdateCopAnalysis throws CRMDBException
     * 
     * @throws Exception
     */
    @Test(expected = CRMDBException.class)
    public void testSaveOrUpdateCopAnalysisThrowsCRMDBException() throws Exception {
        CopAnalysisDto copAnalysis = new CopAnalysisDto();
        service.saveOrUpdateCopAnalysis(copAnalysis);
    }

    /**
     * test method deleteCopAnalysis throws CRMDBException
     * 
     * @throws Exception
     */
    @Test(expected = CRMDBException.class)
    public void testDeleteCopAnalysisThrowsCRMDBException() throws Exception {
        service.deleteCopAnalysis("a, b");
    }

    /**
     * test method getCopAnalysisByID throws CRMDBException
     * 
     * @throws Exception
     */
    @Test(expected = CRMDBException.class)
    public void testGetCopAnalysisByIDThrowsCRMDBException() throws Exception {
        service.getCopAnalysisByID(-1, -1);
    }
}