/**
 * 
 */
package com.ustcsoft.gs.crm.webui.service;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.CustomerSearchBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.customer.dto.CustomerDto;
import com.ustcsoft.gs.crm.webui.customer.service.CustomerService;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

/**
 * @author tangyunpeng
 * 
 */
public class CustomerServiceImplTest extends CRMTest {
    private CustomerService customerService = null;
    private CustomerDto customerDto = new CustomerDto();
    private String searchText = null;
    private static int deleteId = 0;
    CustomerSearchBean searchBean = new CustomerSearchBean();

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        customerService = (CustomerService) CTX.getBean("customerService");
        customerDto.setCustomerAddr("合肥市");
        customerDto.setCustomerName("恒星");
        customerDto.setCustomerStatement("1");
        customerDto.setCustomerType("1");
        customerDto.setFee("1");
        customerDto.setHolder(1);
        customerDto.setIndustry("01");
        customerDto.setIsAbolished(false);
        customerDto.setScale("1");
        customerDto.setValueEvaluate("1");

        String[] all = { "00" };
        searchText = "国";
        searchBean.setCustomerAddr("合");
        searchBean.setSearchText(searchText);
        searchBean.setEarning("");
        searchBean.setHolder("");
        searchBean.setCustomerName("");
        searchBean.setFee(all);
        searchBean.setIndustry(all);
        searchBean.setScale(all);
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
    public void testUpdateCustomer() throws CRMDBException {

        int start = (int) customerService.getCustomerDao().getCustomerSize(
                CustomerConstant.CUSTOMER_COUNT_HQL + CustomerConstant.HOLDER_USER_ID,
                new Integer[] { 1 });
        customerService.updateCustomer(customerDto);
        deleteId = customerDto.getCustomerID();
        int end = (int) customerService.getCustomerDao().getCustomerSize(
                CustomerConstant.CUSTOMER_COUNT_HQL + CustomerConstant.HOLDER_USER_ID,
                new Integer[] { 1 });
        Assert.assertEquals(end - start, 1);

    }

    /**
     * {@link com.ustcsoft.gs.crm.webui.contact.service.impl.SocialInfoServiceImpl#getAllSocialInfo()}
     * 
     * @throws CRMDBException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testSearchOrGetAllCustomer() throws CRMDBException {

        super.map = customerService.searchOrGetAllCustomer(0, null, 1, 25, 1);
        int total = Integer.parseInt(map.get("total").toString());
        List<?> list = (List<?>) map.get("items");
        boolean bool = total >= list.size();
        Assert.assertEquals(bool, true);

        super.map = customerService.searchOrGetAllCustomer(1, searchBean, 1, 25, 1);
        List<?> list1 = (List<?>) map.get("items");
        for (int i = 0; i < list1.size(); i++) {
            boolean bool1 = true;
            Map<String, Object> result = (Map<String, Object>) list1.get(i);
            String holder = (String) result.get("holderName");
            boolean b1 = holder == null || !holder.contains(searchText);
            boolean b2 = ((String) result.get("scaleName")).contains(searchText);
            boolean b3 = ((String) result.get("industryName")).contains(searchText);
            boolean b4 = ((String) result.get("customerTypeName")).contains(searchText);
            boolean b5 = ((String) result.get("feeName")).contains(searchText);
            boolean b6 = ((String) result.get("customerStatementName")).contains(searchText);
            boolean b7 = ((String) result.get("valueEvaluateName")).contains(searchText);
            boolean b8 = ((String) result.get("customerAddr")).contains(searchText);
            String earning = (String) result.get("earning");
            boolean b9 = earning == null || !earning.contains(searchText);
            String descriptions = (String) result.get("descriptions");
            boolean b11 = descriptions == null || !descriptions.contains(searchText);
            boolean b12 = ((String) result.get("customerName")).contains(searchText);
            bool1 = !b1 || b2 || b3 || b4 || b5 || b6 || b7 || b8 || !b9 || !b11 || b12;
            Assert.assertEquals(bool1, true);
        }

        super.map = customerService.searchOrGetAllCustomer(2, searchBean, 1, 25, 1);
        List<?> list2 = (List<?>) map.get("items");
        for (int i = 0; i < list2.size(); i++) {
            Map<String, Object> result = (Map<String, Object>) list2.get(i);
            Assert.assertSame(result.get("customerAddr"), "南");
        }

    }

    /**
     * 
     * @throws CRMDBException
     */
    @Test
    public void testJudgeCustomerName() throws CRMDBException {

        CustomerDto customerDto1 = new CustomerDto();
        customerDto1.setCustomerAddr("合肥市");
        customerDto1.setCustomerName("恒星");
        customerDto1.setCustomerStatement("1");
        customerDto1.setCustomerType("1");
        customerDto1.setFee("1");
        customerDto1.setHolder(1);
        customerDto1.setIndustry("01");
        customerDto1.setIsAbolished(false);
        customerDto1.setScale("1");
        customerDto1.setValueEvaluate("1");
        boolean bool = customerService.judgeCustomerName(customerDto1);
        Assert.assertEquals(bool, true);
        customerDto1.setCustomerName("国创");
        customerService.updateCustomer(customerDto1);
        customerDto1.setCustomerName("恒星");
        boolean bool1 = customerService.judgeCustomerName(customerDto1);
        Assert.assertEquals(bool1, true);

    }

    /**
     * 
     * @throws CRMDBException
     */
    @Test
    public void testDeleteCustomer() throws CRMDBException {

        int start = (int) customerService.getCustomerDao().getCustomerSize(
                CustomerConstant.CUSTOMER_COUNT_HQL + CustomerConstant.HOLDER_USER_ID,
                new Integer[] { 1 });
        customerService.deleteCustomer(String.valueOf(deleteId));
        int end = (int) customerService.getCustomerDao().getCustomerSize(
                CustomerConstant.CUSTOMER_COUNT_HQL + CustomerConstant.HOLDER_USER_ID,
                new Integer[] { 1 });
        Assert.assertEquals(1, start - end);
    }

}
