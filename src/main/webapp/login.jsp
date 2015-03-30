<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="Shortcut Icon" href="html/img/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- <meta http-equiv="x-ua-compatible" content="ie=7" /> -->
<title>USI_CRM</title>
<link rel="stylesheet" type="text/css"
	href="extjs/resources/css/ext-all.css" />
<script type="text/javascript" src="jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="extjs/bootstrap.js"></script>
<script type="text/javascript" src="login.js"></script>
<style type="text/css">
body {
	
}

#titleSize {
	font-size: 2.5em;
}

#loginDiv {
	margin-left: auto;
	margin-right: auto;
	margin-top: 12%;
	height: 340px;
	width: 483px;
	background-image: url(html/img/loginBg.png);
}

.inputNormal {
	background-image: url(html/img/id.png);
}

.passwordNormal {
	background-image: url(html/img/password.png);
}

.inputFocus {
	background-image: url(html/img/id_type.png);
}

.passwordFocus {
	background-image: url(html/img/password_type.png);
}

#loginBtn {
	height: 35px;
	width: 140px;
	float: left;
	line-height: 35px;
	text-align: center;
}

#clearBtn {
	height: 35px;
	width: 140px;
	margin-left: 150px;
	line-height: 35px;
	text-align: center;
}

.loginBtnNormal {
	background-image: url(html/img/enter.png);
}

.clearBtnNormal {
	background-image: url(html/img/clear.png);
}

.loginBtnEnter {
	background-image: url(html/img/enter_hover.png);
}

.clearBtnEnter {
	background-image: url(html/img/clear_hover.png);
}

form input {
	height: 36px;
	width: 264px;
	padding-left: 38px;
	border-left: 0px;
	border-right: 0px;
	border-top: 0px;
	border-bottom: 0px;
	margin-top: 1px;
}

font {
	font-weight: bold;
	size: 4.5em;
	color: #fff;
}
</style>
</head>
<body>
	<div id="loginDiv">
		<form action="" id="loginForm">
			<div style="padding-top: 150px; padding-left: 90px">
				<div id="userNameDiv">
					<input type="text" class="inputNormal" placeholder="UserName" maxlength="20"
						name="userName" id="userName" onfocus="changeInput()" onBlur="changeInputNormal()" />
				</div>
				<div id="passWord" style="margin-top: 10px">
					<input type="password" class="passwordNormal" maxlength="20"
						placeholder="PassWord" name="password" id="password" onfocus="changePwd()"
						onBlur="changePwdNormal()" />
				</div>
			</div>
			<div style="margin-top: 20px; padding-left: 95px">
				<div id="loginBtn" class="loginBtnNormal">
					<font>登&nbsp;&nbsp;录</font>
				</div>
				<div id="clearBtn" class="clearBtnNormal" onclick="clearValue()">
					<font>清&nbsp;&nbsp;空</font>
				</div>
			</div>
		</form>
	</div>
</body>
</html>