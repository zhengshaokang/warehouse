 
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
	<form method="post" action="${DOMAIN}warehouse/updateWarehouseSubmit" class="pageForm required-validate" onsubmit="return validateCallback1(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${warehouse.id}"/>
		<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label style="text-align:right">仓库名称：</label>
				<input name="name" class="required" type="text" size="30" readonly="true" value="${warehouse.name!''}" alt="请输仓库名称"/>
			</div>
			<div class="unit">
				<label style="text-align:right">仓库编码：</label>
				<input name="code" class="required" type="text" size="30" readonly="true" value="${warehouse.code!''}" alt="请输仓库编码"/>
			</div>
			<div class="unit">
				<label style="text-align:right">仓库地址：</label>
				<input name="address" class="required" type="text" size="30" value="${warehouse.address!''}" alt="请输仓库地址"/>
			</div>
			
			<div class="unit">
				<label style="text-align:right">备注：</label>
				<textarea name="remark" alt="备注" cols="35" rows="8">${warehouse.remark!''}</textarea>
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
  

