<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="priv" uri="/mytag/privilege"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<div class="bsplus-grid" data-url="${path}/order/mgr/showList"
	id="order-grid-list" data-sort-field="id" data-sort-order="desc"
	data-size-list="[10,50,100,200]" data-id-field="id"
	data-scroll-height="400px" data-empty-text="暂时没有相关数据"
	data-show-config="true" data-scroll-width="1000px"
	data-left-width="400px" data-right-width="420px" data-sort-all="true"
	data-multi-select="true" data-scroll-position="top-bottom">
	<div class="grid-head">
		<priv:priv-btn btnname="search">
			<div class="grid-query">
				<form>
					<div class="form-group form-group-sm">
						<div class="input-group">
							<span class="input-group-addon">电话号码</span><input type="text"
								name="mobile" class="form-control" placeholder="请输入电话号码"
								style="width: 150px;" />
						</div>
						<div class="input-group">
							<span class="input-group-addon">汇款人姓名</span><input type="text"
								name="fullName" class="form-control" placeholder="请输入汇款人姓名"
								style="width: 150px;" />
						</div>
						<div class="input-group">
							<span class="input-group-addon">订单状态</span><select name="status"
								data-show-checker="true" class="bsplus-select" data-width="150">
								<option value="">全部</option>
								<option value="0">待审核</option>
								<option value="1">已审核</option>
							</select>
						</div>
						<div class="input-group">
							<span class="input-group-addon">支付状态</span><select
								name="payStatus" data-show-checker="true" class="bsplus-select"
								data-width="150">
								<option value="">全部</option>
								<option value="0">待支付</option>
								<option value="1">已支付</option>
								<option value="2">金额异常</option>
							</select>
						</div>
						<button type="button" class="btn blue-haze btn-sm"
							data-action="search">
							<span class="fa fa-search" aria-hidden="true"></span>&nbsp; 查询
						</button>
						<button type="button" class="btn btn-default btn-sm"
							data-action="reset">
							<span class="fa fa-trash" aria-hidden="true"></span>&nbsp; 清空
						</button>
					</div>
				</form>
			</div>
		</priv:priv-btn>
		<div class="grid-functions">
			<div class="form-group">
				<priv:priv-btn btnname="check">
					<button type="button" class="btn green-haze btn-sm"
						data-action="edit" data-beforeaction="Order.beforeCheck"
						data-options="{url:'${path}/order/mgr/orderform',dataUrl:'${path}/order/mgr/showUpdate',saveBtnText:'确认审核',title:'订单审核',iconCls:'fa fa-search',width:'60%'}">
						<span class="fa fa-search" aria-hidden="true"></span>&nbsp; 审核订单
					</button>
				</priv:priv-btn>
				<priv:priv-btn btnname="delete">
					<button type="button" class="btn btn-danger btn-sm"
						data-action="delete"
						data-options="{url:'${path}/order/mgr/deleteOrder'}">
						<span class="fa fa-remove" aria-hidden="true"></span>&nbsp; 删除选中订单
					</button>
				</priv:priv-btn>
			</div>
		</div>
	</div>
	<div class="grid-body">
		<div class="bsplus-table table-bordered">
			<div class="table-fields">
				<div data-type="index" data-common-cls="text-center" data-width="3%"
					data-visible="false" data-fixed="left">序号</div>
				<div data-type="select" data-common-cls="text-center"
					data-width="3%" data-fixed="left">选择</div>
				<div data-field="mobile" data-common-cls="text-center"
					data-allow-sort="true" data-width="8%" data-fixed="left">电话号码</div>
				<div data-field="fullName" data-common-cls="text-center"
					data-allow-sort="true" data-width="7%" data-fixed="left">用户姓名</div>
				<div data-field="orderNo" data-common-cls="text-center"
					data-allow-sort="true" data-width="15%" data-fixed="left">订单编号</div>
				<!-- <div data-field="address" data-common-cls="text-center"
					data-allow-sort="true" data-width="12%">配送地址</div> -->
				<!-- <div data-field="price" data-common-cls="text-center"
					data-allow-sort="true" data-width="8%">购买单价</div> -->
				<div data-field="quantity" data-common-cls="text-center"
					data-allow-sort="true" data-width="8%">充值金额</div>
				<!-- <div data-field="integral" data-common-cls="text-center"
					data-allow-sort="true" data-width="10%">智联币数</div> -->
				<div data-field="payType1" data-common-cls="text-center"
					data-allow-sort="true" data-width="5%"
					data-render-map="{1:'支付宝',2:'微信',3:'银行卡'}">支付类型</div>
				<div data-field="fullName" data-common-cls="text-center"
					data-allow-sort="true" data-width="5%">汇款人姓名</div>
				<!-- <div data-field="totalAmount" data-common-cls="text-center"
					data-allow-sort="true" data-width="6%">订单总金额</div>
				<div data-field="rechargeAmount" data-common-cls="text-center"
					data-allow-sort="true" data-width="6%">支付金额</div> -->
				<div data-field="payNumber" data-common-cls="text-center"
					data-allow-sort="true" data-width="10%">支付账号</div>
				<div data-field="payOrderNo" data-common-cls="text-center"
					data-allow-sort="true" data-width="10%">支付单号</div>
				<div data-field="addTime" data-date-format="yyyy/MM/dd hh:mm:ss"
					data-allow-sort="true" data-common-cls="text-center"
					data-width="10%">购买时间</div>
				<div data-field="checkTime" data-date-format="yyyy/MM/dd hh:mm:ss"
					data-allow-sort="true" data-common-cls="text-center"
					data-width="10%" data-visible="false">审核时间</div>
				<div data-field="remark" data-common-cls="text-center"
					data-visible="false">备注</div>
				<div data-field="status" data-common-cls="text-center"
					data-allow-sort="true" data-width="8%"
					data-render-map="{0:'待审核',1:'已审核'}" data-fixed="right">订单状态</div>
				<div data-field="payStatus" data-common-cls="text-center"
					data-allow-sort="true" data-width="8%"
					data-render-map="{0:'待支付',1:'已支付',2:'支付金额异常'}" data-fixed="right">支付状态</div>
				<div data-field="checkMsg" data-common-cls="text-center"
					data-allow-sort="true" data-width="8%" data-fixed="right">审核信息</div>
			</div>
		</div>
	</div>
</div>
<script src="${path}/js/order/order.js" type="text/javascript"
	charset="UTF-8"></script>
