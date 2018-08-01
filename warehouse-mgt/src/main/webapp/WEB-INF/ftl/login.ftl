<#if agent?? && agent == "M">
<#include "m/login.ftl"/>
<#else>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>VVA Management System</title>
<link type="text/css" rel="stylesheet" href="${DOMAIN}res/css/common/newlogin.css?v=${VERSION!''}7" />
<script src="${BASEPATH}plugin/dwz-ria/js/jquery-2.1.4.js" type="text/javascript"></script>
</head>
<script type="text/javascript">
$(function(){
	if(location.href.indexOf("login") == -1){
		window.location.href = "${DOMAIN}login";
	}
})
</script>
<script type="text/javascript">
 var adminuser_login_login_error="Please input your username or password!";
 var adminuser_login_incorrect="ERROR Incorrect username or password";
function loginformsave() {
    var um = document.getElementById("logintitle");
    if (document.loginform.loginname.value == "") {
        um.innerHTML = adminuser_login_incorrect;
        document.loginform.loginname.focus();
        return false
    }
    if (document.loginform.loginname.value.match(/^[a-zA-Z]{1}[a-zA-Z0-9]{4,19}$/ig) == null) {
        um.innerHTML = adminuser_login_incorrect;
        document.loginform.loginname.focus();
        return false
    }
    if (document.loginform.password.length < 6) {
        um.innerHTML = adminuser_login_incorrect;
        document.loginform.password.focus();
        return false
    }
    if (document.loginform.password.value == "") {
        um.innerHTML = adminuser_login_incorrect;
        document.loginform.password.focus();
        return false
    }

}
</script>
<body>
<div class="login-bg">
    <div class="login-form">
        <header class="login1-header">
            <h1>VVA Management System</h1>
        </header>
    	<form action="${DOMAIN}loginSubmit" method="post" name="loginform" id="loginform" onSubmit="return loginformsave()">
            <div class="login-body login1-body">
                 <div class="login-input-warpper"><input name="systemType" type="radio" value="1" checked="checked"/>Warehouse management system

			    <input name="systemType" type="radio" value="2"/> Comment management system
                </div>
		
		<div class="login-input-warpper">
                    <input type="text" class="login-input easyui-validatebox" data-options="tipPosition:'bottom',required:true,validType:'length[2,20]'" name="loginname" placeholder="username" tabindex="1">
                </div>
                <div class="login-input-warpper">
                    <input type="password" class="login-input easyui-validatebox" data-options="tipPosition:'bottom',required:true" name="password" placeholder="password" tabindex="1">
                </div>

            </div>
            <div class="login-footer">
                <button type="submit" class="pg-btn-submit dinline-block" tabindex="3" act="login_btn">Login</button>

		<span id="logintitle" class="login-tips"> </span>
            </div>
        </form>
    </div>
</div>
</body>
<div class="login-footer">Copyright &copy;2018 VVA  &nbsp;<a target="_blank" href="http://www.miitbeian.gov.cn">粤ICP备18085048号-1</a></div>

</body>
</html>
</#if>