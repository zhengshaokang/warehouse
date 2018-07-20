 
 <script>
 function validateCallback1(form, callback) {
	var $form = $(form);

	if (!$form.valid()) {
		return false;
	}
	var shopId = $("#orderUpdateShop").val();
	if(shopId == "-1") {
		alertMsg.error("请选择店铺！");
		return false;
	}
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
	<form method="post" action="${DOMAIN}comment/order-update-submit" class="pageForm required-validate" onsubmit="return validateCallback1(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label style="text-align:right">订单号：</label>
				<input name="orderNo" class="required" type="text" size="30" value="${order.orderNo!''}" alt="请输订单号"/>
			</div>
			<div class="unit">
				<label style="text-align:right">店铺：</label>
				<select id="orderUpdateShop" name="shopId" style="min-width:120px;" class="required">
					<#list shops?keys as key> 
						<#if order.shopId?? && key?eval == order.shopId> 
							<option value="${key}"  selected="selected">${shops[key]}</option>
						<#else>
							<option value="${key}">${shops[key]}</option>
						</#if>					
					</#list>
				</select>
			</div>
			<div class="unit">
				<label style="text-align:right">参与活动：</label>
				<select name="isJoin" style="min-width:120px;" class="required">
					<#list yesno?keys as key> 
						<#if order.isJoin?? && key?eval == order.isJoin> 
							<option value="${key}" selected="selected">${yesno[key]}</option>			
						<#else>
							<option value="${key}">${yesno[key]}</option>		
						</#if>				
					</#list>
				</select>
			</div>
			<#--
			<div class="unit">
				<label style="text-align:right">审核状态：</label>
				<select name="isPay" style="min-width:120px;" class="required">
					<#list payStatus?keys as key> 
						<#if order.isPay?? && key?eval == order.isPay> 
							<option value="${key}" selected="selected">${payStatus[key]}</option>		
						<#else>
							<option value="${key}">${payStatus[key]}</option>	
						</#if>			
					</#list>
				</select>
			</div>
			-->
			<input name="isPay" type="hidden" value="${order.isPay!''}" />
			<div class="unit">
				<label style="text-align:right">订单状态：</label>
				<select name="orderStatus" style="min-width:120px;" class="required">
					<#list orderStatus?keys as key> 
						<#if order.orderStatus?? && key?eval == order.orderStatus> 
							<option value="${key}" selected="selected">${orderStatus[key]}</option>	
						<#else>
							<option value="${key}">${orderStatus[key]}</option>	
						</#if>				
					</#list>
				</select>
			</div>
			<div class="unit">
				<label style="text-align:right">客户名称：</label>
				<input name="customerName"  type="text" size="30" value="${order.customerName!''}" alt="客户名称"/>
			</div>
			<div class="unit">
				<label style="text-align:right">客户电话：</label>
				<input name="customerMobile"  type="text" size="30" value="${order.customerMobile!''}" alt="请输客户电话"/>
			</div>
			<div class="unit">
				<label style="text-align:right">下单时间：</label>
				<input type="text" maxDate="{%y}-%M-{%d}" name="orderTime" value="${order.orderTime!''}" class="date" dateFmt="yyyy-MM-dd HH:mm:ss"/>
			</div>
			<div class="unit">
				<label style="text-align:right">备注：</label>
				<textarea name="remark" alt="订单备注" cols="35" rows="8">${order.remark!''}</textarea>
			</div>
			<input name="id"  type="hidden" value="${order.id}"/>
			<input name="userId"  type="hidden" value="${order.userId}"/>
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
  

