<#if agent?? && agent == "M">
<#include "m/login.ftl"/>
<#else>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>
<link type="text/css" rel="stylesheet" href="${DOMAIN}res/css/common/login.css?v=${VERSION!''}" />
<script src="${BASEPATH}plugin/dwz-ria/js/jquery-1.7.2.js" type="text/javascript"></script>
</head>
<script type="text/javascript">
$(function(){
	if(location.href.indexOf("login") == -1){
		window.location.href = "${DOMAIN}login";
	}
})
</script>
<body>
<div class="login_div">
    <div class="loginBox" style=" font-size:20px">
    	<form action="${DOMAIN}loginSubmit" method="post" id="sysform">
			<table width="320" border="0" cellpadding="5" cellspacing="5">
				<tr>
		            <td colspan="2">
		                <select name="systemType" style="min-width:120px;" class="select_system">
							<option value="1">仓库系统</option>	
							<option value="2">评价系统</option>					
						</select>
		            </td>
	          </tr>
	          <tr>
	            <td colspan="2">
	              <input type="text" name="loginname" id="loginname" class="inputUser"/>
	            </td>
	          </tr>
	          <tr>
	            <td colspan="2">
	              <input type="password" name="password" id="password" class="inputPass"/>
	              ${loginerror!''}
	            </td>
	          </tr>
	          <tr>
	            <td><a>忘记密码？</a></td>
	            <td align="right"><input name="" type="submit" value="登 录" class="login_btn"/></td>
	          </tr>
	        </table>
		</form>

  </div>
</div>

<div class="login-footer">Copyright &copy;2018 VVA  &nbsp;<a target="_blank" href="http://www.miitbeian.gov.cn">粤ICP备18085048号-1</a></div>

</body>
</html>
</#if>