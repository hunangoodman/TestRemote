<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="priv" uri="/mytag/privilege"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<div class="bsplus-grid" data-url="${path}/mgr/function/showList"
	data-sort-field="functionId" data-sort-order="desc"
	data-id-field="functionId" data-empty-text="暂时没有相关数据"
	data-scroll-height="400px">
	<div class="grid-head">
		<priv:priv-btn btnname="search">
			<div class="grid-query">
				<form>
					<div class="form-group form-group-sm">
						<div class="input-group">
							<span class="input-group-addon">key值 </span><input type="text"
								name="code" class="form-control" placeholder="请输入Key值" />
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
						data-options="{url:'${path}/mgr/function/functionform',title:'添加',inIframe:false,dragable:true}">
						<span class="fa fa-plus" aria-hidden="true"></span>&nbsp; 添加
					</button>
				</priv:priv-btn>
				<priv:priv-btn btnname="edit">
					<button type="button" class="btn btn-info btn-sm"
						data-action="edit"
						data-options="{url:'${path}/mgr/function/functionform',title:'编辑',dataUrl:'${path}/mgr/function/showUpdate',inIframe:false}">
						<span class="fa fa-edit" aria-hidden="true"></span>&nbsp; 编辑
					</button>
				</priv:priv-btn>
				<priv:priv-btn btnname="delete">
					<button type="button" class="btn btn-danger btn-sm"
						data-action="delete"
						data-options="{url:'${path}/mgr/function/deleteFunction'}">
						<span class="fa fa-remove" aria-hidden="true"></span>&nbsp; 删除
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
				<div data-field="functionName" data-common-cls="text-center"
					data-width="20%">按钮名称</div>
				<div data-field="code" data-common-cls="text-center">Key值</div>
				<div data-field="description" data-common-cls="text-center"
					data-width="30%">描述</div>
			</div>
		</div>
	</div>
</div>