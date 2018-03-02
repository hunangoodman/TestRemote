<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<div class="portlet" style="margin-top: 20px;">
	<div class="portlet-title">
		<div class="title">分配权限——角色:${role.roleName}</div>
		<div class="tools">
			<a class="portlet-collapse" href="javascript:void(0)"></a> <input id="roleId"
				value="${role.roleId}" type="hidden" />
		</div>
	</div>
	<div class="portlet-body">
		<div class="bsplus-grid" id="module-select-grid" data-grid-type="treegrid" data-id-field="moduleId"
			data-parent-field="moduleId" data-child-field="parentId" data-tree-column="moduleName"
			data-url="${path}/mgr/module/showModuleList?roleId=${role.roleId}" data-sort-field="moduleId"
			data-sort-order="desc" data-show-pager="false" data-expand-tree-onload="true"
			data-multi-select="true" data-select-onrowclick="false" data-scroll-height="450px"
			data-empty-text="暂时没有相关数据" data-select-all-parent="true" data-deselect-all-children="true"
			data-ondataload="PlusRole.ondataload">
			<div class="grid-head">
				<div class="grid-functions">
					<div class="form-group">
						<button type="button" class="btn btn-info btn-sm" onclick="PlusRole.saveData()">
							<span class="fa fa-save" aria-hidden="true"></span>&nbsp; 保存
						</button>
					</div>
				</div>
			</div>
			<div class="grid-body">
				<div class="bsplus-table table-bordered">
					<div class="table-fields">
						<div data-type="select" data-common-cls="text-center" data-width="5%">选择</div>
						<div data-field="moduleName" data-width="25%">模块名称</div>
						<div data-field="moduleId" data-renderer="PlusRole.renderFunction" data-width="70%">功能按钮</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>