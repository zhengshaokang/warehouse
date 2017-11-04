<style>
</style>

<script>

</script>
 <div class="pageContent">
	<form method="post" action="${DOMAIN}inout/returnstockSubmit" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="recordType" value="${recordType}"/>
		<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label style="text-align:right">SKU：</label>
				<input name="sku" value="" size="30"  class="required" alt="请输入sku！" />
			</div>
			<div class="unit">
				<label style="text-align:right">入库数量：</label>
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
  

