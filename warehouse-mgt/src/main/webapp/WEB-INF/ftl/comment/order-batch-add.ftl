 
 <script>
function iframeCallback1(form, callback){
	var platform = $("#platform").val();
	var shop = $("#orderAddShopbath").val();
	if(typeof platform == "undefined" || platform == "-1") {
		alertMsg.error("请选择平台！");
		return false;
	}
	if(typeof shop == "undefined" || shop == "-1") {
		alertMsg.error("请选择店铺！");
		return false;
	}
	var orderUploadFile = $("#orderUploadFile").val();
	if(typeof orderUploadFile == "undefined" || orderUploadFile == "") {
		alertMsg.error("请选择上传文件！");
		return false;
	}
	var $form = $(form), $iframe = $("#callbackframe");
	if(!$form.valid()) {return false;}

	if ($iframe.size() == 0) {
		$iframe = $("<iframe id='callbackframe' name='callbackframe' src='about:blank' style='display:none'></iframe>").appendTo("body");
	}
	if(!form.ajax) {
		$form.append('<input type="hidden" name="ajax" value="1" />');
	}
	form.target = "callbackframe";
	
	_iframeResponse($iframe[0], callback || DWZ.ajaxDone);
}

function navTabAjaxDone1(json){
	//DWZ.ajaxDone(json);
	
	
	if(json[DWZ.keys.message] && alertMsg) $("#upload-msg").append(json[DWZ.keys.message]);
	
	if (json[DWZ.keys.statusCode] == DWZ.statusCode.ok){
		if (json.navTabId){ //把指定navTab页面标记为需要“重新载入”。注意navTabId不能是当前navTab页面的
			navTab.reloadFlag(json.navTabId);
		} else { //重新载入当前navTab页面
			var $pagerForm = $("#pagerForm", navTab.getCurrentPanel());
			var args = $pagerForm.size()>0 ? $pagerForm.serializeArray() : {}
			navTabPageBreak(args, json.rel);
		}

		if ("closeCurrent" == json.callbackType) {
			setTimeout(function(){navTab.closeCurrentTab(json.navTabId);}, 100);
		} else if ("forward" == json.callbackType) {
			navTab.reload(json.forwardUrl);
		} else if ("forwardConfirm" == json.callbackType) {
			alertMsg.confirm(json.confirmMsg || DWZ.msg("forwardConfirmMsg"), {
				okCall: function(){
					navTab.reload(json.forwardUrl);
				},
				cancelCall: function(){
					navTab.closeCurrentTab(json.navTabId);
				}
			});
		} else {
			navTab.getCurrentPanel().find(":input[initValue]").each(function(){
				var initVal = $(this).attr("initValue");
				$(this).val(initVal);
			});
		}
	}
}

function getshops(obj) {
	var platform = $(obj).val();
	$.ajax({
        type:"post",
        url:"comment/get-shops",
        data:{platform:platform},
        dataType:"json",
        success:function(data){
            if(data!=null) {
            	var html = "";
            	$.each(data,function(key,values){  
			   	 	html += '<option value="'+key+'">'+values+'</option>';	
			  	});
            	$("#orderAddShopbath").append(html);
            }
        },
        error:function(data){
        	mypopup.alert(JSON.stringify(data));
        }
    });
}
 </script>
 
 <style>
 .excel_upload_div{
 	width:600px;
 	height:280px;
 	margin:20px 0 0 30px;
 }
 .excel_upload_div a:hover{
 	cursor:pointer;
 }
 .excel_upload_div input:hover{
 	cursor:pointer;
 }
.excel_upload_div p{
	margin:20px 0 0 0;
	padding:0;
	width:600px;
}
.upload-msg{
	width:600px;
 	height:280px;
 	margin:20px 0 0 30px;
 	font-size:14px;
}
.upload-msg p span{
	padding:0 5px;
}
 </style>
 <div class="pageContent">
	<form  class="pageForm required-validate"  enctype="multipart/form-data"  method="post" novalidate="novalidate"  action="${DOMAIN}comment/order-upload" onsubmit="return iframeCallback1(this, navTabAjaxDone1);">
		<div class="pageFormContent" layoutH="56">
		
			   <div class="excel_upload_div">
			   		
			   		<p>
			   			<label style="text-align:right;width:40px">平台：</label>
						<select id="platform" name="platform" style="min-width:120px;" class="required" onchange="getshops(this)">
							<#list platforms?keys as key> 
								<option value="${key}">${platforms[key]}</option>					
							</#list>
						</select>
			   		
			   			<label style="text-align:right;width:40px">店铺：</label>
						<select id="orderAddShopbath" name="shopId" style="min-width:120px;" class="required">
								<option value="-1">请选择</option>					
						</select>
			   		</p>
			   		
			   		<p>
			   			<input type="file" id="orderUploadFile" accept="file/*" name="orderfile" style="left: 0px;" class="valid">
		               <input type="submit" value="上传文件" />
	               </p>
	               <p>
	               		<a href="${DOMAIN}res/templet/tianmaoorder.zip" style="font-size:16px;text-decoration:underline;" target="_blank" title="点击下载模板">淘宝天猫模板下载</a>
	               </p>
	               <p style="color:red">
	               		导入说明：
	               </p>
	               <p style="color:red;margin:10px 0 0 0;">
	               		1、导入文件格式必须是csv,xls,xlsx
	               </p>
	               <p style="color:red;margin:10px 0 0 0;">
	               		2、导入文件不超过50M，如果文件过大请分批导入
	               </p>
	               <p style="color:red;margin:10px 0 0 0;">
	               		3、导入过程中保持网络正常
	               </p>
               </div>
               <div id="upload-msg" class="upload-msg">
               
               </div>
		</div>
	</form>
</div>
  

