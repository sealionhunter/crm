package com.ustcsoft.gs.crm.webui.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.FileTemplateNameBean;
import com.ustcsoft.gs.crm.webui.customer.bean.ProposalOrContractSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.ProposalOrContractDto;
import com.ustcsoft.gs.crm.webui.customer.service.ProposalOrContractService;
import com.ustcsoft.gs.crm.webui.customer.service.impl.ProposalOrContractServiceImpl;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

/**
 * test for ProposalOrContractServiceImpl
 * 
 * @author zhangqiuli
 */
public class ProposalOrContractServiceImplTest extends CRMTest {

    private static final String TEST1 = "测试一";
    private static final String TOTAL = "total";
    private static final String ITEMS = "items";
    private static final String DATE = "2012-10-10";
    private static final int THREE = 3;
    private static ProposalOrContractService pocs = null;
    private ProposalOrContractDto pocd = new ProposalOrContractDto();
    private ProposalOrContractSearchBean pocsb = new ProposalOrContractSearchBean();
    private Map<String, Object> map = new HashMap<String, Object>();

    @BeforeClass
    public static void setUpBeforeClass() {
        pocs = (ProposalOrContractService) CTX.getBean("proposalOrContractService");
    }

    @Before
    public void setUp() throws Exception {

        pocd.setDescriptions(TEST1);
        pocd.setFileTemplateName(TEST1);
        pocd.setIsAbolished(false);
        pocd.setProposalOrContractName(TEST1);
        pocd.setProposalOrContractObject(TEST1);
        pocd.setProposalOrContractAddDate(DATE);
        pocd.setProposalOrContractEditDate("");
        pocd.setProposalOrContractType(TEST1);
        pocd.setProposalOrContractValue(TEST1);
        pocd.setType(THREE);

        pocsb.setType(THREE);
        pocsb.setTextValue(TEST1);
        pocsb.setProposalOrContractName("测试三");
        pocsb.setProposalOrContractAddDateMin(DATE);
        pocsb.setProposalOrContractAddDateMax("");
        pocsb.setProposalOrContractEditDateMax(DATE);
        pocsb.setProposalOrContractEditDateMin("");
        pocsb.setProposalOrContractObject(TEST1);
        pocsb.setProposalOrContractType(TEST1);
    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * test
     * {@link ProposalOrContractServiceImpl# addOrUpdateProposalOrContract(com.ustcsoft.gs.crm.webui.customer.dto.ProposalOrContractDto)}
     * 
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testAddOrUpdateProposalOrContract() throws CRMDBException {

        map = pocs.getAllProposalOrContract(0, pocsb, 1, 25);
        final Long sizeStart = (Long) map.get(TOTAL);

        // test add
        pocs.addOrUpdateProposalOrContract(pocd);
        assertEquals(TEST1, pocd.getProposalOrContractName());

        pocd.setProposalOrContractName("~(@^_^@)~");
        pocd.setProposalOrContractID(0);
        pocd.setProposalOrContractEditDate("");
        pocs.addOrUpdateProposalOrContract(pocd);

        map = pocs.getAllProposalOrContract(0, pocsb, 1, 25);
        final List<ProposalOrContractDto> pocListOne = (List<ProposalOrContractDto>) map.get(ITEMS);

        assertEquals("~(@^_^@)~", pocListOne.get(1).getProposalOrContractName());
        assertEquals(TEST1, pocListOne.get(1).getFileTemplateName());

        // test edit
        pocd.setProposalOrContractID(pocListOne.get(1).getProposalOrContractID());
        pocd.setProposalOrContractName("客户</td>zjzh<td>~(@^_^@)~za管理");
        pocd.setProposalOrContractEditDate(DATE);

        pocs.addOrUpdateProposalOrContract(pocd);

        map = pocs.getAllProposalOrContract(0, pocsb, 1, 25);
        assertEquals(sizeStart + 2, map.get(TOTAL));
        final List<ProposalOrContractDto> pocList = (List<ProposalOrContractDto>) map.get(ITEMS);

        assertEquals("客户</td>zjzh<td>~(@^_^@)~za管理", pocList.get(1).getProposalOrContractName());
        assertEquals(DATE, pocd.getProposalOrContractEditDate());

    }

    /**
     * test getFileTemplateName method
     * {@link com.ustcsoft.gs.crm.webui.customer.service.impl. ProposalOrContractServiceImpl#getFileTemplateName(int)}
     * 
     * @throws CRMDBException
     */
    @Test
    public void testGetFileTemplateName() throws CRMDBException {

        final List<FileTemplateNameBean> nameList = pocs.getFileTemplateName(THREE);
        assertEquals(0, nameList.size());
    }

    /**
     * test method for
     * {@link com.ustcsoft.gs.crm.webui.customer.service.impl. ProposalOrContractServiceImpl#getAllProposalOrContract (int, com.ustcsoft.gs.crm.webui.customer.bean. ProposalOrContractSearchBean, int)}
     * 
     * @throws CRMDBException
     */
    @Test
    public void testGetAllProposalOrContract() throws CRMDBException {

        map = pocs.getAllProposalOrContract(0, pocsb, 1, 25);
        final Long sizeStart = (Long) map.get(TOTAL);
        map = pocs.getAllProposalOrContract(1, pocsb, 1, 25);
        final Long sizeStart1 = (Long) map.get(TOTAL);
        map = pocs.getAllProposalOrContract(2, pocsb, 1, 25);
        final Long sizeStart2 = (Long) map.get(TOTAL);

        pocd.setProposalOrContractName("测试六");
        pocs.addOrUpdateProposalOrContract(pocd);

        map = pocs.getAllProposalOrContract(0, pocsb, 1, 25);
        assertEquals(sizeStart + 1, map.get(TOTAL));
        map = pocs.getAllProposalOrContract(1, pocsb, 1, 25);
        assertEquals(sizeStart1 + 1, map.get(TOTAL));

        map = pocs.getAllProposalOrContract(2, pocsb, 1, 25);
        assertEquals((long) sizeStart2, map.get(TOTAL));

        pocsb.setProposalOrContractAddDateMax(DATE);
        pocsb.setProposalOrContractEditDateMin(DATE);
        map = pocs.getAllProposalOrContract(2, pocsb, 1, 25);
        assertEquals((long) sizeStart2, map.get(TOTAL));

        pocsb.setProposalOrContractAddDateMin("");
        pocsb.setProposalOrContractEditDateMax("");
        map = pocs.getAllProposalOrContract(2, pocsb, 1, 25);
        assertEquals((long) sizeStart2, map.get(TOTAL));

    }

    /**
     * test merhod for
     * {@link com.ustcsoft.gs.crm.webui.customer.service.impl. ProposalOrContractServiceImpl#checkProposalOrContractName (ProposalOrContractDto)}
     * 
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testCheckProposalOrContractName() throws CRMDBException {

        pocd.setProposalOrContractName(TEST1);
        pocd.setProposalOrContractID(0);

        assertEquals(false, pocs.checkProposalOrContractName(pocd));
        map = pocs.getAllProposalOrContract(0, pocsb, 1, 25);
        final List<ProposalOrContractDto> pocList = (List<ProposalOrContractDto>) map.get(ITEMS);
        pocd.setProposalOrContractID(pocList.get(0).getProposalOrContractID());
        assertEquals(true, pocs.checkProposalOrContractName(pocd));
    }

    /**
     * exception test method for
     * {@link com.ustcsoft.gs.crm.webui.customer.service.impl. ProposalOrContractServiceImpl #checkProposalOrContractName (ProposalOrContractDto)}
     * 
     * @throws NullPointerException
     * @throws CRMDBException
     */
    @Test(expected = NullPointerException.class)
    public void testCheckProposalOrContractNameException() throws NullPointerException,
            CRMDBException {
        pocs.checkProposalOrContractName(null);
    }

    /**
     * exception test
     * {@link com.ustcsoft.gs.crm.webui.customer.service.impl. ProposalOrContractServiceImpl #addOrUpdateProposalOrContract (ProposalOrContractDto)}
     * 
     * @throws CRMDBException
     */
    @Test(expected = CRMDBException.class)
    public void testAddOrUpdateProposalOrContractException() throws CRMDBException {
        pocd.setProposalOrContractAddDate("2012-10-1034er");
        pocd.setProposalOrContractID(0);
        pocd.setProposalOrContractName("1234567890qwerty" + "uiop张o(︶︿︶)o。。。。。。。....awey");
        pocs.addOrUpdateProposalOrContract(pocd);
    }

    /**
     * exception test
     * {@link com.ustcsoft.gs.crm.webui.customer.service.impl. ProposalOrContractServiceImpl#addOrUpdateProposalOrContract (ProposalOrContractDto)}
     * 
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Test(expected = CRMDBException.class)
    public void testAddOrUpdateProposalOrContractExceptionEdit() throws CRMDBException {

        map = pocs.getAllProposalOrContract(0, pocsb, 1, 25);
        final List<ProposalOrContractDto> pocList = (List<ProposalOrContractDto>) map.get(ITEMS);
        final ProposalOrContractDto pocd = pocList.get(0);
        pocd.setProposalOrContractEditDate(DATE);
        pocd.setProposalOrContractName("2012-10-1034er2012-10-1034er2"
                + "012-10-1034er2012-10-1034er2012-10-1034er");
        pocd.setProposalOrContractID(pocd.getProposalOrContractID());
        pocs.addOrUpdateProposalOrContract(pocd);
    }

    /**
     * exception test method for
     * {@link com.ustcsoft.gs.crm.webui.customer.service.impl. ProposalOrContractServiceImpl#deleteProposalOrContract(String)}
     * 
     * @throws CRMDBException
     */
    @Test(expected = CRMDBException.class)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void testDeleteProposalOrContractException() throws CRMDBException {
        pocs.deleteProposalOrContract("qqq");
    }

    /**
     * exception test method for {@link
     * com.ustcsoft.gs.crm.webui.customer.service.impl.
     * ProposalOrContractServiceImpl #getAllProposalOrContract(2,
     * ProposalOrContractSearchBean, int)}
     * 
     * @throws CRMDBException
     */
    @Test(expected = CRMDBException.class)
    public void testGetAllProposalOrContractExceptionOne() throws CRMDBException {
        pocsb.setProposalOrContractAddDateMin("2012-10-10dsg");
        pocsb.setProposalOrContractAddDateMax("");
        pocsb.setProposalOrContractEditDateMax("");
        pocsb.setProposalOrContractEditDateMin("");
        map = pocs.getAllProposalOrContract(2, pocsb, 1, 25);
    }

    /**
     * exception test method for {@link
     * com.ustcsoft.gs.crm.webui.customer.service.impl.
     * ProposalOrContractServiceImpl #getAllProposalOrContract(1,
     * ProposalOrContractSearchBean, int)}
     * 
     * @throws CRMDBException
     */
    @Test(expected = CRMDBException.class)
    public void testGetAllProposalOrContractExceptionTwo() throws CRMDBException {
        map = pocs.getAllProposalOrContract(1, pocsb, -1, 25);
    }

    /**
     * exception test method for {@link
     * com.ustcsoft.gs.crm.webui.customer.service.impl.
     * ProposalOrContractServiceImpl #getAllProposalOrContract(0,
     * ProposalOrContractSearchBean, int)}
     * 
     * @throws CRMDBException
     */
    @Test(expected = CRMDBException.class)
    public void testGetAllProposalOrContractExceptionThree() throws CRMDBException {
        map = pocs.getAllProposalOrContract(0, pocsb, -1, 25);
    }

    /**
     * test method for
     * {@link com.ustcsoft.gs.crm.webui.customer.service.impl. ProposalOrContractServiceImpl #deleteProposalOrContract(String)}
     * 
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Test
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void testDeleteProposalOrContract() throws CRMDBException {

        map = pocs.getAllProposalOrContract(0, pocsb, 1, 25);
        assertEquals(3L, map.get(TOTAL));
        final List<ProposalOrContractDto> pocList = (List<ProposalOrContractDto>) map.get(ITEMS);

        // delete more
        final String IDs = pocList.get(0).getProposalOrContractID() + ","
                + pocList.get(1).getProposalOrContractID() + ","
                + pocList.get(2).getProposalOrContractID();
        pocs.deleteProposalOrContract(IDs);

        map = pocs.getAllProposalOrContract(0, pocsb, 1, 25);
        assertEquals(0L, map.get(TOTAL));

        pocd.setProposalOrContractID(0);
        pocd.setProposalOrContractName("<td>测试四");
        pocd.setProposalOrContractEditDate("");
        pocs.addOrUpdateProposalOrContract(pocd);

        map = pocs.getAllProposalOrContract(0, pocsb, 1, 25);
        assertEquals(1L, map.get(TOTAL));

        // delete only one
        final String pocid = "" + pocd.getProposalOrContractID();
        pocs.deleteProposalOrContract(pocid);

        map = pocs.getAllProposalOrContract(0, pocsb, 1, 25);
        assertEquals(0L, map.get(TOTAL));
    }

}
