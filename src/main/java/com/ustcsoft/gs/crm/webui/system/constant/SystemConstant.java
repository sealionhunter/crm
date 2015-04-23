package com.ustcsoft.gs.crm.webui.system.constant;

import com.ustcsoft.gs.crm.webui.common.constant.CRMConstant;

public class SystemConstant extends CRMConstant {
    /** LOGIN */
    public static final String LOGIN_HQL = "from UserInfoDto where convert(varbinary, userName) = convert(varbinary, ?) and convert(varbinary, password) = convert(varbinary, ?) and isAbolished = false";
    public static final String GET_PWD_ID_HQL = "from UserInfoDto where userID = ? and isAbolished = false";
    public static final String UPDATE_PWD_ID_HQL = "update UserInfoDto set password = ? where userID = ? and isAbolished = false";

    /** Authorization */
    public static final String TREE_FLAG_MENU = "menu";
    public static final String TREE_FLAG_OPERATION = "operation";
    public static final String GET_GROUPID_HQL = "select uid.groupID from UserInfoDto as uid where uid.userID = ?";
    public static final String GET_USER_DOWN_OF_MINISTER = "select new Map(user.userID as memberID,user.userName as memberName,user.realName as memberRealName,user.groupID as memberGroupID) from UserInfoDto as user "
            + "where user.isAbolished = 0 and user.departmentID = (select uid.departmentID from UserInfoDto as uid where uid.userID = ?) and user.groupID > ? order by user.groupID asc";
    public static final String GET_USER_DOWN_OF_CHARGEHAND = "select new Map(user.userID as memberID,user.userName as memberName,user.realName as memberRealName,user.groupID as memberGroupID) from UserInfoDto as user "
            + "where user.isAbolished = 0 and user.departmentID = (select uid.departmentID from UserInfoDto as uid where uid.userID = ?) and user.projectTeamID = (select uid.projectTeamID from UserInfoDto as uid where uid.userID = ?) and user.groupID > ?";
    public static final String GET_GP_ACTIONIDS_HQL = "select group.actionID from ActionGroupDto as group where group.groupID = ? ";
    public static final String GET_USER_ID_HQL = "select user.userID from UserInfoDto as user where user.groupID = ?";
    public static final String DEL_GP_ACTIONIDS_HQL = "from ActionGroupDto as group where group.actionID in(:actionIDs) and group.groupID >= :groupID";
    public static final String GET_GROUP_TREE_HQL = "select td from TreeDto as td,ActionGroupDto as agd where td.id=agd.actionID and td.flag= ? and agd.groupID = ?";
    public static final String EDIT_MENU_TEXT_HQL = "update TreeDto as td set td.text = ? where td.id =?";
    public static final String GET_MENU_TREE_HQL = "select td from TreeDto as td where td.flag= ? and isLeaf = true";
    public static final String GET_GROUP_MANAGER_HQL = "select new Map(gmd.groupID as memberID,gmd.groupName as memberName) from GroupManagerDto as gmd where gmd.groupID > ?";
    public static final String GET_GROUP_ID = "select uid.groupID from UserInfoDto as uid where uid.userID = ? ";
    public static final String GET_GROUP_AUTHORIZATION = "select td.id from TreeDto as td,ActionGroupDto as agd where td.id=agd.actionID  and agd.groupID = ?";
    public static final String GET_OPERA_AUTHORIZATION_HQL = "select agd.actionID from ActionGroupDto as agd ,TreeDto as tree where agd.groupID = ? and tree.id = agd.actionID and tree.fatherID = ?";
    public static final String GET_GROUP_ACCESS_AUTHORIZATION_HQL = "select td.id from TreeDto as td,ActionGroupDto as agd where td.id=agd.actionID and td.flag='menu' and agd.groupID = ?";
    public static final String GET_GROUP_OPERATION_AUTHORIZATION_HQL = "select td.id from TreeDto as td,ActionGroupDto as agd where td.id=agd.actionID and td.flag='operation' and agd.groupID = ?";
    public static final String GET_TREE_FATHER_NODES_HQL = "from TreeDto as td where td.leaf=0";

    public static final String USER_GET_ALL_HQL = "select distinct new Map ( "
            + "(select Group.groupName from GroupManagerDto as Group where Group.groupID = user.groupID) as groupIDB, "
            + "(select Department.departmentName from DepartmentDto as Department where user.departmentID = Department.departmentID) as departmentIDB, "
            + "(select ProjectTeam.projectTeamName from ProjectTeamDto as ProjectTeam where  ProjectTeam.projectTeamID = user.projectTeamID ) as projectTeamIDB, "
            + "(select cDto.value from CodeDto as cDto where user.education = cDto.code) as educationB, "
            + "(select cDto.value from CodeDto as cDto where user.jobTitle = cDto.code) as jobTitleB, "
            + "user.userID as userID, user.jobID as jobID , user.password as password , user.userName as userName , user.realName as realName , user.company as company ,"
            + "user.jobTitle as jobTitle, user.contactInterval as contactInterval, user.education as education, user.groupID as groupID, user.departmentID as departmentID, user.projectTeamID as projectTeamID, "
            + "user.job as job , user.email as email , user.mobile as mobile , user.officePhone as officePhone , user.entryTime as entryTime , user.descriptions as descriptions)"
            + "from UserInfoDto as user where user.isAbolished = 0 and user.groupID > 1 ";
    public static final String USER_MIDQUERY1_HQL = " and user.departmentID =:departmentID ";
    public static final String USER_MIDQUERY2_HQL = " and user.projectTeamID =:projectTeamID ";
    public static final String USERINFO_COUNT_HQL = "select count(*) from UserInfoDto as user where user.isAbolished = 0";
    public static final String USER_USERNAME_COUNT_HQL = USERINFO_COUNT_HQL
            + " and user.userName =:userName ";
    public static final String USER_JOBID_COUNT_HQL = USERINFO_COUNT_HQL
            + " and user.jobID =:jobID ";
    public static final String FIND_USERNAME_HQL = " select user.userName from UserInfoDto as user where user.userID =:userID and user.isAbolished = 0 ";
    public static final String FIND_JOBID_HQL = " select user.jobID from UserInfoDto as user where user.userID =:userID and user.isAbolished = 0 ";
    public static final String USERINFO_COUNT1_HQL = "select count(distinct user.userName) from UserInfoDto as user where user.isAbolished = 0 and user.groupID > 1 ";
    public static final String USERINFO_END_HQL = " and user.realName like:realName ";
    public static final String USER_DELETE_HQL = "update UserInfoDto as user set user.isAbolished = 1 where user.userID in ";
    public static final String SELECT_GROUPID = " from GroupManagerDto where groupID>1 and groupID != :groupID";
    public static final String SELECT_USER_DEPARTMENT = "select new Map(dmDto.departmentID as departmentID,dmDto.departmentName as departmentName) from DepartmentDto as dmDto";
    public static final String SIMPLEQUERY_HQL = "and ( user.jobID like:searchText "
            + " or user.realName like:searchText "
            + " or user.password like:searchText "
            + " or user.job like:searchText "
            + " or user.mobile like:searchText "
            + " or user.email like:searchText "
            + " or user.userName like:searchText "
            + " or user.company like:searchText "
            + " or user.officePhone like:searchText "
            + " or user.entryTime like:searchText "
            + " or user.descriptions like:searchText "
            + "or (select comB.value from CodeDto as comB where comB.code like '00030002%' and user.education = comB.code) like:searchText "
            + "or (select comB.value from CodeDto as comB where comB.code like '00100002%' and user.jobTitle = comB.code) like:searchText "
            + "or (select Department.departmentName from DepartmentDto as Department where user.departmentID = Department.departmentID) like:searchText "
            + "or (select ProjectTeam.projectTeamName from ProjectTeamDto as ProjectTeam where  ProjectTeam.projectTeamID = user.projectTeamID ) like:searchText "
            + "or (select Group.groupName from GroupManagerDto as Group where Group.groupID = user.groupID) like:searchText) ";
    public static final String USERNAME_VALIDATE = "^[a-zA-Z0-9_]{1,20}$";
    public static final String JOBID_VALIDATE = "^[0-9]{1,20}$";
    public static final String SAVED_IDS_REGEX = "^\\d+(, \\d+)*$";
    /** check delete user */
    public static final String QUERY_IN_DEPARTMENT = "select departmentManager from DepartmentDto where departmentManager in (:userID)";
    public static final String QUERY_IN_TEAM = "select projectTeamLeaderID from ProjectTeamDto where projectTeamLeaderID in (:userID)";
    public static final String QUERY_IN_CUSTOMER = "select holder from CustomerDto where holder in (:userID) and isAbolished = 0";
    public static final String QUERY_IN_SALES = "select submitterID from SalesEventDto where submitterID in (:userID)";
    public static final String QUERY_IN_CONTACTTRACK = "select weContact from ContactTrackInfoDto where weContact in (:userID) and isAbolished = 0";

    /** group */
    public static final String SELECT_GROUP_HQL = " from GroupManagerDto where groupID>1 and groupID!=:groupID";
    public static final String GROUPMEMBERS_LIST_HQL = "from UserInfoDto as udto where udto.groupID=:groupID";
    public static final String GROUPMEMBERS_COUNT_HQL = "select count(*) from UserInfoDto as udto where udto.groupID=:groupID";
    public static final String GROUPID = "groupID";
    public static final String GROUP_DELETE_HQL = "delete from GroupManagerDto where groupID=?";
    public static final String GROUPACTION_DELETE_HQL = "delete from ActionGroupDto where groupID=?";
    public static final String SET_USERGROUP_HQL = "update UserInfoDto set groupID=-1 where groupID=?";
    public static final String GET_MAXGROUPID_HQL = "select max(groupID) from GroupManagerDto";
    public static final String GROUP_GROUPNAME_COUNT_HQL = "select count(*) from GroupManagerDto as gmDto where gmDto.groupName=:groupName";
    public static final String FIND_GROUPNAME_HQL = "select group.groupName from GroupManagerDto as group where group.groupID =:groupID";
    public static final String ADD_GROUP_MEMBERS = "from UserInfoDto as uid where uid.userID in(:userID)";
    public static final String REMOVE_GROUP_MEMBERS = "from UserInfoDto as uid where uid.groupID=:groupID and uid.userID in(:userID)";

    /** department */
    public static final String DEPARTMENT_GET_ALL_HQL = "select new Map ( "
            + " ( select Department.departmentName from DepartmentDto as Department where Department.departmentID = dmDto.fatherDepartmentID) as departmentIDB, "
            + " ( select userDto.realName from UserInfoDto as userDto where userDto.userID = dmDto.departmentManager) as managerName, "
            + " dmDto.departmentID as departmentID,dmDto.serialNumber as serialNumber, dmDto.departmentName as departmentName,dmDto.fatherDepartmentID as fatherDepartmentID, "
            + " dmDto.departmentManager as departmentManager, dmDto.departmentPhone as departmentPhone, dmDto.createTime as createTime,dmDto.departmentDescription as departmentDescription)"
            + " from DepartmentDto as dmDto where dmDto.departmentID>1 ";

    public static final String DEPARTMENT_COUNT_HQL = "select count(*) from DepartmentDto as dmDto where dmDto.departmentID>:departmentID ";

    public static final String QUERY_COUNT_HQL = "select count(*) from DepartmentDto as dmDto where dmDto.departmentID>1 ";

    public static final String CHECK_DELETE_HQL = "select dmDto.departmentName from DepartmentDto as dmDto where dmDto.fatherDepartmentID in";

    public static final String DEPARTMENTID = "departmentID";

    public static final String CHILDDEPARTMENTID = "childDepartmentID";

    public static final String IN_DEPARTMENT_LIST_HQL = " and udto.departmentID in (:childDepartmentID)";

    public static final String SIMPLE_QUERY_HQL = "and (dmDto.departmentName like:searchText "
            + "or (select userDto.realName from UserInfoDto as userDto where userDto.userID = dmDto.departmentManager) like:searchText "
            + "or dmDto.departmentPhone like:searchText "
            + "or dmDto.createTime like:searchText "
            + "or dmDto.departmentDescription like:searchText "
            + "or (select Department.departmentName from DepartmentDto as Department where Department.departmentID = dmDto.fatherDepartmentID) like:searchText)";

    public static final String QUERY_FATHERDEPARTNAME_HQL = "and dmDto.fatherDepartmentID = :fatherDepartmentID ";

    public static final String QUERY_MANEGER_HQL = "and (select userDto.realName from UserInfoDto as userDto where userDto.userID = dmDto.departmentManager) like:departmentManager ";

    public static final String QUERY_DEPARTMENTNAME_HQL = "and dmDto.departmentName like:departmentName ";

    public static final String DEPARTMENT_DELETE_HQL = "delete from DepartmentDto as dmDto where dmDto.departmentID in";

    public static final String UPDATE_USER_AFTERDELETE_HQL = "update UserInfoDto set departmentID=1 where departmentID in";

    public static final String UPDATE_TEAM_AFTERDELETE_HQL = "update ProjectTeamDto set departmentID=1 where departmentID in";

    public static final String DEPARTMENTNAME_COUNT_HQL = "select count(*) from DepartmentDto as dmDto where dmDto.departmentName=:departmentName";

    public static final String FIND_DEPARTMENTNAME_HQL = "select dmDto.departmentName from DepartmentDto as dmDto where dmDto.departmentID=:departmentID";

    public static final String GET_DEPTH_HQL = "select dmDto.depth from DepartmentDto as dmDto where dmDto.departmentID=:departmentID";

    public static final String GET_FATHERDEPARTMENT_HQL = "select new Map(dmDto.departmentID as departmentID,dmDto.departmentName as departmentName) from DepartmentDto as dmDto where dmDto.depth<8 and dmDto.departmentID in(:departmentIDs) ";

    public static final String GET_DEPARTMENTMANAGER_HQL = "from UserInfoDto as user where user.departmentID=:departmentID and user.groupID>1 and user.isAbolished = 0 and "
            + "user.userID!=(select Department.departmentManager from DepartmentDto as Department where Department.departmentID=:departmentID)";

    public static final String UPDATE_USERDEPARTMENT_HQL = "update UserInfoDto set departmentID=? where userID=?";

    /** project team */
    public static final String REMOVE_PROJECTTEAM_MEMBERS = "from UserInfoDto as uid where uid.projectTeamID=:projectTeamID and uid.userID in(:userID)";
    public static final String ADD_PROJECTTEAM_MEMBERS = "from UserInfoDto as uid where uid.userID in(:userID)";
    public static final String GET_PROJECTTEAM_MEMBERS = "from UserInfoDto as uid where uid.groupID>1 and uid.isAbolished=0 and uid.projectTeamID=:projectTeamID";
    public static final String GET_OTHER_PROJECTTEAM_MEMBERS = "from UserInfoDto as uid where uid.groupID>1 and uid.isAbolished=0 and uid.projectTeamID!=:projectTeamID";
    public static final String PROJECTTEAMMEMBERS_COUNT_HQL = "select count(*) from UserInfoDto as uid where uid.groupID>1 and uid.isAbolished=0 and uid.projectTeamID=:projectTeamID";
    public static final String OTHER_PROJECTTEAMMEMBERS_COUNT_HQL = "select count(*) from UserInfoDto as uid where uid.groupID>1 and uid.isAbolished=0 and uid.projectTeamID!=:projectTeamID";
    public static final String GET_PROJECTTEAM = "select new Map ( "
            + "ptd.projectTeamID as projectTeamID, "
            + "ptd.projectTeamName as projectTeamName, "
            + "ptd.departmentID as departmentID, "
            + "(select dd.departmentName from DepartmentDto as dd where dd.departmentID=ptd.departmentID) as departmentName, "
            + "ptd.projectTeamLeaderID as projectTeamLeaderID, "
            + "(select uid.realName from UserInfoDto as uid where uid.userID=ptd.projectTeamLeaderID) as projectTeamLeaderName, "
            + "ptd.projectTeamStatusCode as projectTeamStatusCode, "
            + "(select cd.value from CodeDto as cd where cd.code=ptd.projectTeamStatusCode) as projectTeamStatusValue, "
            + "ptd.createTime as createTime, "
            + "ptd.endTime as endTime, "
            + "ptd.description as description) from ProjectTeamDto as ptd where ptd.projectTeamID>-1";
    public static final String PROJECTTEAM_COUNT = "select count(*) from ProjectTeamDto as ptd where ptd.projectTeamID>-1";

    /** organization structure */
    public static final String QUERY_ALL_ORGANIZATION = "from OrganizationalStructureDto as dto order by dto.fatherDepartmentID";

    public static final String QUERY_MAX_DEPTH = "select max(depth) from OrganizationalStructureDto as dto";
}
