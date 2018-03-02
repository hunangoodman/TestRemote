<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<%@ taglib prefix="priv" uri="/mytag/privilege"%>
<div id="member-list" class="bsplus-grid"
	data-url="${path}/consult/showList" data-sort-field="conId"
	data-sort-order="desc" data-id-field="conId" data-scroll-height="400px"
	data-empty-text="暂时没有相关数据" data-show-config="true"
	data-allow-wrap="true" data-scroll-position="top-bottom"
	data-multi-select="false" data-sort-all="true">
	<div class="grid-head">
		<%-- <priv:priv-btn btnname="search"> --%>
			<div class="grid-query">
				<form>
					<div class="form-group form-group-sm">
						<div class="input-group">
							<span class="input-group-addon">标题</span><input type="text"
								name="conHeadline" class="form-control"
								placeholder="请输入标题，支持模糊查询" />
						</div>
						<div class="input-group">
							<span class="input-group-addon">状态</span><select name="status"
								data-show-checker="true" class="bsplus-select" data-width="150">
								<option value="">全部</option>
								<option value="0">新建</option>
								<option value="1">发布</option>
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
		<%-- </priv:priv-btn> --%>
	</div>
	<div class="grid-functions">
		<div class="form-group">
			<priv:priv-btn btnname="add">
					<button type="button" class="btn green-haze btn-sm ani"
						data-action="add"
						data-options="{url:'${path}/consult/consultform',title:'添加资讯',width:'60%'}">
						<span class="fa fa-plus" aria-hidden="true"></span>&nbsp;添加
					</button>
				</priv:priv-btn>
			<priv:priv-btn btnname="edit">
				<button type="button" class="btn btn-info btn-sm" data-action="edit"
					data-options="{url:'${path}/consult/consultform',title:'编辑资讯',width:'60%',dataUrl:'${path}/consult/showUpdate',overflow:'visible'}">
					<span class="fa fa-edit" aria-hidden="true"></span>&nbsp; 编辑
				</button>
			</priv:priv-btn>
			<priv:priv-btn btnname="delete">
				<button type="button" class="btn btn-danger btn-sm"
					data-action="delete"
					data-options="{url:'${path}/consult/deleteConsult'}">
					<span class="fa fa-remove" aria-hidden="true"></span>&nbsp; 删除
				</button>
			</priv:priv-btn>
			<%-- <priv:priv-btn btnname="edit">
				<button type="button" class="btn btn-danger btn-sm"
					data-action="delete"
					data-options="{url:'${path}/consult/issue'}">
					<span class="fa fa-edit" aria-hidden="true"></span>&nbsp; 发布
				</button>
			</priv:priv-btn> --%>
		</div>
	</div>
	<div class="grid-body">
		<div class="bsplus-table table-bordered">
			<div class="table-fields">
				<div data-type="index" data-common-cls="text-center" data-width="4%"
					data-visible="false" data-fixed="left">序号</div>
				<div data-type="select" data-common-cls="text-center"
					data-width="4%" data-fixed="left">选择</div>
				<div data-field="conHeadline" data-common-cls="text-center"
					data-allow-sort="true" data-width="10%" data-fixed="left">标题</div>
				<div data-field="conContent" data-common-cls="text-center"
					data-allow-sort="true" data-width="10%">内容</div>
				<div data-common-cls="text-center" data-field="conAuthor"
					data-allow-sort="true" data-width="4%">作者</div>
				<!-- <div data-field="addDate" data-date-format="yyyy/MM/dd hh:mm:ss"
					data-allow-sort="true" data-common-cls="text-center"
					data-width="4%">添加时间</div> -->
				<div data-field="conDate" data-date-format="yyyy/MM/dd hh:mm:ss"
					data-allow-sort="true" data-common-cls="text-center"
					data-width="4%">发布时间</div>
				<div data-common-cls="text-center" data-field="status"
					data-allow-sort="true" data-width="1%"
					data-render-map="{1:'新建',2:'发布'}" data-fixed="right">状态</div>
			</div>
		</div>
	</div>
</div>
<%-- <script src="${path}/js/member/member.js" type="text/javascript"
	charset="UTF-8"></script> --%>