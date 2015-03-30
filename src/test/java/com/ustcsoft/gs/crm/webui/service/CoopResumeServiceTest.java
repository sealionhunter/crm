package com.ustcsoft.gs.crm.webui.service;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.CoopResumeSearchBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.customer.dao.CoopResumeDao;
import com.ustcsoft.gs.crm.webui.customer.dto.CoopResumeDto;
import com.ustcsoft.gs.crm.webui.customer.dto.CustomerDto;
import com.ustcsoft.gs.crm.webui.customer.service.CoopResumeService;
import com.ustcsoft.gs.crm.webui.customer.service.CustomerService;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

public class CoopResumeServiceTest extends CRMTest {

    private CoopResumeService coopResumeService = null;
    private CustomerService customerService = null;
    private CustomerDto customerDto = new CustomerDto();
    private CoopResumeDto coopResumeDto = new CoopResumeDto();
    private CoopResumeDao coopResumeDao = null;
    private String searchText = null;
    CoopResumeSearchBean searchBean = new CoopResumeSearchBean();
    private static int customerID = 0;
    private static int deleteId = 0;

    @Before
    public void setUp() throws Exception {
        coopResumeService = (CoopResumeService) CTX.getBean("coopResumeService");
        customerService = (CustomerService) CTX.getBean("customerService");
        coopResumeDao = (CoopResumeDao) CTX.getBean("coopResumeDao");

        customerDto.setCustomerAddr("合肥市");
        customerDto.setCustomerName("恒星");
        customerDto.setCustomerStatement("1");
        customerDto.setCustomerType("1");
        customerDto.setFee("1");
        customerDto.setHolder(1);
        customerDto.setIndustry("01");
        customerDto.setScale("1");
        customerDto.setValueEvaluate("1");

        coopResumeDto.setExpBeginDate("2012-12-11");
        coopResumeDto.setExpEndDate("2012-12-11");
        coopResumeDto.setPeopleNumber(11);
        coopResumeDto.setProjectScale("1.1");
        coopResumeDto.setPrincipalWe("typ");
        coopResumeDto.setPrincipalThey("fujisann");
        coopResumeDto.setProjectName("客户关系管理");
        coopResumeDto.setProjectType("1");

        searchText = "国";
        String[] all = { "00" };
        searchBean.setProjectType(all);
        searchBean.setProjectName("客户");

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddOrUpdateCoopResume() throws CRMDBException {
        customerService.updateCustomer(customerDto);
        customerID = customerDto.getCustomerID();
        coopResumeDto.setCustomerID(customerID);
        coopResumeService.addOrUpdateCoopResume(coopResumeDto);
        deleteId = coopResumeDto.getCoopResumeID();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testSearchOrGetAllCoopResume() throws CRMDBException {
        super.map = coopResumeService.searchOrGetAllCoopResume(0, null, 1, customerID, 20);
        int total = Integer.parseInt(map.get("total").toString());
        List<?> list = (List<?>) map.get("items");
        boolean bool = total >= list.size();
        Assert.assertEquals(bool, true);
        super.map = coopResumeService.searchOrGetAllCoopResume(1, searchBean, 1, customerID, 20);
        List<Map<String, Object>> list1 = (List<Map<String, Object>>) map.get("items");
        for (Map<String, Object> result : list1) {
            boolean bool1 = true;
            String descriptions = (String) result.get("descriptions");
            boolean b1 = !(descriptions == null || !descriptions.contains(searchText));
            boolean b2 = ((String) result.get("projectName")).contains(searchText);
            boolean b3 = ((String) result.get("projectTypeName")).contains(searchText);
            boolean b4 = ((String) result.get("expBeginDate")).contains(searchText);
            boolean b5 = ((String) result.get("expEndDate")).contains(searchText);
            String endDate = (String) result.get("endDate");
            boolean b6 = !(endDate == null || !endDate.contains(searchText));
            String beginDate = (String) result.get("beginDate");
            boolean b7 = !(beginDate == null || !beginDate.contains(searchText));
            boolean b8 = ((String) result.get("peopleNumber")).contains(searchText);
            boolean b9 = ((String) result.get("principalThey")).contains(searchText);
            boolean b10 = ((String) result.get("principalWe")).contains(searchText);
            boolean b11 = ((String) result.get("projectDetail")).contains(searchText);

            bool1 = b1 || b2 || b3 || b4 || b5 || b6 || b7 || b8 || b9 || b10 || b11;
            Assert.assertEquals(bool1, true);
        }

        super.map = coopResumeService.searchOrGetAllCoopResume(2, searchBean, 1, customerID, 20);
        List<Map<String, Object>> list2 = (List<Map<String, Object>>) map.get("items");
        for (Map<String, Object> result : list2) {
            Assert.assertSame(result.get("projectTypeName"), "客户");

        }
    }

    @Test
    public void testJudgeProjectName() throws CRMDBException {
        CoopResumeDto coopResumeDto1 = new CoopResumeDto();
        coopResumeDto1.setExpBeginDate("2012-12-11");
        coopResumeDto1.setExpEndDate("2012-12-11");
        coopResumeDto1.setPeopleNumber(11);
        coopResumeDto1.setProjectScale("1.1");
        coopResumeDto1.setPrincipalWe("typ");
        coopResumeDto1.setPrincipalThey("fujisann");
        coopResumeDto1.setProjectName("客户关系管理");
        coopResumeDto1.setProjectType("1");
        coopResumeDto1.setCustomerID(customerID);

        boolean bool = coopResumeService.judgeProjectName(coopResumeDto1);
        Assert.assertEquals(bool, true);
        coopResumeDto1.setProjectName("会议室");
        coopResumeService.addOrUpdateCoopResume(coopResumeDto1);
        coopResumeDto1.setProjectName("客户关系管理");
        boolean bool1 = coopResumeService.judgeProjectName(coopResumeDto1);
        Assert.assertEquals(bool1, true);
    }

    @Test
    public void testDeleteCoopResume() throws CRMDBException {
        int start = (int) coopResumeDao.getCoopResumeSize(CustomerConstant.COOPRESUME_COUNT_HQL,
                customerID);
        coopResumeService.deleteCoopResume(String.valueOf(deleteId));
        int end = (int) coopResumeDao.getCoopResumeSize(CustomerConstant.COOPRESUME_COUNT_HQL,
                customerID);
        Assert.assertEquals(1, start - end);
    }
}
