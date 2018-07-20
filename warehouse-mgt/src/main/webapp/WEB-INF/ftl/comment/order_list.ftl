<form id="pagerForm" method="post" action="${DOMAIN}comment/order-list">
	<input type="hidden" name="pageNum" value="${pageParam.pageNo}" />
	<input type="hidden" name="numPerPage" value="${pageParam.pageSize}" />
	<input type="hidden" name="orderNo" value="${orderParam.orderNo!''}" />
	<input type="hidden" name="shopId" value="${orderParam.shopId!''}" />
	<#if userId == 1>
	<input type="hidden" name="userId" value="${orderParam.userId!''}" />
	</#if>
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${DOMAIN}comment/order-list" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
				        订单号：<input type="text" name="orderNo" value="${orderParam.orderNo!''}"/>
				</td>
				<td>
				          店铺：
				          <select name="shopId" style="min-width:120px;" >
							<#list shops?keys as key> 
								<#if orderParam.shopId ?? && orderParam.shopId == key?eval>
									<option selected="selected" value="${key}">${shops[key]}	</option>	
								<#else>
									<option value="${key}">${shops[key]}</option>	
								</#if>		
							</#list>
						  </select>
				</td>
				<td>
				          审核状态：
				          <select name="isPay" style="min-width:120px;" >
				          	<option value="-1">请选择</option>	
							<#list payStatus?keys as key> 
								<#if orderParam.isPay ?? && orderParam.isPay == key?eval>
									<option selected="selected" value="${key}">${payStatus[key]}	</option>	
								<#else>
									<option value="${key}">${payStatus[key]}</option>	
								</#if>		
							</#list>
						  </select>
				</td>
				<#if userId == 1>
					<td>
					          创建人：
					          <select name="userId" style="min-width:120px;" >
								<#list users?keys as key> 
									<#if orderParam.userId ?? && orderParam.userId == key?eval>
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
			<li><a class="add" href="${DOMAIN}comment/order-add" mask="true" target="navTab"><span>添加订单</span></a></li>
			<li><a class="delete" href="${DOMAIN}comment/order-delete?orderId={id}" target="ajaxTodo" callback="navTabAjaxDone" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="${DOMAIN}comment/order-update?orderId={id}" target="navTab" mask="true" warn="请选择订单"><span>修改</span></a></li>
			<li><a class="add" href="${DOMAIN}comment/order-batch-add" target="navTab"><span>订单导入</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="137">
		<thead>
			<tr>
				<th width="220">店铺</th>
				<th width="180">订单号</th>
				<th width="120">订单状态</th>
				<th width="120">订单金额</th>
				<th width="100">参与活动</th>
				<th width="100">审核状态</th>
				<th width="180">客户名称</th>
				<th width="120">客户电话</th>
				<#if userId == 1>
					<th width="200">创建人</th>
				</#if>
				<th width="160">下单时间</th>
				<th width="100">备注</th>
			</tr>
		</thead>
		<tbody>
			<#if pageParam ?? && pageParam.getPageData() ??>
				<#list pageParam.getPageData() as order>
					<tr target="id" rel="${order.id}">
						<td>
							${shops["${order.shopId!''}"]}
						</td>
						<td>
							${order.orderNo!''}
						</td>
						<td>
							${orderStatus["${order.orderStatus!''}"]}
						</td>
						<td>
							${order.orderAmount!''}
						</td>
						<td>
							${yesno["${order.isJoin!''}"]}
						</td>
						<td>
							${payStatus["${order.isPay!''}"]}
						</td>
						<td>${order.customerName!''}</td>
						<td>
							${order.customerMobile!''}
						</td>
						<#if userId == 1>
							<td>
								${users["${order.userId!''}"]}
							</td>
						</#if>
						<td>
							${order.orderTime!''}
						</td>
						<td>
							${order.remark!''}
						</td>
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
