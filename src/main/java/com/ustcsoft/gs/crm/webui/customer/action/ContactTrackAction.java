package com.ustcsoft.gs.crm.webui.customer.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.customer.bean.ContactTrackListBean;
import com.ustcsoft.gs.crm.webui.customer.bean.ContactTrackSearchBean;
import com.ustcsoft.gs.crm.webui.customer.service.ContactTrackService;

/**
 * 
 * @author zhouzhou
 * 
 */
public class ContactTrackAction extends CRMAction {

    private static final Log LOG = LogFactory.getLog(ContactTrackAction.class);

    private static final long serialVersionUID = 1L;

    /** used for getting class ContactTrackService */
    private ContactTrackService contactTrackService = null;
//    /** used for getting class SalesEventService */
//    private SalesEventService salesEventService;
    /** used for update information */
    private ContactTrackListBean contactTrackListBean = null;
    /** used for check information */
    private int submitFlag = 0;
    private int customerID = 0;

    /**
     * the function is to show the ContactTrackList
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public String execute() throws CRMDBException {
        LOG.debug("method execute start!");
        ContactTrackSearchBean contactTrackSearchBean = (ContactTrackSearchBean) CRMUtils
                .jsonToBean(jsonString, ContactTrackSearchBean.class);
        map = contactTrackService.getAllContactTrack(searchFlag, contactTrackSearchBean, page,
                limit, userID, customerID);
        LOG.debug("method execute end!");
        return SUCCESS;
    }

    /**
     * the function is to add or update the information to database
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public String update() throws CRMDBException {
        LOG.debug("method update start!");
        contactTrackService.updateContactTrack(contactTrackListBean, submitFlag, userID);
        LOG.debug("method update end!");
        return SUCCESS;
    }

    /**
     * validate addOrUpdate
     * 
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public void validateUpdate() throws CRMDBException {
        contactTrackListBean = (ContactTrackListBean) CRMUtils.jsonToBean(jsonString,
                ContactTrackListBean.class);
        if (submitFlag == 1) {
            // 客户选择检查
            if ("".equals(contactTrackListBean.getCustomerID())) {
                addFieldError("customerID", this.getText("contCustomerID.invalid"));
            }
            // 类型选择检查
            if ("".equalsIgnoreCase(contactTrackListBean.getContactType())) {
                addFieldError("contactType", this.getText("contContactType.invalid"));
            }
            // 我方联系人输入检查
            if (contactTrackListBean.getWeContact() == 0) {
                addFieldError("weContact", this.getText("contWeContact.invalid"));
            }
            // 对方联系人选择检查
            if (contactTrackListBean.getOppositeContact() == 0) {
                addFieldError("oppositeContact", this.getText("contOppositeContact.invalid"));
            }
            // 联系方式选择检查
            if ("".equalsIgnoreCase(contactTrackListBean.getContactWay())) {
                addFieldError("contactWay", this.getText("contContactWay.invalid"));
            }
            // 预计联系时间选择检查
            if ("".equalsIgnoreCase(contactTrackListBean.getPlanDateBegin())) {
                addFieldError("planDateBegin", this.getText("contPlanDateBegin.invalid"));
            } else if (!contactTrackListBean.getPlanDateBegin().matches(CRMConstant.DATE_TIME_REG)) {
                addFieldError("planDateBegin", this.getText("contPlanDateBeginMatch.invalid"));
            }
            // 联系内容输入检查
            if ("".equalsIgnoreCase(contactTrackListBean.getContactContent())) {
                addFieldError("contactContent", this.getText("contContactContent.invalid"));
            } else if (contactTrackListBean.getContactContent().length() > 2048) {
                addFieldError("contactContent", this.getText("contContactContentLength.invalid"));
            }
        } else if (submitFlag == 2) {
            // 开始时间选择检查
            if ("".equalsIgnoreCase(contactTrackListBean.getRealityDateBegin())) {
                addFieldError("realityDateBegin", this.getText("contRealityDateBegin.invalid"));
            } else if (!contactTrackListBean.getRealityDateBegin().matches(
                    CRMConstant.DATE_TIME_REG)) {
                addFieldError("realityDateBegin", this.getText("contRealityDateBeginMatch.invalid"));
            }
            // 结束时间选择检查
            if ("".equalsIgnoreCase(contactTrackListBean.getRealityDateEnd())) {
                addFieldError("realityDateEnd", this.getText("contRealityDateEnd.invalid"));
            } else if (!contactTrackListBean.getRealityDateEnd().matches(CRMConstant.DATE_TIME_REG)) {
                addFieldError("realityDateEnd", this.getText("contRealityDateEndMatch.invalid"));
            }
            // 反馈信息输入检查
            if ("".equalsIgnoreCase(contactTrackListBean.getUserFeedbackInformation())) {
                addFieldError("userFeedbackInformation",
                        this.getText("contUserFeedbackInformation.invalid"));
            } else if (contactTrackListBean.getUserFeedbackInformation().length() > 1024) {
                addFieldError("userFeedbackInformation",
                        this.getText("contUserFeedbackInformationLength.invalid"));
            }
            // 策略输入检查
            if ("".equalsIgnoreCase(contactTrackListBean.getStrategy())) {
                addFieldError("strategy", this.getText("contStrategy.invalid"));
            } else if (contactTrackListBean.getStrategy().length() > 1024) {
                addFieldError("strategy", this.getText("contStrategyLength.invalid"));
            }
            // 机会描述和事件输入检查
//            if (contactTrackListBean.getChanceType() != 0) {
//                SalesEventDto salesEventDto = new SalesEventDto();
//                salesEventDto.setEventName(contactTrackListBean.getEventName());
//                if (contactTrackListBean.getChanceType() == 1) {
//                    if (eventID == 0 && "".equalsIgnoreCase(contactTrackListBean.getEventName())) {
//                        addFieldError(CustomerConstant.CHANCENAME,
//                                this.getText("contEventName.invalid"));
//                    } else if (eventID == 0 && contactTrackListBean.getEventName().length() > 50) {
//                        addFieldError(CustomerConstant.CHANCENAME,
//                                this.getText("contEventNameLength.invalid"));
//                    } else if (salesEventService.judgeSalesEventName(salesEventDto) != 0
//                            && eventID == 0) {
//                        addFieldError(CustomerConstant.CHANCENAME,
//                                this.getText("contEventNameRepeat.invalid"));
//                    }
//                    if ("".equalsIgnoreCase(contactTrackListBean.getFindChanceContent())) {
//                        addFieldError(CustomerConstant.FINDCHANCECONTENT,
//                                this.getText("contFindChanceContent.invalid"));
//                    } else if (contactTrackListBean.getFindChanceContent().length() > 100) {
//                        addFieldError(CustomerConstant.FINDCHANCECONTENT,
//                                this.getText("contFindChanceContentLength.invalid"));
//                    }
//                } else if (contactTrackListBean.getChanceType() == 2) {
//                    if ("".equalsIgnoreCase(contactTrackListBean.getCheckChanceContent())) {
//                        addFieldError(CustomerConstant.CHECKCHANCECONTENT,
//                                this.getText("contCheckChanceContent.invalid"));
//                    } else if (contactTrackListBean.getCheckChanceContent().length() > 100) {
//                        addFieldError(CustomerConstant.CHECKCHANCECONTENT,
//                                this.getText("contCheckChanceContentLength.invalid"));
//                    }
//                } else {
//                    if (eventID == 0 && "".equalsIgnoreCase(contactTrackListBean.getEventName())) {
//                        addFieldError(CustomerConstant.CHANCENAME,
//                                this.getText("contEventName.invalid"));
//                    } else if (eventID == 0 && contactTrackListBean.getEventName().length() > 50) {
//                        addFieldError(CustomerConstant.CHANCENAME,
//                                this.getText("contEventNameLength.invalid"));
//                    } else if (eventID == 0
//                            && salesEventService.judgeSalesEventName(salesEventDto) != 0) {
//                        addFieldError(CustomerConstant.CHANCENAME,
//                                this.getText("contEventNameRepeat.invalid"));
//                    }
//                    if ("".equalsIgnoreCase(contactTrackListBean.getFindChanceContent())) {
//                        addFieldError(CustomerConstant.FINDCHANCECONTENT,
//                                this.getText("contFindChanceContent.invalid"));
//                    } else if (contactTrackListBean.getFindChanceContent().length() > 100) {
//                        addFieldError(CustomerConstant.FINDCHANCECONTENT,
//                                this.getText("contFindChanceContentLength.invalid"));
//                    }
//                    if ("".equalsIgnoreCase(contactTrackListBean.getCheckChanceContent())) {
//                        addFieldError(CustomerConstant.CHECKCHANCECONTENT,
//                                this.getText("contCheckChanceContent.invalid"));
//                    } else if (contactTrackListBean.getCheckChanceContent().length() > 100) {
//                        addFieldError(CustomerConstant.CHECKCHANCECONTENT,
//                                this.getText("contCheckChanceContentLength.invalid"));
//                    }
//                }
//            }
        } else if (submitFlag == 3) {
            // 未联系原因输入检查
            if ("".equalsIgnoreCase(contactTrackListBean.getNotContantReason())) {
                addFieldError("notContantReason", this.getText("contNotContantReason.invalid"));
            } else if (contactTrackListBean.getNotContantReason().length() > 1024) {
                addFieldError("notContantReason",
                        this.getText("contNotContantReasonLength.invalid"));
            }
        }
        showFieldError();
    }

    /**
     * the function is to change the isAbolished of contactTrackInfo from false
     * to true
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             in case of Hibernate Exception
     */
    @Override
    public String delete() throws CRMDBException {
        LOG.debug("method delete start!");
        contactTrackService.deleteContactTrack(jsonString);
        LOG.debug("method delete end!");
        return SUCCESS;
    }

    /**
     * @param contactTrackService
     *            the contactTrackService to set
     */
    public void setContactTrackService(ContactTrackService contactTrackService) {
        this.contactTrackService = contactTrackService;
    }

    /**
     * 
     * @return submitFlag
     */
    public int getSubmitFlag() {
        return submitFlag;
    }

    /**
     * @param submitFlag
     *            the submitFlag to set
     */
    public void setSubmitFlag(int submitFlag) {
        this.submitFlag = submitFlag;
    }

//    /**
//     * 
//     * @param salesEventService
//     *            the salesEventService to set
//     */
//    public void setSalesEventService(SalesEventService salesEventService) {
//        this.salesEventService = salesEventService;
//    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
