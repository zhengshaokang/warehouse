<#if agent?? && agent == "M">
<#include "../m/product_list.ftl"/>
<#else>
<form id="pagerForm" method="post" action="${DOMAIN}product/list">
	<input type="hidden" name="pageNum" value="${pageParam.pageNo}" />
	<input type="hidden" name="numPerPage" value="${pageParam.pageSize}" />
	<input type="hidden" name="name" value="${productParam.name!''}" />
	<input type="hidden" name="sku" value="${productParam.sku!''}" />
	<input type="hidden" name="code" value="${productParam.code!''}" />
	<input type="hidden" name="brandId" value="${productParam.brandId!''}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${DOMAIN}product/list" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
			       	 商品名称：<input type="text" name="name" value="${productParam.name!''}"/>
				</td>
				<td>
				   	国际条码：<input type="text" name="code" value="${productParam.code!''}"/>
				</td>
				<td>
				           内部编码：<input type="text" name="sku" value="${productParam.sku!''}"/>
				</td>
				<td>
				           品牌：
				     <select name="brandId" id="add_product_brand" style="min-width:120px;" class="required">
						<option value="-1"></option>
						<#list brands?keys as key> 
							<#if productParam.brandId ?? && productParam.brandId == key?eval>
								<option selected="selected" value="${key}">${brands[key]}	</option>	
							<#else>
								<option value="${key}">${brands[key]}	</option>	
							</#if>		
						</#list>
					</select>
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
			<li><a class="add" href="${DOMAIN}product/productAdd" mask="true" target="navTab" title="新增商品"><span>添加</span></a></li>
			<li><a class="delete" href="${DOMAIN}product/productDelete?productId={id}" target="ajaxTodo" callback="navTabAjaxDone" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="${DOMAIN}product/productUpdate?productId={id}" target="navTab" mask="true" warn="请选择商品" title="修改商品"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="137">
		<thead>
			<tr>
				<th width="80"></th>
				<th width="200">商品名称</th>
				<th width="100">国际条码</th>
				<th width="100">内部编码</th>
				<th width="100">品牌</th>
				<th width="80">单位</th>
				<th width="80">规格</th>
				<th width="80">分类</th>
				<th width="100">供应商</th>
				<th width="100">备注</th>
				<th width="80">操作</th>
			</tr>
		</thead>
		<tbody>
			<#if pageParam ?? && pageParam.getPageData() ??>
				<#list pageParam.getPageData() as product>
					<tr target="id" rel="${product.id}" style="height:40px;">
						<td><img src="${IMGBASEPATH}${product.picUrl!''}_80x80.${product.picType!'jpg'}"></td>
						<td>
							${product.name!''}
						</td>
						<td>
							${product.code!''}
						</td>
						<td>
							${product.sku!''}
						</td>
						<td>
							${brands["${product.brandId!''}"]}
						</td>
						<td>${unitsOptions["${product.unitId!''}"]}</td>
						<td>
							${productSpecificationss["${product.specificationId1!''}"]}>>>
							<#if product.specificationId2 ??>
								${productSpecificationss["${product.specificationId2!''}"]}
							</#if>
						</td>
						<td>
							${productClassifications["${product.classificationId1!''}"]}>>>
							<#if product.classificationId2 ??>
								${productClassifications["${product.classificationId2!''}"]}
							</#if>
						</td>
						<td>
							${suppliers["${product.supplierId!''}"]}
						</td>
						<td>
							${product.remark!''}
						</td>
						<td>
							<a class="add" href="${DOMAIN}product/productIn?productId=${product.id}" mask="true" target="navTab">入库 </a></br>
							<a class="add" href="${DOMAIN}product/productOut?productId=${product.id}" mask="true" target="navTab">出库 </a></br>
							<a class="add" href="${DOMAIN}product/productPrice?productId=${product.id}" mask="true" target="dialog">查看进价 </a></br>
							<a class="add" href="${DOMAIN}product/productQty?productId=${product.id}" mask="true" target="dialog">查看库存</a></br>
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
</#if>
