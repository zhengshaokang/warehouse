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
	<form method="post" name="authFrom" action="${DOMAIN}auth/authUpdateSubmit" class="pageForm required-validate" onsubmit="return validateCallback1(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
		  <input type="hidden" name="id" id="id" value="${authUpdatevo.id!''}"/>
			<div class="unit">
				<label style="text-align:right">菜单名称：</label>
				<input name="authName" class="required" type="text" size="30" value="${authUpdatevo.authName!''}" alt="请输入菜单名称"/>
			</div>
			<div class="unit">
				<label style="text-align:right">菜单链接：</label>
				<input name="authUrl" class="required" type="text" size="30" value="${authUpdatevo.authUrl!''}" alt="请输入菜单链接"/>
			</div>
			<div class="unit">
				<label style="text-align:right">菜单图片路径：</label>
				<input name="authPic"  class="required1" type="text" size="30" value="${authUpdatevo.authPic!''}" alt="请输入完整的菜单图片路径"/>
			</div>
			<div class="unit">
				<label style="text-align:right">父菜单号：</label>
				<input name="parentId" class="required" type="text" size="30" value="${authUpdatevo.parentId!''}" onkeyup="checkNum(this)" alt="请输入父菜账号"/>
			</div>
			<div class="unit">
				<label style="text-align:right">菜单排序：</label>
				<input name="sort" class="required sort" type="text" size="30" value="${authUpdatevo.sort!''}"  onkeyup="checkNum(this)"  alt="请输入菜单排序"/>
			</div>
			<div class="unit">
				<label style="text-align:right">记录状态：</label>
				<#if authUpdatevo.sort??>
				   <#if authUpdatevo.sort==1>
				      <input type='checkbox' name='sorStatus' value='0'/> 未删除  <input type='checkbox' checked="checked" name='sorStatus' value='1'/> 删除
				   <#else>
				      <input type='checkbox' name='sorStatus' checked="checked" value='0'/> 未删除  <input type='checkbox'  name='sorStatus' value='1'/> 删除
				   </#if>
			    <#else>
			       <input type='checkbox' name='sorStatus' value='0'/> 未删除  <input type='checkbox' name='sorStatus' value='1'/> 删除
			   </#if>	
			</div>
		   <div class="unit">
				<label style="text-align:right">按钮权限：</label>
				<#if authUpdatevo.sorStatus??>
				   <#if authUpdatevo.sorStatus==0>
				      <input type='checkbox' name='sorStatus' value='0' checked="checked" /> 增加  <input type='checkbox' name='sorStatus' value='1'/> 删除<input type='checkbox' name='sorStatus' value='2'/> 修改  <input type='checkbox' name='sorStatus' value='3'/> 导出
				   <#elseif authUpdatevo.sorStatus==1>
				      <input type='checkbox' name='sorStatus' value='0'/> 增加  <input type='checkbox' name='sorStatus' value='1' checked="checked" /> 删除<input type='checkbox' name='sorStatus' value='2'/> 修改  <input type='checkbox' name='sorStatus' value='3'/> 导出
				   <#elseif authUpdatevo.sorStatus==2>
				      <input type='checkbox' name='sorStatus' value='0'/> 增加  <input type='checkbox' name='sorStatus' value='1'/> 删除<input type='checkbox' name='sorStatus' value='2' checked="checked"/> 修改  <input type='checkbox' name='sorStatus' value='3'/> 导出
				    <#elseif authUpdatevo.sorStatus==3>
				      <input type='checkbox' name='sorStatus' value='0'/> 增加  <input type='checkbox' name='sorStatus' value='1'/> 删除<input type='checkbox' name='sorStatus' value='2'/> 修改  <input type='checkbox' name='sorStatus' value='3' checked="checked" /> 导出
				   </#if>
			    <#else>
			       <input type='checkbox' name='sorStatus' value='0'/> 增加  <input type='checkbox' name='sorStatus' value='1'/> 删除<input type='checkbox' name='sorStatus' value='2'/> 修改  <input type='checkbox' name='sorStatus' value='3'/> 导出
			   </#if>
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
  

