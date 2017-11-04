 <div class="pageContent">
	<form method="post" action="${DOMAIN}role/roleAdd" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	  	<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label style="text-align:right">角色名：</label>
				<input name="roleName" class="required" type="text" size="30" value="" class="inputText" placeholder="角色名"/>
			</div>
			<div class="unit">
				<div><label style="text-align:right">角色：</label></div>
				<div id='role' class="test">
					<#if roleAuthList??>
						<table class="table" width="80%">
							<tr>
								<th width="20%"><h3>一级菜单</h3></th>
								<th width="50%"><h3>二级菜单</h3></th>
							</tr>
							<#list roleAuthList as auth>
								<tr>
									<td>
										<input type='checkbox' name='addAuthId' value='${auth.id}'/>${auth.authName}
									</td>
									<td>
									<#list roleAuthListSub as authSub>
										<#if auth.id == authSub.parentId>
											<input type='checkbox' name='addAuthId' value='${authSub.id}'/>${authSub.authName}
										</#if>
									</#list>
									</td>
								</tr>
							</#list>
						</table>
					</#if>
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
