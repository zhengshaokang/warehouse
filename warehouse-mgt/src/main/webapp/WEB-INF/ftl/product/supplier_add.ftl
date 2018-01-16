 
 <script>
 function validateCallback1(form, callback) {
	var $form = $(form);

	if (!$form.valid()) {
		return false;
	}
	var _submitFn = function(){
		$.ajax({
			type: form.method || 'POST',
			url:$form.attr("action"),
			data:$form.serializeArray(),
			dataType:"json",
			cache: false,
			success: callback || DWZ.ajaxDone,
			error: DWZ.ajaxError
		});
	}
	_submitFn();
	return false;
}
 </script>
 <div class="pageContent">
	<form method="post" action="${DOMAIN}product/supplierAddSubmit" class="pageForm required-validate" onsubmit="return validateCallback1(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label style="text-align:right">供应商名称：</label>
				<input name="name" class="required" type="text" size="30" value="" alt="请输供应商名称"/>
			</div>
			<div class="unit">
				<label style="text-align:right">联系人：</label>
				<input name="contacts" class="required" type="text" size="30" value="" alt="请输联系人"/>
			</div>
			<div class="unit">
				<label style="text-align:right">联系电话：</label>
				<input name="telephone" class="required" type="text" size="30" value="" alt="请输联系电话"/>
			</div>
			<div class="unit">
				<label style="text-align:right">联系地址：</label>
				<input name="address" class="required" type="text" size="30" value="" alt="请输联系地址"/>
			</div>
			
			<div class="unit">
				<label style="text-align:right">备注：</label>
				<textarea name="remark" alt="备注" cols="35" rows="8"></textarea>
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
  

