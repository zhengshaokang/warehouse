<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<meta name="apple-mobile-web-app-capable" content="yes">
<title>商品入库</title>
<link type="text/css" rel="stylesheet" href="${BASEPATH}css/m/product.css" />
<script src="${BASEPATH}plugin/laydate/laydate.js" type="text/javascript"></script>
<script src="${BASEPATH}plugin/dwz-ria/js/jquery-1.7.2.js" type="text/javascript"></script>
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
}
.p_preview .p_preview_l select{
	height:20px;
	width:50%;
	-webkit-appearance: none;
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
//	验证价格输入
function checkNum(obj) {
     var re = /^-?[0-9]*(\.\d*)?$|^-?d^(\.\d*)?$/;
     if (!re.test(obj.value)) {
	      if(isNaN(obj.value)){  
		      obj.value="";
		      obj.focus();
		      return false;
		  }
     }
}
//验证天数是否为整数
function validateInteger(obj) {
	 var re = /^-?\\d+$/;
     if (!re.test(obj.value)) {
	      if(isNaN(obj.value)){  
		      obj.value="";
		      obj.focus();
		      return false;
		  }
     }
}
function clickSubmit(url) {
	window.location.href = url;
}
function submitInProduct(){
	var qty = $("#qty").val();
	if(qty == "") {
		alert("请输入数量");
		return false;
	}
	var price = $("#price").val();
	if(price == "") {
		alert("请输入进价");
		return false;
	}
	var warehouseCode = $("#warehouseCode").val();
	if(warehouseCode == "-1") {
		alert("请选择仓库");
		return false;
	}
	
	$.ajax({
		type:'POST',
		url:$("#productInForm").attr("action"),
		data:$("#productInForm").serializeArray(),
		dataType:"json",
		cache: false,
		success: function(d){
			if(d.statusCode == 200) {
				alert("入库成功");
			} else {
				alert(d.message);
			}
		},
		error: function(d){
		
		}
	});	
}

laydate.render({
  elem: '#maturityDate'
  ,min: 0
});
laydate.render({
  elem: '#inWarehouseDate'
  ,max: 0
});
</script>
<body>
	<form method="post" id="productInForm" action="${DOMAIN}product/productInSubmit">
		<input type="hidden" name="productId" value="${product.id}"/>
		<div class="p_preview">
			<div class="p_preview_l">
				<label style="text-align:right">商品名称：</label>${product.name!''}
			</div>
			<div class="p_preview_l">
				<label style="text-align:right">当前库存：</label> ${qty}
			</div>
			<div class="p_preview_l">
				<label style="text-align:right">入库数量：</label>
				<input name="qty" id="qty" value="" style="width:60%" alt="请输入数量！" />
			</div>
			<div class="p_preview_l">
				<label style="text-align:right">到期日期：</label>
				<input type="text" id="maturityDate" name="maturityDate" style="width:60%" value="" alt="请输入到期日期！"/>
			</div>
			<div class="p_preview_l">
				<label style="text-align:right">本次进价：</label>
				<input type="text" id="price" name="price" value="" style="width:60%" alt="请输入进价！"/>
			</div>
			<div class="p_preview_l">
				<label style="text-align:right">入库日期：</label>
				<input type="text" name="inWarehouseDate" style="width:60%" value="${currdate!''}" alt="请输入入库日期！"  id="inWarehouseDate"/>
			</div>
			<div class="p_preview_l">
				<label style="text-align:right">存放位置：</label>
				<select name="warehouseCode" id="warehouseCode" style="min-width:120px;" class="required">
					<option value="-1"></option>
					<#list warehouses?keys as key> 
						<option value="${key}">${warehouses[key]}</option>					
					</#list>
				</select>
			</div>
			<div class="p_preview_l" style="height:60px;line-height:60px;">
				<label style="text-align:right">备注：</label>
				<textarea name="remark" alt="入库备注" cols="15" rows="2"></textarea>
			</div>
		</div>
		<div class="button_div">
			<button type="button" onclick="submitInProduct()">保存</button>
	    	<button type="button" onclick="clickSubmit('${DOMAIN}product/productPreview?productId=${product.id}')">取消</button>
	    	<button type="button" onclick="clickSubmit('${DOMAIN}product/list')">商品列表</button>
	    </div>
	</form>
</body>
</html>  

