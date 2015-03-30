/**
 * 
 */
package com.ustcsoft.gs.crm.webui.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import jp.co.dgic.testing.common.virtualmock.MockObjectManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.ContractSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dao.ContractDao;
import com.ustcsoft.gs.crm.webui.customer.dto.ContractDto;
import com.ustcsoft.gs.crm.webui.customer.service.ContractService;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

/**
 * @author zhangchuanhong
 * 
 */
public class ContractServiceTest extends CRMTest {
    private static ContractService contractService = null;
    private static ContractDto contractDto = new ContractDto();
    private static ContractDto errorContractDto = new ContractDto();
    private static ContractSearchBean contractSearchBean = new ContractSearchBean();

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        contractService = (ContractService) CTX.getBean("contractService");
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        contractDto.setContractID(11);
        contractDto.setContractName("合同");
        contractDto.setContractValue("合同内容真的很多啊");
        contractDto.setFileTemplateName("模板名字");
        contractDto.setIsAbolished(false);
        contractDto.setOrderID(1);
        contractDto.setPayEndTime("2013-10-01");
        contractDto.setPayType("000800010001");
        errorContractDto.setContractName("合同");
        errorContractDto.setContractValue("合同内容真的很多啊");
        errorContractDto.setFileTemplateName("模板名字");
        errorContractDto.setIsAbolished(false);
        errorContractDto.setOrderID(1);
        errorContractDto.setPayEndTime("2013-10-01sa");
        errorContractDto.setPayType("000800010001");
        contractSearchBean.setContractName("czxc");
        contractSearchBean.setFileTemplateName("dsad");
        contractSearchBean.setOrderID("2");
        contractSearchBean.setPayEndTimeMax("2013-10");
        contractSearchBean.setPayEndTimeMin("2013-06");
        contractSearchBean.setPayType("000800010002");
        contractSearchBean.setTextValue("2013-09");
    }

    @Test
    public void testAddOrUpdateContract() throws Exception {
        contractService.addOrUpdateContract(contractDto);
    }

    @Test
    public void testAddOrUpdateContractException() throws Exception {
        try {
            contractService.addOrUpdateContract(errorContractDto);
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

    @Test
    public void testDeleteContract() throws Exception {
        contractService.deleteContract("11");
    }

    @Test
    public void testGetAllContract() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map = contractService.getAllContract(0, contractSearchBean, 1, 10);
        int count = (Integer) map.get("total");
        assertEquals(13, count);
        map = contractService.getAllContract(1, contractSearchBean, 1, 10);
        count = (Integer) map.get("total");
        assertEquals(3, count);
        map = contractService.getAllContract(2, contractSearchBean, 1, 10);
        count = (Integer) map.get("total");
        assertEquals(1, count);
    }

    @Test
    public void testGetContractEvent() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map = contractService.getContractEvent(3);
        int eventID = (Integer) map.get("eventID");
        assertEquals(5, eventID);
        map = contractService.getContractEvent(1);
        eventID = (Integer) map.get("eventID");
        assertEquals(0, eventID);
    }

    @Test
    public void testNameOrOrderIdIsExit() throws Exception {
        assertEquals(true, contractService.NameOrOrderIdIsExit("czxc"));
    }

    @Test
    public void testNameOrOrderIdIsExitException() throws Exception {
        MockObjectManager.initialize();
        MockObjectManager.setReturnValueAtAllTimes(ContractDao.class, "NameOrOrderIdIsExit",
                new EmptyResultDataAccessException(0));
        try {
            contractService.NameOrOrderIdIsExit("czxc");
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

    @Test
    public void testUpdateContractEvent() throws Exception {
        contractDto.setContractID(0);
        contractService.updateContractEvent(3, "dsadas", contractDto, userID);
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }
}
