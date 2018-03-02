<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<form class="form-horizontal" method="post"
	action="${path}/mgr/user/addUser">
	<input name="userId" type="hidden" /> <input name="userType"
		type="hidden" value="1" />
	<div class="row">
		<div class="col-xs-6">
			<div class="form-group">
				<label for="userName" class="col-sm-4 control-label">用户名称</label>
				<div class="col-sm-8">
					<div class="input-icon">
						<i class="fa fa-asterisk"></i> <input type="text"
							class="form-control" name="userName" maxlength="100"
							placeholder="请输入用户名" data-bv-notempty
							data-bv-notempty-message="请输入用户名">
					</div>
				</div>
			</div>
		</div>
		<div class="col-xs-6">
			<div class="form-group">
				<label for="fullName" class="col-sm-4 control-label">真实姓名</label>
				<div class="col-sm-8">
					<div class="input-icon">
						<i class="fa fa-asterisk"></i> <input type="text"
							class="form-control" name="fullName" maxlength="30"
							placeholder="请输入真实姓名" data-bv-notempty
							data-bv-notempty-message="请输入真实姓名">
					</div>
				</div>
			</div>
		</div>
	</div>
	<c:if test="${param.flag!='edit'}">
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
					<label for="password" class="col-sm-4 control-label">用户密码</label>
					<div class="col-sm-8">
						<div class="input-icon">
							<i class="fa fa-asterisk"></i><input type="password"
								class="form-control" name="password" id="password"
								placeholder="请输入用户密码" data-bv-notempty
								data-bv-notempty-message="请输入用户密码" data-bv-identical="true"
								maxlength="50" data-bv-identical-field="confirmPassword"
								data-bv-identical-message="前后密码不一致" /> <span
								class="fa fa-eye-slash view-password"></span>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<label for="confirmPassword" class="col-sm-4 control-label">确认密码
					</label>
					<div class="col-sm-8">
						<div class="input-icon">
							<i class="fa fa-asterisk"></i> <input type="password"
								class="form-control" id="confirmPassword" name="confirmPassword"
								placeholder="请再次输入密码" maxlength="50" data-bv-notempty
								data-bv-notempty-message="请再次输入密码" data-bv-identical="true"
								data-bv-identical-field="password"
								data-bv-identical-message="前后密码不一致" /><span
								class="fa fa-eye-slash view-password"></span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:if>
	<div class="row">
		<div class="col-xs-12">
			<div class="form-group">
				<label for="roleIds" class="col-sm-2 control-label">角色名称</label>
				<div class="col-sm-10">
					<input type="hidden" class="form-control" name="roleName"
						id="roleName" />
					<div class="input-icon">
						<i class="fa fa-asterisk"></i><select name="roleIds"
							multiple="multiple" data-placeholder="请选择角色"
							data-direction="down" data-show-checkall="true"
							data-show-checker="true" data-show-filter="true"
							data-select-all-text="全选" data-bv-notempty
							data-bv-notempty-message="请至少选择一个角色" class="bsplus-select"
							id="roleId" data-list-field="list" data-text-field="roleName"
							data-value-field="roleId" data-url="${path}/mgr/role/showAllList">
						</select>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-6">
			<div class="form-group">
				<label class="col-sm-4 control-label">邮箱</label>
				<div class="col-sm-8">
					<input type="email" class="form-control" name="email"
						data-bv-field="email" maxlength="50"
						data-bv-emailaddress-message="邮箱格式错误">
				</div>
			</div>
		</div>
		<div class="col-xs-6">
			<div class="form-group">
				<label class="col-sm-4 control-label">电话号码</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="mobilePhone"
						maxlength="50">
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<div class="form-group">
				<label for="description" class="col-sm-2 control-label">描述</label>
				<div class="col-sm-10">
					<textarea class="form-control" rows="2" name="description"></textarea>
				</div>
			</div>
		</div>
	</div>
</form>