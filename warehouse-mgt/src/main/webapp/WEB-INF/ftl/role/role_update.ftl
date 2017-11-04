 <div class="pageContent">
	<form method="post" action="${DOMAIN}role/roleUpdateSubmit" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	  	<input type='hidden' name='id' id="id" value='${roleupdateVo.id!''}'/>
	  	<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label style="text-align:right">角色名：</label>
				<input name="roleName" class="required" type="text" size="30" value="${roleupdateVo.roleName!''}" class="inputText" placeholder="角色名"/>
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
								<#assign one=1>
								<#if roleupdateVo.auths??>
								<#list roleupdateVo.auths as roleauth>
									<#if auth.id == roleauth.id>
										<#assign one=2>
									</#if>
								</#list>
								</#if>
								<tr>
									<td>
										<#if one==2>
											<input type='checkbox' checked="checked" name='addAuthId' value='${auth.id}'/>${auth.authName}
										<#else>
											<input type='checkbox' name='addAuthId' value='${auth.id}'/>${auth.authName}
										</#if>
									</td>
									<td>
										<#list roleAuthListSub as authSub>
											<#if auth.id == authSub.parentId>
												<#assign two=1>
												<#list roleupdateVo.auths as roleauth>
														<#if auth.id == roleauth.id>
															<#assign two=2>
														</#if>
												</#list>
												<#if two==2>
													<input type='checkbox' checked="checked" name='addAuthId' value='${authSub.id}'/>${authSub.authName}
												<#else>
													<input type='checkbox' name='addAuthId' value='${authSub.id}'/>${authSub.authName}
												</#if>
												<input type='hidden' name='delAuthId' value='${authSub.id}'/>
											</#if>
										</#list>
									</td>
								</tr>
								<input type='hidden' name='delAuthId' value='${auth.id}'/>
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