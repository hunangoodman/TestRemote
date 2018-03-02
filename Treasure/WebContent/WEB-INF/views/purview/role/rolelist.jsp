<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="priv" uri="/mytag/privilege"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<div class="bsplus-grid" data-url="${path}/mgr/role/showList"
	id="rolelist-dataGrid1" data-sort-field="roleId" data-sort-order="desc"
	data-size-list="[10,50,100,200]" data-id-field="roleId"
	data-scroll-height="400px" data-empty-text="暂时没有相关数据">
	<div class="grid-head">
		<priv:priv-btn btnname="search">
			<div class="grid-query">
				<form>
					<div class="form-group form-group-sm">
						<div class="input-group">
							<span class="input-group-addon">角色名称</span><input type="text"
								name="roleName" class="form-control" />
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
				<priv:priv-btn btnname="add">
					<button type="button" class="btn green-haze btn-sm"
						data-action="add"
						data-options="{url:'${path}/mgr/role/roleform',title:'添加角色',refrshType:1,dragAble:false}">
						<span class="fa fa-plus" aria-hidden="true"></span>&nbsp; 添加
					</button>
				</priv:priv-btn>
				<priv:priv-btn btnname="edit">
					<button type="button" class="btn btn-info btn-sm"
						data-action="edit"
						data-options="{url:'${path}/mgr/role/roleform',title:'编辑',dataUrl:'${path}/mgr/role/showUpdate',inIframe:false}">
						<span class="fa fa-edit" aria-hidden="true"></span>&nbsp; 编辑
					</button>
				</priv:priv-btn>
				<priv:priv-btn btnname="delete">
					<button type="button" class="btn btn-danger btn-sm"
						data-action="delete"
						data-options="{url:'${path}/mgr/role/deleteRole'}">
						<span class="fa fa-remove" aria-hidden="true"></span>&nbsp; 删除
					</button>
				</priv:priv-btn>
				<priv:priv-btn btnname="assign">
					<button type="button" class="btn green-haze btn-sm"
						id="bootbox-roleresource">
						<span class="fa fa-hand-o-right" aria-hidden="true"></span>&nbsp;
						分配权限
					</button>
				</priv:priv-btn>
			</div>
		</div>
	</div>
	<div class="grid-body">
		<div class="bsplus-table table-bordered">
			<div class="table-fields">
				<div data-type="index" data-common-cls="text-center"
					data-width="10%">序号</div>
				<div data-type="select" data-common-cls="text-center"
					data-width="10%">选择</div>
				<div data-field="roleName" data-common-cls="text-center"
					data-allow-sort="true">角色名称</div>
				<div data-field="addTime" data-date-format="yyyy/MM/dd hh:mm:ss"
					data-allow-sort="true" data-common-cls="text-center"
					data-width="20%">添加时间</div>
				<div data-field="description" data-common-cls="text-center"
					data-show-tips="true">描述</div>
			</div>
		</div>
	</div>
</div>
<script src="${path}/js/role/role.js" type="text/javascript"
	charset="UTF-8"></script>
