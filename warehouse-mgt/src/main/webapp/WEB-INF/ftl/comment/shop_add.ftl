 
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
	<form method="post" action="${DOMAIN}comment/shop-add-submit" class="pageForm required-validate" onsubmit="return validateCallback1(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label style="text-align:right">店铺名称：</label>
				<input name="name" class="required" type="text" size="30" value="" alt="请输店铺名称"/>
			</div>
			<div class="unit">
				<label style="text-align:right">店铺链接：</label>
				<input name="url" class="required" type="text" size="30" value="" alt="请输店铺链接"/>
			</div>
			<div class="unit">
				<label style="text-align:right">平台：</label>
				<select name="platform" style="min-width:120px;" class="required">
					<#list platforms?keys as key> 
						<option value="${key}">${platforms[key]}</option>					
					</#list>
				</select>
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
  

