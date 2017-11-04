<style>
</style>

<script>

</script>
 <div class="pageContent">
	<form method="post" action="${DOMAIN}product/productOutSubmit" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="productId" value="${product.id}"/>
		<input type="hidden" name="recordType" value="${recordType}"/>
		<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label style="text-align:right">商品名称：</label>${product.name!''}
			</div>
			<div class="unit">
				<label style="text-align:right">SKU：</label>${product.sku!''}
			</div>
			<div class="unit">
				<label style="text-align:right">出库数量：</label>
				<input name="qty" value="" size="30"  class="required" alt="请输入数量！" />
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
  

