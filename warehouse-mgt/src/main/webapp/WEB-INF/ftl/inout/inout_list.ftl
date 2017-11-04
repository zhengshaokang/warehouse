<form id="pagerForm" method="post" action="${DOMAIN}inout/list">
	<input type="hidden" name="pageNum" value="${pageParam.pageNo}" />
	<input type="hidden" name="numPerPage" value="${pageParam.pageSize}" />
	<input type="hidden" name="sku" value="${inoutRecordParam.sku!''}" />
	<input type="hidden" name="inoutType" value="${inoutRecordParam.inoutType!''}" />
	<input type="hidden" name="recordType" value="${inoutRecordParam.recordType!''}" />
	<input type="hidden" name="operateTimeStart" value="${inoutRecordParam.operateTimeStart!''}" />
	<input type="hidden" name="operateTimeEnd" value="${inoutRecordParam.operateTimeEnd!''}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${DOMAIN}inout/list" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
				    SKU：<input type="text" name="sku" value="${inoutRecordParam.sku!''}"/>
				</td>
				<td>
				 	类型：
			 	    <select class="" name="inoutType">
			 	    	<#list inoutTypes?keys as type> 
			 	    		<#if inoutRecordParam.inoutType?? && inoutRecordParam.inoutType?c  == type>
			 	    			<option value='${type}'   selected='selected'>${inoutTypes[type]}</option>
			 	    		<#else>
			 	    			<option value='${type}'  >${inoutTypes[type]}</option>
			 	    		</#if>
			 	    	</#list>
					</select>
				</td>
				<td>
					   记录类型：
				    <select class="" name="recordType">
		 	    	<#list inoutRecordTypes?keys as type> 
			 	    		<#if inoutRecordParam.recordType?? && inoutRecordParam.recordType?c  == type>
			 	    			<option value='${type}'   selected='selected'>${inoutRecordTypes[type]}</option>
			 	    		<#else>
			 	    			<option value='${type}'  >${inoutRecordTypes[type]}</option>
			 	    		</#if>
			 	    	</#list>
					</select>
				</td>
			</tr>
			<tr>
				<td>
				        开始时间：<input type="text" maxDate="{%y}-%M-{%d}" name="operateTimeStart" value="${inoutRecordParam.operateTimeStart!''}" class="date" dateFmt="yyyy-MM-dd hh:ss:mm"/>
				</td>
				<td>
				        结束时间：<input type="text" maxDate="{%y}-%M-{%d}" name="operateTimeEnd" value="${inoutRecordParam.operateTimeEnd!''}" class="date" dateFmt="yyyy-MM-dd  hh:ss:mm"/>
				</td>
				<td>
				  
				</td>
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
				<th width="100">类型</th>
				<th width="230">商品信息</th>
				<th width="150">变更前库存</th>
				<th width="150">变更数量</th>
				<th width="140">变更后库存</th>
				<th width="80">记录类型</th>
				<th width="230">操作信息</th>
			</tr>
		</thead>
		<tbody>
			<#if pageParam ?? && pageParam.getPageData() ??>
				<#list pageParam.getPageData() as inout>
					<tr target="id" rel="${inout.id}" style="height:40px;">
						<td>
							${inoutTypes["${inout.inoutType!''}"]}
						</td>
						<td>
							SKU：${inout.sku!''}</br>
							商品名称：${inout.name!''}
						</td>
						<td>
							${inout.beforeInventory!''}
						</td>
						<td>
							${inout.changeInventory!''}
						</td>
						<td>
							${inout.afterInventory!''}
						</td>
						<td>${inoutRecordTypes["${inout.recordType!''}"]}</td>
						<td>
							操作人：${users["${inout.operator!''}"]}</br>
							操作时间：${inout.operateTime!''}
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
