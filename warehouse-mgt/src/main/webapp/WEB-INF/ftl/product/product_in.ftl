<style>
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

function validateCallback1(form, callback) {
	var $form = $(form);

	if (!$form.valid()) {
		return false;
	}
	var productattrs = $(".product_attr_row"); 
	var flag= false;
	var attrsStr = "";
	$.each(productattrs,function(i,d){
		var s = $(d).find("select").val();
		var i = $(d).find("input").val();
		if(s == "" || i == "") {
			flag = true;
			return false;
		}
		attrsStr += s+"|"+i +",";
	});
	if(attrsStr == "") {
		alertMsg.error("商品属性必须有一个！");
		return false;
	}
	if(flag){
		alertMsg.error("商品属性请填写完整！");
		return false;
	}
	
	//验证图片是否上传
	if(!validProductsPic()){
		return false;
	}
    $("#productpic").val(getAddProductPicDatas());
	$("#productattrs").val(attrsStr.substring(0,attrsStr.length-1));
	var _submitFn = function(){
		$.ajax({
			type: form.method || 'POST',
			url:$form.attr("action"),
			data:$form.serializeArray(),
			dataType:"json",
			cache: false,
			success: callback || DWZ.ajaxDone,
			error: DWZ.ajaxError
		});
	}
	_submitFn();
	return false;
}


</script>
 <div class="pageContent">
	<form method="post" action="${DOMAIN}product/productInSubmit" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="productId" value="${product.id}"/>
		<input type="hidden" name="recordType" value="${recordType}"/>
		<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label style="text-align:right">商品名称：</label>${product.name!''}
			</div>
			<div class="unit">
				<label style="text-align:right">SKU：</label>${product.sku!''}
			</div>
			<div class="unit">
				<label style="text-align:right">入库数量：</label>
				<input name="qty" value="" size="30"  class="required" alt="请输入数量！" />
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
  

