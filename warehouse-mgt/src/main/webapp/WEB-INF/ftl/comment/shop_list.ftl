<form id="pagerForm" method="post" action="${DOMAIN}comment/shop-list">
	<input type="hidden" name="pageNum" value="${pageParam.pageNo}" />
	<input type="hidden" name="numPerPage" value="${pageParam.pageSize}" />
	<input type="hidden" name="shopNo" value="${shopParam.shopNo!''}" />
	<input type="hidden" name="shopId" value="${shopParam.phone!''}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${DOMAIN}comment/shop-list" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
				       店铺名称：<input type="text" name="name" value="${shopParam.name!''}"/>
				</td>
				<td>
				          平台：
			           <select name="platform" style="min-width:120px;" >
							<#list platforms?keys as key> 
								<#if shopParam.platform ?? && shopParam.platform == key?eval>
									<option selected="selected" value="${key}">${platforms[key]}	</option>	
								<#else>
									<option value="${key}">${platforms[key]}</option>	
								</#if>		
							</#list>
					</select>
				</td>
				<#if userId == 1>
					<td>
					          创建人：
					          <select name="userId" style="min-width:120px;" >
								<#list users?keys as key> 
									<#if shopParam.userId ?? && shopParam.userId == key?eval>
										<option selected="selected" value="${key}">${users[key]}	</option>	
									<#else>
										<option value="${key}">${users[key]}</option>	
									</#if>		
								</#list>
							  </select>
					</td>				
				</#if>
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
			<li><a class="add" href="${DOMAIN}comment/shop-add" mask="true" target="dialog"><span>添加</span></a></li>
			<li><a class="delete" href="${DOMAIN}comment/shop-delete?shopId={id}" target="ajaxTodo" callback="navTabAjaxDone" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="${DOMAIN}comment/shop-update?shopId={id}" target="dialog" mask="true" warn="请选择店铺"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="137">
		<thead>
			<tr>
				<th width="60">店铺id</th>
				<th width="200">店铺名称</th>
				<th width="200">店铺链接</th>
				<th width="160">类目</th>
				<th width="160">平台</th>
				<th width="80">状态</th>
				<#if userId == 1>
					<th width="200">创建人</th>
				</#if>
				<th width="120">创建时间</th>
			</tr>
		</thead>
		<tbody>
			<#if pageParam ?? && pageParam.getPageData() ??>
				<#list pageParam.getPageData() as shop>
					<tr target="id" rel="${shop.id}">
						<td>
							${shop.id!''}
						</td>
						<td>
							${shop.name!''}
						</td>
						<td>
							${shop.url!''}
						</td>
						<td>
							${shop.department!''}
						</td>
						<td>
							${platforms["${shop.platform!''}"]}
						</td>
						<td>
							${status["${shop.status!''}"]}
						</td>
						<#if userId == 1>
							<td>
								${users["${shop.userId!''}"]}
							</td>
						</#if>
						<td>${shop.createTime!''}</td>
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
