<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<form class="form-horizontal" method="post" action="${path}/order/mgr/check">
	<input name="id" type="hidden" /> <input name="memberId" type="hidden" />
	<div class="modal-body">
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-4 control-label">订单编号</label>
					<div class="col-sm-8">
						<input type="text" class="readonly" name="orderNo" readonly="readonly" />
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-4 control-label">支付单号</label>
					<div class="col-sm-8">
						<input type="text" class="readonly" name="payOrderNo" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-4 control-label">电话号码</label>
					<div class="col-sm-8">
						<input type="text" class="readonly" name="mobile" readonly="readonly" />
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-4 control-label">汇款姓名</label>
					<div class="col-sm-8">
						<input type="text" class="readonly" name="fullName" readonly="readonly" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<!-- <div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-4 control-label">购买单价</label>
					<div class="col-sm-8">
						<input type="text" class="readonly" name="price" readonly="readonly" />
					</div>
				</div>
			</div> -->
			
			<div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-4 control-label">充值金额</label>
					<div class="col-sm-8">
						<input type="text" class="readonly" name="quantity" readonly="readonly" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-4 control-label">支付类型</label>
					<div class="col-sm-8">
						<!-- <input type="text" class="readonly" name="payType1" /> -->
						<select name="payType1" data-show-checker="true" class="bsplus-select">
							<option value="1">支付宝</option>
							<option value="2">微信</option>
							<option value="3">银行卡</option>
						</select>
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-4 control-label">支付账号</label>
					<div class="col-sm-8">
						<input type="text" class="readonly" name="payNumber" />
					</div>
				</div>
			</div>
		</div>
		<!-- <div class="row">
			<div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-4 control-label">总金额</label>
					<div class="col-sm-8">
						<input type="text" class="readonly" name="totalAmount" readonly="readonly" />
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-4 control-label">支付金额</label>
					<div class="col-sm-8">
						<input type="text" class="readonly" name="rechargeAmount" />
					</div>
				</div>
			</div>
		</div> -->
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">支付状态</label>
			<div class="col-sm-10">
				<select name="payStatus" data-show-checker="true" class="bsplus-select">
					<option value="0">待支付</option>
					<option value="1">已支付</option>
					<option value="2">金额异常</option>
				</select>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-2 control-label">审核信息</label>
					<div class="col-sm-10">
						<textarea class="form-control" rows="1" name="checkMsg"></textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="form-group">
					<label class="col-sm-2 control-label">订单备注</label>
					<div class="col-sm-10">
						<textarea class="form-control" rows="1" name="remark"></textarea>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>