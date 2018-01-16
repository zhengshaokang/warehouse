<form id="pagerForm" method="post" action="${DOMAIN}auth/list.ithml">
	<input type="hidden" name="pageNum" value="${pageParam.pageNo}" />
	<input type="hidden" name="numPerPage" value="${pageParam.pageSize}" />
	<input type="hidden" name="username" value="${authParam.username!''}" />
	<input type="hidden" name="phone" value="${authParam.phone!''}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${DOMAIN}auth/list" method="post">
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${DOMAIN}auth/authAdd" mask="true" target="dialog"><span>添加</span></a></li>
			<li><a class="edit" href="${DOMAIN}auth/authUpdate?id={id}" target="dialog" mask="true" warn="请选择权限"><span>修改</span></a></li>
			<li><a class="delete" href="${DOMAIN}auth/authDelete?id={id}" target="ajaxTodo" callback="navTabAjaxDone" title="确定要删除吗?"><span>删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="137">
		<thead>
			<tr>
			    <th width="120">菜单编号</th>
				<th width="120">菜单名称</th>
				<th width="120">菜单链接</th>
				<th width="120">菜单图片路径</th>
				<th width="120">父菜单号</th>
				<th width="120">菜单排序</th>
				<th width="60">记录状态</th>
			</tr>
		</thead>
		<tbody>
			<#if pageParam ?? && pageParam.getPageData() ??>
				<#list pageParam.getPageData() as user>
					<tr target="id" rel="${user.id}">
					    <td>${user.id}</td>
						<td>${user.authName!''}</td>
						<td>${user.authUrl!''}</td>
						<td>${user.authPic!''}</td>
						<td>${user.parentId!''}</td>
						<td>${user.sort!''}</td>
						<td>${status["${user.recordStatus!''}"]}</td>
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
