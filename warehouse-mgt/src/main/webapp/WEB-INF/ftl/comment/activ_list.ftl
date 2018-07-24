<form id="pagerForm" method="post" action="${DOMAIN}comment/activ-list">
	<input type="hidden" name="pageNum" value="${pageParam.pageNo}" />
	<input type="hidden" name="numPerPage" value="${pageParam.pageSize}" />
	<input type="hidden" name="name" value="${activParam.name!''}" />
	<input type="hidden" name="title" value="${activParam.title!''}" />
	<#if userId == 1>
	<input type="hidden" name="userId" value="${activParam.userId!''}" />
	</#if>
</form>
<script>
function openPic(obj) {

	var left = $(obj).offset().left+$(obj).width();
	var top = $(obj).offset().top;
	if((top-100) > 150 && (top-100) < 200 ) {
		top = top-80;
	} else if((top-100) > 260 && (top-100) < 350) {
		top = top - 160;
	} else if((top-100) > 350){
		top = top - 220;
	}
	var html = '<div id="atpicImage" style="left:'+left+'px; top:'+top+'px; z-index:99999;width:300px;height:300px;border:1px solid #e6e6e6;position: absolute;background:#e3ee34">'
	html += '<div  style="height:100%;width:100%;background: url('+$(obj).attr("picUrl")+')  no-repeat center center;background-size:contain;"></div>';
	html += '</div>';
	$("body").append(html);
}
function closePic(){
	$("#atpicImage").remove();
}
</script>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${DOMAIN}comment/activ-list" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
				      活动名称：<input type="text" name="name" value="${activParam.name!''}"/>
				</td>
				<td>
				          活动标题：<input type="text" name="title" value="${activParam.title!''}"/>
				</td>
				<#if userId == 1>
					<td>
					          创建人：
					          <select name="userId" style="min-width:120px;" >
								<#list users?keys as key> 
									<#if activParam.userId ?? && activParam.userId == key?eval>
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
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${DOMAIN}comment/activ-add" mask="true" target="navTab"><span>添加</span></a></li>
			<li><a class="edit" href="${DOMAIN}comment/activ-update?activId={id}" target="navTab" mask="true" warn="请选择活动"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="137">
		<thead>
			<tr>
				<th width="180">基本信息</th>
				<th width="80">主页背景图</th>
				<th width="80">成功背景图</th>
				<th width="80">流程图</th>
				<th width="180">活动介绍</th>
				<th width="180">活动地址</th>
			</tr>
		</thead>
		<tbody>
			<#if pageParam ?? && pageParam.getPageData() ??>
				<#list pageParam.getPageData() as activ>
					<tr target="id" rel="${activ.id}">
						<td>
							活动名称：${activ.name!''}<br>
							活动标题：${activ.title!''}<br>
							开始时间：${activ.startTime!''}<br>
							结束时间：${activ.endTime!''}<br>
							<#if userId == 1>
							创建人：${users["${activ.userId!''}"]}<br>
							</#if>
							创建时间：${activ.createTime!''}
						</td>
						<td>
							<div  onmouseover="openPic(this)" picUrl="${IMGBASEPATH}${activ.bgPath!''}"  onmouseout="closePic()" style="float:left;height:80px;width:80px;background: url('${IMGBASEPATH}${activ.bgPath!''}')  no-repeat center center;background-size:contain ;" ></div>
						</td>
						<td>
							<div  onmouseover="openPic(this)" picUrl="${IMGBASEPATH}${activ.suBgPath!''}"  onmouseout="closePic()" style="float:left;height:80px;width:80px;background: url('${IMGBASEPATH}${activ.suBgPath!''}')  no-repeat center center;background-size:contain ;" ></div>
						</td>
						<td>
							<div  onmouseover="openPic(this)" picUrl="${IMGBASEPATH}${activ.workflowPath!''}"  onmouseout="closePic()" style="float:left;height:80px;width:80px;background: url('${IMGBASEPATH}${activ.workflowPath!''}')  no-repeat center center;background-size:contain ;" ></div>
						</td>
						<td>
							${activ.description!''}
						</td>
						<td>
							${wxDoMain}${activ.wxLink!''}
						</td>
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
