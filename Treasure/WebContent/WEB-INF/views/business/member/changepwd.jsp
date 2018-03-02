<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<form class="form-horizontal" method="post"
	action="${path}/member/mgr/updateMember">
	<input name="id" type="hidden" />
	<div class="row">
		<div class="col-xs-12">
			<div class="form-group">
				<label class="col-sm-4 control-label">旧密码</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="password"
						maxlength="100" placeholder="请输入旧密码">
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-6">
			<div class="form-group">
				<label class="col-sm-4 control-label">新密码</label>
				<div class="col-sm-8">
					<div class="input-icon">
						<i class="fa fa-asterisk"></i> <input type="tel"
							class="form-control" name="passwordNew" maxlength="30"
							placeholder="请输入新密码" data-bv-notempty
							data-bv-notempty-message="电话号码不能为空">
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<div class="form-group">
				<label class="col-sm-4 control-label">重新输入</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="passwordNewAgain"
						placeholder="请重新输入密码">
				</div>
			</div>
		</div>
	</div>
</form>