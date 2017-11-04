<#-- 输出数字，当i小于0时不输出 -->
<#macro d i>
	<#if i>-1>
		${i}
	</#if>
</#macro>

<#-- 输出字符串，当i为null时输出'' -->
<#macro s i>
	<#if i??>
		${i}
	<#else>
		${''}
	</#if>
</#macro>

<#-- 把日期对象输出yyyy-MM-dd格式的日期，当i为null时输出'' -->
<#macro ymd i>
	<#if i??>
		${i?string("yyyy-MM-dd")} 
	<#else>
		${""}
	</#if>
</#macro>

<#-- 把日期对象输出HH:mm:ss格式的日期，当i为null时输出'' -->
<#macro hms i>
	<#if i??>
		${i?string("HH:mm:ss")} 
	<#else>
		${""}
	</#if>
</#macro>

<#-- 把日期对象输出yyyy-MM-dd HH:mm格式的日期，当i为null时输出'' -->
<#macro ymdhm i>
	<#if i??>
		${i?string("yyyy-MM-dd HH:mm")} 
	<#else>
		${""}
	</#if>
</#macro>

<#-- 把日期对象输出yyyy-MM-dd HH:mm:ss格式的日期，当date为null时输出'' -->
<#macro ymdhms i>
	<#assign tempDate = ''>
	<#if i!=''>
		<#assign tempDate = '${i?string("yyyy-MM-dd HH:mm:ss")}'>
	</#if>
	${tempDate}
</#macro>
