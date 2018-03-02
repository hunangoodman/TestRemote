<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="priv" uri="/mytag/privilege"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<div class="bsplus-grid" data-url="${path}/withdraw/mgr/showList" id="withdraw-grid-list"
	data-sort-field="id" data-sort-order="desc" data-size-list="[10,50,100,200]" data-id-field="id"
	data-scroll-height="400px" data-empty-text="暂时没有相关数据" data-show-config="true"
	data-multi-select="false" data-scroll-position="top-bottom">
	<div class="grid-head">
		<priv:priv-btn btnname="search">
			<div class="grid-query">
				<form>
					<div class="form-group form-group-sm">
						<div class="input-group">
							<span class="input-group-addon">电话号码</span><input type="text" name="mobile"
								class="form-control" placeholder="请输入电话号码，支持模糊查询" style="width: 200px;" />
						</div>
						<div class="input-group">
							<span class="input-group-addon">提现卡号</span><input type="text" name="cardNumber"
								class="form-control" placeholder="请输入提现卡号，支持模糊查询" style="width: 200px;" />
						</div>
						<div class="input-group">
							<span class="input-group-addon">审核状态</span><select name="checkStatus"
								data-show-checker="true" class="bsplus-select" data-width="150">
								<option value="">全部</option>
								<option value="0">待审核</option>
								<option value="1">提现成功</option>
								<option value="2">提现失败</option>
							</select>
						</div>
						<button type="button" class="btn blue-haze btn-sm" data-action="search">
							<span class="fa fa-search" aria-hidden="true"></span>&nbsp; 查询
						</button>
						<button type="button" class="btn btn-default btn-sm" data-action="reset">
							<span class="fa fa-trash" aria-hidden="true"></span>&nbsp; 清空
						</button>
					</div>
				</form>
			</div>
		</priv:priv-btn>
		<div class="grid-functions">
			<div class="form-group">
				<priv:priv-btn btnname="check">
					<button type="button" class="btn green-haze btn-sm" data-action="edit"
						data-beforeaction="Withdraw.beforeCheck"
						data-options="{url:'${path}/withdraw/mgr/withdrawform',dataUrl:'${path}/withdraw/mgr/showUpdate',saveBtnText:'确认审核',title:'提现审核',iconCls:'fa fa-search',width:'60%'}">
						<span class="fa fa-search" aria-hidden="true"></span>&nbsp; 提现审核
					</button>
				</priv:priv-btn>
			</div>
		</div>
	</div>
	<div class="grid-body">
		<div class="bsplus-table table-bordered">
			<div class="table-fields">
				<div data-type="index" data-common-cls="text-center" data-width="3%" data-visible="false">序号</div>
				<div data-type="select" data-common-cls="text-center" data-width="3%">选择</div>
				<div data-field="mobile" data-common-cls="text-center" data-allow-sort="true" data-width="8%">电话号码</div>
				<div data-field="fullName" data-common-cls="text-center" data-allow-sort="true" data-width="8%">用户姓名</div>
				<div data-field="amount" data-common-cls="text-center" data-allow-sort="true" data-width="8%"
					data-before="￥">提现金额</div>
				<div data-field="cardNumber1" data-common-cls="text-center" data-allow-sort="true"
					data-width="8%">提现卡号</div>
				<div data-field="bank" data-common-cls="text-center" data-allow-sort="true" data-width="8%">开户行</div>
				<div data-field="procedureRates" data-common-cls="text-center" data-allow-sort="true"
					data-width="7%">提现手续费率</div>
				<div data-field="procedureMoney" data-common-cls="text-center" data-allow-sort="true"
					data-width="5%" data-before="￥">提现手续费</div>
				<div data-field="realityMoney" data-common-cls="text-center" data-allow-sort="true"
					data-width="5%" data-before="￥">实际到账金额</div>
				<div data-field="applyTime" data-date-format="yyyy/MM/dd hh:mm:ss" data-allow-sort="true"
					data-common-cls="text-center" data-width="8%">申请时间</div>
				<div data-field="checkTime" data-date-format="yyyy/MM/dd hh:mm:ss" data-allow-sort="true"
					data-common-cls="text-center" data-width="8%" data-visible="false">审核时间</div>
				<div data-field="checkStatus" data-common-cls="text-center" data-allow-sort="true"
					data-width="8%" data-render-map="{0:'待审核',1:'提现成功',2:'提现失败'}">审核状态</div>
				<div data-field="remark" data-common-cls="text-center" data-visible="false" data-width="10%">备注</div>
			</div>
		</div>
	</div>
</div>
<script src="${path}/js/withdraw/withdraw.js" type="text/javascript" charset="UTF-8"></script>
