<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>用户登录</title>
<meta content="IE=edge" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta content="text/html; charset=utf-8" http-equiv="Content-type">
<meta name="description" content="">
<meta name="author" content="">
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link rel="stylesheet"
	href="${path}/plugins/bsplus/css/font-awesome.min.css" type="text/css">
<link type="text/css" rel="stylesheet"
	href="${path}/plugins/bootstrap-3.3.5/css/bootstrap.min.css">
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" href="${path}/plugins/bsplus/css/bsplus.css"
	type="text/css">
<link type="text/css" rel="stylesheet" href="${path}/css/login.css">
<link rel="shortcut icon" href="${path}/images/favicon.ico?v=2">
<style type="text/css">
.threed {
	color: #1138C4;
	letter-spacing: 0;
	text-shadow: 0px 1px 0px #999, 0px 2px 0px #888, 0px 3px 0px #777, 0px
		4px 0px #666, 0px 5px 0px #555, 0px 6px 0px #444, 0px 7px 0px #333,
		0px 8px 7px #001135;
	margin-left: 10px;
}
</style>
</head>
<body style="background: #0099CC;">
	<div class="login">
		<div class="login-head">
			<div class="logo">
				<a href="${path}"></a>
			</div>
		</div>
		<div class="login-content">
			<div class="login-form">
				<form method="post" id="login-form">
					<h3 class="form-title">
						登录
					</h3>
					<div class="form-group">
						<label class="control-label visible-ie8 visible-ie9">用户名</label>
						<div class="input-icon">
							<i class="fa fa-user"></i> <input id="userName"
								class="form-control placeholder-no-fix" type="text"
								autocomplete="off" placeholder="请输入用户名" name="userName"
								data-bv-notempty data-bv-notempty-message="请输入用户名" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label visible-ie8 visible-ie9">密码</label>
						<div class="input-icon">
							<i class="fa fa-lock"></i> <input id="password"
								class="form-control placeholder-no-fix" type="password"
								autocomplete="off" placeholder="请输入密码" name="password"
								data-bv-notempty data-bv-notempty-message="请输入用户密码" />
						</div>
					</div>
					<div class="form-actions">
						<input type="checkbox" checked="checked" id="remember"
							name="remember" value="1" data-after-text="记住我" />
						<button type="submit" class="btn blue-haze"
							style="width: 100%; margin-top: 20px;">
							登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录 <i class="fa fa-signin"></i>
						</button>
					</div>
					<div class="forget-password">
						<p>
							没有帐号？请<a>联系系统管理员</a>添加账号。
						</p>
						<p>
							忘记密码？请<a>联系系统管理员</a>重置密码；重置之后登陆可<a>自行修改密码</a>。
						</p>
					</div>
				</form>
			</div>
			<div class="pic">
				<img alt="" src="${path}/images/background.png?v=1" height="450">
			</div>
		</div>
		<div class="login-footer">
			<div class="copyright">2017 &copy;tuanli</div>
		</div>
	</div>
	<script type="text/javascript" src="${path}/js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript"
		src="${path}/plugins/bootstrap-3.3.5/js/bootstrap.min.js"></script>
	<script
		src="${path}/plugins/bootstrapValidator/js/bootstrapValidator.min.js"
		type="text/javascript" charset="UTF-8"></script>
	<script src="${path}/plugins/bsplus/js/bsplus-config.js"
		type="text/javascript" charset="UTF-8"></script>
	<script src="${path}/plugins/bsplus/js/bsplus.all.js"
		type="text/javascript" charset="UTF-8"></script>
	<script src="${path}/js/common.js?v=1" type="text/javascript"
		charset="UTF-8"></script>
	<script type="text/javascript">
		$("#userName").val(Cookies.getCookie("userName"));
		//$("#password").val(Cookies.getCookie("password"));
		$("#login-form").bootstrapValidator({
			excluded : ':disabled',
			message : 'This value is not valid',
			feedbackIcons : {
				valid : 'fa fa-check vicon',
				invalid : 'fa fa-close vicon',
				validating : 'fa fa-refresh vicon'
			}
		}).on('success.form.bv', function(e) {// 表单验证成功
			e.preventDefault();
			$.ajax({
				url : "${path}/mgr/user/dologin",
				type : "POST",
				data : $("#login-form").serialize(),
				success : function(data) {
					if ("true" === data.code) {
						if ($("#remember").prop("checked")) {
							Cookies.setCookie("userName", $("#userName").val());
							//Cookies.setCookie("password", $("#password").val());
						} else {
							Cookies.delCookie("userName");
							Cookies.delCookie("password");
						}
						window.top.location.href = "<c:url value='/mgr/home'/>";
					} else {
						$("#login-form").data('bootstrapValidator').resetForm(true);// 重置验证
						$("#userName").val(Cookies.getCookie("userName"));
						//$("#password").val(Cookies.getCookie("password"));
						bsplus.showToast({
							content : data.msg,
							backColor : "#C9302C"
						});
					}
				}
			});
		});
	</script>
</body>
</html>