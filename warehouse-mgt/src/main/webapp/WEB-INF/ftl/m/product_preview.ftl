<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<meta name="apple-mobile-web-app-capable" content="yes">
<title>商品预览</title>
<link type="text/css" rel="stylesheet" href="${BASEPATH}css/m/product.css" />
<script src="${BASEPATH}plugin/dwz-ria/js/jquery-1.7.2.js" type="text/javascript"></script>
</head>
<style>
.p_preview{
	width:100%;
	margin:5px 0;
	text-align: left;
}
.p_preview .p_preview_l{
	height:26px;
	line-height:26px;
	margin:5px 20px;
}
.p_preview .p_preview_l input{
	height:20px;
	width:50%;
}
.p_preview .p_preview_l span{
	display: inline-block;
	vertical-align: top;
}
.p_preview .qty_list{
	margin:2px 20px;
}
.p_preview .qty_list table{
	border:1px solid #cdcdcd; 
	border-collapse: collapse;
}
.p_preview .qty_list th,td{
	border-bottom:1px solid #cdcdcd; 
	border-right:1px solid #cdcdcd; 
}
.button_div{
	height:30px;
	line-height:30px;
	padding:20px 0 ;
	width:100%;
}
.button_div button{
	padding: 5px 15px;
	cursor: pointer;
}
</style>
<script>

function openPrice(){
	var v = $("#opentalbelist").attr("value");
	if(v == 0) {
	   $("#opentalbelist").attr("value",1).html("隐藏进价");
	   $("#price_talbe").show();
	} else {
		 $("#opentalbelist").attr("value",0).html("查看进价");
	   $("#price_talbe").hide();
	}
}

function openQty(){
	var v = $("#openqtytalbelist").attr("value");
	if(v == 0) {
	   $("#openqtytalbelist").attr("value",1).html("隐藏库存");
	   $("#qty_talbe").show();
	} else {
		 $("#openqtytalbelist").attr("value",0).html("查看库存");
	   $("#qty_talbe").hide();
	}
}

function clickSubmit(url) {
	window.location.href = url;
}
</script>
<body>
	<div class="p_preview">
		<div class="p_preview_l" style="height:80px;line-height:80px;">
			<span style="text-align:right;height:80px;">图片：</span>
			<span><img style="height:80px" src="${IMGBASEPATH}${product.picUrl!''}_80x80.${product.picType!'jpg'}"/></span>
		</div>
		<div class="p_preview_l">
			<label style="text-align:right">商品名称：</label>${product.name!''}
		</div>
		<div class="p_preview_l">
			<label style="text-align:right">国际条码：</label>${product.code!''}
		</div>
		<div class="p_preview_l">
			<label style="text-align:right">内部编码：</label>${product.sku!''}
		</div>
		<div class="p_preview_l">
			<label style="text-align:right">品牌：</label>
				${brands["${product.brandId!''}"]}
		</div>
		<div class="p_preview_l">
			<label style="text-align:right">单位：</label>
			${unitsOptions["${product.unitId!''}"]}
		</div>
		<div class="p_preview_l">
			<label style="text-align:right">商品规格：</label>
			${productSpecificationss["${product.specificationId1!''}"]}>>>
			<#if product.specificationId2 ??>
				${productSpecificationss["${product.specificationId2!''}"]}
			</#if>
		</div>
		<div class="p_preview_l">
			<label style="text-align:right">商品分类：</label>
			${productClassifications["${product.classificationId1!''}"]}>>>
			<#if product.classificationId2 ??>
				${productClassifications["${product.classificationId2!''}"]}
			</#if>
		</div>
		<div class="p_preview_l">
			<label style="text-align:right">临期警示：</label>${product.warning!'0'}天
		</div>
		<div class="p_preview_l">
			<label style="text-align:right">供应商：</label>
			${suppliers["${product.supplierId!''}"]}
		</div>
		<div style="margin:2px 20px;min-height:22px;">
			<label style="text-align:right">进价：</label>
			<a href="#" onclick="openPrice()" id="opentalbelist" value="0">查看进价 </a></br>
		</div>
		
		<div class="qty_list" style="display:none" id="price_talbe">
			<table class="table" width="100%">
				<thead>
					<tr>
						<th width="150">入库时间</th>
						<th width="150">入库数量</th>
						<th width="150">商品单价</th>
					</tr>
				</thead>
				<tbody>
					<#if productPrices ??>
						<#list productPrices as pp>
							<tr target="id" >
								<td>${pp.inDate!''}</td>
								<td>
									${pp.inQty}
								</td>
								<td>
									${pp.price}
								</td>
							</tr>
						</#list>
					</#if>
				</tbody>
			</table>
		</div>
		
		<div class="p_preview_l">
			<label style="text-align:right">备注：</label>${product.remark!''}
		</div>
		
		<div style="margin:2px 20px;min-height:22px;">
			<label style="text-align:right">库存：</label>
			<a href="#" onclick="openQty()" id="openqtytalbelist" value="0">查看库存 </a></br>
		</div>
		
		<div class="qty_list" style="display:none" id="qty_talbe">
			<table width="100%">
				<thead>
					<tr>
						<th width="150">存放位置</th>
						<th width="150">到期日期</th>
						<th width="150">库存</th>
					</tr>
				</thead>
				<tbody>
					<#if inventorys ??>
						<#list inventorys as inventory>
							<tr target="id">
								<td>${warehouses["${inventory.warehouseCode!''}"]}</td>
								<td>
									${inventory.maturityDate!''}
								</td>
								<td>
									${inventory.qty!''}
								</td>
							</tr>
						</#list>
					</#if>
					<tr>
						<td colspan="2" style="text-align: right;">合计：</td>
						<td>${qty}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="button_div">
		<button type="button" onclick="clickSubmit('${DOMAIN}product/productIn?productId=${product.id}')">入库</button>
    	<button type="button" onclick="clickSubmit('${DOMAIN}product/productOut?productId=${product.id}')">出库</button>
    	<button type="button" onclick="clickSubmit('${DOMAIN}product/list')">商品列表</button>
    </div>
</body>
</html>

