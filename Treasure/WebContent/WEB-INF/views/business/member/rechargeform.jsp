<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<form class="form-horizontal" method="post"
	action="${path}/member/mgr/recharge">
	<input name="id" type="hidden" />
	<div class="row">
		<div class="col-xs-6">
			<div class="form-group">
				<label class="col-sm-4 control-label">用户姓名</label>
				<div class="col-sm-8">
					<input type="text" class="readonly" name="fullName" placeholder=""
						readonly="readonly">
				</div>
			</div>
		</div>
		<div class="col-xs-6">
			<div class="form-group">
				<label class="col-sm-4 control-label">电话号码</label>
				<div class="col-sm-8">
					<input type="tel" class="readonly" name="mobile" placeholder=""
						readonly="readonly">
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-6">
			<div class="form-group">
				<label class="col-sm-4 control-label">用户地址</label>
				<div class="col-sm-8">
					<input type="text" class="readonly" name="address" placeholder=""
						readonly="readonly">
				</div>
			</div>
		</div>
		<div class="col-xs-6">
			<div class="form-group">
				<label class="col-sm-4 control-label">身份证号</label>
				<div class="col-sm-8">
					<input type="text" class="readonly" name="idCard" placeholder=""
						readonly="readonly">
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-6">
			<div class="form-group">
				<label class="col-sm-4 control-label">银行卡号</label>
				<div class="col-sm-8">
					<input type="text" class="readonly" name="cardNumber"
						placeholder="" readonly="readonly">
				</div>
			</div>
		</div>
		<div class="col-xs-6">
			<div class="form-group">
				<label class="col-sm-4 control-label">充币数量</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="rate"
						placeholder="请输入充值数量" data-bv-notempty
						data-bv-notempty-message="请输入充值数量">
				</div>
			</div>
		</div>
	</div>
</form>