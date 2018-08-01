<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<meta name="apple-mobile-web-app-capable" content="yes">
<title>登录</title>
<link type="text/css" rel="stylesheet" href="${DOMAIN}res/css/m/login.css?v=${VERSION!''}" />
<script src="${BASEPATH}plugin/dwz-ria/js/jquery-2.1.4.js" type="text/javascript"></script>
</head>
<script type="text/javascript">
$(function(){
	if(location.href.indexOf("login") == -1){
		window.location.href = "${DOMAIN}/login";
	}
})
</script>
<body>
<div class="login_div">
    <div class="loginBox" style=" font-size:20px">
    	<form action="${DOMAIN}/loginSubmit" method="post" id="sysform">
			<table width="320" border="0" cellpadding="5" cellspacing="5">
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
	            <td colspan="2" align="right"><input name="" type="submit" value="登 录" class="login_btn"/></td>
	          </tr>
	        </table>
		</form>

  </div>
</div>
</body>
</html>
