<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<%@ taglib prefix="priv" uri="/mytag/privilege"%>
<div id="member-list" class="bsplus-grid"
	data-url="${path}/member/mgr/showList" data-sort-field="id"
	data-sort-order="desc" data-id-field="id" data-scroll-height="400px"
	data-empty-text="暂时没有相关数据" data-show-config="true"
	data-allow-wrap="true" data-scroll-position="top-bottom"
	data-multi-select="false" data-scroll-width="2000px"
	data-left-width="350px" data-right-width="400px" data-sort-all="true">
	<div class="grid-head">
		<priv:priv-btn btnname="search">
			<div class="grid-query">
				<form>
					<div class="form-group form-group-sm">
						<div class="input-group">
							<span class="input-group-addon">会员名称</span><input type="text"
								name="userName" class="form-control"
								placeholder="请输入会员姓名，支持模糊查询" />
						</div>
						<div class="input-group">
							<span class="input-group-addon">用户姓名</span><input type="text"
								name="fullName" class="form-control"
								placeholder="请输入用户姓名，支持模糊查询" />
						</div>
						<div class="input-group">
							<span class="input-group-addon">电话号码</span><input type="text"
								name="mobile" class="form-control" placeholder="请输入电话号码，支持模糊查询" />
						</div>
						
						<div class="input-group">
							<span class="input-group-addon">钱包地址</span><input type="text"
								name="uuid" class="form-control" placeholder="请输入地址，支持模糊查询" />
						</div>
						
						<div class="input-group">
							<span class="input-group-addon">用户状态</span><select name="status"
								data-show-checker="true" class="bsplus-select" data-width="150">
								<option value="">全部</option>
								<option value="0">锁定</option>
								<option value="1">活动</option>
							</select>
						</div>
						<div class="input-group">
							<span class="input-group-addon">认证状态</span><select
								name="verifiedStatus" data-show-checker="true"
								class="bsplus-select" data-width="150">
								<option value="">全部</option>
								<option value="0">未认证</option>
								<option value="1">已认证</option>
							</select>
						</div>
						<div class="input-group">
							<span class="input-group-addon">激活状态</span><select
								name="activateStatus" data-show-checker="true"
								class="bsplus-select" data-width="150">
								<option value="">全部</option>
								<option value="0">未激活</option>
								<option value="1">已激活</option>
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
	</div>
	<div class="grid-functions">
		<div class="form-group">
			<priv:priv-btn btnname="edit">
				<button type="button" class="btn btn-info btn-sm" data-action="edit"
					data-options="{url:'${path}/member/mgr/memberform',title:'编辑用户',width:'60%',dataUrl:'${path}/member/mgr/showUpdate',overflow:'visible'}">
					<span class="fa fa-edit" aria-hidden="true"></span>&nbsp; 编辑
				</button>
			</priv:priv-btn>
			<priv:priv-btn btnname="lock">
				<button type="button" class="btn btn-info btn-sm" data-action="add"
					data-beforeaction="Member.lock">
					<span class="fa fa-lock" aria-hidden="true"></span>&nbsp; 锁定/解锁
				</button>
			</priv:priv-btn>
			<priv:priv-btn btnname="recharge">
				<button type="button" class="btn btn-info btn-sm" data-action="edit"
					data-beforeaction="Member.recharge"
					data-options="{url:'${path}/member/mgr/rechargeform',title:'大盘充值',width:'50%',dataUrl:'${path}/member/mgr/showUpdate'}">
					<span class="fa fa-edit" aria-hidden="true"></span>&nbsp;大盘充值
				</button>
			</priv:priv-btn>
			<priv:priv-btn btnname="edit">
				<button type="button" class="btn btn-info btn-sm" data-action="edit"
					data-options="{url:'${path}/member/mgr/updatepassword',title:'重置密码',width:'60%',dataUrl:'${path}/member/mgr/showUpdate',overflow:'visible'}">
					<span class="fa fa-edit" aria-hidden="true"></span>&nbsp; 重置密码
				</button>
			</priv:priv-btn>
		</div>
	</div>
	<div class="grid-body">
		<div class="bsplus-table table-bordered">
			<div class="table-fields">
				<div data-type="index" data-common-cls="text-center" data-width="4%"
					data-visible="false" data-fixed="left">序号</div>
				<div data-type="select" data-common-cls="text-center"
					data-width="4%" data-fixed="left">选择</div>
				<div data-field="userName" data-common-cls="text-center"
					data-allow-sort="true" data-width="8%" data-fixed="left">会员名称</div>
				<div data-field="fullName" data-common-cls="text-center"
					data-allow-sort="true" data-width="8%" data-fixed="left">用户姓名</div>
				<div data-field="mobile" data-common-cls="text-center"
					data-allow-sort="true" data-width="8%" data-fixed="left">电话号码</div>
				<!-- <div data-type="img" data-default-img=" "
					data-common-cls="text-center" data-field="head"
					data-allow-sort="true" data-width="5%">头像</div>
				<div data-type="img" data-default-img=" " data-field="positivePhoto"
					data-common-cls="text-center" data-allow-sort="true"
					data-width="5%">身份证【正】</div>
				<div data-type="img" data-default-img=" " data-field="negativePhoto"
					data-common-cls="text-center" data-allow-sort="true"
					data-width="5%">身份证【反】</div> -->
				<div data-field="remark" data-common-cls="text-center"
					data-show-tips="true" data-allow-sort="true" data-width="8%">备注</div>
				<div data-common-cls="text-center" data-field="recommendNum"
					data-allow-sort="true" data-width="4%">推荐人数</div>
				<div data-common-cls="text-center" data-field="recommMoney"
					data-allow-sort="true" data-width="5%" data-before="￥">推荐总业绩</div>
					<div data-common-cls="text-center" data-field="mycode"
					data-allow-sort="true" data-width="8%" data-help-text="注册时填写的推荐码">我的推荐码</div>
				<div data-common-cls="text-center" data-field="adCode"
					data-allow-sort="true" data-width="8%" data-help-text="注册时填写的推荐码">我的推荐人</div>
				<div data-common-cls="text-center" data-field="money"
					data-width="5%" data-allow-sort="true" data-before="￥">现金余额</div>
				<div data-common-cls="text-center" data-field="integral"
					data-allow-sort="true" data-width="6%">可用智联币</div>
				<!-- <div data-common-cls="text-center" data-field="freezeIntegral"
					data-allow-sort="true" data-width="6%">冻结银多宝</div> -->
				<div data-common-cls="text-center" data-field="silver"
					data-allow-sort="true" data-width="6%">能量值</div>
				<!-- <div data-common-cls="text-center" data-field="address"
					data-allow-sort="true" data-width="6%">收货地址</div> -->
				<div data-common-cls="text-center" data-field="cardNumber"
					data-allow-sort="true" data-width="6%">银行卡号</div>
					<div data-common-cls="text-center" data-field="uuid"
					data-allow-sort="true" data-width="6%">钱包地址</div>
				<div data-common-cls="text-center" data-field="bank"
					data-allow-sort="true" data-width="6%">开户行</div>
				<div data-common-cls="text-center" data-field="idCard"
					data-allow-sort="true" data-width="6%">身份证号</div>
				<div data-field="addTime" data-date-format="yyyy/MM/dd hh:mm:ss"
					data-allow-sort="true" data-common-cls="text-center"
					data-width="10%">添加时间</div>
				<div data-common-cls="text-center" data-field="status"
					data-allow-sort="true" data-width="6%"
					data-render-map="{0:'锁定',1:'活动'}" data-fixed="right">用户状态</div>
				<div data-common-cls="text-center" data-field="verifiedStatus"
					data-allow-sort="true" data-width="6%"
					data-render-map="{0:'未认证',1:'已认证'}" data-fixed="right">认证状态</div>
				<!-- <div data-common-cls="text-center" data-field="activateStatus"
					data-allow-sort="true" data-width="6%"
					data-render-map="{0:'未激活',1:'已激活'}" data-fixed="right">激活状态</div> -->
			</div>
		</div>
	</div>
</div>
<script src="${path}/js/member/member.js" type="text/javascript"
	charset="UTF-8"></script>