<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<meta name="apple-mobile-web-app-capable" content="yes">
<title>商品列表</title>
<link type="text/css" rel="stylesheet" href="${BASEPATH}css/m/product.css" />
<script src="${BASEPATH}plugin/dwz-ria/js/jquery-2.1.4.js" type="text/javascript"></script>
</head>
<script>
$(function(){
	$("#queryProductsList").click(function() {
		queryList();
	});
})
function queryList() {
	$("#pagerForm").submit();
}
function openProductDes(productId) {
	window.location.href = "${DOMAIN}product/productPreview?productId="+productId;
}
</script>
<body>
	<div class="p_list_c">
		<div class="p_list_q">
			<form id="pagerForm" method="post" action="${DOMAIN}product/list">
				<input type="text" name="name" value="${productParam.name!''}">
				<span id="queryProductsList">搜索</span>
			</form>
		</div>
		<div class="p_list_r" id="produtResults">
			<#if pageParam ?? && pageParam.getPageData() ??>
				<#list pageParam.getPageData() as product>
				<div class="p_list_r_d" onclick="openProductDes(${product.id!''})">
					<span class="p_list_r_d_pic">
						<img src="${IMGBASEPATH}${product.picUrl!''}_80x80.${product.picType!'jpg'}"/>
					</span>
					<span class="p_list_r_d_title">
						<p>${product.name!''}</p>
						<p>
							${productSpecificationss["${product.specificationId1!''}"]}>>>
								<#if product.specificationId2 ??>
									${productSpecificationss["${product.specificationId2!''}"]}
								</#if>
						</p>
					</span>
				</div>
			</#list>
			</#if>
		</div>
	</div>
</body>
</html>
