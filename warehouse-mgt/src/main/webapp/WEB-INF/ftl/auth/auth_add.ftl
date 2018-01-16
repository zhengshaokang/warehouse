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
	<form method="post" action="${DOMAIN}auth/authAddSubmit" class="pageForm required-validate" onsubmit="return validateCallback1(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label style="text-align:right">菜单名称：</label>
				<input name="authName" class="required" type="text" size="30" value="" alt="请输入菜单名称"/>
			</div>
			<div class="unit">
				<label style="text-align:right">菜单链接：</label>
				<input name="authUrl" class="required" type="text" size="30" value="" alt="请输入菜单链接"/>
			</div>
			<div class="unit">
				<label style="text-align:right">菜单图片路径：</label>
				<input name="authPic" class="required1" type="text" size="30" value="" alt="请输入菜单图片路径"/>
			</div>
			<div class="unit">
				<label style="text-align:right">父菜单号：</label>
				<input name="parentId" class="required1" type="text" size="30" value="0" onkeyup="checkNum(this)" alt="请输入父菜单号"/>
				<label style="color:red">默认0为顶级菜单</label>
			</div>
			<div class="unit">
				<label style="text-align:right">菜单排序：</label>
				<input name="sort" class="required" type="text" size="30" value="" onkeyup="checkNum(this)" alt="请输入菜单排序"/>
			</div>
			<div class="unit">
				<label style="text-align:right">记录状态：</label>
				<input type='checkbox'  checked="checked"  name='recordStatus' value='0'/> 启用  <input type='checkbox' name='recordStatus' value='1'/> 禁用
			</div>
		   <div class="unit">
				<label style="text-align:right">按钮权限：</label>
				<input type='checkbox' name='authButton' value='0'/> 增加  <input type='checkbox' name='authButton' value='1'/> 删除<input type='checkbox' name='authButton' value='2'/> 修改  <input type='checkbox' name='authButton' value='3'/> 导出
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
  

