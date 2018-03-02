<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<%@ taglib prefix="priv" uri="/mytag/privilege"%>
<div id="member-list" class="bsplus-grid"
	data-url="${path}/transfer/mgr/showList1" data-sort-field="id"
	data-sort-order="desc" data-id-field="id" data-scroll-height="400px"
	data-empty-text="暂时没有相关数据" data-show-config="true"
	data-allow-wrap="true" data-scroll-position="top-bottom"
	data-multi-select="false" data-sort-all="true">
	<div class="grid-head">
		<%-- <priv:priv-btn btnname="search"> --%>
			<div class="grid-query">
				<form>
					<div class="form-group form-group-sm">
						
						<div class="input-group">
							<span class="input-group-addon">会员名称</span><input type="text"
								name="userName" class="form-control"
								placeholder="请输入会员名称，支持模糊查询" />
						</div>
						<div class="input-group">
							<span class="input-group-addon">用户姓名</span><input type="text"
								name="fullName" class="form-control"
								placeholder="请输入用户名称，支持模糊查询" />
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

	<div class="grid-body">
		<div class="bsplus-table table-bordered">
			<div class="table-fields">
				<div data-type="index" data-common-cls="text-center" data-width="4%"
					data-visible="false" data-fixed="left">序号</div>
				<!-- <div data-type="select" data-common-cls="text-center"
					data-allow-sort="false" data-width="4%" data-fixed="left">选择</div> -->
					<div data-field="userName" data-common-cls="text-center"
					data-allow-sort="true" data-width="10%" data-fixed="left">会员姓名</div>
				<div data-field="fullName" data-common-cls="text-center"
					data-allow-sort="true" data-width="10%" data-fixed="left">用户姓名</div>
				<div data-field="uuid" data-common-cls="text-center"
					data-allow-sort="true" data-width="10%">转入的钱包地址</div>
				<div data-common-cls="text-center" data-field="amount"
					data-allow-sort="true" data-width="4%">智联币数</div>
				
				<div data-field="time" data-date-format="yyyy/MM/dd hh:mm:ss"
					data-allow-sort="true" data-common-cls="text-center"
					data-width="4%">转账时间</div>
				
			</div>
		</div>
	</div>
</div>
