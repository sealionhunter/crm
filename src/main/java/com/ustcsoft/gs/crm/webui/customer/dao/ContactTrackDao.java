package com.ustcsoft.gs.crm.webui.customer.dao;

import java.util.Map;

import com.ustcsoft.gs.crm.webui.customer.bean.ContactTrackSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dto.ContactTrackInfoDto;

/**
 * 
 * @author zhouzhou
 * 
 */
public interface ContactTrackDao {

    /**
     * the function is getting information form database to ContactTrackList.js
     * 
     * @param searchFlag
     *            is 1 or 2, a flag of searching
     * @param contactTrackSearchBean
     *            store the conditions of searching
     * @param page
     *            store the currentPage
     * @param limit
     *            store the count number
     * 
     * @param customerID
     *            user id
     * 
     * @return List<ContactTrackInfoDto>
     */
    public Map<String, Object> getAllContactTrack(final int searchFlag,
            final ContactTrackSearchBean contactTrackSearchBean, final int page, final int limit,
            final int customerID);

    /**
     * the function is change the isAbolished of contactTrackInfo
     * 
     * @param contactIDs
     */
    public void deleteContactTrack(String contactIDs);

    /**
     * the function is add or update the information
     * 
     * @param contactTrackInfoDto
     */
    public void updateContactTrack(ContactTrackInfoDto contactTrackInfoDto);

    /**
     * the function is add or update the information
     * 
     * @param salesEventDto
     *            salesEventDto
     */
//    public void updateSalesEvent(SalesEventDto salesEventDto);

    /**
     * the function used for add sales event information to database
     * 
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * @param salesTrackDto
     */
//    public void addSalesTrack(SalesTrackDto salesTrackDto);

    /**
     * the function used for add sales event flow information to database
     * 
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * @param salesEventFlowDto
     */
//    public void addSalesEventFlow(SalesEventFlowDto salesEventFlowDto);

    /**
     * the function used for update sales event flow information to database
     * 
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * @param salesEventFlowDto
     */
//    public void updateSalesEventFlow(SalesEventFlowDto salesEventFlowDto);

    /**
     * the function used for updata sales track information to database
     * 
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * @param salesTrackNo
     */
//    public void updateSalesTrack(int salesTrackNo);

    /**
     * the function used for query SalesTrackNo information from database by
     * eventID
     * 
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * @param eventID
     */
//    public int querySalesTrackNoByEventID(int eventID);

    /**
     * the function is delete the sales event by eventID
     * 
     * @param eventID
     * @throws DataAccessException
     */
//    public void deleteSalesEventByEventID(int eventID);

    /**
     * the function used for query SalesEventFlow id information from database
     * by eventID and status
     * 
     * @throws DataAccessException
     *             in case of Hibernate Exception
     * @param eventID
     * @param status
     */
//    public int querySalesEventFlowByEventID(int eventID, int status);

}
