<div class="pageContent">
	<form method="post" action="${DOMAIN}user/updatepwd" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" id="roles" name="roles" value=""/>
			<div class="unit">
				<label style="text-align:right">原密码：</label>
				<input name="pwd" class="required" type="password" size="30" value="" alt="请输入密码"/>
			</div>
			<div class="unit">
				<label style="text-align:right">新密码：</label>
				<input name="password" id="password" class="required" type="password" size="30" value="" alt="请输入密码"/>
			</div>
			<div class="unit">
				<label style="text-align:right">确认新密码：</label>
				<input name="password1" class="required password" type="password" size="30" value="" alt="请输入确认密码"/>	
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
</div>
