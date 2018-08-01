<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<meta name="apple-mobile-web-app-capable" content="yes">
<title>商品出库</title>
<link type="text/css" rel="stylesheet" href="${BASEPATH}css/m/product.css" />
<script src="${BASEPATH}plugin/laydate/laydate.js" type="text/javascript"></script>
<script src="${BASEPATH}plugin/dwz-ria/js/jquery-2.1.4.js" type="text/javascript"></script>
</head>
<style>
.p_preview{
	width:100%;
	margin:5px 0;
	text-align: left;
}
.p_preview .p_preview_l{
	height:32px;
	line-height:32px;
	margin:5px 20px;
}
.p_preview .p_preview_l input{
	height:20px;
	width:50%;
	-webkit-appearance: none;
}
.p_preview .p_preview_l select{
	height:20px;
	width:50%;
	appearance: none;
	-webkit-appearance: none;
	-moz-appearance: none;
}
.button_div{
	height:30px;
	line-height:30px;
	width:100%;
}
.button_div button{
	padding: 5px 15px;
	cursor: pointer;
}
</style>
<script>
function clickSubmit(url) {
	window.location.href = url;
}

function submitOutProduct(){
	var product_out_wcode = $("#product_out_wcode").val();
	if(product_out_wcode == "") {
		alert("请选择仓库");
		return false;
	}
	var product_out_bacthNo = $("#product_out_bacthNo").val();
	if(product_out_bacthNo == "-1") {
		alert("请选择批次");
		return false;
	}
	
	var inoutRecordType = $("#inoutRecordType").val();
	if(inoutRecordType == "-1") {
		alert("请选择类型");
		return false;
	}
	var qty = $("#qty").val();
	if(qty == "") {
		alert("请输入数量");
		return false;
	}
	$.ajax({
		type:'POST',
		url:$("#productOutForm").attr("action"),
		data:$("#productOutForm").serializeArray(),
		dataType:"json",
		cache: false,
		success: function(d){
			if(d.statusCode == 200) {
				alert("出库成功");
			} else {
				alert(d.message);
			}
		},
		error: function(d){
		
		}
	});	
}

</script>
<body>
	<form method="post" action="${DOMAIN}product/productOutSubmit" id="productOutForm">
		<input type="hidden" name="productId" value="${product.id}"/>
		<div class="p_preview">
			<div class="p_preview_l">
				<label style="text-align:right">商品名称：</label>${product.name!''}
			</div>
			<div class="p_preview_l">
				<label style="text-align:right">当前库存：</label>${qty!''}
			</div>
			<div class="p_preview_l">
				<label style="text-align:right">出库位置：</label>
				<select name="warehouseCode" id="product_out_wcode" style="min-width:120px;" class="required">
					<option value="-1"></option>
					<#list warehouses?keys as key> 
						<option value="${key}">${warehouses[key]}</option>					
					</#list>
				</select>
			</div>
			<div class="p_preview_l">
				<label style="text-align:right">出库批次：</label>
				<select name="batchNo" id="product_out_bacthNo" style="width:120px;"  class="required">
					<option value="-1"></option>
					<optgroup label="">
					<#list qtys?keys as key> 
						<option value="${key}">${qtys[key]}</option>					
					</#list>
					</optgroup>
				</select>
			</div>
			<div class="p_preview_l">
				<label style="text-align:right">出库类型：</label>
				<select name="inoutRecordType" id="inoutRecordType" style="min-width:120px;" class="required">
					<option value="-1"></option>
					<#list recordTypes?keys as key> 
						<option value="${key}">${recordTypes[key]}</option>					
					</#list>
				</select>
			</div>
			<div class="p_preview_l">
				<label style="text-align:right">出库数量：</label>
				<input name="qty" id="qty" value="" style="width:60%" class="required" alt="请输入数量！" />
			</div>
			<div class="p_preview_l" style="height:60px;line-height:60px;">
				<label style="text-align:right">备注：</label>
				<textarea name="remark" alt="出库备注" cols="15" rows="2"></textarea>
			</div>
		</div>
		<div class="button_div">
			<button type="button" onclick="submitOutProduct()">保存</button>
	    	<button type="button" onclick="clickSubmit('${DOMAIN}product/productPreview?productId=${product.id}')">取消</button>
	    	<button type="button" onclick="clickSubmit('${DOMAIN}product/list')">商品列表</button>
	    </div>
	</form>
</body>
</html>   

