 
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
	<form method="post" action="${DOMAIN}product/productClassificationUpdateSubmit" class="pageForm required-validate" onsubmit="return validateCallback1(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${productClassification.id}"/>
		<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label style="text-align:right">分类名称：</label>
				<input name="name" class="required" type="text" size="30" readonly="true" value="${productClassification.name!''}" alt="请输入分类名称"/>
			</div>
			<div class="unit">
				<label style="text-align:right">上级分类：</label>
				<select name="parentId">
					<option value="-1"></option>
					<#list productClassificationOne?keys as key> 
						<#if productClassification.parentId == key?eval>
							<option selected="selected" value="${key}">${productClassificationOne[key]}</option>	
						<#else>
							<option value="${key}">${productClassificationOne[key]}	</option>	
						</#if>
										
					</#list>
				</select>
			</div>
			
			<div class="unit">
				<label style="text-align:right">备注：</label>
				<textarea name="remark" alt="备注" cols="35" rows="8">${productClassification.remark!''}</textarea>
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
  

