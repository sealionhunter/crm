package com.ustcsoft.gs.crm.webui.customer.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.OrderSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.OrderDto;
import com.ustcsoft.gs.crm.webui.customer.dto.OrderTrackDto;
import com.ustcsoft.gs.crm.webui.customer.dto.SelectProductDto;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesEventDto;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesEventFlowDto;
import com.ustcsoft.gs.crm.webui.sales.dto.SalesTrackDto;

/**
 * @author liangchengcheng
 */
public interface OrderDao {

    /**
     * The method is used to search record by condition given.
     * 
     * @param searchFlag
     *            query mode
     * @param searchValue
     *            query condition
     * @param currPage
     *            return page
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * @return the infomation you want of the order
     */
    public Map<String, Object> queryOrder(final int searchFlag, final OrderSearchBean searchValue,
            final int currPage, final int limit, String type) throws DataAccessException;

    /**
     * The method is used to get all order.
     * 
     * @param currPage
     * @param limit
     * @param userID
     * @param type
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * @return all the information of the order
     */
    public Map<String, Object> getAllOrder(final int currPage, final int limit,
            final Integer[] userID, String type) throws DataAccessException;

    /**
     * The method is used to get the number of records.
     * 
     * @param queryString
     * @param userID
     * @return the size
     */
    public long getOrderSize(final String queryString, final Integer[] userID);

    /**
     * The method is used to delete record.
     * 
     * @param orderIds
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public void deleteOrder(final String orderIds) throws DataAccessException;

    /**
     * 
     * @param orderIds
     * @throws DataAccessException
     */
    public void changeToOrder(final String orderIds) throws DataAccessException;

    /**
     * The method is used to add or update record.
     * 
     * @param orderDto
     * @param sDto
     * @param sTrackDto
     * @param sFlowDto
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public void addOrUpdateOrder(OrderDto orderDto) throws DataAccessException;

    /**
     * The method is used to add order track record.
     * 
     * @param orderTrackDto
     *            order track information
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public void addOrderTrack(OrderTrackDto orderTrackDto) throws DataAccessException;

    /**
     * 
     * @param valueString
     * @param orderID
     * @throws DataAccessException
     */
    public void addProductInfo(List<SelectProductDto> selectProductDtos) throws DataAccessException;

    /**
     * The method is used to get orderState
     * 
     * @param id
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public List<String> getOrderState(final String orderID) throws DataAccessException;

    /**
     * 
     * @param orderID
     * @return the eventID
     * @throws DataAccessException
     */
    public int getOrderEventID(final int id) throws DataAccessException;

    /**
     * The method is used to get all order track record by orderId.
     * 
     * @param orderID
     *            orderId
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * @return map of all order track
     */
    public Map<String, Object> getAllOrderTrack(final String orderID) throws DataAccessException;

    /**
     * The method is used to get all contact.
     * @param customerID 
     * 
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public Map<String, Object> getCustomerContact(int customerID) throws DataAccessException;

    /**
     * The method is used to judge orderID whether repeat or not.
     * 
     * @param orderID
     * @throws DataAccessException
     *             in case of Hibernate Exception
     */
    public int judgeOrderRepeat(final String orderID) throws DataAccessException;

    /**
     * 
     * @param page
     * @param limit
     * @param userID
     * @param orderID
     * @return the product you bought
     */
    public Map<String, Object> buyProduct(final int page, final int limit, final int userID,
            final String orderID);

    /**
     * 
     * @param contactID
     * @return the phone number
     * @throws DataAccessException
     */
    public String getContactNumber(String contactID) throws DataAccessException;

    /**
     * 
     * @param status
     * @param currPage
     * @return order
     * @throws DataAccessException
     */
    public Map<String, Object> getOrderByStatus(int orderId, final int currPage, final int limit)
            throws DataAccessException;

    /**
     * 
     * @param CustomerID
     * @param status
     * @return salesEvent
     * @throws DataAccessException
     */
    public Map<String, Object> getEventByCustomer(String CustomerID, String status)
            throws DataAccessException;

    /**
     * 
     * @param id
     * @return the status
     * @throws DataAccessException
     */
    public int findStage(String eventID) throws DataAccessException;

    /**
     * 
     * @param salesEventDto
     * @throws DataAccessException
     */
    public void changeStatus(SalesEventDto salesEventDto) throws DataAccessException;

    /**
     * 
     * @param id
     * @return
     * @throws DataAccessException
     */
    public Map<String, Object> getCodeStore(String id) throws DataAccessException;

    /**
     * 
     * @param userID
     * @param productID
     * @return
     * @throws DataAccessException
     */
    public String getPersonalDiscount(String userID, String productID) throws DataAccessException;

    /**
     * 
     * @param salesTrackDtos
     * @throws DataAccessException
     */
    public void updateSalesTrackInfo(List<SalesTrackDto> salesTrackDtos) throws DataAccessException;

    /**
     * 
     * @param salesEventDto
     * @throws DataAccessException
     */
    public void updateSalesEventInfo(SalesEventDto salesEventDto) throws DataAccessException;

    /**
     * 
     * @param salesEventFlowDtos
     * @throws DataAccessException
     */
    public void updateSalesEventFlow(List<SalesEventFlowDto> salesEventFlowDtos)
            throws DataAccessException;

    /**
     * @param orderID
     * @return OrderDto
     */
    public OrderDto getOrderByID(int orderID);

    /**
     * @param eventID
     * @param typeString
     * @return
     */
    public Map<String, Object> getOrderEventDamands(int eventID, String typeString);

    /**
     * 
     * @param eventID
     * @return the eventIsAbolished
     * @throws CRMDBException
     */
    public String eventIsAbolished(int enventID);

    /**
     * @param stringForDelete
     * @return
     */
    public List<Integer> checkContract(String stringForDelete);
}
