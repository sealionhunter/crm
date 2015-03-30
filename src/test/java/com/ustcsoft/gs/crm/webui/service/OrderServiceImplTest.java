package com.ustcsoft.gs.crm.webui.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.co.dgic.testing.common.virtualmock.MockObjectManager;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.contact.dto.ContactInfoDto;
import com.ustcsoft.gs.crm.webui.contact.service.ContactInfoService;
import com.ustcsoft.gs.crm.webui.customer.bean.OrderBean;
import com.ustcsoft.gs.crm.webui.customer.bean.OrderSearchBean;
import com.ustcsoft.gs.crm.webui.customer.constant.CustomerConstant;
import com.ustcsoft.gs.crm.webui.customer.dao.OrderDao;
import com.ustcsoft.gs.crm.webui.customer.dto.CustomerDto;
import com.ustcsoft.gs.crm.webui.customer.dto.OrderDto;
import com.ustcsoft.gs.crm.webui.customer.service.CustomerService;
import com.ustcsoft.gs.crm.webui.customer.service.OrderService;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesEventFlowDto;
import com.ustcsoft.gs.crm.webui.util.CRMTest;

/**
 * 
 * @author liangchengcheng
 * @className OrderServiceImplTest
 * @extends CRMTest
 */
public class OrderServiceImplTest extends CRMTest {
    private static OrderService test = null;
    private static OrderDto orderDto = new OrderDto();
    private static OrderBean orderBean = new OrderBean();
    private static OrderSearchBean orderSearchBean = new OrderSearchBean();
    private static int customerId = 0;
    private static String contactid = "0";

    /**
     * 
     * @throws Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        orderSearchBean = new OrderSearchBean();
        orderSearchBean.setUserID(new Integer[] { 2, 3 });
        orderSearchBean.setCustomerIDValue("2");
        orderSearchBean.setOrderIDValue("4");
        orderSearchBean.setOurRepresentativeValue("444");
        orderSearchBean.setSearchText("343");
        orderSearchBean.setTransactionPriceValue("20");

        test = (OrderService) CTX.getBean("orderService");// beanID
        // add a customer
        CustomerDto customerDto = new CustomerDto();
        CustomerService customerService = null;
        customerService = (CustomerService) CTX.getBean("customerService");
        customerDto.setCustomerName("liangchengcheng");
        customerDto.setIndustry("01");
        customerDto.setFee("1");
        customerDto.setCustomerAddr("rr");
        customerDto.setCustomerStatement("1");
        customerDto.setCustomerType("1");
        customerDto.setIsAbolished(false);
        customerDto.setScale("2");
        customerDto.setValueEvaluate("2");
        customerService.updateCustomer(customerDto);
        customerId = customerDto.getCustomerID();
        // add a contact for the customer
        ContactInfoDto contactInfoDto = new ContactInfoDto();
        ContactInfoService contactInfoService = null;
        contactInfoService = (ContactInfoService) CTX.getBean("contactInfoService");
        contactInfoDto.setCompany("DD");
        contactInfoDto.setDepartment("AA");
        contactInfoDto.setContactName("liangchengcheng");
        contactInfoDto.setJob("FFF");
        contactInfoDto.setPhoneNumber("12546875624");
        contactInfoDto.setIsAbolished(false);
        contactInfoService.addContact(contactInfoDto, null, null, null, null);
    }

    /**
     * 
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test add and update order
     * 
     * @throws CRMDBException
     */
    @Test
    public void testSearchOrGetAllOrder() throws CRMDBException {
        map = test.searchOrGetAllOrder(0, orderSearchBean, 1, 25, 1, "1");
        map = test.searchOrGetAllOrder(1, orderSearchBean, 1, 25, 1, "0");

        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(OrderDao.class, "getAllOrder",
                new EmptyResultDataAccessException(0));
        try {
            test.searchOrGetAllOrder(0, orderSearchBean, 1, 25, 1, "1");
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

    @Test
    public void testDeleteOrder() throws CRMDBException {
        test.deleteOrder("10000,20000");
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(OrderDao.class, "deleteOrder",
                new EmptyResultDataAccessException(0));
        try {
            test.deleteOrder("10000,50000");
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

    /**
     * Test add and update order
     * 
     * @throws CRMDBException
     */
    @Test
    public void testAddOrUpdateOrder() throws CRMDBException {
        test.getOrderDao().getOrderSize(CustomerConstant.ORDER_COUNT_HQL, new Integer[] { 1 });
        // add order
        orderDto.setOrderID("FUJI20131121102147584");
        orderDto.setCustomerID(customerId);
        orderDto.setOrderState("1");
        orderDto.setOurRepresentative("张三");
        orderDto.setDeliveryDate("2012-10-11");
        orderDto.setTransactionPrice("1000");
        orderDto.setContactID(contactid);
        orderDto.setCustomerRepresentative("1");
        orderDto.setCustomerContact("12345678");
        orderDto.setOurTelephone("98765412");
        orderDto.setType(1);
        orderDto.setEventID(3);
        orderDto.setIsAbolished(false);

        List<SalesEventFlowDto> salesEventFlowDtos = new ArrayList<SalesEventFlowDto>();
        SalesEventFlowDto salesEventFlowDto = new SalesEventFlowDto();
        salesEventFlowDto.setDemand("demand");
        salesEventFlowDto.setEventID(3);
        salesEventFlowDto.setId(0);
        salesEventFlowDto.setStatus(5);
        salesEventFlowDtos.add(salesEventFlowDto);

        orderBean.setOrderDto(orderDto);
        orderBean.setSalesEventFlowDtos(salesEventFlowDtos);
        orderBean.setSelectProductDtos(null);

        test.addOrUpdateOrder(orderBean, customerId);
        test.deleteOrder(orderDto.getId() + "");

        orderDto.setOrderID("FUJI" + new Date().toString());
        test.addOrUpdateOrder(orderBean, customerId);

        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(OrderDao.class, "addOrUpdateOrder",
                new EmptyResultDataAccessException(0));
        try {
            test.deleteOrder(orderDto.getId() + "");
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

    /**
     * Test exception when 'transactionPrice'(money) = 'w'(char)
     * 
     * @throws CRMDBException
     */
    @Test(expected = CRMDBException.class)
    public void testGetAllOrderTrack() throws CRMDBException {
        test.getAllOrderTrack("FUJI20131121102147584");
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(OrderDao.class, "getAllOrderTrack",
                new EmptyResultDataAccessException(0));
        try {
            test.getAllOrderTrack("FUJI20131121102147584");
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

    @Test(expected = CRMDBException.class)
    public void testGetCustomerContact() throws CRMDBException {
        test.getCustomerContact();
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(OrderDao.class, "getCustomerContact",
                new EmptyResultDataAccessException(0));
        try {
            test.getCustomerContact();
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

    @Test(expected = CRMDBException.class)
    public void testJudgeOrderRepeat() throws CRMDBException {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(0);
        orderDto.setOrderID("FUJI20131121151933496");
        test.judgeOrderRepeat(orderDto);
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(OrderDao.class, "judgeOrderRepeat",
                new EmptyResultDataAccessException(0));
        try {
            test.judgeOrderRepeat(orderDto);
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

    @Test(expected = CRMDBException.class)
    public void testChangeToOrder() throws CRMDBException {
        // add order
        orderDto.setOrderID("FUJI20131121102147584");
        orderDto.setCustomerID(customerId);
        orderDto.setOrderState("1");
        orderDto.setOurRepresentative("张三");
        orderDto.setDeliveryDate("2012-10-11");
        orderDto.setTransactionPrice("1000");
        orderDto.setContactID(contactid);
        orderDto.setCustomerRepresentative("1");
        orderDto.setCustomerContact("12345678");
        orderDto.setOurTelephone("98765412");
        orderDto.setType(1);
        orderDto.setEventID(3);
        orderDto.setIsAbolished(false);

        List<SalesEventFlowDto> salesEventFlowDtos = new ArrayList<SalesEventFlowDto>();
        SalesEventFlowDto salesEventFlowDto = new SalesEventFlowDto();
        salesEventFlowDto.setDemand("demand");
        salesEventFlowDto.setEventID(3);
        salesEventFlowDto.setId(0);
        salesEventFlowDto.setStatus(5);
        salesEventFlowDtos.add(salesEventFlowDto);
        test.changeToOrder(orderBean, 3);
        orderBean.setOrderDto(orderDto);
        orderBean.setSalesEventFlowDtos(salesEventFlowDtos);
        orderBean.setSelectProductDtos(null);
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(OrderDao.class, "updateSalesEventFlow",
                new EmptyResultDataAccessException(0));
        try {
            test.changeToOrder(orderBean, 3);
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

    @Test(expected = CRMDBException.class)
    public void testAddProductInfo() throws CRMDBException {
        test.addProductInfo(null);
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(OrderDao.class, "addProductInfo",
                new EmptyResultDataAccessException(0));
        try {
            test.addProductInfo(null);
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

    @Test(expected = CRMDBException.class)
    public void testBuyProduct() throws CRMDBException {
        test.buyProduct(1, 25, 3, "FUJI20131121102147584");
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(OrderDao.class, "buyProduct",
                new EmptyResultDataAccessException(0));
        try {
            test.buyProduct(1, 25, 3, "FUJI20131121102147584");
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

    @Test(expected = CRMDBException.class)
    public void testGetContactNumber() throws CRMDBException {
        test.getContactNumber("33");
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(OrderDao.class, "getContactNumber",
                new EmptyResultDataAccessException(0));
        try {
            test.getContactNumber("33");
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

    @Test(expected = CRMDBException.class)
    public void testGetOrderByStatus() throws CRMDBException {
        test.getOrderByStatus(3, 1, 25);
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(OrderDao.class, "getOrderByStatus",
                new EmptyResultDataAccessException(0));
        try {
            test.getOrderByStatus(3, 1, 25);
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

    @Test(expected = CRMDBException.class)
    public void testGetEventByCustomer() throws CRMDBException {
        test.getEventByCustomer("3", "3");
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(OrderDao.class, "getEventByCustomer",
                new EmptyResultDataAccessException(0));
        try {
            test.getEventByCustomer("3", "3");
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

    @Test(expected = CRMDBException.class)
    public void testFindStatus() throws CRMDBException {
        test.findStatus("4");
        MockObjectManager.initialize();
        MockObjectManager.addReturnValue(OrderDao.class, "findStage",
                new EmptyResultDataAccessException(0));
        try {
            test.findStatus("4");
        } catch (Exception e) {
            assertEquals(true, e instanceof CRMDBException);
        }
    }

    @Test(expected = CRMDBException.class)
    public void testAddOrUpdateFormalOrder() throws CRMDBException {
        // add order
        orderDto.setOrderID("FUJI20131121102147584");
        orderDto.setCustomerID(customerId);
        orderDto.setOrderState("1");
        orderDto.setOurRepresentative("张三");
        orderDto.setDeliveryDate("2012-10-11");
        orderDto.setTransactionPrice("1000");
        orderDto.setContactID(contactid);
        orderDto.setCustomerRepresentative("1");
        orderDto.setCustomerContact("12345678");
        orderDto.setOurTelephone("98765412");
        orderDto.setType(1);
        orderDto.setEventID(3);
        orderDto.setIsAbolished(false);

        List<SalesEventFlowDto> salesEventFlowDtos = new ArrayList<SalesEventFlowDto>();
        SalesEventFlowDto salesEventFlowDto = new SalesEventFlowDto();
        salesEventFlowDto.setDemand("demand");
        salesEventFlowDto.setEventID(3);
        salesEventFlowDto.setId(0);
        salesEventFlowDto.setStatus(5);
        salesEventFlowDtos.add(salesEventFlowDto);
        test.addOrUpdateFormalOrder(orderBean, 3);
        orderDto.setId(0);
        orderBean.setOrderDto(orderDto);
        orderBean.setSalesEventFlowDtos(salesEventFlowDtos);
        orderBean.setSelectProductDtos(null);

        test.addOrUpdateFormalOrder(orderBean, 3);
    }

    @Test(expected = CRMDBException.class)
    public void testGetOrderEventDamands() throws CRMDBException {
        test.getOrderEventDamands(3, "2");
    }

    @Test(expected = CRMDBException.class)
    public void testEventIsAbolished() throws CRMDBException {
        test.eventIsAbolished(3);
    }
}