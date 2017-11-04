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
	<form method="post" action="${DOMAIN}user/userCensorUpdateSubmit" class="pageForm required-validate" onsubmit="return validateCallback1(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" id="id" value="${userUpdatevo.id!''}"/>
			<div class="unit">
				<label style="text-align:right">审核原因：</label>
				<textarea name="reason" alt="简介" cols="30" rows="8"></textarea>
			</div>
			
			<div class="unit">
				<label style="text-align:right">状态：</label>
				<input type='checkbox' name='sorStatus' value='0'/> 审核不通过  <input type='checkbox' name='sorStatus' value='1'/> 审核通过
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
  

