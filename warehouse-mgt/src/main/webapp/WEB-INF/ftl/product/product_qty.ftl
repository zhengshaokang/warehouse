 <div class="pageContent">
	<div class="pageFormContent" layoutH="56">
		<table class="table" width="100%" layoutH="137">
		<thead>
			<tr>
				<th width="150">存放位置</th>
				<th width="150">到期日期</th>
				<th width="150">库存</th>
			</tr>
		</thead>
		<tbody>
			<#if inventorys ??>
				<#list inventorys as inventory>
					<tr target="id" style="height:40px;">
						<td>${warehouses["${inventory.warehouseCode!''}"]}</td>
						<td>
							${inventory.maturityDate!''}
						</td>
						<td>
							${inventory.qty!''}
						</td>
					</tr>
				</#list>
			</#if>
			<tr>
				<td colspan="2" style="text-align: right;">合计：</td>
				<td>${qty}</td>
			</tr>
		</tbody>
	</table>
	</div>
</div>
  

