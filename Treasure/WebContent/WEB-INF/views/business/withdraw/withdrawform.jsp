<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<form class="form-horizontal" method="post" action="${path}/withdraw/mgr/check">
	<input name="id" type="hidden" /> <input name="memberId" type="hidden" />
	<div class="modal-body">
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-4 control-label">用户姓名</label>
					<div class="col-sm-8">
						<input type="text" class="readonly" name="fullName" readonly="readonly" />
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-4 control-label">电话号码</label>
					<div class="col-sm-8">
						<input type="text" class="readonly" name="mobile" readonly="readonly" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-4 control-label">提现卡号</label>
					<div class="col-sm-8">
						<input type="text" class="readonly" name="cardNumber" readonly="readonly" />
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-4 control-label">提现金额</label>
					<div class="col-sm-8">
						<input type="text" class="readonly" name="amount" readonly="readonly" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-4 control-label">提现费率</label>
					<div class="col-sm-8">
						<input type="text" class="readonly" name="procedureRates" readonly="readonly" />
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-4 control-label">手续费用</label>
					<div class="col-sm-8">
						<input type="text" class="readonly" name="procedureMoney" readonly="readonly" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-4 control-label">实际到账金额</label>
					<div class="col-sm-8">
						<input type="text" class="readonly" name="realityMoney" readonly="readonly" />
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-4 control-label">申请时间</label>
					<div class="col-sm-8">
						<input type="text" class="readonly" name="applyTime1" readonly="readonly" />
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">支付状态</label>
			<div class="col-sm-10">
				<select name="checkStatus" data-show-checker="true" class="bsplus-select">
					<option value="0">待支付</option>
					<option value="1">提现成功</option>
					<option value="2">提现失败</option>
				</select>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-2 control-label">审核信息</label>
					<div class="col-sm-10">
						<textarea class="form-control" rows="1" name="remark"></textarea>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>