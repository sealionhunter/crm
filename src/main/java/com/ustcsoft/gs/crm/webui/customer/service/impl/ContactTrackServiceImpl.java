package com.ustcsoft.gs.crm.webui.customer.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.customer.bean.ContactTrackListBean;
import com.ustcsoft.gs.crm.webui.customer.bean.ContactTrackSearchBean;
import com.ustcsoft.gs.crm.webui.customer.dao.ContactTrackDao;
import com.ustcsoft.gs.crm.webui.customer.dto.ContactTrackInfoDto;
import com.ustcsoft.gs.crm.webui.customer.service.ContactTrackService;
import com.ustcsoft.gs.crm.webui.customer.service.SourceInfoService;

/**
 * 
 * @author libaoshan
 * 
 */
public class ContactTrackServiceImpl implements ContactTrackService {

    private static final Log LOG = LogFactory.getLog(ContactTrackServiceImpl.class);

    private ContactTrackDao contactTrackDao = null;

    /** define sourceInfoService */
    private SourceInfoService sourceInfoService = null;

    /**
     * @param contactTrackDao
     *            the contactTrackDao to set
     */
    public void setContactTrackDao(ContactTrackDao contactTrackDao) {
        this.contactTrackDao = contactTrackDao;
    }

    /**
     * @param sourceInfoService
     *            the sourceInfoService to set
     */
    public void setSourceInfoService(SourceInfoService sourceInfoService) {
        this.sourceInfoService = sourceInfoService;
    }

    /**
     * the function is getting information form database to ContactTrackList.js
     * 
     * @param searchFlag
     *            is 1 or 2, a flag of searching
     * @param contactTrackSearchBean
     *            store the conditions of searching
     * @param page
     *            store the currentPage
     * @return map
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public Map<String, Object> getAllContactTrack(int searchFlag,
            ContactTrackSearchBean contactTrackSearchBean, int page, int limit, int userID, int customerID)
            throws CRMDBException {
        LOG.debug("method getAllContactTrack start!");
        Map<String, Object> map = new HashMap<String, Object>();
//        Integer[] userIDs = sourceInfoService.getUserID(userID);
        try {
//            map = contactTrackDao.getAllContactTrack(searchFlag, contactTrackSearchBean, page,
//                    limit, userIDs);
            map = contactTrackDao.getAllContactTrack(searchFlag, contactTrackSearchBean, page,
                    limit, customerID);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method getAllContactTrack!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method getAllContactTrack end!");
        return map;
    }

    /**
     * the function is change the isAbolished of contactTrackInfo
     * 
     * @param contactIDs
     *            store the IDs to delete
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteContactTrack(String contactIDs) throws CRMDBException {
        LOG.debug("method deleteContactTrack start!");
        try {
            contactIDs = CRMConstant.LEFT_PARENTHESIS + contactIDs + CRMConstant.RIGHT_PARENTHESIS;
            contactTrackDao.deleteContactTrack(contactIDs);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method deleteContactTrack!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method deleteContactTrack end!");
    }

    /**
     * the function is add or update the information
     * 
     * @param contactTrackListBean
     *            submitFlag, userID, eventID
     * @param submitFlag
     * @param userID
     * @param eventID
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void updateContactTrack(ContactTrackListBean contactTrackListBean, int submitFlag,
            int userID) throws CRMDBException {
        LOG.debug("method updateContactTrack start!");
        try {
            ContactTrackInfoDto contactTrackInfoDto = new ContactTrackInfoDto();
//            contactTrackInfoDto.setCheckResult(contactTrackListBean.getCheckResult());
            contactTrackInfoDto.setContactTheme(contactTrackListBean.getContactTheme());
            contactTrackInfoDto.setWeContact(contactTrackListBean.getWeContact());
            contactTrackInfoDto.setContactContent(contactTrackListBean.getContactContent());
            contactTrackInfoDto.setContactID(contactTrackListBean.getContactID());
            contactTrackInfoDto.setContactType(contactTrackListBean.getContactType());
            contactTrackInfoDto.setContactWay(contactTrackListBean.getContactWay());
            contactTrackInfoDto.setCustomerID(contactTrackListBean.getCustomerID());
            contactTrackInfoDto.setIfContact(contactTrackListBean.getIfContact());
            contactTrackInfoDto.setIsAbolished(contactTrackListBean.getIsAbolished());
            contactTrackInfoDto.setNotContantReason(contactTrackListBean.getNotContantReason());
            contactTrackInfoDto.setOppositeContact(contactTrackListBean.getOppositeContact());
            contactTrackInfoDto.setPlanDateBegin(contactTrackListBean.getPlanDateBegin());
            contactTrackInfoDto.setRealityDateBegin(contactTrackListBean.getRealityDateBegin());
            contactTrackInfoDto.setRealityDateEnd(contactTrackListBean.getRealityDateEnd());
            contactTrackInfoDto.setRemarks(contactTrackListBean.getRemarks());
            contactTrackInfoDto.setStrategy(contactTrackListBean.getStrategy());
//            contactTrackInfoDto.setState(contactTrackListBean.getChanceType());
            contactTrackInfoDto.setUserFeedbackInformation(contactTrackListBean
                    .getUserFeedbackInformation());
            contactTrackInfoDto.setCreateTime(contactTrackListBean.getCreateTime());
            contactTrackInfoDto.setUpdateTime(contactTrackListBean.getUpdateTime());

            if (submitFlag == 2) {
//                SalesEventDto salesEventDto = new SalesEventDto();
//                SalesTrackDto salesTrackDto = new SalesTrackDto();
//                SalesEventFlowDto salesEventFlowDto = new SalesEventFlowDto();
//                int id = 0;
//                int id1 = 0;

//                salesEventDto.setCustomerID(contactTrackInfoDto.getCustomerID());
//                salesEventDto.setContactID(contactTrackInfoDto.getOppositeContact());
//                salesEventDto.setSubmitterID(userID);
//                salesEventDto.setRemarks(contactTrackInfoDto.getRemarks());
//                salesEventDto.setSubmitDate(contactTrackInfoDto.getRealityDateEnd()
//                        .substring(0, 19));
//
//                salesTrackDto.setCustomerID(salesEventDto.getCustomerID());
//                salesTrackDto.setSubmitDate(salesEventDto.getSubmitDate());
//                salesTrackDto.setSubmitterID(salesEventDto.getSubmitterID());
//                if (contactTrackListBean.getChanceType() == 1) {
//                    if (eventID != 0) {
//                        contactTrackInfoDto.setEventID(eventID);
//                        salesEventDto.setEventID(eventID);
//                        id = contactTrackDao.querySalesEventFlowByEventID(eventID,
//                                contactTrackListBean.getChanceType());
//                        if (id != 0) {
//                            salesEventFlowDto.setId(id);
//                            salesEventFlowDto
//                                    .setDemand(contactTrackListBean.getFindChanceContent());
//                            contactTrackDao.updateSalesEventFlow(salesEventFlowDto);
//                        }
//                    } else {
//                        salesEventDto.setEventName(contactTrackListBean.getEventName());
//                        salesEventDto
//                                .setStatus(String.valueOf(contactTrackListBean.getChanceType()));
//                        salesTrackDto.setStatus(salesEventDto.getStatus());
//                        salesTrackDto.setIsAbolished(false);
//
//                        contactTrackDao.updateSalesEvent(salesEventDto);
//                        contactTrackInfoDto.setEventID(salesEventDto.getEventID());
//
//                        salesEventFlowDto.setDemand(contactTrackListBean.getFindChanceContent());
//                        salesEventFlowDto.setEventID(salesEventDto.getEventID());
//                        salesEventFlowDto.setStatus(contactTrackListBean.getChanceType());
//                        contactTrackDao.addSalesEventFlow(salesEventFlowDto);
//                        salesTrackDto.setEventID(salesEventDto.getEventID());
//                        contactTrackDao.addSalesTrack(salesTrackDto);
//                    }
//                } else if (contactTrackListBean.getChanceType() == 2) {
//                    id = contactTrackDao.querySalesEventFlowByEventID(eventID,
//                            contactTrackListBean.getChanceType());
//                    int salesTrackNo = contactTrackDao.querySalesTrackNoByEventID(eventID);
//                    contactTrackDao.updateSalesTrack(salesTrackNo);
//
//                    salesTrackDto.setEventID(eventID);
//                    contactTrackInfoDto.setEventID(eventID);
//                    if (contactTrackInfoDto.getCheckResult()) {
//                        salesEventDto.setEventName(contactTrackListBean.getEventName());
//                        salesEventDto.setEventID(eventID);
//                        salesEventDto
//                                .setStatus(String.valueOf(contactTrackListBean.getChanceType()));
//                        salesTrackDto.setStatus(salesEventDto.getStatus());
//                        salesTrackDto.setIsAbolished(false);
//                        contactTrackDao.updateSalesEvent(salesEventDto);
//                    } else {
//                        contactTrackDao.deleteSalesEventByEventID(eventID);
//                        salesTrackDto
//                                .setStatus(String.valueOf(contactTrackListBean.getChanceType()));
//                        salesTrackDto.setIsAbolished(true);
//                    }
//                    salesEventFlowDto.setDemand(contactTrackListBean.getCheckChanceContent());
//                    salesEventFlowDto.setEventID(eventID);
//                    salesEventFlowDto.setStatus(contactTrackListBean.getChanceType());
//                    if (id != 0) {
//                        salesEventFlowDto.setId(id);
//                        contactTrackDao.updateSalesEventFlow(salesEventFlowDto);
//                    } else {
//                        contactTrackDao.addSalesEventFlow(salesEventFlowDto);
//                    }
//                    contactTrackDao.addSalesTrack(salesTrackDto);
//                } else if (contactTrackListBean.getChanceType() == 3) {
//                    SalesEventFlowDto salesEventFlowDto1 = new SalesEventFlowDto();
//                    if (contactTrackInfoDto.getCheckResult()) {
//                        if (eventID != 0) {
//                            contactTrackInfoDto.setEventID(eventID);
//                            salesEventDto.setEventID(eventID);
//                            id = contactTrackDao.querySalesEventFlowByEventID(eventID,
//                                    contactTrackListBean.getChanceType() - 2);
//                            id1 = contactTrackDao.querySalesEventFlowByEventID(eventID,
//                                    contactTrackListBean.getChanceType() - 1);
//                            if (id != 0) {
//                                salesEventFlowDto.setId(id);
//                                salesEventFlowDto.setDemand(contactTrackListBean
//                                        .getFindChanceContent());
//                                contactTrackDao.updateSalesEventFlow(salesEventFlowDto);
//                            }
//                            if (id1 != 0) {
//                                salesEventFlowDto1.setId(id1);
//                                salesEventFlowDto1.setDemand(contactTrackListBean
//                                        .getCheckChanceContent());
//                                contactTrackDao.updateSalesEventFlow(salesEventFlowDto1);
//                            } else {
//                                salesEventDto.setEventID(eventID);
//                                salesEventDto.setEventName(contactTrackListBean.getEventName());
//                                salesEventDto.setStatus(String.valueOf(contactTrackListBean
//                                        .getChanceType() - 1));
//                                contactTrackDao.updateSalesEvent(salesEventDto);
//
//                                salesTrackDto.setStatus(String.valueOf(contactTrackListBean
//                                        .getChanceType() - 1));
//                                salesTrackDto.setIsAbolished(false);
//                                salesTrackDto.setEventID(eventID);
//                                contactTrackDao.addSalesTrack(salesTrackDto);
//
//                                salesEventFlowDto1.setDemand(contactTrackListBean
//                                        .getCheckChanceContent());
//                                salesEventFlowDto1.setEventID(eventID);
//                                salesEventFlowDto1
//                                        .setStatus(contactTrackListBean.getChanceType() - 1);
//                                contactTrackDao.addSalesEventFlow(salesEventFlowDto1);
//                            }
//                        } else {
//                            salesEventDto.setEventName(contactTrackListBean.getEventName());
//                            salesEventDto.setStatus(String.valueOf(contactTrackListBean
//                                    .getChanceType() - 1));
//                            contactTrackDao.updateSalesEvent(salesEventDto);
//                            contactTrackInfoDto.setEventID(salesEventDto.getEventID());
//
//                            salesTrackDto.setStatus(String.valueOf(contactTrackListBean
//                                    .getChanceType() - 2));
//                            salesTrackDto.setIsAbolished(false);
//                            salesTrackDto.setEventID(salesEventDto.getEventID());
//                            contactTrackDao.addSalesTrack(salesTrackDto);
//
//                            SalesTrackDto salesTrackDto1 = new SalesTrackDto();
//                            salesTrackDto1.setCustomerID(salesEventDto.getCustomerID());
//                            salesTrackDto1.setSubmitDate(salesEventDto.getSubmitDate());
//                            salesTrackDto1.setSubmitterID(salesEventDto.getSubmitterID());
//                            salesTrackDto1.setStatus(salesEventDto.getStatus());
//                            salesTrackDto1.setIsAbolished(false);
//                            salesTrackDto1.setEventID(salesEventDto.getEventID());
//                            contactTrackDao.addSalesTrack(salesTrackDto1);
//
//                            salesEventFlowDto
//                                    .setDemand(contactTrackListBean.getFindChanceContent());
//                            salesEventFlowDto.setEventID(salesEventDto.getEventID());
//                            salesEventFlowDto.setStatus(contactTrackListBean.getChanceType() - 2);
//                            contactTrackDao.addSalesEventFlow(salesEventFlowDto);
//
//                            salesEventFlowDto1.setDemand(contactTrackListBean
//                                    .getCheckChanceContent());
//                            salesEventFlowDto1.setEventID(salesEventFlowDto.getEventID());
//                            salesEventFlowDto1.setStatus(salesEventFlowDto.getStatus() + 1);
//                            contactTrackDao.addSalesEventFlow(salesEventFlowDto1);
//                        }
//                    }
//                }
            }
            contactTrackDao.updateContactTrack(contactTrackInfoDto);
        } catch (DataAccessException e) {
            LOG.error("DataAccessException occurs in method updateContactTrack!", e);
            throw new CRMDBException(e);
        }
        LOG.debug("method updateContactTrack end!");
    }
}
