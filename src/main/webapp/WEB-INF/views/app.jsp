<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- <meta http-equiv="x-ua-compatible" content="ie=7" /> -->
<title>USI_CRM</title>
<style type="text/css">
</style>
<link rel="Shortcut Icon" href="html/img/favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="extjs/resources/css/ext-all.css" />
<link rel='stylesheet' type="text/css" href="html/css/crm.css"/>
<link rel='stylesheet' type="text/css" href="extjs/crm/crm-msg/crm-msg.css"/>
<link rel='stylesheet' type="text/css" href="extjs/crm/funnel-grid/funnel-grid.css"/>
<script type="text/javascript" src="extjs/bootstrap.js"></script>
<script type="text/javascript" src="extjs/crm/crm-msg/crm-msg.js"></script>
<script type="text/javascript" src="extjs/crm/ext-expand.js"></script>
<script type="text/javascript" src="extjs/crm/utils.js"></script>
<script type="text/javascript">
	var REAL_NAME = '<s:property value="#session.realName" />';
	var USER_ID = '<s:property value="#session.userID" />';
	var GROUP_ID = '<s:property value="#session.groupID" />';
	var DEPARTMENT_ID = '<s:property value="#session.departmentID" />';
	var PROJECT_TEAM_ID = '<s:property value="#session.projectTeamID" />';
</script>
<script type="text/javascript" src="extjs/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="app.js"></script>
</head>
<body>
<div style="display:none;">
    <!-- <form id="exportCustomerForm" action="/crm/exportCustomer.action">
        <input type="hidden" id="customerID" name="customerID" />
        <input type="hidden" id="customerIDList" name="customerIDList" />
    </form> -->
    <s:form id="exportCustomerForm" action="exportCustomer.action">
        <input type="hidden" id="customerID" name="customerID" />
        <input type="hidden" id="customerIDList" name="customerIDList" />
    </s:form>
</div>
</body>
</html>