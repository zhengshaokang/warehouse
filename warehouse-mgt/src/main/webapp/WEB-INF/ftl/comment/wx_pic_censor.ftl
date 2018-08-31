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
$(function(){
	  $('input[type=radio][name=picSorStatus]').change(function() {
        if (this.value == '1') {
            $('textarea[name=checkReason]').text("您参与的晒图有礼活动已经通过审核，请到手淘消息中查看领取！");
        } else {
            $('textarea[name=checkReason]').text("很遗憾，您参与的晒图有礼活动由于未按要求进行晒图导致未通过审核，请按要求重新提交！");
        }
    });
})
</script>
 <div class="pageContent">
	<form method="post" action="${DOMAIN}comment/wxpicCheck" class="pageForm required-validate" onsubmit="return validateCallback1(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="wxPicId" id="wxPicId" value="${wxPicId!''}"/>
			<div class="unit">
				<label style="text-align:right">是否通过审核：</label>
				<input type='radio' name='picSorStatus' value='1' checked="checked"/> 是  <input type='radio' name='picSorStatus' value='0'/> 否
			</div>
			<div class="unit">
				<label style="text-align:right">审核原因：</label>
				<textarea name="checkReason" alt="审核原因" cols="30" rows="8">您参与的晒图有礼活动已经通过审核，请到手淘消息中查看领取！</textarea>
			</div>
			<div class="unit">
				<label style="text-align:right">是否发通知：</label>
				<input type='radio' name='sendMassge' value='1' checked="checked"/> 是
				<input type='radio' name='sendMassge' value='0'/> 否
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
  

