<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${DOMAIN}role/roleAdd" rel="roleadd" target="navTab"><span>添加</span></a></li>
			<li><a class='edit'  href="${DOMAIN}role/roleUpdate?roleId={id}" rel="roleupdate" target='navTab'><span>编辑</span></a></li>
			<li><a class="delete" href="${DOMAIN}role/roledelete?roleId={id}" target="ajaxTodo" callback="navTabAjaxDone" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class='icon' target='navTab' href="${DOMAIN}role/roleDetail?roleId={id}"><span>详情</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="76">
		<thead>
		    <tr>
		         <th>序号</th>
				 <th>角色</th>
		    </tr>
		</thead>
    <tbody>
    	<#if roleListParamList ?? && roleListParamList.getPageData() ??>
			<#list roleListParamList.getPageData() as role>
				<tr target="id" rel="${role.id}">
					<td>${role.id}</td>
					<td>${role.roleName}</td>
				</tr>
			</#list>
		</#if>
    </tbody>
      
    </table>
    <div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value,pageNum:1})">
				<#assign seqPage = [20,40,60,80,100]>
				<#list seqPage as pa>
					<#if roleListParamList.pageSize == pa>
						<option value="${pa}" selected="selected">${pa}</option>
					<#elseif roleListParamList.pageSize != pa>
						<option value="${pa}">${pa}</option>
					</#if>
				</#list>
			</select>
			<span>条，共${roleListParamList.dataTotal}条，</span>
			<span>共${roleListParamList.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${roleListParamList.dataTotal?c}" numPerPage="${roleListParamList.pageSize}" pageNumShown="10" currentPage="${roleListParamList.pageNo}"></div>
	</div>
</div>