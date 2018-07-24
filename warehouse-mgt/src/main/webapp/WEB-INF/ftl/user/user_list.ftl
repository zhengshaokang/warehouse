<form id="pagerForm" method="post" action="${DOMAIN}user/list">
	<input type="hidden" name="pageNum" value="${pageParam.pageNo}" />
	<input type="hidden" name="numPerPage" value="${pageParam.pageSize}" />
	<input type="hidden" name="username" value="${userParam.username!''}" />
	<input type="hidden" name="phone" value="${userParam.phone!''}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${DOMAIN}user/list" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
				        用户名称：<input type="text" name="username" value="${userParam.username!''}"/>
				</td>
				<td>
				          手机：<input type="text" name="mobile" value="${userParam.mobile!''}"/>
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${DOMAIN}user/userAdd" mask="true" target="navTab"><span>添加</span></a></li>
			<li><a class="delete" href="${DOMAIN}user/userDelete?userId={id}" target="ajaxTodo" callback="navTabAjaxDone" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="${DOMAIN}user/userUpdate?userId={id}" target="navTab" mask="true" warn="请选择用户"><span>修改</span></a></li>
			<li><a class="delete" href="${DOMAIN}user/userresetpassword?userId={id}" target="ajaxTodo" callback="navTabAjaxDone" title="确定要重置密码吗?"><span>重置密码</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="137">
		<thead>
			<tr>
				<th width="80">用户ID</th>
				<th width="220">基本信息</th>
				<th width="80">用户状态</th>
				<th width="120">用户性质</th>
				<th width="180">会员信息</th>
				<th width="220">测评模板路径</th>
				<th width="220">登录信息</th>
				<th width="100">更新时间</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<#if pageParam ?? && pageParam.getPageData() ??>
				<#list pageParam.getPageData() as user>
					<tr target="id" rel="${user.id}">
						<td>${user.id}</td>
						<td>
							用户名称：${user.loginname!''} <BR>
							真实姓名:${user.realname!''}<BR>
							手机:${user.mobile!''}<BR>
						</td>
						
						<#if user.userStatus == 1>
							<td>正常</td>
						<#elseif user.userStatus ==0>
							<td>冻结</td>
						<#elseif user.userStatus == -1>
							<td></td>
						</#if>
						
						<td>
							仓库用户：<#if user.isWarhouse == 1> 是 <#else>否</#if><br>
							测评用户：<#if user.isComment == 1> 是 <#else>否</#if>
						</td>
						<td>
							级别：<#if user.vipLevel == 2> 正式会员 <#else>试用会员</#if><br>
							到期日期：${user.vipDate!''}
						</td>
						<td>${user.commentUrl!''}</td>
						<td>
							登录时间：${user.loginTime!''}<br>
							登录IP：${user.loginIp!''}<br>
						</td>
						
						<#if user.updateDatetime ??>
							<td>${user.updateDatetime?string("yyyy-MM-dd HH:mm:ss")}</td>
						<#else>
							<td></td>
						</#if>
						
						<td><a class="add" href="${DOMAIN}user/userCensorUpdate?userId=${user.id}" mask="true" target="dialog">未审核 </a>&nbsp;&nbsp;</td>
					</tr>
				</#list>
			</#if>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value,pageNum:1})">
				<#assign seqPage = [20,40,60,80,100]>
				<#list seqPage as pa>
					<#if pageParam.pageSize == pa>
						<option value="${pa}" selected="selected">${pa}</option>
					<#elseif pageParam.pageSize != pa>
						<option value="${pa}">${pa}</option>
					</#if>
				</#list>
			</select>
			<span>条，共${pageParam.dataTotal}条，</span>
			<span>共${pageParam.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${pageParam.dataTotal?c}" numPerPage="${pageParam.pageSize}" pageNumShown="10" currentPage="${pageParam.pageNo}"></div>
	</div>
</div>
