 
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
	<form method="post" action="${DOMAIN}product/productClassificationAddSubmit" class="pageForm required-validate" onsubmit="return validateCallback1(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label style="text-align:right">分类名称：</label>
				<input name="name" class="required" type="text" size="30" value="" alt="请输入分类名称"/>
			</div>
			<div class="unit">
				<label style="text-align:right">上级分类：</label>
				<select name="parentId" style="min-width:80px;">
					<#list productClassificationOne?keys as key> 
						<option value="${key}">${productClassificationOne[key]}	</option>					
					</#list>
				</select>
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
  

