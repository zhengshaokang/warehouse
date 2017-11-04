<style>
.product_attr{
	margin-left: 0; 
	padding-left: 0; 
	float:left;
	width:80%;
}
.product_attr input {
	border-style: solid;
    border-width: 1px;
    font-size: 12px;
    line-height: 15px;
    margin: 0;
    padding: 2px;
    background-color: #fff;
    border-color: #ed9b5f;
}
.product_attr_row {
	float:left;
	width:100%;
	padding:2px;
	height:22px;
}
.product_attr_d1 {
	width:80px;
	float:left;
	border:1px solid #d8bfbf;
	border-right:0;
	height:100%;
}
.product_attr_d1 select input {
	
}
.product_attr_d2 {
	width:200px;
	float:left;
	border:1px solid #d8bfbf;
	border-right:0;
	height:100%;
}
.product_attr_d3 {
	width:80px;
	float:left;
	border:1px solid #d8bfbf;
	height:100%;
	text-align:center;
}
.product_attr_d3 a {line-height:22px;cursor: pointer;}
.product_attr_d3 a:link {text-decoration:underline;} 
.product_attr_d3 a:active {text-decoration:none;} 
.product_attr_d3 a:visited {color:#d8bfbf;text-decoration:none;} 
.product_attr_d3 a:hover {
	color:#d8bfbf;text-decoration:none;
} 
</style>

<script>
$(function(){
	commoInitSwfu();
});
//--------------------------上传商品图片开始--------------------------
var swfu;
function commoInitSwfu() {
	var uploadUrl = domain + "fileupload/uploadpic";
	//在firefox、chrome下，上传不能保留登录信息，所以必须加上jsessionid
	var jsessionid = '${jsessionId}';
	if(jsessionid) {
		uploadUrl += "?jsessionid="+jsessionid;
	}
	swfu=new SWFUpload({
		upload_url : uploadUrl,
		flash_url : basepath + "plugin/swfupload/swfupload.swf",
		file_size_limit : "400 KB",
		file_types : "*.jpg;*.png;*.gif;*.jpeg;*.bmp",
		file_types_description : "Web Image Files",
		file_queue_limit : 0,
		custom_settings : {
			progressTarget : "fsUploadProgress",
			cancelButtonId : "btnCancel"
		},
		debug: false,
		
		button_image_url : basepath + "plugin/swfupload/button_notext.png",
		button_placeholder_id : "spanButtonPlaceHolder",
		button_text: "<span class='btnText'>上传图片</span>",
		button_width: 81,
		button_height: 24,
		button_text_top_padding: 2,
		button_text_left_padding: 20,
		button_text_style: '.btnText{color:#666666;}',
		button_cursor:SWFUpload.CURSOR.HAND,
		
		file_queued_handler : fileQueued,
		file_queue_error_handler : fileQueueError2,
		file_dialog_complete_handler : fileDialogComplete2,
		upload_start_handler : uploadStart2,
		upload_progress_handler : uploadProgress,
		upload_error_handler : uploadError2,
		upload_success_handler : uploadSuccess2,
		upload_complete_handler : uploadComplete,
		queue_complete_handler : queueComplete2
	});
};

function deletePic(obj){
	$(obj).closest("div").remove();
}

//货品图片上传开始
function fileQueueError2(file, errorCode, message) {
	try {
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setError();
		progress.toggleCancel(false);
		switch (errorCode) {
		case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
			progress.setStatus("图片不能超过4MB！");
			break;
		case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
			progress.setStatus("无法上载零字节的文件！");
			break;
		case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
			progress.setStatus("图片格式不正确！正确格式为（*.jpg;*.png;*.gif;*.jpeg;*.bmp）");
			break;
		default:
			if (file !== null) {
				progress.setStatus("图片不正确，不能上传！");
			}
			break;
		}
	} catch (ex) {
    }
}

function fileDialogComplete2(numFilesSelected, numFilesQueued) {
	try {
  		//初始化上传图片
		this.addPostParam("picType","0");
		this.addPostParam("sku",$("#update_product_sku").val());
		if (numFilesSelected > 0) {
			document.getElementById(this.customSettings.cancelButtonId).disabled = false;
		}
		this.startUpload();
	} catch (ex)  {
		
	}
}

function uploadStart2() {
	try {
		swfu.startUpload();
	} catch (ex)  {
        this.debug(ex);
	}
}

function uploadError2(file, errorCode, message) {
	try {
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setError();
		progress.toggleCancel(false);
		if(errorCode == "SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED") {
			progress.setStatus("上传的照片不能超过4M！");
			return;
		} else {
			progress.setStatus("上传失败！"+message + "---- "+errorCode+"----"+file);
			retutn;
		}
	} catch (ex) {
		
    }
}

function uploadSuccess2(file, serverData) {
	try {
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setComplete();
		progress.setStatus("上传成功");
		progress.toggleCancel(false);
		var json = eval("("+serverData+")");
		var img = '<div style="float:left"><img picUrl="'+json.returnPath+'" src= '+basepath+'file/'+json.returnPath+' width="50" height="50"/><a href="javascript:void(0)" onclick="deletePic(this)">X</a></div>';
		$("#updateProductPicDiv").append(img);
	} catch (ex) {	
	}
}

function queueComplete2() {
	try {
		$("#updateProductPicDiv").dragsort("destroy");
		$("#updateProductPicDiv").dragsort({ dragSelector: "div", dragBetween: true, placeHolderTemplate: "<div class='placeHolder' style='float:left'><img /><a></a></div>" });
	} catch (ex)  {
        this.debug(ex);
	}
}
//--------------------------上传商品图片结束-----------------------
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

var attrs = '${productAttrstr}';
//增加属性
function addproductattr() {
	var attr = eval('(' + attrs + ')');
	var html = "<div class='product_attr_row'><div class='product_attr_d1'><select name='type'><option value=''></option>";	
			$.each(attr,function(v,k){
				html += "<option value='"+v+"'>"+k+"</option>";		
			});
		html += "</select></div><div class='product_attr_d2' ><input alt='请输入库存！' size='20' value='' name='count'></div><div class='product_attr_d3'><a class='ddd' onclick='deleteproductattr(this)'>删除</a></div></div>";						
	$(".product_attr").append(html);						
}

function deleteproductattr(obj) {
   $(obj).parent().parent().remove();
}

//统计有效期天数
function countmaturitydate(obj) {
	var d1 = $("#update_product_maturitydate").val();
	var d2 = $("#update_product_productiondate").val();
	var day = countDays(d2,d1);
	$(obj).val(day);
}

(function($){ 
	$.validator.addMethod("effectiveDay", function(value, element) {                 
		var chcheck = /^-?\\d+$/; 
		return this.optional(element) || chcheck.test(value);            
	}, "请输入整数"); 
	$.validator.addMethod("count", function(value, element) {                 
		var chcheck = /^-?\\d+$/; 
		return this.optional(element) || chcheck.test(value);            
	}, "请输入整数"); 
})(jQuery);  

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

//	验证图片是否上传
function validProductsPic(){
	//验证商品图片
	var goodPics = $("#updateProductPicDiv").find("img");
	var flag = true;
	if(goodPics.length == 0){
		flag = false;
		alertMsg.error("请上传商品图片");
	} 
	return flag;
}

//组装商品图片信息
function getAddProductPicDatas(){
    var str="";
	var imgs = $("#updateProductPicDiv img");
	if(imgs != null && imgs != undefined){
		str = $(imgs[0]).attr("picUrl");
	}
	return str;
}

</script>
 <div class="pageContent">
	<form method="post" action="${DOMAIN}product/productUpdateSubmit" class="pageForm required-validate" onsubmit="return validateCallback1(this, navTabAjaxDone);">
		<input type="hidden" id="productattrs" name="productattrs"/>
		<input type="hidden" id="productpic" name="productpic"/>
		<input type="hidden" id="productId" name="id" value="${product.id}"/>
		<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label style="text-align:right">商品名称：</label>
				<input name="name" class="required" type="text" size="30" value="${product.name!''}" alt="请输入商品名称"/>
			</div>
			<div class="unit">
				<label style="text-align:right">SKU：</label>
				<input name="sku" id="update_product_sku" class="required" type="text" size="30" value="${product.sku!''}" readonly="true" alt="请输入SKU"/>
			</div>
			<div class="unit">
				<label style="text-align:right">单位：</label>
				<select name="unit">
					<option value="-1"></option>
					<#list unitsOptions?keys as key> 
						<#if product.unit == key?eval>
							<option selected="selected" value="${key}">${unitsOptions[key]}	</option>	
						<#else>
							<option value="${key}">${unitsOptions[key]}	</option>	
						</#if>
					</#list>
				</select>
			</div>
			<div class="unit">
				<label style="text-align:right">价格：</label>
				<input name="price" value="${product.price!''}" size="30" onkeyup="checkNum(this)" class="required" alt="请输入价格！" />
			</div>
			<div class="unit">
				<label style="text-align:right">生产日期：</label>
				<input type="text" id="update_product_productiondate" name="productionDate" class="date" dateFmt="yyyy-MM-dd" value="${product.productionDate!''}" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
				<span class="info">yyyy-MM-dd</span>
			</div>
			<div class="unit">
				<label style="text-align:right">到期日期：</label>
				<input type="text" id="update_product_maturitydate" name="maturityDate" class="date" dateFmt="yyyy-MM-dd" value="${product.maturityDate!''}" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
				<span class="info">yyyy-MM-dd</span>
			</div>
			<div class="unit">
				<label style="text-align:right">有效天数：</label>
				<#if product.effectiveDay??> 
					<#assign effectiveDay = product.effectiveDay?replace(",","") />
				<#else>
					<#assign effectiveDay = '' />
				</#if>
				<input type="text" name="effectiveDay" value="${effectiveDay}" size="30" onfocus="countmaturitydate(this)" alt="请输入有效天数！" />
			</div>
			<div class="unit">
				<label style="text-align:right">库存：</label>
				<#if product.inventoryAvailable??> 
					<#assign inventoryAvailable = product.inventoryAvailable?replace(",","") />
				<#else>
					<#assign inventoryAvailable = '' />
				</#if>
				<input type="text" name="inventoryAvailable" value="${inventoryAvailable}" size="30"  class="required" alt="请输入库存！"  readonly="true" />
			</div>
			<div class="unit">
				<label style="text-align:right">包装类型：</label>
				<select name="packType">
					<option value="-1"></option>
					<#list packTypes?keys as key> 
						<#if product.packType == key?eval>
							<option selected="selected" value="${key}">${packTypes[key]}	</option>	
						<#else>
							<option value="${key}">${packTypes[key]}	</option>	
						</#if>
					</#list>
				</select>
			</div>
			<div class="unit">
				<label style="text-align:right">商品类型：</label>
				<select name="type">
					<option value="-1"></option>
					<#list productTypes?keys as key> 
						<#if product.type == key?eval>
							<option selected="selected" value="${key}">${productTypes[key]}	</option>	
						<#else>
							<option value="${key}">${productTypes[key]}	</option>	
						</#if>				
					</#list>
				</select>
			</div>
			<div class="unit">
				<label style="text-align:right">商品属性：</label>
				<div class="product_attr">
					<#if product?? && product.productAttribute ?? >
						<#assign i = 0/>
						<#list product.productAttribute as attr>
							<#if i == 0> 
								<div class="product_attr_row">
									<div class="product_attr_d1">
										<select name="type">
											<option value=""></option>
											<#list productAttrs?keys as key> 
												<#if key == attr.name> 
													<option selected="selected" value="${key}">${productAttrs[key]}	</option>
												<#else>
													<option value="${key}">${productAttrs[key]}	</option>
												</#if>
											</#list>	
										</select>
									</div>
									<div class="product_attr_d2">
										<input alt="请输入属性值！" size="20" value="${attr.value!''}" name="value">
									</div>
									<div class="product_attr_d3"><a onclick='addproductattr()'>增加</a></div>
								</div>
							<#else>
								<div class="product_attr_row">
									<div class="product_attr_d1">
										<select name="type">
											<option value=""></option>
											<#list productAttrs?keys as key> 
												<#if key == attr.name> 
													<option selected="selected" value="${key}">${productAttrs[key]}	</option>
												<#else>
													<option value="${key}">${productAttrs[key]}	</option>
												</#if>
											</#list>	
										</select>
									</div>
									<div class="product_attr_d2">
										<input alt="请输入属性值！" size="20" value="${attr.value!''}" name="value">
									</div>
									<div class="product_attr_d3"><a onclick='deleteproductattr()'>删除</a></div>
								</div>
							</#if>
							<#assign i = i+1/>
						</#list>
					</#if>
				</div>
			</div>
			<div class="unit">
				<label style="text-align:right">商品图片上传：</label>
				<div class="setUpload">
					<span class="su_img">
						<span id="spanButtonPlaceHolder"></span>
		       			<input id="btnCancel" type="button" value="终止" style="display:none;" onclick="swfu.cancelQueue();" disabled="disabled" />
	       			</span>
				</div>
				<div id="fsUploadProgress" style="margin-left:124px;"></div>
				<span style="margin-left:124px;">
					<div id="updateProductPicDiv" style="margin-left:124px;">
						<#assign pathimg="${IMGBASEPATH!''}${product.picUrl!''}">
						<div style="float:left"><img picUrl="${product.picUrl!''}" src="${pathimg!''}" width="50" height="50" /><a href="javascript:void(0)" onclick="deletePic(this)">X</a></div>
					</div>
				</span>
			</div>
			<div class="unit">
				<label style="text-align:right">商品描述：</label>
				<textarea name="description" alt="商品描述" cols="35" rows="8">${product.description}</textarea>
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
  

