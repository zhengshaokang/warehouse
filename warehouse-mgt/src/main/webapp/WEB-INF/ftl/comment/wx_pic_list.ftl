<form id="pagerForm" method="post" action="${DOMAIN}comment/wxpic-list">
	<input type="hidden" name="pageNum" value="${pageParam.pageNo}" />
	<input type="hidden" name="numPerPage" value="${pageParam.pageSize}" />
	<input type="hidden" name="orderNo" value="${wxPicParam.orderNo!''}" />
	<input type="hidden" name="payStatus" value="${wxPicParam.payStatus!''}" />
	<#if userId == 1>
	<input type="hidden" name="userId" value="${wxPicParam.userId!''}" />
	</#if>
</form>
<script>
function openWxPic(obj) {

	var left = $(obj).offset().left+$(obj).width();
	var top = $(obj).offset().top;
	if((top-100) > 150 && (top-100) < 200 ) {
		top = top-80;
	} else if((top-100) > 260 && (top-100) < 400) {
		top = top - 160;
	} else if((top-100) > 400){
		top = top - 220;
	}
	var html = '<div id="wxpicImage" style="left:'+left+'px; top:'+top+'px; z-index:99999;width:400px;height:400px;border:1px solid #e6e6e6;position: absolute;background:#000">'
	html += '<div  style="height:100%;width:100%;background: url('+$(obj).attr("picUrl")+')  no-repeat center center;background-size:contain;"></div>';
	html += '</div>';
	$("body").append(html);
}
function closeWxPic(){
	$("#wxpicImage").remove();
}

function validateOrderList(orderNo){
    $.ajax({
        type:"post",
        url:"comment/validateOrder",
        data:{orderNo:orderNo},
        dataType:"json",
        success:function(data){
            var html='<img src="${BASEPATH}/img/comment/exc.gif" style="height:7px;width:7px;" />';
            if(data.id != -1){
            	 html='<img src="${BASEPATH}/img/comment/exg_yes.gif" style="height:7px;width:7px;"/>';
            	 $("#amount_"+orderNo).text(data.orderAmount);
            	 
            	 var isJoin = "";
            	 <#if yesno ??>
					<#list yesno?keys as key>	
						if(${key} == data.isJoin) {
							isJoin = '${yesno[key]}';
						}
					</#list>
				 </#if>
            	  var shop = "";
            	 <#if shops ??>
					<#list shops?keys as key>	
						if(${key} == data.shopId) {
							shop = '${shops[key]}';
						}
					</#list>
				 </#if>
            	 $("#isJoin_"+orderNo).text(isJoin);
            	 $("#shop_"+orderNo).text(shop);
            } else {
            	html='<img src="${BASEPATH}/img/comment/exc.gif" style="height:7px;width:7px;" />';
            }
            $("#orderNo_"+orderNo).find("div").append(html);
        }
    });
}
</script>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${DOMAIN}comment/wxpic-list" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
				       订单号：<input type="text" name="orderNo" value="${wxPicParam.orderNo!''}"/>
				</td>
				<td>
					    审核状态：
			          <select name="payStatus" style="min-width:120px;" >
			          	<option value="-1">请选择</option>
						<#list payStatus?keys as key> 
							<#if wxPicParam.payStatus ?? && wxPicParam.payStatus == key?eval>
								<option selected="selected" value="${key}">${payStatus[key]}	</option>	
							<#else>
								<option value="${key}">${payStatus[key]}</option>	
							</#if>		
						</#list>
					  </select>
				</td>	
				<#if userId == 1>
					<td>
					          创建人：
				          <select name="userId" style="min-width:120px;" >
							<#list users?keys as key> 
								<#if wxPicParam.userId ?? && wxPicParam.userId == key?eval>
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
<style type="text/css">
.figure-list{
  margin: 0;
  padding: 0;
}
.figure-list:after{
  content: "";
  display: block;
  clear: both;
  height: 0;
  overflow: hidden;
  visibility: hidden;
}
.figure-list li{
  list-style: none;
  float: left;
  width: 50px;
  margin: 0 2% 2% 0;
}
.figure-list figure{
  position: relative;
  width: 100%;
  height: 0;
  overflow: hidden;
  margin: 0;
  padding-bottom: 100%; /* 关键就在这里 */
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}
.figure-list figure a{
  display: block;
  position: absolute;
  width: 100%;
  top: 0;
  bottom: 0;
}
</style>


<div class="pageContent">
	<script>
	    var idArrays = [];
	</script>
	<table class="table" width="100%" layoutH="137">
		<thead>
			<tr align=center>
				<th width="100">参与人微信</th>
				<th width="100">所属店铺</th>
				<th width="100">购物订单号</th>
				<th width="60">订单金额</th>
				<th width="300">截图</th>
				<th width="100">上传时间</th>
				<th width="60">是否参与</th>
				<th width="60">审核状态</th>
				<th width="80">操作</th>
			</tr>
		</thead>
		<tbody>
			<#if pageParam ?? && pageParam.getPageData() ??>
				<#list pageParam.getPageData() as wxPic>
					<tr target="id" rel="${wxPic.id}">
						<td>${wxPic.nickname!''}</td>
							
						<td id="shop_${wxPic.orderNo}">
							
						</td>
						
						<td id="orderNo_${wxPic.orderNo}">
							${wxPic.orderNo!''}
						</td>
						
						<td id="amount_${wxPic.orderNo}"></td>
						<td>
							<ul class="figure-list">
<#list wxPic.picUrl?split(",") as pic>
							
								  <li>
    <figure onmouseover="openWxPic(this)" picUrl="${IMGBASEPATH}${pic}"  onmouseout="closeWxPic()"  style="background-image:url(${IMGBASEPATH}${pic})">
      <a href="#"></a>
    </figure>
  </li>
						    </#list></ul>
						</td>
						<td align=center>
							${wxPic.uploadTime!''}
						</td>
						<td align=center id="isJoin_${wxPic.orderNo}">
						
						</td>
						<td align=center>
							${payStatus["${wxPic.payStatus!''}"]}
						</td>
	
						<td>
							<a class="delete" style="padding:5px;" href="${DOMAIN}comment/payordercomment?wxPicId=${wxPic.id}" target="ajaxTodo" callback="navTabAjaxDone" title="确定要返现吗?"><span>返现</span></a><br>
							<a class="delete"  style="padding:5px;" href="${DOMAIN}comment/payorderpass?wxPicId=${wxPic.id}" callback="navTabAjaxDone" title="确定不通过吗?" target="ajaxTodo">不通过</a>
						</td>
						<script>
						    idArrays.push('${wxPic.orderNo}');
						</script>
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
	<script>
	    $.each(idArrays, function (i, v) {
	        validateOrderList(v);
	    })
	</script>
</div>
