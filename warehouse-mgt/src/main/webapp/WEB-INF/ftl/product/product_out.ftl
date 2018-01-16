
 <div class="pageContent">
	<form method="post" action="${DOMAIN}product/productOutSubmit" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="productId" value="${product.id}"/>
		<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label style="text-align:right">商品名称：</label>${product.name!''}
			</div>
			<div class="unit">
				<label style="text-align:right">当前库存：</label>${qty!''}
			</div>
			<div class="unit">
				<label style="text-align:right">出库位置：</label>
				<select name="warehouseCode" id="product_out_wcode" style="min-width:120px;" class="required">
					<option value="-1"></option>
					<#list warehouses?keys as key> 
						<option value="${key}">${warehouses[key]}</option>					
					</#list>
				</select>
			</div>
			<div class="unit">
				<label style="text-align:right">出库批次：</label>
				<select name="batchNo" id="product_out_bacthNo" style="min-width:120px;" class="required">
					<option value="-1"></option>
					<#list qtys?keys as key> 
						<option value="${key}">${qtys[key]}</option>					
					</#list>
				</select>
			</div>
			<div class="unit">
				<label style="text-align:right">出库类型：</label>
				<select name="inoutRecordType" style="min-width:120px;" class="required">
					<option value="-1"></option>
					<#list recordTypes?keys as key> 
						<option value="${key}">${recordTypes[key]}</option>					
					</#list>
				</select>
			</div>
			<div class="unit">
				<label style="text-align:right">出库数量：</label>
				<input name="qty" value="" size="30"  class="required" alt="请输入数量！" />
			</div>
			<div class="unit">
				<label style="text-align:right">备注：</label>
				<textarea name="remark" alt="商品备注" cols="35" rows="8"></textarea>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
				   <div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
			    </li>
			</ul>
		</div>
	</form>
</div>
  

