 <div class="pageContent">
	<div class="pageFormContent" layoutH="56">
		<table class="table" width="100%" layoutH="137">
		<thead>
			<tr>
				<th width="150">入库时间</th>
				<th width="150">入库数量</th>
				<th width="150">商品单价</th>
			</tr>
		</thead>
		<tbody>
			<#if productPrices ??>
				<#list productPrices as pp>
					<tr target="id" style="height:40px;">
						<td>${pp.inDate!''}</td>
						<td>
							${pp.inQty}
						</td>
						<td>
							${pp.price}
						</td>
					</tr>
				</#list>
			</#if>
		</tbody>
	</table>
	</div>
</div>
  

