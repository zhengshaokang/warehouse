 <script src="${BASEPATH}js/comment/activadd.js" type="text/javascript"></script>
 <script>
 var picUrl='${IMGBASEPATH}';
 function validateCallback1(form, callback) {
	var $form = $(form);

	if (!$form.valid()) {
		return false;
	}
	
	var bgpic = $("#activaddbgupload").attr("picUrl");
	if(bgpic == "") {
		$("#activaddbguploaderror").addClass("error").text("主页背景图没有上传，请上传！");
		return false;
	}
	
	var subgpic = $("#activaddsubgupload").attr("picUrl");
	if(subgpic == "") {
		$("#activaddsubguploaderror").addClass("error").text("成功背景图没有上传，请上传！");
		return false;
	}
	
	var wfpic = $("#activaddworkflowupload").attr("picUrl");
	if(wfpic == "") {
		$("#activaddworkflowuploaderror").addClass("error").text("流程图没有上传，请上传！");
		return false;
	}
	var qpic = $("#activaddqcardupload").attr("picUrl");
	if(qpic == "") {
		$("#activaddqcarduploaderror").addClass("error").text("二维码没有上传，请上传！");
		return false;
	}
	var des = $("#activ-add-editor").val();
	var html = '<div  style="height:26px"><span id="activ-add-des-error" class="error">活动介绍为空，请填写</span></div>';
	if(des == "") {
		$("#activ-add-des").append(html);
		return false;
	}
	
	var stime = $("#acitv-add-startTime").val();
	var etime = $("#acitv-add-endTime").val();
	if(stime == "" || etime == "") {
		var timeh =  '<span id="activaddtimeerror" class="error" style="left:10px">活动时间请填写完整！</span>';
		$("#activ-add-time").append(timeh);
		return false;
	}
	$("#addBgPath").val(bgpic);
	$("#addSuBgPath").val(subgpic);
	$("#addworkflowPath").val(wfpic);
	$("#addqcardPath").val(qpic);
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
	<form method="post" action="${DOMAIN}comment/activ-add-submit" class="pageForm required-validate" onsubmit="return validateCallback1(this, navTabAjaxDone);">
		<input type="hidden" name="bgPath" id="addBgPath"/>
		<input type="hidden" name="suBgPath" id="addSuBgPath"/>
		<input type="hidden" name="workflowPath" id="addworkflowPath"/>
		<input type="hidden" name="qcardPath" id="addqcardPath"/>
		<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label style="text-align:right">活动名称：</label>
				<input name="name" class="required" type="text" size="30" value="" alt="请输活动名称"/>
			</div>
			<div class="unit">
				<label style="text-align:right"">页面标题：</label>
				<input name="title" class="required" type="text" size="30" value="" alt="请输页面标题"/>
			</div>
			<div class="unit">
				<label style="text-align:right">主页背景图：</label>
				<div class="upload-wrap ">
					<input id="activaddbgupload" type="file" accept="image/*" picUrl="" style="left: 0px;clip: auto;width:1%" class="valid" >
				</div>
				<span id="activaddbguploaderror"></span>
				<div style="padding:10px 0 0 10px;margin: 0 0 0 120px;">
					主页背景图支持png、jpg、jpeg格式，文件最大1M，尺寸建议640px^1136px
				</div>
			</div>
			
			<div class="unit">
				<label style="text-align:right">成功背景图：</label>
				<div class="upload-wrap ">
					<input id="activaddsubgupload" type="file" accept="image/*" picUrl="" style="left: 0px;clip: auto;width:1%" class="valid" >
				</div>
				<span id="activaddsubguploaderror"></span>
				<div style="padding:10px 0 0 10px;margin: 0 0 0 120px;">
					成功背景图支持png、jpg、jpeg格式，文件最大1M，尺寸建议640px^1136px
				</div>
			</div>
			
			<div class="unit">
				<label style="text-align:right">流程图：</label>
				<div class="upload-wrap activworkflowupload">
					<input id="activaddworkflowupload" type="file" accept="image/*" picUrl="" style="left: 0px;clip: auto;width:1%" class="valid" >
				</div>
				<span id="activaddworkflowuploaderror" class=""></span>
				<div style="padding:10px 0 0 10px;margin: 0 0 0 120px;">
					流程图支持png、jpg、jpeg格式，文件最大1M
				</div>
			</div>
			<div class="unit">
				<label style="text-align:right">客服二维码：</label>
				<div class="upload-wrap activqcardupload">
					<input id="activaddqcardupload" type="file" accept="image/*" picUrl="" style="left: 0px;clip: auto;width:1%" class="valid" >
				</div>
				<span id="activaddqcarduploaderror" class=""></span>
				<div style="padding:10px 0 0 10px;margin: 0 0 0 120px;">
					流程图支持png、jpg、jpeg格式，文件最大1M
				</div>
			</div>
			<div class="unit" id="activ-add-des">
				<label style="text-align:right">活动介绍：</label>
				<textarea tools="Cut,Copy,Paste,Pastetext,|,Source,Fullscreen" cols="100" id="activ-add-editor" rows="6" name="description" class="editor" style="display: none;"></textarea>
			</div>
			<div class="unit" id="activ-add-time">
				<label style="text-align:right">活动时间：</label>
				<span>
					<input class="date textInput readonly valid" type="text" readonly="true" id="acitv-add-startTime" name="startTime" datefmt="yyyy-MM-dd HH:mm:ss">
					<a class="inputDateButton" href="javascript:;">选择</a>
				</span>
				<span class="info">至</span>
				<span>
					<input class="date textInput readonly valid" type="text" readonly="true" id="acitv-add-endTime" name="endTime" datefmt="yyyy-MM-dd HH:mm:ss">
					<a class="inputDateButton" href="javascript:;">选择</a>
				</span>
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
  

