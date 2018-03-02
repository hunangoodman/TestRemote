<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<form class="form-horizontal" method="post"
	action="${path}/mgr/user/updatePassword">
	<input name="userId" type="hidden" value="${loginInfo.userId}" />
	<div class="row">
		<div class="col-xs-12">
			<div class="form-group">
				<label class="col-sm-4 control-label">原始密码</label>
				<div class="col-sm-8">
					<div class="input-icon">
						<i class="fa fa-asterisk"></i> <input type="password"
							class="form-control" name="oldpwd" maxlength="100"
							placeholder="请输入原始密码" data-bv-notempty
							data-bv-notempty-message="请输入原始密码">
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<div class="form-group">
				<label class="col-sm-4 control-label">新密码</label>
				<div class="col-sm-8">
					<div class="input-icon">
						<i class="fa fa-asterisk"></i><input type="password"
							class="form-control" name="password" id="password"
							placeholder="请输入新密码" data-bv-notempty
							data-bv-notempty-message="请输入新密码" data-bv-identical="true"
							maxlength="50" data-bv-identical-field="confirmPassword"
							data-bv-identical-message="前后密码不一致" />
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<div class="form-group">
				<label for="confirmPassword" class="col-sm-4 control-label">确认密码
				</label>
				<div class="col-sm-8">
					<div class="input-icon">
						<i class="fa fa-asterisk"></i> <input type="password"
							class="form-control" id="confirmPassword" name="confirmPassword"
							placeholder="请再次输入新密码" maxlength="50" data-bv-notempty
							data-bv-notempty-message="请再次输入新密码" data-bv-identical="true"
							data-bv-identical-field="password"
							data-bv-identical-message="前后密码不一致" />
					</div>
				</div>
			</div>
		</div>
	</div>
</form>