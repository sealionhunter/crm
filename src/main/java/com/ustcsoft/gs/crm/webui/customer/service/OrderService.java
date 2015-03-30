package com.ustcsoft.gs.crm.webui.customer.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.OrderBean;
import com.ustcsoft.gs.crm.webui.customer.bean.OrderSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dao.OrderDao;
import com.ustcsoft.gs.crm.webui.customer.dto.OrderDto;
import com.ustcsoft.gs.crm.webui.customer.dto.SelectProductDto;

/**
 * @author liangchengcheng
 */
public interface OrderService {

    /**
     * The method is used to get all order by given condition
     * 
     * @param searchFlag
     *            query mode
     * @param searchValue
     *            query condition
     * @param currPage
     *            assigned page
     * @param limit
     * @param userID
     * @param type
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> searchOrGetAllOrder(final int searchFlag,
            OrderSearchBean searchValue, final int currPage, final int limit, final int userID,
            String type) throws CRMDBException;

    /**
     * The method is used to delete record.
     * 
     * @param orderIds
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> deleteOrder(final String orderIds) throws CRMDBException;

    /**
     * The method is used to change the intent order to the order
     * 
     * @param orderIds
     * @throws CRMDBException
     */
    public void changeToOrder(OrderBean orderBean, int userID) throws CRMDBException;

    /**
     * The method is used to add or update record.
     * 
     * @param orderBean
     * @param userID
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public void addOrUpdateOrder(OrderBean orderBean, int userID) throws CRMDBException;

    /**
     * 
     * @param page
     * @param limit
     * @param userID
     * @param orderID
     * @return the product you bought
     * @throws CRMDBException
     */
    public Map<String, Object> buyProduct(final int page, final int limit, final int userID,
            final String orderID) throws CRMDBException;

    /**
     * The method is used to get all order track by orderId
     * 
     * @param orderID
     * @return all the track
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> getAllOrderTrack(final String orderID) throws CRMDBException;

    /**
     * The method is used to get all customer contact.
     * @param customerID 
     * 
     * @return the contact of the customer
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> getCustomerContact(int customerID) throws CRMDBException;

    /**
     * The method is used to judge orderID whether repeat or not.
     * 
     * @param orderDto`
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    public int judgeOrderRepeat(OrderDto orderDto) throws CRMDBException;

    /**
     * @return the orderDao
     */
    public OrderDao getOrderDao();

    /**
     * 
     * @param selectProductDtos
     * @throws CRMDBException
     */
    public void addProductInfo(List<SelectProductDto> selectProductDtos) throws CRMDBException;

    /**
     * 
     * @param contactID
     * @throws CRMDBException
     */
    public String getContactNumber(String contactID) throws CRMDBException;

    /**
     * 
     * @param status
     * @param currPage
     * @return the order
     * @throws DataAccessException
     */
    public Map<String, Object> getOrderByStatus(int orderId, final int currPage, final int limit)
            throws CRMDBException;

    /**
     * 
     * @param customerID
     * @param status
     * @return the event
     * @throws CRMDBException
     */
    public Map<String, Object> getEventByCustomer(String customerID, String status)
            throws CRMDBException;

    /**
     * 
     * @param eventID
     * @return the status
     * @throws CRMDBException
     */
    public int findStatus(String eventID) throws CRMDBException;

    /**
     * 
     * @param eventID
     * @return the eventIsAbolished
     * @throws CRMDBException
     */
    public String eventIsAbolished(int eventID) throws CRMDBException;

    /**
     * 
     * @param orderBean
     * @param userID
     * @throws CRMDBException
     */
    public void addOrUpdateFormalOrder(OrderBean orderBean, int userID) throws CRMDBException;

    /**
     * @param eventID
     * @param typeString
     * @return
     */
    public Map<String, Object> getOrderEventDamands(int eventID, String typeString);
}
