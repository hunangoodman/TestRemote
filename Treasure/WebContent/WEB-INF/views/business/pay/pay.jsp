<%@ page language="java" import="java.util.Date"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<fmt:formatDate value="<%=new Date()%>" pattern="yyyy-MM-dd HH:mm:ss"
	var="currentDate" />
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
<form action="https://openapi.alipay.com/gateway.do" method="get"
		  enctype="application/x-www-form-urlencoded">
		<input name="app_id" value="2017101909395768"/>
		<input name="method" value="alipay.trade.wap.pay"/>
		<input name="charset" value="utf-8"/>
		<input name="sign_type" value="RSA2"/>
		<input name="sign" value="" id="sign"/>
		<input name="timestamp" value="${currentDate}"/>
		<input name="version" value="1.0"/>
		<input name="return_url" value="http://119.29.15.225:8090/Treasure/order/payResult"/>
		<input name="notify_url" value="http://119.29.15.225:8090/Treasure/order/payResult"/>
		<input name="biz_content" value="{body:'test',subject:'银多宝',out_trade_no:'test00116',timeout_express:10,total_amount:0.01,product_code:'QUICK_WAP_WAY'}"/>
		<button type="submit" disabled="disabled" id="pay-btn">提交</button>
	</form>
	<script src='${path}/js/jquery-2.1.4.min.js'></script>
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
		$.ajax({//获取签名
			cache : true,
			type : 'POST',
			url : "${path}/order/sign",
			data : {
				signStr : signArr.join("&")
			},
			success : function(data) {
				console.log(data);
				if (data && 1 == data.code) {//签名成功之后再提交表单
					$sign.val(data.sign);
					$button.prop("disabled", false);
				}
			}
		});
	</script>
</body>
</html>