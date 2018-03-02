<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>没有权限</title>
<meta content="IE=edge" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta content="text/html; charset=utf-8" http-equiv="Content-type">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="${path}/images/favicon.ico?v=2">
<style type="text/css">
.threed {
	color: #fff;
	letter-spacing: 0;
	text-shadow: 0px 1px 0px #999, 0px 2px 0px #888, 0px 3px 0px #777, 0px
		4px 0px #666, 0px 5px 0px #555, 0px 6px 0px #444, 0px 7px 0px #333,
		0px 8px 7px #001135;
	text-align:center;
	margin-top: 15%;
	font-size: 40px;
}
</style>
</head>
<body style="background: #0099CC;">
	<div class="threed">很遗憾，您没有权限访问，请联系管理员获取权限！</div>
</body>
</html>