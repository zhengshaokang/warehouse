<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<meta name="apple-mobile-web-app-capable" content="yes">
<title>晒评价</title>
<link href="${BASEPATH}js/util/mypopup.css" rel="stylesheet" type="text/css"/>
<link href="${BASEPATH}css/comment/wx_upload.css" rel="stylesheet" type="text/css"/>
<script src="${BASEPATH}plugin/dwz-ria/js/jquery-1.7.2.min.js"></script>
<script src="${BASEPATH}js/util/mypopup.js" type="text/javascript"></script>
<script src="${BASEPATH}js/comment/wx_upload.js" type="text/javascript"></script>
</head>
<script>
	var picUrl='${IMGBASEPATH}';
</script>
<style>
.index {
	width:100%;
	height:100%;
	background: url("${BASEPATH}/img/comment/${templet}")  no-repeat center center;
	background-size:100% 100%;
}
.index2 {
	width:100%;
	height:100%;
	display:none;
	background: url("${BASEPATH}/img/comment/su.png")  no-repeat center center;
	background-size:100% 100%;
}
</style>

<body>
	<div class="index">
		<div style="height:56%;"></div>
		<div class="upload_c">
			<div class="upload-c1">
                  <div id="updateImage" class="upload-btn">
                      <input type="file" accept="image/*" id="BtnUpload">
                  </div>
			</div>
		</div>
		<div class="order-box">
			 <input type="text" id="orderNo" placeholder="第2步：填写订单编号(请勿复制物流号)">
		</div>
		<div class="order-submit">
			 <button  type="button" id="orderSubmit">马上提交</button>
		</div>
		<input type="hidden" id="userId" value="${userId}" />
	</div>
	<div class="index2">
		<div style="height:88%;"></div>
		<div class="order-submit">
			 <button  type="button" id="reback">返回</button>
		</div>
	</div>
</body>
</html>