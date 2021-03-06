<form id="pagerForm" method="post" action="${DOMAIN}comment/wxuser-list">
	<input type="hidden" name="pageNum" value="${pageParam.pageNo}" />
	<input type="hidden" name="numPerPage" value="${pageParam.pageSize}" />
	<input type="hidden" name="nickname" value="${wxUserParam.nickname!''}" />
	<#if userId == 1>
	<input type="hidden" name="userId" value="${wxUserParam.userId!''}" />
	</#if>
</form>
<script>
function openWxPic(obj) {

	var left = $(obj).offset().left+$(obj).width();
	var top = $(obj).offset().top;
	if((top-100) > 150 && (top-100) < 200 ) {
		top = top-80;
	} else if((top-100) > 260 && (top-100) < 350) {
		top = top - 160;
	} else if((top-100) > 350){
		top = top - 220;
	}
	var html = '<div id="wxpicImage" style="left:'+left+'px; top:'+top+'px; z-index:99999;width:300px;height:300px;border:1px solid #e6e6e6;position: absolute;background:#000">'
	html += '<div  style="height:100%;width:100%;background: url('+$(obj).attr("picUrl")+')  no-repeat center center;background-size:contain;"></div>';
	html += '</div>';
	$("body").append(html);
}
function closeWxPic(){
	$("#wxpicImage").remove();
}
</script>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${DOMAIN}comment/wxuser-list" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
				       用户名称：<input type="text" name="nickname" value="${wxUserParam.nickname!''}"/>
				</td>
				<#if userId == 1>
					<td>
					          创建人：
				          <select name="userId" style="min-width:120px;" >
							<#list users?keys as key> 
								<#if wxUserParam.userId ?? && wxUserParam.userId == key?eval>
									<option selected="selected" value="${key}">${users[key]}	</option>	
								<#else>
									<option value="${key}">${users[key]}</option>	
								</#if>		
							</#list>
						  </select>
					</td>				
				</#if>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="137">
		<thead>
			<tr>
				<th width="160">用户名称</th>
				<th width="60">性别</th>
				<th width="100">国家</th>
				<th width="160">省</th>
				<th width="160">城市</th>
				<th width="160">语言</th>
				<th width="160">头像</th>
				<th width="160">关注时间</th>
				<#if userId == 1>
					<th width="200">创建人</th>
				</#if>
			</tr>
		</thead>
		<tbody>
			<#if pageParam ?? && pageParam.getPageData() ??>
				<#list pageParam.getPageData() as wxUser>
					<tr target="id" rel="${wxUser.id}">
						<td>
							${wxUser.nickname!''}
						</td>
						<td>
							<#if wxUser.sex !=0>
							${sexs["${wxUser.sex!''}"]}
							</#if>
						</td>
						<td>
							${wxUser.country!''}
						</td>
						<td>
							${wxUser.province!''}
						</td>
						<td>
							${wxUser.city!''}
						</td>
						<td>
							${wxUser.language!''}
						</td>
						<td>
							<div  onmouseover="openWxPic(this)" picUrl="${wxUser.headimgurl}"  onmouseout="closeWxPic()" style="float:left;height:80px;width:80px;background: url('${wxUser.headimgurl}')  no-repeat center center;background-size:contain ;" ></div>
						</td>
						<td>
							${wxUser.logintime!''}
						</td>
						<#if userId == 1>
							<td>
								${users["${wxUser.userId!''}"]}
							</td>
						</#if>
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
					<#if pageParam.pageSize == pa>
						<option value="${pa}" selected="selected">${pa}</option>
					<#elseif pageParam.pageSize != pa>
						<option value="${pa}">${pa}</option>
					</#if>
				</#list>
			</select>
			<span>条，共${pageParam.dataTotal}条，</span>
			<span>共${pageParam.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${pageParam.dataTotal?c}" numPerPage="${pageParam.pageSize}" pageNumShown="10" currentPage="${pageParam.pageNo}"></div>
	</div>
</div>
