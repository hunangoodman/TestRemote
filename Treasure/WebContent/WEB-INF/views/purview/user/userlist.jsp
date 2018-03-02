<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<%@ taglib prefix="priv" uri="/mytag/privilege"%>
<div class="bsplus-grid" data-url="${path}/mgr/user/showList"
	id="user-list-grid" data-sort-field="userId" data-sort-order="desc"
	data-id-field="userId" data-scroll-height="400px" data-show-foot="true"
	data-empty-text="暂时没有相关数据" data-show-config="true" data-allow-wrap="true">
	<div class="grid-head">
		<priv:priv-btn btnname="search">
			<div class="grid-query">
				<form>
					<div class="form-group form-group-sm">
						<div class="input-group">
							<span class="input-group-addon">用户名称</span><input type="text"
								name="userName" class="form-control" placeholder="请输入用户名" />
						</div>
						<div class="input-group">
							<span class="input-group-addon">真实姓名</span><input type="text"
								name="fullName" class="form-control" placeholder="请输入真实姓名" />
						</div>
						<!-- <div class="input-group">
							<span class="input-group-addon">电话号码</span><input type="text"
								name="mobilePhone" class="form-control" placeholder="请输入电话号码" />
						</div> -->
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
				<priv:priv-btn btnname="add">
					<button type="button" class="btn green-haze btn-sm"
						data-action="add"
						data-options="{url:'${path}/mgr/user/userform?flag=add',title:'添加用户',width:'60%',overflow:'visible'}">
						<span class="fa fa-plus" aria-hidden="true"></span>&nbsp; 添加
					</button>
				</priv:priv-btn>
				<priv:priv-btn btnname="edit">
					<button type="button" class="btn btn-info btn-sm"
						data-action="edit"
						data-options="{url:'${path}/mgr/user/userform?flag=edit',title:'编辑用户',width:'60%',dataUrl:'${path}/mgr/user/showUpdate',overflow:'visible'}">
						<span class="fa fa-edit" aria-hidden="true"></span>&nbsp; 编辑
					</button>
				</priv:priv-btn>
				<priv:priv-btn btnname="delete">
					<button type="button" class="btn btn-danger btn-sm"
						data-options="{url:'${path}/mgr/user/deleteUser'}"
						data-action="delete">
						<span class="fa fa-remove" aria-hidden="true"></span>&nbsp; 删除
					</button>
				</priv:priv-btn>
				<priv:priv-btn btnname="lock">
					<button type="button" class="btn btn-info btn-sm" data-action="add"
						data-beforeaction="User.lock">
						<span class="fa fa-lock" aria-hidden="true"></span>&nbsp; 锁定/解锁
					</button>
				</priv:priv-btn>
				<priv:priv-btn btnname="resetpassword">
					<button type="button" class="btn green-haze btn-sm"
						data-action="add" data-beforeaction="User.resetPassword">
						<span class="fa fa-repeat" aria-hidden="true"></span>&nbsp; 重置密码
					</button>
				</priv:priv-btn>
			</div>
		</div>
	</div>
	<div class="grid-body">
		<div class="bsplus-table table-bordered">
			<div class="table-fields">
				<div data-type="index" data-common-cls="text-center" data-width="4%"
					data-visible="false">序号</div>
				<div data-type="select" data-common-cls="text-center"
					data-width="4%">选择</div>
				<div data-field="userName" data-common-cls="text-center"
					data-allow-sort="true" data-width="8%">用户名称</div>
				<div data-field="fullName" data-common-cls="text-center"
					data-allow-sort="true" data-width="8%">真实姓名</div>
				<div data-common-cls="text-center" data-field="userId"
					data-width="10%" data-renderer="User.renderShop">所属店铺</div>
				<div data-common-cls="text-center" data-field="email"
					data-allow-sort="true" data-width="8%">邮箱</div>
				<div data-common-cls="text-center" data-field="status"
					data-width="5%" data-renderer="User.renderStatus"
					data-allow-sort="true">用户状态</div>
				<div data-common-cls="text-center" data-field="mobilePhone"
					data-allow-sort="true" data-width="6%" data-visible="false">电话号码</div>
				<div data-field="addTime" data-date-format="yyyy/MM/dd hh:mm:ss"
					data-allow-sort="true" data-common-cls="text-center"
					data-width="10%">添加时间</div>
				<div data-field="description" data-common-cls="text-center"
					data-show-tips="true" data-allow-sort="true" data-width="8%">描述</div>
				<div data-common-cls="text-center" data-field="userId"
					data-renderer="User.renderRole" data-width="6%">角色名称</div>
			</div>
		</div>
	</div>
</div>
<script src="${path}/js/user/user.js" type="text/javascript"
	charset="UTF-8"></script>