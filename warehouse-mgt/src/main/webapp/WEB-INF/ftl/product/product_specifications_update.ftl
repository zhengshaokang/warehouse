 
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
	<form method="post" action="${DOMAIN}product/productSpecificationssUpdateSubmit" class="pageForm required-validate" onsubmit="return validateCallback1(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${productSpecifications.id}"/>
		<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label style="text-align:right">规格名称：</label>
				<input name="name" class="required" type="text" size="30" readonly="true" value="${productSpecifications.name!''}" alt="请输入规格名称"/>
			</div>
			<div class="unit">
				<label style="text-align:right">上级规格：</label>
				<select name="parentId"  style="min-width:80px;">
					<#list productSpecificationssOne?keys as key> 
					
						<#if productSpecifications.parentId == key?eval>
							<option selected="selected" value="${key}">${productSpecificationssOne[key]}</option>	
						<#else>
							<option value="${key}">${productSpecificationssOne[key]}</option>
						</#if>
											
					</#list>
				</select>
			</div>
			
			<div class="unit">
				<label style="text-align:right">备注：</label>
				<textarea name="remark" alt="备注" cols="35" rows="8">${productSpecifications.remark!''}</textarea>
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
  

