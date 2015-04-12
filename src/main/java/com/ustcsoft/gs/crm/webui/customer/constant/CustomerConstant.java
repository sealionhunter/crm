package com.ustcsoft.gs.crm.webui.customer.constant;

public class CustomerConstant {

    public static final String MAXLENGTH_INVALID = "maxLength.invalid";

//    public static final String[] CUSTOMER_COMMON_WORD = new String[] {
//        "中国", "公司", "责任", "有限" , "股份",
//    };
    public static final String COMMON_WORD_CODE = "00010010";

    public static final String COMPETITORINFOID = "competitorInfoId";

    public static final String COMPETITORINFOID_INVALID = "competitorInfoId.invalid";

    public static final String AREA = "area";

    public static final String CHANCENAME = "chancename";

    public static final String FINDCHANCECONTENT = "findChanceContent";

    public static final String CHECKCHANCECONTENT = "checkChanceContent";

    public static final String AREA_INVALID = "area.invalid";

    public static final String AREANULL_INVALID = "areanull.invalid";

    public static final String ABILITY = "ability";

    public static final String TARGETS = "targets";

    public static final String STRATEGY = "strategy";

    public static final String PERDICTION = "prediction";

    public static final String CUSTOMER_TYPE = "customerType.invalid";

    public static final String ADVANTAGE = "advantage";
    public static final String ADVANTAGE_INVALID = "advantage.invalid";
    public static final String DISADVANTAGE = "disadvantage";
    public static final String DISADVANTAGE_INVALID = "disadvantage.invalid";
    public static final String ADVANALYSIS = "advAnalysis";
    public static final String DISADVANALYSIS = "disadvAnalysis";
    public static final String OTHERS = "others";
    public static final String COMPOSITECOMP = "compositeComp";
    public static final String COMPOSITEDEFENSE = "compositeDefense";
    public static final String COMPOSITECOMPDEFENSE_INVALID = "compositeCompDefense.invalid";
    public static final String ADVICE = "advice";

    public static final String CPRANALYSIS = "CprAnalysis";
    public static final String TIMEERROR = "time.invalid";

    /** customer transfer */
    public static final String AND_USER_GROUP_ID_2_ALL = " and user.groupID >= 2 ";

    public static final String AND_USER_GROUP_ID_2 = " and user.groupID > 2 ";

    public static final String AND_USER_GROUP_ID_1 = " and user.groupID > 1 ";

    public static final String AND_USER_GROUP_ID_3_ALL = " and user.groupID >= 3 ";

    public static final String AND_USER_GROUP_ID_3 = " and user.groupID > 3 ";

    public static final String AND_USER_GROUP_ID_4_ALL = " and user.groupID >= 4 ";

    public static final String AND_USER_GROUP_ID_4 = " and user.groupID > 4 ";

    public static final String USER_PROJECT_TEAM_ID = " and user.projectTeamID = :projectTeamID";

    public static final String SELECT_USER_INFO = "from UserInfoDto as user where user.isAbolished = 0 and user.userID>2";

    public static final String FROM_PROJECT_TEAM_DTO = "from ProjectTeamDto";

    public static final String USER_DEPARTMENT_ID = " and user.departmentID = :departmentID";

    public static final String SELECT_COUNT_TARANSFER = "select count(*) from CustomerDto as cus where cus.holder<=0 and cus.isAbolished = 0";

    public static final String CUS_TRANSFER_HQL = "select new com.ustcsoft.gs.crm.webui.customer.bean.TransferBean( "
            + "(select comB.value from CodeDto as comB where cus.fee = comB.code),cus.customerID,cus.customerAddr,cus.customerName,cus.fee)"
            + "from CustomerDto as cus where cus.isAbolished = 0 and cus.holder <= 0";

    public static final String SELECT__HOLDER = " and (select user.realName from UserInfoDto as user where user.userID = cus.holder) like:holder ";

    public static final String UPDATE_WORK = "update WorkDto as wd set wd.userID = ? where wd.isAbolished = 0 and teamFlag = 0 and wd.customerID in (";

    public static final String TRANSFER__PROJECT_TEAM_ID = USER_PROJECT_TEAM_ID;
    public static final String USER_ID = "userID";
    public static final String TRANSFER_USER_ID = " and user.userID = :userID";
    public static final String PROJECT_TEAM_ID = "projectTeamID";
    public static final String SELECT_DEPARTMENT = "select new Map(dmDto.departmentID as departmentID,dmDto.departmentName as departmentName) from DepartmentDto as dmDto";
    public static final String UPDATE_CUS_TRANSFER = "update CustomerDto as cus set cus.holder = ? where cus.customerID in ";
    public static final String SELECT_TRANSFER = "select new com.ustcsoft.gs.crm.webui.customer.bean.TransferBean( "
            + "(select comB.value from CodeDto as comB where cus.fee = comB.code),"
            + "user.realName,cus.customerID,cus.customerAddr,cus.customerName,cus.holder,cus.fee)"
            + "from CustomerDto as cus,UserInfoDto as user where cus.isAbolished = 0 and user.isAbolished = 0 and cus.holder = user.userID and user.userID in (:userIDs)";
    public static final String SELECT_TRANSFER_COUNT = "select count(*) from CustomerDto as cus,UserInfoDto as user where cus.isAbolished = 0 and user.isAbolished = 0 and cus.holder = user.userID and userID in (:userIDs)";
    public static final String USER_NOTMANAGER_NOTLEADER = " and (user.userID = :userID or user.departmentID in(:departmentIDs)) ";
    public static final String USER_MANAGER_NOTLEADER = " and user.departmentID in(:departmentIDs) ";
    public static final String USER_NOTMANAGER_LEADER = " and (user.userID = :userID or user.departmentID in(:departmentIDs) or user.projectTeamID =:projectTeamID)";
    public static final String USER_MANAGER_LEADER = " and (user.departmentID in(:departmentIDs) or user.projectTeamID =:projectTeamID) ";

    /** customer */
    public static final String VALUE_EVALUATE = "valueEvaluate";

    public static final String SCALE = "scale";

    public static final String INDUSTRY = "industry";

    public static final String FEE = "fee";

    public static final String CUSTOMER_STATEMENT = "customerStatement";

    public static final String CUSTOMER_NAME = "customerName";

    public static final String CUSTOMER_ADDR = "customerAddr";

    public static final String CUSTOMER_ID = "customerID";

    public static final String DESCRIPTIONS = "descriptions";

    public static final String PROJECT_DETAIL = "projectDetail";

    public static final String APPRAISAL = "appraisal";

    public static final String PRINCIPAL_THEY = "principalThey";

    public static final String PRINCIPAL_WE = "principalWe";

    public static final String PEOPLE_NUMBER = "peopleNumber";

    public static final String PROJECTSCALE = "projectScale";

    public static final String EXP_END_DATE = "expEndDate";

    public static final String EXP_BEGIN_DATE = "expBeginDate";

    public static final String PROJECT_TYPE = "projectType";

    public static final String PROJECT_NAME = "projectName";

    public static final String COOP_RESUME_ID = "coopResumeID";

    public static final String IS_ABOLISHED = "isAbolished";

    public static final String END_DATE = "endDate";

    public static final String BEGIN_DATE = "beginDate";

    public static final String CUS_FEE_IN = "and cus.fee in (:fee) ";

    public static final String CUS_INDUSTRY_IN = "and cus.industry in (:industry) ";

    public static final String CUS_SCALE_IN = "and cus.scale in (:scale) ";

    public static final String SELECT_CUS_CUSTOMER_NAME = "select cus.customerName from CustomerDto as cus where cus.isAbolished = 0 and cus.customerID like:customerID ";

    public static final String CUSTOMER_MID_SEARCH = " and cus.customerName like:customerName and cus.earning like:earning and cus.customerAddr like:customerAddr ";

    public static final String CUSTOMER_DEL_HQL = "update CustomerDto as cus set cus.updateTime=CONVERT(varchar(19), getdate(), 120),cus.isAbolished = 1 where cus.customerID in";

    public static final String CUSTOMER_COUNT_HQL = "select count(*) from CustomerDto as cus where cus.isAbolished = 0 ";

    public static final String CUS_COOP_DEL_HQL = "update CoopResumeDto as coop set coop.isAbolished = 1 where coop.customerID in";

    public static final String COOP_DEL_HQL = "update CoopResumeDto as coop set coop.isAbolished = 1 where coop.coopResumeID in";

    public static final String CUS_ORDER_DEL_HQL = "update OrderDto as ord set ord.isAbolished = 1 where ord.customerID in";

    public static final String CUS_WORK_DEL_HQL = "update WorkDto as work set work.isAbolished = 1 where work.customerID in";

    public static final String CUS_COURSE_DEL_HQL = "update SourceInfoDto as sid set sid.isAbolished = 1 where sid.customerID in";

    public static final String CUS_CONTACT_SELECT_DEL_HQL = "delete from ContactSelectDto csd where csd.flag=1 and csd.objectID in";

    public static final String COOPRESUME_COUNT_HQL = "select count(*) from CoopResumeDto as coop where coop.isAbolished = 0 and coop.customerID = ? ";

    public static final String CUSTOMER_HQL = "from CustomerDto as cus where cus.isAbolished = 0 ";

    public static final String HOLDER_USER_ID = " and cus.holder in (:userID) ";

    public static final String CUSTOMER_SIMPLE_AFTER_HQL = " and cus.holder in (:userID) and (cus.customerName like:searchText "
            + "or (select user.realName from UserInfoDto as user where user.userID = cus.holder) like:searchText "
            + "or (select comB.value from CodeDto as comB where cus.scale = comB.code) like:searchText "
            + "or (select comB.value from CodeDto as comB where cus.industry = comB.code)like:searchText "
            + "or (select comB.value from CodeDto as comB where cus.customerType = comB.code) like:searchText "
            + "or (select comB.value from CodeDto as comB where cus.fee = comB.code) like:searchText "
            + "or (select comB.value from CodeDto as comB where cus.customerStatement = comB.code) like:searchText "
            + "or (select comB.value from CodeDto as comB where cus.businessUnit = comB.code) like:searchText "
            + "or (select comB.value from CodeDto as comB where cus.valueEvaluate = comB.code) like:searchText "
            + "or cus.customerAddr like:searchText or cus.earning like:searchText or cus.descriptions like:searchText "
            // add 20150308 start
            + "or cus.business1 like:searchText or cus.business2 like:searchText "
            + "or cus.business3 like:searchText or cus.business4 like:searchText) ";
            // add 20150308 end
    public static final String CUS_COUNT_BEFORE_HQL = "select count(*) from CustomerDto as cus where cus.isAbolished = 0 ";

    public static final String CUS_GET_ALL_HQL = "select new Map( "
            + "(select comB.value from CodeDto as comB where comB.code = cus.scale) as scaleName, "
            + "(select comB.value from CodeDto as comB where comB.code = cus.industry) as industryName, "
            + "(select comB.value from CodeDto as comB where comB.code = cus.customerType) as customerTypeName, "
            + "(select comB.value from CodeDto as comB where comB.code = cus.customerStatement) as customerStatementName, "
            + "(select comB.value from CodeDto as comB where comB.code = cus.valueEvaluate) as valueEvaluateName, "
            + "(select comB.value from CodeDto as comB where comB.code = cus.fee) as feeName, "
            + "(select comB.value from CodeDto as comB where comB.code = cus.businessUnit) as businessUnitName, "
            + "(select user.realName from UserInfoDto as user where user.userID = cus.holder) as holderName,"
            // add 20150308 start
            + "cus.business1 as business1 ,cus.business2 as business2 ,"
            + "cus.business3 as business3 ,cus.business4 as business4 ,"
            + "cus.businessUnit as businessUnit ,cus.number as number ,"
            // add 20150308 end
            //2015-3-29 15:33:53 modified start
            + "cus.createTime as createTime, cus.updateTime as updateTime ,"
            //2015-3-29 15:33:53 modified end
            + "cus.customerAddr as customerAddr ,cus.customerName as customerName,cus.customerID as customerID ,cus.customerStatement as customerStatement, "
            + "cus.customerType as customerType,cus.descriptions as descriptions,cus.earning as earning,cus.fee as fee,cus.holder as holder,"
            + "cus.industry as industry,cus.scale as scale , cus.valueEvaluate as valueEvaluate)"
            + "from CustomerDto as cus where cus.isAbolished = 0 ";
    
    public static final String CUS_GET_BY_ID = "and cus.customerID=?";

    public static final String CUS_GET_IN_IDS = "and cus.customerID in ";

    public static final String CUS_CUSTOMERNAME_COUNT = "select count(*) from CustomerDto as cus where cus.isAbolished = 0 and cus.customerName = :customerName and cus.customerID != :customerID";
    public static final String COOP_PROJECTNAME_COUNT = "select count(*) from CoopResumeDto as coop where coop.isAbolished = 0 and coop.coopResumeID != :coopResumeID and coop.projectName = :projectName and customerID = :customerID ";

    public static final String COOP_FIND_PROJECTNAME = "select coop.projectName from CoopResumeDto as coop where coop.isAbolished = 0 and coop.coopResumeID like:coopResumeID ";
    public static final String COOP_SUPER_COUNT_HQL = "select count(*) from CoopResumeDto as coop where coop.isAbolished = 0 "
            + "and customerID like:customerID ";
    public static final String COOP_SUPER_QUERY_HQL = "select new Map( "
            + "(select comB.value from CodeDto as comB where coop.projectType = comB.code) as projectTypeName, "
            + "coop.projectName as projectName ,coop.expBeginDate as expBeginDate,coop.expEndDate as expEndDate ,coop.beginDate as beginDate, "
            + "coop.endDate as endDate,coop.projectScale as projectScale,coop.peopleNumber as peopleNumber,coop.principalThey as principalThey, "
            + "coop.principalWe as principalWe,coop.projectDetail as projectDetail,coop.projectType as projectType,coop.appraisal as appraisal, "
            + "coop.descriptions as descriptions) from CoopResumeDto as coop where coop.isAbolished = 0 "
            + "and customerID like:customerID ";
    public static final String COOP_CONDITIONS = " and coop.projectName like:projectName  "
            + " and coop.principalWe like:principalWe and coop.principalThey like:principalThey ";

    public static final String PROJECTTYPE_SEARCH = " and coop.projectType in (:projectType) ";

    public static final String COOP_BEGIN_DATE_MAX = " and coop.beginDate <= :beginDateSearchMax";

    public static final String COOP_BEGIN_DATE_MIN = " and coop.beginDate >= :beginDateSearchMin";

    public static final String GET_COOPRESUME_HQL = "select new Map("
            + "(select comB.value from CodeDto as comB where coop.projectType = comB.code) as projectTypeName, "
            + "coop.coopResumeID as coopResumeID,coop.customerID as customerID,coop.projectName as projectName,coop.expBeginDate as expBeginDate,"
            + "coop.expEndDate as expEndDate,coop.beginDate as beginDate,coop.endDate as endDate,coop.projectScale as projectScale,coop.peopleNumber as peopleNumber,"
            + "coop.principalThey as principalThey,coop.principalWe as principalWe,coop.projectDetail as projectDetail,coop.projectType as projectType,coop.appraisal as appraisal,coop.descriptions as descriptions)"
            + "from CoopResumeDto as coop where coop.isAbolished = 0 and coop.customerID like:customerID";
    public static final String COOPRESUME_SIMPLEQUERY_HQL = "select new Map( "
            + "(select comB.value from CodeDto as comB where coop.projectType = comB.code) as projectTypeName, "
            + "coop.coopResumeID as coopResumeID ,coop.customerID as customerID,coop.projectName as projectName ,coop.expBeginDate as expBeginDate ,"
            + "coop.expEndDate as expEndDate,coop.beginDate as beginDate,coop.endDate as endDate,coop.projectScale as projectScale,coop.peopleNumber as peopleNumber,"
            + "coop.principalThey as principalThey,coop.principalWe as principalWe , coop.projectDetail as projectDetail,coop.projectType as projectType,coop.appraisal as appraisal,coop.descriptions as descriptions) "
            + "from CoopResumeDto as coop where coop.isAbolished = 0 "
            + "and ((select comB.value from CodeDto as comB where coop.projectType = comB.code) like:searchText "
            + "or coop.projectName like:searchText or coop.expBeginDate like:searchText or coop.expEndDate like:searchText or coop.beginDate like:searchText or coop.endDate like:searchText "
            + "or coop.projectScale like:searchText or coop.peopleNumber like:searchText or coop.principalThey like:searchText or coop.principalWe like:searchText or coop.projectDetail like:searchText "
            + "or coop.appraisal like:searchText or coop.descriptions like:searchText) and customerID like:customerID ";
    public static final String COOPRESUME_SIMPLECOUNT_HQL = "select count(*) from CoopResumeDto as coop where coop.isAbolished = 0 "
            + "and ((select comB.value from CodeDto as comB where coop.projectType = comB.code) like:searchText "
            + "or coop.projectName like:searchText or coop.expBeginDate like:searchText or coop.expEndDate like:searchText or coop.beginDate like:searchText or coop.endDate like:searchText "
            + "or coop.projectScale like:searchText or coop.peopleNumber like:searchText or coop.principalThey like:searchText or coop.principalWe like:searchText or coop.projectDetail like:searchText "
            + "or coop.appraisal like:searchText or coop.descriptions like:searchText) and customerID like:customerID ";
    /** customer over */

    public static final String SOURCETYPE = "sourceType";

    public static final String PROJECTTYPE = "projectType";

    public static final String CONTACTHISTORYTYPE = "contactType";

    public static final String CONTACTHISTORYWAY = "contactWay";

    public static final String COUNT = "select count(*)";

    public static final String NO_TEMPLATE = "无模板";

    public static final String CONTACTHISTORY_SEARCH_DATE_END = "searchDateEnd";
    public static final String CONTACTHISTORY_SEARCH_DATE_START = "searchDateStart";
    public static final String CONTACTHISTORY_SEARCH_OBJECT = "contactHistorySearchObject";
    public static final String CONTACTHISTORY_OPPOSITE_CONTACT = "oppositeContact";
    public static final String CONTACTHISTORY_CUSTOMER_NAME = "customerName";
//    public static final String CONTACTHISTORY_WE_CONTACT = "weContact";
    public static final String CONTACTHISTORY_SEARCH_FLAG = "searchFlag";
    public static final String CONTACTHISTORY_SEARCH_FLAG_INVALID = "searchFlag.invalid";
    public static final String CONTACTHISTORY_MAXDATE_SEARCH_INVALID = "maxDateSearch.invalid";
    public static final String CONTACTHISTORY_MINDATE_SEARCH_INVALID = "minDateSearch.invalid";
    public static final String CONTACTHISTORY_OPPOSITE_CONTACT_INVALID = "contactHistoryOppositeContact.invalid";
//    public static final String CONTACTHISTORY_WE_CONTACT_INVALID = "contactHistoryWeContact.invalid";
    public static final String CONTACTHISTORY_CUSTOMER_INVALID = "contactHistoryCustomer.invalid";
    public static final String CONTACTHISTORY_SEARCH_TEXT_INVALID = "contactHistorySearchText.invalid";
    public static final String CONTACTHISTORY_JSON_ERROR = "json.error";
    public static final String CONTACTHISTORY_GETALL_HQL = "select new Map("
            + " conT.contactID as contactID,"
            + " conT.customerID as customerID, "
            + " (select cd.customerName from CustomerDto as cd where conT.customerID = cd.customerID and cd.isAbolished = 0) as customerName,"
            + " conT.contactTheme as contactTheme,"
            + " conT.weContact as weContact,"
//            + "(select uiDto.realName from UserInfoDto as uiDto where conT.weContact = uiDto.userID and uiDto.isAbolished = 0) as weContactName,"
            + " conT.oppositeContact as oppositeContact,"
            + " conT.contactWay as contactWay,"
            + " conT.contactType as contactType,"
            + " conT.ifContact as ifContact,"
            + " (select ci.contactName from ContactInfoDto as ci where conT.oppositeContact = ci.contactID and ci.isAbolished = 0) as oppositeContactName,"
            + " (select cod.value from CodeDto as cod where conT.contactType = cod.code) as contactTypeName,"
            + " (select cod.value from CodeDto as cod where conT.contactWay = cod.code) as contactWayName,"
            + " (select cod.value from CodeDto as cod where (conT.ifContact = 1 and cod.code='000600030001') or (conT.ifContact = 0 and cod.code='000600030002')) as ifContactName,"
            + " conT.contactContent as contactContent,"
            + " conT.planDateBegin as planDateBegin,"
            + " conT.realityDateBegin as realityDateBegin,"
            + " conT.realityDateEnd as realityDateEnd,"
            + " conT.notContantReason as notContantReason,"
            + " conT.userFeedbackInformation as userFeedbackInformation,"
            + " conT.strategy as strategy,"
            + " conT.remarks as remarks)"
//            + " conT.eventID as eventID,"
//            + " conT.state as state,"
//            + " (select seD.status from SalesEventDto as seD where conT.eventID = seD.eventID) as chanceType,"
//            + " (select seD.isAbolished from SalesEventDto as seD where conT.eventID = seD.eventID) as flowStatus,"
//            + " (select seD.eventName from SalesEventDto as seD where conT.eventID = seD.eventID) as eventName,"
//            + " (select sefD.demand from SalesEventFlowDto as sefD where sefD.status = 1 and conT.eventID = sefD.eventID) as findChanceContent,"
//            + " (select sefD.demand from SalesEventFlowDto as sefD where sefD.status = 2 and conT.eventID = sefD.eventID) as checkChanceContent,"
//            + " conT.checkResult as checkResult)"
            + " from ContactTrackInfoDto as conT where conT.isAbolished = 0"
            + " and (conT.realityDateBegin is not null or conT.notContantReason is not null)"
            + " and conT.customerID = :customerID";
//            + " and conT.customerID in (select cd.customerID from CustomerDto as cd where cd.customerID = conT.customerID and cd.isAbolished = 0 "
//            + " and cd.holder in (:userID))";
    public static final String CONTACTHISTORY_COUNT_HQL = "select count(*) from ContactTrackInfoDto as conT where conT.isAbolished = 0"
            + " and (conT.realityDateBegin is not null or conT.notContantReason is not null)"
            + " and conT.customerID = :customerID";
//            + " and conT.customerID in (select cd.customerID from CustomerDto as cd where cd.customerID = conT.customerID and cd.isAbolished = 0 and cd.holder in (:userID))";
    public static final String CONTACTHISTORY_SIMPLE_HQL = " and ((select cd.customerName from CustomerDto as cd where conT.customerID = cd.customerID)like :searchText "
//            + " or (select uiDto.realName from UserInfoDto as uiDto where conT.weContact = uiDto.userID) like :searchText "
            + " or (select ci.contactName from ContactInfoDto as ci where conT.oppositeContact = ci.contactID)like :searchText"
            + " or convert(varchar(20),conT.realityDateBegin,120) like :searchText or convert(varchar(20),conT.realityDateEnd,120) like :searchText "
            + " or conT.contactType in (select cod.code from CodeDto as cod where cod.value like :searchText)"
            + " or conT.contactWay in (select cod.code from CodeDto as cod where cod.value like :searchText)"
            + " or (select cod.value from CodeDto as cod where (conT.ifContact = 1 and cod.code='000600030001') or (conT.ifContact = 0 and cod.code='000600030002')) like :searchText)";
    public static final String CONTACTHISTORY_CUSTOMERSEARCH = " and (select cd.customerName from CustomerDto as cd where conT.customerID = cd.customerID) like :customerName ";
//    public static final String CONTACTHISTORY_WECONTACTSEARCH = " and (select uiDto.realName from UserInfoDto as uiDto where conT.weContact = uiDto.userID ) like :weContact ";
    public static final String CONTACTHISTORY_OPPOSITESEARCH = " and (select ci.contactName from ContactInfoDto as ci where conT.oppositeContact = ci.contactID)like :oppositeContact ";
    public static final String CONTACTHISTORY_DATESTARTSEARCH = " and conT.realityDateBegin >= :searchDateStart ";
    public static final String CONTACTHISTORY_DATEENDSEARCH = " and conT.realityDateBegin < DATEADD( day , 1, :searchDateEnd ) ";
    public static final String CONTACTHISTORY_CONTACTTYPESEARCH = " and conT.contactType in (:contactType)";
    public static final String CONTACTHISTORY_CONTACTWAYSEARCH = " and conT.contactWay in (:contactWay)";
    public static final String CONTACTHISTORY_IFCONTACTSEARCH = " and conT.ifContact =";

    public static final String GET_ALLCONTACTTRACK_TOTAL_HQL = "select count(*) from ContactTrackInfoDto as conT where conT.isAbolished = 0"
            + " and conT.realityDateBegin is null"
            + " and conT.notContantReason is null"
//            + " and conT.customerID in (select cd.customerID from CustomerDto as cd where cd.customerID = conT.customerID and cd.isAbolished = 0 and cd.holder in (:userID))";
+ " and conT.customerID = :customerID";
    public static final String CONTACTTRACK_CUSTOMER_SEARCH_HQL = " and (select cd.customerName from CustomerDto as cd where conT.customerID = cd.customerID) like :customerName ";
//    public static final String CONTACTTRACK_WECONTACT_SEARCH_HQL = " and (select uiDto.realName from UserInfoDto as uiDto where conT.weContact = uiDto.userID ) like :weContact ";
    public static final String CONTACTTRACK_OPPOSITECONTACT_SEARCH_HQL = " and (select ci.contactName from ContactInfoDto as ci where conT.oppositeContact = ci.contactID)like :oppositeContact ";
    public static final String CONTACTTRACK_CONTACTDATESTART_SEARCH_HQL = " and conT.planDateBegin >= :planDateMin ";
    public static final String CONTACTTRACK_CONTACTDATEEND_SEARCH_HQL = " and conT.planDateBegin < DATEADD( day , 1, :planDateMax )  ";
    public static final String CONTACTTRACK_CONTACTWAY_SEARCH_HQL = " and conT.contactWay in (:contactWay) ";
    public static final String CONTACTTRACK_CONTACTTYPE_SEARCH_HQL = " and conT.contactType in (:contactType) ";

    public static final String CONTACT_SIMPLE_HQL = " and ((select cd.customerName from CustomerDto as cd where conT.customerID = cd.customerID and cd.isAbolished = 0)like :searchText "
//            + "or (select uiDto.realName from UserInfoDto as uiDto where conT.weContact = uiDto.userID ) like :searchText "
            + "or (select ci.contactName from ContactInfoDto as ci where conT.oppositeContact = ci.contactID  and ci.isAbolished = 0)like :searchText"
            + " or convert(varchar(20),conT.planDateBegin,120) like :searchText"
            + " or conT.contactType in (select cod.code from CodeDto as cod"
            + " where  cod.value like :searchText)"
            + " or conT.contactWay in (select cod.code from CodeDto as cod"
            + " where cod.value like :searchText))";

    public static final String GET_CONTACTTRACK_PREFIX_HQL = "select new Map("
            + " conT.contactID as contactID,"
            + " conT.customerID as customerID,"
            + " (select cd.customerName from CustomerDto as cd where conT.customerID = cd.customerID and cd.isAbolished = 0) as customerName,"
            + " conT.contactTheme as contactTheme,"
//            + " conT.weContact as weContact,"
//            + " (select uiDto.realName from UserInfoDto as uiDto where conT.weContact = uiDto.userID and uiDto.isAbolished = 0) as weContactName,"
            + " conT.oppositeContact as oppositeContact,"
            + " (select ci.contactName from ContactInfoDto as ci where conT.oppositeContact = ci.contactID and ci.isAbolished = 0) as oppositeContactName,"
            + " conT.contactWay as contactWay,"
            + " (select cod.value from CodeDto as cod where cod.code = conT.contactWay) as contactWayName,"
            + " conT.contactContent as contactContent,"
            + " conT.contactType as contactType,"
            + " (select cod.value from CodeDto as cod where cod.code = conT.contactType) as contactTypeName,"
            + " conT.planDateBegin as planDateBegin,"
            + " conT.ifContact as ifContactName,"
            + " conT.remarks as remarks)"
//            + " conT.eventID as eventID,"
//            + " conT.state as state,"
//            + " conT.checkResult as checkResult)"
            + " from ContactTrackInfoDto as conT";

    public static final String GET_ALLCONTACTTRACK_HQL = GET_CONTACTTRACK_PREFIX_HQL
            + " where conT.isAbolished = 0"
            + " and conT.realityDateBegin is null"
            + " and conT.notContantReason is null"
            + " and conT.customerID = :customerID";

//    public static final String GET_CONTACT_TO_NOTIFICATION_HQL = GET_CONTACTTRACK_PREFIX_HQL
//            + " where conT.isAbolished = 0"
//            + " and conT.realityDateBegin is null"
//            + " and conT.notContantReason is null"
//            + " and conT.weContact = :userID"
//            + " and conT.planDateBegin >= :date";

    public static final String GETNUMOFCONTACTTRACK_HQL = "update ContactTrackInfoDto as conT set conT.isAbolished = 'true'"
            + " where conT.contactID in ";

    public static final String CONTACTSELECT_HQL = "select new Map(cons.contactSelectID as contactSelectID,cons.objectID as objectID,"
            + "con.contactID as contactID,con.contactName as contactName,con.company as company,con.job as job,con.phoneNumber as "
            + "phoneNumber) from ContactInfoDto as con,ContactSelectDto as cons where con.contactID = cons.contactID and cons.objectID = :objectID "
            + "and cons.flag = :flag and con.isAbolished = 0";
    public static final String CONTACTSELECT_COUNT_HQL = "select count(*) from ContactInfoDto as con,ContactSelectDto as cons"
            + " where con.contactID = cons.contactID and cons.objectID = :objectID and cons.flag = :flag and con.isAbolished = 0";
    public static final String CONTACTSELECT = "from ContactSelectDto as cons where cons.contactID like :contactID and cons.objectName like :objectName";

    /** cprAnalysis */
    /** get the total of cprAnalysisinfo */
    public static final String CPRANALYSISTOTAL_HQL = "select count(*)"
            + " from CompetitorinfoDto as comp,CodeDto as cd,CprAnalysisDto as cpra"
            + " where cpra.isAbolished = 0 and cd.code like '00010008%'"
            + " and cd.code = cpra.area and comp.competitorInfoId = cpra.competitorInfoId";
    public static final String CPRANALYSISTOTALWIN_HQL = "select count(*)"
            + " from CompetitorinfoDto as comp,CodeDto as cd,CprAnalysisDto as cpra"
            + " where cpra.isAbolished = 0 and cd.code like '00010008%'"
            + " and comp.competitorInfoId = :competitorInfoId"
            + " and cd.code = cpra.area and comp.competitorInfoId = cpra.competitorInfoId";
    /** get all cprAnalysisinfo from the DB */
    public static final String CPRANALYSIS_HQL = "select new Map(cpra.cprAnalysisID as cprAnalysisID,cpra.competitorInfoId as competitorInfoId,"
            + " comp.competitorName as competitorName, cpra.area as area, cpra.ability as ability, cpra.targets as targets,cpra.strategy as strategy, "
            + "cpra.prediction as prediction,cpra.advantage as advantage,cpra.disadvantage as disadvantage,cpra.advAnalysis as advAnalysis,"
            + "cpra.disadvAnalysis as disadvAnalysis, cpra.others as others,cpra.compositeComp as compositeComp,cpra.compositeDefense as compositeDefense,"
            + "cpra.advice as advice, cpra.cprAnalysisTime as cprAnalysisTime,cd.value as areaName) "
            + "from CompetitorinfoDto as comp,CodeDto as cd,CprAnalysisDto as cpra "
            + "where cpra.isAbolished = 0 and cd.code like '00010008%' "
            + "and cd.code = cpra.area and comp.competitorInfoId = cpra.competitorInfoId order by comp.competitorInfoId, cpra.cprAnalysisTime desc";
    public static final String CPRANALYSISWIN_HQL = "select new Map(cpra.cprAnalysisID as cprAnalysisID,cpra.competitorInfoId as competitorInfoId,"
            + " comp.competitorName as competitorName, cpra.area as area, cpra.ability as ability, cpra.targets as targets,cpra.strategy as strategy, "
            + "cpra.prediction as prediction,cpra.advantage as advantage,cpra.disadvantage as disadvantage,cpra.advAnalysis as advAnalysis,"
            + "cpra.disadvAnalysis as disadvAnalysis, cpra.others as others,cpra.compositeComp as compositeComp,cpra.compositeDefense as compositeDefense,"
            + "cpra.advice as advice, cpra.cprAnalysisTime as cprAnalysisTime,cd.value as areaName) "
            + "from CompetitorinfoDto as comp,CodeDto as cd,CprAnalysisDto as cpra "
            + "where cpra.isAbolished = 0 and cd.code like '00010008%' "
            + " and comp.competitorInfoId = :competitorInfoId"
            + " and cd.code = cpra.area and comp.competitorInfoId = cpra.competitorInfoId order by comp.competitorInfoId, cpra.cprAnalysisTime desc";

    /** get all competitorName from table CompetitorinfoDto */
    public static final String GET_ALLCPRNAME = "select new Map(comp.competitorInfoId as "
            + "competitorInfoId,comp.competitorName as competitorName) from CompetitorinfoDto "
            + "as comp where comp.isAbolished = 0 order by comp.competitorName";

    /** delete cprAnalysis info from the DB */
    public static final String DELETE_CPRANALYSIS = "update CprAnalysisDto as cpra set cpra.isAbolished = 1 where cpra.cprAnalysisID in ";

    public static final String GET_COOPERATORS_HQL = "from CooperatorDto as cooperator where cooperator.isAbolished=0";

    public static final String PROPOSALT_COUNT_HQL = "select count(*) from ProposalTDto as prot where prot.isAbolished = 0";
    public static final String PROPOSALT_NAMEANDID = "select new Map(prot.proposalTemplateID as proposalTemplateID,prot.proposalTemplateName as proposalTemplateName,prot.proposalTemplateValue as proposalTemplateValue) "
            + "from ProposalTemplateDto as prot where prot.isAbolished = 0 ";
    public static final String FIND_PROPOSALNAME_HQL = "select count(*) from ProposalTDto as prot where prot.proposalName = ?";

    /** competitor */
    public static final String COMPETITOR_COUNT_HQL = "select count(*) from CompetitorinfoDto as ci where ci.isAbolished = 0 ";
    public static final String COMPETITOR_NAME_COUNT_HQL = COMPETITOR_COUNT_HQL
            + "and ci.competitorName =:competitorName ";
    public static final String COMPETITOR_FINDNAME_HQL = "select com.competitorName from CompetitorinfoDto as com "
            + "where com.isAbolished = 0 and com.competitorInfoId =:competitorInfoId";
    public static final String GET_ALLCOMPETITOR_HQL = "select new Map(ci.competitorName as competitorName,ci.competitorLocation as competitorLocation, "
            + "ci.competitorFoundDate as competitorFoundDate,ci.competitorField as competitorField, "
            + "ci.competitorDescription as competitorDescription,ci.competitorDetail as competitorDetail, "
            + "ci.competitorType as competitorType, ci.competitorInfoId as competitorInfoId, "
            + "ci.competitorProperty as competitorProperty,ci.competitorMoney as competitorMoney,ci.competitorPeople as competitorPeople, "
            + "(select code.value from CodeDto as code where code.code like '00070003%' and ci.competitorProperty = code.code) as competitorPropertyB, "
            + "(select code.value from CodeDto as code where code.code like '00070001%' and ci.competitorMoney = code.code) as competitorMoneyB, "
            + "(select code.value from CodeDto as code where code.code like '00070004%' and ci.competitorType = code.code) as competitorTypeB, "
            + "(select code.value from CodeDto as code where code.code like '00070002%' and ci.competitorPeople = code.code) as competitorPeopleB) "
            + "from CompetitorinfoDto as ci where ci.isAbolished = 0 ";
    public static final String MID_GETALLCOMPETITOR_HQL = " and ci.competitorName like:competitorName and ci.competitorField like:competitorField and ci.competitorLocation like:competitorLocation and ci.competitorType =:competitorType ";
    public static final String COMPETITOR_HQL = "select new Map( "
            + "(select code.value from CodeDto as code where code.code like '00070003%' and ci.competitorProperty = code.code) as competitorPropertyB, "
            + "(select code.value from CodeDto as code where code.code like '00070001%' and ci.competitorMoney = code.code) as competitorMoneyB, "
            + "(select code.value from CodeDto as code where code.code like '00070002%' and ci.competitorPeople = code.code) as competitorPeopleB, "
            + "ci.competitorInfoId as competitorInfoId, ci.competitorName as competitorName ,ci.competitorField as competitorField,ci.competitorLocation  as competitorLocation ,ci.competitorFoundDate as competitorFoundDate, "
            + "ci.competitorDescription as competitorDescription,ci.competitorDetail as competitorDetail,ci.competitorProperty as competitorProperty,ci.competitorMoney as competitorMoney,"
            + "ci.competitorType as competitorType,"
            + "ci.competitorPeople as competitorPeople,ci.competitorFoundDate as competitorFoundDate)"
            + "from CompetitorinfoDto as ci " + "where ci.isAbolished = 0 ";
    public static final String COMPETITOR_SIMPLE_HQL = " and ci.competitorType =:type "
            + "and ( ci.competitorName like:searchText "
            + " or ci.competitorField like:searchText "
            + " or ci.competitorLocation like:searchText "
            + " or ci.competitorFoundDate like:searchText "
            + "or (select code.value from CodeDto as code where code.code like '00070003%' and ci.competitorProperty = code.code) like:searchText "
            + "or (select code.value from CodeDto as code where code.code like '00070001%' and ci.competitorMoney = code.code) like:searchText "
            + "or (select code.value from CodeDto as code where code.code like '00070002%' and ci.competitorPeople = code.code) like:searchText) ";

    /** cooperator */
    public static final String COPANALYSIS_LIST_BEFORE_HQL = "select new Map( "
            + "(select coop.cooperatorName from CooperatorDto as coop where coop.isAbolished = 0"
            + " and coop.cooperatorID = cop.cooperatorID) as cooperatorName,"
            + "(select comB.value from CodeDto as comB where comB.code ="
            + "cop.managementAbility) as managementAbilityName, "
            + "(select comB.value from CodeDto as comB where comB.code ="
            + "cop.responseSpeed) as responseSpeedName, "
            + "(select comB.value from CodeDto as comB where comB.code ="
            + "cop.trustDegree) as trustDegreeName, "
            + "cop.copAnalysisID as copAnalysisID, cop.cooperatorID as cooperatorID ,"
            + "cop.advantageField as advantageField, cop.disadvantageField as disadvantageField, "
            + "cop.managementAbility as managementAbility, cop.responseSpeed as responseSpeed, "
            + "cop.trustDegree as trustDegree, cop.engLevelEvaluation as engLevelEvaluation,"
            + "cop.cooperationSummarize as cooperationSummarize , cop.comphsAnalysis as comphsAnalysis , cop.descriptions as descriptions, cop.createTime as createTime) "
            + "from CopAnalysisDto as cop where cop.isAbolished = 0 and cop.cooperatorID in (select coop.cooperatorID from CooperatorDto as coop where coop.isAbolished = 0)";
    public static final String COPANALYSIS_COUNT_BEFORE_HQL = "select count(*) from CopAnalysisDto as cop where cop.isAbolished = 0 ";

    /** fileTemplate **/
    public static final String FILETEMPLATE_HQL = "from FileTemplateDto as cont where cont.isAbolished = 0 and cont.type = :type";
    public static final String FILETEMPLATECOUNT_HQL = "select count(*) from FileTemplateDto as cont where cont.isAbolished = 0 and cont.type = ?";
    public static final String FILETEMPLATE_SEARCH = "from FileTemplateDto as cont where cont.isAbolished = 0 and cont.type = :type "
            + "and (cont.fileTemplateName like :searchText or cont.descriptions like :searchText)";
    public static final String FILETEMPLATECOUNT_SEARCH = "select count(*) from FileTemplateDto as cont where cont.isAbolished = 0 "
            + "and cont.type = :type and (cont.fileTemplateName like :searchText or cont.descriptions like :searchText)";
    public static final String FILETEMPLATE_ADVANCESEARCH = "from FileTemplateDto as cont where cont.isAbolished = 0 "
            + "and cont.fileTemplateName like :fileTemplateName and cont.descriptions like :fileTemplateDescriptions and cont.type = :type";
    public static final String FILETEMPLATECOUNT_ADVANCESEARCH = "select count(*) from FileTemplateDto as cont where "
            + "cont.isAbolished = 0 and cont.fileTemplateName like :fileTemplateName and cont.descriptions like :fileTemplateDescriptions and cont.type = :type";
    public static final String FILETEMPLATEADDDATESTART = " and cont.fileTemplateAddDate >= :fileTemplateAddDateStart";
    public static final String FILETEMPLATEADDDATEEND = " and cont.fileTemplateAddDate <= :fileTemplateAddDateEnd";
    public static final String FILETEMPLATEEDITDATESTART = " and cont.fileTemplateEditDate >= :fileTemplateEditDateStart";
    public static final String FILETEMPLATEEDITDATEEND = " and cont.fileTemplateEditDate <= :fileTemplateEditDateEnd";
    public static final String DELETE_FILETEMPLATE_HQL = "update FileTemplateDto as cont set cont.isAbolished = 'true' "
            + "where cont.fileTemplateID in ";
    public static final String FIND_FILETEMPLATE_HQL = "select count(*) from FileTemplateDto as cont where "
            + "cont.fileTemplateID !=? and cont.fileTemplateName = ? and cont.type = ? and cont.isAbolished = 0";

    /** contract or proposal */

    public static final String UPDATE_FILETEMPLATENAME = "update ProposalOrContractDto as poc set poc.fileTemplateName= '' where poc.fileTemplateName in (select cont.fileTemplateName from FileTemplateDto as cont where cont.fileTemplateID in ";
    public static final String GET_CONTRACTORPROPOSAL_HQL = "from ProposalOrContractDto as cont where cont.isAbolished=0 "
            + "and cont.type = :type ";
    public static final String GET_PRO_CON_COUNT_HQL = COUNT
            + " from ProposalOrContractDto as cont where cont.isAbolished=0 and cont.type = ? ";
    public static final String SIMPLE_CONTRACTORPROPOSAL_SEARCH = "from ProposalOrContractDto as cont "
            + "where cont.isAbolished=0 and cont.type = :type and ( cont.proposalOrContractName like :searchText "
            + "or cont.proposalOrContractType like :searchText or cont.proposalOrContractObject like :searchText "
            + "or cont.descriptions like :searchText)";
    public static final String SUPER_CONTRACTORPROPOSAL_SEARCH = "from ProposalOrContractDto as cont "
            + "where cont.isAbolished=0 and cont.type = :type and  cont.proposalOrContractName like :proposalOrContractName "
            + "and cont.proposalOrContractType like :proposalOrContractType and cont.proposalOrContractObject "
            + "like :proposalOrContractObject";
    public static final String GET_FILETEMPLATENAME = "select new Map (cont.fileTemplateName as fileTemplateName,cont.fileTemplateID "
            + "as fileTemplateID,cont.fileTemplateValue as fileTemplateValue ) from FileTemplateDto as cont where cont.isAbolished = 0 and cont.type = ";
    public static final String COUNT_CONTRACTORPROPOSALNAME = COUNT
            + "from ProposalOrContractDto as cont where cont.isAbolished=0 and cont.type = ? "
            + "and cont.proposalOrContractID!= ? and cont.proposalOrContractName = ? ";
    public static final String DELETE_CONTRACTORPROPOSAL = "update ProposalOrContractDto as conI set conI.isAbolished = 'true' "
            + "where conI.proposalOrContractID in ";
    public static final String PORC_ADDDATEBETWEEN = " and (cont.proposalOrContractAddDate between :proposalOrContractAddDateMin "
            + "and :proposalOrContractAddDateMax )";
    public static final String PORC_ADDDATESTART = " and cont.proposalOrContractAddDate >= :proposalOrContractAddDateMin ";
    public static final String PORC_ADDDATEEND = " and cont.proposalOrContractAddDate <= :proposalOrContractAddDateMax ";
    public static final String PORC_EDITDATEBETWEEN = " and (cont.proposalOrContractEditDate between :proposalOrContractEditDateMin  "
            + "and :proposalOrContractEditDateMax )";
    public static final String PORC_EDITDATESTART = " and cont.proposalOrContractEditDate >= :proposalOrContractAddDateMin ";
    public static final String PORC_EDITDATEEND = " and cont.proposalOrContractEditDate <= :proposalOrContractEditDateMax  ";

    public static final String BATCH_REMOVE_COPANALY_HQL = "update CopAnalysisDto as c set c.isAbolished = 'true' where c.copAnalysisID in ";

    /** customer source */
    public static final String GET_ALLSOURCE_HQL1 = "select new Map(sou.sourceID as sourceID,cus.customerID as customerID,"
            + "cus.customerName as customerName,sou.sourceArea as sourceArea,"
            + "com.value as sourceTypeName,com.code as sourceType,sou.descriptions as descriptions) "
            + "from SourceInfoDto as sou,CustomerDto as cus,CodeDto as com "
            + "where sou.customerID = cus.customerID"
            + "and com.code = sou.sourceType and sou.isAbolished = 0 "
            + "and cus.isAbolished = 'false' order by sou.sourceID";

    public static final String GET_ALLSOURCE = "select new com.ustcsoft.gs.crm.webui.customer.bean.SourceInfoBean(sou,"
            + "(select com.value from CodeDto as com where com.code = sou.sourceType)as sourceTypeName,"
            + "(select cus.customerName from CustomerDto as cus where sou.customerID = cus.customerID and cus.isAbolished = 0)as customerName) "
            + " from SourceInfoDto as sou where sou.isAbolished=0 and sou.customerID in "
            + "(select cus.customerID from CustomerDto as cus where cus.isAbolished = 0 and cus.holder in (:userID) ) ";

    public static final String SOURCE_SIMPLESEARCH_HQL = " and (sou.sourceArea like :searchText "
            + " or ((select com.value from CodeDto as com where com.code = sou.sourceType) like :searchText)"
            + " or ((select cus.customerName from CustomerDto as cus where sou.customerID = cus.customerID and cus.isAbolished = 0) like :searchText))";

    public static final String SOURCE_SIMPLECOUNT_HQL = "select count(*) "
            + " from SourceInfoDto as sou where sou.isAbolished = false and (sou.sourceArea like :searchText "
            + " or ((select com.value from CodeDto as com where com.code = sou.sourceType) like :searchText)"
            + " or ((select cus.customerName from CustomerDto as cus where sou.customerID = cus.customerID and cus.isAbolished = 0) like :searchText)) "
            + "and sou.customerID in (select cus.customerID from CustomerDto as cus where cus.isAbolished = 0 and cus.holder in (:userID) )";

    public static final String SOURCECUSTOMER_SEARCH = " and (select cus.customerName from CustomerDto as cus where sou.customerID = cus.customerID and cus.isAbolished = 0) like :sourceCustomer ";

    public static final String SOURCE_SEARCH = " and sou.sourceArea like :sourceArea ";

    public static final String SOURCETYPE_SEARCH = " and sou.sourceType in (:sourceType)";

    public static final String SOURCE_ADVANCECOUNT_HQL = "select count(*) "
            + "from SourceInfoDto as sou where sou.isAbolished = 0 "
            + "and sou.customerID in (select cus.customerID from CustomerDto as cus where cus.isAbolished = 0 and cus.holder in (:userID) ) ";

    public static final String GETALL_CUSTOMER_NAMEANDID = "select new Map(cus.customerID as customerID,cus.customerName as customerName) "
            + "from CustomerDto as cus where cus.isAbolished = 0 ";

    public static final String GETCUSTOMER_NAMEANDID = "select new Map(cus.customerID as customerID,cus.customerName as customerName) "
            + "from CustomerDto as cus where cus.isAbolished = 0 and cus.customerID not in "
            + "(select sou.customerID from SourceInfoDto as sou where sou.isAbolished = 0)";

    public static final String GET_ALLSOURCECOUNT_HQL = "select count(*) from SourceInfoDto as con where con.isAbolished = 0 "
            + "and con.customerID in (select cus.customerID from CustomerDto as cus where cus.isAbolished = 0 and cus.holder in (:userID) )";

    public static final String DELETE_SOURCE = "update SourceInfoDto as source set source.isAbolished=1 where source.sourceID in ";

    /** cooperator */
    public static final String SELECT_COOPERATOR_HQL = "select new Map("
            + "coo.cooperatorID as cooperatorID,coo.cooperatorName as cooperatorName,coo.cooperatorCity as cooperatorCity,"
            + "coo.cooperatorIndustry as cooperatorIndustry,coo.cooperatorScale as cooperatorScale,coo.cooperatorType as cooperatorType,"
            + "coo.cooperatorTelephone as cooperatorTelephone,coo.cooperatorEmail as cooperatorEmail,coo.cooperatorFax as cooperatorFax,"
            + "coo.cooperatorWebsite as cooperatorWebsite,coo.cooperatorAddress as cooperatorAddress,coo.cooperatorRemark as cooperatorRemark,"
            + "coo.cooperatorDetail as cooperatorDetail,coo.isAbolished as isAbolished,"
            + "(select cd.value from CodeDto as cd where cd.code = coo.cooperatorIndustry) as industryDisplay,"
            + "(select cd.value from CodeDto as cd where cd.code = coo.cooperatorScale) as scaleDisplay,"
            + "(select cd.value from CodeDto as cd where cd.code = coo.cooperatorType) as typeDisplay,"
            + "(select count(coopd) from coo.cooperationProjectDto coopd where coopd.isAbolished = 0) as projectNumber,"
            + "(select min(coopd.realBeginTime) from coo.cooperationProjectDto coopd where coopd.isAbolished = 0) as cooperationDate,"
            + "(select count(copAna) from CopAnalysisDto copAna where coo.cooperatorID = copAna.cooperatorID) as copAnaNumber) ";

    public static final String SELECT_COOPERATOR_COUNT_HQL = "select count(coo) ";

    public static final String GET_COOPERATOR_HQL = "from CooperatorDto coo where coo.isAbolished=0";

    public static final String GET_COOPERATOR_SIMPLE_HQL = "from CooperatorDto coo where coo.isAbolished=0 "
            + "and (coo.cooperatorName like :searchText "
            + "or (select cd.value from CodeDto as cd where cd.code = coo.cooperatorIndustry) like :searchText "
            + "or (select cd.value from CodeDto as cd where cd.code = coo.cooperatorScale) like :searchText "
            + "or (select cd.value from CodeDto as cd where cd.code = coo.cooperatorType) like :searchText "
            + "or coo.cooperatorCity like :searchText "
            + "or coo.cooperatorRemark like :searchText "
            + "or coo.cooperatorDetail like :searchText "
            + "or coo.cooperatorAddress like :searchText)";

    public static final String GET_COOPERATOR_ADVANCED_HQL = "from CooperatorDto coo where coo.isAbolished = 0 ";
    public static final String COOPERATOR_NAME_HQL = " and coo.cooperatorName like :cooperatorNameSearch ";

    public static final String DEL_COOPERATOR_HQL = "update CooperatorDto coo set coo.isAbolished = 1 where coo.cooperatorID in ";
    public static final String REMOVE_COPANALY_HQL = "update CopAnalysisDto as c set c.isAbolished = 1 where c.cooperatorID in ";
    public static final String DEL_COOPPROJECT_HQL = "update CooperationProjectDto as cpd set cpd.isAbolished = 1 where cpd.cooperatorID in ";
    public static final String DEL_COOPCONTACT_HQL = "delete ContactSelectDto as cs where cs.flag=2 and cs.objectID in";

    public static final String COOPERATION_DATE_MIN_HQL = "and (select min(coopd.realBeginTime) from coo.cooperationProjectDto coopd where coopd.isAbolished =0) >= :cooperationDateMin ";
    public static final String COOPERATION_DATE_MAX_HQL = "and (select min(coopd.realBeginTime) from coo.cooperationProjectDto coopd where coopd.isAbolished =0) <= :cooperationDateMax ";
    public static final String COOPERATOR_INDUSTRY_HQL = "and coo.cooperatorIndustry in (:cooperatorIndustrySearch) ";
    public static final String COOPERATOR_SCALE_HQL = " and coo.cooperatorScale in (:cooperatorScaleSearch) ";
    public static final String COOPERATION_TIMES_MIN_HQL = " and (select count(coopd) from coo.cooperationProjectDto coopd where coopd.isAbolished =0) >= :cooperationTimesMin ";
    public static final String COOPERATION_TIMES_MAX_HQL = " and (select count(coopd) from coo.cooperationProjectDto coopd where coopd.isAbolished =0) <= :cooperationTimesMax ";
    public static final String CHECK_COOPERATOR_NAME_HQL = "select count(coo) from CooperatorDto coo  where "
            + "coo.cooperatorID != :cooperatorID and coo.cooperatorName = :cooperatorName and coo.isAbolished = 0";

    public static final String HQL = "select new Map( "
            + "(select comB.value from CodeDto as comB where cus.scale = comB.code) as scaleName, "
            + "(select comB.value from CodeDto as comB where and cus.valueEvaluate = comB.code) as valueEvaluateName, "
            + "(select comB.value from CodeDto as comB where and cus.industry = comB.code) as industryName, "
            + "(select comB.value from CodeDto as comB where and cus.fee = comB.code) as feeName, "
            + "(select comB.value from CodeDto as comB where and cus.customerType = comB.code) as customerTypeName, "
//            + "(select comB.value from CodeDto as comB where cus.customerStatement = comB.code) as customerStatementName, "
            // add 20150308 start
            + "cus.business1 as business1 ,cus.business2 as business2 ,"
            + "cus.business3 as business3 ,cus.business4 as business4 ,"
            // add 20150308 end
            + "cus.customerAddr as customerAddr ,cus.customerName as customerName,cus.customerID as customerID ,cus.customerStatement as customerStatement ,"
            + "cus.customerType as customerType,cus.descriptions as descriptions,cus.earning as earning,cus.fee as fee,cus.holder as holder,"
            + "cus.industry as industry,cus.scale as scale , cus.valueEvaluate as valueEvaluate)"
            + "from CustomerDto as cus where cus.isAbolished = 0 ";

    /** CooperationProject */
    public static final String SELECT_COOPERATIONPROJECT_HQL = "select new Map("
            + "cop.cooperationProjectID as cooperationProjectID,cop.cooperatorID as cooperatorID,cop.projectName as projectName,"
            + "cop.principalWe as principalWe,cop.principalCooperator as principalCooperator,cop.projectDetail as projectDetail,"
            + "cop.appraisal as appraisal,cop.projectType as projectType,cop.realBeginTime as realBeginTime,cop.realEndTime as realEndTime,"
            + "cop.expectedBeginTime as expectedBeginTime,cop.expectedEndTime as expectedEndTime,cop.projectScale as projectScale,"
            + "cop.cooperatorScale as cooperatorScale,cop.cooperatorPeopleNumber as cooperatorPeopleNumber,"
            + "cop.principalCooperatorPhone as principalCooperatorPhone,cop.projectDetail as projectDetail,cop.isAbolished as isAbolished,"
            + "(select comB.value from CodeDto as comB where cop.projectType = comB.code) as projectTypeDisplay) ";

    public static final String SELECT_COOPERATIONPROJECT_COUNT_HQL = "select count(cop) ";

    public static final String GET_COOPERATIONPROJECT_HQL = " from CooperationProjectDto as cop where cop.isAbolished = 0 and cop.cooperatorID = :cooperatorIDSearch";

    public static final String GET_COOPERATIONPROJECT_SIMPLE_HQL = "from CooperationProjectDto as cop where cop.isAbolished = 0 and cop.cooperatorID = :cooperatorIDSearch and "
            + "(cop.projectName like :searchText or cop.principalWe like :searchText or cop.principalCooperator like :searchText"
            + " or cop.projectDetail like  :searchText or cop.appraisal like :searchText "
            + "or (select comB.value from CodeDto as comB where cop.projectType = comB.code) like :searchText "
            + "or cop.realBeginTime like :searchText)";

    public static final String GET_COOPERATIONPROJECT_ADVANCED_HQL = "from CooperationProjectDto as cop where cop.isAbolished = 0 and cop.cooperatorID = :cooperatorIDSearch ";
    public static final String PROJECT_NAME_HQL = " and cop.projectName like :projectNameSearch ";
    public static final String PRINCIPAL_COOPERATOR_HQL = " and cop.principalCooperator like :principalCooperatorSearch  ";
    public static final String PRINCIPAL_WE_HQL = " and cop.principalWe like  :principalWeSearch  ";
    public static final String PROJECT_TYPE_HQL = " and cop.projectType in (:projectTypeSearch) ";

    public static final String DEL_PROJECT_HQL = "update CooperationProjectDto as cpd set cpd.isAbolished = 1 where cpd.cooperationProjectID in ";
    public static final String PROJECT_DATE_MIN_HQL = " and cop.realBeginTime >= :realBeginTimeMin ";
    public static final String PROJECT_DATE_MAX_HQL = " and cop.realBeginTime <= :realBeginTimeMax ";
    public static final String PROJECT_SCALE_MIN_HQL = " and cop.projectScale >= :projectScaleMin ";
    public static final String PROJECT_SCALE_MAX_HQL = " and cop.projectScale <= :projectScaleMax ";
    public static final String CHECK_PROJECT_NAME_HQL = "select count(cpd) from CooperationProjectDto as cpd  where "
            + "cpd.cooperatorID = :cooperatorID and cpd.cooperationProjectID != :cooperationProjectID and cpd.projectName = :projectName and cpd.isAbolished = 0";
    public static final String COPANALYSIS_SHOW_AFTER_HQL = "and cop.cooperatorID = :ID";
    public static final String COPANALYSIS_SHOW_AFTER_ORDER_HQL = "and cop.cooperatorID = :ID order by cop.createTime desc";

    public static final String GET_CONTACT_HQL = "select new Map("
            + "ci.contactID as contactID, "
            + "ci.contactName as contactName, "
            + "ci.customerID as customerID) "
            + "from ContactInfoDto as ci "
            + "where ci.isAbolished = 0";

    public static final String GET_CONTACT_HQL_2 = "select new Map("
            + "ci.contactID as contactID, "
            + "ci.contactName as contactName, "
            + "ci.customerID as customerID) "
            + "from ContactInfoDto as ci "
            + "where ci.customerID = :customerID and ci.isAbolished = 0";

    /** Order */
    public static final String UPDATE_EVENT_STATUS = "update SalesEventDto sf set sf.status =:status where sf.eventID =:eventID ";
    public static final String GET_MAX_STATUS = " from SalesEventFlowCodeDto as sfcode where sfcode.category= ? order by sfcode.sort DESC";
    public static final String SELECT_EVENT_BYCUSTOMER = "from SalesEventDto as sales where sales.status =? and sales.customerID = ? and sales.isAbolished = 0 and sales.eventID not in(select order.eventID from OrderDto as order where order.isAbolished = 0)";
    public static final String SELECT_COUNT_BYSTATUS = "select count(*) from OrderDto as ord where (ord.eventID = 0 or ord.eventID in (select sal.eventID from SalesEventDto as sal where sal.status in (select flowCode.code from SalesEventFlowCodeDto flowCode where flowCode.sort in (select (flowCode2.sort-1) from SalesEventFlowCodeDto flowCode2 where flowCode2.code = 3)))) and ord.id not in (select contract.orderID from ContractDto contract where contract .isAbolished = 0 and contract.orderID != :orderID)";
    public static final String SELECT_ORDER_BYSTATUS = "select new Map("
            + "(select code.value from CodeDto as code where ord.orderState = code.code) as orderStateName, "
            + "(select con.contactName from ContactInfoDto as con where con.contactID = ord.contactID) as contactName, "
            + "(select sales.status from SalesEventDto as sales where sales.eventID=ord.eventID ) as status, "
            + "(select cid.phoneNumber from ContactInfoDto as cid where cid.contactID = ord.contactID) as phoneNumber, "
            + "ord.id as id,ord.orderID as orderID ,(select cus.customerName from CustomerDto as cus where cus.customerID = ord.customerID) as customerName,ord.orderState as orderState ,"
            + "ord.contactID as contactID,ord.eventID as eventID, ord.ourRepresentative as ourRepresentative,ord.customerContact as customerContact,ord.customerID as customerID, "
            + "ord.deliveryDate as deliveryDate, ord.transactionPrice as transactionPrice,ord.remark as remark,ord.ourTelephone as ourTelephone) "
            + "from OrderDto as ord where ord.isAbolished = 0 and ord.type=1 and ord.customerID in (select cus.customerID from CustomerDto as cus where cus.customerID = ord.customerID and cus.isAbolished = 0 "
            + "and (ord.eventID = 0 or ord.eventID in (select sal.eventID from SalesEventDto as sal where sal.status in (select flowCode.code from SalesEventFlowCodeDto flowCode where flowCode.sort in (select (flowCode2.sort-1) from SalesEventFlowCodeDto flowCode2 where flowCode2.code = 3)))) "
            + "and ord.id not in (select contract.orderID from ContractDto contract where contract.isAbolished = 'false' and contract.orderID != :orderID))";
    public static final String SELECT_CONTACT_INFO = " from ContactInfoDto as con where con.contactID= ? ";
    public static final String SELECT_SALES_FLOW = " from SalesEventFlowDto sflow where sflow.eventID = ? and sflow.status = ? ";
    public static final String SELECT_SALES_TRACK = " from SalesTrackDto strack where strack.eventID = ? and strack.status = ? ";
    public static final String SELECT_ORDER_EVENT_ID = "from OrderDto as ord where ord.id = ? ";
    public static final String ORDER_EVENT_DEL_HQL = "update SalesEventDto as ort set ort.isAbolished = 1 where ort.eventID in (select eventID from OrderDto as ord where ord.orderID = ? )";
    public static final String SELECT_ORDER_PRODUCTS = "select spd.productID from SelectProductDto as spd where spd.orderID = ?";
    public static final String ORDER_DEL_PRODUCT = "delete from SelectProductDto as spd where spd.orderID = ?";
    public static final String GET_BUY_PRODUCT = "select new Map(spd.id as id,spd.productID as productID, spd.name as name,spd.price as price,spd.orderID as orderID,spd.productNumber as productNumber,spd.discount as discount) "
            + "from SelectProductDto as spd " + "where spd.isAbolished=0 and spd.orderID = ? ";
    public static final String GET_COUNT_PRODUCT = "select count(*) from SelectProductDto as pro where pro.orderID in (:userID) pro.orderID = ?";
    public static final String INTENTORDER_HQL_LIST = "select new Map("
            + "(select comB.value from CodeDto as comB where ord.orderState = comB.code) as orderStateName, "
            + "(select con.contactName from ContactInfoDto as con where con.contactID = ord.contactID) as contactName, "
            + "(select sales.status from SalesEventDto as sales where sales.eventID=ord.eventID ) as status, "
            + "(select cid.phoneNumber from ContactInfoDto as cid where cid.contactID = ord.contactID) as phoneNumber, "
            + "(select sedt.eventName from SalesEventDto as sedt where sedt.eventID=ord.eventID) as eventName ,"
            + "(select sdto.demand from SalesEventFlowDto as  sdto where sdto.eventID=ord.eventID and sdto.status = 1) as requirement ,"
            + "(select sdto.demand from SalesEventFlowDto as  sdto where sdto.eventID=ord.eventID and sdto.status = 2) as valueanalys ,"
            + "(select sdto.demand from SalesEventFlowDto as  sdto where sdto.eventID=ord.eventID and sdto.status = 3) as policymakers ,"
            + "(select sdto.demand from SalesEventFlowDto as  sdto where sdto.eventID=ord.eventID and sdto.status = 4) as cognitiveanalys ,"
            + "(select sdto.demand from SalesEventFlowDto as  sdto where sdto.eventID=ord.eventID and sdto.status = 5) as quotations ,"
            + "(select sdto.demand from SalesEventFlowDto as  sdto where sdto.eventID=ord.eventID and sdto.status = 6) as negotiations ,"
            + "ord.id as id,ord.orderID as orderID ,(select cus.customerName from CustomerDto as cus where cus.customerID = ord.customerID) as customerName,ord.orderState as orderState ,"
            + "ord.contactID as contactID,ord.eventID as eventID, ord.ourRepresentative as ourRepresentative,ord.customerContact as customerContact,ord.customerID as customerID,"
            + "ord.deliveryDate as deliveryDate, ord.transactionPrice as transactionPrice,ord.remark as remark,ord.ourTelephone as ourTelephone) "
            + "from OrderDto as ord where ord.isAbolished = 0 and ord.type =0 and ord.customerID in (select cus.customerID from CustomerDto as cus where cus.customerID = ord.customerID and cus.isAbolished = 0 "
            + "and cus.holder in (:userID))";
    public static final String ORDER_HQL_LIST = "select new Map("
            + "(select comB.value from CodeDto as comB where ord.orderState = comB.code) as orderStateName, "
            + "(select con.contactName from ContactInfoDto as con where con.contactID = ord.contactID) as contactName, "
            + "(select sales.status from SalesEventDto as sales where sales.eventID=ord.eventID ) as status, "
            + "(select cid.phoneNumber from ContactInfoDto as cid where cid.contactID = ord.contactID) as phoneNumber, "
            + "(select sedt.eventName from SalesEventDto as sedt where sedt.eventID=ord.eventID) as eventName ,"
            + "(select sdto.demand from SalesEventFlowDto as  sdto where sdto.eventID=ord.eventID and sdto.status = 1) as requirement ,"
            + "(select sdto.demand from SalesEventFlowDto as  sdto where sdto.eventID=ord.eventID and sdto.status = 2) as valueanalys ,"
            + "(select sdto.demand from SalesEventFlowDto as  sdto where sdto.eventID=ord.eventID and sdto.status = 3) as policymakers ,"
            + "(select sdto.demand from SalesEventFlowDto as  sdto where sdto.eventID=ord.eventID and sdto.status = 4) as cognitiveanalys ,"
            + "(select sdto.demand from SalesEventFlowDto as  sdto where sdto.eventID=ord.eventID and sdto.status = 5) as quotations ,"
            + "(select sdto.demand from SalesEventFlowDto as  sdto where sdto.eventID=ord.eventID and sdto.status = 6) as negotiations ,"
            + "ord.id as id,ord.orderID as orderID ,(select cus.customerName from CustomerDto as cus where cus.customerID = ord.customerID) as customerName,ord.orderState as orderState ,"
            + "ord.contactID as contactID,ord.eventID as eventID, ord.ourRepresentative as ourRepresentative,ord.customerContact as customerContact,ord.customerID as customerID, "
            + "ord.deliveryDate as deliveryDate, ord.transactionPrice as transactionPrice,ord.remark as remark,ord.ourTelephone as ourTelephone) "
            + "from OrderDto as ord where ord.isAbolished = 0 and ord.type=1 and ord.customerID in (select cus.customerID from CustomerDto as cus where cus.customerID = ord.customerID and cus.isAbolished = 0 "
            + "and cus.holder in (:userID))";
    public static final String ORDERTRACK_HQL = "select new Map("
            + "(select comB.value from CodeDto as comB where ort.afterState = comB.code) as afterState, "
            + "(select user.realName from UserInfoDto as user where user.userID = ort.editPeople) as editPeople,ort.orderID as orderID,ort.recordTime as recordTime) from OrderTrackDto as ort where ort.orderID=?";
    public static final String ORDER_SIMPLE_QUERY = "simple";
    public static final String ORDER_SUPER_QUERY = "super";
    public static final String INTENTORDER_COUNT_HQL = "select count(*) from OrderDto as ord where ord.isAbolished = 0 and ord.type=0 "
            + " and ord.customerID in (select cus.customerID from CustomerDto as cus where cus.customerID = ord.customerID and cus.isAbolished = 0 and cus.holder in (:userID) )";
    public static final String ORDER_COUNT_HQL = "select count(*) from OrderDto as ord where ord.isAbolished = 0 and ord.type=1 "
            + " and ord.customerID in (select cus.customerID from CustomerDto as cus where cus.customerID = ord.customerID and cus.isAbolished = 0 and cus.holder in (:userID) )";
    public static final String ORDER_DEL_HQL = "update OrderDto as ord set ord.isAbolished = 1 where ord.id in ";
    public static final String ORDERTRACT_DEL_HQL = "update OrderTrackDto as ort set ort.isAbolished = 1 where ort.id in ";
    public static final String INTENTORDER_TO_ORDER = "update OrderDto as ord set ord.type = 1 where ord.id in ";
    public static final String SELECT_ORDER_SUPERQUERY_HQL = "select new Map( "
            + "(select comB.value from CodeDto as comB where ord.orderState = comB.code) as orderStateName, "
            + "(select con.contactName from ContactInfoDto as con where con.contactID = ord.contactID) as contactName,"
            + "(select sales.status from SalesEventDto as sales where sales.eventID=ord.eventID ) as status, "
            + "(select cid.phoneNumber from ContactInfoDto as cid where cid.contactID = ord.contactID) as phoneNumber, "
            + " ord.ourTelephone as ourTelephone,ord.eventID as eventID,ord.customerContact as customerContact,ord.customerID as customerID, "
            + "ord.id as id,ord.orderID as orderID , "
            + "(select cus.customerName from CustomerDto as cus where cus.customerID = ord.customerID) as customerName, "
            + " ord.orderState as orderState,ord.deliveryDate as deliveryDate, "
            + "ord.transactionPrice as transactionPrice,ord.remark as remark,ord.ourRepresentative as ourRepresentative,ord.contactID as contactID) from OrderDto as ord where ord.isAbolished = 0 ";
    public static final String ORDER_SUPERQUERY_HQL = "from OrderDto as ord where ord.isAbolished = 0 and ord.customerID in (select cus.customerID from CustomerDto as cus where cus.customerID = ord.customerID and cus.isAbolished = 0 "
            + "and ord.type=1 ";
    public static final String ORDER_SUPERCOUNT_HQL = "select count(*)";
    public static final String INTENTORDER_SIMPLEQUERY_HQL = "select new Map( "
            + "(select comB.value from CodeDto as comB where ord.orderState = comB.code) as orderStateName, "
            + "(select con.contactName from ContactInfoDto as con where con.contactID = ord.contactID) as contactName, "
            + "(select cid.phoneNumber from ContactInfoDto as cid where cid.contactID = ord.contactID) as phoneNumber, "
            + "(select sales.status from SalesEventDto as sales where sales.eventID=ord.eventID ) as status, "
            + "(select sdto.demand from SalesEventFlowDto as  sdto where sdto.eventID=ord.eventID and sdto.status = 1) as requirement ,"
            + "(select sdto.demand from SalesEventFlowDto as  sdto where sdto.eventID=ord.eventID and sdto.status = 2) as valueanalys ,"
            + "(select sdto.demand from SalesEventFlowDto as  sdto where sdto.eventID=ord.eventID and sdto.status = 3) as policymakers ,"
            + "(select sdto.demand from SalesEventFlowDto as  sdto where sdto.eventID=ord.eventID and sdto.status = 4) as cognitiveanalys ,"
            + "(select sdto.demand from SalesEventFlowDto as  sdto where sdto.eventID=ord.eventID and sdto.status = 5) as quotations ,"
            + "(select sdto.demand from SalesEventFlowDto as  sdto where sdto.eventID=ord.eventID and sdto.status = 6) as negotiations ,"
            + "ord.id as id,ord.orderID as orderID ,(select cus.customerName from CustomerDto as cus where cus.customerID = ord.customerID) as customerName,ord.orderState as orderState ,"
            + "ord.contactID as contactID,ord.ourRepresentative as ourRepresentative,ord.eventID as eventID,ord.customerContact as customerContact,ord.customerID as customerID, "
            + "ord.deliveryDate as deliveryDate, ord.transactionPrice as transactionPrice,ord.remark as remark,ord.ourTelephone as ourTelephone) "
            + "from OrderDto as ord where ord.isAbolished = 0 and ord.type = 0 and ord.customerID in (select cus.customerID from CustomerDto as cus where cus.customerID = ord.customerID and cus.isAbolished = 0 "
            + "and ((select comB.value from CodeDto as comB where ord.orderState = comB.code) like:searchText "
            + "or ord.orderID like:searchText or (select cus.customerName from CustomerDto as cus where cus.customerID = ord.customerID) like:searchText or (select con.contactName from ContactInfoDto as con where con.contactID = ord.contactID) like:searchText "
            + "or ord.ourRepresentative like:searchText or ord.deliveryDate like:searchText or ord.transactionPrice like:searchText or ord.remark like:searchText))";
    public static final String ORDER_SIMPLEQUERY_HQL = "select new Map( "
            + "(select comB.value from CodeDto as comB where ord.orderState = comB.code) as orderStateName, "
            + "(select con.contactName from ContactInfoDto as con where con.contactID = ord.contactID) as contactName, "
            + "(select sales.status from SalesEventDto as sales where sales.eventID=ord.eventID ) as status, "
            + "(select cid.phoneNumber from ContactInfoDto as cid where cid.contactID = ord.contactID) as phoneNumber, "
            + "ord.id as id,ord.orderID as orderID ,(select cus.customerName from CustomerDto as cus where cus.customerID = ord.customerID) as customerName,ord.orderState as orderState ,"
            + "ord.contactID as contactID,ord.ourRepresentative as ourRepresentative, ord.customerContact as customerContact,ord.customerID as customerID, "
            + "ord.deliveryDate as deliveryDate, ord.transactionPrice as transactionPrice,ord.remark as remark,ord.ourTelephone as ourTelephone) "
            + "from OrderDto as ord where ord.isAbolished = 0 and ord.type = 1 and ord.customerID in (select cus.customerID from CustomerDto as cus where cus.customerID = ord.customerID and cus.isAbolished = 0 "
            + "and ((select comB.value from CodeDto as comB where ord.orderState = comB.code) like:searchText "
            + "or ord.orderID like:searchText or (select cus.customerName from CustomerDto as cus where cus.customerID = ord.customerID) like:searchText or (select con.contactName from ContactInfoDto as con where con.contactID = ord.contactID) like:searchText  "
            + "or ord.ourRepresentative like:searchText or ord.deliveryDate like:searchText or ord.transactionPrice like:searchText or ord.remark like:searchText))";
    public static final String INTENTORDER_SIMPLECOUNT_HQL = "select count(*) from OrderDto as ord where ord.isAbolished = 0 and ord.type=0 and ord.customerID in (select cus.customerID from CustomerDto as cus where cus.customerID = ord.customerID and cus.isAbolished = 0 "
            + "and ((select comB.value from CodeDto as comB where ord.orderState = comB.code) like:searchText "
            + "or ord.orderID like:searchText or (select cus.customerName from CustomerDto as cus where cus.customerID = ord.customerID) like:searchText or (select con.contactName from ContactInfoDto as con where con.contactID = ord.contactID) like:searchText "
            + "or ord.ourRepresentative like:searchText or ord.deliveryDate like:searchText or ord.transactionPrice like:searchText or ord.remark like:searchText))";
    public static final String ORDER_SIMPLECOUNT_HQL = "select count(*) from OrderDto as ord where ord.isAbolished = 0 and ord.type=1 and ord.customerID in (select cus.customerID from CustomerDto as cus where cus.customerID = ord.customerID and cus.isAbolished = 0 "
            + "and ((select comB.value from CodeDto as comB where ord.orderState = comB.code) like:searchText "
            + "or ord.orderID like:searchText or (select cus.customerName from CustomerDto as cus where cus.customerID = ord.customerID) like:searchText or (select con.contactName from ContactInfoDto as con where con.contactID = ord.contactID)  like:searchText "
            + "or ord.ourRepresentative like:searchText or ord.deliveryDate like:searchText or ord.transactionPrice like:searchText or ord.remark like:searchText))";
    public static final String USER_GET_ALL_HQL = "select new Map ( "
            + "(select Group.groupName from GroupManagerDto as Group where Group.groupID = user.groupID) as groupIDB, "
            + "(select Department.departmentName from DepartmentDto as Department where user.departmentID = Department.departmentID) as departmentIDB, "
            + "(select ProjectTeam.projectTeamName from ProjectTeamDto as ProjectTeam where  ProjectTeam.projectTeamID = user.projectTeamID ) as projectTeamIDB, "
            + "(select comB.value from CodeDto as comB where user.education = comB.code) as educationB, "
            + "(select comB.value from CodeDto as comB where user.jobTitle = comB.code) as jobTitleB, "
            + "user.userID as userID, user.jobID as jobID , user.password as password , user.userName as userName , user.realName as realName , user.company as company ,"
            + "user.jobTitle as jobTitle, user.education as education, user.groupID as groupID, user.departmentID as departmentID, user.projectTeamID as projectTeamID, "
            + "user.job as job , user.email as email , user.mobile as mobile , user.officePhone as officePhone , user.entryTime as entryTime , user.descriptions as descriptions)"
            + "from UserInfoDto as user where user.isAbolished = 0 ";
    public static final String ORDER_PRODUCTCOUNT_HQL = "select count(*) from SelectProductDto as pro"
            + "where pro.orderID = ";
    public static final String USERINFO_COUNT_HQL = "select count(*) from UserInfoDto as user where user.isAbolished = :isAbolished";
    public static final String ORDERTRACT_ORDERSTATE = "select ord.orderState as orderState from OrderDto as ord where ord.orderID = :orderID";
    public static final String ORDER_ID = "orderID";
    public static final String ORDER_ORDERID_SEARCH = " and ord.orderID like ?";
    public static final String ORDER_CUSTOMERNAME_SEARCH = " and ord.customerID in (select cus.customerID from CustomerDto as cus where cus.customerName like ?) ";
    public static final String ORDER_CONTACTNAME_SEARCH = " and (select con.contactName from ContactInfoDto as con where con.contactID = ord.contactID) like :customerConnectValue ";
    public static final String ORDER_OURREPRESENTATIVE_SEARCH = " and ord.ourRepresentative like ?";
    public static final String ORDER_TRANSACTION_SEARCH = " and ord.transactionPrice = ? ";
    public static final String ORDER_STARTDATEDIG = " and ord.startDate >= :beginDateSearchMinValue";
    public static final String ORDER_STARTDATESMALL = " and ord.startDate <=:beginDateSearchMaxValue";
    public static final String ORDER_ENDDATEDIG = " and ord.endDate >= :endDateSearchMinValue";
    public static final String ORDER_ENDDATESMALL = " and ord.endDate <= :endDateSearchMaxValue";
    public static final String OEDER_ORDERIDREPEAT = " select ord.orderID as orderID from OrderDto as ord where ord.orderID = '";
    public static final String SELECT_REPEAT_ID = " select selpro.orderID as orderID from SelectProductInfo where selpro.orderID = '";
    public static final String USERID = "userID";
    public static final String GETALL_CONTRACT = "select new com.ustcsoft.gs.crm.webui.customer.bean.ContractBean(contract.orderID,contract.contractID ,contract.contractName , "
            + "contract.payEndTime ,contract.payType , contract.contractValue , contract.isAbolished , "
            + "contract.fileTemplateName,(select order.orderID from OrderDto order where order.id = contract.orderID)) from ContractDto as contract";
    public static final String CONTRACT_ISABOLISHED = " where contract.isAbolished = false";
    public static final String GETALL_CONTRACT_COUNT = "select count(*) from ContractDto as contract";
    public static final String DELETE_CONTRACT = "update ContractDto as con set con.isAbolished = 'true' "
            + "where con.contractID in ";
    public static final String DELETE_SALESEVENT_HQL = "update SalesEventDto salesE set salesE.isAbolished = 1 where salesE.eventID =?";
    public static final String QUERY_SALESTRACK_HQL = "from SalesTrackDto as stD where stD.eventID =?";
    public static final String UPDATE_SALESTRACK_HQL = "update SalesTrackDto as stD set stD.isAbolished =false where stD.salesTrackNo =?";
    public static final String QUERY_SALESEVENTFLOW_HQL = "from SalesEventFlowDto as sefD where sefD.eventID=:eventID and sefD.status=:status";
    public static final String UPDATE_SALESEVENTFLOW_HQL = "update SalesEventFlowDto as sefD set sefD.demand=? where sefD.id=?";
    public static final String GET_NEXT_SORT_TO_CHANGE = "select dto.sort from SalesEventFlowCodeDto as dto where dto.code =:code";
    public static final String EVENT_FLOW_CODE = "code";
    public static final String GET_NEXT_CODE_TO_CHANGE = "select dto.code from SalesEventFlowCodeDto as dto where dto.sort =:sort";
    public static final String EVENT_FLOW_SORT = "sort";
    public static final String SELECT_ALL_SALESEVENTFLOW_INFO = "from SalesEventFlowDto dto where dto.eventID =:eventID";
    public static final String EVENT_ID = "eventID";
    public static final String SELECT_SALESTRACK_BY_EVENTID = "from SalesTrackDto dto where dto.eventID =:eventID";
    // contract
    public static final String SEARCHTEXTERROR = "searchTextError";
    public static final String SEARCHTEXTINVAILD = "searchText.invalid";
    public static final String CONTRACTNAMEERROR = "contractNameError";
    public static final String CONTRACTNAMEINVALID = "contractName.invalid";
    public static final String FILETEMPLATENAMEERROR = "fileTemplateNameError";
    public static final String FILETEMPLATENAMEINVALID = "fileTemplateName.invalid";

    // leader advice
    public static final String GET_LEADER_ADVICE_PREFIX_HQL = "select new Map("
            + " ld.adviceID as adviceID,"
            + " ld.customerID as customerID,"
            + " (select cd.customerName from CustomerDto as cd where ld.customerID = cd.customerID and cd.isAbolished = 0) as customerName,"
            + " ld.userID as userID,"
            + " (select uiDto.realName from UserInfoDto as uiDto where ld.userID = uiDto.userID and uiDto.isAbolished = 0) as userName,"
            + " ld.adviceContent as adviceContent,"
            + " ld.hasRead as hasRead,"
            + " ld.createTime as createTime,"
            + " ld.updateTime as updateTime"
            + " )"
            + " from LeaderAdviceDto as ld";
    public static final String GET_LEADER_ADVICE_LIST_HQL = GET_LEADER_ADVICE_PREFIX_HQL
            + " where ld.isAbolished = 0"
            + " and ld.customerID = :customerID";
    public static final String GET_LEADER_ADVICE_TOTAL_HQL = "select count(*) from LeaderAdviceDto as ld"
            + " where ld.isAbolished = 0"
            + " and ld.customerID = :customerID";
    public static final String LEADER_ADVICE_DEL_HQL = "update LeaderAdviceDto as ld set ld.updateTime=CONVERT(varchar(19), getdate(), 120),ld.isAbolished = 1 where ld.adviceID in ";
    public static final String LEADER_ADVICE_QUERY_HQL = GET_LEADER_ADVICE_PREFIX_HQL 
            + " where ld.isAbolished = 0"
            + " and ld.customerID = :customerID"
            + " and ("
            + " (select user.realName from UserInfoDto as user where user.userID = ld.userID) like:searchText"
            + " or ld.adviceContent like:searchText"
            + " )";
    public static final String LEADER_ADVICE_QUERY_TOTAL_HQL = "select count(*) from LeaderAdviceDto as ld"
            + " where ld.isAbolished = 0"
            + " and ld.customerID = :customerID"
            + " and ("
            + " (select user.realName from UserInfoDto as user where user.userID = ld.userID) like:searchText"
            + " or ld.adviceContent like:searchText"
            + " )";
}
