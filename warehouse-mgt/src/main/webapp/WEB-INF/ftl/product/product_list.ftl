<form id="pagerForm" method="post" action="${DOMAIN}product/list">
	<input type="hidden" name="pageNum" value="${pageParam.pageNo}" />
	<input type="hidden" name="numPerPage" value="${pageParam.pageSize}" />
	<input type="hidden" name="name" value="${productParam.name!''}" />
	<input type="hidden" name="sku" value="${productParam.sku!''}" />
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
				   SKU：<input type="text" name="sku" value="${productParam.sku!''}"/>
				</td>
				<td>
				        生产日期：<input type="text" name="productionDate" value="${productParam.productionDate!''}"/>
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
			<li><a class="edit" href="${DOMAIN}product/productUpdate?productId={id}" target="navTab" mask="true" warn="请选择用户" title="修改商品"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="137">
		<thead>
			<tr>
				<th width="100"></th>
				<th width="230">商品信息</th>
				<th width="150">生产信息</th>
				<th width="150">库存</th>
				<th width="140">包装信息</th>
				<th width="80">商品状态</th>
				<th width="230">备注信息</th>
				<th width="80">操作</th>
			</tr>
		</thead>
		<tbody>
			<#if pageParam ?? && pageParam.getPageData() ??>
				<#list pageParam.getPageData() as product>
					<tr target="id" rel="${product.id}" style="height:40px;">
						<td><img src="${IMGBASEPATH}${product.picUrl!''}_80x80.jpg"></td>
						<td>
							商品名称：${product.name!''}</br>
							SKU：${product.sku!''}</br>
							单位：${unitsOptions["${product.unit!''}"]}</br>
							价格(￥)：${product.price!''}</br>
							属性：<#list product.getProductAttribute() as attr>
								<span>${attr.value!''}</span><br>
							</#list>
						</td>
						<td>
							生产日期：${product.productionDate!''}</br>
							到期日期：${product.maturityDate!''}</br>
							有效期：${product.effectiveDay!''} 天</br>
							剩余有效期：${product.effectiveDay1!''} 天
						</td>
						<td>
							总库存：${product.inventoryTotal!''}</br>
							剩余库存：${product.inventoryAvailable!''}</br>
							盘点库存：${product.inventoryCheck!''}</br>
							盘点日期：${product.checkDate!''}
						</td>
						<td>
							包装类型：${packTypes["${product.packType!''}"]}</br>
							商品类型：${productTypes["${product.type!''}"]}
						</td>
						<td>${productStatus["${product.status!''}"]}</td>
						<td>
							${product.description!''}</br>
							<img src="${BASEPATH}/img/common/system-log.png" onclick="mypopup.log(this,'${product.sysRemark!''}')"/>
						</td>
						<td>
							<a class="add" href="${DOMAIN}product/productIn?productId=${product.id}" mask="true" target="dialog">商品入库 </a></br>
							<a class="add" href="${DOMAIN}product/productOut?productId=${product.id}" mask="true" target="dialog">订单出库 </a></br>
							<a class="add" href="${DOMAIN}product/productUp?productId=${product.id}" mask="true" target="ajaxTodo">上架 </a></br>
							<a class="add" href="${DOMAIN}product/productOffline?productId=${product.id}" mask="true" target="ajaxTodo">下架 </a></br>
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
