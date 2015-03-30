package com.ustcsoft.gs.crm.webui.contact.bean;

import com.ustcsoft.gs.crm.webui.contact.dto.ContactInfoDto;

/**
 * sex and education's display information
 * 
 * @author shenkaichuan
 * 
 */
public class ContactBean extends ContactInfoDto {
    /** sex's display information */
    private String sexName;

    /** education's display information */
    private String educationName;

    private String contactTypeName;

    public ContactBean(ContactInfoDto contact, String sexName, String educationName,String contactTypeName) {
        this.sexName = sexName;
        this.educationName = educationName;
        this.contactTypeName = contactTypeName;
        super.setAddr(contact.getAddr());
        super.setBirthday(contact.getBirthday());
        super.setCompany(contact.getCompany());
        super.setContactID(contact.getContactID());
        super.setContactName(contact.getContactName());
        super.setContactNational(contact.getContactNational());
        super.setDepartment(contact.getDepartment());
        super.setDescriptions(contact.getDescriptions());
        super.setEduBackground(contact.getEduBackground());
        super.setEducation(contact.getEducation());
        super.setEmail(contact.getEmail());
        super.setFax(contact.getFax());
        super.setHealth(contact.getHealth());
        super.setHobby(contact.getHobby());
        super.setHomePhone(contact.getHomePhone());
        super.setIsAbolished(contact.getIsAbolished());
        super.setJob(contact.getJob());
        super.setJobResume(contact.getJobResume());
        super.setNativePlace(contact.getNativePlace());
        super.setPhoneNumber(contact.getPhoneNumber());
        super.setPolitical(contact.getPolitical());
        super.setQQ(contact.getQQ());
        super.setSex(contact.getSex());
        super.setCustomerID(contact.getCustomerID());
        super.setContactType(contact.getContactType());
    }

    /**
     * 
     * @return sexName
     */
    public String getSexName() {
        return sexName;
    }

    /**
     * 
     * @param sexName
     *            the sexName to set
     */
    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    /**
     * 
     * @return educationName
     */
    public String getEducationName() {
        return educationName;
    }

    /**
     * 
     * @param educationName
     *            the educationName to set
     */
    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public String getContactTypeName() {
        return contactTypeName;
    }

    public void setContactTypeName(String contactTypeName) {
        this.contactTypeName = contactTypeName;
    }
}
