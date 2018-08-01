 <script src="${BASEPATH}js/comment/activadd.js" type="text/javascript"></script>
 <script>
 var picUrl='${IMGBASEPATH}';
 function validateCallback1(form, callback) {
	var $form = $(form);

	if (!$form.valid()) {
		return false;
	}
	
	var bgpic = $("#activupdatebgupload").attr("picUrl");
	if(bgpic == "") {
		$("#activupdatebguploaderror").addClass("error").text("主页背景图没有上传，请上传！");
		return false;
	}
	
	var subgpic = $("#activupdatesubgupload").attr("picUrl");
	if(subgpic == "") {
		$("#activupdatesubguploaderror").addClass("error").text("主页背景图没有上传，请上传！");
		return false;
	}
	
	var wfpic = $("#activupdateworkflowupload").attr("picUrl");
	if(wfpic == "") {
		$("#activupdateworkflowuploaderror").addClass("error").text("流程图没有上传，请上传！");
		return false;
	}
	var qpic = $("#activupdateqcardupload").attr("picUrl");
	if(qpic == "") {
		$("#activupdateqcarduploaderror").addClass("error").text("二维码没有上传，请上传！");
		return false;
	}
	var des = $("#activ-update-editor").val();
	var html = '<div  style="height:26px"><span id="activ-update-des-error" class="error">活动介绍为空，请填写</span></div>';
	if(des == "") {
		$("#activ-update-des").append(html);
		return false;
	}
	
	var stime = $("#acitv-update-startTime").val();
	var etime = $("#acitv-update-endTime").val();
	if(stime == "" || etime == "") {
		var timeh =  '<span id="activupdatetimeerror" class="error" style="left:10px">活动时间请填写完整！</span>';
		$("#activ-update-time").append(timeh);
		return false;
	}
	$("#updateBgPath").val(bgpic);
	$("#updateSuBgPath").val(subgpic);
	$("#updateworkflowPath").val(wfpic);
	$("#updateqcardPath").val(qpic);
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
	<form method="post" action="${DOMAIN}comment/activ-update-submit" class="pageForm required-validate" onsubmit="return validateCallback1(this, navTabAjaxDone);">
		<input type="hidden" name="bgPath" id="updateBgPath" value="${activ.bgPath!''}"/>
		<input type="hidden" name="suBgPath" id="updateSuBgPath" value="${activ.suBgPath!''}"/>
		<input type="hidden" name="code" value="${activ.code!''}"/>
		<input type="hidden" name="id" value="${activ.id!''}"/>
		<input type="hidden" name="createTime" value="${activ.createTime!''}"/>
		<input type="hidden" name="wxLink" value="${activ.wxLink!''}"/>
		<input type="hidden" name="userId" value="${activ.userId!''}"/>
		<input type="hidden" name="workflowPath" id="updateworkflowPath" value="${activ.workflowPath!''}"/>
		<input type="hidden" name="qcardPath" id="updateqcardPath" value="${activ.qcardPath!''}"/>
		<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label style="text-align:right">活动名称：</label>
				<input name="name" class="required" type="text" size="30" value="${activ.name!''}" alt="请输活动名称"/>
			</div>
			<div class="unit">
				<label style="text-align:right"">页面标题：</label>
				<input name="title" class="required" type="text" size="30" value="${activ.title!''}" alt="请输页面标题"/>
			</div>
			<div class="unit">
				<label style="text-align:right">主页背景图：</label>
				<div class="upload-wrap" <#if activ.bgPath??>style="background:url('${IMGBASEPATH}${activ.bgPath}') no-repeat center center;background-size:contain"</#if>>
					<input id="activupdatebgupload" type="file" accept="image/*" picUrl="${activ.bgPath!''}" style="left: 0px;clip: auto;width:1%" class="valid" >
				</div>
				<span id="activupdatebguploaderror"></span>
				<div style="padding:10px 0 0 10px;margin: 0 0 0 120px;">
					主页背景图支持png、jpg、jpeg格式，文件最大1M，尺寸建议640px^1136px
				</div>
			</div>
			<div class="unit">
				<label style="text-align:right">成功背景图：</label>
				<div class="upload-wrap" <#if activ.suBgPath??>style="background:url('${IMGBASEPATH}${activ.suBgPath}') no-repeat center center;background-size:contain"</#if>>
					<input id="activupdatesubgupload" type="file" accept="image/*" picUrl="${activ.suBgPath!''}" style="left: 0px;clip: auto;width:1%" class="valid" >
				</div>
				<span id="activupdatesubguploaderror"></span>
				<div style="padding:10px 0 0 10px;margin: 0 0 0 120px;">
					成功背景图支持png、jpg、jpeg格式，文件最大1M，尺寸建议640px^1136px
				</div>
			</div>
			<div class="unit">
				<label style="text-align:right">流程图：</label>
				<div class="upload-wrap activworkflowupload" <#if activ.workflowPath??>style="background:url('${IMGBASEPATH}${activ.workflowPath}') no-repeat center center;background-size:contain"</#if>>
					<input id="activupdateworkflowupload" type="file" accept="image/*" picUrl="${activ.workflowPath!''}" style="left: 0px;clip: auto;width:1%" class="valid" >
				</div>
				<span id="activupdateworkflowuploaderror" class=""></span>
				<div style="padding:10px 0 0 10px;margin: 0 0 0 120px;">
					流程图支持png、jpg、jpeg格式，文件最大1M
				</div>
			</div>
			<div class="unit">
				<label style="text-align:right">客服二维码：</label>
				<div class="upload-wrap activqcardupload" <#if activ.qcardPath??>style="background:url('${IMGBASEPATH}${activ.qcardPath}') no-repeat center center;background-size:contain"</#if>>
					<input id="activupdateqcardupload" type="file" accept="image/*" picUrl="${activ.qcardPath!''}" style="left: 0px;clip: auto;width:1%" class="valid" >
				</div>
				<span id="activqcarduploaderror" class=""></span>
				<div style="padding:10px 0 0 10px;margin: 0 0 0 120px;">
					流程图支持png、jpg、jpeg格式，文件最大1M
				</div>
			</div>
			<div class="unit" id="activ-update-des">
				<label style="text-align:right">活动介绍：</label>
				<textarea tools="Cut,Copy,Paste,Pastetext,|,Source,Fullscreen" cols="100" id="activ-update-editor" rows="6" name="description" class="editor" style="display: none;">${activ.description!''}</textarea>
			</div>
			<div class="unit" id="activ-update-time">
				<label style="text-align:right">活动时间：</label>
				<span>
					<input class="date textInput readonly valid" type="text" readonly="true" id="acitv-update-startTime" value="${activ.startTime!''}" name="startTime" datefmt="yyyy-MM-dd HH:mm:ss">
					<a class="inputDateButton" href="javascript:;">选择</a>
				</span>
				<span class="info">至</span>
				<span>
					<input class="date textInput readonly valid" type="text" readonly="true" id="acitv-update-endTime" value="${activ.endTime!''}" name="endTime" datefmt="yyyy-MM-dd HH:mm:ss">
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
  

