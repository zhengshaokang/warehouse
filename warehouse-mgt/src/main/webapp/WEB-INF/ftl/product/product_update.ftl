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
		var img = '<div style="float:left"><img picUrl="'+json.returnPath+'" src= '+uploadpath+'/'+json.returnPath+' width="50" height="50"/><a href="javascript:void(0)" onclick="deletePic(this)">X</a></div>';
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
	 var re = /^[1-9]\d*$/;
     if (!re.test(obj.value)) {
		  obj.value="";
	      obj.focus();
	      return false;
     }
}

(function($){ 
	$.validator.addMethod("warning", function(value, element) {                 
		var chcheck = /^-?\\d+$/; 
		return this.optional(element) || chcheck.test(value);            
	}, "请输入整数"); 
})(jQuery);  

function validateCallback1(form, callback) {
	var $form = $(form);

	if (!$form.valid()) {
		return false;
	}
	
	//验证图片是否上传
	//if(!validProductsPic()){
	//	return false;
	//}
    $("#productpic").val(getAddProductPicDatas());
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
		<input type="hidden" id="productpic" name="productpic"/>
		<input type="hidden" id="productId" name="id" value="${product.id}"/>
		<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label style="text-align:right">商品名称：</label>
				<input name="name" class="required" type="text" readonly="true"  size="30" value="${product.name!''}" alt="请输入商品名称"/>
			</div>
			
			<div class="unit">
				<label style="text-align:right">国际条码：</label>
				<input name="code" id="add_product_code"  type="text" size="30" value="${product.code!''}" alt="请输入国际条码"/>
			</div>
			<div class="unit">
				<label style="text-align:right">内部编码：</label>
				<input name="sku" id="update_product_sku"  type="text" size="30" value="${product.sku!''}" alt="请输入内部编码"/>
			</div>
			<div class="unit">
				<label style="text-align:right">品牌：</label>
				<select name="brandId" id="add_product_brand" style="min-width:120px;" class="required">
					<option value="-1"></option>
					<#list brands?keys as key> 
						<#if product.brandId == key?eval>
							<option selected="selected" value="${key}">${brands[key]}	</option>	
						<#else>
							<option value="${key}">${brands[key]}	</option>	
						</#if>		
					</#list>
				</select>
			</div>
			<div class="unit">
				<label style="text-align:right">单位：</label>
				<select name="unitId" style="min-width:120px;" class="required">
					<option value="-1"></option>
					<#list unitsOptions?keys as key> 
						<#if product.unitId == key?eval>
							<option selected="selected" value="${key}">${unitsOptions[key]}	</option>	
						<#else>
							<option value="${key}">${unitsOptions[key]}	</option>	
						</#if>
					</#list>
				</select>
			</div>
			<div class="unit">
				<label style="text-align:right">商品规格：</label>
				<span style="display:inline;">
					<select name="specificationId1" style="min-width:120px;height:22px;" onchange="specificationSelectSub(this)">
						<option value="-1"></option>
						<#list productSpecificationss?keys as key> 
							<#if product.specificationId1 == key?eval>
								<option selected="selected" value="${key}">${productSpecificationss[key]}	</option>	
							<#else>
								<option value="${key}">${productSpecificationss[key]}</option>	
							</#if>
						</#list>
					</select>
				</span>
				<span style="display:inline;">
					<select name="specificationId2" id="addproductspecification" style="min-width:120px;height:22px;margin-left:10px;">
						<option value="-1"></option>
						<#list productSpecificationssSub?keys as key> 
							<#if product.specificationId2 == key?eval>
								<option selected="selected" value="${key}">${productSpecificationssSub[key]}</option>	
							<#else>
								<option value="${key}">${productSpecificationssSub[key]}</option>	
							</#if>
						</#list>
					</select>
				</span>
			</div>
			<div class="unit">
				<label style="text-align:right">商品分类：</label>
				<span style="display:inline;">
					<select name="classificationId1" style="min-width:120px;height:22px;" onchange="classificationSelectSub(this)">
						<option value="-1"></option>
						<#list productClassifications?keys as key> 
								<#if product.classificationId1 == key?eval>
									<option selected="selected" value="${key}">${productClassifications[key]}	</option>	
								<#else>
									<option value="${key}">${productClassifications[key]}</option>		
								</#if>	
						</#list>
					</select>
				</span>
				<span style="display:inline;">
					<select name="classificationId2" id="addproductclassification" style="min-width:120px;margin-left:10px;height:22px;">
						<option value="-1"></option>
						<#list productClassificationsSub?keys as key> 
							<#if product.classificationId2 == key?eval>
								<option selected="selected" value="${key}">${productClassificationsSub[key]}	</option>	
							<#else>
								<option value="${key}">${productClassificationsSub[key]}</option>	
							</#if>
						</#list>
					</select>
				</span>
			</div>
			<div class="unit">
				<label style="text-align:right">临期警示：</label>
				<input name="warning" value="${product.warning!'0'}" onkeyup="validateInteger(this)" size="30" alt="请输入临期警示！" />
			</div>
			<div class="unit">
				<label style="text-align:right">供应商：</label>
				<select name="supplierId" style="min-width:120px;" class="required">
					<option value="-1"></option>
					<#list suppliers?keys as key> 
						<#if product.supplierId ?? && product.supplierId == key?eval>
							<option selected="selected" value="${key}">${suppliers[key]}</option>	
						<#else>
							<option value="${key}">${suppliers[key]}</option>		
						</#if>
									
					</#list>
				</select>
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
				<label style="text-align:right">备注：</label>
				<textarea name="remark" alt="商品备注" cols="35" rows="8">${product.remark!''}</textarea>
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
  

