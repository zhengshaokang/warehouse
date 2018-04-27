<form id="pagerForm" method="post" action="${DOMAIN}warehouse/inoutlist">
	<input type="hidden" name="pageNum" value="${pageParam.pageNo}" />
	<input type="hidden" name="numPerPage" value="${pageParam.pageSize}" />
	<input type="hidden" name="sku" value="${inoutRecordParam.sku!''}" />
	<input type="hidden" name="inoutType" value="${inoutRecordParam.inoutType!''}" />
	<input type="hidden" name="recordType" value="${inoutRecordParam.recordType!''}" />
	<input type="hidden" name="createTime" value="${inoutRecordParam.createTime!''}" />
	<input type="hidden" name="operateTimeStart" value="${inoutRecordParam.operateTimeStart!''}" />
	<input type="hidden" name="operateTimeEnd" value="${inoutRecordParam.operateTimeEnd!''}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${DOMAIN}warehouse/inoutlist" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
				    SKU：<input type="text" name="sku" value="${inoutRecordParam.sku!''}"/>
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
				<td>
				                出入库日期：<input type="text" maxDate="{%y}-%M-{%d}" name="createTime" value="${inoutRecordParam.createTime!''}"   class="date" dateFmt="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<td>
				        操作开始时间：<input type="text" maxDate="{%y}-%M-{%d}" name="operateTimeStart" value="${inoutRecordParam.operateTimeStart!''}" class="date" dateFmt="yyyy-MM-dd hh:ss:mm"/>
				</td>
				<td>
				        操作结束时间：<input type="text" maxDate="{%y}-%M-{%d}" name="operateTimeEnd" value="${inoutRecordParam.operateTimeEnd!''}" class="date" dateFmt="yyyy-MM-dd  hh:ss:mm"/>
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
				<th width="230">商品信息</th>
				<th width="80">仓库</th>
				<th width="80">变更前库存</th>
				<th width="80">变更数量</th>
				<th width="80">变更后库存</th>
				<th width="80">记录类型</th>
				<th width="80">出入库日期</th>
				<th width="130">备注</th>
				<th width="230">操作信息</th>
			</tr>
		</thead>
		<tbody>
			<#if pageParam ?? && pageParam.getPageData() ??>
				<#list pageParam.getPageData() as inout>
					<tr target="id" rel="${inout.id}" style="height:40px;">
						<td>
							商品名称：${inout.name!''}</br>
							国际条码：${inout.code!''}</br>
							内部编码：${inout.sku!''}</br>
							到期日期：${inout.maturityDate!''}
						</td>
						<td>
							${warehouses["${inout.warehouseCode!''}"]}
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
							${inout.createTime!''}
						</td>
						<td>
							${inout.remark!''}
						</td>
						<td>
							操作人：${users["${inout.operator!''}"]}</br>
							操作时间：${inout.operateTime!''}</br>
							批次号：${inout.bacthNo!''}
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
