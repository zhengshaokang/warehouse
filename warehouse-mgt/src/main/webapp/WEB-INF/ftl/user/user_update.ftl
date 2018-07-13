<style>
.user_ah_ul{
	margin-left: 0; 
	padding-left: 0; 
	display: inline;
}
.user_ah_ul li {
	margin-left: 0;
    padding: 3px 5px; 
    list-style: none; 
    display: inline;
}
.user_ah_ul li input {
	margin:1px 0px 0px 0px;
}

</style>

<script>

(function($){ 
	$.validator.addMethod("chlanguage", function(value, element) {                 
		var chcheck = /[\u4E00-\u9FA5]/; 
		return this.optional(element) || chcheck.test(value);            
	}, "请输入汉字"); 
	$.validator.addMethod("idcard", function(value, element) { 
		var idcheck = /^((1[1-5])|(2[1-3])|(3[1-7])|(4[1-6])|(5[0-4])|(6[1-5])|71|(8[12])|91)\d{4}((19\d{2}(0[13-9]|1[012])(0[1-9]|[12]\d|30))|(19\d{2}(0[13578]|1[02])31)|(19\d{2}02(0[1-9]|1\d|2[0-8]))|(19([13579][26]|[2468][048]|0[48])0229))\d{3}(\d|X|x)?$/;                 
		return this.optional(element) || idcheck.test(value);             
	}, "身份证格式不正确"); 
	$.validator.addMethod("mobile", function(value, element) {                 
		var mobilecheck = /^(13|15|18)\d{9}$/i; 
		return this.optional(element) || mobilecheck.test(value);             
	}, "手机号码格式不正确");     
	$.validator.addMethod("password", function(value, element) { 
		var pwd1 = $("#password").val(); 
		return this.optional(element) || pwd1 ==value;             
	}, "两次密码输入不一致"); 
})(jQuery);  

function validateCallback1(form, callback) {
	var $form = $(form);

	if (!$form.valid()) {
		return false;
	}
	
	var isWarhouse = $("input[name='isWarhouse']").val();
	if(isWarhouse == 1) {
		var roles = $("#role").find("input"); 
		var flag= false;
		var rolesStr = "";
		$.each(roles,function(i,d){
			if($(d).attr("checked") == "checked"){
				flag = true;
				 rolesStr= rolesStr +d.value+",";
			}
		});
		if(!flag){
			alertMsg.error("请选择角色");
			return false;
		}
		$("#roles").val(rolesStr.substring(0,rolesStr.length-1));
	} else {
		$("#roles").val("");
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
	<form method="post" action="${DOMAIN}user/userUpdateSubmit" class="pageForm required-validate" onsubmit="return validateCallback1(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" id="roles" name="roles" value=""/>
			<input type="hidden" name="id" value="${userUpdatevo.id!''}"/>
			<div class="unit">
				<label style="text-align:right">姓名：</label>
				<input name="realname" class="required" type="text" size="30" value="${userUpdatevo.realname!''}" alt="请输入姓名"/>
			</div>
			<div class="unit">
				<label style="text-align:right">账号：</label>
				<input name="loginname" class="required" type="text" size="30" value="${userUpdatevo.loginname!''}" alt="请输入账号"/>
			</div>
			<div class="unit">
				<label style="text-align:right">手机号：</label>
				<input name="mobile" class="required mobile" type="text" size="30" value="${userUpdatevo.mobile!''}" alt="请输入手机号"/>
			</div>
			
			<div class="unit">
				<label style="text-align:right">是否仓库用户：</label>
				<input name="isWarhouse"  type="radio" size="30" value="0" <#if userUpdatevo.isWarhouse==0>checked="checked"</#if> />否
				<input name="isWarhouse"  type="radio" size="30" value="1" <#if userUpdatevo.isWarhouse==1>checked="checked"</#if> />是
			</div>
			<div class="unit">
				<label style="text-align:right">是否评价用户：</label>
				<input name="isComment"  type="radio" size="30" value="0" <#if userUpdatevo.isComment==0>checked="checked"</#if> />否
				<input name="isComment"  type="radio" size="30" value="1" <#if userUpdatevo.isComment==1>checked="checked"</#if> />是
			</div>
			<div class="unit">
				<label style="text-align:right">会员级别：</label>
				<select name="vipLevel" style="min-width:120px;" >
					<option value="1" <#if userUpdatevo.vipLevel==1>selected="selected" </#if>>试用会员</option>	
					<option value="2" <#if userUpdatevo.vipLevel==2>selected="selected" </#if>>正式会员</option>					
				</select>
			</div>
			<div class="unit">
				<label style="text-align:right">会员到期日期：</label>
				<input type="text" minDate="{%y}-%M-{%d}" name="vipDate" value="${userUpdatevo.vipDate!''}"  class="date" dateFmt="yyyy-MM-dd"/>
			</div>
			<div class="unit">
				<label style="text-align:right">测评模板路径：</label>
				<input name="commentUrl" class="" type="text" size="30" value="${userUpdatevo.commentUrl!''}" alt="请输入测评模板路径"/>
			</div>
			
			<div class="unit">
				<div><label style="text-align:right">角色：</label></div>
				<div id='role' class="test">
					<ul class='user_ah_ul'>
						<#list userUpdateSysRoles as role>
							<#assign boo=false/>
							<#list userUpdateUserSysRoles as userrole>
								<#if role.id==userrole.id>
									<#assign boo=true/>
								</#if>
							</#list>
							<#if boo>
								<li><input type='checkbox' class='validRole' name='addRoleId' value='${role.id}' checked="checked"/> ${role.roleName}</li>
							<#else>
								<li><input type='checkbox' class='validRole' name='addRoleId' value='${role.id}'/> ${role.roleName}</li>
							</#if>
							<input type="hidden" name="delRoleId" value="${role.id}"/>
						</#list>
					</ul>
				</div>
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
  

