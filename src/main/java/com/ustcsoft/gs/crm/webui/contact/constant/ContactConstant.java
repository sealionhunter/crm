package com.ustcsoft.gs.crm.webui.contact.constant;

public class ContactConstant {

    public static final String CONTACTFAMILY = "contactFamily";
    public static final String CONTACTSOCIAL = "contactSocial";
    public static final String CONTACT_COUNT = "select count(*) from ContactInfoDto as con"
            + " where con.isAbolished = 0 and con.contactType = :contactType and con.contactID != :contactID and con.customerID = :customerID";
    public static final String GETALLCONTACT_HQL = "select new com.ustcsoft.gs.crm.webui.contact.bean.ContactBean(con,"
            + "(select com.value from CodeDto as com where com.code = con.sex) as sexName,"
            + "(select com.value from CodeDto as com where com.code = con.education) as educationName) "
            + "from ContactInfoDto as con where con.isAbolished = 0";
    public static final String GETALLCONTACT_HQL_2 = "select new com.ustcsoft.gs.crm.webui.contact.bean.ContactBean(con,"
            + "(select com.value from CodeDto as com where com.code = con.sex) as sexName,"
            + "(select com.value from CodeDto as com where com.code = con.education) as educationName,"
            + "(select com.value from CodeDto as com where com.code = con.contactType) as contactTypeName) "
            + "from ContactInfoDto as con where con.customerID = :customerID and con.isAbolished = 0";

    public static final String GETALLCONTACTCOUNT_HQL = "select count(*) from ContactInfoDto as con where con.isAbolished = 0";
    public static final String GETALLCONTACTCOUNT_HQL_2 = "select count(*) from ContactInfoDto as con where con.customerID = :customerID and con.isAbolished = 0";

    public static final String CONTACT_SIMPLESEARCH = "select new com.ustcsoft.gs.crm.webui.contact.bean.ContactBean(con,"
            + "(select com.value from CodeDto as com where con.sex = com.code) as sexName,"
            + "(select com.value from CodeDto as com where con.education = com.code) as educationName) "
            + "from ContactInfoDto as con where con.isAbolished = 0 "
            + "and (con.contactName like :searchText or con.company like :searchText "
            + "or con.department like :searchText or con.phoneNumber like :searchText)";
    public static final String CONTACT_SIMPLESEARCH_2 = "select new com.ustcsoft.gs.crm.webui.contact.bean.ContactBean(con,"
            + "(select com.value from CodeDto as com where con.sex = com.code) as sexName,"
            + "(select com.value from CodeDto as com where con.education = com.code) as educationName,"
            + "(select com.value from CodeDto as com where com.code = con.contactType) as contactTypeName) "
            + "from ContactInfoDto as con where con.customerID = :customerID and con.isAbolished = 0 "
            + "and (con.contactName like :searchText or con.company like :searchText "
            + "or con.department like :searchText or con.phoneNumber like :searchText)";

    public static final String CONTACT_SIMPLECOUNT = "select count(*) from ContactInfoDto as con where con.isAbolished = 0 "
            + "and (con.contactName like :searchText or con.company like :searchText "
            + "or con.department like :searchText or con.phoneNumber like :searchText)";
    public static final String CONTACT_SIMPLECOUNT_2 = "select count(*) from ContactInfoDto as con where con.customerID = :customerID and con.isAbolished = 0 "
            + "and (con.contactName like :searchText or con.company like :searchText "
            + "or con.department like :searchText or con.phoneNumber like :searchText)";

    public static final String CONTACT_ADVANCESEARCH = "select new com.ustcsoft.gs.crm.webui.contact.bean.ContactBean(con,"
            + "(select com.value from CodeDto as com where con.sex = com.code) as sexName,"
            + "(select com.value from CodeDto as com where con.education = com.code) as educationName) "
            + "from ContactInfoDto as con where con.isAbolished = 0 ";
    public static final String CONTACT_ADVANCESEARCH_2 = "select new com.ustcsoft.gs.crm.webui.contact.bean.ContactBean(con,"
            + "(select com.value from CodeDto as com where con.sex = com.code) as sexName,"
            + "(select com.value from CodeDto as com where con.education = com.code) as educationName,"
            + "(select com.value from CodeDto as com where com.code = con.contactType) as contactTypeName) "
            + "from ContactInfoDto as con where con.customerID = :customerID and con.isAbolished = 0 ";

    public static final String CONTACT_SEARCH = "and con.contactName like :contactName  and con.company like :contactCompany "
            + "and con.department like :contactDepartment and con.job like :contactJob";

    public static final String CONTACT_ADVANCECOUNT = "select count(*) from ContactInfoDto as con where con.customerID = :customerID and con.isAbolished = 0 ";

    public static final String CONTACT_FIND_BY_ID = "from ContactInfoDto con where con.contactID=";
    public static final String CONTACT_DELETE = "update ContactInfoDto contact set contact.isAbolished = 1  where contact.contactID in";
    public static final String CONTACT_DELETE_CHECK = "from ContactSelectDto consDto where consDto.contactID in";
    public static final String SOCIAL_DELETE = "update SocialDto Social set Social.isAbolished = 1 where Social.socialID=";
    public static final String FAMILY_DELETE = "update FamilyDto Family set Family.isAbolished = 1 where Family.familyID in";
    public static final String SOCIAL_SELECT = "from SocialDto Social where Social.isAbolished = 0 and Social.contactID=";
    public static final String SOCIAL_SELECT_ALL = "from SocialDto Social where Social.isAbolished = 0";
    public static final String FAMILY_SELECT = "from FamilyDto Family where Family.isAbolished = 0 and Family.contactID=";
    public static final String FAMILY_SELECT_ALL = "from FamilyDto Family where Family.isAbolished = 0";
    public static final String PHONE_REG = "(^(0\\d{2,3}-)?(\\d{7,8})$)|(^1[3-8][0-9]{9}$)";
    public static final String FAX_REG = "^(0\\d{2,3}-)(\\d{7,8})$";
    public static final String EMAIL_REG = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    public static final String QQ_REG = "^[1-9][0-9]{4,20}$";
    public static final String HTML_REG = "/<(\\S*?)[^>]*>.*?</\1>|<.*? />/g";
    public static final String INT_REG = "^\\d{12,12}$";
    public static final int LENGTH_1 = 20;
    public static final int LENGTH_2 = 50;
    public static final int LENGTH_3 = 1024;

    public static final String GET_CONTACT_SELECTED = "select contactID from ContactSelectDto";
    public static final String GET_CONTACT_CANSELECT = "from ContactInfoDto as ctDto where isAbolished=0 ";
    public static final String QUERY_CONTACT_CANSELECT = " and (ctDto.contactName like:contactSearchText"
            + " or ctDto.company like:contactSearchText "
            + " or ctDto.job like:contactSearchText "
            + " or ctDto.phoneNumber like:contactSearchText)";
    public static final String REMOVE_SELECTED = " and ctDto.contactID not in (:selectList)";
    public static final String ITEMS = "items";
    public static final String CONTACT_SEARCH_TEXT = "contactSearchText";
    public static final String SELECT_LIST = "selectList";

}
