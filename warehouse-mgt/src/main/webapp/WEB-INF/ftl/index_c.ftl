<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>评价管理系统</title>

<!--[if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lte IE 9]>
<script src="js/speedup.js" type="text/javascript"></script>
<![endif]-->
<!--
<script src="bin/dwz.min.js" type="text/javascript"></script>
-->
<script>
var domain = '${DOMAIN}';
var basepath = '${BASEPATH}';
var imgbasepath = '${IMGBASEPATH}';
var uploadpath = '${UPLOADPATH}';
</script>
<!-- dwz --> 
<script src="${BASEPATH}plugin/dwz-ria/dwz-include.js" type="text/javascript"></script>

<link href="${BASEPATH}plugin/swfupload/process.css" rel="stylesheet" type="text/css"/>
<script src="${BASEPATH}plugin/swfupload/swfupload.js" type="text/javascript"></script>
<script src="${BASEPATH}plugin/swfupload/swfupload.queue.js" type="text/javascript"></script>
<script src="${BASEPATH}plugin/swfupload/fileprogress.js" type="text/javascript"></script>
<script src="${BASEPATH}plugin/swfupload/handlers.js" type="text/javascript"></script>
<script src="${BASEPATH}plugin/dragsort/jquery.dragsort-0.5.1.min.js"></script>
<script src="${BASEPATH}js/main.js"></script>
<script src="${BASEPATH}js/util/mypopup.js"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("${BASEPATH}plugin/dwz-ria/dwz.frag.xml", {
		loginUrl:"${DOMAIN}login",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"${BASEPATH}plugin/dwz-ria/themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});
</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				        <a class="logo logo_c">标志</a>
				<ul class="nav">
					<li>
						<a href="#" width="600">${sysadmin.realname!''}</a>
           	    	</li>
					<li><a href="${DOMAIN}user/updatepwdinit" target="dialog" width="600">修改密码</a></li>
					<li><a href="${DOMAIN}logout">退 出</a></li>
				</ul>
			</div>
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>
				<div class="accordion" fillSpace="sidebar">
				
					
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="${DOMAIN}comment/shop-list" rel="comment/shop-list" target="navTab">店铺管理</a></li>
						</ul>
						<ul class="tree treeFolder">
							<li><a href="${DOMAIN}comment/order-list" rel="comment/order-list" target="navTab">订单管理</a></li>
						</ul>
						<ul class="tree treeFolder">
							<li><a href="${DOMAIN}comment/wxpic-list" rel="comment/wxpic-list" target="navTab">评价管理</a></li>
						</ul>
						<ul class="tree treeFolder">
							<li><a href="${DOMAIN}comment/wxuser-list" rel="comment/wxuser-list" target="navTab">客户管理</a></li>
						</ul>
						<ul class="tree treeFolder">
							<li><a href="${DOMAIN}comment/activ-list" rel="comment/activ-list" target="navTab">活动管理</a></li>
						</ul>
					</div>
					
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<!--
						<div class="accountInfo">
						</div>
						-->
						<div class="pageFormContent" layoutH="80" style="margin-right:230px">
						
						</div>
						
						<div>
							<iframe width="100%" height="430" class="share_self"  frameborder="0" scrolling="no" src=""></iframe>
						</div>
					</div>
					
				</div>
			</div>
		</div>

	</div>
	<div id="footer">Copyright &copy;2018 VVA  &nbsp;<a target="_blank" href="http://www.miitbeian.gov.cn" style="cursor: pointer;">粤ICP备18085048号-1</a></div>
</body>
</html>