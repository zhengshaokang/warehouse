<form id="pagerForm" method="post" action="${DOMAIN}product/unitlist">
	<input type="hidden" name="pageNum" value="${pageParam.pageNo}" />
	<input type="hidden" name="numPerPage" value="${pageParam.pageSize}" />
	<input type="hidden" name="name" value="${productUnitsParam.name!''}" />
	<input type="hidden" name="remark" value="${productUnitsParam.remark!''}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${DOMAIN}product/unitlist" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
				        单位名称：<input type="text" name="name" value="${productUnitsParam.name!''}"/>
				</td>
				<td>
				        备注：<input type="text" name="remark" value="${productUnitsParam.remark!''}"/>
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
			<li><a class="add" href="${DOMAIN}product/productUnitAdd" mask="true" target="navTab" title="新增商品单位"><span>添加</span></a></li>
			<li><a class="delete" href="${DOMAIN}product/productUnitDelete?id={id}" target="ajaxTodo" callback="navTabAjaxDone" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="${DOMAIN}product/productUnitUpdate?id={id}" target="navTab" mask="true" warn="请选择单位" title="修改商品单位"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="137">
		<thead>
			<tr>
				<th width="230">单位名称</th>
				<th width="150">状态</th>
				<th width="140">备注</th>
				<th width="80">创建时间</th>
				<th width="100">创建人</th>
				<th width="80">操作</th>
			</tr>
		</thead>
		<tbody>
			<#if pageParam ?? && pageParam.getPageData() ??>
				<#list pageParam.getPageData() as pc>
					<tr target="id" rel="${pc.id}" style="height:30px;">
						<td>${pc.name!''}</td>
						<td>
							${status["${pc.status!''}"]}
						</td>
						<td>
							${pc.remark!''}
						</td>
						<td>
							${pc.createTime!''}
						</td>
						<td>
							${users["${pc.creator!''}"]}
						</td>
						<td>
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
