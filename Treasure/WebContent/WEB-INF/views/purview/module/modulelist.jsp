<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="priv" uri="/mytag/privilege"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<div class="bsplus-grid" data-grid-type="treegrid"
	data-id-field="moduleId" data-parent-field="moduleId"
	data-child-field="parentId" data-tree-column="moduleName"
	data-url="${path}/mgr/module/showList" data-sort-field="moduleId"
	data-sort-order="desc" data-show-pager="false"
	data-expand-tree-onload="true" data-multi-select="false"
	data-scroll-height="400px" data-empty-text="暂时没有相关数据">
	<div class="grid-head">
		<div class="grid-functions">
			<div class="form-group">
				<priv:priv-btn btnname="add">
					<button type="button" class="btn green-haze btn-sm ani"
						data-action="add"
						data-options="{url:'${path}/mgr/module/moduleform',title:'添加模块',width:'60%'}">
						<span class="fa fa-plus" aria-hidden="true"></span>&nbsp;添加
					</button>
				</priv:priv-btn>
				<priv:priv-btn btnname="edit">
					<button type="button" class="btn btn-info btn-sm ani"
						data-action="edit"
						data-options="{url:'${path}/mgr/module/moduleform',title:'编辑模块',width:'60%',dataUrl:'${path}/mgr/module/showUpdate'}">
						<span class="fa fa-edit" aria-hidden="true"></span>&nbsp; 编辑
					</button>
				</priv:priv-btn>
				<priv:priv-btn btnname="delete">
					<button type="button" class="btn btn-danger btn-sm"
						data-action="delete"
						data-options="{url:'${path}/mgr/module/deleteModule'}">
						<span class="fa fa-remove" aria-hidden="true"></span>&nbsp; 删除
					</button>
				</priv:priv-btn>
			</div>
		</div>
	</div>
	<div class="grid-body">
		<div class="bsplus-table table-bordered">
			<div class="table-fields">
				<div data-type="index" data-common-cls="text-center" data-width="5%">序号</div>
				<div data-type="select" data-common-cls="text-center"
					data-width="5%">选择</div>
				<div data-field="moduleName" data-width="20%">模块名称</div>
				<div data-field="path" data-common-cls="text-center">模块路径</div>
				<div data-field="icon" data-common-cls="text-center"
					data-width="10%" data-renderer="rendererIcon">图标</div>
				<div data-field="isUsed" data-common-cls="text-center"
					data-renderer="rendererIsUsed" data-width="5%">是否启用</div>
				<div data-field="openType" data-common-cls="text-center"
					data-width="5%">模块排序</div>
				<div data-field="moduleId" data-common-cls="text-center"
					data-renderer="rendererFunction" data-width="25%">功能按钮</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function rendererIsUsed(e) {
		if (e.value == "1") {
			return "启用";
		}
		return "禁用";
	}
	function rendererIcon(e) {
		if (e.value) {
			return "<span class='"+e.value+"' aria-hidden='true'></span>";
		}
		return "";
	}
	function rendererFunction(e) {
		var functions = e.record.functions;
		if (functions != null && functions != undefined && functions.length > 0) {
			var fNames = [];
			for (var i = 0; i < functions.length; i++) {
				fNames.push(functions[i].functionName);
			}
			return fNames.join(",");
		}
		return "";
	}
</script>