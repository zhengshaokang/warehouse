 
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
	<form method="post" action="${DOMAIN}comment/shop-update-submit" class="pageForm required-validate" onsubmit="return validateCallback1(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label style="text-align:right">店铺名称：</label>
				<input name="name" class="required" type="text" size="30" value="${shop.name!''}" alt="请输店铺名称"/>
			</div>
			<div class="unit">
				<label style="text-align:right">店铺链接：</label>
				<input name="url" class="required" type="text" size="30" value="${shop.url!''}" alt="请输店铺链接"/>
			</div>
			<div class="unit">
				<label style="text-align:right">平台：</label>
				<select name="platform" style="min-width:120px;" class="required">
					<#list platforms?keys as key> 
						<#if shop.platform?? && key?eval == shop.platform> 
							<option value="${key}" selected="selected">${platforms[key]}</option>	
						<#else>
							<option value="${key}">${platforms[key]}</option>	
						</#if>				
					</#list>
				</select>
			</div>
			<div class="unit">
				<label style="text-align:right">状态：</label>
				<select name="status" style="min-width:120px;" class="required">
					<#list status?keys as key> 
						<#if shop.status?? && key?eval == shop.status> 
							<option value="${key}" selected="selected">${status[key]}</option>	
						<#else>
							<option value="${key}">${status[key]}</option>	
						</#if>				
					</#list>
				</select>
			</div>
			<input name="id"  type="hidden" value="${shop.id}"/>
			<input name="userId"  type="hidden" value="${shop.userId}"/>
			<input name="department"  type="hidden" value="${shop.department!'-1'}"/>
			<input name="createTime"  type="hidden" value="${shop.createTime!''}"/>
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
  

