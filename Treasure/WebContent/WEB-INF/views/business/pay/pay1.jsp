<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>大盘银多宝——支付测试</title>
<meta name="description"
	content="This is page-header (.page-header &gt; h1)" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>
<body>
	<form action="https://api.mch.weixin.qq.com/pay/unifiedorder"
		method="post" enctype="application/x-www-form-urlencoded">
		<input name="appid" value="wx45ab60e524a28455" /> <input
			name="mch_id" value="1451012502" /> <input name="nonce_str"
			value="<%=Math.random()%>" /> <input name="sign" value="" id="sign" />
		<input name="body" value="银哆宝" /> <input name="out_trade_no"
			value="YDB0111111" /><input name="notify_url"
			value="http://119.29.15.225:8090/Treasure/order/payResult" /> <input
			name="total_fee" value="0.01" /> <input name="spbill_create_ip"
			id="spbill_create_ip" value="14.23.150.211"/> <input name="trade_type" value="MWEB" /> <input
			name="scene_info"
			value="{'h5_info': {'type':'wap','wap_name':'银哆宝','wap_url':'http://ydb.xfnrsc.com'}}" />
		<button type="submit" id="pay-btn">提交</button>
	</form>
	<script src='${path}/js/jquery-2.1.4.min.js'></script>
	<script src='${path}/js/md5.min.js'></script>
	<script type="text/javascript">
		var o = {}, arr = [], signArr = [], key, $sign = $("#sign"), $button = $("#pay-btn");
		$("input").each(function() {//取值
			o[this.name] = this.value;
			arr.push(this.name);
		});
		arr.sort(function(a, b) { //排序【必须】
			return a > b ? 1 : -1;
		});
		for (var i = 0, a = arr.length; i < a; i++) {//键值对形式拼接参数
			key = arr[i];
			if ("sign" != key) {
				o[key] && signArr.push(key + "=" + o[key]);
			}
		}
		console.log(signArr.join("&"));
		$sign.val(md5(signArr.join("&")).toUpperCase());
	//	$("#spbill_create_ip").val($_SERVER["REMOTE_ADDR"] || getenv("REMOTE_ADDR") || "unknow");
	</script>
</body>
</html>