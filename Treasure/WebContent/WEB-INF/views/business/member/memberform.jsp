<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<form class="form-horizontal" method="post"
	action="${path}/member/mgr/updateMember">
	<input name="id" type="hidden" />
	<div class="row">
		<div class="col-xs-6">
			<div class="form-group">
				<label class="col-sm-4 control-label">用户姓名</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="fullName"
						maxlength="100" placeholder="请输入用户姓名">
				</div>
			</div>
		</div>
		<div class="col-xs-6">
			<div class="form-group">
				<label class="col-sm-4 control-label">电话号码</label>
				<div class="col-sm-8">
					<div class="input-icon">
						<i class="fa fa-asterisk"></i> <input type="tel"
							class="form-control" name="mobile" maxlength="30"
							placeholder="请输入电话号码" data-bv-notempty
							data-bv-notempty-message="电话号码不能为空">
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-6">
			<div class="form-group">
				<label class="col-sm-4 control-label">用户地址</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="address"
						placeholder="请输入用户地址">
				</div>
			</div>
		</div>
		<div class="col-xs-6">
			<div class="form-group">
				<label class="col-sm-4 control-label">身份证号码</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="idCard"
						maxlength="18" placeholder="请输入身份证号码">
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-6">
			<div class="form-group">
				<label class="col-sm-4 control-label">银行卡号</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="cardNumber"
						placeholder="请输入银行卡号">
				</div>
			</div>
		</div>
		<div class="col-xs-6">
			<div class="form-group">
				<label class="col-sm-4 control-label">我的推荐码</label>
				<div class="col-sm-8">
					<input type="text" class="readonly" name="mycode"
						readonly="readonly">
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<div class="form-group">
				<label class="col-sm-2 control-label">备注</label>
				<div class="col-sm-10">
					<textarea class="form-control" rows="2" name="remark"
						placeholder="请输入用户备注"></textarea>
				</div>
			</div>
		</div>
	</div>
</form>