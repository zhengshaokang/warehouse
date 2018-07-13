 
 <script>
function iframeCallback1(form, callback){
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
 </script>
 
 <style>
 .excel_upload_div{
 	width:600px;
 	height:400px;
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
 </style>
 <div class="pageContent">
	<form  class="pageForm required-validate"  enctype="multipart/form-data"  method="post" novalidate="novalidate"  action="${DOMAIN}comment/order-upload" onsubmit="return iframeCallback1(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
		
			   <div class="excel_upload_div">
			   		<p>
			   			<input type="file" id="orderUploadFile" accept="file/*" name="orderfile" style="left: 0px;" class="valid">
		               <input type="submit" value="上传文件" />
	               </p>
	               <p>
	               		<a href="${DOMAIN}res/templet/order.xlsx" style="font-size:16px;text-decoration:underline;" target="_blank" title="点击下载模板">模板下载</a>
	               </p>
	               <p style="color:red">
	               		模板说明：
	               </p>
	               <p style="color:red;margin:10px 0 0 0;">
	               		1、订单号，对应各平台的订单号，在评价时需要和此匹配，必须填写
	               </p>
	               <p style="color:red;margin:10px 0 0 0;">
	               		2、店铺id，需要对应店铺管理里面的id，必须要填写
	               </p>
	               <p style="color:red;margin:10px 0 0 0;">
	               		3、是否参与活动、是否已经返现、订单状态等必须按照模板里面的数字填写
	               </p>
               </div>
		</div>
	</form>
</div>
  

