package com.ustcsoft.gs.crm.webui.contact.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcsoft.gs.crm.webui.common.action.CRMAction;
import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;
import com.ustcsoft.gs.crm.webui.common.exception.CRMDBException;
import com.ustcsoft.gs.crm.webui.common.util.CRMUtils;
import com.ustcsoft.gs.crm.webui.contact.bean.ContactSearchBean;
import com.ustcsoft.gs.crm.webui.contact.constant.ContactConstant;
import com.ustcsoft.gs.crm.webui.contact.dto.ContactInfoDto;
import com.ustcsoft.gs.crm.webui.contact.dto.FamilyDto;
import com.ustcsoft.gs.crm.webui.contact.dto.SocialDto;
import com.ustcsoft.gs.crm.webui.contact.service.ContactInfoService;

/**
 * Get, add, edit, and delete contact messages
 * 
 * @author xuzhen
 */
public class ContactListAction extends CRMAction {
    private static final long serialVersionUID = 1L;

    private static Log LOG = LogFactory.getLog(ContactListAction.class);

    private ContactInfoService contactInfoService;

    /** receive added and updated familyInfo messages */
    private String[] familyInfo;

    /** receive added and updated socialInfo messages */
    private String[] socialInfo;

    /** receive deleted familyInfo's familyID */
    private String familyID;

    /** receive deleted socialInfo's socialID */
    private String socialID;

    /** store socialInfo */
    private List<SocialDto> socialInfoList = new ArrayList<SocialDto>();

    /** store familyInfo */
    private List<FamilyDto> familyInfoList = new ArrayList<FamilyDto>();

    /** store contactInfo */
    private ContactInfoDto contact;

    /** */
    private String contactSearchText = null;

    private String customerID = null;
    /**
     * method to get all contact records from DB and return the searched records
     * 
     * @return success
     * @throws CRMDBException
     *             DataAccessException in case of Hibernate Exception
     */
    @Override
    public String execute() throws CRMDBException {
        LOG.debug("method getAllContact start!");
        ContactSearchBean searchBean = (ContactSearchBean) CRMUtils.jsonToBean(jsonString,
                ContactSearchBean.class);
        map = getContactInfoService().getAllContact(searchFlag, searchBean, page, super.limit, customerID);
        LOG.debug("method getAllContact end!");
        return SUCCESS;
    }

    /**
     * format and check message which are change from jsonField
     * 
     */
    @Override
    @SuppressWarnings("unchecked")
    public void validateUpdate() {
        // get contact message
        contact = (ContactInfoDto) CRMUtils.jsonToBean(jsonString, ContactInfoDto.class);

        // get familyInfoList message
        if (familyInfo != null) {
            JSONArray familyJson = JSONArray.fromObject(familyInfo);
            familyInfoList.addAll(JSONArray.toCollection(familyJson, FamilyDto.class));
        }

        // get socialInfoList message
        if (socialInfo != null) {
            JSONArray socialJson = JSONArray.fromObject(socialInfo);
            socialInfoList.addAll(JSONArray.toCollection(socialJson, SocialDto.class));
        }
        contactCheck();
    }

    /**
     * check contact messages
     */
    private void contactCheck() {
        // check contact message
        if (StringUtils.isBlank(contact.getContactName())) {
            addFieldError("contactName",
                    this.getText("name.invalid") + this.getText("not_null.invalid"));
        }
        if (StringUtils.isBlank(contact.getCompany())) {
            addFieldError("contactCompany",
                    this.getText("company.invalid") + this.getText("not_null.invalid"));
        }
        if (StringUtils.isBlank(contact.getJob())) {
            addFieldError("contactJob",
                    this.getText("job.invalid") + this.getText("not_null.invalid"));
        }
        if (StringUtils.isBlank(contact.getDepartment())) {
            addFieldError("contactDepartment",
                    this.getText("department.invalid") + this.getText("not_null.invalid"));
        }
        if (contact.getContactName().length() > ContactConstant.LENGTH_1) {
            addFieldError("contactName",
                    this.getText("name.invalid") + this.getText("flow.invalid"));
        }
        if (contact.getCompany().length() > ContactConstant.LENGTH_2) {
            addFieldError("contactCompany",
                    this.getText("company.invalid") + this.getText("flow.invalid"));
        }
        if (contact.getDepartment().length() > ContactConstant.LENGTH_2) {
            addFieldError("contactDepartment",
                    this.getText("department.invalid") + this.getText("flow.invalid"));
        }
        if (contact.getJob().length() > ContactConstant.LENGTH_2) {
            addFieldError("contactJob", this.getText("job.invalid") + this.getText("flow.invalid"));
        }
        if (!contact.getQQ().isEmpty()) {
            if (contact.getQQ().length() > ContactConstant.LENGTH_1) {
                addFieldError("contactQQ",
                        this.getText("QQ.invalid") + this.getText("flow.invalid"));
            }
            if (!contact.getQQ().matches(ContactConstant.QQ_REG)) {
                addFieldError("contactQQ",
                        this.getText("QQ.invalid") + this.getText("format_error.invalid"));
            }
        }

        if (contact.getDescriptions().length() > ContactConstant.LENGTH_3) {
            addFieldError("contactDescriptions",
                    this.getText("sourcedescriptions.invalid") + this.getText("flow.invalid"));
        }
        if (!contact.getPhoneNumber().matches(ContactConstant.PHONE_REG)) {
            addFieldError("contactPhoneNumber",
                    this.getText("phonenumber.invalid") + this.getText("format_error.invalid"));
        }
        if (!contact.getFax().isEmpty()) {
            if (!contact.getFax().matches(ContactConstant.FAX_REG)) {
                addFieldError("contactFax",
                        this.getText("fax.invalid") + this.getText("format_error.invalid"));
            }
        }
        if (!contact.getEmail().isEmpty()) {
            if (!contact.getEmail().matches(ContactConstant.EMAIL_REG)) {
                addFieldError("contactEmail",
                        this.getText("email.invalid") + this.getText("format_error.invalid"));
            }
        }
        if (!contact.getHomePhone().isEmpty()) {
            if (!contact.getHomePhone().matches(ContactConstant.PHONE_REG)) {
                addFieldError("contactHomePhone",
                        this.getText("homephone.invalid") + this.getText("format_error.invalid"));
            }
        }

        if (!CRMUtils.dateCheck(contact.getBirthday())) {
            addFieldError("contactBirthday",
                    this.getText("birthday.invalid") + this.getText("format_error.invalid"));
        }
        if (!CRMUtils.dateJudge(contact.getBirthday())) {
            addFieldError("contactBirthday",
                    this.getText("birthday.invalid") + this.getText("date_error.invalid"));
        }

        if (!contact.getSex().matches(ContactConstant.INT_REG)) {
            addFieldError("contactSex",
                    this.getText("sex.invalid") + this.getText("combobox_error.invalid"));
        }
        if (!StringUtils.isBlank(contact.getEducation())) {
            if (!contact.getEducation().matches(ContactConstant.INT_REG)) {
                addFieldError("contactEducation",
                        this.getText("education.invalid") + this.getText("combobox_error.invalid"));
            }
        }

//        if (!"000100070003".equals(contact.getContactType()) && contactInfoService.exist(contact)) {
//            addFieldError("contactType", "该客户的【决策人】或【关键人】已经存在！");
//        }

        String gridCheck = CRMConstant.SPACE;

        // check familyInfoList message
        if (familyInfoList != null) {
            for (int i = 0; i < familyInfoList.size(); i++) {
                FamilyDto familyInfo = familyInfoList.get(i);
                if (StringUtils.isBlank(familyInfo.getFamilyName())) {
                    gridCheck = gridCheck + this.getText("family.invalid")
                            + this.getText("name.invalid") + this.getText("not_null.invalid")
                            + CRMConstant.BR;
                }
                if (StringUtils.isBlank(familyInfo.getFamilyRelation())) {
                    gridCheck = gridCheck + this.getText("family.invalid")
                            + this.getText("relation.invalid") + this.getText("not_null.invalid")
                            + CRMConstant.BR;
                }
                if (familyInfo.getDescriptions().length() > ContactConstant.LENGTH_3) {
                    gridCheck = gridCheck + this.getText("family.invalid")
                            + familyInfo.getFamilyName()
                            + this.getText("sourcedescriptions.invalid")
                            + this.getText("flow.invalid") + CRMConstant.BR;
                }
                if (familyInfo.getFamilyRelation().length() > ContactConstant.LENGTH_1) {
                    gridCheck = gridCheck + this.getText("family.invalid")
                            + familyInfo.getFamilyName() + this.getText("relation.invalid")
                            + this.getText("flow.invalid") + CRMConstant.BR;
                }
                if (familyInfo.getFamilyName().length() > ContactConstant.LENGTH_1) {
                    gridCheck = gridCheck + this.getText("family.invalid")
                            + familyInfo.getFamilyName() + this.getText("name.invalid")
                            + this.getText("flow.invalid") + CRMConstant.BR;
                }
                if (familyInfo.getCompany().length() > ContactConstant.LENGTH_2) {
                    gridCheck = gridCheck + this.getText("family.invalid")
                            + familyInfo.getFamilyName() + this.getText("company.invalid")
                            + this.getText("flow.invalid") + CRMConstant.BR;
                }
                if (familyInfo.getJob().length() > ContactConstant.LENGTH_2) {
                    gridCheck = gridCheck + this.getText("family.invalid")
                            + familyInfo.getFamilyName() + this.getText("job.invalid")
                            + this.getText("flow.invalid") + CRMConstant.BR;
                }
                if (!StringUtils.isBlank(familyInfo.getBirthday())) {
                    if (!CRMUtils.dateCheck(familyInfo.getBirthday().substring(0, 10))) {
                        gridCheck = gridCheck + this.getText("family.invalid")
                                + familyInfo.getFamilyName() + this.getText("birthday.invalid")
                                + this.getText("format_error.invalid") + CRMConstant.BR;
                    } else {
                        if (!CRMUtils.dateJudge(familyInfo.getBirthday().substring(0, 10))) {
                            gridCheck = gridCheck + this.getText("family.invalid")
                                    + familyInfo.getFamilyName() + this.getText("birthday.invalid")
                                    + this.getText("date_error.invalid") + CRMConstant.BR;
                        }
                    }
                }
            }
        }

        // check socialInfoList message
        if (socialInfoList != null) {
            for (int i = 0; i < socialInfoList.size(); i++) {
                SocialDto socialInfo = socialInfoList.get(i);
                if (StringUtils.isBlank(socialInfo.getSocialName())) {
                    gridCheck = gridCheck + this.getText("social.invalid")
                            + this.getText("name.invalid") + this.getText("not_null.invalid")
                            + CRMConstant.BR;
                }
                if (StringUtils.isBlank(socialInfo.getSocialRelation())) {
                    gridCheck = gridCheck + this.getText("social.invalid")
                            + this.getText("relation.invalid") + this.getText("not_null.invalid")
                            + CRMConstant.BR;
                }
                if (socialInfo.getDescriptions().length() > ContactConstant.LENGTH_3) {
                    gridCheck = gridCheck + this.getText("social.invalid")
                            + socialInfo.getSocialName()
                            + this.getText("sourcedescriptions.invalid")
                            + this.getText("flow.invalid") + CRMConstant.BR;
                }
                if (socialInfo.getSocialRelation().length() > ContactConstant.LENGTH_1) {
                    gridCheck = gridCheck + this.getText("social.invalid")
                            + socialInfo.getSocialName() + this.getText("relation.invalid")
                            + this.getText("flow.invalid") + CRMConstant.BR;
                }
                if (socialInfo.getSocialName().length() > ContactConstant.LENGTH_1) {
                    gridCheck = gridCheck + this.getText("social.invalid")
                            + socialInfo.getSocialName() + this.getText("name.invalid")
                            + this.getText("flow.invalid") + CRMConstant.BR;
                }
                if (socialInfo.getCompany().length() > ContactConstant.LENGTH_2) {
                    gridCheck = gridCheck + this.getText("social.invalid")
                            + socialInfo.getSocialName() + this.getText("company.invalid")
                            + this.getText("flow.invalid") + CRMConstant.BR;
                }
                if (socialInfo.getJob().length() > ContactConstant.LENGTH_2) {
                    gridCheck = gridCheck + this.getText("social.invalid")
                            + socialInfo.getSocialName() + this.getText("job.invalid")
                            + this.getText("flow.invalid") + CRMConstant.BR;
                }
                if (!StringUtils.isBlank(socialInfo.getBirthday())) {
                    if (!CRMUtils.dateCheck(socialInfo.getBirthday().substring(0, 10))) {
                        gridCheck = gridCheck + this.getText("family.invalid")
                                + socialInfo.getSocialName() + this.getText("birthday.invalid")
                                + this.getText("format_error.invalid") + CRMConstant.BR;
                    } else {
                        if (!CRMUtils.dateJudge(socialInfo.getBirthday().substring(0, 10))) {
                            gridCheck = gridCheck + this.getText("family.invalid")
                                    + socialInfo.getSocialName() + this.getText("birthday.invalid")
                                    + this.getText("date_error.invalid") + CRMConstant.BR;
                        }
                    }
                }
            }
        }

        // if fieldError isn't empty,return check message
        if (gridCheck.length() > 0 || getFieldErrors().size() > 0) {
//            addFieldError("gridError", gridCheck);
//            addFieldError("validate", "false");
            map.putAll(getFieldErrors());
            map.put("validate", false);
        }

    }

    /**
     * method execute for add or update contact record, maintenance it's
     * familyInfo message and socialInfo message
     * 
     * @return success add/edit message success
     * @throws CRMDBException
     *             DataAccessException in case of Hibernate Exception
     */
    @Override
    public String update() throws CRMDBException {
        LOG.debug("method add or update contact start!");
        contactInfoService.addContact(contact, familyInfoList, socialInfoList, familyID, socialID);
        LOG.debug("method add or update contact end!");
        return SUCCESS;
    }

    /**
     * method execute for delete contact record by contactIDs
     * 
     * @return success delete success
     * @throws CRMDBException
     *             DataAccessException in case of Hibernate Exception
     */
    @Override
    public String delete() throws CRMDBException {
        LOG.debug("method delete contact start!");
        map = contactInfoService.deleteContact(jsonString);
        LOG.debug("method delete contact end!");
        return SUCCESS;
    }

    /**
     * method get contact can select
     * 
     * @return SUCCESS
     * @throws CRMDBException
     *             DataAccessException in case of Hibernate Exception
     */
    public String getContactCanSelect() throws CRMDBException {
        LOG.debug("method getContactCanSelect start!");
        map = contactInfoService.getContactCanSelect(contactSearchText);
        LOG.debug("method getContactCanSelect end!");
        return SUCCESS;
    }

    /**
     * @return the contactInfoService
     */
    public ContactInfoService getContactInfoService() {
        return contactInfoService;
    }

    /**
     * @param contactInfoService
     *            the contactInfoService to set
     */
    public void setContactInfoService(ContactInfoService contactInfoService) {
        this.contactInfoService = contactInfoService;
    }

    /**
     * @return the familyInfo
     */
    public String[] getFamilyInfo() {
        return familyInfo;
    }

    /**
     * @param familyInfo
     *            the familyInfo to set
     */
    public void setFamilyInfo(String[] familyInfo) {
        this.familyInfo = familyInfo;
    }

    /**
     * @return the socialInfo
     */
    public String[] getSocialInfo() {
        return socialInfo;
    }

    /**
     * @param socialInfo
     *            the socialInfo to set
     */
    public void setSocialInfo(String[] socialInfo) {
        this.socialInfo = socialInfo;
    }

    /**
     * @return the familyID
     */
    public String getFamilyID() {
        return familyID;
    }

    /**
     * @param familyID
     *            the familyID to set
     */
    public void setFamilyID(String familyID) {
        this.familyID = familyID;
    }

    /**
     * @return the socialID
     */
    public String getSocialID() {
        return socialID;
    }

    /**
     * @param socialID
     *            the socialID to set
     */
    public void setSocialID(String socialID) {
        this.socialID = socialID;
    }

    /**
     * 
     * @return the contactSearchText
     */
    public String getContactSearchText() {
        return contactSearchText;
    }

    /**
     * 
     * @param contactSearchText
     *            the contactSearchText to set
     * @throws UnsupportedEncodingException
     */
    public void setContactSearchText(String contactSearchText) throws UnsupportedEncodingException {
        this.contactSearchText = URLDecoder.decode(contactSearchText, "UTF-8");
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

}
